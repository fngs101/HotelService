import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService
{
    //troche zepsułam bo w tym momencie klasa hotel stała sie zbędna, czy moze byc ta obecna przekształcona na hotelservice
    //i posiadac liste pokojów?
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


    public void bookRoom(int roomNumber, Guest guest, String checkInDate, String checkOutDate) throws HotelException
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
        if(!room.isAvailable())
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
        if(!room.isCleaned())
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

    public void addDates(String dateCheckIn, String dateCheckOut, int roomNumber)
    {
        List<Room> unavailable = getUnavailableRooms();
        for(Room room : unavailable)
        {
            if(room.getNumber() == roomNumber)
            {
                room.setCheckInDate(dateCheckIn);
                room.setCheckOutDate(dateCheckOut);
            }
        }
    }

    public List<Room> getUnavailableRooms()
    {
        return hotel.listUnavailableRooms();
    }
}
