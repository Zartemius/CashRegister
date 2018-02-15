package com.example.artem.cashregister.sale.fragments.saleProcess.platesOfFastAccess;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.artem.cashregister.R;
import java.util.ArrayList;

public class PlateAdapter extends RecyclerView.Adapter<PlateAdapter.NewPlateViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
            mListener = listener;
    }
    private ArrayList<Plate> mListOfPlates;
    private OnItemClickListener mListener;

    public PlateAdapter(ArrayList<Plate> mListOfPlates){
            this.mListOfPlates = mListOfPlates;
    }

    public static class NewPlateViewHolder extends RecyclerView.ViewHolder {
        private final ImageView pictureOfProduct;
        private final TextView nameOfProduct;

        public NewPlateViewHolder(View viewItem, final OnItemClickListener listener) {
            super(viewItem);
            pictureOfProduct = itemView.findViewById(R.id.plate_view_holder_picture);
            nameOfProduct = itemView.findViewById(R.id.plate_view_holder_name_of_product);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
        @Override
        public void onBindViewHolder(NewPlateViewHolder holder, int position) {
            Plate currentItem = mListOfPlates.get(position);
            holder.pictureOfProduct.setImageResource(currentItem.getImageRecource());
            holder.nameOfProduct.setText(currentItem.getNameOfProduct());
        }

        @Override
        public NewPlateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plate_view_holder, parent, false);
            NewPlateViewHolder newPlateViewHolder = new NewPlateViewHolder(view,mListener);
            return newPlateViewHolder;
        }

    @Override
    public int getItemCount() {
        return mListOfPlates.size();
    }
}
