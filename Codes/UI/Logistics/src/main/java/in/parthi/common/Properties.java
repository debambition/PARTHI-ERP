package in.parthi.common;

import java.util.Locale;
import java.util.Scanner;

public class Properties {

    public static final String STATUS_AVAILABLE = "AVAILABLE";
    public static final String STATUS_RETURNED = "RETURNED";
    public static final String STATUS_SOLD = "SOLD OUT";
    private static Scanner sc = new Scanner(System.in);

    public static Scanner getSacnnerInstance() {
        sc.useLocale(Locale.US);
        return sc;
    }
}
