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

        do
        {
            showMenu();
            int choice = readChoice();
            executeChoice(choice);

        }
        while(true);
    }

    public void showMenu()
    {
        System.out.println("Please choose option");
        System.out.println("1 - show all rooms");
        System.out.println("2 - show currently available rooms");
        System.out.println("3  - book a room");
        System.out.println("4 - vacate a room");
        System.out.println("5 - cancel");
    }

    public int readChoice()
    {
        int input;
        try
        {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextInt();
        }
        catch (NumberFormatException e)
        {
            input = readChoice();
        }
        return input;
    }

    public void executeChoice(int input)
    {
        switch (input)
        {
            case 1:
                System.out.println(userService.getRoomList());
                break;
            case 2:
                userService.getAvailableRooms();
                break;
            case 3:
                Scanner scanner2 = new Scanner(System.in);
                int roomToBook = scanner2.nextInt();
                userService.bookRoom(roomToBook);
                break;
            case 4:
                Scanner scanner3 = new Scanner(System.in);
                int roomToVacate = scanner3.nextInt();
                userService.vacateRoom(roomToVacate);
                break;

        }
    }
}
