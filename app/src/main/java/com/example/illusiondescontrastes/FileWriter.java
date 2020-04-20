package com.example.illusiondescontrastes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class FileWriter extends View {
	public FileWriter( Context context ) {
		super( context );
	}

	void createExternalStoragePrivatePicture() {
		// Create a path where we will place our picture in our own private
		// pictures directory.  Note that we don't really need to place a
		// picture in DIRECTORY_PICTURES, since the media scanner will see
		// all media in these directories; this may be useful with other
		// media types such as DIRECTORY_MUSIC however to help it classify
		// your media for display to the user.
		File path = this.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		File file = new File(path, "DemoPicture.jpg");

		try {
			// Very simple code to copy a picture from the application's
			// resource into the external file.  Note that this code does
			// no error checking, and assumes the picture is small (does not
			// try to copy it in chunks).  Note that if external storage is
			// not currently mounted this will silently fail.
			@SuppressLint("ResourceType") InputStream is = getResources().openRawResource(R.drawable.logo);
			OutputStream os = new FileOutputStream(file);
			byte[] data = new byte[is.available()];
			is.read(data);
			os.write(data);
			is.close();
			os.close();

			// Tell the media scanner about the new file so that it is
			// immediately available to the user.
			MediaScannerConnection.scanFile(this.getContext(),
					new String[] { file.toString() }, null,
					new MediaScannerConnection.OnScanCompletedListener() {
						public void onScanCompleted(String path, Uri uri) {
							Log.i("ExternalStorage", "Scanned " + path + ":");
							Log.i("ExternalStorage", "-> uri=" + uri);
						}
					});
		} catch (IOException e) {
			// Unable to create file, likely because external storage is
			// not currently mounted.
			Log.w("ExternalStorage", "Error writing " + file, e);
		}
	}

	void deleteExternalStoragePrivatePicture() {
		// Create a path where we will place our picture in the user's
		// public pictures directory and delete the file.  If external
		// storage is not currently mounted this will fail.
		File path = this.getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		if (path != null) {
			File file = new File(path, "DemoPicture.jpg");
			file.delete();
		}
	}

	boolean hasExternalStoragePrivatePicture() {
		// Create a path where we will place our picture in the user's
		// public pictures directory and check if the file exists.  If
		// external storage is not currently mounted this will think the
		// picture doesn't exist.
		File path = this.getContext().getExternalFilesDir( Environment.DIRECTORY_PICTURES);
		if (path != null) {
			File file = new File(path, "DemoPicture.jpg");
			return file.exists();
		}
		return false;
	}
}