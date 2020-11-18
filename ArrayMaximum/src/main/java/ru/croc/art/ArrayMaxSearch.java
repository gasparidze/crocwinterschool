package ru.croc.art;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ArrayMaxSearch {
    /**
     * ExecutorService
     */
    private ExecutorService service = null;

    /**
     * получить максимум массива
     *
     * @param arrayOfNums массив
     * @param threadsKol количество потоков
     *
     * @return максимум массива
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Integer getArrayMaximum(List<Integer> arrayOfNums, int threadsKol)
            throws ExecutionException, InterruptedException {

        service = Executors.newFixedThreadPool(threadsKol);

        // список максимумов частей
        List<Integer> maximums = new ArrayList<>();

        // часть массива
        List<Integer> part = new ArrayList<>();

        for (int i = 1; i < arrayOfNums.size(); i++) {

            // добавляем числа из исходного массива в новый
            part.add(arrayOfNums.get(i));

            // каждые n элементов ищем максимум части
            if(i % 100 == 0 || i == arrayOfNums.size() - 1){

                Future<Integer> future = findMaximum(part);
                while (!future.isDone());
                maximums.add(future.get());
                // очищаем новый массив
                part.clear();

            }

        }

        // ищем максимум среди максимумов частей
        Future<Integer> future = findMaximum(maximums);
        while (!future.isDone());
        int arrayMaximum = future.get();

        service.shutdown();

        return arrayMaximum;

    }

    /**
     * создание задач на поиск максимума подмассива
     *
     * @param searchArray подмассив
     * @return максимум
     */
    public Future<Integer> findMaximum(final List<Integer> searchArray) {


        Callable<Integer> taskWithResult = new Callable<Integer>() {
            @Override
            public Integer call() {

                for (int i = 0; i < searchArray.size(); i++) {

                    if (searchArray.get(i) > searchArray.get(0)) {
                        searchArray.set(0, searchArray.get(i));
                    }
                }

                return searchArray.get(0);
            }
        };
        return service.submit(taskWithResult);
    }
}
