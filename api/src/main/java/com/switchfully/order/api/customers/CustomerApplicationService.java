package com.switchfully.order.api.customers;

import com.switchfully.order.service.customers.CustomerService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
public class CustomerApplicationService {

    public static final String RESOURCE_NAME = "customers";

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final CustomerOverviewMapper customerOverviewMapper;

    @Inject
    public CustomerApplicationService(CustomerService customerService, CustomerMapper customerMapper, CustomerOverviewMapper customerOverviewMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.customerOverviewMapper = customerOverviewMapper;
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        return customerMapper.toDto(
                customerService.createCustomer(
                        customerMapper.toDomain(customerDto)));
    }

    public CustomerDto updateCustomer(String id, CustomerDto customerDto) {
        return customerMapper.toDto(
                customerService.updateCustomer(
                        customerMapper.toDomain(UUID.fromString(id), customerDto)));
    }

    public List<CustomerOverviewDto> getAllCustomers() {
        return customerService.getAllCustomers().stream()
                .map(customerOverviewMapper::toDto)
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomer(String id) {
        return customerMapper.toDto(
                customerService.getCustomer(UUID.fromString(id)));
    }

}
