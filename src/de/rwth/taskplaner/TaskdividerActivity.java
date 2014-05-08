package de.rwth.taskplaner;

import java.io.Serializable;
import java.util.ArrayList;


import de.rwth.dbHandler.CRUD;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TaskdividerActivity extends Activity implements OnCheckedChangeListener,Parcelable{
	
	
	protected static String courseName;
	protected static String assignment_name="Assignment1";
	protected static String project_name;
	private TextView tv;
	private EditText et;
	private static Button btn;
	private static TableLayout tl;
	private static RadioGroup allOptions;
	private static int no_tasks=0;
	private CRUD mySQL;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_taskdivider);
		tv =(TextView)findViewById(R.id.textView2);
		tv.setText(assignment_name);
		allOptions= (RadioGroup)findViewById(R.id.radioGroup1);
		allOptions.setOnCheckedChangeListener(this);	

	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		btn = (Button)findViewById(R.id.btn);
		tl = (TableLayout)findViewById(R.id.tableLayout1);
		switch(group.getCheckedRadioButtonId())
		{
		case R.id.radio0:
			btn.setVisibility(-1);
			tl.removeAllViews();
		case R.id.radio1:
			
			btn.setVisibility(1);
		}
	}
	public void btn_click(View v){	
		try{			
			
			TableRow row = new TableRow(this);
			et=new EditText(this);
			et.setLayoutParams(new TableRow.LayoutParams( android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 4 ) );
			et.setText("Enter Name of Subtask");
			et.setId(no_tasks);
			no_tasks+=1;
			tl.addView(row);
			row.addView(et);
			btn_save();
			}catch(Exception e){
			Log.e("Activity_failed",e.getMessage());
			}		
	}
	
public void btn_save(){
	
	ArrayList<String>subTasks = new ArrayList<String>();
	   
	for(int i=0;i<tl.getChildCount();i++){
		 TableRow row = (TableRow)tl.getChildAt(i);
		    if(row.getChildCount()>0){
		 	et = (EditText)row.getChildAt(0); // get child index on particular row
		    String task = et.getText().toString();
		    subTasks.add(task);
		    //Toast.makeText(this,subTasks.get(i-1),Toast.LENGTH_SHORT).show();
		    }
	}
	Intent i = new Intent(this,CRUD.class);
	i.putExtra("mainActivity", btn.getText());
}
@Override
public int describeContents() {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public void writeToParcel(Parcel dest, int flags) {
	// TODO Auto-generated method stub
	
}

}
