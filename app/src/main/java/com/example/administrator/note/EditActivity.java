package com.example.administrator.note;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener{
    private Intent i ;
    Button btn_ok, btn_back;
    EditText editdate , editnote;
    TextView text_name ;
    public String new_data, new_note;
    public Bundle bData;
    public DbAdapter dbAdapter;
    public int index;
    private int mYear, mMonth, mDay;
    private Spinner notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_edit );
        initView();
        btn_back.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.plants_array, android.R.layout.simple_spinner_item);
        notify.setAdapter(adapter);
        notify.setOnItemSelectedListener(this);
        bData = this.getIntent().getExtras();
        dbAdapter = new DbAdapter(this);
        if(bData.getString("type").equals("change")){
            text_name.setText("編輯便利貼");
            Cursor c = dbAdapter.queryById(bData.getInt("ID"));
            index = c.getInt(0);
            editdate.setText(c.getString(1));
            editnote.setText(c.getString(2));
        }

    }
    public void initView(){
        text_name = findViewById(R.id.text_name);
        editdate = findViewById(R.id.editdate);
        editnote = findViewById(R.id.editnote);
        btn_back = findViewById(R.id.btn_back);
        btn_ok = findViewById(R.id.btn_ok);
        notify = findViewById(R.id.notify_spinner);
    }
    public void onClick(View v) {
        i = new Intent();
        final Calendar c;

        switch(v.getId()){
            case R.id.editdate:
                c =Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String format = setDateFormat(year, month, dayOfMonth);
                        editdate.setText(format);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
                break;

            case (R.id.btn_back):
                if (bData.getString( "type" ).equals( "add" )) {
                    Intent i = new Intent( this, MainActivity.class );
                    startActivity( i );
                } else {
                    Intent i = new Intent( this, ShowActivity.class );
                    i.putExtra( "ID", index );
                    startActivity( i );
                }
                break;

            case (R.id.btn_ok):
                new_data = editdate.getText().toString();
                new_note = editnote.getText().toString();
                dbAdapter = new DbAdapter(EditActivity.this);
                if(bData.getString("type").equals("add")){
                    try{
                        dbAdapter.createContacts(new_data,new_note);
                    }catch(Exception e){
                        e.printStackTrace();
                    }finally{
                        i.setClass(this,MainActivity.class);
                        startActivity(i);
                    }
                }
                else{
                    try{
                        dbAdapter.updateContacts(index,new_data,new_note);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally{
                        i.putExtra("ID",index);
                        i.setClass(this,MainActivity.class);
                        startActivity(i);
                    }
                }
                break;
        }
    }
    private String setDateFormat(int year , int month , int day){
        return String.valueOf(year) + "-"
                + String.valueOf(month+1) + "-"
                +String.valueOf(day);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "您選擇"+parent.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
