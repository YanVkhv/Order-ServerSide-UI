package com.switchfully.order.api.orders;

import com.switchfully.order.api.orders.dtos.OrderAfterCreationDto;
import com.switchfully.order.api.orders.dtos.OrderCreationDto;
import com.switchfully.order.api.orders.dtos.OrderDto;
import com.switchfully.order.api.orders.dtos.reports.OrdersReportDto;
import com.switchfully.order.service.orders.OrderService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
public class OrderApplicationService {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Inject
    public OrderApplicationService(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    public List<OrderDto> getAllOrders(boolean onlyIncludeShippableToday) {
        return orderService.getAllOrders(onlyIncludeShippableToday).stream()
                .map(order -> orderMapper.toDto(order))
                .collect(Collectors.toList());
    }

    public OrderAfterCreationDto createOrder(OrderCreationDto orderDto) {
        return orderMapper.toOrderAfterCreationDto(
                orderService.createOrder(
                        orderMapper.toDomain(orderDto)));
    }

    public OrderAfterCreationDto reorderOrder(String id) {
        return orderMapper.toOrderAfterCreationDto(
                orderService.reorderOrder(UUID.fromString(id)));
    }

    public OrdersReportDto getOrdersForCustomerReport(String customerId) {
        return orderMapper.toOrdersReportDto(
                orderService.getOrdersForCustomer(UUID.fromString(customerId)));
    }

}
