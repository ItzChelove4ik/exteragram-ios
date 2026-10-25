package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.p000authapi.zbbc;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes4.dex */
public final class zbb implements Runnable {
    private static final Logger zba = null;
    private final String zbb;
    private final StatusPendingResult zbc;

    static {
        zba = new Logger("RevokeAccessOperation", new String[0]);
    }

    public zbb(String r2) {
        this.zbb = Preconditions.checkNotEmpty(r2);
        this.zbc = new StatusPendingResult(null);
    }

    public static PendingResult zba(String r1) {
        if (r1 == null) goto L4;
        zbb r0 = new zbb(r1);
        new Thread(r0).start();
        return r0.zbc;
    L4:
        return PendingResults.immediateFailedResult(new Status(4), null);
    }

    @Override // java.lang.Runnable
    public final void run() {
        Status r0 = Status.RESULT_INTERNAL_ERROR;
        String r5 = this.zbb;     // Catch: Exception -> L9 IOException -> L11
        StringBuilder r7 = new StringBuilder(String.valueOf(r5).length() + 50);     // Catch: Exception -> L9 IOException -> L11
        r7.append("https://accounts.google.com/o/oauth2/revoke?token=");     // Catch: Exception -> L9 IOException -> L11
        r7.append(r5);     // Catch: Exception -> L9 IOException -> L11
        URL r4 = new URL(r7.toString());     // Catch: Exception -> L9 IOException -> L11
        int r2 = zbbc.$r8$clinit;     // Catch: Exception -> L9 IOException -> L11
        HttpURLConnection r22 = (HttpURLConnection) r4.openConnection();     // Catch: Exception -> L9 IOException -> L11
        r22.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");     // Catch: Exception -> L9 IOException -> L11
        int r23 = r22.getResponseCode();     // Catch: Exception -> L9 IOException -> L11
        if (r23 != 200) goto L6;
        r0 = Status.RESULT_SUCCESS;     // Catch: Exception -> L9 IOException -> L11
    L7:
        Logger r42 = zba;     // Catch: Exception -> L9 IOException -> L11
        StringBuilder r6 = new StringBuilder(String.valueOf(r23).length() + 15);     // Catch: Exception -> L9 IOException -> L11
        r6.append("Response Code: ");     // Catch: Exception -> L9 IOException -> L11
        r6.append(r23);     // Catch: Exception -> L9 IOException -> L11
        r42.d(r6.toString(), new Object[0]);     // Catch: Exception -> L9 IOException -> L11
    L15:
        this.zbc.setResult(r0);
        return;
    L6:
        zba.e("Unable to revoke access!", new Object[0]);     // Catch: Exception -> L9 IOException -> L11
    L11:
        e = move-exception;
        Logger r24 = zba;
        String r1 = "IOException when revoking access: ".concat(String.valueOf(e.toString()));
        r24.e(r1, new Object[0]);
    L9:
        e = move-exception;
        Logger r25 = zba;
        String r12 = "Exception when revoking access: ".concat(String.valueOf(e.toString()));
        r25.e(r12, new Object[0]);
        goto L15
    }
}
