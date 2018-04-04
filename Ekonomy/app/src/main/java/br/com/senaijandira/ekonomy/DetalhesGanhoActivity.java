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
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class DetalhesGanhoActivity extends AppCompatActivity {

    TextView txt_categoria_detalhes_ganho, txt_valorganho_ganho, txt_desc_ganho, txt_data_ganho;
    Button btn_editar_ganho, btn_excluir_ganho;
    Integer id;
    Ganho ganho;
    GanhoDAO dao;
    GanhoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_ganho);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dao = GanhoDAO.getInstance();

        txt_categoria_detalhes_ganho = (TextView) findViewById(R.id.txt_categoria_detalhes_ganho);
        txt_valorganho_ganho = (TextView) findViewById(R.id.txt_valorganho_ganho);
        txt_desc_ganho = (TextView) findViewById(R.id.txt_desc_ganho);
        txt_data_ganho = (TextView) findViewById(R.id.txt_data_ganho);
        btn_editar_ganho = (Button) findViewById(R.id.btn_editar_ganho);
        btn_excluir_ganho = (Button) findViewById(R.id.btn_excluir_ganho);

        //acessar id passado por Itent
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //selecionar contato do banco
        ganho = GanhoDAO.getInstance().selectionarUm(this, id);
        txt_categoria_detalhes_ganho.setText(ganho.getCategoria());
        txt_valorganho_ganho.setText(ganho.getValorGanho());
        txt_desc_ganho.setText(ganho.getDescricao());
        txt_data_ganho.setText(ganho.getDtnasc());

    }

    public void abrirEditarGanho(View view) {

        //abre a tela de editar
        Intent intent = new Intent(this, CadastroGanhoActivity.class);

        //passando id do ganho e abrindo tela de visualizar
        intent.putExtra("id", id);

        startActivity(intent);
    }


    public void abrirExcluirGanho(View view) {

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
                GanhoDAO.getInstance() .remover(context, id);
                finish();
            }
        });

        //se o usuario clicar em 'NÃO' faz isso
        builder.setNegativeButton("NÃO", null);
        builder.create() .show();

    }
}
