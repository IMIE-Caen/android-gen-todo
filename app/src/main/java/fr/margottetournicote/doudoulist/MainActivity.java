package fr.margottetournicote.doudoulist;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    DoudouListAdapter aa ;
    EditText itemField ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        itemField =  (EditText) findViewById(R.id.addItemField);
        Button addButton =  (Button) findViewById(R.id.addButton);

        aa = new DoudouListAdapter(this, R.layout.doudou_list_item);
        aa.load();
        ListView doudouList =  (ListView) findViewById(R.id.doudouList);

        doudouList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MaListItem) aa.getItem(aa.getCount() - position -1)).checked = !((MaListItem) aa.getItem(aa.getCount() - position -1)).checked ;
                aa.notifyDataSetChanged();
            }


        });

        doudouList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                aa.remove(aa.getItem(aa.getCount() - position -1));
                return true;
            }
        });


        itemField.setOnEditorActionListener( new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                add(  itemField.getText().toString()  );
                return true;
            }
        });




        addButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                add(  itemField.getText().toString()  );
            }
        });



        doudouList.setAdapter(aa);
    }

    protected void add(String s){
        aa.add(new MaListItem(  s));
        itemField.setText("");
    }

    @Override
    protected void onStop() {
        aa.save();
        super.onStop();
    }
}
