package com.example.controller;

import com.example.model.*;
import com.example.repository.CarRepository;
import com.example.repository.UserRepository;
import com.example.service.CarService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
@Controller
@RequestMapping("/jpa/cars")
public class CarController
{
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/view_cars_user")
    public String viewCarsUser(Model model, @AuthenticationPrincipal CustomUserDetails currentUser, RedirectAttributes redirectAttributes) {
        if (currentUser == null || currentUser.getUser() == null)
        {
            redirectAttributes.addFlashAttribute("error", "You must be logged in to view this page.");
            return "redirect:/login";
        }

        User user = currentUser.getUser();
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "User not found.");
            return "redirect:/login";
        }

        if (!user.getRole().equals(UserRole.ROLE_USER)) {
            redirectAttributes.addFlashAttribute("error", "You do not have permission to view this page.");
            return "redirect:/login";
        }

        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        model.addAttribute("user", user);
        return "view_cars_user";
    }

    @GetMapping("/view_cars_editor")
    public String viewCarsEditor(Model model, @AuthenticationPrincipal CustomUserDetails currentUser, RedirectAttributes redirectAttributes) {
        if (currentUser == null || currentUser.getUser() == null) {
            redirectAttributes.addFlashAttribute("error", "You must be logged in to view this page.");
            return "redirect:/login";
        }

        User user = currentUser.getUser();
        if (!user.getRole().equals(UserRole.ROLE_EDITOR))
        {
            redirectAttributes.addFlashAttribute("error", "You do not have permission to view this page.");
            return "redirect:/login";
        }

        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        model.addAttribute("user", user);
        return "view_cars_editor";
    }

    @PostMapping("/add")
    //@PreAuthorize("hasAuthority('ROLE_EDITOR')")
    public String addCar(@ModelAttribute Car car, @AuthenticationPrincipal CustomUserDetails currentUser)
    {
        User user = userRepository.findById(currentUser.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        car.setUser(user);
        carService.addCar(car);
        return "redirect:/jpa/cars/view_cars_editor";
    }
    @PostMapping("/update")
    //@PreAuthorize("hasAuthority('ROLE_EDITOR')")
    public String updateCar(@ModelAttribute Car car, RedirectAttributes redirectAttributes)
    {
        carService.updateCar(car.getRegistrationNumber(), car);
        redirectAttributes.addFlashAttribute("message", "Car updated successfully.");
        return "redirect:/jpa/cars/view_cars_editor";
    }

    @PostMapping("/delete")
    //@PreAuthorize("hasAuthority('ROLE_EDITOR')")
    public String deleteCar(@RequestParam String registrationNumber, RedirectAttributes redirectAttributes)
    {
        try {
            carService.deleteCar(registrationNumber);
            redirectAttributes.addFlashAttribute("message", "Car deleted successfully.");
        } catch (Exception e)
        {
            redirectAttributes.addFlashAttribute("error", "Failed to delete car: " + e.getMessage());
        }
        return "redirect:/jpa/cars/view_cars_editor";
    }
    @GetMapping("/filter")
    public String getFilteredCars(@RequestParam(required = false) String brand,
                                  @RequestParam(required = false) String color,
                                  @RequestParam(required = false) String fuelType,
                                  Model model) {
        CarFiltres filters = new CarFiltres();
        filters.setBrand(brand);
        filters.setColor(color);
        filters.setFuelType(fuelType);

        List<Car> cars = carService.findCarsByFilters(filters);
        model.addAttribute("cars", cars);
        return "view_cars_user";
    }
}

