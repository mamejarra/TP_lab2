package com.example.sb_online_shop.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity(name = "Orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private double total;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
  private List<Item> items;

  public Order() {
  }

  public Order(Customer customer) {
    this.total = total;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
    this.customer = customer;
  }

  public double getTotal() {
    total = 0.0;
    for (Item item : items) {
      total += item.getPrice() * item.getQuantity();
    }
    return total;
  }

  /*
  public void setTotal(double total) {
    this.total = total;
  }*/

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }
}
