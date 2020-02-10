package com.abadyyy.side_project.nearby.shared.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.abadyyy.side_project.nearby.R

class ServerErrorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):
    ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflateLayout()
    }

    private fun inflateLayout() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.component_server_error, this, true)
    }
}