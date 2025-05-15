package com.Geek.service;

import com.Geek.dto.request.OrderRequestDto;
import com.Geek.model.Address;
import com.Geek.model.Order;
import com.Geek.model.Product;
import com.Geek.model.Store;
import com.Geek.model.User;
import com.Geek.model.Voucher;
import com.Geek.repository.AddressRepository;
import com.Geek.repository.OrderRepository;
import com.Geek.repository.ProductRepository;
import com.Geek.repository.UserRepository;
import com.Geek.repository.VoucherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final VoucherRepository voucherRepository;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        UserRepository userRepository,
                        AddressRepository addressRepository,
                        VoucherRepository voucherRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.voucherRepository = voucherRepository;
    }

    @Transactional
    public Order createOrder(OrderRequestDto orderRequest) {

        UUID userId = UUID.fromString(orderRequest.getUserId());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        UUID productId = UUID.fromString(orderRequest.getProductId());
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));

        UUID addressId = UUID.fromString(orderRequest.getAddressId());
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + addressId));

        Store store = product.getStore();
        if (store == null) {
            throw new IllegalStateException("Product has no associated store");
        }

//        Voucher voucher = null;
//        double discountValue = 0.0;
//        if (orderRequest.getVoucherId() != null) {
//            UUID voucherId = UUID.fromString(orderRequest.getVoucherId());
//            voucher = voucherRepository.findById(voucherId)
//                    .orElseThrow(() -> new EntityNotFoundException("Voucher not found with ID: " + voucherId));
//            discountValue = Double.parseDouble(voucher.getDiscountValue());
//        }

        double productPrice = product.getCurrentPrice();
        double totalValue = orderRequest.getQuantity() * productPrice/* - discountValue*/;
        if (totalValue < 0) {
            totalValue = 0;
        }

        // Create Order
        Order order = new Order();
        order.setStatus("NEW");
        order.setSize(orderRequest.getSize());
        order.setColor(orderRequest.getColor());
        order.setQuantity(orderRequest.getQuantity());
        order.setTotalValue(String.format("%.2f", totalValue));
        order.setUser(user);
        order.setAddress(address);
        order.setStore(store);
//        order.setVoucher(voucher);
//        order.setVouchers(voucher != null ? Collections.singletonList(voucher) : new ArrayList<>());
        order.setProduct(Collections.singletonList(product));

        return orderRepository.save(order);
    }
}