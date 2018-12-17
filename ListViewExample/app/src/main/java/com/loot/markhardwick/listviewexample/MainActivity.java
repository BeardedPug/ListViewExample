package com.loot.markhardwick.listviewexample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loot.markhardwick.listviewexample.backend.main.Fetcher;
import com.loot.markhardwick.listviewexample.backend.main.User;

public class MainActivity extends Activity {
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ListView listView = findViewById(R.id.listView);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        listView.setEmptyView(progressBar);

        adapter = new ListAdapter(this, Fetcher.getUsers());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               //TODO
            }
        });

        Fetcher fetcher = new Fetcher(new Fetcher.FetcherResponse() {
            @Override
            public void processFinish(User[] newUserList) {
                adapter.updateUsers(newUserList);
            }
        });
        fetcher.execute();
    }

    private class ListAdapter extends BaseAdapter {
        Context context;
        User[] users;
        private LayoutInflater inflater;

        ListAdapter(Context context, User[] users) {
            this.context = context;
            this.users = users;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void updateUsers(User[] newUsers){
            users = newUsers;
            this.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return users.length;
        }

        @Override
        public Object getItem(int position) {
            return users[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("DefaultLocale")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View toReturn = convertView;
            if(toReturn == null){
                toReturn = inflater.inflate(R.layout.row, null);
            }
            User user = users[position];
            TextView idField = toReturn.findViewById(R.id.idNumber);
            idField.setText(String.format("%s%d", getString(R.string.id), user.getId()));
            TextView nameField = toReturn.findViewById(R.id.name);
            nameField.setText(String.format("%s%s", getString(R.string.name), user.getName()));
            TextView usernameField = toReturn.findViewById(R.id.username);
            usernameField.setText(String.format("%s%s", getString(R.string.username), user.getUsername()));
            TextView emailField = toReturn.findViewById(R.id.email);
            emailField.setText(String.format("%s%s", getString(R.string.email), user.getEmail()));
            TextView addressField = toReturn.findViewById(R.id.address);
            addressField.setText(String.format("%s%s", getString(R.string.address), user.getAddress().toString()));
            TextView phoneField = toReturn.findViewById(R.id.phone);
            phoneField.setText(String.format("%s%s", getString(R.string.phone), user.getPhone()));
            TextView websiteField = toReturn.findViewById(R.id.website);
            websiteField.setText(String.format("%s%s", getString(R.string.website), user.getWebsite()));
            TextView companyField = toReturn.findViewById(R.id.company);
            companyField.setText(String.format("%s%s", getString(R.string.company), user.getCompany().toString()));
            return toReturn;
        }
    }
}


