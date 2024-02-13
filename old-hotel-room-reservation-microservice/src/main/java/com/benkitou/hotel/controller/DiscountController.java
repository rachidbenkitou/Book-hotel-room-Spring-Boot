package com.benkitou.hotel.controller;

import com.benkitou.hotel.criteria.DiscountCriteria;
import com.benkitou.hotel.dtos.DiscountDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.services.inter.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @PostMapping("/searchBySpecification")
    public ResponseEntity<List<DiscountDto>> searchDiscounts(@RequestBody DiscountCriteria discountCriteria) {
        List<DiscountDto> discounts = discountService.getAllDiscounts(discountCriteria);
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountDto> getDiscountById(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return new ResponseEntity<>(discountService.getDiscountById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DiscountDto> addDiscount(@RequestBody DiscountDto discountDto) throws EntityAlreadyExistsException, EntityNotFoundException {
        return new ResponseEntity<>(discountService.addDiscount(discountDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscountDto> updateDiscountById(@PathVariable(name = "id") Long id, @RequestBody DiscountDto discountDto) throws EntityNotFoundException {
        return new ResponseEntity<>(discountService.updateDiscount(id, discountDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteDiscount(@PathVariable(name = "id") Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        return new ResponseEntity<>(discountService.deleteDiscount(id), HttpStatus.OK);
    }

    @PatchMapping("/{discountId}/activate")
    public ResponseEntity<DiscountDto> modifyDiscountStatusToActive(@PathVariable Long discountId) throws EntityNotFoundException {
        DiscountDto updatedDiscount = discountService.modifyDiscountStatusToActive(discountId);
        return ResponseEntity.ok(updatedDiscount);
    }

    @PatchMapping("/{discountId}/deactivate")
    public ResponseEntity<DiscountDto> modifyDiscountStatusToDeactivate(@PathVariable Long discountId) throws EntityNotFoundException {
        DiscountDto updatedDiscount = discountService.modifyBookingStatusToDesActive(discountId);
        return ResponseEntity.ok(updatedDiscount);
    }
}
