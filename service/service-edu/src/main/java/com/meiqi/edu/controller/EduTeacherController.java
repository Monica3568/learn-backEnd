package com.meiqi.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meiqi.edu.entity.EduTeacher;
import com.meiqi.edu.query.TeacherQuery;
import com.meiqi.edu.service.TeacherService;
import com.meiqi.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Monica
 * @since 2022-07-27
 */
@Api(description = "讲师管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private TeacherService teacherService;


    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public R list() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }


    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        teacherService.removeById(id);
        return R.ok();
    }


    @ApiOperation(value = "分页讲师列表")
    @PostMapping("/pageTeacherCondition/{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                    TeacherQuery teacherQuery) {
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        teacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增教师")
    @PostMapping("/add")
    public R add(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        teacherService.save(teacher);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @PostMapping("/getById/{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师Id", required = true)
            @PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("item",teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("/updateById/{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师Id", required = true)
            @PathVariable String id,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        teacher.setId(id);
        teacherService.updateById(teacher);
        return R.ok();
    }

}

