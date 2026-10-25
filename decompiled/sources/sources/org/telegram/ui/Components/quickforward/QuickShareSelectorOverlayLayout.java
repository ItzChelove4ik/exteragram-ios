package org.telegram.ui.Components.quickforward;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.Bulletin;

/* loaded from: classes7.dex */
public class QuickShareSelectorOverlayLayout extends View {
    private final int currentAccount;
    private final ArrayList<Long> dialogs;
    private final HashMap<String, QuickShareSelectorDrawable> drawableHashMap;
    private final ArrayList<String> drawablesForRemove;

    public static /* synthetic */ void $r8$lambda$8fxfXlOAMHMvhJ42HViJdkED9zQ(QuickShareSelectorOverlayLayout r0, String r1) {
        r0.lambda$open$0(r1);
    }

    public QuickShareSelectorOverlayLayout(Context r1) {
        super(r1);
        this.drawableHashMap = new HashMap();
        this.drawablesForRemove = new ArrayList();
        this.dialogs = new ArrayList();
        this.currentAccount = UserConfig.selectedAccount;
    }

    private void fetchDialogs() {
        this.dialogs.clear();
        UserConfig r0 = UserConfig.getInstance(this.currentAccount);
        long r1 = r0.clientUserId;
        this.dialogs.add(Long.valueOf(r1));
        int r3 = 0;
        if (r0.suggestContacts == false) goto L12;
        ArrayList<TLRPC.TL_topPeer> r02 = MediaDataController.getInstance(this.currentAccount).hints;
        int r4 = r02.size();
        int r5 = 0;
    L5:
        if (r5 >= r4) goto L12;
        TLRPC.TL_topPeer r6 = r02.get(r5);
        r5 = r5 + 1;
        TLRPC.TL_topPeer r62 = r6;
        long r7 = r62.peer.user_id;
        if (r7 == 0) goto L5;
        if (MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r62.peer.user_id)) == null) goto L5;
        this.dialogs.add(Long.valueOf(r7));
    L12:
        ArrayList r03 = new ArrayList();
        ArrayList<TLRPC.Dialog> r42 = MessagesController.getInstance(this.currentAccount).getAllDialogs();
    L14:
        if (r3 >= r42.size()) goto L62;
        TLRPC.Dialog r52 = r42.get(r3);
        if ((r52 instanceof TLRPC.TL_dialog) == false) goto L61;
        long r63 = r52.id;
        if (r63 == r1) goto L61;
        if (DialogObject.isEncryptedDialog(r63) == true) goto L61;
        boolean r64 = DialogObject.isUserDialog(r52.id);
        int r72 = this.currentAccount;
        if (r64 == false) goto L37;
        TLRPC.User r65 = MessagesController.getInstance(r72).getUser(Long.valueOf(r52.id));
        if (r65 == null) goto L61;
        if (UserObject.isBot(r65) == true) goto L61;
        if (UserObject.isDeleted(r65) == true) goto L61;
        if (UserObject.isService(r65.id) == true) goto L61;
        if (r52.folder_id != 1) goto L36;
        r03.add(Long.valueOf(r52.id));
        goto L61
    L36:
        this.dialogs.add(Long.valueOf(r52.id));
        goto L61
    L37:
        TLRPC.Chat r66 = MessagesController.getInstance(r72).getChat(Long.valueOf(-r52.id));
        if (r66 == null) goto L61;
        if (r66.forum == true) goto L61;
        if (ChatObject.isNotInChat(r66) == true) goto L61;
        if (r66.gigagroup == false) goto L48;
        if (ChatObject.hasAdminRights(r66) == false) goto L61;
    L48:
        if (ChatObject.isChannel(r66) == false) goto L58;
        if (r66.creator == true) goto L58;
        TLRPC.TL_chatAdminRights r73 = r66.admin_rights;
        if (r73 == null) goto L56;
        if (r73.post_messages == true) goto L58;
    L56:
        if (r66.megagroup == false) goto L61;
    L58:
        if (r52.folder_id != 1) goto L60;
        r03.add(Long.valueOf(r52.id));
        goto L61
    L60:
        this.dialogs.add(Long.valueOf(r52.id));
    L61:
        r3 = r3 + 1;
        goto L14
    L62:
        this.dialogs.addAll(r03);
    }

    private static String key(ChatMessageCell r3) {
        MessageObject r32 = r3.getMessageObject();
        if (r32 != null) goto L7;
        return null;
    L7:
        return r32.getChatId() + "_" + r32.getId();
    }

    private /* synthetic */ void lambda$open$0(String r2) {
        this.drawablesForRemove.add(r2);
        invalidate();
    }

    private static ArrayList<Long> removeDuplicates(ArrayList<Long> r7) {
        HashSet r0 = new HashSet();
        ArrayList<Long> r1 = new ArrayList();
        int r2 = r7.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L9;
        Long r4 = r7.get(r3);
        r3 = r3 + 1;
        Long r42 = r4;
        if (r0.add(r42) == false) goto L3;
        if (DialogObject.isUserDialog(r42.longValue()) == false) goto L3;
        r1.add(r42);
        goto L3
    L9:
        return r1;
    }

    public void close(ChatMessageCell r1, Bulletin r2) {
        QuickShareSelectorDrawable r0 = this.drawableHashMap.get(key(r1));
        if (r0 == null) goto L6;
        r0.close(r2);
        return;
    }

    public long getSelectedDialogId(ChatMessageCell r1) {
        QuickShareSelectorDrawable r0 = this.drawableHashMap.get(key(r1));
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getSelectedDialogId();
    }

    public MessageObject getSelectedMessageObject(ChatMessageCell r1) {
        QuickShareSelectorDrawable r0 = this.drawableHashMap.get(key(r1));
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.messageObject;
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r1) {
        super.invalidateDrawable(r1);
        if ((r1 instanceof QuickShareSelectorDrawable) == false) goto L6;
        invalidate();
        return;
    }

    public boolean isActive() {
        Iterator<Map.Entry<String, QuickShareSelectorDrawable>> r1 = this.drawableHashMap.entrySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        if (r1.next().getValue().isActive() == false) goto L4;
        return true;
    L9:
        return false;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration r2) {
        super.onConfigurationChanged(r2);
        Iterator<Map.Entry<String, QuickShareSelectorDrawable>> r22 = this.drawableHashMap.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r22.next().getValue().destroy();
        goto L4
    L6:
        this.drawableHashMap.clear();
        this.drawablesForRemove.clear();
    }

    @Override // android.view.View
    public void onDraw(Canvas r5) {
        super.onDraw(r5);
        Iterator<Map.Entry<String, QuickShareSelectorDrawable>> r0 = this.drawableHashMap.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L7;
        r0.next().getValue().draw(r5);
        goto L4
    L7:
        if (this.drawablesForRemove.isEmpty() == true) goto L21;
        ArrayList<String> r52 = this.drawablesForRemove;
        int r02 = r52.size();
        int r1 = 0;
    L9:
        if (r1 >= r02) goto L13;
        String r2 = r52.get(r1);
        r1 = r1 + 1;
        HashMap<String, QuickShareSelectorDrawable> r3 = this.drawableHashMap;
        QuickShareSelectorDrawable r22 = r3.remove(r2);
        if (r22 == null) goto L9;
        r22.destroy();
        goto L9
    L13:
        this.drawablesForRemove.clear();
        return;
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        Iterator<Map.Entry<String, QuickShareSelectorDrawable>> r12 = this.drawableHashMap.entrySet().iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        r12.next().getValue().setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        goto L4
    }

    public void onTouchMoveEvent(ChatMessageCell r1, float r2, float r3) {
        QuickShareSelectorDrawable r0 = this.drawableHashMap.get(key(r1));
        if (r0 == null) goto L6;
        r0.onTouchMoveEvent(r2, r3);
        return;
    }

    public void open(ChatMessageCell r7) {
        fetchDialogs();
        String r4 = key(r7);
        if (r4 == null) goto L10;
        QuickShareSelectorDrawable r0 = new QuickShareSelectorDrawable(this, r7, removeDuplicates(this.dialogs), r4, new QuickShareSelectorOverlayLayout$$ExternalSyntheticLambda0(this, r4));
        r0.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        r0.setCallback(this);
        if (this.drawableHashMap.containsKey(r4) == true) goto L9;
        this.drawableHashMap.put(r4, r0);
        return;
    L9:
        return;
    }
}
