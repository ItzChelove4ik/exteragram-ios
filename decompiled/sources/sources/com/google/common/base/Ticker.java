package com.google.common.base;

/* loaded from: classes5.dex */
public abstract class Ticker {
    private static final Ticker SYSTEM_TICKER = null;

    /* renamed from: com.google.common.base.Ticker$1, reason: invalid class name */
    public class AnonymousClass1 extends Ticker {
        public AnonymousClass1() {
        }

        @Override // com.google.common.base.Ticker
        public long read() {
            return System.nanoTime();
        }
    }

    static {
        SYSTEM_TICKER = new AnonymousClass1();
    }

    public Ticker() {
    }

    public abstract long read();
}
