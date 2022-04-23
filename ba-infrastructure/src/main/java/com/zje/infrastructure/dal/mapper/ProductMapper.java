package com.zje.infrastructure.dal.mapper;

import com.zje.infrastructure.dal.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: zje
 * @createDate 2022/4/17
 * @desc
 */
@Repository
public interface ProductMapper extends ElasticsearchRepository<Product,Long> {
}
