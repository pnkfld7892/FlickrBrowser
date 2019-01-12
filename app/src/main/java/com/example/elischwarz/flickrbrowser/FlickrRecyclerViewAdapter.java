package com.example.elischwarz.flickrbrowser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrRecyclerViewAdapter.FlickrImageViewHolder> {
    private static final String TAG = "FlickrRecyclerViewAdapt";
    private List<Photo> mPhotoList;
    private Context mContext;

    public FlickrRecyclerViewAdapter(Context context, List<Photo> photoList) {
        mPhotoList = photoList;
        mContext = context;
    }

    @NonNull
    @Override
    public FlickrImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Called by layout manager when it needs a new view
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, parent, false);


        return new FlickrImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrImageViewHolder holder, int position) {
        //called by the layout manager when new data is requested for an existing row
        Photo photoItem = mPhotoList.get(position);
        Log.d(TAG, "onBindViewHolder: " + photoItem.getTitle() + " --> " + position);
        Picasso.get().load(photoItem.getImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);

        holder.title.setText(photoItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return ((mPhotoList != null) && (mPhotoList.size() != 0) ? mPhotoList.size() : 0);
    }

    void loadNewData(List<Photo> newPhotos){
        mPhotoList = newPhotos;
        notifyDataSetChanged();
    }

    public Photo getPhoto(int position){
        return ((mPhotoList != null) && (mPhotoList.size() != 0) ? mPhotoList.get(position) : null);
    }

    static class FlickrImageViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "FlickrImageViewHolder";
        ImageView thumbnail;
        TextView title;

        public FlickrImageViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "FlickrImageViewHolder: ctor");
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.title = (TextView) itemView.findViewById(R.id.title);
        }
    }

}

