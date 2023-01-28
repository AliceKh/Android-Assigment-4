package com.example.class3demo2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.class3demo2.model.Student;
import com.squareup.picasso.Picasso;

import java.util.List;


class StudentViewHolder extends RecyclerView.ViewHolder{
    TextView nameTv;
    TextView idTv;
    CheckBox cb;
    List<Student> data;
    ImageView avatarImage;
    public StudentViewHolder(@NonNull View itemView, StudentRecyclerAdapter.OnItemClickListener listener, List<Student> data) {
        super(itemView);
        this.data = data;
        nameTv = itemView.findViewById(R.id.studentlistrow_name_tv);
        idTv = itemView.findViewById(R.id.studentlistrow_id_tv);
        avatarImage = itemView.findViewById(R.id.studentlistrow_avatar_img);
        cb = itemView.findViewById(R.id.studentlistrow_cb);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int)cb.getTag();
                Student st = data.get(pos);
                st.cb = cb.isChecked();
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = getAdapterPosition();
                listener.onItemClick(pos);
            }
        });
    }

    public void bind(Student st, int pos) {
        nameTv.setText(st.name);
        idTv.setText(st.id);
        cb.setChecked(st.cb);
        cb.setTag(pos);
        if (st.getAvatarUrl()  != null && st.getAvatarUrl().length() > 5) {
            Picasso.get().load(st.getAvatarUrl()).placeholder(R.drawable.avatar).into(avatarImage);
        }else{
            avatarImage.setImageResource(R.drawable.avatar);
        }
    }
}

public class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder>{
    OnItemClickListener listener;
    public static interface OnItemClickListener{
        void onItemClick(int pos);
    }

    LayoutInflater inflater;
    List<Student> data;
    public void setData(List<Student> data){
        this.data = data;
        notifyDataSetChanged();
    }
    public StudentRecyclerAdapter(LayoutInflater inflater, List<Student> data){
        this.inflater = inflater;
        this.data = data;
    }

    void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.student_list_row,parent,false);
        return new StudentViewHolder(view,listener, data);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student st = data.get(position);
        holder.bind(st,position);
    }

    @Override
    public int getItemCount() {
        if (data == null) return 0;
        return data.size();
    }

}

