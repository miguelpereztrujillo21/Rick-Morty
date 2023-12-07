package com.example.rickmorty.modules.modules.main

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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

        viewModel.getCharacters()
    }

    private fun initObservers() {
        viewModel.characters.observe(this) { character ->
            character?.let {
                adapter?.submitList(it)
            }
        }
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
        viewModel.error.observe(this) {
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
        }
    }

    private fun initListeners() {
        initChip(binding.layoutFilters.chipAliveMain, Constants.STATUS_ALIVE, true)
        initChip(binding.layoutFilters.chipDeadMain, Constants.STATUS_DEAD, true)
        initChip(binding.layoutFilters.chipUnknowMain, Constants.STATUS_UNKNOW, true)
        initChip(binding.layoutFilters.chipGenderMaleMain, Constants.GENDER_MALE, false)
        initChip(binding.layoutFilters.chipGenderFemaleMain, Constants.GENDER_FEMALE, false)
        initChip(binding.layoutFilters.chipGenderUnknowMain, Constants.STATUS_UNKNOW, false)
        binding.filtersChip.apply {
            setOnCheckedChangeListener { _, isChecked ->
                val colorChip = if (isChecked) R.color.light_grey else R.color.black
                chipBackgroundColor = ColorStateList.valueOf(getColor(colorChip))
                binding.layoutFilters.chipContainer.visibility = if (isChecked)View.VISIBLE else View.GONE
            }
        }
    }

    private fun initChip(chip: Chip, filter: String, isStatus: Boolean) {
        chip.setOnCheckedChangeListener { _, isChecked ->
            val colorChip = if (isChecked) R.color.light_grey else R.color.white
            chip.chipBackgroundColor = ColorStateList.valueOf(getColor(colorChip))
            viewModel.onChipCheckedChanged(isChecked, filter, isStatus)
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
                if (dy != 0&&!viewModel.isLoading && layoutManager.findLastVisibleItemPosition() >= layoutManager.itemCount - 5) {
                    viewModel.isLoading = true
                    viewModel.handlePagination()
                }
            }
        })
    }
}