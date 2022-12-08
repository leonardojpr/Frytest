package com.leonardojpr.frytest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.leonardojpr.frytest.databinding.FragmentFirstBinding
import com.leonardojpr.frytest.domain.api.response.MealsItem
import com.leonardojpr.frytest.ui.home.adapter.MealsAdapter
import com.leonardojpr.frytest.ui.home.mvvm.MealsViewModel
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val viewModel : MealsViewModel by lazy {
        ViewModelProvider(requireActivity())[MealsViewModel::class.java]
    }

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        viewModel.getMeals()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}