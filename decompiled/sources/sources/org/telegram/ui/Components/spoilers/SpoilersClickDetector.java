package org.telegram.ui.Components.spoilers;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.GestureDetectorCompat;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class SpoilersClickDetector {
    private GestureDetectorCompat gestureDetector;
    private int horizontalPadding;
    private boolean trackingTap;
    private int verticalPadding;

    /* renamed from: org.telegram.ui.Components.spoilers.SpoilersClickDetector$1, reason: invalid class name */
    public class AnonymousClass1 extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ SpoilersClickDetector this$0;
        final /* synthetic */ OnSpoilerClickedListener val$clickedListener;
        final /* synthetic */ boolean val$offsetPadding;
        final /* synthetic */ List val$spoilers;
        final /* synthetic */ View val$v;

        public AnonymousClass1(SpoilersClickDetector r1, View r2, boolean r3, List r4, OnSpoilerClickedListener r5) {
            this.this$0 = r1;
            this.val$v = r2;
            this.val$offsetPadding = r3;
            this.val$spoilers = r4;
            this.val$clickedListener = r5;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r4) {
            int r0 = (int) r4.getX();
            int r42 = ((int) r4.getY()) + this.val$v.getScrollY();
            if (this.val$offsetPadding == false) goto L5;
            r0 = r0 - this.val$v.getPaddingLeft();
            r42 = r42 - this.val$v.getPaddingTop();
        L5:
            int r02 = r0 - SpoilersClickDetector.m13800$$Nest$fgethorizontalPadding(this.this$0);
            int r43 = r42 - SpoilersClickDetector.m13802$$Nest$fgetverticalPadding(this.this$0);
            Iterator r1 = this.val$spoilers.iterator();
        L7:
            if (r1.hasNext() == false) goto L12;
            if (((SpoilerEffect) r1.next()).getBounds().contains(r02, r43) == false) goto L7;
            SpoilersClickDetector.m13803$$Nest$fputtrackingTap(this.this$0, true);
            return true;
        L12:
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent r6) {
            if (SpoilersClickDetector.m13801$$Nest$fgettrackingTap(this.this$0) == false) goto L14;
            this.val$v.playSoundEffect(0);
            SpoilersClickDetector.m13803$$Nest$fputtrackingTap(this.this$0, false);
            int r0 = (int) r6.getX();
            int r62 = ((int) r6.getY()) + this.val$v.getScrollY();
            if (this.val$offsetPadding == false) goto L7;
            r0 = r0 - this.val$v.getPaddingLeft();
            r62 = r62 - this.val$v.getPaddingTop();
        L7:
            int r02 = r0 - SpoilersClickDetector.m13800$$Nest$fgethorizontalPadding(this.this$0);
            int r63 = r62 - SpoilersClickDetector.m13802$$Nest$fgetverticalPadding(this.this$0);
            Iterator r2 = this.val$spoilers.iterator();
        L9:
            if (r2.hasNext() == false) goto L14;
            SpoilerEffect r3 = (SpoilerEffect) r2.next();
            if (r3.getBounds().contains(r02, r63) == false) goto L9;
            this.val$clickedListener.onSpoilerClicked(r3, r02, r63);
            return true;
        L14:
            return false;
        }
    }

    public interface OnSpoilerClickedListener {
        void onSpoilerClicked(SpoilerEffect r1, float r2, float r3);
    }

    /* renamed from: -$$Nest$fgethorizontalPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13800$$Nest$fgethorizontalPadding(SpoilersClickDetector r0) {
        return r0.horizontalPadding;
    }

    /* renamed from: -$$Nest$fgettrackingTap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13801$$Nest$fgettrackingTap(SpoilersClickDetector r0) {
        return r0.trackingTap;
    }

    /* renamed from: -$$Nest$fgetverticalPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13802$$Nest$fgetverticalPadding(SpoilersClickDetector r0) {
        return r0.verticalPadding;
    }

    /* renamed from: -$$Nest$fputtrackingTap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13803$$Nest$fputtrackingTap(SpoilersClickDetector r0, boolean r1) {
        r0.trackingTap = r1;
    }

    public SpoilersClickDetector(View r2, List<SpoilerEffect> r3, OnSpoilerClickedListener r4) {
        this(r2, r3, true, r4);
    }

    public SpoilersClickDetector(View r9, List<SpoilerEffect> r10, boolean r11, OnSpoilerClickedListener r12) {
        this.gestureDetector = new GestureDetectorCompat(r9.getContext(), new AnonymousClass1(this, r9, r11, r10, r12));
    }

    public boolean onTouchEvent(MotionEvent r1) {
        return this.gestureDetector.onTouchEvent(r1);
    }

    public void setAdditionalOffsets(int r1, int r2) {
        this.horizontalPadding = r1;
        this.verticalPadding = r2;
    }
}
