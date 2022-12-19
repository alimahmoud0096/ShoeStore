package com.udacity.shoestore.ui.showDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewModel.SharedViewModel
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController


class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding


    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoeDetailBinding.inflate(inflater, container, false)

        with(binding) {

            viewModel = sharedViewModel
            btnSave.setOnClickListener {
                //validate if data entered
                if (validateInputs()) {
                    //add item
                    sharedViewModel.addNewItem()
                    findNavController().popBackStack()
                }
            }

            btnCancel.setOnClickListener {
                findNavController().popBackStack()
            }

        }
        return binding.root
    }

    fun validateInputs(): Boolean {
        with(binding) {
            when {
                editshoeName.text.isNullOrEmpty() -> {
                    editshoeName.error = getText(R.string.Required)
                    return false
                }
                editCompany.text.isNullOrEmpty() -> {
                    editCompany.error = getText(R.string.Required)
                    return false
                }
                editDescription.text.isNullOrEmpty() -> {
                    editDescription.error = getText(R.string.Required)
                    return false
                }
                editshoeSize.text.isNullOrEmpty() -> {
                    editshoeSize.error = getText(R.string.Required)
                    return false
                }
                else -> {
                    return true
                }
            }
        }

    }
}