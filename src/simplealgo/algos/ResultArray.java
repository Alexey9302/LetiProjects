package simplealgo.algos;

public class ResultArray <T> {

    T result = null;

    public ResultArray(T result) {
        this.result = result;
    }

    public void print(){
        if(result instanceof int[]){
            int[] res = (int[])result;

            for(int elem: res)
                System.out.println(elem);
        } else if(result instanceof char[]){
            char[] res = (char[])result;

            for(int elem: res)
                System.out.println(elem);
        } else if(result instanceof Integer){
            System.out.println(result);
        }
    }
}
