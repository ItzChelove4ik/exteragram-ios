package com.google.firebase.sessions;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
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

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \f2\u00020\u0001:\u0001\fB+\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/google/firebase/sessions/FirebaseSessions;", _UrlKt.FRAGMENT_ENCODE_SET, "firebaseApp", "Lcom/google/firebase/FirebaseApp;", "settings", "Lcom/google/firebase/sessions/settings/SessionsSettings;", "backgroundDispatcher", "Lkotlin/coroutines/CoroutineContext;", "sessionsActivityLifecycleCallbacks", "Lcom/google/firebase/sessions/SessionsActivityLifecycleCallbacks;", "<init>", "(Lcom/google/firebase/FirebaseApp;Lcom/google/firebase/sessions/settings/SessionsSettings;Lkotlin/coroutines/CoroutineContext;Lcom/google/firebase/sessions/SessionsActivityLifecycleCallbacks;)V", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FirebaseSessions {
    public static final Companion Companion = null;
    private final FirebaseApp firebaseApp;
    private final SessionsSettings settings;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.sessions.FirebaseSessions$1", f = "FirebaseSessions.kt", i = {}, l = {51, 55}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nFirebaseSessions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FirebaseSessions.kt\ncom/google/firebase/sessions/FirebaseSessions$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,84:1\n2632#2,3:85\n*S KotlinDebug\n*F\n+ 1 FirebaseSessions.kt\ncom/google/firebase/sessions/FirebaseSessions$1\n*L\n52#1:85,3\n*E\n"})
    /* renamed from: com.google.firebase.sessions.FirebaseSessions$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SessionsActivityLifecycleCallbacks $sessionsActivityLifecycleCallbacks;
        int label;
        final /* synthetic */ FirebaseSessions this$0;

        public AnonymousClass1(FirebaseSessions r1, SessionsActivityLifecycleCallbacks r2, Continuation<? super AnonymousClass1> r3) {
            this.this$0 = r1;
            this.$sessionsActivityLifecycleCallbacks = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            return new AnonymousClass1(this.this$0, this.$sessionsActivityLifecycleCallbacks, r3);
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
            if (r1 == 0) goto L10;
            if (r1 == 1) goto L9;
            if (r1 != 2) goto L7;
            ResultKt.throwOnFailure(r6);
        L27:
            if (FirebaseSessions.access$getSettings$p(this.this$0).getSessionsEnabled() == true) goto L29;
            Boxing.boxInt(Log.d("FirebaseSessions", "Sessions SDK disabled. Not listening to lifecycle events."));
        L32:
            return Unit.INSTANCE;
        L29:
            FirebaseSessions.access$getFirebaseApp$p(this.this$0).addLifecycleEventListener(new FirebaseSessions$1$$ExternalSyntheticLambda0(this.$sessionsActivityLifecycleCallbacks));
            Unit r5 = Unit.INSTANCE;
            goto L32
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r6);
        L13:
            Collection r62 = ((Map) r6).values();
            if ((r62 instanceof Collection) == true) goto L16;
        L18:
            Iterator r63 = r62.iterator();
        L20:
            if (r63.hasNext() == false) goto L30;
            if (((SessionSubscriber) r63.next()).isDataCollectionEnabled() == false) goto L20;
            SessionsSettings r64 = FirebaseSessions.access$getSettings$p(this.this$0);
            this.label = 2;
            if (r64.updateSettings(this) != r0) goto L27;
        L25:
            return r0;
        L30:
            Boxing.boxInt(Log.d("FirebaseSessions", "No Sessions subscribers. Not listening to lifecycle events."));
            goto L32
        L16:
            if (r62.isEmpty() == false) goto L18;
        L10:
            ResultKt.throwOnFailure(r6);
            FirebaseSessionsDependencies r65 = FirebaseSessionsDependencies.INSTANCE;
            this.label = 1;
            r6 = r65.getRegisteredSubscribers$com_google_firebase_firebase_sessions(this);
            if (r6 != r0) goto L13;
            goto L13
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/google/firebase/sessions/FirebaseSessions$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "TAG", "Ljava/lang/String;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public FirebaseSessions(FirebaseApp r7, SessionsSettings r8, @Background CoroutineContext r9, SessionsActivityLifecycleCallbacks r10) {
        this.firebaseApp = r7;
        this.settings = r8;
        Log.d("FirebaseSessions", "Initializing Firebase Sessions 3.0.7.");
        Context r72 = r7.getApplicationContext().getApplicationContext();
        if ((r72 instanceof Application) == false) goto L6;
        ((Application) r72).registerActivityLifecycleCallbacks(r10);
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(r9), null, null, new AnonymousClass1(this, r10, null), 3, null);
        return;
    L6:
        Log.e("FirebaseSessions", "Failed to register lifecycle callbacks, unexpected context " + r72.getClass() + '.');
    }

    public static final /* synthetic */ FirebaseApp access$getFirebaseApp$p(FirebaseSessions r0) {
        return r0.firebaseApp;
    }

    public static final /* synthetic */ SessionsSettings access$getSettings$p(FirebaseSessions r0) {
        return r0.settings;
    }
}
