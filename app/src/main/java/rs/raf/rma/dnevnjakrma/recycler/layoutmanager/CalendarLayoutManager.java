package rs.raf.rma.dnevnjakrma.recycler.layoutmanager;

import android.content.Context;
import android.os.Parcelable;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarLayoutManager extends GridLayoutManager {

    private int mPendingTargetPos = -1;
    private int mPendingPosOffset = -1;

    public CalendarLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        if (mPendingTargetPos != -1 && state.getItemCount() > 0) {

            scrollToPositionWithOffset(mPendingTargetPos, mPendingPosOffset);
            mPendingTargetPos = -1;
            mPendingPosOffset = -1;
        }
        super.onLayoutChildren(recycler, state);
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        mPendingTargetPos = -1;
        mPendingPosOffset = -1;
        super.onRestoreInstanceState(state);
    }

    public void setTargetStartPos(int position, int offset) {
        mPendingTargetPos = position;
        mPendingPosOffset = offset;
    }
}
