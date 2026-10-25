package org.telegram.ui.Components.Paint;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.telegram.messenger.ApplicationLoader;

/* loaded from: classes3.dex */
public class PersistColorPalette {
    public static final int COLORS_COUNT = 0;
    private static final List<Integer> DEFAULT_MODIFIABLE_COLORS = null;
    public static final int MODIFIABLE_COLORS_COUNT = 0;
    private static final List<Integer> PRESET_COLORS = null;
    public static final int PRESET_COLORS_COUNT = 0;
    private static PersistColorPalette[] instances;
    private final HashMap<Integer, Integer> brushColor;
    private final List<Integer> colors;
    private int currentAlignment;
    private int currentBrush;
    private int currentTextType;
    private String currentTypeface;
    private float currentWeight;
    private boolean fillShapes;
    private boolean inTextMode;
    private final SharedPreferences mConfig;
    private boolean needSaveBrushColor;
    private List<Integer> pendingChange;

    static {
        List<Integer> r0 = Arrays.asList(new Integer[]{-2645892, -8409090, -5926949, -2386514, -4531041});
        DEFAULT_MODIFIABLE_COLORS = r0;
        List<Integer> r1 = Arrays.asList(new Integer[]{-47814, -30208, -10742, -13318311, -10230046, -16087809, -4236558, -16777216, -1});
        PRESET_COLORS = r1;
        int r02 = r0.size();
        MODIFIABLE_COLORS_COUNT = r02;
        int r12 = r1.size();
        PRESET_COLORS_COUNT = r12;
        COLORS_COUNT = r02 + r12;
        instances = new PersistColorPalette[16];
    }

    public PersistColorPalette(int r4) {
        int r1 = COLORS_COUNT;
        this.colors = new ArrayList(r1);
        this.brushColor = new HashMap(Brush.BRUSHES_LIST.size());
        this.pendingChange = new ArrayList(r1);
        SharedPreferences r42 = ApplicationLoader.applicationContext.getSharedPreferences("photo_color_palette_" + r4, 0);
        this.mConfig = r42;
        this.currentBrush = r42.getInt("brush", 0);
        this.currentWeight = r42.getFloat("weight", 0.5f);
        this.currentTypeface = r42.getString("typeface", "roboto");
        this.currentAlignment = r42.getInt("text_alignment", 0);
        this.currentTextType = r42.getInt("text_type", 0);
        this.fillShapes = r42.getBoolean("fill_shapes", false);
        loadColors();
    }

    private void checkIndex(int r3) {
        if (r3 < 0) goto L7;
        if (r3 >= COLORS_COUNT) goto L7;
        return;
    L7:
        throw new IndexOutOfBoundsException("Color palette index should be in range 0 ... " + COLORS_COUNT);
    }

    private List<Integer> getAllColors() {
        ArrayList r0 = new ArrayList(PRESET_COLORS);
        r0.addAll(this.colors);
        return r0;
    }

    public static PersistColorPalette getInstance(int r2) {
        PersistColorPalette[] r0 = instances;
        if (r0[r2] != null) goto L6;
        r0[r2] = new PersistColorPalette(r2);
    L6:
        return instances[r2];
    }

    private void loadColors() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= MODIFIABLE_COLORS_COUNT) goto L6;
        this.colors.add(Integer.valueOf((int) this.mConfig.getLong("color_" + r1, DEFAULT_MODIFIABLE_COLORS.get(r1).intValue())));
        r1 = r1 + 1;
    L6:
        int r12 = Brush.BRUSHES_LIST.size();
        SharedPreferences r2 = this.mConfig;
        if (r0 >= r12) goto L9;
        this.brushColor.put(Integer.valueOf(r0), Integer.valueOf((int) r2.getLong("brush_color_" + r0, Brush.BRUSHES_LIST.get(r0).getDefaultColor())));
        r0 = r0 + 1;
        goto L6
    L9:
        this.brushColor.put(-1, Integer.valueOf((int) r2.getLong("brush_color_-1", -1)));
    }

    public void cleanup() {
        this.pendingChange.clear();
        this.pendingChange.addAll(DEFAULT_MODIFIABLE_COLORS);
        SharedPreferences.Editor r0 = this.mConfig.edit();
        int r1 = 0;
    L4:
        if (r1 >= Brush.BRUSHES_LIST.size()) goto L6;
        r0.remove("brush_color_" + r1);
        r1 = r1 + 1;
        goto L4
    L6:
        r0.remove("brush_color_-1");
        this.brushColor.clear();
        r0.apply();
        saveColors();
    }

    public int getColor(int r2) {
        checkIndex(r2);
        List<Integer> r1 = getAllColors();
        if (r2 < r1.size()) goto L11;
        int r12 = PRESET_COLORS_COUNT;
        if (r2 >= r12) goto L9;
        return PRESET_COLORS.get(r2).intValue();
    L9:
        return DEFAULT_MODIFIABLE_COLORS.get(r2 - r12).intValue();
    L11:
        return r1.get(r2).intValue();
    }

    public int getCurrentAlignment() {
        return this.currentAlignment;
    }

    public int getCurrentColor() {
        Integer r0 = this.brushColor.get(Integer.valueOf(this.currentBrush));
        if (r0 != null) goto L10;
        SharedPreferences r02 = this.mConfig;
        String r1 = "brush_color_" + this.currentBrush;
        int r2 = this.currentBrush;
        if (r2 != (-1)) goto L7;
        long r22 = -1;
    L8:
        r0 = Integer.valueOf((int) r02.getLong(r1, r22));
        this.brushColor.put(Integer.valueOf(this.currentBrush), r0);
        goto L10
    L7:
        r22 = Brush.BRUSHES_LIST.get(r2).getDefaultColor();
    L10:
        return r0.intValue();
    }

    public int getCurrentColorPosition() {
        int r0 = getCurrentColor();
        List<Integer> r4 = getAllColors();
        int r2 = 0;
    L4:
        if (r2 >= r4.size()) goto L9;
        if (r4.get(r2).intValue() == r0) goto L7;
        r2 = r2 + 1;
        goto L4
    L7:
        return r2;
    L9:
        return 0;
    }

    public int getCurrentTextType() {
        return this.currentTextType;
    }

    public String getCurrentTypeface() {
        return this.currentTypeface;
    }

    public float getCurrentWeight() {
        return this.currentWeight;
    }

    public boolean getFillShapes() {
        return this.fillShapes;
    }

    public float getWeight(String r3, float r4) {
        return this.mConfig.getFloat("weight_" + r3, r4);
    }

    public void resetCurrentColor() {
        setCurrentBrush(0);
    }

    public void saveColors() {
        if (this.pendingChange.isEmpty() == true) goto L5;
    L7:
        SharedPreferences.Editor r0 = this.mConfig.edit();
        if (this.pendingChange.isEmpty() == true) goto L20;
        int r1 = 0;
    L11:
        if (r1 >= MODIFIABLE_COLORS_COUNT) goto L18;
        String r3 = "color_" + r1;
        if (r1 >= this.pendingChange.size()) goto L16;
        List<Integer> r4 = this.pendingChange;
    L17:
        r0.putLong(r3, r4.get(r1).intValue());
        r1 = r1 + 1;
        goto L11
    L16:
        r4 = DEFAULT_MODIFIABLE_COLORS;
        goto L17
    L18:
        this.colors.clear();
        this.colors.addAll(this.pendingChange);
        this.pendingChange.clear();
    L20:
        if (this.needSaveBrushColor == true) goto L22;
    L25:
        r0.apply();
        return;
    L22:
        if (this.brushColor.get(Integer.valueOf(this.currentBrush)) == null) goto L24;
        r0.putLong("brush_color_" + this.currentBrush, r1.intValue());
    L24:
        this.needSaveBrushColor = false;
        goto L25
    L5:
        if (this.needSaveBrushColor == true) goto L7;
    }

    public void selectColor(int r2) {
        selectColor(r2, true);
    }

    public void selectColor(int r7, boolean r8) {
        int r0 = getAllColors().indexOf(Integer.valueOf(r7));
        if (r0 == (-1)) goto L8;
        if (r8 == false) goto L29;
        setCurrentBrushColorByColorIndex(r0);
        return;
    L29:
        return;
    L8:
        if (this.pendingChange.isEmpty() == false) goto L10;
        List<Integer> r1 = this.colors;
    L11:
        ArrayList r02 = new ArrayList(r1);
        this.pendingChange.clear();
        this.pendingChange.add(Integer.valueOf(r7));
        int r2 = 0;
    L12:
        int r3 = r02.size() - 1;
        List<Integer> r5 = this.pendingChange;
        if (r2 >= r3) goto L15;
        r5.add((Integer) r02.get(r2));
        r2 = r2 + 1;
        goto L12
    L15:
        int r03 = r5.size();
        List<Integer> r22 = DEFAULT_MODIFIABLE_COLORS;
        int r32 = r22.size();
        List<Integer> r52 = this.pendingChange;
        if (r03 >= r32) goto L22;
        int r04 = r52.size();
    L18:
        List<Integer> r12 = DEFAULT_MODIFIABLE_COLORS;
        if (r04 >= r12.size()) goto L24;
        this.pendingChange.add(r12.get(r04));
        r04 = r04 + 1;
    L24:
        if (r8 == false) goto L30;
        this.brushColor.put(Integer.valueOf(this.currentBrush), Integer.valueOf(r7));
        this.needSaveBrushColor = true;
        return;
    L30:
        return;
    L22:
        if (r52.size() <= r22.size()) goto L24;
        this.pendingChange = this.pendingChange.subList(0, r22.size());
        goto L24
    L10:
        r1 = this.pendingChange;
        goto L11
    }

    public void setCurrentAlignment(int r2) {
        this.currentAlignment = r2;
        this.mConfig.edit().putInt("text_alignment", r2).apply();
    }

    public void setCurrentBrush(int r2) {
        setCurrentBrush(r2, true);
    }

    public void setCurrentBrush(int r2, boolean r3) {
        this.currentBrush = r2;
        if (r3 == false) goto L5;
        this.mConfig.edit().putInt("brush", r2).apply();
    L5:
        Integer r22 = this.brushColor.get(Integer.valueOf(r2));
        if (r22 == null) goto L9;
        selectColor(r22.intValue(), false);
        saveColors();
        return;
    }

    public void setCurrentBrushColorByColorIndex(int r3) {
        int r32 = getColor(r3);
        this.brushColor.put(Integer.valueOf(this.currentBrush), Integer.valueOf(r32));
        this.needSaveBrushColor = true;
    }

    public void setCurrentTextType(int r2) {
        this.currentTextType = r2;
        this.mConfig.edit().putInt("text_type", r2).apply();
    }

    public void setCurrentTypeface(String r2) {
        this.currentTypeface = r2;
        this.mConfig.edit().putString("typeface", r2).apply();
    }

    public void setCurrentWeight(float r2) {
        this.currentWeight = r2;
        this.mConfig.edit().putFloat("weight", r2).apply();
    }

    public void setInTextMode(boolean r3) {
        if (this.inTextMode == r3) goto L10;
        this.inTextMode = r3;
        if (r3 == false) goto L8;
        setCurrentBrush(-1, false);
        return;
    L8:
        setCurrentBrush(this.mConfig.getInt("brush", 0), false);
        return;
    }

    public void setWeight(String r3, float r4) {
        this.mConfig.edit().putFloat("weight_" + r3, r4).apply();
    }

    public void toggleFillShapes() {
        this.fillShapes = !this.fillShapes;
        this.mConfig.edit().putBoolean("fill_shapes", this.fillShapes).apply();
    }
}
