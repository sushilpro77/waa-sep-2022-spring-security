package edu.miu.springdataday3.controller;

import edu.miu.springdataday3.entity.dto.ReviewDTO;
import edu.miu.springdataday3.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    //get
    @GetMapping
    public List<ReviewDTO> getAll(){
        return reviewService.getAll();
    }

    //post
    @PostMapping
    public ReviewDTO create(ReviewDTO reviewDTO){
        return reviewService.save(reviewDTO);
    }

    //update
    @PutMapping("/{id}")
    public ReviewDTO update(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO){
        return reviewService.update(id, reviewDTO);
    }

    //delete
    @DeleteMapping("/{id}")
    public ReviewDTO delete(@PathVariable Long id){
        return reviewService.delete(id);
    }
}
