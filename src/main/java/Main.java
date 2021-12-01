import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        UserService userService = new UserService();
        System.out.println("Welcome to the Grand Hotel");

        do
        {
            System.out.println("Please choose option");
            System.out.println("1 - show all rooms");
            System.out.println("2 - show currently available rooms");
            System.out.println("3  - book a room");
            System.out.println("4 - vacate a room");
            Scanner scanner = new Scanner(System.in);

            try
            {
                int input = scanner.nextInt();

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
            } catch (NumberFormatException e)
            {
                System.out.println("Wrong number");
            }

        }
        while(true);

    }
}
