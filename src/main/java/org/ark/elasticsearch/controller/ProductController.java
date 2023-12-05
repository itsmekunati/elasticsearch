package org.ark.elasticsearch.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.ark.elasticsearch.model.Product;
import org.ark.elasticsearch.service.ElasticSearchService;
import org.ark.elasticsearch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apis")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/findAll")
    Iterable<Product> findAll() {
        return productService.getProducts();
    }

    @PostMapping("/insert")
    public Product insertProduct(@RequestBody Product product) {
        return productService.insertProduct(product);
    }

    @GetMapping("/matchAll")
    public String matchAll() throws IOException {
        return elasticSearchService.matchAllService().hits().hits().toString();
    }

    @GetMapping("/matchAllProducts")
    public List<Product> matchAllProducts() throws IOException {
        SearchResponse<Product> response = elasticSearchService.matchAllProductsService();
        List<Hit<Product>> listOfHits = response.hits().hits();

        List<Product> productList = new ArrayList<>();
        for (Hit<Product> hit : listOfHits) {
            productList.add(hit.source());
        }
        return productList;
    }

    @GetMapping("/matchByField/{fieldName}")
    public List<Product> matchProductByFieldName(@PathVariable String fieldName) throws IOException {
        SearchResponse<Product> response = elasticSearchService.matchAllProductsByNameService(fieldName);
        List<Product> productList = new ArrayList<>();
        List<Hit<Product>> listOfHits = response.hits().hits();

        for (Hit<Product> hit : listOfHits) {
            productList.add(hit.source());
        }
        return productList;
    }
}
