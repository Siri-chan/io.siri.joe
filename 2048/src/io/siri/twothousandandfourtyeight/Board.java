/*
 * Copyright (c) 2022. Kira "Siri" K.
 * Distributed subject to the terms of the Mozilla Public License (MPL) v 2.0
 * See the LICENSE File for more Details
 * If a copy of the MPL was not distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.siri.twothousandandfourtyeight;

import io.siri.joe.Core;
import io.siri.joe.GameObject;
import io.siri.joe.Maths;
import io.siri.joe.Vector2Int;

import java.awt.*;

import static java.awt.event.KeyEvent.*;

public class Board extends GameObject {
    int[][] board=new int[4][4];
    public Board () {
        super(new Vector2Int(),new Dimension());
        placeTwo();
    }
    int hasFree(){
        for(int[]y:board)
            for(int x:y)
                if(x<2)return 1;
        return 0;
    }
    void placeTwo(){
        if (hasFree()<1) return;
        int x,y;
        do{
            x=vec();y=vec();
        }while(board[x][y]>0);
        board[x][y]=2;
    }
    int notDone(){
        int moves,x,y;
        for(moves=y=0;y<4;y++){
            for(x=0;x<4;x++){
                if(board[y][x]>2047) return -1;
                else if(x<3&&board[y][x]==board[y][x+1]||
                        y<3&&board[y][x]==board[y+1][x])
                    moves++;
            }
        }
        return hasFree()+moves;
    }
    int vec(){
        return Core.c.rand.nextInt(4);
    }
    public void tic(double delta, int[] inputs) {
        if (!(notDone() > 0)) return;
        resolve(inputs);
        placeTwo();
    }
    void resolve(int inputs[]){
        for (int input : inputs) {
            switch (input) {
                case VK_LEFT:
                    if (fold(false, true) > 0) return;
                    break;
                case VK_RIGHT:
                    if (fold(true, true) > 0) return;
                    break;
                case VK_UP:
                    if (fold(false, false) > 0) return;
                    break;
                case VK_DOWN:
                    if (fold(true, false) > 0) return;
                    break;
            }
        }
    }

    // false,true  = up
    // true, true  = down
    // false,false = left
    // true, false = right
    int fold(boolean inv, boolean vert){
        int didMove=0;
        int nextSpot,x,y,v,q,r;
        int[][] nb = new int[4][4];
        for(int i=0;i<4;i++){
            nextSpot=inv?3:0;
            for(int j=0;j<4;j++){
                v=vert?i:j;
                x=inv?3-v:v;
                v=vert?j:i;
                y=inv?3-v:v;
                q=vert?x:nextSpot;
                r=vert?nextSpot:y;
                if(board[y][x]>0){
                    if(nb[r][q]<1){
                        nb[r][q]=board[y][x];
                        didMove+=(inv?-1:1)*(vert?y-r:x-q);
                    }else if(nb[r][q]==board[y][x]){
                        nb[r][q]*=2;
                        nextSpot+=inv?-1:1;
                        didMove++;
                    }else{
                        nextSpot+=inv?-1:1;//suckage
                        q=vert?x:nextSpot;
                        r=vert?nextSpot:y;
                        nb[r][q]=board[y][x];
                        didMove+=(inv?-1:1)*(vert?y-r:x-q);
                    }
                }
            }
        }
        board=nb;
        return didMove;
    }

    @Override
    public void render(Graphics g) {
        for (int y = 0; y < 4; y++){
            for (int x = 0; x < 4; x++){
                int depth = board[x][y];
                Color c = calcColor(depth);
                g.setColor(c);
                g.fillRect(Main.HEIGHT*x/4, Main.HEIGHT*y/4, Main.HEIGHT/4, Main.HEIGHT/4);
                if (depth != 0) {
                    g.setColor(Color.red);
                    g.drawString(Integer.toString(depth), Main.HEIGHT*x/4 + Main.HEIGHT/8, Main.HEIGHT*y/4 + Main.HEIGHT/8);
                }
            }
        }
    }
    Color calcColor(int depth) {
        if (depth == 0)
            return Color.BLACK;
        float[] hsb = Color.RGBtoHSB(Maths.log2(depth) * 10, Maths.log2(depth) * 10, Maths.log2(depth) * 10, null);
        return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
    }
}
