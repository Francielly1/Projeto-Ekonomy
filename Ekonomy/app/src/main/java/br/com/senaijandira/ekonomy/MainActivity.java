package br.com.senaijandira.ekonomy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt_saldo_disponivel, txt_ganhos, txt_ganhos_disponivel, txt_salario, txt_poupanca, txt_investimento, txt_gastos, txt_lazer, txt_transporte, txt_saude, txt_moradia;
    Double salario;
    Double poupanca;
    Double investimento;
    Double lazer, transporte, saude, moradia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Find View By Id's
        txt_saldo_disponivel = (TextView) findViewById(R.id.txt_saldo_disponivel);
        txt_ganhos = (TextView) findViewById(R.id.txt_ganhos);
        txt_ganhos_disponivel = (TextView) findViewById(R.id.txt_ganhos_disponivel);
        txt_salario = (TextView) findViewById(R.id.txt_salario);
        txt_poupanca = (TextView) findViewById(R.id.txt_poupanca);
        txt_investimento = (TextView) findViewById(R.id.txt_investimento);
        txt_gastos = (TextView) findViewById(R.id.txt_gastos);
        txt_lazer = (TextView) findViewById(R.id.txt_lazer);
        txt_transporte = (TextView) findViewById(R.id.txt_transporte);
        txt_saude = (TextView) findViewById(R.id.txt_saude);
        txt_moradia = (TextView) findViewById(R.id.txt_moradia);

    }

    @Override
    protected void onResume() {
        super.onResume();
        visualizarSalario();
        visualizarInvestimento();
        visualizarPoupanca();
        visualizarLazer();
        visualizarTransporte();
        visualizarSaude();
        visualizarMoradia();
        atualizarCamposGanho();
        atualizarCamposGasto();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Função para abrir tela de adicionar GANHO
    public void abrirAdicionarGanho(View view) {
        //CRIANDO OBJETO DE INTENÇÃO
        Intent intencao = new Intent(this, CadastroGanhoActivity.class);
        //ABRINDO TELA: startActivity
        startActivity(intencao);
    }

    //Função para abrir tela de adicionar GASTO
    public void abrirAdicionarGasto(View view) {
        //CRIANDO OBJETO DE INTENÇÃO
        Intent intencao = new Intent(this, CadastroGastActivity.class);
        //ABRINDO TELA: startActivity
        startActivity(intencao);
    }

    //Função para mostrar o valor da Categoria: "VALOR"
     public void visualizarSalario(){

        salario = GanhoDAO.getInstance().selectionarPorCategoria(this, "Salário");

     }

    //Função para mostrar o valor da Categoria: "POUPANÇA"
    public void visualizarPoupanca(){

        poupanca = GanhoDAO.getInstance().selectionarPorCategoria(this, "Poupança");
       /*Log.d("poupanca", poupanca.toString());*/
    }

    //Função para mostrar o valor da Categoria: "INVESTIMENTO"
    public void visualizarInvestimento(){

        investimento = GanhoDAO.getInstance().selectionarPorCategoria(this, "Investimento");
        /*Log.d("investimento", investimento.toString());*/
    }

    //Função para mostrar o valor da Categoria: "Lazer"
    public void visualizarLazer(){

        lazer = GastoDAO.getInstance().selectionarPorCategoriaGasto(this, "Lazer");
    }

    //Função para mostrar o valor da Categoria: "Transpote"
    public void visualizarTransporte(){

        transporte = GastoDAO.getInstance().selectionarPorCategoriaGasto(this, "Transporte");
    }

    //Função para mostrar o valor da Categoria: "Saude"
    public void visualizarSaude(){

        saude = GastoDAO.getInstance().selectionarPorCategoriaGasto(this, "Saúde");
    }

    //Função para mostrar o valor da Categoria: "Moradia"
    public void visualizarMoradia(){

        moradia = GastoDAO.getInstance().selectionarPorCategoriaGasto(this, "Moradia");

    }

    public void atualizarCamposGanho(){
        if (salario == null){
            txt_salario.setText("R$ 0,00");
        }else if (poupanca == null){
            txt_poupanca.setText("R$ 0,00");
        }else if(txt_investimento == null){
            txt_investimento.setText("R$ 0,00");
        }else {
            txt_salario.setText("R$ " + salario.toString());
            txt_poupanca.setText("R$ " + poupanca.toString());
            txt_investimento.setText("R$ " + investimento.toString());
        }

        if (txt_saldo_disponivel == null) {
            txt_saldo_disponivel.setText("R$ 0,00");
        }else{
            txt_saldo_disponivel.setText("R$ " + (salario + poupanca + investimento - (lazer + transporte + saude + moradia))); //ganhos - gastos
        }

        if (txt_ganhos == null) {
            txt_ganhos.setText("R$ 0,00");
        }else{
            txt_ganhos.setText("R$ " + (salario + poupanca + investimento)); //ganhos - gastos
        }

        if(txt_ganhos_disponivel == null){
            txt_ganhos_disponivel.setText("R$ 0,00");
        }else{
            txt_ganhos_disponivel.setText("R$ " + (salario + poupanca + investimento - (lazer + transporte + saude + moradia)));
        }

    }

    public void atualizarCamposGasto(){
        if (txt_lazer == null){
            txt_lazer.setText("R$ 0,00");
        }else if (txt_transporte == null){
            txt_transporte.setText("R$ 0,00");
        }else if (txt_saude == null){
            txt_saude.setText("R$ 0,00");
        }else if (txt_moradia == null){
            txt_moradia.setText("R$ 0,00");
        }else{
            txt_lazer.setText("R$ " + lazer.toString());
            txt_transporte.setText("R$ " + transporte.toString());
            txt_saude.setText("R$ " + saude.toString());
            txt_moradia.setText("R$ " + moradia.toString());

            if (txt_gastos == null){
                txt_gastos.setText("R$ 0,00");
            }else {
                txt_gastos.setText("R$ " + (lazer + transporte + saude + moradia));

            }
        }
    }

    /*ABRIR TELA DE VISUALIZAR GANHOS*/
    public void abrirVisualizarGanhos(View view) {
        //CRIANDO OBJETO DE INTENÇÃO
        Intent intencao = new Intent(this, VisualizarActivity.class);
        //ABRINDO TELA: startActivity
        startActivity(intencao);
    }

    /*ABRIR TELA DE VISUALIZAR GASTOS*/
    public void abrirVisualizarGastos(View view) {
        //CRIANDO OBJETO DE INTENÇÃO
        Intent intencao = new Intent(this, VisualizarGastosActivity.class);
        //ABRINDO TELA: startActivity
        startActivity(intencao);
    }
}
