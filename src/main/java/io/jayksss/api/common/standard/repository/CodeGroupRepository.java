package io.jayksss.api.common.standard.repository;

import io.jayksss.api.common.standard.entity.CodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CodeGroupRepository extends JpaRepository<CodeGroup, Long>, JpaSpecificationExecutor<CodeGroup> {
}
