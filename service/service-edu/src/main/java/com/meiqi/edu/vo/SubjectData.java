package com.meiqi.edu.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author Monica
 * @Date 2022/8/3 10:18
 **/
@Data
@ToString
public class SubjectData {

    //一级分类
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    //二级分类
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
