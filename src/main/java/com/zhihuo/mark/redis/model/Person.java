package com.zhihuo.mark.redis.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Person {

    private String  name;

    private Integer age;

    private String idNo;
}
