package com.zhaojh.research.business.service;

import com.zhaojh.research.business.model.Order;
import com.zhaojh.research.data.domain.Rule;
import com.zhaojh.research.data.repository.RuleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RuleService {

    RuleRepository ruleRepository;

    public void applyRule(Order order) {
        Rules rules = new Rules();
        List<Rule> ruleList = ruleRepository.findByActiveTrueOrderByPriorityAsc();
        ruleList.stream().forEach(rule -> {
            MVELRule mvelRule = new MVELRule()
                    .name(rule.getName())
                    .description("Rule For " + rule.getName())
                    .priority(rule.getPriority())
                    .when(rule.getCondition())
                    .then(rule.getAction());
            rules.register(mvelRule);
        });

        Facts facts = new Facts();
        facts.put("order", order);
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }
}
