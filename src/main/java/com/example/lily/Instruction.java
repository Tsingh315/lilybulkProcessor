package com.example.lily;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Instruction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String orderId;
    private String poOrder;
    private int quantity;

    protected Instruction() {}
    public Instruction(CsvInstruction csvInstruction) {
        this.orderId = csvInstruction.getOrderId();
        this.poOrder = csvInstruction.getPoOrder();
        this.quantity = csvInstruction.getQuantity();
    }
}
