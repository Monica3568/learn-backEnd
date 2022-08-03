package com.meiqi.edu.controller;


import com.meiqi.edu.service.EduCourseService;
import com.meiqi.edu.vo.CourseInfoFormVo;
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

}

