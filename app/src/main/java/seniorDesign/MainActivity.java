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
import android.os.Environment;
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
    private static final String TEMP_PHOTO_FILE = "temporary_holder.jpg";
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
        /*Intent getNameScreenIntent = new Intent(this, CropPicture.class);
        Bundle myExtras = new Bundle();
        //myExtras.putString("callingActivity", "MainBitchinActivity");
        //getNameScreenIntent.putExtras(myExtras);
        final int result = 2;
        startActivityForResult(getNameScreenIntent, result);*/
        /*Intent imageDownload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        System.out.println("O HAYOOO");
        imageDownload.putExtra("crop", "true");
        imageDownload.putExtra("aspectX", 1);
        imageDownload.putExtra("aspectY", 1);
        imageDownload.putExtra("outputX", 200);
        imageDownload.putExtra("outputY", 200);
        imageDownload.putExtra("return-data", true);
        startActivityForResult(imageDownload, 2);*/

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        photoPickerIntent.putExtra("crop", "true");
        Uri uriSavedImage = Uri.fromFile(new File("/sdcard/test.png"));
        CropPicture.currentImage++;
        photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(photoPickerIntent, 2);
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
        } catch (Exception e) {
//                System.out.println(e.getMessage)
        }
        //Intent goingBack = new Intent();
        // Sends data back to the parent and can use RESULT_CANCELED, RESULT_OK, or any
        // custom values starting at RESULT_FIRST_USER. RESULT_CANCELED is sent if
        // this Activity crashes
        //setResult(RESULT_OK, goingBack);

        /*Uri uriSavedImage = Uri.fromFile(new File("/sdcard/crop"+currentImage+".png"));
        currentImage++;
        goingBack.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);*/

        // Close this Activity
        //finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        //if((resultCode == RESULT_OK) && (requestCode == CAMERA_PIC_REQUEST)) {
        if(resultCode == RESULT_OK) {
            if(requestCode == 1) {
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
                } catch (Exception e) {
//                System.out.println(e.getMessage)
                }
            }
            else if(resultCode == 2){

                System.out.println("\n:::::::::::::::aksjfdklasjdfl;j");
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
        public int [] getRGB(Bitmap image) {

            if (null == image) {
                Log.d("hello","NOOOOOOOOOOOOOOO");
                return null;
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

                //int RGB_value = android.graphics.Color.rgb(red, green, blue);

                int [] rgbVals = {red, green, blue};

                return rgbVals;
            }
        }


    public double calibrateCurve(){         //ADD (double blueRGBVals[], double blueRGBVals[], double whiteVals[])

        //THIS SHOULD BE CALLED AFTER ALL THREE CROPS ARE OBTAINED, SO CALIBRATION UNDERNEATH CAN CONTINUE


        //RGB VALUES ARE INPUT FROM getRGB

        double [] blueRGBVals = {0.0, 1.0, 2.0};

        double [] purpleRGBVals = {0.0, 1.0, 2.0};

        double [] whiteVals = {0.0, 1.0, 2.0};

        int iter = 100;

        double total = 0;

        for(int i = 0; i<6; i++) {
            blueRGBVals[i] = 255 - whiteVals[i] + blueRGBVals[i];
            purpleRGBVals[i] = 255 - whiteVals[i] + purpleRGBVals[i];
        }

        double [] BG = {newtraphBlueR(iter,blueRGBVals),newtraphBlueG(iter,blueRGBVals),newtraphBlueB(iter,blueRGBVals),
                newtraphPurpleR(iter,purpleRGBVals),newtraphPurpleG(iter,purpleRGBVals),newtraphPurpleB(iter,purpleRGBVals)};

        for(int i = 0; i<6; i++) {
            total += BG[i];
        }

        return total/6;

    }

    public static double sigR(double x, double t) {
        return 78.94 + (208.2/(1+Math.exp(-1*((x-161.7)/-91.09)))) - t;
    }

    public static double sigG(double x, double t) {
        return 71.86 + (205.3/(1+Math.exp(-1*((x-112)/-91.48)))) - t;
    }

    public static double sigB(double x, double t) {
        return 109.7 +(121.9/(1+Math.exp(-1*((x-163.6)/-62.29)))) - t;
    }

    public static double dsigR(double x) {
        return 208.2*Math.exp((161.7+x)/-91.09)/(-91.09*(Math.exp(161.7/-91.09) + Math.exp(x/-91.09))*(Math.exp(161.7/-91.09) + Math.exp(x/-91.09)));
    }

    public static double dsigG(double x) {
        return 205.3*Math.exp((112+x)/-91.48)/(-91.48*(Math.exp(112/-91.48) + Math.exp(x/-91.48))*(Math.exp(112/-91.48) + Math.exp(x/-91.48)));
    }

    public static double dsigB(double x) {
        return 121.9*Math.exp((163.6+x)/-62.29)/(-62.29*(Math.exp(163.6/-62.29) + Math.exp(x/-62.29))*(Math.exp(163.6/-62.29) + Math.exp(x/-62.29)));
    }

    public static double expR(double x, double t) {
        return 191.1*Math.exp(-0.009766*x) + 62.24*Math.exp(-0.0004514*x) - t;
    }

    public static double expG(double x, double t) {
        return 123.8*Math.exp(-0.005929*x) + 127.9*Math.exp(-0.000418*x) - t;
    }

    public static double expB(double x, double t) {
        return 80.97*Math.exp(-0.004578*x) + 156*Math.exp(-0.0003154*x) - t;
    }

    public static double dexpR(double x) {
        return 191.1*-0.009766*Math.exp(-0.009766*x) + 62.24*-0.0004514*Math.exp(-0.0004514*x);
    }

    public static double dexpG(double x) {
        return 123.8*-0.005929*Math.exp(-0.005929*x) + 127.9*-0.000418*Math.exp(-0.000418*x);
    }

    public static double dexpB(double x) {
        return 80.97*-0.004578*Math.exp(-0.004578*x) + 156*-0.0003154*Math.exp(-0.0003154*x);
    }

    public double newtraphBlueR(int iter, double [] vals){
        double xrold = 0;
        double xr = 0;
        double ea = 0;
        double es = 0;
        double maxit = 0;
        while(true) {
            xrold = xr;
            xr = xr - expR(xr, vals[0])/dexpR(xr) ;
            iter = iter + 1;
            if (xr != 0){
                ea = Math.abs((xr - xrold)/xr) * 100;
            }
            if (ea <= es || iter >= maxit){
                break;
            }
        }
        return xr;
    }

    public double newtraphBlueG(int iter, double [] vals){
        double xrold = 0;
        double xr = 0;
        double ea = 0;
        double es = 0;
        double maxit = 0;
        while(true) {
            xrold = xr;
            xr = xr - expG(xr, vals[1])/dexpG(xr) ;
            iter = iter + 1;
            if (xr != 0){
                ea = Math.abs((xr - xrold)/xr) * 100;
            }
            if (ea <= es || iter >= maxit){
                break;
            }
        }
        return xr;
    }

    public double newtraphBlueB(int iter, double [] vals){
        double xrold = 0;
        double xr = 0;
        double ea = 0;
        double es = 0;
        double maxit = 0;
        while(true) {
            xrold = xr;
            xr = xr - expB(xr, vals[2])/dexpB(xr) ;
            iter = iter + 1;
            if (xr != 0){
                ea = Math.abs((xr - xrold)/xr) * 100;
            }
            if (ea <= es || iter >= maxit){
                break;
            }
        }
        return xr;
    }

    public double newtraphPurpleR(int iter, double [] vals){
        double xrold = 0;
        double xr = 0;
        double ea = 0;
        double es = 0;
        double maxit = 0;
        while(true) {
            xrold = xr;
            xr = xr - sigR(xr, vals[0])/dsigR(xr) ;
            iter = iter + 1;
            if (xr != 0){
                ea = Math.abs((xr - xrold)/xr) * 100;
            }
            if (ea <= es || iter >= maxit){
                break;
            }
        }
        return xr;
    }

    public double newtraphPurpleG(int iter, double [] vals){
        double xrold = 0;
        double xr = 0;
        double ea = 0;
        double es = 0;
        double maxit = 0;
        while(true) {
            xrold = xr;
            xr = xr - sigG(xr, vals[1])/dsigG(xr) ;
            iter = iter + 1;
            if (xr != 0){
                ea = Math.abs((xr - xrold)/xr) * 100;
            }
            if (ea <= es || iter >= maxit){
                break;
            }
        }
        return xr;
    }


    public double newtraphPurpleB(int iter, double [] vals){
        double xrold = 0;
        double xr = 0;
        double ea = 0;
        double es = 0;
        double maxit = 0;
        while(true) {
            xrold = xr;
            xr = xr - sigB(xr, vals[2])/dsigB(xr) ;
            iter = iter + 1;
            if (xr != 0){
                ea = Math.abs((xr - xrold)/xr) * 100;
            }
            if (ea <= es || iter >= maxit){
                break;
            }
        }
        return xr;
    }

}
