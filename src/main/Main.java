package main;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Person[] people = new Person[10];
        Random random = new Random(1128);
        for (int i=0; i<10; i++) {
            people[i] = new Person(((char) (97+i)) + "", (int) (random.nextInt(100)), (int) (random.nextInt(100)));
        }

        VIPQueue q = new VIPQueue(people);

        // VIPQueue 출력
//        for (int i=0; i<10; i++) {
//            Person p = q.pop();
//            System.out.println(p.toString() + " " + p.prioirty());
//        }

        q.pop(); q.pop(); q.pop();

        q.add(new Person("zz", 18, 11));
        q.add(new Person("zz", 18, 11));
        q.add(new Person("zz", 18, 11));

        for (int i=0; i<10; i++) {
            Person p = q.pop();
            System.out.println(p.toString() + " " + p.prioirty());
        }

    }
}
