package com.whut.wlqk.superCalculator.utils.tax;

/**
 * 提供五险一金相关的计算
 * 各项值和总值
 */
public class CalWuxianyijin {

    double injuryMoney;
    double medicalTreatmentMoney;
    double oldCareMoney;
    double procreationMoney;
    double unemployedMoney;
    double publicFundsMoney;

    Wuxianyijin wuxianyijin;
    double raw;

    public CalWuxianyijin(Wuxianyijin wuxianyijin, double raw) {
        this.wuxianyijin = wuxianyijin;
        this.raw = raw;
        this.injuryMoney = wuxianyijin.getInjury() * raw;
        this.medicalTreatmentMoney = wuxianyijin.getMedicalTreatment() * raw;
        this.oldCareMoney = wuxianyijin.getOldCare() * raw;
        this.procreationMoney = wuxianyijin.getProcreation() * raw;
        this.unemployedMoney = wuxianyijin.getUnempliyed() * raw;
        this.publicFundsMoney = wuxianyijin.getPublicfunds() * raw;
    }


    public double getInjuryMoney() {
        return injuryMoney;
    }

    public void setInjuryMoney(double injuryMoney) {
        this.injuryMoney = injuryMoney;
    }

    public double getMedicalTreatmentMoney() {
        return medicalTreatmentMoney;
    }

    public void setMedicalTreatmentMoney(double medicalTreatmentMoney) {
        this.medicalTreatmentMoney = medicalTreatmentMoney;
    }

    public double getOldCareMoney() {
        return oldCareMoney;
    }

    public void setOldCareMoney(double oldCareMoney) {
        this.oldCareMoney = oldCareMoney;
    }

    public double getProcreationMoney() {
        return procreationMoney;
    }

    public void setProcreationMoney(double procreationMoney) {
        this.procreationMoney = procreationMoney;
    }

    public double getUnemployedMoney() {
        return unemployedMoney;
    }

    public void setUnemployedMoney(double unemployedMoney) {
        this.unemployedMoney = unemployedMoney;
    }

    public double getPublicFundsMoney() {
        return publicFundsMoney;
    }

    public void setPublicFundsMoney(double publicFundsMoney) {
        this.publicFundsMoney = publicFundsMoney;
    }

    public Wuxianyijin getWuxianyijin() {
        return wuxianyijin;
    }

    public void setWuxianyijin(Wuxianyijin wuxianyijin) {
        this.wuxianyijin = wuxianyijin;
    }

    public double getRaw() {
        return raw;
    }

    public void setRaw(double raw) {
        this.raw = raw;
    }

    public double wuxianyijinTotal() {
        return injuryMoney + medicalTreatmentMoney + oldCareMoney + procreationMoney + unemployedMoney + publicFundsMoney;
    }
}


