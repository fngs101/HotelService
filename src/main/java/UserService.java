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


    public boolean bookRoom(int roomNumber)
    {
        List<Room> availableRooms = getAvailableRooms();
        for(Room room : availableRooms)
        {
            if(room.getNumber() == roomNumber)
            {
                room.setAvailable(false);
                return true;
            }
        }
        return false;
    }

    public void addNameToGuestList(String identity, String date, int roomNumber)
    {
        //tutaj petla po roomach i do tego wybranego z parametru dodaj do jego listy guests
        for(Room room : hotel.getAllRooms())
        {
            if(room.getNumber() == roomNumber)
            {
                room.addToGuestList(identity, date);
            }
        }
        //niech to zwraca boolean pochodzące od guesta, że niepelnoletni

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
