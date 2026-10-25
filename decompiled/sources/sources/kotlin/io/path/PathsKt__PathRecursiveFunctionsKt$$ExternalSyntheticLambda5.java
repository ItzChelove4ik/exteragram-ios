package kotlin.io.path;

import j$.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* loaded from: classes5.dex */
public final /* synthetic */ class PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda5 implements Function2 {
    public final /* synthetic */ ArrayList f$0;
    public final /* synthetic */ Function3 f$1;
    public final /* synthetic */ Path f$2;
    public final /* synthetic */ Path f$3;
    public final /* synthetic */ Path f$4;

    public /* synthetic */ PathsKt__PathRecursiveFunctionsKt$$ExternalSyntheticLambda5(ArrayList r1, Function3 r2, Path r3, Path r4, Path r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object r8, Object r9) {
        return PathsKt__PathRecursiveFunctionsKt.$r8$lambda$IkZ125cv9leHdM_R4zOtmPWi6nc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (Path) r8, (IOException) r9);
    }
}
