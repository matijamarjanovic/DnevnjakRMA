package rs.raf.rma.dnevnjakrma.recycler.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Consumer;

import rs.raf.rma.dnevnjakrma.R;
import rs.raf.rma.dnevnjakrma.models.Day;

public class DayAdapter extends ListAdapter<Day, DayAdapter.ViewHolder> {

    private final Consumer<Day> onDayClicked;
    private Context context;

    public DayAdapter(@NonNull DiffUtil.ItemCallback<Day> diffCallback, Consumer<Day> onDayClicked) {
        super(diffCallback);
        this.onDayClicked = onDayClicked;
    }

    @NonNull
    @Override
    public DayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_grid_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(view, position -> {
            Day day = getItem(position);
            onDayClicked.accept(day);
        }, this.context);
    }


    @Override
    public void onBindViewHolder(@NonNull DayAdapter.ViewHolder holder, int position) {
        Day day = getItem(position);
        holder.bind(day);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private Context context;

        public ViewHolder(@NonNull View itemView, Consumer<Integer> onItemClicked, Context context) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(v -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION){
                    onItemClicked.accept(getBindingAdapterPosition());
                }
            });
        }

        public void bind(Day day){
            ((TextView) itemView.findViewById(R.id.dayTv)).setText(day.getDate() +".");

            if (day.getPriority() == 3)
                itemView.findViewById(R.id.dayTv).setBackground(ContextCompat.getDrawable(this.context, R.drawable.red_background));
            else if (day.getPriority() == 2)
                itemView.findViewById(R.id.dayTv).setBackground(ContextCompat.getDrawable(this.context, R.drawable.yellow_background));
            else if (day.getPriority() == 1)
                itemView.findViewById(R.id.dayTv).setBackground(ContextCompat.getDrawable(this.context, R.drawable.green_background));

        }
    }


}
