package com.example.class3demo2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.class3demo2.model.Model;
import com.example.class3demo2.model.Student;

import java.util.LinkedList;
import java.util.List;

public class StudentsListFragmentViewModel extends ViewModel {
    private LiveData<List<Student>> data = Model.instance().getAllStudents();

    LiveData<List<Student>> getData(){
        return data;
    }
}
