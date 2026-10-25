package com.google.common.collect;

import java.util.Comparator;

/* loaded from: classes5.dex */
public abstract class ComparisonChain {
    private static final ComparisonChain ACTIVE = null;
    private static final ComparisonChain GREATER = null;
    private static final ComparisonChain LESS = null;

    /* renamed from: com.google.common.collect.ComparisonChain$1, reason: invalid class name */
    public class AnonymousClass1 extends ComparisonChain {
        public AnonymousClass1() {
            super(null);
        }

        public ComparisonChain classify(int r1) {
            if (r1 < 0) goto L4;
            if (r1 <= 0) goto L9;
            return ComparisonChain.access$200();
        L9:
            return ComparisonChain.access$300();
        L4:
            return ComparisonChain.access$100();
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(int r1, int r2) {
            return classify(Integer.compare(r1, r2));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(long r1, long r3) {
            return classify(Long.compare(r1, r3));
        }

        @Override // com.google.common.collect.ComparisonChain
        public <T> ComparisonChain compare(T r1, T r2, Comparator<T> r3) {
            return classify(r3.compare(r1, r2));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareFalseFirst(boolean r1, boolean r2) {
            return classify(Boolean.compare(r1, r2));
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareTrueFirst(boolean r1, boolean r2) {
            return classify(Boolean.compare(r2, r1));
        }

        @Override // com.google.common.collect.ComparisonChain
        public int result() {
            return 0;
        }
    }

    public static final class InactiveComparisonChain extends ComparisonChain {
        final int result;

        public InactiveComparisonChain(int r2) {
            super(null);
            this.result = r2;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(int r1, int r2) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(long r1, long r3) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public <T> ComparisonChain compare(T r1, T r2, Comparator<T> r3) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareFalseFirst(boolean r1, boolean r2) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compareTrueFirst(boolean r1, boolean r2) {
            return this;
        }

        @Override // com.google.common.collect.ComparisonChain
        public int result() {
            return this.result;
        }
    }

    static {
        ACTIVE = new AnonymousClass1();
        LESS = new InactiveComparisonChain(-1);
        GREATER = new InactiveComparisonChain(1);
    }

    private ComparisonChain() {
    }

    public /* synthetic */ ComparisonChain(AnonymousClass1 r1) {
        this();
    }

    public static /* synthetic */ ComparisonChain access$100() {
        return LESS;
    }

    public static /* synthetic */ ComparisonChain access$200() {
        return GREATER;
    }

    public static /* synthetic */ ComparisonChain access$300() {
        return ACTIVE;
    }

    public static ComparisonChain start() {
        return ACTIVE;
    }

    public abstract ComparisonChain compare(int r1, int r2);

    public abstract ComparisonChain compare(long r1, long r3);

    public abstract <T> ComparisonChain compare(T r1, T r2, Comparator<T> r3);

    public abstract ComparisonChain compareFalseFirst(boolean r1, boolean r2);

    public abstract ComparisonChain compareTrueFirst(boolean r1, boolean r2);

    public abstract int result();
}
