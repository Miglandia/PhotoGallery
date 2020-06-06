package ga.lle.ry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ArrayList<File> list;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.image_grid);
        list = imageReader(Environment.getExternalStorageDirectory());


        gridView.setAdapter(new gridAdapter());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(MainActivity.this, FullImageActivity.class);
                intent.putExtra("img", list.get(i).toString());
                startActivity(intent);
            }
        });
    }


    public class gridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            View convertView = null;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.row_layout, viewGroup, false);
                ImageView myImage = convertView.findViewById(R.id.my_image);
                myImage.setImageURI(Uri.parse(list.get(position).toString()));

            }
            return convertView;
        }
    }

    private ArrayList<File> imageReader(File externalStorageDirectory) {
        ArrayList<File> b = new ArrayList<>();
        File[] files = externalStorageDirectory.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                b.addAll(imageReader(files[i]));

            } else {
                if (files[i].getName().endsWith(".jpg")) {
                    b.add(files[i]);
                }
            }
        }



        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                b.addAll(imageReader(files[i]));
                files[i].listFiles();
            } else {
                if (files[i].getName().endsWith(".jpg") || files[i].getName().endsWith(".png")) {
                    b.add(files[i]);
                }
            }
        }

        return b;
    }
}
