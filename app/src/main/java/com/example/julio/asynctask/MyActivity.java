package com.example.julio.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/*
*
* Short Description
*
* Optional longer description or discussion that may contain
* inline tags and some html markup. See the sections below for
* more details on the possible tags and markup. Separated by
* blank lines, this is used in page-level DocBlocks and in
* element-level DockBlocks when the element merits further discussion.
*
* @author	Julio Aguirre
* @date		Sep 29, 2014
* @since	version number
* @param	type argument for a function and what it takes as input
* @return	type what it returns
*/


public class MyActivity extends Activity implements OnClickListener {
	int contador = 0;
	Button btn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		btn = (Button) findViewById(R.id.boton);
		btn.setOnClickListener(this);

	}

	public void onClick(View view) {
		// detect the view that was "clicked"
		switch (view.getId()) {
			case R.id.boton:
				new LongOperation().execute("");
				contador++;
				break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class LongOperation extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					Thread.interrupted();
				}
				String cuenta = Integer.toString(i);
				Log.d("***TRY", cuenta);
			}
			return "Finalizado ...";
		}

		@Override
		protected void onPostExecute(String result) {
			TextView txt = (TextView) findViewById(R.id.output);
			String cuenta = Integer.toString(contador);
			txt.setText("*** " + cuenta + " ***"); // txt.setText(result);
			// might want to change "executed" for the returned string passed
			// into onPostExecute() but that is upto you
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}
}
