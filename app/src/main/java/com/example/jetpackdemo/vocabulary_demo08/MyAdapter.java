package com.example.jetpackdemo.vocabulary_demo08;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpackdemo.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Word> allWords = new ArrayList<>();
    boolean useCardView = false;
    private WordViewModel wordViewModel;

    public MyAdapter(boolean useCardView, WordViewModel wordViewModel) {
        this.useCardView = useCardView;
        this.wordViewModel = wordViewModel;
    }

    public void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view;
        if (useCardView) {
            view = inflater.inflate(R.layout.cell_card, parent, false);
        } else {
            view = inflater.inflate(R.layout.cell_normal, parent, false);
        }


        MyViewHolder holder = new MyViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=" +holder.english.getText());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Word word = (Word) holder.itemView.getTag(R.id.word_for_view_holder);
            word.setShowChineseMeaning(!isChecked);
            wordViewModel.updateWords(word);
            holder.aSwitch.setChecked(isChecked);
            holder.chinese.setVisibility(isChecked ? View.GONE : View.VISIBLE);
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Word word = allWords.get(position);
        holder.itemView.setTag(R.id.word_for_view_holder, word);
        holder.number.setText(String.valueOf(position));
        holder.english.setText(word.getWord());
        holder.chinese.setText(word.getChineseMeaning());

        if (word.isShowChineseMeaning()) {
            holder.aSwitch.setChecked(false);
            holder.chinese.setVisibility(View.VISIBLE);
        } else {
            holder.aSwitch.setChecked(true);
            holder.chinese.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return allWords == null ? 0 : allWords.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView number;
        private TextView chinese;
        private TextView english;
        private Switch aSwitch;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.textViewNumber);
            chinese = itemView.findViewById(R.id.textViewChinese);
            english = itemView.findViewById(R.id.textViewEnglish);
            aSwitch = itemView.findViewById(R.id.switch2);
        }
    }
}
