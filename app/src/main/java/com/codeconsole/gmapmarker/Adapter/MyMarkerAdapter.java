package com.codeconsole.gmapmarker.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codeconsole.gmapmarker.Bean.MarkerBean;
import com.codeconsole.gmapmarker.Listener.OnItemClickListener;
import com.codeconsole.gmapmarker.R;

import java.util.List;

/**
 * Created by user on 23-02-2017.
 */

public class MyMarkerAdapter extends RecyclerView.Adapter<MyMarkerAdapter.Holder> {
    private static final String TAG = "MyMarkerAdapter";

    private Context context;
    private List<MarkerBean> markerBeanList;
    private OnItemClickListener<MarkerBean> listener;

    public MyMarkerAdapter(Context context, List<MarkerBean> markerBeanList,
                           OnItemClickListener<MarkerBean> listener) {
        this.context = context;
        this.markerBeanList = markerBeanList;
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_my_marker_layout, parent, false);
        return new Holder(view);
    }

    @Override
    @SuppressLint("RecyclerView")
    public void onBindViewHolder(Holder holder, final int position) {
        final MarkerBean bean = markerBeanList.get(position);
        holder.nameText.setText(bean.getTitle());
        //holder.createdOnText.setText(DateParser.parseDate(bean.get));
        holder.removeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(bean, position);
                }
            }
        });

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return markerBeanList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        LinearLayout container;
        ImageView icon;
        TextView nameText, createdOnText, removeText;

        public Holder(View v) {
            super(v);
            icon = (ImageView) v.findViewById(R.id.item_my_marker_icon_image_view);
            nameText = (TextView) v.findViewById(R.id.item_my_marker_name_text);
            createdOnText = (TextView) v.findViewById(R.id.item_my_marker_created_on_text);
            removeText = (TextView) v.findViewById(R.id.item_my_marker_remove_text);

            container = (LinearLayout) v.findViewById(R.id.item_my_marker_container);
        }
    }
}
