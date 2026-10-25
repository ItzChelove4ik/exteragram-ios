package j$.desugar.sun.nio.fs;

import java.nio.channels.FileLock;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class f extends FileLock {

    /* renamed from: a, reason: collision with root package name */
    public final FileLock f156a;

    public f(FileLock r8, e r9) {
        super(r9, r8.position(), r8.size(), r8.isShared());
        this.f156a = r8;
    }

    @Override // java.nio.channels.FileLock
    public final boolean isValid() {
        return this.f156a.isValid();
    }

    @Override // java.nio.channels.FileLock
    public final void release() {
        this.f156a.release();
    }
}
