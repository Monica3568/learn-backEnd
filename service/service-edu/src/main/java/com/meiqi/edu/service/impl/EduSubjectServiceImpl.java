package com.meiqi.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meiqi.edu.entity.Subject;
import com.meiqi.edu.vo.SubjectData;
import com.meiqi.edu.listener.SubjectExcelListener;
import com.meiqi.edu.mapper.EduSubjectMapper;
import com.meiqi.edu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meiqi.edu.vo.OneSubjectVo;
import com.meiqi.edu.vo.TwoSubjectVo;
import com.meiqi.servicebase.exception.EduException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Monica
 * @since 2022-08-03
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, Subject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            //文件输入流
            InputStream is = file.getInputStream();

            //调用方法进行读取
            EasyExcel.read(is, SubjectData.class, new SubjectExcelListener(eduSubjectService))
                    .sheet().doRead();

        } catch (Exception e) {
            e.printStackTrace();
            throw new EduException(20002, "添加课程分类失败");
        }
    }

    @Override
    public List<OneSubjectVo> getAllOneTwoSubject() {
        //查询出一级分类
        QueryWrapper<Subject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", 0);
        List<Subject> subjectsOne = baseMapper.selectList(wrapperOne);
        //查询出二级分类
        QueryWrapper<Subject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", 0);
        List<Subject> subjectsTwo = baseMapper.selectList(wrapperTwo);
        //创建list集合，用于封装最终数据
        List<OneSubjectVo> finalSubjectList = new ArrayList<>();
        //封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值
        //封装到要求的最终list集合中
        for (int i = 0; i < subjectsOne.size(); i++) {
            //得到一级分类的单项
            Subject subjectOne = subjectsOne.get(i);
            //将一级分类放入最终返回的list  finalSubjectList
            OneSubjectVo oneSubjectVo = new OneSubjectVo();
            BeanUtils.copyProperties(subjectOne,oneSubjectVo);
            finalSubjectList.add(oneSubjectVo);

            ArrayList<TwoSubjectVo> finalTwoSubjects = new ArrayList<>();

            for (int j = 0; j < subjectsTwo.size(); j++) {
                //得到二级分类的单项
                Subject subjectTwo = subjectsTwo.get(j);
                //判断二级分类单项的父目录
                if (subjectTwo.getParentId().equals(subjectOne.getId())){
                    TwoSubjectVo twoSubjectVo = new TwoSubjectVo();
                    BeanUtils.copyProperties(subjectTwo,twoSubjectVo);
                    finalTwoSubjects.add(twoSubjectVo);
                }
            }
            //把一级下面所有二级分类放到oneSubject里面
            oneSubjectVo.setChildren(finalTwoSubjects);
        }
        return finalSubjectList;
    }

}
