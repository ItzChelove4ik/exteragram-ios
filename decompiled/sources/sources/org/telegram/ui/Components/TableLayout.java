package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.Layout;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.android.dx.AppDataDirGuesser;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.Cells.TextSelectionHelper;

/* loaded from: classes7.dex */
public class TableLayout extends View {
    public static final Alignment BASELINE = null;
    public static final Alignment BOTTOM = null;
    public static final Alignment CENTER = null;
    public static final Alignment END = null;
    public static final Alignment FILL = null;
    private static final Alignment LEADING = null;
    public static final Alignment LEFT = null;
    public static final Alignment RIGHT = null;
    public static final Alignment START = null;
    public static final Alignment TOP = null;
    private static final Alignment TRAILING = null;
    static final Alignment UNDEFINED_ALIGNMENT = null;
    private TableA11yHelper accessibilityHelper;
    private Path backgroundPath;
    private ArrayList<Child> cellsToFixHeight;
    private ArrayList<Child> childrens;
    private int colCount;
    private TableLayoutDelegate delegate;
    private boolean drawLines;
    private int drawingHeight;
    private int drawingWidth;
    private boolean fillWidth;
    private boolean isRtl;
    private boolean isStriped;
    private int itemPaddingBottom;
    private int itemPaddingLeft;
    private int itemPaddingTop;
    private Path linePath;
    private int mAlignmentMode;
    private int mDefaultGap;
    private final Axis mHorizontalAxis;
    private int mLastLayoutParamsHashCode;
    private int mOrientation;
    private boolean mUseDefaultMargins;
    private final Axis mVerticalAxis;
    private int minimumCellHeight;
    private int[] naturalRowLocations;
    private float[] radii;
    private RectF rect;
    private ArrayList<PointF> rowSpans;
    private TextSelectionHelper.ArticleTextSelectionHelper textSelectionHelper;

    /* renamed from: org.telegram.ui.Components.TableLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends Alignment {
        public AnonymousClass1() {
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getAlignmentValue(Child r1, int r2) {
            return Integer.MIN_VALUE;
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getGravityOffset(Child r1, int r2) {
            return Integer.MIN_VALUE;
        }
    }

    /* renamed from: org.telegram.ui.Components.TableLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends Alignment {
        public AnonymousClass2() {
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getAlignmentValue(Child r1, int r2) {
            return 0;
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getGravityOffset(Child r1, int r2) {
            return 0;
        }
    }

    /* renamed from: org.telegram.ui.Components.TableLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends Alignment {
        public AnonymousClass3() {
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getAlignmentValue(Child r1, int r2) {
            return r2;
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getGravityOffset(Child r1, int r2) {
            return r2;
        }
    }

    /* renamed from: org.telegram.ui.Components.TableLayout$4, reason: invalid class name */
    public class AnonymousClass4 extends Alignment {
        final /* synthetic */ Alignment val$ltr;

        public AnonymousClass4(Alignment r1) {
            this.val$ltr = r1;
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getAlignmentValue(Child r1, int r2) {
            return this.val$ltr.getAlignmentValue(r1, r2);
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getGravityOffset(Child r1, int r2) {
            return this.val$ltr.getGravityOffset(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.TableLayout$5, reason: invalid class name */
    public class AnonymousClass5 extends Alignment {
        public AnonymousClass5() {
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getAlignmentValue(Child r1, int r2) {
            return r2 >> 1;
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getGravityOffset(Child r1, int r2) {
            return r2 >> 1;
        }
    }

    /* renamed from: org.telegram.ui.Components.TableLayout$6, reason: invalid class name */
    public class AnonymousClass6 extends Alignment {

        /* renamed from: org.telegram.ui.Components.TableLayout$6$1, reason: invalid class name */
        public class AnonymousClass1 extends Bounds {
            private int size;
            final /* synthetic */ AnonymousClass6 this$0;

            public AnonymousClass1(AnonymousClass6 r1) {
                this.this$0 = r1;
                super(null);
            }

            @Override // org.telegram.ui.Components.TableLayout.Bounds
            public int getOffset(TableLayout r2, Child r3, Alignment r4, int r5, boolean r6) {
                return Math.max(0, super.getOffset(r2, r3, r4, r5, r6));
            }

            @Override // org.telegram.ui.Components.TableLayout.Bounds
            public void include(int r2, int r3) {
                super.include(r2, r3);
                this.size = Math.max(this.size, r2 + r3);
            }

            @Override // org.telegram.ui.Components.TableLayout.Bounds
            public void reset() {
                super.reset();
                this.size = Integer.MIN_VALUE;
            }

            @Override // org.telegram.ui.Components.TableLayout.Bounds
            public int size(boolean r1) {
                return Math.max(super.size(r1), this.size);
            }
        }

        public AnonymousClass6() {
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getAlignmentValue(Child r1, int r2) {
            return Integer.MIN_VALUE;
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public Bounds getBounds() {
            return new AnonymousClass1(this);
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getGravityOffset(Child r1, int r2) {
            return 0;
        }
    }

    /* renamed from: org.telegram.ui.Components.TableLayout$7, reason: invalid class name */
    public class AnonymousClass7 extends Alignment {
        public AnonymousClass7() {
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getAlignmentValue(Child r1, int r2) {
            return Integer.MIN_VALUE;
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getGravityOffset(Child r1, int r2) {
            return 0;
        }

        @Override // org.telegram.ui.Components.TableLayout.Alignment
        public int getSizeInCell(Child r1, int r2, int r3) {
            return r3;
        }
    }

    public static abstract class Alignment {
        public Alignment() {
        }

        public abstract int getAlignmentValue(Child r1, int r2);

        public Bounds getBounds() {
            return new Bounds(null);
        }

        public abstract int getGravityOffset(Child r1, int r2);

        public int getSizeInCell(Child r1, int r2, int r3) {
            return r2;
        }
    }

    public static final class Arc {
        public final Interval span;
        public boolean valid;
        public final MutableInt value;

        public Arc(Interval r2, MutableInt r3) {
            this.valid = true;
            this.span = r2;
            this.value = r3;
        }
    }

    public static final class Assoc<K, V> extends ArrayList<Pair<K, V>> {
        private final Class<K> keyType;
        private final Class<V> valueType;

        private Assoc(Class<K> r1, Class<V> r2) {
            this.keyType = r1;
            this.valueType = r2;
        }

        public static <K, V> Assoc<K, V> of(Class<K> r1, Class<V> r2) {
            return new Assoc(r1, r2);
        }

        public PackedMap<K, V> pack() {
            int r0 = size();
            Object[] r1 = (Object[]) Array.newInstance(this.keyType, r0);
            Object[] r2 = (Object[]) Array.newInstance(this.valueType, r0);
            int r3 = 0;
        L3:
            if (r3 >= r0) goto L6;
            r1[r3] = get(r3).first;
            r2[r3] = get(r3).second;
            r3 = r3 + 1;
            goto L3
        L6:
            return new PackedMap(r1, r2, null);
        }

        public void put(K r1, V r2) {
            add(Pair.create(r1, r2));
        }
    }

    public final class Axis {
        public Arc[] arcs;
        public boolean arcsValid;
        PackedMap<Interval, MutableInt> backwardLinks;
        public boolean backwardLinksValid;
        public int definedCount;
        public int[] deltas;
        PackedMap<Interval, MutableInt> forwardLinks;
        public boolean forwardLinksValid;
        PackedMap<Spec, Bounds> groupBounds;
        public boolean groupBoundsValid;
        public boolean hasWeights;
        public boolean hasWeightsValid;
        public final boolean horizontal;
        public int[] leadingMargins;
        public boolean leadingMarginsValid;
        public int[] locations;
        public boolean locationsValid;
        private int maxIndex;
        boolean orderPreserved;
        private MutableInt parentMax;
        private MutableInt parentMin;
        final /* synthetic */ TableLayout this$0;
        public int[] trailingMargins;
        public boolean trailingMarginsValid;

        /* renamed from: org.telegram.ui.Components.TableLayout$Axis$1, reason: invalid class name */
        public class AnonymousClass1 {
            Arc[][] arcsByVertex;
            int cursor;
            Arc[] result;
            final /* synthetic */ Axis this$1;
            final /* synthetic */ Arc[] val$arcs;
            int[] visited;

            public AnonymousClass1(Axis r2, Arc[] r3) {
                this.this$1 = r2;
                this.val$arcs = r3;
                this.result = new Arc[r3.length];
                this.cursor = r0.length - 1;
                this.arcsByVertex = r2.groupArcsByFirstVertex(r3);
                this.visited = new int[r2.getCount() + 1];
            }

            public Arc[] sort() {
                int r0 = this.arcsByVertex.length;
                int r1 = 0;
            L3:
                if (r1 >= r0) goto L6;
                walk(r1);
                r1 = r1 + 1;
                goto L3
            L6:
                return this.result;
            }

            public void walk(int r8) {
                int[] r0 = this.visited;
                if (r0[r8] == 0) goto L5;
                return;
            L5:
                r0[r8] = 1;
                Arc[] r02 = this.arcsByVertex[r8];
                int r1 = r02.length;
                int r2 = 0;
            L6:
                if (r2 >= r1) goto L8;
                Arc r3 = r02[r2];
                walk(r3.span.max);
                Arc[] r4 = this.result;
                int r5 = this.cursor;
                this.cursor = r5 - 1;
                r4[r5] = r3;
                r2 = r2 + 1;
                goto L6
            L8:
                this.visited[r8] = 2;
            }
        }

        private Axis(TableLayout r2, boolean r3) {
            this.this$0 = r2;
            this.definedCount = Integer.MIN_VALUE;
            this.maxIndex = Integer.MIN_VALUE;
            this.groupBoundsValid = false;
            this.forwardLinksValid = false;
            this.backwardLinksValid = false;
            this.leadingMarginsValid = false;
            this.trailingMarginsValid = false;
            this.arcsValid = false;
            this.locationsValid = false;
            this.hasWeightsValid = false;
            this.orderPreserved = true;
            this.parentMin = new MutableInt(0);
            this.parentMax = new MutableInt(-100000);
            this.horizontal = r3;
        }

        public /* synthetic */ Axis(TableLayout r1, boolean r2, TableLayoutIA r3) {
            this(r1, r2);
        }

        private void addComponentSizes(List<Arc> r5, PackedMap<Interval, MutableInt> r6) {
            int r1 = 0;
        L3:
            Interval[] r2 = r6.keys;
            if (r1 >= r2.length) goto L6;
            include(r5, r2[r1], r6.values[r1], false);
            r1 = r1 + 1;
            goto L3
        }

        private int calculateMaxIndex() {
            int r0 = this.this$0.getChildCount();
            int r2 = 0;
            int r3 = -1;
        L3:
            if (r2 >= r0) goto L9;
            LayoutParams r4 = this.this$0.getChildAt(r2).getLayoutParams();
            if (this.horizontal == false) goto L7;
            Spec r42 = r4.columnSpec;
        L8:
            Interval r43 = r42.span;
            r3 = Math.max(Math.max(Math.max(r3, r43.min), r43.max), r43.size());
            r2 = r2 + 1;
            goto L3
        L7:
            r42 = r4.rowSpec;
            goto L8
        L9:
            if (r3 != (-1)) goto L12;
            return Integer.MIN_VALUE;
        L12:
            return r3;
        }

        private float calculateTotalWeight() {
            int r0 = this.this$0.getChildCount();
            float r1 = 0.0f;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L9;
            LayoutParams r3 = this.this$0.getChildAt(r2).getLayoutParams();
            if (this.horizontal == false) goto L7;
            Spec r32 = r3.columnSpec;
        L8:
            r1 = r1 + r32.weight;
            r2 = r2 + 1;
            goto L3
        L7:
            r32 = r3.rowSpec;
            goto L8
        L9:
            return r1;
        }

        private void computeArcs() {
            getForwardLinks();
            getBackwardLinks();
        }

        private void computeGroupBounds() {
            Bounds[] r0 = this.groupBounds.values;
            int r2 = 0;
        L4:
            if (r2 >= r0.length) goto L6;
            r0[r2].reset();
            r2 = r2 + 1;
            goto L4
        L6:
            int r02 = this.this$0.getChildCount();
            int r22 = 0;
        L7:
            if (r22 >= r02) goto L18;
            Child r6 = this.this$0.getChildAt(r22);
            LayoutParams r3 = r6.getLayoutParams();
            boolean r4 = this.horizontal;
            if (r4 == false) goto L12;
            Spec r32 = r3.columnSpec;
        L11:
            Spec r7 = r32;
            int r33 = this.this$0.getMeasurementIncludingMargin(r6, r4);
            if (r7.weight != 0.0f) goto L16;
            int r42 = 0;
        L17:
            this.groupBounds.getValue(r22).include(this.this$0, r6, r7, this, r33 + r42);
            r22 = r22 + 1;
            goto L7
        L16:
            r42 = this.deltas[r22];
            goto L17
        L12:
            r32 = r3.rowSpec;
            goto L11
        }

        private boolean computeHasWeights() {
            int r0 = this.this$0.getChildCount();
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L13;
            LayoutParams r3 = this.this$0.getChildAt(r2).getLayoutParams();
            if (this.horizontal == false) goto L7;
            Spec r32 = r3.columnSpec;
        L9:
            if (r32.weight != 0.0f) goto L10;
            r2 = r2 + 1;
            goto L3
        L10:
            return true;
        L7:
            r32 = r3.rowSpec;
            goto L9
        L13:
            return false;
        }

        private void computeLinks(PackedMap<Interval, MutableInt> r5, boolean r6) {
            MutableInt[] r0 = r5.values;
            int r1 = 0;
            int r2 = 0;
        L4:
            if (r2 >= r0.length) goto L6;
            r0[r2].reset();
            r2 = r2 + 1;
            goto L4
        L6:
            Bounds[] r4 = getGroupBounds().values;
        L8:
            if (r1 >= r4.length) goto L14;
            int r02 = r4[r1].size(r6);
            MutableInt r22 = r5.getValue(r1);
            int r3 = r22.value;
            if (r6 == true) goto L13;
            r02 = -r02;
        L13:
            r22.value = Math.max(r3, r02);
            r1 = r1 + 1;
            goto L8
        }

        private void computeLocations(int[] r4) {
            if (hasWeights() == true) goto L5;
            solve(r4);
        L7:
            if (this.orderPreserved == true) goto L11;
            int r3 = 0;
            int r0 = r4[0];
            int r1 = r4.length;
        L9:
            if (r3 >= r1) goto L13;
            r4[r3] = r4[r3] - r0;
            r3 = r3 + 1;
            goto L9
        L13:
            return;
        L11:
            return;
        L5:
            solveAndDistributeSpace(r4);
            goto L7
        }

        private void computeMargins(boolean r9) {
            if (r9 == false) goto L4;
            int[] r0 = this.leadingMargins;
        L5:
            int r1 = this.this$0.getChildCount();
            int r2 = 0;
        L6:
            if (r2 >= r1) goto L16;
            Child r3 = this.this$0.getChildAt(r2);
            LayoutParams r4 = r3.getLayoutParams();
            boolean r5 = this.horizontal;
            if (r5 == false) goto L10;
            Spec r42 = r4.columnSpec;
        L11:
            Interval r43 = r42.span;
            if (r9 == false) goto L14;
            int r44 = r43.min;
        L15:
            r0[r44] = Math.max(r0[r44], this.this$0.getMargin1(r3, r5, r9));
            r2 = r2 + 1;
            goto L6
        L14:
            r44 = r43.max;
            goto L15
        L10:
            r42 = r4.rowSpec;
            goto L11
        L16:
            return;
        L4:
            r0 = this.trailingMargins;
            goto L5
        }

        private Arc[] createArcs() {
            ArrayList r0 = new ArrayList();
            ArrayList r1 = new ArrayList();
            addComponentSizes(r0, getForwardLinks());
            addComponentSizes(r1, getBackwardLinks());
            if (this.orderPreserved == false) goto L8;
            int r2 = 0;
        L6:
            if (r2 >= getCount()) goto L8;
            int r5 = r2 + 1;
            include(r0, new Interval(r2, r5), new MutableInt(0));
            r2 = r5;
        L8:
            int r22 = getCount();
            include(r0, new Interval(0, r22), this.parentMin, false);
            include(r1, new Interval(r22, 0), this.parentMax, false);
            return (Arc[]) TableLayout.append(topologicalSort(r0), topologicalSort(r1));
        }

        private PackedMap<Spec, Bounds> createGroupBounds() {
            Assoc r0 = Assoc.of(Spec.class, Bounds.class);
            int r1 = this.this$0.getChildCount();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L10;
            LayoutParams r3 = this.this$0.getChildAt(r2).getLayoutParams();
            boolean r4 = this.horizontal;
            if (r4 == false) goto L7;
            Spec r32 = r3.columnSpec;
        L8:
            r0.put(r32, Spec.m13193$$Nest$mgetAbsoluteAlignment(r32, r4).getBounds());
            r2 = r2 + 1;
            goto L3
        L7:
            r32 = r3.rowSpec;
            goto L8
        L10:
            return r0.pack();
        }

        private PackedMap<Interval, MutableInt> createLinks(boolean r6) {
            Assoc r0 = Assoc.of(Interval.class, MutableInt.class);
            Spec[] r5 = getGroupBounds().keys;
            int r1 = r5.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L9;
            if (r6 == false) goto L6;
            Interval r3 = r5[r2].span;
        L7:
            r0.put(r3, new MutableInt());
            r2 = r2 + 1;
            goto L3
        L6:
            r3 = r5[r2].span.inverse();
            goto L7
        L9:
            return r0.pack();
        }

        private PackedMap<Interval, MutableInt> getBackwardLinks() {
            if (this.backwardLinks != null) goto L6;
            this.backwardLinks = createLinks(false);
        L6:
            if (this.backwardLinksValid == true) goto L9;
            computeLinks(this.backwardLinks, false);
            this.backwardLinksValid = true;
        L9:
            return this.backwardLinks;
        }

        private PackedMap<Interval, MutableInt> getForwardLinks() {
            if (this.forwardLinks != null) goto L6;
            this.forwardLinks = createLinks(true);
        L6:
            if (this.forwardLinksValid == true) goto L9;
            computeLinks(this.forwardLinks, true);
            this.forwardLinksValid = true;
        L9:
            return this.forwardLinks;
        }

        private int getMaxIndex() {
            if (this.maxIndex != Integer.MIN_VALUE) goto L6;
            this.maxIndex = Math.max(0, calculateMaxIndex());
        L6:
            return this.maxIndex;
        }

        private int getMeasure(int r1, int r2) {
            setParentConstraints(r1, r2);
            return size(getLocations());
        }

        private boolean hasWeights() {
            if (this.hasWeightsValid == true) goto L6;
            this.hasWeights = computeHasWeights();
            this.hasWeightsValid = true;
        L6:
            return this.hasWeights;
        }

        private void include(List<Arc> r2, Interval r3, MutableInt r4) {
            include(r2, r3, r4, true);
        }

        private void include(List<Arc> r1, Interval r2, MutableInt r3, boolean r4) {
            if (r2.size() == 0) goto L11;
            if (r4 == false) goto L12;
            Iterator<Arc> r0 = r1.iterator();
        L8:
            if (r0.hasNext() == false) goto L12;
            if (r0.next().span.equals(r2) == false) goto L8;
            return;
        L12:
            r1.add(new Arc(r2, r3));
            return;
        }

        private void init(int[] r1) {
            Arrays.fill(r1, 0);
        }

        private boolean relax(int[] r3, Arc r4) {
            if (r4.valid == true) goto L5;
            return false;
        L5:
            Interval r2 = r4.span;
            int r1 = r2.min;
            int r22 = r2.max;
            int r42 = r4.value.value;
            int r12 = r3[r1] + r42;
            if (r12 <= r3[r22]) goto L9;
            r3[r22] = r12;
            return true;
        L9:
            return false;
        }

        private void setParentConstraints(int r2, int r3) {
            this.parentMin.value = r2;
            int r32 = -r3;
            this.parentMax.value = r32;
            this.locationsValid = false;
        }

        private void shareOutDelta(int r6, float r7) {
            int r1 = 0;
            Arrays.fill(this.deltas, 0);
            int r0 = this.this$0.getChildCount();
        L3:
            if (r1 >= r0) goto L12;
            LayoutParams r2 = this.this$0.getChildAt(r1).getLayoutParams();
            if (this.horizontal == false) goto L7;
            Spec r22 = r2.columnSpec;
        L8:
            float r23 = r22.weight;
            if (r23 == 0.0f) goto L11;
            int r3 = Math.round((r6 * r23) / r7);
            this.deltas[r1] = r3;
            r6 = r6 - r3;
            r7 = r7 - r23;
        L11:
            r1 = r1 + 1;
            goto L3
        L7:
            r22 = r2.rowSpec;
            goto L8
        }

        private int size(int[] r1) {
            return r1[getCount()];
        }

        private boolean solve(int[] r2) {
            return solve(getArcs(), r2);
        }

        private boolean solve(Arc[] r2, int[] r3) {
            return solve(r2, r3, true);
        }

        private boolean solve(Arc[] r11, int[] r12, boolean r13) {
            int r0 = getCount() + 1;
            int r3 = 0;
        L4:
            if (r3 >= r11.length) goto L32;
            init(r12);
            int r4 = 0;
        L6:
            if (r4 >= r0) goto L13;
            int r5 = r11.length;
            int r6 = 0;
            boolean r7 = false;
        L8:
            if (r6 >= r5) goto L10;
            r7 = r7 | relax(r12, r11[r6]);
            r6 = r6 + 1;
            goto L8
        L10:
            if (r7 == false) goto L11;
            r4 = r4 + 1;
            goto L6
        L11:
            return true;
        L13:
            if (r13 == false) goto L14;
            boolean[] r42 = new boolean[r11.length];
            int r52 = 0;
        L16:
            if (r52 >= r0) goto L21;
            int r62 = r11.length;
            int r72 = 0;
        L18:
            if (r72 >= r62) goto L20;
            r42[r72] = r42[r72] | relax(r12, r11[r72]);
            r72 = r72 + 1;
            goto L18
        L20:
            r52 = r52 + 1;
            goto L16
        L21:
            int r53 = 0;
        L23:
            if (r53 >= r11.length) goto L31;
            if (r42[r53] == false) goto L30;
            Arc r63 = r11[r53];
            Interval r73 = r63.span;
            if (r73.min < r73.max) goto L30;
            r63.valid = false;
        L30:
            r53 = r53 + 1;
        L31:
            r3 = r3 + 1;
            goto L4
        L14:
            return false;
        L32:
            return true;
        }

        private void solveAndDistributeSpace(int[] r11) {
            Arrays.fill(getDeltas(), 0);
            solve(r11);
            boolean r2 = true;
            int r0 = (this.parentMin.value * this.this$0.getChildCount()) + 1;
            if (r0 < 2) goto L21;
            float r3 = calculateTotalWeight();
            int r4 = -1;
            int r5 = 0;
        L6:
            if (r5 >= r0) goto L12;
            int r22 = (int) ((r5 + r0) / 2);
            invalidateValues();
            shareOutDelta(r22, r3);
            boolean r6 = solve(getArcs(), r11, false);
            if (r6 == false) goto L10;
            r5 = r22 + 1;
            r4 = r22;
        L11:
            r2 = r6;
            goto L6
        L10:
            r0 = r22;
            goto L11
        L12:
            if (r4 <= 0) goto L19;
            if (r2 == true) goto L20;
            invalidateValues();
            shareOutDelta(r4, r3);
            solve(r11);
            return;
        L20:
            return;
        L19:
            return;
        }

        private Arc[] topologicalSort(List<Arc> r2) {
            return topologicalSort((Arc[]) r2.toArray(new Arc[0]));
        }

        private Arc[] topologicalSort(Arc[] r2) {
            return new AnonymousClass1(this, r2).sort();
        }

        public Arc[] getArcs() {
            if (this.arcs != null) goto L6;
            this.arcs = createArcs();
        L6:
            if (this.arcsValid == true) goto L9;
            computeArcs();
            this.arcsValid = true;
        L9:
            return this.arcs;
        }

        public int getCount() {
            return Math.max(this.definedCount, getMaxIndex());
        }

        public int[] getDeltas() {
            if (this.deltas != null) goto L6;
            this.deltas = new int[this.this$0.getChildCount()];
        L6:
            return this.deltas;
        }

        public PackedMap<Spec, Bounds> getGroupBounds() {
            if (this.groupBounds != null) goto L6;
            this.groupBounds = createGroupBounds();
        L6:
            if (this.groupBoundsValid == true) goto L9;
            computeGroupBounds();
            this.groupBoundsValid = true;
        L9:
            return this.groupBounds;
        }

        public int[] getLeadingMargins() {
            if (this.leadingMargins != null) goto L6;
            this.leadingMargins = new int[getCount() + 1];
        L6:
            if (this.leadingMarginsValid == true) goto L9;
            computeMargins(true);
            this.leadingMarginsValid = true;
        L9:
            return this.leadingMargins;
        }

        public int[] getLocations() {
            if (this.locations != null) goto L6;
            this.locations = new int[getCount() + 1];
        L6:
            if (this.locationsValid == true) goto L9;
            computeLocations(this.locations);
            this.locationsValid = true;
        L9:
            return this.locations;
        }

        public int getMeasure(int r4) {
            int r0 = View.MeasureSpec.getMode(r4);
            int r42 = View.MeasureSpec.getSize(r4);
            if (r0 == Integer.MIN_VALUE) goto L13;
            if (r0 == 0) goto L11;
            if (r0 == 1073741824) goto L9;
            return 0;
        L9:
            return getMeasure(r42, r42);
        L11:
            return getMeasure(0, AppDataDirGuesser.PER_USER_RANGE);
        L13:
            return getMeasure(0, r42);
        }

        public int[] getTrailingMargins() {
            if (this.trailingMargins != null) goto L6;
            this.trailingMargins = new int[getCount() + 1];
        L6:
            if (this.trailingMarginsValid == true) goto L9;
            computeMargins(false);
            this.trailingMarginsValid = true;
        L9:
            return this.trailingMargins;
        }

        public Arc[][] groupArcsByFirstVertex(Arc[] r9) {
            int r8 = getCount() + 1;
            Arc[][] r0 = new Arc[r8][];
            int[] r1 = new int[r8];
            int r2 = r9.length;
            int r3 = 0;
            int r4 = 0;
        L3:
            if (r4 >= r2) goto L5;
            int r5 = r9[r4].span.min;
            r1[r5] = r1[r5] + 1;
            r4 = r4 + 1;
            goto L3
        L5:
            int r22 = 0;
        L6:
            if (r22 >= r8) goto L8;
            r0[r22] = new Arc[r1[r22]];
            r22 = r22 + 1;
            goto L6
        L8:
            Arrays.fill(r1, 0);
            int r82 = r9.length;
        L9:
            if (r3 >= r82) goto L11;
            Arc r23 = r9[r3];
            int r42 = r23.span.min;
            Arc[] r52 = r0[r42];
            int r6 = r1[r42];
            r1[r42] = r6 + 1;
            r52[r6] = r23;
            r3 = r3 + 1;
            goto L9
        L11:
            return r0;
        }

        public void invalidateStructure() {
            this.maxIndex = Integer.MIN_VALUE;
            this.groupBounds = null;
            this.forwardLinks = null;
            this.backwardLinks = null;
            this.leadingMargins = null;
            this.trailingMargins = null;
            this.arcs = null;
            this.locations = null;
            this.deltas = null;
            this.hasWeightsValid = false;
            invalidateValues();
        }

        public void invalidateValues() {
            this.groupBoundsValid = false;
            this.forwardLinksValid = false;
            this.backwardLinksValid = false;
            this.leadingMarginsValid = false;
            this.trailingMarginsValid = false;
            this.arcsValid = false;
            this.locationsValid = false;
        }

        public void layout(int r1) {
            setParentConstraints(r1, r1);
            getLocations();
        }

        public void setCount(int r3) {
            if (r3 != Integer.MIN_VALUE) goto L5;
        L11:
            this.definedCount = r3;
            return;
        L5:
            if (r3 >= getMaxIndex()) goto L11;
            if (this.horizontal == false) goto L9;
            String r0 = "column";
        L10:
            TableLayout.m13177$$Nest$smhandleInvalidParams(r0.concat("Count must be greater than or equal to the maximum of all grid indices (and spans) defined in the LayoutParams of each child"));
            goto L11
        L9:
            r0 = "row";
            goto L10
        }

        public void setOrderPreserved(boolean r1) {
            this.orderPreserved = r1;
            invalidateStructure();
        }
    }

    public static class Bounds {
        public int after;
        public int before;
        public int flexibility;

        private Bounds() {
            reset();
        }

        public /* synthetic */ Bounds(TableLayoutIA r1) {
            this();
        }

        public int getOffset(TableLayout r1, Child r2, Alignment r3, int r4, boolean r5) {
            return this.before - r3.getAlignmentValue(r2, r4);
        }

        public void include(int r2, int r3) {
            this.before = Math.max(this.before, r2);
            this.after = Math.max(this.after, r3);
        }

        public final void include(TableLayout r2, Child r3, Spec r4, Axis r5, int r6) {
            this.flexibility &= r4.getFlexibility();
            int r22 = Spec.m13193$$Nest$mgetAbsoluteAlignment(r4, r5.horizontal).getAlignmentValue(r3, r6);
            include(r22, r6 - r22);
        }

        public void reset() {
            this.before = Integer.MIN_VALUE;
            this.after = Integer.MIN_VALUE;
            this.flexibility = 2;
        }

        public int size(boolean r1) {
            if (r1 == true) goto L8;
            if (TableLayout.canStretch(this.flexibility) == false) goto L8;
            return AppDataDirGuesser.PER_USER_RANGE;
        L8:
            return this.before + this.after;
        }
    }

    public interface CellText extends TextSelectionHelper.TextLayoutBlock {
        void attach(View r1);

        void detach(View r1);

        void draw(Canvas r1, View r2);

        default int getEmojiOnlyCount() {
            return 0;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        default CharSequence getText() {
            if (getLayout() != null) goto L7;
            return null;
        L7:
            return getLayout().getText();
        }

        void setRow(int r1);

        void setX(int r1);

        void setY(int r1);
    }

    public class Child {
        private TL_iv.pageTableCell cell;
        private int fixedHeight;
        private int index;
        private LayoutParams layoutParams;
        private int measuredHeight;
        private int measuredWidth;
        private int naturalWidth;
        private int naturalX;
        public int rowspan;
        private int selectionIndex;
        public int textHeight;
        public CellText textLayout;
        public int textLeft;
        public int textWidth;
        public int textX;
        public int textY;
        final /* synthetic */ TableLayout this$0;
        public int x;
        public int y;

        /* renamed from: -$$Nest$fgetcell, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TL_iv.pageTableCell m13178$$Nest$fgetcell(Child r0) {
            return r0.cell;
        }

        /* renamed from: -$$Nest$fgetfixedHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13179$$Nest$fgetfixedHeight(Child r0) {
            return r0.fixedHeight;
        }

        /* renamed from: -$$Nest$fgetindex, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13180$$Nest$fgetindex(Child r0) {
            return r0.index;
        }

        /* renamed from: -$$Nest$fgetlayoutParams, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LayoutParams m13181$$Nest$fgetlayoutParams(Child r0) {
            return r0.layoutParams;
        }

        /* renamed from: -$$Nest$fgetmeasuredHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13182$$Nest$fgetmeasuredHeight(Child r0) {
            return r0.measuredHeight;
        }

        /* renamed from: -$$Nest$fgetmeasuredWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13183$$Nest$fgetmeasuredWidth(Child r0) {
            return r0.measuredWidth;
        }

        /* renamed from: -$$Nest$fgetnaturalWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13184$$Nest$fgetnaturalWidth(Child r0) {
            return r0.naturalWidth;
        }

        /* renamed from: -$$Nest$fgetnaturalX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13185$$Nest$fgetnaturalX(Child r0) {
            return r0.naturalX;
        }

        /* renamed from: -$$Nest$fputcell, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13186$$Nest$fputcell(Child r0, TL_iv.pageTableCell r1) {
            r0.cell = r1;
        }

        /* renamed from: -$$Nest$fputfixedHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13187$$Nest$fputfixedHeight(Child r0, int r1) {
            r0.fixedHeight = r1;
        }

        /* renamed from: -$$Nest$fputlayoutParams, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13188$$Nest$fputlayoutParams(Child r0, LayoutParams r1) {
            r0.layoutParams = r1;
        }

        /* renamed from: -$$Nest$fputmeasuredHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13189$$Nest$fputmeasuredHeight(Child r0, int r1) {
            r0.measuredHeight = r1;
        }

        /* renamed from: -$$Nest$mcaptureNaturalHorizontalGeometry, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13190$$Nest$mcaptureNaturalHorizontalGeometry(Child r0) {
            r0.captureNaturalHorizontalGeometry();
        }

        /* renamed from: -$$Nest$msetRenderHorizontalGeometry, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13191$$Nest$msetRenderHorizontalGeometry(Child r0, int r1, int r2) {
            r0.setRenderHorizontalGeometry(r1, r2);
        }

        /* renamed from: -$$Nest$msetRenderVerticalGeometry, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13192$$Nest$msetRenderVerticalGeometry(Child r0, int r1, int r2) {
            r0.setRenderVerticalGeometry(r1, r2);
        }

        public Child(TableLayout r1, int r2) {
            this.this$0 = r1;
            this.selectionIndex = -1;
            this.index = r2;
        }

        private void captureNaturalHorizontalGeometry() {
            this.naturalX = this.x;
            this.naturalWidth = this.measuredWidth;
        }

        private void setRenderHorizontalGeometry(int r2, int r3) {
            this.x = r2;
            this.measuredWidth = Math.max(0, r3 - r2);
            if (this.cell != null) goto L5;
            return;
        L5:
            if (this.textLayout == null) goto L9;
            updateTextX();
            return;
        }

        private void setRenderVerticalGeometry(int r2, int r3) {
            this.y = r2;
            this.measuredHeight = Math.max(0, r3 - r2);
            if (this.cell == null) goto L6;
            updateTextY();
            return;
        }

        private void updateTextX() {
            int r0 = -this.textLeft;
            this.textX = r0;
            TL_iv.pageTableCell r1 = this.cell;
            if (r1.align_right == false) goto L7;
            this.textX = r0 + ((this.measuredWidth - this.textWidth) - TableLayout.m13172$$Nest$fgetitemPaddingLeft(this.this$0));
            return;
        L7:
            if (r1.align_center == false) goto L10;
            this.textX = r0 + Math.round((this.measuredWidth - this.textWidth) / 2.0f);
            return;
        L10:
            this.textX = r0 + TableLayout.m13172$$Nest$fgetitemPaddingLeft(this.this$0);
        }

        private void updateTextY() {
            TL_iv.pageTableCell r0 = this.cell;
            if (r0.valign_middle == false) goto L7;
            this.textY = (this.measuredHeight - this.textHeight) / 2;
            return;
        L7:
            if (r0.valign_bottom == false) goto L10;
            this.textY = (this.measuredHeight - this.textHeight) - TableLayout.m13171$$Nest$fgetitemPaddingBottom(this.this$0);
            return;
        L10:
            this.textY = TableLayout.m13173$$Nest$fgetitemPaddingTop(this.this$0);
        }

        public void draw(Canvas r2, View r3) {
            draw(r2, r3, true);
        }

        public void draw(Canvas r17, View r18, boolean r19) {
            Canvas r1 = r17;
            if (this.cell == null) goto L122;
            boolean r4 = false;
            boolean r5 = true;
            if ((this.x + this.measuredWidth) != TableLayout.m13169$$Nest$fgetdrawingWidth(this.this$0)) goto L8;
            boolean r7 = true;
        L10:
            if ((this.y + this.measuredHeight) != TableLayout.m13168$$Nest$fgetdrawingHeight(this.this$0)) goto L12;
            boolean r8 = true;
        L13:
            int r9 = AndroidUtilities.dp(8.0f);
            if (this.cell.header == true) goto L20;
            if (TableLayout.m13170$$Nest$fgetisStriped(this.this$0) == true) goto L18;
        L50:
            if (r19 == false) goto L60;
            if (this.textLayout == null) goto L60;
            r1.save();
            r1.translate(getTextX(), getTextY());
            if (this.selectionIndex >= 0) goto L56;
        L58:
            this.textLayout.draw(r1, r18);
            r1.restore();
            goto L60
        L56:
            if (TableLayout.m13176$$Nest$fgettextSelectionHelper(this.this$0) == null) goto L58;
            TableLayout.m13176$$Nest$fgettextSelectionHelper(this.this$0).draw(r1, (TextSelectionHelper.ArticleSelectableView) this.this$0.getParent().getParent(), this.selectionIndex);
        L60:
            if (TableLayout.m13167$$Nest$fgetdrawLines(this.this$0) == false) goto L119;
            Paint r6 = TableLayout.m13166$$Nest$fgetdelegate(this.this$0).getLinePaint();
            Paint r11 = TableLayout.m13166$$Nest$fgetdelegate(this.this$0).getLinePaint();
            float r12 = r6.getStrokeWidth() / 2.0f;
            float r13 = r11.getStrokeWidth() / 2.0f;
            int r2 = this.x;
            int r3 = this.y;
            if (r2 != 0) goto L70;
            float r22 = r3;
            float r42 = this.measuredHeight + r3;
            if (r3 != 0) goto L66;
            r22 = r22 + r9;
        L66:
            float r32 = r22;
            if (r42 != TableLayout.m13168$$Nest$fgetdrawingHeight(this.this$0)) goto L69;
            r42 = r42 - r9;
        L69:
            int r23 = this.x;
            r1.drawLine(r23 + r12, r32, r23 + r12, r42, r6);
            Paint r14 = r6;
            Paint r62 = r11;
        L71:
            int r15 = this.y;
            int r24 = this.x;
            if (r15 != 0) goto L80;
            float r16 = r24;
            float r33 = this.measuredWidth + r24;
            if (r24 != 0) goto L76;
            r16 = r16 + r9;
        L76:
            float r25 = r16;
            if (r33 != TableLayout.m13169$$Nest$fgetdrawingWidth(this.this$0)) goto L79;
            r33 = r33 - r9;
        L79:
            int r110 = this.y;
            r17.drawLine(r25, r110 + r12, r33, r110 + r12, r14);
        L81:
            if (r7 == false) goto L86;
            int r111 = this.y;
            if (r111 != 0) goto L86;
            float r112 = r111 + r9;
        L85:
            float r34 = r112;
            if (r7 == false) goto L91;
            if (r8 == false) goto L91;
            float r113 = (this.y + this.measuredHeight) - r9;
        L90:
            float r52 = r113;
            int r114 = this.x;
            int r26 = this.measuredWidth;
            Paint r63 = r14;
            r17.drawLine((r114 + r26) - r12, r34, (r114 + r26) - r12, r52, r63);
            int r115 = this.x;
            if (r115 != 0) goto L96;
            if (r8 == false) goto L96;
            float r27 = r115 + r9;
        L97:
            if (r7 == false) goto L101;
            if (r8 == false) goto L101;
            float r116 = (r115 + this.measuredWidth) - r9;
        L100:
            float r43 = r116;
            int r117 = this.y;
            int r35 = this.measuredHeight;
            r17.drawLine(r27, (r117 + r35) - r12, r43, (r117 + r35) - r12, r63);
            if (this.x == 0) goto L105;
        L107:
            if (r7 == false) goto L112;
            if (this.y != 0) goto L112;
            RectF r118 = TableLayout.m13175$$Nest$fgetrect(this.this$0);
            int r28 = this.x;
            int r36 = this.measuredWidth;
            float r53 = r9 * 2;
            int r119 = this.y;
            r118.set(((r28 + r36) - r12) - r53, r119 + r12, (r28 + r36) - r12, (r119 + r12) + r53);
            r17.drawArc(TableLayout.m13175$$Nest$fgetrect(this.this$0), 0.0f, -90.0f, false, r63);
        L112:
            if (this.x != 0) goto L115;
            if (r8 == false) goto L115;
            RectF r120 = TableLayout.m13175$$Nest$fgetrect(this.this$0);
            int r29 = this.x;
            int r44 = this.y;
            int r54 = this.measuredHeight;
            float r132 = r9 * 2;
            r120.set(r29 + r12, ((r44 + r54) - r12) - r132, (r29 + r12) + r132, (r44 + r54) - r12);
            r17.drawArc(TableLayout.m13175$$Nest$fgetrect(this.this$0), 180.0f, -90.0f, false, r63);
        L115:
            if (r7 == false) goto L120;
            if (r8 == false) goto L121;
            RectF r121 = TableLayout.m13175$$Nest$fgetrect(this.this$0);
            int r210 = this.x;
            int r37 = this.measuredWidth;
            float r55 = r9 * 2;
            int r72 = this.y;
            int r82 = this.measuredHeight;
            r121.set(((r210 + r37) - r12) - r55, ((r72 + r82) - r12) - r55, (r210 + r37) - r12, (r72 + r82) - r12);
            r17.drawArc(TableLayout.m13175$$Nest$fgetrect(this.this$0), 0.0f, 90.0f, false, r63);
            return;
        L121:
            return;
        L120:
            return;
        L105:
            if (this.y != 0) goto L107;
            RectF r122 = TableLayout.m13175$$Nest$fgetrect(this.this$0);
            int r211 = this.x;
            int r45 = this.y;
            float r1110 = r9 * 2;
            r122.set(r211 + r12, r45 + r12, (r211 + r12) + r1110, (r45 + r12) + r1110);
            r17.drawArc(TableLayout.m13175$$Nest$fgetrect(this.this$0), -180.0f, 90.0f, false, r63);
        L101:
            r116 = (r115 + this.measuredWidth) - r12;
        L96:
            r27 = r115 - r12;
        L91:
            r113 = (this.y + this.measuredHeight) - r12;
        L86:
            r112 = this.y - r12;
            goto L85
        L80:
            r17.drawLine(r24, r15 - r13, r24 + this.measuredWidth, r15 - r13, r62);
            goto L81
        L70:
            r14 = r6;
            r62 = r11;
            r17.drawLine(r2 - r13, r3, r2 - r13, r3 + this.measuredHeight, r62);
            goto L71
        L119:
            return;
        L18:
            if ((this.layoutParams.rowSpec.span.min % 2) != 0) goto L50;
        L20:
            if (this.x == 0) goto L22;
        L24:
            float[] r212 = TableLayout.m13174$$Nest$fgetradii(this.this$0);
            TableLayout.m13174$$Nest$fgetradii(this.this$0)[1] = 0.0f;
            r212[0] = 0.0f;
        L26:
            if (r7 == true) goto L28;
        L30:
            float[] r64 = TableLayout.m13174$$Nest$fgetradii(this.this$0);
            TableLayout.m13174$$Nest$fgetradii(this.this$0)[3] = 0.0f;
            r64[2] = 0.0f;
        L32:
            if (r7 == false) goto L35;
            if (r8 == false) goto L35;
            float[] r46 = TableLayout.m13174$$Nest$fgetradii(this.this$0);
            float r123 = r9;
            TableLayout.m13174$$Nest$fgetradii(this.this$0)[5] = r123;
            r46[4] = r123;
            r4 = true;
        L37:
            if (this.x != 0) goto L40;
            if (r8 == false) goto L40;
            float[] r213 = TableLayout.m13174$$Nest$fgetradii(this.this$0);
            float r47 = r9;
            TableLayout.m13174$$Nest$fgetradii(this.this$0)[7] = r47;
            r213[6] = r47;
        L41:
            if (r5 == false) goto L46;
            TableLayout.m13175$$Nest$fgetrect(this.this$0).set(this.x, this.y, r3 + this.measuredWidth, r5 + this.measuredHeight);
            TableLayout.m13165$$Nest$fgetbackgroundPath(this.this$0).reset();
            TableLayout.m13165$$Nest$fgetbackgroundPath(this.this$0).addRoundRect(TableLayout.m13175$$Nest$fgetrect(this.this$0), TableLayout.m13174$$Nest$fgetradii(this.this$0), Path.Direction.CW);
            boolean r214 = this.cell.header;
            TableLayout r38 = this.this$0;
            if (r214 == false) goto L45;
            r1.drawPath(TableLayout.m13165$$Nest$fgetbackgroundPath(r38), TableLayout.m13166$$Nest$fgetdelegate(this.this$0).getHeaderPaint());
            goto L50
        L45:
            r1.drawPath(TableLayout.m13165$$Nest$fgetbackgroundPath(r38), TableLayout.m13166$$Nest$fgetdelegate(this.this$0).getStripPaint());
            goto L50
        L46:
            boolean r215 = this.cell.header;
            int r39 = this.x;
            if (r215 == false) goto L49;
            r1.drawRect(r39, this.y, r39 + this.measuredWidth, r4 + this.measuredHeight, TableLayout.m13166$$Nest$fgetdelegate(this.this$0).getHeaderPaint());
            r1 = r17;
            goto L50
        L49:
            int r124 = this.y;
            r1 = r17;
            r1.drawRect(r39, r124, this.measuredWidth + r39, r124 + this.measuredHeight, TableLayout.m13166$$Nest$fgetdelegate(this.this$0).getStripPaint());
        L40:
            float[] r216 = TableLayout.m13174$$Nest$fgetradii(this.this$0);
            TableLayout.m13174$$Nest$fgetradii(this.this$0)[7] = 0.0f;
            r216[6] = 0.0f;
            r5 = r4;
        L35:
            float[] r1111 = TableLayout.m13174$$Nest$fgetradii(this.this$0);
            TableLayout.m13174$$Nest$fgetradii(this.this$0)[5] = 0.0f;
            r1111[4] = 0.0f;
            goto L37
        L28:
            if (this.y != 0) goto L30;
            float[] r48 = TableLayout.m13174$$Nest$fgetradii(this.this$0);
            float r1112 = r9;
            TableLayout.m13174$$Nest$fgetradii(this.this$0)[3] = r1112;
            r48[2] = r1112;
            r4 = true;
            goto L32
        L22:
            if (this.y != 0) goto L24;
            float[] r217 = TableLayout.m13174$$Nest$fgetradii(this.this$0);
            float r1113 = r9;
            TableLayout.m13174$$Nest$fgetradii(this.this$0)[1] = r1113;
            r217[0] = r1113;
            r4 = true;
            goto L26
        L12:
            r8 = false;
            goto L13
        L8:
            r7 = false;
            goto L10
        }

        public LayoutParams getLayoutParams() {
            return this.layoutParams;
        }

        public int getMeasuredHeight() {
            return this.measuredHeight;
        }

        public int getMeasuredWidth() {
            return this.measuredWidth;
        }

        public int getRow() {
            return this.rowspan + 10;
        }

        public int getTextX() {
            return this.x + this.textX;
        }

        public int getTextY() {
            return this.y + this.textY;
        }

        public void layout(int r1, int r2, int r3, int r4) {
            this.x = r1;
            this.y = r2;
        }

        public void measure(int r2, int r3, boolean r4) {
            this.measuredWidth = r2;
            this.measuredHeight = r3;
            if (r4 == false) goto L6;
            this.fixedHeight = r3;
        L6:
            if (this.cell == null) goto L25;
            CellText r22 = this.textLayout;
            if (r22 == null) goto L23;
            Layout r23 = r22.getLayout();
            if (r23 == null) goto L12;
            int r24 = r23.getLineCount();
        L13:
            if (r4 == false) goto L15;
        L22:
            updateTextX();
            goto L23
        L15:
            if (r24 > 1) goto L21;
            if (r24 <= 0) goto L22;
            TL_iv.pageTableCell r25 = this.cell;
            if (r25.align_center == true) goto L21;
            if (r25.align_right == false) goto L22;
        L21:
            setTextLayout(TableLayout.m13166$$Nest$fgetdelegate(this.this$0).createTextLayout(this.cell, this.measuredWidth - (TableLayout.m13172$$Nest$fgetitemPaddingLeft(this.this$0) * 2)));
            this.fixedHeight = (this.textHeight + TableLayout.m13173$$Nest$fgetitemPaddingTop(this.this$0)) + TableLayout.m13171$$Nest$fgetitemPaddingBottom(this.this$0);
            goto L22
        L12:
            r24 = 0;
        L23:
            updateTextY();
            return;
        }

        public void setFixedHeight(int r1) {
            this.measuredHeight = this.fixedHeight;
            updateTextY();
        }

        public void setSelectionIndex(int r1) {
            this.selectionIndex = r1;
        }

        public void setTextLayout(CellText r7) {
            this.textLayout = r7;
            if (r7 == null) goto L5;
            Layout r72 = r7.getLayout();
        L6:
            int r0 = 0;
            if (r72 == null) goto L17;
            this.textWidth = 0;
            this.textLeft = 0;
            int r1 = r72.getLineCount();
        L9:
            if (r0 >= r1) goto L15;
            float r2 = r72.getLineLeft(r0);
            if (r0 != 0) goto L13;
            int r22 = (int) Math.ceil(r2);
        L14:
            this.textLeft = r22;
            this.textWidth = (int) Math.ceil(Math.max(r72.getLineWidth(r0), this.textWidth));
            r0 = r0 + 1;
            goto L9
        L13:
            r22 = Math.min(this.textLeft, (int) Math.ceil(r2));
            goto L14
        L15:
            this.textHeight = r72.getHeight();
            return;
        L17:
            this.textLeft = 0;
            this.textWidth = 0;
            this.textHeight = 0;
            return;
        L5:
            r72 = null;
            goto L6
        }
    }

    public static final class Interval {
        public final int max;
        public final int min;

        public Interval(int r1, int r2) {
            this.min = r1;
            this.max = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L17:
            return false;
        L8:
            if (Interval.class != r5.getClass()) goto L17;
            Interval r52 = (Interval) r5;
            if (this.max == r52.max) goto L14;
            return false;
        L14:
            if (this.min == r52.min) goto L16;
            return false;
        L16:
            return true;
        }

        public int hashCode() {
            return (this.min * 31) + this.max;
        }

        public Interval inverse() {
            return new Interval(this.max, this.min);
        }

        public int size() {
            return this.max - this.min;
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final Interval DEFAULT_SPAN = null;
        private static final int DEFAULT_SPAN_SIZE = 0;
        public Spec columnSpec;
        public Spec rowSpec;

        static {
            Interval r0 = new Interval(Integer.MIN_VALUE, -2147483647);
            DEFAULT_SPAN = r0;
            DEFAULT_SPAN_SIZE = r0.size();
        }

        public LayoutParams() {
            Spec r0 = Spec.UNDEFINED;
            this(r0, r0);
        }

        private LayoutParams(int r1, int r2, int r3, int r4, int r5, int r6, Spec r7, Spec r8) {
            super(r1, r2);
            Spec r12 = Spec.UNDEFINED;
            this.rowSpec = r12;
            this.columnSpec = r12;
            setMargins(r3, r4, r5, r6);
            this.rowSpec = r7;
            this.columnSpec = r8;
        }

        public LayoutParams(Spec r10, Spec r11) {
            this(-2, -2, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, r10, r11);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L17:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L17;
            LayoutParams r52 = (LayoutParams) r5;
            if (this.columnSpec.equals(r52.columnSpec) == true) goto L14;
            return false;
        L14:
            if (this.rowSpec.equals(r52.rowSpec) == true) goto L16;
            return false;
        L16:
            return true;
        }

        public int hashCode() {
            return (this.rowSpec.hashCode() * 31) + this.columnSpec.hashCode();
        }

        public final void setColumnSpecSpan(Interval r2) {
            this.columnSpec = this.columnSpec.copyWriteSpan(r2);
        }

        public final void setRowSpecSpan(Interval r2) {
            this.rowSpec = this.rowSpec.copyWriteSpan(r2);
        }
    }

    public static final class MutableInt {
        public int value;

        public MutableInt() {
            reset();
        }

        public MutableInt(int r1) {
            this.value = r1;
        }

        public void reset() {
            this.value = Integer.MIN_VALUE;
        }
    }

    public static final class PackedMap<K, V> {
        public final int[] index;
        public final K[] keys;
        public final V[] values;

        private PackedMap(K[] r2, V[] r3) {
            int[] r0 = createIndex(r2);
            this.index = r0;
            this.keys = (K[]) compact(r2, r0);
            this.values = (V[]) compact(r3, r0);
        }

        public /* synthetic */ PackedMap(Object[] r1, Object[] r2, TableLayoutIA r3) {
            this(r1, r2);
        }

        private static <K> K[] compact(K[] r5, int[] r6) {
            int r0 = r5.length;
            K[] r1 = (K[]) ((Object[]) Array.newInstance(r5.getClass().getComponentType(), TableLayout.max2(r6, -1) + 1));
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L5;
            r1[r6[r2]] = r5[r2];
            r2 = r2 + 1;
            goto L3
        L5:
            return r1;
        }

        private static <K> int[] createIndex(K[] r6) {
            int r0 = r6.length;
            int[] r1 = new int[r0];
            HashMap r2 = new HashMap();
            int r3 = 0;
        L3:
            if (r3 >= r0) goto L8;
            K r4 = r6[r3];
            Integer r5 = (Integer) r2.get(r4);
            if (r5 != null) goto L7;
            r5 = Integer.valueOf(r2.size());
            r2.put(r4, r5);
        L7:
            r1[r3] = r5.intValue();
            r3 = r3 + 1;
            goto L3
        L8:
            return r1;
        }

        public V getValue(int r2) {
            return this.values[this.index[r2]];
        }
    }

    public static class Spec {
        static final Spec UNDEFINED = null;
        final Alignment alignment;
        final Interval span;
        final boolean startDefined;
        float weight;

        /* renamed from: -$$Nest$mgetAbsoluteAlignment, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Alignment m13193$$Nest$mgetAbsoluteAlignment(Spec r0, boolean r1) {
            return r0.getAbsoluteAlignment(r1);
        }

        static {
            UNDEFINED = TableLayout.spec(Integer.MIN_VALUE);
        }

        private Spec(boolean r2, int r3, int r4, Alignment r5, float r6) {
            this(r2, new Interval(r3, r4 + r3), r5, r6);
        }

        public /* synthetic */ Spec(boolean r1, int r2, int r3, Alignment r4, float r5, TableLayoutIA r6) {
            this(r1, r2, r3, r4, r5);
        }

        private Spec(boolean r1, Interval r2, Alignment r3, float r4) {
            this.startDefined = r1;
            this.span = r2;
            this.alignment = r3;
            this.weight = r4;
        }

        public /* synthetic */ Spec(boolean r1, Interval r2, Alignment r3, float r4, TableLayoutIA r5) {
            this(r1, r2, r3, r4);
        }

        private Alignment getAbsoluteAlignment(boolean r3) {
            Alignment r0 = this.alignment;
            if (r0 == TableLayout.UNDEFINED_ALIGNMENT) goto L6;
            return r0;
        L6:
            if (this.weight != 0.0f) goto L13;
            if (r3 == false) goto L11;
            return TableLayout.START;
        L11:
            return TableLayout.BASELINE;
        L13:
            return TableLayout.FILL;
        }

        public final Spec copyWriteSpan(Interval r4) {
            return new Spec(this.startDefined, r4, this.alignment, this.weight);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L17:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L17;
            Spec r52 = (Spec) r5;
            if (this.alignment.equals(r52.alignment) == true) goto L14;
            return false;
        L14:
            if (this.span.equals(r52.span) == true) goto L16;
            return false;
        L16:
            return true;
        }

        public final int getFlexibility() {
            if (this.alignment == TableLayout.UNDEFINED_ALIGNMENT) goto L5;
            return 2;
        L5:
            if (this.weight != 0.0f) goto L10;
            return 0;
        L10:
            return 2;
        }

        public int hashCode() {
            return (this.span.hashCode() * 31) + this.alignment.hashCode();
        }
    }

    public class TableA11yHelper extends ExploreByTouchHelper {
        final /* synthetic */ TableLayout this$0;
        private final Rect tmpRect;

        public TableA11yHelper(TableLayout r1, View r2) {
            this.this$0 = r1;
            super(r2);
            this.tmpRect = new Rect();
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public int getVirtualViewAt(float r6, float r7) {
            int r0 = this.this$0.getChildCount();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L19;
            Child r2 = this.this$0.getChildAt(r1);
            if (Child.m13183$$Nest$fgetmeasuredWidth(r2) <= 0) goto L18;
            if (Child.m13182$$Nest$fgetmeasuredHeight(r2) <= 0) goto L18;
            if (r6 < r2.x) goto L18;
            if (r6 >= (r3 + Child.m13183$$Nest$fgetmeasuredWidth(r2))) goto L18;
            if (r7 < r2.y) goto L18;
            if (r7 >= (r3 + Child.m13182$$Nest$fgetmeasuredHeight(r2))) goto L18;
            return r1;
        L18:
            r1 = r1 + 1;
            goto L3
        L19:
            return Integer.MIN_VALUE;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void getVisibleVirtualViews(List<Integer> r5) {
            int r0 = this.this$0.getChildCount();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L11;
            Child r2 = this.this$0.getChildAt(r1);
            if (Child.m13183$$Nest$fgetmeasuredWidth(r2) <= 0) goto L10;
            if (Child.m13182$$Nest$fgetmeasuredHeight(r2) <= 0) goto L10;
            r5.add(Integer.valueOf(r1));
        L10:
            r1 = r1 + 1;
            goto L3
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public boolean onPerformActionForVirtualView(int r1, int r2, Bundle r3) {
            return false;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onPopulateNodeForVirtualView(int r8, AccessibilityNodeInfoCompat r9) {
            if (r8 >= 0) goto L5;
        L21:
            this.tmpRect.set(0, 0, 1, 1);
            r9.setBoundsInParent(this.tmpRect);
            r9.setVisibleToUser(false);
            r9.setContentDescription(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L5:
            if (r8 >= this.this$0.getChildCount()) goto L21;
            Child r82 = this.this$0.getChildAt(r8);
            Rect r1 = this.tmpRect;
            int r2 = r82.x;
            r1.set(r2, r82.y, Child.m13183$$Nest$fgetmeasuredWidth(r82) + r2, r82.y + Child.m13182$$Nest$fgetmeasuredHeight(r82));
            r9.setBoundsInParent(this.tmpRect);
            r9.setClassName("android.widget.TextView");
            r9.setEnabled(true);
            CellText r7 = r82.textLayout;
            if (r7 == null) goto L10;
            CharSequence r72 = r7.getText();
        L11:
            if (r72 != null) goto L13;
        L14:
            r72 = " ";
        L15:
            r9.setText(r72);
            if (Child.m13178$$Nest$fgetcell(r82) != null) goto L18;
            return;
        L18:
            if (Child.m13178$$Nest$fgetcell(r82).header == false) goto L24;
            r9.setHeading(true);
            return;
        L24:
            return;
        L13:
            if (r72.length() != 0) goto L15;
        L10:
            r72 = null;
            goto L11
        }
    }

    public interface TableLayoutDelegate {
        CellText createTextLayout(TL_iv.pageTableCell r1, int r2);

        Paint getHeaderPaint();

        Paint getLinePaint();

        Paint getStripPaint();

        default void onLayoutChild(CellText r1, int r2, int r3) {
        }
    }

    /* renamed from: -$$Nest$fgetbackgroundPath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Path m13165$$Nest$fgetbackgroundPath(TableLayout r0) {
        return r0.backgroundPath;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TableLayoutDelegate m13166$$Nest$fgetdelegate(TableLayout r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdrawLines, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13167$$Nest$fgetdrawLines(TableLayout r0) {
        return r0.drawLines;
    }

    /* renamed from: -$$Nest$fgetdrawingHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13168$$Nest$fgetdrawingHeight(TableLayout r0) {
        return r0.drawingHeight;
    }

    /* renamed from: -$$Nest$fgetdrawingWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13169$$Nest$fgetdrawingWidth(TableLayout r0) {
        return r0.drawingWidth;
    }

    /* renamed from: -$$Nest$fgetisStriped, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13170$$Nest$fgetisStriped(TableLayout r0) {
        return r0.isStriped;
    }

    /* renamed from: -$$Nest$fgetitemPaddingBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13171$$Nest$fgetitemPaddingBottom(TableLayout r0) {
        return r0.itemPaddingBottom;
    }

    /* renamed from: -$$Nest$fgetitemPaddingLeft, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13172$$Nest$fgetitemPaddingLeft(TableLayout r0) {
        return r0.itemPaddingLeft;
    }

    /* renamed from: -$$Nest$fgetitemPaddingTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13173$$Nest$fgetitemPaddingTop(TableLayout r0) {
        return r0.itemPaddingTop;
    }

    /* renamed from: -$$Nest$fgetradii, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m13174$$Nest$fgetradii(TableLayout r0) {
        return r0.radii;
    }

    /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m13175$$Nest$fgetrect(TableLayout r0) {
        return r0.rect;
    }

    /* renamed from: -$$Nest$fgettextSelectionHelper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextSelectionHelper.ArticleTextSelectionHelper m13176$$Nest$fgettextSelectionHelper(TableLayout r0) {
        return r0.textSelectionHelper;
    }

    /* renamed from: -$$Nest$smhandleInvalidParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13177$$Nest$smhandleInvalidParams(String r0) {
        handleInvalidParams(r0);
    }

    static {
        UNDEFINED_ALIGNMENT = new AnonymousClass1();
        AnonymousClass2 r0 = new AnonymousClass2();
        LEADING = r0;
        AnonymousClass3 r1 = new AnonymousClass3();
        TRAILING = r1;
        TOP = r0;
        BOTTOM = r1;
        START = r0;
        END = r1;
        LEFT = createSwitchingAlignment(r0);
        RIGHT = createSwitchingAlignment(r1);
        CENTER = new AnonymousClass5();
        BASELINE = new AnonymousClass6();
        FILL = new AnonymousClass7();
    }

    public TableLayout(Context r4, TableLayoutDelegate r5, TextSelectionHelper.ArticleTextSelectionHelper r6) {
        super(r4);
        this.mHorizontalAxis = new Axis(this, true, null);
        this.mVerticalAxis = new Axis(this, false, null);
        this.mOrientation = 0;
        this.mUseDefaultMargins = false;
        this.mAlignmentMode = 1;
        this.mLastLayoutParamsHashCode = 0;
        this.itemPaddingTop = AndroidUtilities.dp(8.0f);
        this.itemPaddingBottom = AndroidUtilities.dp(9.0f);
        this.itemPaddingLeft = AndroidUtilities.dp(12.0f);
        this.fillWidth = true;
        this.naturalRowLocations = new int[0];
        this.cellsToFixHeight = new ArrayList();
        this.rowSpans = new ArrayList();
        this.linePath = new Path();
        this.backgroundPath = new Path();
        this.rect = new RectF();
        this.radii = new float[8];
        this.childrens = new ArrayList();
        this.textSelectionHelper = r6;
        setRowCount(Integer.MIN_VALUE);
        setColumnCount(Integer.MIN_VALUE);
        setOrientation(0);
        setUseDefaultMargins(false);
        setAlignmentMode(1);
        setRowOrderPreserved(true);
        setColumnOrderPreserved(true);
        this.delegate = r5;
        TableA11yHelper r42 = new TableA11yHelper(this, this);
        this.accessibilityHelper = r42;
        ViewCompat.setAccessibilityDelegate(this, r42);
    }

    public static <T> T[] append(T[] r3, T[] r4) {
        T[] r0 = (T[]) ((Object[]) Array.newInstance(r3.getClass().getComponentType(), r3.length + r4.length));
        System.arraycopy(r3, 0, r0, 0, r3.length);
        System.arraycopy(r4, 0, r0, r3.length, r4.length);
        return r0;
    }

    public static boolean canStretch(int r0) {
        if ((r0 & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private static int clip(Interval r1, boolean r2, int r3) {
        int r0 = r1.size();
        if (r3 != 0) goto L5;
        return r0;
    L5:
        if (r2 == false) goto L7;
        int r12 = Math.min(r1.min, r3);
    L9:
        return Math.min(r0, r3 - r12);
    L7:
        r12 = 0;
        goto L9
    }

    private int computeLayoutParamsHashCode() {
        int r0 = getChildCount();
        int r1 = 1;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1 = (r1 * 31) + getChildAt(r2).getLayoutParams().hashCode();
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    private void consistencyCheck() {
        int r0 = this.mLastLayoutParamsHashCode;
        if (r0 != 0) goto L7;
        validateLayoutParams();
        this.mLastLayoutParamsHashCode = computeLayoutParamsHashCode();
        return;
    L7:
        if (r0 == computeLayoutParamsHashCode()) goto L10;
        invalidateStructure();
        consistencyCheck();
        return;
    }

    private static Alignment createSwitchingAlignment(Alignment r1) {
        return new AnonymousClass4(r1);
    }

    private static boolean fits(int[] r2, int r3, int r4, int r5) {
        if (r5 <= r2.length) goto L5;
        return false;
    L5:
        if (r4 >= r5) goto L10;
        if (r2[r4] > r3) goto L8;
        r4 = r4 + 1;
        goto L5
    L8:
        return false;
    L10:
        return true;
    }

    private int getDefaultMargin(Child r5, LayoutParams r6, boolean r7, boolean r8) {
        boolean r1 = false;
        if (this.mUseDefaultMargins == true) goto L5;
        return 0;
    L5:
        if (r7 == false) goto L7;
        Spec r62 = r6.columnSpec;
    L8:
        if (r7 == false) goto L10;
        Axis r0 = this.mHorizontalAxis;
    L11:
        Interval r63 = r62.span;
        if (r7 == true) goto L14;
    L16:
        boolean r3 = false;
    L17:
        if (r3 == r8) goto L22;
        if (r63.min != 0) goto L25;
    L20:
        r1 = true;
    L25:
        return getDefaultMargin(r5, r1, r7, r8);
    L22:
        if (r63.max != r0.getCount()) goto L25;
    L14:
        if (this.isRtl == false) goto L16;
        r3 = true;
        goto L17
    L10:
        r0 = this.mVerticalAxis;
        goto L11
    L7:
        r62 = r6.rowSpec;
        goto L8
    }

    private int getDefaultMargin(Child r1, boolean r2, boolean r3) {
        return this.mDefaultGap / 2;
    }

    private int getDefaultMargin(Child r1, boolean r2, boolean r3, boolean r4) {
        return getDefaultMargin(r1, r3, r4);
    }

    private int getMargin(Child r3, boolean r4, boolean r5) {
        if (this.mAlignmentMode == 1) goto L5;
        if (r4 == false) goto L8;
        Axis r2 = this.mHorizontalAxis;
    L9:
        if (r5 == false) goto L11;
        int[] r22 = r2.getLeadingMargins();
    L12:
        LayoutParams r32 = r3.getLayoutParams();
        if (r4 == false) goto L15;
        Spec r33 = r32.columnSpec;
    L16:
        Interval r34 = r33.span;
        if (r5 == false) goto L19;
        int r35 = r34.min;
    L21:
        return r22[r35];
    L19:
        r35 = r34.max;
        goto L21
    L15:
        r33 = r32.rowSpec;
        goto L16
    L11:
        r22 = r2.getTrailingMargins();
        goto L12
    L8:
        r2 = this.mVerticalAxis;
        goto L9
    L5:
        return getMargin1(r3, r4, r5);
    }

    private int getMeasurement(Child r1, boolean r2) {
        if (r2 == false) goto L6;
        return r1.getMeasuredWidth();
    L6:
        return r1.getMeasuredHeight();
    }

    private int getTotalMargin(Child r3, boolean r4) {
        return getMargin(r3, r4, true) + getMargin(r3, r4, false);
    }

    private static void handleInvalidParams(String r2) {
        throw new IllegalArgumentException(r2 + ". ");
    }

    private void invalidateStructure() {
        this.mLastLayoutParamsHashCode = 0;
        this.mHorizontalAxis.invalidateStructure();
        this.mVerticalAxis.invalidateStructure();
        invalidateValues();
    }

    private void invalidateValues() {
        Axis r0 = this.mHorizontalAxis;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.mVerticalAxis == null) goto L9;
        r0.invalidateValues();
        this.mVerticalAxis.invalidateValues();
        return;
    }

    public static int max2(int[] r3, int r4) {
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r4 = Math.max(r4, r3[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r4;
    }

    private void measureChildWithMargins2(Child r1, int r2, int r3, int r4, int r5, boolean r6) {
        r1.measure(getTotalMargin(r1, true) + r4, getTotalMargin(r1, false) + r5, r6);
    }

    private void measureChildrenWithMargins(int r11, int r12, boolean r13) {
        int r0 = getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L44;
        Child r4 = this.getChildAt(r2);
        LayoutParams r3 = r4.getLayoutParams();
        if (r13 == false) goto L19;
        int r5 = View.MeasureSpec.getSize(r11);
        if (this.colCount != 2) goto L9;
        int r52 = ((int) (r5 / 2.0f)) - (this.itemPaddingLeft * 4);
    L10:
        r4.setTextLayout(this.delegate.createTextLayout(Child.m13178$$Nest$fgetcell(r4), r52));
        if (r4.textLayout == null) goto L17;
        ((ViewGroup.MarginLayoutParams) r3).height = Math.max(this.minimumCellHeight, (r4.textHeight + this.itemPaddingTop) + this.itemPaddingBottom);
        int r53 = r4.textLayout.getEmojiOnlyCount();
        if (r53 <= 0) goto L15;
        int r6 = ((ViewGroup.MarginLayoutParams) r3).height * r53;
    L16:
        ((ViewGroup.MarginLayoutParams) r3).width = r6;
    L18:
        int r7 = ((ViewGroup.MarginLayoutParams) r3).width;
        int r8 = ((ViewGroup.MarginLayoutParams) r3).height;
        TableLayout r32 = this;
        int r54 = r11;
        int r62 = r12;
        r32.measureChildWithMargins2(r4, r54, r62, r7, r8, true);
    L43:
        r2 = r2 + 1;
        TableLayout this = r32;
        r11 = r54;
        r12 = r62;
        goto L3
    L15:
        r6 = (this.itemPaddingLeft * 2) + r4.textWidth;
        goto L16
    L17:
        ((ViewGroup.MarginLayoutParams) r3).width = 0;
        ((ViewGroup.MarginLayoutParams) r3).height = 0;
        goto L18
    L9:
        r52 = (int) (r5 / 1.5f);
        goto L10
    L19:
        r54 = r11;
        r62 = r12;
        if (this.mOrientation != 0) goto L22;
        boolean r112 = true;
    L23:
        if (r112 == false) goto L25;
        Spec r72 = r3.columnSpec;
    L27:
        if (Spec.m13193$$Nest$mgetAbsoluteAlignment(r72, r112) != FILL) goto L42;
        Interval r73 = r72.span;
        if (r112 == false) goto L31;
        Axis r82 = this.mHorizontalAxis;
    L32:
        int[] r83 = r82.getLocations();
        int r74 = (r83[r73.max] - r83[r73.min]) - this.getTotalMargin(r4, r112);
        if (r112 == false) goto L41;
        CellText r113 = r4.textLayout;
        if (r113 == null) goto L37;
        int r114 = r113.getEmojiOnlyCount();
    L38:
        if (r114 <= 0) goto L40;
        int r115 = Math.max(1, Math.round(r74 / r114));
        ((ViewGroup.MarginLayoutParams) r3).height = r115;
        Child.m13187$$Nest$fputfixedHeight(r4, r115);
    L40:
        int r84 = ((ViewGroup.MarginLayoutParams) r3).height;
        r32 = this;
        r32.measureChildWithMargins2(r4, r54, r62, r74, r84, false);
        goto L43
    L37:
        r114 = 0;
        goto L38
    L41:
        int r116 = ((ViewGroup.MarginLayoutParams) r3).width;
        r32 = this;
        r32.measureChildWithMargins2(r4, r54, r62, r116, r74, false);
        goto L43
    L31:
        r82 = this.mVerticalAxis;
        goto L32
    L42:
        r32 = this;
        goto L43
    L25:
        r72 = r3.rowSpec;
        goto L27
    L22:
        r112 = false;
        goto L23
    }

    private static void procrusteanFill(int[] r1, int r2, int r3, int r4) {
        int r0 = r1.length;
        Arrays.fill(r1, Math.min(r2, r0), Math.min(r3, r0), r4);
    }

    private static void setCellGroup(LayoutParams r1, int r2, int r3, int r4, int r5) {
        r1.setRowSpecSpan(new Interval(r2, r3 + r2));
        r1.setColumnSpecSpan(new Interval(r4, r5 + r4));
    }

    public static Spec spec(int r1) {
        return spec(r1, 1);
    }

    public static Spec spec(int r1, int r2) {
        return spec(r1, r2, UNDEFINED_ALIGNMENT);
    }

    public static Spec spec(int r1, int r2, Alignment r3) {
        return spec(r1, r2, r3, 0.0f);
    }

    public static Spec spec(int r7, int r8, Alignment r9, float r10) {
        if (r7 == Integer.MIN_VALUE) goto L5;
        boolean r1 = true;
    L7:
        return new Spec(r1, r7, r8, r9, r10, null);
    L5:
        r1 = false;
        goto L7
    }

    private void updateRenderRowGeometry() {
        int[] r0 = this.naturalRowLocations;
        if (r0.length >= 2) goto L6;
        this.drawingHeight = getMeasuredHeight();
        return;
    L6:
        int r02 = r0.length;
        int r1 = r02 - 1;
        int[] r2 = new int[r1];
        int r4 = 0;
    L7:
        if (r4 >= r1) goto L9;
        int[] r5 = this.naturalRowLocations;
        int r6 = r4 + 1;
        r2[r4] = r5[r6] - r5[r4];
        r4 = r6;
        goto L7
    L9:
        int r42 = 0;
    L11:
        if (r42 >= getChildCount()) goto L29;
        Child r52 = getChildAt(r42);
        CellText r62 = r52.textLayout;
        if (r62 == null) goto L15;
        int r63 = r62.getEmojiOnlyCount();
    L16:
        if (r63 <= 0) goto L28;
        Interval r7 = Child.m13181$$Nest$fgetlayoutParams(r52).rowSpec.span;
        int r8 = Math.max(0, r7.min);
        int r72 = Math.min(r1, r7.max);
        if (r8 >= r72) goto L28;
        int r10 = 0;
        int r9 = r8;
    L22:
        if (r9 >= r72) goto L24;
        r10 = r10 + r2[r9];
        r9 = r9 + 1;
        goto L22
    L24:
        int r53 = Math.max(1, Math.round(Child.m13183$$Nest$fgetmeasuredWidth(r52) / r63)) - r10;
    L25:
        if (r8 >= r72) goto L28;
        if (r53 <= 0) goto L28;
        int r92 = r72 - r8;
        int r102 = ((r53 + r92) - 1) / r92;
        r2[r8] = r2[r8] + r102;
        r53 = r53 - r102;
        r8 = r8 + 1;
    L28:
        r42 = r42 + 1;
        goto L11
    L15:
        r63 = 0;
        goto L16
    L29:
        int[] r03 = new int[r02];
        int r43 = 0;
    L30:
        if (r43 >= r1) goto L32;
        int r54 = r43 + 1;
        r03[r54] = r03[r43] + r2[r43];
        r43 = r54;
        goto L30
    L32:
        this.drawingHeight = r03[r1];
        int r22 = 0;
    L34:
        if (r22 >= getChildCount()) goto L36;
        Child r44 = getChildAt(r22);
        Interval r55 = Child.m13181$$Nest$fgetlayoutParams(r44).rowSpec.span;
        int r64 = Math.max(0, Math.min(r1, r55.min));
        Child.m13192$$Nest$msetRenderVerticalGeometry(r44, r03[r64], r03[Math.max(r64, Math.min(r1, r55.max))]);
        this.delegate.onLayoutChild(r44.textLayout, r44.getTextX(), r44.getTextY());
        r22 = r22 + 1;
        goto L34
    }

    private void validateLayoutParams() {
        if (this.mOrientation != 0) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == false) goto L8;
        Axis r2 = this.mHorizontalAxis;
    L9:
        int r22 = r2.definedCount;
        if (r22 != Integer.MIN_VALUE) goto L13;
        r22 = 0;
    L13:
        int[] r3 = new int[r22];
        int r4 = getChildCount();
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
    L14:
        if (r5 >= r4) goto L43;
        LayoutParams r8 = getChildAt(r5).getLayoutParams();
        if (r0 == false) goto L18;
        Spec r9 = r8.rowSpec;
    L19:
        Interval r10 = r9.span;
        boolean r92 = r9.startDefined;
        int r11 = r10.size();
        if (r92 == false) goto L22;
        r6 = r10.min;
    L22:
        if (r0 == false) goto L24;
        Spec r102 = r8.columnSpec;
    L25:
        Interval r12 = r102.span;
        boolean r103 = r102.startDefined;
        int r13 = clip(r12, r103, r22);
        if (r103 == false) goto L28;
        r7 = r12.min;
    L28:
        if (r22 == 0) goto L39;
        if (r92 == false) goto L31;
        if (r103 == false) goto L31;
    L38:
        procrusteanFill(r3, r7, r7 + r13, r6 + r11);
    L31:
        int r93 = r7 + r13;
        if (fits(r3, r6, r7, r93) == true) goto L38;
        if (r103 == true) goto L34;
        if (r93 <= r22) goto L36;
        r6 = r6 + 1;
        r7 = 0;
        goto L31
    L36:
        r7 = r7 + 1;
        goto L31
    L34:
        r6 = r6 + 1;
    L39:
        if (r0 == false) goto L41;
        setCellGroup(r8, r6, r11, r7, r13);
    L42:
        r7 = r7 + r13;
        r5 = r5 + 1;
        goto L14
    L41:
        setCellGroup(r8, r7, r13, r6, r11);
        goto L42
    L24:
        r102 = r8.rowSpec;
        goto L25
    L18:
        r9 = r8.columnSpec;
        goto L19
    L43:
        return;
    L8:
        r2 = this.mVerticalAxis;
        goto L9
    L5:
        r0 = false;
        goto L6
    }

    public void addChild(int r12, int r13, int r14, int r15) {
        Child r0 = new Child(this, this.childrens.size());
        LayoutParams r1 = new LayoutParams();
        Interval r4 = new Interval(r13, r15 + r13);
        Alignment r5 = FILL;
        r1.rowSpec = new Spec(false, r4, r5, 0.0f, null);
        r1.columnSpec = new Spec(false, new Interval(r12, r14 + r12), r5, 0.0f, null);
        Child.m13188$$Nest$fputlayoutParams(r0, r1);
        r0.rowspan = r13;
        this.childrens.add(r0);
        invalidateStructure();
    }

    public void addChild(TL_iv.pageTableCell r20, int r21, int r22, int r23, int r24) {
        Child r4 = new Child(this, this.childrens.size());
        Child.m13186$$Nest$fputcell(r4, r20);
        LayoutParams r5 = new LayoutParams();
        int r12 = r22 + r24;
        Interval r8 = new Interval(r22, r12);
        Alignment r16 = FILL;
        r5.rowSpec = new Spec(false, r8, r16, 0.0f, null);
        r5.columnSpec = new Spec(false, new Interval(r21, r21 + r23), r16, 1.0f, null);
        Child.m13188$$Nest$fputlayoutParams(r4, r5);
        r4.rowspan = r22;
        this.childrens.add(r4);
        if (r24 <= 1) goto L5;
        this.rowSpans.add(new PointF(r22, r12));
    L5:
        invalidateStructure();
    }

    public void addRows(ArrayList<TL_iv.pageTableRow> r14) {
        int r0 = r14.size();
        int r5 = 0;
        int r8 = 0;
    L3:
        if (r5 >= r0) goto L14;
        TL_iv.pageTableRow r9 = r14.get(r5);
        int r10 = r9.cells.size();
        int r4 = 0;
        int r11 = 0;
    L5:
        if (r11 >= r10) goto L13;
        if (r4 >= 1000) goto L13;
        TL_iv.pageTableCell r3 = r9.cells.get(r11);
        int r2 = Math.max(1, Math.min(r3.colspan, 1000 - r4));
        int r7 = Math.max(1, Math.min(r3.rowspan, r0 - r5));
        if (r3.text == null) goto L11;
        int r6 = r2;
        TableLayout r22 = this;
        r22.addChild(r3, r4, r5, r6, r7);
    L12:
        r4 = r4 + r6;
        r11 = r11 + 1;
        TableLayout this = r22;
        goto L5
    L11:
        r6 = r2;
        r22 = this;
        r22.addChild(r4, r5, r6, r7);
    L13:
        r8 = Math.max(r8, r4);
        r5 = r5 + 1;
        this = this;
        goto L3
    L14:
        this.setColumnCount(r8);
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent r2) {
        TableA11yHelper r0 = this.accessibilityHelper;
        if (r0 == null) goto L9;
        if (r0.dispatchHoverEvent(r2) == false) goto L9;
        return true;
    L9:
        return super.dispatchHoverEvent(r2);
    }

    public int getAlignmentMode() {
        return this.mAlignmentMode;
    }

    public Child getChildAt(int r2) {
        if (r2 >= 0) goto L4;
        return null;
    L4:
        if (r2 < this.childrens.size()) goto L7;
        return null;
    L7:
        return this.childrens.get(r2);
    }

    public int getChildCount() {
        return this.childrens.size();
    }

    public int getColumnCount() {
        return this.mHorizontalAxis.getCount();
    }

    public int getMargin1(Child r4, boolean r5, boolean r6) {
        LayoutParams r0 = r4.getLayoutParams();
        if (r5 == false) goto L7;
        if (r6 == false) goto L6;
        int r1 = ((ViewGroup.MarginLayoutParams) r0).leftMargin;
    L11:
        if (r1 == Integer.MIN_VALUE) goto L13;
        return r1;
    L13:
        return getDefaultMargin(r4, r0, r5, r6);
    L6:
        r1 = ((ViewGroup.MarginLayoutParams) r0).rightMargin;
        goto L11
    L7:
        if (r6 == false) goto L9;
        r1 = ((ViewGroup.MarginLayoutParams) r0).topMargin;
        goto L11
    L9:
        r1 = ((ViewGroup.MarginLayoutParams) r0).bottomMargin;
        goto L11
    }

    public final int getMeasurementIncludingMargin(Child r2, boolean r3) {
        return getMeasurement(r2, r3) + getTotalMargin(r2, r3);
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getRenderHeight() {
        return this.drawingHeight;
    }

    public int getRowCount() {
        return this.mVerticalAxis.getCount();
    }

    public boolean getUseDefaultMargins() {
        return this.mUseDefaultMargins;
    }

    @Override // android.view.View
    public void onDraw(Canvas r4) {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        getChildAt(r1).draw(r4, this);
        r1 = r1 + 1;
        goto L3
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        consistencyCheck();
    }

    @Override // android.view.View
    public void onMeasure(int r31, int r32) {
        TableLayout r1 = this;
        r1.consistencyCheck();
        r1.invalidateValues();
        r1.colCount = 0;
        int r3 = r1.getChildCount();
        int r4 = 0;
    L3:
        if (r4 >= r3) goto L5;
        r1.colCount = Math.max(r1.colCount, Child.m13181$$Nest$fgetlayoutParams(r1.getChildAt(r4)).columnSpec.span.max);
        r4 = r4 + 1;
        goto L3
    L5:
        boolean r7 = true;
        r1.measureChildrenWithMargins(r31, r32, true);
        if (r1.mOrientation != 0) goto L11;
        int r33 = r1.mHorizontalAxis.getMeasure(r31);
        if (r1.fillWidth == false) goto L10;
        r33 = Math.max(r33, View.MeasureSpec.getSize(r31));
        r1.mHorizontalAxis.layout(r33);
    L10:
        r1.measureChildrenWithMargins(r31, r32, false);
        int r0 = r1.mVerticalAxis.getMeasure(r32);
    L12:
        int r8 = Math.max(r0, r1.getSuggestedMinimumHeight());
        r1.setMeasuredDimension(r33, r8);
        r1.mHorizontalAxis.layout(r33);
        r1.mVerticalAxis.layout(r8);
        int[] r9 = r1.mHorizontalAxis.getLocations();
        int[] r10 = r1.mVerticalAxis.getLocations();
        int[] r11 = Arrays.copyOf(r10, r10.length);
        r1.cellsToFixHeight.clear();
        int r12 = r9[r9.length - 1];
        int r13 = r1.getChildCount();
        int r14 = 0;
    L13:
        if (r14 >= r13) goto L44;
        Child r2 = r1.getChildAt(r14);
        LayoutParams r02 = r2.getLayoutParams();
        Spec r34 = r02.columnSpec;
        Spec r03 = r02.rowSpec;
        Interval r42 = r34.span;
        Interval r5 = r03.span;
        int r15 = r9[r42.min];
        int r6 = r10[r5.min];
        int r16 = r9[r42.max] - r15;
        int r17 = r10[r5.max] - r6;
        int r43 = r1.getMeasurement(r2, r7);
        int r62 = r1.getMeasurement(r2, false);
        Alignment r35 = Spec.m13193$$Nest$mgetAbsoluteAlignment(r34, r7);
        Alignment r04 = Spec.m13193$$Nest$mgetAbsoluteAlignment(r03, false);
        Bounds r52 = r1.mHorizontalAxis.getGroupBounds().getValue(r14);
        Bounds r72 = r1.mVerticalAxis.getGroupBounds().getValue(r14);
        int r20 = r35.getGravityOffset(r2, r16 - r52.size(true));
        int r21 = r04.getGravityOffset(r2, r17 - r72.size(true));
        int r22 = r1.getMargin(r2, true, true);
        int r23 = r1.getMargin(r2, false, true);
        int r24 = r1.getMargin(r2, true, false);
        int r25 = r22 + r24;
        int r26 = r23 + r1.getMargin(r2, false, false);
        int r28 = r52.getOffset(r1, r2, r35, r43 + r25, true);
        r1 = this;
        int r322 = r8;
        int r05 = r72.getOffset(r1, r2, r04, r62 + r26, false);
        int r44 = r35.getSizeInCell(r2, r43, r16 - r25);
        int r36 = r04.getSizeInCell(r2, r62, r17 - r26);
        int r152 = (r15 + r20) + r28;
        if (r1.isRtl == true) goto L18;
        int r222 = r22 + r152;
    L17:
        int r53 = r222;
        int r63 = ((r6 + r21) + r05) + r23;
        if (Child.m13178$$Nest$fgetcell(r2) == null) goto L43;
        if (r44 == r2.getMeasuredWidth()) goto L24;
    L25:
        int r06 = 0;
        r2.measure(r44, r36, false);
    L29:
        if (Child.m13179$$Nest$fgetfixedHeight(r2) == 0) goto L43;
        if (Child.m13179$$Nest$fgetfixedHeight(r2) == r36) goto L43;
        if ((Child.m13181$$Nest$fgetlayoutParams(r2).rowSpec.span.max - Child.m13181$$Nest$fgetlayoutParams(r2).rowSpec.span.min) > 1) goto L43;
        int r73 = r1.rowSpans.size();
        int r82 = r06;
    L35:
        if (r82 >= r73) goto L42;
        PointF r153 = r1.rowSpans.get(r82);
        if (r153.x > Child.m13181$$Nest$fgetlayoutParams(r2).rowSpec.span.min) goto L41;
        if (r153.y > Child.m13181$$Nest$fgetlayoutParams(r2).rowSpec.span.min) goto L43;
    L41:
        r82 = r82 + 1;
        goto L35
    L42:
        r1.cellsToFixHeight.add(r2);
        goto L43
    L24:
        if (r36 != r2.getMeasuredHeight()) goto L25;
        r06 = 0;
    L43:
        r2.layout(r53, r63, r44 + r53, r36 + r63);
        r14 = r14 + 1;
        r8 = r322;
        r7 = true;
        goto L13
    L18:
        r222 = ((r12 - r44) - r24) - r152;
        goto L17
    L44:
        int r07 = r1.cellsToFixHeight.size();
        int r54 = 0;
    L45:
        if (r54 >= r07) goto L96;
        Child r27 = r1.cellsToFixHeight.get(r54);
        int r37 = Child.m13182$$Nest$fgetmeasuredHeight(r27) - Child.m13179$$Nest$fgetfixedHeight(r27);
        int r45 = Child.m13180$$Nest$fgetindex(r27) + 1;
        int r64 = r1.childrens.size();
    L47:
        if (r45 >= r64) goto L57;
        Child r74 = r1.childrens.get(r45);
        if (Child.m13181$$Nest$fgetlayoutParams(r27).rowSpec.span.min != Child.m13181$$Nest$fgetlayoutParams(r74).rowSpec.span.min) goto L57;
        if (Child.m13179$$Nest$fgetfixedHeight(r27) < Child.m13179$$Nest$fgetfixedHeight(r74)) goto L52;
        int r92 = Child.m13182$$Nest$fgetmeasuredHeight(r74) - Child.m13179$$Nest$fgetfixedHeight(r74);
        if (r92 <= 0) goto L56;
        r37 = Math.min(r37, r92);
    L56:
        r45 = r45 + 1;
        goto L47
    L52:
        boolean r46 = true;
    L58:
        if (r46 == true) goto L70;
        int r65 = Child.m13180$$Nest$fgetindex(r27) - 1;
    L60:
        if (r65 < 0) goto L70;
        Child r75 = r1.childrens.get(r65);
        if (Child.m13181$$Nest$fgetlayoutParams(r27).rowSpec.span.min != Child.m13181$$Nest$fgetlayoutParams(r75).rowSpec.span.min) goto L70;
        if (Child.m13179$$Nest$fgetfixedHeight(r27) < Child.m13179$$Nest$fgetfixedHeight(r75)) goto L65;
        int r93 = Child.m13182$$Nest$fgetmeasuredHeight(r75) - Child.m13179$$Nest$fgetfixedHeight(r75);
        if (r93 <= 0) goto L69;
        r37 = Math.min(r37, r93);
    L69:
        r65 = r65 - 1;
        goto L60
    L65:
        r46 = true;
    L70:
        if (r46 == true) goto L95;
        r27.setFixedHeight(Child.m13179$$Nest$fgetfixedHeight(r27));
        r8 = r8 - r37;
        int r47 = Child.m13181$$Nest$fgetlayoutParams(r27).rowSpec.span.min;
    L73:
        r47 = r47 + 1;
        if (r47 >= r11.length) goto L76;
        r11[r47] = r11[r47] - r37;
        goto L73
    L76:
        int r48 = r1.childrens.size();
        int r66 = r54;
        int r55 = 0;
    L77:
        if (r55 >= r48) goto L94;
        Child r76 = r1.childrens.get(r55);
        if (r27 == r76) goto L93;
        if (Child.m13181$$Nest$fgetlayoutParams(r27).rowSpec.span.min != Child.m13181$$Nest$fgetlayoutParams(r76).rowSpec.span.min) goto L91;
        if (Child.m13179$$Nest$fgetfixedHeight(r76) == Child.m13182$$Nest$fgetmeasuredHeight(r76)) goto L89;
        r1.cellsToFixHeight.remove(r76);
        if (Child.m13180$$Nest$fgetindex(r76) >= Child.m13180$$Nest$fgetindex(r27)) goto L88;
        r66 = r66 - 1;
    L88:
        r07 = r07 - 1;
    L89:
        Child.m13189$$Nest$fputmeasuredHeight(r76, Child.m13182$$Nest$fgetmeasuredHeight(r76) - r37);
        r76.measure(Child.m13183$$Nest$fgetmeasuredWidth(r76), Child.m13182$$Nest$fgetmeasuredHeight(r76), true);
        goto L93
    L91:
        if (Child.m13181$$Nest$fgetlayoutParams(r27).rowSpec.span.min >= Child.m13181$$Nest$fgetlayoutParams(r76).rowSpec.span.min) goto L93;
        r76.y -= r37;
    L93:
        r55 = r55 + 1;
        goto L77
    L94:
        r54 = r66;
    L95:
        r54 = r54 + 1;
    L57:
        r46 = false;
        goto L58
    L96:
        int r08 = r1.getChildCount();
        int r67 = 0;
    L97:
        if (r67 >= r08) goto L99;
        Child r29 = r1.getChildAt(r67);
        r1.delegate.onLayoutChild(r29.textLayout, r29.getTextX(), r29.getTextY());
        Child.m13190$$Nest$mcaptureNaturalHorizontalGeometry(r29);
        r67 = r67 + 1;
        goto L97
    L99:
        r1.drawingWidth = r12;
        r1.drawingHeight = r8;
        r1.naturalRowLocations = r11;
        r1.setMeasuredDimension(r12, r8);
        return;
    L11:
        int r38 = r1.mVerticalAxis.getMeasure(r32);
        r1.measureChildrenWithMargins(r31, r32, false);
        r33 = r1.mHorizontalAxis.getMeasure(r31);
        r0 = r38;
        goto L12
    }

    public void removeAllChildrens() {
        this.childrens.clear();
        this.rowSpans.clear();
        invalidateStructure();
    }

    @Override // android.view.View
    public void requestLayout() {
        super.requestLayout();
        invalidateValues();
    }

    public void setAlignmentMode(int r1) {
        this.mAlignmentMode = r1;
        requestLayout();
    }

    public void setCellPadding(int r2, int r3, int r4) {
        if (this.itemPaddingLeft == r2) goto L5;
    L9:
        this.itemPaddingLeft = r2;
        this.itemPaddingTop = r3;
        this.itemPaddingBottom = r4;
        requestLayout();
        return;
    L5:
        if (this.itemPaddingTop != r3) goto L9;
        if (this.itemPaddingBottom != r4) goto L9;
    }

    public void setColumnCount(int r2) {
        this.mHorizontalAxis.setCount(r2);
        invalidateStructure();
        requestLayout();
    }

    public void setColumnOrderPreserved(boolean r2) {
        this.mHorizontalAxis.setOrderPreserved(r2);
        invalidateStructure();
        requestLayout();
    }

    public void setDrawLines(boolean r1) {
        this.drawLines = r1;
    }

    public void setFillWidth(boolean r2) {
        if (this.fillWidth != r2) goto L5;
        return;
    L5:
        this.fillWidth = r2;
        requestLayout();
    }

    public void setMinimumCellHeight(int r1) {
        this.minimumCellHeight = r1;
        requestLayout();
    }

    public void setOrientation(int r2) {
        if (this.mOrientation == r2) goto L6;
        this.mOrientation = r2;
        invalidateStructure();
        requestLayout();
        return;
    }

    public void setRenderWidth(int r7) {
        int r0 = getMeasuredWidth();
        this.drawingWidth = Math.max(r0, r7);
        int r72 = 0;
    L4:
        if (r72 >= getChildCount()) goto L13;
        Child r1 = getChildAt(r72);
        if (r0 > 0) goto L8;
    L11:
        Child.m13191$$Nest$msetRenderHorizontalGeometry(r1, Child.m13185$$Nest$fgetnaturalX(r1), Child.m13185$$Nest$fgetnaturalX(r1) + Child.m13184$$Nest$fgetnaturalWidth(r1));
    L12:
        r72 = r72 + 1;
        goto L4
    L8:
        if (this.drawingWidth == r0) goto L11;
        float r3 = r0;
        Child.m13191$$Nest$msetRenderHorizontalGeometry(r1, Math.round((Child.m13185$$Nest$fgetnaturalX(r1) * this.drawingWidth) / r3), Math.round(((Child.m13185$$Nest$fgetnaturalX(r1) + Child.m13184$$Nest$fgetnaturalWidth(r1)) * this.drawingWidth) / r3));
        goto L12
    L13:
        updateRenderRowGeometry();
        invalidate();
    }

    public void setRowCount(int r2) {
        this.mVerticalAxis.setCount(r2);
        invalidateStructure();
        requestLayout();
    }

    public void setRowOrderPreserved(boolean r2) {
        this.mVerticalAxis.setOrderPreserved(r2);
        invalidateStructure();
        requestLayout();
    }

    public void setRtl(boolean r1) {
        this.isRtl = r1;
    }

    public void setStriped(boolean r1) {
        this.isStriped = r1;
    }

    public void setUseDefaultMargins(boolean r1) {
        this.mUseDefaultMargins = r1;
        requestLayout();
    }
}
