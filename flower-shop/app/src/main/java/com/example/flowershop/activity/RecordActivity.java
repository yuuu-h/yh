package com.example.flowershop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flowershop.R;
import com.example.flowershop.adapter.RecordAdapter;
import com.example.flowershop.entity.Record;
import com.example.flowershop.entity.User;
import com.example.flowershop.sqlite.DBRecord;
import com.example.flowershop.utils.CurrentUserUtils;

import java.util.List;

public class RecordActivity extends AppCompatActivity {
    private ListView lvRecord;
    private TextView tvEmpty;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        bindView();
        initView();
    }

    private void bindView() {
        lvRecord = findViewById(R.id.lv_record);
        tvEmpty = findViewById(R.id.tv_empty);
        ivBack = findViewById(R.id.iv_back);
    }

    private void initView() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        User user = CurrentUserUtils.getCurrentUser();
        List<Record> array = DBRecord.getAll(user.getName());
        if (array.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            tvEmpty.setVisibility(View.GONE);
        }
        RecordAdapter adapter = new RecordAdapter(this, R.layout.item_record, array);
        lvRecord.setAdapter(adapter);
    }
}