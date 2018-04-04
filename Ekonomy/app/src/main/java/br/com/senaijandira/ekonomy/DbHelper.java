package br.com.senaijandira.ekonomy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    //nome
    private static String DB_NAME = "economias.db";
    //versão
    private static int DB_VERSION = 1;
    //construtor
    public DbHelper(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    /*CRIAÇÃO DO BANCO ONDE SERÁ IMPLEMENTADA AS TABELAS OU QUALQUER ESTRUTURA INICIAL*/

    @Override
    public void onCreate(SQLiteDatabase db) {

        //TABELA GANHOS
        String sql = "create table tbl_ganho(" +
                "_id INTEGER primary key autoincrement," +
                "categoria CHAR," +
                "valorGanho FLOAT," +
                "descricao TEXT," +
                "dt_nasc TEXT);";

        //executa no banco
        db.execSQL(sql);

        //TABELA GASTOS
        String sql2 = "create table tbl_gasto(" +
                "_id INTEGER primary key autoincrement," +
                "categoriaGasto CHAR," +
                "valorGasto FLOAT," +
                "descricaoGasto TEXT," +
                "data TEXT);";

        //executa no banco
        db.execSQL(sql2);

        //TABELA CATEGORIA
        String sql3;

        sql3 = "create table tbl_categoria(" +
                " _id INTEGER primary key autoincrement," +
                "nome TEXT);";
        db.execSQL(sql3);

        sql3 = "INSERT INTO tbl_categoria (nome) VALUES ('Lazer');" +
                "INSERT INTO tbl_categoria (nome) VALUES ('Tranporte');" +
                "INSERT INTO tbl_categoria (nome) VALUES ('Saúde');" +
                "INSERT INTO tbl_categoria (nome) VALUES ('Moradia');";
        db.execSQL(sql3);
    }

    /*METODO PARA UPGRADES(atualizações) NO BANCO*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion , int newVersion) {
        db.execSQL("drop table tbl_gasto");
        onCreate(db);
        db.execSQL("drop table tbl_ganho");
        onCreate(db);


    }
}
