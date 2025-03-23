package com.zhaojh.research.data.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rule")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rule {

    @Id
    String id;
    String name;
    String condition;
    String action;
    int priority;
    boolean active;
}
