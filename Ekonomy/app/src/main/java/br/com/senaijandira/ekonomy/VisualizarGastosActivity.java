package br.com.senaijandira.ekonomy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class VisualizarGastosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView list_view_gastos;
    GastoAdapter adapter;
    GastoDAO dao;
    TextView txt_saldo_disponivel_visu_gasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_gastos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_saldo_disponivel_visu_gasto = (TextView)findViewById(R.id.txt_saldo_disponivel_visu_gasto);

         /*CRIANDO LISTA*/
        //Pegando Instancia do DAO
        dao = GastoDAO.getInstance();

        list_view_gastos = (ListView) findViewById(R.id.list_view_gastos);

        //adaptador vazio
        adapter = new GastoAdapter(this, new ArrayList<Gasto>());

        //conectando adaptador na lista
        list_view_gastos.setAdapter(adapter);

        list_view_gastos.setOnItemClickListener(this);
    }

    /*ATUALIZANDO PÁGINA INDEPENDENTE DO BOTÃO UTILLIZADO PARA VOLTAR*/
    @Override
    protected void onResume() {
        //Pegando os contatos do banco
        ArrayList<Gasto> gastosCadastrados;
        gastosCadastrados = dao.selecionarTodos(this);

        //limpar o conteudo
        adapter.clear();

        //preenchendo o adaptador com os dados atualizados
        adapter.addAll(gastosCadastrados);
        super.onResume();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        //Pegado o ganho da posição (i = posição) da lista
        Gasto item = adapter.getItem(i);

        //criando objeto de intenção
        Intent intent = new Intent(this, DetalhesGastoActivity.class);

        //passando id do ganho e abrindo tela de visualizar
        intent.putExtra("id", item.getId());

        startActivity(intent);

        //Toast.makeText(this, "Cliquei na lista", Toast.LENGTH_SHORT).show();
    }

}
