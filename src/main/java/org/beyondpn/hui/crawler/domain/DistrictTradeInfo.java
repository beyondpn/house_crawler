package org.beyondpn.hui.crawler.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Created by jianhua on 2016/10/8.
 */
public class DistrictTradeInfo {

    private Date tradeDate;
    private String district;
    private int number;
    private float area;
    private String category;

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("tradeDate", tradeDate)
                .append("district", district)
                .append("number", number)
                .append("area", area)
                .append("category", category)
                .toString();
    }
}
