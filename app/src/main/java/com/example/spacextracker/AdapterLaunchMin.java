package com.example.spacextracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spacextracker.Model.Launches;

import java.util.List;


public class AdapterLaunchMin extends RecyclerView.Adapter<AdapterLaunchMin.MyViewHolder> {
    private List<Launches> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView rocketName;
        public TextView launchDate;
        public TextView missionName;
        public MyViewHolder(View v) {
            super(v);
            launchDate = v.findViewById(R.id.launchDate);
            rocketName = v.findViewById(R.id.launchRocket);
            missionName = v.findViewById(R.id.missionName);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterLaunchMin(List<Launches> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterLaunchMin.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_launch, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.launchDate.setText(mDataset.get(position).getLaunch_date_utc().toString());
        //holder.rocketName.setText(mDataset.get(position).getRocket_name());
        holder.missionName.setText(mDataset.get(position).getMission_name());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}