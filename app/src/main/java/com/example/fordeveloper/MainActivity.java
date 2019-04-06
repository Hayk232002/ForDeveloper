package com.example.fordeveloper;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private Uri selectedImageUri;
    private Uri downloadUri;

    ImageView iv_photo;
    CheckBox chb_gold;
    CheckBox chb_silver;
    CheckBox chb_jewelry;
    CheckBox chb_gift;
    CheckBox chb_pen;
    CheckBox chb_cross;
    Button btn_upload;
    EditText et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_photo = (ImageView) findViewById(R.id.iv_photo);
        chb_gold = (CheckBox) findViewById(R.id.chb_gold);
        chb_silver = (CheckBox) findViewById(R.id.chb_silver);
        chb_jewelry = (CheckBox) findViewById(R.id.chb_jewelry);
        chb_gift = (CheckBox) findViewById(R.id.chb_gift);
        chb_pen = (CheckBox) findViewById(R.id.chb_pen);
        chb_cross = (CheckBox) findViewById(R.id.chb_cross);
        btn_upload = (Button) findViewById(R.id.btn_upload);
        et_name = (EditText) findViewById(R.id.et_name);

        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StorageReference ref = FirebaseStorage.getInstance().getReference().child(et_name.getText().toString());
                ref.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                downloadUri = uri;

                                Log.wtf("nakar", downloadUri.toString());

                                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                FirebaseDatabase.getInstance().getReference("images").child(et_name.getText().toString())
                                        .setValue(new ImageInfo("desc",downloadUri.toString(),et_name.getText().toString(),user.getUid()));
                            }
                        });
                    }
                });
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                iv_photo.setImageURI(selectedImageUri);

                Log.wtf("image",selectedImageUri.toString());
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}
