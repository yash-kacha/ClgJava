import java.util.Arrays;

public class test1 {
    public static void main(String[] args) {
        int[] d = { 0,1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        System.out.println(trap(d));
    }

    public static int subArray(int[] m, int b, int e) {
        
        int max = m[b];
        for (int i = b; i <e; i++) {
            if (m[i] > max)
                max = m[i];
        }
        return max;
    }
    public static int trap(int[] hight) {

        System.out.println(Arrays.toString(hight));
        System.out.println(hight.length);

        if (hight.length < 3)
            return 0;
            System.out.println(hight.length);

        int w = 0;
        for (int i = 1; i < hight.length - 1; i++) {

            int temp1=(subArray(hight, 0, i)),temp2=(subArray(hight, i + 1, hight.length));

            int min = (temp1 >temp2)
                    ? temp2
                    : temp1;

            min = (hight[i] < min) ? min-hight[i] : 0;
            System.out.println("in step : "+i+" water was increased : "+min);

            w+=min;
        }
        return w;
    }
}
