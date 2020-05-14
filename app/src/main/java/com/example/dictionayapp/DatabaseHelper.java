package com.example.dictionayapp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.system.ErrnoException;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

   private  String DB_PATH= null;
   private static String DB_NAME = "eng_dictionary.db";
   private SQLiteDatabase myDataBase;
   private final Context myContext;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        this.DB_PATH="/data/data/"+ context.getPackageName() + "/" + "databases/";
        Log.e("Path 1",DB_PATH);


    }



    public void createDataBase() throws IOException {
        boolean dbExist= checkDatabase();
        if(!dbExist){
            this.getReadableDatabase();
            try {
                copyDataBase();
            }catch (IOException e){
                throw  new Error("Error copying Database");
            }
        }
    }

    public boolean checkDatabase(){
        SQLiteDatabase checkDB = null;
        try {
            String mypath= DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(mypath,null,SQLiteDatabase.OPEN_READONLY);


        }catch (SQLException e){

        }

        if(checkDB!=null){
            checkDB.close();
        }
return  checkDB!=null ? true:false;
    }
    private void copyDataBase() throws IOException{

        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName= DB_PATH + DB_PATH;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length=myInput.read(buffer))>0){
          myOutput.write(buffer,0,length);
        }
         myOutput.flush();
        myOutput.close();
        myInput.close();

        Log.i("copydatabase","Database copied");

    }
 public  void openDataBase() throws SQLException{
        String myPath   = DB_PATH + DB_NAME;
        myDataBase= SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if(myDataBase!= null){
            myDataBase.close();
        }
        super.close();
     }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       try{
           this.getReadableDatabase();
           myContext.deleteDatabase(DB_NAME);
           copyDataBase();
       }catch (IOException e){
           e.printStackTrace();

       }
    }
}

