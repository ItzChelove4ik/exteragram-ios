package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.ArrayList;
import java.util.List;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public class ChildHelper {
    final Bucket mBucket;
    final Callback mCallback;
    final List<View> mHiddenViews;

    public static class Bucket {
        long mData;
        Bucket mNext;

        public Bucket() {
            this.mData = 0;
        }

        private void ensureNext() {
            if (this.mNext != null) goto L6;
            this.mNext = new Bucket();
            return;
        }

        public void clear(int r5) {
            if (r5 < 64) goto L8;
            Bucket r4 = this.mNext;
            if (r4 == null) goto L10;
            r4.clear(r5 - 64);
            return;
        L10:
            return;
        L8:
            this.mData &= ~(1 << r5);
        }

        public int countOnesBefore(int r7) {
            Bucket r0 = this.mNext;
            if (r0 != null) goto L10;
            long r4 = this.mData;
            if (r7 < 64) goto L9;
            return Long.bitCount(r4);
        L9:
            return Long.bitCount(((1 << r7) - 1) & r4);
        L10:
            if (r7 >= 64) goto L14;
            long r02 = this.mData;
            return Long.bitCount(((1 << r7) - 1) & r02);
        L14:
            return r0.countOnesBefore(r7 - 64) + Long.bitCount(this.mData);
        }

        public boolean get(int r5) {
            if (r5 < 64) goto L6;
            ensureNext();
            return this.mNext.get(r5 - 64);
        L6:
            long r0 = this.mData;
            if (((1 << r5) & r0) == 0) goto L10;
            return true;
        L10:
            return false;
        }

        public void insert(int r10, boolean r11) {
            if (r10 < 64) goto L6;
            ensureNext();
            this.mNext.insert(r10 - 64, r11);
            return;
        L6:
            long r0 = this.mData;
            if ((Long.MIN_VALUE & r0) == 0) goto L9;
            boolean r2 = true;
        L10:
            long r7 = (1 << r10) - 1;
            this.mData = ((r0 & (~r7)) << 1) | (r0 & r7);
            if (r11 == false) goto L13;
            set(r10);
        L14:
            if (r2 == false) goto L16;
        L19:
            ensureNext();
            this.mNext.insert(0, r2);
            return;
        L16:
            if (this.mNext != null) goto L19;
            return;
        L13:
            clear(r10);
            goto L14
        L9:
            r2 = false;
            goto L10
        }

        public boolean remove(int r11) {
            if (r11 < 64) goto L6;
            ensureNext();
            return this.mNext.remove(r11 - 64);
        L6:
            long r2 = 1 << r11;
            long r4 = this.mData;
            if ((r4 & r2) == 0) goto L9;
            boolean r112 = true;
        L10:
            long r42 = r4 & (~r2);
            this.mData = r42;
            long r22 = r2 - 1;
            this.mData = (r42 & r22) | Long.rotateRight((~r22) & r42, 1);
            Bucket r0 = this.mNext;
            if (r0 != null) goto L13;
        L16:
            return r112;
        L13:
            if (r0.get(0) == false) goto L15;
            set(63);
        L15:
            this.mNext.remove(0);
            goto L16
        L9:
            r112 = false;
            goto L10
        }

        public void reset() {
            this.mData = 0;
            Bucket r2 = this.mNext;
            if (r2 == null) goto L6;
            r2.reset();
            return;
        }

        public void set(int r5) {
            if (r5 < 64) goto L6;
            ensureNext();
            this.mNext.set(r5 - 64);
            return;
        L6:
            this.mData |= 1 << r5;
        }

        public String toString() {
            if (this.mNext != null) goto L7;
            return Long.toBinaryString(this.mData);
        L7:
            return this.mNext.toString() + "xx" + Long.toBinaryString(this.mData);
        }
    }

    public interface Callback {
        void addView(View r1, int r2);

        void attachViewToParent(View r1, int r2, ViewGroup.LayoutParams r3);

        void detachViewFromParent(int r1);

        View getChildAt(int r1);

        int getChildCount();

        RecyclerView.ViewHolder getChildViewHolder(View r1);

        int indexOfChild(View r1);

        void onEnteredHiddenState(View r1);

        void onLeftHiddenState(View r1);

        void removeAllViews();

        void removeViewAt(int r1);
    }

    public ChildHelper(Callback r1) {
        this.mCallback = r1;
        this.mBucket = new Bucket();
        this.mHiddenViews = new ArrayList();
    }

    private int getOffset(int r5) {
        if (r5 >= 0) goto L5;
        return -1;
    L5:
        int r1 = this.mCallback.getChildCount();
        int r2 = r5;
    L6:
        if (r2 >= r1) goto L14;
        int r3 = r5 - (r2 - this.mBucket.countOnesBefore(r2));
        if (r3 == 0) goto L10;
        r2 = r2 + r3;
    L10:
        if (this.mBucket.get(r2) == false) goto L12;
        r2 = r2 + 1;
        goto L10
    L12:
        return r2;
    L14:
        return -1;
    }

    public void addView(View r2, int r3, boolean r4) {
        if (r3 >= 0) goto L4;
        int r32 = this.mCallback.getChildCount();
    L5:
        this.mBucket.insert(r32, r4);
        if (r4 == false) goto L8;
        hideViewInternal(r2);
    L8:
        this.mCallback.addView(r2, r32);
        return;
    L4:
        r32 = getOffset(r3);
        goto L5
    }

    public void addView(View r2, boolean r3) {
        addView(r2, -1, r3);
    }

    public void attachViewToParent(View r2, int r3, ViewGroup.LayoutParams r4, boolean r5) {
        if (r3 >= 0) goto L4;
        int r32 = this.mCallback.getChildCount();
    L5:
        this.mBucket.insert(r32, r5);
        if (r5 == false) goto L8;
        hideViewInternal(r2);
    L8:
        this.mCallback.attachViewToParent(r2, r32, r4);
        return;
    L4:
        r32 = getOffset(r3);
        goto L5
    }

    public void detachViewFromParent(int r2) {
        int r22 = getOffset(r2);
        this.mBucket.remove(r22);
        this.mCallback.detachViewFromParent(r22);
    }

    public View findHiddenNonRemovedView(int r6) {
        int r0 = this.mHiddenViews.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L12;
        View r2 = this.mHiddenViews.get(r1);
        RecyclerView.ViewHolder r3 = this.mCallback.getChildViewHolder(r2);
        if (r3.getLayoutPosition() != r6) goto L11;
        if (r3.isInvalid() == true) goto L11;
        if (r3.isRemoved() == true) goto L11;
        return r2;
    L11:
        r1 = r1 + 1;
        goto L3
    L12:
        return null;
    }

    public View getChildAt(int r1) {
        int r12 = getOffset(r1);
        return this.mCallback.getChildAt(r12);
    }

    public int getChildCount() {
        return this.mCallback.getChildCount() - this.mHiddenViews.size();
    }

    public View getHiddenChildAt(int r2) {
        if (r2 >= 0) goto L4;
        return null;
    L4:
        if (r2 < this.mHiddenViews.size()) goto L7;
        return null;
    L7:
        return this.mHiddenViews.get(r2);
    }

    public int getHiddenChildCount() {
        return this.mHiddenViews.size();
    }

    public View getUnfilteredChildAt(int r1) {
        return this.mCallback.getChildAt(r1);
    }

    public int getUnfilteredChildCount() {
        return this.mCallback.getChildCount();
    }

    public void hide(View r3) {
        int r0 = this.mCallback.indexOfChild(r3);
        if (r0 < 0) goto L6;
        this.mBucket.set(r0);
        hideViewInternal(r3);
        return;
    L6:
        Native$$ExternalSyntheticBUOutline5.m("view is not a child, cannot hide ", r3);
    }

    public void hideViewInternal(View r2) {
        this.mHiddenViews.add(r2);
        this.mCallback.onEnteredHiddenState(r2);
    }

    public int indexOfChild(View r3) {
        int r32 = this.mCallback.indexOfChild(r3);
        if (r32 != (-1)) goto L6;
        return -1;
    L6:
        if (this.mBucket.get(r32) == false) goto L9;
        return -1;
    L9:
        return r32 - this.mBucket.countOnesBefore(r32);
    }

    public boolean isHidden(View r1) {
        return this.mHiddenViews.contains(r1);
    }

    public void removeAllViewsUnfiltered() {
        this.mBucket.reset();
        int r0 = this.mHiddenViews.size();
    L3:
        r0 = r0 - 1;
        Callback r1 = this.mCallback;
        if (r0 < 0) goto L6;
        r1.onLeftHiddenState(this.mHiddenViews.get(r0));
        this.mHiddenViews.remove(r0);
        goto L3
    L6:
        r1.removeAllViews();
    }

    public void removeView(View r3) {
        int r0 = this.mCallback.indexOfChild(r3);
        if (r0 >= 0) goto L6;
        return;
    L6:
        if (this.mBucket.remove(r0) == false) goto L8;
        unhideViewInternal(r3);
    L8:
        this.mCallback.removeViewAt(r0);
    }

    public void removeViewAt(int r3) {
        int r32 = getOffset(r3);
        View r0 = this.mCallback.getChildAt(r32);
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.mBucket.remove(r32) == false) goto L8;
        unhideViewInternal(r0);
    L8:
        this.mCallback.removeViewAt(r32);
    }

    public boolean removeViewIfHidden(View r4) {
        int r0 = this.mCallback.indexOfChild(r4);
        if (r0 != (-1)) goto L7;
        unhideViewInternal(r4);
        return true;
    L7:
        if (this.mBucket.get(r0) == false) goto L10;
        this.mBucket.remove(r0);
        unhideViewInternal(r4);
        this.mCallback.removeViewAt(r0);
        return true;
    L10:
        return false;
    }

    public String toString() {
        return this.mBucket.toString() + ", hidden list:" + this.mHiddenViews.size();
    }

    public void unhide(View r3) {
        int r0 = this.mCallback.indexOfChild(r3);
        if (r0 >= 0) goto L5;
        Native$$ExternalSyntheticBUOutline5.m("view is not a child, cannot hide ", r3);
        return;
    L5:
        if (this.mBucket.get(r0) == false) goto L8;
        this.mBucket.clear(r0);
        unhideViewInternal(r3);
        return;
    L8:
        MVEL$$ExternalSyntheticBUOutline0.m("trying to unhide a view that was not hidden", r3);
    }

    public boolean unhideViewInternal(View r2) {
        if (this.mHiddenViews.remove(r2) == false) goto L6;
        this.mCallback.onLeftHiddenState(r2);
        return true;
    L6:
        return false;
    }
}
