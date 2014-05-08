package de.rwth.constants;

public class Constants {
	public static final String MYDATABASE_NAME = "TaskPlaner";
	public static final String MAIN_TASK_TABLE_NAME="Main_Tasks" ;
	public static final String SUB_TASK_TABLE_NAME="Sub_Tasks" ;

	public static  int MYDATABASE_VERSION = 1;
	public static  String KEY_ID = "_id";
	public static  String KEY_CONTENT = "Content";
	public static  String SCRIPT_CREATE_DATABASE =
			"create table " + MAIN_TASK_TABLE_NAME + " ("
			+ KEY_ID + " integer primary key autoincrement, "
			+ KEY_CONTENT + " text not null);";
}
