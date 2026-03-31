package io.jayksss.api.kpi.dto;

import java.math.BigDecimal;

public class MinwonPortalKpiResult {
    private String serviceName;
    private Integer reportYear;
    private Integer reportMonth;
    private String kpiName;
    private BigDecimal targetValue;
    private BigDecimal thresholdValue;
    private BigDecimal measuredValue;
    private String breachYn;
    private String breachLevel;

    public MinwonPortalKpiResult() {
    }

    public MinwonPortalKpiResult(
            String serviceName,
            Integer reportYear,
            Integer reportMonth,
            String kpiName,
            BigDecimal targetValue,
            BigDecimal thresholdValue,
            BigDecimal measuredValue,
            String breachYn,
            String breachLevel
    ) {
        this.serviceName = serviceName;
        this.reportYear = reportYear;
        this.reportMonth = reportMonth;
        this.kpiName = kpiName;
        this.targetValue = targetValue;
        this.thresholdValue = thresholdValue;
        this.measuredValue = measuredValue;
        this.breachYn = breachYn;
        this.breachLevel = breachLevel;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getReportYear() {
        return reportYear;
    }

    public void setReportYear(Integer reportYear) {
        this.reportYear = reportYear;
    }

    public Integer getReportMonth() {
        return reportMonth;
    }

    public void setReportMonth(Integer reportMonth) {
        this.reportMonth = reportMonth;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public BigDecimal getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(BigDecimal targetValue) {
        this.targetValue = targetValue;
    }

    public BigDecimal getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(BigDecimal thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public BigDecimal getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(BigDecimal measuredValue) {
        this.measuredValue = measuredValue;
    }

    public String getBreachYn() {
        return breachYn;
    }

    public void setBreachYn(String breachYn) {
        this.breachYn = breachYn;
    }

    public String getBreachLevel() {
        return breachLevel;
    }

    public void setBreachLevel(String breachLevel) {
        this.breachLevel = breachLevel;
    }
}

