package com.balsa.cocktailsapp.ui.mainActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.balsa.cocktailsapp.data.model.Cocktail
import com.balsa.cocktailsapp.databinding.CocktailItemViewBinding

class CocktailsRecycleViewAdapter(
    private val cocktails: List<Cocktail>,
    private val clickListener: (Cocktail) -> Unit
) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CocktailItemViewBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.cocktailItemCardView.setOnClickListener{
            clickListener(cocktails[position])
        }
        holder.bind(cocktails[position])
    }

    override fun getItemCount(): Int {
        return cocktails.size
    }

}

class MyViewHolder(val binding: CocktailItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cocktail : Cocktail) {
        binding.cocktalItemName.text = cocktail.strDrink
        binding.cocktailItemImageView.load(cocktail.strDrinkThumb)
    }
}