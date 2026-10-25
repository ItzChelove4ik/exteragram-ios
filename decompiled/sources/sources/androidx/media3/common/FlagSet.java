package androidx.media3.common;

import android.util.SparseBooleanArray;
import androidx.media3.common.util.Assertions;

/* loaded from: classes3.dex */
public final class FlagSet {
    private final SparseBooleanArray flags;

    /* renamed from: androidx.media3.common.FlagSet$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private boolean buildCalled;
        private final SparseBooleanArray flags;

        public Builder() {
            this.flags = new SparseBooleanArray();
        }

        public Builder add(int r3) {
            Assertions.checkState(!this.buildCalled);
            this.flags.append(r3, true);
            return this;
        }

        public Builder addAll(FlagSet r3) {
            int r0 = 0;
        L4:
            if (r0 >= r3.size()) goto L6;
            add(r3.get(r0));
            r0 = r0 + 1;
            goto L4
        L6:
            return this;
        }

        public Builder addAll(int... r4) {
            int r0 = r4.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            add(r4[r1]);
            r1 = r1 + 1;
            goto L3
        L5:
            return this;
        }

        public Builder addIf(int r1, boolean r2) {
            if (r2 == true) goto L4;
            return this;
        L4:
            return add(r1);
        }

        public FlagSet build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            return new FlagSet(this.flags, null);
        }
    }

    private FlagSet(SparseBooleanArray r1) {
        this.flags = r1;
    }

    public /* synthetic */ FlagSet(SparseBooleanArray r1, AnonymousClass1 r2) {
        this(r1);
    }

    public boolean contains(int r1) {
        return this.flags.get(r1);
    }

    public boolean containsAny(int... r5) {
        int r0 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (contains(r5[r2]) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof FlagSet) == true) goto L10;
        return false;
    L10:
        return this.flags.equals(((FlagSet) r2).flags);
    }

    public int get(int r3) {
        Assertions.checkIndex(r3, 0, size());
        return this.flags.keyAt(r3);
    }

    public int hashCode() {
        return this.flags.hashCode();
    }

    public int size() {
        return this.flags.size();
    }
}
