package com.meiqi.edu.controller;


import com.meiqi.edu.entity.Video;
import com.meiqi.edu.service.EduVideoService;
import com.meiqi.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Monica
 * @since 2022-08-03
 */
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    //添加小节
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody Video video){
        eduVideoService.save(video);
        return R.ok();
    }


    //删除小节
    // TODO 后面这个方法需要完善，删除小节的时候，同时也要把视频删除
    @DeleteMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        eduVideoService.removeById(id);
        return R.ok();
    }

    //修改小节
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody Video video){
        eduVideoService.updateById(video);
        return R.ok();
    }

    //根据小节id查询
    @GetMapping("/getVideoById/{videoId}")
    public R getVideoById(@PathVariable String videoId){
        Video video = eduVideoService.getById(videoId);
        return R.ok().data("video",video);
    }
}

