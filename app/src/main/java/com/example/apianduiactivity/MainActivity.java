package com.example.apianduiactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.os.Bundle;
import android.util.Log;

import com.example.apianduiactivity.databinding.ActivityMainBinding;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private String TAG = "tag";
    private ActivityMainBinding binding;
   private FastItemAdapter<Model> fastItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fastItemAdapter = new FastItemAdapter<>();
        fastItemAdapter.withSelectable(true);

        networkCalling();

    }

    private void setUpRec(){
        binding.rec.setLayoutManager(new LinearLayoutManager(this));
        // binding.rec.setItemAnimator(new SlideDownAlphaAnimator());
        binding.rec.setAdapter(fastItemAdapter);
    }

    void networkCalling(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getDetail().enqueue(new Callback<List<Root>>() {
            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                List<Root> roots = response.body();
                List<Model> models = new ArrayList<>();
                for (Root root : roots){
                    models.add(new Model(root.img_src,root.id,root.type,root.price));
                }

                fastItemAdapter.add(models);
                setUpRec();
            }
            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);

            }
        });
    }
}