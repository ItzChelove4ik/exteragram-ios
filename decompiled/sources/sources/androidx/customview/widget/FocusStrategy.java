package androidx.customview.widget;

import android.graphics.Rect;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes3.dex */
abstract class FocusStrategy {

    /* loaded from: classes.dex */
    public interface BoundsAdapter<T> {
        void obtainBounds(T r1, Rect r2);
    }

    /* loaded from: classes.dex */
    public interface CollectionAdapter<T, V> {
        V get(T r1, int r2);

        int size(T r1);
    }

    public static class SequentialComparator<T> implements Comparator<T> {
        private final BoundsAdapter<T> mAdapter;
        private final boolean mIsLayoutRtl;
        private final Rect mTemp1;
        private final Rect mTemp2;

        public SequentialComparator(boolean r2, BoundsAdapter<T> r3) {
            this.mTemp1 = new Rect();
            this.mTemp2 = new Rect();
            this.mIsLayoutRtl = r2;
            this.mAdapter = r3;
        }

        @Override // java.util.Comparator
        public int compare(T r5, T r6) {
            Rect r0 = this.mTemp1;
            Rect r1 = this.mTemp2;
            this.mAdapter.obtainBounds(r5, r0);
            this.mAdapter.obtainBounds(r6, r1);
            int r52 = r0.top;
            int r62 = r1.top;
            if (r52 >= r62) goto L6;
            return -1;
        L6:
            if (r52 <= r62) goto L8;
            return 1;
        L8:
            int r53 = r0.left;
            int r63 = r1.left;
            if (r53 < r63) goto L11;
            if (r53 > r63) goto L16;
            int r54 = r0.bottom;
            int r64 = r1.bottom;
            if (r54 >= r64) goto L22;
            return -1;
        L22:
            if (r54 <= r64) goto L24;
            return 1;
        L24:
            int r55 = r0.right;
            int r65 = r1.right;
            if (r55 < r65) goto L27;
            if (r55 > r65) goto L32;
            return 0;
        L32:
            if (this.mIsLayoutRtl == false) goto L34;
            return -1;
        L34:
            return 1;
        L27:
            if (this.mIsLayoutRtl == false) goto L29;
            return 1;
        L29:
            return -1;
        L16:
            if (this.mIsLayoutRtl == false) goto L18;
            return -1;
        L18:
            return 1;
        L11:
            if (this.mIsLayoutRtl == false) goto L13;
            return 1;
        L13:
            return -1;
        }
    }

    private static boolean beamBeats(int r3, Rect r4, Rect r5, Rect r6) {
        boolean r0 = beamsOverlap(r3, r4, r5);
        if (beamsOverlap(r3, r4, r6) == true) goto L19;
        if (r0 == false) goto L19;
        if (isToDirectionOf(r3, r4, r6) == true) goto L10;
        return true;
    L10:
        if (r3 != 17) goto L12;
    L18:
        return true;
    L12:
        if (r3 == 66) goto L18;
        if (majorAxisDistance(r3, r4, r5) >= majorAxisDistanceToFarEdge(r3, r4, r6)) goto L17;
        return true;
    L17:
        return false;
    L19:
        return false;
    }

    private static boolean beamsOverlap(int r3, Rect r4, Rect r5) {
        if (r3 == 17) goto L20;
        if (r3 == 33) goto L14;
        if (r3 == 66) goto L20;
        if (r3 == 130) goto L14;
        f$$ExternalSyntheticBUOutline1.m("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        return false;
    L14:
        if (r5.right >= r4.left) goto L16;
    L18:
        return false;
    L16:
        if (r5.left > r4.right) goto L18;
        return true;
    L20:
        if (r5.bottom >= r4.top) goto L22;
    L24:
        return false;
    L22:
        if (r5.top > r4.bottom) goto L24;
        return true;
    }

    public static <L, T> T findNextFocusInAbsoluteDirection(L r7, CollectionAdapter<L, T> r8, BoundsAdapter<T> r9, T r10, Rect r11, int r12) {
        Rect r0 = new Rect(r11);
        T r2 = null;
        int r3 = 0;
        if (r12 != 17) goto L5;
        r0.offset(r11.width() + 1, 0);
    L16:
        int r1 = r8.size(r7);
        Rect r4 = new Rect();
    L17:
        if (r3 >= r1) goto L25;
        T r5 = r8.get(r7, r3);
        if (r5 == r10) goto L24;
        r9.obtainBounds(r5, r4);
        if (isBetterCandidate(r12, r11, r4, r0) == false) goto L24;
        r0.set(r4);
        r2 = r5;
    L24:
        r3 = r3 + 1;
        goto L17
    L25:
        return r2;
    L5:
        if (r12 != 33) goto L7;
        r0.offset(0, r11.height() + 1);
        goto L16
    L7:
        if (r12 != 66) goto L9;
        r0.offset(-(r11.width() + 1), 0);
        goto L16
    L9:
        if (r12 != 130) goto L11;
        r0.offset(0, -(r11.height() + 1));
        goto L16
    L11:
        f$$ExternalSyntheticBUOutline1.m("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        return null;
    }

    public static <L, T> T findNextFocusInRelativeDirection(L r4, CollectionAdapter<L, T> r5, BoundsAdapter<T> r6, T r7, int r8, boolean r9, boolean r10) {
        int r0 = r5.size(r4);
        ArrayList r1 = new ArrayList(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1.add(r5.get(r4, r2));
        r2 = r2 + 1;
        goto L3
    L5:
        Collections.sort(r1, new SequentialComparator(r9, r6));
        if (r8 == 1) goto L14;
        if (r8 == 2) goto L10;
        f$$ExternalSyntheticBUOutline1.m("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
        return null;
    L10:
        return (T) getNextFocusable(r7, r1, r10);
    L14:
        return (T) getPreviousFocusable(r7, r1, r10);
    }

    private static <T> T getNextFocusable(T r1, ArrayList<T> r2, boolean r3) {
        int r0 = r2.size();
        if (r1 != null) goto L5;
        int r12 = -1;
    L6:
        int r13 = r12 + 1;
        if (r13 < r0) goto L9;
        if (r3 == false) goto L14;
        if (r0 > 0) goto L13;
        return null;
    L13:
        return r2.get(0);
    L14:
        return null;
    L9:
        return r2.get(r13);
    L5:
        r12 = r2.lastIndexOf(r1);
        goto L6
    }

    private static <T> T getPreviousFocusable(T r1, ArrayList<T> r2, boolean r3) {
        int r0 = r2.size();
        if (r1 != null) goto L5;
        int r12 = r0;
    L6:
        int r13 = r12 - 1;
        if (r13 >= 0) goto L9;
        if (r3 == false) goto L14;
        if (r0 > 0) goto L13;
        return null;
    L13:
        return r2.get(r0 - 1);
    L14:
        return null;
    L9:
        return r2.get(r13);
    L5:
        r12 = r2.indexOf(r1);
        goto L6
    }

    private static int getWeightedDistanceFor(int r1, int r2) {
        return ((r1 * 13) * r1) + (r2 * r2);
    }

    private static boolean isBetterCandidate(int r3, Rect r4, Rect r5, Rect r6) {
        if (isCandidate(r4, r5, r3) == true) goto L6;
        return false;
    L6:
        if (isCandidate(r4, r6, r3) == true) goto L9;
        return true;
    L9:
        if (beamBeats(r3, r4, r5, r6) == false) goto L12;
        return true;
    L12:
        if (beamBeats(r3, r4, r6, r5) == false) goto L15;
        return false;
    L15:
        if (getWeightedDistanceFor(majorAxisDistance(r3, r4, r5), minorAxisDistance(r3, r4, r5)) >= getWeightedDistanceFor(majorAxisDistance(r3, r4, r6), minorAxisDistance(r3, r4, r6))) goto L17;
        return true;
    L17:
        return false;
    }

    private static boolean isCandidate(Rect r3, Rect r4, int r5) {
        if (r5 != 17) goto L5;
        int r52 = r3.right;
        int r0 = r4.right;
        if (r52 > r0) goto L41;
        if (r3.left >= r0) goto L41;
    L43:
        return false;
    L41:
        if (r3.left <= r4.left) goto L43;
        return true;
    L5:
        if (r5 != 33) goto L7;
        int r53 = r3.bottom;
        int r02 = r4.bottom;
        if (r53 > r02) goto L33;
        if (r3.top >= r02) goto L33;
    L35:
        return false;
    L33:
        if (r3.top <= r4.top) goto L35;
        return true;
    L7:
        if (r5 != 66) goto L9;
        int r54 = r3.left;
        int r03 = r4.left;
        if (r54 < r03) goto L25;
        if (r3.right <= r03) goto L25;
    L27:
        return false;
    L25:
        if (r3.right >= r4.right) goto L27;
        return true;
    L9:
        if (r5 != 130) goto L18;
        int r55 = r3.top;
        int r04 = r4.top;
        if (r55 < r04) goto L15;
        if (r3.bottom <= r04) goto L15;
    L17:
        return false;
    L15:
        if (r3.bottom >= r4.bottom) goto L17;
        return true;
    L18:
        f$$ExternalSyntheticBUOutline1.m("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        return false;
    }

    private static boolean isToDirectionOf(int r3, Rect r4, Rect r5) {
        if (r3 == 17) goto L25;
        if (r3 == 33) goto L21;
        if (r3 == 66) goto L17;
        if (r3 == 130) goto L11;
        f$$ExternalSyntheticBUOutline1.m("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        return false;
    L11:
        if (r4.bottom > r5.top) goto L13;
        return true;
    L13:
        return false;
    L17:
        if (r4.right > r5.left) goto L19;
        return true;
    L19:
        return false;
    L21:
        if (r4.top < r5.bottom) goto L23;
        return true;
    L23:
        return false;
    L25:
        if (r4.left < r5.right) goto L27;
        return true;
    L27:
        return false;
    }

    private static int majorAxisDistance(int r1, Rect r2, Rect r3) {
        return Math.max(0, majorAxisDistanceRaw(r1, r2, r3));
    }

    private static int majorAxisDistanceRaw(int r1, Rect r2, Rect r3) {
        if (r1 == 17) goto L19;
        if (r1 == 33) goto L17;
        if (r1 == 66) goto L15;
        if (r1 == 130) goto L11;
        f$$ExternalSyntheticBUOutline1.m("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        return 0;
    L11:
        return r3.top - r2.bottom;
    L15:
        return r3.left - r2.right;
    L17:
        return r2.top - r3.bottom;
    L19:
        return r2.left - r3.right;
    }

    private static int majorAxisDistanceToFarEdge(int r1, Rect r2, Rect r3) {
        return Math.max(1, majorAxisDistanceToFarEdgeRaw(r1, r2, r3));
    }

    private static int majorAxisDistanceToFarEdgeRaw(int r1, Rect r2, Rect r3) {
        if (r1 == 17) goto L19;
        if (r1 == 33) goto L17;
        if (r1 == 66) goto L15;
        if (r1 == 130) goto L11;
        f$$ExternalSyntheticBUOutline1.m("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        return 0;
    L11:
        return r3.bottom - r2.bottom;
    L15:
        return r3.right - r2.right;
    L17:
        return r2.top - r3.top;
    L19:
        return r2.left - r3.left;
    }

    private static int minorAxisDistance(int r1, Rect r2, Rect r3) {
        if (r1 == 17) goto L16;
        if (r1 == 33) goto L14;
        if (r1 == 66) goto L16;
        if (r1 == 130) goto L14;
        f$$ExternalSyntheticBUOutline1.m("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        return 0;
    L14:
        return Math.abs((r2.left + (r2.width() / 2)) - (r3.left + (r3.width() / 2)));
    L16:
        return Math.abs((r2.top + (r2.height() / 2)) - (r3.top + (r3.height() / 2)));
    }
}
