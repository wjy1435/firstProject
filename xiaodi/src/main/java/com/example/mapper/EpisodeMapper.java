package com.example.mapper;


import com.example.model.entity.Episode;
import org.apache.ibatis.annotations.Param;

public interface EpisodeMapper {


    Episode findFirstEpsiodeById(@Param("videoId") int videoId);
}
