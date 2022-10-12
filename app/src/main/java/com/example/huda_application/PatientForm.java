package com.example.huda_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.CloseGuard;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.regex.Pattern;

public class PatientForm extends AppCompatActivity
{
    FirebaseAuth mAuth;

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // date pattern match

    private static final Pattern SSN_PATTERN = Pattern.compile("^(?!666|000|9\\d{2})\\d{3}"
            + "-(?!00)\\d{2}-"
            +"(?!0{4})\\d{4}$");  // SSN pattern match

    private static final Pattern ZIPCODE_PATTERN = Pattern.compile("^\\d{5}$"); // zipcode

    private static final Pattern PHONE_PATTERN = Pattern.compile("^[2-9]\\d{2}-\\d{3}-\\d{4}$"); // pattern to make sure phone numbers are valid format

    private static Pattern PATIENTSEX_PATTERN = Pattern.compile("^(?:m|M|male|Male|f|F|female|Female)$"); // pattern to check patient sex input

    private static Pattern CONTACTPREF_PATTERN = Pattern.compile("^(?:home|Home|mobile|Mobile)$");

    private static Pattern CONSENT_PATTERN = Pattern.compile("^(?:yes|Yes|No|no)$");

    private static Pattern MARITAL_PATTERN = Pattern.compile("^(?:Single|single|Married|married|partner|Partner|Separated|separated|Divorced|divorced|Widowed|widowed)$");


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);

        mAuth =FirebaseAuth.getInstance();

        // edittext
        final EditText todayDate = findViewById(R.id.todaysDate);
        final EditText visitReason = findViewById(R.id.visitReason);
        final EditText firstName = findViewById(R.id.firstName);
        final EditText lastName = findViewById(R.id.lastName);
        final EditText patientSex = findViewById(R.id.patientSex);
        final EditText patientSsn = findViewById(R.id.patientSSN);
        final EditText patientDOB = findViewById(R.id.patientDob);
        final EditText patientHome = findViewById(R.id.patientHomeNum);
        final EditText patientCell = findViewById(R.id.patientCellNum);
        final EditText patientAdd = findViewById(R.id.patientAddress);
        final EditText patientCity = findViewById(R.id.patientCity);
        final EditText patientState = findViewById(R.id.patientState);
        final EditText patientZip = findViewById(R.id.patientZipCode);
        final EditText patientContactPref = findViewById(R.id.patientPreferredNumber);
        final EditText patientConsentCall = findViewById(R.id.consentToCall);
        final EditText patientConsentText = findViewById(R.id.consentToText);
        final EditText patientInsurance = findViewById(R.id.insuranceProvider);
        final EditText patientEmail = findViewById(R.id.emailAddress);
        final EditText prefLang = findViewById(R.id.langPref);
        final EditText neededTranslator = findViewById(R.id.translatorNeeded);
        final EditText maritalStatusPatient = findViewById(R.id.maritalStatus);
        final EditText incomeHousehold = findViewById(R.id.income);
        final EditText houseHoldSize = findViewById(R.id.houseHold);
        final EditText occupationPatient = findViewById(R.id.occupation);
        final EditText veteranStatus = findViewById(R.id.veteran);
        final EditText emergencyContactName = findViewById(R.id.emergencyName);
        final EditText relationshipToPatient = findViewById(R.id.relationship);
        final EditText emergencyContactPhone = findViewById(R.id.emergencyNumber);
        final EditText patientNameConsent = findViewById(R.id.patientNameFill);
        final EditText patientSigned = findViewById(R.id.patientNamePrinted);
        final EditText patientSig = findViewById(R.id.patientNameSignature);
        final EditText consentDateSign = findViewById(R.id.consentDate);


        // checkboxes
        final CheckBox blackAfricanAmerican = findViewById(R.id.blackAfrican);
        final CheckBox whiteCaucasian = findViewById(R.id.whiteCaucasian);
        final CheckBox asianPatient = findViewById(R.id.asian);
        final CheckBox middleEasternNA = findViewById(R.id.middleEastern);
        final CheckBox hispanicLatinoPatient = findViewById(R.id.hispanicLatino);
        final CheckBox nativeAmericanPatient = findViewById(R.id.nativeAmerican);
        final CheckBox nativeHawiianPacificIslander = findViewById(R.id.nativeHawiianPacific);
        final CheckBox HispanicOrLatino = findViewById(R.id.hispanicOrLatino);
        final CheckBox NotHispanicOrLatino = findViewById(R.id.NotHispanicOrLatino);
        final CheckBox weekly = findViewById(R.id.week);
        final CheckBox biWeekly = findViewById(R.id.twoWeek);
        final CheckBox monthly = findViewById(R.id.month);
        final CheckBox yearly = findViewById(R.id.year);
        final CheckBox unEmp = findViewById(R.id.unemployed);
        final CheckBox empFull = findViewById(R.id.employedFt);
        final CheckBox empPart = findViewById(R.id.employedPt);
        final CheckBox empSelf = findViewById(R.id.selfEmployed);
        final CheckBox empStudent = findViewById(R.id.student);
        final CheckBox empRetired = findViewById(R.id.retired);
        final CheckBox empSeek = findViewById(R.id.seekingEmployment);

        Button buttonSubmit = findViewById(R.id.submitPatient); // set variable for button action

        buttonSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final String dateTxt = todayDate.getText().toString().trim(); // convert the EditText to a String type
                final String visitReasonTxt = visitReason.getText().toString().trim();
                final String firstNameTxt = firstName.getText().toString().trim();
                final String lastNameTxt = lastName.getText().toString().trim();
                final String patientSexTxt = patientSex.getText().toString().trim();
                final String patientSSNTxt = patientSsn.getText().toString().trim();
                final String patientDOBTxt = patientDOB.getText().toString().trim();
                final String patientHomeNumTxt = patientHome.getText().toString().trim();
                final String patientCellNumTxt = patientCell.getText().toString().trim();
                final String patientAddTxt = patientAdd.getText().toString().trim();
                final String patientCityTxt = patientCity.getText().toString().trim();
                final String patientStateTxt = patientState.getText().toString().trim();
                final String patientZipCodeTxt = patientZip.getText().toString().trim();
                final String patientPrefNumberTxt = patientContactPref.getText().toString().trim();
                final String patientConsentCallTxt = patientConsentCall.getText().toString().trim();
                final String patientConsentTextTxt = patientConsentText.getText().toString().trim();
                final String patientInsuranceTxt = patientInsurance.getText().toString().trim();
                final String patientEmailTxt = patientEmail.getText().toString().trim();
                final String prefLangTxt = prefLang.getText().toString().trim();
                final String translatorTxt = neededTranslator.getText().toString().trim();
                final String maritalTxt = maritalStatusPatient.getText().toString().trim();
                final String houseIncomeTxt = incomeHousehold.getText().toString().trim();
                final String houseHoldTxt = houseHoldSize.getText().toString().trim();
                final String occupationTxt = occupationPatient.getText().toString().trim();
                final String veteranTxt = veteranStatus.getText().toString().trim();
                final String emergencyNameTxt = emergencyContactName.getText().toString().trim();
                final String relationshipTxt = relationshipToPatient.getText().toString().trim();
                final String contactPhoneTxt = emergencyContactPhone.getText().toString().trim();
                final String patientConsentName = patientNameConsent.getText().toString().trim();
                final String patientSignedText = patientSigned.getText().toString().trim();
                final String patientSignatureText = patientSig.getText().toString().trim();
                final String consentDateTxt = consentDateSign.getText().toString().trim();

                // information from checkbox input to store in a StringBuilder
                StringBuilder patientRace = new StringBuilder();
                StringBuilder patientEthnicity = new StringBuilder();
                StringBuilder patientIncome = new StringBuilder();
                StringBuilder patientEmp = new StringBuilder();


                if (TextUtils.isEmpty(dateTxt) || !DATE_PATTERN.matcher(dateTxt).matches() ) // check if date is empty
                {
                    Toast.makeText(PatientForm.this,"Must be mm-dd-yyyy",Toast.LENGTH_LONG).show();
                    todayDate.setError("Date format is required");
                    todayDate.requestFocus();
                }
                else if(TextUtils.isEmpty(visitReasonTxt)) // check if visit reason is empty
                {
                    Toast.makeText(PatientForm.this,"Please enter a visit reason",Toast.LENGTH_LONG).show();
                    visitReason.setError("Visit reason is required");
                    visitReason.requestFocus();
                }
                else if(TextUtils.isEmpty(firstNameTxt)) // check if name is empty
                {
                    Toast.makeText(PatientForm.this,"Please enter first name",Toast.LENGTH_LONG).show();
                    firstName.setError("Name is required");
                    firstName.requestFocus();

                }
                else if(TextUtils.isEmpty(lastNameTxt))
                {
                    Toast.makeText(PatientForm.this,"Please enter last name",Toast.LENGTH_LONG).show();
                    lastName.setError("Name is required");
                    lastName.requestFocus();
                }
                else if(TextUtils.isEmpty(patientSexTxt))
                {
                    Toast.makeText(PatientForm.this,"Please enter a sex (Male or Female)",Toast.LENGTH_LONG).show();
                    patientSex.setError("Sex is required");
                    patientSex.requestFocus();
                }
                else if(!PATIENTSEX_PATTERN.matcher(patientSexTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Please enter a sex (Male or Female)",Toast.LENGTH_LONG).show();
                    patientSex.setError("Sex is required");
                    patientSex.requestFocus();
                }
                else if(TextUtils.isEmpty(patientDOBTxt))
                {
                    Toast.makeText(PatientForm.this,"Please enter a Date of birth",Toast.LENGTH_LONG).show();
                }
                else if(!DATE_PATTERN.matcher(patientDOBTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Must be mm-dd-yyyy",Toast.LENGTH_LONG).show();
                    patientDOB.setError("Format required");
                    patientDOB.requestFocus();
                }
                else if(TextUtils.isEmpty(patientAddTxt))
                {
                    Toast.makeText(PatientForm.this,"Address cannot be empty",Toast.LENGTH_LONG).show();
                    patientAdd.setError("Address required");
                    patientAdd.requestFocus();
                }
                else if(TextUtils.isEmpty(patientCityTxt))
                {
                    Toast.makeText(PatientForm.this,"City cannot be empty",Toast.LENGTH_LONG).show();
                    patientCity.setError("City required");
                    patientCity.requestFocus();
                }
                else if(TextUtils.isEmpty(patientStateTxt))
                {
                    Toast.makeText(PatientForm.this,"State cannot be empty",Toast.LENGTH_LONG).show();
                    patientState.setError("Address required");
                    patientState.requestFocus();
                }
                else if(TextUtils.isEmpty(patientZipCodeTxt))
                {
                    Toast.makeText(PatientForm.this,"Zipcode cannot be empty",Toast.LENGTH_LONG).show();
                    patientZip.setError("Zipcode required");
                    patientZip.requestFocus();
                }
                else if(!ZIPCODE_PATTERN.matcher(patientZipCodeTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Zipcode must be 12345",Toast.LENGTH_LONG).show();
                    patientZip.setError("Format required");
                    patientZip.requestFocus();
                }
                else if(TextUtils.isEmpty(patientSSNTxt))
                {
                    Toast.makeText(PatientForm.this,"Please enter a SSN",Toast.LENGTH_LONG).show();
                }
                else if(!SSN_PATTERN.matcher(patientSSNTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Must be 123-12-1234",Toast.LENGTH_LONG).show();
                    patientSsn.setError("Format required");
                    patientSsn.requestFocus();
                }
                else if(TextUtils.isEmpty(patientCellNumTxt))
                {
                    Toast.makeText(PatientForm.this,"Number cannot be empty",Toast.LENGTH_LONG).show();
                    patientCell.setError("Cell number required");
                    patientCell.requestFocus();
                }
                else if(!PHONE_PATTERN.matcher(patientCellNumTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Number must be 123-123-1234",Toast.LENGTH_LONG).show();
                    patientCell.setError("Format is required");
                    patientCell.requestFocus();
                }
                else if(TextUtils.isEmpty(patientHomeNumTxt))
                {
                    Toast.makeText(PatientForm.this,"Number cannot be empty",Toast.LENGTH_LONG).show();
                    patientHome.setError("Home phone number required");
                    patientHome.requestFocus();
                }
                else if(!PHONE_PATTERN.matcher(patientHomeNumTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Number must be 123-123-1234",Toast.LENGTH_LONG).show();
                    patientHome.setError("Format is required");
                    patientHome.requestFocus();
                }
                else if(TextUtils.isEmpty(patientPrefNumberTxt))
                {
                    Toast.makeText(PatientForm.this,"Preference cannot be empty",Toast.LENGTH_LONG).show();
                    patientContactPref.setError("Preference is required");
                    patientContactPref.requestFocus();
                }
                else if(!CONTACTPREF_PATTERN.matcher(patientPrefNumberTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Preference must be Home or Mobile",Toast.LENGTH_LONG).show();
                    patientContactPref.setError("Preference is required");
                    patientContactPref.requestFocus();
                }
                else if(TextUtils.isEmpty(patientConsentCallTxt))
                {
                    Toast.makeText(PatientForm.this,"Consent cannot be empty",Toast.LENGTH_LONG).show();
                    patientConsentCall.setError("Consent is required");
                    patientConsentCall.requestFocus();
                }else if(!CONSENT_PATTERN.matcher(patientConsentCallTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Must be Yes or No",Toast.LENGTH_LONG).show();
                    patientConsentCall.setError("Consent is required");
                    patientConsentCall.requestFocus();
                }
                else if(TextUtils.isEmpty(patientConsentTextTxt))
                {
                    Toast.makeText(PatientForm.this,"Consent cannot be empty",Toast.LENGTH_LONG).show();
                    patientConsentText.setError("Consent is required");
                    patientConsentText.requestFocus();
                }
                else if(!CONSENT_PATTERN.matcher(patientConsentTextTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Must be Yes or No",Toast.LENGTH_LONG).show();
                    patientConsentText.setError("Consent is required");
                    patientConsentText.requestFocus();
                }
                else if(TextUtils.isEmpty(patientInsuranceTxt))
                {
                    Toast.makeText(PatientForm.this,"Insurance must be filled",Toast.LENGTH_LONG).show();
                    patientInsurance.setError("Insurance is required");
                    patientInsurance.requestFocus();
                }
                else if(TextUtils.isEmpty(patientEmailTxt))
                {
                    Toast.makeText(PatientForm.this,"Email cannot be empty",Toast.LENGTH_LONG).show();
                    patientEmail.setError("Email is required");
                    patientEmail.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(patientEmailTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Must be a valid email",Toast.LENGTH_LONG).show();
                    patientEmail.setError("Email is required");
                    patientEmail.requestFocus();
                }
                else if(blackAfricanAmerican.isChecked())
                {
                    patientRace.append("Black/African American");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(whiteCaucasian.isChecked())
                {
                    patientRace.append("White/Caucasian");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(asianPatient.isChecked())
                {
                    patientRace.append("Asian");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(middleEasternNA.isChecked())
                {
                    patientRace.append("Middle Eastern/North African");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(hispanicLatinoPatient.isChecked())
                {
                    patientRace.append("Hispanic/Latino");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(nativeAmericanPatient.isChecked())
                {
                    patientRace.append("Native American");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(nativeHawiianPacificIslander.isChecked())
                {
                    patientRace.append("Native Hawaiian/Pacific Islander");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(HispanicOrLatino.isChecked())
                {
                    patientEthnicity.append("Hispanic or Latino/a");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(NotHispanicOrLatino.isChecked())
                {
                    patientEthnicity.append("Not Hispanic or Latino/a");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(prefLangTxt))
                {
                    Toast.makeText(PatientForm.this,"Language cannot be empty",Toast.LENGTH_LONG).show();
                    prefLang.setError("Language is required");
                    prefLang.requestFocus();
                }
                else if(TextUtils.isEmpty(translatorTxt))
                {
                    Toast.makeText(PatientForm.this,"Translator cannot be empty",Toast.LENGTH_LONG).show();
                    neededTranslator.setError("Translator need is required");
                    neededTranslator.requestFocus();
                }
                else if(!CONSENT_PATTERN.matcher(translatorTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Must be Yes or No",Toast.LENGTH_LONG).show();
                    neededTranslator.setError("Translator need is required");
                    neededTranslator.requestFocus();
                }
                else if(!MARITAL_PATTERN.matcher(maritalTxt).matches() || TextUtils.isEmpty(maritalTxt))
                {
                    Toast.makeText(PatientForm.this,"Must fill in marital status",Toast.LENGTH_LONG).show();
                    maritalStatusPatient.setError("Marital Status is required");
                    maritalStatusPatient.requestFocus();
                }
                else if(TextUtils.isEmpty(houseIncomeTxt))
                {
                    Toast.makeText(PatientForm.this,"Must fill in household income",Toast.LENGTH_LONG).show();
                    incomeHousehold.setError("Income is required");
                    incomeHousehold.requestFocus();
                }
                else if(weekly.isChecked())
                {
                    patientIncome.append("Week");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(biWeekly.isChecked())
                {
                    patientIncome.append("2 Weeks");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(monthly.isChecked())
                {
                    patientIncome.append("Month");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(yearly.isChecked())
                {
                    patientIncome.append("Year");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(houseHoldTxt))
                {
                    Toast.makeText(PatientForm.this,"Must fill in Family size",Toast.LENGTH_LONG).show();
                    houseHoldSize.setError("Household size is required");
                    houseHoldSize.requestFocus();
                }
                else if(unEmp.isChecked())
                {
                    patientEmp.append("Unemployed");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(empFull.isChecked())
                {
                    patientEmp.append("Employed (Full time)");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(empPart.isChecked())
                {
                    patientEmp.append("Employed (Part time)");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(empSelf.isChecked())
                {
                    patientEmp.append("Self-employed");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(empStudent.isChecked())
                {
                    patientEmp.append("Student");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(empRetired.isChecked())
                {
                    patientEmp.append("Retired");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(empSeek.isChecked())
                {
                    patientEmp.append("Seeking employment");
                    Toast.makeText(PatientForm.this,"input recognized",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(occupationTxt))
                {
                    Toast.makeText(PatientForm.this,"Please fill occupation",Toast.LENGTH_LONG).show();
                    occupationPatient.setError("Occupation is required");
                    occupationPatient.requestFocus();
                }
                else if(!CONSENT_PATTERN.matcher(veteranTxt).matches() || TextUtils.isEmpty(veteranTxt))
                {
                    Toast.makeText(PatientForm.this,"Must be Yes or No",Toast.LENGTH_LONG).show();
                    veteranStatus.setError("Veteran status is required");
                    veteranStatus.requestFocus();
                }
                else if(TextUtils.isEmpty(emergencyNameTxt))
                {
                    Toast.makeText(PatientForm.this,"Name cannot be empty",Toast.LENGTH_LONG).show();
                    emergencyContactName.setError("Contact name is required");
                    emergencyContactName.requestFocus();
                }
                else if(TextUtils.isEmpty(relationshipTxt))
                {
                    Toast.makeText(PatientForm.this,"Relationship cannot be empty",Toast.LENGTH_LONG).show();
                    relationshipToPatient.setError("Contact relationship is required");
                    relationshipToPatient.requestFocus();
                }
                else if(TextUtils.isEmpty(contactPhoneTxt) || !PHONE_PATTERN.matcher(contactPhoneTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Contact phone must be filled",Toast.LENGTH_LONG).show();
                    emergencyContactPhone.setError("Contact number is required");
                    emergencyContactPhone.requestFocus();
                }
                else if(TextUtils.isEmpty(patientConsentName))
                {
                    Toast.makeText(PatientForm.this,"Name must be filled",Toast.LENGTH_LONG).show();
                    patientNameConsent.setError("Name is required");
                    patientNameConsent.requestFocus();
                }
                else if(TextUtils.isEmpty(patientSignedText))
                {
                    Toast.makeText(PatientForm.this,"Name must be filled",Toast.LENGTH_LONG).show();
                    patientSigned.setError("Signed name is required");
                    patientSigned.requestFocus();
                }
                else if(TextUtils.isEmpty(patientSignatureText))
                {
                    Toast.makeText(PatientForm.this,"Signature must be filled",Toast.LENGTH_LONG).show();
                    patientSig.setError("Signature is required");
                    patientSig.requestFocus();
                }
                else if(TextUtils.isEmpty(consentDateTxt) || !DATE_PATTERN.matcher(consentDateTxt).matches())
                {
                    Toast.makeText(PatientForm.this,"Date must be filled",Toast.LENGTH_LONG).show();
                    consentDateSign.setError("Date is required");
                    consentDateSign.requestFocus();
                }
                else
                {
                    Toast.makeText(PatientForm.this,"Success!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
//    final String consentDateTxt = consentDateSign.getText().toString().trim();