package br.com.senaijandira.ekonomy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CadastroGanhoActivity extends AppCompatActivity {

    RadioGroup grupo_radio;
    RadioButton rdo_salario, rdo_poupanca, rdo_investimento;
    EditText txt_cadastro_valorganho, txt_cadastro_descricao, txt_cadastro_data;
    Button btn_cadastrar_ganho;
    GanhoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ganho);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Pegando Instancia do DAO
        dao = GanhoDAO.getInstance();

        //FIND VIEW BY IDS
        grupo_radio = (RadioGroup) findViewById(R.id.grupo_radio);
        rdo_salario = (RadioButton) findViewById(R.id.rdo_salario);
        rdo_poupanca = (RadioButton) findViewById(R.id.rdo_poupanca);
        rdo_investimento = (RadioButton) findViewById(R.id.rdo_investimento);

        txt_cadastro_valorganho = (EditText) findViewById(R.id.txt_cadastro_valorganho);
        txt_cadastro_descricao = (EditText) findViewById(R.id.txt_cadastro_descricao);
        txt_cadastro_data = (EditText) findViewById(R.id.txt_cadastro_data);
        btn_cadastrar_ganho = (Button) findViewById(R.id.btn_cadastrar_ganho);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*CADASTRAR CONTATO*/
    public void cadastrarGanho(View view) {
        Ganho g = new Ganho();

        //Adicionando campos
        //Realizando cálculo de acordo com a opção selecionada
        if (rdo_salario.isChecked()){
            g.setCategoria(rdo_salario.getText().toString());
        }else if (rdo_investimento.isChecked()){
            g.setCategoria(rdo_investimento.getText().toString());
        }else if (rdo_poupanca.isChecked()){
            g.setCategoria(rdo_poupanca.getText().toString());
        }

        g.setValorGanho(txt_cadastro_valorganho.getText().toString());
        g.setDescricao(txt_cadastro_descricao.getText().toString());
        g.setDtnasc(txt_cadastro_data.getText().toString());

        GanhoDAO.getInstance().inserir(this, g);

        finish();

    }
}
