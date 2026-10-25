package org.telegram.messenger.voip;

import android.text.TextUtils;
import java.io.File;
import java.util.HashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.voip.Instance;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.Components.voip.VoIPHelper;

/* loaded from: classes.dex */
public class VoIPDebugToSend {
    private final int currentAccount;
    private final HashMap<Long, Data> pending;

    /* loaded from: classes6.dex */
    public final class Data {
        long access_hash;
        long callId;
        String logPath;
        Instance.FinalState state;
        final /* synthetic */ VoIPDebugToSend this$0;

        private Data(VoIPDebugToSend r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Data(VoIPDebugToSend r1, VoIPDebugToSendIA r2) {
            this(r1);
        }
    }

    /* renamed from: $r8$lambda$-rsujs8CUsOFHBW3o_DvrvL7BcA, reason: not valid java name */
    public static /* synthetic */ void m5284$r8$lambda$rsujs8CUsOFHBW3o_DvrvL7BcA(VoIPDebugToSend r0, TL_phone.saveCallDebug r1, TLRPC.InputFile r2) {
        r0.lambda$done$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$IqRemkD8_jxY1i_0eXr8IJOo8cQ(VoIPDebugToSend r0, Data r1, File r2, TL_phone.saveCallDebug r3) {
        r0.lambda$done$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$hCdI5FFkTDm2QMcyUgMnOyrfHJI(VoIPDebugToSend r0, File r1, TL_phone.saveCallDebug r2) {
        r0.lambda$done$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$rgCk66cDJmQmzrHAOOrfGYWnTek(VoIPDebugToSend r0, Data r1, TL_phone.saveCallDebug r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$done$3(r1, r2, r3, r4);
    }

    public VoIPDebugToSend(int r2) {
        this.pending = new HashMap();
        this.currentAccount = r2;
    }

    private /* synthetic */ void lambda$done$0(TL_phone.saveCallDebug r2, TLRPC.InputFile r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        TL_phone.saveCallLog r0 = new TL_phone.saveCallLog();
        r0.peer = r2.peer;
        r0.file = r3;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
    }

    private /* synthetic */ void lambda$done$1(File r3, TL_phone.saveCallDebug r4) {
        FileLoader.getInstance(this.currentAccount).uploadFile(r3.getAbsolutePath(), new VoIPDebugToSend$$ExternalSyntheticLambda0(this, r4));
    }

    private /* synthetic */ void lambda$done$2(Data r2, File r3, TL_phone.saveCallDebug r4) {
        if (AndroidUtilities.gzip(new File(r2.logPath), r3) == true) goto L5;
        return;
    L5:
        AndroidUtilities.runOnUIThread(new VoIPDebugToSend$$ExternalSyntheticLambda2(this, r3, r4));
    }

    private /* synthetic */ void lambda$done$3(Data r2, TL_phone.saveCallDebug r3, TLObject r4, TLRPC.TL_error r5) {
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        FileLog.d("Sent debug logs, response = " + r4);
    L6:
        if ((r4 instanceof TLRPC.TL_boolFalse) == true) goto L8;
        return;
    L8:
        if (TextUtils.isEmpty(r2.logPath) == true) goto L12;
        Utilities.searchQueue.postRunnable(new VoIPDebugToSend$$ExternalSyntheticLambda1(this, r2, new File(r2.logPath + ".gzip"), r3));
        return;
    }

    public void done(long r3, boolean r5) {
        Data r32 = this.pending.remove(Long.valueOf(r3));
        if (r32 == null) goto L8;
        if (r5 == false) goto L9;
        TL_phone.saveCallDebug r4 = new TL_phone.saveCallDebug();
        TLRPC.TL_dataJSON r52 = new TLRPC.TL_dataJSON();
        r4.debug = r52;
        r52.data = r32.state.debugLog;
        TLRPC.TL_inputPhoneCall r53 = new TLRPC.TL_inputPhoneCall();
        r4.peer = r53;
        r53.access_hash = r32.access_hash;
        r53.id = r32.callId;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r4, new VoIPDebugToSend$$ExternalSyntheticLambda3(this, r32, r4));
        return;
    L9:
        return;
    }

    public void push(long r3, long r5, Instance.FinalState r7, String r8) {
        if (TextUtils.isEmpty(r7.debugLog) == true) goto L10;
    L8:
        Data r0 = new Data(this, null);
        r0.callId = r3;
        r0.access_hash = r5;
        r0.state = r7;
        r0.logPath = r8;
        this.pending.put(Long.valueOf(r3), r0);
        return;
    L10:
        r7.debugLog = VoIPService.getStringFromFile(VoIPHelper.getLogFilePath(_UrlKt.FRAGMENT_ENCODE_SET + r3, true));     // Catch: Exception -> L6
    L6:
        e = move-exception;
        e.printStackTrace();
        goto L8
    }
}
