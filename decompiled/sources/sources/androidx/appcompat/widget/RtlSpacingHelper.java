package androidx.appcompat.widget;

/* loaded from: classes3.dex */
class RtlSpacingHelper {
    private int mEnd;
    private int mExplicitLeft;
    private int mExplicitRight;
    private boolean mIsRelative;
    private boolean mIsRtl;
    private int mLeft;
    private int mRight;
    private int mStart;

    public RtlSpacingHelper() {
        this.mLeft = 0;
        this.mRight = 0;
        this.mStart = Integer.MIN_VALUE;
        this.mEnd = Integer.MIN_VALUE;
        this.mExplicitLeft = 0;
        this.mExplicitRight = 0;
        this.mIsRtl = false;
        this.mIsRelative = false;
    }

    public int getEnd() {
        if (this.mIsRtl == false) goto L7;
        return this.mLeft;
    L7:
        return this.mRight;
    }

    public int getLeft() {
        return this.mLeft;
    }

    public int getRight() {
        return this.mRight;
    }

    public int getStart() {
        if (this.mIsRtl == false) goto L7;
        return this.mRight;
    L7:
        return this.mLeft;
    }

    public void setAbsolute(int r2, int r3) {
        this.mIsRelative = false;
        if (r2 == Integer.MIN_VALUE) goto L5;
        this.mExplicitLeft = r2;
        this.mLeft = r2;
    L5:
        if (r3 == Integer.MIN_VALUE) goto L8;
        this.mExplicitRight = r3;
        this.mRight = r3;
        return;
    }

    public void setDirection(boolean r2) {
        if (r2 != this.mIsRtl) goto L5;
        return;
    L5:
        this.mIsRtl = r2;
        if (this.mIsRelative == true) goto L8;
        this.mLeft = this.mExplicitLeft;
        this.mRight = this.mExplicitRight;
        return;
    L8:
        if (r2 == false) goto L19;
        int r22 = this.mEnd;
        if (r22 != Integer.MIN_VALUE) goto L13;
        r22 = this.mExplicitLeft;
    L13:
        this.mLeft = r22;
        int r23 = this.mStart;
        if (r23 != Integer.MIN_VALUE) goto L17;
        r23 = this.mExplicitRight;
    L17:
        this.mRight = r23;
        return;
    L19:
        int r24 = this.mStart;
        if (r24 != Integer.MIN_VALUE) goto L23;
        r24 = this.mExplicitLeft;
    L23:
        this.mLeft = r24;
        int r25 = this.mEnd;
        if (r25 != Integer.MIN_VALUE) goto L27;
        r25 = this.mExplicitRight;
    L27:
        this.mRight = r25;
    }

    public void setRelative(int r3, int r4) {
        this.mStart = r3;
        this.mEnd = r4;
        this.mIsRelative = true;
        if (this.mIsRtl == false) goto L9;
        if (r4 == Integer.MIN_VALUE) goto L6;
        this.mLeft = r4;
    L6:
        if (r3 == Integer.MIN_VALUE) goto L14;
        this.mRight = r3;
        return;
    L14:
        return;
    L9:
        if (r3 == Integer.MIN_VALUE) goto L11;
        this.mLeft = r3;
    L11:
        if (r4 == Integer.MIN_VALUE) goto L15;
        this.mRight = r4;
        return;
    }
}
