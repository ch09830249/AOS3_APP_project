package com.example.myapplication5;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.GoogleAPI;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends Activity  {
    Socket socket;
    public static final String file_name = "//storage//emulated//0//test.txt";
    EditText h;
    EditText g;

    //EditText textToTranlate;
    //Button buttonToTranslate;
    TextView textComment;
    TextView translatedText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        h = (EditText) findViewById(R.id.et1);
        g = (EditText) findViewById(R.id.et2);

        /********/
        //textToTranlate = (EditText)findViewById(R.id.et1);
        //buttonToTranslate = (Button)findViewById(R.id.button1);
        //textComment = (TextView)findViewById(R.id.comment);
        //translatedText = (TextView)findViewById(R.id.translatedtext);
        //buttonToTranslate.setOnClickListener(buttonToTranslateOnClickListener);
        /********/

        final ImageButton submit1 = (ImageButton)findViewById(R.id.button2);
        submit1.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "請說～");
                try{
                    startActivityForResult(intent,200);
                }catch (ActivityNotFoundException a){
                    Toast.makeText(getApplicationContext(),"Intent problem", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //中文英文發送
        final Button submit55 = (Button)findViewById(R.id.button55);
        submit55.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0){
                if (!("".equals(h.getText().toString()))) {
                   String s = (String)(h.getEditableText().toString());
                    try {
                        FileWriter fw = new FileWriter("//storage//emulated//0//test.txt", false);
                        BufferedWriter bw = new BufferedWriter(fw); //將BufferedWeiter與FileWrite物件做連結
                        bw.write(s);
                        bw.newLine();
                        bw.close();
                    } catch (IOException e) {
                        submit55.setText(e.toString());
                        e.printStackTrace();
                    }
                }
                Thread test = new Thread(clientSocket);
                test.start();
            }
        });
        final Button submit66 = (Button)findViewById(R.id.button66);
        submit66.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0){
                if (!("".equals(h.getText().toString()))) {
                    String s = (String)(h.getEditableText().toString());
                    try {
                        FileWriter fw = new FileWriter("//storage//emulated//0//test.txt", false);
                        BufferedWriter bw = new BufferedWriter(fw); //將BufferedWeiter與FileWrite物件做連結
                        bw.write(s);
                        bw.newLine();
                        bw.close();
                    } catch (IOException e) {
                        submit66.setText(e.toString());
                        e.printStackTrace();
                    }
                }
                Thread test = new Thread(clientSocket);
                test.start();
            }
        });
        //中文轉英文

        final Button submit56 = (Button)findViewById(R.id.button56);
        submit56.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0){
                String strTranslatedText = null;
                //String strTextToTranlate = textToTranlate.getText().toString();
                //Translate.setHttpReferrer("http://androidbiancheng.blogspot.com/");
                GoogleAPI.setHttpReferrer("http://translate.google.com.vn/");
                //GoogleAPI.setKey(API_KEY);

                try {
                    strTranslatedText = Translate.DEFAULT.execute( h.getText().toString(), Language.CHINESE_TRADITIONAL, Language.ENGLISH);
                    h.setText(strTranslatedText);
                    //textComment.setText("Text Translated:");
                    //translatedText.setText(strTranslatedText);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    textComment.setText("--- Error in Translate ---");
                    translatedText.setText(null);
                    e.printStackTrace();
                }

                if (!("".equals(h.getText().toString()))) {
                    String s = (String)(h.getEditableText().toString());
                    try {
                        FileWriter fw = new FileWriter("//storage//emulated//0//test.txt", false);
                        BufferedWriter bw = new BufferedWriter(fw); //將BufferedWeiter與FileWrite物件做連結
                        bw.write(s);
                        bw.newLine();
                        bw.close();
                    } catch (IOException e) {
                        submit56.setText(e.toString());
                        e.printStackTrace();
                    }
                }
                Thread test = new Thread(clientSocket);
                test.start();
            }
        });
        //英文轉中文
        final Button submit65 = (Button)findViewById(R.id.button65);
        submit65.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0){
                String strTranslatedText = null;
               //String strTextToTranlate = textToTranlate.getText().toString();
                //Translate.setHttpReferrer("http://androidbiancheng.blogspot.com/");
                GoogleAPI.setHttpReferrer("http://translate.google.com.vn/");
                //GoogleAPI.setKey(API_KEY);

                try {
                    strTranslatedText = Translate.DEFAULT.execute( h.getText().toString(), Language.ENGLISH, Language.CHINESE_TRADITIONAL);
                    h.setText(strTranslatedText);
                    //textComment.setText("Text Translated:");
                    //translatedText.setText(strTranslatedText);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    textComment.setText("--- Error in Translate ---");
                    translatedText.setText(null);
                    e.printStackTrace();
                }

                if (!("".equals(h.getText().toString()))) {
                    String s = (String)(h.getEditableText().toString());
                    try {
                        FileWriter fw = new FileWriter("//storage//emulated//0//test.txt", false);
                        BufferedWriter bw = new BufferedWriter(fw); //將BufferedWeiter與FileWrite物件做連結
                        bw.write(s);
                        bw.newLine();
                        bw.close();
                    } catch (IOException e) {
                        submit65.setText(e.toString());
                        e.printStackTrace();
                    }
                }
                Thread test = new Thread(clientSocket);
                test.start();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView textView = (TextView) this.findViewById(R.id.et1);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200){
            if(resultCode == RESULT_OK && data != null){
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                textView.setText(result.get(0));
                //add
                final Button submit = (Button)findViewById(R.id.button1);
                submit.performClick();
            }
        }
    }

    Runnable clientSocket = new Runnable() {
        public void run() {
            try {
                String s2 = (String)(g.getEditableText().toString());
                InetAddress serverAddr = InetAddress.getByName(s2);
                Log.e("Socket", "Client: Connecting...");
                try {
                    socket = new Socket(serverAddr, 1111);
                    OutputStream outputstream = socket.getOutputStream();
                    File myFile = new File(file_name);
                    if (myFile.exists()) {
                        byte[] mybytearray = new byte[(int) myFile.length()];
                        FileInputStream fis = new FileInputStream(myFile);

                        BufferedInputStream bis = new BufferedInputStream(fis /*,8 * 1024*/);
                        bis.read(mybytearray, 0, mybytearray.length);

                        //輸出到電腦
                        outputstream.write(mybytearray, 0, mybytearray.length);
                        outputstream.flush();
                    } else
                        Log.e("Socket", "file doesn't exist!");
                } catch (Exception e) {
                    Log.e("Socket", "Client: Error", e);
                } finally {
                    socket.close();
                }
            } catch (Exception e) {
            }
        }
    };
}
