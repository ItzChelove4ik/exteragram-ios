package com.google.firebase.sessions;

import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0081\b\u0018\u0000 -2\u00020\u0001:\u0002.-B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006¢\u0006\u0004\b\n\u0010\u000bBE\b\u0010\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u0010J'\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0001¢\u0006\u0004\b\u0017\u0010\u0018J>\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006HÆ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u001a\u0010\"\u001a\u00020!2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\"\u0010#R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010$\u001a\u0004\b%\u0010&R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010'\u001a\u0004\b(\u0010)R%\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\t\u0010*\u001a\u0004\b+\u0010,¨\u0006/"}, d2 = {"Lcom/google/firebase/sessions/SessionData;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/firebase/sessions/SessionDetails;", "sessionDetails", "Lcom/google/firebase/sessions/Time;", "backgroundTime", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/firebase/sessions/ProcessData;", "processDataMap", "<init>", "(Lcom/google/firebase/sessions/SessionDetails;Lcom/google/firebase/sessions/Time;Ljava/util/Map;)V", _UrlKt.FRAGMENT_ENCODE_SET, "seen0", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "(ILcom/google/firebase/sessions/SessionDetails;Lcom/google/firebase/sessions/Time;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", _UrlKt.FRAGMENT_ENCODE_SET, "write$Self$com_google_firebase_firebase_sessions", "(Lcom/google/firebase/sessions/SessionData;Lkotlinx/serialization/encoding/CompositeEncoder;Lkotlinx/serialization/descriptors/SerialDescriptor;)V", "write$Self", "copy", "(Lcom/google/firebase/sessions/SessionDetails;Lcom/google/firebase/sessions/Time;Ljava/util/Map;)Lcom/google/firebase/sessions/SessionData;", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Lcom/google/firebase/sessions/SessionDetails;", "getSessionDetails", "()Lcom/google/firebase/sessions/SessionDetails;", "Lcom/google/firebase/sessions/Time;", "getBackgroundTime", "()Lcom/google/firebase/sessions/Time;", "Ljava/util/Map;", "getProcessDataMap", "()Ljava/util/Map;", "Companion", "$serializer", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SessionData {

    @JvmField
    private static final Lazy<KSerializer<Object>>[] $childSerializers = null;
    public static final Companion Companion = null;
    private final Time backgroundTime;
    private final Map<String, ProcessData> processDataMap;
    private final SessionDetails sessionDetails;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/google/firebase/sessions/SessionData$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/firebase/sessions/SessionData;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final KSerializer<SessionData> serializer() {
            return SessionData$$serializer.INSTANCE;
        }
    }

    /* renamed from: $r8$lambda$xlHOtoaRMTNSTL6lG-7AqFS0Vdk, reason: not valid java name */
    public static /* synthetic */ KSerializer m1983$r8$lambda$xlHOtoaRMTNSTL6lG7AqFS0Vdk() {
        return _childSerializers$_anonymous_();
    }

    static {
        Companion = new Companion(null);
        $childSerializers = new Lazy[]{null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new SessionData$$ExternalSyntheticLambda0())};
    }

    public /* synthetic */ SessionData(int r2, SessionDetails r3, Time r4, Map r5, SerializationConstructorMarker r6) {
        if (1 == (r2 & 1)) goto L5;
        PluginExceptionsKt.throwMissingFieldException(r2, 1, SessionData$$serializer.INSTANCE.getDescriptor());
    L5:
        this.sessionDetails = r3;
        if ((r2 & 2) != 0) goto L8;
        this.backgroundTime = null;
    L10:
        if ((r2 & 4) != 0) goto L13;
        this.processDataMap = null;
        return;
    L13:
        this.processDataMap = r5;
        return;
    L8:
        this.backgroundTime = r4;
        goto L10
    }

    public SessionData(SessionDetails r1, Time r2, Map<String, ProcessData> r3) {
        this.sessionDetails = r1;
        this.backgroundTime = r2;
        this.processDataMap = r3;
    }

    public /* synthetic */ SessionData(SessionDetails r2, Time r3, Map r4, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = null;
    L6:
        if ((r5 & 4) == 0) goto L8;
        r4 = null;
    L8:
        this(r2, r3, r4);
    }

    private static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
        return new LinkedHashMapSerializer(StringSerializer.INSTANCE, ProcessData$$serializer.INSTANCE);
    }

    public static final /* synthetic */ Lazy[] access$get$childSerializers$cp() {
        return $childSerializers;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SessionData copy$default(SessionData r0, SessionDetails r1, Time r2, Map r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = r0.sessionDetails;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = r0.backgroundTime;
    L9:
        if ((r4 & 4) == 0) goto L12;
        r3 = r0.processDataMap;
    L12:
        return r0.copy(r1, r2, r3);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$com_google_firebase_firebase_sessions(SessionData r4, CompositeEncoder r5, SerialDescriptor r6) {
        Lazy<KSerializer<Object>>[] r0 = $childSerializers;
        r5.encodeSerializableElement(r6, 0, SessionDetails$$serializer.INSTANCE, r4.sessionDetails);
        if (r5.shouldEncodeElementDefault(r6, 1) == false) goto L6;
    L7:
        r5.encodeNullableSerializableElement(r6, 1, Time$$serializer.INSTANCE, r4.backgroundTime);
    L9:
        if (r5.shouldEncodeElementDefault(r6, 2) == false) goto L12;
    L13:
        r5.encodeNullableSerializableElement(r6, 2, r0[2].getValue(), r4.processDataMap);
        return;
    L12:
        if (r4.processDataMap != null) goto L13;
        return;
    L6:
        if (r4.backgroundTime == null) goto L9;
        goto L7
    }

    public final SessionData copy(SessionDetails r1, Time r2, Map<String, ProcessData> r3) {
        return new SessionData(r1, r2, r3);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof SessionData) == true) goto L8;
        return false;
    L8:
        SessionData r52 = (SessionData) r5;
        if (Intrinsics.areEqual(this.sessionDetails, r52.sessionDetails) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.backgroundTime, r52.backgroundTime) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.processDataMap, r52.processDataMap) == true) goto L17;
        return false;
    L17:
        return true;
    }

    public final Time getBackgroundTime() {
        return this.backgroundTime;
    }

    public final Map<String, ProcessData> getProcessDataMap() {
        return this.processDataMap;
    }

    public final SessionDetails getSessionDetails() {
        return this.sessionDetails;
    }

    public int hashCode() {
        int r0 = this.sessionDetails.hashCode() * 31;
        Time r1 = this.backgroundTime;
        int r2 = 0;
        if (r1 != null) goto L5;
        int r12 = 0;
    L6:
        int r02 = (r0 + r12) * 31;
        Map<String, ProcessData> r3 = this.processDataMap;
        if (r3 == null) goto L11;
        r2 = r3.hashCode();
    L11:
        return r02 + r2;
    L5:
        r12 = r1.hashCode();
        goto L6
    }

    public String toString() {
        return "SessionData(sessionDetails=" + this.sessionDetails + ", backgroundTime=" + this.backgroundTime + ", processDataMap=" + this.processDataMap + ')';
    }
}
