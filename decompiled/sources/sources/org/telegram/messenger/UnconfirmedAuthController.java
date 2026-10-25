package org.telegram.messenger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import kotlin.jvm.internal.LongCompanionObject;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.AbstractSerializedData;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLParseException;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.Business.BusinessChatbotController;

/* loaded from: classes.dex */
public class UnconfirmedAuthController {
    public final ArrayList<UnconfirmedAuth> auths;
    private final Runnable checkExpiration;
    private final int currentAccount;
    private boolean debug;
    private boolean fetchedCache;
    private boolean fetchingCache;
    private boolean saveAfterFetch;
    private boolean savingCache;

    /* loaded from: classes5.dex */
    public class UnconfirmedAuth extends TLObject {
        public boolean bot;
        public long bot_id;
        public int date;
        public String device;
        public long hash;
        public String location;
        final /* synthetic */ UnconfirmedAuthController this$0;

        /* renamed from: $r8$lambda$HQb9HkZd-QOe50D53sydcjDPWR0, reason: not valid java name */
        public static /* synthetic */ void m5042$r8$lambda$HQb9HkZdQOe50D53sydcjDPWR0(UnconfirmedAuth r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$deny$4(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$JRQbqgUi5sGNh4atJbdYFsC4NyQ(UnconfirmedAuth r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$deny$6(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$UA4TOt-8epU7aIzNLxftpyNsenE, reason: not valid java name */
        public static /* synthetic */ void m5043$r8$lambda$UA4TOt8epU7aIzNLxftpyNsenE(UnconfirmedAuth r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$deny$5(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$YT678n7aKkP5eg5tVeIX_z53Hko(UnconfirmedAuth r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$confirm$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$Ze6KclJN_8c4FhKGVN_p7o0Dc6M(UnconfirmedAuth r0, TLObject r1, Utilities.Callback r2, TLRPC.TL_error r3) {
            r0.lambda$deny$3(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$i3qqUBtAEmUvZz7kHxF6Vngbhi4(UnconfirmedAuth r0, Utilities.Callback r1, TLRPC.Bool r2, TLRPC.TL_error r3) {
            r0.lambda$confirm$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$vYswp_UxHpDVrgI8qN3rJAemZDo(UnconfirmedAuth r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$confirm$2(r1, r2, r3);
        }

        public UnconfirmedAuth(UnconfirmedAuthController r3, AbstractSerializedData r4) {
            this.this$0 = r3;
            int r0 = r4.readInt32(true);
            if (r0 != 2058772876) goto L7;
            this.hash = r4.readInt64(true);
            this.date = r4.readInt32(true);
            this.device = r4.readString(true);
            this.location = r4.readString(true);
            return;
        L7:
            if (r0 != 2058772877) goto L10;
            this.bot_id = r4.readInt64(true);
            this.date = r4.readInt32(true);
            this.device = r4.readString(true);
            this.location = r4.readString(true);
            return;
        L10:
            TLParseException.doThrowOrLog(r4, "UnconfirmedAuth", r0, true);
        }

        public UnconfirmedAuth(UnconfirmedAuthController r3, TL_update.TL_updateNewAuthorization r4) {
            this.this$0 = r3;
            this.hash = r4.hash;
            this.date = r4.date;
            this.device = r4.device;
            this.location = r4.location;
        }

        public UnconfirmedAuth(UnconfirmedAuthController r3, TL_update.TL_updateNewBotConnection r4) {
            this.this$0 = r3;
            this.bot = true;
            long r0 = r4.bot_id;
            this.bot_id = r0;
            this.hash = r0;
            this.date = r4.date;
            this.device = r4.device;
            this.location = r4.location;
        }

        private /* synthetic */ void lambda$confirm$0(Utilities.Callback r2, TLRPC.Bool r3, TLRPC.TL_error r4) {
            if (r2 != null) goto L4;
            return;
        L4:
            if ((r3 instanceof TLRPC.TL_boolTrue) == false) goto L7;
            if (r4 != null) goto L7;
        L8:
            boolean r32 = true;
        L10:
            r2.run(Boolean.valueOf(r32));
            UnconfirmedAuthController.m5041$$Nest$fputdebug(this.this$0, false);
            return;
        L7:
            if (UnconfirmedAuthController.m5040$$Nest$fgetdebug(this.this$0) == true) goto L8;
            r32 = false;
            goto L10
        }

        private /* synthetic */ void lambda$confirm$1(Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
            if (r2 != null) goto L4;
            return;
        L4:
            if ((r3 instanceof TLRPC.TL_boolTrue) == false) goto L7;
            if (r4 != null) goto L7;
        L8:
            boolean r32 = true;
        L10:
            r2.run(Boolean.valueOf(r32));
            UnconfirmedAuthController.m5041$$Nest$fputdebug(this.this$0, false);
            return;
        L7:
            if (UnconfirmedAuthController.m5040$$Nest$fgetdebug(this.this$0) == true) goto L8;
            r32 = false;
            goto L10
        }

        private /* synthetic */ void lambda$confirm$2(Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda0(this, r2, r3, r4));
        }

        private /* synthetic */ void lambda$deny$3(TLObject r4, Utilities.Callback r5, TLRPC.TL_error r6) {
            boolean r0 = r4 instanceof TLRPC.Updates;
            if (r0 == false) goto L5;
            MessagesController.getInstance(UnconfirmedAuthController.m5039$$Nest$fgetcurrentAccount(this.this$0)).processUpdates((TLRPC.Updates) r4, false);
        L5:
            boolean r2 = true;
            BusinessChatbotController.getInstance(UnconfirmedAuthController.m5039$$Nest$fgetcurrentAccount(this.this$0)).invalidate(true);
            if (r5 == null) goto L15;
            if (r0 == false) goto L10;
            if (r6 != null) goto L10;
        L13:
            r5.run(Boolean.valueOf(r2));
            UnconfirmedAuthController.m5041$$Nest$fputdebug(this.this$0, false);
            return;
        L10:
            if (UnconfirmedAuthController.m5040$$Nest$fgetdebug(this.this$0) == true) goto L13;
            r2 = false;
            goto L13
        }

        private /* synthetic */ void lambda$deny$4(Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda3(this, r3, r2, r4));
        }

        private /* synthetic */ void lambda$deny$5(Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
            if (r2 != null) goto L4;
            return;
        L4:
            if ((r3 instanceof TLRPC.TL_boolTrue) == false) goto L7;
            if (r4 != null) goto L7;
        L8:
            boolean r32 = true;
        L10:
            r2.run(Boolean.valueOf(r32));
            UnconfirmedAuthController.m5041$$Nest$fputdebug(this.this$0, false);
            return;
        L7:
            if (UnconfirmedAuthController.m5040$$Nest$fgetdebug(this.this$0) == true) goto L8;
            r32 = false;
            goto L10
        }

        private /* synthetic */ void lambda$deny$6(Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda4(this, r2, r3, r4));
        }

        public void confirm(Utilities.Callback<Boolean> r5) {
            if (this.bot == false) goto L6;
            TL_account.confirmBotConnection r0 = new TL_account.confirmBotConnection();
            r0.bot_id = MessagesController.getInstance(UnconfirmedAuthController.m5039$$Nest$fgetcurrentAccount(this.this$0)).getInputUser(this.bot_id);
            ConnectionsManager.getInstance(UnconfirmedAuthController.m5039$$Nest$fgetcurrentAccount(this.this$0)).sendRequestTyped(r0, new UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda5(this, r5));
            return;
        L6:
            TL_account.changeAuthorizationSettings r02 = new TL_account.changeAuthorizationSettings();
            r02.hash = this.hash;
            r02.confirmed = true;
            ConnectionsManager.getInstance(UnconfirmedAuthController.m5039$$Nest$fgetcurrentAccount(this.this$0)).sendRequest(r02, new UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda6(this, r5));
        }

        public void deny(Utilities.Callback<Boolean> r5) {
            if (this.bot == false) goto L6;
            TL_account.updateConnectedBot r0 = new TL_account.updateConnectedBot();
            r0.deleted = true;
            r0.bot = MessagesController.getInstance(UnconfirmedAuthController.m5039$$Nest$fgetcurrentAccount(this.this$0)).getInputUser(this.bot_id);
            r0.recipients = new TL_account.TL_inputBusinessBotRecipients();
            ConnectionsManager.getInstance(UnconfirmedAuthController.m5039$$Nest$fgetcurrentAccount(this.this$0)).sendRequest(r0, new UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda1(this, r5));
            return;
        L6:
            TL_account.resetAuthorization r02 = new TL_account.resetAuthorization();
            r02.hash = this.hash;
            ConnectionsManager.getInstance(UnconfirmedAuthController.m5039$$Nest$fgetcurrentAccount(this.this$0)).sendRequest(r02, new UnconfirmedAuthController$UnconfirmedAuth$$ExternalSyntheticLambda2(this, r5));
        }

        public boolean expired() {
            if (expiresAfter() > 0) goto L6;
            return true;
        L6:
            return false;
        }

        public long expiresAfter() {
            return (ConnectionsManager.getInstance(UnconfirmedAuthController.m5039$$Nest$fgetcurrentAccount(this.this$0)).getCurrentTime() + MessagesController.getInstance(UnconfirmedAuthController.m5039$$Nest$fgetcurrentAccount(this.this$0)).authorizationAutoconfirmPeriod) - this.date;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            if (this.bot == false) goto L6;
            r3.writeInt32(2058772877);
            r3.writeInt64(this.bot_id);
            r3.writeInt32(this.date);
            r3.writeString(this.device);
            r3.writeString(this.location);
            return;
        L6:
            r3.writeInt32(2058772876);
            r3.writeInt64(this.hash);
            r3.writeInt32(this.date);
            r3.writeString(this.device);
            r3.writeString(this.location);
        }
    }

    /* renamed from: $r8$lambda$6w7xO7feFnZ-AsLLQ4OUAJmTNlQ, reason: not valid java name */
    public static /* synthetic */ void m5034$r8$lambda$6w7xO7feFnZAsLLQ4OUAJmTNlQ(UnconfirmedAuthController r0) {
        r0.lambda$saveCache$4();
    }

    public static /* synthetic */ void $r8$lambda$8TLDwlQ3PmMHxXAE2jVlnaOgqWs(UnconfirmedAuthController r0) {
        r0.lambda$saveCache$3();
    }

    public static /* synthetic */ void $r8$lambda$Ed6GVKZPCOyjWEyKEeJ9vaeFzAc(UnconfirmedAuthController r0) {
        r0.lambda$new$2();
    }

    public static /* synthetic */ void $r8$lambda$EunyHx3T8GwhdkgsX9sjvPHUP40(UnconfirmedAuthController r0, boolean[] r1, ArrayList r2, boolean r3, Utilities.Callback r4) {
        r0.lambda$updateList$7(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$JjcB4dRHU_zMrsjYHZLtna-kHw0, reason: not valid java name */
    public static /* synthetic */ void m5035$r8$lambda$JjcB4dRHU_zMrsjYHZLtnakHw0(UnconfirmedAuthController r0, ArrayList r1, HashSet r2, ArrayList r3) {
        r0.lambda$readCache$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$NJhReUHqCliAHaxOC-3AKA_h2IU, reason: not valid java name */
    public static /* synthetic */ void m5036$r8$lambda$NJhReUHqCliAHaxOC3AKA_h2IU(boolean[] r0, int r1, Runnable r2, Boolean r3) {
        r0[r1] = r3.booleanValue();
        r2.run();
    }

    /* renamed from: $r8$lambda$Xkl6YfEqtx2D_7-2sCSneOIOAm4, reason: not valid java name */
    public static /* synthetic */ void m5037$r8$lambda$Xkl6YfEqtx2D_72sCSneOIOAm4(UnconfirmedAuthController r0) {
        r0.lambda$readCache$1();
    }

    /* renamed from: $r8$lambda$s03l1nRxg-y6Lr_3nNXQXlCZPK0, reason: not valid java name */
    public static /* synthetic */ void m5038$r8$lambda$s03l1nRxgy6Lr_3nNXQXlCZPK0(boolean[] r1, int r2, boolean r3, UnconfirmedAuth r4, Runnable r5) {
        UnconfirmedAuthController$$ExternalSyntheticLambda2 r0 = new UnconfirmedAuthController$$ExternalSyntheticLambda2(r1, r2, r5);
        if (r3 == false) goto L6;
        r4.confirm(r0);
        return;
    L6:
        r4.deny(r0);
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5039$$Nest$fgetcurrentAccount(UnconfirmedAuthController r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdebug, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5040$$Nest$fgetdebug(UnconfirmedAuthController r0) {
        return r0.debug;
    }

    /* renamed from: -$$Nest$fputdebug, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5041$$Nest$fputdebug(UnconfirmedAuthController r0, boolean r1) {
        r0.debug = r1;
    }

    public UnconfirmedAuthController(int r2) {
        this.auths = new ArrayList();
        this.checkExpiration = new UnconfirmedAuthController$$ExternalSyntheticLambda5(this);
        this.debug = false;
        this.currentAccount = r2;
        readCache();
    }

    private /* synthetic */ void lambda$new$2() {
        int r0 = 0;
    L4:
        if (r0 >= this.auths.size()) goto L9;
        if (this.auths.get(r0).expired() == false) goto L8;
        this.auths.remove(r0);
        r0 = r0 - 1;
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        saveCache();
    }

    private /* synthetic */ void lambda$readCache$0(ArrayList r6, HashSet r7, ArrayList r8) {
        MessagesController.getInstance(this.currentAccount).putUsers(r6, true);
        boolean r62 = this.auths.isEmpty();
        int r2 = 0;
    L3:
        int r3 = this.auths.size();
        ArrayList<UnconfirmedAuth> r4 = this.auths;
        if (r2 >= r3) goto L13;
        UnconfirmedAuth r32 = r4.get(r2);
        if (r32 != null) goto L8;
    L11:
        this.auths.remove(r2);
        r2 = r2 - 1;
    L12:
        r2 = r2 + 1;
        goto L3
    L8:
        if (r32.expired() == true) goto L11;
        if (r7.contains(Long.valueOf(r32.hash)) == false) goto L12;
    L13:
        r4.addAll(r8);
        boolean r72 = this.auths.isEmpty();
        this.fetchedCache = true;
        this.fetchingCache = false;
        if (r62 == r72) goto L16;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.unconfirmedAuthUpdate, new Object[0]);
    L16:
        scheduleAuthExpireCheck();
        if (this.saveAfterFetch == false) goto L23;
        this.saveAfterFetch = false;
        saveCache();
        return;
    }

    private /* synthetic */ void lambda$readCache$1() {
        ArrayList<TLRPC.User> r0 = new ArrayList();
        ArrayList<Long> r1 = new ArrayList();
        HashSet r2 = new HashSet();
        ArrayList r3 = new ArrayList();
        SQLiteDatabase r4 = MessagesStorage.getInstance(this.currentAccount).getDatabase();
        SQLiteCursor r5 = null;
        r5 = r4.queryFinalized(String.format(Locale.US, "SELECT data FROM unconfirmed_auth", new Object[0]), new Object[0]);     // Catch: Throwable -> L14 Exception -> L19
    L5:
        if (r5.next() == false) goto L21;
        NativeByteBuffer r42 = r5.byteBufferValue(0);     // Catch: Throwable -> L14 Exception -> L19
        if (r42 == null) goto L5;
        UnconfirmedAuth r6 = new UnconfirmedAuth(this, r42);     // Catch: Throwable -> L14 Exception -> L16
        r3.add(r6);     // Catch: Throwable -> L14 Exception -> L16
        r2.add(Long.valueOf(r6.hash));     // Catch: Throwable -> L14 Exception -> L16
        if (r6.bot == true) goto L11;
    L18:
        r42.reuse();     // Catch: Throwable -> L14 Exception -> L19
        goto L5
    L11:
        if (r1.contains(Long.valueOf(r6.bot_id)) == true) goto L18;
        r1.add(Long.valueOf(r6.bot_id));     // Catch: Throwable -> L14 Exception -> L16
    L16:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L14 Exception -> L19
        goto L18
    L21:
        MessagesStorage.getInstance(this.currentAccount).getUsersInternal(r1, r0);     // Catch: Throwable -> L14 Exception -> L19
    L22:
        r5.dispose();
    L26:
        AndroidUtilities.runOnUIThread(new UnconfirmedAuthController$$ExternalSyntheticLambda7(this, r0, r2, r3));
        return;
    L14:
        th = move-exception;
        if (r5 == null) goto L30;
        r5.dispose();
    L30:
        throw th;
    L19:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L14
        if (r5 == null) goto L26;
        goto L26
    }

    private /* synthetic */ void lambda$saveCache$3() {
        this.savingCache = false;
    }

    private /* synthetic */ void lambda$saveCache$4() {
        SQLiteDatabase r0 = MessagesStorage.getInstance(this.currentAccount).getDatabase();
        SQLitePreparedStatement r1 = null;
        r0.executeFast("DELETE FROM unconfirmed_auth WHERE 1").stepThis().dispose();     // Catch: Throwable -> L23 Exception -> L25
        SQLitePreparedStatement r02 = r0.executeFast("REPLACE INTO unconfirmed_auth VALUES(?)");     // Catch: Throwable -> L23 Exception -> L25
        ArrayList<UnconfirmedAuth> r2 = this.auths;     // Catch: Throwable -> L10 Exception -> L12
        int r3 = r2.size();     // Catch: Throwable -> L10 Exception -> L12
        int r4 = 0;
    L5:
        if (r4 >= r3) goto L21;
        UnconfirmedAuth r5 = r2.get(r4);     // Catch: Throwable -> L10 Exception -> L12
        r4 = r4 + 1;     // Catch: Throwable -> L10 Exception -> L12
        UnconfirmedAuth r52 = r5;     // Catch: Throwable -> L10 Exception -> L12
        r02.requery();     // Catch: Throwable -> L10 Exception -> L12
        NativeByteBuffer r6 = new NativeByteBuffer(r52.getObjectSize());     // Catch: Throwable -> L16
        r52.serializeToStream(r6);     // Catch: Throwable -> L14
        r02.bindByteBuffer(1, r6);     // Catch: Throwable -> L14
        r02.step();     // Catch: Throwable -> L14
        r6.reuse();     // Catch: Throwable -> L10 Exception -> L12
    L14:
        Throwable th = th;
    L18:
        if (r6 == null) goto L45;
        r6.reuse();     // Catch: Throwable -> L10 Exception -> L12
        throw th;     // Catch: Throwable -> L10 Exception -> L12
    L45:
        throw th;     // Catch: Throwable -> L10 Exception -> L12
    L16:
        th = move-exception;
        r6 = null;
        th = th;
        goto L18
    L21:
        if (r02 == null) goto L30;
    L22:
        r02.dispose();
    L30:
        AndroidUtilities.runOnUIThread(new UnconfirmedAuthController$$ExternalSyntheticLambda4(this));
        return;
    L12:
        Exception e = e;
    L27:
        FileLog.e(e);     // Catch: Throwable -> L10
        if (r02 == null) goto L30;
    L10:
        th = th;
        r1 = r02;
    L32:
        if (r1 == null) goto L34;
        r1.dispose();
    L34:
        throw th;
    L25:
        e = move-exception;
        e = e;
        r02 = null;
    L23:
        th = th;
        goto L32
    }

    private /* synthetic */ void lambda$updateList$7(boolean[] r7, ArrayList r8, boolean r9, Utilities.Callback r10) {
        HashSet r0 = new HashSet();
        ArrayList r1 = new ArrayList();
        int r3 = 0;
    L4:
        if (r3 >= r7.length) goto L9;
        if (r7[r3] == false) goto L8;
        UnconfirmedAuth r4 = (UnconfirmedAuth) r8.get(r3);
        r1.add(r4);
        r0.add(Long.valueOf(r4.hash));
    L8:
        r3 = r3 + 1;
        goto L4
    L9:
        if (r9 == true) goto L20;
        int r72 = 0;
    L12:
        if (r72 >= this.auths.size()) goto L18;
        if (r0.contains(Long.valueOf(this.auths.get(r72).hash)) == false) goto L16;
        this.auths.remove(r72);
        r72 = r72 - 1;
    L16:
        r72 = r72 + 1;
        goto L12
    L18:
        if (r0.isEmpty() == true) goto L20;
        saveCache();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.unconfirmedAuthUpdate, new Object[0]);
        scheduleAuthExpireCheck();
    L20:
        r10.run(r1);
    }

    private void scheduleAuthExpireCheck() {
        AndroidUtilities.cancelRunOnUIThread(this.checkExpiration);
        if (this.auths.isEmpty() == true) goto L14;
        ArrayList<UnconfirmedAuth> r0 = this.auths;
        int r1 = r0.size();
        int r4 = 0;
        long r5 = Long.MAX_VALUE;
    L6:
        if (r4 >= r1) goto L9;
        UnconfirmedAuth r7 = r0.get(r4);
        r4 = r4 + 1;
        r5 = Math.min(r5, r7.expiresAfter());
        goto L6
    L9:
        if (r5 != LongCompanionObject.MAX_VALUE) goto L11;
        return;
    L11:
        AndroidUtilities.runOnUIThread(this.checkExpiration, Math.max(0, r5 * 1000));
        return;
    }

    private void updateList(boolean r8, ArrayList<UnconfirmedAuth> r9, Utilities.Callback<ArrayList<UnconfirmedAuth>> r10) {
        ArrayList r3 = new ArrayList(r9);
        boolean[] r2 = new boolean[r3.size()];
        Utilities.Callback[] r92 = new Utilities.Callback[r3.size()];
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L6;
        r92[r0] = new UnconfirmedAuthController$$ExternalSyntheticLambda0(r2, r0, r8, (UnconfirmedAuth) r3.get(r0));
        r0 = r0 + 1;
        goto L4
    L6:
        Utilities.raceCallbacks(new UnconfirmedAuthController$$ExternalSyntheticLambda1(this, r2, r3, r8, r10), r92);
        if (r8 == false) goto L28;
        HashSet r7 = new HashSet();
        int r82 = 0;
    L10:
        if (r82 >= r3.size()) goto L12;
        r7.add(Long.valueOf(((UnconfirmedAuth) r3.get(r82)).hash));
        r82 = r82 + 1;
        goto L10
    L12:
        int r83 = 0;
    L14:
        if (r83 >= this.auths.size()) goto L20;
        if (r7.contains(Long.valueOf(this.auths.get(r83).hash)) == false) goto L18;
        this.auths.remove(r83);
        r83 = r83 - 1;
    L18:
        r83 = r83 + 1;
        goto L14
    L20:
        if (r7.isEmpty() == true) goto L29;
        saveCache();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.unconfirmedAuthUpdate, new Object[0]);
        scheduleAuthExpireCheck();
        return;
    L29:
        return;
    }

    public void cleanup() {
        this.auths.clear();
        saveCache();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.unconfirmedAuthUpdate, new Object[0]);
        scheduleAuthExpireCheck();
    }

    public void confirm(ArrayList<UnconfirmedAuth> r2, Utilities.Callback<ArrayList<UnconfirmedAuth>> r3) {
        updateList(true, r2, r3);
    }

    public void deny(ArrayList<UnconfirmedAuth> r2, Utilities.Callback<ArrayList<UnconfirmedAuth>> r3) {
        updateList(false, r2, r3);
    }

    public void processUpdate(TL_update.TL_updateNewAuthorization r7) {
        int r1 = 0;
    L4:
        if (r1 >= this.auths.size()) goto L14;
        UnconfirmedAuth r2 = this.auths.get(r1);
        if (r2 == null) goto L12;
        if (r2.bot == true) goto L12;
        if (r2.hash != r7.hash) goto L12;
        this.auths.remove(r1);
        r1 = r1 - 1;
    L12:
        r1 = r1 + 1;
        goto L4
    L14:
        if (r7.unconfirmed == false) goto L16;
        this.auths.add(new UnconfirmedAuth(this, r7));
    L16:
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.unconfirmedAuthUpdate, new Object[0]);
        scheduleAuthExpireCheck();
        saveCache();
    }

    public void processUpdate(TL_update.TL_updateNewBotConnection r7) {
        int r1 = 0;
    L3:
        int r2 = this.auths.size();
        ArrayList<UnconfirmedAuth> r3 = this.auths;
        if (r1 >= r2) goto L13;
        UnconfirmedAuth r22 = r3.get(r1);
        if (r22 == null) goto L12;
        if (r22.bot == false) goto L12;
        if (r22.bot_id != r7.bot_id) goto L12;
        this.auths.remove(r1);
        r1 = r1 - 1;
    L12:
        r1 = r1 + 1;
        goto L3
    L13:
        r3.add(new UnconfirmedAuth(this, r7));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.unconfirmedAuthUpdate, new Object[0]);
        scheduleAuthExpireCheck();
        saveCache();
    }

    public void putDebug() {
        this.debug = true;
        TL_update.TL_updateNewAuthorization r1 = new TL_update.TL_updateNewAuthorization();
        r1.unconfirmed = true;
        r1.device = "device";
        r1.location = "location";
        r1.hash = 123;
        processUpdate(r1);
    }

    public void readCache() {
        if (this.fetchedCache == false) goto L5;
        return;
    L5:
        if (this.fetchingCache == true) goto L10;
        this.fetchingCache = true;
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new UnconfirmedAuthController$$ExternalSyntheticLambda3(this));
        return;
    }

    public void saveCache() {
        if (this.savingCache == false) goto L6;
        return;
    L6:
        if (this.fetchingCache == false) goto L9;
        this.saveAfterFetch = true;
        return;
    L9:
        this.savingCache = true;
        MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new UnconfirmedAuthController$$ExternalSyntheticLambda6(this));
    }
}
