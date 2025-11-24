public class t14 {
    public static <T extends Comparable<T>> T findMax(T[] array) {
        T max = array[0];
        for(T element:array){
            if(element.compareTo(max)>0){
                max=element;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        Integer[] nums = {1,5,3,9,2};
        System.out.println(findMax(nums));
    }
}