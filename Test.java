请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。

若队列为空，pop_front 和 max_value 需要返回 -1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class MaxQueue {
    Queue<Integer> queue;
    Deque<Integer> deque;
    public MaxQueue() {
        queue=new LinkedList<>();
        deque=new LinkedList<>();
    }
    
    public int max_value() {
        return deque.isEmpty()?-1:deque.peek();
    }
    
    public void push_back(int value) {
        queue.offer(value);
        while(!deque.isEmpty()&&deque.peekLast()<value){
            deque.pollLast();
        }
        deque.offer(value);
    }
    
    public int pop_front() {
        if(queue.isEmpty()){
            return -1;
        }
        int res=queue.poll();
        if(deque.peek()==res){
            deque.poll();
        }
        return res;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
 
 
 使用队列实现栈的下列操作：

push(x) -- 元素 x 入栈
pop() -- 移除栈顶元素
top() -- 获取栈顶元素
empty() -- 返回栈是否为空

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/implement-stack-using-queues
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class MyStack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    /** Initialize your data structure here. */
    public MyStack() {
        queue1=new LinkedList<>();
        queue2=new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue1.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while(queue1.size()!=1){
            queue2.offer(queue1.poll());
        }
        int res=queue1.poll();
        Queue<Integer> tmp=queue1;
        queue1=queue2;
        queue2=tmp;
        return res;
    }
    
    /** Get the top element. */
    public int top() {
        while(queue1.size()!=1){
            queue2.offer(queue1.poll());
        }
        int res=queue1.poll();
        queue2.offer(res);
        Queue<Integer> tmp=queue1;
        queue1=queue2;
        queue2=tmp;
        return res;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
 
 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode pre=null;
        ListNode cur=head;
        ListNode newHead=null;
        while(cur!=null){
            ListNode curNext=cur.next;
            if(curNext==null){
                newHead=cur;
            }
            cur.next=pre;
            pre=cur;
            cur=curNext;
        }
        return newHead;
    }
}

给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。

初始化 A 和 B 的元素数量分别为 m 和 n。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sorted-merge-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
        int i=m-1;
        int j=n-1;
        int len=A.length-1;
        while(i>=0&&j>=0){
            if(A[i]>B[j]){
                A[len--]=A[i--];
            }else{
                A[len--]=B[j--];
            }
        }
        if(i<0){
            for(int k=0;k<=j;k++){
                A[k]=B[k];
            }
        }
    }
}

在给定的网格中，每个单元格可以有以下三个值之一：

值 0 代表空单元格；
值 1 代表新鲜橘子；
值 2 代表腐烂的橘子。
每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。

返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rotting-oranges
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<Integer> queue=new LinkedList<>();
        int row=grid.length;
        int col=grid[0].length;
        int good=0;
        int times=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]==2){
                    queue.offer(i*col+j);
                }
                if(grid[i][j]==1){
                    good++;
                }
            }
        }
        while(!queue.isEmpty()){
            times++;
            int size=queue.size();
            while(size!=0){
                int tmp=queue.poll();
                int x=tmp/col;
                int y=tmp%col;
                if(x-1>=0&&grid[x-1][y]==1){
                    grid[x-1][y]=2;
                    queue.offer(tmp-col);
                    good--;
                }
                if(x+1<row&&grid[x+1][y]==1){
                    grid[x+1][y]=2;
                    queue.offer(tmp+col);
                    good--;
                }
                if(y-1>=0&&grid[x][y-1]==1){
                    grid[x][y-1]=2;
                    queue.offer(tmp-1);
                    good--;
                }
                if(y+1<col&&grid[x][y+1]==1){
                    grid[x][y+1]=2;
                    queue.offer(tmp+1);
                    good--;
                }
                size--;
            }
        }
        return good==0?(times==0?0:times-1):-1;
    }
}