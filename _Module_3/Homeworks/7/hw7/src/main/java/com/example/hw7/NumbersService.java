package com.example.hw7;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NumbersService {
    public void main(String[] args) {
        this.getAllNumbersFromOneThatDivideBy3(5);
    }

    public List<Integer> getAllNumbersFromOneThatDivideBy3(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0) {
                result.add(i);
            }
        }
        return result;
    }

    public List<Integer> getDividersOfNumbersThatDivideBy3(List<Integer> dividers){
        List<Integer> result = new ArrayList<>();
        for (int num: dividers){
            result.add(num/3);
        }
        return result;
    }

    public int getDividerOfNumberThatDividesBy3(int num_divided_by_3){
        if (num_divided_by_3 % 3 != 0) {
            return -1;
        }
        return num_divided_by_3/3;
    }
}
