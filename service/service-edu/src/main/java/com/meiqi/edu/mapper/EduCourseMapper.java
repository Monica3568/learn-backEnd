package com.meiqi.edu.mapper;

import com.meiqi.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meiqi.edu.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Monica
 * @since 2022-08-03
 */
public interface EduCourseMapper extends BaseMapper<Course> {
    public  CoursePublishVo getPublishCourseInfo(String courseId);
}
