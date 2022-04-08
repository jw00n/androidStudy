package com.example.ex220317;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    EditText edtURL;
    Button btnRequest;

    //Volley 1.2.1에 있는 객체
    RequestQueue requestQueue; // 서버쪽으로 요청객체를 보내주는 역할
    StringRequest stringRequest; // 요청 /응답에 대한 내용을 설정하는 객체


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult= findViewById(R.id.tvResult);
        edtURL = findViewById(R.id.edtURL);
        btnRequest=findViewById(R.id.btnRequest);

        //requestQueue 초기화
        if(requestQueue ==null){
            requestQueue= Volley.newRequestQueue(MainActivity.this); //context, 즉 액티비티에 대한 정보가 필요
        }
     //   btnRequest.setOnClickListener(new View.OnClickListener() {
       //     @Override
         //   public void onClick(View view) {
                String url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20220316";
                //StringRequest 객체 생성
                //서버 경로 , 전송 방식(get, post) , 응답 데이터 처리, 에러 처리
                stringRequest = new StringRequest(
                        Request.Method.POST, //전송방식
                        url, //경로
                        new Response.Listener<String>() {//응답데이터 처리
                            @Override
                            public void onResponse(String response) {
                                //response : 서버에서 전달한 데이터를 저장하는 변수


                                try {
                                    JSONObject obj=new JSONObject(response);
                                    JSONObject result=obj.getJSONObject("boxOfficeResult");
                                    JSONArray list = result.getJSONArray("dailyBoxOfficeList");

                                    tvResult.setText(list.toString());


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }



                            }
                        }, new Response.ErrorListener() { //에러 처리
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("MainActivity",error.toString());
                            }
                        });

                //requestQueue 객체에 요청객체를 전달하는 작업
                requestQueue.add(stringRequest);

      //      }
    //    });
    }
}