package org.telegram.messenger;

import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes.dex */
public class BirthdayController {
    private static volatile BirthdayController[] Instance;
    private static final Object[] lockObjects = null;
    private final int currentAccount;
    private final Set<String> hiddenDays;
    private long lastCheckDate;
    private boolean loading;
    private BirthdayState state;

    public static class BirthdayState {
        public final ArrayList<TLRPC.User> today;
        public String todayKey;
        public final ArrayList<TLRPC.User> tomorrow;
        public String tomorrowKey;
        public final ArrayList<TLRPC.User> yesterday;
        public String yesterdayKey;

        private BirthdayState(String r2, String r3, String r4) {
            this.yesterday = new ArrayList();
            this.today = new ArrayList();
            this.tomorrow = new ArrayList();
            this.yesterdayKey = r2;
            this.todayKey = r3;
            this.tomorrowKey = r4;
        }

        public static BirthdayState from(TL_account.contactBirthdays r20) {
            Calendar r1 = Calendar.getInstance();
            int r3 = r1.get(5);
            int r5 = r1.get(2) + 1;
            int r7 = r1.get(1);
            r1.add(5, -1);
            int r8 = r1.get(5);
            int r9 = r1.get(2) + 1;
            int r12 = r1.get(1);
            Calendar r10 = Calendar.getInstance();
            r10.add(5, 1);
            int r2 = r10.get(5);
            int r4 = r10.get(2) + 1;
            String r13 = r8 + "_" + r9 + "_" + r12;
            String r72 = r3 + "_" + r5 + "_" + r7;
            BirthdayState r102 = new BirthdayState(r13, r72, r2 + "_" + r4 + "_" + r10.get(1));
            ArrayList<TL_account.TL_contactBirthday> r14 = r20.contacts;
            int r6 = r14.size();
            int r11 = 0;
        L3:
            if (r11 >= r6) goto L33;
            TL_account.TL_contactBirthday r122 = r14.get(r11);
            r11 = r11 + 1;
            TL_account.TL_contactBirthday r123 = r122;
            TL_account.TL_birthday r132 = r123.birthday;
            int r142 = r132.day;
            TLRPC.User r15 = null;
            if (r142 == r3) goto L7;
        L9:
            if (r142 == r8) goto L11;
        L13:
            if (r142 == r2) goto L15;
        L17:
            ArrayList<TLRPC.User> r73 = null;
        L18:
            if (r73 == null) goto L31;
            int r133 = 0;
        L21:
            if (r133 >= r20.users.size()) goto L26;
            ArrayList<TL_account.TL_contactBirthday> r17 = r14;
            int r16 = r2;
            if (r20.users.get(r133).id == r123.contact_id) goto L24;
            r133 = r133 + 1;
            r2 = r16;
            r14 = r17;
            goto L21
        L24:
            r15 = r20.users.get(r133);
        L27:
            if (r15 == null) goto L32;
            if (UserObject.isUserSelf(r15) == true) goto L32;
            r73.add(r15);
        L32:
            r2 = r16;
            r14 = r17;
            goto L3
        L26:
            r17 = r14;
            r16 = r2;
            goto L27
        L31:
            r17 = r14;
            r16 = r2;
            goto L32
        L15:
            if (r132.month != r4) goto L17;
            r73 = r102.tomorrow;
            goto L18
        L11:
            if (r132.month != r9) goto L13;
            r73 = r102.yesterday;
            goto L18
        L7:
            if (r132.month != r5) goto L9;
            r73 = r102.today;
            goto L18
        L33:
            return r102;
        }

        public boolean contains(long r8) {
            ArrayList<TLRPC.User> r0 = this.yesterday;
            int r1 = r0.size();
            int r3 = 0;
        L4:
            if (r3 >= r1) goto L8;
            TLRPC.User r5 = r0.get(r3);
            r3 = r3 + 1;
            if (r5.id != r8) goto L4;
            return true;
        L8:
            ArrayList<TLRPC.User> r02 = this.today;
            int r12 = r02.size();
            int r32 = 0;
        L9:
            if (r32 >= r12) goto L13;
            TLRPC.User r52 = r02.get(r32);
            r32 = r32 + 1;
            if (r52.id != r8) goto L9;
            return true;
        L13:
            ArrayList<TLRPC.User> r7 = this.tomorrow;
            int r03 = r7.size();
            int r13 = 0;
        L14:
            if (r13 >= r03) goto L18;
            TLRPC.User r33 = r7.get(r13);
            r13 = r13 + 1;
            if (r33.id != r8) goto L14;
            return true;
        L18:
            return false;
        }

        public boolean isTodayEmpty() {
            return this.today.isEmpty();
        }
    }

    public static class TL_birthdays extends TLObject {
        public static final int constructor = 290452237;
        public ArrayList<TL_account.TL_contactBirthday> contacts;

        private TL_birthdays() {
            this.contacts = new ArrayList();
        }

        public /* synthetic */ TL_birthdays(BirthdayControllerIA r1) {
            this();
        }

        public static TL_birthdays TLdeserialize(InputSerializedData r2, int r3, boolean r4) {
            if (r3 == 290452237) goto L5;
            TL_birthdays r0 = null;
        L7:
            return (TL_birthdays) TLObject.TLdeserialize(TL_birthdays.class, r0, r2, r3, r4);
        L5:
            r0 = new TL_birthdays();
            goto L7
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            if (r0 == 481674261) goto L8;
            if (r6 == true) goto L7;
            return;
        L7:
            throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(r0)}));
        L8:
            int r02 = r5.readInt32(r6);
            int r1 = 0;
        L9:
            if (r1 >= r02) goto L13;
            this.contacts.add(TL_account.TL_contactBirthday.TLdeserialize(r5, r5.readInt32(r6), r6));
            r1 = r1 + 1;
            goto L9
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r4) {
            r4.writeInt32(290452237);
            r4.writeInt32(Vector.constructor);
            int r0 = this.contacts.size();
            r4.writeInt32(r0);
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            this.contacts.get(r1).serializeToStream(r4);
            r1 = r1 + 1;
            goto L3
        }
    }

    public static /* synthetic */ void $r8$lambda$5KknepqqY1xTJHfrOV3jeABZINA(BirthdayController r0, int r1, ArrayList r2, TL_birthdays r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$ImQHbipWYzi94WgVnFOO0zNaPB4(BirthdayController r0, TLObject r1) {
        r0.lambda$check$2(r1);
    }

    /* renamed from: $r8$lambda$juYQZshFEatHCxHuXSHOingFx-Q, reason: not valid java name */
    public static /* synthetic */ void m3896$r8$lambda$juYQZshFEatHCxHuXSHOingFxQ(BirthdayController r0, TL_birthdays r1, ArrayList r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$muMsJjYxIdhiZP2kjF8PaH9_BSI(BirthdayController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$check$3(r1, r2);
    }

    static {
        Instance = new BirthdayController[16];
        lockObjects = new Object[16];
        int r1 = 0;
    L3:
        if (r1 >= 16) goto L5;
        lockObjects[r1] = new Object();
        r1 = r1 + 1;
        goto L3
    }

    private BirthdayController(int r7) {
        this.currentAccount = r7;
        SharedPreferences r0 = MessagesController.getInstance(r7).getMainSettings();
        this.lastCheckDate = r0.getLong("bday_check", 0);
        String r1 = r0.getString("bday_contacts", null);
        if (r1 != null) goto L19;
    L17:
        this.hiddenDays = r0.getStringSet("bday_hidden", new HashSet());
        return;
    L19:
        SerializedData r2 = new SerializedData(Utilities.hexToBytes(r1));     // Catch: Exception -> L12
        TL_birthdays r12 = TL_birthdays.TLdeserialize(r2, r2.readInt32(true), true);     // Catch: Exception -> L12
        if (r12 == null) goto L17;
        if (r12.contacts.isEmpty() == true) goto L17;
        ArrayList r22 = new ArrayList();     // Catch: Exception -> L12
        int r3 = 0;
    L10:
        if (r3 >= r12.contacts.size()) goto L14;
        r22.add(Long.valueOf(r12.contacts.get(r3).contact_id));     // Catch: Exception -> L12
        r3 = r3 + 1;     // Catch: Exception -> L12
        goto L10
    L14:
        MessagesStorage.getInstance(r7).getStorageQueue().postRunnable(new BirthdayController$$ExternalSyntheticLambda3(this, r7, r22, r12));     // Catch: Exception -> L12
    L12:
        e = move-exception;
        FileLog.e(e);
        goto L17
    }

    public static BirthdayController getInstance(int r3) {
        BirthdayController r0 = Instance[r3];
        if (r0 != null) goto L15;
        Object r1 = lockObjects[r3];
        monitor-enter(r1);
        BirthdayController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        BirthdayController[] r03 = Instance;     // Catch: Throwable -> L9
        BirthdayController r2 = new BirthdayController(r3);     // Catch: Throwable -> L9
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

    public static boolean isToday(TLRPC.UserFull r2) {
        if (r2 != null) goto L6;
        return false;
    L6:
        if (isToday(r2.birthday) == true) goto L8;
    L11:
        return false;
    L8:
        if (UserObject.areGiftsDisabled(r2) == true) goto L11;
        return true;
    }

    public static boolean isToday(TL_account.TL_birthday r5) {
        if (r5 != null) goto L5;
        return false;
    L5:
        Calendar r1 = Calendar.getInstance();
        int r2 = r1.get(5);
        int r12 = r1.get(2) + 1;
        if (r5.day == r2) goto L8;
    L10:
        return false;
    L8:
        if (r5.month != r12) goto L10;
        return true;
    }

    private /* synthetic */ void lambda$check$2(TLObject r7) {
        if ((r7 instanceof TL_account.contactBirthdays) == false) goto L6;
        this.lastCheckDate = System.currentTimeMillis();
        TL_account.contactBirthdays r72 = (TL_account.contactBirthdays) r7;
        this.state = BirthdayState.from(r72);
        MessagesController.getInstance(this.currentAccount).putUsers(r72.users, false);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r72.users, null, true, true);
        SharedPreferences.Editor r0 = MessagesController.getInstance(this.currentAccount).getMainSettings().edit();
        r0.putLong("bday_check", this.lastCheckDate);
        TL_birthdays r1 = new TL_birthdays(null);
        r1.contacts = r72.contacts;
        SerializedData r73 = new SerializedData(r1.getObjectSize());
        r1.serializeToStream(r73);
        r0.putString("bday_contacts", Utilities.bytesToHex(r73.toByteArray()));
        r0.apply();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.premiumPromoUpdated, new Object[0]);
        this.loading = false;
        return;
    }

    private /* synthetic */ void lambda$check$3(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new BirthdayController$$ExternalSyntheticLambda2(this, r1));
    }

    private /* synthetic */ void lambda$new$0(TL_birthdays r2, ArrayList r3) {
        TL_account.contactBirthdays r0 = new TL_account.contactBirthdays();
        r0.contacts = r2.contacts;
        r0.users = r3;
        this.state = BirthdayState.from(r0);
    }

    private /* synthetic */ void lambda$new$1(int r1, ArrayList r2, TL_birthdays r3) {
        AndroidUtilities.runOnUIThread(new BirthdayController$$ExternalSyntheticLambda0(this, r3, MessagesStorage.getInstance(r1).getUsers(r2)));
    }

    public void check() {
        if (this.loading == true) goto L31;
        long r0 = System.currentTimeMillis();
        long r2 = this.lastCheckDate;
        boolean r5 = false;
        if (r2 != 0) goto L8;
        boolean r4 = true;
    L9:
        if (r4 == true) goto L18;
        long r22 = r0 - r2;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L13;
        int r42 = 25000;
    L15:
        if (r22 <= r42) goto L17;
        r4 = true;
        goto L18
    L17:
        r4 = false;
        goto L18
    L13:
        r42 = 43200000;
    L18:
        if (r4 == true) goto L27;
        Calendar r23 = Calendar.getInstance();
        r23.setTimeInMillis(this.lastCheckDate);
        Calendar r3 = Calendar.getInstance();
        r3.setTimeInMillis(r0);
        if (r23.get(5) == r3.get(5)) goto L22;
    L25:
        r5 = true;
    L26:
        r4 = r5;
        goto L27
    L22:
        if (r23.get(2) != r3.get(2)) goto L25;
        if (r23.get(1) == r3.get(1)) goto L26;
    L27:
        if (r4 == true) goto L29;
        return;
    L29:
        this.loading = true;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(new TL_account.getBirthdays(), new BirthdayController$$ExternalSyntheticLambda1(this));
        return;
    L8:
        r4 = false;
        goto L9
    }

    public boolean contains() {
        BirthdayState r0 = getState();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isTodayEmpty() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean contains(long r1) {
        BirthdayState r0 = getState();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.contains(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public BirthdayState getState() {
        BirthdayState r0 = this.state;
        if (r0 != null) goto L6;
        return null;
    L6:
        if (this.hiddenDays.contains(r0.todayKey) == false) goto L9;
        return null;
    L9:
        return this.state;
    }

    public void hide() {
        BirthdayState r0 = this.state;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.hiddenDays.contains(r0.todayKey) == false) goto L8;
        return;
    L8:
        this.hiddenDays.add(this.state.todayKey);
        SharedPreferences.Editor r02 = MessagesController.getInstance(this.currentAccount).getMainSettings().edit();
        r02.putStringSet("bday_hidden", this.hiddenDays);
        r02.apply();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.premiumPromoUpdated, new Object[0]);
    }

    public boolean isToday(long r3) {
        BirthdayState r0 = this.state;
        if (r0 != null) goto L5;
    L7:
        TLRPC.UserFull r2 = MessagesController.getInstance(this.currentAccount).getUserFull(r3);
        if (r2 != null) goto L10;
        return false;
    L10:
        if (isToday(r2.birthday) == true) goto L12;
        return false;
    L12:
        if (UserObject.areGiftsDisabled(r2) == true) goto L17;
        return true;
    L17:
        return false;
    L5:
        if (r0.contains(r3) == false) goto L7;
        return true;
    }
}
