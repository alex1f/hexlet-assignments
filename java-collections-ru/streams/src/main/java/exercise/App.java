package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
public class App {
    public static Long getCountOfFreeEmails(List<String> emails){
       return emails.stream()
                .filter(App::isInFreeDomain)
                .count();
    }

    private static boolean isInFreeDomain(String email){
        List<String> freeDomains = List.of("gmail.com",
                "yandex.ru",
                "hotmail.com");
        String[] elements = email.split("@");
        String domain = elements[elements.length-1];

        for (String free : freeDomains){
            if (domain.equals(free)){
                return true;
            }
        }

        return false;
    }
}
// END
