package com.example.administrator.note;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
private Intent i ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater( this );
        menuInflater.inflate( R.menu.add,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_note:
                i.putExtra("type","add");
                i.setClass(MainActivity.this,EditActivity.class);
                startActivity(i);
                break;
            case R.id.note_ch:
                i.putExtra("Id","_id")
                        .setClass(MainActivity.this,ShowActivity.class);
                startActivity(i);
                break;
            case R.id.del_note:

            break;
        }
        return super.onOptionsItemSelected( item );
    }

}
