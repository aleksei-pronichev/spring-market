package ru.pronichev.api;

import java.util.Optional;

public interface Container<T> {

    boolean contains(T id);

    Optional<byte[]> getDataById(T id);

    Optional<String> create(byte[] data);

    void remove(T id);
}