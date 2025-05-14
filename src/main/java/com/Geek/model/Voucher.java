package com.Geek.model;

import java.time.LocalDate;
import java.time.Instant;
import java.util.UUID;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "vouchers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    private String name;
    private String discountValue;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private Order order;

    @ManyToMany(mappedBy = "vouchers")
    private List<User> users = new ArrayList<>();
}
