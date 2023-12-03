package exercise;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String nameA, String nameB, String pathDestination)
            throws IOException {
        Path absoluteA = Paths.get(nameA).toAbsolutePath();
        Path absoluteB = Paths.get(nameB).toAbsolutePath();
        Path absoluteDestination = Paths.get(pathDestination).toAbsolutePath();

  /*      if (!Files.exists(absoluteA)){
            System.out.println("NoSuchFileException");
           throw new NoSuchFileException("nameA");
        } else if (!Files.exists(absoluteB)) {
            System.out.println("NoSuchFileException");
           throw new NoSuchFileException("nameB");
        }*/
        if (!Files.exists(absoluteDestination)){
            Files.createFile(absoluteDestination);
        }

        CompletableFuture<String> result = CompletableFuture
                .supplyAsync(() -> readFile(nameA))
                .exceptionally(ex -> {
                    System.out.println("NoSuchFileException");
                    return null;
                })
                .thenCombine(CompletableFuture.supplyAsync(() -> readFile(nameB))
                                .exceptionally(ex -> "NoSuchFileException")

                                , (s1, s2) -> s1 + "\n" + s2);

        try (BufferedWriter writer = Files.newBufferedWriter(absoluteDestination)) {
            writer.write(result.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static String readFile(String path){
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            return lines.collect(Collectors.joining(" "));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
      CompletableFuture<String> result = unionFiles("src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/union.txt");
        System.out.println(result.get());


        // END
    }
}

