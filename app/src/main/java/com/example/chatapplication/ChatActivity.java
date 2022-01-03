package com.example.chatapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chatapplication.adapter.MessageAdapter;
import com.example.chatapplication.model.Message;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    TextView tvTitle;
    EditText edtMessage;
    RecyclerView recyclerView;
    FloatingActionButton btnSubmit;
    String sender, receiver;
    List<Message> messageList;
    ListenerRegistration listner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        tvTitle = findViewById(R.id.tvTitle);
        edtMessage = findViewById(R.id.edtMessage);
        recyclerView = findViewById(R.id.recyclerView);
        btnSubmit = findViewById(R.id.btnSend);
        messageList = new ArrayList<>();
        sender = getIntent().getStringExtra("sender");
        receiver = getIntent().getStringExtra("receiver");


        tvTitle.setText("Chatting With " + receiver);
        sendMessage();
        setRealTimeUpdate();

    }

    private void sendMessage() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = Long.toString(System.currentTimeMillis());
                com.example.chatapplication.model.Message message = new com.example.chatapplication.model.Message(sender, receiver, edtMessage.getText().toString(), time);

                FirebaseFirestore.getInstance().collection("Chat").document(time).set(message);
                edtMessage.setText("");
            }
        });


    }

    private void setRealTimeUpdate() {

        listner = FirebaseFirestore.getInstance().collection("Chat").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (value != null) {
                    messageList = value.toObjects(com.example.chatapplication.model.Message.class);
                    initRecyclerView();

                }
            }
        });

    }

    private void initRecyclerView() {
        MessageAdapter messageAdapter = new MessageAdapter(messageList, sender);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatActivity.this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(messageAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        listner.remove();
    }
}