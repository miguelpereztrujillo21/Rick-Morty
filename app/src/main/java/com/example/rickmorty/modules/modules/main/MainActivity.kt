package com.example.rickmorty.modules.modules.main

import android.content.Intent
import android.content.res.ColorStateList
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
import com.google.android.material.chip.Chip
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    var adapter: CharacterAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.lifecycleOwner = this
        binding.mainActivity = this
        binding.mainViewModel = viewModel

        initObservers()
        initListeners()
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

        viewModel.filterGender.observe(this) {
            viewModel.getCharacters()
        }

        viewModel.filterStatus.observe(this) {
            viewModel.getCharacters()
        }
        viewModel.error.observe(this, Observer {
            if (it.equals(Constants.NOT_RESULTS)) {
                Utils.showDialog(
                    this,
                    getString(R.string.error_dialog_close_title_not_results),
                    "",
                    this,
                    false
                )
            } else {
                Utils.showDialog(
                    this,
                    getString(R.string.error_dialog_title),
                    getString(R.string.error_dialog_description),
                    this,
                    true
                )
            }
        })
    }

    private fun initListeners() {
        binding.apply {
            initChip(binding.chipAliveMain, Constants.STATUS_ALIVE, true)
            initChip(binding.chipDeadMain, Constants.STATUS_DEAD, true)
            initChip(binding.chipUnknowMain, Constants.STATUS_UNKNOW, true)

            initChip(binding.chipGenderMaleMain, Constants.GENDER_MALE, false)
            initChip(binding.chipGenderFemaleMain, Constants.GENDER_FEMALE, false)
            initChip(binding.chipGenderUnknowMain, Constants.STATUS_UNKNOW, false)
        }
    }

    private fun initChip(chip: Chip, filter: String, isStatus: Boolean) {
        chip.setOnCheckedChangeListener { _, isChecked ->
            chip.chipBackgroundColor =
                ColorStateList.valueOf(
                    if (isChecked) getColor(R.color.light_grey) else getColor(
                        R.color.white
                    )
                )
            if (isStatus && isChecked) {
                viewModel.filterStatus.value = filter
            } else if (isStatus){
                viewModel.filterStatus.value = ""
            }else if (isChecked && !isStatus) {
                viewModel.filterGender.value = filter
            } else {
                viewModel.filterGender.value = ""
            }
        }
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
                if (dy != 0 && !viewModel.isLoading && layoutManager.findLastVisibleItemPosition() >= layoutManager.itemCount - 5) {
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
        })
    }
}