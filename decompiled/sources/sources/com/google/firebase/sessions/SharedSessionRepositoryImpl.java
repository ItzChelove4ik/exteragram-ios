package com.google.firebase.sessions;

import android.util.Log;
import androidx.datastore.core.DataStore;
import com.android.dx.io.Opcodes;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.sessions.api.FirebaseSessionsDependencies;
import com.google.firebase.sessions.api.SessionSubscriber;
import com.google.firebase.sessions.settings.SessionsSettings;
import java.util.Iterator;
import java.util.Map;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.Duration;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b#\b\u0001\u0018\u00002\u00020\u0001:\u0001=BI\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015H\u0082@¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001e\u0010\u001dJ\u0017\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001f\u0010\u001dJ\u000f\u0010 \u001a\u00020\u0017H\u0016¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\"\u0010!R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010#R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010$R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010%R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010&R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010'R\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010(R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010)R\"\u0010*\u001a\u00020\u000b8\u0000@\u0000X\u0080.¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R$\u00101\u001a\u00020\u001b2\u0006\u00100\u001a\u00020\u001b8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b1\u00103R\u0016\u00104\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u00102R\"\u00105\u001a\u00020\u00158\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u0016\u0010;\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010<¨\u0006>"}, d2 = {"Lcom/google/firebase/sessions/SharedSessionRepositoryImpl;", "Lcom/google/firebase/sessions/SharedSessionRepository;", "Lcom/google/firebase/sessions/settings/SessionsSettings;", "sessionsSettings", "Lcom/google/firebase/sessions/SessionGenerator;", "sessionGenerator", "Lcom/google/firebase/sessions/SessionFirelogPublisher;", "sessionFirelogPublisher", "Lcom/google/firebase/sessions/TimeProvider;", "timeProvider", "Landroidx/datastore/core/DataStore;", "Lcom/google/firebase/sessions/SessionData;", "sessionDataStore", "Lcom/google/firebase/sessions/ProcessDataManager;", "processDataManager", "Lkotlin/coroutines/CoroutineContext;", "backgroundDispatcher", "<init>", "(Lcom/google/firebase/sessions/settings/SessionsSettings;Lcom/google/firebase/sessions/SessionGenerator;Lcom/google/firebase/sessions/SessionFirelogPublisher;Lcom/google/firebase/sessions/TimeProvider;Landroidx/datastore/core/DataStore;Lcom/google/firebase/sessions/ProcessDataManager;Lkotlin/coroutines/CoroutineContext;)V", _UrlKt.FRAGMENT_ENCODE_SET, "sessionId", "Lcom/google/firebase/sessions/SharedSessionRepositoryImpl$NotificationType;", "type", _UrlKt.FRAGMENT_ENCODE_SET, "notifySubscribers", "(Ljava/lang/String;Lcom/google/firebase/sessions/SharedSessionRepositoryImpl$NotificationType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sessionData", _UrlKt.FRAGMENT_ENCODE_SET, "isSessionExpired", "(Lcom/google/firebase/sessions/SessionData;)Z", "isColdStart", "isMyProcessStale", "appBackground", "()V", "appForeground", "Lcom/google/firebase/sessions/settings/SessionsSettings;", "Lcom/google/firebase/sessions/SessionGenerator;", "Lcom/google/firebase/sessions/SessionFirelogPublisher;", "Lcom/google/firebase/sessions/TimeProvider;", "Landroidx/datastore/core/DataStore;", "Lcom/google/firebase/sessions/ProcessDataManager;", "Lkotlin/coroutines/CoroutineContext;", "localSessionData", "Lcom/google/firebase/sessions/SessionData;", "getLocalSessionData$com_google_firebase_firebase_sessions", "()Lcom/google/firebase/sessions/SessionData;", "setLocalSessionData$com_google_firebase_firebase_sessions", "(Lcom/google/firebase/sessions/SessionData;)V", "value", "isInForeground", "Z", "()Z", "pendingForegroundCheck", "previousNotificationType", "Lcom/google/firebase/sessions/SharedSessionRepositoryImpl$NotificationType;", "getPreviousNotificationType$com_google_firebase_firebase_sessions", "()Lcom/google/firebase/sessions/SharedSessionRepositoryImpl$NotificationType;", "setPreviousNotificationType$com_google_firebase_firebase_sessions", "(Lcom/google/firebase/sessions/SharedSessionRepositoryImpl$NotificationType;)V", "previousSessionId", "Ljava/lang/String;", "NotificationType", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSharedSessionRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SharedSessionRepository.kt\ncom/google/firebase/sessions/SharedSessionRepositoryImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,264:1\n1863#2,2:265\n*S KotlinDebug\n*F\n+ 1 SharedSessionRepository.kt\ncom/google/firebase/sessions/SharedSessionRepositoryImpl\n*L\n206#1:265,2\n*E\n"})
/* loaded from: classes.dex */
public final class SharedSessionRepositoryImpl implements SharedSessionRepository {
    private final CoroutineContext backgroundDispatcher;
    private boolean isInForeground;
    public SessionData localSessionData;
    private boolean pendingForegroundCheck;
    private NotificationType previousNotificationType;
    private String previousSessionId;
    private final ProcessDataManager processDataManager;
    private final DataStore<SessionData> sessionDataStore;
    private final SessionFirelogPublisher sessionFirelogPublisher;
    private final SessionGenerator sessionGenerator;
    private final SessionsSettings sessionsSettings;
    private final TimeProvider timeProvider;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.sessions.SharedSessionRepositoryImpl$1", f = "SharedSessionRepository.kt", i = {}, l = {96}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.google.firebase.sessions.SharedSessionRepositoryImpl$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ SharedSessionRepositoryImpl this$0;

        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/google/firebase/sessions/SessionData;", "it", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "com.google.firebase.sessions.SharedSessionRepositoryImpl$1$1", f = "SharedSessionRepository.kt", i = {}, l = {94}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.google.firebase.sessions.SharedSessionRepositoryImpl$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00261 extends SuspendLambda implements Function3<FlowCollector<? super SessionData>, Throwable, Continuation<? super Unit>, Object> {
            private /* synthetic */ Object L$0;
            /* synthetic */ Object L$1;
            int label;
            final /* synthetic */ SharedSessionRepositoryImpl this$0;

            public C00261(SharedSessionRepositoryImpl r1, Continuation<? super C00261> r2) {
                this.this$0 = r1;
                super(3, r2);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super SessionData> r1, Throwable r2, Continuation<? super Unit> r3) {
                return invoke2(r1, r2, r3);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(FlowCollector<? super SessionData> r2, Throwable r3, Continuation<? super Unit> r4) {
                C00261 r0 = new C00261(this.this$0, r4);
                r0.L$0 = r2;
                r0.L$1 = r3;
                return r0.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r11) {
                Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int r1 = this.label;
                if (r1 == 0) goto L8;
                if (r1 != 1) goto L6;
                ResultKt.throwOnFailure(r11);
            L12:
                return Unit.INSTANCE;
            L6:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            L8:
                ResultKt.throwOnFailure(r11);
                FlowCollector r112 = (FlowCollector) this.L$0;
                Throwable r12 = (Throwable) this.L$1;
                SessionData r4 = new SessionData(SharedSessionRepositoryImpl.access$getSessionGenerator$p(this.this$0).generateNewSession(null), null, null, 4, null);
                Log.d("FirebaseSessions", "Init session datastore failed with exception message: " + r12.getMessage() + ". Emit fallback session " + r4.getSessionDetails().getSessionId());
                this.L$0 = null;
                this.label = 1;
                if (r112.emit(r4, this) != r0) goto L12;
                return r0;
            }
        }

        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        /* renamed from: com.google.firebase.sessions.SharedSessionRepositoryImpl$1$2, reason: invalid class name */
        public static final class AnonymousClass2<T> implements FlowCollector {
            final /* synthetic */ SharedSessionRepositoryImpl this$0;

            public AnonymousClass2(SharedSessionRepositoryImpl r1) {
                this.this$0 = r1;
            }

            public final Object emit(SessionData r3, Continuation<? super Unit> r4) {
                this.this$0.setLocalSessionData$com_google_firebase_firebase_sessions(r3);
                if (SharedSessionRepositoryImpl.access$getPendingForegroundCheck$p(this.this$0) == false) goto L5;
                SharedSessionRepositoryImpl.access$setPendingForegroundCheck$p(this.this$0, false);
                this.this$0.appForeground();
            L5:
                String r32 = r3.getSessionDetails().getSessionId();
                Object r2 = SharedSessionRepositoryImpl.access$notifySubscribers(this.this$0, r32, NotificationType.GENERAL, r4);
                if (r2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L9;
                return r2;
            L9:
                return Unit.INSTANCE;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object r1, Continuation r2) {
                return emit((SessionData) r1, r2);
            }
        }

        public AnonymousClass1(SharedSessionRepositoryImpl r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass1(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r6);
        L12:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r6);
            Flow r62 = FlowKt.m3551catch(SharedSessionRepositoryImpl.access$getSessionDataStore$p(this.this$0).getData(), new C00261(this.this$0, null));
            AnonymousClass2 r12 = new AnonymousClass2(this.this$0);
            this.label = 1;
            if (r62.collect(r12, this) != r0) goto L12;
            return r0;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/google/firebase/sessions/SharedSessionRepositoryImpl$NotificationType;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "GENERAL", "FALLBACK", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum NotificationType extends Enum<NotificationType> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ NotificationType[] $VALUES = null;
        public static final NotificationType FALLBACK = null;
        public static final NotificationType GENERAL = null;

        private static final /* synthetic */ NotificationType[] $values() {
            return new NotificationType[]{GENERAL, FALLBACK};
        }

        static {
            GENERAL = new NotificationType("GENERAL", 0);
            FALLBACK = new NotificationType("FALLBACK", 1);
            NotificationType[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        NotificationType(String r1, int r2) {
        }

        public static NotificationType valueOf(String r1) {
            return (NotificationType) Enum.valueOf(NotificationType.class, r1);
        }

        public static NotificationType[] values() {
            return (NotificationType[]) $VALUES.clone();
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[NotificationType.values().length];
            r0[NotificationType.GENERAL.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L9:
            r0[NotificationType.FALLBACK.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L5:
            $EnumSwitchMapping$0 = r0;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.sessions.SharedSessionRepositoryImpl$appBackground$1", f = "SharedSessionRepository.kt", i = {}, l = {118}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.google.firebase.sessions.SharedSessionRepositoryImpl$appBackground$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01641 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ SharedSessionRepositoryImpl this$0;

        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/google/firebase/sessions/SessionData;", "sessionData"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "com.google.firebase.sessions.SharedSessionRepositoryImpl$appBackground$1$1", f = "SharedSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.google.firebase.sessions.SharedSessionRepositoryImpl$appBackground$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00271 extends SuspendLambda implements Function2<SessionData, Continuation<? super SessionData>, Object> {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SharedSessionRepositoryImpl this$0;

            public C00271(SharedSessionRepositoryImpl r1, Continuation<? super C00271> r2) {
                this.this$0 = r1;
                super(2, r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
                C00271 r0 = new C00271(this.this$0, r3);
                r0.L$0 = r2;
                return r0;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(SessionData r1, Continuation<? super SessionData> r2) {
                return ((C00271) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(SessionData r1, Continuation<? super SessionData> r2) {
                return invoke2(r1, r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r7) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) goto L6;
                ResultKt.throwOnFailure(r7);
                return SessionData.copy$default((SessionData) this.L$0, null, SharedSessionRepositoryImpl.access$getTimeProvider$p(this.this$0).currentTime(), null, 5, null);
            L6:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        public C01641(SharedSessionRepositoryImpl r1, Continuation<? super C01641> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new C01641(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01641) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r6v9 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L11;
            if (r1 != 1) goto L9;
            ResultKt.throwOnFailure(r7);     // Catch: Exception -> L7
            C01641 this = this;
        L18:
        L17:
            return Unit.INSTANCE;
        L9:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L11:
            ResultKt.throwOnFailure(r7);
            DataStore r72 = SharedSessionRepositoryImpl.access$getSessionDataStore$p(this.this$0);     // Catch: Exception -> L7
            C00271 r12 = new C00271(this.this$0, null);     // Catch: Exception -> L7
            this.label = 1;     // Catch: Exception -> L7
            Object r6 = r72.updateData(r12, this);     // Catch: Exception -> L7
            this = r6;
            if (r6 != r0) goto L18;
            return r0;
        L7:
            e = move-exception;
            Log.d("FirebaseSessions", "App backgrounded, failed to update data. Message: " + e.getMessage());
            SharedSessionRepositoryImpl r73 = this.this$0;
            r73.setLocalSessionData$com_google_firebase_firebase_sessions(SessionData.copy$default(r73.getLocalSessionData$com_google_firebase_firebase_sessions(), null, SharedSessionRepositoryImpl.access$getTimeProvider$p(this.this$0).currentTime(), null, 5, null));
            goto L17
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.sessions.SharedSessionRepositoryImpl$appForeground$1", f = "SharedSessionRepository.kt", i = {}, l = {142, 193}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.google.firebase.sessions.SharedSessionRepositoryImpl$appForeground$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01651 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SessionData $sessionData;
        int label;
        final /* synthetic */ SharedSessionRepositoryImpl this$0;

        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/google/firebase/sessions/SessionData;", "currentSessionData"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "com.google.firebase.sessions.SharedSessionRepositoryImpl$appForeground$1$1", f = "SharedSessionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.google.firebase.sessions.SharedSessionRepositoryImpl$appForeground$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00281 extends SuspendLambda implements Function2<SessionData, Continuation<? super SessionData>, Object> {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SharedSessionRepositoryImpl this$0;

            public C00281(SharedSessionRepositoryImpl r1, Continuation<? super C00281> r2) {
                this.this$0 = r1;
                super(2, r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
                C00281 r0 = new C00281(this.this$0, r3);
                r0.L$0 = r2;
                return r0;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(SessionData r1, Continuation<? super SessionData> r2) {
                return ((C00281) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(SessionData r1, Continuation<? super SessionData> r2) {
                return invoke2(r1, r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r7) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) goto L23;
                ResultKt.throwOnFailure(r7);
                SessionData r0 = (SessionData) this.L$0;
                boolean r72 = SharedSessionRepositoryImpl.access$isSessionExpired(this.this$0, r0);
                boolean r1 = SharedSessionRepositoryImpl.access$isColdStart(this.this$0, r0);
                boolean r2 = SharedSessionRepositoryImpl.access$isMyProcessStale(this.this$0, r0);
                if (r1 == false) goto L7;
                Map<String, ProcessData> r3 = SharedSessionRepositoryImpl.access$getProcessDataManager$p(this.this$0).generateProcessDataMap();
            L11:
                if (r1 == false) goto L13;
                SessionDetails r5 = null;
            L14:
                if (r72 == true) goto L21;
                if (r1 == true) goto L21;
                if (r2 == true) goto L19;
                return r0;
            L19:
                return SessionData.copy$default(r0, null, null, SharedSessionRepositoryImpl.access$getProcessDataManager$p(this.this$0).updateProcessDataMap(r3), 3, null);
            L21:
                SessionDetails r73 = SharedSessionRepositoryImpl.access$getSessionGenerator$p(this.this$0).generateNewSession(r5);
                SharedSessionRepositoryImpl.access$getSessionFirelogPublisher$p(this.this$0).mayLogSession(r73);
                SharedSessionRepositoryImpl.access$getProcessDataManager$p(this.this$0).onSessionGenerated();
                return r0.copy(r73, null, r3);
            L13:
                r5 = r0.getSessionDetails();
                goto L14
            L7:
                if (r2 == false) goto L9;
                r3 = SharedSessionRepositoryImpl.access$getProcessDataManager$p(this.this$0).updateProcessDataMap(r0.getProcessDataMap());
                goto L11
            L9:
                r3 = r0.getProcessDataMap();
                goto L11
            L23:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        public C01651(SharedSessionRepositoryImpl r1, SessionData r2, Continuation<? super C01651> r3) {
            this.this$0 = r1;
            this.$sessionData = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            return new C01651(this.this$0, this.$sessionData, r3);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C01651) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v7 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r11) {
            Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r0 = this.label;
            if (r0 == 0) goto L13;
            if (r0 == 1) goto L9;
            if (r0 != 2) goto L7;
            ResultKt.throwOnFailure(r11);
        L23:
            return Unit.INSTANCE;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r11);     // Catch: Exception -> L11
            C01651 this = this;
        L24:
            goto L23
        L13:
            ResultKt.throwOnFailure(r11);
            DataStore r112 = SharedSessionRepositoryImpl.access$getSessionDataStore$p(this.this$0);     // Catch: Exception -> L11
            C00281 r02 = new C00281(this.this$0, null);     // Catch: Exception -> L11
            this.label = 1;     // Catch: Exception -> L11
            Object r10 = r112.updateData(r02, this);     // Catch: Exception -> L11
            this = r10;
            if (r10 != r1) goto L24;
        L21:
            return r1;
        L11:
            e = move-exception;
            Log.d("FirebaseSessions", "App foregrounded, failed to update data. Message: " + e.getMessage());
            if (SharedSessionRepositoryImpl.access$isSessionExpired(this.this$0, this.$sessionData) == false) goto L23;
            SessionDetails r5 = SharedSessionRepositoryImpl.access$getSessionGenerator$p(this.this$0).generateNewSession(this.$sessionData.getSessionDetails());
            this.this$0.setLocalSessionData$com_google_firebase_firebase_sessions(SessionData.copy$default(this.$sessionData, r5, null, null, 4, null));
            SharedSessionRepositoryImpl.access$getSessionFirelogPublisher$p(this.this$0).mayLogSession(r5);
            SharedSessionRepositoryImpl r113 = this.this$0;
            String r03 = r5.getSessionId();
            NotificationType r2 = NotificationType.FALLBACK;
            this.label = 2;
            if (SharedSessionRepositoryImpl.access$notifySubscribers(r113, r03, r2, this) != r1) goto L23;
            goto L21
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.sessions.SharedSessionRepositoryImpl", f = "SharedSessionRepository.kt", i = {0, 0}, l = {Opcodes.DIV_DOUBLE_2ADDR}, m = "notifySubscribers", n = {"sessionId", "type"}, s = {"L$0", "L$1"})
    /* renamed from: com.google.firebase.sessions.SharedSessionRepositoryImpl$notifySubscribers$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01661 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SharedSessionRepositoryImpl this$0;

        public C01661(SharedSessionRepositoryImpl r1, Continuation<? super C01661> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return SharedSessionRepositoryImpl.access$notifySubscribers(this.this$0, null, null, this);
        }
    }

    public SharedSessionRepositoryImpl(SessionsSettings r1, SessionGenerator r2, SessionFirelogPublisher r3, TimeProvider r4, DataStore<SessionData> r5, ProcessDataManager r6, @Background CoroutineContext r7) {
        this.sessionsSettings = r1;
        this.sessionGenerator = r2;
        this.sessionFirelogPublisher = r3;
        this.timeProvider = r4;
        this.sessionDataStore = r5;
        this.processDataManager = r6;
        this.backgroundDispatcher = r7;
        this.previousNotificationType = NotificationType.GENERAL;
        this.previousSessionId = _UrlKt.FRAGMENT_ENCODE_SET;
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(r7), null, null, new AnonymousClass1(this, null), 3, null);
    }

    public static final /* synthetic */ boolean access$getPendingForegroundCheck$p(SharedSessionRepositoryImpl r0) {
        return r0.pendingForegroundCheck;
    }

    public static final /* synthetic */ ProcessDataManager access$getProcessDataManager$p(SharedSessionRepositoryImpl r0) {
        return r0.processDataManager;
    }

    public static final /* synthetic */ DataStore access$getSessionDataStore$p(SharedSessionRepositoryImpl r0) {
        return r0.sessionDataStore;
    }

    public static final /* synthetic */ SessionFirelogPublisher access$getSessionFirelogPublisher$p(SharedSessionRepositoryImpl r0) {
        return r0.sessionFirelogPublisher;
    }

    public static final /* synthetic */ SessionGenerator access$getSessionGenerator$p(SharedSessionRepositoryImpl r0) {
        return r0.sessionGenerator;
    }

    public static final /* synthetic */ TimeProvider access$getTimeProvider$p(SharedSessionRepositoryImpl r0) {
        return r0.timeProvider;
    }

    public static final /* synthetic */ boolean access$isColdStart(SharedSessionRepositoryImpl r0, SessionData r1) {
        return r0.isColdStart(r1);
    }

    public static final /* synthetic */ boolean access$isMyProcessStale(SharedSessionRepositoryImpl r0, SessionData r1) {
        return r0.isMyProcessStale(r1);
    }

    public static final /* synthetic */ boolean access$isSessionExpired(SharedSessionRepositoryImpl r0, SessionData r1) {
        return r0.isSessionExpired(r1);
    }

    public static final /* synthetic */ Object access$notifySubscribers(SharedSessionRepositoryImpl r0, String r1, NotificationType r2, Continuation r3) {
        return r0.notifySubscribers(r1, r2, r3);
    }

    public static final /* synthetic */ void access$setPendingForegroundCheck$p(SharedSessionRepositoryImpl r0, boolean r1) {
        r0.pendingForegroundCheck = r1;
    }

    private final boolean isColdStart(SessionData r2) {
        Map<String, ProcessData> r22 = r2.getProcessDataMap();
        if (r22 == null) goto L8;
        boolean r1 = this.processDataManager.isColdStart(r22);
        if (r1 == false) goto L7;
        Log.d("FirebaseSessions", "Cold app start detected");
    L7:
        return r1;
    L8:
        Log.d("FirebaseSessions", "No process data map");
        return true;
    }

    private final boolean isMyProcessStale(SessionData r4) {
        Map<String, ProcessData> r42 = r4.getProcessDataMap();
        ProcessDataManager r0 = this.processDataManager;
        if (r42 == null) goto L8;
        boolean r43 = r0.isMyProcessStale(r42);
        if (r43 == false) goto L7;
        Log.d("FirebaseSessions", "Process " + this.processDataManager.getMyProcessName() + " is stale");
    L7:
        return r43;
    L8:
        Log.d("FirebaseSessions", "No process data for " + r0.getMyProcessName());
        return true;
    }

    private final boolean isSessionExpired(SessionData r9) {
        Time r0 = r9.getBackgroundTime();
        boolean r3 = false;
        if (r0 != null) goto L5;
        Log.d("FirebaseSessions", "Session " + r9.getSessionDetails().getSessionId() + " has not backgrounded yet");
        return false;
    L5:
        if (Duration.m3374compareToLRDsOJo(this.timeProvider.currentTime().m1984minus5sfh64U(r0), this.sessionsSettings.m1987getSessionRestartTimeoutUwyO8pc()) <= 0) goto L7;
        r3 = true;
    L7:
        if (r3 == false) goto L9;
        Log.d("FirebaseSessions", "Session " + r9.getSessionDetails().getSessionId() + " is expired");
    L9:
        return r3;
    }

    private final Object notifySubscribers(String r6, NotificationType r7, Continuation<? super Unit> r8) {
        if ((r8 instanceof C01661) == false) goto L7;
        C01661 r0 = (C01661) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        r7 = (NotificationType) r0.L$1;
        r6 = (String) r0.L$0;
        ResultKt.throwOnFailure(r82);
    L21:
        Iterator r5 = ((Map) r82).values().iterator();
    L23:
        if (r5.hasNext() == false) goto L34;
        SessionSubscriber r83 = (SessionSubscriber) r5.next();
        r83.onSessionChanged(new SessionSubscriber.SessionDetails(r6));
        int r02 = WhenMappings.$EnumSwitchMapping$0[r7.ordinal()];
        if (r02 != 1) goto L27;
        String r84 = "Notified " + r83.getSessionSubscriberName() + " of new session " + r6;
    L32:
        Log.d("FirebaseSessions", r84);
        goto L23
    L27:
        if (r02 != 2) goto L29;
        r84 = "Notified " + r83.getSessionSubscriberName() + " of new fallback session " + r6;
        goto L32
    L29:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L34:
        return Unit.INSTANCE;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r82);
        this.previousNotificationType = r7;
        if (Intrinsics.areEqual(this.previousSessionId, r6) == true) goto L17;
        this.previousSessionId = r6;
        FirebaseSessionsDependencies r52 = FirebaseSessionsDependencies.INSTANCE;
        r0.L$0 = r6;
        r0.L$1 = r7;
        r0.label = 1;
        r82 = r52.getRegisteredSubscribers$com_google_firebase_firebase_sessions(r0);
        if (r82 != r12) goto L21;
        return r12;
    L17:
        return Unit.INSTANCE;
    L7:
        r0 = new C01661(this, r8);
        goto L8
    }

    @Override // com.google.firebase.sessions.SharedSessionRepository
    public void appBackground() {
        this.isInForeground = false;
        if (this.localSessionData != null) goto L6;
        Log.d("FirebaseSessions", "App backgrounded, but local SessionData not initialized");
        return;
    L6:
        Log.d("FirebaseSessions", "App backgrounded on " + this.processDataManager.getMyProcessName());
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(this.backgroundDispatcher), null, null, new C01641(this, null), 3, null);
    }

    @Override // com.google.firebase.sessions.SharedSessionRepository
    public void appForeground() {
        this.isInForeground = true;
        if (this.localSessionData != null) goto L6;
        this.pendingForegroundCheck = true;
        Log.d("FirebaseSessions", "App foregrounded, but local SessionData not initialized");
        return;
    L6:
        SessionData r0 = getLocalSessionData$com_google_firebase_firebase_sessions();
        Log.d("FirebaseSessions", "App foregrounded on " + this.processDataManager.getMyProcessName());
        if (isSessionExpired(r0) == false) goto L9;
    L12:
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(this.backgroundDispatcher), null, null, new C01651(this, r0, null), 3, null);
        return;
    L9:
        if (isMyProcessStale(r0) == true) goto L12;
    }

    public final SessionData getLocalSessionData$com_google_firebase_firebase_sessions() {
        SessionData r0 = this.localSessionData;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return null;
    }

    @Override // com.google.firebase.sessions.SharedSessionRepository
    public boolean isInForeground() {
        return this.isInForeground;
    }

    public final void setLocalSessionData$com_google_firebase_firebase_sessions(SessionData r1) {
        this.localSessionData = r1;
    }
}
