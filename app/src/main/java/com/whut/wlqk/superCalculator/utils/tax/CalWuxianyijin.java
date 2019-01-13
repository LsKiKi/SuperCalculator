package com.whut.wlqk.superCalculator.utils.tax;

public class CalWuxianyijin {

    /**
     * 根据城市数据和raw工资计算五险一金
     * @param wuxianyijin
     * @param raw
     * @return 五险一金计算结果 double
     */
    static public double wuxianyijin(Wuxianyijin wuxianyijin, double raw) {
        double rate = wuxianyijin.getInjury()+wuxianyijin.getMedicalTreatment()+wuxianyijin.getOldCare()+
                wuxianyijin.getProcreation()+wuxianyijin.getProcreation()+wuxianyijin.getPublicfunds();
        return rate*raw;
    }



}


