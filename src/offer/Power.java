package offer;
public class Power {
    /**
     *Description:求base的exponen次幂，用位与
     * eg.13=1+4+8
     * */
    public double power(double base, int exponent) {
        if(exponent==1) return base;
        if(exponent==0) return 1;
        if(Math.abs(base-0)<Math.pow(10,-10)) return 0;
        double res=1;
        int fg=Math.abs(exponent);
        while(fg!=0){
            if((fg&1)!=0) res*=base;
            base*=base;
            fg>>=1;
        }
        res=exponent>0?res:(1.0/res);
        return res;
    }
}
