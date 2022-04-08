package com.example.testDB;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ex220317.R;

public class DBconnectActivity extends AppCompatActivity {
    Button registerBtn;
    EditText idet, pwet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbconnect);

        setTitle("ORACLE");

        registerBtn = (Button)findViewById(R.id.register_btn);
        idet = (EditText)findViewById(R.id.register_id);
        pwet = (EditText)findViewById(R.id.register_pw);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String result;
                    String id = idet.getText().toString();
                    String pw = pwet.getText().toString();

                    RegisterActivity task = new RegisterActivity();
                    result = task.execute(id, pw).get();
                    // receiveMsg값을 받는
                } catch (Exception e) {
                    Log.i("DBtest", ".....ERROR.....!");
                }
            }
        });

    }
}