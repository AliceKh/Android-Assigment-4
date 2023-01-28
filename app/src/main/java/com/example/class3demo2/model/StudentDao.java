package com.example.class3demo2.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("select * from Student")
    LiveData<List<Student>> getAll();

    @Query("select * from Student where id = :studentId")
    Student getStudentById(String studentId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Student... students);

    @Delete
    void delete(Student student);
}

