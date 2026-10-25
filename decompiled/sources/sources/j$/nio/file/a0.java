package j$.nio.file;

import java.util.function.Consumer;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class a0 implements Consumer {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f186a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Consumer f187b;

    public /* synthetic */ a0(Consumer r1, int r2) {
        this.f186a = r2;
        this.f187b = r1;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object r2) {
        int r0 = this.f186a;
        Consumer r1 = this.f187b;
        switch(r0) {
            case 0: goto L6;
            default: goto L4;
        };
    L4:
        r1.accept(j$.desugar.sun.nio.fs.g.f(r2));
        return;
    L6:
        r1.accept(j$.desugar.sun.nio.fs.g.f(r2));
    }
}
