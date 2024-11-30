package by.specific.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.*;


@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "amount")
    private int amount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public  Purchase(){}

    public Purchase(String name, String model, int amount) {
        this.name = name;
        this.model = model;
        this.amount = amount;
    }
}
