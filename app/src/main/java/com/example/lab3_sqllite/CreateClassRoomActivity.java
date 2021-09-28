package com.example.lab3_sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab3_sqllite.dao.ClassRoomDAO;
import com.example.lab3_sqllite.model.ClassRoom;

public class CreateClassRoomActivity extends AppCompatActivity {
    private ClassRoomDAO classRoomDAO;
    private boolean needRefresh;
    private EditText editID;
    private EditText editName;
    private EditText editTeacher;
    private EditText editAmount;

    private Button btnAdd;
    private Button btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class_room);
        editID = findViewById(R.id.editID);
        editName = findViewById(R.id.editName);
        editTeacher = findViewById(R.id.editTeacher);
        editAmount = findViewById(R.id.editAmount);

        btnCancel = findViewById(R.id.btnCancel);
        btnAdd = findViewById(R.id.btnUpdate);

        classRoomDAO = new ClassRoomDAO(this);

    }
    //chuc nang cancel
    public void cancel(View view) {
        this.onBackPressed();
    }
    //Chuc nang Add student
    public void addClassRoom(View view) {
        String id = editID.getText().toString();
        String name = editName.getText().toString();
        String teacher = editTeacher.getText().toString();
        int amount = Integer.parseInt(editAmount.getText().toString());

        ClassRoom classRoom = new ClassRoom(id,name,teacher,amount);
        classRoomDAO.create(classRoom);
        this.needRefresh = true;

        // tro lai mainActivyti
        this.onBackPressed();
    }
    //Khi activity nay da hoan thanh
    //co the can gui phan hoi ve activity da goi no
    public void finish(){
        //chuan bi du lieu intetn
        Intent data = new Intent();
        //yeu cau list view reset lai data hoac khong
        data.putExtra("needRefresh",needRefresh);

        //Activity da hoan thanh OK,tra ve du lieu
        this.setResult(Activity.RESULT_OK,data);
        super.finish();
    }

}