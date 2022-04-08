package com.example.ex220317;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserid, edtUserpw;
    Button btnLogin, btnUserJoin;

    private static RequestQueue requestQueue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserid=findViewById(R.id.edtUserid);
        edtUserpw=findViewById(R.id.edtUserpw);
        btnLogin=findViewById(R.id.btnLogin);
        btnUserJoin=findViewById(R.id.btnUserJoin);

        if(requestQueue ==null){
            requestQueue= Volley.newRequestQueue(LoginActivity.this);
        }

        //버튼을 클릭했을때 사용자가 입력한 아이디 , 비밀벊호를 서버로 전송하는기능구현
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId= edtUserid.getText().toString();
                String userPw=edtUserpw.getText().toString();
                String serverUrl="http://222.102.104.36:8082/AndroidServer/LoginService";

                request = new StringRequest(
                        Request.Method.POST,
                        serverUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //로그인 성공시 닉네임 반환
                                //아니면 0 반환

                                if(response.equals("0")){
                                    Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(LoginActivity.this, response+"님 로그인", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        })
                        {

                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        //전송방식이 POST일 때 사용하는 메소드
                        //-> 데이터를 전달
                        //->파일관련 데이터를 전달할때

                        //인터페이스로 된 MAP이라 자식클래스로 구현한 걸 넣어줘야
                        Map<String, String> param = new HashMap<>(); //실제는 뭐 hashmap?
                        param.put("userId", userId); //key = server , value= 데이터 -> 서버한테는 key+value로 넘어간다.
                        param.put("userPw", userPw);

                        return param; //여기서 전달된당당
                    }
                        };
                    requestQueue.add(request);

            }
        });


    }

}