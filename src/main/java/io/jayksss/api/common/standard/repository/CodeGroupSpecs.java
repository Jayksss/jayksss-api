package io.jayksss.api.common.standard.repository;

import io.jayksss.api.common.standard.entity.CodeGroup;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public final class CodeGroupSpecs {
    private CodeGroupSpecs() {
    }

    public static Specification<CodeGroup> search(String groupCode, Boolean useYn) {
        return (root, query, cb) -> {
            List<Predicate> p = new ArrayList<>();
            if (groupCode != null && !groupCode.isBlank()) {
                p.add(cb.equal(root.get("groupCode"), groupCode));
            }
            if (useYn != null) {
                p.add(cb.equal(root.get("useYn"), useYn));
            }
            return p.isEmpty() ? cb.conjunction() : cb.and(p.toArray(Predicate[]::new));
        };
    }
}
