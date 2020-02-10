package com.abadyyy.side_project.nearby.features.ventures

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abadyyy.side_project.nearby.R
import com.abadyyy.side_project.nearby.databinding.ActivityVenuesBinding
import com.abadyyy.side_project.nearby.shared.di.DaggerAwareViewModelFactory
import com.abadyyy.side_project.nearby.shared.store.remote.datasource.LocationAPI
import com.abadyyy.side_project.nearby.shared.ui.bindingadapters.VenuesRecyclerAdapter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class VenuesListActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: DaggerAwareViewModelFactory
    lateinit var binding: ActivityVenuesBinding
    private var currentMode = LocationAPI.Mode.RealTime
    private val adapter: VenuesRecyclerAdapter = VenuesRecyclerAdapter()
    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(VenuesListViewModel::class.java)
    }
    private val locationApi = LocationAPI(this, ::getMode, {
        viewModel.refreshData(it.latitude, it.longitude)
    }, {
        handleLocationError(it)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityVenuesBinding>(this, R.layout.activity_venues)
                .apply {
                    viewModel = this@VenuesListActivity.viewModel
                    lifecycleOwner = this@VenuesListActivity
                }
        locationApi.refreshLocation()
        initViews()
        observeData()
    }

    private fun observeData() {
        viewModel.ventures.observe(this, Observer {
            adapter.setItems(it)
        })
    }

    private fun initViews() {
        binding.modeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                currentMode = LocationAPI.Mode.RealTime
                locationApi.refreshLocation()
            } else {
                currentMode = LocationAPI.Mode.Single
            }
        }
        binding.venuesRv.apply {
            adapter = this@VenuesListActivity.adapter
            layoutManager = LinearLayoutManager(this@VenuesListActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }


    fun getMode(): LocationAPI.Mode {
        return currentMode
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        locationApi.onActivityResult(requestCode, resultCode, data)
    }

    fun handleLocationError(error: LocationAPI.Error) {
        //TODO handle this later.
    }
}
