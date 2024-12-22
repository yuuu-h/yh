package com.example.flowershop.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.flowershop.R;
import com.example.flowershop.adapter.CartAdapter;
import com.example.flowershop.entity.Record;
import com.example.flowershop.entity.Stuff;
import com.example.flowershop.entity.User;
import com.example.flowershop.sqlite.DBCart;
import com.example.flowershop.sqlite.DBRecord;
import com.example.flowershop.sqlite.DBStuff;
import com.example.flowershop.utils.CurrentUserUtils;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    public static ArrayList<Record> record = new ArrayList<>();
    public static TextView _tvTotal;
    private ListView lvCart;
    private Button btnBuy;
    private TextView etEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        bindView(v);
        initView();
        return v;
    }

    private void bindView(View v) {
        lvCart = v.findViewById(R.id.lv_cart);
        etEmpty = v.findViewById(R.id.tv_empty);
        _tvTotal = v.findViewById(R.id.tv_total);
        btnBuy = v.findViewById(R.id.btn_buy);
    }

    private void initView() {
        _tvTotal.setText("0.00");
        //点击购买
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CartFragment.record.size() == 0) {
                    Toast.makeText(getContext(), "你还未选择任何商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                double total = 0;
                for (Record r : CartFragment.record) {
                    //添加购买记录到数据库
                    if (DBRecord.add(r)) {
                        total += Double.valueOf(r.getPrice());

                        Log.d("成功添加购买记录-----", r.toString());
                        //从购物车数据库中删除已经购买的商品
                        if (DBCart.del(r.getId(), r.getUsername())) {
                            Log.d("成功从购物车中删除-----", r.toString());
                        }
                    } else {
                        Log.d("失败添加购买记录-----", r.toString());
                    }
                }
                record = new ArrayList<>();
                _tvTotal.setText("0.00");
                Toast.makeText(getContext(), "价格总计" + String.format("%.2f", total) + "元，购买成功！", Toast.LENGTH_SHORT).show();
                set();

            }
        });
        set();
    }

    /**
     * 从数据库中读取加入购物车的商品
     */
    private void set() {
        User user = CurrentUserUtils.getCurrentUser();
        ArrayList<Stuff> array = new ArrayList<>();
        //获取购物车中商品的所有id
        ArrayList<String> allId = DBCart.getLikesTitle(user.getName());
        if (allId.size() == 0) {
            etEmpty.setVisibility(View.VISIBLE);
        } else {
            etEmpty.setVisibility(View.GONE);
        }
        //通过id依次获取商品信息
        for (String s : allId) {
            Stuff stuff = DBStuff.getById(Integer.parseInt(s));
            array.add(stuff);
        }
        for (Stuff t : array) {
            Log.d("--------", t.toString());
        }
        CartAdapter adapter = new CartAdapter(getContext(), R.layout.item_cart, array);
        lvCart.setAdapter(adapter);
        lvCart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = (array.get(i)).getName();
                Toast.makeText(getContext(), "name：" + s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}