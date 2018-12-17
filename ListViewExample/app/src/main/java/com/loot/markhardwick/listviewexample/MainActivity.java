package com.loot.markhardwick.listviewexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.loot.markhardwick.listviewexample.backend.main.Fetcher;
import com.loot.markhardwick.listviewexample.backend.main.User;

import java.util.HashMap;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Fetcher fetcher = new Fetcher();
        fetcher.execute();

        final ListView listView = findViewById(R.id.listView);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        listView.setEmptyView(progressBar);
        final ListAdapter adapter = new ListAdapter(this, android.R.layout.simple_list_item_1,Fetcher.getUsers());
        listView.setAdapter(adapter);

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO something?
            }
        });
    }

    private class ListAdapter extends ArrayAdapter<User> {
        HashMap<User, Integer> mIdMap = new HashMap<>();

        ListAdapter(Context context, int textViewResourceId, User[] objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.length; ++i) {
                mIdMap.put(objects[i], i);
            }
        }

        @Override
        public long getItemId(int position) {
            User item = getItem(position);
            try {
                return mIdMap.get(item);
            } catch(NullPointerException e){
                return -1;
            }
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
