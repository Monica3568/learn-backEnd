package com.meiqi.edu.service;

import com.meiqi.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meiqi.edu.vo.OneSubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Monica
 * @since 2022-08-03
 */
public interface EduSubjectService extends IService<Subject> {

    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubjectVo> getAllOneTwoSubject();
}
