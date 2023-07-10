package com.example.rickmorty.modules.modules.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.R
import com.example.rickmorty.databinding.ActivityMainBinding
import com.example.rickmorty.modules.adapters.CharacterAdapter
import com.example.rickmorty.modules.helpers.Constants
import com.example.rickmorty.modules.helpers.Utils
import com.example.rickmorty.modules.modules.character_detail.ActivityCharacterDetail
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    var adapter: CharacterAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.lifecycleOwner = this
        binding.mainActivity = this
        binding.mainViewModel = viewModel

        initObservers()

        setUpRecycler()

        viewModel.getCharacters();


    }

    private fun initObservers() {
        viewModel.characters.observe(this, Observer { character ->
            character?.let {
                adapter?.submitList(it)
            }
        })
        viewModel.filterText.observe(this) {
            viewModel.getCharacters()
            viewModel.currentPage = 1
        }
        viewModel.error.observe(this, Observer {
            Utils.showDialog(this,getString(R.string.error_dialog_title),getString(R.string.error_dialog_description),this)
        })
    }

    private fun setUpRecycler() {
        val layoutManager = GridLayoutManager(this, 2)

        binding.recyclerMain.layoutManager = layoutManager

        adapter = CharacterAdapter(this, object : CharacterAdapter.ClickListener {
            override fun onClick(position: Int) {
                val intent = Intent(this@MainActivity, ActivityCharacterDetail::class.java)
                val bundle = Bundle()
                bundle.putString(
                    Constants.BUNDLE_KEY_CHARACTER,
                    Gson().toJson(adapter?.getCharacterPosition(position))
                )
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
        binding.recyclerMain.adapter = adapter

        binding.recyclerMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (!viewModel.isLoading && lastVisibleItemPosition >= totalItemCount - 5) {
                    loadMoreCharacters()
                }
            }
        })
    }


    private fun loadMoreCharacters() {
        viewModel.isLoading = true
        viewModel.currentPage.let { currentPage ->
            viewModel.maxPages?.let { maxPages ->
                if (maxPages > currentPage) {
                    viewModel.currentPage += 1
                    viewModel.getCharacters()
                    viewModel.cacheFilteredCharacters = true
                }else{
                    viewModel.currentPage = 1
                }
            }
        }


    }

}