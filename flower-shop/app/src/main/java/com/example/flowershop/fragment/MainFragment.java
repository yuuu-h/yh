package com.example.flowershop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.flowershop.R;
import com.example.flowershop.activity.DetailActivity;
import com.example.flowershop.adapter.StuffAdapter;
import com.example.flowershop.entity.Stuff;
import com.example.flowershop.sqlite.DBStuff;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {

    private ListView lvStuff;
    private EditText etSearch;
    private ImageView ivSearch;

    private List<Stuff> stuffList;

    private StuffAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        bindView(v);
        initView();
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = etSearch.getText().toString();
                setSearch(str);
            }
        });
        return v;
    }

    private void bindView(View v) {
        lvStuff = v.findViewById(R.id.lv_stuff);
        etSearch = v.findViewById(R.id.et_search);
        ivSearch = v.findViewById(R.id.iv_search);
    }

    private void initView() {
        stuffList = DBStuff.getAll();
        adapter = new StuffAdapter(getContext(), R.layout.item_stuff, stuffList);
        lvStuff.setAdapter(adapter);
        lvStuff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("id", (stuffList.get(i)).getId());
                intent.setClass(getContext(), DetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSearch(String str) {
        List<Stuff> arrayTemp = DBStuff.getAll();
        List<Stuff> array = new ArrayList<>();
        for (Stuff t : arrayTemp) {
            if (t.getName().contains(str) || t.getTitle().contains(str)) {
                array.add(t);
            }
        }
        if (array.size() == 0) {
            Toast.makeText(getContext(), "未搜索到关键字商品", Toast.LENGTH_SHORT).show();
            array = arrayTemp;
        }
        stuffList = array;
        adapter.setData(stuffList);
    }

}