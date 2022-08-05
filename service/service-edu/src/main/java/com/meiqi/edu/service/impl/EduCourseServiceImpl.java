package com.meiqi.edu.service.impl;

import com.meiqi.edu.entity.Course;
import com.meiqi.edu.entity.CourseDescription;
import com.meiqi.edu.mapper.EduCourseMapper;
import com.meiqi.edu.service.EduCourseDescriptionService;
import com.meiqi.edu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meiqi.edu.vo.CourseInfoFormVo;
import com.meiqi.edu.vo.CoursePublishVo;
import com.meiqi.servicebase.exception.EduException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    @Resource
    private EduCourseMapper eduCourseMapper;


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

    @Override
    public CourseInfoFormVo getCourseInfo(String courseId) {
        Course course = baseMapper.selectById(courseId);
        CourseInfoFormVo courseInfoFormVo = new CourseInfoFormVo();
        BeanUtils.copyProperties(course,courseInfoFormVo);

        CourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoFormVo.setDescription(courseDescription.getDescription());
        return courseInfoFormVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoFormVo courseInfoFormVo) {
        //1、修改课程表
        Course eduCourse = new Course();
        BeanUtils.copyProperties(courseInfoFormVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update <= 0){
            throw new EduException(20001,"修改课程信息失败");
        }

        //2、修改描述信息
        CourseDescription CourseDescription = new CourseDescription();
        CourseDescription.setDescription(courseInfoFormVo.getDescription());
        CourseDescription.setId(courseInfoFormVo.getId());
        eduCourseDescriptionService.updateById(CourseDescription);
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        return eduCourseMapper.getPublishCourseInfo(courseId);
    }

}
