package com.google.android.material.carousel;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class KeylineState {
    private final int carouselSize;
    private final int firstFocalKeylineIndex;
    private final float itemSize;
    private final List<Keyline> keylines;
    private final int lastFocalKeylineIndex;
    private int totalVisibleFocalItems;

    /* renamed from: com.google.android.material.carousel.KeylineState$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private static final int NO_INDEX = -1;
        private static final float UNKNOWN_LOC = Float.MIN_VALUE;
        private final int carouselSize;
        private int firstFocalKeylineIndex;
        private final float itemSize;
        private int lastFocalKeylineIndex;
        private float lastKeylineMaskedSize;
        private int latestAnchorKeylineIndex;
        private Keyline tmpFirstFocalKeyline;
        private final List<Keyline> tmpKeylines;
        private Keyline tmpLastFocalKeyline;

        public Builder(float r3, int r4) {
            this.tmpKeylines = new ArrayList();
            this.firstFocalKeylineIndex = -1;
            this.lastFocalKeylineIndex = -1;
            this.lastKeylineMaskedSize = 0.0f;
            this.latestAnchorKeylineIndex = -1;
            this.itemSize = r3;
            this.carouselSize = r4;
        }

        private static float calculateKeylineLocationForItemPosition(float r0, float r1, int r2, int r3) {
            return (r0 - (r2 * r1)) + (r3 * r1);
        }

        public Builder addAnchorKeyline(float r7, float r8, float r9) {
            return addKeyline(r7, r8, r9, false, true);
        }

        public Builder addKeyline(float r2, float r3, float r4) {
            return addKeyline(r2, r3, r4, false);
        }

        public Builder addKeyline(float r7, float r8, float r9, boolean r10) {
            return addKeyline(r7, r8, r9, r10, false);
        }

        public Builder addKeyline(float r9, float r10, float r11, boolean r12, boolean r13) {
            float r0 = r11 / 2.0f;
            float r1 = r9 - r0;
            float r02 = r0 + r9;
            int r2 = this.carouselSize;
            if (r02 <= r2) goto L6;
            float r03 = Math.abs(r02 - Math.max(r02 - r11, r2));
        L10:
            return addKeyline(r9, r10, r11, r12, r13, r03);
        L6:
            r03 = 0.0f;
            if (r1 >= 0.0f) goto L10;
            r03 = Math.abs(r1 - Math.min(r1 + r11, 0.0f));
            goto L10
        }

        public Builder addKeyline(float r10, float r11, float r12, boolean r13, boolean r14, float r15) {
            return addKeyline(r10, r11, r12, r13, r14, r15, 0.0f, 0.0f);
        }

        public Builder addKeyline(float r11, float r12, float r13, boolean r14, boolean r15, float r16, float r17, float r18) {
            if (r13 > 0.0f) goto L6;
            return this;
        L6:
            if (r15 == false) goto L17;
            if (r14 == true) goto L15;
            int r1 = this.latestAnchorKeylineIndex;
            if (r1 == (-1)) goto L14;
            if (r1 == 0) goto L14;
            f$$ExternalSyntheticBUOutline1.m("Anchor keylines must be either the first or last keyline.");
            return null;
        L14:
            this.latestAnchorKeylineIndex = this.tmpKeylines.size();
            goto L17
        L15:
            f$$ExternalSyntheticBUOutline1.m("Anchor keylines cannot be focal.");
            return null;
        L17:
            Keyline r19 = new Keyline(Float.MIN_VALUE, r11, r12, r13, r15, r16, r17, r18);
            Keyline r112 = this.tmpFirstFocalKeyline;
            if (r14 == false) goto L33;
            if (r112 != null) goto L22;
            this.tmpFirstFocalKeyline = r19;
            this.firstFocalKeylineIndex = this.tmpKeylines.size();
        L22:
            if (this.lastFocalKeylineIndex == (-1)) goto L29;
            if ((this.tmpKeylines.size() - this.lastFocalKeylineIndex) <= 1) goto L29;
            f$$ExternalSyntheticBUOutline1.m("Keylines marked as focal must be placed next to each other. There cannot be non-focal keylines between focal keylines.");
            return null;
        L29:
            if (r13 != this.tmpFirstFocalKeyline.maskedItemSize) goto L31;
            this.tmpLastFocalKeyline = r19;
            this.lastFocalKeylineIndex = this.tmpKeylines.size();
        L46:
            this.lastKeylineMaskedSize = r19.maskedItemSize;
            this.tmpKeylines.add(r19);
            return this;
        L31:
            f$$ExternalSyntheticBUOutline1.m("Keylines that are marked as focal must all have the same masked item size.");
            return null;
        L33:
            if (r112 != null) goto L40;
            if (r19.maskedItemSize >= this.lastKeylineMaskedSize) goto L40;
            f$$ExternalSyntheticBUOutline1.m("Keylines before the first focal keyline must be ordered by incrementing masked item size.");
            return null;
        L40:
            if (this.tmpLastFocalKeyline == null) goto L46;
            if (r19.maskedItemSize <= this.lastKeylineMaskedSize) goto L46;
            f$$ExternalSyntheticBUOutline1.m("Keylines after the last focal keyline must be ordered by decreasing masked item size.");
            return null;
        }

        public Builder addKeylineRange(float r7, float r8, float r9, int r10) {
            return addKeylineRange(r7, r8, r9, r10, false);
        }

        public Builder addKeylineRange(float r3, float r4, float r5, int r6, boolean r7) {
            if (r6 > 0) goto L4;
        L9:
            return this;
        L4:
            if (r5 <= 0.0f) goto L9;
            int r0 = 0;
        L7:
            if (r0 >= r6) goto L9;
            addKeyline((r0 * r5) + r3, r4, r5, r7);
            r0 = r0 + 1;
            goto L7
        }

        public KeylineState build() {
            if (this.tmpFirstFocalKeyline == null) goto L10;
            ArrayList r3 = new ArrayList();
            int r0 = 0;
        L6:
            if (r0 >= this.tmpKeylines.size()) goto L9;
            Keyline r1 = this.tmpKeylines.get(r0);
            r3.add(new Keyline(calculateKeylineLocationForItemPosition(this.tmpFirstFocalKeyline.locOffset, this.itemSize, this.firstFocalKeylineIndex, r0), r1.locOffset, r1.mask, r1.maskedItemSize, r1.isAnchor, r1.cutoff, r1.leftOrTopPaddingShift, r1.rightOrBottomPaddingShift));
            r0 = r0 + 1;
            goto L6
        L9:
            return new KeylineState(this.itemSize, r3, this.firstFocalKeylineIndex, this.lastFocalKeylineIndex, this.carouselSize, null);
        L10:
            Segment$$ExternalSyntheticBUOutline1.m("There must be a keyline marked as focal.");
            return null;
        }
    }

    public static final class Keyline {
        final float cutoff;
        final boolean isAnchor;
        final float leftOrTopPaddingShift;
        final float loc;
        final float locOffset;
        final float mask;
        final float maskedItemSize;
        final float rightOrBottomPaddingShift;

        public Keyline(float r10, float r11, float r12, float r13) {
            this(r10, r11, r12, r13, false, 0.0f, 0.0f, 0.0f);
        }

        public Keyline(float r1, float r2, float r3, float r4, boolean r5, float r6, float r7, float r8) {
            this.loc = r1;
            this.locOffset = r2;
            this.mask = r3;
            this.maskedItemSize = r4;
            this.isAnchor = r5;
            this.cutoff = r6;
            this.leftOrTopPaddingShift = r7;
            this.rightOrBottomPaddingShift = r8;
        }

        public static Keyline lerp(Keyline r5, Keyline r6, float r7) {
            return new Keyline(AnimationUtils.lerp(r5.loc, r6.loc, r7), AnimationUtils.lerp(r5.locOffset, r6.locOffset, r7), AnimationUtils.lerp(r5.mask, r6.mask, r7), AnimationUtils.lerp(r5.maskedItemSize, r6.maskedItemSize, r7));
        }
    }

    private KeylineState(float r2, List<Keyline> r3, int r4, int r5, int r6) {
        this.itemSize = r2;
        this.keylines = Collections.unmodifiableList(r3);
        this.firstFocalKeylineIndex = r4;
        this.lastFocalKeylineIndex = r5;
    L3:
        if (r4 > r5) goto L8;
        if (r3.get(r4).cutoff != 0.0f) goto L7;
        this.totalVisibleFocalItems++;
    L7:
        r4 = r4 + 1;
        goto L3
    L8:
        this.carouselSize = r6;
    }

    public /* synthetic */ KeylineState(float r1, List r2, int r3, int r4, int r5, AnonymousClass1 r6) {
        this(r1, r2, r3, r4, r5);
    }

    public static KeylineState lerp(KeylineState r11, KeylineState r12, float r13) {
        if (r11.getItemSize() != r12.getItemSize()) goto L14;
        List<Keyline> r0 = r11.getKeylines();
        List<Keyline> r2 = r12.getKeylines();
        if (r0.size() != r2.size()) goto L12;
        ArrayList r7 = new ArrayList();
        int r1 = 0;
    L8:
        if (r1 >= r11.getKeylines().size()) goto L10;
        r7.add(Keyline.lerp(r0.get(r1), r2.get(r1), r13));
        r1 = r1 + 1;
        goto L8
    L10:
        int r8 = AnimationUtils.lerp(r11.getFirstFocalKeylineIndex(), r12.getFirstFocalKeylineIndex(), r13);
        int r9 = AnimationUtils.lerp(r11.getLastFocalKeylineIndex(), r12.getLastFocalKeylineIndex(), r13);
        return new KeylineState(r11.getItemSize(), r7, r8, r9, r11.carouselSize);
    L12:
        f$$ExternalSyntheticBUOutline1.m("Keylines being linearly interpolated must have the same number of keylines.");
        return null;
    L14:
        f$$ExternalSyntheticBUOutline1.m("Keylines being linearly interpolated must have the same item size.");
        return null;
    }

    public static KeylineState reverse(KeylineState r10, int r11) {
        Builder r0 = new Builder(r10.getItemSize(), r11);
        float r112 = (r11 - r10.getLastKeyline().locOffset) - (r10.getLastKeyline().maskedItemSize / 2.0f);
        int r8 = r10.getKeylines().size() - 1;
    L3:
        if (r8 < 0) goto L12;
        Keyline r9 = r10.getKeylines().get(r8);
        float r1 = (r9.maskedItemSize / 2.0f) + r112;
        if (r8 >= r10.getFirstFocalKeylineIndex()) goto L7;
    L9:
        boolean r4 = false;
    L10:
        r0.addKeyline(r1, r9.mask, r9.maskedItemSize, r4, r9.isAnchor);
        r112 = r112 + r9.maskedItemSize;
        r8 = r8 - 1;
        goto L3
    L7:
        if (r8 > r10.getLastFocalKeylineIndex()) goto L9;
        r4 = true;
        goto L10
    L12:
        return r0.build();
    }

    public int getCarouselSize() {
        return this.carouselSize;
    }

    public Keyline getFirstFocalKeyline() {
        return this.keylines.get(this.firstFocalKeylineIndex);
    }

    public int getFirstFocalKeylineIndex() {
        return this.firstFocalKeylineIndex;
    }

    public Keyline getFirstKeyline() {
        return this.keylines.get(0);
    }

    public Keyline getFirstNonAnchorKeyline() {
        int r0 = 0;
    L4:
        if (r0 >= this.keylines.size()) goto L9;
        Keyline r1 = this.keylines.get(r0);
        if (r1.isAnchor == false) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return null;
    }

    public List<Keyline> getFocalKeylines() {
        return this.keylines.subList(this.firstFocalKeylineIndex, this.lastFocalKeylineIndex + 1);
    }

    public float getItemSize() {
        return this.itemSize;
    }

    public List<Keyline> getKeylines() {
        return this.keylines;
    }

    public Keyline getLastFocalKeyline() {
        return this.keylines.get(this.lastFocalKeylineIndex);
    }

    public int getLastFocalKeylineIndex() {
        return this.lastFocalKeylineIndex;
    }

    public Keyline getLastKeyline() {
        return this.keylines.get(r1.size() - 1);
    }

    public Keyline getLastNonAnchorKeyline() {
        int r0 = this.keylines.size() - 1;
    L3:
        if (r0 < 0) goto L8;
        Keyline r1 = this.keylines.get(r0);
        if (r1.isAnchor == false) goto L6;
        r0 = r0 - 1;
        goto L3
    L6:
        return r1;
    L8:
        return null;
    }

    public int getNumberOfNonAnchorKeylines() {
        Iterator<Keyline> r0 = this.keylines.iterator();
        int r1 = 0;
    L4:
        if (r0.hasNext() == false) goto L9;
        if (r0.next().isAnchor == false) goto L4;
        r1 = r1 + 1;
        goto L4
    L9:
        return this.keylines.size() - r1;
    }

    public int getTotalVisibleFocalItems() {
        return this.totalVisibleFocalItems;
    }
}
