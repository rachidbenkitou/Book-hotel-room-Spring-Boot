package com.benkitou.hotel.services;

import com.benkitou.hotel.criteria.DiscountCriteria;
import com.benkitou.hotel.daos.DiscountRepository;
import com.benkitou.hotel.daos.specifications.DiscountSpecification;
import com.benkitou.hotel.dtos.DiscountDto;
import com.benkitou.hotel.dtos.ResponseDto;
import com.benkitou.hotel.entities.Discount;
import com.benkitou.hotel.exceptions.EntityAlreadyExistsException;
import com.benkitou.hotel.exceptions.EntityNotFoundException;
import com.benkitou.hotel.exceptions.EntityServiceException;
import com.benkitou.hotel.mappers.DiscountMapper;
import com.benkitou.hotel.services.inter.DiscountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    @Override
    public List<DiscountDto> getAllDiscounts(DiscountCriteria discountCriteria) {
        try {
            DiscountSpecification discountSpecification = new DiscountSpecification(discountCriteria);
            return discountMapper.modelsToDtos(discountRepository.findAll(discountSpecification));

        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving discounts.", e);
        }
    }

    @Override
    public DiscountDto getDiscountById(Long id) throws EntityNotFoundException {
        try {
            DiscountCriteria discountCriteria = DiscountCriteria.builder().id(id).build();

            List<DiscountDto> discountDtos = getAllDiscounts(discountCriteria);
            return discountDtos.stream()
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(String.format("The discount with the id %d is not found.", id)));
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while retrieving discount.", e);
        }
    }

    @Override
    public DiscountDto addDiscount(DiscountDto discountDto) throws EntityNotFoundException, EntityAlreadyExistsException {
        discountDto.setId(null);

        if (discountRepository.existsByType(discountDto.getType())) {
            throw new EntityAlreadyExistsException(String.format("The discount with type %s is already exists.", discountDto.getType()));
        }

        try {
            Discount savedDiscount = discountRepository.save(discountMapper.dtoToModel(discountDto));
            return discountMapper.modelToDto(savedDiscount);
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while storing the discount.", e);
        }
    }

    @Override
    public DiscountDto updateDiscount(Long id, DiscountDto discountDto) throws EntityNotFoundException {
        // Use Optional for clarity
        DiscountDto existingDiscount = Optional.ofNullable(getDiscountById(id))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Discount with ID %d not found.", id)));

        // Ensure ID consistency
        if (!existingDiscount.getId().equals(discountDto.getId())) {
            throw new IllegalArgumentException("The provided ID and DTO ID do not match.");
        }

        try {
            Discount updatedDiscount = discountRepository.save(discountMapper.dtoToModel(discountDto));
            return discountMapper.modelToDto(updatedDiscount);
        } catch (Exception e) {
            // Handle any exceptions during the update process
            throw new EntityServiceException("An error occurred while updating the discount.", e);
        }
    }

    @Override
    public ResponseDto deleteDiscount(Long id) throws EntityAlreadyExistsException, EntityNotFoundException {
        if (!discountRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Discount with ID %d not found.", id));
        }
        try {
            discountRepository.deleteById(id);
            return ResponseDto.builder()
                    .message("Discount deleted successfully.")
                    .status(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while deleting the discount.", e);
        }
    }

    private Discount retrieveDiscountById(Long id) {
        return discountRepository.findById(id)
                .orElseThrow(() -> new EntityServiceException(String.format("The discount with id %d not found.", id)));
    }

    @Override
    public DiscountDto modifyDiscountStatusToActive(Long id) throws EntityNotFoundException {
        try {
            Discount discount = retrieveDiscountById(id);
            discount.setIsActive(true);
            return discountMapper.modelToDto(discountRepository.save(discount));
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while modifying discount status to active.", e);

        }
    }

    @Override
    public DiscountDto modifyBookingStatusToDesActive(Long id) throws EntityNotFoundException {
        try {
            Discount discount = retrieveDiscountById(id);
            discount.setIsActive(false);
            return discountMapper.modelToDto(discountRepository.save(discount));
        } catch (Exception e) {
            throw new EntityServiceException("An error occurred while modifying discount status to inactive.", e);

        }
    }
}
