package uz.pdp.online.lesson_10_1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_10_1.Entity.Hotel;
import uz.pdp.online.lesson_10_1.Repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping
    public List<Hotel> getHotels() {
        final List<Hotel> hotelList = hotelRepository.findAll();
        return hotelList;
    }

    @GetMapping("/{hotelId}")
    public Hotel getHotelById(@PathVariable Integer hotelId){
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isPresent()){
            final Hotel hotel = optionalHotel.get();
            return hotel;
        }
        return null;
    }

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel){
        hotelRepository.save(hotel);
        return "Hotel saved";
    }

    @DeleteMapping("/{hotelId}")
    public String deleteHotel(@PathVariable Integer hotelId){
        try{
           hotelRepository.deleteById(hotelId);
           return "Hotel deleted";
        }catch (Exception e){
            return "Hotel id not found or another error was happened";
        }
    }

    @PutMapping("/{hotelId}")
    public String editHotel(@PathVariable Integer hotelId,@RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isPresent())
        {
            final Hotel editingHotel = optionalHotel.get();
            editingHotel.setName(hotel.getName());
            return "Hotel edited";
        }
        return "Hotel id not found";
    }
}
