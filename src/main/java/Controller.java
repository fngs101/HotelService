import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Controller
{

    //interakcja only
    private UserService userService;
    private boolean isRunning;
    Controller()
    {
        userService = new UserService();
        isRunning = true;
    }

    public void manageHotel()
    {
        System.out.println("Welcome to the Grand Hotel");

        int choice;
        do
        {
            showMenu();
            choice = readChoice();
            executeChoice(choice);

        }
        while(choice != 8);
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
        }
        catch (NumberFormatException |InputMismatchException e)
        {
            System.out.println("Not found, enter again");
            input = readChoice();
        }
        return input;
    }

    public void executeChoice(int input)
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
        for(Room room : allRoomList)
        {
            System.out.println(room.toString());
        }
    }

    public void listAvailableRooms()
    {
        List<Room> allAvailableRooms = userService.getAvailableRooms();
        for(Room room : allAvailableRooms)
        {
            System.out.println(room.toString());
        }
    }

    public void bookRoom()
    {
        //poprawic za dlugie
        System.out.println("Please enter room number you wish to book");
        Scanner scanner = new Scanner(System.in);
        int roomToBook = scanner.nextInt();
        boolean isAvailable = userService.checkIfAvailable(roomToBook);
        if(isAvailable)
        {
            boolean isAdult = readGuestData(roomToBook);
            if(isAdult)
            {
                userService.bookRoom(roomToBook);
                askForDates(roomToBook);
                System.out.println("Room booked, please check your email");
            }
            else
            {
                System.out.println("Sorry, only adults can book rooms");
            }
        } else
        {
            System.out.println("Room booked or in cleaning");
        }


    }

    public void askForDates(int roomNumber)
    {
        System.out.println("Please enter check in date (YYY-MM-DD)");
        Scanner scanner = new Scanner(System.in);
        String dateCheckIn = scanner.nextLine();
        System.out.println("Please enter check out date (YYYY-MM-DD)");
        Scanner scanner2 = new Scanner(System.in);
        String dateCheckout = scanner2.nextLine();
        userService.addDates(dateCheckIn, dateCheckout, roomNumber);
    }

    public boolean readGuestData(int roomNumber)
    {
        System.out.println("Please insert guest number");
        System.out.println("Please insert your name and lastname");
        Scanner scanner = new Scanner(System.in);
        String nameLastname = scanner.nextLine();
        System.out.println("Please insert your date of birth (yyyy-mm-dd)");
        Scanner scanner2 = new Scanner(System.in);
        String date = scanner2.nextLine();
        return userService.addNameToGuestList(nameLastname, date, roomNumber);
    }

//    public void readDateOfBirth()
//    {
//        czy to nie powinna byc osobna metoda na datę guest, bo może ta wyżej juz troche za duzo ma?
//
//    }

    public void vacateRoom()
    {
        System.out.println("Enter the number of the room which you are vacating");
        Scanner scanner3 = new Scanner(System.in);
        int roomToVacate = scanner3.nextInt();
        boolean canBeVacated = userService.vacateRoom(roomToVacate);
        if(canBeVacated)
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
        List<Room> uncleanedRooms = userService.listUncleanedRooms();
        for(Room room : uncleanedRooms)
        {
            System.out.println(room.toString());
        }
        if(uncleanedRooms.isEmpty())
        {
            System.out.println("No matching results");
        }
    }

    public void askForCleaning()
    {
        System.out.println("Which room would you like to have cleaned? (number)");
        Scanner scanner = new Scanner(System.in);
        int roomToBeCleaned = scanner.nextInt();
        boolean cleaned = userService.cleanRoom(roomToBeCleaned);
        if(cleaned)
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
        for(Room room : unavailableRooms)
        {
            System.out.println(room.toString());
        }

    }

}
