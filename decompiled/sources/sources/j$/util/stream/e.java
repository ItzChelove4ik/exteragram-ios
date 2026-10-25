package j$.util.stream;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class e implements Collector {

    /* renamed from: a, reason: collision with root package name */
    public final a f491a;

    /* renamed from: b, reason: collision with root package name */
    public final b f492b;

    /* renamed from: c, reason: collision with root package name */
    public final c f493c;
    public final d d;
    public final Set e;

    public e(a r1, b r2, c r3, d r4, Set r5) {
        this.f491a = r1;
        this.f492b = r2;
        this.f493c = r3;
        this.d = r4;
        this.e = r5;
    }

    @Override // java.util.stream.Collector
    public final BiConsumer accumulator() {
        return this.f492b;
    }

    @Override // java.util.stream.Collector
    public final Set characteristics() {
        return this.e;
    }

    @Override // java.util.stream.Collector
    public final BinaryOperator combiner() {
        return this.f493c;
    }

    @Override // java.util.stream.Collector
    public final Function finisher() {
        return this.d;
    }

    @Override // java.util.stream.Collector
    public final Supplier supplier() {
        return this.f491a;
    }
}
