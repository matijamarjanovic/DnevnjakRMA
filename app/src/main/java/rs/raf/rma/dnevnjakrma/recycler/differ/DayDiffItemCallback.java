package rs.raf.rma.dnevnjakrma.recycler.differ;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.rma.dnevnjakrma.models.Day;

public class DayDiffItemCallback extends DiffUtil.ItemCallback<Day> {
    @Override
    public boolean areItemsTheSame(@NonNull Day oldItem, @NonNull Day newItem) {

        boolean same = false;

        if (oldItem.getMonth() == newItem.getMonth() &&
                oldItem.getDate() == newItem.getDate() &&
                    oldItem.getYear() == newItem.getYear())
            same = true;

        return same;
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull Day oldItem, @NonNull Day newItem) {
        boolean same = false;

        if (oldItem.getDay() == newItem.getDay() &&
                oldItem.getPriority() == newItem.getPriority())
            same = true;

        return same;
    }
}
