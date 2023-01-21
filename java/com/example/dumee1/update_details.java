package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class update_details extends AppCompatActivity {
    CircleImageView profileImageView;
    ImageView profile;
    EditText et1, et2, et3, et4;
    private final int img_id = 1;
    Button bt1;
    FirebaseUser firebaseUser;
    String s3;
    private Uri uri;
    private static final int PICK_IMAGE = 1;
    UploadTask uploadTask;
    ProgressBar progressBar;
    FirebaseStorage firebaseStorage;
    FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;
    DocumentReference documentReference;
    private static final String TAG = "ProfileActivity";
    int color = 0xFFcc2200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);

        profileImageView = findViewById(R.id.profileImageView);
        profile = findViewById(R.id.profilepic);
        et1 = findViewById(R.id.username);
        et2 = findViewById(R.id.pwd);
        et3 = findViewById(R.id.cfpwd);
        et4 = findViewById(R.id.phno);
        bt1 = findViewById(R.id.saveProfileButton);
        progressBar = findViewById(R.id.progressBar);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        s3 = firebaseUser.getPhoneNumber().toString().trim();
        String d = s3.substring(1, s3.length());
        documentReference = firebaseFirestore.collection("Service Provider").document("Service").collection("Plumber").document(d);
        storageReference = firebaseStorage.getInstance().getReference("profile image");
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploaddata();
            }
        });

    }
    public void handleImageClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an image"), img_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == img_id && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                profileImageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getfileExt(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploaddata() {
        final String s1, s2, s3, s4;
        s1 = et1.getText().toString().trim();
        s2 = et2.getText().toString().trim();
        s3 = et3.getText().toString().trim();
        s4 = et4.getText().toString().trim();

        if (!TextUtils.isEmpty(s1) || !TextUtils.isEmpty(s2) || !TextUtils.isEmpty(s3) || !TextUtils.isEmpty(s4) || uri != null) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(color);
            final StorageReference Reference = storageReference.child(System.currentTimeMillis() + "." + getfileExt(uri));
            uploadTask = Reference.putFile(uri);
            Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return Reference.getDownloadUrl();
                }
            })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {

                            if (task.isSuccessful()) {
                                Uri downloaduri = task.getResult();
                                Map<String, Object> profie = new HashMap<String, Object>();
                                profie.put("Name", s1);
                                profie.put("Password", s2);
                                profie.put("Phone", s4);

                                profie.put("Url", downloaduri.toString());
                                documentReference.set(profie)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                progressBar.setVisibility(View.INVISIBLE);

                                                Toast.makeText(getApplicationContext(), "Profile created for user" + s4, Toast.LENGTH_SHORT).show();
                                                Intent intent1 = new Intent(update_details.this, profiledetails.class);
                                                startActivity(intent1);
                                                finish();

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.getResult().exists()) {
                            String name = task.getResult().getString("Name");
                            String pwd = task.getResult().getString("Password");
                            String phno = task.getResult().getString("Phone");
                            String urll = task.getResult().getString("Url");
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(urll));
                                profileImageView.setImageBitmap(bitmap);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            et1.setText(name);
                            et2.setText(pwd);
                            et3.setText(pwd);
                            et4.setText(phno);

                        } else {
                            Toast.makeText(update_details.this, "No profile created", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}
