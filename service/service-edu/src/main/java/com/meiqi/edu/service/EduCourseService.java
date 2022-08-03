package com.meiqi.edu.service;

import com.meiqi.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meiqi.edu.vo.CourseInfoFormVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Monica
 * @since 2022-08-03
 */
public interface EduCourseService extends IService<Course> {
    //添加课程基本信息方法
    String saveCourseInfo(CourseInfoFormVo courseInfoForm);
}

