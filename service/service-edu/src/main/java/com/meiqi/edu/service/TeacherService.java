package com.meiqi.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meiqi.edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meiqi.edu.query.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Monica
 * @since 2022-07-27
 */
public interface TeacherService extends IService<EduTeacher> {
    /**
     * 根据讲师名称name，讲师头衔level、讲师入驻时间gmt_create（时间段）查询
     * @param pageParam
     * @param teacherQuery
     */
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}
