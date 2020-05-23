package com.tibiainfo.model.dto.item;

import com.tibiainfo.model.entity.item.Item;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ItemDTO {

    Long id;

    String title;

    String name;

    String plural;

    String article;

    Boolean marketable;

    Boolean stackable;

    Boolean pickupable;

    Integer valueSell;

    Integer valueBuy;

    Double weight;

    String itemClass;

    String type;

    String typeSecondary;

    String flavorText;

    Integer lightColor;

    Integer lightRadius;

    String version;

    Integer clientId;

    Integer timestamp;

    List<ItemAttributeDTO> attributes;

    public ItemDTO(Item item) {
        this(item, true);
    }

    public ItemDTO(Item item, boolean extended) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.type = item.getType();

        if (extended) {
            this.name = item.getName();
            this.plural = item.getPlural();
            this.article = item.getArticle();
            this.marketable = item.getMarketable();
            this.stackable = item.getStackable();
            this.pickupable = item.getPickupable();
            this.valueSell = item.getValueSell();
            this.valueBuy = item.getValueBuy();
            this.weight = item.getWeight();
            this.itemClass = item.getItemClass();
            this.typeSecondary = item.getTypeSecondary();
            this.flavorText = item.getFlavorText();
            this.lightColor = item.getLightColor();
            this.lightRadius = item.getLightRadius();
            this.version = item.getVersion();
            this.clientId = item.getClientId();
            this.timestamp = item.getTimestamp();
            this.attributes = item.getAttributes()
                    .stream()
                    .map(ItemAttributeDTO::new)
                    .collect(Collectors.toList());
        }
    }

}
