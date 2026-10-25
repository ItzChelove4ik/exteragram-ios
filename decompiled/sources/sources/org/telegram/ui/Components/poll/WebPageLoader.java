package org.telegram.ui.Components.poll;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes7.dex */
public class WebPageLoader {
    private final HashMap<String, ArrayList<Utilities.Callback2<TLRPC.WebPage, TLObject>>> callbacks;
    private final int currentAccount;
    private final HashMap<String, TLRPC.WebPage> pages;

    public static /* synthetic */ void $r8$lambda$GUKg2kx00eBFfAKgY82y77RKqNA(WebPageLoader r0, String r1, TL_account.webPagePreview r2, TLRPC.TL_error r3) {
        r0.lambda$get$0(r1, r2, r3);
    }

    public WebPageLoader(int r2) {
        this.pages = new HashMap();
        this.callbacks = new HashMap();
        this.currentAccount = r2;
    }

    private /* synthetic */ void lambda$get$0(String r4, TL_account.webPagePreview r5, TLRPC.TL_error r6) {
        int r0 = 0;
        if (r5 == null) goto L7;
        MessagesController.getInstance(this.currentAccount).putUsers(r5.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r5.chats, false);
        TLRPC.MessageMedia r52 = r5.media;
        if (r52 == null) goto L7;
        TLRPC.WebPage r53 = r52.webpage;
    L8:
        this.pages.put(r4, r53);
        ArrayList<Utilities.Callback2<TLRPC.WebPage, TLObject>> r3 = this.callbacks.remove(r4);
        if (r3 == null) goto L13;
        int r42 = r3.size();
    L11:
        if (r0 >= r42) goto L15;
        Utilities.Callback2<TLRPC.WebPage, TLObject> r1 = r3.get(r0);
        r0 = r0 + 1;
        r1.run(r53, r6);
        goto L11
    L15:
        return;
    L13:
        return;
    L7:
        r53 = null;
        goto L8
    }

    public void apply(LongSparseArray<TLRPC.WebPage> r4) {
        Iterator<Map.Entry<String, TLRPC.WebPage>> r3 = this.pages.entrySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L11;
        Map.Entry<String, TLRPC.WebPage> r0 = r3.next();
        if (r0.getValue() == null) goto L4;
        TLRPC.WebPage r1 = r4.get(r0.getValue().id);
        if (r1 == null) goto L4;
        r0.setValue(r1);
        goto L4
    }

    public void get(String r4, Utilities.Callback2<TLRPC.WebPage, TLObject> r5) {
        if (this.pages.containsKey(r4) == false) goto L6;
        r5.run(this.pages.get(r4), null);
        return;
    L6:
        boolean r0 = this.callbacks.containsKey(r4);
        ArrayList<Utilities.Callback2<TLRPC.WebPage, TLObject>> r1 = this.callbacks.get(r4);
        if (r1 != null) goto L9;
        r1 = new ArrayList();
        this.callbacks.put(r4, r1);
    L9:
        r1.add(r5);
        if (r0 == false) goto L12;
        return;
    L12:
        TL_account.getWebPagePreview r52 = new TL_account.getWebPagePreview();
        r52.message = r4;
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r52, new AiTonesController$$ExternalSyntheticLambda0(), new WebPageLoader$$ExternalSyntheticLambda0(this, r4));
    }

    public TLRPC.WebPage getWebPage(String r1) {
        return this.pages.get(r1);
    }

    public boolean isLoading(String r1) {
        return this.callbacks.containsKey(r1);
    }
}
