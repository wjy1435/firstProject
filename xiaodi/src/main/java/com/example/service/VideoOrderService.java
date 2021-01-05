package com.example.service;

import com.example.model.entity.VideoOrder;
import com.example.util.JsonData;

import java.util.List;

public interface VideoOrderService {

    int save(int userId,int videoId);

    List<VideoOrder>  listOrder(Integer id);
}
