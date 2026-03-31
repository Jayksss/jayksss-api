package io.jayksss.api.kpi.repository;

import io.jayksss.api.kpi.dto.MinwonPortalKpiResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MinwonPortalKpiNativeQueryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<MinwonPortalKpiResult> fetchMinwonPortalKpiResults(String serviceCode, int year, int month) {
        String sql = """
                SELECT
                    s.service_name,
                    pr.report_year,
                    pr.report_month,
                    k.kpi_name,
                    r.target_value,
                    r.threshold_value,
                    r.measured_value,
                    r.breach_yn,
                    r.breach_level
                FROM slm_performance_report pr
                JOIN slm_performance_result r
                    ON pr.performance_report_id = r.performance_report_id
                JOIN slm_sla_kpi sk
                    ON r.sla_kpi_id = sk.sla_kpi_id
                JOIN slm_kpi k
                    ON sk.kpi_id = k.kpi_id
                JOIN svc_service s
                    ON pr.service_id = s.service_id
                WHERE s.service_code = :serviceCode
                  AND pr.report_year = :year
                  AND pr.report_month = :month
                ORDER BY sk.display_order
                """;

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("serviceCode", serviceCode);
        query.setParameter("year", year);
        query.setParameter("month", month);

        @SuppressWarnings("unchecked")
        List<Object[]> rows = query.getResultList();

        List<MinwonPortalKpiResult> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(mapRow(row));
        }
        return result;
    }

    private static MinwonPortalKpiResult mapRow(Object[] row) {
        String serviceName = (String) row[0];
        Integer reportYear = toInteger(row[1]);
        Integer reportMonth = toInteger(row[2]);
        String kpiName = (String) row[3];
        BigDecimal targetValue = toBigDecimal(row[4]);
        BigDecimal thresholdValue = toBigDecimal(row[5]);
        BigDecimal measuredValue = toBigDecimal(row[6]);
        String breachYn = toYn(row[7]);
        String breachLevel = toStringOrNull(row[8]);

        return new MinwonPortalKpiResult(
                serviceName,
                reportYear,
                reportMonth,
                kpiName,
                targetValue,
                thresholdValue,
                measuredValue,
                breachYn,
                breachLevel
        );
    }

    private static String toYn(Object v) {
        if (v == null) {
            return null;
        }
        if (v instanceof Boolean b) {
            return b ? "Y" : "N";
        }
        if (v instanceof Number n) {
            return n.intValue() == 0 ? "N" : "Y";
        }
        String s = v.toString().trim();
        if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("yes") || s.equals("1") || s.equalsIgnoreCase("true")) {
            return "Y";
        }
        if (s.equalsIgnoreCase("n") || s.equalsIgnoreCase("no") || s.equals("0") || s.equalsIgnoreCase("false")) {
            return "N";
        }
        return s;
    }

    private static String toStringOrNull(Object v) {
        if (v == null) {
            return null;
        }
        return v.toString();
    }

    private static Integer toInteger(Object v) {
        if (v == null) {
            return null;
        }
        if (v instanceof Number n) {
            return n.intValue();
        }
        if (v instanceof String) {
            return Integer.parseInt((String) v);
        }
        return Integer.parseInt(v.toString());
    }

    private static BigDecimal toBigDecimal(Object v) {
        if (v == null) {
            return null;
        }
        if (v instanceof BigDecimal bd) {
            return bd;
        }
        if (v instanceof Number n) {
            return BigDecimal.valueOf(n.doubleValue());
        }
        return new BigDecimal(v.toString());
    }
}

