package uz.pdp.online.lesson_10_1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_10_1.Entity.Hotel;
import uz.pdp.online.lesson_10_1.Entity.Room;
import uz.pdp.online.lesson_10_1.Payload.RoomDto;
import uz.pdp.online.lesson_10_1.Repository.HotelRepository;
import uz.pdp.online.lesson_10_1.Repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/room")
public class RoomController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/byHotelId/{hotelId}")
    public Page<Room> getRoomsByHotel(@RequestParam int page, @PathVariable Integer hotelId) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<Room> allByHotelId = roomRepository.findAllByHotelId(hotelId, pageable);
        return allByHotelId;
    }

    @GetMapping
    public List<Room> getRooms() {
        List<Room> roomList = roomRepository.findAll();
        return roomList;
    }

    @GetMapping("/{roomId}")
    public Room getRoomById(@PathVariable Integer roomId) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            final Room room = optionalRoom.get();
            return room;
        }
        return null;
    }

    @PostMapping()
    public String addRoom(@RequestBody RoomDto roomDto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()) {
            return "Hotel id not found";
        }
        Hotel hotel = optionalHotel.get();
        Room room = new Room();
        room.setHotel(hotel);
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        room.setNumber(roomDto.getNumber());

        roomRepository.save(room);
        return "Room added";
    }

    @DeleteMapping("/{roomId}")
    public String deleteRoom(@PathVariable Integer roomId) {
        roomRepository.deleteById(roomId);
        return "Room deleted";
    }

    @PutMapping("/{roomId}")
    public String editRoom(@PathVariable Integer roomId, @RequestBody RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (!optionalRoom.isPresent()) {
            return "Room id not found";
        }
        Room room = optionalRoom.get();

        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()) {
            return "Hotel id not found";
        }
        Hotel hotel = optionalHotel.get();
        room.setHotel(hotel);
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        roomRepository.save(room);
        return "Room changes saved";
    }

}
