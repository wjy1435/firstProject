package com.example.mapper;

import com.example.model.entity.VideoOrder;
import com.example.util.JsonData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoOrderMapper {


    /**
     * 查询该用户 是否购买过此产品
     * @param userId
     * @param videoId
     * @param state
     * @return
     */
    VideoOrder findByUidAndVidAndState(@Param("userId") int userId, @Param("videoId") int videoId,@Param("state") int state);

    /**
     * 下单
     * @param videoOrder
     * @return
     */
    int saveOrder(VideoOrder videoOrder);

    List<VideoOrder> listOrder(@Param("userId") Integer userId);
}
