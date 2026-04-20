package com.course_genie.car;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {

    private final CarService carService;

    @GetMapping("/section/{sectionId}")
    public CarDTO getCarBySection(@PathVariable Long sectionId) {
        return carService.getCarBySection(sectionId);
    }

    @PutMapping("/update")
    public CarDTO updateCar(@RequestBody CarDTO carDto) {
        return carService.updateCarReflections(carDto);
    }
    @GetMapping("/section/{sectionId}/html")
    public String getCarHtml(@PathVariable Long sectionId) {
        return carService.generateCarHtml(sectionId);
    }
}