package com.zje.infrastructure.service.bo;

import lombok.Data;

@Data
public class BaseBO {

    private String id;
    private String uuid;
    private String createDate;
    private String createBy;
    private String updateDate;
    private String updateBy;
    private Boolean enableFlag;


}
