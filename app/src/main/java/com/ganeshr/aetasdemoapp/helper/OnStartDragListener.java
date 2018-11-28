package com.ganeshr.aetasdemoapp.helper;

import android.support.v7.widget.RecyclerView;

import com.ganeshr.aetasdemoapp.AndroidVersions;

/**
 * Listener for manual initiation of a drag.
 */
public interface OnStartDragListener {

    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
    void removeSelectedItem(int position);
    void addItemOnSelectedPostion();

}
