package org.telegram.ui.Business;

import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public class BusinessChatbotController {
    private static volatile BusinessChatbotController[] Instance;
    private static final Object[] lockObjects = null;
    private ArrayList<Utilities.Callback<TL_account.connectedBots>> callbacks;
    private final int currentAccount;
    private long lastTime;
    private boolean loaded;
    private boolean loading;
    private TL_account.connectedBots value;

    public static /* synthetic */ void $r8$lambda$YcSuTEfDGT1ZrAkW1aunILsq91M(BusinessChatbotController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$load$1(r1, r2);
    }

    /* renamed from: $r8$lambda$fo1nLm1pkDInr1gduTmp-Awaz3M, reason: not valid java name */
    public static /* synthetic */ void m6314$r8$lambda$fo1nLm1pkDInr1gduTmpAwaz3M(BusinessChatbotController r0, TLObject r1) {
        r0.lambda$load$0(r1);
    }

    static {
        Instance = new BusinessChatbotController[16];
        lockObjects = new Object[16];
        int r1 = 0;
    L3:
        if (r1 >= 16) goto L5;
        lockObjects[r1] = new Object();
        r1 = r1 + 1;
        goto L3
    }

    private BusinessChatbotController(int r2) {
        this.callbacks = new ArrayList();
        this.currentAccount = r2;
    }

    public static BusinessChatbotController getInstance(int r3) {
        BusinessChatbotController r0 = Instance[r3];
        if (r0 != null) goto L15;
        Object r1 = lockObjects[r3];
        monitor-enter(r1);
        BusinessChatbotController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        BusinessChatbotController[] r03 = Instance;     // Catch: Throwable -> L9
        BusinessChatbotController r2 = new BusinessChatbotController(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(r1);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    L15:
        return r0;
    }

    private /* synthetic */ void lambda$load$0(TLObject r3) {
        this.loading = false;
        if ((r3 instanceof TL_account.connectedBots) == false) goto L5;
        TL_account.connectedBots r32 = (TL_account.connectedBots) r3;
    L6:
        this.value = r32;
        if (r32 == null) goto L9;
        MessagesController.getInstance(this.currentAccount).putUsers(this.value.users, false);
    L9:
        this.lastTime = System.currentTimeMillis();
        this.loaded = true;
        notifyUpdate();
        return;
    L5:
        r32 = null;
        goto L6
    }

    private /* synthetic */ void lambda$load$1(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new BusinessChatbotController$$ExternalSyntheticLambda1(this, r1));
    }

    public TL_account.connectedBots getValue() {
        return this.value;
    }

    public void invalidate(boolean r2) {
        this.loaded = false;
        if (r2 == false) goto L6;
        load(null);
        return;
    }

    public void load(Utilities.Callback<TL_account.connectedBots> r5) {
        if (r5 == null) goto L5;
        this.callbacks.add(r5);
    L5:
        if (this.loading == false) goto L8;
        return;
    L8:
        if ((System.currentTimeMillis() - this.lastTime) > 60000) goto L15;
        boolean r52 = this.loaded;
        if (r52 == false) goto L15;
        if (r52 == false) goto L17;
        notifyUpdate();
        return;
    L17:
        return;
    L15:
        this.loading = true;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_account.getConnectedBots(), new BusinessChatbotController$$ExternalSyntheticLambda0(this));
    }

    public void notifyUpdate() {
        int r1 = 0;
    L3:
        int r2 = this.callbacks.size();
        ArrayList<Utilities.Callback<TL_account.connectedBots>> r3 = this.callbacks;
        if (r1 >= r2) goto L9;
        if (r3.get(r1) == null) goto L8;
        this.callbacks.get(r1).run(this.value);
    L8:
        r1 = r1 + 1;
        goto L3
    L9:
        r3.clear();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updatedChatbot, new Object[0]);
    }
}
