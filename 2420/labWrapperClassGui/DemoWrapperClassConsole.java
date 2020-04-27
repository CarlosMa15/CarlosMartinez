package labWrapperClassGui;

public class DemoWrapperClassConsole {
    public static void main(String[] args)
    {
        
        DemoWrapperClass dwc = new DemoWrapperClass();
        
        System.out.println("Method minMax:\n");
        System.out.println(dwc.minMax());

        System.out.println("\nMethod toBinary:\n");
        System.out.println(dwc.toBinary(111));

        System.out.println("\nMethod charProperties:\n");
        
        System.out.println(dwc.charProperties(' '));
        System.out.println(dwc.charProperties('2'));
        System.out.println(dwc.charProperties('B'));
        System.out.println(dwc.charProperties('b'));

    }

}
