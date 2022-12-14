package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huda_application.user.PatientFormData;
import com.example.huda_application.user.UserManager;
import com.example.huda_application.patientInsurance;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

public class PatientForm extends AppCompatActivity
{

    private EditText todayDate;
    private EditText consentDate;
    String patientRaceTxt = "";
    String patientEthnicityTxt = "";
    String patientIncomeTxt = "";
    String patientEmpTxt = "";

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // date pattern match

    private static final Pattern SSN_PATTERN = Pattern.compile("^(?!666|000|9\\d{2})\\d{3}"
            + "-(?!00)\\d{2}-"
            + "(?!0{4})\\d{4}$");  // SSN pattern match

    private static final Pattern ZIPCODE_PATTERN = Pattern.compile("^\\d{5}$"); // zipcode

    private static final Pattern PHONE_PATTERN = Pattern.compile("^[2-9]\\d{2}-\\d{3}-\\d{4}$"); // pattern to make sure phone numbers are valid format

    private static Pattern PATIENTSEX_PATTERN = Pattern.compile("^(?:m|M|male|Male|f|F|female|Female)$"); // pattern to check patient sex input

    private static Pattern CONTACTPREF_PATTERN = Pattern.compile("^(?:home|Home|mobile|Mobile)$");

    private static Pattern CONSENT_PATTERN = Pattern.compile("^(?:yes|Yes|No|no)$");

    private static Pattern INSURANCE_PATTERN = Pattern.compile("^(?:NA|na|Na)$");

    private static Pattern MARITAL_PATTERN = Pattern.compile("^(?:Single|single|Married|married|partner|Partner|Separated|separated|Divorced|divorced|Widowed|widowed)$");

    private static Pattern LETTERS_PATTERN = Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$"); // Letters pattern match

    private static Pattern DIGITS_PATTERN = Pattern.compile("[0-9]+");

    private static Pattern MONEY_PATTERN = Pattern.compile("^\\$(([1-9]\\d{0,2}(,\\d{3})*)|(([1-9]\\d*)?\\d))(\\.\\d\\d)?$");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);


        DAOInsurance dao = new DAOInsurance(); // new DAOInsurance object

        EditText todayDate = findViewById(R.id.todaysDate);
        todayDate.addTextChangedListener(new TextWatcher()  // function that autofills the '-' for todayDate text field
        {
            int prevL = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                prevL = todayDate.getText().toString().length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                int length = editable.length();
                if ((prevL < length) && (length == 2 || length == 5))
                {
                    editable.append("-");
                }
            }
        });

        EditText patientDOB = findViewById(R.id.patientDob);
        patientDOB.addTextChangedListener(new TextWatcher()
        {  // function that autofills the '-' for patientDOB text field
            int prevL = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                prevL = patientDOB.getText().toString().length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                int length = editable.length();
                if ((prevL < length) && (length == 2 || length == 5))
                {
                    editable.append("-");
                }
            }
        });

        EditText consentDate = findViewById(R.id.consentDate);
        consentDate.addTextChangedListener(new TextWatcher()  // function that autofills the '-' for consentDate text field
        {
            int prevL = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                prevL = consentDate.getText().toString().length();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                int length = editable.length();
                if ((prevL < length) && (length == 2 || length == 5))
                {
                    editable.append("-");
                }
            }
        });

        // checkboxes
        CheckBox blackAfricanAmerican = findViewById(R.id.blackAfrican);
        CheckBox whiteCaucasian = findViewById(R.id.whiteCaucasian);
        CheckBox asianPatient = findViewById(R.id.asian);
        CheckBox middleEasternNA = findViewById(R.id.middleEastern);
        CheckBox hispanicLatinoPatient = findViewById(R.id.hispanicLatino);
        CheckBox nativeAmericanPatient = findViewById(R.id.nativeAmerican);
        CheckBox nativeHawiianPacificIslander = findViewById(R.id.nativeHawiianPacific);
        CheckBox HispanicOrLatino = findViewById(R.id.hispanicOrLatino);
        CheckBox NotHispanicOrLatino = findViewById(R.id.NotHispanicOrLatino);
        CheckBox weekly = findViewById(R.id.week);
        CheckBox biWeekly = findViewById(R.id.twoWeek);
        CheckBox monthly = findViewById(R.id.month);
        CheckBox yearly = findViewById(R.id.year);
        CheckBox unEmp = findViewById(R.id.unemployed);
        CheckBox empFull = findViewById(R.id.employedFt);
        CheckBox empPart = findViewById(R.id.employedPt);
        CheckBox empSelf = findViewById(R.id.selfEmployed);
        CheckBox empStudent = findViewById(R.id.student);
        CheckBox empRetired = findViewById(R.id.retired);
        CheckBox empSeek = findViewById(R.id.seekingEmployment);
        
        Button nextFormButton = findViewById(R.id.nextForm); // set variable for button action
        nextFormButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) // om click method
            {
                // setting the EditText variable equal to the ID from the PatientForm XML
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


                // convert the EditText to a String type
                final String dateTxt = todayDate.getText().toString().trim();
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

                // patientInsurance object that takes all the string parameters up to the insurance information
                com.example.huda_application.patientInsurance info = new patientInsurance(dateTxt,visitReasonTxt,lastNameTxt,firstNameTxt,patientSexTxt,
                        patientDOBTxt,patientAddTxt,patientCityTxt,patientStateTxt,patientZipCodeTxt,
                        patientSSNTxt,patientHomeNumTxt,patientCellNumTxt,patientPrefNumberTxt,patientConsentCallTxt,patientConsentTextTxt,patientInsuranceTxt);

                // if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                if (TextUtils.isEmpty(dateTxt))
                {
                    Toast.makeText(PatientForm.this, "Must be mm-dd-yyyy", Toast.LENGTH_LONG).show();
                    todayDate.setError("Date format is required");
                    todayDate.requestFocus();
                }
                // Else if statement to check if the string matches the DATE_PATTERN and if it does not,
                // another error will be thrown
                else if(!DATE_PATTERN.matcher(dateTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Date cannot be empty", Toast.LENGTH_LONG).show();
                    todayDate.setError("Today's date is required");
                    todayDate.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(visitReasonTxt)) // check if visit reason is empty and too long
                {
                    Toast.makeText(PatientForm.this, "Please enter a visit reason", Toast.LENGTH_LONG).show();
                    visitReason.setError("Visit reason is required");
                    visitReason.requestFocus();
                }
                // Else if statement to also check if the string length is less than 30 characters, if > 30 then
                // another error will be thrown
                else if (visitReasonTxt.length() > 30) // check if visit reason is empty and too long
                {
                    Toast.makeText(PatientForm.this, "Character limit must be < 30", Toast.LENGTH_LONG).show();
                    visitReason.setError("Max character limit required");
                    visitReason.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(firstNameTxt)) // check if name is empty
                {
                    Toast.makeText(PatientForm.this, "Please enter first name", Toast.LENGTH_LONG).show();
                    firstName.setError("Name is required");
                    firstName.requestFocus();

                }
                // Else if statement to also check if the string length is less than 20 characters, if > 20 then
                // another error will be thrown
                else if (firstNameTxt.length() > 20) // check if name is empty
                {
                    Toast.makeText(PatientForm.this, "Character limit must be < 20", Toast.LENGTH_LONG).show();
                    firstName.setError("Max character limit required");
                    firstName.requestFocus();

                }
                // else if statement that uses LETTERS_PATTERN to check if the string follows the pattern
                // if it does not, an error will be displayed.
                else if(!LETTERS_PATTERN.matcher(firstNameTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Name can only contain letters", Toast.LENGTH_LONG).show();
                    firstName.setError("Name format is required");
                    firstName.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(lastNameTxt))
                {
                    Toast.makeText(PatientForm.this, "Please enter last name", Toast.LENGTH_LONG).show();
                    lastName.setError("Name is required");
                    lastName.requestFocus();
                }
                // Else if statement to check if the string length is less than 20 characters, if > 20 then
                // another error will be thrown
                else if (lastNameTxt.length() > 20)
                {
                    Toast.makeText(PatientForm.this, "Character limit must be < 20", Toast.LENGTH_LONG).show();
                    lastName.setError("Max Character limit required");
                    lastName.requestFocus();
                }
                // else if statement that uses LETTERS_PATTERN to check if the string follows the pattern
                // if it does not, an error will be displayed.
                else if(!LETTERS_PATTERN.matcher(lastNameTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Name can only contain letters", Toast.LENGTH_LONG).show();
                    lastName.setError("Name format is required");
                    lastName.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientSexTxt))
                {
                    Toast.makeText(PatientForm.this, "Please enter a sex (Male or Female)", Toast.LENGTH_LONG).show();
                    patientSex.setError("Sex is required");
                    patientSex.requestFocus();
                }
                // else if statement that checks if the string follows the PATIENTSEX_PATTERN,
                // if it does not, then an error will be displayed
                else if (!PATIENTSEX_PATTERN.matcher(patientSexTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Please enter a sex (Male or Female)", Toast.LENGTH_LONG).show();
                    patientSex.setError("Sex pattern is required");
                    patientSex.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientDOBTxt))
                {
                    Toast.makeText(PatientForm.this, "Please enter a Date of birth", Toast.LENGTH_LONG).show();
                    patientDOB.setError("Date of birth is required");
                    patientDOB.requestFocus();
                }
                // else if statement that checks if the string follows the DATE_PATTERN,
                // if it does not, then an error will be displayed
                else if (!DATE_PATTERN.matcher(patientDOBTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Must be MM-DD-YYYY", Toast.LENGTH_LONG).show();
                    patientDOB.setError("Date format is required");
                    patientDOB.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientAddTxt))
                {
                    Toast.makeText(PatientForm.this, "Address cannot be empty", Toast.LENGTH_LONG).show();
                    patientAdd.setError("Address is required");
                    patientAdd.requestFocus();
                }
                // Else if statement to check if the string length is less than 30 characters, if > 30 then
                // another error will be thrown
                else if (patientAddTxt.length() > 30)
                {
                    Toast.makeText(PatientForm.this, "Character limit must be < 30", Toast.LENGTH_LONG).show();
                    patientAdd.setError("Max Character limit required");
                    patientAdd.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientCityTxt))
                {
                    Toast.makeText(PatientForm.this, "City cannot be empty", Toast.LENGTH_LONG).show();
                    patientCity.setError("City is required");
                    patientCity.requestFocus();
                }
                // Else if statement to check if the string length is less than 20 characters, if > 20 then
                // another error will be thrown
                else if (patientCityTxt.length() > 20)
                {
                    Toast.makeText(PatientForm.this, "Character limit must be < 20", Toast.LENGTH_LONG).show();
                    patientCity.setError("Max Character limit required");
                    patientCity.requestFocus();
                }
                    // else if statement that checks if the string follows the LETTERS_PATTERN,
                // if it does not, then an error will be displayed
                else if(!LETTERS_PATTERN.matcher(patientCityTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "City must be alphabetic", Toast.LENGTH_LONG).show();
                    patientCity.setError("City name format is required");
                    patientCity.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed
                else if (TextUtils.isEmpty(patientStateTxt))
                {
                    Toast.makeText(PatientForm.this, "State cannot be empty", Toast.LENGTH_LONG).show();
                    patientState.setError("State is required");
                    patientState.requestFocus();
                }
                // Else if statement to check if the string length is less than 20 characters, if > 20 then
                // another error will be thrown
                else if (patientStateTxt.length() > 20)
                {
                    Toast.makeText(PatientForm.this, "Character limit must be < 20", Toast.LENGTH_LONG).show();
                    patientState.setError("Max Character limit required");
                    patientState.requestFocus();
                }
                // else if statement that checks if the string follows the LETTERS_PATTERN,
                // if it does not, then an error will be displayed
                else if(!LETTERS_PATTERN.matcher(patientStateTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "State must be valid", Toast.LENGTH_LONG).show();
                    patientState.setError("State name format is required");
                    patientState.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientZipCodeTxt))
                {
                    Toast.makeText(PatientForm.this, "Zipcode cannot be empty", Toast.LENGTH_LONG).show();
                    patientZip.setError("Zipcode is required");
                    patientZip.requestFocus();
                }
                // else if statement that checks if the string follows the ZIPCODE_PATTERN,
                // if it does not, then an error will be displayed
                else if (!ZIPCODE_PATTERN.matcher(patientZipCodeTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Zipcode must be 12345", Toast.LENGTH_LONG).show();
                    patientZip.setError("Zipcode Format is required");
                    patientZip.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientSSNTxt))
                {
                    Toast.makeText(PatientForm.this, "Please enter a SSN", Toast.LENGTH_LONG).show();
                }
                // else if statement that checks if the string follows the SSN_PATTERN,
                // if it does not, then an error will be displayed
                else if (!SSN_PATTERN.matcher(patientSSNTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Must be 123-12-1234", Toast.LENGTH_LONG).show();
                    patientSsn.setError("SSN format is required");
                    patientSsn.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientCellNumTxt))
                {
                    Toast.makeText(PatientForm.this, "Number cannot be empty", Toast.LENGTH_LONG).show();
                    patientCell.setError("Cell number is required");
                    patientCell.requestFocus();
                }
                // else if statement that checks if the string follows the PHONE_PATTERN,
                // if it does not, then an error will be displayed
                else if (!PHONE_PATTERN.matcher(patientCellNumTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Number must be 123-123-1234", Toast.LENGTH_LONG).show();
                    patientCell.setError("Phone number format is required");
                    patientCell.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientHomeNumTxt))
                {
                    Toast.makeText(PatientForm.this, "Number cannot be empty", Toast.LENGTH_LONG).show();
                    patientHome.setError("Home phone number is required");
                    patientHome.requestFocus();
                }
                // else if statement that checks if the string follows the PHONE_PATTERN,
                // if it does not, then an error will be displayed
                else if (!PHONE_PATTERN.matcher(patientHomeNumTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Number must be 123-123-1234", Toast.LENGTH_LONG).show();
                    patientHome.setError("Phone number format is required");
                    patientHome.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientPrefNumberTxt))
                {
                    Toast.makeText(PatientForm.this, "Preference cannot be empty", Toast.LENGTH_LONG).show();
                    patientContactPref.setError("Preferred number is required");
                    patientContactPref.requestFocus();
                }
                // else if statement that checks if the string follows the PHONE_PATTERN,
                // if it does not, then an error will be displayed
                else if (!CONTACTPREF_PATTERN.matcher(patientPrefNumberTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Preference must be Home or Mobile", Toast.LENGTH_LONG).show();
                    patientContactPref.setError("Preference option format is required");
                    patientContactPref.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientConsentCallTxt))
                {
                    Toast.makeText(PatientForm.this, "Consent cannot be empty", Toast.LENGTH_LONG).show();
                    patientConsentCall.setError("Consent is required");
                    patientConsentCall.requestFocus();
                }
                // else if statement that checks if the string follows the CONSENT_PATTERN,
                // if it does not, then an error will be displayed
                else if (!CONSENT_PATTERN.matcher(patientConsentCallTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Must be Yes or No", Toast.LENGTH_LONG).show();
                    patientConsentCall.setError("Consent format is required");
                    patientConsentCall.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientConsentTextTxt))
                {
                    Toast.makeText(PatientForm.this, "Consent cannot be empty", Toast.LENGTH_LONG).show();
                    patientConsentText.setError("Consent is required");
                    patientConsentText.requestFocus();
                }
                // else if statement that checks if the string follows the CONSENT_PATTERN,
                // if it does not, then an error will be displayed
                else if (!CONSENT_PATTERN.matcher(patientConsentTextTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Must be Yes or No", Toast.LENGTH_LONG).show();
                    patientConsentText.setError("Consent is required");
                    patientConsentText.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientInsuranceTxt))
                {
                    Toast.makeText(PatientForm.this, "Insurance must be filled", Toast.LENGTH_LONG).show();
                    patientInsurance.setError("Insurance is required");
                    patientInsurance.requestFocus();
                }
                // Else if statement to check if the string length is less than 30 characters, if > 30 then
                // another error will be thrown
                else if (patientInsuranceTxt.length() > 30)
                {
                    Toast.makeText(PatientForm.this, "Character limit must be < 30", Toast.LENGTH_LONG).show();
                    patientInsurance.setError("Max Character limit required");
                    patientInsurance.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientEmailTxt))
                {
                    Toast.makeText(PatientForm.this, "Email cannot be empty", Toast.LENGTH_LONG).show();
                    patientEmail.setError("Email is required");
                    patientEmail.requestFocus();
                }
                // else if statement that checks if the string follows the pattern within the block and
                // if it does not, then an error will be displayed
                else if (!Patterns.EMAIL_ADDRESS.matcher(patientEmailTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Must be a valid email", Toast.LENGTH_LONG).show();
                    patientEmail.setError("Email format is required");
                    patientEmail.requestFocus();
                }
                // if statements to check if selected race CheckBox was clicked,
                // if yes then set patientRacTxt to the selected race
                if (blackAfricanAmerican.isChecked())
                {
                    patientRaceTxt += blackAfricanAmerican.getText().toString();
                }
                if (whiteCaucasian.isChecked())
                {
                    patientRaceTxt += whiteCaucasian.getText().toString();
                }
                if (asianPatient.isChecked())
                {
                    patientRaceTxt += asianPatient.getText().toString();
                }
                if (middleEasternNA.isChecked())
                {
                    patientRaceTxt += middleEasternNA.getText().toString();
                }
                if (hispanicLatinoPatient.isChecked())
                {
                    patientRaceTxt += hispanicLatinoPatient.getText().toString();
                }
                if (nativeAmericanPatient.isChecked())
                {
                    patientRaceTxt += nativeAmericanPatient.getText().toString();
                }
                if (nativeHawiianPacificIslander.isChecked())
                {
                    patientRaceTxt += nativeHawiianPacificIslander.getText().toString();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if(TextUtils.isEmpty(patientRaceTxt))
                {
                    Toast.makeText(PatientForm.this, "Race cannot cannot be empty", Toast.LENGTH_LONG).show();
                }
                // if statements to check if selected ethnicity CheckBox was clicked,
                // if yes then set patientRacTxt to the selected ethnicity
                if (HispanicOrLatino.isChecked())
                {
                    patientEthnicityTxt = HispanicOrLatino.getText().toString();
                }
                if (NotHispanicOrLatino.isChecked())
                {
                    patientEthnicityTxt = NotHispanicOrLatino.getText().toString();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if(TextUtils.isEmpty(patientEthnicityTxt))
                {
                    Toast.makeText(PatientForm.this, "Ethnicity cannot cannot be empty", Toast.LENGTH_LONG).show();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(prefLangTxt))
                {
                    Toast.makeText(PatientForm.this, "Language cannot be empty", Toast.LENGTH_LONG).show();
                    prefLang.setError("Language is required");
                    prefLang.requestFocus();
                }
                // Else if statement to check if the string length is less than 20 characters, if > 20 then
                // another error will be thrown
                else if (prefLangTxt.length() > 20)
                {
                    Toast.makeText(PatientForm.this, "Character limit must be < 20", Toast.LENGTH_LONG).show();
                    prefLang.setError("Max character limit is required");
                    prefLang.requestFocus();
                }
                // else if statement that checks if the string follows the pattern within the block and
                // if it does not, then an error will be displayed
                else if(!LETTERS_PATTERN.matcher(prefLangTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Language must be valid", Toast.LENGTH_LONG).show();
                    prefLang.setError("Language format is required");
                    prefLang.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(translatorTxt))
                {
                    Toast.makeText(PatientForm.this, "Translator cannot be empty", Toast.LENGTH_LONG).show();
                    neededTranslator.setError("Translator need is required");
                    neededTranslator.requestFocus();
                }
                // else if statement that checks if the string follows the pattern within the block and
                // if it does not, then an error will be displayed
                else if (!CONSENT_PATTERN.matcher(translatorTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Must be Yes or No", Toast.LENGTH_LONG).show();
                    neededTranslator.setError("Input format is required");
                    neededTranslator.requestFocus();
                }
                // Else if statement to check if the string matches the MARITAL_PATTERN and if it does not,
                // another error will be displayed
                else if (!MARITAL_PATTERN.matcher(maritalTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Must fill in marital status", Toast.LENGTH_LONG).show();
                    maritalStatusPatient.setError("Valid marital status is required");
                    maritalStatusPatient.requestFocus();
                }
                // if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(maritalTxt))
                {
                    Toast.makeText(PatientForm.this, "Must fill in marital status", Toast.LENGTH_LONG).show();
                    maritalStatusPatient.setError("Valid marital status is required");
                    maritalStatusPatient.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                // || operator to also check if the string length is less than 8 characters, if > 8 then
                // another error will be thrown
                else if (TextUtils.isEmpty(houseIncomeTxt) || houseIncomeTxt.length() > 8)
                {
                    Toast.makeText(PatientForm.this, "Must fill in household income", Toast.LENGTH_LONG).show();
                    incomeHousehold.setError("Income is required");
                    incomeHousehold.requestFocus();
                }
                // else if statement that checks if the string follows the pattern within the block and
                // if it does not, then an error will be displayed
                else if (!DIGITS_PATTERN.matcher(houseIncomeTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Must be valid income", Toast.LENGTH_LONG).show();
                    incomeHousehold.setError("Income format is required");
                    incomeHousehold.requestFocus();
                }
                // if statements to check if the pay schedule CheckBox was clicked,
                // if yes then set it to the patientIncomeTxt to the selected pay schedule
                if (weekly.isChecked())
                {
                    patientIncomeTxt = weekly.getText().toString();
                }
                if (biWeekly.isChecked())
                {
                    patientIncomeTxt = biWeekly.getText().toString();
                }
                if (monthly.isChecked())
                {
                    patientIncomeTxt = monthly.getText().toString();
                }
                if (yearly.isChecked())
                {
                    patientIncomeTxt = yearly.getText().toString();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if(TextUtils.isEmpty(patientIncomeTxt))
                {
                    Toast.makeText(PatientForm.this,"Duration cannot be empty",Toast.LENGTH_LONG);
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                // || operator to also check if the string length is less than 2 characters, if > 2 then
                // another error will be thrown
                else if (TextUtils.isEmpty(houseHoldTxt) || houseHoldTxt.length() > 2)
                {
                    Toast.makeText(PatientForm.this, "Must fill in Family size", Toast.LENGTH_LONG).show();
                    houseHoldSize.setError("Household size is required");
                    houseHoldSize.requestFocus();
                }
                // else if statement that checks if the string follows the pattern within the block and
                // if it does not, then an error will be displayed
                else if (!DIGITS_PATTERN.matcher(houseHoldTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Must be only digits", Toast.LENGTH_LONG).show();
                    houseHoldSize.setError("Number format is required");
                    houseHoldSize.requestFocus();
                }
                // if statements to check if the employment status CheckBox was clicked,
                // if yes then set it to the patientEmpTxt to the selected pay schedule
                if (unEmp.isChecked())
                {
                    patientEmpTxt = unEmp.getText().toString();
                }
                if (empFull.isChecked())
                {
                    patientEmpTxt = empFull.getText().toString();
                }
                if (empPart.isChecked())
                {
                    patientEmpTxt = empPart.getText().toString();
                }
                if (empSelf.isChecked())
                {
                    patientEmpTxt = empSelf.getText().toString();
                }
                if (empStudent.isChecked())
                {
                    patientEmpTxt = empStudent.getText().toString();
                }
                if (empRetired.isChecked())
                {
                    patientEmpTxt = empRetired.getText().toString();
                }
                if (empSeek.isChecked())
                {
                    patientEmpTxt = empSeek.getText().toString();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if(TextUtils.isEmpty(patientEmpTxt))
                {
                    Toast.makeText(PatientForm.this, "Employment status is required", Toast.LENGTH_LONG).show();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(occupationTxt))
                {
                    Toast.makeText(PatientForm.this, "Please fill occupation", Toast.LENGTH_LONG).show();
                    occupationPatient.setError("Occupation is required");
                    occupationPatient.requestFocus();
                }
                // Else if statement to check if the string length is less than 30 characters, if > 30 then
                // another error will be thrown
                else if (occupationTxt.length() > 30)
                {
                    Toast.makeText(PatientForm.this, "Character limit must be < 20", Toast.LENGTH_LONG).show();
                    occupationPatient.setError("Max character limit required");
                    occupationPatient.requestFocus();
                }
                // else if statement that checks if the string follows the pattern within the block and
                // if it does not, then an error will be displayed
                else if(!LETTERS_PATTERN.matcher(occupationTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Occupation must contain only letters", Toast.LENGTH_LONG).show();
                    occupationPatient.setError("Occupation format is required");
                    occupationPatient.requestFocus();
                }
                // if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                // || operator to also check if the string matches the CONSENT_PATTERN and if it does not,
                // another error will be displayed
                else if (!CONSENT_PATTERN.matcher(veteranTxt).matches() || TextUtils.isEmpty(veteranTxt))
                {
                    Toast.makeText(PatientForm.this, "Must be Yes or No", Toast.LENGTH_LONG).show();
                    veteranStatus.setError("Veteran status is required");
                    veteranStatus.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(emergencyNameTxt))
                {
                    Toast.makeText(PatientForm.this, "Name cannot be empty", Toast.LENGTH_LONG).show();
                    emergencyContactName.setError("Contact name is required");
                    emergencyContactName.requestFocus();
                }
                // Else if statement to check if the string length is less than 30 characters, if > 30 then
                // another error will be thrown
                else if (emergencyNameTxt.length() > 30)
                {
                    Toast.makeText(PatientForm.this, "Name length cannot be > 30", Toast.LENGTH_LONG).show();
                    emergencyContactName.setError("Max character limit required");
                    emergencyContactName.requestFocus();
                }
                // else if statement that checks if the string follows the pattern within the block and
                // if it does not, then an error will be displayed
                else if(!LETTERS_PATTERN.matcher(emergencyNameTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Name must contain only letters", Toast.LENGTH_LONG).show();
                    emergencyContactName.setError("Contact format is required");
                    emergencyContactName.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(relationshipTxt))
                {
                    Toast.makeText(PatientForm.this, "Relationship cannot be empty", Toast.LENGTH_LONG).show();
                    relationshipToPatient.setError("Contact relationship is required");
                    relationshipToPatient.requestFocus();
                }
                // Else if statement to  check if the string length is less than 20 characters, if > 20 then
                // another error will be thrown
                else if (relationshipTxt.length() > 20)
                {
                    Toast.makeText(PatientForm.this, "Cannot be longer than > 20 characters", Toast.LENGTH_LONG).show();
                    relationshipToPatient.setError("Max character limit required");
                    relationshipToPatient.requestFocus();
                }
                // else if statement that checks if the string follows the pattern within the block and
                // if it does not, then an error will be displayed
                else if(!LETTERS_PATTERN.matcher(relationshipTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Relation must contain only letters", Toast.LENGTH_LONG).show();
                    relationshipToPatient.setError("Relationship format is required");
                    relationshipToPatient.requestFocus();
                }
                // else if statement that checks if the string follows the pattern within the block and
                // if it does not, then an error will be displayed
                else if(!PHONE_PATTERN.matcher(contactPhoneTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Contact phone must be filled", Toast.LENGTH_LONG).show();
                    emergencyContactPhone.setError("Phone number format is required");
                    emergencyContactPhone.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(contactPhoneTxt))
                {
                    Toast.makeText(PatientForm.this, "Contact phone must not be empty", Toast.LENGTH_LONG).show();
                    emergencyContactPhone.setError("Contact number is required");
                    emergencyContactPhone.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                // || operator to also check if the string length is less than 30 characters, if > 30 then
                // another error will be thrown
                else if (TextUtils.isEmpty(patientConsentName) || patientConsentName.length() > 30)
                {
                    Toast.makeText(PatientForm.this, "Name must be filled", Toast.LENGTH_LONG).show();
                    patientNameConsent.setError("Name is required");
                    patientNameConsent.requestFocus();
                }
                // else if statement that checks if the patient name matches the the firstname + lastname,
                // if not, then an error will be displayed
                else if(!patientConsentName.equals(firstNameTxt + " " + lastNameTxt))
                {
                    Toast.makeText(PatientForm.this, "Name must match First name and Last name", Toast.LENGTH_LONG).show();
                    patientNameConsent.setError("Name match is required");
                    patientNameConsent.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientSignedText))
                {
                    Toast.makeText(PatientForm.this, "Name must be filled", Toast.LENGTH_LONG).show();
                    patientSigned.setError("Signed name is required");
                    patientSigned.requestFocus();
                }
                // Else if statement to check if the string length is less than 30 characters, if > 30 then
                // another error will be thrown
                else if (patientSignedText.length() > 30)
                {
                    Toast.makeText(PatientForm.this, "Name cannot be > 30 characters", Toast.LENGTH_LONG).show();
                    patientSigned.setError("Character length limit is required");
                    patientSigned.requestFocus();
                }
                // else if statement that checks if the patient signed name matches the consent name,
                // if not, then an error will be displayed
                else if(!patientSignedText.equals(patientConsentName))
                {
                    Toast.makeText(PatientForm.this, "Patient name must match", Toast.LENGTH_LONG).show();
                    patientSigned.setError("Name match is required");
                    patientSigned.requestFocus();
                }
                // else if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(patientSignatureText))
                {
                    Toast.makeText(PatientForm.this, "Signature must be filled", Toast.LENGTH_LONG).show();
                    patientSig.setError("Signature is required");
                    patientSig.requestFocus();
                }
                // Else if statement to also check if the string length is less than 30 characters, if > 30 then
                // another error will be thrown
                else if (patientSignatureText.length() > 30)
                {
                    Toast.makeText(PatientForm.this, "Signature cannot be > 30 characters", Toast.LENGTH_LONG).show();
                    patientSig.setError("Max character length is required");
                    patientSig.requestFocus();
                }
                // else if statement that checks if the patient signature name matches the signed name,
                // if not, then an error will be displayed
                else if(!patientSignatureText.equals(patientConsentName))
                {
                    Toast.makeText(PatientForm.this, "Patient name must match", Toast.LENGTH_LONG).show();
                    patientSig.setError("Name match is required");
                    patientSig.requestFocus();
                }
                // if statement that uses TextUtils.isEmpty() to check if the string is empty,
                // if it is empty, an error will be displayed.
                else if (TextUtils.isEmpty(consentDateTxt))
                {
                    Toast.makeText(PatientForm.this, "Date cannot empty", Toast.LENGTH_LONG).show();
                    consentDateSign.setError("Date is required");
                    consentDateSign.requestFocus();
                }
                // Else if statement to check if the string matches the DATE_PATTERN and if it does not,
                // another error will be displayed
                else if (!DATE_PATTERN.matcher(consentDateTxt).matches())
                {
                    Toast.makeText(PatientForm.this, "Date must be MM-DD-YYYY", Toast.LENGTH_LONG).show();
                    consentDateSign.setError("Date format is required");
                    consentDateSign.requestFocus();
                }
                // else if statement that checks if the consent date matches the todayDate field.
                // if not, then an error will be displayed
                else if(!consentDateTxt.equals(dateTxt))
                {
                    Toast.makeText(PatientForm.this, "Date must match today's date", Toast.LENGTH_LONG).show();
                    consentDateSign.setError("Date match is required");
                    consentDateSign.requestFocus();
                }
                // If all the inputs are valid then it will go into the else block where all the strings will be set
                // to the formData object and pushed to the database
                else
                {
                    //first form
                    PatientFormData formData = new PatientFormData();
                    formData.setTodaysDate(dateTxt);
                    formData.setReasonForVisit(visitReasonTxt);
                    formData.setFirstName(firstNameTxt);
                    formData.setLastName(lastNameTxt);
                    formData.setPatientSex(patientSexTxt);
                    formData.setPatientSSN(patientSSNTxt);
                    formData.setDateOfBirth(patientDOBTxt);
                    formData.setHomePhoneNo(patientHomeNumTxt);
                    formData.setCellPhoneNo(patientCellNumTxt);
                    formData.setPatientAddress(patientAddTxt);
                    formData.setPatientCity(patientCityTxt);
                    formData.setPatientState(patientStateTxt);
                    formData.setPatientZipCode(patientZipCodeTxt);
                    formData.setPrefContactMethod(patientPrefNumberTxt);
                    formData.setConsentToCall(patientConsentCallTxt);
                    formData.setConsentToText(patientConsentTextTxt);
                    formData.setInsuranceProvider(patientInsuranceTxt);
                    formData.setEmailAddress(patientEmailTxt);
                    formData.setPrefLanguage(prefLangTxt);
                    formData.setTranslatorNeed(translatorTxt);
                    formData.setMaritalStatus(maritalTxt);
                    formData.setHouseHoldIncome(houseHoldTxt);
                    formData.setFamilySize(houseHoldTxt);
                    formData.setOccupation(occupationTxt);
                    formData.setVeteranStatus(veteranTxt);
                    formData.setEmergencyContact(emergencyNameTxt);
                    formData.setEmergencyRelationship(relationshipTxt);
                    formData.setEmergencyContactNumber(contactPhoneTxt);
                    formData.setPatientNameSelfVolunteer(patientConsentName);
                    formData.setPatientNamePrinted(patientSignedText);
                    formData.setPatientSignature(patientSignatureText);
                    formData.setPatientConsentDate(consentDateTxt);
                    formData.setPatientRace(patientRaceTxt);
                    formData.setPatientEthnicity(patientEthnicityTxt);
                    formData.setIncomePayTime(patientIncomeTxt);
                    formData.setEmploymentStatus(patientEmpTxt);
                    formData.setUID(UserManager.getInstance().getCurrentUser().getUserId());

                    // If statement to check if the patient does not have insurance,
                    // if they do have insurance then this block of code will be executed where
                    // only the information up til the insurance will get saved and pushed to the
                    // realtime database.
                    if(!INSURANCE_PATTERN.matcher(patientInsuranceTxt).matches())
                    {
                        Toast.makeText(PatientForm.this, "Please wait for further instructions to see if you Qualify for care", Toast.LENGTH_LONG).show();


                        dao.add(info).addOnSuccessListener(suc->
                        {
                            Toast.makeText(PatientForm.this, "Insurance information recorded", Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener(er->
                        {
                            Toast.makeText(PatientForm.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                        });

                        // After the insurance information is pushed, the user will be taken back to the login activity
                        Intent insuranceApproval = new Intent(PatientForm.this, MainActivity.class);
                        startActivity(insuranceApproval);
                    }
                    // Else block will execute if the patient does not have insurance and they will continue onto
                    // the next patient form
                    else
                    {
                        // Intent to take patient to the next form
                        Intent patientContract = new Intent(PatientForm.this, PatientContract.class);
                        patientContract.putExtra("patientdata",formData ); // take information from this java file and move it to the next form
                        startActivity(patientContract);
                    }
                }
            }
        });
    }
}