package io.jayksss.api.common.standard.controller;

import io.jayksss.api.common.convert.YnBooleanParser;
import io.jayksss.api.common.standard.entity.Category;
import io.jayksss.api.common.standard.entity.CodeDetail;
import io.jayksss.api.common.standard.entity.CodeGroup;
import io.jayksss.api.common.standard.entity.FilePolicy;
import io.jayksss.api.common.standard.entity.SystemConfig;
import io.jayksss.api.common.standard.service.CommonStandardQueryService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/common/standard")
public class CommonStandardController {
    private final CommonStandardQueryService queryService;

    public CommonStandardController(CommonStandardQueryService queryService) {
        this.queryService = queryService;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> badRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @GetMapping("/categories")
    public List<Category> listCategories(
            @RequestParam(required = false) String categoryType,
            @RequestParam(required = false) String categoryCode,
            @RequestParam(required = false) String parentCategoryCode,
            @RequestParam(name = "useYn", required = false) String useYn
    ) {
        return queryService.findCategories(categoryType, categoryCode, parentCategoryCode, YnBooleanParser.parseFilter(useYn));
    }

    @GetMapping("/code-groups")
    public List<CodeGroup> listCodeGroups(
            @RequestParam(required = false) String groupCode,
            @RequestParam(name = "useYn", required = false) String useYn
    ) {
        return queryService.findCodeGroups(groupCode, YnBooleanParser.parseFilter(useYn));
    }

    /**
     * 공통코드 상세 조회 예시 (그룹 코드는 {@link io.jayksss.api.common.standard.CodeDetailGroupCode} 참고):
     * {@code GET /api/common/standard/code-details?groupCode=PROC_TYPE&useYn=Y} (또는 {@code true}, {@code y})
     * — 예: 요청/구성/변경관리 등 프로세스 유형(01~08). 다른 그룹은 {@code CHANGE_TYPE}, {@code INQUIRY_STATUS} 등.
     */
    @GetMapping("/code-details")
    public List<CodeDetail> listCodeDetails(
            @RequestParam(required = false) String groupCode,
            @RequestParam(required = false) String detailCode,
            @RequestParam(name = "useYn", required = false) String useYn
    ) {
        return queryService.findCodeDetails(groupCode, detailCode, YnBooleanParser.parseFilter(useYn));
    }

    @GetMapping("/file-policies")
    public List<FilePolicy> listFilePolicies(
            @RequestParam(required = false) String policyType,
            @RequestParam(required = false) String extension,
            @RequestParam(name = "useYn", required = false) String useYn
    ) {
        return queryService.findFilePolicies(policyType, extension, YnBooleanParser.parseFilter(useYn));
    }

    @GetMapping("/system-configs")
    public List<SystemConfig> listSystemConfigs(
            @RequestParam(required = false) String configKey,
            @RequestParam(name = "useYn", required = false) String useYn
    ) {
        return queryService.findSystemConfigs(configKey, YnBooleanParser.parseFilter(useYn));
    }
}
