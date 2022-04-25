package uz.pdp.online.lesson_10_1.Payload;

import lombok.Data;

@Data
public class RoomDto {
    private Integer number;

    private Integer floor;

    private Integer size;

    private Integer hotelId;
}
