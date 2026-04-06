package io.jayksss.api.common.standard.repository;

import io.jayksss.api.common.standard.entity.FilePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FilePolicyRepository extends JpaRepository<FilePolicy, Long>, JpaSpecificationExecutor<FilePolicy> {
}
