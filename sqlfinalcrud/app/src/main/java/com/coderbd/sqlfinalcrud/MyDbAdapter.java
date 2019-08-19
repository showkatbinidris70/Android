package com.coderbd.sqlfinalcrud;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MyDbAdapter {
 MyDbHelper helper;
 public MyDbAdapter(Context context){
     this.helper = new MyDbHelper(context);
 }


    static class MyDbHelper extends SQLiteOpenHelper{

        //Database information
        static final String DB_NAME= "PROD.DB";
        static final  int DB_VERSION= 1;


        public MyDbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }
        //// Start///
        //Table Name
        public static final String TABLE_NAME = "PRODUCT";

        // Table Columns
        public static final String ID = "id";
        public static final String PRODUCT_NAME = "product_name";
        public static final String QTY = "qty";
        //createing table query
        private static final String CREATE_TABLE = " create table " + TABLE_NAME + "(" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODUCT_NAME + " TEXT NOT NULL,"
                + QTY + " INTEGER NOT NULL)";


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);

        }
    }
    public  long insertData (Product product){
     SQLiteDatabase db = helper.getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(MyDbHelper.PRODUCT_NAME, product.getProductname());
        cv.put(MyDbHelper.QTY, product.getQuantity());
        long id = db.insert(MyDbHelper.TABLE_NAME, null, cv);
        return id;
    }
    public List <Product> getList(){
     SQLiteDatabase db = helper.getReadableDatabase();
     String [] projection = {MyDbHelper.ID, MyDbHelper.PRODUCT_NAME, MyDbHelper.QTY};
        Cursor cursor = db.query(
                MyDbHelper.TABLE_NAME, // The Table to query
                projection,  //The array of columns to return (pass null ot get all)
                null, // The colum for the where clause
                null, // The values for the where clause
                null,// don't group the rows
                null,// don't filter by row groups
                null// The sort order

        );
        List<Product> list = new ArrayList<>();
        while (cursor.moveToNext()){
            Product product = new Product(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    Integer.parseInt(cursor.getString(2)));
            list.add(product);
        }
        cursor.close();
        return list;
    }
    public void deleteProduct(int id){
     SQLiteDatabase db = helper.getWritableDatabase();
     db.execSQL("DELETE FROM " + MyDbHelper.TABLE_NAME + " WHERE " + MyDbHelper.ID + "=" + id + " ' ");
     db.close();
    }
    public Product findProductById(int id){
     SQLiteDatabase db = helper.getReadableDatabase();
     String [] projection = {MyDbHelper.ID,
     MyDbHelper.PRODUCT_NAME, MyDbHelper.QTY
     };
     String selection = MyDbHelper.ID + " = " + id;
     Cursor cursor = db.query(
             MyDbHelper.TABLE_NAME, // The Table to query
             projection, // The array of columns to return (pass null to get all)
             selection, // The columns for the Where clause
             null, // don't group teh rows
             null,
             null,
             null
     );
     Product product = new Product();
        if ((cursor.moveToFirst())) {
            cursor.moveToFirst();
            product.setId(Integer.parseInt(cursor.getString(0)));
            product.setProductname(cursor.getString(1));
            product.setQuantity(Integer.parseInt(cursor.getString(2)));
            cursor.close();
        }else {
            product = null;
        }
        return product;
        }



}
