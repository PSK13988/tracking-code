package com.trakdesk.app.helpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.trakdesk.app.R;

import static com.trakdesk.app.adapters.MultiContactsAdapter.TYPE_CONTACT;

public class DividerDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private final Paint mPaint;
    private int mHeightDp;

    public DividerDecoration(Context context) {
        this(context, Color.argb((int) (255 * 0.2), 0, 0, 0), 0.5f);
        this.mContext = context;
    }

    public DividerDecoration(Context context, int color, float heightDp) {
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        mHeightDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightDp, context.getResources().getDisplayMetrics());
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        Log.d("DividerDecoration", "getItemOffsets position: " + position);
        if (position != -1) {
            int viewType = parent.getAdapter().getItemViewType(position);
            if (viewType == TYPE_CONTACT) {
                outRect.set(0, mHeightDp, 0, 0);
            } else {
                outRect.setEmpty();
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            if (position != -1) {
                int viewType = parent.getAdapter().getItemViewType(position);
                if (viewType == TYPE_CONTACT) {
                    int margin = mContext.getResources().getDimensionPixelSize(R.dimen._60sdp);
                    c.drawRect(view.getLeft() + margin, view.getBottom() + mHeightDp, view.getRight(), view.getBottom(), mPaint);
                }
            }
        }
    }
}