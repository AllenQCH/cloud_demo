package cn.itcast.order.service;

import cn.itcast.feign.client.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

//    @Autowired
////    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);

//        String url = "http://localhost:8081/user/"+order.getUserId();
//        String url = "http://userservice/user/"+order.getUserId();
//        User user = restTemplate.getForObject(url,User.class);

        //利用feign来发起http请求查询用户，并代替了RestTemplate发起http请求
        User user = userClient.findById(order.getUserId());
        order.setUser(user);
        // 4.返回
        return order;
    }
}
