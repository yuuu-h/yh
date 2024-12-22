package com.example.flowershop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.flowershop.R;
import com.example.flowershop.entity.Record;
import com.example.flowershop.entity.Stuff;
import com.example.flowershop.entity.User;
import com.example.flowershop.sqlite.DBCart;
import com.example.flowershop.sqlite.DBRecord;
import com.example.flowershop.sqlite.DBStuff;
import com.example.flowershop.utils.CurrentUserUtils;

public class DetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvName, tvPrice, tvKind;
    private ImageView ivImage, ivBack;
    private Button btnBuy, btnAdd;

    private Stuff stuff;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        stuff = DBStuff.getById(Integer.parseInt(id));
        user = CurrentUserUtils.getCurrentUser();
        bindView();
        initView();
    }

    private void bindView() {
        tvTitle = findViewById(R.id.tv_title);
        tvName = findViewById(R.id.tv_name);
        tvPrice = findViewById(R.id.tv_price);
        tvKind = findViewById(R.id.tv_kind);
        ivImage = findViewById(R.id.iv_image);
        btnBuy = findViewById(R.id.btn_buy);
        btnAdd = findViewById(R.id.btn_add);
        ivBack = findViewById(R.id.iv_back);
    }

    private void initView() {
        tvTitle.setText(stuff.getTitle());
        tvName.setText("名称：" + stuff.getName());
        tvPrice.setText("价格：¥" + stuff.getPrice());

        Glide.with(ivImage).load(stuff.getPic()).into(ivImage);

        tvKind.setText("分类：" + stuff.getKind());
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DBCart.add(stuff.getId(), user.getName())) {
                    Toast.makeText(DetailActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailActivity.this, "加入购物车失败，或已在购物车中", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Record r = new Record(user.getName(), stuff.getId(), tvName.getText().toString(), stuff.getPrice(), user.getAddress());
                if (DBRecord.add(r)) {
                    Toast.makeText(DetailActivity.this, "价格" + stuff.getPrice() + "元，购买成功！", Toast.LENGTH_SHORT).show();
                    Log.d("-----", r.toString());
                } else {
                    Toast.makeText(DetailActivity.this, "购买失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}