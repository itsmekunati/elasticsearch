package org.ark.elasticsearch.repository;

import org.ark.elasticsearch.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product, Integer> {
}
