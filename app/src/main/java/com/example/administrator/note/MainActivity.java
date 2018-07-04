package com.example.administrator.note;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    private Intent i;
    private ListView listData;
    private DbAdapter dbAdapter;
    private SimpleCursorAdapter dataAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_data:
                i=new Intent();
                i.putExtra("type", "add");
                i.setClass(MainActivity.this, EditActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listData = findViewById(R.id.note_list);
        dbAdapter = new DbAdapter(this);
        listData.setVisibility(View.VISIBLE);
        displaylistView();

    }
    private void displaylistView () {
        Cursor cursor = dbAdapter.listContacts();
        String[] columns = new String[]{
                dbAdapter.KEY_DATE,
                dbAdapter.KEY_NOTE
        };
        int[] to = new int[]{
                R.id.data_view,
                R.id.note_date,
        };
        dataAdapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor, columns, to, 0);
        listData.setAdapter(dataAdapter);
        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = (Cursor) listData.getItemAtPosition(position);
                int item_id = c.getInt(c.getColumnIndexOrThrow("_id"));
                i = new Intent();
                i.putExtra("type", "show")
                        .putExtra("ID", item_id)
                        .setClass(MainActivity.this, ShowActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
}