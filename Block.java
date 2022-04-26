package ru.takemakekeep.chainofcakes;



public class Block {
    int posX;
    int posY;
    int indexNeedPosX;
    int indexNeedPosY;
    int[][] fieldAllPos;
    int width;
    int grps;
    short color;
    boolean itsNewBlock;
    boolean inGame;
    boolean isBloct;
    boolean isMove;
    boolean anim;
    int animNum;
    int blockSpeed;
	boolean unionUp;
	boolean unionDown;
	boolean unionLeft;
	boolean unionRight;

    Block(int[][] fieldPos) {
        if (fieldPos != null) {
            fieldAllPos = fieldPos;
            grps = 0;
            animNum = 0;
            anim = false;
            inGame = false;
            isBloct = true;
            isMove = false;
            unionUp = false;
            unionDown = false;
            unionLeft = false;
            unionRight = false;
            blockSpeed = 1000;
            width = fieldPos[0][1] - fieldPos[0][0];
        }
    }

    protected void newBlock(short color, int x, int y){
        animNum = 0;
        itsNewBlock = true;
        this.color = color;
        indexNeedPosX = x;
        indexNeedPosY = y;
        posX = fieldAllPos[0][indexNeedPosX];
        posY = fieldAllPos[1][indexNeedPosY];
        inGame = true;
        anim = false;
    }
    protected int getPosX() {
        return posX;
    }
    protected int getPosY(){
        return posY;
    }
    protected int getInGame() {
        if (inGame) return 1;
        return 0;
    }
    protected void setIndexNeedPos(int x, int y) {
        indexNeedPosX = x;
        indexNeedPosY = y;
    }
    protected void posRavenNeed() {
        posX = fieldAllPos[0][indexNeedPosX];
        posY = fieldAllPos[1][indexNeedPosY];
    }

    protected void update(int fps) {

        if (fieldAllPos[0][indexNeedPosX] == posX
                && fieldAllPos[1][indexNeedPosY] == posY) {
            isMove = false;
        }
        else {
            isMove = true;
        }
        if (fieldAllPos[0][indexNeedPosX] < posX) {
            if (posX - fieldAllPos[0][indexNeedPosX] > width / 7) {
                blockSpeed = width * 10;
//                if(fieldAllPos[0].length > 12)
                    blockSpeed = width*5;
            } else {
                blockSpeed = fps;
            }
            posX = posX - (blockSpeed / fps);
        }
        if (fieldAllPos[0][indexNeedPosX] > posX) {
            if (fieldAllPos[0][indexNeedPosX] - posX > width / 7) {
                blockSpeed = width * 10;
//                if(fieldAllPos[0].length > 12)
                    blockSpeed = width*5;
            } else {
                blockSpeed = fps;
            }
            posX = posX + (blockSpeed / fps);
        }
        if (fieldAllPos[1][indexNeedPosY] < posY) {
            if (posY - fieldAllPos[1][indexNeedPosY] > width / 7) {
                blockSpeed = width * 10;
//                if(fieldAllPos[0].length > 12)
                    blockSpeed = width*5;
            } else {
                blockSpeed = fps;
            }
            posY = posY - (blockSpeed / fps);
        }
        if (fieldAllPos[1][indexNeedPosY] > posY) {
            if (fieldAllPos[1][indexNeedPosY] - posY > width / 7) {
                blockSpeed = width * 10;
//                if(fieldAllPos[0].length > 12)
                    blockSpeed = width*5;
            } else {
                blockSpeed = fps;
            }
            posY = posY + (blockSpeed / fps);
        }
    }

    public void destroy() {
            anim = true;

    }
    public int animNum() {
        animNum++;
        if (animNum < 5) return 0;
        if (animNum < 10) return 1;
        if (animNum < 15) return 2;
        if (animNum < 22) return 3;
        //if (animNum < 25) return 4;
       // if (animNum < 30) return 5;
        //if (animNum < 35) return 6;
       // if (animNum < 40) return 7;
        animNum = 0;
        grps = 0;
        inGame = false;
        anim = false;
        isBloct = true;
        isMove = false;
        unionUp = false;
        unionDown = false;
        unionLeft = false;
        unionRight = false;
        return 3;
    }
}
