package com.example.rickmorty.modules.modules.character_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.rickmorty.R
import com.example.rickmorty.databinding.ActivityCharacterDetailBinding
import com.example.rickmorty.modules.helpers.Constants
import com.example.rickmorty.modules.helpers.Utils
import com.example.rickmorty.modules.models.Character
import com.google.gson.Gson

class ActivityCharacterDetail : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding
    var character: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_detail)

        getExtras()
    }

    fun initComponents(characterJson: String?) {

        val character = Gson().fromJson(characterJson, Character::class.java)

        Glide.with(this)
            .load(character.image)
            .into(binding.imageCharacterDetail)
        binding.textNameCharacterDetail.text = character.name
        binding.textSpeciesCharacterDetail.text = character.species
        binding.imageStatusCharacterDetail.setImageResource(Utils.setStatusIcon(character.status))

        setGenderIcon(character)

    }

    fun getExtras() {
        character = intent.extras?.getString(Constants.BUNDLE_KEY_CHARACTER)
        initComponents(character)
    }

    fun setGenderIcon(character :Character){
        if (character?.gender?.equals(Constants.GENDER_MALE) == true) {
            binding.imageGenderCharacterDetail.setImageResource(R.drawable.ic_male)
        } else if (character?.gender?.equals(Constants.GENDER_MALE) == true) {
            binding.imageGenderCharacterDetail.setImageResource(R.drawable.ic_female)
        } else {
            binding.imageGenderCharacterDetail.setImageResource(R.drawable.ic_question_mark)
        }
    }
}