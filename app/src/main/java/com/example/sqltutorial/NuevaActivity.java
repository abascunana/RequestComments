package com.example.sqltutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqltutorial.db.DBcomments;

public class NuevaActivity extends AppCompatActivity {
    EditText name,comment,namerq;
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
                }
            }
        });
    }
}