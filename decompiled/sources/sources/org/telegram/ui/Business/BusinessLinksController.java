package org.telegram.ui.Business;

import android.text.TextUtils;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes6.dex */
public class BusinessLinksController {
    private static volatile BusinessLinksController[] Instance;
    private static final Object[] lockObjects = null;
    public final int currentAccount;
    public final ArrayList<TL_account.TL_businessChatLink> links;
    private boolean loaded;
    private boolean loading;

    public static /* synthetic */ void $r8$lambda$GGPlPM2gh4QJWSu8vQ4eGIqyvaI(BusinessLinksController r0, String r1, TL_account.TL_businessChatLink r2) {
        r0.lambda$deleteLinkUndoable$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$HIOjS_3f2IxeRbLs8vTFfZN3AJQ(BusinessLinksController r0, MessagesStorage r1, boolean r2) {
        r0.lambda$load$1(r1, r2);
    }

    /* renamed from: $r8$lambda$N5frq9-u1PgvtR8ytCPZLub0xKA, reason: not valid java name */
    public static /* synthetic */ void m6329$r8$lambda$N5frq9u1PgvtR8ytCPZLub0xKA(BusinessLinksController r0, TLObject r1) {
        r0.lambda$createEmptyLink$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$PBVcmFyNyCVJJr0zps1mIJjDvnc(BusinessLinksController r0, TLObject r1, TL_account.TL_businessChatLink r2) {
        r0.lambda$deleteLinkUndoable$7(r1, r2);
    }

    /* renamed from: $r8$lambda$aSav2QK-RCbP6lkw2fghxfB2H4g, reason: not valid java name */
    public static /* synthetic */ void m6330$r8$lambda$aSav2QKRCbP6lkw2fghxfB2H4g(BusinessLinksController r0, TL_account.TL_businessChatLink r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$editLink$12(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$e6BELc2xeS3g1UzOyHFowy5n_3g(BusinessLinksController r0, TL_account.TL_businessChatLink r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$deleteLinkUndoable$8(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$gRg-SO2EGIgT-2V6CFw_nbWrg-w, reason: not valid java name */
    public static /* synthetic */ void m6331$r8$lambda$gRgSO2EGIgT2V6CFw_nbWrgw(BusinessLinksController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$load$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$gVJNAGu2jWJMqiCxyRgbyRcVKGQ(MessagesStorage r5, ArrayList r6) {
        SQLitePreparedStatement r0 = null;
        NativeByteBuffer r02 = null;
        r0 = null;
        SQLiteDatabase r52 = r5.getDatabase();     // Catch: Throwable -> L24 Exception -> L26
        r52.executeFast("DELETE FROM business_links").stepThis().dispose();     // Catch: Throwable -> L24 Exception -> L26
        SQLitePreparedStatement r53 = r52.executeFast("REPLACE INTO business_links VALUES(?, ?)");     // Catch: Throwable -> L24 Exception -> L26
        int r1 = 0;
    L41:
    L13:
        e = e;
        r0 = r53;
    L27:
        FileLog.e(e);     // Catch: Throwable -> L24
        if (r0 == null) goto L45;
        r0.dispose();
        return;
    L45:
        return;
    L11:
        th = th;
        r0 = r53;
    L31:
        if (r0 == null) goto L33;
        r0.dispose();
    L33:
        throw th;
    L6:
        if (r1 >= r6.size()) goto L21;
        TL_account.TL_businessChatLink r2 = (TL_account.TL_businessChatLink) r6.get(r1);     // Catch: Throwable -> L11 Exception -> L13
        NativeByteBuffer r3 = new NativeByteBuffer(r2.getObjectSize());     // Catch: Throwable -> L17
        r2.serializeToStream(r3);     // Catch: Throwable -> L15
        r53.requery();     // Catch: Throwable -> L15
        r53.bindByteBuffer(1, r3);     // Catch: Throwable -> L15
        r53.bindInteger(2, r1);     // Catch: Throwable -> L15
        r53.step();     // Catch: Throwable -> L15
        r3.reuse();     // Catch: Throwable -> L11 Exception -> L13
        r1 = r1 + 1;     // Catch: Throwable -> L11 Exception -> L13
    L15:
        th = th;
        r02 = r3;
    L18:
        if (r02 == null) goto L20;
        r02.reuse();     // Catch: Throwable -> L11 Exception -> L13
    L20:
        throw th;     // Catch: Throwable -> L11 Exception -> L13
    L17:
        th = th;
        goto L18
    L21:
        if (r53 == null) goto L44;
        r53.dispose();
        return;
    L44:
        return;
    L26:
        e = e;
    L24:
        th = th;
        goto L31
    }

    /* renamed from: $r8$lambda$iv1RTs41oMj2Ft-R-1WZVdhEQqA, reason: not valid java name */
    public static /* synthetic */ void m6332$r8$lambda$iv1RTs41oMj2FtR1WZVdhEQqA(BusinessLinksController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createEmptyLink$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$oqgm3Hnnhli91CwsIj8lzB4b85s(BusinessLinksController r0, ArrayList r1, ArrayList r2, ArrayList r3, boolean r4) {
        r0.lambda$load$0(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$oseGYhGkdi7s_GSqFLVWIQcOfKc(BusinessLinksController r0, TLObject r1) {
        r0.lambda$load$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$y2fZpFBdZXt_OZ62PrXzNPDBkWQ(BusinessLinksController r0, int r1, TL_account.TL_businessChatLink r2) {
        r0.lambda$deleteLinkUndoable$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$z3IqPp0KktciTpQbNZLiQtgkI1g(BusinessLinksController r0, TLObject r1, TL_account.TL_businessChatLink r2, Runnable r3) {
        r0.lambda$editLink$11(r1, r2, r3);
    }

    static {
        Instance = new BusinessLinksController[16];
        lockObjects = new Object[16];
        int r1 = 0;
    L3:
        if (r1 >= 16) goto L5;
        lockObjects[r1] = new Object();
        r1 = r1 + 1;
        goto L3
    }

    private BusinessLinksController(int r2) {
        this.links = new ArrayList();
        this.loading = false;
        this.loaded = false;
        this.currentAccount = r2;
    }

    private void editLink(TL_account.TL_businessChatLink r3, TL_account.TL_inputBusinessChatLink r4, Runnable r5) {
        TL_account.editBusinessChatLink r0 = new TL_account.editBusinessChatLink();
        r0.slug = r3.link;
        if (r4.entities.isEmpty() == true) goto L6;
        r4.flags |= 1;
    L6:
        if (TextUtils.isEmpty(r4.title) == true) goto L8;
        r4.flags |= 2;
    L8:
        r0.link = r4;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new BusinessLinksController$$ExternalSyntheticLambda7(this, r3, r5));
    }

    public static BusinessLinksController getInstance(int r3) {
        BusinessLinksController r0 = Instance[r3];
        if (r0 != null) goto L15;
        Object r1 = lockObjects[r3];
        monitor-enter(r1);
        BusinessLinksController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        BusinessLinksController[] r03 = Instance;     // Catch: Throwable -> L9
        BusinessLinksController r2 = new BusinessLinksController(r3);     // Catch: Throwable -> L9
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

    private /* synthetic */ void lambda$createEmptyLink$4(TLObject r4) {
        if ((r4 instanceof TL_account.TL_businessChatLink) == false) goto L6;
        TL_account.TL_businessChatLink r42 = (TL_account.TL_businessChatLink) r4;
        this.links.add(r42);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.businessLinksUpdated, new Object[0]);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.businessLinkCreated, new Object[]{r42});
        saveToCache();
        return;
    }

    private /* synthetic */ void lambda$createEmptyLink$5(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new BusinessLinksController$$ExternalSyntheticLambda11(this, r1));
    }

    private /* synthetic */ void lambda$deleteLinkUndoable$6(int r2, TL_account.TL_businessChatLink r3) {
        this.links.add(r2, r3);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.businessLinksUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$deleteLinkUndoable$7(TLObject r2, TL_account.TL_businessChatLink r3) {
        if ((r2 instanceof TLRPC.TL_boolTrue) == true) goto L5;
        FileLog.e(new RuntimeException("Unexpected response from server!"));
        return;
    L5:
        if (this.links.contains(r3) == false) goto L7;
        this.links.remove(r3);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.businessLinksUpdated, new Object[0]);
    L7:
        saveToCache();
    }

    private /* synthetic */ void lambda$deleteLinkUndoable$8(TL_account.TL_businessChatLink r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new BusinessLinksController$$ExternalSyntheticLambda12(this, r2, r1));
    }

    private /* synthetic */ void lambda$deleteLinkUndoable$9(String r3, TL_account.TL_businessChatLink r4) {
        TL_account.deleteBusinessChatLink r0 = new TL_account.deleteBusinessChatLink();
        r0.slug = r3;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new BusinessLinksController$$ExternalSyntheticLambda10(this, r4));
    }

    private /* synthetic */ void lambda$editLink$11(TLObject r2, TL_account.TL_businessChatLink r3, Runnable r4) {
        if ((r2 instanceof TL_account.TL_businessChatLink) == false) goto L11;
        TL_account.TL_businessChatLink r22 = (TL_account.TL_businessChatLink) r2;
        int r32 = this.links.indexOf(r3);
        if (r32 == (-1)) goto L12;
        this.links.set(r32, r22);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.businessLinksUpdated, new Object[0]);
        if (r4 == null) goto L9;
        r4.run();
    L9:
        saveToCache();
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$editLink$12(TL_account.TL_businessChatLink r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new BusinessLinksController$$ExternalSyntheticLambda8(this, r3, r1, r2));
    }

    private /* synthetic */ void lambda$load$0(ArrayList r2, ArrayList r3, ArrayList r4, boolean r5) {
        this.links.clear();
        this.links.addAll(r2);
        MessagesController.getInstance(this.currentAccount).putUsers(r3, true);
        MessagesController.getInstance(this.currentAccount).putChats(r4, true);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.quickRepliesUpdated, new Object[0]);
        this.loading = false;
        load(false, r5);
    }

    private /* synthetic */ void lambda$load$1(MessagesStorage r13, boolean r14) {
        ArrayList r2 = new ArrayList();
        ArrayList<TLRPC.User> r3 = new ArrayList();
        ArrayList<TLRPC.Chat> r4 = new ArrayList();
        SQLiteCursor r1 = null;
        r1 = r13.getDatabase().queryFinalized("SELECT data FROM business_links ORDER BY order_value ASC", new Object[0]);     // Catch: Throwable -> L7 Exception -> L9
    L5:
        if (r1.next() == false) goto L11;
        NativeByteBuffer r0 = r1.byteBufferValue(0);     // Catch: Throwable -> L7 Exception -> L9
        r2.add(TL_account.TL_businessChatLink.TLdeserialize(r0, r0.readInt32(false), false));     // Catch: Throwable -> L7 Exception -> L9
        r0.reuse();     // Catch: Throwable -> L7 Exception -> L9
        goto L5
    L11:
        r1.dispose();     // Catch: Throwable -> L7 Exception -> L9
        ArrayList<Long> r02 = new ArrayList();     // Catch: Throwable -> L7 Exception -> L9
        ArrayList r5 = new ArrayList();     // Catch: Throwable -> L7 Exception -> L9
        int r7 = 0;
    L13:
        if (r7 >= r2.size()) goto L28;
        TL_account.TL_businessChatLink r8 = (TL_account.TL_businessChatLink) r2.get(r7);     // Catch: Throwable -> L7 Exception -> L9
        if (r8.entities.isEmpty() == true) goto L26;
        int r9 = 0;
    L18:
        if (r9 >= r8.entities.size()) goto L26;
        TLRPC.MessageEntity r10 = r8.entities.get(r9);     // Catch: Throwable -> L7 Exception -> L9
        if ((r10 instanceof TLRPC.TL_messageEntityMentionName) == false) goto L23;
        r02.add(Long.valueOf(((TLRPC.TL_messageEntityMentionName) r10).user_id));     // Catch: Throwable -> L7 Exception -> L9
    L25:
        r9 = r9 + 1;     // Catch: Throwable -> L7 Exception -> L9
        goto L18
    L23:
        if ((r10 instanceof TLRPC.TL_inputMessageEntityMentionName) == false) goto L25;
        r02.add(Long.valueOf(((TLRPC.TL_inputMessageEntityMentionName) r10).user_id.user_id));     // Catch: Throwable -> L7 Exception -> L9
    L26:
        r7 = r7 + 1;     // Catch: Throwable -> L7 Exception -> L9
        goto L13
    L28:
        if (r02.isEmpty() == true) goto L31;
        r13.getUsersInternal(r02, r3);     // Catch: Throwable -> L7 Exception -> L9
    L31:
        if (r5.isEmpty() == true) goto L33;
        r13.getChatsInternal(TextUtils.join(",", r5), r4);     // Catch: Throwable -> L7 Exception -> L9
    L33:
        r1.dispose();
    L37:
        AndroidUtilities.runOnUIThread(new BusinessLinksController$$ExternalSyntheticLambda2(this, r2, r3, r4, r14));
        return;
    L9:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L7
        if (r1 == null) goto L37;
    L7:
        th = move-exception;
        if (r1 == null) goto L52;
        r1.dispose();
        throw th;
    L52:
        throw th;
    }

    private /* synthetic */ void lambda$load$2(TLObject r5) {
        if ((r5 instanceof TL_account.businessChatLinks) == false) goto L5;
        TL_account.businessChatLinks r52 = (TL_account.businessChatLinks) r5;
        this.links.clear();
        this.links.addAll(r52.links);
        MessagesController.getInstance(this.currentAccount).putUsers(r52.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r52.chats, false);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r52.users, r52.chats, true, true);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.businessLinksUpdated, new Object[0]);
        saveToCache();
    L6:
        this.loading = false;
        this.loaded = true;
        return;
    L5:
        FileLog.e(new RuntimeException("Unexpected response from server!"));
        goto L6
    }

    private /* synthetic */ void lambda$load$3(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new BusinessLinksController$$ExternalSyntheticLambda6(this, r1));
    }

    private void load(boolean r3, boolean r4) {
        if (this.loading == false) goto L5;
        return;
    L5:
        if (this.loaded == false) goto L9;
        if (r4 == false) goto L16;
        if (r3 == false) goto L9;
        return;
    L16:
        return;
    L9:
        this.loading = true;
        if (r3 == false) goto L13;
        MessagesStorage r32 = MessagesStorage.getInstance(this.currentAccount);
        r32.getStorageQueue().postRunnable(new BusinessLinksController$$ExternalSyntheticLambda0(this, r32, r4));
        return;
    L13:
        TL_account.getBusinessChatLinks r33 = new TL_account.getBusinessChatLinks();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r33, new BusinessLinksController$$ExternalSyntheticLambda1(this));
    }

    private void saveToCache() {
        ArrayList r0 = new ArrayList(this.links);
        MessagesStorage r3 = MessagesStorage.getInstance(this.currentAccount);
        r3.getStorageQueue().postRunnable(new BusinessLinksController$$ExternalSyntheticLambda9(r3, r0));
    }

    public static String stripHttps(String r1) {
        if (r1.startsWith("https://") == true) goto L5;
        return r1;
    L5:
        return r1.substring(8);
    }

    public boolean canAddNew() {
        if (this.links.size() >= MessagesController.getInstance(this.currentAccount).businessChatLinksLimit) goto L6;
        return true;
    L6:
        return false;
    }

    public void createEmptyLink() {
        TL_account.createBusinessChatLink r0 = new TL_account.createBusinessChatLink();
        TL_account.TL_inputBusinessChatLink r1 = new TL_account.TL_inputBusinessChatLink();
        r0.link = r1;
        r1.message = _UrlKt.FRAGMENT_ENCODE_SET;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new BusinessLinksController$$ExternalSyntheticLambda5(this));
    }

    public void deleteLinkUndoable(BaseFragment r6, String r7) {
        TL_account.TL_businessChatLink r0 = findLink(r7);
        if (r0 == null) goto L6;
        int r1 = this.links.indexOf(r0);
        this.links.remove(r0);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.businessLinksUpdated, new Object[0]);
        BulletinFactory.of(r6).createUndoBulletin(LocaleController.getString(R.string.BusinessLinkDeleted), true, new BusinessLinksController$$ExternalSyntheticLambda3(this, r1, r0), new BusinessLinksController$$ExternalSyntheticLambda4(this, r7, r0)).show();
        return;
    }

    public void editLinkMessage(String r2, String r3, ArrayList<TLRPC.MessageEntity> r4, Runnable r5) {
        TL_account.TL_businessChatLink r22 = findLink(r2);
        if (r22 != null) goto L5;
        return;
    L5:
        TL_account.TL_inputBusinessChatLink r0 = new TL_account.TL_inputBusinessChatLink();
        r0.message = r3;
        r0.entities = r4;
        r0.title = r22.title;
        editLink(r22, r0, r5);
    }

    public void editLinkTitle(String r3, String r4) {
        TL_account.TL_businessChatLink r32 = findLink(r3);
        if (r32 != null) goto L5;
        return;
    L5:
        TL_account.TL_inputBusinessChatLink r0 = new TL_account.TL_inputBusinessChatLink();
        r0.message = r32.message;
        r0.entities = r32.entities;
        r0.title = r4;
        editLink(r32, r0, null);
    }

    public TL_account.TL_businessChatLink findLink(String r6) {
        int r0 = 0;
    L4:
        if (r0 >= this.links.size()) goto L16;
        TL_account.TL_businessChatLink r1 = this.links.get(r0);
        if (TextUtils.equals(r1.link, r6) == true) goto L15;
        if (TextUtils.equals(r1.link, "https://" + r6) == true) goto L15;
        if (TextUtils.equals(r1.link, "https://t.me/m/" + r6) == true) goto L15;
        if (TextUtils.equals(r1.link, "tg://message?slug=" + r6) == true) goto L15;
        r0 = r0 + 1;
    L15:
        return r1;
    L16:
        return null;
    }

    public void load(boolean r3) {
        if (this.loaded == true) goto L6;
        load(true, r3);
        return;
    L6:
        if (r3 == false) goto L9;
        load(false, true);
        return;
    }
}
