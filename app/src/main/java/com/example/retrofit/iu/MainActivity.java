package com.example.retrofit.iu;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.retrofit.R;
import com.example.retrofit.adapter.AdapterPhotos;
import com.example.retrofit.controller.ControllerInstancePhotos;
import com.example.retrofit.interfaces.GetDataServicePhotos;
import com.example.retrofit.model.ModelPhoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private AdapterPhotos adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataServicePhotos service = ControllerInstancePhotos.getControllerInstancePhotos().create(GetDataServicePhotos.class);
        Call<List<ModelPhoto>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<ModelPhoto>>() {
            @Override
            public void onResponse(Call<List<ModelPhoto>> call, Response<List<ModelPhoto>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<ModelPhoto>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<ModelPhoto> photoList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new AdapterPhotos(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
