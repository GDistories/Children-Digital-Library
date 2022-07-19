package edu.cn.bookadminister.app;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.cn.bookadminister.R;
import edu.cn.bookadminister.activity.AddbooksActivity;
import edu.cn.bookadminister.activity.DeleteBooksActivity;
import edu.cn.bookadminister.activity.QueryBooksActivity;
import edu.cn.bookadminister.activity.UpdateBooksActivity;


public class HomeFragment extends Fragment implements View.OnClickListener{


    private Button bt_createe;
    private Button bt_updatee;
    private Button bt_deletee;
    private Button bt_read;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bt_createe = getActivity().findViewById(R.id.bt_createe);
        bt_createe.setOnClickListener(this);

        bt_updatee = getActivity().findViewById(R.id.bt_updatee);
        bt_updatee.setOnClickListener(this);

        bt_deletee = getActivity().findViewById(R.id.bt_deletee);
        bt_deletee.setOnClickListener(this);

        bt_read = getActivity().findViewById(R.id.bt_read);
        bt_read.setOnClickListener(this);

        SharedPreferences sp = getActivity().getSharedPreferences("userInfo", MODE_PRIVATE);
        String userName = "Welcome User " + sp.getString("username","") + "!\nPlease Select Your Function";
        TextView textView = getActivity().findViewById(R.id.tv_title);
        textView.setText(userName);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_createe){
            // 准备跳到下一个活动页面，studentList
            Intent intent = new Intent(getContext(), AddbooksActivity.class);
            startActivityForResult(intent, 0);
        }else if (v.getId() == R.id.bt_updatee){
            // 准备跳到下一个活动页面，studentList
            Intent intent = new Intent(getContext(), UpdateBooksActivity.class);
            startActivityForResult(intent, 1);
        }else if (v.getId() == R.id.bt_deletee){
            // 准备跳到下一个活动页面，studentList
            Intent intent = new Intent(getContext(), DeleteBooksActivity.class);
            startActivityForResult(intent, 2);
        }else if (v.getId() == R.id.bt_read){
            // 准备跳到下一个活动页面，studentList
            Intent intent = new Intent(getContext(), QueryBooksActivity.class);
            startActivityForResult(intent, 3);
        }
    }
}
