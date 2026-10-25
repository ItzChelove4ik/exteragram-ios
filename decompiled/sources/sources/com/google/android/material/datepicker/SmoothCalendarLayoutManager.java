package com.google.android.material.datepicker;

import android.content.Context;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
class SmoothCalendarLayoutManager extends LinearLayoutManager {
    private static final float MILLISECONDS_PER_INCH = 100.0f;

    /* renamed from: com.google.android.material.datepicker.SmoothCalendarLayoutManager$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearSmoothScroller {
        final /* synthetic */ SmoothCalendarLayoutManager this$0;

        public AnonymousClass1(SmoothCalendarLayoutManager r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public float calculateSpeedPerPixel(DisplayMetrics r1) {
            return SmoothCalendarLayoutManager.MILLISECONDS_PER_INCH / r1.densityDpi;
        }
    }

    public SmoothCalendarLayoutManager(Context r1, int r2, boolean r3) {
        super(r1, r2, r3);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView r1, RecyclerView.State r2, int r3) {
        AnonymousClass1 r22 = new AnonymousClass1(this, r1.getContext());
        r22.setTargetPosition(r3);
        startSmoothScroll(r22);
    }
}
