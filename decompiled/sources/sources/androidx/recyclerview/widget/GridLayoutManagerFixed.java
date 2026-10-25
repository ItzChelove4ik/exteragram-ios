package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
public abstract class GridLayoutManagerFixed extends GridLayoutManager {
    private ArrayList<View> additionalViews;
    private boolean canScrollVertically;

    public GridLayoutManagerFixed(Context r1, int r2, int r3, boolean r4) {
        super(r1, r2, r3, r4);
        this.additionalViews = new ArrayList(4);
        this.canScrollVertically = true;
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager
    public int[] calculateItemBorders(int[] r3, int r4, int r5) {
        int r2 = 1;
        if (r3 != null) goto L5;
    L8:
        r3 = new int[r4 + 1];
    L9:
        r3[0] = 0;
    L10:
        if (r2 > r4) goto L12;
        r3[r2] = (int) Math.ceil((r2 / r4) * r5);
        r2 = r2 + 1;
        goto L10
    L12:
        return r3;
    L5:
        if (r3.length != (r4 + 1)) goto L8;
        if (r3[r3.length - 1] == r5) goto L9;
        goto L8
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.canScrollVertically;
    }

    public abstract boolean hasSiblingChild(int r1);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v36 */
    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager
    public void layoutChunk(RecyclerView.Recycler r20, RecyclerView.State r21, LinearLayoutManager.LayoutState r22, LinearLayoutManager.LayoutChunkResult r23) {
        GridLayoutManagerFixed r0 = this;
        RecyclerView.Recycler r6 = r20;
        int r10 = r0.mOrientationHelper.getModeInOther();
        boolean r11 = false;
        ?? r12 = 1;
        if (r22.mItemDirection != 1) goto L5;
        boolean r13 = true;
    L6:
        r23.mConsumed = 0;
        int r1 = r22.mCurrentPosition;
        int r14 = -1;
        if (r0.mShouldReverseLayout == true) goto L9;
    L28:
        ?? r15 = true;
    L29:
        if (r15 == false) goto L131;
        int r16 = r0.mSpanCount;
        ?? r152 = ((r0.additionalViews.isEmpty() ? 1 : 0) ^ r12) == true ? 1 : 0;
        int r2 = r11 ? 1 : 0;
    L32:
        if (r2 >= r0.mSpanCount) goto L51;
        if (r22.hasMore(r21) == false) goto L51;
        if (r16 <= 0) goto L51;
        int r3 = r22.mCurrentPosition;
        r16 = r16 - r0.getSpanSize(r6, r21, r3);
        if (r16 < 0) goto L51;
        if (r0.additionalViews.isEmpty() == true) goto L42;
        View r4 = r0.additionalViews.get(r11 ? 1 : 0);
        r0.additionalViews.remove(r11 ? 1 : 0);
        r22.mCurrentPosition -= r12;
    L43:
        if (r4 == null) goto L51;
        r0.mSet[r2] = r4;
        r2 = r2 + 1;
        if (r22.mLayoutDirection != r14) goto L32;
        if (r16 > 0) goto L32;
        if (r0.hasSiblingChild(r3) == false) goto L32;
        r152 = r12;
        goto L32
    L42:
        r4 = r22.next(r6);
    L51:
        if (r2 == 0) goto L52;
        r0.assignSpans(r6, r21, r2, r13);
        float r17 = 0.0f;
        int r32 = r11 ? 1 : 0;
        int r42 = r32;
    L55:
        if (r32 >= r2) goto L71;
        View r5 = r0.mSet[r32];
        if (r22.mScrapList != null) goto L61;
        if (r13 == false) goto L60;
        r0.addView(r5);
    L64:
        r0.calculateItemDecorationsForChild(r5, r0.mDecorInsets);
        r0.measureChild(r5, r10, r11);
        int r142 = r0.mOrientationHelper.getDecoratedMeasurement(r5);
        if (r142 <= r42) goto L67;
        r42 = r142;
    L67:
        GridLayoutManager.LayoutParams r143 = (GridLayoutManager.LayoutParams) r5.getLayoutParams();
        float r52 = (r0.mOrientationHelper.getDecoratedMeasurementInOther(r5) * 1.0f) / r143.mSpanSize;
        if (r52 <= r17) goto L70;
        r17 = r52;
    L70:
        r32 = r32 + 1;
        goto L55
    L60:
        r0.addView(r5, r11 ? 1 : 0);
        goto L64
    L61:
        if (r13 == false) goto L63;
        r0.addDisappearingView(r5);
        goto L64
    L63:
        r0.addDisappearingView(r5, r11 ? 1 : 0);
        goto L64
    L71:
        int r18 = r11 ? 1 : 0;
    L72:
        View[] r33 = r0.mSet;
        if (r18 >= r2) goto L79;
        View r34 = r33[r18];
        if (r0.mOrientationHelper.getDecoratedMeasurement(r34) == r42) goto L77;
        GridLayoutManager.LayoutParams r53 = (GridLayoutManager.LayoutParams) r34.getLayoutParams();
        Rect r122 = r53.mDecorInsets;
        int r144 = ((r122.top + r122.bottom) + ((ViewGroup.MarginLayoutParams) r53).topMargin) + ((ViewGroup.MarginLayoutParams) r53).bottomMargin;
        int r162 = r18;
        boolean r62 = false;
        r0.measureChildWithDecorationsAndMargin(r34, RecyclerView.LayoutManager.getChildMeasureSpec(r0.mCachedBorders[r53.mSpanSize], TLObject.FLAG_30, ((r122.left + r122.right) + ((ViewGroup.MarginLayoutParams) r53).leftMargin) + ((ViewGroup.MarginLayoutParams) r53).rightMargin, ((ViewGroup.MarginLayoutParams) r53).width, false), View.MeasureSpec.makeMeasureSpec(r42 - r144, TLObject.FLAG_30), true);
    L78:
        r18 = r162 + 1;
        r11 = r62;
        goto L72
    L77:
        r162 = r18;
        r62 = r11;
        goto L78
    L79:
        int r63 = r11;
        boolean r19 = r0.shouldLayoutChildFromOpositeSide(r33[r63]);
        if (r19 == false) goto L84;
        int r54 = -1;
        if (r22.mLayoutDirection != (-1)) goto L85;
    L88:
        int r110 = r22.mLayoutDirection;
        int r35 = r22.mOffset;
        if (r110 != r54) goto L91;
        int r36 = r35 - r23.mConsumed;
        int r55 = r36;
        int r37 = r36 - r42;
        int r111 = r63;
    L92:
        int r112 = r2 - 1;
    L93:
        if (r112 < 0) goto L107;
        View r24 = r0.mSet[r112];
        GridLayoutManager.LayoutParams r123 = (GridLayoutManager.LayoutParams) r24.getLayoutParams();
        int r145 = r0.mOrientationHelper.getDecoratedMeasurementInOther(r24);
        if (r22.mLayoutDirection != 1) goto L97;
        r111 = r111 - r145;
    L97:
        int r146 = r145 + r111;
        int r25 = r111;
        r0 = this;
        int r64 = r42;
        r0.layoutDecoratedWithMargins(r24, r25, r37, r146, r55);
        if (r22.mLayoutDirection != (-1)) goto L100;
        r111 = r146;
    L102:
        if (r123.isItemRemoved() == false) goto L104;
    L105:
        r23.mIgnoreConsumed = true;
    L106:
        r23.mFocusable |= r24.hasFocusable();
        r112 = r112 - 1;
        r42 = r64;
        goto L93
    L104:
        if (r123.isItemChanged() == false) goto L106;
    L100:
        r111 = r25;
        goto L102
    L107:
        int r163 = r42;
    L130:
        r23.mConsumed += r163;
        Arrays.fill(r0.mSet, null);
        r6 = r20;
        r12 = 1;
        r15 = r152;
        r11 = false;
        r14 = -1;
        goto L29
    L91:
        int r113 = r23.mConsumed + r35;
        int r38 = r113 + r42;
        r37 = r113;
        r111 = r0.getWidth();
        r55 = r38;
    L85:
        if (r19 == false) goto L87;
    L109:
        int r65 = r42;
        int r114 = r22.mLayoutDirection;
        int r39 = r22.mOffset;
        if (r114 != (-1)) goto L113;
        int r310 = r39 - r23.mConsumed;
        int r115 = r310 - r65;
        int r43 = r0.getWidth();
    L112:
        int r56 = r310;
        int r311 = r115;
        int r116 = 0;
    L115:
        if (r116 >= r2) goto L129;
        View r117 = r0.mSet[r116];
        GridLayoutManager.LayoutParams r124 = (GridLayoutManager.LayoutParams) r117.getLayoutParams();
        int r147 = r0.mOrientationHelper.getDecoratedMeasurementInOther(r117);
        int r164 = r65;
        if (r22.mLayoutDirection != (-1)) goto L119;
        r43 = r43 - r147;
    L119:
        int r148 = r147 + r43;
        int r149 = r2;
        int r26 = r43;
        r43 = r148;
        r0 = this;
        r0.layoutDecoratedWithMargins(r117, r26, r311, r43, r56);
        if (r22.mLayoutDirection == 1) goto L124;
        r43 = r26;
    L124:
        if (r124.isItemRemoved() == false) goto L126;
    L127:
        r23.mIgnoreConsumed = true;
    L128:
        r23.mFocusable |= r117.hasFocusable();
        r116 = r116 + 1;
        r2 = r149;
        r65 = r164;
        goto L115
    L126:
        if (r124.isItemChanged() == false) goto L128;
    L129:
        r163 = r65;
        goto L130
    L113:
        r115 = r23.mConsumed + r39;
        r310 = r115 + r65;
        r43 = 0;
        goto L112
    L87:
        if (r22.mLayoutDirection != 1) goto L109;
    L84:
        r54 = -1;
        goto L85
    L52:
        r23.mFinished = r12;
        return;
    L131:
        return;
    L9:
        if (r22.mLayoutDirection == (-1)) goto L28;
        if (r0.hasSiblingChild(r1) == false) goto L28;
        if (r0.findViewByPosition(r22.mCurrentPosition + 1) != null) goto L28;
        boolean r27 = r0.hasSiblingChild(r22.mCurrentPosition + 1);
        int r312 = r22.mCurrentPosition;
        if (r27 == false) goto L17;
        r22.mCurrentPosition = r312 + 3;
    L18:
        int r28 = r22.mCurrentPosition;
        int r313 = r28;
    L19:
        if (r313 <= r1) goto L27;
        View r44 = r22.next(r6);
        if (r44 == null) goto L26;
        r0.additionalViews.add(r44);
        if (r313 == r28) goto L26;
        r0.calculateItemDecorationsForChild(r44, r0.mDecorInsets);
        r0.measureChild(r44, r10, false);
        int r45 = r0.mOrientationHelper.getDecoratedMeasurement(r44);
        r22.mOffset -= r45;
        r22.mAvailable += r45;
    L26:
        r313 = r313 - 1;
        goto L19
    L27:
        r22.mCurrentPosition = r28;
        goto L28
    L17:
        r22.mCurrentPosition = r312 + 2;
        goto L18
    L5:
        r13 = false;
        goto L6
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager
    public void measureChild(View r7, int r8, boolean r9) {
        GridLayoutManager.LayoutParams r0 = (GridLayoutManager.LayoutParams) r7.getLayoutParams();
        Rect r1 = r0.mDecorInsets;
        int r2 = ((r1.top + r1.bottom) + ((ViewGroup.MarginLayoutParams) r0).topMargin) + ((ViewGroup.MarginLayoutParams) r0).bottomMargin;
        int r3 = ((r1.left + r1.right) + ((ViewGroup.MarginLayoutParams) r0).leftMargin) + ((ViewGroup.MarginLayoutParams) r0).rightMargin;
        measureChildWithDecorationsAndMargin(r7, RecyclerView.LayoutManager.getChildMeasureSpec(this.mCachedBorders[r0.mSpanSize], r8, r3, ((ViewGroup.MarginLayoutParams) r0).width, false), RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), getHeightMode(), r2, ((ViewGroup.MarginLayoutParams) r0).height, true), r9);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public void recycleViewsFromStart(RecyclerView.Recycler r6, int r7, int r8) {
        if (r7 < 0) goto L32;
        int r82 = getChildCount();
        if (this.mShouldReverseLayout == false) goto L16;
        int r83 = r82 - 1;
        int r0 = r83;
    L7:
        if (r0 < 0) goto L33;
        View r1 = getChildAt(r0);
        if ((r1.getBottom() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) r1.getLayoutParams())).bottomMargin) > r7) goto L14;
        if ((r1.getTop() + r1.getHeight()) > r7) goto L14;
        r0 = r0 - 1;
    L14:
        recycleChildren(r6, r83, r0);
        return;
    L33:
        return;
    L16:
        int r12 = 0;
    L17:
        if (r12 >= r82) goto L34;
        View r2 = getChildAt(r12);
        if ((r2.getBottom() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) r2.getLayoutParams())).bottomMargin) > r7) goto L24;
        if ((r2.getTop() + r2.getHeight()) > r7) goto L24;
        r12 = r12 + 1;
    L24:
        recycleChildren(r6, 0, r12);
        return;
    L34:
        return;
    }

    public void setCanScrollVertically(boolean r1) {
        this.canScrollVertically = r1;
    }

    public abstract boolean shouldLayoutChildFromOpositeSide(View r1);
}
