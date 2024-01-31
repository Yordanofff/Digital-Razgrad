package com.example.hw7;

import org.springframework.stereotype.Service;

@Service
public class WordService {
    String printWordNTimes(String word, int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(word);
            result.append(" ");
        }
        return result.toString();
    }
}
