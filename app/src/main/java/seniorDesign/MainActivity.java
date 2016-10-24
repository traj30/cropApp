package seniorDesign;

//import java.io.*;
//import java.util.*;

import android.content.Context;
//import android.database.Cursor;
import android.content.Intent;
import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import net.simplifiedcoding.androidcameraapp.R;

import java.io.File;
import java.io.IOException;

import static net.simplifiedcoding.androidcameraapp.R.id.image;


public class MainActivity extends ActionBarActivity {//implements View.OnClickListener {

    private Button btnCamera;
    private Button btnCrop;
    private ImageView capturedImage;
    private Button display_RGB_button;
    private TextView R_value_display;
    private TextView G_value_display;
    private TextView B_value_display;
    //private Uri mImageCaptureUri;
    //static final int CAMERA_PIC_REQUEST = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
        btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCrop = (Button) findViewById(R.id.btnCrop);

        capturedImage= (ImageView) findViewById(R.id.capturedImage);

        R_value_display = (TextView) findViewById(R.id.R_value_display);

        G_value_display = (TextView) findViewById(R.id.G_value_display);

        B_value_display = (TextView) findViewById(R.id.B_value_display);

        //display_RGB_button = (Button) findViewById(R.id.display_RGB_button);

        //display_RGB_button.setTypeface(font);

        //display_RGB_button.setOnClickListener(MainActivity.this);

        btnCamera.setTypeface(font);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        btnCrop.setTypeface(font);

        btnCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cropPicture();
            }
        });
    }

    private void openCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(intent, 0);

        //intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        Uri uriSavedImage = Uri.fromFile(new File("/sdcard/test.png"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(intent, 1);
    }

    public void cropPicture(){
        Intent getNameScreenIntent = new Intent(this, CropPicture.class);
        Bundle myExtras = new Bundle();
        //myExtras.putString("callingActivity", "MainBitchinActivity");
        //getNameScreenIntent.putExtras(myExtras);
        final int result = 1;
        startActivityForResult(getNameScreenIntent, result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        //if((resultCode == RESULT_OK) && (requestCode == CAMERA_PIC_REQUEST)) {
        if(resultCode == RESULT_OK) {
//            Bitmap bp = (Bitmap) data.getExtras().get("data");
//            capturedImage.setImageBitmap(bp);
//            Log.d("hello", "" + getRGB(bp));
            Uri uriSavedImage = Uri.fromFile(new File("/sdcard/test.png"));

           //Uri tempUri = getImageUri(getApplicationContext(),bp);
            try {
//                Uri imageUri = data.getData();
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriSavedImage);
//                int a = getRGB(bitmap);
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/test.png", bmOptions);
                capturedImage.setImageBitmap(bitmap);

                Log.d("hello", "works" + getRGB(bitmap));
//                Log.d("hellocatch (IOException e){ // catch all IOExceptions not handled by previous catch blocks\n" +
//                        "//            System.out.println(\"General I/O exception: \" + e.getMessage());\n" +
//                        "//            //e.printStackTrace();\n" +
//                        "//            }", "mauricio");
            }
            catch (Exception e){
//                System.out.println(e.getMessage)
            }

            /*
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getApplicationContext(),bp);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File finalFile = new File(getRealPathFromURI(tempUri));

            String pathToImage = mImageCaptureUri.getPath();

            Bitmap often = BitmapFactory.decodeFile(pathToImage);
            */


            //int RGB_value = getRGB(bp);

        }
    }



/*
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = android.provider.MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(android.provider.MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }*/




    /**
     * Calculates the average RGB color of an image
     */
    /*
    public class AverageColorCalculus extends Calculus<Integer> {

        public static final String TAG = AverageColorCalculus.class.getSimpleName();

        public AverageColorCalculus(Bitmap image) {
            super(image);
        }*/

        //@Override
        public int getRGB(Bitmap image) {

            if (null == image) {
                Log.d("hello","NOOOOOOOOOOOOOOO");
                return android.graphics.Color.TRANSPARENT;
            } else {

                int optI = 4;
                int optJ = 4;
                int pixelCount = (image.getWidth()/(2*optI)) * (image.getHeight()/(2*optJ));
                int red, green, blue;
                red = green = blue = 0;


                Log.d("hello","width" + image.getWidth());
                Log.d("hello","height" + image.getHeight());


                for (int i = (image.getWidth()/4); i < (image.getWidth()/2)+(image.getWidth()/4); i+=optI) {
                    for (int j = (image.getHeight()/4); j < (image.getHeight()/2)+(image.getHeight()/4); j+=optJ) {
                        int pixel = image.getPixel(i, j);

                        red += android.graphics.Color.red(pixel);
                        green += android.graphics.Color.green(pixel);
                        blue += android.graphics.Color.blue(pixel);

                        Log.d("hello","r" + red);
                        Log.d("hello","g" + green);
                        Log.d("hello","b" + blue);

                    }
                }


                red /= pixelCount;
                green /= pixelCount;
                blue /= pixelCount;

                R_value_display.setText("Red: "+red);
                G_value_display.setText("Green: "+green);
                B_value_display.setText("Blue: "+blue);

                int RGB_value = android.graphics.Color.rgb(red, green, blue);

                return RGB_value;
            }
        }

}
