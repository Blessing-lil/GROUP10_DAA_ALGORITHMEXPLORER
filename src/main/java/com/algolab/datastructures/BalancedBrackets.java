package com.algolab.datastructures;

import java.util.ArrayDeque;
import java.util.Deque;

public class BalancedBrackets {

    public boolean isBalanced(String expression) {

        if (expression == null) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char character : expression.toCharArray()) {

            if (character == '(' ||
                character == '{' ||
                character == '[') {

                stack.push(character);
            }

            else if (character == ')' ||
                     character == '}' ||
                     character == ']') {

                if (stack.isEmpty()) {
                    return false;
                }

                char opening = stack.pop();

                if (!matches(opening, character)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private boolean matches(char opening, char closing) {

        return (opening == '(' && closing == ')') ||
               (opening == '{' && closing == '}') ||
               (opening == '[' && closing == ']');
    }

    public String check(String expression) {

        if (isBalanced(expression)) {
            return "Balanced brackets!";
        }

        return "Unbalanced brackets!";
    }
}
