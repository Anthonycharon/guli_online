package com.doux.edu.POJO.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangTao
 * @create 2023-02-26-18:37
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> children = new ArrayList<>();
}
