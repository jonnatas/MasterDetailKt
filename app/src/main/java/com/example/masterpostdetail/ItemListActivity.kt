package com.example.masterpostdetail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.masterpostdetail.model.Post
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list_content.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(item_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val call = RetrofitInitializer().noteService().getAllPosts()

        call.enqueue(object : Callback<List<Post>?> {
            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                Log.e("onFailure error", t.message)
            }

            override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
                response.body()?.let {
                    val posts: List<Post> = it
                    configureList(posts, recyclerView)
                }
            }

        })

    }

    private fun configureList(
        posts: List<Post>,
        recyclerView: RecyclerView
    ) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, posts, twoPane)

    }

    class SimpleItemRecyclerViewAdapter(
        private val parentActivity: ItemListActivity,
        private val mPosts: List<Post>,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = mPosts[position]
            holder.idView.text = item.id.toString()
            holder.contentView.text = item.title

            with(holder.itemView) {
                tag = item
                setOnClickListener(View.OnClickListener {
                    if (twoPane) {
                        val fragment = ItemDetailFragment().apply {
                            arguments = Bundle().apply {
                                putString(ItemDetailFragment.ARG_ITEM_ID, item.id.toString())
                                putSerializable(ItemDetailFragment.ARG_OBJECT, item)
                            }
                        }
                        parentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit()
                    } else {
                        val intent = Intent(it.context, ItemDetailActivity::class.java).apply {
                            putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id)
                            putExtra(ItemDetailFragment.ARG_POST, item.title)
                            putExtra(ItemDetailFragment.ARG_OBJECT, item)
                        }
                        it.context.startActivity(intent)
                    }
                })
            }
        }

        override fun getItemCount() = mPosts.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView: TextView = view.content
        }
    }
}
