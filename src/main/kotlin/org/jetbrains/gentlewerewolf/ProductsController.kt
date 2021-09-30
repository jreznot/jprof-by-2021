package org.jetbrains.gentlewerewolf

import org.springframework.data.annotation.Id
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import java.math.BigDecimal
import java.util.*

@RestController
@RequestMapping("/api/products")
class ProductsController(
    private val productsService: ProductsService,
    private val productsRepository: ProductsRepository,
    private val salesService: SalesService
) {
    @PostMapping
    fun post(@RequestBody product: ProductData): Mono<ResponseEntity<*>> {
        salesService.updateSales()

        return productsRepository.deleteById(UUID.randomUUID())
            .flatMap { productsRepository.save(Product(UUID.randomUUID(), product.price)) }
            .doOnSuccess { salesService.updateSales() }
            .map { ResponseEntity.ok(it) }
    }

    @PostMapping("/process")
    fun process(): Mono<List<Product>> {
        return productsRepository.findAll()
            .toFlux()
            .collectList()
            .map { list ->
                productsService.process(list).asSequence()
                    .filter { it.price.intValueExact() == 100 }
                    .toList()
            }
    }
}

@Service
class ProductsService {
    fun process(products: List<Product>): Sequence<Product> {
        return products.asSequence()
            .filter { it.price.compareTo(BigDecimal.valueOf(100)) == 1 }
    }
}

interface ProductsRepository : R2dbcRepository<Product, UUID>

class Product(
    @Id val id: UUID,
    var price: BigDecimal
)

class ProductData(
    val price: BigDecimal
)