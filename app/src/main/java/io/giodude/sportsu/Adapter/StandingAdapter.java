package io.giodude.sportsu.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.giodude.sportsu.Model.StandingModel;
import io.giodude.sportsu.R;

public class StandingAdapter extends RecyclerView.Adapter<StandingAdapter.ViewHolder> {
    Context context;
    public List<StandingModel.Table> data;
    public StandingModel.Table ee;
    TextView update, played, draw, lose, win, rank, tname;

    public StandingAdapter(Context context, List<StandingModel.Table> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.standingitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(data.get(position));
        ee = data.get(position);

        Picasso.get().load(ee.getStrTeamBadge()).into(holder.img);
        if (ee.getIdTeam() == null) {
            holder.name.setText("Unknown");
        } else {
            holder.name.setText(ee.getStrTeam());
        }
        if (ee.getIntWin() == null) {
            holder.win.setText("0");
        } else {
            holder.win.setText(ee.getIntWin());
        }
        if (ee.getIntLoss() == null) {
            holder.lose.setText("0");
        } else {
            holder.lose.setText(ee.getIntLoss());
        }
        if (ee.getIntDraw() == null) {
            holder.draw.setText("0");
        } else {
            holder.draw.setText(ee.getIntDraw());
        }

        final Dialog myDialog;
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.standingclicks);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        update = myDialog.findViewById(R.id.teamupdate);
        played = myDialog.findViewById(R.id.teamplayed);
        draw = myDialog.findViewById(R.id.teamdraw);
        lose = myDialog.findViewById(R.id.teamlose);
        win = myDialog.findViewById(R.id.teamwin);
        rank = myDialog.findViewById(R.id.teamrank);
        tname = myDialog.findViewById(R.id.teamname);
        for (int i = 0; i < data.size(); i++) {
            if (holder.name.getText() == data.get(position).getStrTeam()) {
                update.setText(data.get(position).getDateUpdated());
                played.setText(data.get(position).getIntPlayed());
                draw.setText(data.get(position).getIntDraw());
                lose.setText(data.get(position).getIntLoss());
                win.setText(data.get(position).getIntWin());
                rank.setText(data.get(position).getIntRank());
                tname.setText(data.get(position).getStrTeam());
            }
        }
        holder.itemView.setOnClickListener(v -> {
            myDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, win, lose, draw;
        ImageView img;
        CardView c1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            c1 = itemView.findViewById(R.id.card1);
            img = itemView.findViewById(R.id.imgid);
            name = itemView.findViewById(R.id.teamname);
            win = itemView.findViewById(R.id.teamwin);
            lose = itemView.findViewById(R.id.teamlose);
            draw = itemView.findViewById(R.id.teamdraw);
        }
    }
}
