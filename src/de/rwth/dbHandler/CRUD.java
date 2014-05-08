package de.rwth.dbHandler;
import java.util.ArrayList;


import  de.rwth.constants.Constants;
import de.rwth.taskplaner.subTasks;
import de.rwth.taskplaner.subTasks;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteClosable;

public class CRUD extends SQLiteOpenHelper {

private static String main_taskID;
private static String main_taskName;
private static String main_endDate;
private static String main_endTime;

private static String Sub_taskID;
private static String Sub_taskName;
private static String Sub_endDate;
private static String Sub_endTime;
private static String SubTaskstatus;
private String CREATE_TABLE_SRING;
private static Boolean isCompleted;

//create table MY_DATABASE (ID integer primary key, Content text not null);




public CRUD(Context c){
super(c,Constants.MYDATABASE_NAME,null,Constants.MYDATABASE_VERSION);
}

@Override
public void onCreate(SQLiteDatabase db) {
	// TODO Auto-generated method stub
	insertMainTask(db);
	insertSubtask(db);
}
public void insertMainTask(SQLiteDatabase db){
	CREATE_TABLE_SRING = mainTaskQueryString(main_taskID,
				main_taskName,
				main_endDate,
				main_endTime);
	db.execSQL(CREATE_TABLE_SRING);
} 
public void insertSubtask(SQLiteDatabase db){
	
		CREATE_TABLE_SRING = subTaskQueryString(Sub_taskID,main_taskID,
										Sub_taskName,
										Sub_endDate,
										Sub_endTime,SubTaskstatus);	
		db.execSQL(CREATE_TABLE_SRING);
}
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// TODO Auto-generated method stub
	
}

public  String mainTaskQueryString(String tid,String tName,String eDate,String eTime){
	String query = "CREATE TABLE " + Constants.MAIN_TASK_TABLE_NAME + "("
            + tid + " INTEGER PRIMARY KEY," + tName + " TEXT,"
            + eDate + " TEXT," + eTime +" TEXT)";
    return query;
}

public  String subTaskQueryString(String st_id,String t_id,String tName,String eDate,String eTime,String isCompleted){
	String query = "CREATE TABLE " + Constants.SUB_TASK_TABLE_NAME + "("
            + st_id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + t_id + " INTEGER FOREIGHN KEY," + tName + " TEXT,"
            + eDate + " TEXT," + eTime +" TEXT," + isCompleted + " BOOLEAN)";
    return query;
}

public ArrayList<subTasks> getAllsubTasks(String t_id) {
    ArrayList<subTasks> subtaskslists = new ArrayList<subTasks>();
    // Select All Query
    String selectQuery = "SELECT  * FROM " + Constants.SUB_TASK_TABLE_NAME + "Where t_id =" + Integer.parseInt(t_id) ;
 
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null); 
 
    // looping through all rows and adding to list
    if (cursor.moveToFirst()) {
        do {
        	subTasks task = new subTasks();
        	task.setID(Integer.parseInt(cursor.getString(0)));
        	task.setName(cursor.getString(1));
        	task.setenddate(cursor.getString(2));
        	task.setendTime(cursor.getString(3));
            // Adding contact to list
        	subtaskslists.add(task);
        } while (cursor.moveToNext());
    }
 
    // return contact list
    return subtaskslists;
}

}