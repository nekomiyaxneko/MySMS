package io.github.nekomiyaxneko.mybms;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private String TAG="viewdebug";
    private List<Student> mStudents;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView studentName;
        TextView studentScore;
        TextView studentId;

        public ViewHolder(View view){
            super(view);
            studentName=(TextView) view.findViewById(R.id.student_name);
            studentScore=(TextView) view.findViewById(R.id.student_score);
            studentId=(TextView) view.findViewById(R.id.student_id);
        }
    }
    public  StudentAdapter(List<Student> studentList){
        mStudents=studentList;//构造函数，传一个list进来
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.student_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        Student student = mStudents.get(position);
        Log.d(TAG, "make: stu_id:"+student.getStudent_id()+" name:"+student.getName()+" score:"+student.getScore());
        holder.studentName.setText(student.getName());
        holder.studentScore.setText(student.getScore());
        holder.studentId.setText(student.getStudent_id());
        Log.d(TAG, "onBindViewHolder: view over");

    }
    @Override
    public int getItemCount(){
        return mStudents.size();
    }
}
