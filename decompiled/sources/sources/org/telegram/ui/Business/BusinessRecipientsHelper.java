package org.telegram.ui.Business;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.UsersSelectActivity;

/* loaded from: classes6.dex */
public class BusinessRecipientsHelper {
    public final ArrayList<Long> alwaysShow;
    public boolean bot;
    public final Context context;
    public final int currentAccount;
    private TL_account.TL_businessBotRecipients currentValue;
    private boolean doNotExcludeNewChats;
    public boolean exclude;
    public boolean excludeExpanded;
    public int excludeFlags;
    public final BaseFragment fragment;
    public boolean includeExpanded;
    public int includeFlags;
    public final ArrayList<Long> neverShow;
    public final Theme.ResourcesProvider resourcesProvider;
    private int shiftDp;
    public final Runnable update;

    /* renamed from: $r8$lambda$9sSeIOT5ZZDmryqzNf-oDcQJbVs, reason: not valid java name */
    public static /* synthetic */ void m6333$r8$lambda$9sSeIOT5ZZDmryqzNfoDcQJbVs(BusinessRecipientsHelper r0, int r1, boolean r2, UItem r3, AlertDialog r4, int r5) {
        r0.lambda$onClick$0(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$HVJhmHXRzlHykK729iMzpLHjQgo(BusinessRecipientsHelper r0, boolean r1, ArrayList r2, int r3) {
        r0.lambda$selectChatsFor$1(r1, r2, r3);
    }

    public BusinessRecipientsHelper(Context r2, int r3, Runnable r4, Theme.ResourcesProvider r5) {
        this.alwaysShow = new ArrayList();
        this.neverShow = new ArrayList();
        this.shiftDp = -4;
        this.context = r2;
        this.currentAccount = r3;
        this.fragment = null;
        this.update = r4;
        this.resourcesProvider = r5;
    }

    public BusinessRecipientsHelper(BaseFragment r2, Runnable r3) {
        this.alwaysShow = new ArrayList();
        this.neverShow = new ArrayList();
        this.shiftDp = -4;
        this.context = r2.getContext();
        this.currentAccount = r2.getCurrentAccount();
        this.fragment = r2;
        this.update = r3;
        this.resourcesProvider = r2.getResourceProvider();
    }

    private int getFlag(String r5) {
        r5.getClass();
        char r3 = 65535;
        switch(r5.hashCode()) {
            case -1197490811: goto L18;
            case -567451565: goto L14;
            case -268161860: goto L10;
            case 151051367: goto L6;
            default: goto L21;
        };
    L21:
        switch(r3) {
            case 0: goto L27;
            case 1: goto L25;
            case 2: goto L24;
            case 3: goto L23;
            default: goto L22;
        };
    L22:
        return 0;
    L23:
        return 1;
    L24:
        return 2;
    L25:
        return 4;
    L27:
        return 8;
    L6:
        if (r5.equals("existing_chats") == false) goto L21;
        r3 = 3;
        goto L21
    L10:
        if (r5.equals("new_chats") == false) goto L21;
        r3 = 2;
        goto L21
    L14:
        if (r5.equals("contacts") == false) goto L21;
        r3 = 1;
        goto L21
    L18:
        if (r5.equals("non_contacts") == false) goto L21;
        r3 = 0;
        goto L21
    }

    private String getFlagName(int r1) {
        if (r1 == 1) goto L15;
        if (r1 == 2) goto L13;
        if (r1 == 4) goto L11;
        return LocaleController.getString(R.string.FilterNonContacts);
    L11:
        return LocaleController.getString(R.string.FilterContacts);
    L13:
        return LocaleController.getString(R.string.FilterNewChats);
    L15:
        return LocaleController.getString(R.string.FilterExistingChats);
    }

    private /* synthetic */ void lambda$onClick$0(int r1, boolean r2, UItem r3, AlertDialog r4, int r5) {
        if (r1 == 0) goto L6;
        if (r2 == true) goto L5;
        int r22 = this.excludeFlags;
        this.excludeFlags = (~r1) & r22;
    L10:
        this.update.run();
        return;
    L5:
        int r23 = this.includeFlags;
        this.includeFlags = (~r1) & r23;
        goto L10
    L6:
        if (r2 == true) goto L8;
        ArrayList<Long> r12 = this.neverShow;
    L9:
        r12.remove(Long.valueOf(r3.dialogId));
        goto L10
    L8:
        r12 = this.alwaysShow;
        goto L9
    }

    private /* synthetic */ void lambda$selectChatsFor$1(boolean r2, ArrayList r3, int r4) {
        int r0 = 0;
        if (r2 == false) goto L8;
        this.includeFlags = r4;
        this.alwaysShow.clear();
        this.alwaysShow.addAll(r3);
    L6:
        if (r0 >= this.alwaysShow.size()) goto L12;
        this.neverShow.remove(this.alwaysShow.get(r0));
        r0 = r0 + 1;
    L12:
        this.update.run();
        return;
    L8:
        this.excludeFlags = r4;
        this.neverShow.clear();
        this.neverShow.addAll(r3);
    L10:
        if (r0 >= this.neverShow.size()) goto L12;
        this.alwaysShow.remove(this.neverShow.get(r0));
        r0 = r0 + 1;
        goto L10
    }

    private void selectChatsFor(boolean r5) {
        if (r5 == false) goto L4;
        ArrayList<Long> r0 = this.alwaysShow;
    L5:
        UsersSelectActivity r02 = new UsersSelectActivity(r5, r0, getFlags()).asPrivateChats();
        if (this.bot == true) goto L8;
    L11:
        boolean r1 = false;
    L12:
        r02.noChatTypes = r1;
        r02.allowSelf = false;
        if (r5 == false) goto L15;
    L17:
        boolean r12 = false;
    L18:
        r02.doNotNewChats = r12;
        r02.setDelegate(new BusinessRecipientsHelper$$ExternalSyntheticLambda1(this, r5));
        BaseFragment r4 = this.fragment;
        if (r4 == null) goto L22;
        r4.presentFragment(r02);
        return;
    L22:
        BaseFragment r42 = LaunchActivity.getSafeLastFragment();
        if (r42 != null) goto L25;
        return;
    L25:
        BaseFragment.BottomSheetParams r52 = new BaseFragment.BottomSheetParams();
        r52.transitionFromLeft = true;
        r52.allowNestedScroll = false;
        r42.showAsSheet(r02, r52);
        return;
    L15:
        if (this.doNotExcludeNewChats == false) goto L17;
        r12 = true;
        goto L18
    L8:
        if (this.exclude == true) goto L11;
        if (r5 == true) goto L11;
        r1 = true;
        goto L12
    L4:
        r0 = this.neverShow;
        goto L5
    }

    public void doNotExcludeNewChats() {
        this.doNotExcludeNewChats = true;
    }

    public void fillItems(ArrayList<UItem> r2, UniversalAdapter r3) {
        fillItems(r2, r3, true);
    }

    public void fillItems(ArrayList<UItem> r9, UniversalAdapter r10, boolean r11) {
        r10.whiteSectionStart();
        int r0 = getFlags();
        boolean r1 = this.exclude;
        String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 == false) goto L5;
    L40:
        boolean r12 = this.bot;
        if (r12 == false) goto L43;
    L44:
        if (r12 == false) goto L48;
        if (this.exclude == true) goto L48;
    L72:
        if (this.neverShow.isEmpty() == true) goto L78;
        if (TextUtils.isEmpty(r4) == true) goto L76;
        r4 = r4 + " + " + this.neverShow.size();
        goto L78
    L76:
        r4 = r4 + LocaleController.formatPluralStringComma("Chats", this.neverShow.size());
    L78:
        if (TextUtils.isEmpty(r4) == false) goto L80;
        r4 = LocaleController.getString(R.string.BusinessChatsExcludedAdd2);
    L80:
        r9.add(UItem.asButton(103, LocaleController.getString(R.string.BusinessChatsExcluded), r4).setEnabled(r11));
    L81:
        r10.whiteSectionEnd();
        return;
    L48:
        if ((r0 & 1) == 0) goto L54;
        if (TextUtils.isEmpty(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L52;
        r4 = ", ";
    L52:
        r4 = r4 + LocaleController.getString(R.string.FilterExistingChats);
    L54:
        if ((r0 & 2) == 0) goto L60;
        if (TextUtils.isEmpty(r4) == true) goto L58;
        r4 = r4.concat(", ");
    L58:
        r4 = r4 + LocaleController.getString(R.string.FilterNewChats);
    L60:
        if ((r0 & 4) == 0) goto L66;
        if (TextUtils.isEmpty(r4) == true) goto L64;
        r4 = r4.concat(", ");
    L64:
        r4 = r4 + LocaleController.getString(R.string.FilterContacts);
    L66:
        if ((r0 & 8) == 0) goto L72;
        if (TextUtils.isEmpty(r4) == true) goto L70;
        r4 = r4.concat(", ");
    L70:
        r4 = r4 + LocaleController.getString(R.string.FilterNonContacts);
        goto L72
    L43:
        if (this.exclude == false) goto L81;
    L5:
        if ((r0 & 1) != 0) goto L7;
        String r13 = _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        if ((r0 & 2) == 0) goto L19;
        if (TextUtils.isEmpty(r13) == true) goto L17;
        r13 = r13.concat(", ");
    L17:
        r13 = r13 + LocaleController.getString(R.string.FilterNewChats);
    L19:
        if ((r0 & 4) == 0) goto L25;
        if (TextUtils.isEmpty(r13) == true) goto L23;
        r13 = r13.concat(", ");
    L23:
        r13 = r13 + LocaleController.getString(R.string.FilterContacts);
    L25:
        if ((r0 & 8) == 0) goto L31;
        if (TextUtils.isEmpty(r13) == true) goto L29;
        r13 = r13.concat(", ");
    L29:
        r13 = r13 + LocaleController.getString(R.string.FilterNonContacts);
    L31:
        if (this.alwaysShow.isEmpty() == true) goto L37;
        if (TextUtils.isEmpty(r13) == true) goto L35;
        r13 = r13 + " + " + this.alwaysShow.size();
        goto L37
    L35:
        r13 = r13 + LocaleController.formatPluralStringComma("Chats", this.alwaysShow.size());
    L37:
        if (TextUtils.isEmpty(r13) == false) goto L39;
        r13 = LocaleController.getString(R.string.BusinessChatsIncludedAdd2);
    L39:
        r9.add(UItem.asButton(101, LocaleController.getString(R.string.BusinessChatsIncluded), r13).setEnabled(r11));
        goto L40
    L7:
        if (TextUtils.isEmpty(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L9;
        String r14 = ", ";
    L10:
        r13 = r14 + LocaleController.getString(R.string.FilterExistingChats);
        goto L13
    L9:
        r14 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L10
    }

    public TL_account.TL_inputBusinessBotRecipients getBotInputValue() {
        TL_account.TL_inputBusinessBotRecipients r0 = new TL_account.TL_inputBusinessBotRecipients();
        int r1 = getFlags();
        r0.flags = r1 & (-49);
        int r3 = 0;
        boolean r4 = true;
        if ((r1 & 1) == 0) goto L5;
        boolean r2 = true;
    L6:
        r0.existing_chats = r2;
        if ((r1 & 2) == 0) goto L9;
        boolean r22 = true;
    L10:
        r0.new_chats = r22;
        if ((r1 & 4) == 0) goto L13;
        boolean r23 = true;
    L14:
        r0.contacts = r23;
        if ((r1 & 8) != 0) goto L18;
        r4 = false;
    L18:
        r0.non_contacts = r4;
        boolean r12 = this.exclude;
        r0.exclude_selected = r12;
        if (r12 == false) goto L21;
        ArrayList<Long> r13 = this.neverShow;
    L23:
        if (r13.isEmpty() == true) goto L33;
        MessagesController r24 = MessagesController.getInstance(UserConfig.selectedAccount);
        r0.flags |= 16;
        int r5 = 0;
    L26:
        if (r5 >= r13.size()) goto L33;
        TLRPC.InputUser r6 = r24.getInputUser(r13.get(r5).longValue());
        if (r6 != null) goto L30;
        FileLog.e("businessRecipientsHelper: user not found " + r13.get(r5));
    L31:
        r5 = r5 + 1;
        goto L26
    L30:
        r0.users.add(r6);
    L33:
        if (this.exclude == true) goto L42;
        MessagesController r14 = MessagesController.getInstance(UserConfig.selectedAccount);
        r0.flags |= 64;
    L36:
        if (r3 >= this.neverShow.size()) goto L42;
        TLRPC.InputUser r25 = r14.getInputUser(this.neverShow.get(r3).longValue());
        if (r25 != null) goto L40;
        FileLog.e("businessRecipientsHelper: user not found " + this.neverShow.get(r3));
    L41:
        r3 = r3 + 1;
        goto L36
    L40:
        r0.exclude_users.add(r25);
    L42:
        return r0;
    L21:
        r13 = this.alwaysShow;
        goto L23
    L13:
        r23 = false;
        goto L14
    L9:
        r22 = false;
        goto L10
    L5:
        r2 = false;
        goto L6
    }

    public TL_account.TL_businessBotRecipients getBotValue() {
        TL_account.TL_businessBotRecipients r0 = new TL_account.TL_businessBotRecipients();
        int r1 = getFlags();
        r0.flags = r1 & (-49);
        int r3 = 0;
        boolean r4 = true;
        if ((r1 & 1) == 0) goto L5;
        boolean r2 = true;
    L6:
        r0.existing_chats = r2;
        if ((r1 & 2) == 0) goto L9;
        boolean r22 = true;
    L10:
        r0.new_chats = r22;
        if ((r1 & 4) == 0) goto L13;
        boolean r23 = true;
    L14:
        r0.contacts = r23;
        if ((r1 & 8) != 0) goto L18;
        r4 = false;
    L18:
        r0.non_contacts = r4;
        boolean r12 = this.exclude;
        r0.exclude_selected = r12;
        if (r12 == false) goto L21;
        ArrayList<Long> r13 = this.neverShow;
    L23:
        if (r13.isEmpty() == true) goto L33;
        MessagesController r24 = MessagesController.getInstance(UserConfig.selectedAccount);
        r0.flags |= 16;
        int r5 = 0;
    L26:
        if (r5 >= r13.size()) goto L33;
        if (r24.getInputUser(r13.get(r5).longValue()) != null) goto L30;
        FileLog.e("businessRecipientsHelper: user not found " + r13.get(r5));
    L31:
        r5 = r5 + 1;
        goto L26
    L30:
        r0.users.add(r13.get(r5));
    L33:
        if (this.exclude == true) goto L42;
        MessagesController r14 = MessagesController.getInstance(UserConfig.selectedAccount);
        r0.flags |= 64;
    L36:
        if (r3 >= this.neverShow.size()) goto L42;
        if (r14.getInputUser(this.neverShow.get(r3).longValue()) != null) goto L40;
        FileLog.e("businessRecipientsHelper: user not found " + this.neverShow.get(r3));
    L41:
        r3 = r3 + 1;
        goto L36
    L40:
        r0.users.add(this.neverShow.get(r3));
    L42:
        return r0;
    L21:
        r13 = this.alwaysShow;
        goto L23
    L13:
        r23 = false;
        goto L14
    L9:
        r22 = false;
        goto L10
    L5:
        r2 = false;
        goto L6
    }

    public int getFlags() {
        if (this.exclude == false) goto L7;
        return this.excludeFlags;
    L7:
        return this.includeFlags;
    }

    public TL_account.TL_inputBusinessRecipients getInputValue() {
        TL_account.TL_inputBusinessRecipients r0 = new TL_account.TL_inputBusinessRecipients();
        int r1 = getFlags();
        r0.flags = r1 & (-49);
        int r3 = 0;
        boolean r4 = true;
        if ((r1 & 1) == 0) goto L5;
        boolean r2 = true;
    L6:
        r0.existing_chats = r2;
        if ((r1 & 2) == 0) goto L9;
        boolean r22 = true;
    L10:
        r0.new_chats = r22;
        if ((r1 & 4) == 0) goto L13;
        boolean r23 = true;
    L14:
        r0.contacts = r23;
        if ((r1 & 8) != 0) goto L18;
        r4 = false;
    L18:
        r0.non_contacts = r4;
        boolean r12 = this.exclude;
        r0.exclude_selected = r12;
        if (r12 == false) goto L21;
        ArrayList<Long> r6 = this.neverShow;
    L23:
        if (r6.isEmpty() == true) goto L32;
        MessagesController r13 = MessagesController.getInstance(UserConfig.selectedAccount);
        r0.flags |= 16;
    L26:
        if (r3 >= r6.size()) goto L32;
        TLRPC.InputUser r24 = r13.getInputUser(r6.get(r3).longValue());
        if (r24 != null) goto L30;
        FileLog.e("businessRecipientsHelper: user not found " + r6.get(r3));
    L31:
        r3 = r3 + 1;
        goto L26
    L30:
        r0.users.add(r24);
    L32:
        return r0;
    L21:
        r6 = this.alwaysShow;
        goto L23
    L13:
        r23 = false;
        goto L14
    L9:
        r22 = false;
        goto L10
    L5:
        r2 = false;
        goto L6
    }

    public TL_account.TL_businessRecipients getValue() {
        TL_account.TL_businessRecipients r0 = new TL_account.TL_businessRecipients();
        int r1 = getFlags();
        r0.flags = r1 & (-49);
        int r3 = 0;
        boolean r4 = true;
        if ((r1 & 1) == 0) goto L5;
        boolean r2 = true;
    L6:
        r0.existing_chats = r2;
        if ((r1 & 2) == 0) goto L9;
        boolean r22 = true;
    L10:
        r0.new_chats = r22;
        if ((r1 & 4) == 0) goto L13;
        boolean r23 = true;
    L14:
        r0.contacts = r23;
        if ((r1 & 8) != 0) goto L18;
        r4 = false;
    L18:
        r0.non_contacts = r4;
        boolean r12 = this.exclude;
        r0.exclude_selected = r12;
        if (r12 == false) goto L21;
        ArrayList<Long> r6 = this.neverShow;
    L23:
        if (r6.isEmpty() == true) goto L32;
        MessagesController r13 = MessagesController.getInstance(UserConfig.selectedAccount);
        r0.flags |= 16;
    L26:
        if (r3 >= r6.size()) goto L32;
        if (r13.getInputUser(r6.get(r3).longValue()) != null) goto L30;
        FileLog.e("businessRecipientsHelper: user not found " + r6.get(r3));
    L31:
        r3 = r3 + 1;
        goto L26
    L30:
        r0.users.add(r6.get(r3));
    L32:
        return r0;
    L21:
        r6 = this.alwaysShow;
        goto L23
    L13:
        r23 = false;
        goto L14
    L9:
        r22 = false;
        goto L10
    L5:
        r2 = false;
        goto L6
    }

    public boolean hasChanges() {
        TL_account.TL_businessBotRecipients r0 = this.currentValue;
        if (r0 != null) goto L6;
        return true;
    L6:
        if (r0.exclude_selected == this.exclude) goto L9;
        return true;
    L9:
        if ((r0.flags & (-49)) == getFlags()) goto L12;
        return true;
    L12:
        if (this.exclude == false) goto L14;
        ArrayList<Long> r02 = this.neverShow;
    L16:
        if (r02.size() == this.currentValue.users.size()) goto L18;
        return true;
    L18:
        int r3 = 0;
    L20:
        if (r3 >= r02.size()) goto L26;
        if (this.currentValue.users.contains(r02.get(r3)) == false) goto L23;
        r3 = r3 + 1;
        goto L20
    L23:
        return true;
    L26:
        if (this.bot == true) goto L28;
    L39:
        return false;
    L28:
        if (this.exclude == true) goto L39;
        if (this.neverShow.size() == this.currentValue.exclude_users.size()) goto L32;
        return true;
    L32:
        int r03 = 0;
    L34:
        if (r03 >= this.neverShow.size()) goto L39;
        if (this.currentValue.exclude_users.contains(this.neverShow.get(r03)) == false) goto L37;
        r03 = r03 + 1;
        goto L34
    L37:
        return true;
    L14:
        r02 = this.alwaysShow;
        goto L16
    }

    public boolean onClick(UItem r8) {
        int r0 = r8.id;
        boolean r1 = false;
        int r12 = 0;
        if (r0 != 101) goto L5;
    L38:
        if (r0 != 101) goto L40;
        r1 = true;
    L40:
        selectChatsFor(r1);
        return true;
    L5:
        if (r0 == 103) goto L38;
        if (r0 != 102) goto L12;
        this.includeExpanded = true;
        this.update.run();
        return true;
    L12:
        if (r0 != 104) goto L16;
        this.excludeExpanded = true;
        this.update.run();
        return true;
    L16:
        if (r8.viewType != 11) goto L37;
        boolean r02 = r8.include;
        String r3 = r8.chatType;
        if (r3 == null) goto L21;
        r12 = getFlag(r3);
    L21:
        if (r12 != 0) goto L23;
        String r32 = MessagesController.getInstance(this.currentAccount).getPeerName(r8.dialogId);
    L24:
        AlertDialog.Builder r4 = new AlertDialog.Builder(this.context, this.resourcesProvider);
        if (r02 == true) goto L27;
        int r5 = R.string.BusinessRecipientsRemoveExcludeTitle;
    L28:
        AlertDialog.Builder r42 = r4.setTitle(LocaleController.getString(r5));
        if (r02 == true) goto L31;
        int r52 = R.string.BusinessRecipientsRemoveExcludeMessage;
    L32:
        AlertDialog r82 = r42.setMessage(LocaleController.formatString(r52, new Object[]{r32})).setPositiveButton(LocaleController.getString(R.string.Remove), new BusinessRecipientsHelper$$ExternalSyntheticLambda0(this, r12, r02, r8)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
        BaseFragment r7 = this.fragment;
        if (r7 == null) goto L35;
        r7.showDialog(r82);
    L36:
        return true;
    L35:
        r82.show();
        goto L36
    L31:
        r52 = R.string.BusinessRecipientsRemoveIncludeMessage;
        goto L32
    L27:
        r5 = R.string.BusinessRecipientsRemoveIncludeTitle;
        goto L28
    L23:
        r32 = getFlagName(r12);
        goto L24
    L37:
        return false;
    }

    public void setExclude(boolean r1) {
        this.exclude = r1;
    }

    public void setValue(TL_account.TL_businessBotRecipients r3) {
        this.bot = true;
        this.currentValue = r3;
        if (r3 != null) goto L6;
        this.exclude = true;
        this.excludeFlags = 0;
        this.includeFlags = 0;
        this.alwaysShow.clear();
        this.neverShow.clear();
        return;
    L6:
        boolean r0 = r3.exclude_selected;
        this.exclude = r0;
        if (r0 == false) goto L10;
        this.includeFlags = 0;
        this.excludeFlags = r3.flags & (-49);
        this.alwaysShow.clear();
        this.neverShow.clear();
        this.neverShow.addAll(this.currentValue.users);
        return;
    L10:
        this.includeFlags = r3.flags & (-49);
        this.excludeFlags = 0;
        this.alwaysShow.clear();
        this.neverShow.clear();
        this.alwaysShow.addAll(this.currentValue.users);
        this.neverShow.addAll(this.currentValue.exclude_users);
    }

    public void setValue(TL_account.TL_businessRecipients r4) {
        this.bot = false;
        if (r4 == null) goto L5;
        TL_account.TL_businessBotRecipients r1 = new TL_account.TL_businessBotRecipients();
        this.currentValue = r1;
        r1.flags = r4.flags;
        r1.existing_chats = r4.existing_chats;
        r1.new_chats = r4.new_chats;
        r1.contacts = r4.contacts;
        r1.non_contacts = r4.non_contacts;
        r1.exclude_selected = r4.exclude_selected;
        r1.users = r4.users;
    L6:
        TL_account.TL_businessBotRecipients r42 = this.currentValue;
        if (r42 != null) goto L10;
        this.exclude = true;
        this.excludeFlags = 0;
        this.includeFlags = 0;
        this.alwaysShow.clear();
        this.neverShow.clear();
        return;
    L10:
        boolean r12 = r42.exclude_selected;
        this.exclude = r12;
        if (r12 == false) goto L14;
        this.includeFlags = 0;
        this.excludeFlags = r42.flags & (-49);
        this.alwaysShow.clear();
        this.neverShow.clear();
        this.neverShow.addAll(this.currentValue.users);
        return;
    L14:
        this.includeFlags = r42.flags & (-49);
        this.excludeFlags = 0;
        this.alwaysShow.clear();
        this.neverShow.clear();
        this.alwaysShow.addAll(this.currentValue.users);
        this.neverShow.addAll(this.currentValue.exclude_users);
        return;
    L5:
        this.currentValue = null;
        goto L6
    }

    public boolean validate(UniversalRecyclerView r4) {
        if (this.exclude == false) goto L5;
        return true;
    L5:
        if (this.alwaysShow.isEmpty() == true) goto L7;
        return true;
    L7:
        if (this.includeFlags != 0) goto L13;
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        View r1 = r4.findViewByItemId(101);
        int r2 = -this.shiftDp;
        this.shiftDp = r2;
        AndroidUtilities.shakeViewSpring(r1, r2);
        r4.smoothScrollToPosition(r4.findPositionByItemId(101));
        return false;
    L13:
        return true;
    }
}
