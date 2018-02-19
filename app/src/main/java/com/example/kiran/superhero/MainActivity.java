package com.example.kiran.superhero;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


      RecyclerView recycleview;

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle actionBarDrawerToggle;//to make sure that 2 child can operate properly

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setuptoolbar();

        navigationView = (NavigationView) findViewById(R.id.navigationmenu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.ios:
                        Toast.makeText(MainActivity.this,"click ios",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.about:
                        Toast.makeText(MainActivity.this,"click about us",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.news:
                        Toast.makeText(MainActivity.this,"click news",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.and:
                        Toast.makeText(MainActivity.this,"click and",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.home:
                        Toast.makeText(MainActivity.this,"click home",Toast.LENGTH_SHORT).show();
                        break;


                }

                return false;
            }
        });

        getdata();
    }

    private void setuptoolbar(){

        drawer = (DrawerLayout)findViewById(R.id.drawer);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//to make toolbar as actionbar as backward compatible
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.app_name,R.string.app_name);
        //to make sure that when we click hamburg icon second child come and when we click outside that child goes out from screen we used this ACTIONBAR TOGGLE
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();//manage all of drawer layout and toolbar ..it is a helper class
        //to sync drawerlayout and toolbar
        //to make sure toolbar and b=navigationbar can coexist together

    }

    private void getdata(){



        recycleview = (RecyclerView)findViewById(R.id.recycler);
        recycleview.setHasFixedSize(true);
          recycleview.setLayoutManager(new LinearLayoutManager(this));



        // firsttext = (TextView)findViewById(R.id.firsttext);

        Call<Postlist> postlist = bloggerAPI.getService().getpostlist();
        postlist.enqueue(new Callback<Postlist>() {
            @Override
            public void onResponse(Call<Postlist> call, Response<Postlist> response) {

                Postlist list = response.body();//get list of item class thing

                Toast.makeText(getApplicationContext(),"success occurred",Toast.LENGTH_LONG).show();

             //   firsttext.setText(list.getEtag());


                recycleradapter adapter = new recycleradapter(MainActivity.this,list.getItems());
                recycleview.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Postlist> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"error occurred",Toast.LENGTH_LONG).show();



            }
        });

    }
}


