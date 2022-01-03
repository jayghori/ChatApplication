package com.example.chatapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapplication.R;
import com.example.chatapplication.model.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    List<Message> messageList;
    String senderName;
    int TYPE_SENDER=0;
    int TYPE_RECEIVER=1;

    public MessageAdapter(List<Message> messageList, String senderName) {
        this.messageList = messageList;
        this.senderName = senderName;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        if (viewType==TYPE_SENDER){
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sender,parent,false));
        }else
        {
            return  new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_receiver,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull  MessageAdapter.ViewHolder holder, int position) {

        holder.textView.setText(messageList.get(position).getMessage());
    }

    @Override
    public int getItemViewType(int position) {

        if (messageList.get(position).getSenderName().equals(senderName))
            return TYPE_SENDER;

        else
            return TYPE_RECEIVER;
     }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.textView);

        }
    }
}
