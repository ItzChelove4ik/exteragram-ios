package org.telegram.messenger;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.collection.LongSparseArray;
import com.google.android.gms.cast.MediaError;
import java.text.CollationKey;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.Bulletin;

/* loaded from: classes.dex */
public class ContactsController extends BaseController {
    private static volatile ContactsController[] Instance = null;
    public static final int PRIVACY_RULES_TYPE_ADDED_BY_PHONE = 7;
    public static final int PRIVACY_RULES_TYPE_BIO = 9;
    public static final int PRIVACY_RULES_TYPE_BIRTHDAY = 11;
    public static final int PRIVACY_RULES_TYPE_CALLS = 2;
    public static final int PRIVACY_RULES_TYPE_COUNT = 15;
    public static final int PRIVACY_RULES_TYPE_FORWARDS = 5;
    public static final int PRIVACY_RULES_TYPE_GIFTS = 12;
    public static final int PRIVACY_RULES_TYPE_INVITE = 1;
    public static final int PRIVACY_RULES_TYPE_LASTSEEN = 0;
    public static final int PRIVACY_RULES_TYPE_MESSAGES = 10;
    public static final int PRIVACY_RULES_TYPE_MUSIC = 14;
    public static final int PRIVACY_RULES_TYPE_NO_PAID_MESSAGES = 13;
    public static final int PRIVACY_RULES_TYPE_P2P = 3;
    public static final int PRIVACY_RULES_TYPE_PHONE = 6;
    public static final int PRIVACY_RULES_TYPE_PHOTO = 4;
    public static final int PRIVACY_RULES_TYPE_VOICE_MESSAGES = 8;
    private static Collator cachedCollator;
    private static Locale cachedCollatorLocale;
    private static final String[] projectionNames = null;
    private static final String[] projectionPhones = null;
    private ArrayList<TLRPC.PrivacyRule> addedByPhonePrivacyRules;
    private ArrayList<TLRPC.PrivacyRule> bioPrivacyRules;
    private ArrayList<TLRPC.PrivacyRule> birthdayPrivacyRules;
    private ArrayList<TLRPC.PrivacyRule> callPrivacyRules;
    private int completedRequestsCount;
    public ArrayList<TLRPC.TL_contact> contacts;
    public HashMap<String, Contact> contactsBook;
    private boolean contactsBookLoaded;
    public HashMap<String, Contact> contactsBookSPhones;
    public HashMap<String, TLRPC.TL_contact> contactsByPhone;
    public HashMap<String, TLRPC.TL_contact> contactsByShortPhone;
    public ConcurrentHashMap<Long, TLRPC.TL_contact> contactsDict;
    public boolean contactsLoaded;
    private boolean contactsSyncInProgress;
    private ArrayList<Long> delayedContactsUpdate;
    private int deleteAccountTTL;
    public boolean doneLoadingContacts;
    private ArrayList<TLRPC.PrivacyRule> forwardsPrivacyRules;
    private ArrayList<TLRPC.PrivacyRule> giftsPrivacyRules;
    private TLRPC.GlobalPrivacySettings globalPrivacySettings;
    private ArrayList<TLRPC.PrivacyRule> groupPrivacyRules;
    private boolean ignoreChanges;
    private String inviteLink;
    private String lastContactsVersions;
    private ArrayList<TLRPC.PrivacyRule> lastseenPrivacyRules;
    private final Object loadContactsSync;
    private boolean loadingContacts;
    private int loadingDeleteInfo;
    private int loadingGlobalSettings;
    private int[] loadingPrivacyInfo;
    private boolean migratingContacts;
    private ArrayList<TLRPC.PrivacyRule> musicPrivacyRules;
    private ArrayList<TLRPC.PrivacyRule> noPaidMessagesPrivacyRules;
    private final Object observerLock;
    private ArrayList<TLRPC.PrivacyRule> p2pPrivacyRules;
    public HashMap<String, Contact> phoneBookByShortPhones;
    public ArrayList<Contact> phoneBookContacts;
    public ArrayList<String> phoneBookSectionsArray;
    public HashMap<String, ArrayList<Object>> phoneBookSectionsDict;
    private ArrayList<TLRPC.PrivacyRule> phonePrivacyRules;
    private ArrayList<TLRPC.PrivacyRule> profilePhotoPrivacyRules;
    private HashMap<String, String> sectionsToReplace;
    public ArrayList<String> sortedUsersMutualSectionsArray;
    public ArrayList<String> sortedUsersSectionsArray;
    private Account systemAccount;
    private boolean updatingInviteLink;
    public HashMap<String, ArrayList<TLRPC.TL_contact>> usersMutualSectionsDict;
    public HashMap<String, ArrayList<TLRPC.TL_contact>> usersSectionsDict;
    private ArrayList<TLRPC.PrivacyRule> voiceMessagesRules;

    /* renamed from: org.telegram.messenger.ContactsController$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 extends Collator {
        public AnonymousClass1() {
        }

        @Override // java.text.Collator
        public int compare(String r1, String r2) {
            if (r1 == null) goto L7;
            if (r2 != null) goto L6;
            return 0;
        L6:
            return r1.compareTo(r2);
        L7:
            return 0;
        }

        @Override // java.text.Collator
        public CollationKey getCollationKey(String r1) {
            return null;
        }

        @Override // java.text.Collator
        public int hashCode() {
            return 0;
        }
    }

    public static class Contact {
        public int contact_id;
        public String first_name;
        public int imported;
        public boolean isGoodProvider;
        public String key;
        public String last_name;
        public boolean namesFilled;
        public ArrayList<Integer> phoneDeleted;
        public ArrayList<String> phoneTypes;
        public ArrayList<String> phones;
        public String provider;
        public ArrayList<String> shortPhones;
        public TLRPC.User user;

        public Contact() {
            this.phones = new ArrayList(4);
            this.phoneTypes = new ArrayList(4);
            this.shortPhones = new ArrayList(4);
            this.phoneDeleted = new ArrayList(4);
        }

        public static String getLetter(String r3, String r4) {
            if (TextUtils.isEmpty(r3) == true) goto L7;
            return r3.substring(0, 1);
        L7:
            if (TextUtils.isEmpty(r4) == false) goto L9;
            return "#";
        L9:
            return r4.substring(0, 1);
        }

        public String getLetter() {
            return getLetter(this.first_name, this.last_name);
        }
    }

    /* loaded from: classes5.dex */
    public class MyContentObserver extends ContentObserver {
        private Runnable checkRunnable;
        final /* synthetic */ ContactsController this$0;

        /* renamed from: $r8$lambda$V7bWM39F88C-9C-h7Xl0TeFehTo, reason: not valid java name */
        public static /* synthetic */ void m3973$r8$lambda$V7bWM39F88C9Ch7Xl0TeFehTo() {
            int r0 = 0;
        L4:
            if (r0 >= 16) goto L9;
            if (UserConfig.getInstance(r0).isClientActivated() == false) goto L8;
            ConnectionsManager.getInstance(r0).resumeNetworkMaybe();
            ContactsController.getInstance(r0).checkContacts();
        L8:
            r0 = r0 + 1;
            goto L4
        }

        public MyContentObserver(ContactsController r1) {
            this.this$0 = r1;
            super(null);
            this.checkRunnable = new ContactsController$MyContentObserver$$ExternalSyntheticLambda0();
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return false;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean r3) {
            super.onChange(r3);
            Object r32 = ContactsController.m3972$$Nest$fgetobserverLock(this.this$0);
            monitor-enter(r32);
        L8:
            th = move-exception;
            throw th;
        L5:
            if (ContactsController.m3971$$Nest$fgetignoreChanges(this.this$0) == false) goto L10;
            monitor-exit(r32);     // Catch: Throwable -> L8
            return;
        L10:
            monitor-exit(r32);     // Catch: Throwable -> L8
            Utilities.globalQueue.cancelRunnable(this.checkRunnable);
            Utilities.globalQueue.postRunnable(this.checkRunnable, 500);
        }
    }

    public class PhoneBookContact {
        String id;
        String lookup_key;
        String name;
        String phone;
        final /* synthetic */ ContactsController this$0;

        private PhoneBookContact(ContactsController r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ PhoneBookContact(ContactsController r1, ContactsControllerIA r2) {
            this(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$0PnKLTdSopc3WCZnITve8_AQ5jc(ContactsController r0, int r1, ArrayList r2, ArrayList r3, LongSparseArray r4, boolean r5) {
        r0.lambda$processLoadedContacts$36(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$1xjqEF65wb-lfqg6b5fAr775no0, reason: not valid java name */
    public static /* synthetic */ void m3946$r8$lambda$1xjqEF65wblfqg6b5fAr775no0(ContactsController r0) {
        r0.lambda$checkContacts$5();
    }

    public static /* synthetic */ void $r8$lambda$2qZKhkGa089FwjazysaGddvWfok(ContactsController r0) {
        r0.lambda$checkAppAccount$4();
    }

    public static /* synthetic */ void $r8$lambda$3jnjTQqUBfORORrJc8O4UCRAJD0(ContactsController r0, ArrayList r1, ArrayList r2) {
        r0.lambda$applyContactsUpdates$48(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$3kLSExHkbpEOZRcpxnKH3hiSKQ8(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$3kd4NdEgr4433iMZoGZHujSEx60(ContactsController r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$loadPrivacySettings$62(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$4KfgWDUonH9O9ehvA3SEyEA4ZGE(ContactsController r0, HashMap r1, HashMap r2, boolean r3, HashMap r4, ArrayList r5, HashMap r6) {
        r0.lambda$performSyncPhoneBook$24(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$5GKTbGuzjxwLDC5HMezCq28lIG8(ContactsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$checkInviteText$3(r1, r2);
    }

    /* renamed from: $r8$lambda$5qDOy5UJRVuVLqDAHJyrH-hgyz0, reason: not valid java name */
    public static /* synthetic */ void m3947$r8$lambda$5qDOy5UJRVuVLqDAHJyrHhgyz0(ContactsController r0, SparseArray r1) {
        r0.lambda$migratePhoneBookToV7$12(r1);
    }

    public static /* synthetic */ int $r8$lambda$7FRrR4a9X27JXVm5UFt68w07Xyw(Collator r3, String r4, String r5) {
        char r1 = r4.charAt(0);
        char r0 = r5.charAt(0);
        if (r1 != '#') goto L6;
        return 1;
    L6:
        if (r0 != '#') goto L10;
        return -1;
    L10:
        return r3.compare(r4, r5);
    }

    /* renamed from: $r8$lambda$A-AUcvLXc5v30rVqrQH-O4iJ_II, reason: not valid java name */
    public static /* synthetic */ void m3948$r8$lambda$AAUcvLXc5v30rVqrQHO4iJ_II(ContactsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadPrivacySettings$63(r1, r2);
    }

    /* renamed from: $r8$lambda$Bbi35o5KlwqaBwxhqrTezO2G-iQ, reason: not valid java name */
    public static /* synthetic */ void m3949$r8$lambda$Bbi35o5KlwqaBwxhqrTezO2GiQ(ContactsController r0, HashMap r1, ArrayList r2, HashMap r3) {
        r0.lambda$performSyncPhoneBook$17(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$CTYcgKLZGPGli3d_aNlTWQ13FK4(ContactsController r0, HashMap r1, ArrayList r2, HashMap r3) {
        r0.lambda$performSyncPhoneBook$23(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Dez0dveAeqJzN6RK_2zNtQ0ow6U(ContactsController r0, TLRPC.TL_help_inviteText r1) {
        r0.lambda$checkInviteText$2(r1);
    }

    public static /* synthetic */ int $r8$lambda$EoqSNI82ZJZ2JYcR7TTNAdah2R4(TLRPC.TL_contact r2, TLRPC.TL_contact r3) {
        long r0 = r2.user_id;
        long r22 = r3.user_id;
        if (r0 <= r22) goto L6;
        return 1;
    L6:
        if (r0 >= r22) goto L9;
        return -1;
    L9:
        return 0;
    }

    /* renamed from: $r8$lambda$Fmo1HFpCkIxcaM_lWI0-g1Nk2QE, reason: not valid java name */
    public static /* synthetic */ void m3950$r8$lambda$Fmo1HFpCkIxcaM_lWI0g1Nk2QE(ContactsController r0, TLRPC.User r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$addContact$52(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$FoB-aAXvnt6Jb3kEdF1M1ph5aKY, reason: not valid java name */
    public static /* synthetic */ void m3951$r8$lambda$FoBaAXvnt6Jb3kEdF1M1ph5aKY(ContactsController r0, SharedPreferences.Editor r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$reloadContactsStatuses$59(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$HVpxctdv20CJSj5I0-SELhktJpw, reason: not valid java name */
    public static /* synthetic */ void m3952$r8$lambda$HVpxctdv20CJSj5I0SELhktJpw(ContactsController r0) {
        r0.lambda$loadContacts$27();
    }

    public static /* synthetic */ void $r8$lambda$Hp6ctSJBOv96WLyQi3Dde8aTwuY(ContactsController r0, HashMap r1) {
        r0.lambda$deleteContactsUndoable$53(r1);
    }

    /* renamed from: $r8$lambda$KbLsX-vMGafzUDmGZ1eZT6bqpk0, reason: not valid java name */
    public static /* synthetic */ void m3953$r8$lambda$KbLsXvMGafzUDmGZ1eZT6bqpk0(ContactsController r0, ArrayList r1, HashMap r2, HashMap r3) {
        r0.lambda$mergePhonebookAndTelegramContacts$40(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$KdxsDzNdBySWk-SquPm6yasNp8U, reason: not valid java name */
    public static /* synthetic */ void m3954$r8$lambda$KdxsDzNdBySWkSquPm6yasNp8U(String r3) {
        Uri r32 = Uri.parse(r3);
        ContentValues r0 = new ContentValues();
        r0.put("last_time_contacted", Long.valueOf(System.currentTimeMillis()));
        ApplicationLoader.applicationContext.getContentResolver().update(r32, r0, null, null);
    }

    /* renamed from: $r8$lambda$KkK_FcxfCxCbU-A-2tkb9JlJ5-g, reason: not valid java name */
    public static /* synthetic */ int m3955$r8$lambda$KkK_FcxfCxCbUA2tkb9JlJ5g(LongSparseArray r2, Collator r3, TLRPC.TL_contact r4, TLRPC.TL_contact r5) {
        TLRPC.User r42 = (TLRPC.User) r2.get(r4.user_id);
        TLRPC.User r22 = (TLRPC.User) r2.get(r5.user_id);
        return r3.compare(UserObject.getFirstName(r42), UserObject.getFirstName(r22));
    }

    /* renamed from: $r8$lambda$KlIjI7GG0t6uXPQc-mmk93U_cog, reason: not valid java name */
    public static /* synthetic */ void m3956$r8$lambda$KlIjI7GG0t6uXPQcmmk93U_cog(ContactsController r0) {
        r0.lambda$applyContactsUpdates$47();
    }

    public static /* synthetic */ void $r8$lambda$LPXaMnhvb9vvbhLiZsqb60MvmRY(ContactsController r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadContacts$28(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$LVPnt8kERKq7ysQ2jLhMqkcyOCk(ContactsController r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$deleteAllContacts$9(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$LXm6-HIOxMGgypUoFNTZ7d027uc, reason: not valid java name */
    public static /* synthetic */ void m3957$r8$lambda$LXm6HIOxMGgypUoFNTZ7d027uc(ContactsController r0, HashMap r1, boolean r2, boolean r3, boolean r4, boolean r5, boolean r6, boolean r7) {
        r0.lambda$performSyncPhoneBook$25(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$Lee4IsbS_VCMhzSoWjoqpQX4Rtw(ContactsController r0, boolean r1, ArrayList r2, HashMap r3, HashMap r4, ArrayList r5) {
        r0.lambda$mergePhonebookAndTelegramContacts$41(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ int $r8$lambda$LmjoaGJCZKV1FB4RaDKP3eteBZo(Collator r3, String r4, String r5) {
        char r1 = r4.charAt(0);
        char r0 = r5.charAt(0);
        if (r1 != '#') goto L6;
        return 1;
    L6:
        if (r0 != '#') goto L10;
        return -1;
    L10:
        return r3.compare(r4, r5);
    }

    /* renamed from: $r8$lambda$MKhBLgz5-59E9l_nZkQOFEIqiw4, reason: not valid java name */
    public static /* synthetic */ void m3958$r8$lambda$MKhBLgz559E9l_nZkQOFEIqiw4(ContactsController r0, ArrayList r1) {
        r0.lambda$deleteContactsUndoable$54(r1);
    }

    public static /* synthetic */ void $r8$lambda$T391OfHRXQgw7BmeLbdlekkKpPE(ContactsController r0, TLRPC.User r1) {
        r0.lambda$addContact$50(r1);
    }

    public static /* synthetic */ void $r8$lambda$TjQ3Dbu7ZcqFrPSG3m52fOjrJ4E(ContactsController r0, Long r1) {
        r0.lambda$applyContactsUpdates$46(r1);
    }

    public static /* synthetic */ void $r8$lambda$ToADsxE3N9JHg7gagGllfpu2j2k(ContactsController r0, HashMap r1, HashMap r2, boolean r3, HashMap r4, ArrayList r5, HashMap r6) {
        r0.lambda$performSyncPhoneBook$16(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$U0hZb1EEBrDbMUUg7je_roSR5HQ(ContactsController r0, TLRPC.TL_error r1, TLObject r2, int r3) {
        r0.lambda$loadPrivacySettings$64(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$UMJyTg5FZ2H7PXjh4yKIqvnzz0k(ContactsController r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$loadGlobalPrivacySetting$60(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Uj7OPqCCSLLb1HFn6R9GIRIXst4(ContactsController r0, HashMap r1, HashMap r2) {
        r0.lambda$processLoadedContacts$35(r1, r2);
    }

    /* renamed from: $r8$lambda$VrPp5qPZa2fqY-i8fuHQUHC99kQ, reason: not valid java name */
    public static /* synthetic */ void m3959$r8$lambda$VrPp5qPZa2fqYi8fuHQUHC99kQ(ContactsController r0, TLRPC.Updates r1, TLRPC.User r2) {
        r0.lambda$addContact$51(r1, r2);
    }

    /* renamed from: $r8$lambda$WzPKw0eDXIhv6oQVE8-ad2kfSmY, reason: not valid java name */
    public static /* synthetic */ int m3960$r8$lambda$WzPKw0eDXIhv6oQVE8ad2kfSmY(Collator r2, Object r3, Object r4) {
        boolean r0 = r3 instanceof TLRPC.User;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 == false) goto L6;
        TLRPC.User r32 = (TLRPC.User) r3;
        String r33 = formatName(r32.first_name, r32.last_name);
    L13:
        if ((r4 instanceof TLRPC.User) == false) goto L16;
        TLRPC.User r42 = (TLRPC.User) r4;
        r1 = formatName(r42.first_name, r42.last_name);
    L23:
        return r2.compare(r33, r1);
    L16:
        if ((r4 instanceof Contact) == false) goto L23;
        Contact r43 = (Contact) r4;
        TLRPC.User r02 = r43.user;
        if (r02 == null) goto L21;
        String r44 = formatName(r02.first_name, r02.last_name);
    L20:
        r1 = r44;
        goto L23
    L21:
        r44 = formatName(r43.first_name, r43.last_name);
        goto L20
    L6:
        if ((r3 instanceof Contact) == false) goto L11;
        Contact r34 = (Contact) r3;
        TLRPC.User r03 = r34.user;
        if (r03 == null) goto L10;
        r33 = formatName(r03.first_name, r03.last_name);
        goto L13
    L10:
        r33 = formatName(r34.first_name, r34.last_name);
        goto L13
    L11:
        r33 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L13
    }

    /* renamed from: $r8$lambda$Xtmda3KQqq-tI4Vvr8ZSOIf3-og, reason: not valid java name */
    public static /* synthetic */ void m3961$r8$lambda$Xtmda3KQqqtI4Vvr8ZSOIf3og(ContactsController r0, ArrayList r1) {
        r0.lambda$performWriteContactsToPhoneBook$45(r1);
    }

    /* renamed from: $r8$lambda$Y5mN5wdIk67yD1N2X-yeV0PZnUo, reason: not valid java name */
    public static /* synthetic */ void m3962$r8$lambda$Y5mN5wdIk67yD1N2XyeV0PZnUo(ContactsController r0, HashMap r1, boolean r2, boolean r3, boolean r4) {
        r0.lambda$syncPhoneBookByAlert$7(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$ZKNH-5iaNAqjClOZaekmBh9OdzA, reason: not valid java name */
    public static /* synthetic */ void m3963$r8$lambda$ZKNH5iaNAqjClOZaekmBh9OdzA(ContactsController r0) {
        r0.lambda$performSyncPhoneBook$18();
    }

    public static /* synthetic */ void $r8$lambda$ZVhyQydyIgvFpmvfw5umCORqO1Q(ContactsController r0, ArrayList r1, boolean r2, String r3) {
        r0.lambda$deleteContact$56(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$a6l0bmRmSkmg7UPTWcbpsnbTNPk(ContactsController r0, HashMap r1, HashMap r2) {
        r0.lambda$processLoadedContacts$34(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$aABnLa2UgPmbmiowyp91HnEMRYM(ContactsController r0) {
        r0.lambda$cleanup$1();
    }

    public static /* synthetic */ void $r8$lambda$bMJ0DRNOJ2oAh0jhYOEVRoMO0_8(ContactsController r0, HashMap r1, ArrayList r2, HashMap r3) {
        r0.lambda$performSyncPhoneBook$15(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$cNan2huPu_3LAsQLuy76fKd4jdY(ContactsController r0) {
        r0.lambda$processLoadedContacts$29();
    }

    public static /* synthetic */ void $r8$lambda$d9fSO0SZw5RrbDFqlcNTkUgAsMM(ContactsController r0, ArrayList r1, ConcurrentHashMap r2, HashMap r3, HashMap r4, ArrayList r5, ArrayList r6, int r7, boolean r8, boolean r9) {
        r0.lambda$processLoadedContacts$33(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$eGrkH1UgmSPsk2tyn_Cf1iBRNFk(ContactsController r0, Runnable r1) {
        r0.lambda$deleteAllContacts$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$fCU2aIPNw30MxjkBmyOAVVUJGxM(ContactsController r0, ArrayList r1) {
        r0.lambda$deleteContact$55(r1);
    }

    /* renamed from: $r8$lambda$fsPGeYDOzu_e9-TgPeVmBNZ96zw, reason: not valid java name */
    public static /* synthetic */ void m3964$r8$lambda$fsPGeYDOzu_e9TgPeVmBNZ96zw(ContactsController r0, SharedPreferences.Editor r1, TLObject r2) {
        r0.lambda$reloadContactsStatuses$58(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$hGKiWaKZlL1gl1pfkB1fCc9fP7Y(ContactsController r0, HashMap r1, SparseArray r2, boolean[] r3, HashMap r4, TLRPC.TL_contacts_importContacts r5, int r6, HashMap r7, boolean r8, HashMap r9, ArrayList r10, HashMap r11, TLObject r12, TLRPC.TL_error r13) {
        r0.lambda$performSyncPhoneBook$20(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
    }

    /* renamed from: $r8$lambda$hj1bMqpqHFptNGw-1SaNPswetxQ, reason: not valid java name */
    public static /* synthetic */ int m3965$r8$lambda$hj1bMqpqHFptNGw1SaNPswetxQ(Collator r3, String r4, String r5) {
        char r1 = r4.charAt(0);
        char r0 = r5.charAt(0);
        if (r1 != '#') goto L6;
        return 1;
    L6:
        if (r0 != '#') goto L10;
        return -1;
    L10:
        return r3.compare(r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$jUsDPri2OZczhmXPoq18WH8_cf0(ContactsController r0) {
        r0.lambda$readContacts$11();
    }

    /* renamed from: $r8$lambda$lZpJHqRAyplVr2g7JR0-6Fg9OLc, reason: not valid java name */
    public static /* synthetic */ void m3966$r8$lambda$lZpJHqRAyplVr2g7JR06Fg9OLc(ContactsController r0) {
        r0.lambda$forceImportContacts$6();
    }

    public static /* synthetic */ void $r8$lambda$lr1NJ7lfk6ZkSbutCIYALTMtoWo(ContactsController r0, HashMap r1, HashMap r2, boolean r3, HashMap r4, ArrayList r5, HashMap r6) {
        r0.lambda$performSyncPhoneBook$22(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ int $r8$lambda$luv2pvYNpBak6rdoMFwhTSDLbSg(ContactsController r0, Collator r1, TLRPC.TL_contact r2, TLRPC.TL_contact r3) {
        return r0.lambda$buildContactsSectionsArrays$43(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$mmZcqDtTTUrtJlVqbQBnR1298kE(ContactsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadGlobalPrivacySetting$61(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$nkYWmnp7usxTQyNstDo0rBpOMU0(ContactsController r0, ArrayList r1, ArrayList r2, boolean r3, String r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$deleteContact$57(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$qaJr8S-2ja-Tdab27du0-a2DgO4, reason: not valid java name */
    public static /* synthetic */ void m3967$r8$lambda$qaJr8S2jaTdab27du0a2DgO4(ContactsController r0, HashMap r1, HashMap r2, boolean r3, HashMap r4, ArrayList r5, HashMap r6, boolean[] r7) {
        r0.lambda$performSyncPhoneBook$19(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ int $r8$lambda$qp1Mm9C7fv5a8XV5V4R8DcUSLyE(Collator r3, String r4, String r5) {
        char r1 = r4.charAt(0);
        char r0 = r5.charAt(0);
        if (r1 != '#') goto L6;
        return 1;
    L6:
        if (r0 != '#') goto L10;
        return -1;
    L10:
        return r3.compare(r4, r5);
    }

    /* renamed from: $r8$lambda$tVtR4Gbnnvv1E-UM_B4AhoDUFto, reason: not valid java name */
    public static /* synthetic */ void m3968$r8$lambda$tVtR4Gbnnvv1EUM_B4AhoDUFto(ContactsController r0, int r1, HashMap r2, boolean r3, boolean r4) {
        r0.lambda$performSyncPhoneBook$14(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$u4LNxGK2nxedbaprqL2mwwbsi58(ContactsController r0, HashMap r1, ArrayList r2, HashMap r3) {
        r0.lambda$performSyncPhoneBook$21(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$w32BRk5ZBa-Nj9wVcdtpRzb8Ohc, reason: not valid java name */
    public static /* synthetic */ void m3969$r8$lambda$w32BRk5ZBaNj9wVcdtpRzb8Ohc(ContactsController r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$wSws278k-EIHaJyk0bEjBj-pZwQ, reason: not valid java name */
    public static /* synthetic */ void m3970$r8$lambda$wSws278kEIHaJyk0bEjBjpZwQ(ContactsController r0, ArrayList r1, int r2, ArrayList r3) {
        r0.lambda$processLoadedContacts$37(r1, r2, r3);
    }

    public static /* synthetic */ int $r8$lambda$yGcrFYDPChyOJMc3cE_7E1f7VFk(Collator r2, Contact r3, Contact r4) {
        String r0 = r3.first_name;
        if (r0.length() != 0) goto L5;
        r0 = r3.last_name;
    L5:
        String r32 = r4.first_name;
        if (r32.length() != 0) goto L9;
        r32 = r4.last_name;
    L9:
        return r2.compare(r0, r32);
    }

    public static /* synthetic */ void $r8$lambda$ziwlaapE6vVKuJf_0Ns9plNZrAk(ContactsController r0, int r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$loadPrivacySettings$65(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetignoreChanges, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m3971$$Nest$fgetignoreChanges(ContactsController r0) {
        return r0.ignoreChanges;
    }

    /* renamed from: -$$Nest$fgetobserverLock, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object m3972$$Nest$fgetobserverLock(ContactsController r0) {
        return r0.observerLock;
    }

    static {
        projectionPhones = new String[]{"lookup", "data1", "data2", "data3", "display_name", "account_type"};
        projectionNames = new String[]{"lookup", "data2", "data3", "data5"};
        Instance = new ContactsController[16];
    }

    public ContactsController(int r5) {
        super(r5);
        this.loadContactsSync = new Object();
        this.observerLock = new Object();
        this.lastContactsVersions = _UrlKt.FRAGMENT_ENCODE_SET;
        this.delayedContactsUpdate = new ArrayList();
        this.sectionsToReplace = new HashMap();
        this.loadingPrivacyInfo = new int[15];
        this.contactsBook = new HashMap();
        this.contactsBookSPhones = new HashMap();
        this.phoneBookContacts = new ArrayList();
        this.phoneBookSectionsDict = new HashMap();
        this.phoneBookSectionsArray = new ArrayList();
        this.phoneBookByShortPhones = new HashMap();
        this.contacts = new ArrayList();
        this.contactsDict = new ConcurrentHashMap(20, 1.0f, 2);
        this.usersSectionsDict = new HashMap();
        this.sortedUsersSectionsArray = new ArrayList();
        this.usersMutualSectionsDict = new HashMap();
        this.sortedUsersMutualSectionsArray = new ArrayList();
        this.contactsByPhone = new HashMap();
        this.contactsByShortPhone = new HashMap();
        if (MessagesController.getMainSettings(this.currentAccount).getBoolean("needGetStatuses", false) == false) goto L5;
        reloadContactsStatuses();
    L5:
        this.sectionsToReplace.put("À", "A");
        this.sectionsToReplace.put("Á", "A");
        this.sectionsToReplace.put("Ä", "A");
        this.sectionsToReplace.put("Ù", "U");
        this.sectionsToReplace.put("Ú", "U");
        this.sectionsToReplace.put("Ü", "U");
        this.sectionsToReplace.put("Ì", "I");
        this.sectionsToReplace.put("Í", "I");
        this.sectionsToReplace.put("Ï", "I");
        this.sectionsToReplace.put("È", "E");
        this.sectionsToReplace.put("É", "E");
        this.sectionsToReplace.put("Ê", "E");
        this.sectionsToReplace.put("Ë", "E");
        this.sectionsToReplace.put("Ò", "O");
        this.sectionsToReplace.put("Ó", "O");
        this.sectionsToReplace.put("Ö", "O");
        this.sectionsToReplace.put("Ç", "C");
        this.sectionsToReplace.put("Ñ", "N");
        this.sectionsToReplace.put("Ÿ", "Y");
        this.sectionsToReplace.put("Ý", "Y");
        this.sectionsToReplace.put("Ţ", "Y");
        if (r5 != 0) goto L9;
        Utilities.globalQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda54(this));
        return;
    }

    private void applyContactToPhoneBook(ArrayList<ContentProviderOperation> r13, TLRPC.User r14) {
        if (r14 != null) goto L4;
        return;
    L4:
        int r0 = r13.size();
        ContentProviderOperation.Builder r1 = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI);
        r1.withValue("account_name", this.systemAccount.name);
        r1.withValue("account_type", this.systemAccount.type);
        if (TextUtils.isEmpty(r14.phone) == false) goto L7;
        String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        r1.withValue("sync1", r12);
        r1.withValue("sync2", Long.valueOf(r14.id));
        r13.add(r1.build());
        Uri r122 = ContactsContract.Data.CONTENT_URI;
        ContentProviderOperation.Builder r15 = ContentProviderOperation.newInsert(r122);
        r15.withValueBackReference("raw_contact_id", r0);
        r15.withValue("mimetype", "vnd.android.cursor.item/name");
        r15.withValue("data2", r14.first_name);
        r15.withValue("data3", r14.last_name);
        r13.add(r15.build());
        if (TextUtils.isEmpty(r14.phone) == false) goto L11;
        String r16 = formatName(r14.first_name, r14.last_name);
    L12:
        ContentProviderOperation.Builder r3 = ContentProviderOperation.newInsert(r122);
        r3.withValueBackReference("raw_contact_id", r0);
        r3.withValue("mimetype", "vnd.android.cursor.item/vnd.org.telegram.messenger.android.profile");
        r3.withValue("data1", Long.valueOf(r14.id));
        r3.withValue("data2", "Telegram Profile");
        r3.withValue("data3", LocaleController.formatString("ContactShortcutMessage", R.string.ContactShortcutMessage, new Object[]{r16}));
        r3.withValue("data4", Long.valueOf(r14.id));
        r13.add(r3.build());
        ContentProviderOperation.Builder r32 = ContentProviderOperation.newInsert(r122);
        r32.withValueBackReference("raw_contact_id", r0);
        r32.withValue("mimetype", "vnd.android.cursor.item/vnd.org.telegram.messenger.android.call");
        r32.withValue("data1", Long.valueOf(r14.id));
        r32.withValue("data2", "Telegram Voice Call");
        r32.withValue("data3", LocaleController.formatString("ContactShortcutVoiceCall", R.string.ContactShortcutVoiceCall, new Object[]{r16}));
        r32.withValue("data4", Long.valueOf(r14.id));
        r13.add(r32.build());
        ContentProviderOperation.Builder r123 = ContentProviderOperation.newInsert(r122);
        r123.withValueBackReference("raw_contact_id", r0);
        r123.withValue("mimetype", "vnd.android.cursor.item/vnd.org.telegram.messenger.android.call.video");
        r123.withValue("data1", Long.valueOf(r14.id));
        r123.withValue("data2", "Telegram Video Call");
        r123.withValue("data3", LocaleController.formatString("ContactShortcutVideoCall", R.string.ContactShortcutVideoCall, new Object[]{r16}));
        r123.withValue("data4", Long.valueOf(r14.id));
        r13.add(r123.build());
        return;
    L11:
        r16 = "+" + r14.phone;
        goto L12
    L7:
        r12 = r14.phone;
        goto L8
    }

    private void applyContactsUpdates(ArrayList<Long> r12, ConcurrentHashMap<Long, TLRPC.User> r13, ArrayList<TLRPC.TL_contact> r14, ArrayList<Long> r15) {
        int r0 = 0;
        if (r14 == null) goto L5;
        if (r15 == null) goto L5;
    L16:
        if (BuildVars.LOGS_ENABLED == false) goto L18;
        FileLog.d("process update - contacts add = " + r14.size() + " delete = " + r15.size());
    L18:
        StringBuilder r122 = new StringBuilder();
        StringBuilder r1 = new StringBuilder();
        int r2 = 0;
        boolean r3 = false;
    L19:
        TLRPC.User r7 = null;
        if (r2 >= r14.size()) goto L43;
        TLRPC.TL_contact r4 = r14.get(r2);
        if (r13 == null) goto L24;
        r7 = r13.get(Long.valueOf(r4.user_id));
    L24:
        if (r7 != null) goto L26;
        r7 = getMessagesController().getUser(Long.valueOf(r4.user_id));
    L27:
        if (r7 != null) goto L29;
    L40:
        r3 = true;
    L41:
        r2 = r2 + 1;
        goto L19
    L29:
        if (TextUtils.isEmpty(r7.phone) == true) goto L40;
        Contact r42 = this.contactsBookSPhones.get(r7.phone);
        if (r42 == null) goto L37;
        int r8 = r42.shortPhones.indexOf(r7.phone);
        if (r8 == (-1)) goto L37;
        r42.phoneDeleted.set(r8, 0);
    L37:
        if (r122.length() == 0) goto L39;
        r122.append(",");
    L39:
        r122.append(r7.phone);
        goto L41
    L26:
        getMessagesController().putUser(r7, true);
    L43:
        if (r0 >= r15.size()) goto L66;
        Long r22 = r15.get(r0);
        Utilities.phoneBookQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda2(this, r22));
        if (r13 == null) goto L47;
        TLRPC.User r43 = r13.get(r22);
    L48:
        if (r43 != null) goto L50;
        r43 = getMessagesController().getUser(r22);
    L51:
        if (r43 != null) goto L54;
        r3 = true;
    L64:
        r0 = r0 + 1;
        goto L43
    L54:
        if (TextUtils.isEmpty(r43.phone) == true) goto L64;
        Contact r23 = this.contactsBookSPhones.get(r43.phone);
        if (r23 == null) goto L61;
        int r9 = r23.shortPhones.indexOf(r43.phone);
        if (r9 == (-1)) goto L61;
        r23.phoneDeleted.set(r9, 1);
    L61:
        if (r1.length() == 0) goto L63;
        r1.append(",");
    L63:
        r1.append(r43.phone);
        goto L64
    L50:
        getMessagesController().putUser(r43, true);
        goto L51
    L47:
        r43 = null;
        goto L48
    L66:
        if (r122.length() == 0) goto L68;
    L69:
        getMessagesStorage().applyPhoneBookUpdates(r122.toString(), r1.toString());
    L70:
        if (r3 == false) goto L73;
        Utilities.stageQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda3(this));
        return;
    L73:
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda4(this, r14, r15));
        return;
    L68:
        if (r1.length() == 0) goto L70;
    L5:
        r14 = new ArrayList();
        r15 = new ArrayList();
        int r16 = 0;
    L7:
        if (r16 >= r12.size()) goto L16;
        Long r24 = r12.get(r16);
        if (r24.longValue() <= 0) goto L12;
        TLRPC.TL_contact r32 = new TLRPC.TL_contact();
        r32.user_id = r24.longValue();
        r14.add(r32);
    L14:
        r16 = r16 + 1;
        goto L7
    L12:
        if (r24.longValue() >= 0) goto L14;
        r15.add(Long.valueOf(-r24.longValue()));
        goto L14
    }

    private void buildContactsSectionsArrays(boolean r9) {
        Collator r0 = getLocaleCollator();
        if (r9 == false) goto L5;
        Collections.sort(this.contacts, new ContactsController$$ExternalSyntheticLambda17(this, r0));
    L5:
        HashMap<String, ArrayList<TLRPC.TL_contact>> r92 = new HashMap();
        ArrayList<String> r1 = new ArrayList();
        int r3 = 0;
    L7:
        if (r3 >= this.contacts.size()) goto L26;
        TLRPC.TL_contact r4 = this.contacts.get(r3);
        TLRPC.User r5 = getMessagesController().getUser(Long.valueOf(r4.user_id));
        if (r5 == null) goto L25;
        String r52 = UserObject.getFirstName(r5);
        if (r52.length() <= 1) goto L15;
        r52 = r52.substring(0, 1);
    L15:
        if (r52.length() != 0) goto L17;
        String r53 = "#";
    L18:
        String r6 = this.sectionsToReplace.get(r53);
        if (r6 == null) goto L21;
        r53 = r6;
    L21:
        ArrayList<TLRPC.TL_contact> r62 = r92.get(r53);
        if (r62 != null) goto L24;
        r62 = new ArrayList();
        r92.put(r53, r62);
        r1.add(r53);
    L24:
        r62.add(r4);
        goto L25
    L17:
        r53 = r52.toUpperCase();
    L25:
        r3 = r3 + 1;
        goto L7
    L26:
        Collections.sort(r1, new ContactsController$$ExternalSyntheticLambda18(r0));
        this.usersSectionsDict = r92;
        this.sortedUsersSectionsArray = r1;
    }

    private boolean checkContactsInternal() {
        boolean r1 = false;
    L34:
        e = move-exception;
        FileLog.e(e);
    L36:
        return r1;
    L4:
        if (hasContactsPermission() == true) goto L6;
        return false;
    L6:
        ContentResolver r3 = ApplicationLoader.applicationContext.getContentResolver();     // Catch: Exception -> L34
        Cursor r2 = r3.query(ContactsContract.RawContacts.CONTENT_URI, new String[]{"version"}, null, null, null);     // Catch: Exception -> L27
        if (r2 != null) goto L40;
    L29:
        if (r2 == null) goto L36;
        r2.close();     // Catch: Exception -> L27
        goto L36
    L40:
        StringBuilder r32 = new StringBuilder();     // Catch: Throwable -> L13
    L11:
        if (r2.moveToNext() == false) goto L15;
        r32.append(r2.getString(r2.getColumnIndex("version")));     // Catch: Throwable -> L13
        goto L11
    L15:
        String r0 = r32.toString();     // Catch: Throwable -> L13
        if (this.lastContactsVersions.length() != 0) goto L18;
    L20:
        this.lastContactsVersions = r0;     // Catch: Throwable -> L13
    L18:
        if (this.lastContactsVersions.equals(r0) == true) goto L20;
        r1 = true;
    L13:
        th = move-exception;
        r2.close();     // Catch: Throwable -> L24
    L44:
        throw th;     // Catch: Exception -> L27
    L24:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L27
        throw th;     // Catch: Exception -> L27
    L27:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L34
        goto L36
    }

    private void deleteContactFromPhoneBook(long r6) {
        if (hasContactsPermission() == false) goto L32;
        Object r1 = this.observerLock;
        monitor-enter(r1);
        this.ignoreChanges = true;     // Catch: Throwable -> L23
        monitor-exit(r1);     // Catch: Throwable -> L23
        ApplicationLoader.applicationContext.getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter("caller_is_syncadapter", "true").appendQueryParameter("account_name", this.systemAccount.name).appendQueryParameter("account_type", this.systemAccount.type).build(), "sync2 = " + r6, null);     // Catch: Exception -> L12
    L14:
        Object r62 = this.observerLock;
        monitor-enter(r62);
        this.ignoreChanges = false;     // Catch: Throwable -> L20
        monitor-exit(r62);     // Catch: Throwable -> L20
        return;
    L20:
        th = move-exception;
        throw th;
    L12:
        e = move-exception;
        FileLog.e(e);
    L23:
        th = move-exception;
        throw th;
    }

    public static <T extends TLRPC.PrivacyRule> T findRule(ArrayList<TLRPC.PrivacyRule> r5, Class<T> r6) {
        if (r5 != null) goto L5;
        return null;
    L5:
        int r1 = r5.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L11;
        TLRPC.PrivacyRule r3 = r5.get(r2);
        r2 = r2 + 1;
        TLRPC.PrivacyRule r32 = r3;
        if (r6.isInstance(r32) == false) goto L6;
        return r6.cast(r32);
    L11:
        return null;
    }

    public static String formatName(String r1, String r2) {
        return formatName(r1, r2, 0);
    }

    public static String formatName(String r6, String r7, int r8) {
        if (r6 == null) goto L5;
        r6 = r6.trim();
    L5:
        if (r6 == null) goto L11;
        if (r7 != null) goto L11;
        if (r8 <= 0) goto L11;
        if (r6.contains(" ") == false) goto L11;
        int r72 = r6.indexOf(" ");
        String r2 = r6.substring(r72 + 1);
        r6 = r6.substring(0, r72);
        r7 = r2;
    L11:
        if (r7 == null) goto L14;
        r7 = r7.trim();
    L14:
        if (r6 == null) goto L16;
        int r3 = r6.length();
    L17:
        if (r7 == null) goto L19;
        int r4 = r7.length();
    L20:
        StringBuilder r22 = new StringBuilder((r3 + r4) + 1);
        if (LocaleController.nameDisplayOrder != 1) goto L49;
        if (r6 != null) goto L24;
    L40:
        if (r7 != null) goto L42;
    L77:
        return r22.toString();
    L42:
        if (r7.length() <= 0) goto L77;
        if (r8 > 0) goto L45;
    L48:
        r22.append(r7);
        goto L77
    L45:
        if (r7.length() <= (r8 + 2)) goto L48;
        return r7.substring(0, r8).concat("…");
    L24:
        if (r6.length() <= 0) goto L40;
        if (r8 > 0) goto L27;
    L30:
        r22.append(r6);
        if (r7 == null) goto L77;
        if (r7.length() <= 0) goto L77;
        r22.append(" ");
        if (r8 > 0) goto L37;
    L39:
        r22.append(r7);
        goto L77
    L37:
        if ((r22.length() + r7.length()) <= r8) goto L39;
        r22.append(r7.charAt(0));
        goto L77
    L27:
        if (r6.length() <= (r8 + 2)) goto L30;
        return r6.substring(0, r8).concat("…");
    L49:
        if (r7 != null) goto L51;
    L67:
        if (r6 == null) goto L77;
        if (r6.length() <= 0) goto L77;
        if (r8 > 0) goto L72;
    L75:
        r22.append(r6);
        goto L77
    L72:
        if (r6.length() <= (r8 + 2)) goto L75;
        return r6.substring(0, r8).concat("…");
    L51:
        if (r7.length() <= 0) goto L67;
        if (r8 > 0) goto L54;
    L57:
        r22.append(r7);
        if (r6 == null) goto L77;
        if (r6.length() <= 0) goto L77;
        r22.append(" ");
        if (r8 > 0) goto L64;
    L66:
        r22.append(r6);
        goto L77
    L64:
        if ((r22.length() + r6.length()) <= r8) goto L66;
        r22.append(r6.charAt(0));
        goto L77
    L54:
        if (r7.length() <= (r8 + 2)) goto L57;
        return r7.substring(0, r8).concat("…");
    L19:
        r4 = 0;
        goto L20
    L16:
        r3 = 0;
        goto L17
    }

    public static String formatName(TLObject r1) {
        if ((r1 instanceof TLRPC.User) == false) goto L7;
        return formatName((TLRPC.User) r1);
    L7:
        if ((r1 instanceof TLRPC.Chat) == false) goto L11;
        return ((TLRPC.Chat) r1).title;
    L11:
        return LocaleController.getString(R.string.HiddenName);
    }

    public static String formatName(TLRPC.User r2) {
        if (r2 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return formatName(r2.first_name, r2.last_name, 0);
    }

    private long getContactsHash(ArrayList<TLRPC.TL_contact> r8) {
        ArrayList r0 = new ArrayList(r8);
        Collections.sort(r0, new ContactsController$$ExternalSyntheticLambda50());
        int r82 = r0.size();
        long r1 = 0;
        int r4 = -1;
    L3:
        if (r4 >= r82) goto L8;
        if (r4 != (-1)) goto L6;
        r1 = MediaDataController.calcHash(r1, getUserConfig().contactsSavedCount);
    L7:
        r4 = r4 + 1;
        goto L3
    L6:
        r1 = MediaDataController.calcHash(r1, ((TLRPC.TL_contact) r0.get(r4)).user_id);
        goto L7
    L8:
        return r1;
    }

    public static ContactsController getInstance(int r3) {
        ContactsController r0 = Instance[r3];
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(ContactsController.class);
        ContactsController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        ContactsController[] r03 = Instance;     // Catch: Throwable -> L9
        ContactsController r2 = new ContactsController(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(ContactsController.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public static Collator getLocaleCollator() {
        if (cachedCollator != null) goto L5;
    L23:
        Locale r0 = Locale.getDefault();     // Catch: Exception -> L8
        cachedCollatorLocale = r0;     // Catch: Exception -> L8
        Collator r02 = Collator.getInstance(r0);     // Catch: Exception -> L8
        cachedCollator = r02;     // Catch: Exception -> L8
        r02.setStrength(1);     // Catch: Exception -> L8
    L11:
        if (cachedCollator != null) goto L17;
        Collator r03 = Collator.getInstance();     // Catch: Exception -> L14
        cachedCollator = r03;     // Catch: Exception -> L14
        r03.setStrength(1);     // Catch: Exception -> L14
    L14:
        e = move-exception;
        FileLog.e(e);
    L17:
        if (cachedCollator != null) goto L20;
        cachedCollator = new AnonymousClass1();
    L20:
        return cachedCollator;
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L11
    L5:
        if (cachedCollatorLocale == Locale.getDefault()) goto L11;
        goto L23
    }

    public static boolean hasContactsPermission() {
        if (ApplicationLoader.applicationContext.checkSelfPermission("android.permission.READ_CONTACTS") != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean hasContactsWritePermission() {
        if (ApplicationLoader.applicationContext.checkSelfPermission("android.permission.WRITE_CONTACTS") != 0) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isNotValidNameString(String r7) {
        if (TextUtils.isEmpty(r7) == false) goto L5;
        return true;
    L5:
        int r6 = r7.length();
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r2 >= r6) goto L14;
        char r4 = r7.charAt(r2);
        if (r4 < '0') goto L12;
        if (r4 > '9') goto L12;
        r3 = r3 + 1;
    L12:
        r2 = r2 + 1;
        goto L6
    L14:
        if (r3 <= 3) goto L16;
        return true;
    L16:
        return false;
    }

    private /* synthetic */ void lambda$addContact$50(TLRPC.User r2) {
        addContactToPhoneBook(r2, true);
    }

    private /* synthetic */ void lambda$addContact$51(TLRPC.Updates r13, TLRPC.User r14) {
        int r1 = 0;
        boolean r2 = false;
    L4:
        if (r1 >= r13.users.size()) goto L37;
        TLRPC.User r3 = r13.users.get(r1);
        if (r3.contact == false) goto L31;
        Contact r5 = this.contactsBookSPhones.get(r3.phone);
        if (r5 == null) goto L31;
        String r6 = r5.getLetter();
        String r7 = Contact.getLetter(r14.first_name, r14.last_name);
        if (r5.user != null) goto L31;
        r5.user = r14;
        if (r6.equals(r7) == true) goto L29;
        ArrayList<Object> r22 = this.phoneBookSectionsDict.get(r7);
        if (r22 != null) goto L16;
        r22 = new ArrayList();
        this.phoneBookSectionsDict.put(r7, r22);
        this.phoneBookSectionsArray.add(r7);
    L16:
        r22.add(r5);
        ArrayList<Object> r23 = this.phoneBookSectionsDict.get(r6);
        if (r23 == null) goto L29;
        int r72 = r23.size();
        int r8 = 0;
    L19:
        if (r8 >= r72) goto L29;
        Object r9 = r23.get(r8);
        r8 = r8 + 1;
        if ((r9 instanceof Contact) == false) goto L19;
        Contact r92 = (Contact) r9;
        if (r92.contact_id != r5.contact_id) goto L19;
        if (r23.remove(r92) == false) goto L29;
        if (r23.isEmpty() == false) goto L29;
        this.phoneBookSectionsDict.remove(r6);
        this.phoneBookSectionsArray.remove(r6);
    L29:
        r2 = true;
    L31:
        if (r3.contact == false) goto L36;
        if (this.contactsDict.get(Long.valueOf(r3.id)) != null) goto L36;
        TLRPC.TL_contact r4 = new TLRPC.TL_contact();
        r4.user_id = r3.id;
        this.contacts.add(r4);
        this.contactsDict.put(Long.valueOf(r4.user_id), r4);
    L36:
        r1 = r1 + 1;
        goto L4
    L37:
        buildContactsSectionsArrays(true);
        if (r2 == false) goto L40;
        mergePhonebookAndTelegramContacts(this.phoneBookSectionsDict, this.phoneBookSectionsArray, this.phoneBookByShortPhones, false);
    L40:
        getNotificationCenter().postNotificationName(NotificationCenter.contactsDidLoad, new Object[0]);
    }

    private /* synthetic */ void lambda$addContact$52(TLRPC.User r7, TLObject r8, TLRPC.TL_error r9) {
        if (r9 == null) goto L4;
        return;
    L4:
        TLRPC.Updates r82 = (TLRPC.Updates) r8;
        TLRPC.UserProfilePhoto r92 = r7.photo;
        if (r92 != null) goto L7;
    L15:
        getMessagesController().processUpdates(r82, false);
        int r93 = 0;
    L17:
        if (r93 >= r82.users.size()) goto L29;
        TLRPC.User r1 = r82.users.get(r93);
        if (r1.id != r7.id) goto L28;
        Utilities.phoneBookQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda19(this, r1));
        TLRPC.TL_contact r2 = new TLRPC.TL_contact();
        r2.user_id = r1.id;
        ArrayList<TLRPC.TL_contact> r3 = new ArrayList();
        r3.add(r2);
        getMessagesStorage().putContacts(r3, false);
        if (TextUtils.isEmpty(r1.phone) == true) goto L28;
        formatName(r1.first_name, r1.last_name);
        getMessagesStorage().applyPhoneBookUpdates(r1.phone, _UrlKt.FRAGMENT_ENCODE_SET);
        Contact r22 = this.contactsBookSPhones.get(r1.phone);
        if (r22 == null) goto L28;
        int r12 = r22.shortPhones.indexOf(r1.phone);
        if (r12 == (-1)) goto L28;
        r22.phoneDeleted.set(r12, 0);
    L28:
        r93 = r93 + 1;
        goto L17
    L29:
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda20(this, r82, r7));
        return;
    L7:
        if (r92.personal == false) goto L15;
        int r94 = 0;
    L10:
        if (r94 >= r82.users.size()) goto L15;
        if (r82.users.get(r94).id != r7.id) goto L14;
        r82.users.get(r94).photo = r7.photo;
    L14:
        r94 = r94 + 1;
        goto L10
    }

    private /* synthetic */ void lambda$applyContactsUpdates$46(Long r3) {
        deleteContactFromPhoneBook(r3.longValue());
    }

    private /* synthetic */ void lambda$applyContactsUpdates$47() {
        loadContacts(false, 0);
    }

    private /* synthetic */ void lambda$applyContactsUpdates$48(ArrayList r10, ArrayList r11) {
        int r1 = 0;
    L4:
        if (r1 >= r10.size()) goto L9;
        TLRPC.TL_contact r2 = (TLRPC.TL_contact) r10.get(r1);
        if (this.contactsDict.get(Long.valueOf(r2.user_id)) != null) goto L8;
        this.contacts.add(r2);
        this.contactsDict.put(Long.valueOf(r2.user_id), r2);
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        int r12 = 0;
    L11:
        if (r12 >= r11.size()) goto L17;
        Long r22 = (Long) r11.get(r12);
        TLRPC.TL_contact r3 = this.contactsDict.get(r22);
        if (r3 == null) goto L15;
        this.contacts.remove(r3);
        this.contactsDict.remove(r22);
    L15:
        r12 = r12 + 1;
        goto L11
    L17:
        if (r10.isEmpty() == true) goto L19;
        updateUnregisteredContacts();
        performWriteContactsToPhoneBook();
    L19:
        performSyncPhoneBook(getContactsCopy(this.contactsBook), false, false, false, false, true, false);
        buildContactsSectionsArrays(!r10.isEmpty());
        getNotificationCenter().postNotificationName(NotificationCenter.contactsDidLoad, new Object[0]);
    }

    private /* synthetic */ int lambda$buildContactsSectionsArrays$43(Collator r4, TLRPC.TL_contact r5, TLRPC.TL_contact r6) {
        TLRPC.User r52 = getMessagesController().getUser(Long.valueOf(r5.user_id));
        TLRPC.User r3 = getMessagesController().getUser(Long.valueOf(r6.user_id));
        return r4.compare(UserObject.getFirstName(r52), UserObject.getFirstName(r3));
    }

    private /* synthetic */ void lambda$checkAppAccount$4() {
        AccountManager r2 = AccountManager.get(ApplicationLoader.applicationContext);
        Account[] r4 = r2.getAccountsByType("org.telegram.messenger");     // Catch: Throwable -> L27
        int r6 = 0;
    L4:
        if (r6 >= r4.length) goto L21;
        Account r7 = r4[r6];     // Catch: Throwable -> L27
        int r8 = 0;
    L8:
        if (r8 >= 16) goto L31;
        TLRPC.User r9 = UserConfig.getInstance(r8).getCurrentUser();     // Catch: Throwable -> L27
        if (r9 == null) goto L17;
        if (r7.name.equals(_UrlKt.FRAGMENT_ENCODE_SET + r9.id) == false) goto L17;
        if (r8 != this.currentAccount) goto L19;
        this.systemAccount = r7;     // Catch: Throwable -> L27
    L19:
        r6 = r6 + 1;
    L17:
        r8 = r8 + 1;
        goto L8
    L31:
        r2.removeAccount(r4[r6], null, null);     // Catch: Exception -> L26 Throwable -> L27
    L21:
        if (getUserConfig().isClientActivated() == false) goto L42;
        readContacts();
        if (this.systemAccount != null) goto L43;
        Account r42 = new Account(_UrlKt.FRAGMENT_ENCODE_SET + getUserConfig().getClientUserId(), "org.telegram.messenger");     // Catch: Exception -> L28
        this.systemAccount = r42;     // Catch: Exception -> L28
        r2.addAccountExplicitly(r42, _UrlKt.FRAGMENT_ENCODE_SET, null);     // Catch: Exception -> L28
        return;
    L44:
        return;
    L43:
        return;
    }

    private /* synthetic */ void lambda$checkContacts$5() {
        if (checkContactsInternal() == true) goto L5;
        return;
    L5:
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.d("detected contacts change");
    L7:
        performSyncPhoneBook(getContactsCopy(this.contactsBook), true, false, true, false, true, false);
    }

    private /* synthetic */ void lambda$checkInviteText$2(TLRPC.TL_help_inviteText r4) {
        this.updatingInviteLink = false;
        SharedPreferences.Editor r0 = MessagesController.getMainSettings(this.currentAccount).edit();
        String r42 = r4.message;
        this.inviteLink = r42;
        r0.putString("invitelink", r42);
        r0.putInt("invitelinktime", (int) (System.currentTimeMillis() / 1000));
        r0.apply();
    }

    private /* synthetic */ void lambda$checkInviteText$3(TLObject r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L7;
        TLRPC.TL_help_inviteText r12 = (TLRPC.TL_help_inviteText) r1;
        if (r12.message.length() == 0) goto L8;
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda24(this, r12));
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$cleanup$1() {
        this.migratingContacts = false;
        this.completedRequestsCount = 0;
    }

    private /* synthetic */ void lambda$deleteAllContacts$8(Runnable r15) {
        AccountManager r2 = AccountManager.get(ApplicationLoader.applicationContext);
        Account[] r5 = r2.getAccountsByType("org.telegram.messenger");     // Catch: Throwable -> L20
        this.systemAccount = null;     // Catch: Throwable -> L20
        int r6 = 0;
    L4:
        if (r6 >= r5.length) goto L22;
        Account r7 = r5[r6];     // Catch: Throwable -> L20
        int r8 = 0;
    L8:
        if (r8 >= 16) goto L16;
        TLRPC.User r9 = UserConfig.getInstance(r8).getCurrentUser();     // Catch: Throwable -> L20
        if (r9 == null) goto L15;
        if (r7.name.equals(_UrlKt.FRAGMENT_ENCODE_SET + r9.id) == false) goto L15;
        r2.removeAccount(r7, null, null);     // Catch: Throwable -> L20
    L15:
        r8 = r8 + 1;
    L16:
        r6 = r6 + 1;
    L22:
        Account r52 = new Account(_UrlKt.FRAGMENT_ENCODE_SET + getUserConfig().getClientUserId(), "org.telegram.messenger");     // Catch: Exception -> L21
        this.systemAccount = r52;     // Catch: Exception -> L21
        r2.addAccountExplicitly(r52, _UrlKt.FRAGMENT_ENCODE_SET, null);     // Catch: Exception -> L21
    L18:
        getMessagesStorage().putCachedPhoneBook(new HashMap(), false, true);
        getMessagesStorage().putContacts(new ArrayList(), true);
        this.phoneBookContacts.clear();
        this.contacts.clear();
        this.contactsDict.clear();
        this.usersSectionsDict.clear();
        this.usersMutualSectionsDict.clear();
        this.sortedUsersSectionsArray.clear();
        this.phoneBookSectionsDict.clear();
        this.phoneBookSectionsArray.clear();
        this.phoneBookByShortPhones.clear();
        this.delayedContactsUpdate.clear();
        this.sortedUsersMutualSectionsArray.clear();
        this.contactsByPhone.clear();
        this.contactsByShortPhone.clear();
        getNotificationCenter().postNotificationName(NotificationCenter.contactsDidLoad, new Object[0]);
        loadContacts(false, 0);
        r15.run();
    }

    private /* synthetic */ void lambda$deleteAllContacts$9(Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        if (r3 != null) goto L5;
        this.contactsBookSPhones.clear();
        this.contactsBook.clear();
        this.completedRequestsCount = 0;
        this.migratingContacts = false;
        this.contactsSyncInProgress = false;
        this.contactsLoaded = false;
        this.loadingContacts = false;
        this.contactsBookLoaded = false;
        this.lastContactsVersions = _UrlKt.FRAGMENT_ENCODE_SET;
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda1(this, r1));
        return;
    L5:
        AndroidUtilities.runOnUIThread(r1);
    }

    private /* synthetic */ void lambda$deleteContact$55(ArrayList r5) {
        int r0 = r5.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Object r2 = r5.get(r1);
        r1 = r1 + 1;
        deleteContactFromPhoneBook(((TLRPC.User) r2).id);
        goto L3
    }

    private /* synthetic */ void lambda$deleteContact$56(ArrayList r10, boolean r11, String r12) {
        int r0 = r10.size();
        boolean r2 = false;
        int r3 = 0;
    L4:
        if (r3 >= r0) goto L8;
        Object r5 = r10.get(r3);
        r3 = r3 + 1;
        TLRPC.User r52 = (TLRPC.User) r5;
        TLRPC.TL_contact r6 = this.contactsDict.get(Long.valueOf(r52.id));
        if (r6 == null) goto L4;
        this.contacts.remove(r6);
        this.contactsDict.remove(Long.valueOf(r52.id));
        r2 = true;
        goto L4
    L8:
        if (r2 == false) goto L10;
        buildContactsSectionsArrays(false);
    L10:
        getNotificationCenter().postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_NAME)});
        getNotificationCenter().postNotificationName(NotificationCenter.contactsDidLoad, new Object[0]);
        if (r11 == false) goto L19;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.formatString("DeletedFromYourContacts", R.string.DeletedFromYourContacts, new Object[]{r12})});
        return;
    }

    private /* synthetic */ void lambda$deleteContact$57(ArrayList r3, ArrayList r4, boolean r5, String r6, TLObject r7, TLRPC.TL_error r8) {
        if (r8 == null) goto L4;
        return;
    L4:
        int r0 = 0;
        getMessagesController().processUpdates((TLRPC.Updates) r7, false);
        getMessagesStorage().deleteContacts(r3);
        Utilities.phoneBookQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda36(this, r4));
    L6:
        if (r0 >= r4.size()) goto L16;
        TLRPC.User r32 = (TLRPC.User) r4.get(r0);
        if (TextUtils.isEmpty(r32.phone) == true) goto L15;
        getMessagesStorage().applyPhoneBookUpdates(r32.phone, _UrlKt.FRAGMENT_ENCODE_SET);
        Contact r72 = this.contactsBookSPhones.get(r32.phone);
        if (r72 == null) goto L15;
        int r33 = r72.shortPhones.indexOf(r32.phone);
        if (r33 == (-1)) goto L15;
        r72.phoneDeleted.set(r33, 1);
    L15:
        r0 = r0 + 1;
        goto L6
    L16:
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda37(this, r4, r5, r6));
    }

    private /* synthetic */ void lambda$deleteContactsUndoable$53(HashMap r5) {
        Iterator r52 = r5.entrySet().iterator();
    L4:
        if (r52.hasNext() == false) goto L6;
        Map.Entry r0 = (Map.Entry) r52.next();
        TLRPC.User r2 = (TLRPC.User) r0.getKey();
        TLRPC.TL_contact r02 = (TLRPC.TL_contact) r0.getValue();
        r2.contact = true;
        this.contacts.add(r02);
        this.contactsDict.put(Long.valueOf(r2.id), r02);
        goto L4
    L6:
        buildContactsSectionsArrays(true);
        getNotificationCenter().postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_NAME)});
        getNotificationCenter().postNotificationName(NotificationCenter.contactsDidLoad, new Object[0]);
    }

    private /* synthetic */ void lambda$deleteContactsUndoable$54(ArrayList r2) {
        deleteContact(r2, false);
    }

    private /* synthetic */ void lambda$forceImportContacts$6() {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("force import contacts");
    L5:
        performSyncPhoneBook(new HashMap(), true, true, true, true, false, false);
    }

    private /* synthetic */ void lambda$loadContacts$27() {
        Object r0 = this.loadContactsSync;
        monitor-enter(r0);
        this.loadingContacts = false;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        getNotificationCenter().postNotificationName(NotificationCenter.contactsDidLoad, new Object[0]);
        return;
    L9:
        th = move-exception;
        throw th;
    }

    private /* synthetic */ void lambda$loadContacts$28(long r3, TLObject r5, TLRPC.TL_error r6) {
        if (r6 != null) goto L18;
        TLRPC.contacts_Contacts r52 = (TLRPC.contacts_Contacts) r5;
        if (r3 != 0) goto L6;
    L16:
        UserConfig r32 = getUserConfig();
        r32.contactsSavedCount = r52.saved_count;
        getUserConfig().saveConfig(false);
        processLoadedContacts(r52.contacts, r52.users, 0);
        return;
    L6:
        if ((r52 instanceof TLRPC.TL_contacts_contactsNotModified) == false) goto L16;
        this.contactsLoaded = true;
        if (this.delayedContactsUpdate.isEmpty() == false) goto L10;
    L12:
        UserConfig r33 = getUserConfig();
        r33.lastContactsSyncTime = (int) (System.currentTimeMillis() / 1000);
        getUserConfig().saveConfig(false);
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda0(this));
        if (BuildVars.LOGS_ENABLED == false) goto L19;
        FileLog.d("load contacts don't change");
        return;
    L19:
        return;
    L10:
        if (this.contactsBookLoaded == false) goto L12;
        applyContactsUpdates(this.delayedContactsUpdate, null, null, null);
        this.delayedContactsUpdate.clear();
        goto L12
    }

    private /* synthetic */ void lambda$loadGlobalPrivacySetting$60(TLRPC.TL_error r2, TLObject r3) {
        if (r2 != null) goto L5;
        this.globalPrivacySettings = (TLRPC.GlobalPrivacySettings) r3;
        this.loadingGlobalSettings = 2;
    L6:
        getNotificationCenter().postNotificationName(NotificationCenter.privacyRulesUpdated, new Object[0]);
        return;
    L5:
        this.loadingGlobalSettings = 0;
        goto L6
    }

    private /* synthetic */ void lambda$loadGlobalPrivacySetting$61(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda30(this, r3, r2));
    }

    private /* synthetic */ void lambda$loadPrivacySettings$62(TLRPC.TL_error r2, TLObject r3) {
        if (r2 != null) goto L5;
        this.deleteAccountTTL = ((TLRPC.TL_accountDaysTTL) r3).days;
        this.loadingDeleteInfo = 2;
    L6:
        getNotificationCenter().postNotificationName(NotificationCenter.privacyRulesUpdated, new Object[0]);
        return;
    L5:
        this.loadingDeleteInfo = 0;
        goto L6
    }

    private /* synthetic */ void lambda$loadPrivacySettings$63(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda62(this, r3, r2));
    }

    private /* synthetic */ void lambda$loadPrivacySettings$64(TLRPC.TL_error r3, TLObject r4, int r5) {
        if (r3 != null) goto L21;
        TL_account.privacyRules r42 = (TL_account.privacyRules) r4;
        getMessagesController().putUsers(r42.users, false);
        getMessagesController().putChats(r42.chats, false);
        switch(r5) {
            case 0: goto L19;
            case 1: goto L18;
            case 2: goto L17;
            case 3: goto L16;
            case 4: goto L15;
            case 5: goto L14;
            case 6: goto L13;
            case 7: goto L6;
            case 8: goto L12;
            case 9: goto L11;
            case 10: goto L6;
            case 11: goto L10;
            case 12: goto L9;
            case 13: goto L8;
            case 14: goto L7;
            default: goto L6;
        };
    L6:
        this.addedByPhonePrivacyRules = r42.rules;
    L20:
        this.loadingPrivacyInfo[r5] = 2;
    L22:
        getNotificationCenter().postNotificationName(NotificationCenter.privacyRulesUpdated, new Object[0]);
        return;
    L7:
        this.musicPrivacyRules = r42.rules;
        goto L20
    L8:
        this.noPaidMessagesPrivacyRules = r42.rules;
        goto L20
    L9:
        this.giftsPrivacyRules = r42.rules;
        goto L20
    L10:
        this.birthdayPrivacyRules = r42.rules;
        goto L20
    L11:
        this.bioPrivacyRules = r42.rules;
        goto L20
    L12:
        this.voiceMessagesRules = r42.rules;
        goto L20
    L13:
        this.phonePrivacyRules = r42.rules;
        goto L20
    L14:
        this.forwardsPrivacyRules = r42.rules;
        goto L20
    L15:
        this.profilePhotoPrivacyRules = r42.rules;
        goto L20
    L16:
        this.p2pPrivacyRules = r42.rules;
        goto L20
    L17:
        this.callPrivacyRules = r42.rules;
        goto L20
    L18:
        this.groupPrivacyRules = r42.rules;
        goto L20
    L19:
        this.lastseenPrivacyRules = r42.rules;
        goto L20
    L21:
        this.loadingPrivacyInfo[r5] = 0;
        goto L22
    }

    private /* synthetic */ void lambda$loadPrivacySettings$65(int r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda14(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$mergePhonebookAndTelegramContacts$40(ArrayList r1, HashMap r2, HashMap r3) {
        this.phoneBookSectionsArray = r1;
        this.phoneBookByShortPhones = r2;
        this.phoneBookSectionsDict = r3;
    }

    private /* synthetic */ void lambda$mergePhonebookAndTelegramContacts$41(boolean r7, ArrayList r8, HashMap r9, HashMap r10, ArrayList r11) {
        if (r7 == false) goto L20;
        int r72 = r8.size();
        int r1 = 0;
    L4:
        if (r1 >= r72) goto L20;
        TLRPC.User r2 = getMessagesController().getUser(Long.valueOf(((TLRPC.TL_contact) r8.get(r1)).user_id));
        if (r2 == null) goto L19;
        if (TextUtils.isEmpty(r2.phone) == true) goto L19;
        Contact r3 = (Contact) r9.get(r2.phone.substring(Math.max(0, r3.length() - 7)));
        if (r3 != null) goto L13;
        String r32 = Contact.getLetter(r2.first_name, r2.last_name);
        ArrayList r4 = (ArrayList) r10.get(r32);
        if (r4 != null) goto L18;
        r4 = new ArrayList();
        r10.put(r32, r4);
        r11.add(r32);
    L18:
        r4.add(r2);
        goto L19
    L13:
        if (r3.user != null) goto L19;
        r3.user = r2;
    L19:
        r1 = r1 + 1;
    L20:
        Collator r73 = getLocaleCollator();
        Iterator r82 = r10.values().iterator();
    L22:
        if (r82.hasNext() == false) goto L24;
        Collections.sort((ArrayList) r82.next(), new ContactsController$$ExternalSyntheticLambda25(r73));
        goto L22
    L24:
        Collections.sort(r11, new ContactsController$$ExternalSyntheticLambda26(r73));
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda27(this, r11, r9, r10));
    }

    private /* synthetic */ void lambda$migratePhoneBookToV7$12(SparseArray r9) {
        if (this.migratingContacts == false) goto L5;
        return;
    L5:
        this.migratingContacts = true;
        HashMap<String, Contact> r1 = new HashMap();
        HashMap<String, Contact> r2 = readContactsFromPhoneBook();
        HashMap r3 = new HashMap();
        Iterator<Map.Entry<String, Contact>> r22 = r2.entrySet().iterator();
    L6:
        int r5 = 0;
        if (r22.hasNext() == false) goto L12;
        Contact r4 = r22.next().getValue();
    L10:
        if (r5 >= r4.shortPhones.size()) goto L6;
        r3.put(r4.shortPhones.get(r5), r4.key);
        r5 = r5 + 1;
        goto L10
    L12:
        int r23 = 0;
    L14:
        if (r23 >= r9.size()) goto L24;
        Contact r42 = (Contact) r9.valueAt(r23);
        int r6 = 0;
    L17:
        if (r6 >= r42.shortPhones.size()) goto L22;
        String r7 = (String) r3.get(r42.shortPhones.get(r6));
        if (r7 != null) goto L20;
        r6 = r6 + 1;
        goto L17
    L20:
        r42.key = r7;
        r1.put(r7, r42);
    L22:
        r23 = r23 + 1;
        goto L14
    L24:
        if (BuildVars.LOGS_ENABLED == false) goto L26;
        FileLog.d("migrated contacts " + r1.size() + " of " + r9.size());
    L26:
        getMessagesStorage().putCachedPhoneBook(r1, true, false);
    }

    private /* synthetic */ void lambda$new$0() {
        if (hasContactsPermission() == false) goto L9;
        ApplicationLoader.applicationContext.getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true, new MyContentObserver(this));     // Catch: Throwable -> L6
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$performSyncPhoneBook$13(HashMap r9) {
        ArrayList<TLRPC.User> r0 = new ArrayList();
        if (r9 == null) goto L33;
        if (r9.isEmpty() == true) goto L33;
        HashMap r2 = new HashMap();     // Catch: Exception -> L15
        int r3 = 0;
    L8:
        if (r3 >= this.contacts.size()) goto L18;
        TLRPC.User r4 = getMessagesController().getUser(Long.valueOf(this.contacts.get(r3).user_id));     // Catch: Exception -> L15
        if (r4 == null) goto L17;
        if (TextUtils.isEmpty(r4.phone) == true) goto L17;
        r2.put(r4.phone, r4);     // Catch: Exception -> L15
    L17:
        r3 = r3 + 1;     // Catch: Exception -> L15
        goto L8
    L18:
        Iterator r92 = r9.entrySet().iterator();     // Catch: Exception -> L15
    L19:
        if (r92.hasNext() == false) goto L33;
        Contact r32 = (Contact) ((Map.Entry) r92.next()).getValue();     // Catch: Exception -> L15
        int r42 = 0;
        boolean r5 = false;
    L23:
        if (r42 >= r32.shortPhones.size()) goto L28;
        TLRPC.User r6 = (TLRPC.User) r2.get(r32.shortPhones.get(r42));     // Catch: Exception -> L15
        if (r6 == null) goto L27;
        r0.add(r6);     // Catch: Exception -> L15
        r32.shortPhones.remove(r42);     // Catch: Exception -> L15
        r42 = r42 - 1;
        r5 = true;
    L27:
        r42 = r42 + 1;     // Catch: Exception -> L15
        goto L23
    L28:
        if (r5 == false) goto L19;
        r32.shortPhones.size();     // Catch: Exception -> L15
    L15:
        e = move-exception;
        FileLog.e(e);
    L33:
        if (r0.isEmpty() == true) goto L50;
        deleteContact(r0, false);
        return;
    }

    private /* synthetic */ void lambda$performSyncPhoneBook$14(int r2, HashMap r3, boolean r4, boolean r5) {
        getNotificationCenter().postNotificationName(NotificationCenter.hasNewContactsToImport, new Object[]{Integer.valueOf(r2), r3, Boolean.valueOf(r4), Boolean.valueOf(r5)});
    }

    private /* synthetic */ void lambda$performSyncPhoneBook$15(HashMap r2, ArrayList r3, HashMap r4) {
        mergePhonebookAndTelegramContacts(r2, r3, r4);
        updateUnregisteredContacts();
        getNotificationCenter().postNotificationName(NotificationCenter.contactsDidLoad, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.contactsImported, new Object[0]);
    }

    private /* synthetic */ void lambda$performSyncPhoneBook$16(HashMap r2, HashMap r3, boolean r4, HashMap r5, ArrayList r6, HashMap r7) {
        this.contactsBookSPhones = r2;
        this.contactsBook = r3;
        this.contactsSyncInProgress = false;
        this.contactsBookLoaded = true;
        if (r4 == false) goto L6;
        this.contactsLoaded = true;
    L6:
        if (this.delayedContactsUpdate.isEmpty() == false) goto L8;
    L10:
        getMessagesStorage().putCachedPhoneBook(r3, false, false);
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda21(this, r5, r6, r7));
        return;
    L8:
        if (this.contactsLoaded == false) goto L10;
        applyContactsUpdates(this.delayedContactsUpdate, null, null, null);
        this.delayedContactsUpdate.clear();
        goto L10
    }

    private /* synthetic */ void lambda$performSyncPhoneBook$17(HashMap r1, ArrayList r2, HashMap r3) {
        mergePhonebookAndTelegramContacts(r1, r2, r3);
        getNotificationCenter().postNotificationName(NotificationCenter.contactsImported, new Object[0]);
    }

    private /* synthetic */ void lambda$performSyncPhoneBook$18() {
        getMessagesStorage().getCachedPhoneBook(true);
    }

    private /* synthetic */ void lambda$performSyncPhoneBook$19(HashMap r1, HashMap r2, boolean r3, HashMap r4, ArrayList r5, HashMap r6, boolean[] r7) {
        this.contactsBookSPhones = r1;
        this.contactsBook = r2;
        this.contactsSyncInProgress = false;
        this.contactsBookLoaded = true;
        if (r3 == false) goto L6;
        this.contactsLoaded = true;
    L6:
        if (this.delayedContactsUpdate.isEmpty() == false) goto L8;
    L10:
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda32(this, r4, r5, r6));
        if (r7[0] == false) goto L14;
        Utilities.globalQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda33(this), 300000);
        return;
    L14:
        return;
    L8:
        if (this.contactsLoaded == false) goto L10;
        applyContactsUpdates(this.delayedContactsUpdate, null, null, null);
        this.delayedContactsUpdate.clear();
        goto L10
    }

    private /* synthetic */ void lambda$performSyncPhoneBook$20(HashMap r13, SparseArray r14, boolean[] r15, HashMap r16, TLRPC.TL_contacts_importContacts r17, int r18, HashMap r19, boolean r20, HashMap r21, ArrayList r22, HashMap r23, TLObject r24, TLRPC.TL_error r25) {
        this.completedRequestsCount++;
        if (r25 == null) goto L5;
        HashMap r9 = r16;
        int r7 = 0;
    L30:
        if (r7 >= r17.contacts.size()) goto L32;
        r13.remove(r14.get((int) r17.contacts.get(r7).client_id));
        r7 = r7 + 1;
        goto L30
    L32:
        r15[0] = true;
        if (BuildVars.LOGS_ENABLED == false) goto L36;
        FileLog.d("import contacts error " + r25.text);
    L36:
        if (this.completedRequestsCount == r18) goto L38;
        return;
    L38:
        if (r13.isEmpty() == true) goto L40;
        getMessagesStorage().putCachedPhoneBook(r13, false, false);
    L40:
        Utilities.stageQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda55(this, r19, r9, r20, r21, r22, r23, r15));
        return;
    L5:
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.d("contacts imported");
    L7:
        TLRPC.TL_contacts_importedContacts r3 = (TLRPC.TL_contacts_importedContacts) r24;
        if (r3.retry_contacts.isEmpty() == true) goto L16;
        int r4 = 0;
    L11:
        if (r4 >= r3.retry_contacts.size()) goto L13;
        r13.remove(r14.get((int) r3.retry_contacts.get(r4).longValue()));
        r4 = r4 + 1;
        goto L11
    L13:
        r15[0] = true;
        if (BuildVars.LOGS_ENABLED == false) goto L16;
        FileLog.d("result has retry contacts");
    L16:
        int r42 = 0;
    L18:
        if (r42 >= r3.popular_invites.size()) goto L23;
        TLRPC.TL_popularContact r72 = r3.popular_invites.get(r42);
        Contact r8 = (Contact) r16.get(r14.get((int) r72.client_id));
        if (r8 == null) goto L22;
        r8.imported = r72.importers;
    L22:
        r42 = r42 + 1;
        goto L18
    L23:
        r9 = r16;
        getMessagesStorage().putUsersAndChats(r3.users, null, true, true);
        ArrayList<TLRPC.TL_contact> r2 = new ArrayList();
        int r43 = 0;
    L25:
        if (r43 >= r3.imported.size()) goto L27;
        TLRPC.TL_contact r6 = new TLRPC.TL_contact();
        r6.user_id = r3.imported.get(r43).user_id;
        r2.add(r6);
        r43 = r43 + 1;
        goto L25
    L27:
        processLoadedContacts(r2, r3.users, 2);
        goto L36
    }

    private /* synthetic */ void lambda$performSyncPhoneBook$21(HashMap r2, ArrayList r3, HashMap r4) {
        mergePhonebookAndTelegramContacts(r2, r3, r4);
        updateUnregisteredContacts();
        getNotificationCenter().postNotificationName(NotificationCenter.contactsDidLoad, new Object[0]);
        getNotificationCenter().postNotificationName(NotificationCenter.contactsImported, new Object[0]);
    }

    private /* synthetic */ void lambda$performSyncPhoneBook$22(HashMap r1, HashMap r2, boolean r3, HashMap r4, ArrayList r5, HashMap r6) {
        this.contactsBookSPhones = r1;
        this.contactsBook = r2;
        this.contactsSyncInProgress = false;
        this.contactsBookLoaded = true;
        if (r3 == false) goto L6;
        this.contactsLoaded = true;
    L6:
        if (this.delayedContactsUpdate.isEmpty() == false) goto L8;
    L10:
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda31(this, r4, r5, r6));
        return;
    L8:
        if (this.contactsLoaded == false) goto L10;
        applyContactsUpdates(this.delayedContactsUpdate, null, null, null);
        this.delayedContactsUpdate.clear();
        goto L10
    }

    private /* synthetic */ void lambda$performSyncPhoneBook$23(HashMap r1, ArrayList r2, HashMap r3) {
        mergePhonebookAndTelegramContacts(r1, r2, r3);
    }

    private /* synthetic */ void lambda$performSyncPhoneBook$24(HashMap r1, HashMap r2, boolean r3, HashMap r4, ArrayList r5, HashMap r6) {
        this.contactsBookSPhones = r1;
        this.contactsBook = r2;
        this.contactsSyncInProgress = false;
        this.contactsBookLoaded = true;
        if (r3 == false) goto L6;
        this.contactsLoaded = true;
    L6:
        if (this.delayedContactsUpdate.isEmpty() == false) goto L8;
    L10:
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda34(this, r4, r5, r6));
        return;
    L8:
        if (this.contactsLoaded == false) goto L10;
        applyContactsUpdates(this.delayedContactsUpdate, null, null, null);
        this.delayedContactsUpdate.clear();
        goto L10
    }

    private /* synthetic */ void lambda$performSyncPhoneBook$25(HashMap r31, boolean r32, boolean r33, boolean r34, boolean r35, boolean r36, boolean r37) {
        HashMap r0 = new HashMap();
        Iterator r2 = r31.entrySet().iterator();
    L3:
        int r8 = 0;
        if (r2.hasNext() == false) goto L10;
        Contact r4 = (Contact) ((Map.Entry) r2.next()).getValue();
    L7:
        if (r8 >= r4.shortPhones.size()) goto L3;
        r0.put(r4.shortPhones.get(r8), r4);
        r8 = r8 + 1;
        goto L7
    L10:
        if (BuildVars.LOGS_ENABLED == false) goto L12;
        FileLog.d("start read contacts from phone");
    L12:
        if (r32 == true) goto L14;
        checkContactsInternal();
    L14:
        HashMap<String, Contact> r5 = readContactsFromPhoneBook();
        HashMap r10 = new HashMap();
        HashMap r7 = new HashMap();
        ArrayList r6 = new ArrayList();
        Iterator<Map.Entry<String, Contact>> r22 = r5.entrySet().iterator();
    L16:
        if (r22.hasNext() == false) goto L24;
        Contact r42 = r22.next().getValue();
        int r9 = r42.shortPhones.size();
        int r11 = 0;
    L18:
        if (r11 >= r9) goto L20;
        r7.put(r42.shortPhones.get(r11).substring(Math.max(0, r12.length() - 7)), r42);
        r11 = r11 + 1;
        goto L18
    L20:
        String r92 = r42.getLetter();
        ArrayList r112 = (ArrayList) r10.get(r92);
        if (r112 != null) goto L23;
        r112 = new ArrayList();
        r10.put(r92, r112);
        r6.add(r92);
    L23:
        r112.add(r42);
        goto L16
    L24:
        HashMap r23 = new HashMap();
        int r43 = r31.size();
        ArrayList r13 = new ArrayList();
        if (r31.isEmpty() == true) goto L162;
        Iterator<Map.Entry<String, Contact>> r93 = r5.entrySet().iterator();
        int r15 = 0;
        int r16 = 0;
    L28:
        if (r93.hasNext() == false) goto L139;
        Map.Entry<String, Contact> r17 = r93.next();
        String r113 = r17.getKey();
        Contact r14 = r17.getValue();
        Contact r172 = (Contact) r31.get(r113);
        ArrayList r21 = r6;
        if (r172 == null) goto L32;
    L37:
        Contact r62 = r172;
    L38:
        if (r62 == null) goto L40;
        r14.imported = r62.imported;
    L40:
        if (r62 != null) goto L42;
        HashMap r173 = r0;
    L53:
        boolean r02 = false;
    L54:
        if (r62 == null) goto L56;
        if (r02 == true) goto L56;
        int r03 = 0;
    L59:
        if (r03 >= r14.phones.size()) goto L114;
        String r82 = r14.shortPhones.get(r03);
        HashMap r232 = r7;
        Iterator<Map.Entry<String, Contact>> r222 = r93;
        String r72 = r82.substring(Math.max(0, r82.length() - 7));
        r23.put(r82, r14);
        int r94 = r62.shortPhones.indexOf(r82);
        if (r33 == false) goto L82;
        int r24 = r94;
        TLRPC.TL_contact r95 = this.contactsByPhone.get(r82);
        if (r95 == null) goto L78;
        HashMap r25 = r10;
        int r27 = r43;
        HashMap<String, Contact> r26 = r5;
        TLRPC.User r44 = getMessagesController().getUser(Long.valueOf(r95.user_id));
        if (r44 == null) goto L75;
        r16 = r16 + 1;
        if (TextUtils.isEmpty(r44.first_name) == false) goto L75;
        if (TextUtils.isEmpty(r44.last_name) == false) goto L75;
        if (TextUtils.isEmpty(r14.first_name) == true) goto L73;
    L74:
        boolean r45 = true;
        r24 = -1;
    L76:
        int r96 = r24;
    L83:
        if (r96 != (-1)) goto L112;
        if (r33 == false) goto L113;
        if (r45 == true) goto L111;
        TLRPC.TL_contact r46 = this.contactsByPhone.get(r82);
        if (r46 == null) goto L109;
        TLRPC.User r47 = getMessagesController().getUser(Long.valueOf(r46.user_id));
        if (r47 == null) goto L107;
        r16 = r16 + 1;
        String r52 = r47.first_name;
        if (r52 != null) goto L94;
        r52 = _UrlKt.FRAGMENT_ENCODE_SET;
    L94:
        String r48 = r47.last_name;
        if (r48 != null) goto L99;
        r48 = _UrlKt.FRAGMENT_ENCODE_SET;
    L99:
        if (r52.equals(r14.first_name) == false) goto L103;
        if (r48.equals(r14.last_name) == true) goto L113;
    L103:
        if (TextUtils.isEmpty(r14.first_name) == false) goto L111;
        if (TextUtils.isEmpty(r14.last_name) == false) goto L111;
    L107:
        r15 = r15 + 1;
        goto L111
    L109:
        if (this.contactsByShortPhone.containsKey(r72) == false) goto L111;
        r16 = r16 + 1;
    L111:
        TLRPC.TL_inputPhoneContact r49 = new TLRPC.TL_inputPhoneContact();
        r49.client_id = r14.contact_id | (r03 << 32);
        r49.first_name = r14.first_name;
        r49.last_name = r14.last_name;
        r49.phone = r14.phones.get(r03);
        r13.add(r49);
    L113:
        r03 = r03 + 1;
        r93 = r222;
        r7 = r232;
        r10 = r25;
        r5 = r26;
        r43 = r27;
        goto L59
    L112:
        r14.phoneDeleted.set(r03, r62.phoneDeleted.get(r96));
        r62.phones.remove(r96);
        r62.shortPhones.remove(r96);
        r62.phoneDeleted.remove(r96);
        r62.phoneTypes.remove(r96);
        goto L113
    L73:
        if (TextUtils.isEmpty(r14.last_name) == false) goto L74;
    L75:
        r45 = false;
        goto L76
    L78:
        r27 = r43;
        r26 = r5;
        r25 = r10;
        if (this.contactsByShortPhone.containsKey(r72) == false) goto L81;
        r16 = r16 + 1;
    L81:
        r96 = r24;
        r45 = false;
        goto L83
    L82:
        r27 = r43;
        r26 = r5;
        r24 = r94;
        r25 = r10;
        goto L81
    L114:
        int r272 = r43;
        HashMap<String, Contact> r262 = r5;
        HashMap r233 = r7;
        Iterator<Map.Entry<String, Contact>> r223 = r93;
        HashMap r252 = r10;
        if (r62.phones.isEmpty() == false) goto L138;
        r31.remove(r113);
    L138:
        r0 = r173;
        r6 = r21;
        r93 = r223;
        r7 = r233;
        r10 = r252;
        r5 = r262;
        r43 = r272;
        r8 = 0;
    L56:
        r272 = r43;
        r262 = r5;
        r233 = r7;
        r223 = r93;
        r252 = r10;
        int r410 = 0;
    L119:
        if (r410 >= r14.phones.size()) goto L136;
        String r53 = r14.shortPhones.get(r410);
        r53.substring(Math.max(0, r53.length() - 7));
        r23.put(r53, r14);
        if (r62 == null) goto L128;
        int r73 = r62.shortPhones.indexOf(r53);
        if (r73 == (-1)) goto L128;
        Integer r74 = r62.phoneDeleted.get(r73);
        r14.phoneDeleted.set(r410, r74);
        if (r74.intValue() != 1) goto L128;
    L135:
        r410 = r410 + 1;
    L128:
        if (r33 == false) goto L135;
        if (r02 == false) goto L131;
    L134:
        TLRPC.TL_inputPhoneContact r54 = new TLRPC.TL_inputPhoneContact();
        r54.client_id = r14.contact_id | (r410 << 32);
        r54.first_name = r14.first_name;
        r54.last_name = r14.last_name;
        r54.phone = r14.phones.get(r410);
        r13.add(r54);
        goto L135
    L131:
        if (this.contactsByPhone.containsKey(r53) == false) goto L133;
        r16 = r16 + 1;
        goto L135
    L133:
        r15 = r15 + 1;
        goto L134
    L136:
        if (r62 == null) goto L138;
        r31.remove(r113);
        goto L138
    L42:
        if (TextUtils.isEmpty(r14.first_name) == true) goto L46;
        r173 = r0;
        if (r62.first_name.equals(r14.first_name) == true) goto L48;
    L51:
        r02 = true;
    L48:
        if (TextUtils.isEmpty(r14.last_name) == true) goto L53;
        if (r62.last_name.equals(r14.last_name) == true) goto L53;
    L46:
        r173 = r0;
    L32:
        if (r8 >= r14.shortPhones.size()) goto L37;
        r62 = (Contact) r0.get(r14.shortPhones.get(r8));
        if (r62 != null) goto L35;
        r8 = r8 + 1;
        goto L32
    L35:
        r113 = r62.key;
        goto L38
    L139:
        int r273 = r43;
        HashMap<String, Contact> r263 = r5;
        ArrayList r212 = r6;
        HashMap r234 = r7;
        HashMap r253 = r10;
        if (r34 == false) goto L142;
    L151:
        r43 = r273;
    L152:
        if (r33 == true) goto L154;
    L160:
        r5 = r263;
    L161:
        int r04 = r16;
    L201:
        if (BuildVars.LOGS_ENABLED == false) goto L203;
        FileLog.d("done processing contacts");
    L203:
        if (r33 == true) goto L205;
        HashMap<String, Contact> r3 = r5;
        Utilities.stageQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda13(this, r23, r3, r34, r253, r212, r234));
        if (r3.isEmpty() == true) goto L274;
        getMessagesStorage().putCachedPhoneBook(r3, false, false);
        return;
    L274:
        return;
    L205:
        if (r13.isEmpty() == false) goto L207;
        Utilities.stageQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda12(this, r23, r5, r34, r253, r212, r234));
        return;
    L207:
        if (BuildVars.LOGS_ENABLED == false) goto L209;
        FileLog.e("start import contacts");
    L209:
        if (r36 == false) goto L219;
        if (r15 == 0) goto L219;
        if (r15 < 30) goto L214;
        HashMap r83 = r23;
        int r28 = 1;
    L221:
        if (BuildVars.LOGS_ENABLED == false) goto L223;
        FileLog.d("new phone book contacts " + r15 + " serverContactsInPhonebook " + r04 + " totalContacts " + this.contactsByPhone.size());
    L223:
        if (r28 == 0) goto L226;
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda9(this, r28, r31, r34, r32));
        return;
    L226:
        if (r37 == false) goto L229;
        Utilities.stageQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda10(this, r83, r5, r34, r253, r212, r234));
        return;
    L229:
        ContactsController r1 = this;
        HashMap<String, Contact> r38 = r5;
        ArrayList r63 = r212;
        HashMap r75 = r234;
        HashMap r55 = r253;
        boolean[] r411 = {false};
        HashMap r29 = new HashMap(r38);
        HashMap<String, Contact> r264 = r38;
        SparseArray r39 = new SparseArray();
        Iterator r05 = r29.entrySet().iterator();
    L231:
        if (r05.hasNext() == false) goto L233;
        Contact r97 = (Contact) ((Map.Entry) r05.next()).getValue();
        r39.put(r97.contact_id, r97.key);
        goto L231
    L233:
        r1.completedRequestsCount = 0;
        int r06 = (int) Math.ceil(r13.size() / 500.0d);
        int r142 = 0;
    L234:
        if (r142 >= r06) goto L275;
        ArrayList r114 = r63;
        TLRPC.TL_contacts_importContacts r64 = new TLRPC.TL_contacts_importContacts();
        int r98 = r142 * MediaError.DetailedErrorCode.SEGMENT_UNKNOWN;
        r64.contacts = new ArrayList(r13.subList(r98, Math.min(r98 + MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, r13.size())));
        ConnectionsManager r152 = r1.getConnectionsManager();
        HashMap r12 = r75;
        int r76 = r06;
        HashMap r102 = r55;
        HashMap<String, Contact> r56 = r264;
        ContactsController$$ExternalSyntheticLambda11 r07 = new ContactsController$$ExternalSyntheticLambda11(r1, r29, r39, r411, r56, r64, r76, r83, r34, r102, r114, r12);
        r55 = r102;
        r63 = r114;
        r75 = r12;
        r152.sendRequest(r64, r07, 6);
        r142 = r142 + 1;
        r29 = r29;
        r06 = r76;
        r83 = r83;
        r264 = r56;
        r39 = r39;
        r1 = this;
        goto L234
    L275:
        return;
    L214:
        if (r34 == false) goto L219;
        if (r43 != 0) goto L219;
        if ((this.contactsByPhone.size() - r04) <= ((this.contactsByPhone.size() / 3) * 2)) goto L219;
        r83 = r23;
        r28 = 2;
    L219:
        r83 = r23;
        r28 = 0;
        goto L221
    L154:
        if (r31.isEmpty() == true) goto L160;
        if (r263.isEmpty() == true) goto L160;
        if (r13.isEmpty() == false) goto L160;
        r5 = r263;
        getMessagesStorage().putCachedPhoneBook(r5, false, false);
        goto L161
    L142:
        if (r31.isEmpty() == false) goto L151;
        if (r13.isEmpty() == false) goto L151;
        r43 = r273;
        if (r43 != r263.size()) goto L152;
        if (BuildVars.LOGS_ENABLED == false) goto L273;
        FileLog.d("contacts not changed!");
        return;
    L273:
        return;
    L162:
        r212 = r6;
        r234 = r7;
        r253 = r10;
        if (r33 == false) goto L199;
        Iterator<Map.Entry<String, Contact>> r08 = r5.entrySet().iterator();
        int r162 = 0;
    L166:
        if (r08.hasNext() == false) goto L197;
        Map.Entry<String, Contact> r65 = r08.next();
        Contact r77 = r65.getValue();
        String r66 = r65.getKey();
        int r67 = 0;
    L169:
        if (r67 >= r77.phones.size()) goto L166;
        if (r35 == true) goto L195;
        String r84 = r77.shortPhones.get(r67);
        String r99 = r84.substring(Math.max(0, r84.length() - 7));
        TLRPC.TL_contact r85 = this.contactsByPhone.get(r84);
        if (r85 == null) goto L193;
        TLRPC.User r86 = getMessagesController().getUser(Long.valueOf(r85.user_id));
        if (r86 == null) goto L195;
        r162 = r162 + 1;
        String r910 = r86.first_name;
        if (r910 != null) goto L179;
        r910 = _UrlKt.FRAGMENT_ENCODE_SET;
    L179:
        String r87 = r86.last_name;
        if (r87 != null) goto L184;
        r87 = _UrlKt.FRAGMENT_ENCODE_SET;
    L184:
        if (r910.equals(r77.first_name) == false) goto L188;
        if (r87.equals(r77.last_name) == false) goto L188;
    L196:
        r67 = r67 + 1;
    L188:
        if (TextUtils.isEmpty(r77.first_name) == false) goto L195;
        if (TextUtils.isEmpty(r77.last_name) == false) goto L195;
    L193:
        if (this.contactsByShortPhone.containsKey(r99) == false) goto L195;
        r162 = r162 + 1;
    L195:
        TLRPC.TL_inputPhoneContact r88 = new TLRPC.TL_inputPhoneContact();
        r88.client_id = r77.contact_id | (r67 << 32);
        r88.first_name = r77.first_name;
        r88.last_name = r77.last_name;
        r88.phone = r77.phones.get(r67);
        r13.add(r88);
        goto L196
    L197:
        r04 = r162;
    L198:
        r15 = 0;
        goto L201
    L199:
        r04 = 0;
        goto L198
    }

    private /* synthetic */ void lambda$performWriteContactsToPhoneBook$45(ArrayList r1) {
        performWriteContactsToPhoneBookInternal(r1);
    }

    private /* synthetic */ void lambda$processLoadedContacts$29() {
        this.doneLoadingContacts = true;
        getNotificationCenter().postNotificationName(NotificationCenter.contactsDidLoad, new Object[0]);
    }

    private /* synthetic */ void lambda$processLoadedContacts$33(ArrayList r1, ConcurrentHashMap r2, HashMap r3, HashMap r4, ArrayList r5, ArrayList r6, int r7, boolean r8, boolean r9) {
        this.contacts = r1;
        this.contactsDict = r2;
        this.usersSectionsDict = r3;
        this.usersMutualSectionsDict = r4;
        this.sortedUsersSectionsArray = r5;
        this.sortedUsersMutualSectionsArray = r6;
        this.doneLoadingContacts = true;
        if (r7 == 2) goto L12;
        Object r22 = this.loadContactsSync;
        monitor-enter(r22);
        this.loadingContacts = false;     // Catch: Throwable -> L9
        monitor-exit(r22);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    L12:
        performWriteContactsToPhoneBook();
        updateUnregisteredContacts();
        getNotificationCenter().postNotificationName(NotificationCenter.contactsDidLoad, new Object[0]);
        if (r7 == 1) goto L16;
        if (r8 == true) goto L16;
        saveContactsLoadTime();
    L17:
        if (r9 == false) goto L22;
        loadContacts(false, 0);
        return;
    L22:
        return;
    L16:
        reloadContactsStatusesMaybe(false);
        goto L17
    }

    private /* synthetic */ void lambda$processLoadedContacts$34(HashMap r1, HashMap r2) {
        this.contactsByPhone = r1;
        this.contactsByShortPhone = r2;
    }

    private /* synthetic */ void lambda$processLoadedContacts$35(HashMap r3, HashMap r4) {
        Utilities.globalQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda56(this, r3, r4));
        if (this.contactsSyncInProgress == false) goto L5;
        return;
    L5:
        this.contactsSyncInProgress = true;
        getMessagesStorage().getCachedPhoneBook(false);
    }

    private /* synthetic */ void lambda$processLoadedContacts$36(int r20, ArrayList r21, ArrayList r22, LongSparseArray r23, boolean r24) {
        ArrayList r2 = r21;
        LongSparseArray r0 = r23;
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        FileLog.d("done loading contacts");
    L6:
        if (r20 == 1) goto L8;
    L15:
        if (r20 != 0) goto L17;
        getUserConfig().lastContactsSyncTime = (int) (System.currentTimeMillis() / 1000);
        getUserConfig().saveConfig(false);
    L17:
        int r3 = 0;
        boolean r10 = false;
    L19:
        if (r3 >= r2.size()) goto L27;
        TLRPC.TL_contact r4 = r2.get(r3);
        if (MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r4.user_id)) != null) goto L25;
        if (r4.user_id == getUserConfig().getClientUserId()) goto L25;
        r2.remove(r3);
        r3 = r3 - 1;
        r10 = true;
    L25:
        r3 = r3 + 1;
        goto L19
    L27:
        if (r20 == 1) goto L33;
        getMessagesStorage().putUsersAndChats(r22, null, true, true);
        MessagesStorage r42 = getMessagesStorage();
        if (r20 == 2) goto L31;
        boolean r6 = true;
    L32:
        r42.putContacts(r2, r6);
        goto L33
    L31:
        r6 = false;
    L33:
        Collator r43 = getLocaleCollator();
        Collections.sort(r2, new ContactsController$$ExternalSyntheticLambda40(r0, r43));
        ConcurrentHashMap r62 = new ConcurrentHashMap(20, 1.0f, 2);
        HashMap r32 = new HashMap();
        HashMap r7 = new HashMap();
        ArrayList r9 = new ArrayList();
        ArrayList r13 = new ArrayList();
        if (this.contactsBookLoaded == true) goto L36;
        HashMap r14 = new HashMap();
        HashMap r15 = new HashMap();
    L37:
        int r12 = 0;
    L39:
        if (r12 >= r2.size()) goto L71;
        TLRPC.TL_contact r11 = r2.get(r12);
        ConcurrentHashMap r222 = r62;
        TLRPC.User r5 = (TLRPC.User) r0.get(r11.user_id);
        if (r5 != null) goto L43;
        int r16 = r12;
        ArrayList r63 = r13;
        int r25 = 0;
        ConcurrentHashMap r132 = r222;
    L70:
        r12 = r16 + 1;
        ConcurrentHashMap r02 = r132;
        r13 = r63;
        r62 = r02;
        r0 = r23;
        r2 = r21;
        goto L39
    L43:
        r16 = r12;
        ArrayList r64 = r13;
        r132 = r222;
        r132.put(Long.valueOf(r11.user_id), r11);
        if (r14 != null) goto L46;
    L48:
        r25 = 0;
    L49:
        String r03 = UserObject.getFirstName(r5);
        if (r03.length() <= 1) goto L53;
        r03 = r03.substring(r25, 1);
    L53:
        if (r03.length() != 0) goto L55;
        String r04 = "#";
    L56:
        String r65 = this.sectionsToReplace.get(r04);
        if (r65 == null) goto L59;
        r04 = r65;
    L59:
        ArrayList r66 = (ArrayList) r32.get(r04);
        if (r66 != null) goto L62;
        r66 = new ArrayList();
        r32.put(r04, r66);
        r9.add(r04);
    L62:
        r66.add(r11);
        if (r5.mutual_contact == false) goto L69;
        ArrayList r52 = (ArrayList) r7.get(r04);
        if (r52 != null) goto L67;
        r52 = new ArrayList();
        r7.put(r04, r52);
        r63 = r64;
        r63.add(r04);
    L68:
        r52.add(r11);
        goto L70
    L67:
        r63 = r64;
        goto L68
    L69:
        r63 = r64;
        goto L70
    L55:
        r04 = r03.toUpperCase();
        goto L56
    L46:
        if (TextUtils.isEmpty(r5.phone) == true) goto L48;
        r14.put(r5.phone, r11);
        r25 = 0;
        r15.put(r5.phone.substring(Math.max(0, r12.length() - 7)), r11);
        goto L49
    L71:
        ArrayList r18 = r13;
        Collections.sort(r9, new ContactsController$$ExternalSyntheticLambda41(r43));
        Collections.sort(r18, new ContactsController$$ExternalSyntheticLambda42(r43));
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda43(this, r21, r62, r32, r7, r9, r18, r20, r24, r10));
        if (this.delayedContactsUpdate.isEmpty() == false) goto L74;
    L78:
        if (r14 == null) goto L81;
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda44(this, r14, r15));
        return;
    L81:
        this.contactsLoaded = true;
        return;
    L74:
        if (this.contactsLoaded == false) goto L78;
        if (this.contactsBookLoaded == false) goto L78;
        applyContactsUpdates(this.delayedContactsUpdate, null, null, null);
        this.delayedContactsUpdate.clear();
        goto L78
    L36:
        r14 = null;
        r15 = null;
        goto L37
    L8:
        if (r2.isEmpty() == false) goto L10;
    L11:
        loadContacts(false, getContactsHash(r2));
        if (r2.isEmpty() == false) goto L15;
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda39(this));
        return;
    L10:
        if (Math.abs((System.currentTimeMillis() / 1000) - getUserConfig().lastContactsSyncTime) < 86400) goto L15;
        goto L11
    }

    private /* synthetic */ void lambda$processLoadedContacts$37(ArrayList r12, int r13, ArrayList r14) {
        MessagesController r0 = getMessagesController();
        int r1 = 0;
        if (r13 != 1) goto L5;
        boolean r3 = true;
    L6:
        r0.putUsers(r12, r3);
        LongSparseArray r9 = new LongSparseArray();
        boolean r10 = r14.isEmpty();
        if (r13 != 2) goto L19;
        if (this.contacts.isEmpty() == true) goto L19;
        int r02 = 0;
    L12:
        if (r02 >= r14.size()) goto L17;
        if (this.contactsDict.get(Long.valueOf(((TLRPC.TL_contact) r14.get(r02)).user_id)) == null) goto L16;
        r14.remove(r02);
        r02 = r02 - 1;
    L16:
        r02 = r02 + 1;
        goto L12
    L17:
        r14.addAll(this.contacts);
    L19:
        if (r1 >= r14.size()) goto L24;
        TLRPC.User r03 = getMessagesController().getUser(Long.valueOf(((TLRPC.TL_contact) r14.get(r1)).user_id));
        if (r03 == null) goto L23;
        r9.put(r03.id, r03);
    L23:
        r1 = r1 + 1;
        goto L19
    L24:
        Utilities.stageQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda52(this, r13, r14, r12, r9, r10));
        return;
    L5:
        r3 = false;
        goto L6
    }

    private /* synthetic */ void lambda$readContacts$11() {
        if (this.contacts.isEmpty() == true) goto L5;
    L9:
        Object r0 = this.loadContactsSync;
        monitor-enter(r0);
        this.loadingContacts = false;     // Catch: Throwable -> L15
        monitor-exit(r0);     // Catch: Throwable -> L15
        return;
    L15:
        th = move-exception;
        throw th;
    L5:
        if (this.contactsLoaded == true) goto L9;
        loadContacts(true, 0);
    }

    private /* synthetic */ void lambda$reloadContactsStatuses$58(SharedPreferences.Editor r8, TLObject r9) {
        r8.remove("needGetStatuses").commit();
        Vector r92 = (Vector) r9;
        if (r92.objects.isEmpty() == true) goto L35;
        ArrayList<TLRPC.User> r82 = new ArrayList();
        ArrayList<T> r93 = r92.objects;
        int r0 = r93.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L34;
        Object r2 = r93.get(r1);
        r1 = r1 + 1;
        TLRPC.TL_user r3 = new TLRPC.TL_user();
        TLRPC.TL_contactStatus r22 = (TLRPC.TL_contactStatus) r2;
        if (r22 == null) goto L5;
        TLRPC.UserStatus r4 = r22.status;
        if ((r4 instanceof TLRPC.TL_userStatusRecently) == false) goto L17;
        if (r4.by_me == false) goto L14;
        int r5 = -1000;
    L15:
        r4.expires = r5;
    L30:
        TLRPC.User r42 = getMessagesController().getUser(Long.valueOf(r22.user_id));
        if (r42 == null) goto L33;
        r42.status = r22.status;
    L33:
        r3.status = r22.status;
        r82.add(r3);
        goto L5
    L14:
        r5 = -100;
        goto L15
    L17:
        if ((r4 instanceof TLRPC.TL_userStatusLastWeek) == false) goto L24;
        if (r4.by_me == false) goto L21;
        int r52 = -1001;
    L22:
        r4.expires = r52;
        goto L30
    L21:
        r52 = -101;
        goto L22
    L24:
        if ((r4 instanceof TLRPC.TL_userStatusLastMonth) == false) goto L30;
        if (r4.by_me == false) goto L28;
        int r53 = -1002;
    L29:
        r4.expires = r53;
        goto L30
    L28:
        r53 = -102;
        goto L29
    L34:
        getMessagesStorage().updateUsers(r82, true, true, true);
    L35:
        getNotificationCenter().postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_STATUS)});
    }

    private /* synthetic */ void lambda$reloadContactsStatuses$59(SharedPreferences.Editor r1, TLObject r2, TLRPC.TL_error r3) {
        if ((r2 instanceof Vector) == false) goto L6;
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda23(this, r1, r2));
        return;
    }

    private /* synthetic */ void lambda$syncPhoneBookByAlert$7(HashMap r10, boolean r11, boolean r12, boolean r13) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("sync contacts by alert");
    L5:
        performSyncPhoneBook(r10, true, r11, r12, false, false, r13);
    }

    private void mergePhonebookAndTelegramContacts(HashMap<String, ArrayList<Object>> r2, ArrayList<String> r3, HashMap<String, Contact> r4) {
        mergePhonebookAndTelegramContacts(r2, r3, r4, true);
    }

    private void mergePhonebookAndTelegramContacts(HashMap<String, ArrayList<Object>> r9, ArrayList<String> r10, HashMap<String, Contact> r11, boolean r12) {
        ArrayList r3 = new ArrayList(this.contacts);
        Utilities.globalQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda47(this, r12, r3, r11, r9, r10));
    }

    private void performWriteContactsToPhoneBook() {
        ArrayList r0 = new ArrayList(this.contacts);
        Utilities.phoneBookQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda38(this, r0));
    }

    private void performWriteContactsToPhoneBookInternal(ArrayList<TLRPC.TL_contact> r17) {
        long r2 = System.currentTimeMillis();
        Cursor r4 = null;
        Account r5 = this.systemAccount;     // Catch: Throwable -> L12 Exception -> L14
        if (hasContactsPermission() == false) goto L50;
        if (r5 != null) goto L7;
        return;
    L7:
        if (hasContactsWritePermission() == false) goto L70;
        SharedPreferences r52 = MessagesController.getMainSettings(this.currentAccount);     // Catch: Throwable -> L12 Exception -> L14
        int r6 = 0;
        boolean r7 = r52.getBoolean("contacts_updated_v7", false);     // Catch: Throwable -> L12 Exception -> L14
        boolean r8 = !r7;
        if (r7 == true) goto L16;
        r52.edit().putBoolean("contacts_updated_v7", true).apply();     // Catch: Throwable -> L12 Exception -> L14
    L16:
        ContentResolver r10 = ApplicationLoader.applicationContext.getContentResolver();     // Catch: Throwable -> L12 Exception -> L14
        Cursor r1 = r10.query(ContactsContract.RawContacts.CONTENT_URI, new String[]{"_id", "sync2"}, null, null, null);     // Catch: Throwable -> L12 Exception -> L14
        LongSparseArray r53 = new LongSparseArray();     // Catch: Throwable -> L22 Exception -> L24
        if (r1 != null) goto L20;
        r4 = r1;
    L48:
        if (r4 == null) goto L54;
    L49:
        r4.close();
    L54:
        FileLog.d("performWriteContactsToPhoneBookInternal " + (System.currentTimeMillis() - r2));
        return;
    L20:
        if (r1.moveToNext() == false) goto L26;
        r53.put(r1.getLong(1), Long.valueOf(r1.getLong(0)));     // Catch: Throwable -> L22 Exception -> L24
        goto L20
    L26:
        r1.close();     // Catch: Throwable -> L22 Exception -> L24
        FileLog.d("performWriteContactsToPhoneBookInternal contacts array " + r17.size() + " " + r8 + " bookContactsSize=" + r53.size() + " currentAccount=" + this.currentAccount);     // Catch: Throwable -> L12 Exception -> L14
        ArrayList<ContentProviderOperation> r12 = null;
    L30:
        if (r6 >= r17.size()) goto L42;
        TLRPC.TL_contact r11 = r17.get(r6);     // Catch: Throwable -> L12 Exception -> L14
        if (r7 == true) goto L35;
    L36:
        if (r12 != null) goto L38;
        r12 = new ArrayList();     // Catch: Throwable -> L12 Exception -> L14
    L38:
        applyContactToPhoneBook(r12, getMessagesController().getUser(Long.valueOf(r11.user_id)));     // Catch: Throwable -> L12 Exception -> L14
        if (r12.size() <= 450) goto L41;
        r10.applyBatch("com.android.contacts", r12);     // Catch: Throwable -> L12 Exception -> L14
        r12.clear();     // Catch: Throwable -> L12 Exception -> L14
    L41:
        r6 = r6 + 1;     // Catch: Throwable -> L12 Exception -> L14
        goto L30
    L35:
        if (r53.indexOfKey(r11.user_id) >= 0) goto L41;
    L42:
        if (r12 == null) goto L48;
        if (r12.isEmpty() == true) goto L48;
        r10.applyBatch("com.android.contacts", r12);     // Catch: Throwable -> L12 Exception -> L14
        r12.clear();     // Catch: Throwable -> L12 Exception -> L14
        goto L48
    L24:
        e = e;
        r4 = r1;
    L51:
        FileLog.e(e);     // Catch: Throwable -> L12
        if (r4 == null) goto L54;
    L22:
        th = th;
        r4 = r1;
    L56:
        if (r4 == null) goto L58;
        r4.close();
    L58:
        throw th;
    L70:
        return;
    L50:
        return;
    L14:
        e = e;
    L12:
        th = th;
        goto L56
    }

    private void reloadContactsStatuses() {
        saveContactsLoadTime();
        getMessagesController().clearFullUsers();
        SharedPreferences.Editor r0 = MessagesController.getMainSettings(this.currentAccount).edit();
        r0.putBoolean("needGetStatuses", true).apply();
        TLRPC.TL_contacts_getStatuses r1 = new TLRPC.TL_contacts_getStatuses();
        getConnectionsManager().sendRequest(r1, new ContactsController$$ExternalSyntheticLambda57(this, r0));
    }

    private void saveContactsLoadTime() {
        MessagesController.getMainSettings(this.currentAccount).edit().putLong("lastReloadStatusTime", System.currentTimeMillis()).apply();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private void updateUnregisteredContacts() {
        HashMap r0 = new HashMap();
        int r1 = this.contacts.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L11;
        TLRPC.TL_contact r4 = this.contacts.get(r3);
        TLRPC.User r5 = getMessagesController().getUser(Long.valueOf(r4.user_id));
        if (r5 == null) goto L10;
        if (TextUtils.isEmpty(r5.phone) == true) goto L10;
        r0.put(r5.phone, r4);
    L10:
        r3 = r3 + 1;
        goto L3
    L11:
        ArrayList<Contact> r12 = new ArrayList();
        Iterator<Map.Entry<String, Contact>> r32 = this.contactsBook.entrySet().iterator();
    L13:
        if (r32.hasNext() == false) goto L24;
        Contact r42 = r32.next().getValue();
        int r52 = 0;
    L16:
        if (r52 >= r42.phones.size()) goto L23;
        if (r0.containsKey(r42.shortPhones.get(r52)) == true) goto L13;
        if (r42.phoneDeleted.get(r52).intValue() == 1) goto L13;
        r52 = r52 + 1;
        goto L16
    L23:
        r12.add(r42);
        goto L13
    L24:
        Collections.sort(r12, new ContactsController$$ExternalSyntheticLambda51(getLocaleCollator()));
        this.phoneBookContacts = r12;
    }

    public void addContact(TLRPC.User r3, TLRPC.TL_textWithEntities r4, boolean r5) {
        if (r3 != null) goto L4;
        return;
    L4:
        TLRPC.TL_contacts_addContact r0 = new TLRPC.TL_contacts_addContact();
        r0.id = getMessagesController().getInputUser(r3);
        r0.first_name = r3.first_name;
        r0.last_name = r3.last_name;
        String r1 = r3.phone;
        r0.phone = r1;
        r0.add_phone_privacy_exception = r5;
        if (r1 != null) goto L8;
        r0.phone = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        if (r4 == null) goto L14;
        r0.flags |= 2;
        r0.note = r4;
    L14:
        getConnectionsManager().sendRequest(r0, new ContactsController$$ExternalSyntheticLambda8(this, r3), 6);
        return;
    L8:
        if (r1.length() <= 0) goto L12;
        if (r0.phone.startsWith("+") == true) goto L12;
        r0.phone = "+" + r0.phone;
        goto L12
    }

    public void addContact(TLRPC.User r2, boolean r3) {
        addContact(r2, null, r3);
    }

    public long addContactToPhoneBook(TLRPC.User r8, boolean r9) {
        long r2 = -1;
        if (this.systemAccount == null) goto L39;
        if (r8 == null) goto L39;
        if (hasContactsWritePermission() == true) goto L9;
        return -1;
    L9:
        Object r1 = this.observerLock;
        monitor-enter(r1);
        this.ignoreChanges = true;     // Catch: Throwable -> L36
        monitor-exit(r1);     // Catch: Throwable -> L36
        ContentResolver r12 = ApplicationLoader.applicationContext.getContentResolver();
        if (r9 == true) goto L43;
    L17:
        ArrayList<ContentProviderOperation> r92 = new ArrayList();
        applyContactToPhoneBook(r92, r8);
        ContentProviderResult[] r93 = r12.applyBatch("com.android.contacts", r92);     // Catch: Exception -> L26
        if (r93 != null) goto L21;
    L28:
        Object r94 = this.observerLock;
        monitor-enter(r94);
        this.ignoreChanges = false;     // Catch: Throwable -> L33
        monitor-exit(r94);     // Catch: Throwable -> L33
        return r2;
    L33:
        th = move-exception;
        throw th;
    L21:
        if (r93.length <= 0) goto L28;
        Uri r95 = r93[0].uri;     // Catch: Exception -> L26
        if (r95 == null) goto L28;
        r2 = Long.parseLong(r95.getLastPathSegment());     // Catch: Exception -> L26
    L26:
        e = move-exception;
        FileLog.e(e);
        goto L28
    L43:
        r12.delete(ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter("caller_is_syncadapter", "true").appendQueryParameter("account_name", this.systemAccount.name).appendQueryParameter("account_type", this.systemAccount.type).build(), "sync2 = " + r8.id, null);     // Catch: Exception -> L40
    L36:
        th = move-exception;
        throw th;
    L39:
        return -1;
    }

    public void checkAppAccount() {
        this.systemAccount = null;
        Utilities.globalQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda49(this));
    }

    public void checkContacts() {
        Utilities.globalQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda7(this));
    }

    public void checkInviteText() {
        SharedPreferences r0 = MessagesController.getMainSettings(this.currentAccount);
        this.inviteLink = r0.getString("invitelink", null);
        int r02 = r0.getInt("invitelinktime", 0);
        if (this.updatingInviteLink == false) goto L5;
        return;
    L5:
        if (this.inviteLink != null) goto L7;
    L8:
        this.updatingInviteLink = true;
        getConnectionsManager().sendRequest(new TLRPC.TL_help_getInviteText(), new ContactsController$$ExternalSyntheticLambda16(this), 2);
        return;
    L7:
        if (Math.abs((System.currentTimeMillis() / 1000) - r02) >= 86400) goto L8;
    }

    public void cleanup() {
        this.contactsBook.clear();
        this.contactsBookSPhones.clear();
        this.phoneBookContacts.clear();
        this.contacts.clear();
        this.contactsDict.clear();
        this.usersSectionsDict.clear();
        this.usersMutualSectionsDict.clear();
        this.sortedUsersSectionsArray.clear();
        this.sortedUsersMutualSectionsArray.clear();
        this.delayedContactsUpdate.clear();
        this.contactsByPhone.clear();
        this.contactsByShortPhone.clear();
        this.phoneBookSectionsDict.clear();
        this.phoneBookSectionsArray.clear();
        this.phoneBookByShortPhones.clear();
        this.loadingContacts = false;
        this.contactsSyncInProgress = false;
        this.doneLoadingContacts = false;
        this.contactsLoaded = false;
        this.contactsBookLoaded = false;
        this.lastContactsVersions = _UrlKt.FRAGMENT_ENCODE_SET;
        this.loadingGlobalSettings = 0;
        this.loadingDeleteInfo = 0;
        this.deleteAccountTTL = 0;
        Arrays.fill(this.loadingPrivacyInfo, 0);
        this.lastseenPrivacyRules = null;
        this.groupPrivacyRules = null;
        this.callPrivacyRules = null;
        this.p2pPrivacyRules = null;
        this.profilePhotoPrivacyRules = null;
        this.bioPrivacyRules = null;
        this.musicPrivacyRules = null;
        this.birthdayPrivacyRules = null;
        this.giftsPrivacyRules = null;
        this.forwardsPrivacyRules = null;
        this.phonePrivacyRules = null;
        Utilities.globalQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda53(this));
    }

    public void createOrUpdateConnectionServiceContact(long r28, String r30, String r31) {
        if (hasContactsPermission() == true) goto L28;
        return;
    L28:
        ContentResolver r17 = ApplicationLoader.applicationContext.getContentResolver();     // Catch: Exception -> L25
        ArrayList<ContentProviderOperation> r14 = new ArrayList();     // Catch: Exception -> L25
        Uri r18 = ContactsContract.Groups.CONTENT_URI.buildUpon().appendQueryParameter("caller_is_syncadapter", "true").build();     // Catch: Exception -> L25
        Uri r9 = ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter("caller_is_syncadapter", "true").build();     // Catch: Exception -> L25
        Account r10 = this.systemAccount;     // Catch: Exception -> L25
        Cursor r102 = r17.query(r18, new String[]{"_id"}, "title=? AND account_type=? AND account_name=?", new String[]{"TelegramConnectionService", r10.type, r10.name}, null);     // Catch: Exception -> L25
        if (r102 != null) goto L9;
    L11:
        ContentValues r3 = new ContentValues();     // Catch: Exception -> L25
        Cursor r172 = r102;
        r3.put("account_type", this.systemAccount.type);     // Catch: Exception -> L25
        r3.put("account_name", this.systemAccount.name);     // Catch: Exception -> L25
        r3.put("group_visible", 0);     // Catch: Exception -> L25
        r3.put("group_is_read_only", 1);     // Catch: Exception -> L25
        r3.put("title", "TelegramConnectionService");     // Catch: Exception -> L25
        int r8 = Integer.parseInt(r17.insert(r18, r3).getLastPathSegment());     // Catch: Exception -> L25
    L12:
        if (r172 == null) goto L14;
        r172.close();     // Catch: Exception -> L25
    L14:
        Uri r182 = ContactsContract.Data.CONTENT_URI;     // Catch: Exception -> L25
        Cursor r32 = r17.query(r182, new String[]{"raw_contact_id"}, "mimetype=? AND data1=?", new String[]{"vnd.android.cursor.item/group_membership", r8 + _UrlKt.FRAGMENT_ENCODE_SET}, null);     // Catch: Exception -> L25
        int r92 = r14.size();     // Catch: Exception -> L25
        int r173 = r8;
        if (r32 != null) goto L18;
    L20:
        Cursor r16 = r32;
        r14.add(ContentProviderOperation.newInsert(r9).withValue("account_type", this.systemAccount.type).withValue("account_name", this.systemAccount.name).withValue("raw_contact_is_read_only", 1).withValue("aggregation_mode", 3).build());     // Catch: Exception -> L25
        r14.add(ContentProviderOperation.newInsert(r182).withValueBackReference("raw_contact_id", r92).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data2", r30).withValue("data3", r31).build());     // Catch: Exception -> L25
        r14.add(ContentProviderOperation.newInsert(r182).withValueBackReference("raw_contact_id", r92).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", "+99084" + r28).build());     // Catch: Exception -> L25
        r14.add(ContentProviderOperation.newInsert(r182).withValueBackReference("raw_contact_id", r92).withValue("mimetype", "vnd.android.cursor.item/group_membership").withValue("data1", Integer.valueOf(r173)).build());     // Catch: Exception -> L25
    L21:
        if (r16 == null) goto L23;
        r16.close();     // Catch: Exception -> L25
    L23:
        r17.applyBatch("com.android.contacts", r14);     // Catch: Exception -> L25
        return;
    L18:
        if (r32.moveToFirst() == false) goto L20;
        int r0 = r32.getInt(0);     // Catch: Exception -> L25
        r16 = r32;
        r14.add(ContentProviderOperation.newUpdate(r9).withSelection("_id=?", new String[]{r0 + _UrlKt.FRAGMENT_ENCODE_SET}).withValue("deleted", 0).build());     // Catch: Exception -> L25
        ContentProviderOperation.Builder r33 = ContentProviderOperation.newUpdate(r182).withSelection("raw_contact_id=? AND mimetype=?", new String[]{r0 + _UrlKt.FRAGMENT_ENCODE_SET, "vnd.android.cursor.item/phone_v2"});     // Catch: Exception -> L25
        StringBuilder r4 = new StringBuilder("+99084");     // Catch: Exception -> L25
        r4.append(r28);     // Catch: Exception -> L25
        r14.add(r33.withValue("data1", r4.toString()).build());     // Catch: Exception -> L25
        r14.add(ContentProviderOperation.newUpdate(r182).withSelection("raw_contact_id=? AND mimetype=?", new String[]{r0 + _UrlKt.FRAGMENT_ENCODE_SET, "vnd.android.cursor.item/name"}).withValue("data2", r30).withValue("data3", r31).build());     // Catch: Exception -> L25
        goto L21
    L9:
        if (r102.moveToFirst() == false) goto L11;
        r8 = r102.getInt(0);     // Catch: Exception -> L25
        r172 = r102;
    L25:
        e = move-exception;
        FileLog.e(e);
    }

    public void deleteAllContacts(Runnable r9) {
        resetImportedContacts();
        TLRPC.TL_contacts_deleteContacts r0 = new TLRPC.TL_contacts_deleteContacts();
        int r1 = this.contacts.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        TLRPC.TL_contact r3 = this.contacts.get(r2);
        r0.id.add(getMessagesController().getInputUser(r3.user_id));
        r2 = r2 + 1;
        goto L3
    L5:
        getConnectionsManager().sendRequest(r0, new ContactsController$$ExternalSyntheticLambda6(this, r9));
    }

    public void deleteConnectionServiceContact() {
        if (hasContactsPermission() == false) goto L27;
        ContentResolver r2 = ApplicationLoader.applicationContext.getContentResolver();     // Catch: Exception -> L21
        Account r8 = this.systemAccount;     // Catch: Exception -> L21
        Cursor r82 = r2.query(ContactsContract.Groups.CONTENT_URI, new String[]{"_id"}, "title=? AND account_type=? AND account_name=?", new String[]{"TelegramConnectionService", r8.type, r8.name}, null);     // Catch: Exception -> L21
        if (r82 != null) goto L8;
    L18:
        if (r82 == null) goto L26;
        r82.close();     // Catch: Exception -> L21
        return;
    L26:
        return;
    L8:
        if (r82.moveToFirst() == false) goto L18;
        int r3 = r82.getInt(0);     // Catch: Exception -> L21
        r82.close();     // Catch: Exception -> L21
        Cursor r83 = r2.query(ContactsContract.Data.CONTENT_URI, new String[]{"raw_contact_id"}, "mimetype=? AND data1=?", new String[]{"vnd.android.cursor.item/group_membership", r3 + _UrlKt.FRAGMENT_ENCODE_SET}, null);     // Catch: Exception -> L21
        if (r83 != null) goto L12;
    L15:
        if (r83 == null) goto L25;
        r83.close();     // Catch: Exception -> L21
        return;
    L25:
        return;
    L12:
        if (r83.moveToFirst() == false) goto L15;
        int r1 = r83.getInt(0);     // Catch: Exception -> L21
        r83.close();     // Catch: Exception -> L21
        r2.delete(ContactsContract.RawContacts.CONTENT_URI, "_id=?", new String[]{r1 + _UrlKt.FRAGMENT_ENCODE_SET});     // Catch: Exception -> L21
        return;
    L21:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public void deleteContact(ArrayList<TLRPC.User> r10, boolean r11) {
        if (r10 != null) goto L4;
        return;
    L4:
        if (r10.isEmpty() == true) goto L19;
        TLRPC.TL_contacts_deleteContacts r0 = new TLRPC.TL_contacts_deleteContacts();
        ArrayList r3 = new ArrayList();
        int r1 = r10.size();
        int r4 = 0;
    L7:
        if (r4 >= r1) goto L13;
        TLRPC.User r5 = r10.get(r4);
        getMessagesController().getStoriesController().removeContact(r5.id);
        TLRPC.InputUser r6 = getMessagesController().getInputUser(r5);
        if (r6 == null) goto L12;
        r5.contact = false;
        r3.add(Long.valueOf(r5.id));
        r0.id.add(r6);
    L12:
        r4 = r4 + 1;
        goto L7
    L13:
        getConnectionsManager().sendRequest(r0, new ContactsController$$ExternalSyntheticLambda35(this, r3, r10, r11, r10.get(0).first_name));
        return;
    }

    public void deleteContactsUndoable(Context r10, BaseFragment r11, ArrayList<TLRPC.User> r12) {
        if (r12 != null) goto L4;
        return;
    L4:
        if (r12.isEmpty() == true) goto L13;
        HashMap r0 = new HashMap();
        int r1 = r12.size();
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L9;
        TLRPC.User r4 = r12.get(r3);
        TLRPC.TL_contact r5 = this.contactsDict.get(Long.valueOf(r4.id));
        r4.contact = false;
        this.contacts.remove(r5);
        this.contactsDict.remove(Long.valueOf(r4.id));
        r0.put(r4, r5);
        r3 = r3 + 1;
        goto L7
    L9:
        buildContactsSectionsArrays(false);
        getNotificationCenter().postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_NAME)});
        getNotificationCenter().postNotificationName(NotificationCenter.contactsDidLoad, new Object[0]);
        Bulletin.SimpleLayout r13 = new Bulletin.SimpleLayout(r10, r11.getResourceProvider());
        r13.setTimer();
        r13.textView.setText(LocaleController.formatPluralString("ContactsDeletedUndo", r0.size(), new Object[0]));
        Bulletin.UndoButton r2 = new Bulletin.UndoButton(r10, true, true, r11.getResourceProvider());
        r2.setUndoAction(new ContactsController$$ExternalSyntheticLambda63(this, r0));
        r2.setDelayedAction(new ContactsController$$ExternalSyntheticLambda64(this, r12));
        r13.setButton(r2);
        Bulletin.make(r11, r13, 5000).show();
        return;
    }

    public void deleteUnknownAppAccounts() {
        this.systemAccount = null;     // Catch: Exception -> L17
        AccountManager r11 = AccountManager.get(ApplicationLoader.applicationContext);     // Catch: Exception -> L17
        Account[] r1 = r11.getAccountsByType("org.telegram.messenger");     // Catch: Exception -> L17
        int r3 = 0;
    L4:
        if (r3 >= r1.length) goto L30;
        Account r4 = r1[r3];     // Catch: Exception -> L17
        int r5 = 0;
    L8:
        if (r5 >= 16) goto L23;
        TLRPC.User r6 = UserConfig.getInstance(r5).getCurrentUser();     // Catch: Exception -> L17
        if (r6 == null) goto L14;
        if (r4.name.equals(_UrlKt.FRAGMENT_ENCODE_SET + r6.id) == false) goto L14;
    L16:
        r3 = r3 + 1;
    L14:
        r5 = r5 + 1;
        goto L8
    L23:
        r11.removeAccount(r1[r3], null, null);     // Catch: Exception -> L20
        goto L16
    L30:
        return;
    L17:
        e = move-exception;
        e.printStackTrace();
    }

    public void forceImportContacts() {
        Utilities.globalQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda5(this));
    }

    public HashMap<String, Contact> getContactsCopy(HashMap<String, Contact> r5) {
        HashMap<String, Contact> r4 = new HashMap();
        Iterator<Map.Entry<String, Contact>> r52 = r5.entrySet().iterator();
    L4:
        if (r52.hasNext() == false) goto L6;
        Map.Entry<String, Contact> r0 = r52.next();
        Contact r1 = new Contact();
        Contact r02 = r0.getValue();
        r1.phoneDeleted.addAll(r02.phoneDeleted);
        r1.phones.addAll(r02.phones);
        r1.phoneTypes.addAll(r02.phoneTypes);
        r1.shortPhones.addAll(r02.shortPhones);
        r1.first_name = r02.first_name;
        r1.last_name = r02.last_name;
        r1.contact_id = r02.contact_id;
        String r03 = r02.key;
        r1.key = r03;
        r4.put(r03, r1);
        goto L4
    L6:
        return r4;
    }

    public int getDeleteAccountTTL() {
        return this.deleteAccountTTL;
    }

    public TLRPC.GlobalPrivacySettings getGlobalPrivacySettings() {
        return this.globalPrivacySettings;
    }

    public String getInviteText(int r2) {
        String r1 = this.inviteLink;
        if (r1 != null) goto L6;
        r1 = "https://telegram.org/dl";
    L6:
        if (r2 <= 1) goto L8;
        return String.format(LocaleController.getPluralString("InviteTextNum", r2), new Object[]{Integer.valueOf(r2), r1});
    L12:
        return LocaleController.formatString(R.string.InviteText2, new Object[]{r1});
    L8:
        return LocaleController.formatString(R.string.InviteText2, new Object[]{r1});
    }

    public boolean getLoadingDeleteInfo() {
        if (this.loadingDeleteInfo == 2) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean getLoadingGlobalSettings() {
        if (this.loadingGlobalSettings == 2) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean getLoadingPrivacyInfo(int r1) {
        if (this.loadingPrivacyInfo[r1] == 2) goto L6;
        return true;
    L6:
        return false;
    }

    public ArrayList<TLRPC.PrivacyRule> getPrivacyRules(int r1) {
        switch(r1) {
            case 0: goto L32;
            case 1: goto L30;
            case 2: goto L28;
            case 3: goto L26;
            case 4: goto L24;
            case 5: goto L22;
            case 6: goto L20;
            case 7: goto L18;
            case 8: goto L16;
            case 9: goto L14;
            case 10: goto L3;
            case 11: goto L12;
            case 12: goto L10;
            case 13: goto L8;
            case 14: goto L6;
            default: goto L3;
        };
    L3:
        return null;
    L6:
        return this.musicPrivacyRules;
    L8:
        return this.noPaidMessagesPrivacyRules;
    L10:
        return this.giftsPrivacyRules;
    L12:
        return this.birthdayPrivacyRules;
    L14:
        return this.bioPrivacyRules;
    L16:
        return this.voiceMessagesRules;
    L18:
        return this.addedByPhonePrivacyRules;
    L20:
        return this.phonePrivacyRules;
    L22:
        return this.forwardsPrivacyRules;
    L24:
        return this.profilePhotoPrivacyRules;
    L26:
        return this.p2pPrivacyRules;
    L28:
        return this.callPrivacyRules;
    L30:
        return this.groupPrivacyRules;
    L32:
        return this.lastseenPrivacyRules;
    }

    public boolean isContact(long r1) {
        if (this.contactsDict.get(Long.valueOf(r1)) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isLoadingContacts() {
        Object r0 = this.loadContactsSync;
        monitor-enter(r0);
        boolean r1 = this.loadingContacts;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public void loadContacts(boolean r3, long r4) {
        Object r0 = this.loadContactsSync;
        monitor-enter(r0);
        this.loadingContacts = true;     // Catch: Throwable -> L18
        monitor-exit(r0);     // Catch: Throwable -> L18
        if (r3 == false) goto L14;
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.d("load contacts from cache");
    L11:
        getMessagesStorage().getContacts();
        return;
    L14:
        if (BuildVars.LOGS_ENABLED == false) goto L16;
        FileLog.d("load contacts from server");
    L16:
        TLRPC.TL_contacts_getContacts r32 = new TLRPC.TL_contacts_getContacts();
        r32.hash = r4;
        getConnectionsManager().sendRequest(r32, new ContactsController$$ExternalSyntheticLambda22(this, r4));
        return;
    L18:
        th = move-exception;
        throw th;
    }

    public void loadGlobalPrivacySetting() {
        if (this.loadingGlobalSettings != 0) goto L6;
        this.loadingGlobalSettings = 1;
        TL_account.getGlobalPrivacySettings r0 = new TL_account.getGlobalPrivacySettings();
        getConnectionsManager().sendRequest(r0, new ContactsController$$ExternalSyntheticLambda28(this));
        return;
    }

    public void loadPrivacySettings() {
        loadPrivacySettings(false);
    }

    public void loadPrivacySettings(boolean r7) {
        if (this.loadingDeleteInfo != 0) goto L5;
        this.loadingDeleteInfo = 1;
        getConnectionsManager().sendRequest(new TL_account.getAccountTTL(), new ContactsController$$ExternalSyntheticLambda58(this));
    L5:
        loadGlobalPrivacySetting();
        int r2 = 0;
    L6:
        int[] r3 = this.loadingPrivacyInfo;
        if (r2 >= r3.length) goto L34;
        if (r7 == false) goto L13;
        if (r3[r2] == 1) goto L33;
    L15:
        r3[r2] = 1;
        TL_account.getPrivacy r32 = new TL_account.getPrivacy();
        switch(r2) {
            case 0: goto L31;
            case 1: goto L30;
            case 2: goto L29;
            case 3: goto L28;
            case 4: goto L27;
            case 5: goto L26;
            case 6: goto L25;
            case 7: goto L24;
            case 8: goto L23;
            case 9: goto L22;
            case 10: goto L33;
            case 11: goto L21;
            case 12: goto L20;
            case 13: goto L19;
            case 14: goto L18;
            default: goto L33;
        };
    L18:
        r32.key = new TLRPC.TL_inputPrivacyKeySavedMusic();
    L32:
        getConnectionsManager().sendRequest(r32, new ContactsController$$ExternalSyntheticLambda59(this, r2));
        goto L33
    L19:
        r32.key = new TLRPC.TL_inputPrivacyKeyNoPaidMessages();
        goto L32
    L20:
        r32.key = new TLRPC.TL_inputPrivacyKeyStarGiftsAutoSave();
        goto L32
    L21:
        r32.key = new TLRPC.TL_inputPrivacyKeyBirthday();
        goto L32
    L22:
        r32.key = new TLRPC.TL_inputPrivacyKeyAbout();
        goto L32
    L23:
        r32.key = new TLRPC.TL_inputPrivacyKeyVoiceMessages();
        goto L32
    L24:
        r32.key = new TLRPC.TL_inputPrivacyKeyAddedByPhone();
        goto L32
    L25:
        r32.key = new TLRPC.TL_inputPrivacyKeyPhoneNumber();
        goto L32
    L26:
        r32.key = new TLRPC.TL_inputPrivacyKeyForwards();
        goto L32
    L27:
        r32.key = new TLRPC.TL_inputPrivacyKeyProfilePhoto();
        goto L32
    L28:
        r32.key = new TLRPC.TL_inputPrivacyKeyPhoneP2P();
        goto L32
    L29:
        r32.key = new TLRPC.TL_inputPrivacyKeyPhoneCall();
        goto L32
    L30:
        r32.key = new TLRPC.TL_inputPrivacyKeyChatInvite();
        goto L32
    L31:
        r32.key = new TLRPC.TL_inputPrivacyKeyStatusTimestamp();
    L33:
        r2 = r2 + 1;
        goto L6
    L13:
        if (r3[r2] == 0) goto L15;
    L34:
        getNotificationCenter().postNotificationName(NotificationCenter.privacyRulesUpdated, new Object[0]);
    }

    public void markAsContacted(String r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        Utilities.phoneBookQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda60(r2));
    }

    public void migratePhoneBookToV7(SparseArray<Contact> r3) {
        Utilities.globalQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda48(this, r3));
    }

    public void performSyncPhoneBook(HashMap<String, Contact> r11, boolean r12, boolean r13, boolean r14, boolean r15, boolean r16, boolean r17) {
        if (r13 == false) goto L4;
    L6:
        Utilities.globalQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda15(this, r11, r14, r12, r13, r15, r16, r17));
        return;
    L4:
        if (this.contactsBookLoaded == true) goto L6;
    }

    public void processContactsUpdates(ArrayList<Long> r13, ConcurrentHashMap<Long, TLRPC.User> r14) {
        ArrayList<TLRPC.TL_contact> r0 = new ArrayList();
        ArrayList<Long> r1 = new ArrayList();
        int r2 = r13.size();
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L19;
        Long r5 = r13.get(r4);
        r4 = r4 + 1;
        Long r52 = r5;
        if (r52.longValue() > 0) goto L6;
        if (r52.longValue() >= 0) goto L3;
        r1.add(Long.valueOf(-r52.longValue()));
        if (this.delayedContactsUpdate.isEmpty() == true) goto L3;
        int r53 = this.delayedContactsUpdate.indexOf(Long.valueOf(-r52.longValue()));
        if (r53 == (-1)) goto L3;
        this.delayedContactsUpdate.remove(r53);
        goto L3
    L6:
        TLRPC.TL_contact r6 = new TLRPC.TL_contact();
        r6.user_id = r52.longValue();
        r0.add(r6);
        if (this.delayedContactsUpdate.isEmpty() == true) goto L3;
        int r54 = this.delayedContactsUpdate.indexOf(Long.valueOf(-r52.longValue()));
        if (r54 == (-1)) goto L3;
        this.delayedContactsUpdate.remove(r54);
        goto L3
    L19:
        if (r1.isEmpty() == true) goto L22;
        getMessagesStorage().deleteContacts(r1);
    L22:
        if (r0.isEmpty() == true) goto L25;
        getMessagesStorage().putContacts(r0, false);
    L25:
        if (this.contactsLoaded == true) goto L27;
    L31:
        this.delayedContactsUpdate.addAll(r13);
        if (BuildVars.LOGS_ENABLED == false) goto L51;
        FileLog.d("delay update - contacts add = " + r0.size() + " delete = " + r1.size());
        return;
    L51:
        return;
    L27:
        if (this.contactsBookLoaded == false) goto L31;
        applyContactsUpdates(r13, r14, r0, r1);
    }

    public void processLoadedContacts(ArrayList<TLRPC.TL_contact> r2, ArrayList<TLRPC.User> r3, int r4) {
        AndroidUtilities.runOnUIThread(new ContactsController$$ExternalSyntheticLambda45(this, r3, r4, r2));
    }

    public void readContacts() {
        Object r0 = this.loadContactsSync;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.loadingContacts == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        this.loadingContacts = true;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        Utilities.stageQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda61(this));
    }

    public HashMap<String, Contact> readContactsFromPhoneBook() {
        ContactsController r0 = this;
        if (r0.getUserConfig().syncContacts == true) goto L10;
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        FileLog.d("contacts sync disabled");
    L8:
        return new HashMap();
    L10:
        if (hasContactsPermission() == false) goto L12;
        StringBuilder r4 = new StringBuilder();     // Catch: Throwable -> L231
        ContentResolver r6 = ApplicationLoader.applicationContext.getContentResolver();     // Catch: Throwable -> L231
        HashMap r5 = new HashMap();     // Catch: Throwable -> L231
        ArrayList r12 = new ArrayList();     // Catch: Throwable -> L231
        Cursor r7 = r6.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projectionPhones, null, null, null);     // Catch: Throwable -> L231
        long r13 = System.currentTimeMillis();     // Catch: Throwable -> L229
        String r15 = "+";
        int r3 = 1;
        if (r7 != null) goto L278;
        ContentResolver r21 = r6;
        ArrayList r18 = r12;
        long r22 = r13;
        String r25 = "+";
        int r02 = 2;
        int r1 = 3;
        Cursor r32 = r7;
        HashMap<String, Contact> r42 = null;
        int r52 = 1;
    L257:
        String r62 = TextUtils.join(",", r18);     // Catch: Throwable -> L114
        int r132 = r02;
        ContentResolver r63 = r21;
        r32 = r63.query(ContactsContract.Data.CONTENT_URI, projectionNames, "lookup IN (" + r62 + ") AND mimetype = 'vnd.android.cursor.item/name'", null, null);     // Catch: Throwable -> L114
        if (r32 != null) goto L110;
        ContactsController r152 = this;
    L267:
        StringBuilder r72 = new StringBuilder();     // Catch: Throwable -> L220
        r72.append("loading contacts 1 query time = ");     // Catch: Throwable -> L220
        r72.append(System.currentTimeMillis() - r22);     // Catch: Throwable -> L220
        r72.append(" contactsSize = ");     // Catch: Throwable -> L220
        if (r42 != null) goto L170;
        int r8 = 0;
    L171:
        r72.append(r8);     // Catch: Throwable -> L220
        FileLog.d(r72.toString());     // Catch: Throwable -> L220
        long r17 = System.currentTimeMillis();     // Catch: Throwable -> L220
        HashMap r73 = new HashMap();     // Catch: Throwable -> L220
        ArrayList r82 = new ArrayList();     // Catch: Throwable -> L220
        Cursor r74 = r63.query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id", "lookup", "display_name"}, "has_phone_number = ?", new String[]{MVEL.VERSION_SUB}, null);     // Catch: Throwable -> L220
        if (r74 != null) goto L174;
    L184:
        FileLog.d("loading contacts 2 query time = " + (System.currentTimeMillis() - r17) + " phoneBookConacts size = " + r82.size());     // Catch: Throwable -> L220
        long r153 = System.currentTimeMillis();     // Catch: Throwable -> L220
        if (r82.isEmpty() == true) goto L223;
        Pattern r75 = Pattern.compile(".*(\\+[0-9 \\-]+).*");     // Catch: Throwable -> L220
        Cursor r182 = r32;
        r32 = r63.query(ContactsContract.Data.CONTENT_URI, new String[]{"contact_id", "data1", "data2", "data3", "data4"}, "contact_id IN (" + TextUtils.join(", ", r82) + ")", null, null);     // Catch: Throwable -> L218
        if (r32 == null) goto L223;
    L191:
        if (r32.moveToNext() == false) goto L217;
        PhoneBookContact r76 = (PhoneBookContact) r73.get(r32.getString(0));     // Catch: Throwable -> L114
        if (r76 == null) goto L214;
        int r64 = 4;
        String[] r83 = {r32.getString(1), r32.getString(2), r32.getString(3), r32.getString(4)};     // Catch: Throwable -> L114
        int r9 = 0;
    L195:
        if (r9 >= r64) goto L214;
        String r10 = r83[r9];     // Catch: Throwable -> L114
        if (r10 == null) goto L213;
        Matcher r102 = r75.matcher(r10);     // Catch: Throwable -> L114
        if (r102.matches() == false) goto L202;
        r76.phone = r102.group(1).replace(" ", _UrlKt.FRAGMENT_ENCODE_SET).replace("-", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L114
    L202:
        String r103 = r76.phone;     // Catch: Throwable -> L114
        if (r103 == null) goto L213;
        String r11 = r25;
        if (r103.startsWith(r11) == false) goto L208;
        r103 = r76.phone.substring(1);     // Catch: Throwable -> L114
    L208:
        Contact r84 = new Contact();     // Catch: Throwable -> L114
        r84.first_name = r76.name;     // Catch: Throwable -> L114
        r84.last_name = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Throwable -> L114
        int r65 = r52 + 1;     // Catch: Throwable -> L114
        r84.contact_id = r52;     // Catch: Throwable -> L114
        r84.key = r76.lookup_key;     // Catch: Throwable -> L114
        r84.phones.add(r76.phone);     // Catch: Throwable -> L114
        r84.shortPhones.add(r103);     // Catch: Throwable -> L114
        r84.phoneDeleted.add(0);     // Catch: Throwable -> L114
        r84.phoneTypes.add(LocaleController.getString(R.string.PhoneOther));     // Catch: Throwable -> L114
        if (r42 != null) goto L211;
        r42 = new HashMap();     // Catch: Throwable -> L114
    L211:
        r42.put(r76.lookup_key, r84);     // Catch: Throwable -> L114
        r52 = r65;
    L216:
        r25 = r11;
    L213:
        r9 = r9 + 1;     // Catch: Throwable -> L114
        r25 = r25;
        r64 = 4;
    L214:
        r11 = r25;
        goto L216
    L217:
        r32.close();     // Catch: Throwable -> L114
    L218:
        th = th;
    L219:
        HashMap<String, Contact> r33 = r42;
        r7 = r182;
    L261:
        FileLog.e(th);     // Catch: Throwable -> L237
        if (r33 == null) goto L239;
        r33.clear();     // Catch: Throwable -> L237
    L239:
        if (r7 != null) goto L265;
    L244:
        r42 = r33;
    L245:
        if (r42 == null) goto L248;
        return r42;
    L248:
        return new HashMap();
    L265:
        r7.close();     // Catch: Exception -> L242
    L242:
        e = move-exception;
        FileLog.e(e);
    L237:
        th = move-exception;
        if (r7 == null) goto L305;
        r7.close();     // Catch: Exception -> L252
        throw th;
    L252:
        e = move-exception;
        FileLog.e(e);
        throw th;
    L305:
        throw th;
    L223:
        FileLog.d("loading contacts 3 query time = " + (System.currentTimeMillis() - r153));     // Catch: Throwable -> L114
        if (r32 == null) goto L245;
        r32.close();     // Catch: Exception -> L227
    L227:
        e = move-exception;
        FileLog.e(e);
    L174:
        if (r74.moveToNext() == false) goto L183;
        PhoneBookContact r85 = new PhoneBookContact(r152, null);     // Catch: Throwable -> L114
        r85.id = r74.getString(0);     // Catch: Throwable -> L114
        r85.lookup_key = r74.getString(1);     // Catch: Throwable -> L114
        r85.name = r74.getString(r132);     // Catch: Throwable -> L114
        if (r42 == null) goto L180;
        if (r42.get(r85.lookup_key) != null) goto L174;
    L180:
        if (TextUtils.isEmpty(r85.name) == true) goto L174;
        r73.put(r85.id, r85);     // Catch: Throwable -> L114
        r82.add(r85.id);     // Catch: Throwable -> L114
        goto L174
    L183:
        r74.close();     // Catch: Throwable -> L114
        goto L184
    L170:
        r8 = r42.size();     // Catch: Throwable -> L220
    L220:
        th = th;
        r182 = r32;
    L110:
        if (r32.moveToNext() == false) goto L163;
        String r86 = r32.getString(0);     // Catch: Throwable -> L114
        String r92 = r32.getString(1);     // Catch: Throwable -> L114
        String r77 = r32.getString(r132);     // Catch: Throwable -> L114
        String r104 = r32.getString(r1);     // Catch: Throwable -> L114
        if (r42 == null) goto L116;
        Contact r87 = r42.get(r86);     // Catch: Throwable -> L114
    L117:
        if (r87 == null) goto L110;
        if (r87.namesFilled == true) goto L110;
        if (r87.isGoodProvider == false) goto L137;
        if (r92 == null) goto L124;
        r87.first_name = r92;     // Catch: Throwable -> L114
    L125:
        if (r77 == null) goto L127;
        r87.last_name = r77;     // Catch: Throwable -> L114
    L129:
        if (TextUtils.isEmpty(r104) == true) goto L160;
        if (TextUtils.isEmpty(r87.first_name) == true) goto L135;
        r87.first_name += " " + r104;
        goto L160
    L135:
        r87.first_name = r104;     // Catch: Throwable -> L114
    L160:
        r87.namesFilled = true;     // Catch: Throwable -> L114
        goto L110
    L127:
        r87.last_name = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Throwable -> L114
        goto L129
    L124:
        r87.first_name = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Throwable -> L114
        goto L125
    L137:
        if (isNotValidNameString(r92) == true) goto L143;
        if (r87.first_name.contains(r92) == false) goto L141;
    L148:
        if (r92 == null) goto L150;
        r87.first_name = r92;     // Catch: Throwable -> L114
    L152:
        if (TextUtils.isEmpty(r104) == false) goto L154;
    L157:
        if (r77 == null) goto L159;
        r87.last_name = r77;     // Catch: Throwable -> L114
        goto L160
    L159:
        r87.last_name = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Throwable -> L114
        goto L160
    L154:
        if (TextUtils.isEmpty(r87.first_name) == true) goto L156;
        r87.first_name += " " + r104;
        goto L157
    L156:
        r87.first_name = r104;     // Catch: Throwable -> L114
        goto L157
    L150:
        r87.first_name = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Throwable -> L114
        goto L152
    L141:
        if (r92.contains(r87.first_name) == true) goto L148;
    L143:
        if (isNotValidNameString(r77) == true) goto L160;
        if (r87.last_name.contains(r77) == true) goto L148;
        if (r92.contains(r87.last_name) == false) goto L160;
    L116:
        r87 = null;
        goto L117
    L163:
        r152 = this;
        r32.close();     // Catch: Throwable -> L114 Exception -> L256
    L165:
        r32 = null;
    L114:
        th = th;
        r7 = r32;
        r33 = r42;
        goto L261
    L278:
        int r93 = r7.getCount();     // Catch: Throwable -> L100
        if (r93 <= 0) goto L102;
        HashMap<String, Contact> r88 = new HashMap(r93);     // Catch: Throwable -> L100
        int r94 = 1;
    L269:
    L44:
        th = th;
        r33 = r88;
        goto L261
    L25:
        if (r7.moveToNext() == false) goto L99;
        String r105 = r7.getString(r3);     // Catch: Throwable -> L44
        String r14 = r7.getString(5);     // Catch: Throwable -> L44
        if (r14 != null) goto L30;
        r14 = _UrlKt.FRAGMENT_ENCODE_SET;
    L30:
        if (r14.indexOf(".sim") == 0) goto L32;
        boolean r34 = true;
    L34:
        if (TextUtils.isEmpty(r105) == false) goto L37;
        ContentResolver r212 = r6;
    L36:
        long r222 = r13;
        String r252 = r15;
    L54:
        r6 = r212;
        r13 = r222;
    L55:
        r15 = r252;
        r3 = 1;
        goto L269
    L37:
        r212 = r6;
        String r106 = PhoneFormat.stripExceptNumbers(r105, true);     // Catch: Throwable -> L44
        if (TextUtils.isEmpty(r106) == true) goto L36;
        if (r106.startsWith(r15) == false) goto L46;
        String r66 = r106.substring(1);     // Catch: Throwable -> L44
    L43:
        r222 = r13;
        String r142 = r7.getString(0);     // Catch: Throwable -> L44
        r4.setLength(0);     // Catch: Throwable -> L44
        DatabaseUtils.appendEscapedSQLString(r4, r142);     // Catch: Throwable -> L44
        String r133 = r4.toString();     // Catch: Throwable -> L44
        r252 = r15;
        Contact r154 = (Contact) r5.get(r66);     // Catch: Throwable -> L44
        if (r154 == null) goto L57;
        if (r154.isGoodProvider == true) goto L54;
        if (r14.equals(r154.provider) == true) goto L54;
        r4.setLength(0);     // Catch: Throwable -> L44
        DatabaseUtils.appendEscapedSQLString(r4, r154.key);     // Catch: Throwable -> L44
        r12.remove(r4.toString());     // Catch: Throwable -> L44
        r12.add(r133);     // Catch: Throwable -> L44
        r154.key = r142;     // Catch: Throwable -> L44
        r154.isGoodProvider = r34;     // Catch: Throwable -> L44
        r154.provider = r14;     // Catch: Throwable -> L44
        goto L54
    L57:
        if (r12.contains(r133) == true) goto L59;
        r12.add(r133);     // Catch: Throwable -> L44
    L59:
        int r155 = r7.getInt(2);     // Catch: Throwable -> L44
        Contact r134 = r88.get(r142);     // Catch: Throwable -> L44
        if (r134 != null) goto L73;
        r134 = new Contact();     // Catch: Throwable -> L44
        StringBuilder r24 = r4;
        String r183 = r7.getString(4);     // Catch: Throwable -> L44
        if (r183 != null) goto L64;
        String r43 = _UrlKt.FRAGMENT_ENCODE_SET;
    L66:
        if (r0.isNotValidNameString(r43) == false) goto L68;
        r134.first_name = r43;     // Catch: Throwable -> L44
        r134.last_name = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Throwable -> L44
        ArrayList r184 = r12;
    L72:
        r134.provider = r14;     // Catch: Throwable -> L44
        r134.isGoodProvider = r34;     // Catch: Throwable -> L44
        r134.key = r142;     // Catch: Throwable -> L44
        r134.contact_id = r94;     // Catch: Throwable -> L44
        r88.put(r142, r134);     // Catch: Throwable -> L44
        r94 = r94 + 1;     // Catch: Throwable -> L44
    L74:
        r134.shortPhones.add(r66);     // Catch: Throwable -> L44
        r134.phones.add(r106);     // Catch: Throwable -> L44
        r134.phoneDeleted.add(0);     // Catch: Throwable -> L44
        if (r155 != 0) goto L84;
        String r16 = r7.getString(3);     // Catch: Throwable -> L44
        ArrayList<String> r03 = r134.phoneTypes;     // Catch: Throwable -> L44
        if (r16 != null) goto L80;
        r16 = LocaleController.getString(R.string.PhoneMobile);     // Catch: Throwable -> L44
    L80:
        r03.add(r16);     // Catch: Throwable -> L44
    L97:
        r5.put(r66, r134);     // Catch: Throwable -> L44
        r0 = this;
        r12 = r184;
        r6 = r212;
        r13 = r222;
        r4 = r24;
        goto L55
    L84:
        if (r155 != 1) goto L87;
        r134.phoneTypes.add(LocaleController.getString(R.string.PhoneHome));     // Catch: Throwable -> L44
        goto L97
    L87:
        if (r155 != 2) goto L90;
        r134.phoneTypes.add(LocaleController.getString(R.string.PhoneMobile));     // Catch: Throwable -> L44
        goto L97
    L90:
        if (r155 != 3) goto L93;
        r134.phoneTypes.add(LocaleController.getString(R.string.PhoneWork));     // Catch: Throwable -> L44
        goto L97
    L93:
        ArrayList<String> r35 = r134.phoneTypes;
        if (r155 != 12) goto L96;
        r35.add(LocaleController.getString(R.string.PhoneMain));     // Catch: Throwable -> L44
        goto L97
    L96:
        r35.add(LocaleController.getString(R.string.PhoneOther));     // Catch: Throwable -> L44
        goto L97
    L68:
        int r04 = r43.lastIndexOf(32);     // Catch: Throwable -> L44
        r184 = r12;
        if (r04 == (-1)) goto L71;
        r134.first_name = r43.substring(0, r04).trim();     // Catch: Throwable -> L44
        r134.last_name = r43.substring(r04 + 1).trim();     // Catch: Throwable -> L44
        goto L72
    L71:
        r134.first_name = r43;     // Catch: Throwable -> L44
        r134.last_name = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Throwable -> L44
        goto L72
    L64:
        r43 = r183.trim();     // Catch: Throwable -> L44
        goto L66
    L73:
        r24 = r4;
        r184 = r12;
        goto L74
    L46:
        r66 = r106;
        goto L43
    L32:
        r34 = false;
    L99:
        r21 = r6;
        r18 = r12;
        r22 = r13;
        r25 = r15;
        r02 = 2;
        r1 = 3;
        r7.close();     // Catch: Throwable -> L44 Exception -> L255
    L104:
        r42 = r88;
        r52 = r94;
        r32 = null;
        goto L257
    L102:
        r88 = null;
        r94 = 1;
    L100:
        th = th;
        r33 = null;
    L229:
        th = th;
        r33 = null;
    L231:
        th = th;
        r33 = null;
        r7 = null;
        goto L261
    L12:
        if (BuildVars.LOGS_ENABLED == false) goto L15;
        FileLog.d("app has no contacts permissions");
    L15:
        return new HashMap();
    }

    public void reloadContactsStatusesMaybe(boolean r7) {
    L9:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if (MessagesController.getMainSettings(this.currentAccount).getLong("lastReloadStatusTime", 0) < (System.currentTimeMillis() - 10800000)) goto L7;
        if (r7 == true) goto L7;
        return;
    L7:
        reloadContactsStatuses();     // Catch: Exception -> L9
    }

    public void resetImportedContacts() {
        TLRPC.TL_contacts_resetSaved r0 = new TLRPC.TL_contacts_resetSaved();
        getConnectionsManager().sendRequest(r0, new ContactsController$$ExternalSyntheticLambda46());
    }

    public void setDeleteAccountTTL(int r1) {
        this.deleteAccountTTL = r1;
    }

    public void setPrivacyRules(ArrayList<TLRPC.PrivacyRule> r2, int r3) {
        switch(r3) {
            case 0: goto L17;
            case 1: goto L16;
            case 2: goto L15;
            case 3: goto L14;
            case 4: goto L13;
            case 5: goto L12;
            case 6: goto L11;
            case 7: goto L10;
            case 8: goto L9;
            case 9: goto L8;
            case 10: goto L18;
            case 11: goto L7;
            case 12: goto L6;
            case 13: goto L5;
            case 14: goto L4;
            default: goto L18;
        };
    L4:
        this.musicPrivacyRules = r2;
        goto L18
    L5:
        this.noPaidMessagesPrivacyRules = r2;
        goto L18
    L6:
        this.giftsPrivacyRules = r2;
        goto L18
    L7:
        this.birthdayPrivacyRules = r2;
        goto L18
    L8:
        this.bioPrivacyRules = r2;
        goto L18
    L9:
        this.voiceMessagesRules = r2;
        goto L18
    L10:
        this.addedByPhonePrivacyRules = r2;
        goto L18
    L11:
        this.phonePrivacyRules = r2;
        goto L18
    L12:
        this.forwardsPrivacyRules = r2;
        goto L18
    L13:
        this.profilePhotoPrivacyRules = r2;
        goto L18
    L14:
        this.p2pPrivacyRules = r2;
        goto L18
    L15:
        this.callPrivacyRules = r2;
        goto L18
    L16:
        this.groupPrivacyRules = r2;
        goto L18
    L17:
        this.lastseenPrivacyRules = r2;
    L18:
        getNotificationCenter().postNotificationName(NotificationCenter.privacyRulesUpdated, new Object[0]);
        reloadContactsStatuses();
    }

    public void syncPhoneBookByAlert(HashMap<String, Contact> r8, boolean r9, boolean r10, boolean r11) {
        Utilities.globalQueue.postRunnable(new ContactsController$$ExternalSyntheticLambda29(this, r8, r9, r10, r11));
    }
}
