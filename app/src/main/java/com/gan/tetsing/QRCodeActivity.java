package com.gan.tetsing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gan.tetsing.Models.CRUDMessage;
import com.gan.tetsing.REST.RetrofitClient;
import com.gan.tetsing.REST.RetrofitInterface;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QRCodeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonScan;
    private TextView textViewJudul, textViewIsi;

    private IntentIntegrator intentIntegrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        buttonScan = (Button) findViewById(R.id.buttonScan);
        textViewJudul = (TextView) findViewById(R.id.textViewJudul);
        textViewIsi = (TextView) findViewById(R.id.textViewIsi);

        // attaching onclickListener
        buttonScan.setOnClickListener(this);
    }

    // Mendapatkan hasil scan


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Hasil tidak ditemukan", Toast.LENGTH_SHORT).show();
            }else{
                // jika qrcode berisi data
                try{
                    // converting the data json
                    JSONObject object = new JSONObject(result.getContents());
                    // atur nilai ke textviews
                    textViewJudul.setText(object.getString("judul"));
                    textViewIsi.setText(object.getString("isi"));
                    String judul = textViewJudul.getText().toString();
                    String isi = textViewIsi.getText().toString();
                    RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);


                    Call<CRUDMessage> insertBerita = retrofitInterface.insertBerita(judul, isi);
                    insertBerita.enqueue(new Callback<CRUDMessage>() {
                        @Override
                        public void onResponse(Call<CRUDMessage> call, Response<CRUDMessage> response) {
                            Toast.makeText(QRCodeActivity.this,
                                    "Insert Berita " + response.body().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<CRUDMessage> call, Throwable t) {
                            Log.d("failure", t.getMessage());
                        }
                    });
                }catch (JSONException e){
                    e.printStackTrace();
                    // jika format encoded tidak sesuai maka hasil
                    // ditampilkan ke toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        // inisialisasi IntentIntegrator(scanQR)
        intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
