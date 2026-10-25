package com.google.android.material.carousel;

import androidx.core.math.MathUtils;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.carousel.CarouselStrategy;
import com.google.android.material.carousel.KeylineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class KeylineStateList {
    private static final int NO_INDEX = -1;
    private final KeylineState defaultState;
    private final float endShiftRange;
    private final List<KeylineState> endStateSteps;
    private final float[] endStateStepsInterpolationPoints;
    private final float startShiftRange;
    private final List<KeylineState> startStateSteps;
    private final float[] startStateStepsInterpolationPoints;

    /* renamed from: com.google.android.material.carousel.KeylineStateList$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$material$carousel$CarouselStrategy$StrategyType = null;

        static {
            int[] r0 = new int[CarouselStrategy.StrategyType.values().length];
            $SwitchMap$com$google$android$material$carousel$CarouselStrategy$StrategyType = r0;
            r0[CarouselStrategy.StrategyType.CONTAINED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L5
            return;
        }
    }

    private KeylineStateList(KeylineState r4, List<KeylineState> r5, List<KeylineState> r6) {
        this.defaultState = r4;
        this.startStateSteps = Collections.unmodifiableList(r5);
        this.endStateSteps = Collections.unmodifiableList(r6);
        float r0 = r5.get(r5.size() - 1).getFirstKeyline().loc - r4.getFirstKeyline().loc;
        this.startShiftRange = r0;
        float r42 = r4.getLastKeyline().loc - r6.get(r6.size() - 1).getLastKeyline().loc;
        this.endShiftRange = r42;
        this.startStateStepsInterpolationPoints = getStateStepInterpolationPoints(r0, r5, true);
        this.endStateStepsInterpolationPoints = getStateStepInterpolationPoints(r42, r6, false);
    }

    private KeylineState closestStateStepFromInterpolation(List<KeylineState> r1, float r2, float[] r3) {
        float[] r0 = getStateStepsRange(r1, r2, r3);
        if (r0[0] < 0.5f) goto L7;
        return r1.get((int) r0[2]);
    L7:
        return r1.get((int) r0[1]);
    }

    private static int findFirstIndexAfterLastFocalKeylineWithMask(KeylineState r2, float r3) {
        int r0 = r2.getLastFocalKeylineIndex();
    L4:
        if (r0 >= r2.getKeylines().size()) goto L10;
        if (r3 == r2.getKeylines().get(r0).mask) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L10:
        return r2.getKeylines().size() - 1;
    }

    private static int findFirstNonAnchorKeylineIndex(KeylineState r2) {
        int r0 = 0;
    L4:
        if (r0 >= r2.getKeylines().size()) goto L9;
        if (r2.getKeylines().get(r0).isAnchor == false) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    private static int findLastIndexBeforeFirstFocalKeylineWithMask(KeylineState r2, float r3) {
        int r0 = r2.getFirstFocalKeylineIndex() - 1;
    L3:
        if (r0 < 0) goto L8;
        if (r3 == r2.getKeylines().get(r0).mask) goto L6;
        r0 = r0 - 1;
        goto L3
    L6:
        return r0;
    L8:
        return 0;
    }

    private static int findLastNonAnchorKeylineIndex(KeylineState r2) {
        int r0 = r2.getKeylines().size() - 1;
    L3:
        if (r0 < 0) goto L8;
        if (r2.getKeylines().get(r0).isAnchor == false) goto L6;
        r0 = r0 - 1;
        goto L3
    L6:
        return r0;
    L8:
        return -1;
    }

    public static KeylineStateList from(Carousel r1, KeylineState r2, float r3, float r4, float r5, CarouselStrategy.StrategyType r6) {
        return new KeylineStateList(r2, getStateStepsStart(r1, r2, r3, r4, r6), getStateStepsEnd(r1, r2, r3, r5, r6));
    }

    private static float[] getStateStepInterpolationPoints(float r6, List<KeylineState> r7, boolean r8) {
        int r0 = r7.size();
        float[] r1 = new float[r0];
        int r2 = 1;
    L3:
        if (r2 >= r0) goto L13;
        int r3 = r2 - 1;
        KeylineState r4 = r7.get(r3);
        KeylineState r5 = r7.get(r2);
        if (r8 == false) goto L7;
        float r52 = r5.getFirstKeyline().loc - r4.getFirstKeyline().loc;
    L8:
        float r53 = r52 / r6;
        if (r2 != (r0 - 1)) goto L11;
        float r32 = 1.0f;
    L12:
        r1[r2] = r32;
        r2 = r2 + 1;
        goto L3
    L11:
        r32 = r1[r3] + r53;
        goto L12
    L7:
        r52 = r4.getLastKeyline().loc - r5.getLastKeyline().loc;
        goto L8
    L13:
        return r1;
    }

    private static List<KeylineState> getStateStepsEnd(Carousel r17, KeylineState r18, float r19, float r20, CarouselStrategy.StrategyType r21) {
        ArrayList r6 = new ArrayList();
        r6.add(r18);
        int r8 = findLastNonAnchorKeylineIndex(r18);
        if (r17.isHorizontal() == false) goto L6;
        int r1 = r17.getContainerWidth();
    L5:
        int r11 = r1;
        if (isLastFocalItemVisibleAtRightOfContainer(r17, r18) == true) goto L31;
        if (r8 == (-1)) goto L31;
        int r12 = r8 - r18.getLastFocalKeylineIndex();
        float r3 = r18.getFirstKeyline().locOffset - (r18.getFirstKeyline().maskedItemSize / 2.0f);
        if (r12 <= 0) goto L15;
    L18:
        float r7 = 0.0f;
        int r5 = 0;
    L19:
        if (r5 >= r12) goto L33;
        KeylineState r9 = (KeylineState) r6.get(r6.size() - 1);
        int r10 = r8 - r5;
        float r15 = r7 + r18.getKeylines().get(r10).cutoff;
        int r102 = r10 + 1;
        if (r102 >= r18.getKeylines().size()) goto L23;
        int r72 = findLastIndexBeforeFirstFocalKeylineWithMask(r9, r18.getKeylines().get(r102).mask) + 1;
    L24:
        int r13 = r11;
        int r92 = r72;
        KeylineState r93 = moveKeylineAndCreateKeylineState(r9, r8, r92, r3 - r15, (r18.getFirstFocalKeylineIndex() + r5) + 1, (r18.getLastFocalKeylineIndex() + r5) + 1, r13);
        if (r5 != (r12 - 1)) goto L29;
        if (r20 <= 0.0f) goto L29;
        r93 = shiftKeylineStateForPadding(r93, r20, r13, false, r19, r21);
        r13 = r13;
    L29:
        r6.add(r93);
        r5 = r5 + 1;
        r11 = r13;
        r7 = r15;
        goto L19
    L23:
        r72 = 0;
    L33:
        return r6;
    L15:
        if (r18.getLastFocalKeyline().cutoff <= 0.0f) goto L18;
        r6.add(shiftKeylinesAndCreateKeylineState(r18, (r3 - r18.getLastFocalKeyline().cutoff) - r20, r11));
        return r6;
    L31:
        if (r20 <= 0.0f) goto L33;
        r6.add(shiftKeylineStateForPadding(r18, r20, r11, false, r19, r21));
        goto L33
    L6:
        r1 = r17.getContainerHeight();
        goto L5
    }

    private static float[] getStateStepsRange(List<KeylineState> r7, float r8, float[] r9) {
        int r72 = r7.size();
        float r1 = r9[0];
        int r3 = 1;
    L4:
        if (r3 >= r72) goto L11;
        float r5 = r9[r3];
        if (r8 <= r5) goto L8;
        r3 = r3 + 1;
        r1 = r5;
        goto L4
    L8:
        return new float[]{AnimationUtils.lerp(0.0f, 1.0f, r1, r5, r8), r3 - 1, r3};
    L11:
        return new float[]{0.0f, 0.0f, 0.0f};
    }

    private static List<KeylineState> getStateStepsStart(Carousel r15, KeylineState r16, float r17, float r18, CarouselStrategy.StrategyType r19) {
        ArrayList r6 = new ArrayList();
        r6.add(r16);
        int r8 = findFirstNonAnchorKeylineIndex(r16);
        if (r15.isHorizontal() == false) goto L6;
        int r152 = r15.getContainerWidth();
    L5:
        int r11 = r152;
        if (isFirstFocalItemAtLeftOfContainer(r16) == true) goto L30;
        if (r8 == (-1)) goto L30;
        int r153 = r16.getFirstFocalKeylineIndex() - r8;
        float r2 = r16.getFirstKeyline().locOffset - (r16.getFirstKeyline().maskedItemSize / 2.0f);
        if (r153 <= 0) goto L15;
    L18:
        int r3 = 0;
        float r4 = 0.0f;
    L19:
        if (r3 >= r153) goto L32;
        KeylineState r7 = (KeylineState) r6.get(r6.size() - 1);
        int r5 = r8 + r3;
        int r9 = r16.getKeylines().size() - 1;
        r4 = r4 + r16.getKeylines().get(r5).cutoff;
        int r52 = r5 - 1;
        if (r52 < 0) goto L23;
        r9 = findFirstIndexAfterLastFocalKeylineWithMask(r7, r16.getKeylines().get(r52).mask) - 1;
    L23:
        int r13 = r11;
        KeylineState r92 = moveKeylineAndCreateKeylineState(r7, r8, r9, r2 + r4, (r16.getFirstFocalKeylineIndex() - r3) - 1, (r16.getLastFocalKeylineIndex() - r3) - 1, r13);
        r11 = r13;
        if (r3 != (r153 - 1)) goto L28;
        if (r18 <= 0.0f) goto L28;
        r92 = shiftKeylineStateForPadding(r92, r18, r11, true, r17, r19);
    L28:
        r6.add(r92);
        r3 = r3 + 1;
    L32:
        return r6;
    L15:
        if (r16.getFirstFocalKeyline().cutoff <= 0.0f) goto L18;
        r6.add(shiftKeylinesAndCreateKeylineState(r16, (r2 + r16.getFirstFocalKeyline().cutoff) + r18, r11));
        return r6;
    L30:
        if (r18 <= 0.0f) goto L32;
        r6.add(shiftKeylineStateForPadding(r16, r18, r11, true, r17, r19));
        goto L32
    L6:
        r152 = r15.getContainerHeight();
        goto L5
    }

    private static boolean isFirstFocalItemAtLeftOfContainer(KeylineState r3) {
        if ((r3.getFirstFocalKeyline().locOffset - (r3.getFirstFocalKeyline().maskedItemSize / 2.0f)) >= 0.0f) goto L5;
        return false;
    L5:
        if (r3.getFirstFocalKeyline() != r3.getFirstNonAnchorKeyline()) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isLastFocalItemVisibleAtRightOfContainer(Carousel r3, KeylineState r4) {
        int r0 = r3.getContainerHeight();
        if (r3.isHorizontal() == false) goto L6;
        r0 = r3.getContainerWidth();
    L6:
        if ((r4.getLastFocalKeyline().locOffset + (r4.getLastFocalKeyline().maskedItemSize / 2.0f)) <= r0) goto L8;
        return false;
    L8:
        if (r4.getLastFocalKeyline() != r4.getLastNonAnchorKeyline()) goto L13;
        return true;
    L13:
        return false;
    }

    private static KeylineState lerp(List<KeylineState> r1, float r2, float[] r3) {
        float[] r22 = getStateStepsRange(r1, r2, r3);
        return KeylineState.lerp(r1.get((int) r22[1]), r1.get((int) r22[2]), r22[0]);
    }

    private static KeylineState moveKeylineAndCreateKeylineState(KeylineState r8, int r9, int r10, float r11, int r12, int r13, int r14) {
        ArrayList r0 = new ArrayList(r8.getKeylines());
        r0.add(r10, (KeylineState.Keyline) r0.remove(r9));
        KeylineState.Builder r1 = new KeylineState.Builder(r8.getItemSize(), r14);
        int r92 = 0;
    L4:
        if (r92 >= r0.size()) goto L12;
        KeylineState.Keyline r102 = (KeylineState.Keyline) r0.get(r92);
        float r4 = r102.maskedItemSize;
        float r2 = r11 + (r4 / 2.0f);
        if (r92 < r12) goto L9;
        if (r92 > r13) goto L9;
        boolean r5 = true;
    L10:
        r1.addKeyline(r2, r102.mask, r4, r5, r102.isAnchor, r102.cutoff);
        r11 = r11 + r102.maskedItemSize;
        r92 = r92 + 1;
    L9:
        r5 = false;
        goto L10
    L12:
        return r1.build();
    }

    private static KeylineState shiftKeylineStateForPadding(KeylineState r1, float r2, int r3, boolean r4, float r5, CarouselStrategy.StrategyType r6) {
        if (AnonymousClass1.$SwitchMap$com$google$android$material$carousel$CarouselStrategy$StrategyType[r6.ordinal()] == 1) goto L7;
        return shiftKeylineStateForPaddingUncontained(r1, r2, r3, r4);
    L7:
        return shiftKeylineStateForPaddingContained(r1, r2, r3, r4, r5);
    }

    private static KeylineState shiftKeylineStateForPaddingContained(KeylineState r16, float r17, int r18, boolean r19, float r20) {
        ArrayList r0 = new ArrayList(r16.getKeylines());
        KeylineState.Builder r2 = new KeylineState.Builder(r16.getItemSize(), r18);
        float r1 = r17 / r16.getNumberOfNonAnchorKeylines();
        if (r19 == false) goto L5;
        float r3 = r17;
    L6:
        float r13 = r3;
        int r14 = 0;
    L8:
        if (r14 >= r0.size()) goto L29;
        KeylineState.Keyline r32 = (KeylineState.Keyline) r0.get(r14);
        if (r32.isAnchor == false) goto L13;
        r2.addKeyline(r32.locOffset, r32.mask, r32.maskedItemSize, false, true, r32.cutoff);
    L27:
        r14 = r14 + 1;
        goto L8
    L13:
        if (r14 >= r16.getFirstFocalKeylineIndex()) goto L15;
    L17:
        boolean r6 = false;
    L18:
        float r5 = r32.maskedItemSize - r1;
        float r4 = CarouselStrategy.getChildMaskPercentage(r5, r16.getItemSize(), r20);
        float r7 = (r5 / 2.0f) + r13;
        float r8 = Math.abs(r7 - r32.locOffset);
        float r33 = r32.cutoff;
        if (r19 == false) goto L21;
        float r9 = r8;
    L22:
        if (r19 == false) goto L25;
        float r10 = 0.0f;
    L26:
        r2.addKeyline(r7, r4, r5, r6, false, r33, r9, r10);
        r13 = r13 + r5;
        goto L27
    L25:
        r10 = r8;
        goto L26
    L21:
        r9 = 0.0f;
        goto L22
    L15:
        if (r14 > r16.getLastFocalKeylineIndex()) goto L17;
        r6 = true;
        goto L18
    L29:
        return r2.build();
    L5:
        r3 = 0.0f;
        goto L6
    }

    private static KeylineState shiftKeylineStateForPaddingUncontained(KeylineState r16, float r17, int r18, boolean r19) {
        ArrayList r1 = new ArrayList(r16.getKeylines());
        KeylineState.Builder r3 = new KeylineState.Builder(r16.getItemSize(), r18);
        boolean r2 = true;
        if (r19 == false) goto L5;
        int r13 = 0;
    L6:
        int r14 = 0;
    L8:
        if (r14 >= r1.size()) goto L38;
        KeylineState.Keyline r4 = (KeylineState.Keyline) r1.get(r14);
        if (r4.isAnchor == false) goto L13;
        if (r14 != r13) goto L13;
        r3.addKeyline(r4.locOffset, r4.mask, r4.maskedItemSize, false, true, r4.cutoff);
    L36:
        r14 = r14 + 1;
        r2 = true;
    L13:
        float r5 = r4.locOffset;
        if (r19 == false) goto L16;
        float r52 = r5 + r17;
    L18:
        if (r19 == false) goto L20;
        float r10 = r17;
    L21:
        if (r19 == false) goto L23;
        float r11 = 0.0f;
    L25:
        if (r14 >= r16.getFirstFocalKeylineIndex()) goto L27;
    L30:
        boolean r7 = false;
    L29:
        float r8 = r52;
        float r53 = r4.mask;
        float r9 = r4.maskedItemSize;
        boolean r42 = r4.isAnchor;
        if (r19 == false) goto L34;
        float r22 = Math.max(0.0f, ((r9 / 2.0f) + r8) - r18);
    L35:
        r3.addKeyline(r8, r53, r9, r7, r42, Math.abs(r22), r10, r11);
        goto L36
    L34:
        r22 = Math.min(0.0f, r8 - (r9 / 2.0f));
        goto L35
    L27:
        if (r14 > r16.getLastFocalKeylineIndex()) goto L30;
        r7 = r2;
        goto L29
    L23:
        r11 = r17;
        goto L25
    L20:
        r10 = 0.0f;
        goto L21
    L16:
        r52 = r5 - r17;
        goto L18
    L38:
        return r3.build();
    L5:
        r13 = r1.size() - 1;
        goto L6
    }

    private static KeylineState shiftKeylinesAndCreateKeylineState(KeylineState r7, float r8, int r9) {
        return moveKeylineAndCreateKeylineState(r7, 0, 0, r8, r7.getFirstFocalKeylineIndex(), r7.getLastFocalKeylineIndex(), r9);
    }

    public KeylineState getDefaultState() {
        return this.defaultState;
    }

    public KeylineState getEndState() {
        return this.endStateSteps.get(r1.size() - 1);
    }

    public Map<Integer, KeylineState> getKeylineStateForPositionMap(int r11, int r12, int r13, boolean r14) {
        float r0 = this.defaultState.getItemSize();
        HashMap r1 = new HashMap();
        int r3 = 0;
        int r4 = 0;
    L3:
        int r5 = -1;
        if (r3 >= r11) goto L18;
        if (r14 == false) goto L7;
        int r7 = (r11 - r3) - 1;
    L8:
        float r8 = r7 * r0;
        if (r14 == true) goto L13;
        r5 = 1;
    L13:
        if ((r8 * r5) <= (r13 - this.endShiftRange)) goto L15;
    L16:
        Integer r52 = Integer.valueOf(r7);
        List<KeylineState> r72 = this.endStateSteps;
        r1.put(r52, r72.get(MathUtils.clamp(r4, 0, r72.size() - 1)));
        r4 = r4 + 1;
    L17:
        r3 = r3 + 1;
        goto L3
    L15:
        if (r3 < (r11 - this.endStateSteps.size())) goto L17;
    L7:
        r7 = r3;
        goto L8
    L18:
        int r132 = r11 - 1;
        int r32 = 0;
    L19:
        if (r132 < 0) goto L33;
        if (r14 == false) goto L22;
        int r42 = (r11 - r132) - 1;
    L23:
        float r73 = r42 * r0;
        if (r14 == false) goto L26;
        int r82 = -1;
    L28:
        if ((r73 * r82) >= (r12 + this.startShiftRange)) goto L30;
    L31:
        Integer r43 = Integer.valueOf(r42);
        List<KeylineState> r74 = this.startStateSteps;
        r1.put(r43, r74.get(MathUtils.clamp(r32, 0, r74.size() - 1)));
        r32 = r32 + 1;
    L32:
        r132 = r132 - 1;
        goto L19
    L30:
        if (r132 >= this.startStateSteps.size()) goto L32;
    L26:
        r82 = 1;
        goto L28
    L22:
        r42 = r132;
        goto L23
    L33:
        return r1;
    }

    public KeylineState getShiftedState(float r2, float r3, float r4) {
        return getShiftedState(r2, r3, r4, false);
    }

    public KeylineState getShiftedState(float r6, float r7, float r8, boolean r9) {
        float r0 = this.startShiftRange + r7;
        float r1 = r8 - this.endShiftRange;
        float r2 = getStartState().getFirstFocalKeyline().leftOrTopPaddingShift;
        float r3 = getEndState().getFirstFocalKeyline().rightOrBottomPaddingShift;
        if (this.startShiftRange != r2) goto L6;
        r0 = r0 + r2;
    L6:
        if (this.endShiftRange != r3) goto L9;
        r1 = r1 - r3;
    L9:
        if (r6 >= r0) goto L12;
        float r62 = AnimationUtils.lerp(1.0f, 0.0f, r7, r0, r6);
        List<KeylineState> r72 = this.startStateSteps;
        float[] r82 = this.startStateStepsInterpolationPoints;
    L14:
        if (r9 == false) goto L18;
        return closestStateStepFromInterpolation(r72, r62, r82);
    L18:
        return lerp(r72, r62, r82);
    L12:
        if (r6 <= r1) goto L20;
        r62 = AnimationUtils.lerp(0.0f, 1.0f, r1, r8, r6);
        r72 = this.endStateSteps;
        r82 = this.endStateStepsInterpolationPoints;
        goto L14
    L20:
        return this.defaultState;
    }

    public KeylineState getStartState() {
        return this.startStateSteps.get(r1.size() - 1);
    }
}
