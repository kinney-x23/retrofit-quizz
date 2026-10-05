package com.example.quizzz;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Button buttonNastepne;
    RadioButton radioButtonA,radioButtonB,radioButtonC;
    RadioGroup radioGroupPytania;
    TextView textView;
    List<Pytanie> pytaniaZInternetu;

    int nr_pytania;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        buttonNastepne = findViewById(R.id.button);
        radioGroupPytania = findViewById(R.id.radiogroup);
        radioButtonA = findViewById(R.id.radioButton);
        radioButtonB = findViewById(R.id.radioButton2);
        radioButtonC = findViewById(R.id.radioButton3);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/kinney-x23/retrofit_jsonPytania/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //mamy zbudowanego retrofita

        Placeholder jsonPlaceHolder = retrofit.create(Placeholder.class);
        //zbiera pytania z plliku db
        Call<List<Pytanie>> call = jsonPlaceHolder.getPytania();
        //wiocha
        call.enqueue(new Callback<List<Pytanie>>() {
            @Override
            public void onResponse(Call<List<Pytanie>> call, Response<List<Pytanie>> response)
                {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,
                            response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                    pytaniaZInternetu = response.body();
                    textView.setText(pytaniaZInternetu.get(0).getTrescPytania());
                    wypiszPytanie(0);

                }

            @Override
            public void onFailure(Call<List<Pytanie>> call, Throwable throwable) {
            }
        });

        buttonNastepne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sprawdzCzyDobrze(nr_pytania)){
                    Toast.makeText(MainActivity.this,"ok", Toast.LENGTH_SHORT).show();
                    nr_pytania++;
                    if(nr_pytania<pytaniaZInternetu.size())
                    {
                        wypiszPytanie(nr_pytania);
                    }
                }

                nr_pytania++;
                wypiszPytanie(nr_pytania);


            }
        });
    }

    private boolean sprawdzCzyDobrze(int nr_pytania)
    {
        int kliknieteId = radioGroupPytania.getCheckedRadioButtonId();
        int indeksOK = pytaniaZInternetu.get(nr_pytania).getPoprawna();
        int[] indeksy = new int[]{R.id.radioButton,R.id.radioButton3,R.id.radioButton2 };
        if(kliknieteId == indeksy[indeksOK])
        {
            return true;
        }
        return false;
    }
    private void wypiszPytanie(int nrPytania){
        radioGroupPytania.clearCheck();
        textView.setText(pytaniaZInternetu.get(nrPytania).getTrescPytania());
        radioButtonA.setText(pytaniaZInternetu.get(nrPytania).getOdpA());
        radioButtonB.setText(pytaniaZInternetu.get(nrPytania).getOdpB());
        radioButtonC.setText(pytaniaZInternetu.get(nrPytania).getOdpC());
    }
}