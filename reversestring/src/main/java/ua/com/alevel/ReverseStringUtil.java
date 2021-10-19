package ua.com.alevel;

public final class ReverseStringUtil {

    private ReverseStringUtil() { }

    public static String reverse(String src, boolean full) {
        if (full) {
            return new StringBuilder(src).reverse().toString();
        }
        return "";
    }
}
