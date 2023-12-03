package com.example.lily;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CsvInstruction {
    @CsvBindByName(column = "Order Id")
    private String orderId;
    @CsvBindByName(column = "Item code")
    private String poOrder;
    @CsvBindByName(column = "Quantity")
    private int quantity;
}
