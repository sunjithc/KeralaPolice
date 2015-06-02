package com.example.keralapolice;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChiefFragment extends Fragment {
View view;
	@Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle args) {
        view = inflater.inflate(R.layout.activity_chief, container, false);

	return view;
	
	}
}