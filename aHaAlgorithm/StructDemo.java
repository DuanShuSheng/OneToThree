import java.util.ArrayList;
import java.util.List;

public class StructDemo {
    public static void main(String[] args) {
        deCodeNumber();
        plalindrome();
        fishing();
    }

    // 解码数字
    static void deCodeNumber() {
        int[] array = {6, 3, 1, 7, 5, 8, 9, 2, 4};
        MyQueue queue = new MyQueue(array);
        boolean tobeDelete = true;
        while (queue.head != queue.tail) {
            if (tobeDelete) {
                tobeDelete = false;
                System.out.print(queue.dequeue() + " ");
            } else {
                tobeDelete = true;
                queue.enqueue(queue.dequeue());
            }
        }
        System.out.println();
    }

    // 判断是否是回文
    static void plalindrome() {
        String plalindrome = "abba";
        MyStack stack = new MyStack(plalindrome);
        for (int i = 0; i < plalindrome.length(); i++) {
            if (stack.isEmpty()) {
                continue;
            }
            if (stack.peek() != plalindrome.charAt(i)) {
                System.out.println("Not a palindrome");
                return;
            }
            stack.push(plalindrome.charAt(i));
        }
        if (stack.isEmpty()) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not a palindrome");
        }
    }

    // 小猫钓鱼
    static void fishing() {
        int[] arrLeft = {2, 4, 1, 2, 5, 6};
        int[] arrRight = {3, 1, 3, 5, 6, 4};
        MyQueue queueLeft = new MyQueue(arrLeft);
        MyQueue queueRight = new MyQueue(arrRight);
        MyStack stack = new MyStack();
        boolean leftTurn = true;
        while (!queueLeft.isEmpty() && !queueRight.isEmpty()) {
            if (stack.isEmpty()) {
                if (leftTurn) {
                    stack.push(queueLeft.dequeue());
                    leftTurn = false;
                } else {
                    stack.push(queueRight.dequeue());
                    leftTurn = true;
                }
            } else {
                if (leftTurn) {
                    int left = queueLeft.dequeue();
                    List<Integer> leftList = stack.getMatchArray(left);
                    if (leftList.size() == 0) {
                        stack.push(left);
                    } else {
                        for (Integer integer : leftList) {
                            queueLeft.enqueue(integer);
                        }
                    }
                    leftTurn = false;
                } else {
                    int right = queueRight.dequeue();
                    List<Integer> rightList = stack.getMatchArray(right);
                    if (rightList.size() == 0) {
                        stack.push(right);
                    } else {
                        for (Integer integer : rightList) {
                            queueRight.enqueue(integer);
                        }
                    }
                    leftTurn = true;
                }
            }
        }
        if (queueLeft.isEmpty()) {
            System.out.println("right赢了,left无牌,right牌");
            queueRight.print();
        } else {
            System.out.println("left赢了,right无牌,left牌");
            queueLeft.print();
        }
        System.out.println("桌上的牌");
        stack.print();
    }

    // 队列
    static class MyQueue {
        List<Integer> array = new ArrayList<>();
        int head;
        int tail;

        public MyQueue(int[] array) {
            for (int i : array) {
                this.array.add(i);
            }
            head = 0;
            tail = array.length;
        }
        public void enqueue(int value) {
            array.add(value);
            tail++;
        }
        public int dequeue() {
            if (head == tail) {
                System.out.println("Queue is empty");
                return -1;
            }
            int value = array.get(head);
            head++;
            return value;
        }

        public boolean isEmpty() {
            return head == tail;
        }

        public void print() {
            for (int i = head; i < tail; i++) {
                System.out.print(array.get(i) + " ");
            }
            System.out.println();
        }
    }

    // 栈
    static class MyStack {
        List<Integer> array = new ArrayList<>();
        int head = 0;

        public MyStack() {
        }
        public MyStack(String str) {
            for (int i = 0; i < str.length(); i++) {
                array.add((int) str.charAt(i));
            }
        }
        public void push(int value) {
            if (head == array.size()) {
                array.add(value);
            } else {
                array.set(head, value);
            }
            head++;
        }
        public int pop() {
            if (head == 0) {
                System.out.println("Stack is empty");
                return -1;
            }
            head--;
            return array.get(head);
        }
        public boolean isEmpty() {
            return head == 0;
        }
        public int peek() {
            if (head == 0) {
                System.out.println("Stack is empty");
                return -1;
            }
            return array.get(head - 1);
        }

        public List<Integer> getMatchArray(int num) {
            List<Integer> result = new ArrayList<>();
            boolean isMatched = false;
            for (int j = 0; j < head; j++) {
                Integer i = array.get(j);
                if (i == num) {
                    isMatched = true;
                    break;
                }
            }
            if (isMatched) {
                result.add(num);
                while(peek() != num) {
                    result.add(pop());
                }
                result.add(pop());
            }
            return result;
        }
        public void print() {
            for (int i = head - 1; i >= 0; i--) {
                System.out.print(array.get(i) + " ");
            }
            System.out.println();
        }
    }
}
