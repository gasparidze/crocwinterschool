package ru.croc.art;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ArrayMaxSearchTest {
    @Test
    public void maxSearch() throws ExecutionException, InterruptedException {

        // размер массива
        int N = 10000000;

        // массив
        List<Integer> array = new ArrayList<>(N);

        // заполняем массив случайными числами
        for(int i = 0; i < N; i++){

            array.add((int) ( Math.random() * (1000 - -1000 + 1) + -1000));

        }

        // масимум для теста
        int testMaximum = Collections.max(array);

        ArrayMaxSearch arrayMaxSearch = new ArrayMaxSearch();

        // максимум с помощью программы
        int myMaximum = arrayMaxSearch.getArrayMaximum(array, 4);

        Assertions.assertEquals(testMaximum, myMaximum);

    }
}
