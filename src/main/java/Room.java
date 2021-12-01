public class Room
{
    private int number;
    private int size;
    private boolean bathroomIncluded;
    private boolean available;

    Room(int number, int size, boolean bathroomIncluded, boolean available)
    {
        this.number = number;
        this.size = size;
        this.bathroomIncluded = bathroomIncluded;
        this.available = available;
    }

    public boolean isAvailable()
    {
        return available;
    }

    @Override
    public String toString()
    {
        return "Room number "  + number + " available?" + available;
    }

    public int getNumber()
    {
        return number;
    }

    public void setAvailable(boolean available)
    {
        this.available = available;
    }
}
