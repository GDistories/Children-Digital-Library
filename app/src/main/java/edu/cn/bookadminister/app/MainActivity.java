package edu.cn.bookadminister.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import edu.cn.bookadminister.activity.AddbooksActivity;
import edu.cn.bookadminister.activity.DeleteBooksActivity;
import edu.cn.bookadminister.activity.QueryBooksActivity;
import edu.cn.bookadminister.R;
import edu.cn.bookadminister.activity.UpdateBooksActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txt_home;
    private TextView txt_book;
    private TextView txt_me;
    private FrameLayout ly_content;

    //Fragment Object
    private HomeFragment homeFragment;
    private BookListFragment bookListFragment;
    private PersonFragment personFragment;
    private FragmentManager fManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        init();
        bindViews();


        //getFragmentManager();不推荐使用

    }

    private void bindViews() {
        txt_home = findViewById(R.id.txt_home);
        txt_book =findViewById(R.id.txt_book);
        ly_content = findViewById(R.id.ly_content);
        txt_me = findViewById(R.id.txt_me);

        txt_home.setOnClickListener(this);
        txt_book.setOnClickListener(this);
        txt_me.setOnClickListener(this);

        fManager = getSupportFragmentManager();
        fragmentTransaction = fManager.beginTransaction();
        if(homeFragment==null){
            homeFragment = new HomeFragment();
            fragmentTransaction.add(R.id.ly_content,homeFragment);
        }
        fragmentTransaction.show(homeFragment);
        txt_home.setTextColor(getResources().getColor(R.color.colorAccent));
        fragmentTransaction.commit();
    }

    //设置文本的选中状态
    private void setSelected(){
        txt_home.setSelected(false);
        txt_book.setSelected(false);
        txt_me.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction transaction){
        if(homeFragment!=null) transaction.hide(homeFragment);
        if(bookListFragment!=null) transaction.hide(bookListFragment);
        if(personFragment!=null) transaction.hide(personFragment);
    }

    @Override
    public void onClick(View view) {

        fragmentTransaction = fManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (view.getId()){
            case R.id.txt_home:
                setSelected();
                txt_home.setSelected(true);
                if(homeFragment==null){
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.ly_content,homeFragment);
                }
                else{
                    fragmentTransaction.show(homeFragment);
                }
                txt_home.setTextColor(getResources().getColor(R.color.colorAccent));
                txt_book.setTextColor(getResources().getColor(R.color.grey));
                txt_me.setTextColor(getResources().getColor(R.color.grey));
                break;
            case  R.id.txt_book:
                setSelected();
                txt_book.setSelected(true);
                if(bookListFragment==null){
                    bookListFragment = new BookListFragment();
                    fragmentTransaction.add(R.id.ly_content,bookListFragment);
                }
                else{
                    fragmentTransaction.show(bookListFragment);
                }
                txt_home.setTextColor(getResources().getColor(R.color.grey));
                txt_book.setTextColor(getResources().getColor(R.color.colorAccent));
                txt_me.setTextColor(getResources().getColor(R.color.grey));
                break;
            case  R.id.txt_me:
                setSelected();
                txt_me.setSelected(true);
                if(personFragment==null){
                    personFragment = new PersonFragment();
                    fragmentTransaction.add(R.id.ly_content,personFragment);
                }
                else{
                    fragmentTransaction.show(personFragment);
                }
                txt_home.setTextColor(getResources().getColor(R.color.grey));
                txt_book.setTextColor(getResources().getColor(R.color.grey));
                txt_me.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
        }
        fragmentTransaction.commit();

    }

}

