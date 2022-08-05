package com.meiqi.edu.controller;


import com.meiqi.edu.entity.Chapter;
import com.meiqi.edu.service.EduChapterService;
import com.meiqi.edu.vo.ChapterVo;
import com.meiqi.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Monica
 * @since 2022-08-03
 */
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;


    //获取课程大纲列表，根据课程id进行查询
    @GetMapping("/getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = eduChapterService.getChapterVideoByCourseId(courseId);

        return R.ok().data("allChapterVideo",list);
    }
    //添加章节
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody Chapter chapter){
        eduChapterService.save(chapter);
        return R.ok();
    }

    //根据章节id查询
    @GetMapping("/getChapter/{chapterId}")
    public R getChapter(@PathVariable String chapterId){
        Chapter chapter = eduChapterService.getById(chapterId);
        return R.ok().data("chapter",chapter);
    }

    //修改章节
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody Chapter chapter){
        eduChapterService.updateById(chapter);
        return R.ok();
    }

    //删除章节
    @DeleteMapping("/deleteById/{chapterId}")
    public R deleteById(@PathVariable String chapterId){
        boolean flag = eduChapterService.deleteChapter(chapterId);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }

    }
}

