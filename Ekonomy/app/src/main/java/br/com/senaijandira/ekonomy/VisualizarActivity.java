package br.com.senaijandira.ekonomy;

import android.app.Activity;
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

public class VisualizarActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView list_view_ganhos;
    GanhoAdapter adapter;
    GanhoDAO dao;
    TextView txt_saldo_disponível_visu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_saldo_disponível_visu = (TextView)findViewById(R.id.txt_saldo_disponivel_visu);

        /*CRIANDO LISTA*/
        //Pegando Instancia do DAO
        dao = GanhoDAO.getInstance();

        list_view_ganhos = (ListView) findViewById(R.id.list_view_ganhos);

        //adaptador vazio
        adapter = new GanhoAdapter(this, new ArrayList<Ganho>());

        //conectando adaptador na lista
        list_view_ganhos.setAdapter(adapter);


      list_view_ganhos.setOnItemClickListener(this);

    }

    /*ATUALIZANDO PÁGINA INDEPENDENTE DO BOTÃO UTILLIZADO PARA VOLTAR*/
    @Override
    protected void onResume() {
        //Pegando os contatos do banco
        ArrayList<Ganho> ganhosCadastrados;
        ganhosCadastrados = dao.selecionarTodos(this);

        //limpar o conteudo
        adapter.clear();

        //preenchendo o adaptador com os dados atualizados
        adapter.addAll(ganhosCadastrados);
        super.onResume();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        //Pegado o ganho da posição (i = posição) da lista
        Ganho item = adapter.getItem(i);

        //criando objeto de intenção
        Intent intent = new Intent(this, DetalhesGanhoActivity.class);

        //passando id do ganho e abrindo tela de visualizar
        intent.putExtra("id", item.getId());

        startActivity(intent);

        //Toast.makeText(this, "Cliquei na lista", Toast.LENGTH_SHORT).show();
    }

}
