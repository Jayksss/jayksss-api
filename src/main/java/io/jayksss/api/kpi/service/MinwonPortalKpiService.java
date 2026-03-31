package io.jayksss.api.kpi.service;

import io.jayksss.api.kpi.dto.MinwonPortalKpiResult;
import io.jayksss.api.kpi.repository.MinwonPortalKpiNativeQueryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MinwonPortalKpiService {
    private final MinwonPortalKpiNativeQueryRepository repository;

    public MinwonPortalKpiService(MinwonPortalKpiNativeQueryRepository repository) {
        this.repository = repository;
    }

    public List<MinwonPortalKpiResult> getMinwonPortalKpi(int year, int month) {
        return repository.fetchMinwonPortalKpiResults("MINWON-PORTAL", year, month);
    }
}

