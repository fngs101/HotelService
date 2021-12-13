import java.time.LocalDate;
import java.time.LocalDateTime;

public class Guest
{
    private String name;
    private String lastname;
    private LocalDate birthday;

    Guest(String name, String lastname, LocalDate birthday)
    {
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
    }

    @Override
    public String toString()
    {
        return name + " " +
                lastname
                + " " + birthday;
    }

    public boolean isAdult()
    {
        LocalDate underage = LocalDate.parse("2002-12-31");
        return birthday.isAfter(underage);
    }
}
