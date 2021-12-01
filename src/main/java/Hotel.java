import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel
{
    private List<Room> rooms;

    Hotel()
    {
        rooms = new ArrayList<Room>();
        rooms.add(new Room(1, 2, true, true));
        rooms.add(new Room(2, 3, false, true));
        rooms.add(new Room(3, 2, true, false));
        rooms.add(new Room(4, 4, true, true));
        rooms.add(new Room(5, 1, false, false));
        rooms.add(new Room(6, 1, true, true));
        rooms.add(new Room(7, 6, true, false));
        rooms.add(new Room(8, 2, false, true));
        rooms.add(new Room(9, 3, true, false));
        rooms.add(new Room(10, 4, true, true));

    }

    public List<Room> getAllRooms()
    {
        return rooms;
    }


}
