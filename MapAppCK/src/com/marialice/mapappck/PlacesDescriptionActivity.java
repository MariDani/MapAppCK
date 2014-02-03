package com.marialice.mapappck;

/* 
 * this is the activity for the detail view of places,
 * individual for each place
 */
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlacesDescriptionActivity extends Activity {

	// include our classes
	DatabaseContent dbclass = new DatabaseContent();
	TextToBitmap drawclass = new TextToBitmap();

	Double lat;
	Double lon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_places_description);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		createDetails();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.description, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressLint("ResourceAsColor")
	public void createDetails() {
		TextView textViewTitle = (TextView) findViewById(R.id.desc_title);
		TextView textViewDesc = (TextView) findViewById(R.id.description);
		ImageView imageViewIcon = (ImageView) findViewById(R.id.desc_icon);
		ImageView imageViewWifi = (ImageView) findViewById(R.id.wifi);
		ImageView imageViewTerrace = (ImageView) findViewById(R.id.terrace);
		ImageView imageViewSundays = (ImageView) findViewById(R.id.sundays);
		ImageView imageViewCalmPlace = (ImageView) findViewById(R.id.calmplace);
		ImageView imageViewSmoking = (ImageView) findViewById(R.id.smoking);
		Button buttonShowMap = (Button) findViewById(R.id.ShowMap);

		Intent listintent = getIntent();
		String titlels = listintent.getStringExtra("listDataChild");
		// creates the title according to the intent extra
		textViewTitle.setText(titlels);

		List<Poi> dbpois = dbclass.queryDataFromDatabase(this);
		for (int i = 0; i < dbpois.size(); i++) {
			Poi poi = dbpois.get(i);
			String titledb = poi.getTitle();

			if (titledb.equals(titlels)) {

				// creates the description based on title comparison
				textViewDesc.setText(poi.getDescription());
				// creates the icon in the TextToBitmap class using symbol and
				// number
				imageViewIcon.setImageBitmap(drawclass.drawTextToBitmap(
						getApplicationContext(), poi.getSymbol(),
						poi.getNumber()));
				// requests lat and lon for usage with the button 'goto_map'
				lat = poi.getLat();
				lon = poi.getLon();

				// if the entrance in db is true, set the extra info icon
				if (poi.getWifi() == true) {
					imageViewWifi.setImageResource(R.drawable.description_wifi);
					imageViewWifi.setVisibility(View.VISIBLE);
					// OnLongClickListener();
				}
				if (poi.getTerrace() == true) {
					imageViewTerrace
							.setImageResource(R.drawable.description_terrace);
					imageViewTerrace.setVisibility(View.VISIBLE);
					// OnLongClickListener();
				}
				if (poi.getSundays() == true) {
					imageViewSundays
							.setImageResource(R.drawable.description_sunday);
					imageViewSundays.setVisibility(View.VISIBLE);
					// OnLongClickListener();
				}
				if (poi.getCalmplace() == true) {
					imageViewCalmPlace
							.setImageResource(R.drawable.description_calmplace);
					imageViewCalmPlace.setVisibility(View.VISIBLE);
					// OnLongClickListener();
				}
				if (poi.getNonsmoking() == true) {
					imageViewSmoking
							.setImageResource(R.drawable.description_smoking);
					imageViewSmoking.setVisibility(View.VISIBLE);
					// OnLongClickListener();
				}
				if (poi.getTouristclassic() == true) {
					textViewDesc.setBackgroundResource(R.drawable.border_tc);
					// OnLongClickListener();
				}

				// show map button
				if (poi.getCategory().equals("sightseeing")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_sightseeing);
				} else if (poi.getCategory().equals("museum")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_museum);
				} else if (poi.getCategory().equals("shopping")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_shopping);
				} else if (poi.getCategory().equals("eat")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_eat);
				} else if (poi.getCategory().equals("cafe")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_cafe);
				} else if (poi.getCategory().equals("bar")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_bar);
				} else if (poi.getCategory().equals("hidden")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_hidden);
				} else if (poi.getCategory().equals("museumsightseeing")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_museumsightseeing);
				} else if (poi.getCategory().equals("museumcafe")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_museumcafe);
				} else if (poi.getCategory().equals("shoppingeat")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_shoppingeat);
				} else if (poi.getCategory().equals("hiddencafe")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_hiddencafe);
				} else if (poi.getCategory().equals("shoppingcafe")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_shoppingcafe);
				} else if (poi.getCategory().equals("shoppingsightseeing")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_shoppingsightseeing);
				} else if (poi.getCategory().equals("barcafe")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_barcafe);
				} else if (poi.getCategory().equals("barsightseeing")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_barsightseeing);
				} else if (poi.getCategory().equals("sightseeinghidden")) {
					buttonShowMap
							.setBackgroundResource(R.drawable.button_show_map_sightseeinghidden);
				} else {
					buttonShowMap.setBackgroundColor(R.color.grey);
				}
			}
		}
	}

	/*
	 * // on long click listener private void OnLongClickListener() { ViewGroup
	 * showLegend = (ViewGroup) findViewById(R.id.description_legend);
	 * showLegend.setOnLongClickListener(new OnLongClickListener() {
	 * 
	 * @Override public boolean onLongClick(View view) {
	 * 
	 * LayoutInflater inflater = getLayoutInflater(); // Inflate the Layout View
	 * layout = inflater.inflate( R.layout.custom_toast_description, (ViewGroup)
	 * findViewById(R.id.custom_toast_layout)); Toast toast = new
	 * Toast(getApplicationContext()); toast.setDuration(Toast.LENGTH_LONG);
	 * toast.setGravity(Gravity.TOP | Gravity.RIGHT, 10, 300);
	 * toast.setView(layout); toast.show(); return true; } }); }
	 */

	// on click listener
	public void showLegend(View view) {
		LayoutInflater inflater = getLayoutInflater(); // Inflate the Layout
		View layout = inflater.inflate(R.layout.custom_toast_description,
				(ViewGroup) findViewById(R.id.custom_toast_layout));

		Toast toast = new Toast(getApplicationContext());
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setGravity(Gravity.TOP | Gravity.RIGHT, 10, 300);
		toast.setView(layout);
		toast.show();

	}

	// this method is called when the button is clicked
	public void gotomap(View view) {
		if (lat == null) {
			// this toast is for catching and indicating errors in data flow
			Context context = getApplicationContext();
			CharSequence text = "no lat lon values!";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		} else {
			// using the lat lon values requested previously, the map activity
			// is
			// started with the values as extras attached
			Intent mapintent = new Intent(this, MapActivity.class);
			mapintent.putExtra("lat", lat);
			mapintent.putExtra("lon", lon);
			startActivity(mapintent);
		}
	}

}
