package com.google.android.gms.common.api.internal;

import android.os.SystemClock;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
final class zaby implements OnCompleteListener {
    private final GoogleApiManager zaa;
    private final int zab;
    private final ApiKey zac;
    private final long zad;
    private final long zae;

    public zaby(GoogleApiManager r1, int r2, ApiKey r3, long r4, long r6, String r8, String r9) {
        this.zaa = r1;
        this.zab = r2;
        this.zac = r3;
        this.zad = r4;
        this.zae = r6;
    }

    public static zaby zaa(GoogleApiManager r11, int r12, ApiKey r13) {
        if (r11.zam() == false) goto L33;
        RootTelemetryConfiguration r0 = RootTelemetryConfigManager.getInstance().getConfig();
        if (r0 != null) goto L8;
        boolean r02 = true;
    L23:
        long r2 = 0;
        if (r02 == false) goto L26;
        long r5 = System.currentTimeMillis();
    L27:
        if (r02 == false) goto L30;
        r2 = SystemClock.elapsedRealtime();
    L30:
        return new zaby(r11, r12, r13, r5, r2, null, null);
    L26:
        r5 = 0;
        goto L27
    L8:
        if (r0.getMethodInvocationTelemetryEnabled() == false) goto L20;
        r02 = r0.getMethodTimingTelemetryEnabled();
        zabk r1 = r11.zag(r13);
        if (r1 == null) goto L23;
        if ((r1.zaf() instanceof BaseGmsClient) == false) goto L31;
        BaseGmsClient r22 = (BaseGmsClient) r1.zaf();
        if (r22.hasConnectionInfo() == false) goto L23;
        if (r22.isConnecting() == true) goto L23;
        ConnectionTelemetryConfiguration r03 = zab(r1, r22, r12);
        if (r03 == null) goto L32;
        r1.zas();
        r02 = r03.getMethodTimingTelemetryEnabled();
        goto L23
    L32:
        return null;
    L31:
        return null;
    L20:
        return null;
    L33:
        return null;
    }

    private static ConnectionTelemetryConfiguration zab(zabk r2, BaseGmsClient r3, int r4) {
        ConnectionTelemetryConfiguration r32 = r3.getTelemetryConfiguration();
        if (r32 != null) goto L5;
    L20:
        return null;
    L5:
        if (r32.getMethodInvocationTelemetryEnabled() == false) goto L20;
        int[] r1 = r32.getMethodInvocationMethodKeyAllowlist();
        if (r1 != null) goto L15;
        int[] r12 = r32.getMethodInvocationMethodKeyDisallowlist();
        if (r12 == null) goto L18;
        if (ArrayUtils.contains(r12, r4) == true) goto L20;
    L18:
        if (r2.zar() >= r32.getMaxMethodInvocationsLogged()) goto L20;
        return r32;
    L15:
        if (ArrayUtils.contains(r1, r4) == true) goto L18;
        goto L20
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task r26) {
        GoogleApiManager r1 = this.zaa;
        if (r1.zam() == false) goto L58;
        RootTelemetryConfiguration r2 = RootTelemetryConfigManager.getInstance().getConfig();
        if (r2 != null) goto L8;
    L9:
        zabk r3 = r1.zag(this.zac);
        if (r3 != null) goto L12;
        return;
    L12:
        if ((r3.zaf() instanceof BaseGmsClient) == false) goto L56;
        BaseGmsClient r4 = (BaseGmsClient) r3.zaf();
        long r5 = this.zad;
        boolean r10 = true;
        if (r5 <= 0) goto L16;
        boolean r12 = true;
    L17:
        int r23 = r4.getGCoreServiceId();
        if (r2 == null) goto L33;
        r12 = r12 & r2.getMethodTimingTelemetryEnabled();
        int r14 = r2.getBatchPeriodMillis();
        int r15 = r2.getMaxMethodInvocationsInBatch();
        int r22 = r2.getVersion();
        if (r4.hasConnectionInfo() == true) goto L22;
    L32:
        long r9 = r5;
        int r32 = r14;
    L31:
        int r52 = r15;
    L34:
        int r6 = -1;
        if (r26.isSuccessful() == false) goto L38;
        int r152 = 0;
        int r16 = 0;
    L48:
        if (r12 == false) goto L51;
        long r62 = this.zae;
        long r11 = System.currentTimeMillis();
        r6 = (int) (SystemClock.elapsedRealtime() - r62);
        long r17 = r9;
        long r19 = r11;
    L52:
        r1.zar(new MethodInvocation(this.zab, r152, r16, r17, r19, null, null, r23, r6), r22, r32, r52);
        return;
    L51:
        r17 = 0;
        r19 = 0;
        goto L52
    L38:
        if (r26.isCanceled() == false) goto L40;
        r16 = -1;
        r152 = 100;
        goto L48
    L40:
        Exception r42 = r26.getException();
        if ((r42 instanceof ApiException) == false) goto L47;
        Status r43 = ((ApiException) r42).getStatus();
        int r112 = r43.getStatusCode();
        ConnectionResult r44 = r43.getConnectionResult();
        if (r44 != null) goto L46;
    L44:
        r16 = -1;
    L45:
        r152 = r112;
        goto L48
    L46:
        r16 = r44.getErrorCode();
        goto L45
    L47:
        r112 = 101;
        goto L44
    L22:
        if (r4.isConnecting() == true) goto L32;
        ConnectionTelemetryConfiguration r33 = zab(r3, r4, this.zab);
        if (r33 != null) goto L26;
        return;
    L26:
        if (r33.getMethodTimingTelemetryEnabled() == false) goto L29;
        if (r5 <= 0) goto L29;
    L30:
        r15 = r33.getMaxMethodInvocationsLogged();
        r12 = r10;
        r32 = r14;
        r9 = r5;
    L29:
        r10 = false;
        goto L30
    L33:
        r9 = r5;
        r22 = 0;
        r52 = 100;
        r32 = 5000;
        goto L34
    L16:
        r12 = false;
        goto L17
    L56:
        return;
    L8:
        if (r2.getMethodInvocationTelemetryEnabled() == true) goto L9;
        return;
    }
}
