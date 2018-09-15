package com.raycoarana.plaayer.car.tv.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.apps.auto.sdk.ui.FabDrawable
import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.tv.viewmodel.LiveTvChannelGridViewModel
import com.raycoarana.plaayer.core.di.DaggerCarFragment
import com.raycoarana.plaayer.core.ui.GridMarginItemDecorator
import com.raycoarana.plaayer.databinding.CarFragmentLiveTvChannelGridBinding
import javax.inject.Inject

class LiveTvChannelGridFragment : DaggerCarFragment() {

    @Inject
    lateinit var viewModel: LiveTvChannelGridViewModel

    private lateinit var binding: CarFragmentLiveTvChannelGridBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.car_fragment_live_tv_channel_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = CarFragmentLiveTvChannelGridBinding.bind(view, component)
        binding.items.addItemDecoration(GridMarginItemDecorator(context!!, R.dimen.grid_horizontal_space, R.dimen.grid_vertical_space))
        binding.items.layoutManager = GridLayoutManager(context, COLUMNS)
        val fabDrawable = FabDrawable(context)
        fabDrawable.setFabColor(R.color.colorAccent)
        binding.change.background = fabDrawable
        binding.viewModel = viewModel
    }

    override fun onComponentReady() {
        component.inject(this)
    }

    companion object {
        const val COLUMNS = 6
    }
}
