package br.com.senaijandira.ekonomy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class GastoDAO {

    ArrayList<Gasto> lstGasto = new ArrayList<>();

    private Integer idGasto = 0;

    Context context;

    /*Esquema de classe Singleton*/
    private static GastoDAO instance;

    public static GastoDAO getInstance() {
        //se não houver um contato, cria um novo
        if (instance == null) {
            instance = new GastoDAO();
        }
        //se houver um contato, retorna o mesmo
        return instance;
    }

    /*MÉTODO INSERIR*/
    public Boolean inserir(Context context, Gasto gt){

        //Acessar banco de dados
        SQLiteDatabase db = new DbHelper(context).getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("categoriaGasto", gt.getCategoriaGasto());
        valores.put("valorGasto", gt.getValorGasto());
        valores.put("descricaoGasto", gt.getDescricaoGasto());
        valores.put("data", gt.getData());

        long id = db.insert("tbl_gasto", null, valores);

        //verifica de deu erro ou  não na hora de inserir
        if(id != -1){
            //confirma inserção
            return true;
        }else{
            return false;
        }
    }

    /*SELECIONAR UM GASTO*/
    public Gasto selectionarUm(Context context, int id){

        SQLiteDatabase db = new DbHelper(context).getReadableDatabase();

        String sql = "select * from tbl_gasto where _id =" +id;

        Cursor cursor= db.rawQuery(sql, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            Gasto g = new Gasto();
            g.setId(cursor.getInt(0) /*acessando a coluna do ID*/);
            g.setCategoriaGasto(cursor.getString(1));
            g.setValorGasto(cursor.getString(2));
            g.setDescricaoGasto(cursor.getString(3));
            g.setData(cursor.getString(4));


            cursor.close();
            return g;

        }

        //retorna que não achou item
        return null;
    }

    /*MÉTODO ATUALIZAR*/
    public Boolean atualizar(Context context, Gasto g){

        SQLiteDatabase db = new DbHelper(context).getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("categoriaGasto", g.getCategoriaGasto());
        valores.put("valorGasto", g.getValorGasto());
        valores.put("descricaoGasto", g.getDescricaoGasto());
        valores.put("dataGasto", g.getData());

        db.update("tbl_gasto", valores, "_id=?", new String[]{g.getId().toString()});

        return true;
    }

    /*MÉTODO REMOVER*/
    public Boolean remover(Context context, Integer id){

        SQLiteDatabase db = new DbHelper(context).getWritableDatabase();
        db.delete("tbl_gasto", "_id=?", new String[]{id.toString()});

        return true;
    }

    //Função para mostrar valores das Categoria
    public Double selectionarPorCategoriaGasto(Context context, String categoriaGasto){

        SQLiteDatabase db = new DbHelper(context).getReadableDatabase();

        String sql = "select * from tbl_gasto where categoriaGasto = ? ;" ; //where categoria = ? ;

        Cursor cursor= db.rawQuery(sql,new String[]{categoriaGasto }); // new String[]{categoria }


        Double soma = 0.0;
        while (cursor.moveToNext()){


            soma += cursor.getDouble(2);

        }

        cursor.close();

        return soma;
    }

    /*SELECIONAR TODOS OS GASTOS - VISUALIZAR*/
    public ArrayList<Gasto> selecionarTodos(Context context){

        ArrayList<Gasto> retorno = new ArrayList<>();

        SQLiteDatabase db = new DbHelper(context).getReadableDatabase();
        String sql = "select * from tbl_gasto;";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Gasto g = new Gasto();
            g.setId(cursor.getInt(0));
            g.setCategoriaGasto(cursor.getString(1));
            g.setValorGasto(cursor.getString(2));
            g.setDescricaoGasto(cursor.getString(3));
            g.setData(cursor.getString(4));

            retorno.add(g);
        }
        return retorno;
    }
}
