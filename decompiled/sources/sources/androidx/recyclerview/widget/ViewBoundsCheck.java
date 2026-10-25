package androidx.recyclerview.widget;

import android.view.View;

/* loaded from: classes.dex */
class ViewBoundsCheck {
    BoundFlags mBoundFlags;
    final Callback mCallback;

    public static class BoundFlags {
        int mBoundFlags;
        int mChildEnd;
        int mChildStart;
        int mRvEnd;
        int mRvStart;

        public BoundFlags() {
            this.mBoundFlags = 0;
        }

        public void addFlags(int r2) {
            this.mBoundFlags = r2 | this.mBoundFlags;
        }

        public boolean boundsMatch() {
            int r0 = this.mBoundFlags;
            if ((r0 & 7) != 0) goto L5;
        L7:
            int r02 = this.mBoundFlags;
            if ((r02 & 112) != 0) goto L10;
        L12:
            int r03 = this.mBoundFlags;
            if ((r03 & 1792) != 0) goto L15;
        L17:
            int r04 = this.mBoundFlags;
            if ((r04 & 28672) != 0) goto L20;
            return true;
        L20:
            if (((compare(this.mChildEnd, this.mRvEnd) << 12) & r04) != 0) goto L24;
            return false;
        L24:
            return true;
        L15:
            if ((r03 & (compare(this.mChildEnd, this.mRvStart) << 8)) != 0) goto L17;
            return false;
        L10:
            if ((r02 & (compare(this.mChildStart, this.mRvEnd) << 4)) != 0) goto L12;
            return false;
        L5:
            if ((r0 & compare(this.mChildStart, this.mRvStart)) != 0) goto L7;
            return false;
        }

        public int compare(int r1, int r2) {
            if (r1 <= r2) goto L5;
            return 1;
        L5:
            if (r1 != r2) goto L8;
            return 2;
        L8:
            return 4;
        }

        public void resetFlags() {
            this.mBoundFlags = 0;
        }

        public void setBounds(int r1, int r2, int r3, int r4) {
            this.mRvStart = r1;
            this.mRvEnd = r2;
            this.mChildStart = r3;
            this.mChildEnd = r4;
        }
    }

    public interface Callback {
        View getChildAt(int r1);

        int getChildEnd(View r1);

        int getChildStart(View r1);

        int getParentEnd();

        int getParentStart();
    }

    public ViewBoundsCheck(Callback r1) {
        this.mCallback = r1;
        this.mBoundFlags = new BoundFlags();
    }

    public View findOneViewWithinBoundFlags(int r9, int r10, int r11, int r12) {
        int r0 = this.mCallback.getParentStart();
        int r1 = this.mCallback.getParentEnd();
        if (r10 <= r9) goto L5;
        int r2 = 1;
    L6:
        View r3 = null;
    L7:
        if (r9 == r10) goto L18;
        View r4 = this.mCallback.getChildAt(r9);
        int r5 = this.mCallback.getChildStart(r4);
        int r6 = this.mCallback.getChildEnd(r4);
        this.mBoundFlags.setBounds(r0, r1, r5, r6);
        if (r11 == 0) goto L13;
        this.mBoundFlags.resetFlags();
        this.mBoundFlags.addFlags(r11);
        if (this.mBoundFlags.boundsMatch() == false) goto L13;
        return r4;
    L13:
        if (r12 == 0) goto L17;
        this.mBoundFlags.resetFlags();
        this.mBoundFlags.addFlags(r12);
        if (this.mBoundFlags.boundsMatch() == false) goto L17;
        r3 = r4;
    L17:
        r9 = r9 + r2;
        goto L7
    L18:
        return r3;
    L5:
        r2 = -1;
        goto L6
    }

    public boolean isViewWithinBoundFlags(View r6, int r7) {
        this.mBoundFlags.setBounds(this.mCallback.getParentStart(), this.mCallback.getParentEnd(), this.mCallback.getChildStart(r6), this.mCallback.getChildEnd(r6));
        if (r7 == 0) goto L6;
        this.mBoundFlags.resetFlags();
        this.mBoundFlags.addFlags(r7);
        return this.mBoundFlags.boundsMatch();
    L6:
        return false;
    }
}
