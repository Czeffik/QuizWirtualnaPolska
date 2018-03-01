package com.trzewik.quizwirtualnapolska.adapters;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.trzewik.quizwirtualnapolska.R;
import com.trzewik.quizwirtualnapolska.db.entity.QuizAnswer;

import java.util.List;

public class QuestionAnswerAdapter extends RecyclerView.Adapter<QuestionAnswerAdapter.QuizAnswerViewHolder> {

    private final List<QuizAnswer> list;

    public QuestionAnswerAdapter(List<QuizAnswer> list) {
        this.list = list;
    }

    @Override
    public QuizAnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new QuizAnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuizAnswerViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class QuizAnswerViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView text;

        public QuizAnswerViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
        }

        public void bind(QuizAnswer quizAnswer) {

            text.setText(quizAnswer.getText());
            Bitmap bMap = BitmapFactory.decodeFile(quizAnswer.getImagePath());
            image.setImageBitmap(bMap);

        }
    }
}