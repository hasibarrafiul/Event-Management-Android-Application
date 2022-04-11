package com.techrz.moblieprogramminglab;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ListView lvEvents;
    private ArrayList<Event> events;
    private CustomEventAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        System.out.println("@mainActivity OnCreate");

        setContentView(R.layout.activity_main);

        Button btnCreateNew = findViewById(R.id.createNew);
        btnCreateNew.setOnClickListener(v->createNew());

        Button history = findViewById(R.id.history);
        history.setOnClickListener(v->history());

        Button load = findViewById(R.id.loadData);
        load.setOnClickListener(v->loadAttendenceList());

        findViewById(R.id.exit).setOnClickListener(v->finish());
        lvEvents = findViewById(R.id.lvEvents);
        loadData();

    }

    private void loadData(){
        events = new ArrayList<>();
        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs");
        if (rows.getCount() == 0) {
            return;
        }
        //events = new Event[rows.getCount()];
        while (rows.moveToNext()) {
            String key = rows.getString(0);
            String eventData = rows.getString(1);
            String[] fieldValues = eventData.split("-::-");

            String name = fieldValues[0];
            String place = fieldValues[1];
            String dateTime = fieldValues[3];
            Event e = new Event(key, name, place, dateTime, "", "", "", "", "", "");
            events.add(e);
        }
        db.close();
        adapter = new CustomEventAdapter(this, events);
        lvEvents.setAdapter(adapter);

        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                // String item = (String) parent.getItemAtPosition(position);
                System.out.println(position);
                Intent i = new Intent(MainActivity.this, EventInformation.class);
                i.putExtra("EventKey", events.get(position).key);
                startActivity(i);
            }
        });
        lvEvents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //String message = "Do you want to delete event - "+events[position].name +" ?";
                String message = "Do you want to delete event - "+events.get(position).name +" ?";
                showDialog(message, "Delete Event", events.get(position).key);
                return true;

            }
        });
    }
    public void showDialog(String message, String title, String key){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);

        builder.setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                Util.getInstance().deleteByKey(MainActivity.this, key);
                dialog.cancel();
                loadData();
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    void createNew(){
        Intent i = new Intent(this, EventInformation.class);
        startActivity(i);
    }
    void history(){

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("@mainActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("@mainActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("@mainActivity onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("@mainActivity onRestart");
        loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("@mainActivity onStop");
        events.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("@mainActivity onDestroy");
    }

    void loadAttendenceList(){
        Intent intent = new Intent(this, MyAttendanceActivity.class);
        startActivity(intent);
    }
}