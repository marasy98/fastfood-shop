package com.example.fast_food.controller;

import com.example.fast_food.entities.Product;
import com.example.fast_food.payload.PagingResponse;
import com.example.fast_food.repositories.ProductRepository;
import com.example.fast_food.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PagingController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/getAccount11")
    public Product find() {
        return productRepository.findByProductId(2L);
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    @GetMapping("/tutorials")
    public ResponseEntity<Map<String, Object>> getAllTutorialsPage(
            @RequestParam(defaultValue = "null") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "productId,desc") String[] sort) {
        System.out.println(search);
        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Product> tutorials = new ArrayList<Product>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Product> pageTuts;
            if(search.equals("null")){
                pageTuts = productRepository.findAll(pagingSort);
            }else{
                search="%"+search+"%";
                pageTuts = productRepository.findByProductName(search,pagingSort);
                System.out.println(pageTuts.getSize());
            }
            List<PagingResponse> pagingResponses=new ArrayList<>();
            tutorials = pageTuts.getContent();
            for (int i = 0; i < tutorials.size(); i++) {
                PagingResponse pagingResponse=new PagingResponse();
                pagingResponse.setId(tutorials.get(i).getProductId());
                pagingResponse.setCategoryName(tutorials.get(i).getCategory().getCategoryName());
                pagingResponse.setDescription(tutorials.get(i).getDescription());
                pagingResponse.setImageName(tutorials.get(i).getImageProduct().getImageName());
                pagingResponse.setPrice(tutorials.get(i).getPrice());
                pagingResponse.setProductName(tutorials.get(i).getProductName());
                pagingResponse.setQuantity(tutorials.get(i).getQuantity());
                pagingResponses.add(pagingResponse);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("pagingResponses", pagingResponses);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            response.put("listCategory", categoryService.findAll());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
