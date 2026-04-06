package io.jayksss.api.common.standard.repository;

import io.jayksss.api.common.standard.entity.CodeDetail;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public final class CodeDetailSpecs {
    private CodeDetailSpecs() {
    }

    public static Specification<CodeDetail> search(String groupCode, String detailCode, Boolean useYn) {
        return (root, query, cb) -> {
            List<Predicate> p = new ArrayList<>();
            if (groupCode != null && !groupCode.isBlank()) {
                p.add(cb.equal(root.get("groupCode"), groupCode));
            }
            if (detailCode != null && !detailCode.isBlank()) {
                p.add(cb.equal(root.get("detailCode"), detailCode));
            }
            if (useYn != null) {
                p.add(cb.equal(root.get("useYn"), useYn));
            }
            return p.isEmpty() ? cb.conjunction() : cb.and(p.toArray(Predicate[]::new));
        };
    }
}
