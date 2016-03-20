/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vpc.app.vainruling.plugins.academic.perfeval.service.model;

import java.sql.Timestamp;
import net.vpc.app.vainruling.api.model.AppPeriod;
import net.vpc.app.vainruling.plugins.academic.service.model.config.AcademicTeacher;
import net.vpc.upa.UserFieldModifier;
import net.vpc.upa.config.Entity;
import net.vpc.upa.config.Field;
import net.vpc.upa.config.Id;
import net.vpc.upa.config.Path;
import net.vpc.upa.config.Sequence;

/**
 *
 * @author vpc
 */
@Entity
@Path("/Education/History")
public class AcademicFeedbackHistory {

    @Id
    @Sequence
    private int id;
    @Field(modifiers = UserFieldModifier.SUMMARY)
    private AppPeriod period;
    @Field(modifiers = UserFieldModifier.SUMMARY)
    private AcademicTeacher teacher;
    @Field(modifiers = UserFieldModifier.SUMMARY)
    private String className;
    @Field(modifiers = UserFieldModifier.SUMMARY)
    private String programName;
    @Field(modifiers = UserFieldModifier.SUMMARY)
    private String semesterName;
    @Field(modifiers = UserFieldModifier.SUMMARY)
    private String course;
    @Field(modifiers = UserFieldModifier.SUMMARY)
    private String questionGroup;
    @Field(modifiers = UserFieldModifier.SUMMARY)
    private String question;
    @Field(modifiers = UserFieldModifier.SUMMARY)
    private String student;
    private String observations;
    private int position;
    @Field(modifiers = UserFieldModifier.SUMMARY)
    private String response;
    private Timestamp updateDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public AppPeriod getPeriod() {
        return period;
    }

    public void setPeriod(AppPeriod period) {
        this.period = period;
    }

    public AcademicTeacher getTeacher() {
        return teacher;
    }

    public void setTeacher(AcademicTeacher teacher) {
        this.teacher = teacher;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getQuestionGroup() {
        return questionGroup;
    }

    public void setQuestionGroup(String questionGroup) {
        this.questionGroup = questionGroup;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}