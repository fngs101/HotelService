import java.time.LocalDate;

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
        return name + ", date of birth: " + birthday;
    }

    public boolean isAdult()
    {
        LocalDate birthDate = LocalDate.parse(birthday);
        LocalDate underage = LocalDate.parse("2002-12-14");
        return birthDate.isBefore(underage);
    }

    public String getName()
    {
        return name;
    }

    public String getBirthday()
    {
        return birthday;
    }
}
