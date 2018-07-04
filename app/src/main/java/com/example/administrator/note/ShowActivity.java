package com.example.administrator.note;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.os.Build.ID;

public class ShowActivity extends AppCompatActivity {
    public Bundle bData;
    private DbAdapter dbAdapter;
    private int index,id;
    private TextView date_view , note_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_show );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        initView();
        bData = this.getIntent().getExtras();
        id = bData.getInt("ID");
        dbAdapter = new DbAdapter(this);
        Cursor c = dbAdapter.queryById(id);
        index = c.getInt(0);
        date_view.setText(c.getString(1));
        note_view.setText(c.getString(2));
        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(  );
                i.putExtra( "type" , "change" )
                        .putExtra("ID",id);
                i.setClass( ShowActivity.this , EditActivity.class );
                startActivity( i );

            }
        } );
    }
    private void initView(){
        date_view = findViewById(R.id.date_view);
        note_view = findViewById(R.id.note_view);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.del,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.del_data:
                Boolean isDeleted = dbAdapter.deleteContacts(index);
                if(isDeleted)
                    Toast.makeText(ShowActivity.this,"已刪除!", Toast.LENGTH_LONG).show();
                Intent i = new Intent();
                i.setClass( this, MainActivity.class );
                startActivity( i );
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
