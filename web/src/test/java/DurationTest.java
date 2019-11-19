import org.springframework.boot.convert.DurationStyle;

import java.time.Duration;

public class DurationTest {
    public static void main(String[] args) {
        System.out.println(DurationStyle.SIMPLE.parse("10m"));
        System.out.println(DurationStyle.ISO8601.parse("10m"));
        System.out.println(Duration.parse("10m"));
    }
}
