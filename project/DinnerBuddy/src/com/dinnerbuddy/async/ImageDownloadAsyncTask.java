package com.dinnerbuddy.async;

import java.io.IOException;
import java.net.URL;

import com.dinnerbuddy.util.ViewHolder;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;


public class ImageDownloadAsyncTask extends AsyncTask<ViewHolder, Void, ViewHolder> {

	@Override
	protected ViewHolder doInBackground(ViewHolder... params) {
		//load image directly
		ViewHolder viewHolder = params[0];
		try {
			URL imageURL = new URL(viewHolder.imageURL);
			viewHolder.bitmap = BitmapFactory.decodeStream(imageURL.openStream());
		} catch (IOException e) {
			Log.e("error", "Downloading Image Failed");
			viewHolder.bitmap = null;
		}
		return viewHolder;
	}
	
	@Override
	protected void onPostExecute(ViewHolder result) {

		if (result.bitmap != null) {
			result.imageView.setImageBitmap(result.bitmap);
		}
	}
}
