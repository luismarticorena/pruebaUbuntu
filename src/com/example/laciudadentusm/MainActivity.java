package com.example.laciudadentusm;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText correo;
	EditText pass;
	boolean error=false;
	String nombre;
	String apellido;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(this,"Se esta creando",Toast.LENGTH_LONG).show();
		setContentView(R.layout.activity_main);
		
		correo=(EditText)findViewById(R.id.correo);
		pass=(EditText)findViewById(R.id.password);
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void irInvitado(View v){
		Intent i=new Intent(this,MenuPrincipalActivity.class);
		i.putExtra("Nombre","Invitado");
		i.putExtra("Apellido"," ");
		startActivity(i);
	}

	public void Entrar(View v){
		Intent i=new Intent(this,MenuPrincipalActivity.class);
		i.putExtra("Nombre",nombre);
		i.putExtra("Apellido",apellido);
		startActivity(i);

	}

	public void registrarUsuario(View v){
		
		Intent i=new Intent(this,RegistroActivity.class);
		startActivity(i);
		
	}
	public void atras(View v){
		Intent i=new Intent(this,MainActivity.class);
		startActivity(i);
	}

}
