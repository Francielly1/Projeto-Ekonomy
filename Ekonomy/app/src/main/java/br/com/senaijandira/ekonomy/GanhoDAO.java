package br.com.senaijandira.ekonomy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GanhoDAO {

    ArrayList<Ganho> lstGanho = new ArrayList<>();

    private Integer idGanho = 0;

    Context context;

    /*Esquema de classe Singleton*/
    private static GanhoDAO instance;

    public static GanhoDAO getInstance() {
        //se não houver um contato, cria um novo
        if (instance == null) {
            instance = new GanhoDAO();
        }
        //se houver um contato, retorna o mesmo
        return instance;
    }

    /*MÉTODO INSERIR*/
    public Boolean inserir(Context context, Ganho g){

        //Acessar banco de dados
        SQLiteDatabase db = new DbHelper(context).getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("categoria", g.getCategoria());
        valores.put("valorGanho", g.getValorGanho());
        valores.put("descricao", g.getDescricao());
        valores.put("dt_nasc", g.getDtnasc());

        long id = db.insert("tbl_ganho", null, valores);

        //verifica de deu erro ou  não na hora de inserir
        if(id != -1){
            //confirma inserção
            return true;
        }else{
            return false;
        }
    }

    /*SELECIONAR UM GANHO*/
    public Ganho selectionarUm(Context context, int id){

        SQLiteDatabase db = new DbHelper(context).getReadableDatabase();

        String sql = "select * from tbl_ganho where _id =" +id;

        Cursor cursor= db.rawQuery(sql, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            Ganho g = new Ganho();
            g.setId(cursor.getInt(0) /*acessando a coluna do ID*/);
            g.setCategoria(cursor.getString(1));
            g.setValorGanho(cursor.getString(2));
            g.setDescricao(cursor.getString(3));
            g.setDtnasc(cursor.getString(4));


            cursor.close();
            return g;

        }

        //retorna que não achou item
        return null;
    }

    /*SELECIONAR TODOS OS GANHOS - VISUALIZAR*/
    public ArrayList<Ganho> selecionarTodos(Context context){

        ArrayList<Ganho> retorno = new ArrayList<>();

        SQLiteDatabase db = new DbHelper(context).getReadableDatabase();
        String sql = "select * from tbl_ganho;";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Ganho g = new Ganho();
            g.setId(cursor.getInt(0));
            g.setCategoria(cursor.getString(1));
            g.setValorGanho(cursor.getString(2));
            g.setDescricao(cursor.getString(3));
            g.setDtnasc(cursor.getString(4));

            retorno.add(g);
        }
        return retorno;
    }

    /*MÉTODO ATUALIZAR*/
    public Boolean atualizar(Context context, Ganho g){

        SQLiteDatabase db = new DbHelper(context).getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("categoria", g.getCategoria());
        valores.put("valorGanho", g.getValorGanho());
        valores.put("descricao", g.getDescricao());
        valores.put("data", g.getDtnasc());

        db.update("tbl_ganho", valores, "_id=?", new String[]{g.getId().toString()});

        return true;
    }

    /*MÉTODO REMOVER*/
    public Boolean remover(Context context, Integer id){

        SQLiteDatabase db = new DbHelper(context).getWritableDatabase();
        db.delete("tbl_ganho", "_id=?", new String[]{id.toString()});

        return true;
    }

    //Função para mostrar valores das Categoria
    public Double selectionarPorCategoria(Context context, String categoria){

        SQLiteDatabase db = new DbHelper(context).getReadableDatabase();

        String sql = "select * from tbl_ganho where categoria = ? ;" ; //where categoria = ? ;

        Cursor cursor= db.rawQuery(sql,new String[]{categoria }); // new String[]{categoria }


        Double soma = 0.0;
        while (cursor.moveToNext()){


             soma += cursor.getDouble(2);

        }

        cursor.close();

        return soma;
    }
}
