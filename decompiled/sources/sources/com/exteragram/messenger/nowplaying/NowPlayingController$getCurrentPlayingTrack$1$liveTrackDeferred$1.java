package com.exteragram.messenger.nowplaying;

import com.exteragram.messenger.api.dto.NowPlayingDTO;
import com.exteragram.messenger.api.network.ApiClient;
import com.exteragram.messenger.api.network.ApiService;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.FileLog;
import retrofit2.Response;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/exteragram/messenger/api/dto/NowPlayingDTO;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.exteragram.messenger.nowplaying.NowPlayingController$getCurrentPlayingTrack$1$liveTrackDeferred$1", f = "NowPlayingController.kt", i = {}, l = {94}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class NowPlayingController$getCurrentPlayingTrack$1$liveTrackDeferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NowPlayingDTO>, Object> {
    final /* synthetic */ boolean $checkApi;
    final /* synthetic */ long $userId;
    int label;

    public NowPlayingController$getCurrentPlayingTrack$1$liveTrackDeferred$1(boolean r1, long r2, Continuation<? super NowPlayingController$getCurrentPlayingTrack$1$liveTrackDeferred$1> r4) {
        this.$checkApi = r1;
        this.$userId = r2;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r4, Continuation<?> r5) {
        return new NowPlayingController$getCurrentPlayingTrack$1$liveTrackDeferred$1(this.$checkApi, this.$userId, r5);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super NowPlayingDTO> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super NowPlayingDTO> r2) {
        return ((NowPlayingController$getCurrentPlayingTrack$1$liveTrackDeferred$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L11;
        if (r1 != 1) goto L9;
        ResultKt.throwOnFailure(r7);     // Catch: Throwable -> L7
    L19:
        Response r72 = (Response) r7;     // Catch: Throwable -> L7
        if (r72.isSuccessful() == false) goto L23;
        return (NowPlayingDTO) r72.body();
    L23:
        return null;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L11:
        ResultKt.throwOnFailure(r7);
        if (this.$checkApi == false) goto L25;
        ApiClient r73 = ApiClient.INSTANCE;
        if (r73.getRequestsEnabled$TMessagesProj() == false) goto L25;
        ApiService r74 = r73.getApiService();     // Catch: Throwable -> L7
        long r4 = this.$userId;     // Catch: Throwable -> L7
        this.label = 1;     // Catch: Throwable -> L7
        r7 = r74.getCurrentPlayingTrack(r4, this);     // Catch: Throwable -> L7
        if (r7 != r0) goto L19;
        return r0;
    L25:
        return null;
    L7:
        th = move-exception;
        FileLog.e(th);
        goto L25
    }
}
