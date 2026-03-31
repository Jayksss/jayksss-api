package io.jayksss.api.kpi.controller;

import io.jayksss.api.kpi.dto.MinwonPortalKpiResult;
import io.jayksss.api.kpi.service.MinwonPortalKpiService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kpi")
public class MinwonPortalKpiController {
    private final MinwonPortalKpiService service;

    public MinwonPortalKpiController(MinwonPortalKpiService service) {
        this.service = service;
    }

    @GetMapping("/minwon-portal")
    public List<MinwonPortalKpiResult> getMinwonPortal(
            @RequestParam(defaultValue = "2026") int year,
            @RequestParam(defaultValue = "3") int month
    ) {
        return service.getMinwonPortalKpi(year, month);
    }
}

