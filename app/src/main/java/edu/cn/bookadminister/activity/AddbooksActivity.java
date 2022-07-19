package edu.cn.bookadminister.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.cn.bookadminister.R;
import edu.cn.bookadminister.bean.Book;
import edu.cn.bookadminister.database.BooksDAO;

public class AddbooksActivity extends AppCompatActivity implements View.OnClickListener {
    //组件定义
    private EditText etStudentid;
    private EditText etStudentname;
    private EditText etMajoy;
    private EditText etBooknum;
    SharedPreferences sp ;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbooks);
        //初始化界面
        initView();
    }

    //初始化界面
    private void initView() {
        sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        etStudentid=(EditText)findViewById(R.id.et_studentid);
        etStudentname = (EditText) findViewById(R.id.et_studentname);
        etMajoy = (EditText) findViewById(R.id.et_majoy);
        etBooknum = (EditText) findViewById(R.id.et_booknum);
        etStudentname.setText(sp.getString("username",""));
        etStudentname.setEnabled(false);
        btnAdd = (Button) findViewById(R.id.btn_add);
        //设置按钮的点击事件
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //当单击“添加”按钮时，获取添加信息
        String studentid=etStudentid.getText().toString().trim();
        String studentname = etStudentname.getText().toString().trim();
        String majoy = etMajoy.getText().toString().trim();
        String booknum = etBooknum.getText().toString();


        //检验信息是否正确
        if (TextUtils.isEmpty(studentid)) {
            Toast.makeText(this, "Please input item id", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(studentname)) {
            Toast.makeText(this, "Please input student username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(majoy)) {
            Toast.makeText(this, "Please input item type", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(booknum)) {
            Toast.makeText(this, "Please input barcode", Toast.LENGTH_SHORT).show();
            return;
        }

        //添加借书信息
        Book o =new Book();
        o.studentid= studentid;
        o.studentname = studentname;
        o.majoy = majoy;
        o.booknum= booknum;
        o.userName = sp.getString("username","");

        //创建数据库访问对象
        BooksDAO dao = new BooksDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //执行数据库访问方法
        long result = dao.addBooks(o);

        if (result > 0) {
            Toast.makeText(this, "Borrow success!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Borrow failure!", Toast.LENGTH_SHORT).show();
        }
        //关闭数据库
        dao.close();
        //关闭活动
        finish();

    }
}