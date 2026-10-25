package com.exteragram.messenger.nowplaying;

import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.Result;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class NowPlayingController$dbUpdateNowPlaying$2$1<T> implements Consumer {
    final /* synthetic */ CancellableContinuation<Integer> $continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public NowPlayingController$dbUpdateNowPlaying$2$1(CancellableContinuation<? super Integer> r1) {
        this.$continuation = r1;
    }

    public final void accept(Integer r1) {
        this.$continuation.resumeWith(Result.m2018constructorimpl(r1));
    }

    @Override // java.util.function.Consumer
    public /* bridge */ /* synthetic */ void accept(Object r1) {
        accept((Integer) r1);
    }
}
