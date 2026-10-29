package com.example.bingodice;

import java.util.*;

public final class BingoDiceSolver {
    private BingoDiceSolver() {}
    public static final class Move {
        public final int firstDie, target, row, col, score;
        public final Integer secondDie;
        public final boolean winsBingo;
        Move(int a, Integer b, int t, int r, int c, int s, boolean w) {
            firstDie=a; secondDie=b; target=t; row=r; col=c; score=s; winsBingo=w;
        }
    }
    // board[r][c] holds number; marked[r][c] is true for marked cells and free center.
    // Returns null when no legal move exists; caller should Pass.
    public static Move chooseMove(int[][] board, boolean[][] marked, int[] dice) {
        validate(board, marked, dice);
        Move best=null;
        Set<String> seen=new HashSet<>();
        for(int i=0;i<dice.length;i++) {
            best=consider(board,marked,dice[i],null,dice[i],best,seen);
            for(int j=0;j<dice.length;j++) if(i!=j)
                best=consider(board,marked,dice[i],dice[j],dice[i]*10+dice[j],best,seen);
        }
        return best;
    }
    private static Move consider(int[][] b, boolean[][] m, int first, Integer second, int n, Move best, Set<String> seen) {
        for(int r=0;r<5;r++) for(int c=0;c<5;c++) if(!m[r][c] && b[r][c]==n) {
            String k=r+":"+c; if(!seen.add(k)) continue;
            boolean win=complete(m,r,c); int progress=progress(m,r,c);
            Move x=new Move(first,second,n,r,c,(win?10000:0)+progress*10+(second==null?0:1),win);
            if(best==null || x.score>best.score) best=x;
        }
        return best;
    }
    private static int progress(boolean[][] m,int r,int c) {
        int s=0;
        for(int i=0;i<5;i++){ if(m[r][i])s++; if(m[i][c])s++; if(r==c&&m[i][i])s++; if(r+c==4&&m[i][4-i])s++; }
        return s;
    }
    private static boolean complete(boolean[][] m,int r,int c) {
        int a=0,b=0,d=0,e=0;
        for(int i=0;i<5;i++){if(m[r][i])a++;if(m[i][c])b++;if(m[i][i])d++;if(m[i][4-i])e++;}
        return a>=4||b>=4||(r==c&&d>=4)||(r+c==4&&e>=4);
    }
    private static void validate(int[][] b,boolean[][] m,int[] dice) {
        if(b==null||b.length!=5||m==null||m.length!=5)throw new IllegalArgumentException("Need 5x5 arrays");
        for(int r=0;r<5;r++)if(b[r]==null||b[r].length!=5||m[r]==null||m[r].length!=5)throw new IllegalArgumentException("Need 5x5 arrays");
        if(dice==null||dice.length==0)throw new IllegalArgumentException("Need dice");
        for(int d:dice)if(d<1||d>6)throw new IllegalArgumentException("Dice must be 1..6");
    }
}
