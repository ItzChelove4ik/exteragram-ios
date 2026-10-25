package j$.nio.file.attribute;

import java.util.Collections;
import java.util.Set;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class k implements java.nio.file.attribute.FileAttribute {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ FileAttribute f201a;

    public k(FileAttribute r1) {
        this.f201a = r1;
    }

    @Override // java.nio.file.attribute.FileAttribute
    public final String name() {
        return "posix:permissions";
    }

    @Override // java.nio.file.attribute.FileAttribute
    public final Object value() {
        return Collections.unmodifiableSet(j$.desugar.sun.nio.fs.g.m((Set) this.f201a.value()));
    }
}
