package com.example.project1.admin.UploadNotices;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.project1.R;
import com.example.project1.admin.Adapters.DataClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

public class upload_details extends AppCompatActivity {

    ImageView mImageView;
    Button mChooseBtn;
    private EditText noticetitle;
    private Button upldbtn;
    private Uri imageUri;
    ProgressBar progressBar;
    final  private DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    final private StorageReference storageReference= FirebaseStorage.getInstance().getReference();

    String branch="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_details);

        mImageView = findViewById(R.id.imgview);
        mChooseBtn = findViewById(R.id.bttnn);
        noticetitle=findViewById(R.id.notice_title);
        progressBar =findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        upldbtn=findViewById(R.id.button8);

        branch=getIntent().getStringExtra("branch");

        ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()== Activity.RESULT_OK ){
                    Intent data=result.getData();
                    imageUri=data.getData();
                    mImageView.setImageURI(imageUri);
                }
                else{
                    Toast.makeText(upload_details.this, "No Image Selected", Toast.LENGTH_SHORT).show();

                }
            }
        });


        mChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker=new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });



        upldbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri!=null){
                    uploadToFirebase(imageUri);
                }else{
                    Toast.makeText(upload_details.this,"Please select image",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

//
    private void  uploadToFirebase(Uri uri){

        String caption=noticetitle.getText().toString();

        // Get the current date and time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateAndTime = sdf.format(new Date());

        final  StorageReference imageReference=storageReference.child(System.currentTimeMillis()+"."+getFileExtension(uri));

        imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        DataClass dataClass=new DataClass(uri.toString(),caption, currentDateAndTime);
                        //dataClass.setDateTime(currentDateAndTime);

//                        String key=databaseReference.push();
                        databaseReference.child(branch).child(currentDateAndTime).setValue(dataClass);
                         progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(upload_details.this, "uploaded", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(upload_details.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private String getFileExtension(Uri fileUri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
    }
}
