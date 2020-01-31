package com.example.reuesview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray


class MyRecyclerAdapter (context: Context,fm: FragmentManager, val dataSource: JSONArray) : RecyclerView.Adapter<MyRecyclerAdapter.Holder>() {



    private val thiscontext : Context = context
    private val fragment: FragmentManager = fm

    class Holder(view : View) : RecyclerView.ViewHolder(view) {

        private val View = view;

        lateinit var layout : LinearLayout
        lateinit var titleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var image: ImageView

        fun Holder(){

            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            titleTextView = View.findViewById<View>(R.id.tv_name) as TextView
            detailTextView = View.findViewById<View>(R.id.tv_description) as TextView
            image = View.findViewById<View>(R.id.imgV) as ImageView

        }


    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recy_layout, parent, false))
    }


    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.Holder()

        holder.titleTextView.setText( dataSource.getJSONObject(position).getString("firstname").toString() +" "+dataSource.getJSONObject(position).getString("lastname").toString() )
        holder.detailTextView.setText( dataSource.getJSONObject(position).getString("position").toString() )

        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.image)



      holder.layout.setOnClickListener{

          Toast.makeText(thiscontext,holder.titleTextView.text.toString(),Toast.LENGTH_SHORT).show()

          val head:String = dataSource.getJSONObject(position).getString("firstname").toString() +" "+dataSource.getJSONObject(position).getString("lastname").toString()
          val body:String = dataSource.getJSONObject(position).getString("position").toString()
          val img:String = dataSource.getJSONObject(position).getString("image").toString()
          val skill:String = "HTML Language: "+dataSource.getJSONObject(position).getString("html").toString()+"\n"+
                                "CSS Language: "+dataSource.getJSONObject(position).getString("css").toString()+"\n"+
                                "Php Language: "+dataSource.getJSONObject(position).getString("php").toString()+"\n"+
                                "Javascript Language: "+dataSource.getJSONObject(position).getString("javascript").toString()+"\n"+
                                "Sql Language: "+dataSource.getJSONObject(position).getString("sql").toString()+"\n"

          val item_selected = item_selected().newInstance(head,body,img,skill)
          val transaction : FragmentTransaction = fragment.beginTransaction()
          transaction.replace(R.id.layout, item_selected,"item_selected")
          transaction.addToBackStack("item_selected")
          transaction.commit()
           Toast.makeText(thiscontext,holder.titleTextView.text.toString(),Toast.LENGTH_SHORT).show()
       }

    }







}
