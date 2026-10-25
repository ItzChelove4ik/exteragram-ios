package com.google.firebase.sessions.api;

import android.util.Log;
import androidx.lifecycle.LiveData$$ExternalSyntheticBUOutline0;
import com.google.firebase.sessions.api.SessionSubscriber;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.InterruptibleKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001c\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u0010H\u0080@¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u0014\u0010\u0015RT\u0010\u0019\u001aB\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00040\u0004\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\r0\r \u0018* \u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00040\u0004\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\r0\r\u0018\u00010\u00100\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Lcom/google/firebase/sessions/api/FirebaseSessionsDependencies;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Lcom/google/firebase/sessions/api/SessionSubscriber$Name;", "subscriberName", _UrlKt.FRAGMENT_ENCODE_SET, "addDependency", "(Lcom/google/firebase/sessions/api/SessionSubscriber$Name;)V", "Lcom/google/firebase/sessions/api/SessionSubscriber;", "subscriber", "register", "(Lcom/google/firebase/sessions/api/SessionSubscriber;)V", "Lcom/google/firebase/sessions/api/FirebaseSessionsDependencies$Dependency;", "getDependency", "(Lcom/google/firebase/sessions/api/SessionSubscriber$Name;)Lcom/google/firebase/sessions/api/FirebaseSessionsDependencies$Dependency;", _UrlKt.FRAGMENT_ENCODE_SET, "getRegisteredSubscribers$com_google_firebase_firebase_sessions", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRegisteredSubscribers", "getSubscriber$com_google_firebase_firebase_sessions", "(Lcom/google/firebase/sessions/api/SessionSubscriber$Name;)Lcom/google/firebase/sessions/api/SessionSubscriber;", "getSubscriber", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin.jvm.PlatformType", "dependencies", "Ljava/util/Map;", "Dependency", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFirebaseSessionsDependencies.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FirebaseSessionsDependencies.kt\ncom/google/firebase/sessions/api/FirebaseSessionsDependencies\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,104:1\n462#2:105\n412#2:106\n1246#3,4:107\n*S KotlinDebug\n*F\n+ 1 FirebaseSessionsDependencies.kt\ncom/google/firebase/sessions/api/FirebaseSessionsDependencies\n*L\n75#1:105\n75#1:106\n75#1:107,4\n*E\n"})
/* loaded from: classes.dex */
public final class FirebaseSessionsDependencies {
    public static final FirebaseSessionsDependencies INSTANCE = null;
    private static final Map<SessionSubscriber.Name, Dependency> dependencies = null;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0082\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/google/firebase/sessions/api/FirebaseSessionsDependencies$Dependency;", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/concurrent/CountDownLatch;", "latch", "Lcom/google/firebase/sessions/api/SessionSubscriber;", "subscriber", "<init>", "(Ljava/util/concurrent/CountDownLatch;Lcom/google/firebase/sessions/api/SessionSubscriber;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/util/concurrent/CountDownLatch;", "getLatch", "()Ljava/util/concurrent/CountDownLatch;", "Lcom/google/firebase/sessions/api/SessionSubscriber;", "getSubscriber", "()Lcom/google/firebase/sessions/api/SessionSubscriber;", "setSubscriber", "(Lcom/google/firebase/sessions/api/SessionSubscriber;)V", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Dependency {
        private final CountDownLatch latch;
        private SessionSubscriber subscriber;

        public Dependency(CountDownLatch r1, SessionSubscriber r2) {
            this.latch = r1;
            this.subscriber = r2;
        }

        public /* synthetic */ Dependency(CountDownLatch r1, SessionSubscriber r2, int r3, DefaultConstructorMarker r4) {
            if ((r3 & 2) == 0) goto L5;
            r2 = null;
        L5:
            this(r1, r2);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof Dependency) == true) goto L8;
            return false;
        L8:
            Dependency r52 = (Dependency) r5;
            if (Intrinsics.areEqual(this.latch, r52.latch) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.subscriber, r52.subscriber) == true) goto L14;
            return false;
        L14:
            return true;
        }

        public final CountDownLatch getLatch() {
            return this.latch;
        }

        public final SessionSubscriber getSubscriber() {
            return this.subscriber;
        }

        public int hashCode() {
            int r0 = this.latch.hashCode() * 31;
            SessionSubscriber r1 = this.subscriber;
            if (r1 != null) goto L5;
            int r12 = 0;
        L7:
            return r0 + r12;
        L5:
            r12 = r1.hashCode();
            goto L7
        }

        public final void setSubscriber(SessionSubscriber r1) {
            this.subscriber = r1;
        }

        public String toString() {
            return "Dependency(latch=" + this.latch + ", subscriber=" + this.subscriber + ')';
        }
    }

    public static Unit $r8$lambda$b_1K8a3Wd55c4yJZHwHemPSc1zQ(Dependency r0) {
        r0.getLatch().await();
        return Unit.INSTANCE;
    }

    static {
        INSTANCE = new FirebaseSessionsDependencies();
        dependencies = Collections.synchronizedMap(new LinkedHashMap());
    }

    private FirebaseSessionsDependencies() {
    }

    @JvmStatic
    public static final void addDependency(SessionSubscriber.Name r6) {
        Map<SessionSubscriber.Name, Dependency> r0 = dependencies;
        if (r0.containsKey(r6) == false) goto L6;
        Log.d("FirebaseSessions", "Dependency " + r6 + " already added.");
        return;
    L6:
        r0.put(r6, new Dependency(new CountDownLatch(1), null, 2, null));
        Log.d("FirebaseSessions", "Dependency to " + r6 + " added.");
    }

    private final Dependency getDependency(SessionSubscriber.Name r2) {
        Dependency r1 = dependencies.get(r2);
        if (r1 != null) goto L5;
        LiveData$$ExternalSyntheticBUOutline0.m("Cannot get dependency ", r2, ". Dependencies should be added at class load time.");
        return null;
    L5:
        return r1;
    }

    @JvmStatic
    public static final void register(SessionSubscriber r5) {
        SessionSubscriber.Name r0 = r5.getSessionSubscriberName();
        Dependency r1 = INSTANCE.getDependency(r0);
        if (r1.getSubscriber() == null) goto L6;
        Log.d("FirebaseSessions", "Subscriber " + r0 + " already registered.");
        return;
    L6:
        r1.setSubscriber(r5);
        Log.d("FirebaseSessions", "Subscriber " + r0 + " registered.");
        r1.getLatch().countDown();
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0092 -> B:21:0x0093). Please report as a decompilation issue!!! */
    public final Object getRegisteredSubscribers$com_google_firebase_firebase_sessions(Continuation<? super Map<SessionSubscriber.Name, ? extends SessionSubscriber>> r9) {
        if ((r9 instanceof FirebaseSessionsDependencies$getRegisteredSubscribers$1) == false) goto L7;
        FirebaseSessionsDependencies$getRegisteredSubscribers$1 r0 = (FirebaseSessionsDependencies$getRegisteredSubscribers$1) r9;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r8 = r0.result;
        Object r92 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r12 = r0.label;
        if (r12 == 0) goto L14;
        if (r12 != 1) goto L12;
        Object r13 = r0.L$4;
        Map r4 = (Map) r0.L$3;
        SessionSubscriber.Name r5 = (SessionSubscriber.Name) r0.L$2;
        Iterator r6 = (Iterator) r0.L$1;
        Map r7 = (Map) r0.L$0;
        ResultKt.throwOnFailure(r8);
    L21:
        r4.put(r13, INSTANCE.getSubscriber$com_google_firebase_firebase_sessions(r5));
        r4 = r7;
    L16:
        if (r6.hasNext() == false) goto L22;
        Map.Entry r82 = (Map.Entry) r6.next();
        r13 = r82.getKey();
        r5 = (SessionSubscriber.Name) r82.getKey();
        FirebaseSessionsDependencies$$ExternalSyntheticLambda0 r72 = new FirebaseSessionsDependencies$$ExternalSyntheticLambda0((Dependency) r82.getValue());
        r0.L$0 = r4;
        r0.L$1 = r6;
        r0.L$2 = r5;
        r0.L$3 = r4;
        r0.L$4 = r13;
        r0.label = 1;
        if (InterruptibleKt.runInterruptible$default(null, r72, r0, 1, null) != r92) goto L20;
        return r92;
    L20:
        r7 = r4;
        goto L21
    L22:
        return r4;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r8);
        Map<SessionSubscriber.Name, Dependency> r83 = dependencies;
        LinkedHashMap r14 = new LinkedHashMap(MapsKt.mapCapacity(r83.size()));
        r6 = r83.entrySet().iterator();
        r4 = r14;
    L7:
        r0 = new FirebaseSessionsDependencies$getRegisteredSubscribers$1(this, r9);
        goto L8
    }

    public final SessionSubscriber getSubscriber$com_google_firebase_firebase_sessions(SessionSubscriber.Name r2) {
        SessionSubscriber r1 = getDependency(r2).getSubscriber();
        if (r1 == null) goto L5;
        return r1;
    L5:
        LiveData$$ExternalSyntheticBUOutline0.m("Subscriber ", r2, " has not been registered.");
        return null;
    }
}
