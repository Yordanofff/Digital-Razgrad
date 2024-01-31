package com.example.hw7;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SumNumbersService {

public List<Integer> getNumbersFromOneToN(int n) {
    List<Integer> result = new ArrayList<>();
    for (int i = 1; i <= n ; i++) {
        result.add(i);
    }
    return result;
}

public int getSumOfNumbersFromOneToN(int n) {
    int result = 0;
    for (int i = 1; i <= n ; i++) {
        result += i;
    }
    return result;
}

}
