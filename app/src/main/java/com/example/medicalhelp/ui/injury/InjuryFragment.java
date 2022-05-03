package com.example.medicalhelp.ui.injury;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medicalhelp.R;
import com.example.medicalhelp.database.AppDatabase;
import com.example.medicalhelp.database.Entry;
import com.example.medicalhelp.database.EntryDao;
import com.example.medicalhelp.databinding.FragmentInjuryBinding;

import java.util.List;
import java.util.Objects;

public class InjuryFragment extends Fragment {

    private FragmentInjuryBinding binding;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentInjuryBinding.inflate(
                inflater,
                container,
                false
        );
        View root = binding.getRoot();

        TextView title = binding.textInjury;
        TextView content = binding.textSubinjury;

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
                                "Классификации и виды травм")).findFirst().orElseThrow(() -> new RuntimeException("No such element"));


        requireActivity().runOnUiThread(() -> {
            title.setText(_foundendElement.title);
            content.setText(_foundendElement.content);
        });

        return root;
    }
}