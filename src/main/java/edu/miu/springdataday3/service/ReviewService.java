package edu.miu.springdataday3.service;

import edu.miu.springdataday3.entity.Review;
import edu.miu.springdataday3.entity.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    //get
    List<ReviewDTO> getAll();

    //post
    ReviewDTO save(ReviewDTO reviewDTO);

    //update
    ReviewDTO update(Long id, ReviewDTO reviewDTO);

    //delete
    ReviewDTO delete(Long id);
}
