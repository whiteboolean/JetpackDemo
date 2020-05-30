package com.example.jetpackdemo.day08_vocabulary_demo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpackdemo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsFragment extends Fragment {

    Button button1, button3;
    TextView textWords;

    private WordViewModel wordViewModel;
    private RecyclerView recyclerView;
    private MyListAdapter myAdapter1, myAdapter2;
    private Switch switch1;
    private FloatingActionButton floatingActionButton2;
    private LiveData<List<Word>> filterData;
    private SharedPreferences sharedPreferences;
    private static final String VIEW_TYPE = "view_type";
    private List<Word> allWords;
    private DividerItemDecoration dividerItemDecoration;
    private ItemTouchHelper itemTouchHelper;

    public WordsFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_data:
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setMessage("是否确认删除全部？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        wordViewModel.deleteAllWords();
                    }
                });
                builder.create().show();
                break;

            case R.id.switchViewType:
                sharedPreferences = requireActivity().getSharedPreferences(VIEW_TYPE, Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                if (recyclerView.getAdapter() == myAdapter1) {
                    recyclerView.setAdapter(myAdapter2);
                    recyclerView.addItemDecoration(dividerItemDecoration);
                    edit.putBoolean("isAdapter1", false);
                } else {
                    recyclerView.setAdapter(myAdapter1);
                    recyclerView.removeItemDecoration(dividerItemDecoration);
                    edit.putBoolean("isAdapter1", true);
                }
                edit.apply();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setMaxWidth(700);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData.removeObservers(getViewLifecycleOwner());
                filterData = wordViewModel.getPatternLiveData(newText.trim());
                filterData.observe(getViewLifecycleOwner(), new Observer<List<Word>>() {
                    @Override
                    public void onChanged(List<Word> words) {
                        int temp = myAdapter1.getItemCount();
//                        myAdapter1.setAllWords(words);
                        allWords = words;
//                        myAdapter2.setAllWords(words);
                        if (temp != words.size()) {
                            recyclerView.smoothScrollBy(0, -200);
                            myAdapter1.submitList(words);
                            myAdapter2.submitList(words);
//                            myAdapter1.notifyItemInserted(0);
//                            myAdapter2.notifyItemInserted(0);
                        }
                    }
                });
                return true;//不需要后续处理
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_words, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        floatingActionButton2 = view.findViewById(R.id.floatingActionButton2);
        recyclerView = view.findViewById(R.id.recyclerView);
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        sharedPreferences = requireActivity().getSharedPreferences(VIEW_TYPE, Context.MODE_PRIVATE);
        boolean isAdapter1 = sharedPreferences.getBoolean("isAdapter1", false);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        myAdapter2 = new MyListAdapter(true, wordViewModel);
        myAdapter1 = new MyListAdapter(false, wordViewModel);
        if (isAdapter1) {
            recyclerView.setAdapter(myAdapter1);
        } else {
            recyclerView.setAdapter(myAdapter2);
        }
        dividerItemDecoration = new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.START | ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {


                Word wordFrom = allWords.get(viewHolder.getAdapterPosition());
                Word wordTo = allWords.get(target.getAdapterPosition());
                int idTemp = wordFrom.getId();
                wordFrom.setId(wordTo.getId());
                wordTo.setId(idTemp);
                wordViewModel.updateWords(wordFrom, wordTo);
                myAdapter1.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                myAdapter2.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Word word = allWords.get(viewHolder.getAdapterPosition());
                wordViewModel.deleteWords(word);
                Snackbar.make(requireActivity().findViewById(R.id.wordRoot), "删除了一个词汇", 3000).setAction("撤销", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wordViewModel.insertWords(word);
                    }
                }).show();

            }

            Drawable icon = ContextCompat.getDrawable(requireActivity(),R.drawable.ic_delete_black_24dp);
            Drawable background= new ColorDrawable(Color.LTGRAY);

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                View itemView = viewHolder.itemView ;
                int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) /2 ;
                int iconLeft,iconRight ,iconTop ,iconBottom;
                int backTop,backBottom,backLeft,backRight;
                backTop = itemView.getTop();
                backBottom = itemView.getBottom() ;
                iconTop = itemView.getTop()+(itemView.getHeight() - icon.getIntrinsicHeight())/2;
                iconBottom = iconTop + icon.getIntrinsicHeight() ;
                if(dX>=0){
                    backLeft = itemView.getLeft();
                    backRight = itemView.getRight();
                    background.setBounds(backLeft,backTop,backRight,backBottom);
                    iconLeft = itemView.getLeft()+iconMargin ;
                    iconRight = iconLeft+icon.getIntrinsicHeight() ;
                    icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);
                }else if (dX<=0){
                    backRight = itemView.getRight() ;
                    backLeft = itemView.getRight()+(int)dX ;
                    background.setBounds(backLeft,backTop,backRight,backBottom);
                    iconRight = itemView.getRight()-icon.getIntrinsicHeight() ;
                    iconLeft = iconRight - icon.getIntrinsicHeight() ;
                    icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);
                }else{
                    background.setBounds(0,0,0,0);
                    icon.setBounds(0,0,0,0);

                }
                background.draw(c);
                icon.draw(c);

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);




        floatingActionButton2.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_wordsFragment_to_addFragment2));
        filterData = wordViewModel.getListLiveData();
        filterData.observe(getViewLifecycleOwner(), new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                int temp = myAdapter1.getItemCount();
//                myAdapter1.setAllWords(words);
//                myAdapter2.setAllWords(words);
                allWords = words;
                if (temp != words.size()) {
                    myAdapter1.submitList(words);
                    myAdapter2.submitList(words);
//                    myAdapter1.notifyItemInserted(0);
//                    myAdapter2.notifyItemInserted(0);
                }
            }
        });

    }
}
