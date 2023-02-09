//https://leetcode.com/problems/regular-expression-matching
//TC:mn
//SC:mn
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean [][] dp= new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int j=1;j<=n;j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-2];
            }
        }
        for(int i=1 ; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(p.charAt(j-1) != '*'){
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }else{
                    dp[i][j] = dp[i][j-2];//0case
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.')//1case preceeding char check
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                }
            }
        }
        return dp[m][n];
    }
}

//Sc: n
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean [] dp= new boolean[n+1];
        dp[0] = true;
        for(int j=1;j<=n;j++){
            if(p.charAt(j-1) == '*'){
                dp[j] = dp[j-2];
            }
        }
        
        for(int i=1 ; i<=m; i++){
            boolean diagUp = dp[0];
            dp[0] = false;
            for(int j=1; j<=n; j++){
                boolean temp = dp[j];
                if(p.charAt(j-1) != '*'){
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.'){
                        dp[j] = diagUp;;
                    }else{
                        dp[j] = false;
                    }
                }else{
                    if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.')//1case preceeding char check
                        dp[j] = dp[j-2] || dp[j];
                    else
                        dp[j] = dp[j-2];//0case
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }
}
