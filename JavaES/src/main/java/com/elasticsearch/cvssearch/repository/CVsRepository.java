package com.elasticsearch.cvssearch.repository;

import com.elasticsearch.cvssearch.model.Cvs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CVsRepository extends ElasticsearchRepository<Cvs,String> {

    List<Cvs> findByCv(String langage);
}
