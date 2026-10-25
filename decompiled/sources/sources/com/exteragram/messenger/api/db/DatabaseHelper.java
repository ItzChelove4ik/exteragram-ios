package com.exteragram.messenger.api.db;

import com.exteragram.messenger.api.dto.AddedRegDateDTO;
import com.exteragram.messenger.api.dto.BoostySubscriberDTO;
import com.exteragram.messenger.api.dto.NowPlayingInfoDTO;
import com.exteragram.messenger.api.dto.ProfileDTO;
import com.sun.jna.Callback;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.FileLog;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0086@¢\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\tH\u0086@¢\u0006\u0002\u0010\u000bJ \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013H\u0007J(\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00142\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00170\u0013H\u0007J\u001e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0013H\u0007J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u000eH\u0007J\u001c\u0010\u001b\u001a\u00020\u00072\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\tH\u0086@¢\u0006\u0002\u0010\u000bJ\u001c\u0010\u001e\u001a\u00020\u00102\u0012\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\t0\u0013H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/exteragram/messenger/api/db/DatabaseHelper;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "insertProfiles", _UrlKt.FRAGMENT_ENCODE_SET, "profiles", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/api/dto/ProfileDTO;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteProfiles", "ids", _UrlKt.FRAGMENT_ENCODE_SET, "getNowPlaying", _UrlKt.FRAGMENT_ENCODE_SET, "id", Callback.METHOD_NAME, "Ljava/util/function/Consumer;", "Lcom/exteragram/messenger/api/dto/NowPlayingInfoDTO;", "updateNowPlaying", "newNowPlaying", _UrlKt.FRAGMENT_ENCODE_SET, "isRegDateAdded", "userId", "setRegDateAdded", "insertBoostySubscribers", "subscribers", "Lcom/exteragram/messenger/api/dto/BoostySubscriberDTO;", "getBoostySubscribers", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DatabaseHelper {
    public static final DatabaseHelper INSTANCE = null;
    private static final CoroutineScope scope = null;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.api.db.DatabaseHelper", f = "DatabaseHelper.kt", i = {0}, l = {42}, m = "deleteProfiles", n = {"ids"}, s = {"L$0"})
    /* renamed from: com.exteragram.messenger.api.db.DatabaseHelper$deleteProfiles$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DatabaseHelper this$0;

        public AnonymousClass1(DatabaseHelper r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.deleteProfiles(null, this);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.api.db.DatabaseHelper$getBoostySubscribers$1", f = "DatabaseHelper.kt", i = {}, l = {127}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nDatabaseHelper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DatabaseHelper.kt\ncom/exteragram/messenger/api/db/DatabaseHelper$getBoostySubscribers$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,139:1\n1080#2:140\n*S KotlinDebug\n*F\n+ 1 DatabaseHelper.kt\ncom/exteragram/messenger/api/db/DatabaseHelper$getBoostySubscribers$1\n*L\n128#1:140\n*E\n"})
    /* renamed from: com.exteragram.messenger.api.db.DatabaseHelper$getBoostySubscribers$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01341 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Consumer<List<BoostySubscriberDTO>> $callback;
        int label;

        public C01341(Consumer<List<BoostySubscriberDTO>> r1, Continuation<? super C01341> r2) {
            this.$callback = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new C01341(this.$callback, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01341) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L11;
            if (r1 != 1) goto L9;
            ResultKt.throwOnFailure(r4);     // Catch: Throwable -> L7 CancellationException -> L20
        L15:
            List<BoostySubscriberDTO> r42 = CollectionsKt.sortedWith((List) r4, new DatabaseHelper$getBoostySubscribers$1$invokeSuspend$$inlined$sortedByDescending$1());     // Catch: Throwable -> L7 CancellationException -> L20
            this.$callback.accept(r42);     // Catch: Throwable -> L7 CancellationException -> L20
        L19:
            return Unit.INSTANCE;
        L9:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L11:
            ResultKt.throwOnFailure(r4);
            BoostySubscriberDao r43 = ExteraDatabase.Companion.getInstance().boostySubscriberDao();     // Catch: Throwable -> L7 CancellationException -> L20
            this.label = 1;     // Catch: Throwable -> L7 CancellationException -> L20
            r4 = r43.getAll(this);     // Catch: Throwable -> L7 CancellationException -> L20
            if (r4 != r0) goto L15;
            return r0;
        L20:
            e = move-exception;
            throw e;
        L7:
            th = move-exception;
            FileLog.e(th);
            this.$callback.accept(CollectionsKt.emptyList());
            goto L19
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.api.db.DatabaseHelper$getNowPlaying$1", f = "DatabaseHelper.kt", i = {}, l = {56}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.api.db.DatabaseHelper$getNowPlaying$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01351 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Consumer<NowPlayingInfoDTO> $callback;
        final /* synthetic */ long $id;
        int label;

        public C01351(long r1, Consumer<NowPlayingInfoDTO> r3, Continuation<? super C01351> r4) {
            this.$id = r1;
            this.$callback = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            return new C01351(this.$id, this.$callback, r4);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01351) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L11;
            if (r1 != 1) goto L9;
            ResultKt.throwOnFailure(r7);     // Catch: Throwable -> L7 CancellationException -> L24
        L15:
            ProfileDTO r72 = (ProfileDTO) r7;     // Catch: Throwable -> L7 CancellationException -> L24
            Consumer<NowPlayingInfoDTO> r02 = this.$callback;     // Catch: Throwable -> L7 CancellationException -> L24
            if (r72 == null) goto L18;
            NowPlayingInfoDTO r73 = r72.getNowPlaying();     // Catch: Throwable -> L7 CancellationException -> L24
        L19:
            r02.accept(r73);     // Catch: Throwable -> L7 CancellationException -> L24
        L23:
            return Unit.INSTANCE;
        L18:
            r73 = null;
            goto L19
        L9:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L11:
            ResultKt.throwOnFailure(r7);
            ProfileDao r74 = ExteraDatabase.Companion.getInstance().profileDao();     // Catch: Throwable -> L7 CancellationException -> L24
            long r4 = this.$id;     // Catch: Throwable -> L7 CancellationException -> L24
            this.label = 1;     // Catch: Throwable -> L7 CancellationException -> L24
            r7 = r74.getById(r4, this);     // Catch: Throwable -> L7 CancellationException -> L24
            if (r7 != r0) goto L15;
            return r0;
        L24:
            e = move-exception;
            throw e;
        L7:
            th = move-exception;
            FileLog.e(th);
            this.$callback.accept(null);
            goto L23
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.api.db.DatabaseHelper", f = "DatabaseHelper.kt", i = {0}, l = {113}, m = "insertBoostySubscribers", n = {"subscribers"}, s = {"L$0"})
    /* renamed from: com.exteragram.messenger.api.db.DatabaseHelper$insertBoostySubscribers$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01361 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DatabaseHelper this$0;

        public C01361(DatabaseHelper r1, Continuation<? super C01361> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.insertBoostySubscribers(null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.api.db.DatabaseHelper", f = "DatabaseHelper.kt", i = {0}, l = {30}, m = "insertProfiles", n = {"profiles"}, s = {"L$0"})
    /* renamed from: com.exteragram.messenger.api.db.DatabaseHelper$insertProfiles$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01371 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ DatabaseHelper this$0;

        public C01371(DatabaseHelper r1, Continuation<? super C01371> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.insertProfiles(null, this);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.api.db.DatabaseHelper$isRegDateAdded$1", f = "DatabaseHelper.kt", i = {}, l = {87}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.api.db.DatabaseHelper$isRegDateAdded$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01381 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Consumer<Boolean> $callback;
        final /* synthetic */ long $userId;
        int label;

        public C01381(long r1, Consumer<Boolean> r3, Continuation<? super C01381> r4) {
            this.$userId = r1;
            this.$callback = r3;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            return new C01381(this.$userId, this.$callback, r4);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01381) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L11;
            if (r1 != 1) goto L9;
            ResultKt.throwOnFailure(r6);     // Catch: Throwable -> L7 CancellationException -> L20
        L15:
            boolean r62 = ((Boolean) r6).booleanValue();     // Catch: Throwable -> L7 CancellationException -> L20
            this.$callback.accept(Boxing.boxBoolean(r62));     // Catch: Throwable -> L7 CancellationException -> L20
        L19:
            return Unit.INSTANCE;
        L9:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L11:
            ResultKt.throwOnFailure(r6);
            AddedRegDateDao r63 = ExteraDatabase.Companion.getInstance().addedRegDateDao();     // Catch: Throwable -> L7 CancellationException -> L20
            long r3 = this.$userId;     // Catch: Throwable -> L7 CancellationException -> L20
            this.label = 1;     // Catch: Throwable -> L7 CancellationException -> L20
            r6 = r63.isAdded(r3, this);     // Catch: Throwable -> L7 CancellationException -> L20
            if (r6 != r0) goto L15;
            return r0;
        L20:
            e = move-exception;
            throw e;
        L7:
            th = move-exception;
            FileLog.e(th);
            this.$callback.accept(Boxing.boxBoolean(false));
            goto L19
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.api.db.DatabaseHelper$setRegDateAdded$1", f = "DatabaseHelper.kt", i = {}, l = {102}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.api.db.DatabaseHelper$setRegDateAdded$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01391 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $userId;
        int label;

        public C01391(long r1, Continuation<? super C01391> r3) {
            this.$userId = r1;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            return new C01391(this.$userId, r4);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01391) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r6);     // Catch: Throwable -> L13 CancellationException -> L17
        L19:
        L16:
            return Unit.INSTANCE;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r6);
            AddedRegDateDao r62 = ExteraDatabase.Companion.getInstance().addedRegDateDao();     // Catch: Throwable -> L13 CancellationException -> L17
            AddedRegDateDTO r12 = new AddedRegDateDTO(this.$userId);     // Catch: Throwable -> L13 CancellationException -> L17
            this.label = 1;     // Catch: Throwable -> L13 CancellationException -> L17
            if (r62.insert(r12, this) != r0) goto L19;
            return r0;
        L17:
            e = move-exception;
            throw e;
        L13:
            th = move-exception;
            FileLog.e(th);
            goto L16
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.api.db.DatabaseHelper$updateNowPlaying$1", f = "DatabaseHelper.kt", i = {}, l = {72}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.api.db.DatabaseHelper$updateNowPlaying$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01401 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Consumer<Integer> $callback;
        final /* synthetic */ long $id;
        final /* synthetic */ NowPlayingInfoDTO $newNowPlaying;
        int label;

        public C01401(long r1, NowPlayingInfoDTO r3, Consumer<Integer> r4, Continuation<? super C01401> r5) {
            this.$id = r1;
            this.$newNowPlaying = r3;
            this.$callback = r4;
            super(2, r5);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
            return new C01401(this.$id, this.$newNowPlaying, this.$callback, r8);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01401) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L11;
            if (r1 != 1) goto L9;
            ResultKt.throwOnFailure(r6);     // Catch: Throwable -> L7 CancellationException -> L20
        L15:
            int r62 = ((Number) r6).intValue();     // Catch: Throwable -> L7 CancellationException -> L20
            this.$callback.accept(Boxing.boxInt(r62));     // Catch: Throwable -> L7 CancellationException -> L20
        L19:
            return Unit.INSTANCE;
        L9:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L11:
            ResultKt.throwOnFailure(r6);
            ProfileDao r63 = ExteraDatabase.Companion.getInstance().profileDao();     // Catch: Throwable -> L7 CancellationException -> L20
            long r3 = this.$id;     // Catch: Throwable -> L7 CancellationException -> L20
            NowPlayingInfoDTO r12 = this.$newNowPlaying;     // Catch: Throwable -> L7 CancellationException -> L20
            this.label = 1;     // Catch: Throwable -> L7 CancellationException -> L20
            r6 = r63.updateNowPlaying(r3, r12, this);     // Catch: Throwable -> L7 CancellationException -> L20
            if (r6 != r0) goto L15;
            return r0;
        L20:
            e = move-exception;
            throw e;
        L7:
            th = move-exception;
            FileLog.e(th);
            this.$callback.accept(Boxing.boxInt(0));
            goto L19
        }
    }

    static {
        INSTANCE = new DatabaseHelper();
        scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    }

    private DatabaseHelper() {
    }

    @JvmStatic
    public static final void getBoostySubscribers(Consumer<List<BoostySubscriberDTO>> r6) {
        BuildersKt.launch$default(scope, null, null, new C01341(r6, null), 3, null);
    }

    @JvmStatic
    public static final void getNowPlaying(long r6, Consumer<NowPlayingInfoDTO> r8) {
        BuildersKt.launch$default(scope, null, null, new C01351(r6, r8, null), 3, null);
    }

    @JvmStatic
    public static final void isRegDateAdded(long r6, Consumer<Boolean> r8) {
        BuildersKt.launch$default(scope, null, null, new C01381(r6, r8, null), 3, null);
    }

    @JvmStatic
    public static final void setRegDateAdded(long r6) {
        BuildersKt.launch$default(scope, null, null, new C01391(r6, null), 3, null);
    }

    @JvmStatic
    public static final void updateNowPlaying(long r7, NowPlayingInfoDTO r9, Consumer<Integer> r10) {
        BuildersKt.launch$default(scope, null, null, new C01401(r7, r9, r10, null), 3, null);
    }

    public final Object deleteProfiles(List<Long> r5, Continuation<? super Boolean> r6) {
        if ((r6 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r4 = r0.result;
        Object r62 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r12 = r0.label;
        boolean r2 = true;
        if (r12 == 0) goto L16;
        if (r12 != 1) goto L14;
        List r52 = (List) r0.L$0;
        ResultKt.throwOnFailure(r4);     // Catch: Throwable -> L20 CancellationException -> L24
    L26:
    L23:
        return Boxing.boxBoolean(r2);
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        ResultKt.throwOnFailure(r4);
        ProfileDao r42 = ExteraDatabase.Companion.getInstance().profileDao();     // Catch: Throwable -> L20 CancellationException -> L24
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r5);     // Catch: Throwable -> L20 CancellationException -> L24
        r0.label = 1;     // Catch: Throwable -> L20 CancellationException -> L24
        if (r42.deleteProfiles(r5, r0) != r62) goto L26;
        return r62;
    L24:
        e = move-exception;
        throw e;
    L20:
        th = move-exception;
        FileLog.e(th);
        r2 = false;
    L7:
        r0 = new AnonymousClass1(this, r6);
        goto L8
    }

    public final Object insertBoostySubscribers(List<BoostySubscriberDTO> r5, Continuation<? super Boolean> r6) {
        if ((r6 instanceof C01361) == false) goto L7;
        C01361 r0 = (C01361) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r4 = r0.result;
        Object r62 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r12 = r0.label;
        boolean r2 = true;
        if (r12 == 0) goto L16;
        if (r12 != 1) goto L14;
        List r52 = (List) r0.L$0;
        ResultKt.throwOnFailure(r4);     // Catch: Throwable -> L20 CancellationException -> L24
    L26:
    L23:
        return Boxing.boxBoolean(r2);
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        ResultKt.throwOnFailure(r4);
        BoostySubscriberDao r42 = ExteraDatabase.Companion.getInstance().boostySubscriberDao();     // Catch: Throwable -> L20 CancellationException -> L24
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r5);     // Catch: Throwable -> L20 CancellationException -> L24
        r0.label = 1;     // Catch: Throwable -> L20 CancellationException -> L24
        if (r42.replaceSubscribers(r5, r0) != r62) goto L26;
        return r62;
    L24:
        e = move-exception;
        throw e;
    L20:
        th = move-exception;
        FileLog.e(th);
        r2 = false;
    L7:
        r0 = new C01361(this, r6);
        goto L8
    }

    public final Object insertProfiles(List<ProfileDTO> r5, Continuation<? super Boolean> r6) {
        if ((r6 instanceof C01371) == false) goto L7;
        C01371 r0 = (C01371) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r4 = r0.result;
        Object r62 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r12 = r0.label;
        boolean r2 = true;
        if (r12 == 0) goto L16;
        if (r12 != 1) goto L14;
        List r52 = (List) r0.L$0;
        ResultKt.throwOnFailure(r4);     // Catch: Throwable -> L20 CancellationException -> L24
    L26:
    L23:
        return Boxing.boxBoolean(r2);
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        ResultKt.throwOnFailure(r4);
        ProfileDao r42 = ExteraDatabase.Companion.getInstance().profileDao();     // Catch: Throwable -> L20 CancellationException -> L24
        r0.L$0 = SpillingKt.nullOutSpilledVariable(r5);     // Catch: Throwable -> L20 CancellationException -> L24
        r0.label = 1;     // Catch: Throwable -> L20 CancellationException -> L24
        if (r42.insertAll(r5, r0) != r62) goto L26;
        return r62;
    L24:
        e = move-exception;
        throw e;
    L20:
        th = move-exception;
        FileLog.e(th);
        r2 = false;
    L7:
        r0 = new C01371(this, r6);
        goto L8
    }
}
