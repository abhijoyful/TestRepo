package com.baeldung.hexarchitecture.service;

import com.baeldung.hexarchitecture.domain.Order;

/**
 * Shopping service interface to handle requests from controllers.
 */
public interface ShoppingServicePort {

    public Order placeOrder(String productName);

    public void updateInventory();

}
