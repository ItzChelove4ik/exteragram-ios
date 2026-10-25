package com.exteragram.messenger.components;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import com.exteragram.messenger.utils.MarkdownUtils;
import java.util.ArrayList;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.tl.TL_iv;

/* loaded from: classes4.dex */
public abstract class PreformattedScrollView extends HorizontalScrollView {
    private Group group;

    public static final class Group {
        private int contentWidth;
        private PreformattedScrollView driver;
        private int scrollX;
        private final ArrayList<PreformattedScrollView> views;

        /* renamed from: -$$Nest$fgetcontentWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m1203$$Nest$fgetcontentWidth(Group r0) {
            return r0.contentWidth;
        }

        /* renamed from: -$$Nest$fgetdriver, reason: not valid java name */
        public static /* bridge */ /* synthetic */ PreformattedScrollView m1204$$Nest$fgetdriver(Group r0) {
            return r0.driver;
        }

        /* renamed from: -$$Nest$fgetscrollX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m1205$$Nest$fgetscrollX(Group r0) {
            return r0.scrollX;
        }

        /* renamed from: -$$Nest$fputdriver, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m1206$$Nest$fputdriver(Group r0, PreformattedScrollView r1) {
            r0.driver = r1;
        }

        /* renamed from: -$$Nest$madd, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m1207$$Nest$madd(Group r0, PreformattedScrollView r1) {
            r0.add(r1);
        }

        /* renamed from: -$$Nest$madjustContentWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m1208$$Nest$madjustContentWidth(Group r0, PreformattedScrollView r1, int r2) {
            return r0.adjustContentWidth(r1, r2);
        }

        /* renamed from: -$$Nest$mremove, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m1209$$Nest$mremove(Group r0, PreformattedScrollView r1) {
            r0.remove(r1);
        }

        /* renamed from: -$$Nest$mscrollTo, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m1210$$Nest$mscrollTo(Group r0, PreformattedScrollView r1, int r2) {
            r0.scrollTo(r1, r2);
        }

        public Group() {
            this.views = new ArrayList();
        }

        private void add(PreformattedScrollView r2) {
            if (this.views.contains(r2) == true) goto L6;
            this.views.add(r2);
            return;
        }

        private int adjustContentWidth(PreformattedScrollView r4, int r5) {
            if (r5 <= this.contentWidth) goto L14;
            this.contentWidth = r5;
            int r0 = 0;
        L6:
            if (r0 >= this.views.size()) goto L14;
            PreformattedScrollView r1 = this.views.get(r0);
            if (r1 == r4) goto L12;
            if (r1.getChildCount() <= 0) goto L12;
            View r12 = r1.getChildAt(0);
            Objects.requireNonNull(r12);
            AndroidUtilities.runOnUIThread(new PreformattedScrollView$$ExternalSyntheticLambda0(r12));
        L12:
            r0 = r0 + 1;
        L14:
            return this.contentWidth;
        }

        private void remove(PreformattedScrollView r2) {
            this.views.remove(r2);
            if (this.driver != r2) goto L6;
            this.driver = null;
            return;
        }

        private void scrollTo(PreformattedScrollView r4, int r5) {
            this.scrollX = r5;
            int r0 = 0;
        L4:
            if (r0 >= this.views.size()) goto L9;
            PreformattedScrollView r1 = this.views.get(r0);
            if (r1 == r4) goto L8;
            r1.scrollTo(r5, r1.getScrollY());
        L8:
            r0 = r0 + 1;
            goto L4
        }
    }

    public PreformattedScrollView(Context r1) {
        super(r1);
    }

    public int adjustContentWidth(int r2) {
        Group r0 = this.group;
        if (r0 != null) goto L5;
        return r2;
    L5:
        return Group.m1208$$Nest$madjustContentWidth(r0, this, r2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r2) {
        if (this.group == null) goto L8;
        if (r2.getActionMasked() != 0) goto L8;
        Group.m1206$$Nest$fputdriver(this.group, this);
    L8:
        return super.dispatchTouchEvent(r2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Group r0 = this.group;
        if (r0 == null) goto L10;
        Group.m1207$$Nest$madd(r0, this);
        scrollTo(Group.m1205$$Nest$fgetscrollX(this.group), getScrollY());
        if (getChildCount() > 0) goto L7;
        return;
    L7:
        if (getChildAt(0).getMeasuredWidth() >= Group.m1203$$Nest$fgetcontentWidth(this.group)) goto L12;
        View r3 = getChildAt(0);
        Objects.requireNonNull(r3);
        AndroidUtilities.runOnUIThread(new PreformattedScrollView$$ExternalSyntheticLambda0(r3));
        return;
    L12:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Group r0 = this.group;
        if (r0 == null) goto L6;
        Group.m1209$$Nest$mremove(r0, this);
        return;
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        Group r12 = this.group;
        if (r12 == null) goto L6;
        scrollTo(Group.m1205$$Nest$fgetscrollX(r12), getScrollY());
        return;
    }

    @Override // android.view.View
    public void onScrollChanged(int r1, int r2, int r3, int r4) {
        super.onScrollChanged(r1, r2, r3, r4);
        Group r22 = this.group;
        if (r22 != null) goto L5;
        return;
    L5:
        if (Group.m1204$$Nest$fgetdriver(r22) != this) goto L9;
        Group.m1210$$Nest$mscrollTo(this.group, this, r1);
        return;
    }

    public void setBlock(TL_iv.PageBlock r5) {
        Group r1 = null;
        if ((r5 instanceof MarkdownUtils.PreformattedChunk) == false) goto L5;
        MarkdownUtils.PreformattedChunk r52 = (MarkdownUtils.PreformattedChunk) r5;
    L6:
        Group r0 = this.group;
        if (r0 == null) goto L9;
        Group.m1209$$Nest$mremove(r0, this);
    L9:
        if (r52 == null) goto L11;
        r1 = r52.scrollGroup;
    L11:
        this.group = r1;
        if (r1 != null) goto L14;
    L16:
        boolean r02 = true;
        int r12 = 0;
        if (r52 != null) goto L19;
        boolean r2 = true;
    L20:
        setHorizontalScrollBarEnabled(r2);
        if (r52 != null) goto L23;
    L25:
        boolean r22 = false;
    L26:
        if (r52 != null) goto L28;
    L30:
        r02 = false;
    L32:
        if (r22 == false) goto L34;
        int r3 = 0;
    L35:
        if (r02 == false) goto L37;
        int r53 = 0;
    L38:
        setPadding(0, r3, 0, r53);
        if (getChildCount() <= 0) goto L49;
        ViewGroup.MarginLayoutParams r4 = (ViewGroup.MarginLayoutParams) getChildAt(0).getLayoutParams();
        float r54 = 12.0f;
        if (r22 == true) goto L44;
        r12 = AndroidUtilities.dp(12.0f);
    L44:
        r4.topMargin = r12;
        if (r02 == false) goto L47;
        r54 = 4.0f;
    L47:
        r4.bottomMargin = AndroidUtilities.dp(r54);
        return;
    L49:
        return;
    L37:
        r53 = AndroidUtilities.dp(8.0f);
        goto L38
    L34:
        r3 = AndroidUtilities.dp(8.0f);
        goto L35
    L28:
        if (r52.joinsNext == false) goto L30;
    L23:
        if (r52.joinsPrevious == false) goto L25;
        r22 = true;
        goto L26
    L19:
        r2 = false;
        goto L20
    L14:
        if (isAttachedToWindow() == false) goto L16;
        Group.m1207$$Nest$madd(this.group, this);
        goto L16
    L5:
        r52 = null;
        goto L6
    }
}
