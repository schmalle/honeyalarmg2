package honeyalarm;

public class Helpers
{
    public static boolean compare(String a, String b)
    {
        if (a == null)
            return false;
        if (b == null)
            return false;

        if (a.equals(b))
            return true;
        else
            return false;

    }

}
