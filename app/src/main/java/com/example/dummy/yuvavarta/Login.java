package com.example.dummy.yuvavarta;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Login extends Activity {
    EditText txtUsername, txtPassword;
    Button btnLogin;
    TextView reg;
    SessionManager session;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());
        txtUsername = findViewById(R.id.etUser);
        txtPassword =  findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.login);
        reg = findViewById(R.id.reg);
        reg.setClickable(true);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(haveNetworkConnection()){
                    progressDialog = ProgressDialog.show(Login.this, "Logging In...",
                            "Verifying login details !", true);
                    String username = txtUsername.getText().toString();
                    String password = txtPassword.getText().toString();
                    session.createLoginSession("Daily Yuvavarta", "dailyyuvavarta@gmail.com");
                    BackgroundTask backgroundTask = new BackgroundTask(Login.this);
                    backgroundTask.execute(username, password);
                }
                else
                {
                    Toast.makeText(Login.this,"No Internet Access!",Toast.LENGTH_LONG).show();
                }
                }

        });
    }

    public class BackgroundTask extends AsyncTask<String, Void, String> {
        Context context;
        AlertDialog alertDialog;

        BackgroundTask(Context ctx) {
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String login_url = "http://yuvavarta.com/yuva_login.php";
            try
            {
                String username = params[0];
                String password = params[1];
                //login process
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "Utf-8"));
                String post_data = URLEncoder.encode("username", "Utf-8") + "=" + URLEncoder.encode(username, "Utf-8") + "&" +
                        URLEncoder.encode("password", "Utf-8") + "=" + URLEncoder.encode(password, "Utf-8");
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
        protected void onPreExecute()
        {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login Status !");
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("LogIn Successful !"))
            {
                SharedPreferences sf=getSharedPreferences("userData", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sf.edit();
                ed.putString("username",txtUsername.getText().toString());
                ed.putString("password",txtPassword.getText().toString());
                ed.commit();

                txtUsername.setText("");
                txtPassword.setText("");
                progressDialog.dismiss();
                Toast.makeText(getBaseContext(),"Login Successfull !",Toast.LENGTH_LONG).show();
                fetchData feta = new fetchData(Login.this);
                feta.execute(txtUsername.getText().toString());
               /* Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
*/
            }
            else
            {
                progressDialog.dismiss();
                alertDialog.setMessage(result);
                alertDialog.show();

            }


        }
    }
    public class fetchData extends AsyncTask<String, Void, String>
    {

        public fetchData(Context context) {
            Context ctx = context;
        }
        String result2 = "";
        @Override
        protected String doInBackground(String... params) {
            String fetch_url = "http://yuvavarta.com/yuva_fetch.php";
            try
            {
                String username = params[0];
                URL url2 = new URL(fetch_url);
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) url2.openConnection();
                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection2.setDoInput(true);
                httpURLConnection2.setDoInput(true);
                OutputStream outputStream2 = httpURLConnection2.getOutputStream();
                BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(outputStream2, "Utf-8"));
                String post_data2 = URLEncoder.encode("username", "Utf-8") + "=" + URLEncoder.encode(username, "Utf-8");

                bufferedWriter2.write(post_data2);
                bufferedWriter2.flush();
                bufferedWriter2.close();
                outputStream2.close();

                InputStream inputStream2 = httpURLConnection2.getInputStream();
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2, "iso-8859-1"));
                String line2 = "";
                while ((line2 = bufferedReader2.readLine()) != null) {
                    result2 += line2;
                }
                bufferedReader2.close();
                inputStream2.close();
                httpURLConnection2.disconnect();
                return result2;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result2;
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onPostExecute(String result2) {
            String a[]=result2.split(";");
            Intent intent=new Intent(Login.this,MainActivity.class);
            intent.putExtra("Name",a[0]);
            intent.putExtra("City",a[1]);
            intent.putExtra("Phone",a[2]);
            startActivity(intent);
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}


