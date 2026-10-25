package org.scilab.forge.jlatexmath;

import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.CharCompanionObject;
import ru.noties.jlatexmath.awt.Font;

/* loaded from: classes5.dex */
public class FontInfo {
    public static final int NUMBER_OF_CHAR_CODES = 256;
    private static Map<Integer, FontInfo> fonts;
    private final Object base;
    private int boldId;
    protected final String boldVersion;
    private int[][] extensions;
    private Font font;
    private final int fontId;
    private final String fontName;
    private int itId;
    protected final String itVersion;
    private final Map<CharCouple, Float> kern;
    private final Map<CharCouple, Character> lig;
    private float[][] metrics;
    private CharFont[] nextLarger;
    private final String path;
    private final float quad;
    private int romanId;
    protected final String romanVersion;
    private char skewChar;
    private final float space;
    private int ssId;
    protected final String ssVersion;
    private int ttId;
    protected final String ttVersion;
    private HashMap<Character, Character> unicode;
    private final float xHeight;

    public class CharCouple {
        private final char left;
        private final char right;
        final /* synthetic */ FontInfo this$0;

        public CharCouple(FontInfo r1, char r2, char r3) {
            this.this$0 = r1;
            this.left = r2;
            this.right = r3;
        }

        public boolean equals(Object r3) {
            CharCouple r32 = (CharCouple) r3;
            if (this.left == r32.left) goto L5;
            return false;
        L5:
            if (this.right != r32.right) goto L10;
            return true;
        L10:
            return false;
        }

        public int hashCode() {
            return (this.left + this.right) % 128;
        }
    }

    static {
        fonts = new HashMap();
    }

    public FontInfo(int r2, Object r3, String r4, String r5, int r6, float r7, float r8, float r9, String r10, String r11, String r12, String r13, String r14) {
        this.lig = new HashMap();
        this.kern = new HashMap();
        this.unicode = null;
        this.skewChar = CharCompanionObject.MAX_VALUE;
        this.fontId = r2;
        this.base = r3;
        this.path = r4;
        this.fontName = r5;
        this.xHeight = r7;
        this.space = r8;
        this.quad = r9;
        this.boldVersion = r10;
        this.romanVersion = r11;
        this.ssVersion = r12;
        this.ttVersion = r13;
        this.itVersion = r14;
        if (r6 == 0) goto L5;
        this.unicode = new HashMap(r6);
    L6:
        this.metrics = new float[r6][];
        this.nextLarger = new CharFont[r6];
        this.extensions = new int[r6][];
        fonts.put(Integer.valueOf(r2), this);
        return;
    L5:
        r6 = 256;
        goto L6
    }

    public static Font getFont(int r1) {
        return fonts.get(Integer.valueOf(r1)).getFont();
    }

    public void addKern(char r3, char r4, float r5) {
        this.kern.put(new CharCouple(this, r3, r4), new Float(r5));
    }

    public void addLigature(char r3, char r4, char r5) {
        this.lig.put(new CharCouple(this, r3, r4), new Character(r5));
    }

    public int getBoldId() {
        return this.boldId;
    }

    public int[] getExtension(char r2) {
        HashMap<Character, Character> r0 = this.unicode;
        int[][] r1 = this.extensions;
        if (r0 != null) goto L7;
        return r1[r2];
    L7:
        return r1[r0.get(Character.valueOf(r2)).charValue()];
    }

    public Font getFont() {
        if (this.font != null) goto L9;
        Object r0 = this.base;
        String r1 = this.path;
        if (r0 != null) goto L7;
        this.font = DefaultTeXFontParser.createFont(r1);
        goto L9
    L7:
        this.font = DefaultTeXFontParser.createFont(r1);
    L9:
        return this.font;
    }

    public int getId() {
        return this.fontId;
    }

    public int getItId() {
        return this.itId;
    }

    public float getKern(char r3, char r4, float r5) {
        Float r2 = this.kern.get(new CharCouple(this, r3, r4));
        if (r2 != null) goto L7;
        return 0.0f;
    L7:
        return r2.floatValue() * r5;
    }

    public CharFont getLigature(char r3, char r4) {
        Character r32 = this.lig.get(new CharCouple(this, r3, r4));
        if (r32 != null) goto L7;
        return null;
    L7:
        return new CharFont(r32.charValue(), this.fontId);
    }

    public float[] getMetrics(char r2) {
        HashMap<Character, Character> r0 = this.unicode;
        float[][] r1 = this.metrics;
        if (r0 != null) goto L7;
        return r1[r2];
    L7:
        return r1[r0.get(Character.valueOf(r2)).charValue()];
    }

    public CharFont getNextLarger(char r2) {
        HashMap<Character, Character> r0 = this.unicode;
        CharFont[] r1 = this.nextLarger;
        if (r0 != null) goto L7;
        return r1[r2];
    L7:
        return r1[r0.get(Character.valueOf(r2)).charValue()];
    }

    public float getQuad(float r1) {
        return this.quad * r1;
    }

    public int getRomanId() {
        return this.romanId;
    }

    public char getSkewChar() {
        return this.skewChar;
    }

    public float getSpace(float r1) {
        return this.space * r1;
    }

    public int getSsId() {
        return this.ssId;
    }

    public int getTtId() {
        return this.ttId;
    }

    public float getXHeight(float r1) {
        return this.xHeight * r1;
    }

    public boolean hasSpace() {
        if (this.space <= 1.0E-7f) goto L6;
        return true;
    L6:
        return false;
    }

    public void setBoldId(int r2) {
        if (r2 != (-1)) goto L5;
        r2 = this.fontId;
    L5:
        this.boldId = r2;
    }

    public void setExtension(char r4, int[] r5) {
        HashMap<Character, Character> r0 = this.unicode;
        if (r0 != null) goto L7;
        this.extensions[r4] = r5;
        return;
    L7:
        if (r0.containsKey(Character.valueOf(r4)) == true) goto L10;
        char r02 = (char) this.unicode.size();
        this.unicode.put(Character.valueOf(r4), Character.valueOf(r02));
        this.extensions[r02] = r5;
        return;
    L10:
        this.extensions[this.unicode.get(Character.valueOf(r4)).charValue()] = r5;
    }

    public void setItId(int r2) {
        if (r2 != (-1)) goto L5;
        r2 = this.fontId;
    L5:
        this.itId = r2;
    }

    public void setMetrics(char r4, float[] r5) {
        HashMap<Character, Character> r0 = this.unicode;
        if (r0 != null) goto L7;
        this.metrics[r4] = r5;
        return;
    L7:
        if (r0.containsKey(Character.valueOf(r4)) == true) goto L10;
        char r02 = (char) this.unicode.size();
        this.unicode.put(Character.valueOf(r4), Character.valueOf(r02));
        this.metrics[r02] = r5;
        return;
    L10:
        this.metrics[this.unicode.get(Character.valueOf(r4)).charValue()] = r5;
    }

    public void setNextLarger(char r4, char r5, int r6) {
        HashMap<Character, Character> r0 = this.unicode;
        if (r0 != null) goto L7;
        this.nextLarger[r4] = new CharFont(r5, r6);
        return;
    L7:
        if (r0.containsKey(Character.valueOf(r4)) == true) goto L10;
        char r02 = (char) this.unicode.size();
        this.unicode.put(Character.valueOf(r4), Character.valueOf(r02));
        this.nextLarger[r02] = new CharFont(r5, r6);
        return;
    L10:
        this.nextLarger[this.unicode.get(Character.valueOf(r4)).charValue()] = new CharFont(r5, r6);
    }

    public void setRomanId(int r2) {
        if (r2 != (-1)) goto L5;
        r2 = this.fontId;
    L5:
        this.romanId = r2;
    }

    public void setSkewChar(char r1) {
        this.skewChar = r1;
    }

    public void setSsId(int r2) {
        if (r2 != (-1)) goto L5;
        r2 = this.fontId;
    L5:
        this.ssId = r2;
    }

    public void setTtId(int r2) {
        if (r2 != (-1)) goto L5;
        r2 = this.fontId;
    L5:
        this.ttId = r2;
    }
}
