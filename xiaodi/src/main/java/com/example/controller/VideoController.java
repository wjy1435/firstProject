package com.example.controller;


import com.example.model.entity.Video;
import com.example.model.entity.VideoBanner;
import com.example.service.VideoService;
import com.example.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {


    @Autowired
    private VideoService videoService;

    /**
     * 视频列表
     * @return
     */
    @RequestMapping("list")
    public Object videoList(){
        List<Video> videoList  = videoService.listVideo();

        return JsonData.buildSuccess(videoList);
    }

    /**
     * 轮播图
     * @return
     */
    @RequestMapping("listBanner")
    public Object videoBannerList(){

        List<VideoBanner> bannerList  = videoService.listBanner();
//        int i = 1/0;
        return JsonData.buildSuccess(bannerList);
    }

    /**
     * 根据id 查询详情
     * @param videoId
     * @return
     */
    @RequestMapping("findDetailById")
    public Object findDetailById(@RequestParam(value = "video_id",required = true) int videoId){

//        List<VideoBanner> bannerList  = videoService.listBanner();

        Video video =videoService.findDetailById(videoId);
        return JsonData.buildSuccess(video);
    }



}
