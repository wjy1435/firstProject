package com.example.mapper;

import com.example.model.entity.Video;
import com.example.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface VideoMapper {

    /**查询全部  视频
     * */
    List<Video> listVideo();

    List<VideoBanner> listBanner();

    /**
     * 根据id 查询详情
     * @param videoId
     * @return
     */
    Video findDetailById(@Param("vId") int videoId);

    Video findVideoById(int videoId);
}
