package org.telegram.ui.Components;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes7.dex */
public abstract class CircularViewPager extends ViewPager {
    private Adapter adapter;

    /* renamed from: org.telegram.ui.Components.CircularViewPager$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewPager.OnPageChangeListener {
        private int scrollState;
        final /* synthetic */ CircularViewPager this$0;

        public AnonymousClass1(CircularViewPager r1) {
            this.this$0 = r1;
        }

        private void checkCurrentItem() {
            if (CircularViewPager.m10015$$Nest$fgetadapter(this.this$0) == null) goto L8;
            int r0 = this.this$0.getCurrentItem();
            int r1 = CircularViewPager.m10015$$Nest$fgetadapter(this.this$0).getExtraCount() + CircularViewPager.m10015$$Nest$fgetadapter(this.this$0).getRealPosition(r0);
            if (r0 == r1) goto L9;
            this.this$0.setCurrentItem(r1, false);
            return;
        L9:
            return;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r1) {
            if (r1 != 0) goto L4;
            checkCurrentItem();
        L4:
            this.scrollState = r1;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r1, float r2, int r3) {
            if (r1 == this.this$0.getCurrentItem()) goto L5;
            return;
        L5:
            if (r2 == 0.0f) goto L7;
            return;
        L7:
            if (this.scrollState != 1) goto L12;
            checkCurrentItem();
            return;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r1) {
        }
    }

    public static abstract class Adapter extends PagerAdapter {
        public Adapter() {
        }

        public abstract int getExtraCount();

        public int getRealPosition(int r3) {
            int r0 = getCount();
            int r2 = getExtraCount();
            if (r3 < r2) goto L5;
            int r02 = r0 - r2;
            if (r3 < r02) goto L11;
            return r3 - r02;
        L11:
            return r3 - r2;
        L5:
            return ((r0 - (r2 * 2)) - ((r2 - r3) - 1)) - 1;
        }
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Adapter m10015$$Nest$fgetadapter(CircularViewPager r0) {
        return r0.adapter;
    }

    public CircularViewPager(Context r1) {
        super(r1);
        addOnPageChangeListener(new AnonymousClass1(this));
    }

    @Override // androidx.viewpager.widget.ViewPager
    @Deprecated
    public void setAdapter(PagerAdapter r2) {
        if ((r2 instanceof Adapter) == false) goto L6;
        setAdapter((Adapter) r2);
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline0.m();
    }

    public void setAdapter(Adapter r2) {
        this.adapter = r2;
        super.setAdapter(r2);
        if (r2 == null) goto L6;
        setCurrentItem(r2.getExtraCount(), false);
        return;
    }
}
