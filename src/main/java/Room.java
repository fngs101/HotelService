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

    Room(int number, int size, boolean bathroomIncluded, boolean available)
    {
        this.number = number;
        this.size = size;
        this.bathroomIncluded = bathroomIncluded;
        this.available = available;
        guestList = new ArrayList<>();
    }

    public boolean isAvailable()
    {
        return available;
    }

    @Override
    public String toString()
    {
        return "Room number "  + number;
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



}
