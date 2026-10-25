package com.google.firebase.sessions.settings;

import android.net.Uri;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.sessions.ApplicationInfo;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007Jj\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000b2\"\u0010\r\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000e2\"\u0010\u0012\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000eH\u0096@¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/google/firebase/sessions/settings/RemoteSettingsFetcher;", "Lcom/google/firebase/sessions/settings/CrashlyticsSettingsFetcher;", "appInfo", "Lcom/google/firebase/sessions/ApplicationInfo;", "blockingDispatcher", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Lcom/google/firebase/sessions/ApplicationInfo;Lkotlin/coroutines/CoroutineContext;)V", "doConfigFetch", _UrlKt.FRAGMENT_ENCODE_SET, "headerOptions", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "onSuccess", "Lkotlin/Function2;", "Lorg/json/JSONObject;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "onFailure", "(Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "settingsUrl", "Ljava/net/URL;", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RemoteSettingsFetcher implements CrashlyticsSettingsFetcher {
    public static final Companion Companion = null;
    private final ApplicationInfo appInfo;
    private final CoroutineContext blockingDispatcher;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/google/firebase/sessions/settings/RemoteSettingsFetcher$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "FIREBASE_SESSIONS_BASE_URL_STRING", _UrlKt.FRAGMENT_ENCODE_SET, "FIREBASE_PLATFORM", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.google.firebase.sessions.settings.RemoteSettingsFetcher$doConfigFetch$2", f = "RemoteSettingsFetcher.kt", i = {}, l = {73, 75, 78}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nRemoteSettingsFetcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RemoteSettingsFetcher.kt\ncom/google/firebase/sessions/settings/RemoteSettingsFetcher$doConfigFetch$2\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,105:1\n216#2,2:106\n1#3:108\n*S KotlinDebug\n*F\n+ 1 RemoteSettingsFetcher.kt\ncom/google/firebase/sessions/settings/RemoteSettingsFetcher$doConfigFetch$2\n*L\n58#1:106,2\n*E\n"})
    /* renamed from: com.google.firebase.sessions.settings.RemoteSettingsFetcher$doConfigFetch$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Map<String, String> $headerOptions;
        final /* synthetic */ Function2<String, Continuation<? super Unit>, Object> $onFailure;
        final /* synthetic */ Function2<JSONObject, Continuation<? super Unit>, Object> $onSuccess;
        int label;
        final /* synthetic */ RemoteSettingsFetcher this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(RemoteSettingsFetcher r1, Map<String, String> r2, Function2<? super JSONObject, ? super Continuation<? super Unit>, ? extends Object> r3, Function2<? super String, ? super Continuation<? super Unit>, ? extends Object> r4, Continuation<? super AnonymousClass2> r5) {
            this.this$0 = r1;
            this.$headerOptions = r2;
            this.$onSuccess = r3;
            this.$onFailure = r4;
            super(2, r5);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
            return new AnonymousClass2(this.this$0, this.$headerOptions, this.$onSuccess, this.$onFailure, r8);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r6v0, types: [T, java.lang.String] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r8) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L14;
            if (r1 == 1) goto L10;
            if (r1 == 2) goto L10;
            if (r1 != 3) goto L8;
            ResultKt.throwOnFailure(r8);
        L38:
            return Unit.INSTANCE;
        L8:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L10:
            ResultKt.throwOnFailure(r8);     // Catch: Exception -> L12
            goto L38
        L14:
            ResultKt.throwOnFailure(r8);
            HttpsURLConnection r82 = (HttpsURLConnection) RemoteSettingsFetcher.access$settingsUrl(this.this$0).openConnection();     // Catch: Exception -> L12
            r82.setRequestMethod("GET");     // Catch: Exception -> L12
            r82.setRequestProperty("Accept", "application/json");     // Catch: Exception -> L12
            Iterator<Map.Entry<String, String>> r12 = this.$headerOptions.entrySet().iterator();     // Catch: Exception -> L12
        L17:
            if (r12.hasNext() == false) goto L19;
            Map.Entry<String, String> r5 = r12.next();     // Catch: Exception -> L12
            r82.setRequestProperty(r5.getKey(), r5.getValue());     // Catch: Exception -> L12
            goto L17
        L19:
            int r13 = r82.getResponseCode();     // Catch: Exception -> L12
            if (r13 != 200) goto L28;
            InputStream r83 = r82.getInputStream();     // Catch: Exception -> L12
            BufferedReader r14 = new BufferedReader(new InputStreamReader(r83));     // Catch: Exception -> L12
            StringBuilder r3 = new StringBuilder();     // Catch: Exception -> L12
            Ref.ObjectRef r52 = new Ref.ObjectRef();     // Catch: Exception -> L12
        L22:
            ?? r6 = r14.readLine();     // Catch: Exception -> L12
            r52.element = r6;     // Catch: Exception -> L12
            if (r6 == 0) goto L25;
            r3.append(r6);     // Catch: Exception -> L12
            goto L22
        L25:
            r14.close();     // Catch: Exception -> L12
            r83.close();     // Catch: Exception -> L12
            JSONObject r84 = new JSONObject(r3.toString());     // Catch: Exception -> L12
            Function2<JSONObject, Continuation<? super Unit>, Object> r15 = this.$onSuccess;     // Catch: Exception -> L12
            this.label = 1;     // Catch: Exception -> L12
            if (r15.invoke(r84, this) != r0) goto L38;
        L36:
            return r0;
        L28:
            Function2<String, Continuation<? super Unit>, Object> r85 = this.$onFailure;     // Catch: Exception -> L12
            String r16 = "Bad response code: " + r13;     // Catch: Exception -> L12
            this.label = 2;     // Catch: Exception -> L12
            if (r85.invoke(r16, this) != r0) goto L38;
        L12:
            e = move-exception;
            Function2<String, Continuation<? super Unit>, Object> r17 = this.$onFailure;
            String r32 = e.getMessage();
            if (r32 != null) goto L34;
            r32 = e.toString();
        L34:
            this.label = 3;
            if (r17.invoke(r32, this) != r0) goto L38;
            goto L36
        }
    }

    static {
        Companion = new Companion(null);
    }

    public RemoteSettingsFetcher(ApplicationInfo r1, @Blocking CoroutineContext r2) {
        this.appInfo = r1;
        this.blockingDispatcher = r2;
    }

    public static final /* synthetic */ URL access$settingsUrl(RemoteSettingsFetcher r0) {
        return r0.settingsUrl();
    }

    private final URL settingsUrl() {
        return new URL(new Uri.Builder().scheme("https").authority("firebase-settings.crashlytics.com").appendPath("spi").appendPath("v2").appendPath("platforms").appendPath("android").appendPath("gmp").appendPath(this.appInfo.getAppId()).appendPath("settings").appendQueryParameter("build_version", this.appInfo.getAndroidAppInfo().getAppBuildVersion()).appendQueryParameter("display_version", this.appInfo.getAndroidAppInfo().getVersionName()).build().toString());
    }

    @Override // com.google.firebase.sessions.settings.CrashlyticsSettingsFetcher
    public Object doConfigFetch(Map<String, String> r8, Function2<? super JSONObject, ? super Continuation<? super Unit>, ? extends Object> r9, Function2<? super String, ? super Continuation<? super Unit>, ? extends Object> r10, Continuation<? super Unit> r11) {
        Object r7 = BuildersKt.withContext(this.blockingDispatcher, new AnonymousClass2(this, r8, r9, r10, null), r11);
        if (r7 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r7;
    L6:
        return Unit.INSTANCE;
    }
}
