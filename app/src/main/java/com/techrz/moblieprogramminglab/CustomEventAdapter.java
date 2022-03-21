package com.techrz.moblieprogramminglab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class CustomEventAdapter extends ArrayAdapter<Event> {

    private final Context context;
    private final ArrayList<Event> values;


    public CustomEventAdapter(@NonNull Context context, @NonNull ArrayList<Event> objects) {
        super(context, -1, objects);
        this.context = context;
        this.values = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.layout_event_row, parent, false);

        TextView eventName = rowView.findViewById(R.id.tvEventName);
        TextView eventDateTime = rowView.findViewById(R.id.tvEventDateTime);
        TextView eventPlaceName = rowView.findViewById(R.id.tvEventPlaceName);

        Event e = values.get(position);
        eventName.setText(e.name);
        eventDateTime.setText(e.datetime);
        eventPlaceName.setText(e.place);

        return rowView;
    }
}
