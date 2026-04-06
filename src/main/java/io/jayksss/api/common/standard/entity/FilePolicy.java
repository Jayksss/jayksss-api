package io.jayksss.api.common.standard.entity;

import io.jayksss.api.common.jpa.converter.YnBooleanConverter;
import io.jayksss.api.common.jpa.entity.BaseDateEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(catalog = "common", name = "file_policy")
public class FilePolicy extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id", nullable = false)
    private Long policyId;

    @Column(name = "policy_type", length = 50, nullable = false)
    private String policyType;

    @Column(name = "extension", length = 20, nullable = false)
    private String extension;

    @Column(name = "mime_type", length = 100)
    private String mimeType;

    @Column(name = "max_size_mb", nullable = false)
    private Integer maxSizeMb = 10;

    @Convert(converter = YnBooleanConverter.class)
    @Column(name = "use_yn", length = 1, nullable = false)
    private Boolean useYn = true;

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Integer getMaxSizeMb() {
        return maxSizeMb;
    }

    public void setMaxSizeMb(Integer maxSizeMb) {
        this.maxSizeMb = maxSizeMb;
    }

    public Boolean getUseYn() {
        return useYn;
    }

    public void setUseYn(Boolean useYn) {
        this.useYn = useYn;
    }
}
