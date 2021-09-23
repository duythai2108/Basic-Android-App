package com.example.lab3_sqllite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab3_sqllite.model.ClassRoom;

import java.util.ArrayList;
import java.util.List;

public class ClassRoomDAO  {
    DBManager dbManager;

    public ClassRoomDAO(Context context) {
        dbManager = new DBManager(context);

    }
    // Cai dat CRUD
    public void create(ClassRoom classRoom) {
        Log.i(DBManager.TAG,"DBManager create..." + classRoom.getId());
        SQLiteDatabase db = dbManager.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBManager.CLASSROOM_ID,classRoom.getId());
        values.put(DBManager.CLASSROOM_NAME,classRoom.getName());
        values.put(DBManager.CLASSROOM_TEACHER,classRoom.getTeacher());
        values.put(DBManager.CLASSROOM_AMOUNT,classRoom.getAmount());

        db.insert(DBManager.CLASSROOM_TABLE_NAME,null,values);
        db.close();
    }

    public List<ClassRoom> readAll() {
        List<ClassRoom> listClassRoom = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DBManager.CLASSROOM_TABLE_NAME;

        SQLiteDatabase db = dbManager.getWritableDatabase();
        Cursor cursor =  db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                ClassRoom classRoom = new ClassRoom();
                classRoom.setId(cursor.getString(0));
                classRoom.setName(cursor.getString(1));
                classRoom.setTeacher(cursor.getString(2));
                classRoom.setAmount(cursor.getInt(3));

                listClassRoom.add(classRoom);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listClassRoom;
    }
    public ClassRoom read(String id) {

        SQLiteDatabase db = dbManager.getReadableDatabase();
        Cursor cursor = db.query(DBManager.CLASSROOM_TABLE_NAME,new String[]{
                DBManager.CLASSROOM_ID,
                DBManager.CLASSROOM_NAME,DBManager.CLASSROOM_TEACHER,
                DBManager.CLASSROOM_AMOUNT},DBManager.CLASSROOM_ID + "=?",
                new String[]{id},null,null,null,null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        ClassRoom classRoom = new ClassRoom(cursor.getString(0),
                cursor.getString(1),cursor.getString(2),
                cursor.getInt(3));
        cursor.close();
        db.close();
        return  classRoom;




    }
    public int update(ClassRoom classRoom) {
        SQLiteDatabase db = dbManager.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBManager.CLASSROOM_NAME,classRoom.getName());
        values.put(DBManager.CLASSROOM_TEACHER,classRoom.getTeacher());
        values.put(DBManager.CLASSROOM_AMOUNT,classRoom.getAmount());

        return db.update(DBManager.CLASSROOM_TABLE_NAME,values,DBManager.CLASSROOM_ID+
                "=?",new String[]{String.valueOf(classRoom.getId())});
    }
    public void delete(String id) {
        SQLiteDatabase db = dbManager.getWritableDatabase();
        db.delete(DBManager.CLASSROOM_TABLE_NAME,DBManager.CLASSROOM_ID+"=?",new String[]{id});
    }
    public int count(){
        String countQuery = "SELECT * from " + DBManager.CLASSROOM_TABLE_NAME;
        SQLiteDatabase db = dbManager.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        int count = 0;
        try {
            if(cursor.moveToFirst()){
                count = cursor.getCount();
            }
            return  count;
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    public void seed() {
        int count = this.count();
        if(count == 0) {
            ClassRoom class1 = new ClassRoom("CLASS000","Lap trinh Android","MR NAM",120);
            ClassRoom class2 = new ClassRoom("CLASS001","Lap trinh Java","MR NAM",50);
            this.create(class1);
            this.create(class2);
        }
    }

}
