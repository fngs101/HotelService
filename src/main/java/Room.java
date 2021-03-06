import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Room
{
    private int number;
    private int size;
    private boolean bathroomIncluded;
    private boolean available;
    private List<Guest> guestList;
    private boolean cleaned;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    Room(int number, int size, boolean bathroomIncluded, boolean available)
    {
        this.number = number;
        this.size = size;
        this.bathroomIncluded = bathroomIncluded;
        this.available = available;
        guestList = new ArrayList<>();
        cleaned = true;
        checkInDate = null;
        checkOutDate = null;
    }

    public boolean isAvailable()
    {
        return available;
    }

    @Override
    public String toString()
    {
        if(!guestList.isEmpty())
        {
            return "Room number " + number + ", checkout date: " + checkOutDate +
                    "\nBooked by " + guestList.get(0);
        }
        return "Room number "  + number + ", not booked";
    }

    public int getNumber()
    {
        return number;
    }

    public void setAvailable(boolean available)
    {
        this.available = available;
    }

    public boolean addToGuestList(String name, String birthday)
    {
        Guest guest = new Guest(name, birthday);
        if(guest.isAdult())
        {
            guestList.add(guest);
            return true;
        }
        return false;

    }

    public void setUncleaned()
    {
        cleaned = false;
    }

    public void setCleaned()
    {
        cleaned = true;
    }

    public boolean isCleaned()
    {
        return cleaned;
    }

    public void setCheckInDate(LocalDate checkInDate)
    {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate)
    {
        this.checkOutDate = checkOutDate;
    }

}
