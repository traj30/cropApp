package seniorDesign;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import net.simplifiedcoding.androidcameraapp.R;

import java.io.File;


public class CropPicture extends Activity {
    Button BtDownload;
    ImageView IvImage;
    public static int currentImage = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        BtDownload = (Button)findViewById(R.id.BtDownload);
        IvImage = (ImageView)findViewById(R.id.IvImage);

        // Get the Intent that called for this Activity to open
        Intent activityThatCalled = getIntent();
        // Get the data that was sent
        Bundle callingBundle = activityThatCalled.getExtras();

        BtDownload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent imageDownload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                System.out.println("O HAYOOO");
                imageDownload.putExtra("crop", "true");
                imageDownload.putExtra("aspectX", 1);
                imageDownload.putExtra("aspectY", 1);
                imageDownload.putExtra("outputX", 200);
                imageDownload.putExtra("outputY", 200);
                imageDownload.putExtra("return-data", true);
                startActivityForResult(imageDownload, 3);

                Intent goingBack = new Intent();
                // Sends data back to the parent and can use RESULT_CANCELED, RESULT_OK, or any
                // custom values starting at RESULT_FIRST_USER. RESULT_CANCELED is sent if
                // this Activity crashes
                setResult(RESULT_OK, goingBack);

                Uri uriSavedImage = Uri.fromFile(new File("/sdcard/crop"+currentImage+".png"));
                currentImage++;
                goingBack.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);

                // Close this Activity
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 3 && resultCode == RESULT_OK && data != null)
        {
            System.out.println("Should be here!!!");
            Bundle extras = data.getExtras();
            Bitmap image = extras.getParcelable("data");
            IvImage.setImageBitmap(image);
        }
    }
}
