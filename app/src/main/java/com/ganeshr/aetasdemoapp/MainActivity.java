package com.ganeshr.aetasdemoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ganeshr.aetasdemoapp.helper.OnStartDragListener;
import com.ganeshr.aetasdemoapp.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnDragListener, MAdapter.getIndex, OnStartDragListener{

    @Bind(R.id.rView)
    RecyclerView recyclerView;

    @Bind(R.id.bottomScreen1)
    CardView bottomScreen1;

    @Bind(R.id.bottomScreen2)
    CardView bottomScreen2;

    @Bind(R.id.bottomScreen3)
    CardView bottomScreen3;

    @Bind(R.id.bottomScreen4)
    CardView bottomScreen4;

    @Bind(R.id.waitingRoomLayout)
    LinearLayout waitingRoomLayout;

    @Bind(R.id.txtWaitingCount)
    TextView txtWaitingCount;

    @Bind(R.id.txtRoomTableCount)
    TextView txtRoomTableCount;

    @Bind(R.id.txtGymCount)
    TextView txtGymCount;

    private ItemTouchHelper mItemTouchHelper;
    MAdapter mAdapter;

    AndroidVersions versions;
    int position;

    int waitingRoomCount = 0, roomTableCount = 0, gymCount = 0;

    ArrayList<AndroidVersions> androidVersionsArrayList;

    private boolean isDroppedRView = false;
    private boolean isDroppedScreen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        prepareList();
        setRViewLayout();

        bottomScreen1.setOnDragListener(this);
        bottomScreen2.setOnDragListener(this);
        bottomScreen3.setOnDragListener(this);
        bottomScreen4.setOnDragListener(this);

        recyclerView.setOnDragListener(this);

        bottomScreen1.setOnClickListener(this);
        bottomScreen2.setOnClickListener(this);
        bottomScreen3.setOnClickListener(this);
        bottomScreen4.setOnClickListener(this);

    }

    private void prepareList() {
        androidVersionsArrayList = new ArrayList();

        AndroidVersions version1 = new AndroidVersions();
        version1.setVersionName("Android 1.6. Donut.");


        AndroidVersions version2 = new AndroidVersions();
        version2.setVersionName("Android 2.1. Eclair.");


        AndroidVersions version3 = new AndroidVersions();
        version3.setVersionName("Android 2.2. Froyo.");


        AndroidVersions version4 = new AndroidVersions();
        version4.setVersionName("Android 2.3. Gingerbread.");


        AndroidVersions version5 = new AndroidVersions();
        version5.setVersionName("Android 3.0. Honeycomb.");


        AndroidVersions version6 = new AndroidVersions();
        version6.setVersionName("Android 4.0. Ice Cream Sandwich.");


        AndroidVersions version7 = new AndroidVersions();
        version7.setVersionName("Android 4.1. Jelly Bean.");


        AndroidVersions version8 = new AndroidVersions();
        version8.setVersionName("Android 4.4. KitKat.");

        AndroidVersions version9 = new AndroidVersions();
        version9.setVersionName("Android 5.0 Lollipop.");


        AndroidVersions version10 = new AndroidVersions();
        version10.setVersionName("Android 6.0 Marshmallow.");

        AndroidVersions version11 = new AndroidVersions();
        version11.setVersionName("Android 7.0 Nougat.");


        AndroidVersions version12 = new AndroidVersions();
        version12.setVersionName("Android 8.0 Oreo.");


        AndroidVersions version13 = new AndroidVersions();
        version13.setVersionName("Android 9.0 Pie.");

        androidVersionsArrayList.add(version13);
        androidVersionsArrayList.add(version12);
        androidVersionsArrayList.add(version11);
        androidVersionsArrayList.add(version10);
        androidVersionsArrayList.add(version9);
        androidVersionsArrayList.add(version8);
        androidVersionsArrayList.add(version7);
        androidVersionsArrayList.add(version6);
        androidVersionsArrayList.add(version5);
        androidVersionsArrayList.add(version4);
        androidVersionsArrayList.add(version3);
        androidVersionsArrayList.add(version2);
        androidVersionsArrayList.add(version1);

    }

    private void setRViewLayout() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mAdapter = new MAdapter(androidVersionsArrayList, this, this,this);
        recyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (v.getId()) {

            case R.id.rView:
                switch (action) {
                    case DragEvent.ACTION_DROP:
                        isDroppedRView = true;
                        androidVersionsArrayList.add(position, versions );
                        mAdapter.notifyItemInserted(position);
//                        mAdapter.notifyDataSetChanged();
                        break;


                    case DragEvent.ACTION_DRAG_ENDED:{

                            if((!isDroppedScreen && !isDroppedRView)) {
                                androidVersionsArrayList.add(position, versions);
                                mAdapter.notifyItemInserted(position);
                            }


                        isDroppedRView = false;
                        isDroppedScreen = false;
                    }
                    break;


                }

            break;

            case R.id.bottomScreen1:
                switch (action) {
                    case DragEvent.ACTION_DROP:
                        isDroppedScreen = true;
                        txtWaitingCount.setText(String.valueOf(++waitingRoomCount));
                        refreshList();
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        bottomScreen1.setBackground(getResources().getDrawable(R.drawable.dotted_border));
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                    case DragEvent.ACTION_DRAG_ENDED:
//                        isDroppedScreen = false;
                        bottomScreen1.setBackgroundColor(getResources().getColor(R.color.white));
                        break;


                }
            break;

            case R.id.bottomScreen2:
                switch (action) {
                    case DragEvent.ACTION_DROP:
                        isDroppedScreen = true;
                        txtRoomTableCount.setText(String.valueOf(++roomTableCount));
//                        refreshList();
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        bottomScreen2.setBackground(getResources().getDrawable(R.drawable.dotted_border));
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                    case DragEvent.ACTION_DRAG_ENDED:
                        bottomScreen2.setBackgroundColor(getResources().getColor(R.color.white));
                        break;
                }
            break;

            case R.id.bottomScreen3:
                switch (action) {
                    case DragEvent.ACTION_DROP:
                        isDroppedScreen = true;
                        txtGymCount.setText(String.valueOf(++gymCount));
//                        refreshList();
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        bottomScreen3.setBackground(getResources().getDrawable(R.drawable.dotted_border));
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                    case DragEvent.ACTION_DRAG_ENDED:

                        bottomScreen3.setBackgroundColor(getResources().getColor(R.color.white));
                        break;

                }
            break;

            case R.id.bottomScreen4:
                switch (action) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        bottomScreen4.setBackground(getResources().getDrawable(R.drawable.dotted_border));
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                    case DragEvent.ACTION_DRAG_ENDED:
                        bottomScreen4.setBackgroundColor(getResources().getColor(R.color.white));
                        break;

                    case DragEvent.ACTION_DROP:
                        isDroppedScreen = true;
                        refreshList();
                        break;
                }
            break;

        }
        return true;
    }

    private void refreshList() {
//        androidVersionsArrayList.remove(position);
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottomScreen1:
                Toast.makeText(this, "Screen 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bottomScreen2:
                Toast.makeText(this, "Screen 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bottomScreen3:
                Toast.makeText(this, "Screen 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bottomScreen4:
                Toast.makeText(this, "Screen 4", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void getItemIndex(AndroidVersions versions, int position) {
        this.versions = versions;
        this.position = position;
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void removeSelectedItem(int position) {
        androidVersionsArrayList.remove(position);
        mAdapter.notifyItemRemoved(position);
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addItemOnSelectedPostion() {
        androidVersionsArrayList.add(position,versions);
        mAdapter.notifyItemInserted(position);
    }

}
