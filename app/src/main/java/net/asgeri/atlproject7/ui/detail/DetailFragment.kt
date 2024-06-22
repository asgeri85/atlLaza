package net.asgeri.atlproject7.ui.detail

import android.os.Bundle
import android.view.View
import net.asgeri.atlproject7.base.BaseFragment
import net.asgeri.atlproject7.databinding.FragmentDetailBinding


class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val imageAdapter = ImageAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPagerDrtail.adapter = imageAdapter
        binding.dotsIndicator.attachTo(binding.viewPagerDrtail)


        val list = arrayListOf(
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSj0ZGR6zea2DlBEDAJbqYxwCqi8R7C9y1bXA&s",
            "https://imgv3.fotor.com/images/slider-image/A-blurry-low-quality-female-portrait-photo.jpg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQryF3MOO9tF930FRLt8L-M_VesvDk6ummUuQ&s"
        )



        imageAdapter.updateList(list)
    }

}