package br.com.senaijandira.ekonomy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CadastroGastActivity extends AppCompatActivity {

     EditText txt_cadastro_valorgasto, txt_cadastro_descricao_gasto, txt_cadastro_data_gasto;
     Button btn_cadastrar_gasto;
     Spinner sp_categoria;

     private static String[] CATEGORIAS = new String[]{
             "Lazer", "Transporte", "Saúde", "Moradia"
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_gast);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FIND VIEW BY IDS
        txt_cadastro_valorgasto = (EditText) findViewById(R.id.txt_cadastro_valorgasto);
        txt_cadastro_descricao_gasto = (EditText) findViewById(R.id.txt_cadastro_descricao_gasto);
        txt_cadastro_data_gasto = (EditText) findViewById(R.id.txt_cadastro_data_gasto);
        btn_cadastrar_gasto = (Button) findViewById(R.id.btn_cadastrar_gasto);


        sp_categoria = (Spinner) findViewById(R.id.sp_categoria);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, CATEGORIAS);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_categoria.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void cadastrarGasto(View view) {
        Gasto gt = new Gasto();

        //Adicionando campos
        gt.setCategoriaGasto(sp_categoria.getSelectedItem().toString());
        gt.setValorGasto(txt_cadastro_valorgasto.getText().toString());
        gt.setDescricaoGasto(txt_cadastro_descricao_gasto.getText().toString());
        gt.setData(txt_cadastro_data_gasto.getText().toString());


        GastoDAO.getInstance().inserir(this, gt);
        finish();

    }
}












