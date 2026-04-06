package io.jayksss.api.common.standard.repository;

import io.jayksss.api.common.standard.entity.Category;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public final class CategorySpecs {
    private CategorySpecs() {
    }

    public static Specification<Category> search(
            String categoryType,
            String categoryCode,
            String parentCategoryCode,
            Boolean useYn
    ) {
        return (root, query, cb) -> {
            List<Predicate> p = new ArrayList<>();
            if (categoryType != null && !categoryType.isBlank()) {
                p.add(cb.equal(root.get("categoryType"), categoryType));
            }
            if (categoryCode != null && !categoryCode.isBlank()) {
                p.add(cb.equal(root.get("categoryCode"), categoryCode));
            }
            if (parentCategoryCode != null && !parentCategoryCode.isBlank()) {
                p.add(cb.equal(root.get("parentCategoryCode"), parentCategoryCode));
            }
            if (useYn != null) {
                p.add(cb.equal(root.get("useYn"), useYn));
            }
            return p.isEmpty() ? cb.conjunction() : cb.and(p.toArray(Predicate[]::new));
        };
    }
}
