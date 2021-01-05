package com.example.controller;


import com.example.model.entity.VideoOrder;
import com.example.model.request.VideOrderRequest;
import com.example.service.VideoOrderService;
import com.example.service.VideoService;
import com.example.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/pri/order")
public class VideoOrderController {


    @Autowired
    private VideoOrderService videoOrderService;


    /**
     * 下单
     * @param videOrderRequest
     * @param request
     * @return
     */
    @RequestMapping("save")
    public JsonData saveOrder(@RequestBody VideOrderRequest videOrderRequest, HttpServletRequest request) {

        Integer userId = (Integer) request.getAttribute("id");

        int rows = videoOrderService.save(userId, videOrderRequest.getVideoId());

        return rows == 0 ? JsonData.buildError("下单失败") : JsonData.buildSuccess();
    }

    /**
     * 查询 订单列表
     * @return
     */

    @RequestMapping("list")
    public JsonData listOrder(HttpServletRequest request) {
//        Integer id = (Integer) request.getAttribute("id ");//用户id
        Integer id = (Integer) request.getAttribute("id");
        List<VideoOrder> listOrder = videoOrderService.listOrder(id);

        return JsonData.buildSuccess(listOrder);
    }
}
