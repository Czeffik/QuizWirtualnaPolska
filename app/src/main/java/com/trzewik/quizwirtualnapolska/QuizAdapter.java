package com.trzewik.quizwirtualnapolska;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.trzewik.quizwirtualnapolska.db.entity.Quiz;
import com.trzewik.quizwirtualnapolska.model.quizDetails.question.Image;

import java.util.List;


public class QuizAdapter extends ArrayAdapter<Quiz> implements View.OnClickListener{

    private List<Quiz> quizzes;
    Context mContext;

    public QuizAdapter(List<Quiz> quizzes, Context context) {
        super(context, R.layout.row_item, quizzes);
        this.quizzes = quizzes;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Quiz quiz=(Quiz)object;

        switch (v.getId())
        {
            //tutaj jest do wstawienia otwieranie nowego activity
            case R.id.title:
                Snackbar.make(v, "Release date " +quiz.getId(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
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
        Bitmap bMap = BitmapFactory.decodeByteArray(quiz.getImage(), 0, quiz.getImage().length);
        viewHolder.image.setImageBitmap(bMap);

        // Return the completed view to render on screen
        return convertView;
    }

    private static class ViewHolder {
        TextView txtTitle;
        TextView txtDescription;
        ImageView image;
    }
}
