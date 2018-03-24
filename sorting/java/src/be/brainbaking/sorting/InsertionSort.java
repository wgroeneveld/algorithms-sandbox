package be.brainbaking.sorting;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort implements Sortable {

    public List<Integer> sort(List<Integer> list) {
        if(list.size() == 0) return list;

        // not the same thing, I don't want to mutate the incoming list.
        // start with nothing and insert it into the right position of result list.
        // ex. https://www.geeksforgeeks.org/insertion-sort/
        List<Integer> result = new ArrayList<>();
        result.add(list.get(0));

        for(int i = 1; i <  list.size(); i++) {
            int curr = list.get(i);

            boolean added = false;
            for(int j = 0; j < result.size(); j++) {
                if(result.get(j) > curr) {
                    result.add(j, curr);
                    added = true;
                    break;
                }
            }
            if(!added) result.add(curr);
        }

        return result;
    }

}
