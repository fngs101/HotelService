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

    public boolean isAvailable(int roomNumber) throws HotelException
    {
        Room room = hotel.getRoom(roomNumber);
        return room.isCleaned() && room.isAvailable();
    }

    public void addNameToGuestList(String identity, String date, int roomNumber)
    {
        for(Room room : getRoomList())
        {
            if(room.getNumber() == roomNumber)
            {
                room.addToGuestList(identity, date);
            }
        }
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

    public void validateCheckInDate(LocalDate date) throws DateException
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

    public boolean isCheckOutDateValid(LocalDate checkInDate, LocalDate checkOutDate) throws DateException
    {
        if(checkOutDate.isBefore(checkInDate))
        {
            throw new DateException("Check out date cannot be before check in date");
        }
        return true;
    }

    public void addCheckOutDate(LocalDate checkInDate, LocalDate checkOutDate, int roomToBook) throws DateException
    {
        if(isCheckOutDateValid(checkInDate, checkOutDate))
        {
            hotel.addCheckOutDate(checkOutDate, roomToBook);
        }
    }
}
