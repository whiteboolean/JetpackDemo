package com.example.jetpackdemo.vocabulary_demo08;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpackdemo.R;

import java.util.ArrayList;
import java.util.List;


public class MyListAdapter extends ListAdapter<Word,MyListAdapter.MyViewHolder> {


    boolean useCardView ;
    private WordViewModel wordViewModel;

    public MyListAdapter(boolean useCardView, WordViewModel wordViewModel) {
        //比较元素
        super(new DiffUtil.ItemCallback<Word>() {
            @Override
            public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
                return oldItem.getWord().equalsIgnoreCase(newItem.getWord())&&
                        oldItem.getChineseMeaning().equalsIgnoreCase(newItem.getChineseMeaning()) &&
                        oldItem.isShowChineseMeaning()== newItem.isShowChineseMeaning();
            }
        });
        this.useCardView = useCardView;
        this.wordViewModel = wordViewModel;
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
        Word word = getItem(position);
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
