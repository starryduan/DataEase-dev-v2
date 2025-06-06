package io.dataease.model;

import io.dataease.constant.CommonConstants;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class BusiNodeRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 3859379188035689316L;

    private String busiFlag;

    private String id;

    private Boolean leaf;

    private Integer weight;

    private String sortType;

    private String resourceTable;
}
