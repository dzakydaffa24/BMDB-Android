package com.example.bmdb.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bmdb.adapter.ItemAdapter
import com.example.bmdb.databinding.FragmentOverviewBinding
import com.example.bmdb.viewmodel.OverviewViewModel

/**
 * A simple [Fragment] subclass.
 */
class OverviewFragment : Fragment() {

    private lateinit var viewModel: OverviewViewModel
    private lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(OverviewViewModel::class.java)
        binding = FragmentOverviewBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val viewAdapter = ItemAdapter { item -> showDetail(item) }
        binding.recyclerView.adapter = viewAdapter

        viewModel.items.observe(viewLifecycleOwner, Observer { list ->
            viewAdapter.submitList(list)
        })

        return binding.root
    }

    fun showDetail(title: String) {
        Log.d("Movie", title)
    }

}
