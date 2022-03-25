package com.example.chatbot_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdaper extends RecyclerView.Adapter {

    private ArrayList<chatModel> chatModelArrayList;
    private Context context;

    public MyAdaper(ArrayList<chatModel> chatModelArrayList, Context context) {
        this.chatModelArrayList = chatModelArrayList;
        this.context = context;
    }

    public MyAdaper() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_layout,parent,false);
                return  new UserViewHolder(view);

            case 1:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_msg_layout,parent,false);
                return  new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
     chatModel chatModel=chatModelArrayList.get(position);
     switch (chatModel.getSender()){
         case "user":
             ((UserViewHolder)holder).UserTv.setText(chatModel.getMessage());
             break;

         case "bot":
             ((BotViewHolder)holder).BootMsg.setText(chatModel.getMessage());
             break;

     }
    }

    @Override
    public int getItemViewType(int position) {
        switch (chatModelArrayList.get(position).getSender()){
            case "user":
                return 0;

            case "bot":
                return 1;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return chatModelArrayList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
         TextView UserTv;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            UserTv=itemView.findViewById(R.id.User_msg_id);
        }
    }
    public static class BotViewHolder extends RecyclerView.ViewHolder{
          TextView BootMsg;
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            BootMsg=itemView.findViewById(R.id.Admin_msg_is);
        }
    }
}
