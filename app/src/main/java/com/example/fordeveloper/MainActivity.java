package com.example.fordeveloper;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String chb_pen_string;
    private String chb_cross_string;
    private String chb_pendent_string;

    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private Uri selectedImageUri;
    private Uri downloadUri;

    ImageView iv_photo;
    Button btn_upload;
    Button btn_men;
    Button btn_women;
    Button btn_child;
    Button btn_wedding;
    FrameLayout fl;
    EditText et_name;

    FragmentMen fragmentMen;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_photo = (ImageView) findViewById(R.id.iv_photo);
        btn_upload = (Button) findViewById(R.id.btn_upload);
        btn_men = (Button) findViewById(R.id.btn_men);
        btn_women = (Button) findViewById(R.id.btn_women);
        btn_child = (Button) findViewById(R.id.btn_child);
        btn_wedding = (Button) findViewById(R.id.btn_wedding);
        et_name = (EditText) findViewById(R.id.et_name);
        fl = (FrameLayout) findViewById(R.id.fl);

        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
            }
        });

        btn_upload.setOnClickListener(this);
        btn_men.setOnClickListener(this);
        btn_women.setOnClickListener(this);
        btn_child.setOnClickListener(this);
        btn_wedding.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_upload:
            {
//                final StorageReference ref = FirebaseStorage.getInstance().getReference().child(et_name.getText().toString());
//                ref.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                downloadUri = uri;
//
//                                Log.wtf("nakar", downloadUri.toString());
//
//                                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//                                FirebaseDatabase.getInstance().getReference("images").child(et_name.getText().toString())
//                                        .setValue(new ImageInfo("desc",downloadUri.toString(),et_name.getText().toString(),user.getUid()));
//                            }
//                        });
//                    }
//                });

                break;
            }

            case R.id.btn_men:
            {
                fragmentMen = new FragmentMen();

                ft=getFragmentManager().beginTransaction();

                ft.replace(fl.getId(),fragmentMen);

                ft.commit();

                break;
            }

            case R.id.btn_women:
            {


                break;
            }
        }
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

    public void setChbPenString(Boolean chb_pen_string_bool){
        if (chb_pen_string_bool){
            chb_pen_string = "yas";
        }

        else {
            chb_pen_string = "no";
        }

        Log.wtf("chb_pen_string",chb_pen_string);
    }

    public void setChbCrossString(Boolean chb_cross_string_bool){
        if (chb_cross_string_bool){
            chb_cross_string = "yas";
        }

        else {
            chb_cross_string = "no";
        }

        Log.wtf("chb_cross_string",chb_cross_string);
    }

    public void setChbPendentString(Boolean chb_pendent_string_bool){
        if (chb_pendent_string_bool){
            chb_pendent_string = "yas";
        }

        else {
            chb_pendent_string = "no";
        }

        Log.wtf("chb_pendent_string",chb_pendent_string);
    }
}
