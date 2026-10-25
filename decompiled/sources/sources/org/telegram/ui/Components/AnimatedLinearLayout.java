package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.RectF;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import me.vkryl.android.animator.ListAnimator;
import me.vkryl.core.lambda.Destroyable;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes3.dex */
public abstract class AnimatedLinearLayout extends LinearLayout {
    private static final Comparator<Holder> comparator = null;
    private final ListAnimator.Callback callback;
    private float lastAnimatedHeight;
    private final ListAnimator<Holder> listAnimator;
    private Runnable onAnimatedHeightChanged;
    private boolean skipNextAnimation;
    private int totalHeight;
    private int totalWidth;
    private final HashMap<View, Holder> viewHolders;
    private final ArrayList<Holder> visibleHolders;

    public static class Holder implements ListAnimator.Measurable, Destroyable {
        private boolean hasInAnimator;
        private boolean isVisible;
        private int order;
        private int priority;
        private String tag;
        public final View view;

        /* renamed from: -$$Nest$fgethasInAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m8808$$Nest$fgethasInAnimator(Holder r0) {
            return r0.hasInAnimator;
        }

        /* renamed from: -$$Nest$fgetisVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m8809$$Nest$fgetisVisible(Holder r0) {
            return r0.isVisible;
        }

        /* renamed from: -$$Nest$fgetorder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m8810$$Nest$fgetorder(Holder r0) {
            return r0.order;
        }

        /* renamed from: -$$Nest$fgetpriority, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m8811$$Nest$fgetpriority(Holder r0) {
            return r0.priority;
        }

        /* renamed from: -$$Nest$fputhasInAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8812$$Nest$fputhasInAnimator(Holder r0, boolean r1) {
            r0.hasInAnimator = r1;
        }

        /* renamed from: -$$Nest$fputisVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8813$$Nest$fputisVisible(Holder r0, boolean r1) {
            r0.isVisible = r1;
        }

        /* renamed from: -$$Nest$fputorder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8814$$Nest$fputorder(Holder r0, int r1) {
            r0.order = r1;
        }

        /* renamed from: -$$Nest$fputpriority, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8815$$Nest$fputpriority(Holder r0, int r1) {
            r0.priority = r1;
        }

        /* renamed from: -$$Nest$fputtag, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m8816$$Nest$fputtag(Holder r0, String r1) {
            r0.tag = r1;
        }

        public Holder(View r1) {
            this.view = r1;
        }

        public boolean equals(Object r2) {
            if ((r2 instanceof Holder) == true) goto L5;
            return false;
        L5:
            return this.view.equals(((Holder) r2).view);
        }

        @Override // me.vkryl.android.animator.ListAnimator.Measurable
        public int getHeight() {
            return this.view.getMeasuredHeight();
        }

        @Override // me.vkryl.android.animator.ListAnimator.Measurable
        public int getWidth() {
            return this.view.getMeasuredWidth();
        }

        public int hashCode() {
            return this.view.hashCode();
        }

        @Override // me.vkryl.core.lambda.Destroyable
        public void performDestroy() {
            if (this.isVisible == true) goto L5;
            this.view.setVisibility(8);
        L5:
            this.hasInAnimator = false;
        }
    }

    public static /* synthetic */ int $r8$lambda$XQxmrRbKZheFMgoel3bjqleu990(Holder r0) {
        return Holder.m8810$$Nest$fgetorder(r0);
    }

    public static /* synthetic */ void $r8$lambda$dv9EgLjAtJ_7fZUjlR4p9jhEXfM(AnimatedLinearLayout r0, ListAnimator r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ int $r8$lambda$xrzkjOYEt3Pkxnx3Mv6lvfDyd9Q(Holder r0) {
        return Holder.m8811$$Nest$fgetpriority(r0);
    }

    static {
        comparator = Comparator.comparingInt(new AnimatedLinearLayout$$ExternalSyntheticLambda1()).thenComparingInt(new AnimatedLinearLayout$$ExternalSyntheticLambda2());
    }

    public AnimatedLinearLayout(Context r5) {
        super(r5);
        this.viewHolders = new HashMap();
        this.visibleHolders = new ArrayList();
        AnimatedLinearLayout$$ExternalSyntheticLambda0 r52 = new AnimatedLinearLayout$$ExternalSyntheticLambda0(this);
        this.callback = r52;
        this.listAnimator = new ListAnimator(r52, CubicBezierInterpolator.EASE_OUT_QUINT, 420);
    }

    private void checkViewsVisibility() {
        Iterator<ListAnimator.Entry<Holder>> r0 = this.listAnimator.iterator();
    L4:
        if (r0.hasNext() == false) goto L10;
        ListAnimator.Entry<Holder> r1 = r0.next();
        View r2 = r1.item.view;
        RectF r3 = r1.getRectF();
        if (getOrientation() != 1) goto L8;
        r2.setTranslationY((getPaddingTop() + r3.top) - r2.getTop());
    L9:
        setChildVisibilityFactor(r2, r1.getVisibility());
        goto L4
    L8:
        r2.setTranslationX((getPaddingLeft() + r3.left) - r2.getLeft());
        goto L9
    L10:
        float r02 = getMetadata().getTotalHeight();
        if (this.lastAnimatedHeight == r02) goto L19;
        this.lastAnimatedHeight = r02;
        Runnable r6 = this.onAnimatedHeightChanged;
        if (r6 == null) goto L20;
        r6.run();
        return;
    L20:
        return;
    }

    private /* synthetic */ void lambda$new$0(ListAnimator r1) {
        checkViewsVisibility();
        onItemsChanged();
    }

    public final void calculateTotalSizesAfterMeasure() {
        int r0 = 0;
        this.totalHeight = 0;
        this.totalWidth = 0;
        int r1 = getChildCount();
    L3:
        if (r0 >= r1) goto L11;
        View r2 = getChildAt(r0);
        Holder r3 = this.viewHolders.get(r2);
        if (r2.getVisibility() != 0) goto L10;
        if (r3 == null) goto L10;
        if (Holder.m8809$$Nest$fgetisVisible(r3) == false) goto L10;
        this.totalWidth += r2.getMeasuredWidth();
        this.totalHeight += r2.getMeasuredHeight();
    L10:
        r0 = r0 + 1;
        goto L3
    }

    public float getAnimatedHeightWithPadding() {
        return getAnimatedHeightWithPadding(getPaddingTop() + getPaddingBottom());
    }

    public float getAnimatedHeightWithPadding(float r2) {
        return getMetadata().getTotalHeight() + (r2 * getMetadata().getTotalVisibility());
    }

    public int getEntriesCount() {
        return this.listAnimator.size();
    }

    public ListAnimator.Entry<Holder> getEntry(int r1) {
        return this.listAnimator.getEntry(r1);
    }

    public ListAnimator.Metadata getMetadata() {
        return this.listAnimator.getMetadata();
    }

    public int getSumHeightOfAllVisibleChild() {
        return this.totalHeight;
    }

    public int getSumWidthOfAllVisibleChild() {
        return this.totalWidth;
    }

    public boolean isViewVisible(View r1) {
        Holder r0 = this.viewHolders.get(r1);
        if (r0 != null) goto L5;
        return false;
    L5:
        if (Holder.m8809$$Nest$fgetisVisible(r0) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void onItemsChanged() {
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        super.onLayout(r2, r3, r4, r5, r6);
        this.visibleHolders.clear();
        int r22 = getChildCount();
        int r42 = 0;
    L3:
        if (r42 >= r22) goto L13;
        View r52 = getChildAt(r42);
        Holder r62 = this.viewHolders.get(r52);
        if (r62 == null) goto L12;
        Holder.m8814$$Nest$fputorder(r62, r42);
        if (r52.getVisibility() != 0) goto L12;
        if (Holder.m8809$$Nest$fgetisVisible(r62) == false) goto L12;
        this.visibleHolders.add(r62);
    L12:
        r42 = r42 + 1;
        goto L3
    L13:
        Collections.sort(this.visibleHolders, comparator);
        this.listAnimator.reset(this.visibleHolders, !this.skipNextAnimation);
        ArrayList<Holder> r23 = this.visibleHolders;
        int r43 = r23.size();
        int r53 = 0;
    L14:
        if (r53 >= r43) goto L16;
        Holder r0 = r23.get(r53);
        r53 = r53 + 1;
        Holder.m8812$$Nest$fputhasInAnimator(r0, true);
        goto L14
    L16:
        this.skipNextAnimation = false;
        checkViewsVisibility();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        calculateTotalSizesAfterMeasure();
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View r2) {
        super.onViewAdded(r2);
        r2.setVisibility(8);
        this.viewHolders.put(r2, new Holder(r2));
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View r1) {
        super.onViewRemoved(r1);
        this.viewHolders.remove(r1);
    }

    public void setChildVisibilityFactor(View r2, float r3) {
        float r1 = AndroidUtilities.lerp(0.95f, 1.0f, r3);
        r2.setAlpha(r3);
        r2.setScaleX(r1);
        r2.setScaleY(r1);
    }

    public void setDebugName(View r1, String r2) {
        Holder r0 = this.viewHolders.get(r1);
        if (r0 == null) goto L6;
        Holder.m8816$$Nest$fputtag(r0, r2);
        return;
    }

    public void setOnAnimatedHeightChangedListener(Runnable r1) {
        this.onAnimatedHeightChanged = r1;
    }

    public void setPriority(View r1, int r2) {
        Holder r0 = this.viewHolders.get(r1);
        if (r0 == null) goto L6;
        Holder.m8815$$Nest$fputpriority(r0, r2);
        return;
    }

    public void setViewVisible(View r2, boolean r3) {
        setViewVisible(r2, r3, true);
    }

    public void setViewVisible(View r3, boolean r4, boolean r5) {
        if (r3 == null) goto L21;
        Holder r32 = this.viewHolders.get(r3);
        if (r32 != null) goto L7;
        return;
    L7:
        if (Holder.m8809$$Nest$fgetisVisible(r32) == r4) goto L20;
        Holder.m8813$$Nest$fputisVisible(r32, r4);
        if (r4 == false) goto L11;
        r32.view.setVisibility(0);
    L11:
        if (r4 == false) goto L13;
    L15:
        if (r5 == true) goto L17;
        this.skipNextAnimation = true;
    L17:
        requestLayout();
        return;
    L13:
        if (Holder.m8808$$Nest$fgethasInAnimator(r32) == true) goto L15;
        r32.view.setVisibility(8);
        goto L15
    L20:
        return;
    }
}
