package com.example.translatefromeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Context context=this;
    static EditText translated ;
    EditText textToTrans;
    Spinner lang;
    Button btnTran;
    String API_KEY;
    String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translated = findViewById(R.id.translated);
        textToTrans = findViewById(R.id.textToTrans);
        lang = findViewById(R.id.lang);
        btnTran =findViewById(R.id.btnTran);

        API_KEY = this.getString(R.string.key);
        URL = this.getString(R.string.url);

        String lan[] = getResources().getStringArray(R.array.Languages);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lang.setAdapter(adapter);
        //lang.setOnItemSelectedListener(this);
        translated.setEnabled(false);


        btnTran.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Translate(view);
                        try {
                            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        } catch (Exception e) {
                        }

                    }
                });


    }

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//        String lan[] = getResources().getStringArray(R.array.Languages);
//        String selected = lan[i];
//        //Toast.makeText(getApplicationContext(), "Selected User: "+lan[i] ,Toast.LENGTH_SHORT).show();
//    }


    public void Translate(View view) {

        //Default variables for translation
        String textToBeTranslated= textToTrans.getText().toString();
        String selected =lang.getSelectedItem().toString();
        String languagePair = selected;
        //Executing the translation function
        Translate(textToBeTranslated,languagePair);

    }

    void Translate(String textToBeTranslated, String languagePair){
        TranslatorBackgroundTask translatorBackgroundTask= new TranslatorBackgroundTask(context);


        String translationResult = String.valueOf(translatorBackgroundTask.execute(textToBeTranslated,languagePair,API_KEY,URL));
        Log.d("Translation Result",translationResult);

    }



}
