package com.example.chatbot_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private EditText editText;
    private RecyclerView recyclerView;
    private final String BOT_Key="bot";
    private final String User_Key="user";
    private ArrayList<chatModel> arrayList;
    private MyAdaper myAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.Input_message_editetext_id);
        floatingActionButton=findViewById(R.id.Send_button_id);
        recyclerView=findViewById(R.id.Recycle_view_id);
        arrayList =new ArrayList<>();
        myAdaper=new MyAdaper(arrayList,this);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(myAdaper);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Enter your Message", Toast.LENGTH_SHORT).show();
                    return;
                }

                getResponse(editText.getText().toString());
                editText.setText("");
            }


        });
    }
    private void getResponse(String message) {
        arrayList.add(new chatModel(message,User_Key));
        myAdaper.notifyDataSetChanged();
        String Url="http://api.brainshop.ai/get?bid=165007&key=yArPXMcluwnwUWso&uid=[uid]&msg="+message;
        String Base_Url="http://api.brainshop.ai/";

        Retrofit retrofit=new Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitApi retrofitApi=retrofit.create(RetrofitApi.class);
        Call<msgModel> call=retrofitApi.getMessage(Url);
        call.enqueue(new Callback<msgModel>() {
            @Override
            public void onResponse(Call<msgModel> call, Response<msgModel> response) {
                if (response.isSuccessful())
                {
                    msgModel msgModel=response.body();
                    arrayList.add(new chatModel(msgModel.getCnt(),BOT_Key));
                    myAdaper.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<msgModel> call, Throwable t) {
                arrayList.add(new chatModel("Please revert your question",BOT_Key));
                myAdaper.notifyDataSetChanged();
            }
        });
    }
}