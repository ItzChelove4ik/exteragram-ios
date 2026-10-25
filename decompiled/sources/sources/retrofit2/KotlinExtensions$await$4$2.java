package retrofit2;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlinx.coroutines.CancellableContinuation;
import okhttp3.internal.url._UrlKt;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0001J(\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007H\u0016J \u0010\b\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"retrofit2/KotlinExtensions$await$4$2", "Lretrofit2/Callback;", "onResponse", _UrlKt.FRAGMENT_ENCODE_SET, "call", "Lretrofit2/Call;", PluginsConstants.RESPONSE, "Lretrofit2/Response;", "onFailure", "t", _UrlKt.FRAGMENT_ENCODE_SET, "retrofit"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class KotlinExtensions$await$4$2<T> implements Callback<T> {
    final /* synthetic */ CancellableContinuation<T> $continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public KotlinExtensions$await$4$2(CancellableContinuation<? super T> r1) {
        this.$continuation = r1;
    }

    @Override // retrofit2.Callback
    public void onFailure(Call<T> r1, Throwable r2) {
        CancellableContinuation<T> r0 = this.$continuation;
        Result.Companion r12 = Result.Companion;
        r0.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r2)));
    }

    @Override // retrofit2.Callback
    public void onResponse(Call<T> r1, Response<T> r2) {
        boolean r12 = r2.isSuccessful();
        CancellableContinuation<T> r0 = this.$continuation;
        if (r12 == false) goto L6;
        Result.Companion r13 = Result.Companion;
        r0.resumeWith(Result.m2018constructorimpl(r2.body()));
        return;
    L6:
        Result.Companion r14 = Result.Companion;
        r0.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(new HttpException(r2))));
    }
}
