package com.trzewik.quizwirtualnapolska.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.trzewik.quizwirtualnapolska.R;
import com.trzewik.quizwirtualnapolska.db.entity.Quiz;

import java.util.List;


public class QuizAdapter extends ArrayAdapter<Quiz> {

    Context mContext;
    private List<Quiz> quizzes;

    public QuizAdapter(List<Quiz> quizzes, Context context) {
        super(context, R.layout.row_item, quizzes);
        this.quizzes = quizzes;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Quiz quiz = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.row_item, parent, false);

            viewHolder.txtTitle = convertView.findViewById(R.id.title);
            viewHolder.txtDescription = convertView.findViewById(R.id.description);
            viewHolder.image = convertView.findViewById(R.id.image);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtTitle.setText(quiz.getTitle());
        viewHolder.txtDescription.setText("description");

        Bitmap bMap = BitmapFactory.decodeFile(quiz.getImagePath());
        viewHolder.image.setImageBitmap(bMap);

        return convertView;
    }

    private static class ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        ImageView image;
    }
}
