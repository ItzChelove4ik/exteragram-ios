package com.google.firebase.sessions.settings;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u001d\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0017\u0010\u0014\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000e\u0010\u0017\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001b"}, d2 = {"Lcom/google/firebase/sessions/settings/SessionsSettings;", _UrlKt.FRAGMENT_ENCODE_SET, "localOverrideSettings", "Lcom/google/firebase/sessions/settings/SettingsProvider;", "remoteSettings", "<init>", "(Lcom/google/firebase/sessions/settings/SettingsProvider;Lcom/google/firebase/sessions/settings/SettingsProvider;)V", "sessionsEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "getSessionsEnabled", "()Z", "samplingRate", _UrlKt.FRAGMENT_ENCODE_SET, "getSamplingRate", "()D", "sessionRestartTimeout", "Lkotlin/time/Duration;", "getSessionRestartTimeout-UwyO8pc", "()J", "isValidSamplingRate", "isValidSessionRestartTimeout", "isValidSessionRestartTimeout-LRDsOJo", "(J)Z", "updateSettings", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SessionsSettings {
    public static final Companion Companion = null;
    private final SettingsProvider localOverrideSettings;
    private final SettingsProvider remoteSettings;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/google/firebase/sessions/settings/SessionsSettings$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.sessions.settings.SessionsSettings", f = "SessionsSettings.kt", i = {}, l = {98, 99}, m = "updateSettings", n = {}, s = {})
    /* renamed from: com.google.firebase.sessions.settings.SessionsSettings$updateSettings$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SessionsSettings this$0;

        public AnonymousClass1(SessionsSettings r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.updateSettings(this);
        }
    }

    static {
        Companion = new Companion(null);
    }

    public SessionsSettings(SettingsProvider r1, SettingsProvider r2) {
        this.localOverrideSettings = r1;
        this.remoteSettings = r2;
    }

    private final boolean isValidSamplingRate(double r4) {
        if (0.0d <= r4) goto L5;
    L8:
        return false;
    L5:
        if (r4 > 1.0d) goto L8;
        return true;
    }

    /* renamed from: isValidSessionRestartTimeout-LRDsOJo, reason: not valid java name */
    private final boolean m1986isValidSessionRestartTimeoutLRDsOJo(long r1) {
        if (Duration.m3402isPositiveimpl(r1) == true) goto L5;
        return false;
    L5:
        if (Duration.m3397isFiniteimpl(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final double getSamplingRate() {
        Double r0 = this.localOverrideSettings.getSamplingRate();
        if (r0 == null) goto L7;
        double r02 = r0.doubleValue();
        if (isValidSamplingRate(r02) == false) goto L7;
        return r02;
    L7:
        Double r03 = this.remoteSettings.getSamplingRate();
        if (r03 == null) goto L12;
        double r04 = r03.doubleValue();
        if (isValidSamplingRate(r04) == false) goto L14;
        return r04;
    L14:
        return 1.0d;
    L12:
        return 1.0d;
    }

    /* renamed from: getSessionRestartTimeout-UwyO8pc, reason: not valid java name */
    public final long m1987getSessionRestartTimeoutUwyO8pc() {
        Duration r0 = this.localOverrideSettings.mo1985getSessionRestartTimeoutFghU774();
        if (r0 == null) goto L7;
        long r02 = r0.m3421unboximpl();
        if (m1986isValidSessionRestartTimeoutLRDsOJo(r02) == false) goto L7;
        return r02;
    L7:
        Duration r03 = this.remoteSettings.mo1985getSessionRestartTimeoutFghU774();
        if (r03 == null) goto L12;
        long r04 = r03.m3421unboximpl();
        if (m1986isValidSessionRestartTimeoutLRDsOJo(r04) == false) goto L12;
        return r04;
    L12:
        Duration.Companion r3 = Duration.Companion;
        return DurationKt.toDuration(30, DurationUnit.MINUTES);
    }

    public final boolean getSessionsEnabled() {
        Boolean r0 = this.localOverrideSettings.getSessionEnabled();
        if (r0 != null) goto L5;
        Boolean r1 = this.remoteSettings.getSessionEnabled();
        if (r1 != null) goto L9;
        return true;
    L9:
        return r1.booleanValue();
    L5:
        return r0.booleanValue();
    }

    public final Object updateSettings(Continuation<? super Unit> r6) {
        if ((r6 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        ResultKt.throwOnFailure(r62);
    L23:
        return Unit.INSTANCE;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r62);
    L19:
        SettingsProvider r5 = this.remoteSettings;
        r0.label = 2;
        if (r5.updateSettings(r0) != r12) goto L23;
    L21:
        return r12;
    L16:
        ResultKt.throwOnFailure(r62);
        SettingsProvider r63 = this.localOverrideSettings;
        r0.label = 1;
        if (r63.updateSettings(r0) != r12) goto L19;
    L7:
        r0 = new AnonymousClass1(this, r6);
        goto L8
    }
}
