package com.tofly.youke.domain.po;

import com.tofly.youke.common.domain.BaseDomain;

public class Teacher extends BaseDomain {
    private Integer teacherId;

    private String teacherName;

    private String teacherType;

    private String teacherNo;

    private String englishName;

    private String universityId;

    private String instituteId;

    private String majorId;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getTeacherType() {
        return teacherType;
    }

    public void setTeacherType(String teacherType) {
        this.teacherType = teacherType == null ? null : teacherType.trim();
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo == null ? null : teacherNo.trim();
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId == null ? null : universityId.trim();
    }

    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId == null ? null : instituteId.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", teacherId=").append(teacherId);
        sb.append(", teacherName=").append(teacherName);
        sb.append(", teacherType=").append(teacherType);
        sb.append(", teacherNo=").append(teacherNo);
        sb.append(", englishName=").append(englishName);
        sb.append(", universityId=").append(universityId);
        sb.append(", instituteId=").append(instituteId);
        sb.append(", majorId=").append(majorId);
        sb.append("]");
        return sb.toString();
    }
}