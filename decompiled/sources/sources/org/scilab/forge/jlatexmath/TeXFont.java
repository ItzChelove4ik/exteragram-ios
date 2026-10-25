package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public interface TeXFont {
    public static final int NO_FONT = -1;

    TeXFont copy();

    TeXFont deriveFont(float r1);

    float getAxisHeight(int r1);

    float getBigOpSpacing1(int r1);

    float getBigOpSpacing2(int r1);

    float getBigOpSpacing3(int r1);

    float getBigOpSpacing4(int r1);

    float getBigOpSpacing5(int r1);

    boolean getBold();

    Char getChar(char r1, String r2, int r3);

    Char getChar(String r1, int r2);

    Char getChar(CharFont r1, int r2);

    Char getDefaultChar(char r1, int r2);

    float getDefaultRuleThickness(int r1);

    float getDenom1(int r1);

    float getDenom2(int r1);

    float getEM(int r1);

    Extension getExtension(Char r1, int r2);

    boolean getIt();

    float getKern(CharFont r1, CharFont r2, int r3);

    CharFont getLigature(CharFont r1, CharFont r2);

    int getMuFontId();

    Char getNextLarger(Char r1, int r2);

    float getNum1(int r1);

    float getNum2(int r1);

    float getNum3(int r1);

    float getQuad(int r1, int r2);

    boolean getRoman();

    float getScaleFactor();

    float getSize();

    float getSkew(CharFont r1, int r2);

    float getSpace(int r1);

    boolean getSs();

    float getSub1(int r1);

    float getSub2(int r1);

    float getSubDrop(int r1);

    float getSup1(int r1);

    float getSup2(int r1);

    float getSup3(int r1);

    float getSupDrop(int r1);

    boolean getTt();

    float getXHeight(int r1, int r2);

    boolean hasNextLarger(Char r1);

    boolean hasSpace(int r1);

    boolean isExtensionChar(Char r1);

    TeXFont scaleFont(float r1);

    void setBold(boolean r1);

    void setIt(boolean r1);

    void setRoman(boolean r1);

    void setSs(boolean r1);

    void setTt(boolean r1);
}
