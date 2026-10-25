package com.exteragram.messenger.nowplaying;

import com.exteragram.messenger.api.dto.NowPlayingInfoDTO;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.Result;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class NowPlayingController$dbGetNowPlaying$2$1<T> implements Consumer {
    final /* synthetic */ CancellableContinuation<NowPlayingInfoDTO> $continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public NowPlayingController$dbGetNowPlaying$2$1(CancellableContinuation<? super NowPlayingInfoDTO> r1) {
        this.$continuation = r1;
    }

    public final void accept(NowPlayingInfoDTO r1) {
        this.$continuation.resumeWith(Result.m2018constructorimpl(r1));
    }

    @Override // java.util.function.Consumer
    public /* bridge */ /* synthetic */ void accept(Object r1) {
        accept((NowPlayingInfoDTO) r1);
    }
}
