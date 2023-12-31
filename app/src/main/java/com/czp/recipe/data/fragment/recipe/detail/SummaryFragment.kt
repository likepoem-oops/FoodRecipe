package com.czp.recipe.data.fragment.recipe.detail

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.czp.recipe.R
import com.czp.recipe.databinding.FragmentSummaryBinding
import org.jsoup.Jsoup

class SummaryFragment(private val summary: String) : Fragment() {
    private lateinit var binding: FragmentSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSummaryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        // binding.summaryTextView.text = Html.fromHtml(summary)
        binding.summaryTextView.text = Jsoup.parse(summary).text()

    }
}