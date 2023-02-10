package com.example.sqltutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqltutorial.db.DBcomments;

import java.util.List;

public class NuevaActivity extends AppCompatActivity {
    EditText name,comment,namerq;
    Spinner mSpinner;
    Button submmit;
    TextView showcom;

    Button request;
    private void limpiar(){
        name.setText("");
        comment.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva);
        namerq = (EditText) findViewById(R.id.editNameComm2);
        showcom = (TextView) findViewById(R.id.CommentArea);
        name = (EditText) findViewById(R.id.editNameComm);
        comment = (EditText) findViewById(R.id.editComm);
        submmit = (Button) findViewById(R.id.btnSubmit);
        request = (Button) findViewById(R.id.btnrequest);
        mSpinner = (Spinner) findViewById(R.id.cmntspp);
        loadSpinnerData();
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBcomments dBcomments= new DBcomments(NuevaActivity.this);
                String comment = dBcomments.SelectComment(namerq.getText().toString());
                showcom.setText(comment);

            }
        });
        submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBcomments dBcomments= new DBcomments(NuevaActivity.this);
               long id= dBcomments.insertaComment(name.getText().toString(),comment.getText().toString());
                if (id > 0){
                    Toast.makeText(NuevaActivity.this,"insertado",Toast.LENGTH_LONG);
                    limpiar();
                    loadSpinnerData();
                }
            }
        });
    }
    private void loadSpinnerData() {
        DBcomments db = new DBcomments(getApplicationContext());
        List<String> labels = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mSpinner.setAdapter(dataAdapter);
    }
}