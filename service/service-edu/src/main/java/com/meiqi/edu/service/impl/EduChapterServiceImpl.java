package com.meiqi.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meiqi.edu.entity.Chapter;
import com.meiqi.edu.entity.Video;
import com.meiqi.edu.mapper.EduChapterMapper;
import com.meiqi.edu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meiqi.edu.service.EduVideoService;
import com.meiqi.edu.vo.ChapterVo;
import com.meiqi.edu.vo.VideoVo;
import com.meiqi.servicebase.exception.EduException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Monica
 * @since 2022-08-03
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, Chapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        QueryWrapper<Chapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<Chapter> chapters = baseMapper.selectList(wrapperChapter);

        QueryWrapper<Video> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<Video> videos = videoService.list(wrapperVideo);

        ArrayList<ChapterVo> finalChapterVos  = new ArrayList<>();

        for (int i = 0; i < chapters.size(); i++) {
            Chapter chapter = chapters.get(i);

            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            finalChapterVos.add(chapterVo);

            //填充课时vo对象
            ArrayList<VideoVo> finalVideoVos = new ArrayList<>();
            for (int j = 0; j < videos.size(); j++) {
                Video video = videos.get(j);

                if (chapter.getId().equals(video.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    finalChapterVos.add(chapterVo);
                }
            }
            chapterVo.setChildren(finalVideoVos);
        }
        return finalChapterVos;
    }

    //删除章节的方法
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapter章节id 查询查询小节表，如果查询有数据，则不删除
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        //判断
        if (count>0){
            //能查询出来小节，不进行删除
            throw new EduException(20001,"还有小节数据，不能删除");
        }else {
            //不能查询出小节，进行删除
            int delete = baseMapper.deleteById(chapterId);
            return delete>0;
        }
    }
}
