package com.udacity.shoestore.ui.showListing

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.card.MaterialCardView
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import com.udacity.shoestore.databinding.ShowItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.auth.LoginFragmentDirections
import com.udacity.shoestore.viewModel.SharedViewModel


class ShoeListingFragment : Fragment() {


    private lateinit var binding: FragmentShoeListingBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoeListingBinding.inflate( inflater, container, false)

        setHasOptionsMenu(true)
        initUi()
        binding.add.setOnClickListener {
            /**
             * to create new instance
             * */
            viewModel.shoe = Shoe()
            findNavController().navigate(ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailFragment())
        }
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController().apply {
            //used this to clear all backstack only login  screen in stack
            popBackStack()
        })
                || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    private fun initUi() {
        viewModel.getShoesItems().observe(viewLifecycleOwner) {
            it.forEach { shoe ->
                val viewItem = ShowItemBinding.inflate(LayoutInflater.from(context))
                viewItem.myShoe = shoe
                binding.mainItemsLayout.addView(viewItem.root)
            }
        }
    }
}