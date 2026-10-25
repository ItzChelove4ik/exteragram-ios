package com.exteragram.messenger.nowplaying;

import com.exteragram.messenger.api.dto.NowPlayingDTO;
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
import org.telegram.tgnet.TLRPC;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/exteragram/messenger/api/dto/NowPlayingDTO;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.exteragram.messenger.nowplaying.NowPlayingController$getCurrentPlayingTrack$1$savedTrackDeferred$1", f = "NowPlayingController.kt", i = {}, l = {103}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class NowPlayingController$getCurrentPlayingTrack$1$savedTrackDeferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NowPlayingDTO>, Object> {
    final /* synthetic */ TLRPC.Document $savedMusic;
    int label;

    public NowPlayingController$getCurrentPlayingTrack$1$savedTrackDeferred$1(TLRPC.Document r1, Continuation<? super NowPlayingController$getCurrentPlayingTrack$1$savedTrackDeferred$1> r2) {
        this.$savedMusic = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
        return new NowPlayingController$getCurrentPlayingTrack$1$savedTrackDeferred$1(this.$savedMusic, r2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super NowPlayingDTO> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super NowPlayingDTO> r2) {
        return ((NowPlayingController$getCurrentPlayingTrack$1$savedTrackDeferred$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L9;
        if (r1 != 1) goto L7;
        ResultKt.throwOnFailure(r4);
        return r4;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        ResultKt.throwOnFailure(r4);
        NowPlayingController r42 = NowPlayingController.INSTANCE;
        TLRPC.Document r12 = this.$savedMusic;
        this.label = 1;
        Object r3 = NowPlayingController.access$processSavedMusic(r42, r12, this);
        if (r3 != r0) goto L12;
        return r0;
    L12:
        return r3;
    }
}
