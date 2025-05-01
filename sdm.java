import java.util.*;

public class sdm {
    static List<Integer> SDM_Guloso(float[] s, float[] f, int n) {
        f[0] = Float.NEGATIVE_INFINITY;
        var x = new ArrayList<Integer>();
        int i = 0;
        for (int k = 1; k <= n; k++) {
            if (s[k] > f[i]) {
                x.add(k);
                i = k;
            }
        }

        return x;
    }

    public static void main(String[] args) {
        
    }
}