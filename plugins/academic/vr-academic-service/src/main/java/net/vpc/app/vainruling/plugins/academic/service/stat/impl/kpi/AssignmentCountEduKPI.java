package net.vpc.app.vainruling.plugins.academic.service.stat.impl.kpi;

import net.vpc.app.vainruling.core.service.stats.*;
import net.vpc.app.vainruling.plugins.academic.service.model.config.AcademicTeacher;
import net.vpc.app.vainruling.plugins.academic.service.model.current.AcademicCourseAssignmentInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vpc on 8/29/16.
 */
public class AssignmentCountEduKPI implements KPI<AcademicCourseAssignmentInfo> {
    public static final AssignmentCountEduKPI INSTANCE=new AssignmentCountEduKPI();
    final DefaultKPIValueDef COL1 = new DefaultKPIValueDef("AssignmentCount");
    final KPIValueDef[] COLS = {COL1};

    @Override
    public KPIEvaluator<AcademicCourseAssignmentInfo> createEvaluator() {
        return new KPIEvaluator<AcademicCourseAssignmentInfo>() {
            private int count;

            @Override
            public void start() {

            }

            @Override
            public void visit(AcademicCourseAssignmentInfo assignment) {
                count++;
            }

            @Override
            public KPIValue[] evaluate() {
                return new KPIValue[]{
                        new DefaultKPIValue(COL1, count)
                };
            }
        };
    }

    @Override
    public KPIValueDef[] getValueDefinitions() {
        return COLS;
    }
}
