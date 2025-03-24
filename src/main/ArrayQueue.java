package main;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayQueue<E> {

    private E[] queue;
    private int front, rear, size;

    /**
     * 자료구조 배포 PDF의 ArrayQueue 구현 문제입니다.
     * 배열의 front는 항상 맨 처음 원소의 이전 index를 가리키고 rear는 마지막 원소의 index를 가리키도록 구현하세요.
     * ArrayQueue 구현이 어렵지 않아 큐 활용 문제인 요세푸스 문제 요구사항 또한 완성하면 됩니다.
     * PR 날릴 때 작성한 코드에 대한 1줄 요약도 작성해주세요.
     */
    public ArrayQueue() {
        queue = (E[]) new Object[2];
        front = rear = size = 0;
    }

    public E element() {
        // TODO 큐의 맨 앞의 원소를 반환하는 코드를 작성하시오
        // TODO 큐가 비어있다면 NoSuchElementException 예외를 발생시키시오
        if (isEmpty()) {throw new NoSuchElementException();}
        E item = queue[front];

        return item;
    }

    public void add(E item) {
        // TODO 큐에 item을 추가하는 코드를 작성하시오
        // TODO item이 null이라면 NullPointerException 예외를 발생시키시오
        // TODO 큐가 꽉 차있으면 길이를 resize()를 활용하여 2배로 늘리시오
        if (item == null) {throw new NullPointerException();}
        if (size > 0 && queue[size()-1] != null) {resize(size() * 2);}
        else if (size == 0) {size++;}
        queue[rear] = item;
//        System.out.println(rear);

        rear = 0;
        for (int i=0; i<size(); i++){
            if (queue[i] != null) {rear++;}
            else {break;}
        }
    }

    private void resize(int newSize) {
        // TODO resize를 완성시키시오
        E[] tmp = (E[]) new Object[newSize];
        for (int i=0; i<Math.min(newSize, size()); i++) {
            tmp[i] = queue[i];
        }
        queue = tmp.clone();
        size = newSize;
    }

    public E remove() {
        // TODO 큐에서 item을 반환하고 삭제하는 코드를 작성하시오
        // TODO 큐가 비어있다면 NoSuchElementException 예외를 발생시키시오
        // TODO 큐가 배열의 1/4만 사용하고 있다면, 길이를 resize()를 활용하여 절반으로 줄이시오
        if (isEmpty() || queue[front] == null) {throw new NoSuchElementException();}
        if (rear == queue.length/4) {resize(size()/2);}

        E item = queue[front];

        E[] tmp = (E[]) new Object[size()];
        for (int i=0; i<size()-1; i++) {
            tmp[i] = queue[i+1];
        }
        queue = tmp.clone();
        size--;

        rear = 0;
        for (int i=0; i<size(); i++){
            if (queue[i] != null) {rear++;}
            else {break;}
        }
        return item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
