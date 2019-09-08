package com.dempaul;

import java.util.NoSuchElementException;

public class MyHashMap implements MyMap {

    private static final int DEFAULT_CAPACITY = 2;
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = DEFAULT_CAPACITY;
    private int size;

    private Entry[] table = new Entry[capacity];

    private class Entry {
        private final int key;
        private long value;

        Entry(int key, long value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public void put(int key, long value) {
        if (!(size < capacity * LOAD_FACTOR)) {
            resize();
        }
        int hash = hash(key);
        int index;
        for (int i = 0; i < capacity; i++) {
            index = (hash + i) % capacity;
            if (table[index] != null) {
                if (table[index].key == key) {
                    table[index].value = value;
                    break;
                }
            } else {
                table[index] = new Entry(key, value);
                size++;
                break;
            }
        }
    }

    @Override
    public long get(int key) {
        int hash = hash(key);
        int index;
        for (int i = 0; i < capacity; i++) {
            index = (hash + i) % capacity;
            if (table[index] != null) {
                if (table[index].key == key) {
                    return table[index].value;
                }
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        size = 0;
        Entry[] oldTable = table;
        capacity = table.length * 2;
        table = new Entry[capacity];
        for (Entry entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    private int hash(int key) {
        int hashVal = key % capacity;
        if (hashVal < 0) {
            hashVal += capacity;
        }
        return hashVal;
    }
}
