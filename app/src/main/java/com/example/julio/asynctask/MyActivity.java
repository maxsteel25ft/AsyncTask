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
*	Modificado de un original obtenido desde la Internet
*	Septiembre 28, 2014
*	New York
*/


public class MyActivity extends Activity implements OnClickListener {
		public int contador = 0;
		Button btn;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_my);
			btn = (Button) findViewById(R.id.boton);
			// because we implement OnClickListener we only have to pass "this"
			// (much easier)
			btn.setOnClickListener(this);
			int count = 0;
		}

		public void onClick(View view) {
			// detect the view that was "clicked"
			switch (view.getId()) {
				case R.id.boton:
					new LongOperation().execute("");
					contador ++;
					break;
			}
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
					Log.d("***TRY",cuenta);
				}
				return "Executed";
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
			protected void onPreExecute() {}

			@Override
			protected void onProgressUpdate(Void... values) {}
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
}
