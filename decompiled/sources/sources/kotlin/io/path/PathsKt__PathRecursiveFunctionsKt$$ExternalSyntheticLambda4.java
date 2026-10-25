package kotlin.io.path;

import j$.nio.file.Path;
import j$.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* loaded from: classes5.dex */
public final /* synthetic */ class PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda4 implements Function2 {
    public final /* synthetic */ ArrayList f$0;
    public final /* synthetic */ Function3 f$1;
    public final /* synthetic */ Path f$2;
    public final /* synthetic */ Path f$3;
    public final /* synthetic */ Path f$4;
    public final /* synthetic */ Function3 f$5;

    public /* synthetic */ PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda4(ArrayList r1, Function3 r2, Path r3, Path r4, Path r5, Function3 r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object r9, Object r10) {
        return PathsKt__PathRecursiveFunctionsKt.m3242$r8$lambda$Q20yqNtXDRXNb_l9dEjE2GCB44(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (Path) r9, (BasicFileAttributes) r10);
    }
}
