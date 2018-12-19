package com.example.dummy.yuvavarta;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class Register extends Activity {
    EditText name,mail,phone,city,uname,pass,pass2;
    String State;
    Validations validations=new Validations();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.name);
        mail = (EditText) findViewById(R.id.mail);
        phone = (EditText) findViewById(R.id.phone);
        city = (EditText) findViewById(R.id.city);
        uname = (EditText) findViewById(R.id.uname);
        pass = (EditText) findViewById(R.id.pass);
        pass2 = (EditText) findViewById(R.id.pass2);
        final Spinner spinner = (Spinner) findViewById(R.id.states);
        State = spinner.getSelectedItem().toString();



    }
        public void onSubmit(View view){
                SendData sendData= new SendData(Register.this);
           if(validations.checkBlanks(name.getText().toString(),mail.getText().toString(),phone.getText().toString(),city.getText().toString(),uname.getText().toString(),pass.getText().toString(),pass2.getText().toString(),State)){
               Toast.makeText(Register.this,"Fields should not be vacant !",Toast.LENGTH_SHORT).show();
           }
            else if(validations.checkNumber(name.getText().toString()) || validations.checkSpecialChars(name.getText().toString())){
                Toast.makeText(Register.this,"Invalid Name !",Toast.LENGTH_SHORT).show();
            }
            else if(validations.checkNumber(city.getText().toString())||validations.checkSpecialChars(city.getText().toString()))
            {
                Toast.makeText(Register.this,"Invalid City !",Toast.LENGTH_SHORT).show();
            }
            else if(!validations.checkNumberSize(phone.getText().toString())||validations.checkSpecialChars(phone.getText().toString())||validations.checkLetters(phone.getText().toString()))
            {
                Toast.makeText(Register.this,"Invalid Phone Number !",Toast.LENGTH_SHORT).show();
            }
            else if(!validations.checkPasswordSize(pass.getText().toString()) || !pass.getText().toString().equals(pass2.getText().toString()))
            {
                Toast.makeText(Register.this,"Password dont match !",Toast.LENGTH_SHORT).show();
            }
            else {

                sendData.execute(name.getText().toString(), mail.getText().toString(), phone.getText().toString(), city.getText().toString(), State, uname.getText().toString(), pass.getText().toString(), pass2.getText().toString());
            }
        }
    public class SendData extends AsyncTask<String, Void, String> {
    Context context;
        AlertDialog alertDialog;
        SendData(Context ctx) {
                        context = ctx;
                    }
        @Override
        protected String doInBackground(String... params)
        {
            String login_url = "http://yuvavarta.com/yuva_register.php";
            try
            {
                            String paraName = params[0];
                            String paraMail = params[1];
                            String paraPhone = params[2];
                            String paraCity = params[3];
                            String paraState = params[4];
                            String paraUname = params[5];
                            String paraPass = params[6];
                            String paraPass2 = params[7];

                                URL url = new URL(login_url);
                                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                                httpURLConnection.setRequestMethod("POST");
                                httpURLConnection.setDoInput(true);
                                httpURLConnection.setDoInput(true);
                                OutputStream outputStream = httpURLConnection.getOutputStream();
                                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "Utf-8"));
                                String post_data = URLEncoder.encode("Name", "Utf-8") + "=" + URLEncoder.encode(paraName, "Utf-8")
                                        + "&" +
                                        URLEncoder.encode("Mail", "Utf-8") + "=" + URLEncoder.encode(paraMail, "Utf-8")
                                        + "&" +
                                        URLEncoder.encode("Phone", "Utf-8") + "=" + URLEncoder.encode(paraPhone, "Utf-8")
                                        + "&" +
                                        URLEncoder.encode("City", "Utf-8") + "=" + URLEncoder.encode(paraCity, "Utf-8")
                                        + "&" +
                                        URLEncoder.encode("State", "Utf-8") + "=" + URLEncoder.encode(paraState, "Utf-8")
                                        + "&" +
                                        URLEncoder.encode("Username", "Utf-8") + "=" + URLEncoder.encode(paraUname, "Utf-8")
                                        + "&" +
                                        URLEncoder.encode("Password", "Utf-8") + "=" + URLEncoder.encode(paraPass, "Utf-8");
                                bufferedWriter.write(post_data);
                                bufferedWriter.flush();
                                bufferedWriter.close();
                                outputStream.close();

                                InputStream inputStream = httpURLConnection.getInputStream();
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                                String line = "";
                                String result = "";
                                while ((line = bufferedReader.readLine()) != null) {
                                    result += line;
                                }
                                bufferedReader.close();
                                inputStream.close();
                                return result;

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                    @Override
                    protected void onPreExecute() {
                        alertDialog = new AlertDialog.Builder(context).create();
                        alertDialog.setTitle("Registration Status !");
                    }
                    @Override
                    protected void onPostExecute(String result)
                    {
                        if (result=="Registered Successfully !")
                        {
                            name.setText("");
                            mail.setText("");
                            phone.setText("");
                            city.setText("");
                            State="";
                            uname.setText("");
                            pass.setText("");
                            pass2.setText("");
                           // Toast.makeText(getBaseContext(),"Registered Successfully !",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Register.this, Login.class);
                            startActivity(i);
                        }
                        else
                        {
                            alertDialog.setMessage(result);
                            alertDialog.show();
                        }
                    }
                }
}
