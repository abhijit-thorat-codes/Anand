package com.example.dummy.anand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Contact extends AppCompatActivity {
TextView mail,site;
    final String to="dailyyuvavarta@gmail.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar=(Toolbar)findViewById(R.id.myBar);
        setSupportActionBar(toolbar);

        mail=(TextView)findViewById(R.id.email);
        site=(TextView)findViewById(R.id.web);
        mail.setClickable(true);
        site.setClickable(true);
        site.setMovementMethod(LinkMovementMethod.getInstance());
        String text="<a href='http://www.yuvavarta.in'>http://www.yuvavarta.in</a>";
        site.setText(Html.fromHtml(text));
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(),"This is Mail!",Toast.LENGTH_SHORT).show();
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, to);
                email.putExtra(Intent.EXTRA_SUBJECT, "Voucher Orders !");
                email.putExtra(Intent.EXTRA_TEXT, "Hello !");
                email.setType("message/rfc822");
                email.setPackage("com.google.android.gm");
                //startActivity(Intent.createChooser(email, "Choose an Email client :"));
                startActivity(email);
            }
        });
    }
}
