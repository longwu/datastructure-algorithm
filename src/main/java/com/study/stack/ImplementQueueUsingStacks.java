package com.study.stack;

import java.util.*;
import java.util.function.Consumer;

/**
 * 232. 用栈实现队列
 * <p>
 * 使用栈实现队列的下列操作：
 * <p>
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 * 示例:
 * <p>
 * MyQueue queue = new MyQueue();
 * <p>
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 * 说明:
 * <p>
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 * <p>
 * https://leetcode-cn.com/problems/implement-queue-using-stacks
 */
public class ImplementQueueUsingStacks {
    public static void main(String[] args) {
        //MyQueue obj = new MyQueue();
        //MyQueueByStack2 obj = new MyQueueByStack2();
        MyQueueByList obj = new MyQueueByList();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        //根据队列先入先出的原理, 下面pop和peak都应该是第一个元素1
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
//        System.out.println(obj.peek());
//        System.out.println(obj.empty());
    }
}


/**
 * 使用两个栈来实现一个队列
 * 比如将1-2-3放入第一个栈里变成3-2-1  先进的到了最底下
 * 再将第一个栈的元素3-2-1放到第2个栈里变成了1-2-3  先进的到了最底下
 * <p>
 * 最后使用栈的pop方法将最顶上的元素1移除,之后是2,3
 */
class MyQueueByStack {

    private Stack<Integer> input;
    private Stack<Integer> output;

    /**
     * Initialize your data structure here.
     */
    public MyQueueByStack() {
        input = new Stack<Integer>();
        output = new Stack<Integer>();
    }

    /**
     * Push element x to the back of queue.
     * <p>
     * 需要借助两个stack进行元素的颠倒, 1 2 3 入input栈后 变成 3 2 1, 再取出入output栈后变成 1 2 3, 就成了先进先出的效果
     */
    public void push(int x) {
        //在往input栈中方元素之前, 需要把output栈里的元素全部放回input栈
        while (!output.isEmpty()) {
            input.push(output.pop());
        }
        //然后再将元素x放入input
        input.push(x);
        //最后再将所有元素放入到output栈, 这样从output出去第一个的元素为x
        while (!input.isEmpty()) {
            output.push(input.pop());
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (output.isEmpty()) {
            return -1;
        }
        return output.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (output.isEmpty()) {
            return -1;
        }
        return output.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return output.isEmpty();
    }
}

/**
 * 使用两个栈LIFO实现FIFO队列
 */
class MyQueueByStack2 {

    private Stack<Integer> inputStack = new Stack();
    private Stack<Integer> outputStack = new Stack();

    public void push(int x) {
        // 先将元素放到input栈中, 然后再转入output栈中

        // 将outputStack中的元素1 2 3全部取出放到inputStack中变成 3 2 1
        while (!outputStack.isEmpty()) {
            inputStack.push(outputStack.pop());
        }

        // 将新入的元素放到inputStack顶部 顺序变成了 x 3 2 1
        inputStack.push(x);

        // 再将inputStack中的元素x 3 2 1取出放到outputStack中变成 1 2 3 x
        while (!inputStack.isEmpty()) {
            outputStack.push(inputStack.pop());
        }
    }

    /**
     * 取出并移除栈顶元素
     *
     * @return
     */
    public int pop() {
        if (outputStack.isEmpty())
            return -1;
        return outputStack.pop();
    }

    /**
     * 取出不移除栈顶元素
     *
     * @return
     */
    public int peek() {
        if (outputStack.isEmpty())
            return -1;
        return outputStack.peek();
    }

    /**
     * 获取堆中元素总个数
     *
     * @return
     */
    public int size() {
        return outputStack.size();
    }
}

class MyQueueByList {

    List<Integer> result = new ArrayList<Integer>();

    public void push(int x) {
        result.add(x);
    }

    /**
     * 将第一个元素弹出, 并移除该元素
     * @return
     */
    public int pop() {
        if (result.isEmpty())
            return -1;
        int element = result.get(0);
        result.remove(0);
        return element;
    }

    /**
     * 将第一个元素弹出, 不移除该元素
     * @return
     */
    public int peek() {
        if (result.isEmpty())
            return -1;
        return result.get(0);
    }
}
