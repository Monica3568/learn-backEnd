package com.meiqi.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meiqi.edu.entity.EduTeacher;
import com.meiqi.edu.mapper.TeacherMapper;
import com.meiqi.edu.query.TeacherQuery;
import com.meiqi.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Monica
 * @since 2022-07-27
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, EduTeacher> implements TeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        if (null == teacherQuery){
            baseMapper.selectPage(pageParam,wrapper);
            return;
        }
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(name)){
            wrapper.like("level",level);
        }
        if (!StringUtils.isEmpty(name)){
            wrapper.like("begin",begin);
        }
        if (!StringUtils.isEmpty(name)){
            wrapper.like("end",end);
        }
        baseMapper.selectPage(pageParam,wrapper);
    }
}
