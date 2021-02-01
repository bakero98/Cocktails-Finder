package com.balsa.cocktailsapp.ui.mainActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.balsa.cocktailsapp.CocktailsApplication
import com.balsa.cocktailsapp.R
import com.balsa.cocktailsapp.data.model.Cocktail
import com.balsa.cocktailsapp.databinding.ActivityMainBinding
import com.balsa.cocktailsapp.ui.detailActivity.DetailActivity
import javax.inject.Inject


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainActivityViewModel : MainActivityViewModel

    @Inject
    lateinit var mainActivityViewModelFactory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        injectDagger()
        setUpViewModel()
        initRecycleView()
        setContentView(binding.root)
        createToolbar()
    }

    private fun createToolbar() {
        binding.toolbar.inflateMenu(R.menu.search_menu)

        val search = binding.toolbar.menu.findItem(R.id.menuSearch)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = false
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        if (p0 != null) {
            mainActivityViewModel.searchQuery.value = p0
            mainActivityViewModel.resetCocktails()
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if (p0 != null) {
            mainActivityViewModel.searchQuery.value = p0
            mainActivityViewModel.resetCocktails()
        }
        return true
    }

    private fun setUpViewModel() {
        mainActivityViewModel = ViewModelProvider(this, mainActivityViewModelFactory).get(
            MainActivityViewModel::class.java)
        mainActivityViewModel.resetCocktails()
    }

    private fun injectDagger() {
        CocktailsApplication.cocktailComponent.inject(this)
    }

    fun initRecycleView() {
        binding.cocktailsRecycleView.layoutManager = LinearLayoutManager(binding.root.context)
        displayCocktails()
    }

    private fun displayCocktails() {
        mainActivityViewModel.cocktails.observe(this, Observer { cocktails ->
            if (cocktails != null) {
                binding.cocktailsRecycleView.adapter =
                    CocktailsRecycleViewAdapter(cocktails) { selectedCocktail: Cocktail ->
                        openDetails(selectedCocktail)
                    }
            }
        })
    }

    private fun openDetails(cocktail: Cocktail) {
        val intent = Intent(baseContext, DetailActivity::class.java)
        intent.putExtra("cocktailId", cocktail.idDrink)
        startActivity(intent)
    }

}