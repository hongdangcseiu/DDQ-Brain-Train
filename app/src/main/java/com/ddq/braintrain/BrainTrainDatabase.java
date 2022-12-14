package com.ddq.braintrain;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ddq.braintrain.models.AccountModel;

public class BrainTrainDatabase extends SQLiteOpenHelper {
    public  static final String TABLE_NAME = "account";
    public  static final String COL = "userName" ;
    public  static final String COL_1 = "password";
    public  static final String COL_2 = "gender";
    public  static final String COL_3 = "dob";
    public  static final String COL_4= "personal_id";

    public BrainTrainDatabase(@Nullable Context context) {
        super(context, "ddqbraintrain.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
  /*      db.execSQL("CREATE TABLE IF NOT EXISTS \"account\" (\n" +
                "\t\"userName\"\tTEXT Primary Key,\n" +
                "\t\"password\"\tTEXT,\n" +
                "\t\"gender\"\tTEXT,\n" +
                "\t\"dob\"\tTEXT,\n" +
                "\t\"personal_id\"\tTEXT\n" +
                ")");*/
        db.execSQL("CREATE TABLE if not exists \"attention_game_one\" (\n" +
                "\t\"image_id\"\tINTEGER,\n" +
                "\t\"image_name\"\tTEXT,\n" +
                "\t\"image\"\tTEXT,\n" +
                "\t\"complete_status\"\tINTEGER,\n" +
                "\t\"x_coordinate\"\tINTEGER,\n" +
                "\t\"y_coordinate\"\tINTEGER\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"attention_game_three\" (\n" +
                "\t\"level\"\tINTEGER,\n" +
                "\t\"number_of_shark\"\tINTEGER,\n" +
                "\t\"number_of_boat\"\tINTEGER,\n" +
                "\t\"point_per_boat\"\tINTEGER,\n" +
                "\t\"level_pass_score\"\tINTEGER,\n" +
                "\t\"allowable_number_of_bite\"\tINTEGER,\n" +
                "\t\"score\"\tINTEGER,\n" +
                "\t\"complete_status\"\tINTEGER\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"attention_game_two\" (\n" +
                "\t\"level\"\tINTEGER,\n" +
                "\t\"pair\"\tINTEGER,\n" +
                "\t\"time\"\tINTEGER,\n" +
                "\t\"score\"\tINTEGER,\n" +
                "\t\"complete_status_easy\"\tINTEGER,\n" +
                "\t\"complete_status_medium\"\tINTEGER,\n" +
                "\t\"complete_status_hard\"\tINTEGER\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"language_game_twoAndThree\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"word\"\tTEXT,\n" +
                "\t\"complete_status\"\tINTEGER\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"language_game_four\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"word\"\tTEXT,\n" +
                "\t\"complete_status\"\tINTEGER\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"math_game_one\" (\n" +
                "\t\"level\"\tINTEGER,\n" +
                "\t\"score\"\tINTEGER,\n" +
                "\t\"expression1\"\ttext,\n" +
                "\t\"expression2\"\ttext,\n" +
                "\t\"expressionresult1\"\tINTEGER,\n" +
                "\t\"expressionresult2\"\tINTEGER,\n" +
                "\t\"complete_status\"\tINTEGER\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"math_game_two_multiple_of_hundred\" (\n" +
                "\t\"level\"\tINTEGER,\n" +
                "\t\"option\"\tINTEGER,\n" +
                "\t\"time\"\tINTEGER,\n" +
                "\t\"point\"\tINTEGER,\n" +
                "\t\"complete_status\"\tINTEGER\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"math_game_two_multiple_of_ten\" (\n" +
                "\t\"level\"\tINTEGER,\n" +
                "\t\"option\"\tINTEGER,\n" +
                "\t\"time\"\tINTEGER,\n" +
                "\t\"point\"\tINTEGER,\n" +
                "\t\"complete_status\"\tINTEGER\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"math_game_two_multiple_of_thousand\" (\n" +
                "\t\"level\"\tINTEGER,\n" +
                "\t\"option\"\tINTEGER,\n" +
                "\t\"time\"\tINTEGER,\n" +
                "\t\"point\"\tINTEGER,\n" +
                "\t\"complete_status\"\tINTEGER\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"memory_game_library_animal\" (\n" +
                "\t\"animal_id\"\tINTEGER,\n" +
                "\t\"animal_name\"\tTEXT,\n" +
                "\t\"animal_image\"\tTEXT\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"memory_game_library_fruit\" (\n" +
                "\t\"fruit_id\"\tINTEGER,\n" +
                "\t\"fruit_name\"\tTEXT,\n" +
                "\t\"fruit_image\"\tTEXT\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"memory_game_library_household_item\" (\n" +
                "\t\"household_item_id\"\tINTEGER,\n" +
                "\t\"household_item_name\"\tTEXT,\n" +
                "\t\"household_item_image\"\tTEXT\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"memory_game_library_logo\" (\n" +
                "\t\"logo_id\"\tINTEGER,\n" +
                "\t\"logo_name\"\tTEXT,\n" +
                "\t\"logo_image\"\tTEXT\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"memory_game_library_shape\" (\n" +
                "\t\"shape_id\"\tINTEGER,\n" +
                "\t\"shape_name\"\tTEXT,\n" +
                "\t\"shape_image\"\tTEXT\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"memory_game_library_transportation\" (\n" +
                "\t\"transportation_id\"\tINTEGER,\n" +
                "\t\"transportation_name\"\tTEXT,\n" +
                "\t\"transportation_image\"\tTEXT\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"memory_game_one\" (\n" +
                "\t\"level\"\tINTEGER,\n" +
                "\t\"tiles\"\tINTEGER,\n" +
                "\t\"gridx\"\tINTEGER,\n" +
                "\t\"gridy\"\tINTEGER,\n" +
                "\t\"score\"\tINTEGER,\n" +
                "\t\"complete_status\"\tINTEGER\n" +
                ")");
        db.execSQL("CREATE TABLE if not exists \"memory_game_three\" (\n" +
                "\t\"level\"\tINTEGER,\n" +
                "\t\"number_of_card\"\tINTEGER,\n" +
                "\t\"hide_card\"\tINTEGER,\n" +
                "\t\"score\"\tINTEGER,\n" +
                "\t\"time\"\tINTEGER,\n" +
                "\t\"complete_status_easy\"\tINTEGER,\n" +
                "\t\"complete_status_medium\"\tINTEGER,\n" +
                "\t\"complete_status_hard\"\tINTEGER\n" +
                ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"memory_game_two\" (\n" +
                "\t\"level\"\tINTEGER,\n" +
                "\t\"number_of_item\"\tINTEGER,\n" +
                "\t\"score\"\tINTEGER,\n" +
                "\t\"complete_status\"\tINTEGER\n" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS \"progress\" (\n" +
                "\t\"game_id\"\tINTEGER,\n" +
                "\t\"game_name\"\tTEXT,\n" +
                "\t\"max_score\"\tINTEGER,\n" +
                "\t\"user_score\"\tINTEGER,\n" +
                "\t\"complete_status\"\tINTEGER\n" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists account");
        db.execSQL("drop table if exists attention_game_one");
        db.execSQL("drop table if exists attention_game_three");
        db.execSQL("drop table if exists attention_game_two");
        db.execSQL("drop table if exists language_game_four");
        db.execSQL("drop table if exists language_game_twoAndThree");
        db.execSQL("drop table if exists math_game_one");
        db.execSQL("drop table if exists math_game_two_multiple_of_hundred");
        db.execSQL("drop table if exists math_game_two_multiple_of_ten");
        db.execSQL("drop table if exists math_game_two_multiple_of_thousand");
        db.execSQL("drop table if exists memory_game_library_animal");
        db.execSQL("drop table if exists memory_game_library_fruit");
        db.execSQL("drop table if exists memory_game_library_household_item");
        db.execSQL("drop table if exists memory_game_library_logo");
        db.execSQL("drop table if exists memory_game_library_shape");
        db.execSQL("drop table if exists memory_game_library_transportation");
        db.execSQL("drop table if exists memory_game_one");
        db.execSQL("drop table if exists memory_game_three");
        db.execSQL("drop table if exists memory_game_two");
        db.execSQL("drop table if exists progress");
        onCreate(db);

    }

    public void updateUserScore(int gameID, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE progress SET user_score=" + score + " WHERE game_id = " + gameID);

    }

    public void updateCompletedStatus(String table, int level) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + table + " SET complete_status=1 WHERE level = " + level);

    }


    public void updateCompletedStatus(String table, String row, int level) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + table + " SET " + row + " = 1 WHERE level = " + level);

    }

    public void updateCell(String table, String cell, int value, int level) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + table + " SET " + cell + " = " + value + " WHERE level = " + level);
    }

    public Boolean insertAccount(String userName, String password, String gender, String dob, String personal_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL, userName);
        contentValues.put(COL_1,  password);
        contentValues.put(COL_2, gender);
        contentValues.put(COL_3, dob);
        contentValues.put(COL_4, personal_id);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkUserName (String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from account where userName=?", new String[] {userName});
        if (cursor.getCount()>0){
            return true;
        }
        return false;
    }

    public boolean checkUserNamePassword (String userName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from account where userName=? and password=?", new String[] {userName, password});
        if (cursor.getCount()>0){
            return true;
        }
        return false;
    }
}
