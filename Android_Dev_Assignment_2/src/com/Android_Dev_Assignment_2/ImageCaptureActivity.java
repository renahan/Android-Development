package com.Android_Dev_Assignment_2;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class ImageCaptureActivity extends Activity 
{

	public static final int IMAGE_RESULT = 5;
	private static final int RATING_RESULT = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_capture);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_capture, menu);
		return true;
	}
	
	/**
	 * This method will be invoked when btnLaunchCamera is clicked. 
	 * @param v
	 */
	public void onLaunchCameraClicked(View v)
	{
		//Use an implicit intent to invoke camera
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		
		//Start intent and anticipate a result
		startActivityForResult(cameraIntent, IMAGE_RESULT);
	}
	
	/**
	 * This method will be invoked when btnLaunchGallery is clicked. 
	 * @param v
	 */
	public void onBtnLaunchGalleryClicked(View v)
	{
		
		//Select or pick an image from a gallery
		Intent pictureSelectIntent = new Intent(Intent.ACTION_PICK);
		
		//get file system directory location of pictures
		String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
		
		//convert to URI
		Uri picturesDirectory = Uri.parse(path);
		
		//Load intent with directory path and set accepted image type 
		pictureSelectIntent.setDataAndType(picturesDirectory, "image/*");
		
		//start the activity and tell it what we want
		startActivityForResult(pictureSelectIntent, IMAGE_RESULT);
		
	}
	
	/**
	 * This method will receive intent result codes IMAGE_RESULT and RATING RESULT and execute accordingly.
	 * @param v
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK)
		{
			//only execute if good result received
			if(requestCode == IMAGE_RESULT)
			{
				//get image URI
				Uri photolocation = data.getData();
				
				//create intent for ImageInformationActivity
				Intent imageInformationActivity = new Intent(this, ImageInformationActivity.class);

				//Add Uri data to intent
				imageInformationActivity.putExtra("IMAGE_URI", photolocation.toString());
				
				//execute intent with return code IMAGE_RESULT
				startActivityForResult(imageInformationActivity, RATING_RESULT);

			}

			else if (requestCode == RATING_RESULT)
			{
				//set default value for rating
				int norating = 0;
				
				//get rating from ImageInformationActivity intent
				int rating = data.getIntExtra("RATING_VALUE", norating);
				
				//Only report rating if it is 4 or higher
				if (rating >= 4)
				{
					Toast.makeText(this, R.string.glad_you_liked_it_ , Toast.LENGTH_LONG).show();
				}
			}
		}
	}
}
