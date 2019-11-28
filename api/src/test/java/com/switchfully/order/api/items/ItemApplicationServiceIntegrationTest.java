package com.switchfully.order.api.items;

import com.switchfully.order.IntegrationTest;
import com.switchfully.order.domain.items.ItemRepository;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

class ItemApplicationServiceIntegrationTest extends IntegrationTest {

    @Inject
    private ItemApplicationService itemApplicationService;
    @Inject
    private ItemRepository itemRepository;

    @Test
    void createItem() {
        ItemDto itemDto = itemApplicationService.createItem(createAnItem());

        assertThat(itemDto.getId()).isNotNull().isNotEmpty();
        assertThat(itemDto).isEqualToIgnoringGivenFields(createAnItem(), "id", "stockUrgency");
    }

//    @Test
//    void getItem() {
//        ItemDto createdItemDto = new TestRestTemplate()
//                .postForObject(format(HTTP_LOCALHOST + ":%s/%s", getPort(), ItemApplicationService.RESOURCE_NAME),
//                        createAnItem().withAmountOfStock(12), ItemDto.class);
//
//        ItemDto foundItemDto = new TestRestTemplate()
//                .getForObject(format(HTTP_LOCALHOST + ":%s/%s/%s", getPort(), ItemApplicationService.RESOURCE_NAME, createdItemDto.getId()), ItemDto.class);
//
//        assertThat(foundItemDto).isEqualToComparingFieldByField(createdItemDto);
//    }
//
//    @Test
//    void getAllItems() {
//        ItemDto item1 = new TestRestTemplate()
//                .postForObject(format(HTTP_LOCALHOST + ":%s/%s", getPort(), ItemApplicationService.RESOURCE_NAME),
//                        createAnItem().withAmountOfStock(12), ItemDto.class);
//        ItemDto item2 = new TestRestTemplate()
//                .postForObject(format(HTTP_LOCALHOST + ":%s/%s", getPort(), ItemApplicationService.RESOURCE_NAME),
//                        createAnItem().withAmountOfStock(2), ItemDto.class);
//        ItemDto item3 = new TestRestTemplate()
//                .postForObject(format(HTTP_LOCALHOST + ":%s/%s", getPort(), ItemApplicationService.RESOURCE_NAME),
//                        createAnItem().withAmountOfStock(16), ItemDto.class);
//        ItemDto item4 = new TestRestTemplate()
//                .postForObject(format(HTTP_LOCALHOST + ":%s/%s", getPort(), ItemApplicationService.RESOURCE_NAME),
//                        createAnItem().withAmountOfStock(8), ItemDto.class);
//        ItemDto item5 = new TestRestTemplate()
//                .postForObject(format(HTTP_LOCALHOST + ":%s/%s", getPort(), ItemApplicationService.RESOURCE_NAME),
//                        createAnItem().withAmountOfStock(4), ItemDto.class);
//
//        ItemOverviewDto[] items = new TestRestTemplate()
//                .getForObject(format(HTTP_LOCALHOST + ":%s/%s", getPort(), ItemApplicationService.RESOURCE_NAME), ItemOverviewDto[].class);
//
//        assertThat(items).hasSize(5);
//        assertThat(items[0]).usingRecursiveComparison().ignoringFields("description").isEqualTo(item2);
//        assertThat(items[1]).usingRecursiveComparison().ignoringFields("description").isEqualTo(item5);
//        assertThat(items[2]).usingRecursiveComparison().ignoringFields("description").isEqualTo(item4);
//        assertThat(items[3]).usingRecursiveComparison().ignoringFields("description").isEqualTo(item1);
//        assertThat(items[4]).usingRecursiveComparison().ignoringFields("description").isEqualTo(item3);
//    }
//
//    @Test
//    void getAllItems_givenAStockUrgencyFilter_thenOnlyReturnItemsWithThatUrgency() {
//        ItemDto item1 = new TestRestTemplate()
//                .postForObject(format(HTTP_LOCALHOST + ":%s/%s", getPort(), ItemApplicationService.RESOURCE_NAME),
//                        createAnItem().withAmountOfStock(12), ItemDto.class);
//        ItemDto item2 = new TestRestTemplate()
//                .postForObject(format(HTTP_LOCALHOST + ":%s/%s", getPort(), ItemApplicationService.RESOURCE_NAME),
//                        createAnItem().withAmountOfStock(20), ItemDto.class);
//        new TestRestTemplate()
//                .postForObject(format(HTTP_LOCALHOST + ":%s/%s", getPort(), ItemApplicationService.RESOURCE_NAME),
//                        createAnItem().withAmountOfStock(8), ItemDto.class);
//        ItemDto item4 = new TestRestTemplate()
//                .postForObject(format(HTTP_LOCALHOST + ":%s/%s", getPort(), ItemApplicationService.RESOURCE_NAME),
//                        createAnItem().withAmountOfStock(16), ItemDto.class);
//        new TestRestTemplate()
//                .postForObject(format(HTTP_LOCALHOST + ":%s/%s", getPort(), ItemApplicationService.RESOURCE_NAME),
//                        createAnItem().withAmountOfStock(4), ItemDto.class);
//
//        ItemOverviewDto[] items = new TestRestTemplate()
//                .getForObject(format(HTTP_LOCALHOST + ":%s/%s?stockUrgency=STOCK_HIGH", getPort(),
//                        ItemApplicationService.RESOURCE_NAME), ItemOverviewDto[].class);
//
//        assertThat(items).hasSize(3);
//        assertThat(items[0]).usingRecursiveComparison().ignoringFields("description").isEqualTo(item1);
//        assertThat(items[1]).usingRecursiveComparison().ignoringFields("description").isEqualTo(item4);
//        assertThat(items[2]).usingRecursiveComparison().ignoringFields("description").isEqualTo(item2);
//    }
//
//    @Test
//    void updateItem() {
//        Item alreadyExistingItem = itemRepository.save(anItem()
//                .withName("Laptop X10")
//                .withDescription("A fancy laptop")
//                .withAmountOfStock(15)
//                .withPrice(Price.create(BigDecimal.valueOf(1199.95)))
//                .build());
//
//        ItemDto itemToUpdate = new ItemDto()
//                .withId(alreadyExistingItem.getId())
//                .withName(alreadyExistingItem.getName())
//                .withDescription("A very fancy laptop")
//                .withPrice(alreadyExistingItem.getPrice().getAmount().floatValue())
//                .withAmountOfStock(10);
//
//        ResponseEntity<ItemDto> result = new TestRestTemplate()
//                .exchange(format(HTTP_LOCALHOST + ":%s/%s/%s", getPort(), ItemApplicationService.RESOURCE_NAME, alreadyExistingItem.getId().toString()),
//                        HttpMethod.PUT,
//                        new HttpEntity<>(itemToUpdate),
//                        ItemDto.class);
//
//        assertThat(result.getBody()).isEqualToIgnoringGivenFields(itemToUpdate, "stockUrgency");
//    }

    private ItemDto createAnItem() {
        return new ItemDto()
                .withName("Half-Life 3")
                .withDescription("Boehoehoe...")
                .withPrice(45.50f)
                .withAmountOfStock(50510);
    }

}
