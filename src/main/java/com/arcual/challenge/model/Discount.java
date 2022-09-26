package com.arcual.challenge.model;

public class Discount {
    private long bundleSize;
    private long price;

    public Discount (long bundleSize, long price) {
        this.bundleSize = bundleSize;
        this.price = price;
    }

    public long getBundleSize() {
        return bundleSize;
    }

    public void setBundleSize(long bundleSize) {
        this.bundleSize = bundleSize;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
