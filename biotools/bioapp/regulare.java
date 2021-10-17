package biotools.bioapp;


import java.util.Locale;

public class regulare {
    static String patternDNA = "[ATGC]*";
    static String patternRNA = "[AUGC]*";
    static String patternProtein = "[^BJOUXZ0-9]*";
    static String example = "AAA";
    public static void main(String[] args) {
        System.out.println(example.toUpperCase(Locale.ROOT).matches(patternProtein));
        System.out.println("Hello world!");
    }
}
