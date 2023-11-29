package com.integrationtest.first.integrationtest;

import com.integrationtest.first.integrationtest.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface h2TestRepository extends JpaRepository<Product,Integer> {
}
