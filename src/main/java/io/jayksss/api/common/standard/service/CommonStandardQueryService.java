package io.jayksss.api.common.standard.service;

import io.jayksss.api.common.standard.entity.Category;
import io.jayksss.api.common.standard.entity.CodeDetail;
import io.jayksss.api.common.standard.entity.CodeGroup;
import io.jayksss.api.common.standard.entity.FilePolicy;
import io.jayksss.api.common.standard.entity.SystemConfig;
import io.jayksss.api.common.standard.repository.CategoryRepository;
import io.jayksss.api.common.standard.repository.CategorySpecs;
import io.jayksss.api.common.standard.repository.CodeDetailRepository;
import io.jayksss.api.common.standard.repository.CodeDetailSpecs;
import io.jayksss.api.common.standard.repository.CodeGroupRepository;
import io.jayksss.api.common.standard.repository.CodeGroupSpecs;
import io.jayksss.api.common.standard.repository.FilePolicyRepository;
import io.jayksss.api.common.standard.repository.FilePolicySpecs;
import io.jayksss.api.common.standard.repository.SystemConfigRepository;
import io.jayksss.api.common.standard.repository.SystemConfigSpecs;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommonStandardQueryService {
    private final CategoryRepository categoryRepository;
    private final CodeGroupRepository codeGroupRepository;
    private final CodeDetailRepository codeDetailRepository;
    private final FilePolicyRepository filePolicyRepository;
    private final SystemConfigRepository systemConfigRepository;

    public CommonStandardQueryService(
            CategoryRepository categoryRepository,
            CodeGroupRepository codeGroupRepository,
            CodeDetailRepository codeDetailRepository,
            FilePolicyRepository filePolicyRepository,
            SystemConfigRepository systemConfigRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.codeGroupRepository = codeGroupRepository;
        this.codeDetailRepository = codeDetailRepository;
        this.filePolicyRepository = filePolicyRepository;
        this.systemConfigRepository = systemConfigRepository;
    }

    @Transactional(readOnly = true)
    public List<Category> findCategories(
            String categoryType,
            String categoryCode,
            String parentCategoryCode,
            Boolean useYn
    ) {
        return categoryRepository.findAll(
                CategorySpecs.search(categoryType, categoryCode, parentCategoryCode, useYn),
                Sort.by(Sort.Order.asc("sortOrder"), Sort.Order.asc("categoryCode"))
        );
    }

    @Transactional(readOnly = true)
    public List<CodeGroup> findCodeGroups(String groupCode, Boolean useYn) {
        return codeGroupRepository.findAll(
                CodeGroupSpecs.search(groupCode, useYn),
                Sort.by(Sort.Order.asc("groupCode"))
        );
    }

    /**
     * 공통코드 상세 목록. 파라미터 없으면 전체, {@code groupCode} 등으로 필터 가능.
     * 그룹 코드 상수: {@link io.jayksss.api.common.standard.CodeDetailGroupCode}.
     */
    @Transactional(readOnly = true)
    public List<CodeDetail> findCodeDetails(String groupCode, String detailCode, Boolean useYn) {
        return codeDetailRepository.findAll(
                CodeDetailSpecs.search(groupCode, detailCode, useYn),
                Sort.by(Sort.Order.asc("sortOrder"), Sort.Order.asc("detailCode"))
        );
    }

    @Transactional(readOnly = true)
    public List<FilePolicy> findFilePolicies(String policyType, String extension, Boolean useYn) {
        return filePolicyRepository.findAll(
                FilePolicySpecs.search(policyType, extension, useYn),
                Sort.by(Sort.Order.asc("policyType"), Sort.Order.asc("extension"))
        );
    }

    @Transactional(readOnly = true)
    public List<SystemConfig> findSystemConfigs(String configKey, Boolean useYn) {
        return systemConfigRepository.findAll(
                SystemConfigSpecs.search(configKey, useYn),
                Sort.by(Sort.Order.asc("configKey"))
        );
    }
}
