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
import com.trzewik.quizwirtualnapolska.db.entity.QuizAnswer;

import java.util.List;

/**
 * Created by trzewik on 01.03.2018.
 */

public class QuestionAnswerListAdapter extends ArrayAdapter<QuizAnswer> {

    Context mContext;
    private List<QuizAnswer> answers;

    public QuestionAnswerListAdapter(List<QuizAnswer> answers, Context context) {
        super(context, R.layout.quiz_item, answers);
        this.answers = answers;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QuizAnswer answer = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            if (answer.getImagePath().length()>0){
                convertView = inflater.inflate(R.layout.image_answer_item, parent, false);

                viewHolder.image = convertView.findViewById(R.id.image);
                viewHolder.text = null;

                convertView.setTag(viewHolder);
            }
            else{
                convertView = inflater.inflate(R.layout.text_answer_item, parent, false);

                viewHolder.text = convertView.findViewById(R.id.text);
                viewHolder.image = null;

                convertView.setTag(viewHolder);
            }

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (viewHolder.text!=null) {
            viewHolder.text.setText(answer.getText());
        }
        if (viewHolder.image!=null){
            Bitmap bMap = BitmapFactory.decodeFile(answer.getImagePath());
            viewHolder.image.setImageBitmap(bMap);
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView text;
        ImageView image;
    }
}
