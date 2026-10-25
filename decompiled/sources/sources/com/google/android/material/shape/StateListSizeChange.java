package com.google.android.material.shape;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.TypedValue;
import android.util.Xml;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.material.R;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class StateListSizeChange {
    private static final int INITIAL_CAPACITY = 10;
    private SizeChange defaultSizeChange;
    SizeChange[] sizeChanges;
    int stateCount;
    int[][] stateSpecs;

    public static class SizeChange {
        public SizeChangeAmount widthChange;

        public SizeChange(SizeChange r3) {
            SizeChangeAmount r32 = r3.widthChange;
            this.widthChange = new SizeChangeAmount(r32.type, r32.amount);
        }

        public SizeChange(SizeChangeAmount r1) {
            this.widthChange = r1;
        }
    }

    public static class SizeChangeAmount {
        float amount;
        SizeChangeType type;

        public SizeChangeAmount(SizeChangeType r1, float r2) {
            this.type = r1;
            this.amount = r2;
        }

        public int getChange(int r3) {
            SizeChangeType r0 = this.type;
            if (r0 != SizeChangeType.PERCENT) goto L8;
            float r2 = this.amount * r3;
        L6:
            return (int) r2;
        L8:
            if (r0 != SizeChangeType.PIXELS) goto L10;
            r2 = this.amount;
            goto L6
        L10:
            return 0;
        }
    }

    public enum SizeChangeType extends Enum<SizeChangeType> {
        private static final /* synthetic */ SizeChangeType[] $VALUES = null;
        public static final SizeChangeType PERCENT = null;
        public static final SizeChangeType PIXELS = null;

        private static /* synthetic */ SizeChangeType[] $values() {
            return new SizeChangeType[]{PERCENT, PIXELS};
        }

        static {
            PERCENT = new SizeChangeType("PERCENT", 0);
            PIXELS = new SizeChangeType("PIXELS", 1);
            $VALUES = $values();
        }

        SizeChangeType(String r1, int r2) {
        }

        public static SizeChangeType valueOf(String r1) {
            return (SizeChangeType) Enum.valueOf(SizeChangeType.class, r1);
        }

        public static SizeChangeType[] values() {
            return (SizeChangeType[]) $VALUES.clone();
        }
    }

    public StateListSizeChange() {
        this.stateSpecs = new int[10][];
        this.sizeChanges = new SizeChange[10];
    }

    private void addStateSizeChange(int[] r3, SizeChange r4) {
        int r0 = this.stateCount;
        if (r0 != 0) goto L5;
    L6:
        this.defaultSizeChange = r4;
    L8:
        if (r0 < this.stateSpecs.length) goto L10;
        growArray(r0, r0 + 10);
    L10:
        int[][] r02 = this.stateSpecs;
        int r1 = this.stateCount;
        r02[r1] = r3;
        this.sizeChanges[r1] = r4;
        this.stateCount = r1 + 1;
        return;
    L5:
        if (r3.length != 0) goto L8;
        goto L6
    }

    public static StateListSizeChange create(Context r5, TypedArray r6, int r7) {
        int r62 = r6.getResourceId(r7, 0);
        if (r62 != 0) goto L6;
        return null;
    L6:
        if (r5.getResources().getResourceTypeName(r62).equals("xml") == true) goto L35;
        return null;
    L35:
        XmlResourceParser r63 = r5.getResources().getXml(r62);     // Catch: Throwable -> L32
        StateListSizeChange r0 = new StateListSizeChange();     // Catch: Throwable -> L20
        AttributeSet r1 = Xml.asAttributeSet(r63);     // Catch: Throwable -> L20
    L10:
        int r2 = r63.next();     // Catch: Throwable -> L20
        if (r2 == 2) goto L15;
        if (r2 != 1) goto L10;
    L15:
        if (r2 != 2) goto L25;
        if (r63.getName().equals(PluginsConstants.Settings.TYPE_SELECTOR) == false) goto L22;
        r0.loadSizeChangeFromItems(r5, r63, r1, r5.getTheme());     // Catch: Throwable -> L20
    L22:
        r63.close();     // Catch: Throwable -> L32 Throwable -> L32 Throwable -> L32
        return r0;
    L25:
        throw new XmlPullParserException("No start tag found");     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        if (r63 != null) goto L33;
    L31:
        throw th;     // Catch: Throwable -> L32 Throwable -> L32 Throwable -> L32
    L33:
        r63.close();     // Catch: Throwable -> L29
    L29:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L32 Throwable -> L32 Throwable -> L32
    L32:
        return null;
    }

    private SizeChangeAmount getSizeChangeAmount(TypedArray r2, int r3, SizeChangeAmount r4) {
        TypedValue r1 = r2.peekValue(r3);
        if (r1 == null) goto L13;
        int r32 = r1.type;
        if (r32 != 5) goto L10;
        return new SizeChangeAmount(SizeChangeType.PIXELS, TypedValue.complexToDimensionPixelSize(r1.data, r2.getResources().getDisplayMetrics()));
    L10:
        if (r32 != 6) goto L13;
        return new SizeChangeAmount(SizeChangeType.PERCENT, r1.getFraction(1.0f, 1.0f));
    L13:
        return r4;
    }

    private void growArray(int r4, int r5) {
        int[][] r0 = new int[r5][];
        System.arraycopy(this.stateSpecs, 0, r0, 0, r4);
        this.stateSpecs = r0;
        SizeChange[] r52 = new SizeChange[r5];
        System.arraycopy(this.sizeChanges, 0, r52, 0, r4);
        this.sizeChanges = r52;
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

    private void loadSizeChangeFromItems(Context r12, XmlPullParser r13, AttributeSet r14, Resources.Theme r15) {
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
        TypedArray r23 = r22.obtainAttributes(r14, R.styleable.StateListSizeChange);
    L19:
        SizeChangeAmount r4 = getSizeChangeAmount(r23, R.styleable.StateListSizeChange_widthChange, null);
        r23.recycle();
        int r24 = r14.getAttributeCount();
        int[] r5 = new int[r24];
        int r6 = 0;
        int r7 = 0;
    L20:
        if (r6 >= r24) goto L29;
        int r8 = r14.getAttributeNameResource(r6);
        if (r8 == R.attr.widthChange) goto L28;
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
        addStateSizeChange(StateSet.trimStateSet(r5, r7), new SizeChange(r4));
        goto L3
    L18:
        r23 = r15.obtainStyledAttributes(r14, R.styleable.StateListSizeChange, 0, 0);
        goto L19
    }

    public SizeChange getDefaultSizeChange() {
        return this.defaultSizeChange;
    }

    public int getMaxWidthChange(int r6) {
        int r0 = -r6;
        int r1 = 0;
    L4:
        if (r1 >= this.stateCount) goto L13;
        SizeChangeAmount r2 = this.sizeChanges[r1].widthChange;
        SizeChangeType r3 = r2.type;
        if (r3 != SizeChangeType.PIXELS) goto L10;
        float r02 = Math.max(r0, r2.amount);
    L8:
        r0 = (int) r02;
    L12:
        r1 = r1 + 1;
        goto L4
    L10:
        if (r3 != SizeChangeType.PERCENT) goto L12;
        r02 = Math.max(r0, r6 * r2.amount);
        goto L8
    L13:
        return r0;
    }

    public SizeChange getSizeChangeForState(int[] r1) {
        int r12 = indexOfStateSet(r1);
        if (r12 >= 0) goto L5;
        r12 = indexOfStateSet(StateSet.WILD_CARD);
    L5:
        if (r12 >= 0) goto L9;
        return this.defaultSizeChange;
    L9:
        return this.sizeChanges[r12];
    }

    public boolean isStateful() {
        if (this.stateCount <= 1) goto L5;
        return true;
    L5:
        return false;
    }
}
