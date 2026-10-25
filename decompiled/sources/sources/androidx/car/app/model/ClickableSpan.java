package androidx.car.app.model;

import android.annotation.SuppressLint;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class ClickableSpan extends CarSpan {
    private final OnClickDelegate mOnClickDelegate;

    private ClickableSpan() {
        this.mOnClickDelegate = null;
    }

    private ClickableSpan(OnClickListener r1) {
        this.mOnClickDelegate = OnClickDelegateImpl.create(r1);
    }

    @SuppressLint({"ExecutorRegistration"})
    public static ClickableSpan create(OnClickListener r1) {
        Objects.requireNonNull(r1);
        return new ClickableSpan(r1);
    }

    public boolean equals(Object r4) {
        boolean r0 = true;
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof ClickableSpan) == true) goto L8;
        return false;
    L8:
        ClickableSpan r42 = (ClickableSpan) r4;
        if (this.mOnClickDelegate != null) goto L11;
        boolean r3 = true;
    L12:
        Boolean r32 = Boolean.valueOf(r3);
        if (r42.mOnClickDelegate == null) goto L17;
        r0 = false;
    L17:
        return r32.equals(Boolean.valueOf(r0));
    L11:
        r3 = false;
        goto L12
    }

    public OnClickDelegate getOnClickDelegate() {
        OnClickDelegate r0 = this.mOnClickDelegate;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int hashCode() {
        if (this.mOnClickDelegate != null) goto L5;
        boolean r0 = true;
    L7:
        return Objects.hash(new Object[]{Boolean.valueOf(r0)});
    L5:
        r0 = false;
        goto L7
    }

    public String toString() {
        return "[clickable]";
    }
}
