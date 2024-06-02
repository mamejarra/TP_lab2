
package com.example.sb_online_shop.repository;
import org.springframework.data.repository.CrudRepository;
import com.example.sb_online_shop.domain.Order;

public interface OrderRepository extends CrudRepository<Order,Long>{
    
}
