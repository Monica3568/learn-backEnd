package com.meiqi.edu.vo;

/**
 * @Author Monica
 * @Date 2022/8/3 14:19
 **/

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//一级分类
@Data
public class OneSubjectVo {
    private String id;
    private String title;

    //一个一级分类有多个二级分类
    private List<TwoSubjectVo> children = new ArrayList<>();

}