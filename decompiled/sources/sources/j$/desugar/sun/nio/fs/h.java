package j$.desugar.sun.nio.fs;

import j$.nio.file.Path;
import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.g0;
import j$.time.ZoneId;
import j$.time.temporal.r;
import java.nio.file.DirectoryStream;
import java.util.Collections;
import java.util.Set;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class h implements g0, DirectoryStream.Filter, FileAttribute, j$.time.temporal.l {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f157a;

    /* renamed from: b, reason: collision with root package name */
    public final Object f158b;

    public /* synthetic */ h(Object r1, int r2) {
        this.f157a = r2;
        this.f158b = r1;
    }

    @Override // java.nio.file.DirectoryStream.Filter
    public boolean accept(Object r2) {
        switch(this.f157a) {
            case 1: goto L7;
            default: goto L5;
        };
    L5:
        return ((DirectoryStream.Filter) this.f158b).accept(g.f(r2));
    L7:
        return ((g0) this.f158b).p(((Path) r2).getFileName());
    }

    @Override // j$.time.temporal.l
    public Object d(j$.time.e r2) {
        if (r2 != j$.time.temporal.q.f463a) goto L7;
        return (ZoneId) this.f158b;
    L7:
        return super.d(r2);
    }

    @Override // j$.time.temporal.l
    public boolean i(j$.time.temporal.p r1) {
        return false;
    }

    @Override // j$.time.temporal.l
    public long k(j$.time.temporal.p r2) {
        throw new r(j$.time.c.a("Unsupported field: ", r2));
    }

    @Override // j$.nio.file.attribute.FileAttribute
    public String name() {
        switch(this.f157a) {
            case 3: goto L6;
            default: goto L4;
        };
    L4:
        return "posix:permissions";
    L6:
        return "posix:permissions";
    }

    @Override // j$.nio.file.g0
    public boolean p(Path r1) {
        return ((Pattern) this.f158b).matcher(r1.toString()).matches();
    }

    @Override // j$.nio.file.attribute.FileAttribute
    public Object value() {
        int r0 = this.f157a;
        Object r1 = this.f158b;
        switch(r0) {
            case 3: goto L7;
            default: goto L5;
        };
    L5:
        return Collections.unmodifiableSet((Set) r1);
    L7:
        return Collections.unmodifiableSet(g.m((Set) ((java.nio.file.attribute.FileAttribute) r1).value()));
    }
}
