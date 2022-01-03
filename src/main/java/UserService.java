import java.time.LocalDate;
import java.util.List;

public class UserService
{
    //cala logika apki
    private Hotel hotel;

    UserService()
    {
        hotel = new Hotel();
    }

    public List<Room> getRoomList()
    {
        return hotel.getAllRooms();
    }

    public List<Room> getAvailableRooms()
    {
        return hotel.getAvailableRooms();
    }


    public void bookRoom(int roomNumber) throws HotelException
    {
        Room room = hotel.getRoom(roomNumber);
        room.setAvailable(false);
    }

    public boolean checkIfAvailable(int roomNumber) throws HotelException
    {
        Room room = hotel.getRoom(roomNumber);
        return room.isCleaned() && room.isAvailable();
    }

    public boolean addNameToGuestList(String identity, String date, int roomNumber)
    {
        boolean added = false;
        for(Room room : getRoomList())
        {
            if(room.getNumber() == roomNumber)
            {
                added = room.addToGuestList(identity, date);
            }
        }
        return added;

    }

    public boolean vacateRoom(int roomNumber) throws HotelException
    {
        Room room = hotel.getRoom(roomNumber);
        if (!room.isAvailable())
        {
            room.setAvailable(true);
            room.setUncleaned();
            return true;
        }
        return false;
    }

    public boolean cleanRoom(int roomNumber) throws HotelException
    {
        Room room = hotel.getRoom(roomNumber);
        if (!room.isCleaned())
        {
            room.setCleaned();
            return true;
        }
        return false;
    }

    public List<Room> getUncleanedRooms()
    {
        return hotel.listUncleanedRooms();
    }

    public List<Room> getUnavailableRooms()
    {
        return hotel.listUnavailableRooms();
    }

    public void validateDate(LocalDate date) throws DateException
    {

        if (date.isBefore(LocalDate.now()))
        {
            throw new DateException("Cannot choose date earlier than " + LocalDate.now());
        }


    }

    public void validateStringDate(String date) throws DateException
    {
        if(!date.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$"))
        {
            throw new DateException("Wrong date format");
        }
    }

    public void addCheckInDate(LocalDate checkInDate, int roomToBook)
    {
        hotel.addCheckInDate(checkInDate, roomToBook);
    }

    public void addCheckOutDate(LocalDate checkOutDate, int roomToBook)
    {
        hotel.addCheckOutDate(checkOutDate, roomToBook);
    }
}
