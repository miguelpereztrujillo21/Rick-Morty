package com.example.rickmorty.modules.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.R
import com.example.rickmorty.databinding.ActivityMainBinding
import com.example.rickmorty.modules.adapters.CharacterAdapter
import com.example.rickmorty.modules.models.Character

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    var adapter: CharacterAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initObservers()

        viewModel.getCharacters();

    }

    fun initObservers() {
        viewModel.characters.observe(this, Observer {character ->
            character?.let {
                initRecycler(it.results)
            }
        })
    }

    fun initRecycler(characters: ArrayList<Character>?) {

        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        binding.recyclerMain.layoutManager = layoutManager
        adapter = CharacterAdapter(this,object : CharacterAdapter.ClickListener {
            override fun onClick(position: Int) {
            }
        })
       // Set Up Adapter
        adapter?.submitList(characters)
        binding.recyclerMain.adapter = adapter
    }
}