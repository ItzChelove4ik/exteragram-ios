package org.telegram.ui;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.voip.ConferenceCall;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.AvatarsImageView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes6.dex */
public abstract class GroupCallSheet {

    public static class UserView extends FrameLayout {
        private final AvatarDrawable avatarDrawable;
        private final int currentAccount;
        private final BackupImageView imageView;
        private final LinkSpanDrawable.LinksTextView textView;

        public static class Factory extends UItem.UItemFactory<UserView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((UserView) r1).set(r2.dialogId);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public UserView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new UserView(r1, r3, r5);
            }
        }

        public UserView(Context r9, int r10, Theme.ResourcesProvider r11) {
            super(r9);
            this.avatarDrawable = new AvatarDrawable();
            this.currentAccount = r10;
            BackupImageView r102 = new BackupImageView(r9);
            this.imageView = r102;
            r102.setRoundRadius(AndroidUtilities.dp(28.0f));
            addView(r102, LayoutHelper.createFrame(56, 56.0f, 49, 0.0f, 17.0f, 0.0f, 0.0f));
            LinkSpanDrawable.LinksTextView r92 = TextHelper.makeLinkTextView(r9, 12.0f, Theme.key_windowBackgroundWhiteBlackText, false, r11);
            this.textView = r92;
            r92.setGravity(17);
            addView(r92, LayoutHelper.createFrame(-2, -2.0f, 49, 6.0f, 77.66f, 6.0f, 0.0f));
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(82.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
        }

        public void set(long r2) {
            TLObject r22 = MessagesController.getInstance(this.currentAccount).getUserOrChat(r2);
            this.avatarDrawable.setInfo(r22);
            this.imageView.setForUserOrChat(r22, this.avatarDrawable);
            this.textView.setText(DialogObject.getName(r22));
            this.textView.setMaxWidth(Math.max(AndroidUtilities.dp(41.0f), HintView2.cutInFancyHalf(this.textView.getText(), this.textView.getPaint())));
        }
    }

    public static /* synthetic */ boolean $r8$lambda$0wXRjKk4tiXvqp2xchk3q6Rg_NQ(int r4, long r5, Long r7) {
        if (r7.longValue() != UserConfig.getInstance(r4).getClientUserId()) goto L5;
        return false;
    L5:
        if (r7.longValue() == r5) goto L10;
        return true;
    L10:
        return false;
    }

    public static /* synthetic */ void $r8$lambda$Akf10XNgC6J0t1JRJKCuBgnc3cI(AlertDialog r7, Browser.Progress r8, TLObject r9, int r10, Context r11, long r12, TLRPC.InputGroupCall r14, TLRPC.TL_error r15) {
        if (r7 == null) goto L4;
        r7.dismiss();
    L4:
        if (r8 == null) goto L7;
        r8.end();
    L7:
        if ((r9 instanceof TL_phone.groupCall) == false) goto L23;
        TL_phone.groupCall r92 = (TL_phone.groupCall) r9;
        MessagesController.getInstance(r10).putUsers(r92.users, false);
        MessagesController.getInstance(r10).putChats(r92.chats, false);
        if (VoIPService.getSharedInstance() == null) goto L20;
        ConferenceCall r72 = VoIPService.getSharedInstance().conference;
        if (r72 == null) goto L20;
        TLRPC.GroupCall r73 = r72.groupCall;
        if (r73 == null) goto L20;
        if (r92.call.id != r73.id) goto L20;
        int r1 = r10;
        LaunchActivity r102 = LaunchActivity.instance;
        if (r102 == null) goto L21;
        GroupCallActivity.create(r102, AccountInstance.getInstance(VoIPService.getSharedInstance().getAccount()), null, null, false, null);
        return;
    L21:
        show(r11, r1, r12, r14, r92.call, r92.participants);
        return;
    L20:
        r1 = r10;
        goto L21
    L23:
        if (r15 != null) goto L25;
    L30:
        if (r15 == null) goto L36;
        BaseFragment r74 = LaunchActivity.getSafeLastFragment();
        if (r74 == null) goto L37;
        BulletinFactory.of(r74).showForError(r15);
        return;
    L37:
        return;
    L36:
        return;
    L25:
        if ("GROUPCALL_INVALID".equalsIgnoreCase(r15.text) == false) goto L30;
        BaseFragment r75 = LaunchActivity.getSafeLastFragment();
        if (r75 == null) goto L35;
        BulletinFactory.of(r75).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.LinkIsNoActive)).show();
        return;
    }

    public static /* synthetic */ void $r8$lambda$Foiu6CG_1ucS_wThAXsc32eF6_k(AlertDialog r10, Browser.Progress r11, int r12, Context r13, long r14, TLRPC.InputGroupCall r16, TLObject r17, TLRPC.TL_error r18) {
        AndroidUtilities.runOnUIThread(new GroupCallSheet$$ExternalSyntheticLambda2(r10, r11, r17, r12, r13, r14, r16, r18));
    }

    public static /* synthetic */ void $r8$lambda$TVeBK_4RmYxYXVPfjATxLO0FfbM(int r1, int r2) {
        ConnectionsManager.getInstance(r1).cancelRequest(r2, true);
    }

    public static /* synthetic */ Long $r8$lambda$ezYnCHhp5dkCnJQagF1TC6AognE(TLRPC.GroupCallParticipant r2) {
        return Long.valueOf(DialogObject.getPeerDialogId(r2.peer));
    }

    /* renamed from: $r8$lambda$plmRm_-ZCfuxi0WM15hUydZwDTE, reason: not valid java name */
    public static /* synthetic */ void m15336$r8$lambda$plmRm_ZCfuxi0WM15hUydZwDTE(BottomSheet r0, Context r1, CheckBox2 r2, int r3, TLRPC.InputGroupCall r4, View r5) {
        r0.dismiss();
        Activity r02 = AndroidUtilities.findActivity(r1);
        if (r02 != null) goto L5;
        return;
    L5:
        MessagesController.getGlobalMainSettings().edit().putBoolean("callmiconstart", r2.isChecked()).apply();
        VoIPHelper.joinConference(r02, r3, r4, false, null);
    }

    public static /* synthetic */ void $r8$lambda$uOXxZGwZT0bVQWHGSuPdRe3GfLE(CheckBox2 r1, View r2) {
        r1.setChecked(!r1.isChecked(), true);
        MessagesController.getGlobalMainSettings().edit().putBoolean("callmiconstart", r1.isChecked()).apply();
    }

    public static void show(Context r1, int r2, long r3, String r5, Browser.Progress r6) {
        TLRPC.TL_inputGroupCallSlug r52 = new TLRPC.TL_inputGroupCallSlug();
        r52.slug = r5;
        show(r1, r2, r3, r52, r6);
    }

    public static void show(Context r9, int r10, long r11, TLRPC.InputGroupCall r13, Browser.Progress r14) {
        if (VoIPService.getSharedInstance() == null) goto L28;
        ConferenceCall r0 = VoIPService.getSharedInstance().conference;
        if (r0 == null) goto L28;
        if ((r13 instanceof TLRPC.TL_inputGroupCall) == false) goto L18;
        TLRPC.GroupCall r1 = r0.groupCall;
        if (r1 != null) goto L11;
    L12:
        TLRPC.InputGroupCall r02 = r0.inputGroupCall;
        if ((r02 instanceof TLRPC.TL_inputGroupCall) == false) goto L28;
        if (r13.id != r02.id) goto L28;
    L16:
        Context r4 = r9;
        LaunchActivity r92 = LaunchActivity.instance;
        if (r92 == null) goto L29;
        GroupCallActivity.create(r92, AccountInstance.getInstance(VoIPService.getSharedInstance().getAccount()), null, null, false, null);
        return;
    L29:
        if (r14 != null) goto L32;
        AlertDialog r93 = new AlertDialog(r4, 3);
        r93.showDelayed(300);
    L31:
        AlertDialog r12 = r93;
        TL_phone.getGroupCall r94 = new TL_phone.getGroupCall();
        r94.call = r13;
        r94.limit = 10;
        int r95 = ConnectionsManager.getInstance(r10).sendRequest(r94, new GroupCallSheet$$ExternalSyntheticLambda0(r12, r14, r10, r4, r11, r13));
        if (r14 == null) goto L37;
        r14.onCancel(new GroupCallSheet$$ExternalSyntheticLambda1(r10, r95));
        r14.init();
        return;
    L37:
        return;
    L32:
        r93 = null;
        goto L31
    L11:
        if (r13.id == r1.id) goto L16;
    L18:
        if ((r13 instanceof TLRPC.TL_inputGroupCallSlug) == false) goto L28;
        TLRPC.InputGroupCall r03 = r0.inputGroupCall;
        if ((r03 instanceof TLRPC.TL_inputGroupCallSlug) == false) goto L28;
        if (TextUtils.equals(r03.slug, r13.slug) == true) goto L16;
    L28:
        r4 = r9;
        goto L29
    }

    public static void show(Context r21, int r22, long r23, TLRPC.InputGroupCall r25, TLRPC.GroupCall r26, ArrayList<TLRPC.GroupCallParticipant> r27) {
        DarkThemeResourceProvider r0 = new DarkThemeResourceProvider();
        BottomSheet.Builder r1 = new BottomSheet.Builder(r21, false, r0);
        LinearLayout r5 = new LinearLayout(r21);
        r5.setOrientation(1);
        r5.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), AndroidUtilities.dp(8.0f));
        FrameLayout r8 = new FrameLayout(r21);
        r8.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r0)));
        ImageView r9 = new ImageView(r21);
        r9.setImageResource(R.drawable.filled_calls_users);
        r8.addView(r9, LayoutHelper.createFrame(56, 56, 17));
        r5.addView(r8, LayoutHelper.createLinear(80, 80, 1, 2, 21, 2, 13));
        int r82 = Theme.key_windowBackgroundWhiteBlackText;
        LinkSpanDrawable.LinksTextView r92 = TextHelper.makeLinkTextView(r21, 20.0f, r82, true, r0);
        r92.setText(LocaleController.getString(R.string.GroupCallLinkTitle));
        r92.setGravity(17);
        r5.addView(r92, LayoutHelper.createLinear(-1, -2, 1, 2, 0, 2, 4));
        List r93 = (List) r27.stream().map(new GroupCallSheet$$ExternalSyntheticLambda3()).filter(new GroupCallSheet$$ExternalSyntheticLambda4(r22, r23)).collect(Collectors.toList());
        boolean r11 = r93.isEmpty();
        LinkSpanDrawable.LinksTextView r83 = TextHelper.makeLinkTextView(r21, 14.0f, r82, false, r0);
        r83.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.GroupCallLinkText)));
        r83.setGravity(17);
        r83.setMaxWidth(HintView2.cutInFancyHalf(r83.getText(), r83.getPaint()));
        r5.addView(r83, LayoutHelper.createLinear(-1, -2, 1, 2, 0, 2, 23));
        if (r11 == true) goto L15;
        View r84 = new View(r21);
        r84.setBackgroundColor(-14012362);
        r5.addView(r84, LayoutHelper.createLinear(-1, 0.66f, 7, 0, 0, 0, 0));
        AvatarsImageView r85 = new AvatarsImageView(r21, false);
        r85.setCentered(true);
        r85.setSize(AndroidUtilities.dp(38.0f));
        int r112 = Math.min(3, r93.size());
        r85.setCount(r112);
        int r13 = 0;
    L5:
        if (r13 >= r112) goto L7;
        r85.setObject(r13, r22, MessagesController.getInstance(r22).getUser((Long) r93.get(r13)));
        r13 = r13 + 1;
        goto L5
    L7:
        r85.commitTransition(false);
        r5.addView(r85, LayoutHelper.createLinear(-1, 58, 2.0f, 11.0f, 5.0f, 0.0f));
        LinkSpanDrawable.LinksTextView r86 = TextHelper.makeLinkTextView(r21, 14.0f, Theme.key_windowBackgroundWhiteBlackText, false, r0);
        r86.setGravity(17);
        if (r93.size() != 1) goto L11;
        r86.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GroupCallLinkText2One, new Object[]{DialogObject.getShortName(r22, ((Long) r93.get(0)).longValue())})));
    L14:
        r86.setMaxWidth(HintView2.cutInFancyHalf(r86.getText(), r86.getPaint()));
        r5.addView(r86, LayoutHelper.createLinear(-1, -2, 1, 2, 0, 2, 25));
        goto L15
    L11:
        if (r93.size() != 2) goto L13;
        r86.setText(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.GroupCallLinkText2Two, new Object[]{DialogObject.getShortName(r22, ((Long) r93.get(0)).longValue()), DialogObject.getShortName(r22, ((Long) r93.get(1)).longValue())})));
        goto L14
    L13:
        r86.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("GroupCallLinkText2Many", r27.size() - 2, new Object[]{DialogObject.getShortName(r22, ((Long) r93.get(0)).longValue()), DialogObject.getShortName(r22, ((Long) r93.get(1)).longValue())})));
    L15:
        LinearLayout r87 = new LinearLayout(r21);
        r87.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f));
        r87.setClipToPadding(false);
        r87.setOrientation(0);
        r87.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, r0), 20, 20));
        CheckBox2 r94 = new CheckBox2(r21, 24, r0);
        r94.setColor(Theme.key_radioBackgroundChecked, Theme.key_checkboxDisabled, Theme.key_checkboxCheck);
        r94.setDrawUnchecked(true);
        r94.setChecked(MessagesController.getGlobalMainSettings().getBoolean("callmiconstart", true), false);
        r94.setDrawBackgroundAsArc(10);
        r87.addView(r94, LayoutHelper.createLinear(26, 26, 16, 0, 0, 0, 0));
        TextView r10 = new TextView(r21);
        r10.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r0));
        r10.setTextSize(1, 14.0f);
        r10.setText(LocaleController.getString(R.string.GroupCallLinkMicrophone));
        r87.addView(r10, LayoutHelper.createLinear(-2, -2, 16, 9, 0, 0, 0));
        r5.addView(r87, LayoutHelper.createLinear(-2, 38, 1, 0, 4, 0, 12));
        ScaleStateListAnimator.apply(r87, 0.025f, 1.5f);
        r87.setOnClickListener(new GroupCallSheet$$ExternalSyntheticLambda5(r94));
        ButtonWithCounterView r6 = new ButtonWithCounterView(r21, r0).setRound();
        r6.setText(LocaleController.getString(R.string.GroupCallLinkJoin), false);
        r5.addView(r6, LayoutHelper.createLinear(-1, 48, 2.0f, 0.0f, 2.0f, 0.0f));
        r1.setCustomView(r5);
        BottomSheet r12 = r1.create();
        r6.setOnClickListener(new GroupCallSheet$$ExternalSyntheticLambda6(r12, r21, r94, r22, r25));
        r12.fixNavigationBar();
        r12.show();
    }
}
