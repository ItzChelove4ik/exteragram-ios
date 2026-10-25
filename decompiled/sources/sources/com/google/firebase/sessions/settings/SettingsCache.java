package com.google.firebase.sessions.settings;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u000f\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&¢\u0006\u0002\u0010\u0005J\u000f\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010\bJ\u000f\u0010\t\u001a\u0004\u0018\u00010\nH&¢\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH¦@¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/google/firebase/sessions/settings/SettingsCache;", _UrlKt.FRAGMENT_ENCODE_SET, "hasCacheExpired", _UrlKt.FRAGMENT_ENCODE_SET, "sessionsEnabled", "()Ljava/lang/Boolean;", "sessionSamplingRate", _UrlKt.FRAGMENT_ENCODE_SET, "()Ljava/lang/Double;", "sessionRestartTimeout", _UrlKt.FRAGMENT_ENCODE_SET, "()Ljava/lang/Integer;", "updateConfigs", _UrlKt.FRAGMENT_ENCODE_SET, "sessionConfigs", "Lcom/google/firebase/sessions/settings/SessionConfigs;", "(Lcom/google/firebase/sessions/settings/SessionConfigs;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public interface SettingsCache {
    boolean hasCacheExpired();

    Integer sessionRestartTimeout();

    Double sessionSamplingRate();

    Boolean sessionsEnabled();

    Object updateConfigs(SessionConfigs r1, Continuation<? super Unit> r2);
}
