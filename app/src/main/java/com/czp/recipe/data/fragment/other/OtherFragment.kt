package com.czp.recipe.data.fragment.other

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.czp.recipe.R
import com.czp.recipe.databinding.FragmentOtherBinding


class OtherFragment : Fragment() {
    private lateinit var binding: FragmentOtherBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOtherBinding.inflate(inflater)

        return binding.root
    }
}