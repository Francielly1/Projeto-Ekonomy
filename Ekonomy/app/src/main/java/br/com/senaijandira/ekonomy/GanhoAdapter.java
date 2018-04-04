package br.com.senaijandira.ekonomy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class GanhoAdapter extends ArrayAdapter<Ganho> {

    //construtor
    public GanhoAdapter (Context ctx, ArrayList<Ganho> lst){
        super(ctx, 0, lst);
    }

    //Monta os itens e os mostram na tela. View: qualquer componente que seja exibido na tela

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null){
            //lê o arquivo e retorna em formato view
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, null);
        }

        Ganho item = getItem(position);

        TextView txt_item_categoria = v.findViewById(R.id.txt_item_categoria);
        TextView txt_item_valor = v.findViewById(R.id.txt_item_valor);
        TextView txt_item_data = v.findViewById(R.id.txt_item_data);

        txt_item_categoria.setText(item.getCategoria());
        txt_item_valor.setText(item.getValorGanho());
        txt_item_data.setText(item.getDtnasc());

        return v;
    }


}
