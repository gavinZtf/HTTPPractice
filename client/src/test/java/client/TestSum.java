package client;

public class TestSum {
    
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    
    /**
     * @功能 
     * @作者 Gavin
     */
   public int getNextSeqNum() {
       seqNum.set(seqNum.get() + 1);
       return seqNum.get();
   }

    public static void main(String[] args) {
        TestSum sn = new TestSum();
        
        TestClient t1 = new TestClient(sn);  
        TestClient t2 = new TestClient(sn);  
        TestClient t3 = new TestClient(sn);  
        t1.start();  
        t2.start();  
        t3.start();
        
    }
    
    /**
     * 内部类
     */
    private static class TestClient extends Thread {
        
        private TestSum ts;

        public TestClient(TestSum sn) {
            this.ts = sn;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("thread[" + Thread.currentThread().getName() + "]----> sn[" 
                    + ts.getNextSeqNum());
            }
        }
        
    }

}
