package com.whut.wlqk.superCalculator.utils.tax;

public class Wuxianyijin {
    String city;
    double oldCare;
    double medicalTreatment;
    double unempliyed;
    double injury;
    double procreation;
    double publicfunds;

    public Wuxianyijin(String city, double oldCare, double medicalTreatment,
                       double unempliyed, double injury, double procreation, double publicfunds) {
        this.city = city;
        this.oldCare = oldCare;
        this.medicalTreatment = medicalTreatment;
        this.unempliyed = unempliyed;
        this.injury = injury;
        this.procreation = procreation;
        this.publicfunds = publicfunds;
    }

    public Wuxianyijin(){
        super();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getOldCare() {
        return oldCare;
    }

    public void setOldCare(double oldCare) {
        this.oldCare = oldCare;
    }

    public double getMedicalTreatment() {
        return medicalTreatment;
    }

    public void setMedicalTreatment(double medicalTreatment) {
        this.medicalTreatment = medicalTreatment;
    }

    public double getUnempliyed() {
        return unempliyed;
    }

    public void setUnempliyed(double unempliyed) {
        this.unempliyed = unempliyed;
    }

    public double getInjury() {
        return injury;
    }

    public void setInjury(double injury) {
        this.injury = injury;
    }

    public double getProcreation() {
        return procreation;
    }

    public void setProcreation(double procreation) {
        this.procreation = procreation;
    }

    public double getPublicfunds() {
        return publicfunds;
    }

    public void setPublicfunds(double publicfunds) {
        this.publicfunds = publicfunds;
    }


}
