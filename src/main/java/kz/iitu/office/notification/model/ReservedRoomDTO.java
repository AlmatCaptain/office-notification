package kz.iitu.office.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservedRoomDTO {
    private Long employeeId;
    private String login;
    private String roomNumber;
    private String date;
    private String toDate;
}
