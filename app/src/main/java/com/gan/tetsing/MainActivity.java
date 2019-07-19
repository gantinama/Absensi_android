package com.gan.tetsing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gan.tetsing.Models.Parsing.GetLogin;
import com.gan.tetsing.REST.RetrofitClient;
import com.gan.tetsing.REST.RetrofitInterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RetrofitInterface rfInterface;
    LinearLayout layoutProgress;
    EditText edUser, edPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        layoutProgress = (LinearLayout) findViewById(R.id.layoutProgress);
        rfInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProcess();
            }
        });
    }

    private boolean validateInputs() {
        edUser = (EditText) findViewById(R.id.edUser);
        edPass = (EditText) findViewById(R.id.edPass);


        return edUser.getText().toString().length() > 0
                && edPass.getText().toString().length() > 0;
    }

    private void loginProcess(){
        if(validateInputs()) {
            Map<String,String> parameter = new HashMap<>();
            parameter.put("user",edUser.getText().toString());
            parameter.put("pass",edPass.getText().toString());


            layoutProgress.setVisibility(View.VISIBLE);

            Call<GetLogin> getLoginCall = rfInterface.getLogin(parameter);
            getLoginCall.enqueue(new Callback<GetLogin>() {
                @Override
                public void onResponse(Call<GetLogin> call, Response<GetLogin> response) {
                    if (response.code() == 200){
                        String nama = response.body().getLogin().getNamaUser();
                        Toast.makeText(MainActivity.this, "Welcome " + nama, Toast.LENGTH_LONG).show();
                        keMainMenu();
                    } else if (response.code() == 400) {
                        Toast.makeText(MainActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Address Not Found", Toast.LENGTH_SHORT).show();
                    }
                    layoutProgress.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<GetLogin> call, Throwable t) {
                    Log.d("failure", t.getMessage());
                    layoutProgress.setVisibility(View.GONE);
                }
            });
        } else {
            Toast.makeText(this, "Input are Incorrect", Toast.LENGTH_LONG).show();
        }
    }

    private void keMainMenu() {
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        startActivity(intent);
    }
}
