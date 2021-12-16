import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel
{
    private List<Room> rooms;
//dostep do danych czyli pokoj, zapisywanie, pobieranie danych - klasa repozytorium
    Hotel()
    {
        rooms = new ArrayList<>();
        rooms.add(new Room(1, 2, true, true));
        rooms.add(new Room(2, 3, false, true));
        rooms.add(new Room(3, 2, true, true));
        rooms.add(new Room(4, 4, true, true));
        rooms.add(new Room(5, 1, false, true));
        rooms.add(new Room(6, 1, true, true));
        rooms.add(new Room(7, 6, true, true));
        rooms.add(new Room(8, 2, false, true));
        rooms.add(new Room(9, 3, true, true));
        rooms.add(new Room(10, 4, true, true));

    }

    public List<Room> getAllRooms()
    {
        return rooms;
    }

    public Room getRoom(int roomNumber) throws HotelException
    {
        for(Room room : rooms)
        {
            if(room.getNumber() == roomNumber)
            {
                return room;
            }
        }
        throw new HotelException("Room not found");
    }

    public List<Room> listUncleanedRooms()
    {
        List<Room> uncleanedRooms = new ArrayList<>();
        for(Room room : rooms)
        {
            if(!room.isCleaned())
            {
                uncleanedRooms.add(room);
            }
        }
        return uncleanedRooms;
    }

    public List<Room> listUnavailableRooms()
    {
        List<Room> unavailableRooms = new ArrayList<>();
        for(Room room : rooms)
        {
            if(!room.isAvailable() && room.isCleaned())
            {
                unavailableRooms.add(room);
            }
        }
        return unavailableRooms;
    }

    public List<Room> getAvailableRooms()
    {
        List<Room> allRooms = rooms;
        return allRooms
                .stream()
                .filter(Room::isAvailable)
                .collect(Collectors.toList());
    }
}
