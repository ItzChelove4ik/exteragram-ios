package com.google.firebase.concurrent;

import android.annotation.SuppressLint;
import androidx.concurrent.futures.AbstractResolvableFuture;
import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@SuppressLint({"RestrictedApi"})
/* loaded from: classes5.dex */
class DelegatingScheduledFuture<V> extends AbstractResolvableFuture<V> implements ScheduledFuture<V> {
    private final ScheduledFuture<?> upstreamFuture;

    /* renamed from: com.google.firebase.concurrent.DelegatingScheduledFuture$1, reason: invalid class name */
    public class AnonymousClass1 implements Completer<V> {
        final /* synthetic */ DelegatingScheduledFuture this$0;

        public AnonymousClass1(DelegatingScheduledFuture r1) {
            this.this$0 = r1;
        }

        @Override // com.google.firebase.concurrent.DelegatingScheduledFuture.Completer
        public void set(V r1) {
            DelegatingScheduledFuture.access$000(this.this$0, r1);
        }

        @Override // com.google.firebase.concurrent.DelegatingScheduledFuture.Completer
        public void setException(Throwable r1) {
            DelegatingScheduledFuture.access$100(this.this$0, r1);
        }
    }

    public interface Completer<T> {
        void set(T r1);

        void setException(Throwable r1);
    }

    public interface Resolver<T> {
        ScheduledFuture<?> addCompleter(Completer<T> r1);
    }

    public DelegatingScheduledFuture(Resolver<V> r2) {
        this.upstreamFuture = r2.addCompleter(new AnonymousClass1(this));
    }

    public static /* synthetic */ boolean access$000(DelegatingScheduledFuture r0, Object r1) {
        return r0.set(r1);
    }

    public static /* synthetic */ boolean access$100(DelegatingScheduledFuture r0, Throwable r1) {
        return r0.setException(r1);
    }

    @Override // androidx.concurrent.futures.AbstractResolvableFuture
    public void afterDone() {
        this.upstreamFuture.cancel(wasInterrupted());
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Delayed r1) {
        return compareTo2(r1);
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(Delayed r1) {
        return this.upstreamFuture.compareTo(r1);
    }

    @Override // java.util.concurrent.Delayed
    public long getDelay(TimeUnit r1) {
        return this.upstreamFuture.getDelay(r1);
    }
}
