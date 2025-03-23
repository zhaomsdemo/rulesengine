package com.zhaojh.research.data.repository;

import com.zhaojh.research.data.domain.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends MongoRepository<Rule, String> {

    List<Rule> findByActiveTrueOrderByPriorityAsc();
}
