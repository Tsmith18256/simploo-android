package com.simploo.simplooapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.simploo.simplooapp.ApiClient.PingInterface;
import com.simploo.simplooapp.ApiClient.TodoInterface;
import com.simploo.simplooapp.DataModel.Ping;
import com.simploo.simplooapp.DataModel.Todo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String ENDPOINT_URL = "http://10.10.39.35:5000";
    private TextView resultTv;
    private TodoInterface getTodos;
    private PingInterface getPing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit rf = new Retrofit.Builder()
                .baseUrl(ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getPing = rf.create(PingInterface.class);
//        getTodos = rf.create(TodoInterface.class);

//        Button allBtn = (Button) findViewById(R.id.getallBtn);
//        allBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadTodos();
//            }
//        });
//
//        Button oneBtn = (Button) findViewById(R.id.oneBtn);
//        oneBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loadTodo(1);
//            }
//        });

        Button pingBtn = (Button) findViewById(R.id.pingBtn);
        pingBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loadPing();
            }
        });

        resultTv = (TextView) findViewById(R.id.resultTv);


    }

//    private void loadTodos(){
//        Call<Todo> call = getTodos.all();
//        call.enqueue(new Callback<Todo>() {
//            @Override
//            public void onResponse(Call<Todo> call, Response<Todo> response) {
//                Todo result = response.body();
//                displayResult(result);
//            }
//
//            @Override
//            public void onFailure(Call<Todo> call, Throwable t) {
//
//            }
//        });
//    }

//    private void displayResult(Todo res) {
//        if(res != null){
//            List<Todo> todos = res.getTodos();
//            String tmp = "";
//            for(Todo todo: todos){
//                tmp+= todo.getId() + " | " + todo.getTitle() + " | " + (todo.getCompleted() ? "Completed" : "Todo");
//                resultTv.setText(tmp);
//            }
//        } else{
//            resultTv.setText("Error getting todos");
//        }
//    }

//    private void loadTodo(int id){
//        Call<Todo> call = getTodos.select(id);
//        call.enqueue(new Callback<Todo>() {
//            @Override
//            public void onResponse(Call<Todo> call, Response<Todo> response) {
//                displayTodo(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Todo> call, Throwable t) {
//
//            }
//        });
//    }

//    private void displayTodo(Todo todo) {
//        if(todo != null) {
//            String tmp = todo.getId() + " | " + todo.getTitle() + " | " + (todo.getCompleted() ? "Completed" : "Todo");
//            resultTv.setText(tmp);
//        } else{
//            resultTv.setText("Error getting todo");
//        }
//    }

    private void loadPing(){
        Call<Ping> call = getPing.ping();
        call.enqueue(new Callback<Ping>() {
            @Override
            public void onResponse(Call<Ping> call, Response<Ping> response) {
                displayPing(response.body());
            }

            @Override
            public void onFailure(Call<Ping> call, Throwable t) {

            }
        });
    }

    private void displayPing(Ping ping) {
        if(ping != null){
            String tmp = "Ping localhost: " + ping.getSuccess();
            resultTv.setText(tmp);

        } else {
            resultTv.setText("Error getting ping");
        }
    }
}
