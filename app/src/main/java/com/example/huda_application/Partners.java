package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Partners extends AppCompatActivity implements View.OnClickListener {

    private ImageView bcbsButton, stJosephButton, MSUCHMButton, MSUComButton, cfscButton, deltaDentalButton, wsuPharmButton, eliteSmileButton;
    private ImageView familyRehabButton, sayButton, circleSocksButton, iagdButton, michiganMuslimButton, tawheedCenterButton, muslimCenterButton, wccdButton, detroitMercyButton;
    private ImageView bccpButton, esaButton, ferndaleButton, premierButton, msuNursingButton, chamberlainButton, authorityButton, matrixButton, accessButton, lasmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partners);

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
        if (view.getId() == R.id.bcbsLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bcbsm.com/")));

        }
        else if (view.getId() == R.id.stJosephLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.stjoesoakland.org/welcome-to-st-joseph-mercy-oakland")));
        }
        else if (view.getId() == R.id.MSUCHMLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://humanmedicine.msu.edu/")));

        }
        else if (view.getId() == R.id.MSUComLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://com.msu.edu/")));

        }
        else if (view.getId() == R.id.cfscLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://cfsem.org/")));

        }
        else if (view.getId() == R.id.deltaDentalLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.deltadentalmi.com/")));

        }
        else if (view.getId() == R.id.wsuPharmLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://cphs.wayne.edu/")));

        }
        else if (view.getId() == R.id.eliteSmileLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elitesmilecenter.com/")));

        }
        else if (view.getId() == R.id.familyRehabLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://familyrehabcare.com/")));

        }
        else if (view.getId() == R.id.sayLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://saydetroit.org/clinic/")));

        }
        else if (view.getId() == R.id.circleSocksLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.circlesocks.com/mens/shaggy-blink-huda")));

        }
        else if (view.getId() == R.id.iagdLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.iagdmasjid.net/")));

        }
        else if (view.getId() == R.id.michiganMuslimLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mimuslimcouncil.org/")));

        }
        else if (view.getId() == R.id.tawheedCenterLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tawheedcenter.org/")));

        }
        else if (view.getId() == R.id.muslimCenterLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://muslimcenterdetroit.com/")));

        }
        else if (view.getId() == R.id.wccdLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://wcccd.edu/")));

        }
        else if (view.getId() == R.id.detroitMercyLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://dental.udmercy.edu/")));

        }
        else if (view.getId() == R.id.bccpLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://bcccp.org/")));

        }
        else if (view.getId() == R.id.esaLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.eyesurgeonsassoc.com/")));

        }
        else if (view.getId() == R.id.ferndaleLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ferndalefamilypharmacy.com/")));

        }

        else if (view.getId() == R.id.premierLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/pcs-michigan.com/web/home")));

        }
        else if (view.getId() == R.id.msuNursingLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://nursing.msu.edu/")));

        }
        else if (view.getId() == R.id.chamberlainLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.chamberlain.edu/academics/nursing-school/master-of-science-in-nursing")));

        }
        else if (view.getId() == R.id.authorityLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://authorityhealth.org/programs-resources/michigan-medicaid/")));

        }
        else if (view.getId() == R.id.matrixLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.matrixhumanservices.org/")));

        }
        else if (view.getId() == R.id.lasmLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.thelasmc.com/")));

        }
        else if (view.getId() == R.id.accessLogo)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.accesscommunity.org/")));

        }

    }

}