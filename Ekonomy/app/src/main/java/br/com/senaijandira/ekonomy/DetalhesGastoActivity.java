package br.com.senaijandira.ekonomy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetalhesGastoActivity extends AppCompatActivity {

    TextView txt_categoria_detalhes_gasto, txt_valorgasto_gasto, txt_desc_gasto, txt_data_gasto;
    Button btn_editar_gasto, btn_excluir_gasto;
    Integer id;
    Gasto gasto;
    GastoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_gasto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dao = GastoDAO.getInstance();

        txt_categoria_detalhes_gasto = (TextView) findViewById(R.id.txt_categoria_detalhes_gasto);
        txt_valorgasto_gasto = (TextView) findViewById(R.id.txt_valorgasto_gasto);
        txt_desc_gasto = (TextView) findViewById(R.id.txt_desc_gasto);
        txt_data_gasto = (TextView) findViewById(R.id.txt_data_gasto);
        btn_editar_gasto = (Button) findViewById(R.id.btn_editar_gasto);
        btn_excluir_gasto = (Button) findViewById(R.id.btn_excluir_gasto);

        //acessar id passado por Itent
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        gasto = GastoDAO.getInstance().selectionarUm(this, id);
        txt_categoria_detalhes_gasto.setText(gasto.getCategoriaGasto());
        txt_valorgasto_gasto.setText(gasto.getValorGasto());
        txt_desc_gasto.setText(gasto.getDescricaoGasto());
        txt_data_gasto.setText(gasto.getData());
    }

    public void abrirEditarGasto(View view) {

        //abre a tela de editar
        Intent intent = new Intent(this, CadastroGastActivity.class);

        //passando id do ganho e abrindo tela de visualizar
        intent.putExtra("id", id);

        startActivity(intent);
    }



        public void abrirExcluirGasto(View view) {

            //excluir

            //cria caixa de dialogo para confirmar exclusão
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Excluir");
            builder.setMessage("Tem certeza que deseja excluir?");

            //se o usuario clicar em 'SIM' faz isso
            final Context context = this;
            builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    GastoDAO.getInstance() .remover(context, id);
                    finish();
                }
            });

            //se o usuario clicar em 'NÃO' faz isso
            builder.setNegativeButton("NÃO", null);
            builder.create() .show();
        }
}

