package net.vpc.app.vainruling.core.service.stats;

public interface KPI<V> {

    KPIEvaluator<V> createEvaluator();

    KPIValueDef[] getValueDefinitions();
}
