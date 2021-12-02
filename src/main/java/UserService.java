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

        //pętla
//        boolean isAvailable = Arrays.stream(availableRooms)
//        for(Room r : availableRooms)
//        {
//            if(room.isBathroomIncluded())
//        }
        //stream

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

    public boolean vacateRoom(int roomNumber)
    {
        //NOTE jak w tym zrobić negacje
//        List<Room> allRooms = hotel.getAllRooms().stream().filter(Room::isAvailable).collect(Collectors.toList());;
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
