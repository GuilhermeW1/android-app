package com.example.acoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.acoes.adapter.MovementAdapter;
import com.example.acoes.controller.MovementController;
import com.example.acoes.models.Movement;

import java.util.ArrayList;

public class MovementHistory extends AppCompatActivity {

    ListView movementListView;
    Context context;
    MovementController controller;
    MovementAdapter adapter;
    ArrayList<Movement> listMovement;
    int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movement_history);

        context = MovementHistory.this;
        movementListView = findViewById(R.id.movementActivity_list);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            idUser = extras.getInt("id", 0);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    private void atualizarLista(){
        try {
            controller = new MovementController(context);
            listMovement = controller.lista(idUser);

            adapter = new MovementAdapter(context, listMovement);
            movementListView.setAdapter(adapter);

        }catch (Exception ex){
           // Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO", ex.getMessage());
        }
    }
}