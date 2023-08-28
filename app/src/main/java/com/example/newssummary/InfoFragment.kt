package com.example.newssummary

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newssummary.data.model.Article
import com.example.newssummary.databinding.FragmentInfoBinding
import com.example.newssummary.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar



/**
 * A simple [Fragment] subclass.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFragment : Fragment() {
    private lateinit var fragmentInfoBinding: FragmentInfoBinding
    private lateinit var viewModel:NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {   //selected_article
        super.onViewCreated(view, savedInstanceState)
        fragmentInfoBinding = FragmentInfoBinding.bind(view)
        if(savedInstanceState != null) {
            val args: InfoFragmentArgs by navArgs()
            Log.i("selected", args.toString())
            val article = args.selectedArticle

            viewModel = (activity as MainActivity).viewModel

            fragmentInfoBinding.wvInfo.apply {
                webViewClient = WebViewClient()
                try {
                    if (article.url != null)
                        loadUrl(article.url)

                } catch (e: Exception) {
                    Toast.makeText(activity, "ERROR:" + article.title.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
            fragmentInfoBinding.fiSave.setOnClickListener {

                viewModel.saveArticle(article)
                Snackbar.make(view, "Saved Successfully!", Snackbar.LENGTH_LONG).show()
            }

            fragmentInfoBinding.suGet.setOnClickListener {
                getArticleSummary(article)

            }
        }

    }

    private fun getArticleSummary(article: Article){
        viewModel.getSummary(article)
        viewModel.summary.observe(viewLifecycleOwner) {
                            val bundle = Bundle().apply {
            putSerializable("summary_title", it)
                }
                findNavController().navigate(
                    R.id.action_infoFragment_to_summFragment,
                    bundle
                )

        }
    }


}
