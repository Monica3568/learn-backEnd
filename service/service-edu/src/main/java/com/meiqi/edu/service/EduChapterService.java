package com.meiqi.edu.service;

import com.meiqi.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meiqi.edu.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Monica
 * @since 2022-08-03
 */
public interface EduChapterService extends IService<Chapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);
}
