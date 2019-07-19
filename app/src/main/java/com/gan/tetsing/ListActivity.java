package com.gan.tetsing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.gan.tetsing.Adapters.BeritaAdapter;
import com.gan.tetsing.Models.Berita;
import com.gan.tetsing.Models.Parsing.GetAllBerita;
import com.gan.tetsing.REST.RetrofitClient;
import com.gan.tetsing.REST.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Spinner spinner = (Spinner) findViewById(R.id.spiMatkul);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.matkul, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object matkulSelected = parent.getItemAtPosition(position);
                /*Toast.makeText(ListActivity.this,
                        "Insert Berita " + matkulSelected,
                        Toast.LENGTH_SHORT).show();*/

                final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerBerita);
                final LinearLayout layoutProgress = (LinearLayout) findViewById(R.id.layoutProgress);

                if (matkulSelected != null){
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);

                    layoutProgress.setVisibility(View.VISIBLE);
                    Call<GetAllBerita> getAllBerita = retrofitInterface.getAllBerita();
                    getAllBerita.enqueue(new Callback<GetAllBerita>() {
                        @Override
                        public void onResponse(Call<GetAllBerita> call, Response<GetAllBerita> response) {
                            List<Berita> beritaList = response.body().getBerita();
                            RecyclerView.Adapter adapter = new BeritaAdapter(beritaList);
                            recyclerView.setAdapter(adapter);

                            layoutProgress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<GetAllBerita> call, Throwable t) {
                            Log.d("failure", t.getMessage());
                            layoutProgress.setVisibility(View.GONE);
                        }
                    });
                }
                else{
                    recyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
