package com.example.huda_application;

public class patientInsurance
{
    private String todaysDate;
    private String reasonForVisit;
    private String lastName;
    private String firstName;
    private String patientSex;
    private String dateOfBirth;
    private String patientAddress;
    private String patientCity;
    private String patientState;
    private String patientZipCode;
    private String patientSSN;
    private String homePhoneNo;
    private String cellPhoneNo;
    private String prefContactMethod;
    private String consentToCall;
    private String consentToText;
    private String insuranceProvider;

    public String getTodaysDate() {
        return todaysDate;
    }

    public void setTodaysDate(String todaysDate) {
        this.todaysDate = todaysDate;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientCity() {
        return patientCity;
    }

    public void setPatientCity(String patientCity) {
        this.patientCity = patientCity;
    }

    public String getPatientState() {
        return patientState;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }

    public String getPatientZipCode() {
        return patientZipCode;
    }

    public void setPatientZipCode(String patientZipCode) {
        this.patientZipCode = patientZipCode;
    }

    public String getPatientSSN() {
        return patientSSN;
    }

    public void setPatientSSN(String patientSSN) {
        this.patientSSN = patientSSN;
    }

    public String getHomePhoneNo() {
        return homePhoneNo;
    }

    public void setHomePhoneNo(String homePhoneNo) {
        this.homePhoneNo = homePhoneNo;
    }

    public String getCellPhoneNo() {
        return cellPhoneNo;
    }

    public void setCellPhoneNo(String cellPhoneNo) {
        this.cellPhoneNo = cellPhoneNo;
    }

    public String getPrefContactMethod() {
        return prefContactMethod;
    }

    public void setPrefContactMethod(String prefContactMethod) {
        this.prefContactMethod = prefContactMethod;
    }

    public String getConsentToCall() {
        return consentToCall;
    }

    public void setConsentToCall(String consentToCall) {
        this.consentToCall = consentToCall;
    }

    public String getConsentToText() {
        return consentToText;
    }

    public void setConsentToText(String consentToText) {
        this.consentToText = consentToText;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }



    public patientInsurance(String todaysDate, String reasonForVisit, String lastName, String firstName, String patientSex, String dateOfBirth, String patientAddress, String patientCity, String patientState, String patientZipCode, String patientSSN, String homePhoneNo, String cellPhoneNo, String prefContactMethod, String consentToCall, String consentToText, String insuranceProvider) {
        this.todaysDate = todaysDate;
        this.reasonForVisit = reasonForVisit;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patientSex = patientSex;
        this.dateOfBirth = dateOfBirth;
        this.patientAddress = patientAddress;
        this.patientCity = patientCity;
        this.patientState = patientState;
        this.patientZipCode = patientZipCode;
        this.patientSSN = patientSSN;
        this.homePhoneNo = homePhoneNo;
        this.cellPhoneNo = cellPhoneNo;
        this.prefContactMethod = prefContactMethod;
        this.consentToCall = consentToCall;
        this.consentToText = consentToText;
        this.insuranceProvider = insuranceProvider;
    }


}
