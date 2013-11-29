package com.example.laciudadentusm;

// Librerias para el GPS
import com.example.laciudadentusm.cronogramaEventos.CronogramaFragment;
import com.example.laciudadentusm.mapa.MapaCiudadFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
//import android.app.Fragment;
import android.support.v4.app.Fragment; 
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MenuPrincipalActivity extends ActionBarActivity {

	private ListView mDrawerList;
	private DrawerLayout mDrawer;
	private CustomActionBarDrawerToggle mDrawerToggle;
	private String[] menuItems, menuItems2;

	Activity actividad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_drawer);

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		actividad=this;

		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		_initMenu();
		mDrawerToggle = new CustomActionBarDrawerToggle(this, mDrawer);
		mDrawer.setDrawerListener(mDrawerToggle);
		
		
		//--------------- Muestra el fragment por defecto -------------///
		
		Fragment fragment = new MenuPrincipalFragment();
		
		FragmentManager fragmentManager = 
				getSupportFragmentManager();
		
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment)
				.commit();
		
	}

	private void _initMenu() {
		
		NsMenuAdapter mAdapter = new NsMenuAdapter(this);

		// Agrega un secccion 
		mAdapter.addHeader(R.string.menu_main_header1);

		// Agrega un bloque para esa seccion
		menuItems = getResources().getStringArray(
				R.array.menu_mi_perfil);
		String[] menuItemsIcon = getResources().getStringArray(
				R.array.menu_mi_perfil_icon);

		int res = 0;
		for (String item : menuItems) {

			int id_title = getResources().getIdentifier(item, "string", this.getPackageName());
			int id_icon = getResources().getIdentifier(menuItemsIcon[res],
					"drawable", this.getPackageName());

			NsMenuItemModel mItem = new NsMenuItemModel(id_title, id_icon);
			if (res==1) mItem.counter=12; //it is just an example...
			if (res==3) mItem.counter=3; //it is just an example...
			mAdapter.addItem(mItem);
			res++;
		}

		
		mAdapter.addHeader(R.string.menu_main_header2);
	
		menuItems2 = getResources().getStringArray(
				R.array.menu_la_ciudad);
		String[] menuItemsIcon2 = getResources().getStringArray(
				R.array.menu_la_ciudad_icon);
		
		int res2 = 0;
		for (String item : menuItems2) {

			int id_title = getResources().getIdentifier(item, "string", this.getPackageName());
			int id_icon = getResources().getIdentifier(menuItemsIcon2[res2],
					"drawable", this.getPackageName());

			NsMenuItemModel mItem = new NsMenuItemModel(id_title, id_icon);
			if (res2==1) mItem.counter=12; //it is just an example...
			if (res2==3) mItem.counter=3; //it is just an example...
			mAdapter.addItem(mItem);
			res2++;
		}
	
		mAdapter.addHeader(R.string.menu_main_header3);

		mDrawerList = (ListView) findViewById(R.id.drawer);
		if (mDrawerList != null)
			mDrawerList.setAdapter(mAdapter);
		 
		//mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {



				Fragment fragment = null;

				switch (position) {
					case 1:
						
						// Verificamos el google play services esta disponible
						int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

						// Showing status
						if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

							int requestCode = 10;
							Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, actividad, requestCode);
							dialog.show();
						}else { // Google Play Services are available        	
							Toast.makeText(getApplicationContext(), "ta entrando", Toast.LENGTH_LONG).show();
							fragment = new MapaCiudadFragment();
						}
						
						break;
					case 2:
						fragment = new Fragment2();
						break;
					case 3:
						fragment = new CronogramaFragment();
						break;
				}
				
				FragmentManager fragmentManager = 
						getSupportFragmentManager();
				
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment)
						.commit();

				mDrawerList.setItemChecked(position, true);

			//	tituloSeccion = opcionesMenu[position];
			//	getSupportActionBar().setTitle(tituloSeccion);

				mDrawer.closeDrawer(mDrawerList);	
			}
		});	
	}


	public void buscarEventosEntretenimiento(View v){
				
	
		Fragment fragment = new MapaCiudadFragment();
		
		FragmentManager fragmentManager = 
				getSupportFragmentManager();
		
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment)
				.commit();
		
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawer.isDrawerOpen(mDrawerList);
      //  menu.findItem(R.id.action_save).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		/*
		 * The action bar home/up should open or close the drawer.
		 * ActionBarDrawerToggle will take care of this.
		 */
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		// Handle your other action bar items...
		return super.onOptionsItemSelected(item);
	}

	private class CustomActionBarDrawerToggle extends ActionBarDrawerToggle {

		public CustomActionBarDrawerToggle(Activity mActivity,DrawerLayout mDrawerLayout){
			super(
			    mActivity,
			    mDrawerLayout, 
			    R.drawable.ic_drawer,
			    R.string.ns_menu_open, 
			    R.string.ns_menu_close);
		}

		@Override
		public void onDrawerClosed(View view) {
			getSupportActionBar().setTitle(getString(R.string.ns_menu_close));
			supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
		}

		@Override
		public void onDrawerOpened(View drawerView) {
			getSupportActionBar().setTitle(getString(R.string.ns_menu_open));
			supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
		}
	}	
}
