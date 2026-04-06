package io.jayksss.api.common.standard.repository;

import io.jayksss.api.common.standard.entity.SystemConfig;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public final class SystemConfigSpecs {
    private SystemConfigSpecs() {
    }

    public static Specification<SystemConfig> search(String configKey, Boolean useYn) {
        return (root, query, cb) -> {
            List<Predicate> p = new ArrayList<>();
            if (configKey != null && !configKey.isBlank()) {
                p.add(cb.equal(root.get("configKey"), configKey));
            }
            if (useYn != null) {
                p.add(cb.equal(root.get("useYn"), useYn));
            }
            return p.isEmpty() ? cb.conjunction() : cb.and(p.toArray(Predicate[]::new));
        };
    }
}
