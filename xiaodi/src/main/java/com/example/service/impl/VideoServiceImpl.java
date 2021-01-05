package com.example.service.impl;


import com.example.config.CacheKeyManager;
import com.example.model.entity.Video;
//import com.example.service.VidelService;
import com.example.model.entity.VideoBanner;
import com.example.mapper.VideoMapper;
import com.example.service.VideoService;
import com.example.util.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class VideoServiceImpl implements VideoService {


    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;

    @Override
    public List<Video> listVideo() {

        try {
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_LIST,()->{
                List<Video> videoList = videoMapper.listVideo();
                System.out.println("数据库中找视频列表");
                return   videoList;
            });
            if(cacheObj instanceof List){   // 如果找到了   直接返回
                List<Video> videoList = (List<Video>) cacheObj;
                return  videoList;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<VideoBanner> listBanner() {


        try {   //缓存中没有从 数据库中查找
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY,()->{
                List<VideoBanner> bannerList = videoMapper.listBanner();
                System.out.println("数据库中找轮播图");
                return   bannerList;
            });

            if(cacheObj instanceof List){   // 如果找到了   直接返回
                List<VideoBanner> bannerList = (List<VideoBanner>) cacheObj;
                return  bannerList;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return  null;
    }

    @Override
    public Video findDetailById(int videoId) {

        try {
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_DETAIL,()->{
                Video video = videoMapper.findDetailById(videoId);
                System.out.println("数据库中找视频详情");
                return   video;
            });
            if(cacheObj instanceof  Video){
                Video video = (Video) cacheObj;
                return  video;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

}
