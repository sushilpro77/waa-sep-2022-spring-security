package edu.miu.springdataday3.service.impl;

import edu.miu.springdataday3.entity.Review;
import edu.miu.springdataday3.entity.dto.ReviewDTO;
import edu.miu.springdataday3.repo.ReviewRepo;
import edu.miu.springdataday3.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<ReviewDTO> getAll() {
        List<Review> reviews = reviewRepo.findAll();
        return reviews.stream().map(review -> modelMapper.map(review, ReviewDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO save(ReviewDTO reviewDTO) {
        return modelMapper.map( reviewRepo.save(modelMapper.map(reviewDTO, Review.class)), ReviewDTO.class );
    }

    @Override
    public ReviewDTO update(Long id, ReviewDTO reviewDTO) {
        if (id != reviewDTO.getId()) throw new RuntimeException("Not found");
        return modelMapper.map( reviewRepo.save(modelMapper.map(reviewDTO, Review.class)), ReviewDTO.class );

    }

    @Override
    public ReviewDTO delete(Long id) {
        Review review = reviewRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        reviewRepo.delete(review);
        return modelMapper.map(review, ReviewDTO.class);
    }
}
