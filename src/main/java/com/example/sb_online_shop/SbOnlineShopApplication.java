package com.example.sb_online_shop;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.sb_online_shop.domain.Customer;
import com.example.sb_online_shop.domain.Product;
import com.example.sb_online_shop.domain.Item;

import com.example.sb_online_shop.repository.CustomerRepository;
import com.example.sb_online_shop.domain.Order;
import com.example.sb_online_shop.repository.OrderRepository;
import com.example.sb_online_shop.repository.ItemRepository;
import com.example.sb_online_shop.repository.ProductRepository;

@SpringBootApplication
public class SbOnlineShopApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(SbOnlineShopApplication.class);
	private final CustomerRepository customerRepository;
	private final OrderRepository orderRepository;
	private final ItemRepository itemRepository;
	private final ProductRepository productRepository;


	public SbOnlineShopApplication(
        CustomerRepository customerRepository,
        OrderRepository orderRepository,
        ItemRepository itemRepository,
        ProductRepository productRepository
) {
		this.customerRepository = customerRepository;
		this.orderRepository = orderRepository;
    this.itemRepository = itemRepository;
    this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SbOnlineShopApplication.class, args);
		logger.info("Spring Boot online shop application started successfully at http://localhost:8080");
	}

	@Override
	public void run(String... args) throws Exception {
    // Create some customers
		Customer c1 = new Customer("Bugs Bunny", "New York", "user",  59000);
		Customer c2 = new Customer("Daffy Duck", "Los Angeles", "user", 37000);
		Customer c3 = new Customer("Porky Pig", "Miami", "user", 28000 );
    customerRepository.saveAll(Arrays.asList(c1,c2, c3));

    Order o1 = new Order(c1);
		Order o2 = new Order(c1);
		Order o3 = new Order(c2);
		Order o4 = new Order(c3);
    orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4));

    // Create a product
    Product p1 = new Product("Mangue", "green", "mangue.jpg", 600);
    Product p2 = new Product("Apple", "Red fruit", "apple.jpg", 400);
    Product p3 = new Product("Laitier", "lait", "lait.jpg", 100);
    Product p4 = new Product("Banane", " fruit", "fruit.jpg", 900);
    productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));

    // Create an item
    Item i1 = new Item(4, 100, o1, p1);
    Item i2 = new Item(3, 200, o2, p2);
    Item i3 = new Item(2, 300, o3, p3);
    Item i4 = new Item(7, 400, o4, p4);

    itemRepository.saveAll(Arrays.asList(i1, i2, i3, i4));


		System.out.println("----- All Orders ------");

    for (Order o : orderRepository.findAll()) {
      System.out.println("Customer: " + o.getCustomer().getFullname() + ", Order: " + o.getId() + ", Prix_Total: " + o.getTotal() + "$");

      for (Item item : o.getItems()) {
        System.out.println("\tItem: " + item.getProduct().getName() + ", Quantity: " + item.getQuantity() + ", Price: " + item.getPrice());
      }

      System.out.println("-----             ------");
    }
		System.out.println("-----             ------");

	}

}