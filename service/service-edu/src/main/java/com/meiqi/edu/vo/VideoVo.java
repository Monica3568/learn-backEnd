package com.meiqi.edu.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Monica
 * @Date 2022/8/4 11:46
 **/
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private Boolean free;

}