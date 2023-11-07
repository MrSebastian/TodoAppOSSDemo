package de.mrsebastian.todoappdemo.backend.configuration;

public class Profiles {

    public static final String NOT = "!";

    public static final String PROFILE_SQL = "persistence-sql";

    public static final String PROFILE_MONGO = "persistence-mongo";

    private Profiles() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
