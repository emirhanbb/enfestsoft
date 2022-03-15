package com.enesgul.ticaretsmilator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Connection extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
    }


    public void click(View view){

        Intent intent = new Intent(Connection.this, Main2Activity.class);
        startActivity(intent);
        finish();

    }
}
