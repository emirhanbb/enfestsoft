package com.enesgul.ticaretsmilator;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OdemeTuru extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    CardView mobilOdeme, krediKarti, bankaHavale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odeme_turu);

        bottomNavigationView = findViewById(R.id.bottommenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(menulistener);
        mobilOdeme = findViewById(R.id.mobilOdeme);
        krediKarti = findViewById(R.id.krediKarti);

        mobilOdeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OdemeTuru.this,Odeme.class);
                startActivity(intent);
            }
        });

        krediKarti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OdemeTuru.this,Main2Activity.class);
                intent.putExtra("link","https://enfessoft.com/ticaretsimulator/market/kredikarti.php");
                startActivity(intent);
            }
        });



    }


    private BottomNavigationView.OnNavigationItemSelectedListener menulistener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){

                case R.id.mesajlar:
                    Intent intent = new Intent(OdemeTuru.this,Main2Activity.class);
                    intent.putExtra("link","https://enfessoft.com/ticaretsimulator/gelenkutusu.php");
                    startActivity(intent);
                    break;
                case R.id.siralama:
                    Intent intent2 = new Intent(OdemeTuru.this,Main2Activity.class);
                    intent2.putExtra("link","https://enfessoft.com/ticaretsimulator/siralama.php");
                    startActivity(intent2);
                    break;

                case R.id.sohbet:
                    Intent intent3 = new Intent(OdemeTuru.this,Main2Activity.class);
                    intent3.putExtra("link","https://enfessoft.com/ticaretsimulator/sohbet.php");
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OdemeTuru.this,Main2Activity.class);
        intent.putExtra("link","https://enfessoft.com/ticaretsimulator/");
        startActivity(intent);
        finish();
    }
}