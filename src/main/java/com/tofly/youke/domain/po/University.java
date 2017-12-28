package com.tofly.youke.domain.po;

import com.tofly.youke.common.domain.BaseDomain;

public class University extends BaseDomain {
    private Integer universityId;

    private String universityName;

    private String universityCode;

    private String country;

    private String province;

    private String city;

    private String universityFeature;

    private String universityType;

    private String educationLevel;

    private String category;

    private String membershipType;

    private String membership;

    private String graduateSchool;

    private String address;

    private String phone;

    private String website;

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName == null ? null : universityName.trim();
    }

    public String getUniversityCode() {
        return universityCode;
    }

    public void setUniversityCode(String universityCode) {
        this.universityCode = universityCode == null ? null : universityCode.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getUniversityFeature() {
        return universityFeature;
    }

    public void setUniversityFeature(String universityFeature) {
        this.universityFeature = universityFeature == null ? null : universityFeature.trim();
    }

    public String getUniversityType() {
        return universityType;
    }

    public void setUniversityType(String universityType) {
        this.universityType = universityType == null ? null : universityType.trim();
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel == null ? null : educationLevel.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType == null ? null : membershipType.trim();
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership == null ? null : membership.trim();
    }

    public String getGraduateSchool() {
        return graduateSchool;
    }

    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool == null ? null : graduateSchool.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", universityId=").append(universityId);
        sb.append(", universityName=").append(universityName);
        sb.append(", universityCode=").append(universityCode);
        sb.append(", country=").append(country);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", universityFeature=").append(universityFeature);
        sb.append(", universityType=").append(universityType);
        sb.append(", educationLevel=").append(educationLevel);
        sb.append(", category=").append(category);
        sb.append(", membershipType=").append(membershipType);
        sb.append(", membership=").append(membership);
        sb.append(", graduateSchool=").append(graduateSchool);
        sb.append(", address=").append(address);
        sb.append(", phone=").append(phone);
        sb.append(", website=").append(website);
        sb.append("]");
        return sb.toString();
    }
}