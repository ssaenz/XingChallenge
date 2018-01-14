package com.ssaenz.xingchallenge.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Listener to be fired when the scroll is at the end of the view.
 *
 */

public abstract class EndScrollListener extends RecyclerView.OnScrollListener {

    private boolean loading = false;
    private int previousTotal = 0;

    public abstract void onScrollEnd(int totalItemCount);

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = layoutManager.getItemCount();

        if (loading) {
            //Data is already loaded
            if (totalItemCount > previousTotal) {
                loading = false;
            }
        }
        //The scroll is at the end.
        else if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
            onScrollEnd(totalItemCount);
            loading = true;
            previousTotal = totalItemCount;
        }
    }
}
