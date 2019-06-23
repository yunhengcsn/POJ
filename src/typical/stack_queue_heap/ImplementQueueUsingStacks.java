package typical.stack_queue_heap;

import java.util.Stack;

/**
 * Description: 232. Implement Queue using Stacks
 *
 * @author csn
 */
public class ImplementQueueUsingStacks {

    /*
     * Solution 1: use two stacks
     */
    class MyQueue1 {
        Stack<Integer> stackPush;
        Stack<Integer> stackPop;

        /** Initialize your data structure here. */
        public MyQueue1() {
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stackPush.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            peek();
            return stackPop.pop();
        }

        /** Get the front element. */
        public int peek() {
            if(stackPop.isEmpty()) {
                while(!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stackPush.isEmpty() && stackPop.isEmpty();
        }
    }

    /**
     * Solution 2 : use another stack when push
     */
    class MyQueue2 {
        Stack<Integer> queue = new Stack<>();
        // Push element x to the back of queue.
        public void push(int x) {
            Stack<Integer> temp = new Stack<>();
            while(!queue.empty()){
                temp.push(queue.pop());
            }
            queue.push(x);
            while(!temp.empty()){
                queue.push(temp.pop());
            }
        }

        // Removes the element from in front of queue.
        public int pop() {
            return queue.pop();
        }

        // Get the front element.
        public int peek() {
            return queue.peek();
        }

        // Return whether the queue is empty.
        public boolean empty() {
            return queue.empty();
        }
    }

/*
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
}
