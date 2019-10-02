package com.example.masterpostdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.masterpostdetail.model.Post
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: Post? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.get(ARG_OBJECT) as Post?
                activity?.toolbar_layout?.title = item?.title
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the dummy content as text in a TextView.
        rootView.item_detail.text = item?.body

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_OBJECT = "item_object"
        const val ARG_ITEM_ID = "item_id"
        const val ARG_POST = "post_object"

    }
}
