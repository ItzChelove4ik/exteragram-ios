package com.google.firebase.sessions;

import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.installations.InstallationTokenResult;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.tasks.TasksKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0000\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/google/firebase/sessions/InstallationId;", _UrlKt.FRAGMENT_ENCODE_SET, "fid", _UrlKt.FRAGMENT_ENCODE_SET, "authToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getFid", "()Ljava/lang/String;", "getAuthToken", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class InstallationId {
    public static final Companion Companion = null;
    private final String authToken;
    private final String fid;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/google/firebase/sessions/InstallationId$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Lcom/google/firebase/sessions/InstallationId;", "firebaseInstallations", "Lcom/google/firebase/installations/FirebaseInstallationsApi;", "(Lcom/google/firebase/installations/FirebaseInstallationsApi;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r9v12, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r9v15 */
        /* JADX WARN: Type inference failed for: r9v16 */
        /* JADX WARN: Type inference failed for: r9v17 */
        /* JADX WARN: Type inference failed for: r9v3 */
        /* JADX WARN: Type inference failed for: r9v4, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r9v5, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r9v8 */
        public final Object create(FirebaseInstallationsApi r9, Continuation<? super InstallationId> r10) {
            if ((r10 instanceof InstallationId$Companion$create$1) == false) goto L7;
            InstallationId$Companion$create$1 r0 = (InstallationId$Companion$create$1) r10;
            int r1 = r0.label;
            if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
            r0.label = r1 - Integer.MIN_VALUE;
        L8:
            Object r8 = r0.result;
            Object r102 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r12 = r0.label;
            String r6 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r12 == 0) goto L24;
            if (r12 == 1) goto L19;
            if (r12 != 2) goto L17;
            ?? r92 = (String) r0.L$0;
            ResultKt.throwOnFailure(r8);     // Catch: Exception -> L15
            r9 = r92;
        L34:
            String r82 = (String) r8;     // Catch: Exception -> L15
            ?? r93 = r9;
            if (r82 == null) goto L40;
            r6 = r82;
            r93 = r9;
        L40:
            return new InstallationId(r6, r93, null);
        L17:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L19:
            FirebaseInstallationsApi r94 = (FirebaseInstallationsApi) r0.L$0;
            ResultKt.throwOnFailure(r8);     // Catch: Exception -> L22
            r9 = r94;
        L28:
            FirebaseInstallationsApi r7 = r9;
            ?? r95 = ((InstallationTokenResult) r8).getToken();     // Catch: Exception -> L22
            FirebaseInstallationsApi r83 = r7;
        L31:
            Task<String> r84 = r83.getId();     // Catch: Exception -> L15
            r0.L$0 = r95;     // Catch: Exception -> L15
            r0.label = 2;     // Catch: Exception -> L15
            r8 = TasksKt.await(r84, r0);     // Catch: Exception -> L15
            r9 = r95;
            if (r8 != r102) goto L34;
        L33:
            return r102;
        L24:
            ResultKt.throwOnFailure(r8);
            Task<InstallationTokenResult> r85 = r9.getToken(false);     // Catch: Exception -> L22
            r0.L$0 = r9;     // Catch: Exception -> L22
            r0.label = 1;     // Catch: Exception -> L22
            r8 = TasksKt.await(r85, r0);     // Catch: Exception -> L22
            r9 = r9;
            if (r8 != r102) goto L28;
        L22:
            e = move-exception;
            Log.w("FirebaseSessions", "Error getting authentication token.", e);
            r83 = r9;
            r95 = _UrlKt.FRAGMENT_ENCODE_SET;
        L15:
            e = move-exception;
            Log.w("FirebaseSessions", "Error getting Firebase installation id .", e);
            r93 = r9;
        L7:
            r0 = new InstallationId$Companion$create$1(this, r10);
            goto L8
        }
    }

    static {
        Companion = new Companion(null);
    }

    private InstallationId(String r1, String r2) {
        this.fid = r1;
        this.authToken = r2;
    }

    public /* synthetic */ InstallationId(String r1, String r2, DefaultConstructorMarker r3) {
        this(r1, r2);
    }

    public final String getAuthToken() {
        return this.authToken;
    }

    public final String getFid() {
        return this.fid;
    }
}
