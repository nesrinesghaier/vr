/*
 * To change this license header, choose License Headers in Project Properties.
 *
 * and open the template in the editor.
 */
package net.vpc.app.vainruling.plugins.academic.service;

import net.vpc.app.vainruling.core.service.model.AppPeriod;
import net.vpc.app.vainruling.plugins.academic.service.stat.DeviationConfig;

/**
 * @author taha.bensalah@gmail.com
 */
public class TeacherGenerationOptions {

    private Integer[] teacherIds;
    private String semester;
    private GeneratedContent[] contents;
    private DeviationConfig deviationConfig=new DeviationConfig();
    private String templateFolder;
    private String outputFolder;
    private String outputNamePattern;
    private CourseAssignmentFilter courseAssignmentFilter;
    private boolean includeIntents;
    private AppPeriod period;

    public Integer[] getTeacherIds() {
        return teacherIds;
    }

    public TeacherGenerationOptions setTeacherIds(Integer... teacherIds) {
        this.teacherIds = teacherIds;
        return this;
    }

    public String getSemester() {
        return semester;
    }

    public TeacherGenerationOptions setSemester(String semester) {
        this.semester = semester;
        return this;
    }

    public GeneratedContent[] getContents() {
        return contents;
    }

    public TeacherGenerationOptions setContents(GeneratedContent... contents) {
        this.contents = contents;
        return this;
    }

    public String getTemplateFolder() {
        return templateFolder;
    }

    public TeacherGenerationOptions setTemplateFolder(String templateFolder) {
        this.templateFolder = templateFolder;
        return this;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public TeacherGenerationOptions setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
        return this;
    }

    public String getOutputNamePattern() {
        return outputNamePattern;
    }

    public TeacherGenerationOptions setOutputNamePattern(String outputNamePattern) {
        this.outputNamePattern = outputNamePattern;
        return this;
    }

    public CourseAssignmentFilter getCourseAssignmentFilter() {
        return courseAssignmentFilter;
    }

    public TeacherGenerationOptions setCourseAssignmentFilter(CourseAssignmentFilter inclueIntents) {
        this.courseAssignmentFilter = inclueIntents;
        return this;
    }

    public boolean isIncludeIntents() {
        return includeIntents;
    }

    public TeacherGenerationOptions setIncludeIntents(boolean includeIntents) {
        this.includeIntents = includeIntents;
        return this;
    }

    public AppPeriod getPeriod() {
        return period;
    }

    public TeacherGenerationOptions setPeriod(AppPeriod period) {
        this.period = period;
        return this;
    }

    public DeviationConfig getDeviationConfig() {
        return deviationConfig;
    }

    public void setDeviationConfig(DeviationConfig deviationConfig) {
        this.deviationConfig = deviationConfig;
    }
}
