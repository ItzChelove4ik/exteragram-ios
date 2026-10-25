package com.google.firebase.sessions;

import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.sessions.InstallationId;
import com.google.firebase.sessions.api.FirebaseSessionsDependencies;
import com.google.firebase.sessions.api.SessionSubscriber;
import com.google.firebase.sessions.settings.SessionsSettings;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0001\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B3\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010\u0015\u001a\u00020\u0016H\u0082@¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0016H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/google/firebase/sessions/SessionFirelogPublisherImpl;", "Lcom/google/firebase/sessions/SessionFirelogPublisher;", "firebaseApp", "Lcom/google/firebase/FirebaseApp;", "firebaseInstallations", "Lcom/google/firebase/installations/FirebaseInstallationsApi;", "sessionSettings", "Lcom/google/firebase/sessions/settings/SessionsSettings;", "eventGDTLogger", "Lcom/google/firebase/sessions/EventGDTLoggerInterface;", "backgroundDispatcher", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Lcom/google/firebase/FirebaseApp;Lcom/google/firebase/installations/FirebaseInstallationsApi;Lcom/google/firebase/sessions/settings/SessionsSettings;Lcom/google/firebase/sessions/EventGDTLoggerInterface;Lkotlin/coroutines/CoroutineContext;)V", "mayLogSession", _UrlKt.FRAGMENT_ENCODE_SET, "sessionDetails", "Lcom/google/firebase/sessions/SessionDetails;", "attemptLoggingSessionEvent", "sessionEvent", "Lcom/google/firebase/sessions/SessionEvent;", "shouldLogSession", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldCollectEvents", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSessionFirelogPublisher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionFirelogPublisher.kt\ncom/google/firebase/sessions/SessionFirelogPublisherImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,129:1\n2632#2,3:130\n*S KotlinDebug\n*F\n+ 1 SessionFirelogPublisher.kt\ncom/google/firebase/sessions/SessionFirelogPublisherImpl\n*L\n99#1:130,3\n*E\n"})
/* loaded from: classes.dex */
public final class SessionFirelogPublisherImpl implements SessionFirelogPublisher {
    public static final Companion Companion = null;
    private static final double randomValueForSampling = 0.0d;
    private final CoroutineContext backgroundDispatcher;
    private final EventGDTLoggerInterface eventGDTLogger;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final SessionsSettings sessionSettings;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/google/firebase/sessions/SessionFirelogPublisherImpl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "randomValueForSampling", _UrlKt.FRAGMENT_ENCODE_SET, "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.sessions.SessionFirelogPublisherImpl$mayLogSession$1", f = "SessionFirelogPublisher.kt", i = {2}, l = {70, 71, 77}, m = "invokeSuspend", n = {"installationId"}, s = {"L$0"})
    /* renamed from: com.google.firebase.sessions.SessionFirelogPublisherImpl$mayLogSession$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SessionDetails $sessionDetails;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        final /* synthetic */ SessionFirelogPublisherImpl this$0;

        public AnonymousClass1(SessionFirelogPublisherImpl r1, SessionDetails r2, Continuation<? super AnonymousClass1> r3) {
            this.this$0 = r1;
            this.$sessionDetails = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            return new AnonymousClass1(this.this$0, this.$sessionDetails, r3);
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
        public final Object invokeSuspend(Object r9) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L13;
            if (r1 == 1) goto L12;
            if (r1 == 2) goto L11;
            if (r1 != 3) goto L9;
            SessionsSettings r02 = (SessionsSettings) this.L$5;
            SessionDetails r12 = (SessionDetails) this.L$4;
            FirebaseApp r2 = (FirebaseApp) this.L$3;
            SessionEvents r3 = (SessionEvents) this.L$2;
            SessionFirelogPublisherImpl r4 = (SessionFirelogPublisherImpl) this.L$1;
            InstallationId r8 = (InstallationId) this.L$0;
            ResultKt.throwOnFailure(r9);
            SessionsSettings r32 = r02;
            SessionEvents r03 = r3;
            SessionDetails r22 = r12;
            FirebaseApp r13 = r2;
        L8:
            SessionFirelogPublisherImpl r7 = r4;
            SessionFirelogPublisherImpl.access$attemptLoggingSessionEvent(r7, r03.buildSession(r13, r22, r32, (Map) r9, r8.getFid(), r8.getAuthToken()));
        L27:
            return Unit.INSTANCE;
        L9:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L11:
            ResultKt.throwOnFailure(r9);
        L21:
            InstallationId r92 = (InstallationId) r9;
            r4 = this.this$0;
            SessionEvents r33 = SessionEvents.INSTANCE;
            r13 = SessionFirelogPublisherImpl.access$getFirebaseApp$p(r4);
            SessionDetails r5 = this.$sessionDetails;
            SessionsSettings r6 = SessionFirelogPublisherImpl.access$getSessionSettings$p(this.this$0);
            FirebaseSessionsDependencies r72 = FirebaseSessionsDependencies.INSTANCE;
            this.L$0 = r92;
            this.L$1 = r4;
            this.L$2 = r33;
            this.L$3 = r13;
            this.L$4 = r5;
            this.L$5 = r6;
            this.label = 3;
            Object r82 = r72.getRegisteredSubscribers$com_google_firebase_firebase_sessions(this);
            if (r82 != r0) goto L24;
        L23:
            return r0;
        L24:
            r9 = r82;
            r8 = r92;
            r03 = r33;
            r22 = r5;
            r32 = r6;
            goto L8
        L12:
            ResultKt.throwOnFailure(r9);
        L17:
            if (((Boolean) r9).booleanValue() == false) goto L27;
            InstallationId.Companion r93 = InstallationId.Companion;
            FirebaseInstallationsApi r14 = SessionFirelogPublisherImpl.access$getFirebaseInstallations$p(this.this$0);
            this.label = 2;
            r9 = r93.create(r14, this);
            if (r9 != r0) goto L21;
        L13:
            ResultKt.throwOnFailure(r9);
            SessionFirelogPublisherImpl r94 = this.this$0;
            this.label = 1;
            r9 = SessionFirelogPublisherImpl.access$shouldLogSession(r94, this);
            if (r9 != r0) goto L17;
            goto L23
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.sessions.SessionFirelogPublisherImpl", f = "SessionFirelogPublisher.kt", i = {}, l = {98, 104}, m = "shouldLogSession", n = {}, s = {})
    /* renamed from: com.google.firebase.sessions.SessionFirelogPublisherImpl$shouldLogSession$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01631 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SessionFirelogPublisherImpl this$0;

        public C01631(SessionFirelogPublisherImpl r1, Continuation<? super C01631> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return SessionFirelogPublisherImpl.access$shouldLogSession(this.this$0, this);
        }
    }

    static {
        Companion = new Companion(null);
        randomValueForSampling = Math.random();
    }

    public SessionFirelogPublisherImpl(FirebaseApp r1, FirebaseInstallationsApi r2, SessionsSettings r3, EventGDTLoggerInterface r4, @Background CoroutineContext r5) {
        this.firebaseApp = r1;
        this.firebaseInstallations = r2;
        this.sessionSettings = r3;
        this.eventGDTLogger = r4;
        this.backgroundDispatcher = r5;
    }

    public static final /* synthetic */ void access$attemptLoggingSessionEvent(SessionFirelogPublisherImpl r0, SessionEvent r1) {
        r0.attemptLoggingSessionEvent(r1);
    }

    public static final /* synthetic */ FirebaseApp access$getFirebaseApp$p(SessionFirelogPublisherImpl r0) {
        return r0.firebaseApp;
    }

    public static final /* synthetic */ FirebaseInstallationsApi access$getFirebaseInstallations$p(SessionFirelogPublisherImpl r0) {
        return r0.firebaseInstallations;
    }

    public static final /* synthetic */ SessionsSettings access$getSessionSettings$p(SessionFirelogPublisherImpl r0) {
        return r0.sessionSettings;
    }

    public static final /* synthetic */ Object access$shouldLogSession(SessionFirelogPublisherImpl r0, Continuation r1) {
        return r0.shouldLogSession(r1);
    }

    private final void attemptLoggingSessionEvent(SessionEvent r2) {
        this.eventGDTLogger.log(r2);     // Catch: RuntimeException -> L5
        Log.d("FirebaseSessions", "Successfully logged Session Start event.");     // Catch: RuntimeException -> L5
        return;
    L5:
        e = move-exception;
        Log.e("FirebaseSessions", "Error logging Session Start event to DataTransport: ", e);
    }

    private final boolean shouldCollectEvents() {
        if (randomValueForSampling > this.sessionSettings.getSamplingRate()) goto L6;
        return true;
    L6:
        return false;
    }

    private final Object shouldLogSession(Continuation<? super Boolean> r8) {
        if ((r8 instanceof C01631) == false) goto L7;
        C01631 r0 = (C01631) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        ResultKt.throwOnFailure(r82);
    L33:
        if (this.sessionSettings.getSessionsEnabled() == true) goto L37;
        Log.d("FirebaseSessions", "Sessions SDK disabled through settings API. Events will not be sent.");
        return Boxing.boxBoolean(false);
    L37:
        if (shouldCollectEvents() == true) goto L41;
        Log.d("FirebaseSessions", "Sessions SDK has dropped this session due to sampling.");
        return Boxing.boxBoolean(false);
    L41:
        return Boxing.boxBoolean(true);
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r82);
    L19:
        Collection r83 = ((Map) r82).values();
        if ((r83 instanceof Collection) == true) goto L22;
    L24:
        Iterator r84 = r83.iterator();
    L26:
        if (r84.hasNext() == false) goto L42;
        if (((SessionSubscriber) r84.next()).isDataCollectionEnabled() == false) goto L26;
        SessionsSettings r85 = this.sessionSettings;
        r0.label = 2;
        if (r85.updateSettings(r0) != r12) goto L33;
    L31:
        return r12;
    L42:
        Log.d("FirebaseSessions", "Sessions SDK disabled through data collection. Events will not be sent.");
        return Boxing.boxBoolean(false);
    L22:
        if (r83.isEmpty() == false) goto L24;
    L16:
        ResultKt.throwOnFailure(r82);
        FirebaseSessionsDependencies r86 = FirebaseSessionsDependencies.INSTANCE;
        r0.label = 1;
        r82 = r86.getRegisteredSubscribers$com_google_firebase_firebase_sessions(r0);
        if (r82 != r12) goto L19;
    L7:
        r0 = new C01631(this, r8);
        goto L8
    }

    @Override // com.google.firebase.sessions.SessionFirelogPublisher
    public void mayLogSession(SessionDetails r8) {
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(this.backgroundDispatcher), null, null, new AnonymousClass1(this, r8, null), 3, null);
    }
}
