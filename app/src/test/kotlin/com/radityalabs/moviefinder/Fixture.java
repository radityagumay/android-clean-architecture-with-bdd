package com.radityalabs.moviefinder;

import com.google.gson.Gson;

import java.io.InputStreamReader;

public class Fixture<T> {
    private final Class<T> fixtureClass;
    private final String fixtureFile;

    public Fixture(Class<T> fixtureClass, String fixtureFile) {
        this.fixtureClass = fixtureClass;
        this.fixtureFile = fixtureFile;
    }

    public T load() {
        InputStreamReader fixtureStreamReader = new InputStreamReader(fixtureClass.getClassLoader().getResourceAsStream(fixtureFile));
        return new Gson().fromJson(fixtureStreamReader, fixtureClass);
    }
}

