package rs.raf.rma.dnevnjakrma.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.rma.dnevnjakrma.models.Task;

public class TaskDiffItemCallback extends DiffUtil.ItemCallback<Task> {
    @Override
    public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
        boolean same = false;

        if (oldItem.getDay().equals(newItem.getDay())&&
                newItem.getStart().equals(oldItem.getStart()))
            same = true;

        return same;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {

        if (oldItem.getName().equals(newItem.getName())&&
                newItem.getPriority() == oldItem.getPriority())
            return true;

        return false;
    }
}
