package com.example.laciudadentusm;


import com.example.laciudadentusm.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MenuPrincipalFragment extends Fragment  {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_menu_principal, container, false);
	}
	

}

