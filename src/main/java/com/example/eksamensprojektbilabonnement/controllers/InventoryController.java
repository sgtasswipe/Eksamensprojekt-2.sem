package com.example.eksamensprojektbilabonnement.controllers;


import com.example.eksamensprojektbilabonnement.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.eksamensprojektbilabonnement.models.inheritance.Car;
import com.example.eksamensprojektbilabonnement.services.InventoryService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private CarService carService;

    @GetMapping("/inventory")
    public String showInventory(Model model) {
    // TODO Flyt logik til service
        List<Car> cars = inventoryService.getAllCars();
        for (Car car : cars){
            car.setCarType(carService.getCarTypeByChassisNumber(car.getChassisNumber()));
        }
        model.addAttribute("cars", cars);
        return "home/inventory";
    }


    @GetMapping("/sort_and_filter_cars")
    public String SortCars(@RequestParam(required = false) String sortType, @RequestParam(required = false) String filterBy, RedirectAttributes redirectAttributes) {
    // TODO Flyt logik til service
        List<Car> cars;
        if (sortType == null && filterBy == null) {
            return "redirect:/inventory";
        } else if (filterBy == null) {
            filterBy = "ALL";
            cars = inventoryService.setSortCriteria(sortType, filterBy);
        } else if (sortType == null) {
            cars = inventoryService.getFilteredCars(filterBy);
        } else {
            cars = inventoryService.setSortCriteria(sortType, filterBy);
        }
        redirectAttributes.addFlashAttribute("cars", cars);
        return "redirect:/show_inventory_sorted";
    }

    @GetMapping("/show_inventory_sorted")
    public String showInventorySorted(@ModelAttribute("cars") List<Car> cars, Model model) {
        model.addAttribute("cars", cars);
        return "home/inventory";
    }

    @GetMapping("/rented_cars")
    public String showRentedCars(Model model) {
        List<Car> rentedCars = inventoryService.getFilteredCars("RENTED");
        double totalPrice = rentedCars.stream().mapToDouble(Car::getPrice).sum();
        model.addAttribute("rentedCars", rentedCars);
        model.addAttribute("totalPrice", totalPrice);
        return "home/rented_cars";
    }

    @GetMapping("/returned_cars")
    public String showReturnedCars(Model model) {
        List<Car> returnedCars = inventoryService.getFilteredCars("RETURNED");
        model.addAttribute("returnedCars", returnedCars);
        return "home/returned_cars";
    }
}
