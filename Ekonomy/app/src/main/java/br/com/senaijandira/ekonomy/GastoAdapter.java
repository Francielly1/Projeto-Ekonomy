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

public class GastoAdapter  extends ArrayAdapter<Gasto> {

    //construtor
    public GastoAdapter (Context ctx, ArrayList<Gasto> lst){
        super(ctx, 0, lst);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null){
            //lê o arquivo e retorna em formato view
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item_gasto, null);
        }

        Gasto item = getItem(position);

        TextView txt_item_categoria_gasto = v.findViewById(R.id.txt_item_categoria_gasto);
        TextView txt_item_valor_gasto = v.findViewById(R.id.txt_item_valor_gasto);
        TextView txt_item_data_gasto = v.findViewById(R.id.txt_item_data_gasto);

        txt_item_categoria_gasto.setText(item.getCategoriaGasto());
        txt_item_valor_gasto.setText(item.getValorGasto());
        txt_item_data_gasto.setText(item.getData());

        return v;
    }


}
