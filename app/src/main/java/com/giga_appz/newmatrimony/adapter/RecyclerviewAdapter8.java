package com.giga_appz.newmatrimony.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.giga_appz.newmatrimony.CustomOnClick;
import com.giga_appz.newmatrimony.Matcheslist;
import com.giga_appz.newmatrimony.R;

import java.util.List;

/**
 * Created by NANDHU on 23-09-2017.
 */
public class RecyclerviewAdapter8 extends RecyclerView.Adapter<RecyclerviewAdapter8.MyViewHolder> {


    List<Matcheslist> matcheslists;
    private Context context;
    private LayoutInflater inflater;
    private static CustomOnClick customOnClick;
    public RelativeLayout linear;
    public String imageopen;

    public RecyclerviewAdapter8(Context context, List<Matcheslist> idavaka) {
        this.context = context;
        this.matcheslists = idavaka;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.matches, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Matcheslist list=matcheslists.get(position);
        holder.age.setText(""+list.getAge());
        holder.education.setText(" , "+list.getEducation());
        holder.place.setText(""+list.getHome());
        holder.id.setText(""+list.getId());
        /*File imgFile = new  File("https://unaccommodating-com.000webhostapp.com/priestDetails/php/"+list.getPhoto());
        holder.profileMain.setImageURI(Uri.fromFile(imgFile));*/
        imageopen="http://www.mangallyam.com/public/images/profile/dp_170/"+list.getPhoto();
        //Picasso.with(context).load(imageopen).into(holder.profileMain);
        Glide.with(context)
                .load(imageopen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.profileMain);
        //holder.profileMain.setImageURI(Uri.parse(imageopen));



    }
    public void setOnItemClickListener(CustomOnClick customOnClick){
        RecyclerviewAdapter8.customOnClick=customOnClick;
    }
    @Override
    public int getItemCount() {
        return matcheslists.size();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        private TextView age, education, place,id;
        private ImageView profileEnlarge,profileMain;
        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.profileid);
            age = (TextView) itemView.findViewById(R.id.age);
            education = (TextView) itemView.findViewById(R.id.education);
            place = (TextView) itemView.findViewById(R.id.place);
            profileMain= (ImageView) itemView.findViewById(R.id.profile_image);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            profileMain.setOnClickListener(this);
            cardView.setOnClickListener(this);
            //itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            customOnClick.onItemClick(v,getAdapterPosition());

        }
    }
}
