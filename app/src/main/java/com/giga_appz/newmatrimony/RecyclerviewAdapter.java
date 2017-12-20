package com.giga_appz.newmatrimony;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

/**
 * Created by NANDHU on 23-09-2017.
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder> {


    List<Matcheslist> idavaka;
    private Context context;
    private LayoutInflater inflater;
    private static CustomOnClick customOnClick;
    public RelativeLayout linear;
    public String imageopen;

    public RecyclerviewAdapter(Context context, List<Matcheslist> idavaka) {
        this.context = context;
        this.idavaka = idavaka;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = inflater.inflate(R.layout.matches, parent, false);
        linear= (RelativeLayout) rootView.findViewById(R.id.linearList);
        linear.setBackgroundColor(rootView.getResources().getColor(R.color.colorPrimary));
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Matcheslist list=idavaka.get(position);
        holder.name.setText(""+list.getName());
        holder.email.setText("Email: "+list.getEmail());
        holder.mobile.setText("Mobile: "+list.getMobile());

        /*File imgFile = new  File("https://unaccommodating-com.000webhostapp.com/priestDetails/php/"+list.getPhoto());
        holder.profileMain.setImageURI(Uri.fromFile(imgFile));*/
        imageopen="https://unaccommodating-com.000webhostapp.com/priestDetails/php/"+list.getPhoto();
        //Picasso.with(context).load(imageopen).into(holder.profileMain);



    }
    public void setOnItemClickListener(CustomOnClick customOnClick){
        RecyclerviewAdapter.customOnClick=customOnClick;
    }
    @Override
    public int getItemCount() {
        return idavaka.size();

    }
    private void customDialog(){
        /*Dialog dialog;

        private void showDialog() {
            // custom dialog
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.custom_dialog);

            // set the custom dialog components - text, image and button
            ImageButton close = (ImageButton) dialog.findViewById(R.id.btnClose);
            Button buy = (Button) dialog.findViewById(R.id.btnBuy);

            // Close Button
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    //TODO Close button action
                }
            });

            // Buy Button
            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    //TODO Buy button action
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.show();
        }*/
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name, email, mobile;
        private NetworkImageView imageview;
        private ProgressBar ratingbar;
        private CircularImageView profileEnlarge,profileMain;
        private Button call;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.age);
            mobile = (TextView) itemView.findViewById(R.id.place);
            profileEnlarge= (CircularImageView) itemView.findViewById(R.id.profile_image);
            profileMain= (CircularImageView) itemView.findViewById(R.id.profile_image);
            profileEnlarge.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            customOnClick.onItemClick(v,getAdapterPosition());

        }
    }
}
