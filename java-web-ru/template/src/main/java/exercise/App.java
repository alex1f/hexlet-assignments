package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import java.util.Collections;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            UsersPage allUsers = new UsersPage(USERS);
            ctx.render("users/index.jte", Collections.singletonMap("usersList", allUsers));
        });


        app.get("/users/{id}", ctx -> {
            long id = Long.parseLong(ctx.pathParam("id"));
            User targetUser = null;
            for (User user : USERS){
                if (user.getId() == id) {
                    targetUser = user;
                    break;
                }
            }

            if (targetUser == null){
                throw new NotFoundResponse("User not found");
            } else {
                UserPage userPage = new UserPage(targetUser);
                ctx.render("users/show.jte", Collections.singletonMap("userPage", userPage));
            }
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
