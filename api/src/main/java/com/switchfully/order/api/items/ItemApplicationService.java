package com.switchfully.order.api.items;

import com.switchfully.order.domain.items.Item.StockUrgency;
import com.switchfully.order.service.items.ItemService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
public class ItemApplicationService {

    public static final String RESOURCE_NAME = "items";

    private final ItemService itemService;
    private final ItemMapper itemMapper;
    private final ItemOverviewMapper itemOverviewMapper;

    @Inject
    public ItemApplicationService(ItemService itemService, ItemMapper itemMapper, ItemOverviewMapper itemOverviewMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
        this.itemOverviewMapper = itemOverviewMapper;
    }

    public ItemDto createItem(ItemDto itemDto) {
        return itemMapper.toDto(
                itemService.createItem(
                        itemMapper.toDomain(itemDto)));
    }

    public ItemDto updateItem(String id, ItemDto itemDto) {
        return itemMapper.toDto(
                itemService.updateItem(
                        itemMapper.toDomain(UUID.fromString(id), itemDto)));
    }

    public ItemDto getItem(String id) {
        return itemMapper.toDto(
                itemService.getItem(UUID.fromString(id)));
    }

    public List<ItemOverviewDto> getAllItems(String stockUrgency) {
        List<ItemOverviewDto> allItems = itemService.getAllItems().stream()
                .map(itemOverviewMapper::toDto)
                .sorted(Comparator.comparingInt(ItemOverviewDto::getAmountOfStock))
                .collect(Collectors.toList());
        return filterOnStockUrgency(stockUrgency, allItems);
    }

    private List<ItemOverviewDto> filterOnStockUrgency(String stockUrgency, List<ItemOverviewDto> allItems) {
        if (stockUrgency != null) {
            StockUrgency stockUrgencyToFilterOn = StockUrgency.valueOf(stockUrgency);
            return allItems.stream()
                    .filter(item -> StockUrgency.valueOf(item.getStockUrgency()).equals(stockUrgencyToFilterOn))
                    .collect(Collectors.toList());
        } else {
            return allItems;
        }
    }

}
