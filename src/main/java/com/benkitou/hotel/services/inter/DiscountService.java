package com.benkitou.hotel.services.inter;

import com.benkitou.hotel.criteria.DiscountCriteria;
import com.benkitou.hotel.dtos.DiscountDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;

import java.util.List;

public interface DiscountService {
    List<DiscountDto> getAllDiscounts(DiscountCriteria discountCriteria);

    DiscountDto getDiscountById(Long id) throws EntityNotFoundException;

    DiscountDto addDiscount(DiscountDto discountDto) throws EntityNotFoundException, EntityAlreadyExistsException;

    DiscountDto updateDiscount(Long id, DiscountDto discountDto) throws EntityNotFoundException;

    ResponseDto deleteDiscount(Long id) throws EntityAlreadyExistsException, EntityNotFoundException;


    DiscountDto modifyDiscountStatusToActive(Long activeStatusId) throws EntityNotFoundException;

    DiscountDto modifyBookingStatusToDesActive(Long desActiveStatusId) throws EntityNotFoundException;
}
