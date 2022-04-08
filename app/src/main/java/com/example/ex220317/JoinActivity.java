package com.example.ex220317;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class JoinActivity extends AppCompatActivity {

    EditText edtJoinId, edtJoinPw, edtJoinNick;
    Button btnJoin;

    RequestQueue requestQueue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);


        edtJoinId = findViewById(R.id.edtJoinid);
        edtJoinPw=findViewById(R.id.edtJoinPw);
        edtJoinNick= findViewById(R.id.edtJoinNick);

        btnJoin= findViewById(R.id.btnJoin);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtJoinId.getText().toString();
                String pw =edtJoinPw.getText().toString();
                String nick= edtJoinNick.getText().toString();
                String serverURL= "";

                request=new StringRequest(
                        Request.Method.POST,
                        serverURL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //response->0 또는 1

                                if(response.equals("1")){
                                    //회원가입 성공시 loginActivity 로 이동할것
                                    Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(JoinActivity.this, "가입 성공", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(JoinActivity.this, "회원가입 실패!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Join",error.toString());
                            }
                        }
                ){
                    @Nullable
                    @Override
                    protected  Map<String, String> getParams() throws AuthFailureError{
                    return super.getParams();
                    }
                };

                requestQueue.add(request);
            }
        });
    }
}