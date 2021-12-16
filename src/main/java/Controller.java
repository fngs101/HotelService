import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Controller
{

    //interakcja only
    private UserService userService;

    Controller()
    {
        userService = new UserService();
    }

    public void manageHotel()
    {
        System.out.println("Welcome to the Grand Hotel");

        int choice = 0;
        do
        {
            try
            {
                showMenu();
                choice = readChoice();
                executeChoice(choice);
            } catch (HotelException e)
            {
                System.out.println(e.getMessage());
            }
        }
        while (choice != 7);
        System.out.println("Did not find what you were looking for? Call Hotel Service 600-500-400");
    }

    public void showMenu()
    {
        System.out.println("Please choose option");
        System.out.println("1 - show all rooms");
        System.out.println("2 - show currently available rooms");
        System.out.println("3 - book a room");
        System.out.println("4 - vacate a room");
        System.out.println("5 - show all uncleaned rooms");
        System.out.println("6 - ask to clean a room");
        System.out.println("7 - cancel");
    }

    public int readChoice()
    {
        int input;
        try
        {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextInt();
        } catch (NumberFormatException | InputMismatchException e)
        {
            System.out.println("Not found, enter again");
            input = readChoice();
        }
        return input;
    }

    public void executeChoice(int input) throws HotelException
    {
        switch (input)
        {
            case 1:
                listAllRooms();
                break;
            case 2:
                listAvailableRooms();
                break;
            case 3:
                bookRoom();
                break;
            case 4:
                vacateRoom();
                break;
            case 5:
                listUncleanedRooms();
                break;
            case 6:
                askForCleaning();
                break;
            case 7:
                listAllUnavailable();
                break;
            case 8:
                break;
        }
    }

    public void listAllRooms()
    {
        List<Room> allRoomList = userService.getRoomList();
        printRooms(allRoomList);
    }

    public void listAvailableRooms()
    {
        List<Room> allAvailableRooms = userService.getAvailableRooms();
        printRooms(allAvailableRooms);
    }

    private void printRooms(List<Room> rooms)
    {
        for (Room room : rooms)
        {
            System.out.println(room.toString());
        }
    }

    public void bookRoom() throws HotelException
    {
        System.out.println("Please enter room number you wish to book");
        Scanner scanner = new Scanner(System.in);
        int roomToBook = scanner.nextInt();
        boolean isAvailable = userService.checkIfAvailable(roomToBook);
        if (!isAvailable)
        {
            System.out.println("Room booked or in cleaning");
            return;
        }

        String dateCHeckin = askForDate("Please enter check in date (YYY-MM-DD)");
        String dateCHeckout = askForDate("Please enter check out date (YYYY-MM-DD)");
        System.out.println("Room booked, please check your email");

        Guest guest = readGuestData();  //ewentualnie lista gosci, zeby sprawdzac czy jest adult jeden zn ich
        if (!guest.isAdult())
        {
            System.out.println("Sorry, only adults can book rooms");
            return;

        }



    }

    public String askForDate(String question)
    {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();

        return date;
    }

    public Guest readGuestData()
    {
        System.out.println("Please insert your name and lastname");
        Scanner scanner = new Scanner(System.in);
        String nameLastname = scanner.nextLine();
        System.out.println("Please insert your date of birth (yyyy-mm-dd)");
        String date = scanner.nextLine();
        return new Guest(nameLastname, date);
    }

//    public void readDateOfBirth()
//    {
//        czy to nie powinna byc osobna metoda na datę guest, bo może ta wyżej juz troche za duzo ma?
//
//    }

    public void vacateRoom() throws HotelException
    {
        System.out.println("Enter the number of the room which you are vacating");
        Scanner scanner3 = new Scanner(System.in);
        int roomToVacate = scanner3.nextInt();
        boolean canBeVacated = userService.vacateRoom(roomToVacate);
        if (canBeVacated)
        {
            System.out.println("You have successfully checked out");
        } else
        {
            System.out.println("Wrong room number");
        }
    }

    public void listUncleanedRooms()
    {
        System.out.println("List of uncleaned rooms:");
        List<Room> uncleanedRooms = userService.getUncleanedRooms();
        for (Room room : uncleanedRooms)
        {
            System.out.println(room.toString());
        }
        if (uncleanedRooms.isEmpty())
        {
            System.out.println("No matching results");
        }
    }

    public void askForCleaning() throws HotelException
    {
        System.out.println("Which room would you like to have cleaned? (number)");
        Scanner scanner = new Scanner(System.in);
        int roomToBeCleaned = scanner.nextInt();
        boolean cleaned = userService.cleanRoom(roomToBeCleaned);
        if (cleaned)
        {
            System.out.println("The room has been cleaned");
        } else
        {
            System.out.println("Room already clean");
        }
    }

    public void listAllUnavailable()
    {
        List<Room> unavailableRooms = userService.getUnavailableRooms();
        for (Room room : unavailableRooms)
        {
            System.out.println(room.toString());
        }

    }

}
