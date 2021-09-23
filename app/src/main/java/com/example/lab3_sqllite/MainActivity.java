package com.example.lab3_sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.lab3_sqllite.dao.ClassRoomDAO;
import com.example.lab3_sqllite.model.ClassRoom;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final ClassRoomDAO classRoomDAO = new ClassRoomDAO(this);

    private static final int REQUEST_CODE = 1000;

    private ListView listView;
    private Button btnCreate;
    private Button btnDExits;

    private final List<ClassRoom> classRoomList = new ArrayList<>();

    private ArrayAdapter<ClassRoom> listViewAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnDExits = (Button) findViewById(R.id.btnExit);

        classRoomDAO.seed();

        List<ClassRoom> list = classRoomDAO.readAll();
        this.classRoomList.addAll(list);

        this.listViewAdapter = new ArrayAdapter<ClassRoom>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1,this.classRoomList);

        this.listView.setAdapter(this.listViewAdapter);
    }
}