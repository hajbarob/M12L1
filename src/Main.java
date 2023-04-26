import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> a = List.of("a", "b", "c", "a", "A");

        System.out.println("a.stream().distinct().collect(Collectors.toList()) = "
                + a.stream().distinct().collect(Collectors.toList()));
    }
}