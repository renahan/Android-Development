package com.Android_Dev_Assignment_2;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ImageInformationActivity extends Activity {

	private ImageView imgImageResult;
	private Bitmap extractedImage;
	private TextView lblImageWidthValue;
	private TextView lblHeightValue;
	private TextView lblSizeValue;
	private TextView lblLocationValue;
	private String imageUriString;
	private String sizestr;
	private String heightstr;
	private String widthstr;
	private RatingBar rtngbrImage;
	public RatingBar ratingBarValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_information);
		
		//get reference to layout components 
		imgImageResult = (ImageView) findViewById(R.id.imgImageResult);
		lblImageWidthValue = (TextView) findViewById(R.id.lblImageWidthValue);
		lblHeightValue = (TextView) findViewById(R.id.lblHeightValue);
		lblSizeValue = (TextView) findViewById(R.id.lblSizeValue);
		lblLocationValue = (TextView) findViewById(R.id.lblLocationValue);
		rtngbrImage = (RatingBar) findViewById(R.id.rtngbrImage);

		ExtractUriData();
		
		SetupDataFields();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_information, menu);
		return true;
	}
	/**
	 * This method will use a given Uri of an image to generate a bitmap and extract its width, height and size in bytes.
	 * @param v
	 */
	private void ExtractUriData() {
		//get uri string from intent
		imageUriString = getIntent().getStringExtra("IMAGE_URI");

		//convert uri string to uri
		Uri uri = Uri.parse(imageUriString);

		try 
		{
			// a stream of data from the file
			InputStream openInputStream = getContentResolver().openInputStream(uri);

			//take a stream of data and convert to a bitmap
			extractedImage = BitmapFactory.decodeStream(openInputStream);
			
			//load uri into imgImageResult (imageView)
			imgImageResult.setImageBitmap(extractedImage);
			
			//get image size and convert to string
			int size = extractedImage.getByteCount();
			sizestr = String.valueOf(size);
			
			//get image height and convert to string
			int height = extractedImage.getHeight();
			heightstr = String.valueOf(height);
			
			//get image width and convert to string
			int width = extractedImage.getWidth();
			widthstr = String.valueOf(width);
			

		} 
		
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();

			//alert the user that something went wrong
			Toast.makeText(this, getString(R.string.unable_to_open_image_) , Toast.LENGTH_LONG).show();
		}
		
	}
	/**
	 * This method will input an images width, height, and size in bytes (extracted from Uri) into repective TextViews.
	 * @param v
	 */
	public void SetupDataFields()
	{
		//Set TextViews with respective data
		lblImageWidthValue.setText(widthstr + R.string._pixels);
		
		lblHeightValue.setText(heightstr + R.string._pixels);
		
		lblSizeValue.setText(sizestr + R.string._bytes);
		
		lblLocationValue.setText(imageUriString);
	}
	/**
	 * This method will pull the value of the rating bar, prepare it to return it to ImageCaptureActivity and close the activity.
	 * @param v
	 */
	public void onBtnBackClicked(View v)
	{
		//Get rating value from rating bar
		int rating = (int) rtngbrImage.getRating();
		
		//load rating value to return to previous activity
		getIntent().putExtra("RATING_VALUE", rating);
		
		//Report that the intent was successful and return data
		setResult(RESULT_OK, getIntent());
		
		//close this activity and return to previous activity
		finish();
	}
	
}
