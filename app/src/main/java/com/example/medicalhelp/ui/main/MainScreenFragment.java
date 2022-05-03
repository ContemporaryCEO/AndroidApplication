package com.example.medicalhelp.ui.main;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.example.medicalhelp.R;
import com.example.medicalhelp.database.AppDatabase;
import com.example.medicalhelp.database.Entry;
import com.example.medicalhelp.database.EntryDao;
import com.example.medicalhelp.databinding.FragmentMainScreenBinding;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MainScreenFragment extends Fragment {

    FragmentMainScreenBinding binding;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            binding = FragmentMainScreenBinding.inflate(
                    inflater,
                    container,
                    false
            );

        View root = binding.getRoot();

        EntryDao dao = Room
                .databaseBuilder(
                        requireActivity()
                                .getApplicationContext(),
                        AppDatabase.class,
                        "default"
                )
                .allowMainThreadQueries()
                .build()
                .entryDao();

        List<Entry> all = dao.getAll();
        List<String> titles = all.stream().map(
                _current -> _current.title
        ).collect(Collectors.toList());

        SearchView search = binding.mainSearchTool;
        ListView searchContent = binding.searchContent;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1,
                titles
        );

        searchContent.setAdapter(adapter);

        search.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        adapter.getFilter().filter(s);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                         adapter.getFilter().filter(s);
                        return false;
                    }
                }
        );

        return root;
    }
}