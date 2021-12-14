import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            if(room.getNumber() == roomNumber)
            {
                return true;
            }
        }
        return false;
    }

    public boolean addNameToGuestList(String identity, String date, int roomNumber)
    {
        boolean added = false;
        for(Room room : hotel.getAllRooms())
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
        List<Room> allRooms = hotel.getAllRooms();
        for(Room room : allRooms)
        {
            if(room.getNumber() == roomNumber && !room.isAvailable())
            {
                room.setAvailable(true);
                return true;
            }
        }
        return false;
    }
}
