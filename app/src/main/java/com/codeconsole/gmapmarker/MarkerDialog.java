package com.codeconsole.gmapmarker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codeconsole.gmapmarker.Bean.MarkerBean;
import com.codeconsole.gmapmarker.Database.MarkerTable;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by madhan on 24/2/17.
 */

public class MarkerDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = "MarkerDialog";

    public MarkerDialog(Context context) {
        super(context);
    }

    EditText nameText;
    Button cancelBtn, saveBtn;
    MarkerTable table;

    Marker marker;

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_marker_layout);

        initViews();
        setCancelable(false);
        table = new MarkerTable(getContext());
        cancelBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

    }

    private void initViews() {
        nameText = (EditText) findViewById(R.id.dialog_marker_name_text);
        cancelBtn = (Button) findViewById(R.id.dialog_marker_cancel_btn);
        saveBtn = (Button) findViewById(R.id.dialog_marker_save_btn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_marker_save_btn:
                String name = nameText.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    saveMarker(name);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "Marker should not be empty", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.dialog_marker_cancel_btn:
                dismiss();
                break;
        }
    }

    //new marker
    private void saveMarker(String name) {
        MarkerTable table = new MarkerTable(getContext());

        if (marker != null) {
            MarkerBean bean = new MarkerBean();
            bean.setLabelId(0);
            bean.setTitle(name);
            bean.setLatitude(String.valueOf(marker.getPosition().latitude));
            bean.setLongitude(String.valueOf(marker.getPosition().longitude));
            bean.setIconPath("");

            table.insertMarker(bean);
        } else {
            Toast.makeText(getContext(), "Marker object is empty", Toast.LENGTH_SHORT).show();
        }

    }
}
