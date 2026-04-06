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
@Table(catalog = "common", name = "code_detail")
public class CodeDetail extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id", nullable = false)
    private Long detailId;

    @Column(name = "group_code", length = 50, nullable = false)
    private String groupCode;

    @Column(name = "detail_code", length = 50, nullable = false)
    private String detailCode;

    @Column(name = "detail_name", length = 100, nullable = false)
    private String detailName;

    @Column(name = "detail_desc", length = 255)
    private String detailDesc;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;

    @Column(name = "extra_attr1", length = 100)
    private String extraAttr1;

    @Column(name = "extra_attr2", length = 100)
    private String extraAttr2;

    @Convert(converter = YnBooleanConverter.class)
    @Column(name = "use_yn", length = 1, nullable = false)
    private Boolean useYn = true;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getExtraAttr1() {
        return extraAttr1;
    }

    public void setExtraAttr1(String extraAttr1) {
        this.extraAttr1 = extraAttr1;
    }

    public String getExtraAttr2() {
        return extraAttr2;
    }

    public void setExtraAttr2(String extraAttr2) {
        this.extraAttr2 = extraAttr2;
    }

    public Boolean getUseYn() {
        return useYn;
    }

    public void setUseYn(Boolean useYn) {
        this.useYn = useYn;
    }
}
