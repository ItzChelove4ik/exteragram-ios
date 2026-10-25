package com.google.firebase.sessions.settings;

import android.util.Log;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "it", "Lorg/json/JSONObject;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "com.google.firebase.sessions.settings.RemoteSettings$updateSettings$2$1", f = "RemoteSettings.kt", i = {}, l = {126}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class RemoteSettings$updateSettings$2$1 extends SuspendLambda implements Function2<JSONObject, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ RemoteSettings this$0;

    public RemoteSettings$updateSettings$2$1(RemoteSettings r1, Continuation<? super RemoteSettings$updateSettings$2$1> r2) {
        this.this$0 = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        RemoteSettings$updateSettings$2$1 r0 = new RemoteSettings$updateSettings$2$1(this.this$0, r3);
        r0.L$0 = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(JSONObject r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(JSONObject r1, Continuation<? super Unit> r2) {
        return ((RemoteSettings$updateSettings$2$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r15) {
        Object r4 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r5 = this.label;
        Integer r7 = null;
        if (r5 == 0) goto L8;
        if (r5 != 1) goto L6;
        ResultKt.throwOnFailure(r15);
    L50:
        return Unit.INSTANCE;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L8:
        ResultKt.throwOnFailure(r15);
        JSONObject r152 = (JSONObject) this.L$0;
        Log.d("FirebaseSessions", "Fetched settings: " + r152);
        if (r152.has("app_quality") == false) goto L41;
        JSONObject r153 = (JSONObject) r152.get("app_quality");
    L15:
        e = move-exception;
        JSONException r154 = e;
        Integer r0 = null;
        Integer r1 = null;
        Double r2 = null;
    L40:
        Boxing.boxInt(Log.e("FirebaseSessions", "Error parsing the configs remotely fetched: ", r154));
        Integer r11 = r1;
        Double r10 = r2;
        ?? r9 = r7;
        r7 = r0;
    L42:
        SettingsCache r155 = RemoteSettings.access$getSettingsCache$p(this.this$0);
        if (r7 == null) goto L45;
        int r02 = r7.intValue();
    L46:
        SessionConfigs r8 = new SessionConfigs(r9, r10, r11, Boxing.boxInt(r02), Boxing.boxLong(RemoteSettings.access$getTimeProvider$p(this.this$0).currentTime().getSeconds()));
        this.label = 1;
        if (r155.updateConfigs(r8, this) != r4) goto L50;
        return r4;
    L45:
        r02 = RemoteSettings.access$getCompanion$p().getDefaultCacheDuration();
        goto L46
    L12:
        if (r153.has("sessions_enabled") == false) goto L17;
        Integer r3 = (Boolean) r153.get("sessions_enabled");     // Catch: JSONException -> L15
    L55:
    L22:
        e = move-exception;
        r154 = e;
        r0 = null;
        r1 = null;
        r2 = null;
    L24:
        r7 = r3;
        goto L40
    L19:
        if (r153.has("sampling_rate") == false) goto L25;
        r2 = (Double) r153.get("sampling_rate");     // Catch: JSONException -> L22
    L57:
    L30:
        e = move-exception;
        r154 = e;
        r0 = null;
        r1 = null;
        goto L24
    L27:
        if (r153.has("session_timeout_seconds") == false) goto L32;
        r1 = (Integer) r153.get("session_timeout_seconds");     // Catch: JSONException -> L30
    L51:
    L36:
        e = move-exception;
        r154 = e;
        r0 = r7;
        goto L24
    L34:
        if (r153.has("cache_duration") == false) goto L38;
        r7 = (Integer) r153.get("cache_duration");     // Catch: JSONException -> L36
    L38:
        Unit r156 = Unit.INSTANCE;     // Catch: JSONException -> L36
        r11 = r1;
        r10 = r2;
        r9 = r3;
        goto L42
    L32:
        r1 = null;
        goto L51
    L25:
        r2 = null;
        goto L57
    L17:
        r3 = null;
        goto L55
    L41:
        r9 = 0;
        r10 = null;
        r11 = null;
        goto L42
    }
}
