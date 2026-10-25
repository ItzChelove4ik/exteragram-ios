package org.telegram.messenger.voip;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.ConnectionService;
import android.telecom.PhoneAccountHandle;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

@TargetApi(26)
/* loaded from: classes6.dex */
public class TelegramConnectionService extends ConnectionService {
    public TelegramConnectionService() {
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        FileLog.w("ConnectionService created");
        return;
    }

    @Override // android.telecom.ConnectionService
    public Connection onCreateIncomingConnection(PhoneAccountHandle r3, ConnectionRequest r4) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("onCreateIncomingConnection ");
    L5:
        Bundle r2 = r4.getExtras();
        if (r2.getInt("call_type") != 1) goto L15;
        VoIPService r22 = VoIPService.getSharedInstance();
        if (r22 != null) goto L11;
        return null;
    L11:
        if (r22.isOutgoing() == false) goto L14;
        return null;
    L14:
        return r22.getConnectionAndStartCall();
    L15:
        r2.getInt("call_type");
        return null;
    }

    @Override // android.telecom.ConnectionService
    public void onCreateIncomingConnectionFailed(PhoneAccountHandle r1, ConnectionRequest r2) {
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        FileLog.e("onCreateIncomingConnectionFailed ");
    L6:
        if (VoIPService.getSharedInstance() == null) goto L9;
        VoIPService.getSharedInstance().callFailedFromConnectionService();
        return;
    }

    @Override // android.telecom.ConnectionService
    public Connection onCreateOutgoingConnection(PhoneAccountHandle r3, ConnectionRequest r4) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("onCreateOutgoingConnection ");
    L5:
        Bundle r2 = r4.getExtras();
        if (r2.getInt("call_type") != 1) goto L12;
        VoIPService r22 = VoIPService.getSharedInstance();
        if (r22 != null) goto L11;
        return null;
    L11:
        return r22.getConnectionAndStartCall();
    L12:
        r2.getInt("call_type");
        return null;
    }

    @Override // android.telecom.ConnectionService
    public void onCreateOutgoingConnectionFailed(PhoneAccountHandle r1, ConnectionRequest r2) {
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        FileLog.e("onCreateOutgoingConnectionFailed ");
    L6:
        if (VoIPService.getSharedInstance() == null) goto L9;
        VoIPService.getSharedInstance().callFailedFromConnectionService();
        return;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        FileLog.w("ConnectionService destroyed");
        return;
    }
}
