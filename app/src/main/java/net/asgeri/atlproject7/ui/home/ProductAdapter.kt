package net.asgeri.atlproject7.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.asgeri.atlproject7.databinding.ItemProductHomeBinding
import net.asgeri.atlproject7.model.ProductResponse

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val productList = arrayListOf<ProductResponse>()

    inner class ProductViewHolder(val itemProductHomeBinding: ItemProductHomeBinding) :
        RecyclerView.ViewHolder(itemProductHomeBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            ItemProductHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]

        holder.itemProductHomeBinding.product = item
    }

    fun updateList(newList:List<ProductResponse>){
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged() //DiffUtlill Callback
    }

}