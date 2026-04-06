package io.jayksss.api.common.standard.repository;

import io.jayksss.api.common.standard.entity.CodeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CodeDetailRepository extends JpaRepository<CodeDetail, Long>, JpaSpecificationExecutor<CodeDetail> {
}
