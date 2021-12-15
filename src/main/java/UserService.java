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
        List<Room> allRooms = getRoomList();
        return allRooms
                .stream()
                .filter(Room::isAvailable)
                .collect(Collectors.toList());
    }


    public void bookRoom(int roomNumber)
    {
        List<Room> availableRooms = getAvailableRooms();
        for(Room room : availableRooms)
        {
            if(room.getNumber() == roomNumber)
            {
                room.setAvailable(false);
            }

        }

    }

    public boolean checkIfAvailable(int roomNumber)
    {
        List<Room> availableRooms = getAvailableRooms();
        for(Room room : availableRooms)
        {
            if(room.getNumber() == roomNumber && room.isCleaned())
            {
                return true;
            }
        }
        return false;
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

    public boolean vacateRoom(int roomNumber)
    {
        List<Room> allRooms = getRoomList();
        for(Room room : allRooms)
        {
            if(room.getNumber() == roomNumber && !room.isAvailable())
            {
                room.setAvailable(true);
                room.setUncleaned();
                return true;
            }
        }
        return false;
    }

    public boolean cleanRoom(int roomNumber)
    {
        for(Room room : getRoomList())
        {
            if(room.getNumber() == roomNumber && !room.isCleaned())
            {
                room.setCleaned();
                return true;
            }
        }
        return false;
    }

    public List<Room> listUncleanedRooms()
    {
        List<Room> uncleanedRooms = new ArrayList<>();
        for(Room room : getRoomList())
        {
            if(!room.isCleaned())
            {
                uncleanedRooms.add(room);
            }
        }
        return uncleanedRooms;
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
        List<Room> unavailableRooms = new ArrayList<>();
        for(Room room : getRoomList())
        {
            if(!room.isAvailable() && room.isCleaned())
            {
                unavailableRooms.add(room);
            }
        }
        return unavailableRooms;
    }
}
