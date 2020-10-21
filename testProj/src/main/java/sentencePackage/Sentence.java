package hey;

import java.util.*;

/**
 * класс предложения
 */
public class Sentence {
    /**поступающее предложение*/
    private String str;

    /**
     * конструктор
     * @param str - поступающая строка
     */
    public Sentence(String str) {
        this.str = str;
    }

    /**
     * Метод отчистки строки предложения
     * @return - отчищенная строка
     */
    public String clearSentence() {
        str = str.toLowerCase();
        str = str.replaceAll("\\d", "");
        str = str.replaceAll("\\p{Punct}", " ");
        str = str.replaceAll("\n", " ");
        str = str.replaceAll("[\\s]{2,}", " ");
        return str;
    }

    /**
     * Метод нахождения количества каждого слова в предложении
     * @return - коллекция Map, ключ которой - слово, значение - количество повторений данного слова в предложении
     */
    public Map repetitions() {
        LinkedHashMap<String,Integer> quantity = new LinkedHashMap<>();
        String[] strArray = str.split(" ");
        for (String str:strArray){
            if(!quantity.containsKey(str)){
                quantity.put(str,1);
            }else{
                quantity.put(str,quantity.get(str) + 1);
            }
        }
        return quantity;
    }

}


