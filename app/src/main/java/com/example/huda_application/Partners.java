package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import java.util.Locale;

public class Partners extends AppCompatActivity implements View.OnClickListener {

    // Declaring all image view variables that will be used in this code
    private ImageView bcbsButton, stJosephButton, MSUCHMButton, MSUComButton, cfscButton, deltaDentalButton, wsuPharmButton, eliteSmileButton;
    private ImageView familyRehabButton, sayButton, circleSocksButton, iagdButton, michiganMuslimButton, tawheedCenterButton, muslimCenterButton, wccdButton, detroitMercyButton;
    private ImageView bccpButton, esaButton, ferndaleButton, premierButton, msuNursingButton, chamberlainButton, authorityButton, matrixButton, accessButton, lasmButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Creating Partners Page
        super.onCreate(savedInstanceState);
        Saved_language();
        setContentView(R.layout.activity_partners);

        // Assigning all variables to their respective ImageViews
        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener((View.OnClickListener) this);
        bcbsButton  = (ImageView) findViewById(R.id.bcbsLogo);
        stJosephButton = (ImageView) findViewById(R.id.stJosephLogo);
        MSUCHMButton = (ImageView) findViewById(R.id.MSUCHMLogo);
        MSUComButton = (ImageView) findViewById(R.id.MSUComLogo);
        cfscButton = (ImageView) findViewById(R.id.cfscLogo);
        deltaDentalButton = (ImageView) findViewById(R.id.deltaDentalLogo);
        wsuPharmButton = (ImageView) findViewById(R.id.wsuPharmLogo);
        eliteSmileButton = (ImageView) findViewById(R.id.eliteSmileLogo);
        familyRehabButton = (ImageView) findViewById(R.id.familyRehabLogo);
        sayButton = (ImageView) findViewById(R.id.sayLogo);
        circleSocksButton = (ImageView) findViewById(R.id.circleSocksLogo);
        iagdButton = (ImageView) findViewById(R.id.iagdLogo);
        michiganMuslimButton = (ImageView) findViewById(R.id.michiganMuslimLogo);
        tawheedCenterButton = (ImageView) findViewById(R.id.tawheedCenterLogo);
        muslimCenterButton = (ImageView) findViewById(R.id.muslimCenterLogo);
        wccdButton = (ImageView) findViewById(R.id.wccdLogo);
        detroitMercyButton = (ImageView) findViewById(R.id.detroitMercyLogo);
        bccpButton = (ImageView) findViewById(R.id.bccpLogo);
        esaButton = (ImageView) findViewById(R.id.esaLogo);
        ferndaleButton = (ImageView) findViewById(R.id.ferndaleLogo);
        //shoresButton = (ImageView) findViewById(R.id.shoresLogo);
        premierButton = (ImageView) findViewById(R.id.premierLogo);
        msuNursingButton = (ImageView) findViewById(R.id.msuNursingLogo);
        chamberlainButton = (ImageView) findViewById(R.id.chamberlainLogo);
        authorityButton = (ImageView) findViewById(R.id.authorityLogo);
        matrixButton= (ImageView) findViewById(R.id.matrixLogo);
        accessButton = (ImageView) findViewById(R.id.accessLogo);
        lasmButton = (ImageView)findViewById(R.id.lasmLogo);

        // Setting each variables onClick listeners
        bcbsButton.setOnClickListener((View.OnClickListener) this);
        stJosephButton.setOnClickListener((View.OnClickListener) this);
        MSUCHMButton.setOnClickListener((View.OnClickListener) this);
        MSUComButton.setOnClickListener((View.OnClickListener) this);
        cfscButton.setOnClickListener((View.OnClickListener) this);
        deltaDentalButton.setOnClickListener((View.OnClickListener) this);
        wsuPharmButton.setOnClickListener((View.OnClickListener) this);
        eliteSmileButton.setOnClickListener((View.OnClickListener) this);
        familyRehabButton.setOnClickListener((View.OnClickListener) this);
        sayButton.setOnClickListener((View.OnClickListener) this);
        circleSocksButton.setOnClickListener((View.OnClickListener) this);
        iagdButton.setOnClickListener((View.OnClickListener) this);
        michiganMuslimButton.setOnClickListener((View.OnClickListener) this);
        muslimCenterButton.setOnClickListener((View.OnClickListener) this);
        wccdButton.setOnClickListener((View.OnClickListener) this);
        detroitMercyButton.setOnClickListener((View.OnClickListener) this);
        bccpButton.setOnClickListener((View.OnClickListener) this);
        esaButton.setOnClickListener((View.OnClickListener) this);
        ferndaleButton.setOnClickListener((View.OnClickListener) this);
        premierButton.setOnClickListener((View.OnClickListener) this);
        msuNursingButton.setOnClickListener((View.OnClickListener) this);
        chamberlainButton.setOnClickListener((View.OnClickListener) this);
        authorityButton.setOnClickListener((View.OnClickListener) this);
        matrixButton.setOnClickListener((View.OnClickListener) this);
        accessButton.setOnClickListener((View.OnClickListener) this);
        lasmButton.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view)
    {
        // Setting the intent for the BCBS logo to redirect to the BCBS website
        if (view.getId() == R.id.bcbsLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bcbsm.com/")));

        }

        // Setting the intent for the St. Joseph logo to redirect to the St. Joseph website
        else if (view.getId() == R.id.stJosephLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.stjoesoakland.org/welcome-to-st-joseph-mercy-oakland")));
        }

        // Setting the intent for the MSUCHM logo to redirect to the MSUCHM website
        else if (view.getId() == R.id.MSUCHMLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://humanmedicine.msu.edu/")));

        }

        // Setting the intent for the MSUCOM logo to redirect to the MSUCOM website
        else if (view.getId() == R.id.MSUComLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://com.msu.edu/")));

        }

        // Setting the intent for the CFCS logo to redirect to the CFCS website
        else if (view.getId() == R.id.cfscLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://cfsem.org/")));

        }

        // Setting the intent for the Delta Dental logo to redirect to the Delta Dental website
        else if (view.getId() == R.id.deltaDentalLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.deltadentalmi.com/")));

        }

        // Setting the intent for the WSU Pharmacy logo to redirect to the WSU Pharmacy website
        else if (view.getId() == R.id.wsuPharmLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://cphs.wayne.edu/")));

        }

        // Setting the intent for the Elite Smile logo to redirect to the Elite Smile website
        else if (view.getId() == R.id.eliteSmileLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elitesmilecenter.com/")));

        }

        // Setting the intent for the Family Rehab logo to redirect to the Family Rehab website
        else if (view.getId() == R.id.familyRehabLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://familyrehabcare.com/")));

        }

        // Setting the intent for the Say logo to redirect to the Say website
        else if (view.getId() == R.id.sayLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://saydetroit.org/clinic/")));

        }

        // Setting the intent for the Circle Socks logo to redirect to the Circle Socks website
        else if (view.getId() == R.id.circleSocksLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.circlesocks.com/mens/shaggy-blink-huda")));

        }

        // Setting the intent for the IAGD logo to redirect to the IAGD website
        else if (view.getId() == R.id.iagdLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.iagdmasjid.net/")));

        }

        // Setting the intent for the Michigan Muslim logo to redirect to the Michigan Muslim website
        else if (view.getId() == R.id.michiganMuslimLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mimuslimcouncil.org/")));

        }

        // Setting the intent for the Tawheed Center logo to redirect to the Tawheed Center website
        else if (view.getId() == R.id.tawheedCenterLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tawheedcenter.org/")));

        }

        // Setting the intent for the Muslim Center logo to redirect to the Muslim Center website
        else if (view.getId() == R.id.muslimCenterLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://muslimcenterdetroit.com/")));

        }

        // Setting the intent for the WCCD logo to redirect to the WCCD website
        else if (view.getId() == R.id.wccdLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://wcccd.edu/")));

        }

        // Setting the intent for the UDM logo to redirect to the UDM website
        else if (view.getId() == R.id.detroitMercyLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://dental.udmercy.edu/")));

        }

        // Setting the intent for the BCCP logo to redirect to the BCCP website
        else if (view.getId() == R.id.bccpLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://bcccp.org/")));

        }

        // Setting the intent for the ESA logo to redirect to the ESA website
        else if (view.getId() == R.id.esaLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.eyesurgeonsassoc.com/")));

        }

        // Setting the intent for the Ferndale logo to redirect to the Ferndale website
        else if (view.getId() == R.id.ferndaleLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ferndalefamilypharmacy.com/")));

        }

        // Setting the intent for the Premier logo to redirect to the Premier website
        else if (view.getId() == R.id.premierLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/pcs-michigan.com/web/home")));

        }

        // Setting the intent for the MSU Nursing logo to redirect to the MSU Nursing website
        else if (view.getId() == R.id.msuNursingLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://nursing.msu.edu/")));

        }

        // Setting the intent for the Chamberlain logo to redirect to the Chamberlain website
        else if (view.getId() == R.id.chamberlainLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.chamberlain.edu/academics/nursing-school/master-of-science-in-nursing")));

        }

        // Setting the intent for the Authority logo to redirect to the Authority website
        else if (view.getId() == R.id.authorityLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://authorityhealth.org/programs-resources/michigan-medicaid/")));

        }

        // Setting the intent for the Matrix logo to redirect to the Matrix website
        else if (view.getId() == R.id.matrixLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.matrixhumanservices.org/")));

        }

        // Setting the intent for the LASM logo to redirect to the LASM website
        else if (view.getId() == R.id.lasmLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.thelasmc.com/")));

        }

        // Setting the intent for the Access logo to redirect to the Access website
        else if (view.getId() == R.id.accessLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.accesscommunity.org/")));

        }

        // Setting the intent for the Back Button logo to redirect to the previous page, Main Application
        else if (view.getId() == R.id.backButton) {
            Intent prev = new Intent(this, MainApplication.class);
            startActivity(prev);
        }

    }
    private void picklanguage(String l)
    {
        SharedPreferences.Editor Saver = getSharedPreferences("langauge", MODE_MULTI_PROCESS).edit();
        Locale language_option =  new Locale(l);
        DisplayMetrics metrics =  getBaseContext().getResources().getDisplayMetrics();                  // Picks the locale after the language is picked from the dialog
        language_swtich(l, metrics, language_option);

        Saver.putString("prev_language" ,l);
        Saver.apply();
    }
    public void language_swtich(String l , DisplayMetrics m , Locale lang) {

        Locale.setDefault(lang);

        Configuration page = new Configuration();                                                   // Switch the language
        page.locale = lang;

        getBaseContext().getResources().updateConfiguration(page, m);

    }


    public void Saved_language(){
        SharedPreferences saved_language =getSharedPreferences("langauge", MODE_MULTI_PROCESS);
        picklanguage(saved_language.getString("prev_language" , ""));
        // choose the saved language from the application
    }

}