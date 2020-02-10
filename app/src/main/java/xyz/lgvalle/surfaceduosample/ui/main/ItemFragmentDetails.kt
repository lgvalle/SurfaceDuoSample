package xyz.lgvalle.surfaceduosample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_item_detail.view.*
import xyz.lgvalle.surfaceduosample.R

class ItemFragmentDetails : Fragment() {
    private var item: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getSerializable("item") as Item
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            this.tvTitle.text = item?.id
            this.tvBody.text = item?.content
        }
    }

    companion object {
        fun newInstance(item: Item) =
            ItemFragmentDetails().apply {
                arguments = Bundle().apply { putSerializable("item", item) }
            }
    }
}
