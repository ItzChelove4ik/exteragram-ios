package org.telegram.tgnet;

import android.util.SparseArray;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public class TLClassStore {
    static TLClassStore store;
    private SparseArray<TLObjectFactory> classStore;

    /* loaded from: classes6.dex */
    public interface TLObjectFactory {
        TLObject create();
    }

    static {
    }

    public TLClassStore() {
        SparseArray<TLObjectFactory> r0 = new SparseArray();
        this.classStore = r0;
        r0.put(TLRPC.TL_error.constructor, new TLClassStore$$ExternalSyntheticLambda0());
        this.classStore.put(TLRPC.TL_decryptedMessageService.constructor, new TLClassStore$$ExternalSyntheticLambda11());
        this.classStore.put(TLRPC.TL_decryptedMessage.constructor, new TLClassStore$$ExternalSyntheticLambda12());
        this.classStore.put(TLRPC.TL_decryptedMessageLayer.constructor, new TLClassStore$$ExternalSyntheticLambda13());
        this.classStore.put(TLRPC.TL_decryptedMessage_layer17.constructor, new TLClassStore$$ExternalSyntheticLambda12());
        this.classStore.put(TLRPC.TL_decryptedMessage_layer45.constructor, new TLClassStore$$ExternalSyntheticLambda14());
        this.classStore.put(TLRPC.TL_decryptedMessageService_layer8.constructor, new TLClassStore$$ExternalSyntheticLambda15());
        this.classStore.put(TLRPC.TL_decryptedMessage_layer8.constructor, new TLClassStore$$ExternalSyntheticLambda16());
        this.classStore.put(TLRPC.TL_message_secret.constructor, new TLClassStore$$ExternalSyntheticLambda17());
        this.classStore.put(TLRPC.TL_message_secret_layer72.constructor, new TLClassStore$$ExternalSyntheticLambda18());
        this.classStore.put(TLRPC.TL_message_secret_old.constructor, new TLClassStore$$ExternalSyntheticLambda1());
        this.classStore.put(TLRPC.TL_messageEncryptedAction.constructor, new TLClassStore$$ExternalSyntheticLambda2());
        this.classStore.put(TLRPC.TL_null.constructor, new TLClassStore$$ExternalSyntheticLambda3());
        this.classStore.put(TLRPC.TL_updateShortChatMessage.constructor, new TLClassStore$$ExternalSyntheticLambda4());
        this.classStore.put(TLRPC.TL_updates.constructor, new TLClassStore$$ExternalSyntheticLambda5());
        this.classStore.put(TLRPC.TL_updateShortMessage.constructor, new TLClassStore$$ExternalSyntheticLambda6());
        this.classStore.put(TLRPC.TL_updateShort.constructor, new TLClassStore$$ExternalSyntheticLambda7());
        this.classStore.put(TLRPC.TL_updatesCombined.constructor, new TLClassStore$$ExternalSyntheticLambda8());
        this.classStore.put(TLRPC.TL_updateShortSentMessage.constructor, new TLClassStore$$ExternalSyntheticLambda9());
        this.classStore.put(TLRPC.TL_updatesTooLong.constructor, new TLClassStore$$ExternalSyntheticLambda10());
    }

    public static TLClassStore Instance() {
        if (store != null) goto L6;
        store = new TLClassStore();
    L6:
        return store;
    }

    public TLObject TLdeserialize(NativeByteBuffer r1, int r2, boolean r3) {
        TLObjectFactory r0 = this.classStore.get(r2);
        if (r0 == null) goto L6;
        TLObject r02 = r0.create();
        r02.readParams(r1, r3);
        return r02;
    L6:
        return null;
    }
}
