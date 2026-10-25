package com.exteragram.messenger.api.db;

import com.exteragram.messenger.api.dto.BoostySubscriberDTO;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H§@¢\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H§@¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u0003H§@¢\u0006\u0002\u0010\tJ\u001c\u0010\u000b\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0097@¢\u0006\u0002\u0010\u0007¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/api/db/BoostySubscriberDao;", _UrlKt.FRAGMENT_ENCODE_SET, "insertAll", _UrlKt.FRAGMENT_ENCODE_SET, "subscribers", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/api/dto/BoostySubscriberDTO;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "replaceSubscribers", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public interface BoostySubscriberDao {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        @Deprecated
        public static Object replaceSubscribers(BoostySubscriberDao r0, List<BoostySubscriberDTO> r1, Continuation<? super Unit> r2) {
            return BoostySubscriberDao.access$replaceSubscribers$jd(r0, r1, r2);
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.api.db.BoostySubscriberDao", f = "BoostySubscriberDao.kt", i = {0, 0, 1, 1}, l = {34, 35}, m = "replaceSubscribers$suspendImpl", n = {"$this", "subscribers", "$this", "subscribers"}, s = {"L$0", "L$1", "L$0", "L$1"})
    /* renamed from: com.exteragram.messenger.api.db.BoostySubscriberDao$replaceSubscribers$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ BoostySubscriberDao this$0;

        public AnonymousClass1(BoostySubscriberDao r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return BoostySubscriberDao.replaceSubscribers$suspendImpl(this.this$0, null, this);
        }
    }

    static /* synthetic */ Object access$replaceSubscribers$jd(BoostySubscriberDao r0, List r1, Continuation r2) {
        return super.replaceSubscribers(r1, r2);
    }

    static /* synthetic */ Object replaceSubscribers$suspendImpl(BoostySubscriberDao r5, List<BoostySubscriberDTO> r6, Continuation<? super Unit> r7) {
        if ((r7 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r7;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        List r52 = (List) r0.L$1;
        BoostySubscriberDao r53 = (BoostySubscriberDao) r0.L$0;
        ResultKt.throwOnFailure(r72);
    L23:
        return Unit.INSTANCE;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        r6 = (List) r0.L$1;
        r5 = (BoostySubscriberDao) r0.L$0;
        ResultKt.throwOnFailure(r72);
    L19:
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r5);
        r0.L$1 = SpillingKt.nullOutSpilledVariable(r6);
        r0.label = 2;
        if (r5.insertAll(r6, r0) != r12) goto L23;
    L21:
        return r12;
    L16:
        ResultKt.throwOnFailure(r72);
        r0.L$0 = r5;
        r0.L$1 = r6;
        r0.label = 1;
        if (r5.deleteAll(r0) != r12) goto L19;
    L7:
        r0 = new AnonymousClass1(r5, r7);
        goto L8
    }

    Object deleteAll(Continuation<? super Unit> r1);

    Object getAll(Continuation<? super List<BoostySubscriberDTO>> r1);

    Object insertAll(List<BoostySubscriberDTO> r1, Continuation<? super Unit> r2);

    default Object replaceSubscribers(List<BoostySubscriberDTO> r1, Continuation<? super Unit> r2) {
        return replaceSubscribers$suspendImpl(this, r1, r2);
    }
}
