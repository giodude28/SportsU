package io.giodude.sportsu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.giodude.sportsu.Model.PastEventModel;
import io.giodude.sportsu.R;
import io.giodude.sportsu.View.PastView;

public class PastEventAdapter extends RecyclerView.Adapter<PastEventAdapter.ViewHolder> {
    Context context;
    public List<PastEventModel.Event> data;
    public PastEventModel.Event ee;

    public PastEventAdapter(Context context, List<PastEventModel.Event> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.pastitem,parent,false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(data.get(position));
        ee = data.get(position);
        holder.date.setText(ee.getDateEvent());
//        holder.time.setText(ee.getStrTime());
        holder.league.setText(ee.getStrLeague());
        holder.season.setText(ee.getStrSeason());
        holder.home.setText(ee.getStrHomeTeam());
        holder.away.setText(ee.getStrAwayTeam());
        holder.venue.setText(ee.getStrVenue());
        if (ee.getIntHomeScore() == null){
            holder.homescore.setText("0");
        }else {
            holder.homescore.setText(ee.getIntHomeScore().toString());
        }

        if (ee.getIntAwayScore() == null){
            holder.awayscore.setText("0");
        }else {
            holder.awayscore.setText(ee.getIntAwayScore().toString());
        }

        Picasso.get().load(ee.getStrThumb()).into(holder.img, new Callback() {
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
        public TextView date;
        public TextView time;
        public TextView league;
        public TextView season;
        public TextView home;
        public TextView away;
        public TextView venue;
        public TextView homescore;
        public TextView awayscore;
        public ImageView img;
        ProgressBar progressBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            date = itemView.findViewById(R.id.eventdate);
            league = itemView.findViewById(R.id.eventleague);
            home = itemView.findViewById(R.id.home);
            awayscore = itemView.findViewById(R.id.awayscore);
            homescore = itemView.findViewById(R.id.homescore);
            away = itemView.findViewById(R.id.away);
            season = itemView.findViewById(R.id.eventseason);
            venue = itemView.findViewById(R.id.eventvenue);
            progressBar = itemView.findViewById(R.id.progress_load_photo);
        }
    }
}
