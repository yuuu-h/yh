package com.example.flowershop.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.flowershop.R;
import com.example.flowershop.fragment.CartFragment;
import com.example.flowershop.fragment.MainFragment;
import com.example.flowershop.fragment.MeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new CartFragment());
        fragments.add(new MeFragment());

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    FragmentTransaction tran3 = getSupportFragmentManager().beginTransaction();
                    tran3.replace(R.id.main, fragments.get(0));
                    tran3.commit();
                    return true;
                } else if (itemId == R.id.cart) {
                    FragmentTransaction tran2 = getSupportFragmentManager().beginTransaction();
                    tran2.replace(R.id.main, fragments.get(1));
                    tran2.commit();

                    return true;
                } else if (itemId == R.id.me) {
                    FragmentTransaction tran1 = getSupportFragmentManager().beginTransaction();
                    tran1.replace(R.id.main, fragments.get(2));
                    tran1.commit();
                    return true;
                }
                return false;
            }
        });
        navigation.setSelectedItemId(R.id.home);
    }
}