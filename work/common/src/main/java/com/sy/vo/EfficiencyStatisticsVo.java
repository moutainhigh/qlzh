package com.sy.vo;

import java.util.Objects;

public class EfficiencyStatisticsVo {

    private String sonName;

    private String name_1; //车间

    private String name_2; //工程队

    private String name_3; //班组

  private int sonTime;

    private int sonWorkTime;

    private double sonPower;

    private String name; //总厂

    private int time; //总厂

    private int time1; //车间

    private int time2; //工程队

    private int time3; //班组

    private int workTime;

    private int workTime1;

    private int workTime2;

    private int workTime3;

    private double power;

    private double power1;

    private double power2;

    private double power3;

    private String workNo;


    public String getSonName() {
        return sonName;
    }

    public void setSonName(String sonName) {
        this.sonName = sonName;
    }

    public int getSonTime() {
        return sonTime;
    }

    public void setSonTime(int sonTime) {
        this.sonTime = sonTime;
    }

    public String getName_1() { return name_1; }

    public void setName_1(String name_1) { this.name_1 = name_1; }

    public String getName_2() { return name_2; }

    public void setName_2(String name_2) { this.name_2 = name_2; }

    public String getName_3() { return name_3; }

    public void setName_3(String name_3) { this.name_3 = name_3; }

    public int getSonWorkTime() {
        return sonWorkTime;
    }

    public void setSonWorkTime(int sonWorkTime) {
        this.sonWorkTime = sonWorkTime;
    }

    public double getSonPower() {
        return sonPower;
    }

    public void setSonPower(double sonPower) {
        this.sonPower = sonPower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime1() {
        return time1;
    }

    public void setTime1(int time1) {
        this.time1 = time1;
    }

    public int getTime2() {
        return time2;
    }

    public void setTime2(int time2) {
        this.time2 = time2;
    }

    public int getTime3() {
        return time3;
    }

    public void setTime3(int time3) {
        this.time3 = time3;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    public int getWorkTime1() {
        return workTime1;
    }

    public void setWorkTime1(int workTime1) {
        this.workTime1 = workTime1;
    }

    public int getWorkTime2() {
        return workTime2;
    }

    public void setWorkTime2(int workTime2) {
        this.workTime2 = workTime2;
    }

    public int getWorkTime3() {
        return workTime3;
    }

    public void setWorkTime3(int workTime3) {
        this.workTime3 = workTime3;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getPower1() {
        return power1;
    }

    public void setPower1(double power1) {
        this.power1 = power1;
    }

    public double getPower2() {
        return power2;
    }

    public void setPower2(double power2) {
        this.power2 = power2;
    }

    public double getPower3() {
        return power3;
    }

    public void setPower3(double power3) {
        this.power3 = power3;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EfficiencyStatisticsVo that = (EfficiencyStatisticsVo) o;
        return sonTime == that.sonTime &&
                sonWorkTime == that.sonWorkTime &&
                Double.compare(that.sonPower, sonPower) == 0 &&
                time == that.time &&
                workTime == that.workTime &&
                Double.compare(that.power, power) == 0 &&
                sonName.equals(that.sonName) &&
                name.equals(that.name) &&
                workNo.equals(that.workNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sonName, sonTime, sonWorkTime, sonPower, name, time, workTime, power, workNo);
    }

    @Override
    public String toString() {
        return "EfficiencyStatisticsVo{" +
                "sonName='" + sonName + '\'' +
                ", sonTime=" + sonTime +
                ", sonWorkTime=" + sonWorkTime +
                ", sonPower=" + sonPower +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", workTime=" + workTime +
                ", power=" + power +
                ", workNo='" + workNo + '\'' +
                '}';
    }*/

  /*  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EfficiencyStatisticsVo that = (EfficiencyStatisticsVo) o;
        return sonTime == that.sonTime &&
                sonWorkTime == that.sonWorkTime &&
                Double.compare(that.sonPower, sonPower) == 0 &&
                time == that.time &&
                workTime == that.workTime &&
                Double.compare(that.power, power) == 0 &&
                Objects.equals(sonName, that.sonName) &&
                Objects.equals(name_1, that.name_1) &&
                Objects.equals(name_2, that.name_2) &&
                Objects.equals(name_3, that.name_3) &&
                Objects.equals(name, that.name) &&
                Objects.equals(workNo, that.workNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sonName, name_1, name_2, name_3, sonTime, sonWorkTime, sonPower, name, time, workTime, power, workNo);
    }

    @Override
    public String toString() {
        return "EfficiencyStatisticsVo{" +
                "sonName='" + sonName + '\'' +
                ", name_1='" + name_1 + '\'' +
                ", name_2='" + name_2 + '\'' +
                ", name_3='" + name_3 + '\'' +
                ", sonTime=" + sonTime +
                ", sonWorkTime=" + sonWorkTime +
                ", sonPower=" + sonPower +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", workTime=" + workTime +
                ", power=" + power +
                ", workNo='" + workNo + '\'' +
                '}';
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EfficiencyStatisticsVo that = (EfficiencyStatisticsVo) o;
        return sonTime == that.sonTime &&
                sonWorkTime == that.sonWorkTime &&
                Double.compare(that.sonPower, sonPower) == 0 &&
                time == that.time &&
                time1 == that.time1 &&
                time2 == that.time2 &&
                time3 == that.time3 &&
                workTime == that.workTime &&
                workTime1 == that.workTime1 &&
                workTime2 == that.workTime2 &&
                workTime3 == that.workTime3 &&
                Double.compare(that.power, power) == 0 &&
                Double.compare(that.power1, power1) == 0 &&
                Double.compare(that.power2, power2) == 0 &&
                Double.compare(that.power3, power3) == 0 &&
                Objects.equals(sonName, that.sonName) &&
                Objects.equals(name_1, that.name_1) &&
                Objects.equals(name_2, that.name_2) &&
                Objects.equals(name_3, that.name_3) &&
                Objects.equals(name, that.name) &&
                Objects.equals(workNo, that.workNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sonName, name_1, name_2, name_3, sonTime, sonWorkTime, sonPower, name, time, time1, time2, time3, workTime, workTime1, workTime2, workTime3, power, power1, power2, power3, workNo);
    }

    @Override
    public String toString() {
        return "EfficiencyStatisticsVo{" +
                "sonName='" + sonName + '\'' +
                ", name_1='" + name_1 + '\'' +
                ", name_2='" + name_2 + '\'' +
                ", name_3='" + name_3 + '\'' +
                ", sonTime=" + sonTime +
                ", sonWorkTime=" + sonWorkTime +
                ", sonPower=" + sonPower +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", time1=" + time1 +
                ", time2=" + time2 +
                ", time3=" + time3 +
                ", workTime=" + workTime +
                ", workTime1=" + workTime1 +
                ", workTime2=" + workTime2 +
                ", workTime3=" + workTime3 +
                ", power=" + power +
                ", power1=" + power1 +
                ", power2=" + power2 +
                ", power3=" + power3 +
                ", workNo='" + workNo + '\'' +
                '}';
    }
}
