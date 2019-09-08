package com.dempaul;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class MyHashMapTest {

    private MyMap myMap;

    @Before
    public void setUp() {
        myMap = new MyHashMap();
    }

    @Test
    public void addFewElementsToMap() {
        long elementOne = 11111L;
        long elementTwo = 22222L;
        long elementTree = -11111L;
        long elementFour = -22222L;

        Assert.assertEquals(0, myMap.size());

        myMap.put(0, elementOne);
        myMap.put(1, elementTwo);
        myMap.put(-1, elementTree);
        myMap.put(-2, elementFour);

        Assert.assertEquals(4, myMap.size());

        Assert.assertEquals(elementOne, myMap.get(0));
        Assert.assertEquals(elementTwo, myMap.get(1));
        Assert.assertEquals(elementTree, myMap.get(-1));
        Assert.assertEquals(elementFour, myMap.get(-2));
    }

    @Test
    public void addFewElementsWithSameKeyHashToMap() {
        long[] elements = {11111L, 22222L, 33333L, 44444L};

        int startKey = -100;
        int elementIndex = 0;
        int numOfElements = 16;

        Assert.assertEquals(0, myMap.size());

        for (int i = 0; i < numOfElements; i++) {
            int key = startKey + i * numOfElements;
            myMap.put(key, elements[elementIndex % elements.length]);
            elementIndex++;
        }

        Assert.assertEquals(numOfElements, myMap.size());

        for (int i = 0; i < numOfElements; i++) {
            int key = startKey + i * numOfElements;
            Assert.assertEquals(elements[elementIndex % elements.length], myMap.get(key));
            elementIndex++;
        }
    }

    @Test
    public void addTwoElementsWithSameKey() {
        int key = 1;
        long elementOne = 11111L;
        long elementTwo = 22222L;

        myMap.put(key, elementOne);
        myMap.put(key, elementTwo);

        Assert.assertEquals(
                "The element with the same key should be rewritten",
                elementTwo,
                myMap.get(key));
    }

    @Test(expected = NoSuchElementException.class)
    public void getElementWhenKeyDontExist() {
        myMap.get(12);
    }
}
