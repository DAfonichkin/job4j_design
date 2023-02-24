package ru.job4j.ood.lsp.model;

import java.util.Date;

public class Food {
    private final String name;
    private final Date expireDate;
    private final Date createDate;
    private int price;
    private int discount;

    public Food(String name, Date createDate, Date expireDate, int price, int discount) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public int getFreshPercent(Date checkingDate) {
        long timeFromCreateToCheck = checkingDate.getTime() - createDate.getTime();
        long timeFromCreateToExpire = expireDate.getTime() - createDate.getTime();
        return Math.round((float) timeFromCreateToCheck / timeFromCreateToExpire * 100);
    }

}
