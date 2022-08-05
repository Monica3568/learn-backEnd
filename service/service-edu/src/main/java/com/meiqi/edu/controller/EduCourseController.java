package com.meiqi.edu.controller;


import com.meiqi.edu.entity.Course;
import com.meiqi.edu.service.EduCourseService;
import com.meiqi.edu.vo.CourseInfoFormVo;
import com.meiqi.edu.vo.CoursePublishVo;
import com.meiqi.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Monica
 * @since 2022-08-03
 */
@RestController
@CrossOrigin //解决跨域问题
@RequestMapping("/eduservice/course")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    //添加课程基本信息方法
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoFormVo courseInfoForm){
        String id = eduCourseService.saveCourseInfo(courseInfoForm);
        return R.ok().data("id",id);
    }
    //根据课程id查询课程基本信息
    @GetMapping("/getCourseInfoById/{courseId}")
    public R getCourseInfoById(@PathVariable String courseId){
        CourseInfoFormVo courseInfoForm = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoForm",courseInfoForm);
    }

    //修改课程信息
    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoFormVo courseInfoFormVo){
        eduCourseService.updateCourseInfo(courseInfoFormVo);
        return R.ok();
    }
    //根据课程id查询课程确认信息
    @GetMapping("/getpublishCourseInfo/{id}")
    public R getpublishCourseInfo(@PathVariable String id){
        CoursePublishVo publishCourseInfo = eduCourseService.getPublishCourseInfo(id);
        return R.ok().data("publishCourse",publishCourseInfo);
    }
    //课程最终发布
//修改课程状态
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        Course course = new Course();
        course.setStatus("Normal"); //设置课程发布状态
        course.setId(id);
        boolean flag = eduCourseService.updateById(course);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

