package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        EditText titleInput=(EditText) findViewById(R.id.titleinput);
        EditText descriptionInput=(EditText) findViewById(R.id.descriptioninput);
        MaterialButton saveBtn=findViewById(R.id.savebtn);
        Realm.init(getApplicationContext());
        Realm realm=Realm.getDefaultInstance();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tittle=titleInput.getText().toString();
                String description=descriptionInput.getText().toString();
                long createdTime=System.currentTimeMillis();

                realm.beginTransaction();
                Note note=realm.createObject(Note.class);
                note.setTitle(tittle);
                note.setDescription(description);
                note.setCreatedTime(createdTime);
                realm.commitTransaction();
                Toast.makeText(AddNoteActivity.this, "Note Saves", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}