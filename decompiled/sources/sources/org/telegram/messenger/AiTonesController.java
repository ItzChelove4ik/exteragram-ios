package org.telegram.messenger;

import j$.util.Base64;
import java.util.ArrayList;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_aicompose;

/* loaded from: classes.dex */
public final class AiTonesController {
    public final int currentAccount;
    public long hash;
    private boolean loadedLocal;
    public boolean open;
    private int requestId;
    private long requestedTime;
    public final ArrayList<TL_aicompose.AiComposeTone> tones;

    public static /* synthetic */ void $r8$lambda$UptpkKvhAyA2z3TemhdvecNYuZM(AiTonesController r0, TL_aicompose.Tones r1, TLRPC.TL_error r2) {
        r0.lambda$request$0(r1, r2);
    }

    public AiTonesController(int r3) {
        this.tones = new ArrayList();
        this.requestId = -1;
        this.requestedTime = 0;
        this.currentAccount = r3;
    }

    private /* synthetic */ void lambda$request$0(TL_aicompose.Tones r3, TLRPC.TL_error r4) {
        this.requestId = -1;
        this.requestedTime = System.currentTimeMillis();
        if ((r3 instanceof TL_aicompose.TL_tones) == false) goto L6;
        MessagesController.getInstance(this.currentAccount).putUsers(r3.users, false);
        this.tones.clear();
        TL_aicompose.TL_tones r32 = (TL_aicompose.TL_tones) r3;
        this.tones.addAll(r32.tones);
        this.hash = r32.hash;
        save();
        notifyUpdate();
        return;
    }

    private void save() {
        TL_aicompose.TL_tones r0 = new TL_aicompose.TL_tones();
        r0.hash = this.hash;
        r0.tones.addAll(this.tones);
        SerializedData r1 = new SerializedData(r0.getObjectSize());
        r0.serializeToStream(r1);
        MessagesController.getInstance(this.currentAccount).getMainSettings().edit().putString("ai_styles", Base64.getEncoder().encodeToString(r1.toByteArray())).apply();
    }

    public void add(TL_aicompose.AiComposeTone r7) {
        if ((r7 instanceof TL_aicompose.TL_aiComposeTone) == false) goto L13;
        long r2 = ((TL_aicompose.TL_aiComposeTone) r7).id;
        int r0 = 0;
    L6:
        if (r0 >= this.tones.size()) goto L13;
        if ((this.tones.get(r0) instanceof TL_aicompose.TL_aiComposeTone) == false) goto L12;
        if (((TL_aicompose.TL_aiComposeTone) this.tones.get(r0)).id != r2) goto L12;
        return;
    L12:
        r0 = r0 + 1;
    L13:
        this.tones.add(0, r7);
        save();
        notifyUpdate();
    }

    public void cancel() {
        if (this.requestId < 0) goto L6;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.requestId, true);
        this.requestId = -1;
        return;
    }

    public void edit(TL_aicompose.TL_aiComposeTone r6) {
        int r0 = 0;
    L4:
        if (r0 >= this.tones.size()) goto L12;
        if ((this.tones.get(r0) instanceof TL_aicompose.TL_aiComposeTone) == false) goto L11;
        if (((TL_aicompose.TL_aiComposeTone) this.tones.get(r0)).id != r6.id) goto L11;
        this.tones.set(r0, r6);
        notifyUpdate();
        return;
    L11:
        r0 = r0 + 1;
        goto L4
    }

    public int getSavedTonesCount() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= this.tones.size()) goto L9;
        if ((this.tones.get(r0) instanceof TL_aicompose.TL_aiComposeTone) == false) goto L8;
        r1 = r1 + 1;
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        return r1;
    }

    public void invalidate() {
        this.requestedTime = 0;
        if (this.open == false) goto L6;
        load();
        return;
    }

    public boolean isLoading() {
        if (this.requestId < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void load() {
        if (this.loadedLocal == true) goto L13;
        this.loadedLocal = true;
        String r1 = MessagesController.getInstance(this.currentAccount).getMainSettings().getString("ai_styles", null);     // Catch: Exception -> L11
        if (r1 == null) goto L13;
        SerializedData r2 = new SerializedData(Base64.getDecoder().decode(r1));     // Catch: Exception -> L11
        TL_aicompose.Tones r0 = TL_aicompose.Tones.TLdeserialize(r2, r2.readInt32(true), true);     // Catch: Exception -> L11
        if ((r0 instanceof TL_aicompose.TL_tones) == false) goto L13;
        this.hash = ((TL_aicompose.TL_tones) r0).hash;     // Catch: Exception -> L11
        this.tones.clear();     // Catch: Exception -> L11
        this.tones.addAll(((TL_aicompose.TL_tones) r0).tones);     // Catch: Exception -> L11
    L11:
        e = move-exception;
        FileLog.e(e);
    L13:
        request();
    }

    public void notifyUpdate() {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.loadedAiComposeTones, new Object[]{this});
    }

    public void remove(TL_aicompose.AiComposeTone r2) {
        this.tones.remove(r2);
        save();
        notifyUpdate();
    }

    public void request() {
        if (this.requestId < 0) goto L6;
        return;
    L6:
        if ((System.currentTimeMillis() - this.requestedTime) >= 1800000) goto L8;
        return;
    L8:
        TL_aicompose.getTones r0 = new TL_aicompose.getTones();
        r0.hash = this.hash;
        this.requestId = ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r0, new AiTonesController$$ExternalSyntheticLambda0(), new AiTonesController$$ExternalSyntheticLambda1(this));
    }

    public void unsave(TL_aicompose.AiComposeTone r2) {
        remove(r2);
        TL_aicompose.saveTone r0 = new TL_aicompose.saveTone();
        r0.tone = TL_aicompose.InputAiComposeTone.from(r2);
        r0.unsave = true;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, null);
    }
}
