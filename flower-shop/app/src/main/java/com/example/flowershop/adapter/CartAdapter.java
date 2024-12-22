package com.example.flowershop.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.flowershop.R;
import com.example.flowershop.entity.Record;
import com.example.flowershop.entity.Stuff;
import com.example.flowershop.entity.User;
import com.example.flowershop.fragment.CartFragment;
import com.example.flowershop.utils.CurrentUserUtils;

import java.util.List;

public class CartAdapter extends ArrayAdapter<Stuff> {

    private final User user;

    public CartAdapter(@NonNull Context context, int resource, @NonNull List<Stuff> objects) {
        super(context, resource, objects);
        user = CurrentUserUtils.getCurrentUser();
    }

    //每个子项被滚动到屏幕内的时候会被调用
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Stuff s = getItem(position);//得到当前项的 Stuff 实例

        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_cart, parent, false);

        TextView title = view.findViewById(R.id.tv_title);
        TextView price = view.findViewById(R.id.tv_price);
        ImageView pic = view.findViewById(R.id.iv_pic);
        CheckBox box = view.findViewById(R.id.box);
        title.setText(s.getTitle());
        price.setText("¥" + s.getPrice());

        Glide.with(pic).load(s.getPic()).into(pic);

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), s.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        /*
        点击多选框，把此商品的价格加去或减去，显示在购物车页面的总价total中
        添加或删除，商品到购买记录中。
        */
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                double total = Double.parseDouble(CartFragment._tvTotal.getText().toString());
                if (box.isChecked()) {
                    //Toast.makeText(getContext(), "选中了", Toast.LENGTH_SHORT).show();
                    Record r = new Record(user.getName(), s.getId(), s.getName(), s.getPrice(), user.getAddress());
                    CartFragment.record.add(r);
                    total += Double.parseDouble(r.getPrice());
                } else {
                    int i = 0;
                    for (Record r : CartFragment.record) {
                        if (r.getId().equals(s.getId())) {
                            total -= Double.parseDouble(r.getPrice());
                            CartFragment.record.remove(i);
                            break;
                        }
                        i++;
                    }
                }
                CartFragment._tvTotal.setText(String.format("%.2f", total));
            }
        });
        return view;
    }

}

