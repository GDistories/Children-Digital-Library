package edu.cn.bookadminister.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.cn.bookadminister.R;
import edu.cn.bookadminister.activity.AboutActivity;
import edu.cn.bookadminister.activity.FeedbackActivity;

import static android.content.Context.MODE_PRIVATE;


public class PersonFragment extends Fragment {

    public PersonFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences sp = getActivity().getSharedPreferences("userInfo", MODE_PRIVATE);
        String userName = sp.getString("username","");
        TextView textView = getActivity().findViewById(R.id.tv_username);
        textView.setText(userName);
    }

    @Override
    public void onStart() {
        super.onStart();
        ImageView im_profile = getView().findViewById(R.id.im_profile);
        TextView tv_username = getView().findViewById(R.id.tv_username);
        ImageView im_sex = getView().findViewById(R.id.im_sex);
        TextView tv_myData = getView().findViewById(R.id.tv_myData);
        TextView tv_profile = getView().findViewById(R.id.tv_profile);
        TextView tv_setting = getActivity().findViewById(R.id.txt_setting);
        TextView tv_feedback = getActivity().findViewById(R.id.txt_feedback);
        TextView tv_update = getActivity().findViewById(R.id.txt_update);
        TextView tv_about = getActivity().findViewById(R.id.txt_about);

        tv_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
            }
        });

        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.searching_update, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), R.string.no_update, Toast.LENGTH_SHORT).show();
            }
        });

        tv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutActivity.class));
            }
        });
    }
}
