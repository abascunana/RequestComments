package com.example.sqltutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sqltutorial.db.DbHelper;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    //UTILIZAR ESTOS VÍDEOS https://www.youtube.com/watch?v=iWQIXjQ8ucA&list=PL-Mlm_HYjCo-rlZFTSTsrCOGODT_TTWqp
Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.btnCrear);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DbHelper dbHelper = new DbHelper(MainActivity.this);

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                dbHelper.onCreate(db);
                if (db != null){
                    Log.println(Log.ASSERT,"mensaje","Base Creada");
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menunuevo:
                nuevoRegistro();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this,NuevaActivity.class);
        startActivity(intent);
    }
}