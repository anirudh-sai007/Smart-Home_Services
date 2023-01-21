package com.example.dumee1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.imaginativeworld.oopsnointernet.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.NoInternetDialog;
import org.imaginativeworld.oopsnointernet.NoInternetSnackbar;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Upload_image extends AppCompatActivity {
    CircleImageView profileImageView;
    private  final  int img_id=1;
    Button bt1,bt2;
    private Uri uri;
    private static final int PICK_IMAGE = 1;
    UploadTask uploadTask;
   FirebaseStorage firebaseStorage;
    FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;
    DocumentReference documentReference;
    String s1,s2,s3,s4;
    private static final String TAG = "ProfileActivity";
    int color = 0xFFcc2200;
    // No Internet Dialog
    NoInternetDialog noInternetDialog;

    // No Internet Snackbar
    NoInternetSnackbar noInternetSnackbar;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        s1=getIntent().getStringExtra("Phone");
        s2=getIntent().getStringExtra("Field");
        s3=getIntent().getStringExtra("Name");
        s4=getIntent().getStringExtra("Password");

        profileImageView = findViewById(R.id.profileImageView);
        bt1 = findViewById(R.id.skipp);
        bt2 = findViewById(R.id.upload);
        firebaseFirestore=FirebaseFirestore.getInstance();
        documentReference = firebaseFirestore.collection("Service Provider").document("Service").collection(s2).document(s1);;
        storageReference = firebaseStorage.getInstance().getReference("Profile_Picture");
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleImageClick(v);
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Upload_image.this,Upload_workimages.class);
                i.putExtra("Phone",s1);
                i.putExtra("Field",s2);
                i.putExtra("Name",s3);
                i.putExtra("Password",s4);
                startActivity(i);
                finish();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoInternetDialog.Builder builder1 = new NoInternetDialog.Builder(Upload_image.this);

                builder1.setConnectionCallback(new ConnectionCallback() { // Optional
                    @Override
                    public void hasActiveConnection(boolean hasActiveConnection) {
                        // ...

                        dialog = new Dialog(Upload_image.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_wait4);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        final  StorageReference Reference=storageReference.child(System.currentTimeMillis() + "." + getfileExt(uri));
                        uploadTask=Reference.putFile(uri);
                        Task<Uri> uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if(!task.isSuccessful())
                                {
                                    throw task.getException();
                                }
                                return Reference.getDownloadUrl();
                            }
                        })
                                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if(task.isSuccessful()) {
                                            Uri downloaduri = task.getResult();
                                            Map<String, Object> profile = new HashMap<String, Object>();
                                            profile.put("Name", s3);
                                            profile.put("Password", s4);
                                            profile.put("Field", s2);
                                            profile.put("Phone", s1);
                                            profile.put("Url", downloaduri.toString());


                                            documentReference.set(profile)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Intent i2=new Intent(Upload_image.this,Upload_workimages.class);
                                                            i2.putExtra("Phone",s1);
                                                            i2.putExtra("Field",s2);
                                                            i2.putExtra("Name",s3);
                                                            i2.putExtra("Password",s4);
                                                            startActivity(i2);
                                                            finish();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            dialog = new Dialog(Upload_image.this);
                                                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                            dialog.setContentView(R.layout.failure_emoji);
                                                            dialog.setCanceledOnTouchOutside(true);
                                                            dialog.show();
                                                        }
                                                    });
                                        }
                                    }
                                });
                    }
                });
                builder1.setCancelable(false); // Optional
                builder1.setNoInternetConnectionTitle("No Internet"); // Optional
                builder1.setNoInternetConnectionMessage("Check your Internet connection and try again"); // Optional
                builder1.setShowInternetOnButtons(true); // Optional
                builder1.setPleaseTurnOnText("Please turn on"); // Optional
                builder1.setWifiOnButtonText("Wifi"); // Optional
                builder1.setMobileDataOnButtonText("Mobile data"); // Optional

                builder1.setOnAirplaneModeTitle("No Internet"); // Optional
                builder1.setOnAirplaneModeMessage("You have turned on the airplane mode."); // Optional
                builder1.setPleaseTurnOffText("Please turn off"); // Optional
                builder1.setAirplaneModeOffButtonText("Airplane mode"); // Optional
                builder1.setShowAirplaneModeOffButtons(true); // Optional

                noInternetDialog = builder1.build();


                // No Internet Snackbar
                NoInternetSnackbar.Builder builder2 = new NoInternetSnackbar.Builder(Upload_image.this, (ViewGroup) findViewById(android.R.id.content));

                builder2.setConnectionCallback(new ConnectionCallback() { // Optional
                    @Override
                    public void hasActiveConnection(boolean hasActiveConnection) {
                        // ...

                        dialog = new Dialog(Upload_image.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_wait4);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                        final  StorageReference Reference=storageReference.child(System.currentTimeMillis() + "." + getfileExt(uri));
                        uploadTask=Reference.putFile(uri);
                        Task<Uri> uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if(!task.isSuccessful())
                                {
                                    throw task.getException();
                                }
                                return Reference.getDownloadUrl();
                            }
                        })
                                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if(task.isSuccessful()) {
                                            Uri downloaduri = task.getResult();
                                            Map<String, Object> profile = new HashMap<String, Object>();
                                            profile.put("Name", s3);
                                            profile.put("Password", s4);
                                            profile.put("Field", s2);
                                            profile.put("Phone", s1);
                                            profile.put("Url", downloaduri.toString());


                                            documentReference.set(profile)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Intent i2=new Intent(Upload_image.this,Upload_workimages.class);
                                                            i2.putExtra("Phone",s1);
                                                            i2.putExtra("Field",s2);
                                                            i2.putExtra("Name",s3);
                                                            i2.putExtra("Password",s4);
                                                            startActivity(i2);
                                                            finish();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            dialog = new Dialog(Upload_image.this);
                                                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                            dialog.setContentView(R.layout.failure_emoji);
                                                            dialog.setCanceledOnTouchOutside(true);
                                                            dialog.show();
                                                        }
                                                    });
                                        }
                                    }
                                });
                    }
                });
                builder2.setIndefinite(true); // Optional
                builder2.setNoInternetConnectionMessage("No active Internet connection!"); // Optional
                builder2.setOnAirplaneModeMessage("You have turned on the airplane mode!"); // Optional
                builder2.setSnackbarActionText("Settings");
                builder2.setShowActionToDismiss(false);
                builder2.setSnackbarDismissActionText("OK");

                noInternetSnackbar = builder2.build();


            }
        });




    }



    public void handleImageClick(View view) {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select an image"),img_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == img_id && resultCode== RESULT_OK && data!=null && data.getData()!=null)
        {

            uri=data.getData();
            //    Picasso.get().load(uri).into(profile);
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                profileImageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getfileExt(Uri uri)
    {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }


}