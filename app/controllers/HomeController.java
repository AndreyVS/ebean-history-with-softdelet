package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;
import models.Account;
import models.Settings;

import play.mvc.*;
import play.libs.Json;

public class HomeController extends Controller {

    public Result index() {

        return ok("ok");

    }

    // User API

    /**
     * GET /user/:id
     */
    public Result getUser(Long id) {

        User user = User.find
                .query()
                .setId(id)
                .findOne();

        return user != null ? ok(Json.toJson(user)) : ok("User not found");

    }

    /**
     * GET /deleted/user/:id
     */
    // In this case Query threw SQLException
    public Result getDeletedUser(Long id) {

        User user = User.find
                .query()
                .setId(id)
                .setIncludeSoftDeletes()
                .findOne();

        return user != null ? ok(Json.toJson(user)) : ok("User not found");

    }

    /**
     * DELETE /user/:id
     */
    public Result deleteUser(Long id) {

        User.find.query().setId(id).findOne().delete();

        return ok("ok");

    }

    // Settings API

    /**
     * GET /settings/:id
     */
    public Result getSettings(Long id) {

        Settings settings = Settings.find
                .query()
                .setId(id)
                .findOne();

        return settings != null ? ok(Json.toJson(settings)) : ok("Settings not found");

    }

    /**
     * GET /fetch/settings/:id
     */
    public Result getSettingsWithFetch(Long id) {

        Settings settings = Settings.find
                .query()
                .fetch("user","*")
                .setId(id)
                .findOne();

        return settings != null ? ok(Json.toJson(settings)) : ok("User not found");

    }

    /**
     * GET /deleted/settings/:id
     */
    public Result getDeletedSettings(Long id) {

        Settings settings = Settings.find
                .query()
                .setId(id)
                .setIncludeSoftDeletes()
                .findOne();

        return settings != null ? ok(Json.toJson(settings)) : ok("Settings not found");

    }

    /**
     * GET /deleted/fetch/settings/:id
     */
    // In this case Query threw SQLException
    public Result getDeletedSettingsWithFetch(Long id) {

        Settings settings = Settings.find
                .query()
                .fetch("user","*")
                .setId(id)
                .setIncludeSoftDeletes()
                .findOne();

        return settings != null ? ok(Json.toJson(settings)) : ok("User not found");

    }

    /**
     * DELETE /settings/:id
     */
    public Result deleteSettings(Long id) {

        Settings.find.query().setId(id).findOne().delete();

        return ok("ok");

    }

    // Account API

    /**
     * GET /account/:id
     */
    public Result getAccount(Long id) {

        Account account = Account.find
                .query()
                .setId(id)
                .findOne();

        return account != null ? ok(Json.toJson(account)) : ok("Account not found");

    }

    /**
     * GET /deleted/account/:id
     */
    public Result getDeletedAccount(Long id) {

        Account account = Account.find
                .query()
                .setId(id)
                .setIncludeSoftDeletes()
                .findOne();

        return account != null ? ok(Json.toJson(account)) : ok("Account not found");

    }

    /**
     * DELETE /account/:id
     */
    public Result deleteAccount(Long id) {

        Account.find.query().setId(id).findOne().delete();

        return ok("ok");

    }

}
