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
import com.trzewik.quizwirtualnapolska.db.entity.QuestionAnswer;

import java.util.List;

import static com.trzewik.quizwirtualnapolska.model.quizDetails.enums.AnswerType.ANSWER_IMAGE;
import static com.trzewik.quizwirtualnapolska.model.quizDetails.enums.AnswerType.ANSWER_TEXT;
import static com.trzewik.quizwirtualnapolska.model.quizDetails.enums.AnswerType.ANSWER_TEXT_IMAGE;


public class AnswerListAdapter extends ArrayAdapter<QuestionAnswer> {

    Context mContext;
    private List<QuestionAnswer> answers;

    public AnswerListAdapter(List<QuestionAnswer> answers, Context context) {
        super(context, R.layout.quiz_item, answers);
        this.answers = answers;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QuestionAnswer answer = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            if (answer.getAnswerType().equals(ANSWER_IMAGE.toString())) {
                convertView = inflater.inflate(R.layout.image_answer_item, parent, false);

                viewHolder.image = convertView.findViewById(R.id.image);
                viewHolder.text = null;

                convertView.setTag(viewHolder);
            } else if (answer.getAnswerType().equals(ANSWER_TEXT.toString())) {
                convertView = inflater.inflate(R.layout.text_answer_item, parent, false);

                viewHolder.text = convertView.findViewById(R.id.text);
                viewHolder.image = null;

                convertView.setTag(viewHolder);
            } else if (answer.getAnswerType().equals(ANSWER_TEXT_IMAGE.toString())) {
                convertView = inflater.inflate(R.layout.text_image_answer_item, parent, false);

                viewHolder.text = convertView.findViewById(R.id.text);
                viewHolder.image = convertView.findViewById(R.id.image);

                convertView.setTag(viewHolder);
            } else {
                convertView = inflater.inflate(R.layout.text_answer_item, parent, false);

                viewHolder.text = convertView.findViewById(R.id.text);
                viewHolder.image = null;

                convertView.setTag(viewHolder);
            }

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (answer.getAnswerType().equals(ANSWER_TEXT.toString())) {
            viewHolder.text.setText(answer.getText());
        } else if (answer.getAnswerType().equals(ANSWER_IMAGE.toString())) {
            Bitmap bMap = BitmapFactory.decodeFile(answer.getPathToImage());
            viewHolder.image.setImageBitmap(bMap);
        } else if (answer.getAnswerType().equals(ANSWER_TEXT_IMAGE.toString())) {
            viewHolder.text.setText(answer.getText());
            Bitmap bMap = BitmapFactory.decodeFile(answer.getPathToImage());
            viewHolder.image.setImageBitmap(bMap);
        } else {
            viewHolder.text.setText(answer.getText());
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView text;
        ImageView image;
    }
}
