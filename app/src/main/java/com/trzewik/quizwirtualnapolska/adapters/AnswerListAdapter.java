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
import com.trzewik.quizwirtualnapolska.model.quizDetails.enums.AnswerType;

import java.util.List;


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
        AnswerType answerType = AnswerType.valueOf(answer.getAnswerType());
        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            switch (answerType) {
                case ANSWER_IMAGE:
                    convertView = inflater.inflate(R.layout.image_answer_item, parent, false);

                    viewHolder.image = convertView.findViewById(R.id.image);
                    viewHolder.text = null;
                    break;
                case ANSWER_TEXT_IMAGE:
                    convertView = inflater.inflate(R.layout.text_image_answer_item, parent, false);

                    viewHolder.text = convertView.findViewById(R.id.text);
                    viewHolder.image = convertView.findViewById(R.id.image);

                    convertView.setTag(viewHolder);
                    break;
                default:
                    convertView = inflater.inflate(R.layout.text_answer_item, parent, false);

                    viewHolder.text = convertView.findViewById(R.id.text);
                    viewHolder.image = null;

                    convertView.setTag(viewHolder);
                    break;
            }

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Bitmap bMap;
        switch (answerType) {
            case ANSWER_IMAGE:
                bMap = BitmapFactory.decodeFile(answer.getPathToImage());
                viewHolder.image.setImageBitmap(bMap);
                break;
            case ANSWER_TEXT_IMAGE:
                viewHolder.text.setText(answer.getText());
                bMap = BitmapFactory.decodeFile(answer.getPathToImage());
                viewHolder.image.setImageBitmap(bMap);
                break;
            default:
                viewHolder.text.setText(answer.getText());
                break;
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView text;
        ImageView image;
    }
}
