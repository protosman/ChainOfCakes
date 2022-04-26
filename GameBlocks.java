package ru.takemakekeep.chainofcakes;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;

public class GameBlocks {
    public boolean gameOver;
    private int numberOfBlock;
    boolean endStep;
    boolean moveTrue;
    private int maxBlocksInGroup;
    private int numNewBlockInStep;
    private int[] groupCounter;
    protected Block[][] blocksField;
    protected Block[] block;
    private short color;
    public final int STOPPED = 0;
    public final int UP = 1;
    public final int RIGHT = 2;
    public final int DOWN = 3;
    public final int LEFT = 4;
    private int state = STOPPED;
    private int stateTemp = state;
    private int numberOfColor;
    private Bitmap[][] imageBlock;
    protected int counter;
    protected int maxCounter;
    protected boolean sound;
    int fps=0;

    protected SoundPool mSoundPool;
    private int unionBlockSound;

    GameBlocks(int sizeX, int sizeY, int numberOfColor, int numNewBlockStep, int numMaxInGrps, Bitmap[][] imageBlock, int[][] fieldPos, int cntr, Context context, int maxCounter, boolean snd) {
        this.maxCounter = maxCounter;
        sound = snd;

        Log.d("unionBlockSound0", "Play" + sound);
        gameOver = false;
        maxBlocksInGroup = numMaxInGrps;
        numNewBlockInStep = numNewBlockStep;
        groupCounter = new int[sizeX * sizeY];
        moveTrue = false;
        counter = cntr;
        Log.e("Error joining thread  ", "GB cntrs = ");

        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        try {
            unionBlockSound = mSoundPool.load(context.getAssets().openFd("unionBlock.ogg"), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }


        this.numberOfColor = numberOfColor;
        if (this.numberOfColor > 5) this.numberOfColor = 5; 
        this.imageBlock = imageBlock;
        endStep = true;
        blocksField = new Block[sizeX][sizeY];

        numberOfBlock = sizeX * sizeY;
        block = new Block[numberOfBlock];
        for (int i = 0; i < numberOfBlock; i++) {
            groupCounter[i] = 0;
            block[i] = new Block(fieldPos);
        }
    }

    protected Bitmap getBitmap(int i) {
// up right down left number
// 0    0     0    0    0
// 0    0     0    1    1
// 0    0     1    0    2
// 0    0     1    1    3
// 0    1     0    0    4
// 0    1     0    1    5
// 0    1     1    0    6
// 0    1     1    1    7
// 1    0     0    0    8
// 1    0     0    1    9
// 1    0     1    0    10
// 1    0     1    1    11
// 1    1     0    0    12
// 1    1     0    1    13
// 1    1     1    0    14
// 1    1     1    1    15
        if (block[i].inGame) {

            block[i].update(fps);

            if (block[i].anim) {
                return imageBlock[block[i].color + 5][block[i].animNum()];
            }
            if (!block[i].unionUp && !block[i].unionRight && !block[i].unionDown && !block[i].unionLeft) {
                return imageBlock[block[i].color][0];
            } else if (!block[i].unionUp && !block[i].unionRight && !block[i].unionDown && block[i].unionLeft) {
                return imageBlock[block[i].color][1];
            } else if (!block[i].unionUp && !block[i].unionRight && block[i].unionDown && !block[i].unionLeft) {
                return imageBlock[block[i].color][2];
            } else if (!block[i].unionUp && !block[i].unionRight && block[i].unionDown && block[i].unionLeft) {
                return imageBlock[block[i].color][3];
            } else if (!block[i].unionUp && block[i].unionRight && !block[i].unionDown && !block[i].unionLeft) {
                return imageBlock[block[i].color][4];
            } else if (!block[i].unionUp && block[i].unionRight && !block[i].unionDown && block[i].unionLeft) {
                return imageBlock[block[i].color][5];
            } else if (!block[i].unionUp && block[i].unionRight && block[i].unionDown && !block[i].unionLeft) {
                return imageBlock[block[i].color][6];
            } else if (!block[i].unionUp && block[i].unionRight && block[i].unionDown && block[i].unionLeft) {
                return imageBlock[block[i].color][7];
            } else if (block[i].unionUp && !block[i].unionRight && !block[i].unionDown && !block[i].unionLeft) {
                return imageBlock[block[i].color][8];
            } else if (block[i].unionUp && !block[i].unionRight && !block[i].unionDown && block[i].unionLeft) {
                return imageBlock[block[i].color][9];
            } else if (block[i].unionUp && !block[i].unionRight && block[i].unionDown && !block[i].unionLeft) {
                return imageBlock[block[i].color][10];
            } else if (block[i].unionUp && !block[i].unionRight && block[i].unionDown && block[i].unionLeft) {
                return imageBlock[block[i].color][11];
            } else if (block[i].unionUp && block[i].unionRight && !block[i].unionDown && !block[i].unionLeft) {
                return imageBlock[block[i].color][12];
            } else if (block[i].unionUp && block[i].unionRight && !block[i].unionDown && block[i].unionLeft) {
                return imageBlock[block[i].color][13];
            } else if (block[i].unionUp && block[i].unionRight && block[i].unionDown && !block[i].unionLeft) {
                return imageBlock[block[i].color][14];
            } else if (block[i].unionUp && block[i].unionRight && block[i].unionDown && block[i].unionLeft) {
                return imageBlock[block[i].color][15];
            }
        }
        return null;
    }

    protected int getPosX(int i) {
        return block[i].getPosX();
    }

    protected int getPosY(int i) {
        return block[i].getPosY();
    }

    protected void update(int fps) {
        this.fps = fps;
        switch (state) {
            case UP:
                stateTemp = UP;
                do {
                    for (int y = 0; y < blocksField[0].length; y++) {
                        for (int x = 0; x < blocksField.length; x++) {
                            if (blocksField[x][y] != null) {
                                blocksField[x][y].itsNewBlock = false;
                                if (y == 0) {
                                    if (blocksField[x][y].grps == 0) {
                                        blocksField[x][y].isBloct = true;
                                    } else {
                                        bloctGroup(blocksField[x][y].grps);
                                    }

                                } else {
                                    if (!blocksField[x][y].isBloct) {
                                        if (blocksField[x][y - 1] == null) {
                                            blocksField[x][y - 1] = blocksField[x][y];
                                            blocksField[x][y] = null;
                                        } else {
                                            if (blocksField[x][y].grps == 0) {
                                                blocksField[x][y].isBloct = true;
                                            } else {
                                                bloctGroupAndReturn(blocksField[x][y].grps, x, y);
                                                return;
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                    setNeedPosBlocks(fps);
                } while (!allBlocksIsBloct());
                state = STOPPED;
                break;
            case DOWN:
                stateTemp = DOWN;
                do {
                    for (int y = blocksField[0].length - 1; y >= 0; y--) {
                        for (int x = 0; x < blocksField.length; x++) {
                            if (blocksField[x][y] != null) {
                                blocksField[x][y].itsNewBlock = false;
                                if (y == blocksField[0].length - 1) {
                                    if (blocksField[x][y].grps == 0) {
                                        blocksField[x][y].isBloct = true;
                                    } else {
                                        bloctGroup(blocksField[x][y].grps);
                                    }

                                } else {
                                    if (!blocksField[x][y].isBloct) {
                                        if (blocksField[x][y + 1] == null) {
                                            blocksField[x][y + 1] = blocksField[x][y];
                                            blocksField[x][y] = null;
                                        } else {
                                            if (blocksField[x][y].grps == 0) {
                                                blocksField[x][y].isBloct = true;
                                            } else {
                                                bloctGroupAndReturn(blocksField[x][y].grps, x, y);
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    setNeedPosBlocks(fps);
                } while (!allBlocksIsBloct());
                state = STOPPED;
                break;
            case LEFT:
                stateTemp = LEFT;
                do {
                    for (int x = 0; x < blocksField.length; x++) {
                        for (int y = 0; y < blocksField[x].length; y++) {
                            if (blocksField[x][y] != null) {
                                blocksField[x][y].itsNewBlock = false;
                                if (x == 0) {
                                    if (blocksField[x][y].grps == 0) {
                                        blocksField[x][y].isBloct = true;
                                    } else {
                                        bloctGroup(blocksField[x][y].grps);
                                    }
                                } else {
                                    if (!blocksField[x][y].isBloct) {
                                        if (blocksField[x - 1][y] == null) {
                                            blocksField[x - 1][y] = blocksField[x][y];
                                            blocksField[x][y] = null;
                                        } else {
                                            if (blocksField[x][y].grps == 0) {
                                                blocksField[x][y].isBloct = true;
                                            } else {
                                                bloctGroupAndReturn(blocksField[x][y].grps, x, y);
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    setNeedPosBlocks(fps);
                } while (!allBlocksIsBloct());
                state = STOPPED;
                break;
            case RIGHT:
                stateTemp = RIGHT;
                do {
                    for (int x = blocksField.length - 1; x >= 0; x--) {
                        for (int y = 0; y < blocksField[x].length; y++) {
                            if (blocksField[x][y] != null) {
                                blocksField[x][y].itsNewBlock = false;
                                if (x == blocksField.length - 1) {
                                    if (blocksField[x][y].grps == 0) {
                                        blocksField[x][y].isBloct = true;
                                    } else {
                                        bloctGroup(blocksField[x][y].grps);
                                    }
                                } else {
                                    if (!blocksField[x][y].isBloct) {
                                        if (blocksField[x + 1][y] == null) {
                                            blocksField[x + 1][y] = blocksField[x][y];
                                            blocksField[x][y] = null;
                                        } else {
                                            if (blocksField[x][y].grps == 0) {
                                                blocksField[x][y].isBloct = true;
                                            } else {
                                                bloctGroupAndReturn(blocksField[x][y].grps, x, y);
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    setNeedPosBlocks(fps);
                } while (!allBlocksIsBloct());
                state = STOPPED;
                break;
            case STOPPED:
                if(mSoundPool != null) mSoundPool.stop(unionBlockSound);
                for (int x = 0; x < blocksField.length; x++) {
                    for (int y = 0; y < blocksField[x].length; y++) {
                        if (blocksField[x][y] != null) {
                            blocksField[x][y].setIndexNeedPos(x, y);
//                            blocksField[x][y].update(fps);
                            unionBlocksAndGroups(x, y);
                            blocksField[x][y].isBloct = false;
                        }
                    }
                }
                if (!endStep) {
                    Log.d("gameblocks","1 ");
                    if (allBlocksIsStop()) {
                        Log.d("gameblocks","2 ");
                        deleteGroups();
                        if (state == STOPPED) {
                            Log.d("gameblocks","3 " );
                            if(animStop()) {
                                if(moveTrue) {
                                    moveTrue = false;
                                    addBlock(numNewBlockInStep);
                                }
                                endStep = true;
                            }
 /*                           if (moveTrue && animStop()) {
                                Log.d("gameblocks","4 ");
                                addBlock(numNewBlockInStep);
                                moveTrue = false;
                               endStep = true;
                             }
       */                  }
                    }
                }
                break;
            default:
                break;
        }
    }

    private boolean animStop() {
        for (int i = 0; i < block.length; i++) {
            if (block[i].anim) {
                return false;
            }
        }
        return true;
    }

    private void setNeedPosBlocks(int fps) {
        for (int x = 0; x < blocksField.length; x++) {
            for (int y = 0; y < blocksField[x].length; y++) {
                if (blocksField[x][y] != null) {
                    blocksField[x][y].setIndexNeedPos(x, y);
//                    blocksField[x][y].update(fps);
                }
            }
        }

    }

    private void deleteGroups() {
        for (int i = 1; i < groupCounter.length; i++) {
            if (groupCounter[i] >= maxBlocksInGroup) {
                for (int x = 0; x < blocksField.length; x++) {
                    for (int y = 0; y < blocksField[x].length; y++) {
                        if (blocksField[x][y] != null) {
                            if (blocksField[x][y].grps == i) {

                                blocksField[x][y].destroy();
                                blocksField[x][y] = null;
                            }
                        }
                    }
                }
                //counter = counter + groupCounter[i];
                if (counter < maxCounter) {
                    counter++;
                }
                //or counter + 1;
                moveTrue = true;
                Log.d("gameblocksdeletegroups","4 " + moveTrue);
                groupCounter[i] = 0;
                state = stateTemp;
            }
        }
    }

    private void unionBlocksAndGroups(int x, int y) {
        if (x < blocksField.length - 1) {
            if (blocksField[x + 1][y] != null) {
                if (!blocksField[x][y].isMove && !blocksField[x + 1][y].isMove) {
                    if (!blocksField[x][y].itsNewBlock && !blocksField[x + 1][y].itsNewBlock) {
                        if (stateTemp == RIGHT || stateTemp == LEFT) {
                            if (blocksField[x][y].color == blocksField[x + 1][y].color
                                    && blocksField[x + 1][y].getPosX() - blocksField[x][y].getPosX() <= blocksField[x][y].width) {
                                unionGrps(blocksField[x][y], blocksField[x + 1][y]);
                            }
                        }
                        if (blocksField[x][y].grps != 0 && blocksField[x][y].grps == blocksField[x + 1][y].grps) {
                            if (!blocksField[x][y].unionRight && !blocksField[x + 1][y].unionLeft) {
                                if (sound) {
                                    try {
                                        mSoundPool.play(unionBlockSound, 1, 1, 0, 0, 1);
                                        Log.d("unionBlockSound", "Play");
                                    } catch (Exception e) {
                                        if (mSoundPool != null) {
                                            mSoundPool.release();
                                            mSoundPool =null;
                                            Log.d("soundGameBlocksExc", " 1");
                                        }
                                    }
                                }
                                moveTrue = true;

                                blocksField[x][y].unionRight = true;
                                blocksField[x + 1][y].unionLeft = true;
                            }
                        }
                    }
                }
            }
        }
        if (y < blocksField[x].length - 1) {
            if (blocksField[x][y + 1] != null) {
                if (!blocksField[x][y].isMove && !blocksField[x][y + 1].isMove) {
                    if (!blocksField[x][y].itsNewBlock && !blocksField[x][y + 1].itsNewBlock) {
                        if (stateTemp == UP || stateTemp == DOWN) {
                            if (blocksField[x][y].color == blocksField[x][y + 1].color
                                    && blocksField[x][y + 1].getPosY() - blocksField[x][y].getPosY() <= blocksField[x][y].width * 0.6) {
                                unionGrps(blocksField[x][y], blocksField[x][y + 1]);
                            }
                        }
                        if (blocksField[x][y].grps != 0 && blocksField[x][y].grps == blocksField[x][y + 1].grps) {
                            if(!blocksField[x][y].unionDown & !blocksField[x][y + 1].unionUp) {
                                Log.d("unionBlockSound", "Play" + sound);
                                if (sound) {
                                    try {
                                        mSoundPool.play(unionBlockSound, 1, 1, 0, 0, 1);
                                        Log.d("unionBlockSound", "Play");
                                    } catch (Exception e) {
                                        if (mSoundPool != null) {
                                            mSoundPool.release();
                                            mSoundPool = null;
                                            Log.d("soundGameBlocksExc", " 1");
                                        }
                                    }
                                }
                                moveTrue = true;

                                blocksField[x][y].unionDown = true;
                                blocksField[x][y + 1].unionUp = true;
                            }
                        }
                    }
                }
            }
        }
    }

    private void unionGrps(Block block1, Block block2) {
        if (block1.grps != block2.grps || (block1.grps == 0 && block2.grps == 0)) {
            if (block1.grps != 0) {
                if (block2.grps != 0) {
                    int tempGrps = block2.grps;
                    groupCounter[block1.grps] += groupCounter[block2.grps];
                    for (int x = 0; x < blocksField.length; x++) {
                        for (int y = 0; y < blocksField[x].length; y++) {
                            if (blocksField[x][y] != null) {
                                if (blocksField[x][y].grps == tempGrps) {
                                    blocksField[x][y].grps = block1.grps;
                                }
                            }
                        }
                    }
                    groupCounter[tempGrps] = 0;
                } else {
                    block2.grps = block1.grps;
                    ++groupCounter[block1.grps];
                }
            } else {
                if (block2.grps != 0) {
                    block1.grps = block2.grps;
                    ++groupCounter[block2.grps];
                } else {
                    int tempGrps = freeGroup();
                    block1.grps = tempGrps;
                    block2.grps = tempGrps;
                }
            }
        }
    }

    private int freeGroup() {
        for (int i = 1; i < groupCounter.length; i++) {
            if (groupCounter[i] == 0) {
                groupCounter[i] = 2;
                return i;
            }
        }
        return -1;
    }

    private boolean allBlocksIsStop() {
        for (int x = 0; x < blocksField.length; x++) {
            for (int y = 0; y < blocksField[x].length; y++) {
                if (blocksField[x][y] != null) {
                    unionBlocksAndGroups(x, y);
                    if (blocksField[x][y].isMove) {
                        moveTrue = true;
                        Log.d("gameblock","allBlocksIsStop" + moveTrue);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected void bloctGroupAndReturn(int group, int x1, int y1) {
        switch (state) {
            case UP:
                for (int y = y1 - 1; y >= 0; y--) {
                    for (int x = blocksField.length - 1; x >= 0; x--) {
                        if (y == y1 - 1 && x >= x1) {
                            continue;
                        } else {
                            if (blocksField[x][y] != null) {
                                if (!blocksField[x][y].isBloct && blocksField[x][y + 1] == null) {
                                    blocksField[x][y + 1] = blocksField[x][y];
                                    blocksField[x][y] = null;
                                }
                            }
                        }
                    }
                }
                bloctGroup(group);
                break;
            case DOWN:
                for (int y = y1 + 1; y < blocksField[0].length; y++) {
                    for (int x = blocksField.length - 1; x >= 0; x--) {
                        if (y == y1 + 1 && x >= x1) {
                            continue;
                        } else {
                            if (blocksField[x][y] != null) {
                                if (!blocksField[x][y].isBloct && blocksField[x][y - 1] == null) {
                                    blocksField[x][y - 1] = blocksField[x][y];
                                    blocksField[x][y] = null;
                                }
                            }
                        }
                    }
                }
                bloctGroup(group);
                break;
            case LEFT:
                for (int x = x1 - 1; x >= 0; x--) {
                    for (int y = blocksField[0].length - 1; y >= 0; y--) {
                        if (x == x1 - 1 && y >= y1) {
                            continue;
                        } else {
                            if (blocksField[x][y] != null) {
                                if (!blocksField[x][y].isBloct && blocksField[x + 1][y] == null) {
                                    blocksField[x + 1][y] = blocksField[x][y];
                                    blocksField[x][y] = null;
                                }
                            }
                        }
                    }
                }
                bloctGroup(group);
                break;
            case RIGHT:
                for (int x = x1 + 1; x < blocksField.length; x++) {
                    for (int y = blocksField[0].length - 1; y >= 0; y--) {
                        if (x == x1 + 1 && y >= y1) {
                            continue;
                        } else {
                            if (blocksField[x][y] != null) {
                                if (!blocksField[x][y].isBloct && blocksField[x - 1][y] == null) {
                                    blocksField[x - 1][y] = blocksField[x][y];
                                    blocksField[x][y] = null;
                                }
                            }
                        }
                    }
                }
                bloctGroup(group);
                break;
            default:
                break;
        }
    }

    protected void bloctGroup(int group) {
        for (int x = 0; x < blocksField.length; x++) {
            for (int y = 0; y < blocksField[x].length; y++) {
                if (blocksField[x][y] != null) {
                    if (blocksField[x][y].grps == group) {
                        blocksField[x][y].isBloct = true;
                    }
                }
            }
        }
    }

    protected boolean allBlocksIsBloct() {
        for (int x = 0; x < blocksField.length; x++) {
            for (int y = 0; y < blocksField[x].length; y++) {
                if (blocksField[x][y] != null) {
                    if (!blocksField[x][y].isBloct) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected void sortingY() {
        Block tempBlock;
        for (int i = block.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (block[j].inGame && block[i].inGame) {
                    if (block[j].getPosY() > block[i].getPosY()) {
                        tempBlock = block[i];
                        block[i] = block[j];
                        block[j] = tempBlock;
                    }
                }
            }
        }

    }

    protected void setState(int state) {
        this.state = state;
    }

    protected void addBlock(int numBlockInStep) {
        for (int n = 0; n < numBlockInStep; n++) {
            for (int i = 0; i < block.length; i++) {
                if (i == block.length - 1 && block[i].inGame) {
                    gameOver = true;
                }
                if (!block[i].inGame) {
                    int x;
                    int y;
                    do {
                        x = (int) (Math.random() * blocksField.length);
                        y = (int) (Math.random() * blocksField[0].length);
                    } while (blocksField[x][y] != null);
                    color = (short) (Math.random() * numberOfColor);
                    block[i].newBlock(color, x, y);
                    blocksField[x][y] = block[i];
                    break;
                }
            }
        }
        sortingY();
    }

    public int[] reserveBlock() {
        int[] reserv = new int[block.length];
        for (int i = 0; i < reserv.length; i++) {
            reserv[i] = (block[i].getInGame() * 10000000 + block[i].indexNeedPosX * 100000
                    + block[i].indexNeedPosY * 1000 + block[i].grps * 10 + block[i].color);
            Log.e("reserveBlock ", Integer.toString(reserv[i]));
        }
        return  reserv;
    }
   // public int reverveField () {
   //     return (blocksField.length*10000000 + blocksField[0].length*100000 + numberOfColor*10000 + numNewBlockInStep *100+maxBlocksInGroup);
  //  }
    public void resume(int[] blocks) {
        for(int i = 0; i < blocks.length; i++) {

            if (blocks[i]/10000000 == 0)block[i].inGame = false;
            else {
                Log.e("Error resume  ", Integer.toString(blocks[i]));
                block[i].inGame = true;
                block[i].indexNeedPosX = blocks[i] % 10000000 / 100000;
                block[i].indexNeedPosY = blocks[i] % 100000 / 1000;
                block[i].grps = blocks[i] % 1000 / 10;
                block[i].color = (short) (blocks[i] % 10);
                blocksField[blocks[i] % 10000000 / 100000][blocks[i] % 100000 / 1000] = block[i];
                block[i].posRavenNeed();
                groupCounter[block[i].grps]++;
            }

        }
        sortingY();
    }
    

//                   ТЕСТЫ

//    protected void addBlock() {
//        block[0].newBlock((short)0, 0, 0);
//        blocksField[0][0] = block[0];
//        block[1].newBlock((short)0, 0, 1);
//        blocksField[0][1] = block[1];
//        block[2].newBlock((short)1, 0, 2);
//        blocksField[0][2] = block[2];
//        block[3].newBlock((short)1, 0, 3);
//        blocksField[0][3] = block[3];
//        block[4].newBlock((short)1, 1, 0);
//        blocksField[1][0] = block[4];
//        block[5].newBlock((short)1, 1, 1);
//        blocksField[1][1] = block[5];
//        block[6].newBlock((short)2, 2, 0);
//        blocksField[2][0] = block[6];
//        block[7].newBlock((short)2, 3, 0);
//        blocksField[3][0] = block[7];
//        block[8].newBlock((short)2, 4, 0);
//        blocksField[4][0] = block[8];
//        block[9].newBlock((short)2, 4, 1);
//        blocksField[4][1] = block[9];
//
//        block[0].grps = 1;
//        block[1].grps = 1;
//        block[2].grps = 2;
//        block[3].grps = 2;
//        block[4].grps = 0;
//        block[5].grps = 0;
//        block[6].grps = 0;
//        block[7].grps = 0;
//        block[8].grps = 3;
//        block[9].grps = 3;
//        groupCounter[1] = 2;
//        groupCounter[2] = 2;
//        groupCounter[3] = 2;
//    }
//    protected void addBlock() {
//        block[0].newBlock((short)1, 0, 0);
//        blocksField[0][0] = block[0];
//        block[1].newBlock((short)2, 0, 2);
//        blocksField[0][2] = block[1];
//        block[2].newBlock((short)1, 1, 0);
//        blocksField[1][0] = block[2];
//        block[3].newBlock((short)1, 1, 1);
//        blocksField[1][1] = block[3];
//        block[4].newBlock((short)2, 1, 2);
//        blocksField[1][2] = block[4];
//        block[5].newBlock((short)1, 2, 0);
//        blocksField[2][0] = block[5];
//        block[6].newBlock((short)2, 2, 2);
//        blocksField[2][2] = block[6];
//        block[7].newBlock((short)2, 3, 2);
//        blocksField[3][2] = block[7];
//        block[8].newBlock((short)0, 4, 1);
//        blocksField[4][1] = block[8];
//        block[9].newBlock((short)0, 4, 2);
//        blocksField[4][2] = block[9];
//
//        block[0].grps = 2;
//        block[1].grps = 3;
//        block[2].grps = 2;
//        block[3].grps = 2;
//        block[4].grps = 3;
//        block[5].grps = 2;
//        block[6].grps = 3;
//        block[7].grps = 3;
//        block[8].grps = 1;
//        block[9].grps = 1;
//        groupCounter[1] = 2;
//        groupCounter[2] = 4;
//        groupCounter[3] = 4;
//    }
//    protected void addBlock() {
//        block[0].newBlock((short)0, 0, 0);
//        blocksField[0][0] = block[0];
//        block[1].newBlock((short)0, 0, 1);
//        blocksField[0][1] = block[1];
//        block[2].newBlock((short)0, 0, 2);
//        blocksField[0][2] = block[2];
//        block[3].newBlock((short)0, 0, 3);
//        blocksField[0][3] = block[3];
//        block[4].newBlock((short)0, 1, 0);
//        blocksField[1][0] = block[4];
//        block[5].newBlock((short)0, 1, 2);
//        blocksField[1][2] = block[5];
//        block[6].newBlock((short)0, 2, 0);
//        blocksField[2][0] = block[6];
//        block[7].newBlock((short)0, 2, 2);
//        blocksField[2][2] = block[7];
//        block[8].newBlock((short)0, 3, 1);
//        blocksField[3][1] = block[8];
//        block[9].newBlock((short)0, 3, 2);
//        blocksField[3][2] = block[9];
//
//        block[0].grps = 0;
//        block[1].grps = 0;
//        block[2].grps = 0;
//        block[3].grps = 0;
//        block[4].grps = 0;
//        block[5].grps = 0;
//        block[6].grps = 0;
//        block[7].grps = 0;
//        block[8].grps = 0;
//        block[9].grps = 0;
//        groupCounter[1] = 2;
//        groupCounter[2] = 4;
//        groupCounter[3] = 4;
//    }
}
