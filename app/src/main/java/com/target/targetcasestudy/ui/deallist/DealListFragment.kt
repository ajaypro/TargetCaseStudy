package com.target.targetcasestudy.ui.deallist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.network.NetworkService
import com.target.targetcasestudy.databinding.FragmentDealListBinding
import com.target.targetcasestudy.ui.DealItemAdapter
import com.target.targetcasestudy.ui.DealItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DealListFragment : Fragment() {

    lateinit var dealItemAdapter: DealItemAdapter

    lateinit var binding: FragmentDealListBinding

    lateinit var dealsListViewModel: DealsListViewModel

    @Inject
    lateinit var networkService: NetworkService

    /**
     * Inflates the layout with View Binding, sets its lifecycle owner to the DealListFragment
     * and sets up the RecyclerView with an adapter.
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dealsListViewModel = setupViewModel()

        dealItemAdapter = DealItemAdapter(DealItemClickListener { dealItem ->

            dealsListViewModel.displayToDealItem(dealItem)
        })

        binding = FragmentDealListBinding.inflate(inflater)

        binding.deallistRv.apply {
            adapter = dealItemAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        dealsListViewModel.status.observe(viewLifecycleOwner, {
            when (it) {
                DealListStatus.LOADING -> {
                    binding.statusImage.visibility = View.VISIBLE
                    binding.statusImage.setImageResource(R.drawable.loading_animation)
                }
                DealListStatus.ERROR -> {
                    binding.statusImage.visibility = View.VISIBLE
                    binding.statusImage.setImageResource(R.drawable.ic_connection_error)
                }
                DealListStatus.DONE -> {
                    binding.statusImage.visibility = View.GONE

                    dealsListViewModel.dealList.observe(viewLifecycleOwner, { deals ->

                        dealItemAdapter.submitList(deals.products)
                    })

                    dealsListViewModel.navigateToDealItem.observe(
                        viewLifecycleOwner, {
                            it?.let { dealItem ->
                                this.findNavController().navigate(
                                    DealListFragmentDirections.actionDealListFragmentToDealItemFragment(
                                        dealItem
                                    )
                                )
                                dealsListViewModel.doneNavigating()
                            }
                        })
                }
            }
        })

        return binding.root
    }

    private fun setupViewModel(): DealsListViewModel = ViewModelProvider(
        this,
        DealsListViewModelFactory(networkService)
    ).get(DealsListViewModel::class.java)
}
