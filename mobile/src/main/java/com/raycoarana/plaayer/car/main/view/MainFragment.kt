package com.raycoarana.plaayer.car.main.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.main.viewmodel.MainViewModel
import com.raycoarana.plaayer.core.di.DaggerCarFragment
import com.raycoarana.plaayer.core.ui.MarginItemDecorator
import com.raycoarana.plaayer.databinding.CarFragmentMainBinding
import javax.inject.Inject

class MainFragment : DaggerCarFragment() {

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var binding: CarFragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.car_fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = DataBindingUtil.bind(view, component) ?: throw Exception("Invalid view")
        binding.items.addItemDecoration(MarginItemDecorator(context!!, R.dimen.grid_horizontal_space, R.dimen.grid_vertical_space))
        binding.items.layoutManager = GridLayoutManager(context, COLUMNS)
        binding.viewModel = viewModel
    }

    override fun onComponentReady() {
        component.inject(this)
    }

    companion object {
        const val COLUMNS = 3
    }
}
