package org.telegram.ui.Adapters;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import org.telegram.PhoneFormat.PhoneFormat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.SearchAdapterHelper;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.ForegroundColorSpanThemable;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public abstract class SearchAdapter extends RecyclerListView.SelectionAdapter {
    private ArrayList<ContactEntry> allUnregistredContacts;
    private boolean allowBots;
    private boolean allowChats;
    private boolean allowPhoneNumbers;
    private boolean allowSelf;
    private boolean allowUsernameSearch;
    private long channelId;
    private LongSparseArray<TLRPC.User> ignoreUsers;
    public boolean includeLoading;
    public boolean includeSearch;
    private String lastQuery;
    private Context mContext;
    private boolean onlyMutual;
    private SearchAdapterHelper searchAdapterHelper;
    private boolean searchInProgress;
    private int searchPointer;
    private int searchReqId;
    private ArrayList<Object> searchResult;
    private ArrayList<CharSequence> searchResultNames;
    private Timer searchTimer;
    private LongSparseArray<TLRPC.User> selectedUsers;
    private ArrayList<ContactsController.Contact> unregistredContacts;
    int unregistredContactsHeaderRow;
    private boolean useUserCell;

    /* renamed from: org.telegram.ui.Adapters.SearchAdapter$1, reason: invalid class name */
    public class AnonymousClass1 implements SearchAdapterHelper.SearchAdapterHelperDelegate {
        final /* synthetic */ SearchAdapter this$0;

        public AnonymousClass1(SearchAdapter r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Adapters.SearchAdapterHelper.SearchAdapterHelperDelegate
        public LongSparseArray<TLRPC.User> getExcludeUsers() {
            return SearchAdapter.m5946$$Nest$fgetignoreUsers(this.this$0);
        }

        @Override // org.telegram.ui.Adapters.SearchAdapterHelper.SearchAdapterHelperDelegate
        public void onDataSetChanged(int r2) {
            this.this$0.notifyDataSetChanged();
            if (r2 == 0) goto L6;
            this.this$0.onSearchProgressChanged();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Adapters.SearchAdapter$2, reason: invalid class name */
    public class AnonymousClass2 extends TimerTask {
        final /* synthetic */ SearchAdapter this$0;
        final /* synthetic */ String val$query;

        public AnonymousClass2(SearchAdapter r1, String r2) {
            this.this$0 = r1;
            this.val$query = r2;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            SearchAdapter.m5947$$Nest$fgetsearchTimer(this.this$0).cancel();     // Catch: Exception -> L4
            SearchAdapter.m5948$$Nest$fputsearchTimer(this.this$0, null);     // Catch: Exception -> L4
        L6:
            SearchAdapter.m5949$$Nest$mprocessSearch(this.this$0, this.val$query);
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Adapters.SearchAdapter$3, reason: invalid class name */
    public class AnonymousClass3 extends View {
        final /* synthetic */ SearchAdapter this$0;

        public AnonymousClass3(SearchAdapter r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(52.0f), TLObject.FLAG_30));
        }
    }

    public static class ContactEntry {
        ContactsController.Contact contact;
        String q1;
        String q2;

        private ContactEntry() {
        }

        public /* synthetic */ ContactEntry(SearchAdapterIA r1) {
            this();
        }
    }

    public static /* synthetic */ void $r8$lambda$BayU6JSuHEJFxV_Rr9jyj4tPAlg(SearchAdapter r0, int r1, ArrayList r2, ArrayList r3, ArrayList r4) {
        r0.lambda$updateSearchResults$2(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$PRIbVpCC3AkYI3a9P55E7ClgKLE(SearchAdapter r0, String r1) {
        r0.lambda$processSearch$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZdPCF4b34PH_cs1T_LptIhYKYcU(SearchAdapter r0, String r1, int r2, ArrayList r3, int r4) {
        r0.lambda$processSearch$0(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetignoreUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m5946$$Nest$fgetignoreUsers(SearchAdapter r0) {
        return r0.ignoreUsers;
    }

    /* renamed from: -$$Nest$fgetsearchTimer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Timer m5947$$Nest$fgetsearchTimer(SearchAdapter r0) {
        return r0.searchTimer;
    }

    /* renamed from: -$$Nest$fputsearchTimer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5948$$Nest$fputsearchTimer(SearchAdapter r0, Timer r1) {
        r0.searchTimer = r1;
    }

    /* renamed from: -$$Nest$mprocessSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5949$$Nest$mprocessSearch(SearchAdapter r0, String r1) {
        r0.processSearch(r1);
    }

    public SearchAdapter(RecyclerListView r1, Context r2, LongSparseArray<TLRPC.User> r3, LongSparseArray<TLRPC.User> r4, boolean r5, boolean r6, boolean r7, boolean r8, boolean r9, boolean r10, int r11, Theme.ResourcesProvider r12) {
        this.searchResult = new ArrayList();
        this.searchResultNames = new ArrayList();
        this.unregistredContacts = new ArrayList();
        this.mContext = r2;
        this.ignoreUsers = r3;
        this.selectedUsers = r4;
        this.onlyMutual = r6;
        this.allowUsernameSearch = r5;
        this.allowChats = r7;
        this.allowBots = r8;
        this.channelId = r11;
        this.allowSelf = r9;
        this.allowPhoneNumbers = r10;
        SearchAdapterHelper r13 = new SearchAdapterHelper(true);
        this.searchAdapterHelper = r13;
        r13.setDelegate(new AnonymousClass1(this));
    }

    private /* synthetic */ void lambda$processSearch$0(String r22, int r23, ArrayList r24, int r25) {
        String r2 = r22.trim().toLowerCase();
        if (r2.length() != 0) goto L6;
        updateSearchResults(r23, new ArrayList(), new ArrayList(), this.unregistredContacts);
        return;
    L6:
        String r3 = LocaleController.getInstance().getTranslitString(r2);
        if (r2.equals(r3) == false) goto L9;
    L10:
        r3 = null;
    L11:
        int r4 = 0;
        int r6 = 1;
        if (r3 == null) goto L14;
        int r7 = 1;
    L15:
        int r72 = r7 + 1;
        String[] r8 = new String[r72];
        r8[0] = r2;
        if (r3 == null) goto L18;
        r8[1] = r3;
    L18:
        ArrayList<Object> r9 = new ArrayList();
        ArrayList<CharSequence> r10 = new ArrayList();
        ArrayList<ContactsController.Contact> r11 = new ArrayList();
        int r12 = 0;
    L20:
        if (r12 >= r24.size()) goto L74;
        TLRPC.TL_contact r15 = (TLRPC.TL_contact) r24.get(r12);
        int r222 = r4;
        int r17 = r6;
        String r16 = null;
        TLRPC.User r42 = MessagesController.getInstance(r25).getUser(Long.valueOf(r15.user_id));
        if (this.allowSelf == true) goto L29;
        if (r42.self == false) goto L29;
    L26:
        int r19 = r12;
    L27:
        r6 = r17;
    L73:
        r12 = r19 + 1;
        r4 = r222;
    L29:
        if (this.onlyMutual == true) goto L31;
    L32:
        LongSparseArray<TLRPC.User> r5 = this.ignoreUsers;
        int r62 = r12;
        if (r5 != null) goto L35;
    L37:
        int r52 = 3;
        String[] r122 = new String[3];
        r122[r222] = ContactsController.formatName(r42.first_name, r42.last_name).toLowerCase();
        String r13 = LocaleController.getInstance().getTranslitString(r122[r222]);
        r122[r17] = r13;
        if (r122[r222].equals(r13) == false) goto L41;
        r122[r17] = null;
    L41:
        if (UserObject.isReplyUser(r42) == false) goto L44;
        r122[2] = LocaleController.getString(R.string.RepliesTitle).toLowerCase();
    L46:
        int r132 = r222;
        int r18 = r132;
    L47:
        if (r132 >= r72) goto L36;
        String r152 = r8[r132];
        r19 = r62;
        int r63 = r222;
    L49:
        if (r63 >= r52) goto L59;
        String r53 = r122[r63];
        if (r53 != null) goto L53;
        int r20 = r63;
    L58:
        r63 = r20 + 1;
        r52 = 3;
        goto L49
    L53:
        if (r53.startsWith(r152) == true) goto L56;
        r20 = r63;
        if (r53.contains(" " + r152) == false) goto L58;
    L56:
        r18 = r17;
    L59:
        String r54 = UserObject.getPublicUsername(r42);
        if (r18 != 0) goto L65;
        if (r54 == null) goto L65;
        if (r54.startsWith(r152) == false) goto L65;
        int r55 = 2;
    L66:
        if (r55 != 0) goto L67;
        r132 = r132 + 1;
        r18 = r55;
        r62 = r19;
        r52 = 3;
        r16 = null;
        goto L47
    L67:
        r6 = r17;
        if (r55 != r6) goto L70;
        r10.add(AndroidUtilities.generateSearchName(r42.first_name, r42.last_name, r152));
    L71:
        r9.add(r42);
        goto L73
    L70:
        r10.add(AndroidUtilities.generateSearchName("@" + UserObject.getPublicUsername(r42), r16, "@" + r152));
    L65:
        r55 = r18;
    L36:
        r19 = r62;
        goto L27
    L44:
        if (r42.self == false) goto L46;
        r122[2] = LocaleController.getString(R.string.SavedMessages).toLowerCase();
        goto L46
    L35:
        if (r5.indexOfKey(r15.user_id) < 0) goto L37;
    L31:
        if (r42.mutual_contact == false) goto L26;
    L74:
        int r223 = r4;
        if (this.allUnregistredContacts != null) goto L79;
        this.allUnregistredContacts = new ArrayList();
        ArrayList<ContactsController.Contact> r43 = ContactsController.getInstance(r25).phoneBookContacts;
        int r56 = r43.size();
        int r64 = r223;
    L77:
        if (r64 >= r56) goto L79;
        ContactsController.Contact r73 = r43.get(r64);
        r64 = r64 + 1;
        ContactsController.Contact r74 = r73;
        ContactEntry r82 = new ContactEntry(null);
        r82.contact = r74;
        r82.q1 = (r74.first_name + " " + r74.last_name).toLowerCase();
        r82.q2 = (r74.last_name + " " + r74.first_name).toLowerCase();
        this.allUnregistredContacts.add(r82);
    L79:
        int r44 = r223;
    L81:
        if (r44 >= this.allUnregistredContacts.size()) goto L94;
        ContactEntry r57 = this.allUnregistredContacts.get(r44);
        if (r3 == null) goto L89;
        if (r57.q1.toLowerCase().contains(r3) == false) goto L87;
    L92:
        r11.add(r57.contact);
    L93:
        r44 = r44 + 1;
        goto L81
    L87:
        if (r57.q1.toLowerCase().contains(r3) == true) goto L92;
    L89:
        if (r57.q1.toLowerCase().contains(r2) == true) goto L92;
        if (r57.q1.toLowerCase().contains(r2) == false) goto L93;
    L94:
        updateSearchResults(r23, r9, r10, r11);
        return;
    L14:
        r7 = 0;
        goto L15
    L9:
        if (r3.length() != 0) goto L11;
        goto L10
    }

    private /* synthetic */ void lambda$processSearch$1(String r15) {
        this.lastQuery = r15;
        if (this.allowUsernameSearch == false) goto L5;
        this.searchAdapterHelper.queryServerSearch(r15, true, this.allowChats, this.allowBots, this.allowSelf, false, this.channelId, this.allowPhoneNumbers, -1, 1);
    L5:
        int r5 = UserConfig.selectedAccount;
        ArrayList r4 = new ArrayList(ContactsController.getInstance(r5).contacts);
        this.searchInProgress = true;
        int r3 = this.searchPointer;
        this.searchPointer = r3 + 1;
        this.searchReqId = r3;
        notifyDataSetChanged();
        Utilities.searchQueue.postRunnable(new SearchAdapter$$ExternalSyntheticLambda1(this, r15, r3, r4, r5));
    }

    private /* synthetic */ void lambda$updateSearchResults$2(int r2, ArrayList r3, ArrayList r4, ArrayList r5) {
        if (r2 != this.searchReqId) goto L6;
        this.searchResult = r3;
        this.searchResultNames = r4;
        this.unregistredContacts = r5;
        this.searchAdapterHelper.mergeResults(r3);
        this.searchInProgress = false;
        notifyDataSetChanged();
        onSearchProgressChanged();
        return;
    }

    private void processSearch(String r2) {
        AndroidUtilities.runOnUIThread(new SearchAdapter$$ExternalSyntheticLambda0(this, r2));
    }

    private void updateSearchResults(int r7, ArrayList<Object> r8, ArrayList<CharSequence> r9, ArrayList<ContactsController.Contact> r10) {
        AndroidUtilities.runOnUIThread(new SearchAdapter$$ExternalSyntheticLambda2(this, r7, r8, r9, r10));
    }

    public Object getItem(int r5) {
        int r0 = this.searchResult.size();
        int r1 = this.unregistredContacts.size();
        int r2 = this.searchAdapterHelper.getGlobalSearch().size();
        int r3 = this.searchAdapterHelper.getPhoneSearch().size();
        if (r5 < 0) goto L7;
        if (r5 >= r0) goto L7;
        return this.searchResult.get(r5);
    L7:
        int r52 = r5 - r0;
        if (r1 <= 0) goto L16;
        if (r52 != 0) goto L11;
        return null;
    L11:
        if (r52 <= 0) goto L15;
        if (r52 > r1) goto L15;
        return this.unregistredContacts.get(r52 - 1);
    L15:
        r52 = r52 - (r1 + 1);
    L16:
        if (r52 < 0) goto L20;
        if (r52 >= r3) goto L20;
        return this.searchAdapterHelper.getPhoneSearch().get(r52);
    L20:
        int r53 = r52 - r3;
        if (r53 <= 0) goto L25;
        if (r53 > r2) goto L25;
        return this.searchAdapterHelper.getGlobalSearch().get(r53 - 1);
    L25:
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        this.unregistredContactsHeaderRow = -1;
        int r0 = this.searchResult.size();
        if (this.includeSearch == false) goto L6;
        r0 = r0 + 1;
    L6:
        if (this.unregistredContacts.isEmpty() == true) goto L8;
        this.unregistredContactsHeaderRow = r0;
        r0 = r0 + (this.unregistredContacts.size() + 1);
    L8:
        int r1 = this.searchAdapterHelper.getGlobalSearch().size();
        if (r1 == 0) goto L11;
        r0 = r0 + (r1 + 1);
    L11:
        int r12 = this.searchAdapterHelper.getPhoneSearch().size();
        if (r12 == 0) goto L15;
        r0 = r0 + r12;
    L15:
        if (this.includeLoading == true) goto L17;
        return r0;
    L17:
        if (searchInProgress() == true) goto L19;
        return r0;
    L19:
        return r0 + 3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int r4) {
        if (this.includeSearch == false) goto L9;
        if (r4 != 0) goto L7;
        return 4;
    L7:
        r4 = r4 - 1;
    L9:
        if (this.includeLoading == true) goto L11;
    L16:
        Object r3 = getItem(r4);
        if (r3 != null) goto L20;
        return 1;
    L20:
        if ((r3 instanceof String) == false) goto L27;
        if ("section".equals((String) r3) == false) goto L24;
        return 1;
    L24:
        return 2;
    L27:
        if ((r3 instanceof ContactsController.Contact) == false) goto L29;
        return 3;
    L29:
        return 0;
    L11:
        if (searchInProgress() == false) goto L16;
        if (r4 < ((getItemCount() - (this.includeSearch ? 1 : 0)) - 3)) goto L16;
        return 5;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r1) {
        int r0 = r1.getItemViewType();
        if (r0 != 0) goto L5;
        return true;
    L5:
        if (r0 != 2) goto L7;
        return true;
    L7:
        if (r0 == 3) goto L14;
        return false;
    L14:
        return true;
    }

    public boolean isGlobalSearch(int r7) {
        int r0 = this.searchResult.size();
        int r1 = this.unregistredContacts.size();
        int r2 = this.searchAdapterHelper.getGlobalSearch().size();
        int r6 = this.searchAdapterHelper.getPhoneSearch().size();
        if (r7 < 0) goto L7;
        if (r7 >= r0) goto L7;
        return false;
    L7:
        if (r7 <= r0) goto L12;
        if (r7 >= ((r0 + r1) + 1)) goto L12;
        return false;
    L12:
        if (r7 <= ((r0 + r1) + 1)) goto L17;
        if (r7 >= (((r0 + r6) + r1) + 1)) goto L17;
        return false;
    L17:
        if (r7 > (((r0 + r6) + r1) + 1)) goto L19;
    L21:
        return false;
    L19:
        if (r7 > ((((r2 + r6) + r0) + r1) + 1)) goto L21;
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r14, int r15) {
        if (this.includeSearch == false) goto L7;
        if (r15 == 0) goto L123;
        r15 = r15 - 1;
        goto L7
    L123:
        return;
    L7:
        int r0 = r14.getItemViewType();
        boolean r2 = true;
        if (r0 == 0) goto L29;
        if (r0 != 1) goto L11;
        GraySectionCell r142 = (GraySectionCell) r14.itemView;
        if (r15 != this.unregistredContactsHeaderRow) goto L24;
        r142.setText(LocaleController.getString(R.string.InviteToTelegramShort));
        return;
    L24:
        if (getItem(r15) != null) goto L27;
        r142.setText(LocaleController.getString(R.string.GlobalSearch));
        return;
    L27:
        r142.setText(LocaleController.getString(R.string.PhoneNumberSearch));
        return;
    L11:
        if (r0 != 2) goto L13;
        String r13 = (String) getItem(r15);
        TextCell r143 = (TextCell) r14.itemView;
        r143.setColors(-1, Theme.key_windowBackgroundWhiteBlueText2);
        r143.setText(LocaleController.formatString(R.string.AddContactByPhone, new Object[]{PhoneFormat.getInstance().format("+" + r13)}), false);
        return;
    L13:
        if (r0 != 3) goto L124;
        ProfileSearchCell r5 = (ProfileSearchCell) r14.itemView;
        ContactsController.Contact r6 = (ContactsController.Contact) getItem(r15);
        r5.setData(r6, null, ContactsController.formatName(r6.first_name, r6.last_name), PhoneFormat.getInstance().format("+" + r6.shortPhones.get(0)), false, false);
        return;
    L124:
        return;
    L29:
        TLObject r52 = (TLObject) getItem(r15);
        if (r52 == null) goto L121;
        boolean r4 = r52 instanceof TLRPC.User;
        CharSequence r62 = null;
        if (r4 == false) goto L55;
        TLRPC.User r02 = (TLRPC.User) r52;
        String r7 = UserObject.getPublicUsername(r02);
        if (r7 != null) goto L36;
    L52:
        long r8 = r02.id;
        boolean r10 = r02.self;
    L53:
        long r11 = r8;
        if (r15 >= this.searchResult.size()) goto L70;
        CharSequence r152 = this.searchResultNames.get(r15);
        if (r152 == null) goto L68;
        if (r7 == null) goto L68;
        if (r7.length() <= 0) goto L68;
        if (r152.toString().startsWith("@".concat(r7)) == false) goto L68;
        CharSequence r82 = r152;
    L92:
        boolean r153 = this.useUserCell;
        View r144 = r14.itemView;
        if (r153 == false) goto L105;
        UserCell r145 = (UserCell) r144;
        if (r4 == true) goto L97;
    L99:
        boolean r154 = false;
    L100:
        r145.setMutual(r154);
        r145.setData(r52, r62, r82, 0);
        if (this.selectedUsers.indexOfKey(r11) >= 0) goto L104;
        r2 = false;
    L104:
        r145.setChecked(r2, false);
        return;
    L97:
        if (((TLRPC.User) r52).mutual_contact == false) goto L99;
        r154 = true;
        goto L100
    L105:
        ProfileSearchCell r42 = (ProfileSearchCell) r144;
        if (r10 == false) goto L108;
        r62 = LocaleController.getString(R.string.SavedMessages);
    L108:
        r42.setData(r52, null, r62, r82, false, r10);
        if (this.selectedUsers.indexOfKey(r11) >= 0) goto L112;
        r2 = false;
    L112:
        r42.setChecked(r2, false);
        return;
    L68:
        r82 = null;
        r62 = r152;
        goto L92
    L70:
        if (r15 <= this.searchResult.size()) goto L91;
        if (r7 == null) goto L91;
        String r155 = this.searchAdapterHelper.getLastFoundUsername();
        if (r155 != null) goto L75;
    L114:
        SpannableStringBuilder r03 = new SpannableStringBuilder();     // Catch: Exception -> L87
        r03.append("@");     // Catch: Exception -> L87
        r03.append(r7);     // Catch: Exception -> L87
        if (r155 == null) goto L89;
        int r83 = AndroidUtilities.indexOfIgnoreCase(r7, r155);     // Catch: Exception -> L87
        if (r83 == (-1)) goto L89;
        int r156 = r155.length();     // Catch: Exception -> L87
        if (r83 != 0) goto L84;
        r156 = r156 + 1;     // Catch: Exception -> L87
    L85:
        r03.setSpan(new ForegroundColorSpanThemable(Theme.key_windowBackgroundWhiteBlueText4), r83, r156 + r83, 33);     // Catch: Exception -> L87
        goto L89
    L84:
        r83 = r83 + 1;     // Catch: Exception -> L87
    L89:
        r82 = r03;
    L87:
        e = move-exception;
        FileLog.e(e);
        r82 = r7;
        goto L92
    L75:
        if (r155.startsWith("@") == false) goto L114;
        r155 = r155.substring(1);
    L91:
        r82 = null;
        goto L92
    L36:
        if (this.lastQuery == null) goto L52;
        if (r7.toLowerCase().contains(this.lastQuery.toLowerCase()) == true) goto L52;
        if (r02.usernames == null) goto L52;
        int r84 = 0;
    L43:
        if (r84 >= r02.usernames.size()) goto L52;
        TLRPC.TL_username r9 = r02.usernames.get(r84);
        if (r9 == null) goto L51;
        if (r9.active == false) goto L51;
        if (r9.username.toLowerCase().contains(this.lastQuery.toLowerCase()) == false) goto L51;
        r7 = r9.username;
    L51:
        r84 = r84 + 1;
        goto L43
    L55:
        if ((r52 instanceof TLRPC.Chat) == false) goto L57;
        TLRPC.Chat r04 = (TLRPC.Chat) r52;
        r7 = ChatObject.getPublicUsername(r04);
        r8 = r04.id;
        r10 = false;
        goto L53
    L57:
        r8 = 0;
        r10 = false;
        r7 = null;
        goto L53
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
        if (r4 == 0) goto L17;
        if (r4 != 1) goto L6;
        GraySectionCell r42 = new GraySectionCell(this.mContext, 26, null);
        r42.setNoBackground(true);
        View r43 = r42;
    L21:
        return new RecyclerListView.Holder(r43);
    L6:
        if (r4 != 3) goto L8;
        ProfileSearchCell r44 = new ProfileSearchCell(this.mContext);
        r44.setCallCellStyle();
        r43 = r44;
        goto L21
    L8:
        if (r4 != 4) goto L10;
        View r45 = new AnonymousClass3(this, this.mContext);
        r45.setId(9);
        r45.setTag(-33024);
        r43 = r45;
        goto L21
    L10:
        if (r4 == 5) goto L12;
        r43 = new TextCell(this.mContext, 16, false);
        goto L21
    L12:
        FlickerLoadingView r46 = new FlickerLoadingView(this.mContext);
        r46.setIsSingleCell(true);
        r46.setViewType(29);
        r46.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        r43 = r46;
        goto L21
    L17:
        if (this.useUserCell == false) goto L19;
        r43 = new UserCell(this.mContext, 1, 1, false);
        goto L21
    L19:
        ProfileSearchCell r47 = new ProfileSearchCell(this.mContext);
        r47.setCallCellStyle();
        r43 = r47;
        goto L21
    }

    public abstract void onSearchProgressChanged();

    public void searchDialogs(String r14) {
        Timer r0 = this.searchTimer;     // Catch: Exception -> L6
        if (r0 == null) goto L8;
        r0.cancel();     // Catch: Exception -> L6
    L8:
        this.searchResult.clear();
        this.unregistredContacts.clear();
        this.searchResultNames.clear();
        if (this.allowUsernameSearch == false) goto L11;
        this.searchAdapterHelper.queryServerSearch(null, true, this.allowChats, this.allowBots, this.allowSelf, false, this.channelId, this.allowPhoneNumbers, 0, 0);
    L11:
        notifyDataSetChanged();
        if (TextUtils.isEmpty(r14) == true) goto L17;
        Timer r1 = new Timer();
        this.searchTimer = r1;
        r1.schedule(new AnonymousClass2(this, r14), 200, 300);
        return;
    L17:
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    public boolean searchInProgress() {
        if (this.searchInProgress == false) goto L5;
        return true;
    L5:
        if (this.searchAdapterHelper.isSearchInProgress() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void setUseUserCell(boolean r1) {
        this.useUserCell = r1;
    }
}
