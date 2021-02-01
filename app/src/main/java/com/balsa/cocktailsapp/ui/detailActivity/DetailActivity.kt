package com.balsa.cocktailsapp.ui.detailActivity

import android.content.Intent
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.balsa.cocktailsapp.CocktailsApplication
import com.balsa.cocktailsapp.data.model.Cocktail
import com.balsa.cocktailsapp.databinding.ActivityDetailBinding
import javax.inject.Inject


class DetailActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityDetailBinding
    private lateinit var detailActivityViewModel: DetailActivityViewModel
    private var cocktailId = "0"

    @Inject
    lateinit var detailActivityViewModelFactory: DetailActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityDetailBinding.inflate(layoutInflater)
        injectDagger()
        setExtrasFromIntent()
        setUpViewModel()
        setContentView(mainBinding.root)
        observe()
    }

    private fun observe() {
        detailActivityViewModel.currentCocktail.observe(this, Observer { cocktail ->
            bind(cocktail)
        })
    }

    private fun bind(cocktail: Cocktail) {
        setImageSize()
        mainBinding.apply {
            cocktailName.text = cocktail.strDrink
            cocktailImage.load(cocktail.strDrinkThumb)
            cocktailRecepie.text = cocktail.strInstructions
        }
        setVideoButton(cocktail)
        setIngridients(cocktail)
    }

    private fun injectDagger() {
        CocktailsApplication.cocktailComponent.inject(this)
    }

    private fun setIngridients(cocktail: Cocktail) {
        val allIngridients = listOf(
            cocktail.strMeasure1 + " " + cocktail.strIngredient1 + "\n",
            cocktail.strMeasure2 + " " + cocktail.strIngredient2 + "\n",
            cocktail.strMeasure3 + " " + cocktail.strIngredient3 + "\n",
            cocktail.strMeasure4 + " " + cocktail.strIngredient4 + "\n",
            cocktail.strMeasure5 + " " + cocktail.strIngredient5 + "\n",
            cocktail.strMeasure6 + " " + cocktail.strIngredient6 + "\n",
            cocktail.strMeasure7 + " " + cocktail.strIngredient7 + "\n",
            cocktail.strMeasure8 + " " + cocktail.strIngredient8 + "\n",
            cocktail.strMeasure9 + " " + cocktail.strIngredient9 + "\n",
            cocktail.strMeasure10 + " " + cocktail.strIngredient10 + "\n",
            cocktail.strMeasure11 + " " + cocktail.strIngredient11 + "\n",
            cocktail.strMeasure12 + " " + cocktail.strIngredient12 + "\n",
            cocktail.strMeasure13 + " " + cocktail.strIngredient13 + "\n",
            cocktail.strMeasure14 + " " + cocktail.strIngredient14 + "\n",
            cocktail.strMeasure15 + " " + cocktail.strIngredient15 + "\n",
        )
        for( s in allIngridients) {
            if(!s.contains("null")) {
                mainBinding.cocktailIngridients.append(s)
            }
        }
    }

    private fun setVideoButton(cocktail: Cocktail) {
        if (cocktail.strVideo.isNullOrEmpty()) {
            mainBinding.watchVideo.isVisible = false
        } else {
            mainBinding.watchVideo.setOnClickListener{
                watchYoutubeVideo(cocktail.strVideo)
            }
        }
    }
    private fun watchYoutubeVideo(id: String) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(id)
            )
        )
    }

    private fun setImageSize() {
        mainBinding.cocktailRelativeLayout.minimumHeight = mainBinding.root.width
        mainBinding.cocktailImage.minimumHeight = mainBinding.root.width
    }

    private fun setExtrasFromIntent() {
        val extras = intent.extras
        if (extras != null) {
            cocktailId = extras.getString("cocktailId")!!
        }
    }

    private fun setUpViewModel() {
        detailActivityViewModel = ViewModelProvider(this, detailActivityViewModelFactory).get(DetailActivityViewModel::class.java)
        detailActivityViewModel.fillInformationById(cocktailId)
    }
}