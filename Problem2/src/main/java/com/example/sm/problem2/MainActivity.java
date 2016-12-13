package com.example.sm.problem2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyBaseAdapter adapter;
    ListView listview;
    ArrayList<Employee> emp_list = new ArrayList<Employee>();
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MyBaseAdapter(this, emp_list);
        listview = (ListView) findViewById(R.id.listView1) ;
        listview.setAdapter(adapter);
        listview.setOnItemClickListener((AdapterView.OnItemClickListener)adapter);
    }
    @Override
    public void onClick(View v){
        EditText edit_name = (EditText) findViewById(R.id.edit_name);
        EditText edit_age = (EditText) findViewById(R.id.edit_age);
        EditText edit_salary = (EditText) findViewById(R.id.edit_salary);

        Employee employee;

        switch (v.getId()){
            case R.id.btn_inc:
                employee = new Employee("",0,0);
                a = emp_list.indexOf(employee);
                employee = emp_list.get(a);
                employee.increase();
                emp_list.set(a,employee);
                break;

            case R.id.btn_dec:
                employee = new Employee("",0,0);
                a = emp_list.indexOf(employee);
                employee = emp_list.get(a);
                employee.decrease();
                emp_list.set(a,employee);
                break;

            case R.id.btn_store:
                employee = new Employee("",0,0);
                emp_list.add(employee);
                break;

            case R.id.btn_modify:
                employee = new Employee("",0,0);
                a = emp_list.indexOf(employee);
                employee = emp_list.get(a);
                employee.setSalary(0);
                employee.setName("");
                employee.setAge(0);
                emp_list.set(a,employee);
                break;

            case R.id.btn_delete:
                employee = new Employee("",0,0);
                a = emp_list.indexOf(employee);
                emp_list.remove(a);
                break;
        }
    }
}

interface Payment {
    void increase();
    void decrease();
}
