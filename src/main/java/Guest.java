import java.time.LocalDate;
import java.time.LocalDateTime;

public class Guest
{
    private String name;
    private String birthday;

    Guest(String name, String birthday)
    {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString()
    {
        return name + " " + birthday;
    }

    public boolean isAdult()
    {
        LocalDate birthDate = LocalDate.parse(birthday);
        LocalDate underage = LocalDate.parse("2002-12-14");
        return birthDate.isBefore(underage);
    }
}
