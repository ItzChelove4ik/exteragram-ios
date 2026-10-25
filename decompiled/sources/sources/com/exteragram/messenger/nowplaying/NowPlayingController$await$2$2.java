package com.exteragram.messenger.nowplaying;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlinx.coroutines.CancellableContinuation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/exteragram/messenger/nowplaying/NowPlayingController$await$2$2", "Lokhttp3/Callback;", "onResponse", _UrlKt.FRAGMENT_ENCODE_SET, "call", "Lokhttp3/Call;", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "onFailure", "e", "Ljava/io/IOException;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class NowPlayingController$await$2$2 implements Callback {
    final /* synthetic */ CancellableContinuation<Response> $continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public NowPlayingController$await$2$2(CancellableContinuation<? super Response> r1) {
        this.$continuation = r1;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call r1, IOException r2) {
        if (this.$continuation.isCancelled() == false) goto L5;
        return;
    L5:
        CancellableContinuation<Response> r0 = this.$continuation;
        Result.Companion r12 = Result.Companion;
        r0.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r2)));
    }

    @Override // okhttp3.Callback
    public void onResponse(Call r1, Response r2) {
        this.$continuation.resumeWith(Result.m2018constructorimpl(r2));
    }
}
