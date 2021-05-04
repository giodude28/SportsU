package io.giodude.sportsu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.giodude.sportsu.Model.TeamModel;
import io.giodude.sportsu.R;

public class TeamIconAdapter extends RecyclerView.Adapter<TeamIconAdapter.ViewHolder> {
    Context context;
    public List<TeamModel.Team> data;
    public TeamModel.Team ee;

    public TeamIconAdapter(Context context, List<TeamModel.Team> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.teamicon,parent,false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(data.get(position));
        ee = data.get(position);
            Picasso.get().load(ee.getStrTeamBadge()).into(holder.img, new Callback() {
                @Override
                public void onSuccess() {
                    holder.progressBar.setVisibility(View.GONE);
                }
                @Override
                public void onError(Exception e) {
                    holder.img.setImageResource(R.drawable.ic_launcher_background);
                    holder.progressBar.setVisibility(View.GONE);
                }
            });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       ImageView img;
        ProgressBar progressBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.teamicon);
            progressBar = itemView.findViewById(R.id.progress_load_photo);
        }
    }
}
