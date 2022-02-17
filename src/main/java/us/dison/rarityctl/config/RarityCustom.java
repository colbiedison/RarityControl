package us.dison.rarityctl.config;

import java.util.List;

public class RarityCustom {
    private String name;
    private List<String> items;
    private String color;

    public String getName() {
        return name;
    }

    public List<String> getItems() {
        return items;
    }

    public int getColor() {
        return Integer.parseInt(color, 16);
    }
}
