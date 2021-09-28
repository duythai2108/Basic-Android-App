package com.example.lab3_sqllite;

import androidx.annotation.CallSuper;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private Button btnExits;

    private  List<ClassRoom> classRoomList = new ArrayList<>();

    private ArrayAdapter<ClassRoom> listViewAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnExits = (Button) findViewById(R.id.btnExit);

        classRoomDAO.seed();

        List<ClassRoom> list = classRoomDAO.readAll();
        this.classRoomList.addAll(list);

        this.listViewAdapter = new ArrayAdapter<ClassRoom>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, this.classRoomList);

        this.listView.setAdapter(this.listViewAdapter);

        //Cai dat su kien click trne list view
        //Su kie nay ho tro chuc nang xem chi tiet va chinh sua mau tin duoc chon
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int positon, long id) {
                //Get the selected item set on listView
                ClassRoom selectedItem = (ClassRoom) parent.getItemAtPosition(positon);
                Intent intent = new Intent(MainActivity.this, UpdateClassRoomActivity.class);
                intent.putExtra("classRoom", selectedItem);
                //Start UpdateClassRoomAcivity co phan hoi
                MainActivity.this.startActivityForResult(intent, REQUEST_CODE);
            }
        });
        //Cai dat su kien Long Click event
        //Su kien nay ho tro chuc nang xoa lop hoc

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Get the selecteditem on ListView
                final ClassRoom selectedItem = (ClassRoom) parent.getItemAtPosition(position);
                //Hoi truoc khi xoa
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage(selectedItem.getName() + ".Are you sure to delete this ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",(dialogInterface, i) -> {
                            classRoomDAO.delete(selectedItem.getId());
                            classRoomList.remove(selectedItem);
                            listViewAdapter.notifyDataSetChanged();
                        })
                        .setNegativeButton("No",null)
                        .show();

                return true;
            }

        });




    }


    public void exit(View view) {
        finish();
        moveTaskToBack(true);
    }
    //phuong thuc xu ly khi ban vao button create
    public void create(View view){
        Intent intent = new Intent(this,CreateClassRoomActivity.class);
        //Start editNoteActivity co phan hoi
        this.startActivityForResult(intent,REQUEST_CODE);
    }
    //Khi createClassrommActivity hoac updateClassroomActibity hoan thanh,no gui phan hoi lai
    //Su phan hoi se xay ra neu chung ta da start activity tren bang cach su dung startActivityResult()
    //Khi phan hoi thi cac phuong thuc call back se duoc tra vao phuong thuc onActivityResult;
    @Override
    protected  void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            boolean needRefresh = data.getBooleanExtra("needRefresh", true);
            //Refresh list view
            if (needRefresh) {
                this.classRoomList.clear();
                ClassRoomDAO classRoomDAO = new ClassRoomDAO(this);
                List<ClassRoom> list = classRoomDAO.readAll();
                this.classRoomList.addAll(list);
                //thong bao du lieu thay doi (de refresh listview)
                this.listViewAdapter.notifyDataSetChanged();
            }
        }

    }





}