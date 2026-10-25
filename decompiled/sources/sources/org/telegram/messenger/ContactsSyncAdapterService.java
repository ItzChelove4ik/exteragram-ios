package org.telegram.messenger;

import android.accounts.Account;
import android.accounts.OperationCanceledException;
import android.app.Service;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.IBinder;

/* loaded from: classes5.dex */
public class ContactsSyncAdapterService extends Service {
    private static SyncAdapterImpl sSyncAdapter;

    public static class SyncAdapterImpl extends AbstractThreadedSyncAdapter {
        private Context mContext;

        public SyncAdapterImpl(Context r2) {
            super(r2, true);
            this.mContext = r2;
        }

        @Override // android.content.AbstractThreadedSyncAdapter
        public void onPerformSync(Account r2, Bundle r3, String r4, ContentProviderClient r5, SyncResult r6) {
            ContactsSyncAdapterService.m3975$$Nest$smperformSync(this.mContext, r2, r3, r4, r5, r6);     // Catch: OperationCanceledException -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }
    }

    /* renamed from: -$$Nest$smperformSync, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3975$$Nest$smperformSync(Context r0, Account r1, Bundle r2, String r3, ContentProviderClient r4, SyncResult r5) {
        performSync(r0, r1, r2, r3, r4, r5);
    }

    static {
    }

    public ContactsSyncAdapterService() {
    }

    private SyncAdapterImpl getSyncAdapter() {
        if (sSyncAdapter != null) goto L6;
        sSyncAdapter = new SyncAdapterImpl(this);
    L6:
        return sSyncAdapter;
    }

    private static void performSync(Context r0, Account r1, Bundle r2, String r3, ContentProviderClient r4, SyncResult r5) {
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        FileLog.d("performSync: " + r1.toString());
        return;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r1) {
        return getSyncAdapter().getSyncAdapterBinder();
    }
}
