package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.jvm.functions.Function1;
import okhttp3.internal.cache.DiskLruCache;

/* loaded from: classes5.dex */
public final /* synthetic */ class DiskLruCache$Editor$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ DiskLruCache f$0;
    public final /* synthetic */ DiskLruCache.Editor f$1;

    public /* synthetic */ DiskLruCache$Editor$$ExternalSyntheticLambda0(DiskLruCache r1, DiskLruCache.Editor r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return DiskLruCache.Editor.$r8$lambda$A4Vm2KUtMTjVaHywVLE7iXxpmms(this.f$0, this.f$1, (IOException) r2);
    }
}
