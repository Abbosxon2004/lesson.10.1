package uz.pdp.online.lesson_10_1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.online.lesson_10_1.Entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
