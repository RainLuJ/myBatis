package com.lujun61.entity;

public class People {
    private Integer pId;
    private String pName;
    private String pEmail;
    private Integer pAge;

    public People() {
    }

    public People(Integer pId, String pName, String pEmail, Integer pAge) {
        this.pId = pId;
        this.pName = pName;
        this.pEmail = pEmail;
        this.pAge = pAge;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public Integer getpAge() {
        return pAge;
    }

    public void setpAge(Integer pAge) {
        this.pAge = pAge;
    }

    @Override
    public String toString() {
        return "People{" +
                "pId=" + pId +
                ", pName='" + pName + '\'' +
                ", pEmail='" + pEmail + '\'' +
                ", pAge=" + pAge +
                '}';
    }
}
