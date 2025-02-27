package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public abstract class BaseModel {
    private String id;
    private String name;
    private int quantity;

    public BaseModel() {
        this.id = UUID.randomUUID().toString();
    }
}
