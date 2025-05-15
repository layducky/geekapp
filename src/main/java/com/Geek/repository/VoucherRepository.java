package com.Geek.repository;

import com.Geek.model.Voucher;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, UUID> {
}