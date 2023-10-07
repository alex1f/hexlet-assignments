package exercise;

import io.javalin.Javalin;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            String rawPage = ctx.queryParam("page");
            int page = 0;
            if (rawPage != null) {
                page = Integer.parseInt(rawPage);
                System.out.println(page);
            }
            if (page > 0) {
                int leftInclusive = (page -1) * 5;
                int rightExclusive = leftInclusive + 5;
                ctx.json(USERS.subList(leftInclusive, rightExclusive));
            } else {
                ctx.json(USERS.subList(0, 5));
            }
        }
        );
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
