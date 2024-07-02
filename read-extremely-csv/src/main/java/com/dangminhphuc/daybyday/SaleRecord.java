package com.dangminhphuc.daybyday;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaleRecord {
    private String region;
    private String country;
    private String itemType;
    private String salesChannel;
    private String orderPriority;
    private String orderDate;
    private String orderID;
    private String shipDate;
    private String unitsSold;
    private String unitPrice;
    private String unitCost;
    private String totalRevenue;
    private String totalCost;
    private String totalProfit;
}
