package com.zje.infrastructure.dal.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author: zje
 * @createDate 2022/4/17
 * @desc
 */
@Data
@Document(indexName = "product")
public class Product {

    // 主键
    @Id
    private Long id;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Keyword)
    private String category;
    @Field(type = FieldType.Double)
    private Double price;
    // 不能被分开且不能被查询
    @Field(type = FieldType.Keyword,index = false)
    private String images;


}
