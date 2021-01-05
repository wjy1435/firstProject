package com.example.service;

import com.example.model.entity.Video;
import com.example.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {


    /**
     *  全部视频列表
     * */
    List<Video> listVideo();

    List<VideoBanner> listBanner();

    Video findDetailById(int videoId);
}
