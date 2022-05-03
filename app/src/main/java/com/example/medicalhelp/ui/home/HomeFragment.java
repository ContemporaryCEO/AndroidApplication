package com.example.medicalhelp.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.medicalhelp.database.AppDatabase;
import com.example.medicalhelp.database.Entry;
import com.example.medicalhelp.database.EntryDao;
import com.example.medicalhelp.databinding.FragmentHomeBinding;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TextView title = binding.textHome;
        TextView content = binding.textSubhome;

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
        Entry _foundendElement
                = all
                .stream()
                .filter
                        (_currentElement -> Objects.equals(_currentElement.title,
                                "Виды и степени ожогов")).findFirst().orElseThrow(() -> new RuntimeException("No such element"));


        requireActivity().runOnUiThread(() -> {
                title.setText(_foundendElement.title);
                content.setText(_foundendElement.content);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}