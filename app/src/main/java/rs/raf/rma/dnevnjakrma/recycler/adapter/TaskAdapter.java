package rs.raf.rma.dnevnjakrma.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Consumer;

import rs.raf.rma.dnevnjakrma.R;
import rs.raf.rma.dnevnjakrma.models.Day;
import rs.raf.rma.dnevnjakrma.models.Task;

public class TaskAdapter extends ListAdapter<Task, TaskAdapter.ViewHolder> {

    private final Consumer<Task> onTaskClicked;
    private Context context;

    public TaskAdapter(@NonNull DiffUtil.ItemCallback<Task> diffCallback, Consumer<Task> onTaskClicked) {
        super(diffCallback);
        this.onTaskClicked = onTaskClicked;
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        context = parent.getContext();
        return new TaskAdapter.ViewHolder(view, position -> {
            Task task = getItem(position);
            onTaskClicked.accept(task);
        }, this.context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = getItem(position);
        holder.bind(task);
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

        public void bind(Task task){
            ((TextView)itemView.findViewById(R.id.taskTimeTv)).setText(task.getStart().toString() +" - " +task.getEnd().toString());
            ((TextView)itemView.findViewById(R.id.taskNameTv)).setText(task.getName());
            ((Button)itemView.findViewById(R.id.editBtn)).setText("Edit");
            ((Button)itemView.findViewById(R.id.deleteBtn)).setText("Delete");

            if (task.getPriority() == 3)
                itemView.findViewById(R.id.constraint).setBackground(ContextCompat.getDrawable(this.context, R.drawable.red_background));
            else if (task.getPriority() == 2)
                itemView.findViewById(R.id.constraint).setBackground(ContextCompat.getDrawable(this.context, R.drawable.yellow_background));
            else if (task.getPriority() == 1)
                itemView.findViewById(R.id.constraint).setBackground(ContextCompat.getDrawable(this.context, R.drawable.green_background));
        }
    }

}
