package org.telegram.ui.Components;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public abstract class CreateBotAlert {

    /* renamed from: org.telegram.ui.Components.CreateBotAlert$1, reason: invalid class name */
    public class AnonymousClass1 implements TextWatcher {
        final /* synthetic */ Runnable val$checkUsername;

        public AnonymousClass1(Runnable r1) {
            this.val$checkUsername = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            this.val$checkUsername.run();
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Components.CreateBotAlert$2, reason: invalid class name */
    public class AnonymousClass2 extends ClickableSpan {
        final /* synthetic */ Runnable val$onClick;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass2(Theme.ResourcesProvider r1, Runnable r2) {
            this.val$resourcesProvider = r1;
            this.val$onClick = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            this.val$onClick.run();
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r2) {
            super.updateDrawState(r2);
            r2.setUnderlineText(false);
            r2.setColor(Theme.getColor(Theme.key_undo_cancelColor, this.val$resourcesProvider));
        }
    }

    public static /* synthetic */ boolean $r8$lambda$DNHBVk_FfcLHor8rzZBpk0hsHQg(Runnable r0, TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L6;
        r0.run();
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ void $r8$lambda$GmKpaDpTm5NvpHF_qD44TjE4Y10(ButtonWithCounterView r1, String[] r2, String[] r3, String r4, TextInfoPrivacyCell r5, Theme.ResourcesProvider r6, int[] r7, TLRPC.Bool r8, TLRPC.TL_error r9) {
        r1.setLoading(false);
        r2[0] = null;
        if ((r8 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        r3[0] = r4;
        r1.setEnabled(true);
        r5.setText(LocaleController.formatString(R.string.UsernameAvailable, new Object[]{"@" + r4}));
        r5.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGreenText, r6));
        return;
    L6:
        r3[0] = null;
        r1.setEnabled(false);
        r5.setText(LocaleController.getString(R.string.UsernameInUse));
        r5.setTextColor(Theme.getColor(Theme.key_text_RedBold, r6));
        int r12 = -r7[0];
        r7[0] = r12;
        AndroidUtilities.shakeViewSpring(r5, r12);
    }

    public static /* synthetic */ void $r8$lambda$HgXZGfTztm1TiVgohibuDACSlU4(BottomSheet r0, View r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$O4LfDmOpx_9Y5LMHst2_FDOR_54(boolean[] r2, Utilities.Callback r3, int[] r4, int r5, DialogInterface r6) {
        if (r2[0] == true) goto L8;
        r2[0] = true;
        if (r3 == null) goto L8;
        r3.run(null);
    L8:
        if (r4[0] < 0) goto L11;
        ConnectionsManager.getInstance(r5).cancelRequest(r4[0], true);
        r4[0] = -1;
        return;
    }

    public static /* synthetic */ void $r8$lambda$Qpj78b_0NziE56xjf31rIY9Vfg8(String[] r2, Runnable r3, EditTextCell r4, int[] r5, ButtonWithCounterView r6, boolean r7, int r8, TLRPC.User r9, int[] r10, boolean[] r11, Utilities.Callback r12, BottomSheet r13, Theme.ResourcesProvider r14, Context r15) {
        if (r2[0] != null) goto L6;
        r3.run();
        return;
    L6:
        String r32 = r4.editText.getText().toString().trim();
        if (TextUtils.isEmpty(r32) == false) goto L10;
        int r22 = -r5[0];
        r5[0] = r22;
        AndroidUtilities.shakeViewSpring(r4, r22);
        return;
    L10:
        r6.setLoading(true);
        TL_bots.createBot r42 = new TL_bots.createBot();
        r42.via_deeplink = r7;
        r42.username = r2[0];
        r42.name = r32;
        r42.manager_id = MessagesController.getInstance(r8).getInputUser(r9);
        r10[0] = ConnectionsManager.getInstance(r8).sendRequestTyped(r42, new AiTonesController$$ExternalSyntheticLambda0(), new CreateBotAlert$$ExternalSyntheticLambda8(r10, r6, r11, r8, r12, r13, r14, r15, r9), 1024);
    }

    /* renamed from: $r8$lambda$UVa_TRG5c6A9jayVCV9VEts8O-U, reason: not valid java name */
    public static /* synthetic */ void m10046$r8$lambda$UVa_TRG5c6A9jayVCV9VEts8OU(BottomSheet r0, Context r1) {
        r0.dismiss();
        Browser.openUrl(r1, "https://t.me/BotFather?start=deletebot");
    }

    public static /* synthetic */ void $r8$lambda$XtOmawUBSCOCSO2uDnujT3n1OLY(Runnable r0, View r1) {
        r0.run();
    }

    /* renamed from: $r8$lambda$Z8-7qQ9FB7PN4baMJPXCnYjip_4, reason: not valid java name */
    public static /* synthetic */ boolean m10047$r8$lambda$Z87qQ9FB7PN4baMJPXCnYjip_4(EditTextCell r0, TextView r1, int r2, KeyEvent r3) {
        if (r2 != 5) goto L6;
        r0.editText.requestFocus();
        EditTextCaption r02 = r0.editText;
        r02.setSelection(r02.length());
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ void $r8$lambda$beTIbpFDg1oTOp6ADA6Nl3xYAEw(int[] r2, ButtonWithCounterView r3, boolean[] r4, int r5, Utilities.Callback r6, BottomSheet r7, Theme.ResourcesProvider r8, Context r9, TLRPC.User r10, TLRPC.User r11, TLRPC.TL_error r12) {
        r2[0] = -1;
        r3.setLoading(false);
        if (r11 == null) goto L9;
        r4[0] = true;
        MessagesController.getInstance(r5).putUser(r11, false);
        ArrayList r22 = new ArrayList();
        r22.add(r11);
        MessagesStorage.getInstance(r5).putUsersAndChats(r22, null, false, false);
        if (r6 == null) goto L7;
        r6.run(r11);
    L7:
        r7.dismiss();
        return;
    L9:
        if (r12 != null) goto L11;
        return;
    L11:
        if ("BOT_CREATE_LIMIT_EXCEEDED".equalsIgnoreCase(r12.text) == false) goto L17;
        MessagesController r23 = MessagesController.getInstance(r5);
        boolean r32 = UserConfig.getInstance(r5).isPremium();
        BulletinFactory r42 = BulletinFactory.of(r7.topBulletinContainer, r8);
        int r52 = R.raw.error;
        String r62 = LocaleController.getString(R.string.CreateManagedBotLimitTitle);
        if (r32 == false) goto L15;
        String r24 = LocaleController.formatString(R.string.CreateManagedBotLimitText, new Object[]{Integer.valueOf(r23.config.botsCreateLimitPremium.get())});
    L16:
        r42.createSimpleBulletin(r52, r62, highlightBotFather(r9, AndroidUtilities.replaceSingleLink(r24, Theme.getColor(Theme.key_undo_cancelColor, r8), new CreateBotAlert$$ExternalSyntheticLambda9(r7)), new CreateBotAlert$$ExternalSyntheticLambda10(r7, r9), r8)).setDuration(8000).show();
    L30:
        AndroidUtilities.hideKeyboard(r7.getCurrentFocus());
        return;
    L15:
        r24 = LocaleController.formatString(R.string.CreateManagedBotLimitTextPremium, new Object[]{Integer.valueOf(r23.config.botsCreateLimitPremium.get()), Integer.valueOf(r23.config.botsCreateLimitDefault.get())});
        goto L16
    L17:
        String r25 = r12.text;
        if (r25 == null) goto L23;
        if (r25.startsWith("FLOOD_WAIT_") == false) goto L23;
        BulletinFactory.of(r7.topBulletinContainer, r8).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.CreateManagedBotLimitTitle), LocaleController.formatString(R.string.CreateManagedBotLimitTextTime, new Object[]{LocaleController.formatDuration(Integer.parseInt(r12.text.substring(11)))})).show();
    L23:
        if ("MANAGER_PERMISSION_MISSING".equalsIgnoreCase(r12.text) == true) goto L25;
        BulletinFactory.of(r7.topBulletinContainer, r8).showForError(r12);
        goto L30
    L25:
        if (TextUtils.isEmpty(UserObject.getPublicUsername(r10)) == true) goto L27;
        String r26 = "@" + UserObject.getPublicUsername(r10);
    L28:
        BulletinFactory.of(r7.topBulletinContainer, r8).createSimpleBulletin(R.raw.error, AndroidUtilities.replaceSingleLinkBold(LocaleController.formatString(R.string.CreateManagedBotUnsupported, new Object[]{r26}), Theme.getColor(Theme.key_undo_cancelColor, r8))).show();
        goto L30
    L27:
        r26 = UserObject.getUserName(r10);
        goto L28
    }

    /* renamed from: $r8$lambda$duLcuQuCStUZz-3PaAYwTm-fqe0, reason: not valid java name */
    public static /* synthetic */ void m10048$r8$lambda$duLcuQuCStUZz3PaAYwTmfqe0(EditTextCell r10, int[] r11, int r12, String[] r13, String[] r14, ButtonWithCounterView r15, TextInfoPrivacyCell r16, Theme.ResourcesProvider r17, int[] r18) {
        String r4 = r10.getText() + "bot";
        if (r4.length() >= 4) goto L10;
        if (r11[0] < 0) goto L7;
        ConnectionsManager.getInstance(r12).cancelRequest(r11[0], true);
        r11[0] = -1;
    L7:
        r14[0] = null;
        r13[0] = null;
        r15.setLoading(false);
        r15.setEnabled(false);
        r16.setText(LocaleController.getString(R.string.UsernameInvalidShort));
        r16.setTextColor(Theme.getColor(Theme.key_text_RedBold, r17));
        int r102 = -r18[0];
        r18[0] = r102;
        AndroidUtilities.shakeViewSpring(r16, r102);
        return;
    L10:
        if (r4.length() <= 32) goto L17;
        if (r11[0] < 0) goto L14;
        ConnectionsManager.getInstance(r12).cancelRequest(r11[0], true);
        r11[0] = -1;
    L14:
        r14[0] = null;
        r13[0] = null;
        r15.setLoading(false);
        r15.setEnabled(false);
        r16.setText(LocaleController.getString(R.string.UsernameInvalidLong));
        r16.setTextColor(Theme.getColor(Theme.key_text_RedBold, r17));
        int r103 = -r18[0];
        r18[0] = r103;
        AndroidUtilities.shakeViewSpring(r16, r103);
        return;
    L17:
        if (TextUtils.equals(r14[0], r4) == false) goto L19;
        return;
    L19:
        r14[0] = r4;
        r13[0] = null;
        r16.setText(LocaleController.getString(R.string.UsernameChecking));
        r16.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4, r17));
        TL_bots.checkUsername r104 = new TL_bots.checkUsername();
        r104.username = r4;
        r15.setLoading(true);
        r11[0] = ConnectionsManager.getInstance(r12).sendRequestTyped(r104, new AiTonesController$$ExternalSyntheticLambda0(), new CreateBotAlert$$ExternalSyntheticLambda7(r15, r14, r13, r4, r16, r17, r18));
    }

    /* renamed from: $r8$lambda$xZYcFF9Rk1bcL4YP6-Sw5LitsM0, reason: not valid java name */
    public static /* synthetic */ void m10049$r8$lambda$xZYcFF9Rk1bcL4YP6Sw5LitsM0(BottomSheet r2) {
        r2.dismiss();
        BaseFragment r22 = LaunchActivity.getSafeLastFragment();
        if (r22 == null) goto L6;
        r22.presentFragment(new PremiumPreviewFragment("create_bot"));
        return;
    }

    private static SpannableStringBuilder highlightBotFather(Context r1, CharSequence r2, Runnable r3, Theme.ResourcesProvider r4) {
        if ((r2 instanceof SpannableStringBuilder) == true) goto L5;
        SpannableStringBuilder r12 = new SpannableStringBuilder(r2);
    L6:
        int r22 = AndroidUtilities.charSequenceIndexOf(r12, "@BotFather");
        if (r22 < 0) goto L9;
        r12.setSpan(new AnonymousClass2(r4, r3), r22, r22 + 10, 33);
    L9:
        return r12;
    L5:
        r12 = (SpannableStringBuilder) r2;
        goto L6
    }

    public static void show(Context r33, int r34, TLRPC.User r35, TLRPC.TL_requestPeerTypeCreateBot r36, boolean r37, Utilities.Callback<TLRPC.User> r38, Theme.ResourcesProvider r39, BulletinFactory r40, boolean r41) {
        if (r35.bot_can_manage_bots == false) goto L5;
        BottomSheet.Builder r10 = new BottomSheet.Builder(r33, true, r39);
        LinearLayout r13 = new LinearLayout(r33);
        r13.setClipChildren(false);
        r13.setClipToPadding(false);
        r13.setOrientation(1);
        r10.setCustomView(r13);
        BackupImageView r0 = new BackupImageView(r33);
        AvatarDrawable r2 = new AvatarDrawable();
        r2.setInfo(r35);
        r0.setForUserOrChat(r35, r2);
        r0.setRoundRadius(AndroidUtilities.dp(40.0f));
        r13.addView(r0, LayoutHelper.createLinear(80, 80, 49, 0, 22, 0, 16));
        TextView r02 = new TextView(r33);
        r02.setTypeface(AndroidUtilities.bold());
        int r15 = Theme.key_windowBackgroundWhiteBlackText;
        r02.setTextColor(Theme.getColor(r15, r39));
        r02.setTextSize(1, 20.0f);
        r02.setText(LocaleController.getString(R.string.CreateManagedBotTitle));
        r02.setGravity(17);
        r13.addView(r02, LayoutHelper.createLinear(-1, -2, 55, 16, 0, 16, 8));
        TextView r03 = new TextView(r33);
        r03.setTextColor(Theme.getColor(r15, r39));
        r03.setTextSize(1, 14.0f);
        r03.setText(AndroidUtilities.replaceSingleLinkBold(LocaleController.formatString(R.string.CreateManagedBotText, new Object[]{UserObject.getUserName(r35)}), Theme.getColor(Theme.key_chat_messageLinkIn, r39)));
        r03.setGravity(17);
        r13.addView(r03, LayoutHelper.createLinear(-1, -2, 55, 16, 0, 16, 22));
        EditTextCell r04 = new EditTextCell(r33, LocaleController.getString(R.string.CreateManagedBotName), false, false, -1, r39);
        r04.editText.setImeOptions(5);
        int r22 = AndroidUtilities.dp(16.0f);
        int r3 = Theme.key_windowBackgroundWhite;
        r04.setBackground(Theme.createRoundRectDrawable(r22, Theme.getColor(r3, r39)));
        r04.setText(r36.suggested_name);
        r13.addView(r04, LayoutHelper.createLinear(-1, -2, 55, 12, 0, 12, 0));
        TextInfoPrivacyCell r23 = new TextInfoPrivacyCell(r33, r39);
        r23.setText(LocaleController.getString(R.string.CreateManagedBotNameInfo));
        r13.addView(r23, LayoutHelper.createLinear(-1, -2));
        EditTextCell r05 = new EditTextCell(r33, LocaleController.getString(R.string.CreateManagedBotUsername), false, false, 29, r39);
        LinearLayout r24 = new LinearLayout(r33);
        r24.setOrientation(0);
        r05.removeView(r05.editText);
        r05.editText.setHintColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText, r39));
        r05.editText.setRightText("bot");
        r05.editText.setPadding(0, AndroidUtilities.dp(15.0f), AndroidUtilities.dp(63.0f), AndroidUtilities.dp(15.0f));
        TextView r32 = new TextView(r33);
        r32.setTextSize(1, 17.0f);
        r32.setText("@");
        r32.setTextColor(Theme.getColor(r15, r39));
        r32.setGravity(17);
        r32.setPadding(0, AndroidUtilities.dp(15.0f), 0, AndroidUtilities.dp(15.0f));
        r24.addView(r32, LayoutHelper.createLinear(-2, -1, 21.0f, -1.0f, 0.0f, 0.0f));
        r24.addView(r05.editText, LayoutHelper.createLinear(0, -1, 119.0f, 1, 0, 0, 0, 0));
        r05.addView(r24, LayoutHelper.createFrame(-1, -1.0f));
        r05.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(16.0f), Theme.getColor(r3, r39)));
        r05.editText.setImeOptions(6);
        String r25 = r36.suggested_username;
        if (r25 == null) goto L24;
        r25 = r25.trim();
    L24:
        if (r25 != null) goto L26;
    L28:
        r05.setText(r25);
        r13.addView(r05, LayoutHelper.createLinear(-1, -2, 55, 12, 0, 12, 0));
        r04.editText.setOnEditorActionListener(new CreateBotAlert$$ExternalSyntheticLambda0(r05));
        TextInfoPrivacyCell r310 = new TextInfoPrivacyCell(r33, r39);
        r310.setText(LocaleController.getString(R.string.CreateManagedBotUsernameInfo));
        r13.addView(r310, LayoutHelper.createLinear(-1, -2));
        LinearLayout r7 = new LinearLayout(r33);
        r7.setOrientation(0);
        r7.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
        r7.setClipToPadding(false);
        r13.addView(r7, LayoutHelper.createLinear(-1, -2));
        ButtonWithCounterView r4 = new ButtonWithCounterView(r33, r39).setRound().setNeutral();
        r4.setText(LocaleController.getString(R.string.Cancel));
        r7.addView(r4, LayoutHelper.createLinear(0, 48, 119.0f, 1, 0, 0, 5, 0));
        ButtonWithCounterView r8 = new ButtonWithCounterView(r33, r39).setRound();
        r8.setText(LocaleController.getString(R.string.CreateManagedBotButton));
        r7.addView(r8, LayoutHelper.createLinear(0, 48, 119.0f, 1, 5, 0, 0, 0));
        BottomSheet r72 = r10.create();
        r72.useBackgroundTopPadding = false;
        r72.smoothKeyboardAnimationEnabled = true;
        int r102 = Theme.key_windowBackgroundGray;
        r72.setBackgroundColor(Theme.getColor(r102, r39));
        r72.fixNavigationBar(Theme.getColor(r102, r39));
        boolean[] r103 = new boolean[1];
        String[] r132 = new String[1];
        int[] r222 = {-1};
        int[] r21 = {4};
        CreateBotAlert$$ExternalSyntheticLambda1 r12 = new CreateBotAlert$$ExternalSyntheticLambda1(r05, new int[]{-1}, r34, r132, new String[1], r8, r310, r39, r21);
        CreateBotAlert$$ExternalSyntheticLambda2 r06 = new CreateBotAlert$$ExternalSyntheticLambda2(r132, r12, r04, r21, r8, r37, r34, r35, r222, r103, r38, r72, r39, r33);
        r05.editText.addTextChangedListener(new AnonymousClass1(r12));
        if (TextUtils.isEmpty(r25) == true) goto L31;
        r12.run();
    L31:
        r05.editText.setOnEditorActionListener(new CreateBotAlert$$ExternalSyntheticLambda3(r06));
        r4.setOnClickListener(new CreateBotAlert$$ExternalSyntheticLambda4(r72));
        r8.setEnabled(false);
        r8.setOnClickListener(new CreateBotAlert$$ExternalSyntheticLambda5(r06));
        r72.setOnDismissListener(new CreateBotAlert$$ExternalSyntheticLambda6(r103, r38, r222, r34));
        r72.show();
        return;
    L26:
        if (r25.toLowerCase().endsWith("bot") == false) goto L28;
        r25 = r25.substring(0, r25.length() - 3);
        goto L28
    L5:
        if (r40 != null) goto L12;
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if (r1 != null) goto L11;
        if (r38 == null) goto L33;
        r38.run(null);
        return;
    L33:
        return;
    L11:
        BulletinFactory r14 = BulletinFactory.of(r1);
    L14:
        if (TextUtils.isEmpty(UserObject.getPublicUsername(r35)) == true) goto L16;
        String r26 = "@" + UserObject.getPublicUsername(r35);
    L17:
        r14.createSimpleBulletin(R.raw.error, AndroidUtilities.replaceSingleLinkBold(LocaleController.formatString(R.string.CreateManagedBotUnsupported, new Object[]{r26}), Theme.getColor(Theme.key_undo_cancelColor, r39))).show();
        if (r38 == null) goto L34;
        r38.run(null);
        return;
    L34:
        return;
    L16:
        r26 = UserObject.getUserName(r35);
        goto L17
    L12:
        r14 = r40;
        goto L14
    }
}
