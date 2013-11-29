package com.example.laciudadentusm.cronogramaEventos;

import com.example.laciudadentusm.R;
import com.example.laciudadentusm.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CronogramaFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_cronogramas, container, false);
	}
}
