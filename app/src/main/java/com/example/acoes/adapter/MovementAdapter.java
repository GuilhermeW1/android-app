package com.example.acoes.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.acoes.AddMovement;
import com.example.acoes.R;
import com.example.acoes.controller.MovementController;
import com.example.acoes.models.Movement;
import com.example.acoes.tools.Tools;

import java.util.ArrayList;


public class MovementAdapter extends ArrayAdapter<Movement> {

    private final Context context;
    private final ArrayList<Movement> elementos;
    MovementController controller;

    public MovementAdapter(Context context, ArrayList<Movement> elementos){
        super(context, R.layout.item_movement_history, elementos);//todo aqiuwer
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        try{

            Movement objeto = elementos.get(position);

            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //toda vez que passa por um item da lista, os elementos são associados
            View rowView = inflater.inflate(R.layout.item_movement_history, parent, false);

            TextView acao = rowView.findViewById(R.id.itemMovementList_label_cdacao);
            TextView vlrMov = rowView.findViewById(R.id.itemMovementList_label_valorCompra);
            TextView qntdMov = rowView.findViewById(R.id.itemMovementList_label_qntd);
            TextView dataMov = rowView.findViewById(R.id.itemMovementList_label_data_movement);

            ///////////
            MovementController controller = new MovementController(context);

            String teste = controller.findAcaoById(objeto.getId_acao());
            acao.setText(teste);
            vlrMov.setText(String.valueOf(objeto.getVlr_total()));
            qntdMov.setText(String.valueOf(objeto.getQntd_total_acoes()));

            String data = Tools.converterData(objeto.getDate(), "yyyy-MM-dd", "dd/MM/yyyy");
            dataMov.setText(data);

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent tela = new Intent(context, AddMovement.class);
                    tela.putExtra("id_movement", objeto.getId_mov());
                    context.startActivity(tela);
                }
            });

            return rowView;

        }catch (Exception ex){
            //Tools.toastMessage(ex.getMessage(), context);
            Log.e("ERRO LANGUAGE ADAPTER GETVIEW", ex.getMessage());
            return null;
        }
    }
}
