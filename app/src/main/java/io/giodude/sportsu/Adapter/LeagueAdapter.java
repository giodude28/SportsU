package io.giodude.sportsu.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.giodude.sportsu.Model.LeagueModel;
import io.giodude.sportsu.Model.TeamModel;
import io.giodude.sportsu.Network.team;
import io.giodude.sportsu.R;
import io.giodude.sportsu.View.LeagueView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeagueAdapter extends RecyclerView.Adapter<LeagueAdapter.ViewHolder> {
    Context context;
    public List<LeagueModel.League> data;
    public LeagueModel.League ee;
    public LeagueAdapter(Context context, List<LeagueModel.League> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.leagueitem,parent,false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(data.get(position));
        ee = data.get(position);
        holder.title.setText(ee.getStrLeague());
        holder.id.setText(ee.getIdLeague());
        holder.title.setOnClickListener((View.OnClickListener) v -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setMessage("Do you want to Change on this League?");
            alertDialogBuilder.setPositiveButton("yes",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            String l = holder.title.getText().toString();
                            String s = holder.id.getText().toString();
                            team.pastid = s;
                            team.teamid = l;
                            notifyDataSetChanged();
                            Toast.makeText(context,"Change Successfully!",Toast.LENGTH_LONG).show();}
                    });

            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.leaguename);
            id = itemView.findViewById(R.id.leagueid);
        }
    }
}
