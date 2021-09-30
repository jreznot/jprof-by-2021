package org.jetbrains.gentlewerewolf;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SalesService {
    private final JdbcTemplate jdbcTemplate;

    public SalesService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @NotNull
    public Product findSale(@Nullable User user, String jsonData) {
        return findFirst(user, jsonData);
    }

    private Product findFirst(User user, String data) {
        return null;
    }

    public void updateSales() {
        jdbcTemplate.execute("update SALES set IS_ON_SALE = true where IS_ON_SALE = false");
    }

    public String generateSalesJson() {
        return "{'price' : 100500}";
    }
}
