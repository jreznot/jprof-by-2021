package org.jetbrains.gentlewerewolf;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JsonTests {
    private final SalesService salesService;

    public JsonTests(@Autowired SalesService salesService) {
        this.salesService = salesService;
    }

    @Test
    public void testJson() throws Exception {
        JSONAssert.assertEquals(
                "{'price': 100}",
                salesService.generateSalesJson(),
                false
        );
    }
}
