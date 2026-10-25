package com.google.firebase.sessions.settings;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0017\b\u0081\b\u0018\u0000 /2\u00020\u0001:\u00020/B9\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fBM\b\u0010\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u000b\u0010\u0010J'\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u001b\u001a\u00020\u001aHÖ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u001a\u0010 \u001a\u00020\u00022\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b \u0010!R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010$R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010%\u001a\u0004\b&\u0010'R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010(\u001a\u0004\b)\u0010*R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010(\u001a\u0004\b+\u0010*R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010,\u001a\u0004\b-\u0010.¨\u00061"}, d2 = {"Lcom/google/firebase/sessions/settings/SessionConfigs;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "sessionsEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "sessionSamplingRate", _UrlKt.FRAGMENT_ENCODE_SET, "sessionTimeoutSeconds", "cacheDurationSeconds", _UrlKt.FRAGMENT_ENCODE_SET, "cacheUpdatedTimeSeconds", "<init>", "(Ljava/lang/Boolean;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;)V", "seen0", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "(ILjava/lang/Boolean;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", _UrlKt.FRAGMENT_ENCODE_SET, "write$Self$com_google_firebase_firebase_sessions", "(Lcom/google/firebase/sessions/settings/SessionConfigs;Lkotlinx/serialization/encoding/CompositeEncoder;Lkotlinx/serialization/descriptors/SerialDescriptor;)V", "write$Self", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Boolean;", "getSessionsEnabled", "()Ljava/lang/Boolean;", "Ljava/lang/Double;", "getSessionSamplingRate", "()Ljava/lang/Double;", "Ljava/lang/Integer;", "getSessionTimeoutSeconds", "()Ljava/lang/Integer;", "getCacheDurationSeconds", "Ljava/lang/Long;", "getCacheUpdatedTimeSeconds", "()Ljava/lang/Long;", "Companion", "$serializer", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SessionConfigs {
    public static final Companion Companion = null;
    private final Integer cacheDurationSeconds;
    private final Long cacheUpdatedTimeSeconds;
    private final Double sessionSamplingRate;
    private final Integer sessionTimeoutSeconds;
    private final Boolean sessionsEnabled;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/google/firebase/sessions/settings/SessionConfigs$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/google/firebase/sessions/settings/SessionConfigs;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final KSerializer<SessionConfigs> serializer() {
            return SessionConfigs$$serializer.INSTANCE;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public /* synthetic */ SessionConfigs(int r2, Boolean r3, Double r4, Integer r5, Integer r6, Long r7, SerializationConstructorMarker r8) {
        if (31 == (r2 & 31)) goto L5;
        PluginExceptionsKt.throwMissingFieldException(r2, 31, SessionConfigs$$serializer.INSTANCE.getDescriptor());
    L5:
        this.sessionsEnabled = r3;
        this.sessionSamplingRate = r4;
        this.sessionTimeoutSeconds = r5;
        this.cacheDurationSeconds = r6;
        this.cacheUpdatedTimeSeconds = r7;
    }

    public SessionConfigs(Boolean r1, Double r2, Integer r3, Integer r4, Long r5) {
        this.sessionsEnabled = r1;
        this.sessionSamplingRate = r2;
        this.sessionTimeoutSeconds = r3;
        this.cacheDurationSeconds = r4;
        this.cacheUpdatedTimeSeconds = r5;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$com_google_firebase_firebase_sessions(SessionConfigs r3, CompositeEncoder r4, SerialDescriptor r5) {
        r4.encodeNullableSerializableElement(r5, 0, BooleanSerializer.INSTANCE, r3.sessionsEnabled);
        r4.encodeNullableSerializableElement(r5, 1, DoubleSerializer.INSTANCE, r3.sessionSamplingRate);
        IntSerializer r0 = IntSerializer.INSTANCE;
        r4.encodeNullableSerializableElement(r5, 2, r0, r3.sessionTimeoutSeconds);
        r4.encodeNullableSerializableElement(r5, 3, r0, r3.cacheDurationSeconds);
        r4.encodeNullableSerializableElement(r5, 4, LongSerializer.INSTANCE, r3.cacheUpdatedTimeSeconds);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof SessionConfigs) == true) goto L8;
        return false;
    L8:
        SessionConfigs r52 = (SessionConfigs) r5;
        if (Intrinsics.areEqual(this.sessionsEnabled, r52.sessionsEnabled) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.sessionSamplingRate, r52.sessionSamplingRate) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.sessionTimeoutSeconds, r52.sessionTimeoutSeconds) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.cacheDurationSeconds, r52.cacheDurationSeconds) == true) goto L21;
        return false;
    L21:
        if (Intrinsics.areEqual(this.cacheUpdatedTimeSeconds, r52.cacheUpdatedTimeSeconds) == true) goto L23;
        return false;
    L23:
        return true;
    }

    public final Integer getCacheDurationSeconds() {
        return this.cacheDurationSeconds;
    }

    public final Long getCacheUpdatedTimeSeconds() {
        return this.cacheUpdatedTimeSeconds;
    }

    public final Double getSessionSamplingRate() {
        return this.sessionSamplingRate;
    }

    public final Integer getSessionTimeoutSeconds() {
        return this.sessionTimeoutSeconds;
    }

    public final Boolean getSessionsEnabled() {
        return this.sessionsEnabled;
    }

    public int hashCode() {
        Boolean r0 = this.sessionsEnabled;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = r02 * 31;
        Double r2 = this.sessionSamplingRate;
        if (r2 != null) goto L9;
        int r22 = 0;
    L10:
        int r04 = (r03 + r22) * 31;
        Integer r23 = this.sessionTimeoutSeconds;
        if (r23 != null) goto L13;
        int r24 = 0;
    L14:
        int r05 = (r04 + r24) * 31;
        Integer r25 = this.cacheDurationSeconds;
        if (r25 != null) goto L17;
        int r26 = 0;
    L18:
        int r06 = (r05 + r26) * 31;
        Long r3 = this.cacheUpdatedTimeSeconds;
        if (r3 == null) goto L23;
        r1 = r3.hashCode();
    L23:
        return r06 + r1;
    L17:
        r26 = r25.hashCode();
        goto L18
    L13:
        r24 = r23.hashCode();
        goto L14
    L9:
        r22 = r2.hashCode();
        goto L10
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public String toString() {
        return "SessionConfigs(sessionsEnabled=" + this.sessionsEnabled + ", sessionSamplingRate=" + this.sessionSamplingRate + ", sessionTimeoutSeconds=" + this.sessionTimeoutSeconds + ", cacheDurationSeconds=" + this.cacheDurationSeconds + ", cacheUpdatedTimeSeconds=" + this.cacheUpdatedTimeSeconds + ')';
    }
}
