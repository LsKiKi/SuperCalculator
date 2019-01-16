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

    /**
     * 计算五险一金的构造函数
     *
     * @param wuxianyijin 五险一金各项利率
     * @param raw         初始工资
     */
    public CalWuxianyijin(Wuxianyijin wuxianyijin, double raw) {
        this.wuxianyijin = wuxianyijin;
        this.raw = raw;
        this.injuryMoney = wuxianyijin.getInjury() * raw;  //计算工伤保险
        this.medicalTreatmentMoney = wuxianyijin.getMedicalTreatment() * raw;  //医疗保险
        this.oldCareMoney = wuxianyijin.getOldCare() * raw;  //养老保险
        this.procreationMoney = wuxianyijin.getProcreation() * raw; //生育险
        this.unemployedMoney = wuxianyijin.getUnempliyed() * raw; //失业险
        this.publicFundsMoney = wuxianyijin.getPublicfunds() * raw;  //公积金
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

    /**
     * 计算五险一金总额
     *
     * @return double 五险一金总额
     */
    public double wuxianyijinTotal() {
        return injuryMoney + medicalTreatmentMoney + oldCareMoney + procreationMoney + unemployedMoney + publicFundsMoney;
    }
}


