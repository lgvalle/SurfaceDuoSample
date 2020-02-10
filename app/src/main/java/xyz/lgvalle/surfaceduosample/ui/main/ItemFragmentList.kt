package xyz.lgvalle.surfaceduosample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_item_list.view.*
import xyz.lgvalle.surfaceduosample.R

class ItemFragmentList : Fragment() {

    private val viewModel by lazy {
        activity?.let { ViewModelProviders.of(it).get(ItemViewModel::class.java) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.item_list.layoutManager = LinearLayoutManager(this@ItemFragmentList.context)

        viewModel?.apply {
            items.observe(viewLifecycleOwner, Observer { items ->
                view.item_list.adapter = ItemListViewAdapter(items) { item -> selectItem(item) }
            })
        }
    }

    companion object {
        fun newInstance() = ItemFragmentList()
    }
}
