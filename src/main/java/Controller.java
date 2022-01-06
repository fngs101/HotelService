import java.time.LocalDate;
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
            } catch (HotelException | DateException e)
            {
                System.out.println(e.getMessage());
            }
        }
        while (choice != 8);
        System.out.println("Have an idea to improve the service? Write a mail to the developer: fabDev@fabDev.com");
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
        System.out.println("7 - show all unavailable rooms");
        System.out.println("8 - cancel");
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

    public void executeChoice(int input) throws HotelException, DateException
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

    public void bookRoom() throws HotelException, DateException
    {
        //nie bardzo pomysł jak skrócić tę metodę jeszcze bardziej
        int roomToBook = askForRoomNumber();

        if (!userService.isAvailable(roomToBook))
        {
            System.out.println("Room booked or in cleaning");
            return;
        }

        LocalDate checkInDate = askForDate("Please enter check in date (YYYY-MM-DD)");
        userService.addCheckInDate(checkInDate, roomToBook);

        LocalDate checkOutDate = askForDate("Please enter check out date (YYYY-MM-DD)");
        userService.addCheckOutDate(checkInDate, checkOutDate, roomToBook);

        Guest guest = readGuestData();
        if (!guest.isAdult())
        {
            System.out.println("Only adults can book rooms");
            return;
        }

        userService.bookRoom(roomToBook);
        userService.addNameToGuestList(guest.getName(), guest.getBirthday(), roomToBook);
        System.out.println("Room booked");

    }

    public int askForRoomNumber()
    {
        System.out.println("Please enter room number you wish to book");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public LocalDate askForDate(String question) throws DateException
    {
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();
        userService.validateStringDate(date);
        LocalDate dateConverted = LocalDate.parse(date);
        userService.validateCheckInDate(dateConverted);
        return dateConverted;

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
        if(unavailableRooms.isEmpty())
        {
            System.out.println("All rooms are available");
        } else
        {
            for (Room room : unavailableRooms)
            {
                System.out.println(room.toString());
            }
        }

    }

}
