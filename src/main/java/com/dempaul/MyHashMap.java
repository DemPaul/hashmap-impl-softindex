package com.dempaul;

import java.util.NoSuchElementException;

public class MyHashMap implements MyMap {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = DEFAULT_CAPACITY;
    private int size;

    private int[] keyTable = new int[capacity];
    private long[] valueTable = new long[capacity];
    private boolean[] presenceCheckTable = new boolean[capacity];

    @Override
    public void put(int key, long value) {
        if (!(size < capacity * LOAD_FACTOR)) {
            resize();
        }
        int hash = hash(key);
        int index;
        for (int i = 0; i < capacity; i++) {
            index = (hash + i) % capacity;
            if (presenceCheckTable[index]) {
                if (keyTable[index] == key) {
                    valueTable[index] = value;
                    break;
                }
            } else {
                keyTable[index] = key;
                valueTable[index] = value;
                presenceCheckTable[index] = true;
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
            if (presenceCheckTable[index]) {
                if (keyTable[index] == key) {
                    return valueTable[index];
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
        int[] oldKeyTable = keyTable;
        long[] oldValueTable = valueTable;
        boolean[] oldPresenceCheckTable = presenceCheckTable;
        capacity *= 2;
        keyTable = new int[capacity];
        valueTable = new long[capacity];
        presenceCheckTable = new boolean[capacity];
        for (int i = 0; i < oldPresenceCheckTable.length; i++) {
            if (oldPresenceCheckTable[i]) {
                put(oldKeyTable[i], oldValueTable[i]);
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
