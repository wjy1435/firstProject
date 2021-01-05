package com.example.service.impl;

import com.example.exception.XDException;
import com.example.mapper.*;
import com.example.model.entity.Episode;
import com.example.model.entity.PlayRecord;
import com.example.model.entity.Video;
import com.example.model.entity.VideoOrder;
import com.example.service.VideoOrderService;
import com.example.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {


    @Autowired
    private VideoOrderMapper videoOrderMapper;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoMapper videoMapper;


    @Autowired
    private EpisodeMapper episodeMapper;


    @Autowired
    private PlayRecordMapper playRecordMapper;
    /**
     * 下单操作
     * 未来版本   优惠券折扣    风控用户查询  生成订单基础信息  生成支付信息
     * @param userId
     * @param videoId
     * @return
     */
    @Override
    @Transactional
    public int save(int userId, int videoId) {

        //判断是否已经购买
        VideoOrder videoOrder = videoOrderMapper.findByUidAndVidAndState(userId, videoId, 1);
        if( videoOrder!=null){ //已经购买
            return  0;
        }
       Video video=  videoMapper.findVideoById(videoId);

        VideoOrder videoOrderNew = new VideoOrder();
        videoOrderNew.setCreatTime(new Date());
        videoOrderNew.setOutTradeNo(UUID.randomUUID().toString());
        videoOrderNew.setState(1);
        videoOrderNew.setTotalFee(video.getPrice());
        videoOrderNew.setUserId(userId);
        videoOrderNew.setVideoId(videoId);
        videoOrderNew.setVideoImg(video.getCoverImg());
        videoOrderNew.setVideoTitle(video.getTitle());

        int rows = videoOrderMapper.saveOrder(videoOrderNew);//影响行数


        if(rows == 1){
            Episode episode = episodeMapper.findFirstEpsiodeById(videoId);  //集数

            if(episode == null){
                throw  new XDException(-1,"该视频没有集信息,请运营人员");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
            playRecordMapper.savePlayRecord(playRecord);
        }



        return rows;
    }

    @Override
    public List<VideoOrder> listOrder(Integer id) {

        return videoOrderMapper.listOrder(id);
    }
}
