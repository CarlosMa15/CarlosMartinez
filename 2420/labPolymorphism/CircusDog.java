package labPolymorphism;

/**
 * Creates a new object of type CircusDog
 * This Class extends Class Dog
 * @author carlosmartinez
 */
public class CircusDog extends Dog
{
    public CircusDog(String b)
    {
        super(b);
    }

    @Override
    public void move()
    {
        System.out.println("tightrope walking");
    }
}
