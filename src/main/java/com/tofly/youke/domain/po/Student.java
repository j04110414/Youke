package com.tofly.youke.domain.po;

import com.tofly.youke.common.domain.BaseDomain;

/**
 * @author lyrics
 */
public class Student extends BaseDomain {

    private String studentName;

    private String studentNo;

    private String gender;

    private String idCard;

    private String email;

    private String phone;

    private String countryCode;

    private String countryName;

    private String universityId;

    private String universityName;

    private String instituteId;

    private String instituteName;

    private String majorId;

    private String majorName;

    private String delFlag;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo == null ? null : studentNo.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode == null ? null : countryCode.trim();
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId == null ? null : universityId.trim();
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName == null ? null : universityName.trim();
    }

    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId == null ? null : instituteId.trim();
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName == null ? null : instituteName.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", studentName=").append(studentName);
        sb.append(", studentNo=").append(studentNo);
        sb.append(", gender=").append(gender);
        sb.append(", idCard=").append(idCard);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", countryCode=").append(countryCode);
        sb.append(", countryName=").append(countryName);
        sb.append(", universityId=").append(universityId);
        sb.append(", universityName=").append(universityName);
        sb.append(", instituteId=").append(instituteId);
        sb.append(", instituteName=").append(instituteName);
        sb.append(", majorId=").append(majorId);
        sb.append(", majorName=").append(majorName);
        sb.append(", delFlag=").append(delFlag);
        sb.append("]");
        return sb.toString();
    }
}