package com.example.dictionayapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;         //replace import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    static  DatabaseHelper myDbHelper;
    static boolean databaseOpened=false;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            // adding tool bar
        Toolbar  toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        searchView =  findViewById(R.id.search_view);

          // without adding onclick listener we will be able to click on the icon only not the whole searchview
        searchView.setOnClickListener(v -> {
              searchView.setIconified(false);    //iconify	boolean: a true value will collapse the SearchView to an icon, while a false will expand it.

        });

       myDbHelper =new DatabaseHelper(this);
if(myDbHelper.checkDatabase()){
    openDatabase();
}else {
    LoadDatabaseAsync task= new LoadDatabaseAsync(MainActivity.this);
    task.execute();
}

    }

    protected static void openDatabase() {
        try{
            myDbHelper.openDataBase();
            databaseOpened= true;

        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {             /* inflating the menu file  */
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {              /*    response after selecting the menu items
                                                                                            */
      int id = item.getItemId();

      if (id==R.id.action_settings){

          Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
          startActivity(intent);
          return true;
      }
      if(id== R.id.action_exit){
          System.exit(0);
          return true;
      }

        return super.onOptionsItemSelected(item);
    }
}
