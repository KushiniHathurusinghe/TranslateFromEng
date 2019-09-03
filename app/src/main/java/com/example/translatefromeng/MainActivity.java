package com.example.translatefromeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Context context=this;
    static EditText translated ;
    EditText textToTrans;
    Spinner lang;
    Button btnTran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translated = findViewById(R.id.translated);
        textToTrans = findViewById(R.id.textToTrans);
        lang = findViewById(R.id.lang);
        btnTran =findViewById(R.id.btnTran);


        String lan[] = getResources().getStringArray(R.array.Languages);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lang.setAdapter(adapter);
        lang.setOnItemSelectedListener(this);
        translated.setEnabled(false);


        btnTran.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Translate(view);

                    }
                });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String lan[] = getResources().getStringArray(R.array.Languages);
        String selected = lan[i];
        //Toast.makeText(getApplicationContext(), "Selected User: "+lan[i] ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void Translate(View view) {

        //Default variables for translation
        String textToBeTranslated= textToTrans.getText().toString();
        String selected =lang.getSelectedItem().toString();
        String languagePair = "en-"+selected;
        //Executing the translation function
        Translate(textToBeTranslated,languagePair);

    }

    void Translate(String textToBeTranslated, String languagePair){
        TranslatorBackgroundTask translatorBackgroundTask= new TranslatorBackgroundTask(context);


        String translationResult = String.valueOf(translatorBackgroundTask.execute(textToBeTranslated,languagePair));
        Log.d("Translation Result",translationResult);

    }



}
