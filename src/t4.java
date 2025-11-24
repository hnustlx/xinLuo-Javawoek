public class t4 {
    public static void main(String[] args){
        for(int i=1;i<=9;i++){
            for(int j=1;j<=i;j++){
                System.out.printf("%d*%d=%-3d",i,j,i*j);
            }
            System.out.println();
        }
    }
}
