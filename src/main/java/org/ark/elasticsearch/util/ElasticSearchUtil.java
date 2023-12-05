package org.ark.elasticsearch.util;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.val;

import java.util.function.Supplier;

public class ElasticSearchUtil {

    public static Supplier<Query> supplier(){
        return () -> Query.of(q ->q.matchAll(matchAllQuery()));
    }

    public static Supplier<Query> SupplierWithNameField(String fieldName){
        return ()->Query.of(q ->q.match(matchQueryWithName(fieldName)));
    }

    public static MatchAllQuery matchAllQuery() {
        val matchAllQuery = new MatchAllQuery.Builder();
        return matchAllQuery.build();
    }

    public static MatchQuery matchQueryWithName(String fieldName){
        val matchQuery = new MatchQuery.Builder();
        return matchQuery.field("name").query(fieldName).build();
    }
}
