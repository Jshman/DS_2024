package main;

import java.util.Stack;

public class PostNotation {

    private final int N;
    private final String notation;
    private final int[] number;

    public PostNotation(int N, String notation, int[] number) {
        this.N = N;
        this.notation = notation;
        this.number = number;
    }

    /**
     *  {@link PostNotation}의 String notation 은 후위 표기식임이 보장됩니다.
     *
     *  <p>getNumber(char alphabet) 메소드를 활용하여 calcValue()를 완성하면 됩니다.</p>
     *  <p>src/test/PostNotationTest 에서 테스트 결과를 확인하거나,</p>
     *  <p>MainJava를 실행하여 임의의 중위 표기식 입력으로 결과값을 확인하면 됩니다.</p>
     *  <p>연산자는 모두 4가지로 +,-,*,/ 가 주어지며 나눗셈 연산은 소숫점을 버립니다.</p>
     *  <p></p>
     *  <p>예제 입력</p>
     *  <p>5</p>
     *  <p>A+B*C-D/E</p></p>
     *  <p>1</p>
     *  <p>2</p>
     *  <p>3</p>
     *  <p>4</p>
     *  <p>5</p>
     *  <p></p>
     *  <p>예제 출력</p>
     *  <p>7</p>
     */
    public int calcValue() {
        // TODO 후위 표기식과 각 피연산자에 대응하는 값들이 주어져 있을 때, 그 식을 계산하는 프로그램을 작성하시오.
        System.out.println(notation);
        // 스택에 식 표현하기
        Stack<Character> exp = new Stack<>();
        for (int i=notation.length()-1; i>-1; i--) {exp.push(notation.charAt(i));}

        // 숫자만 담는 스택
        Stack<Integer> nums = new Stack<>();

        while (!exp.isEmpty()) {
            char c = exp.pop();
            if ('A'<= c && c <='Z') {
                nums.push(getNumber(c));
            } else {
                int n2 = nums.pop();
                int n1 = nums.pop();
                int result = switch (c) {
                    case '+' -> n1 + n2;
                    case '-' -> n1 - n2;
                    case '*' -> n1 * n2;
                    case '/' -> n1 / n2;
                    default -> 0; //소수점 버림
                };
                nums.push(result);
            }
        }

        return nums.pop();
    }

    private int getNumber(char alphabet) {
        return number[alphabet - 'A'];
    }

    public static String convert(String infixNotation) {
        StringBuilder res = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<infixNotation.length(); i++) {
            char element = infixNotation.charAt(i);
            if('A' <= element && element <= 'Z') {
                res.append(element);
            } else {
                if (element == '(') {
                    stack.add('(');
                } else if (element == '*' || element == '/') {
                    while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
                        res.append(stack.pop());
                    }
                    stack.add(element);
                } else if (element == '+' || element == '-') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        res.append(stack.pop());
                    }
                    stack.add(element);
                } else { // )
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        res.append(stack.pop());
                    }
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.toString();
    }
}
