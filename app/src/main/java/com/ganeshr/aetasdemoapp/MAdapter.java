package com.ganeshr.aetasdemoapp;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganeshr.aetasdemoapp.helper.ItemTouchHelperAdapter;
import com.ganeshr.aetasdemoapp.helper.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;


public class MAdapter extends RecyclerView.Adapter<MAdapter.MViewHolder> implements ItemTouchHelperAdapter{

    Context mContext;
    ArrayList<AndroidVersions> versionsArrayList;
    getIndex listener;
    int  dragItemPosition;
    AndroidVersions darggedItem;
    private final OnStartDragListener mDragStartListener;

    interface getIndex {
        void getItemIndex(AndroidVersions obj, int position);
    }

    public MAdapter(ArrayList<AndroidVersions> versionsArrayList, Context context , getIndex listener,OnStartDragListener mDragStartListener) {
        this.versionsArrayList = versionsArrayList;
        mContext = context;
        this.listener = listener;
        this.mDragStartListener= mDragStartListener;
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item, viewGroup, false);
        return new MViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MViewHolder mViewHolder, final int position) {
        final AndroidVersions versions;
        versions = versionsArrayList.get(mViewHolder.getAdapterPosition());
        mViewHolder.txtHeading.setText(versions.getVersionName());

        mViewHolder.rowParentLayout.setTag(position);


        mViewHolder.rowParentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                dragItemPosition = mViewHolder.getAdapterPosition();
                clipData(view, dragItemPosition);
                darggedItem  = versions;
                mDragStartListener.removeSelectedItem(dragItemPosition);
                return true;

            }
        });

//        mViewHolder.rowParentLayout.setOnDragListener(this);

//     mViewHolder.imgDragView.setOnTouchListener(new View.OnTouchListener() {
//         @Override
//         public boolean onTouch(View view, MotionEvent event) {
//             clipData(mViewHolder.rowParentLayout, position);
//             return true;
//         }
//     });


//
//         mViewHolder.imgDragView.setOnTouchListener(new View.OnTouchListener() {
//             @Override
//             public boolean onTouch(View view, MotionEvent event) {
//                     mDragStartListener.onStartDrag(mViewHolder);
//                 return false;
//
////                 clipData(mViewHolder.rowParentLayout, position);
////                 return true;
//             }
//         });

    }

    private void clipData(View view, int position){
        ClipData data = ClipData.newPlainText("", "");
//        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(data, new DragShadowBuilder(view, mContext), view,  0);
        view.setVisibility(View.VISIBLE);
        listener.getItemIndex(versionsArrayList.get(position), position);
    }

    @Override
    public void onItemDismiss(int position) {
        versionsArrayList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(versionsArrayList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
//
//    @Override
//    public boolean onDrag(View view, DragEvent dragEvent) {
//        int action = dragEvent.getAction();
//
//        switch (action) {
//            case DragEvent.ACTION_DROP:
//                View viewSource = (View) dragEvent.getLocalState();
//                view.setVisibility(View.VISIBLE);
//                mDragStartListener.addItemOnSelectedPostion();
////                notifyDataSetChanged();
////                shiftItemPosition(viewSource, view);
//                break;
//        }
//
//        return true;
//    }

    private void shiftItemPosition(View viewSource, View view) {

        int positionSource = -1;
        int positionTarget = -1;

        if (view.getId() == R.id.rowParentLayout) {
            RecyclerView target = (RecyclerView) view.getParent();
            positionTarget = (int) view.getTag();

            RecyclerView source = (RecyclerView) viewSource.getParent();

            MAdapter adapterSource = (MAdapter) source.getAdapter();
            positionSource = (int) viewSource.getTag();

            AndroidVersions customList = adapterSource.getCustomList().get(positionSource);
            ArrayList<AndroidVersions> customListSource = adapterSource.getCustomList();

            customListSource.remove(positionSource);
            adapterSource.updateCustomList(customListSource);
            adapterSource.notifyDataSetChanged();

            MAdapter adapterTarget = (MAdapter) target.getAdapter();
            ArrayList<AndroidVersions> customListTarget = adapterTarget.getCustomList();
            if (positionTarget >= 0) {
                customListTarget.add(positionTarget, customList);
            } else {
                customListTarget.add(customList);
            }
            adapterTarget.updateCustomList(customListTarget);
            adapterTarget.notifyDataSetChanged();
        }
    }

    public void updateCustomList(ArrayList<AndroidVersions> versionsList) {
        this.versionsArrayList = versionsList;
    }

    public ArrayList<AndroidVersions> getCustomList() {
        return versionsArrayList;
    }

    @Override
    public int getItemCount() {
        return versionsArrayList.size();
    }

    class MViewHolder extends RecyclerView.ViewHolder {
        TextView txtHeading;
        CardView rowParentLayout;
        ImageView imgDragView;

        public MViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHeading = itemView.findViewById(R.id.txtHeading);
            rowParentLayout = itemView.findViewById(R.id.rowParentLayout);
            imgDragView = itemView.findViewById(R.id.imgDragView);
            rowParentLayout.setVisibility(View.VISIBLE);
        }
    }
}

