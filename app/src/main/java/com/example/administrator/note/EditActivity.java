package com.example.administrator.note;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EditActivity extends AppCompatActivity implements View.OnClickListener{
    private Intent i ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_edit );

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case (R.id.btn_back):
                i.setClass( this,MainActivity.class );
                startActivity( i );
                finish();
                break;
            case (R.id.btn_ok):
                break;
        }
    }
}
