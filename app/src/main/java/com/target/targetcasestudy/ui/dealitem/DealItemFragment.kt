package com.target.targetcasestudy.ui.dealitem

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.databinding.FragmentDealItemBinding
import com.target.targetcasestudy.isValidPharse
import com.target.targetcasestudy.loadImage
import java.util.*

class DealItemFragment : Fragment() {

  // Used ViewBinding for ease and null safety, faster compilation
  lateinit var binding: FragmentDealItemBinding

  lateinit var dealItemViewModel: DealItemViewModel


  /**
   * Inflates the layout with View Binding, sets its lifecycle owner to the DealItemFragment
   * and sets up the RecyclerView with an adapter.
   */

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

     binding = FragmentDealItemBinding.inflate(inflater)

    /**
     * getting arguments from DealListFragment on selected Deal using safeargs
     */
    val dealItem = DealItemFragmentArgs.fromBundle(requireArguments()).dealItem

    dealItemViewModel = setupViewModel(dealItem)

    binding.apply {

      dealItemViewModel.selectedDealItem.observe(viewLifecycleOwner, {

        loadImage(dealItemImg, it.img_url)
        dealItemDetailTxt.text = if(!it.description.isValidPharse()) it.description.capitalize(Locale.ROOT) else "No Description available"
        dealItemSalePrice.text = it.salePrice?.display_string
        dealItemRegPrice.apply {
          paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
          text = it.regularPrice?.display_string
        }
        dealItemTitle.text = it.title.capitalize(Locale.ROOT)
      })
    }

    return binding.root
  }

  private fun setupViewModel(dealItem: DealItem): DealItemViewModel = ViewModelProvider(this,
    DealItemViewModelFactory (dealItem)
  ).get(DealItemViewModel::class.java)

}
