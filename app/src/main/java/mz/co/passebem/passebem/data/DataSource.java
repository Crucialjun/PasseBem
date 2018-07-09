package mz.co.passebem.passebem.data;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import mz.co.passebem.passebem.model.Pergunta;

public class DataSource {

    private static final String TAG = "DataSource";
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static  DatabaseReference myRef = database.getReference("perguntas");
    public static ArrayList<Object> perguntas;
    private static ChildEventListener mChildEventListener;

    public static void getTodasPerguntas(){
        perguntas = new ArrayList<>();

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Pergunta pergunta = dataSnapshot.getValue(Pergunta.class);
                perguntas.add(pergunta);
                Log.i(TAG, "onChildAdded");
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.i(TAG, "onChildChanged");
            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i(TAG, "onChildRemoved");
            }

            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            public void onCancelled(DatabaseError databaseError) {
                Log.i(TAG, "onCancelled");
            }
        };
        myRef.addChildEventListener(mChildEventListener);
    }



}
