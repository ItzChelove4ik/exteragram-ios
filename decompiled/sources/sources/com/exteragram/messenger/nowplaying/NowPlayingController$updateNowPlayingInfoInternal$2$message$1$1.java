package com.exteragram.messenger.nowplaying;

import kotlin.Metadata;
import kotlin.Result;
import kotlinx.coroutines.CancellableContinuation;
import org.telegram.messenger.Utilities;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class NowPlayingController$updateNowPlayingInfoInternal$2$message$1$1<T> implements Utilities.Callback {
    final /* synthetic */ CancellableContinuation<String> $continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public NowPlayingController$updateNowPlayingInfoInternal$2$message$1$1(CancellableContinuation<? super String> r1) {
        this.$continuation = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public /* bridge */ /* synthetic */ void run(Object r1) {
        run((String) r1);
    }

    public final void run(String r1) {
        this.$continuation.resumeWith(Result.m2018constructorimpl(r1));
    }
}
