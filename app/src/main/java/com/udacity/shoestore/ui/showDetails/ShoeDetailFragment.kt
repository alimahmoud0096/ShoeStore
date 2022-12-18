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


    private val viewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.viewModel = viewModel
        binding.btnSave.setOnClickListener {
            //validate if data entered
            if (validateInputs()) {
                //add item
                viewModel.addNewItem()
                findNavController().popBackStack()
            }
        }

        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }


        return binding.root
    }

    fun validateInputs(): Boolean {
        when {
            binding.editshoeName.text.isNullOrEmpty() -> {
                binding.editshoeName.error = getText(R.string.Required)
                return false
            }
            binding.editCompany.text.isNullOrEmpty() -> {
                binding.editCompany.error = getText(R.string.Required)
                return false
            }
            binding.editDescription.text.isNullOrEmpty() -> {
                binding.editDescription.error = getText(R.string.Required)
                return false
            }
            binding.editshoeSize.text.isNullOrEmpty() -> {
                binding.editshoeSize.error = getText(R.string.Required)
                return false
            }
            else -> {
                return true
            }
        }

    }
}