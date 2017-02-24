package com.codeconsole.gmapmarker.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codeconsole.gmapmarker.Adapter.MyMarkerAdapter;
import com.codeconsole.gmapmarker.Bean.MarkerBean;
import com.codeconsole.gmapmarker.Database.MarkerTable;
import com.codeconsole.gmapmarker.Listener.OnItemClickListener;
import com.codeconsole.gmapmarker.R;

import java.util.ArrayList;
import java.util.List;

public class MyMarkerActivity extends AppCompatActivity implements OnItemClickListener<MarkerBean> {

    RecyclerView markerRV;
    List<MarkerBean> markerList;
    MyMarkerAdapter adapter;

    MarkerTable table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_marker);

        markerRV = (RecyclerView) findViewById(R.id.my_marker_recycler_view);
        initAdapter();

        table = new MarkerTable(MyMarkerActivity.this);

        if (table.getAllMarker() != null && table.getAllMarker().size() > 0) {
            markerList.addAll(table.getAllMarker());
            adapter.notifyDataSetChanged();
        }

    }

    private void initAdapter() {
        markerList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(MyMarkerActivity.this,
                LinearLayoutManager.VERTICAL, false);
        markerRV.setLayoutManager(layoutManager);

        adapter = new MyMarkerAdapter(MyMarkerActivity.this, markerList, MyMarkerActivity.this);
        markerRV.setAdapter(adapter);
    }

    @Override
    public void onItemClick(MarkerBean model, int position) {
        table.deleteMarker(model);
        markerList.remove(position);
        adapter.notifyDataSetChanged();
    }
}
