package io.jayksss.api.common.standard.repository;

import io.jayksss.api.common.standard.entity.FilePolicy;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public final class FilePolicySpecs {
    private FilePolicySpecs() {
    }

    public static Specification<FilePolicy> search(String policyType, String extension, Boolean useYn) {
        return (root, query, cb) -> {
            List<Predicate> p = new ArrayList<>();
            if (policyType != null && !policyType.isBlank()) {
                p.add(cb.equal(root.get("policyType"), policyType));
            }
            if (extension != null && !extension.isBlank()) {
                p.add(cb.equal(root.get("extension"), extension));
            }
            if (useYn != null) {
                p.add(cb.equal(root.get("useYn"), useYn));
            }
            return p.isEmpty() ? cb.conjunction() : cb.and(p.toArray(Predicate[]::new));
        };
    }
}
