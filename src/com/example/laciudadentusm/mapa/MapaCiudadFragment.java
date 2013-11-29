package com.example.laciudadentusm.mapa;

import com.example.laciudadentusm.MenuPrincipalActivity;
import com.example.laciudadentusm.*;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


//base datos


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.database.Cursor;

public class MapaCiudadFragment extends Fragment implements GoogleMap.OnMyLocationChangeListener{

	private LinearLayout linealL;
	private ImageView imagVi;
	private Button B;
	private View myContentsView;
	private static View view;
	private Context context;

	private static final int MAP_ZOOM = 15;
	private static final String SAVED_LOCATION = "saved_location";
	private GoogleMap map;
	private Location myLocation;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_mapaciudad, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		restoreMyLocation(savedInstanceState);
		setupMap();
	}

	private void restoreMyLocation(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			myLocation = savedInstanceState.getParcelable(SAVED_LOCATION);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		initMap();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putParcelable(SAVED_LOCATION, myLocation);
		super.onSaveInstanceState(outState);
	}

	private void setupMap() {
		FragmentManager fragmentManager = getChildFragmentManager();
		SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
		if (mapFragment == null) {
			mapFragment = SupportMapFragment.newInstance();
			fragmentManager.beginTransaction().replace(R.id.map, mapFragment).commit();
		}
	}

	private void initMap() {
		if (map == null) {
			SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
			map = mapFragment.getMap();
		}

		if (map != null) {

			map.getUiSettings().setZoomControlsEnabled(true);

			map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

				@Override
				public void onInfoWindowClick(Marker arg0) {
					// TODO Auto-generated method stub
					Intent i = new Intent("android.intent.action.MAIN2");
					startActivity(i);

					//FragmentManager fragmentManager = getFragmentManager();
					//fragmentManager.beginTransaction().replace(R.id.content_frame,
					//DetailFragment.getInstance(marker.getPosition())).addToBackStack(null).commit();

				}
			});

			map.setInfoWindowAdapter(new InfoWindowAdapter() {

				@Override
				public View getInfoContents(Marker marker) {

					LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
					myContentsView = inflater.inflate(R.layout.activity_marcador, null);
					TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.title));
					tvTitle.setText(marker.getTitle());
					TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
					tvSnippet.setText(marker.getSnippet());

					return myContentsView;
				}

				@Override
				public View getInfoWindow(Marker marker) {
					// TODO Auto-generated method stub
					return null;
				}
			}); 


			//--------------- Se dibujan los marcadores en la mapa----------------//

			map.addMarker(new MarkerOptions().position(new LatLng(-16.40834,-71.494574)).title("Prueba"));

			// ---obtener todos los contactos---
			
			/*
			MenuPrincipalActivity.db.abrir();
			Cursor c =  MenuPrincipalActivity.db.obtenerTodosLosContactos();
			Toast.makeText(getActivity(), "Wilder2", Toast.LENGTH_LONG).show();

			if (c.moveToFirst()) {
				do {
					DisplayContact(c);
				} while (c.moveToNext());
			}
			MenuPrincipalActivity.db.cerrar();

			 */

			if (myLocation == null) {
				setLocationTracking(true);
			} else {
				addMyLocationMarker();
			}
		} else {
			Toast.makeText(getActivity(), "google maps no lo soporta", Toast.LENGTH_LONG).show();
		}

	}

	private void setLocationTracking(boolean tracking) {
		map.setMyLocationEnabled(true);
		map.setOnMyLocationChangeListener(tracking ? this : null);
	}

	@Override
	public void onMyLocationChange(Location location) {
		if (myLocation == null) {
			myLocation = location;
			centerMapOnMyLocation();
			setLocationTracking(false);
			addMyLocationMarker();
		}
	}

	private void centerMapOnMyLocation() {
		map.moveCamera(CameraUpdateFactory.newLatLng(getMyLatLng()));
		map.animateCamera(CameraUpdateFactory.zoomTo(MAP_ZOOM));
	}

	private void addMyLocationMarker() {
		map.addMarker(new MarkerOptions()
		.position(getMyLatLng())
		.visible(false)
				);
	}

	private LatLng getMyLatLng() {
		return new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
	}

	public void DisplayContact(Cursor c) {

		Toast.makeText(
				getActivity(),
				"idEmpresa: " + c.getString(0) + "\n" + "NombreEmpresa: " + c.getString(1)
				+ "\n" + "imgEmpresa: " + c.getString(2), Toast.LENGTH_LONG)
				.show();
	}
}
