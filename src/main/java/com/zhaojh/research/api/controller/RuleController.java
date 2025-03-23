package com.zhaojh.research.api.controller;

import com.zhaojh.research.business.model.Order;
import com.zhaojh.research.business.service.RuleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
@RequestMapping("/rule")
public class RuleController {

    RuleService ruleService;

    @PostMapping("/discount")
    public Order applyDiscount(@RequestBody Order order) {
        ruleService.applyRule(order);
        return order;
    }
}
