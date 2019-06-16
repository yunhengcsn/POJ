package offer;

import java.util.Stack;

/**
 * Description:定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 *
 * @author csn
 */
public class MinStack {

    /*
     * use one stack
     */
    class MinStack1 {
        int min = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<>();
        public void push(int x) {
            // only push the old minimum value when the current
            // minimum value changes after pushing the new value x
            if(x <= min){
                stack.push(min);
                min=x;
            }
            stack.push(x);
        }

        public void pop() {
            // if pop operation could result in the changing of the current minimum value,
            // pop twice and change the current minimum value to the last minimum value.
            if(stack.pop() == min) min=stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    /*
     * use two stacks
     */
    class MinStack2 {
        Stack<Integer> stack = new Stack<>();
        int min = Integer.MAX_VALUE;
        public void push(int node) {
            stack.push(node);
            if(node < min) min = node;
        }

        public void pop() {
            if(stack.pop() == min) {
                //update min
                min = Integer.MAX_VALUE;
                Stack<Integer> temp = new Stack<>();
                while(!stack.isEmpty()) {
                    min = Math.min(stack.peek(),min);
                    temp.push(stack.pop());
                }
                while(!temp.isEmpty()) stack.push(temp.pop());
            }
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return min;
        }
    }
}
