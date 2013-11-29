package com.example.laciudadentusm.mapa;

import com.example.laciudadentusm.R;
import com.example.laciudadentusm.R.layout;
import com.example.laciudadentusm.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class Lugarmapa extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lugarmapa);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lugarmapa, menu);
		return true;
	}

}
