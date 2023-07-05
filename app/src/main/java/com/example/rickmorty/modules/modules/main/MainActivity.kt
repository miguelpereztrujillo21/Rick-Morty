package com.example.rickmorty.modules.modules.main

import android.content.Intent
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
import com.example.rickmorty.modules.helpers.Constants
import com.example.rickmorty.modules.models.Character
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
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initObservers()

        viewModel.getCharacters();

    }

    fun initObservers() {
        viewModel.characters.observe(this, Observer { character ->
            character?.let {
                setUpRecycler(it.results)
            }
        })
        viewModel.error.observe(this,{
            //TODO
        })
    }

    fun setUpRecycler(characters: ArrayList<Character>?) {

        val context = this;
        binding.recyclerMain.layoutManager = GridLayoutManager(this, 2)
        // Set Up Adapter
        adapter = CharacterAdapter(this,
            object : CharacterAdapter.ClickListener {

            override fun onClick(position: Int) {
                val intent = Intent(context, ActivityCharacterDetail::class.java)
                val bundle = Bundle()
                bundle.putString(
                    Constants.BUNDLE_KEY_CHARACTER,
                    Gson().toJson(characters?.get(position))
                )
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
        adapter?.submitList(characters)
        binding.recyclerMain.adapter = adapter
    }
}