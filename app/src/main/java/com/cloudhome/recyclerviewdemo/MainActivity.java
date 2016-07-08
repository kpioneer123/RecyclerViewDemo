package com.cloudhome.recyclerviewdemo;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cloudhome.recyclerviewdemo.adapter.SimpleAdapter;
import com.cloudhome.recyclerviewdemo.view.DividerLine;
import com.cloudhome.recyclerviewdemo.view.RecyclerViewItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private List<String> mDatas;

    private SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initViews();

        initDatas();

        mAdapter = new SimpleAdapter(this,mDatas);
        mRecyclerView.setAdapter(mAdapter);


        //设置RecyclerView的布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        //1.设置RecyclerView的Item间分隔线  方式一

       // mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        //2.设置RecyclerView的Item间分隔线  方式二
        DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
        dividerLine.setSize(2);
        dividerLine.setColor(0xffeeeeee);
        mRecyclerView.addItemDecoration(dividerLine);

       // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //2.设置RecyclerView的Item间分隔线  方式三
        //horizonal mode,line
   //     rvData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
    //   mRecyclerView.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL, 0xffe1e1e1,5,20,10));

        //horizonal mode, image resouce
//        rvData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
//        rvData.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,this, R.drawable.diver));

        //vertical mode, line
//        rvData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
//        rvData.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_VERTICAL, Color.RED,10,20,10));

        //vertical mode, image resouce
//        rvData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
//        rvData.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_VERTICAL,this, R.drawable.diver_vertical));
        //this image has no ninepatch
//        rvData.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_VERTICAL,this, R.drawable.diver_v));

        //grid
     //   mRecyclerView.setLayoutManager(new GridLayoutManager(this, 6));
      //  mRecyclerView.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_GRID, Color.RED,10,20,10));
//        rvData.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_GRID,this, R.drawable.diver_color));




        mAdapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {

                Toast.makeText(MainActivity.this,"click :"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this,"long click :"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
    }

    private void initDatas() {
        mDatas = new ArrayList<String>();

        for(int i = 'A';i<='Z';i++)
        {
            mDatas.add(""+(char)i);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {

            case R.id.id_action_add:
                mAdapter.addData(1);
                break;
            case R.id.id_action_delete:
                mAdapter.removeData(1);
                break;
            case R.id.id_action_gridview:
              //  mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));

                  mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                  mRecyclerView.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_GRID, Color.RED,10,0,0));

                break;
            case R.id.id_action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_horizontalGridView:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                        StaggeredGridLayoutManager.HORIZONTAL));
                break;

            case R.id.id_action_staggeredgridview:
                Intent intent = new Intent(this , StaggeredGridLayoutActivity.class);
              startActivity(intent);
                break;
        }
        return true;
    }

}
