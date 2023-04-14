package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class PojoHerokuAppBookingDates {

    /*
    {
       "checkin" : "2021-06-01",
       "checkout" : "2021-06-10"
    }

     */

    private String checkin;
    private String checkout;


}