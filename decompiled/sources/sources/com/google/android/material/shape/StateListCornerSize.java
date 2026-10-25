package com.google.android.material.shape;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.material.R;
import com.google.android.material.shape.ShapeAppearanceModel;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class StateListCornerSize {
    private static final int INITIAL_CAPACITY = 10;
    CornerSize[] cornerSizes;
    private CornerSize defaultCornerSize;
    int stateCount;
    int[][] stateSpecs;

    public StateListCornerSize() {
        this.stateSpecs = new int[10][];
        this.cornerSizes = new CornerSize[10];
    }

    private void addStateCornerSize(int[] r3, CornerSize r4) {
        int r0 = this.stateCount;
        if (r0 != 0) goto L5;
    L6:
        this.defaultCornerSize = r4;
    L8:
        if (r0 < this.stateSpecs.length) goto L10;
        growArray(r0, r0 + 10);
    L10:
        int[][] r02 = this.stateSpecs;
        int r1 = this.stateCount;
        r02[r1] = r3;
        this.cornerSizes[r1] = r4;
        this.stateCount = r1 + 1;
        return;
    L5:
        if (r3.length != 0) goto L8;
        goto L6
    }

    public static StateListCornerSize create(Context r4, TypedArray r5, int r6, CornerSize r7) {
        int r0 = r5.getResourceId(r6, 0);
        if (r0 != 0) goto L7;
        return create(ShapeAppearanceModel.getCornerSize(r5, r6, r7));
    L7:
        if (r4.getResources().getResourceTypeName(r0).equals("xml") == false) goto L9;
        XmlResourceParser r52 = r4.getResources().getXml(r0);     // Catch: Throwable -> L34
        StateListCornerSize r62 = new StateListCornerSize();     // Catch: Throwable -> L22
        AttributeSet r02 = Xml.asAttributeSet(r52);     // Catch: Throwable -> L22
    L12:
        int r1 = r52.next();     // Catch: Throwable -> L22
        if (r1 == 2) goto L17;
        if (r1 != 1) goto L12;
    L17:
        if (r1 != 2) goto L27;
        if (r52.getName().equals(PluginsConstants.Settings.TYPE_SELECTOR) == false) goto L24;
        r62.loadCornerSizesFromItems(r4, r52, r02, r4.getTheme());     // Catch: Throwable -> L22
    L24:
        r52.close();     // Catch: Throwable -> L34 Throwable -> L34 Throwable -> L34
        return r62;
    L27:
        throw new XmlPullParserException("No start tag found");     // Catch: Throwable -> L22
    L22:
        th = move-exception;
        if (r52 != null) goto L36;
    L33:
        throw th;     // Catch: Throwable -> L34 Throwable -> L34 Throwable -> L34
    L36:
        r52.close();     // Catch: Throwable -> L31
    L31:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L34 Throwable -> L34 Throwable -> L34
        goto L33
    L35:
        return create(r7);
    L9:
        return create(ShapeAppearanceModel.getCornerSize(r5, r6, r7));
    }

    public static StateListCornerSize create(CornerSize r2) {
        StateListCornerSize r0 = new StateListCornerSize();
        r0.addStateCornerSize(StateSet.WILD_CARD, r2);
        return r0;
    }

    private void growArray(int r4, int r5) {
        int[][] r0 = new int[r5][];
        System.arraycopy(this.stateSpecs, 0, r0, 0, r4);
        this.stateSpecs = r0;
        CornerSize[] r52 = new CornerSize[r5];
        System.arraycopy(this.cornerSizes, 0, r52, 0, r4);
        this.cornerSizes = r52;
    }

    private int indexOfStateSet(int[] r4) {
        int[][] r0 = this.stateSpecs;
        int r1 = 0;
    L4:
        if (r1 >= this.stateCount) goto L9;
        if (StateSet.stateSetMatches(r0[r1], r4) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return -1;
    }

    private void loadCornerSizesFromItems(Context r12, XmlPullParser r13, AttributeSet r14, Resources.Theme r15) {
        int r0 = r13.getDepth() + 1;
    L3:
        int r2 = r13.next();
        if (r2 == 1) goto L30;
        int r3 = r13.getDepth();
        if (r3 >= r0) goto L10;
        if (r2 != 3) goto L10;
        return;
    L10:
        if (r2 != 2) goto L3;
        if (r3 > r0) goto L3;
        if (r13.getName().equals(PluginsConstants.Settings.ITEM) == false) goto L3;
        Resources r22 = r12.getResources();
        if (r15 != null) goto L18;
        TypedArray r23 = r22.obtainAttributes(r14, R.styleable.ShapeAppearance);
    L19:
        CornerSize r4 = ShapeAppearanceModel.getCornerSize(r23, R.styleable.ShapeAppearance_cornerSize, new AbsoluteCornerSize(0.0f));
        r23.recycle();
        int r24 = r14.getAttributeCount();
        int[] r5 = new int[r24];
        int r6 = 0;
        int r7 = 0;
    L20:
        if (r6 >= r24) goto L29;
        int r8 = r14.getAttributeNameResource(r6);
        if (r8 == R.attr.cornerSize) goto L28;
        int r9 = r7 + 1;
        if (r14.getAttributeBooleanValue(r6, false) == true) goto L27;
        r8 = -r8;
    L27:
        r5[r7] = r8;
        r7 = r9;
    L28:
        r6 = r6 + 1;
        goto L20
    L29:
        addStateCornerSize(StateSet.trimStateSet(r5, r7), r4);
        goto L3
    L18:
        r23 = r15.obtainStyledAttributes(r14, R.styleable.ShapeAppearance, 0, 0);
        goto L19
    }

    public CornerSize getCornerSizeForState(int[] r1) {
        int r12 = indexOfStateSet(r1);
        if (r12 >= 0) goto L5;
        r12 = indexOfStateSet(StateSet.WILD_CARD);
    L5:
        if (r12 >= 0) goto L9;
        return this.defaultCornerSize;
    L9:
        return this.cornerSizes[r12];
    }

    public CornerSize getDefaultCornerSize() {
        return this.defaultCornerSize;
    }

    public boolean isStateful() {
        if (this.stateCount <= 1) goto L5;
        return true;
    L5:
        return false;
    }

    public StateListCornerSize withTransformedCornerSizes(ShapeAppearanceModel.CornerSizeUnaryOperator r6) {
        StateListCornerSize r0 = new StateListCornerSize();
        r0.stateCount = this.stateCount;
        int[][] r1 = new int[this.stateSpecs.length][];
        r0.stateSpecs = r1;
        int[][] r2 = this.stateSpecs;
        int r4 = 0;
        System.arraycopy(r2, 0, r1, 0, r2.length);
        r0.cornerSizes = new CornerSize[this.cornerSizes.length];
    L4:
        if (r4 >= this.stateCount) goto L6;
        r0.cornerSizes[r4] = r6.apply(this.cornerSizes[r4]);
        r4 = r4 + 1;
        goto L4
    L6:
        return r0;
    }
}
