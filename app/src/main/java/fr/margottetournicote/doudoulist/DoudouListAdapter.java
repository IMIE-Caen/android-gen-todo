package fr.margottetournicote.doudoulist;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 27/02/17.
 */

public class DoudouListAdapter extends ArrayAdapter {
    public DoudouListAdapter(Context context, int resource) {
        super(context, resource, new ArrayList<MaListItem>());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MaListItem item = (MaListItem) getItem(getCount() - position -1);

        CheckedTextView v = (CheckedTextView) super.getView(position, convertView, parent);
        // TODO: C'est comme ça que ça marche ?

        v.setText(item.nom);


        v.setChecked(item.checked);
        if (item.checked)
            v.setPaintFlags(v.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG );


        else{
            v.setPaintFlags(v.getPaintFlags()  & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }

        return v;

    }


    public void save(){


        String filename = "save";



        String str = "";
        for(int i = 0 ; i < this.getCount() ; i ++){
            str += ((MaListItem) getItem(i)).nom + "," + (((MaListItem) getItem(i)).checked ? "t" : "f") + "\n";
        }

        FileOutputStream outputStream;

        try {
            outputStream = getContext().openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(str.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void load(){
        try
        {
            String filename = "save";
            FileInputStream fileInputStream = getContext().openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));

            String line;
            while( (line = br.readLine()) != null){
                String[] split = line.split(",");
                add(new MaListItem(split[0], split[1].equals("t")));
            }


        }
        catch (Exception e)
        {

        }
    }

}
