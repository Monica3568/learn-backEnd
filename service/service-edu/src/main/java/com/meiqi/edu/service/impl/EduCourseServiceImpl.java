package com.meiqi.edu.service.impl;

import com.meiqi.edu.entity.Course;
import com.meiqi.edu.entity.CourseDescription;
import com.meiqi.edu.mapper.EduCourseMapper;
import com.meiqi.edu.service.EduCourseDescriptionService;
import com.meiqi.edu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meiqi.edu.vo.CourseInfoFormVo;
import com.meiqi.servicebase.exception.EduException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Monica
 * @since 2022-08-03
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, Course> implements EduCourseService {

    //课程描述注入
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoFormVo courseInfoForm) {
        //向课程表里面添加课程基本信息
        //CourseInfoForm对象 转换成 EduCourse对象
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);
        int result = baseMapper.insert(course);

        if (result <= 0) {//表示添加失败
            throw new EduException(20001, "添加课程信息失败");
        }
        //获取添加之后课程信息的id
        String id = course.getId();
        //想课程简介表里面添加课程简介
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        //手动设置描述课程表的id，与上面的课程信息表id关联
        courseDescription.setId(id);
        eduCourseDescriptionService.save(courseDescription);
        return id;
    }
}
