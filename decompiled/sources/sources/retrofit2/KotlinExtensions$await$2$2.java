package retrofit2;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.KotlinNullPointerException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlinx.coroutines.CancellableContinuation;
import okhttp3.internal.url._UrlKt;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J$\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0016J\u001e\u0010\b\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"retrofit2/KotlinExtensions$await$2$2", "Lretrofit2/Callback;", "onResponse", _UrlKt.FRAGMENT_ENCODE_SET, "call", "Lretrofit2/Call;", PluginsConstants.RESPONSE, "Lretrofit2/Response;", "onFailure", "t", _UrlKt.FRAGMENT_ENCODE_SET, "retrofit"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class KotlinExtensions$await$2$2<T> implements Callback<T> {
    final /* synthetic */ CancellableContinuation<T> $continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public KotlinExtensions$await$2$2(CancellableContinuation<? super T> r1) {
        this.$continuation = r1;
    }

    @Override // retrofit2.Callback
    public void onFailure(Call<T> r1, Throwable r2) {
        CancellableContinuation<T> r0 = this.$continuation;
        Result.Companion r12 = Result.Companion;
        r0.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r2)));
    }

    @Override // retrofit2.Callback
    public void onResponse(Call<T> r4, Response<T> r5) {
        if (r5.isSuccessful() == false) goto L10;
        T r52 = r5.body();
        if (r52 != null) goto L8;
        Invocation r42 = (Invocation) r4.request().tag(Invocation.class);
        KotlinNullPointerException r0 = new KotlinNullPointerException("Response from " + r42.service().getName() + '.' + r42.method().getName() + " was null but response body type was declared as non-null");
        CancellableContinuation<T> r3 = this.$continuation;
        Result.Companion r43 = Result.Companion;
        r3.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r0)));
        return;
    L8:
        this.$continuation.resumeWith(Result.m2018constructorimpl(r52));
        return;
    L10:
        CancellableContinuation<T> r32 = this.$continuation;
        Result.Companion r44 = Result.Companion;
        r32.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(new HttpException(r5))));
    }
}
