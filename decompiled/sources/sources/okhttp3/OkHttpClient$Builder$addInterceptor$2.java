package okhttp3;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Interceptor;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
@SourceDebugExtension({"SMAP\nOkHttpClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OkHttpClient.kt\nokhttp3/OkHttpClient$Builder$addInterceptor$2\n*L\n1#1,1388:1\n*E\n"})
/* loaded from: classes5.dex */
public final class OkHttpClient$Builder$addInterceptor$2 implements Interceptor {
    final /* synthetic */ Function1<Interceptor.Chain, Response> $block;

    /* JADX WARN: Multi-variable type inference failed */
    public OkHttpClient$Builder$addInterceptor$2(Function1<? super Interceptor.Chain, Response> r1) {
        this.$block = r1;
    }

    @Override // okhttp3.Interceptor
    public final Response intercept(Interceptor.Chain r1) {
        return this.$block.invoke(r1);
    }
}
