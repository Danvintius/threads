import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String[] texts = new String[25];
        List<Thread> threads = new ArrayList<>();
        long startTs = System.currentTimeMillis(); // start time
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("aab", 30_000);
        }

        for (int i = 0; i < texts.length; i++) {
        Runnable logic = () -> {
            for (String text : texts) {
                int maxSize = 0;
                for (int x = 0; x < text.length(); x++) {
                    for (int j = 0; j < text.length(); j++) {
                        if (x >= j) {
                            continue;
                        }
                        boolean bFound = false;
                        for (int k = x; k < j; k++) {
                            if (text.charAt(k) == 'b') {
                                bFound = true;
                                break;
                            }
                        }
                        if (!bFound && maxSize < j - x) {
                            maxSize = j - x;
                        }
                    }
                }
                System.out.println(text.substring(0, 100) + " -> " + maxSize);
            }
        };

        Thread runner = new Thread(logic);
        runner.start();
        threads.add(runner);
    }

        for (Thread thread : threads)
            thread.join(); // зависаем, ждём когда поток объект которого лежит в thread завершится
        long endTs = System.currentTimeMillis(); // end time

        System.out.println("Time: " + (endTs - startTs) + "ms");
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
