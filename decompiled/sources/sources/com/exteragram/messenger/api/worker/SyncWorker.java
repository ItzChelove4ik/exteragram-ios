package com.exteragram.messenger.api.worker;

import android.content.Context;
import androidx.work.CoroutineWorker;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.exteragram.messenger.api.ApiController;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\tH\u0096@¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/exteragram/messenger/api/worker/SyncWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SyncWorker extends CoroutineWorker {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.api.worker.SyncWorker", f = "SyncWorker.kt", i = {}, l = {14}, m = "doWork", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.api.worker.SyncWorker$doWork$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SyncWorker this$0;

        public AnonymousClass1(SyncWorker r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.doWork(this);
        }
    }

    public SyncWorker(Context r1, WorkerParameters r2) {
        super(r1, r2);
    }

    @Override // androidx.work.CoroutineWorker
    public Object doWork(Continuation<? super ListenableWorker.Result> r8) {
        if ((r8 instanceof AnonymousClass1) == false) goto L8;
        AnonymousClass1 r0 = (AnonymousClass1) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L8;
        r0.label = r1 - Integer.MIN_VALUE;
    L7:
        AnonymousClass1 r4 = r0;
        Object r7 = r4.result;
        Object r82 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r02 = r4.label;
        if (r02 == 0) goto L15;
        if (r02 != 1) goto L13;
        ResultKt.throwOnFailure(r7);
    L19:
        if (((Boolean) r7).booleanValue() == false) goto L23;
        return ListenableWorker.Result.success();
    L23:
        return ListenableWorker.Result.retry();
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r7);
        ApiController r12 = ApiController.INSTANCE;
        r4.label = 1;
        r7 = ApiController.performSync$default(r12, null, false, r4, 3, null);
        if (r7 != r82) goto L19;
        return r82;
    L8:
        r0 = new AnonymousClass1(this, r8);
        goto L7
    }
}
