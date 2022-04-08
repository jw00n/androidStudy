package com.example.ex220317;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class testJsonActivity extends AppCompatActivity {

    Button btnJson;
    TextView tvJson;

    RequestQueue requestQueue;
    StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_json);

        btnJson=findViewById(R.id.btnJson);
        tvJson=findViewById(R.id.tvJson);

        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(testJsonActivity.this);
        }

        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String serverUrl="http://121.147.52.219:8081/TestServer/TestService2";

                //서버에 요청한 후 응답 데이터를 log.d()로 출력하시오.
                //요청방식 / 서버주소 / 응답데이터 처리 / 에러처리
                request=new StringRequest(
                        Request.Method.GET,
                        serverUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.d("응답",response);

                                try {
                                    JSONArray array=new JSONArray(response);
                                    StringBuffer sb = new StringBuffer();
                                    //많은양을 연산할경우 버퍼를 이용한다.

                                    for( int i=0; i<array.length(); i++){
                                        JSONObject obj=(JSONObject) array.get(i);
                                        //배열에서 꺼내올때는 객체로 변환시켜서

                                        String proNum = obj.getString("proNum");
                                        String proName= obj.getString("proName");
                                        String proPrice = obj.getString("proPrice");

                                        sb.append(proNum);
                                        sb.append("/");
                                        sb.append(proName);
                                        sb.append("/");
                                        sb.append(proPrice);
                                        sb.append("\n");
                                        
                                        
                                    }
                                    //결과 보여주기
                                    tvJson.setText(sb.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }

                );
                requestQueue.add(request);
            }
        });
    }
}