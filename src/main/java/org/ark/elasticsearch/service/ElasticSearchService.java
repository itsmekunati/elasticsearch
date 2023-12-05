package org.ark.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import org.ark.elasticsearch.model.Product;
import org.ark.elasticsearch.util.ElasticSearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public SearchResponse<Map> matchAllService() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        return elasticsearchClient.search(s -> s.query(supplier.get()), Map.class);
    }

    public SearchResponse<Product> matchAllProductsService() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        return elasticsearchClient.search(s -> s.index("products").query(supplier.get()), Product.class);
    }

    public SearchResponse<Product> matchAllProductsByNameService(String field) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.SupplierWithNameField(field);
        return elasticsearchClient.search(s -> s.index("products").query(supplier.get()), Product.class);
    }


}
