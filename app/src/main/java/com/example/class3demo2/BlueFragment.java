package com.example.class3demo2;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.class3demo2.model.Model;
import com.example.class3demo2.model.Student;

public class BlueFragment extends Fragment {
    TextView titleTv;
    //    Student st;
    String title;
    String name;
    String ID;
    String birthday;
    String time;

    public static BlueFragment newInstance(String title){
        BlueFragment frag = new BlueFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE",title);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            this.title = bundle.getString("TITLE");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blue, container, false);

//        st = BlueFragmentArgs.fromBundle(getArguments()).getStudent();
        name = BlueFragmentArgs.fromBundle(getArguments()).getName();
        ID = BlueFragmentArgs.fromBundle(getArguments()).getId();

        birthday = BlueFragmentArgs.fromBundle(getArguments()).getBirthday();
        time = BlueFragmentArgs.fromBundle(getArguments()).getTime();


        TextView nameEt = view.findViewById(R.id.editNameEt);
        TextView idEt = view.findViewById(R.id.editIdEt);
        TextView birthdayEt = view.findViewById(R.id.editBirthdayEt);
        TextView timeEt = view.findViewById(R.id.editTimeEt);
        if (name != null){
            nameEt.setText(name);
        }
        if (ID != null){
            idEt.setText(ID);
        }
        if (birthday != null){
            birthdayEt.setText(birthday);
        }    if (time != null){
            timeEt.setText(time);
        }

        Button saveButton = view.findViewById(R.id.saveButton);
        Button button = view.findViewById(R.id.bluefrag_back_btn);
        button.setOnClickListener((view1)->{
////            Navigation.findNavController(view1).popBackStack();
            saveButton.setVisibility(View.VISIBLE);
            enableView(saveButton);
            enableView(nameEt);
//            enableView(idEt);
            enableView(birthdayEt);
            enableView(timeEt);
        });

        saveButton.setOnClickListener((view1)->{
            Model.instance().editStudent(new Student(idEt.getText().toString(), nameEt.getText().toString(), birthdayEt.getText().toString(), timeEt.getText().toString(), "", false),(unused)->{
                Navigation.findNavController(view1).popBackStack();
            });

        });
        return view;
    }

    public void enableView(View view) {
        view.setEnabled(!view.isEnabled());
    }


    public void setTitle(String title) {
        this.title = title;
        if (titleTv != null){
            titleTv.setText(title);
        }
    }
}