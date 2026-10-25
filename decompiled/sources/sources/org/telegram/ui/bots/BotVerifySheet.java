package org.telegram.ui.bots;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EditTextSuggestionsFix;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes7.dex */
public abstract class BotVerifySheet {

    /* renamed from: org.telegram.ui.bots.BotVerifySheet$1, reason: invalid class name */
    public class AnonymousClass1 implements TextWatcher {
        boolean ignoreEditText;
        final /* synthetic */ EditTextBoldCursor val$editText;
        final /* synthetic */ OutlineTextContainerView val$editTextContainer;
        final /* synthetic */ int val$maxLength;

        public AnonymousClass1(EditTextBoldCursor r1, int r2, OutlineTextContainerView r3) {
            this.val$editText = r1;
            this.val$maxLength = r2;
            this.val$editTextContainer = r3;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r5) {
            CharSequence r52 = this.val$editText.getText();
            if (this.ignoreEditText == true) goto L7;
            int r0 = r52.length();
            int r2 = this.val$maxLength;
            if (r0 <= r2) goto L7;
            this.ignoreEditText = true;
            EditTextBoldCursor r02 = this.val$editText;
            r52 = r52.subSequence(0, r2);
            r02.setText(r52);
            EditTextBoldCursor r03 = this.val$editText;
            r03.setSelection(r03.length());
            this.ignoreEditText = false;
        L7:
            this.val$editTextContainer.animateSelection(this.val$editText.isFocused(), !TextUtils.isEmpty(r52));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public static /* synthetic */ void $r8$lambda$5aKZVRDWxSyiakwwXmxo6orSDOA(ButtonWithCounterView r0, TL_bots.botVerifierSettings r1, EditTextBoldCursor r2, int r3, OutlineTextContainerView r4, int r5, long r6, long r8, BottomSheet r10, Utilities.Callback r11, View r12) {
        if (r0.isLoading() == false) goto L6;
        return;
    L6:
        if (r1.can_modify_custom_description == true) goto L8;
    L11:
        r0.setLoading(true);
        TL_bots.setCustomVerification r42 = new TL_bots.setCustomVerification();
        r42.enabled = true;
        r42.flags = 1 | r42.flags;
        r42.bot = MessagesController.getInstance(r5).getInputUser(r6);
        r42.peer = MessagesController.getInstance(r5).getInputPeer(r8);
        if (r1.can_modify_custom_description == false) goto L14;
        r42.custom_description = r2.getText().toString();
    L16:
        if (TextUtils.isEmpty(r42.custom_description) == true) goto L18;
        r42.flags |= 4;
    L18:
        ConnectionsManager.getInstance(r5).sendRequest(r42, new BotVerifySheet$$ExternalSyntheticLambda4(r0, r10, r11));
        return;
    L14:
        r42.custom_description = r1.custom_description;
        goto L16
    L8:
        if (r2.getText().length() <= r3) goto L11;
        r4.animateError(1.0f);
        AndroidUtilities.shakeViewSpring(r4, -6.0f);
    }

    public static /* synthetic */ void $r8$lambda$CqNQ0xOAXduerhTwwAKKDewTzMc(ButtonWithCounterView r1, TLObject r2, BottomSheet r3, Utilities.Callback r4) {
        r1.setLoading(false);
        if ((r2 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        r3.dismiss();
        r4.run(Boolean.FALSE);
        return;
    }

    public static /* synthetic */ void $r8$lambda$JlUszoSuMSH5jXSHcziYtLxabhI(boolean[] r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new BotVerifySheet$$ExternalSyntheticLambda8(r0, r2, r1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: $r8$lambda$luL9K-TGpW2Y5hY9SWCy3mBdECk, reason: not valid java name */
    public static /* synthetic */ void m21348$r8$lambda$luL9KTGpW2Y5hY9SWCy3mBdECk(TopicsFragment r2, DialogsActivity r3, long r4, int r6, Boolean r7) {
        if (r2 == null) goto L4;
        r2.finishFragment();
        r3.removeSelfFromStack();
    L5:
        BaseFragment r22 = LaunchActivity.getSafeLastFragment();
        if (r22 != null) goto L9;
        return;
    L9:
        if (r4 < 0) goto L11;
        TLRPC.User r32 = MessagesController.getInstance(r6).getUser(Long.valueOf(r4));
        String r42 = UserObject.getForcedFirstName(r32);
        TLRPC.Chat r33 = r32;
    L15:
        BulletinFactory r23 = BulletinFactory.of(r22);
        if (r7.booleanValue() == false) goto L18;
        int r5 = R.string.BotSentRevokeVerifyRequest;
    L19:
        r23.createUsersBulletin(r33, AndroidUtilities.replaceTags(LocaleController.formatString(r5, new Object[]{r42}))).show(false);
        return;
    L18:
        r5 = R.string.BotSentVerifyRequest;
        goto L19
    L11:
        TLRPC.Chat r34 = MessagesController.getInstance(r6).getChat(Long.valueOf(-r4));
        if (r34 != null) goto L14;
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        r33 = r34;
        goto L15
    L14:
        r42 = r34.title;
        r33 = r34;
        goto L15
    L4:
        r3.finishFragment();
        goto L5
    }

    /* renamed from: $r8$lambda$mwSmAbZqB-325NgxstpKnTL1mhs, reason: not valid java name */
    public static /* synthetic */ void m21349$r8$lambda$mwSmAbZqB325NgxstpKnTL1mhs(boolean[] r1, TLObject r2, Utilities.Callback r3) {
        r1[0] = false;
        if ((r2 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        r3.run(Boolean.TRUE);
        return;
    }

    public static /* synthetic */ void $r8$lambda$p8rYKYSGCmQGfb_lmklrZFNbtxQ(boolean[] r1, int r2, long r3, long r5, Utilities.Callback r7, AlertDialog r8, int r9) {
        if (r1[0] == false) goto L5;
        return;
    L5:
        r1[0] = true;
        TL_bots.setCustomVerification r0 = new TL_bots.setCustomVerification();
        r0.enabled = false;
        r0.flags |= 1;
        r0.bot = MessagesController.getInstance(r2).getInputUser(r3);
        r0.peer = MessagesController.getInstance(r2).getInputPeer(r5);
        ConnectionsManager.getInstance(r2).sendRequest(r0, new BotVerifySheet$$ExternalSyntheticLambda6(r1, r7));
    }

    public static /* synthetic */ void $r8$lambda$um82XLivK1m2Ksv8lhRYBE4jFGE(OutlineTextContainerView r0, EditTextBoldCursor r1, View r2, boolean r3) {
        r0.animateSelection(r3, !TextUtils.isEmpty(r1.getText()));
    }

    public static /* synthetic */ boolean $r8$lambda$x5Yrw2bULKJdRpOmKd7iEVSZhKY(DialogsActivity r4, int r5, long r6, TL_bots.botVerifierSettings r8, DialogsActivity r9, ArrayList r10, CharSequence r11, boolean r12, boolean r13, int r14, int r15, TopicsFragment r16) {
        if (r10.isEmpty() == false) goto L5;
        return false;
    L5:
        long r0 = ((MessagesStorage.TopicKey) r10.get(0)).dialogId;
        openSheet(r4.getContext(), r5, r6, r0, r8, new BotVerifySheet$$ExternalSyntheticLambda1(r16, r4, r0, r5));
        return true;
    }

    public static /* synthetic */ void $r8$lambda$xlFu_NtTsgyBAI6Da4WZvNnhyMU(ButtonWithCounterView r0, BottomSheet r1, Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new BotVerifySheet$$ExternalSyntheticLambda7(r0, r3, r1, r2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void openRemoveVerify(Context r19, int r20, long r21, long r23, TL_bots.botVerifierSettings r25, Utilities.Callback<Boolean> r26) {
        if (r19 != null) goto L6;
        return;
    L6:
        if (r23 < 0) goto L8;
        TLRPC.User r2 = MessagesController.getInstance(r20).getUser(Long.valueOf(r23));
        String r3 = UserObject.getForcedFirstName(r2);
        TLRPC.Chat r22 = r2;
    L12:
        FrameLayout r4 = new FrameLayout(r19);
        FrameLayout r7 = new FrameLayout(r19);
        r7.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), Theme.getColor(Theme.key_groupcreate_spanBackground)));
        BackupImageView r9 = new BackupImageView(r19);
        r9.setRoundRadius(AndroidUtilities.dp(28.0f));
        AvatarDrawable r8 = new AvatarDrawable();
        r8.setInfo(r22);
        r9.setForUserOrChat(r22, r8);
        r7.addView(r9, LayoutHelper.createFrame(28, 28, 51));
        BackupImageView r24 = new BackupImageView(r19);
        r24.setEmojiColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_verifiedBackground), PorterDuff.Mode.SRC_IN));
        r24.setAnimatedEmojiDrawable(AnimatedEmojiDrawable.make(r20, 3, r25.icon));
        r7.addView(r24, LayoutHelper.createFrame(20, 20.0f, 19, 34.0f, 0.0f, 0.0f, 0.0f));
        SimpleTextView r27 = new SimpleTextView(r19);
        r27.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r27.setTextSize(13);
        r27.setEllipsizeByGradient(true);
        r27.setText(r3);
        r27.setWidthWrapContent(true);
        r7.addView(r27, LayoutHelper.createFrame(-2, -2.0f, 19, 57.0f, 0.0f, 10.0f, 0.0f));
        r4.addView(r7, LayoutHelper.createFrame(-2, -2.0f, 17, 16.0f, 0.0f, 16.0f, 0.0f));
        boolean[] r28 = new boolean[1];
        AlertDialog.Builder r0 = new AlertDialog.Builder(r19).setTitle(LocaleController.getString(R.string.BotRemoveVerificationTitle));
        if (r23 < 0) goto L15;
        int r1 = R.string.BotRemoveVerificationText;
    L16:
        r0.setMessage(LocaleController.getString(r1)).setView(r4).setNegativeButton(LocaleController.getString(R.string.Cancel), null).setPositiveButton(LocaleController.getString(R.string.Remove), new BotVerifySheet$$ExternalSyntheticLambda5(r28, r20, r21, r23, r26)).makeRed(-1).show();
        return;
    L15:
        r1 = R.string.BotRemoveVerificationChatText;
        goto L16
    L8:
        TLRPC.Chat r29 = MessagesController.getInstance(r20).getChat(Long.valueOf(-r23));
        if (r29 != null) goto L11;
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        r22 = r29;
        goto L12
    L11:
        r3 = r29.title;
        r22 = r29;
        goto L12
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void openSheet(Context r26, int r27, long r28, long r30, TL_bots.botVerifierSettings r32, Utilities.Callback<Boolean> r33) {
        if (r26 != null) goto L5;
        return;
    L5:
        MessagesController.getInstance(r27).getUser(Long.valueOf(r28));
        if (r30 < 0) goto L12;
        TLRPC.User r4 = MessagesController.getInstance(r27).getUser(Long.valueOf(r30));
        String r5 = UserObject.getForcedFirstName(r4);
        if (r4.bot_verification_icon != r32.icon) goto L11;
        openRemoveVerify(r26, r27, r28, r30, r32, r33);
        return;
    L11:
        String r7 = r5;
        TLRPC.Chat r6 = null;
        TLRPC.User r52 = r4;
        TLRPC.Chat r42 = r4;
    L21:
        BottomSheet.Builder r8 = new BottomSheet.Builder(r26, true);
        LinearLayout r11 = new LinearLayout(r26);
        r11.setOrientation(1);
        r11.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f));
        r11.setClipChildren(false);
        r11.setClipToPadding(false);
        FrameLayout r12 = new FrameLayout(r26);
        r12.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), Theme.getColor(Theme.key_groupcreate_spanBackground)));
        BackupImageView r3 = new BackupImageView(r26);
        r3.setRoundRadius(AndroidUtilities.dp(28.0f));
        AvatarDrawable r14 = new AvatarDrawable();
        r14.setInfo(r42);
        r3.setForUserOrChat(r42, r14);
        r12.addView(r3, LayoutHelper.createFrame(28, 28, 51));
        BackupImageView r34 = new BackupImageView(r26);
        r34.setEmojiColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_verifiedBackground), PorterDuff.Mode.SRC_IN));
        int r43 = 3;
        r34.setAnimatedEmojiDrawable(AnimatedEmojiDrawable.make(r27, 3, r32.icon));
        r12.addView(r34, LayoutHelper.createFrame(20, 20.0f, 19, 34.0f, 0.0f, 0.0f, 0.0f));
        SimpleTextView r35 = new SimpleTextView(r26);
        r35.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r35.setTextSize(13);
        r35.setEllipsizeByGradient(true);
        r35.setText(r7);
        r35.setWidthWrapContent(true);
        r12.addView(r35, LayoutHelper.createFrame(-2, -2.0f, 19, 57.0f, 0.0f, 10.0f, 0.0f));
        r11.addView(r12, LayoutHelper.createLinear(-2, -2, 1, 16, 0, 16, 0));
        TextView r36 = new TextView(r26);
        int r122 = Theme.key_windowBackgroundWhiteBlackText;
        r36.setTextColor(Theme.getColor(r122));
        r36.setTextSize(1, 20.0f);
        r36.setGravity(17);
        if (UserObject.isBot(r52) == false) goto L24;
        r36.setText(LocaleController.getString(R.string.BotVerifyBotTitle));
    L30:
        r36.setTypeface(AndroidUtilities.bold());
        r11.addView(r36, LayoutHelper.createLinear(-1, -2, 24.0f, 21.0f, 24.0f, 8.33f));
        TextView r53 = new TextView(r26);
        r53.setTextColor(Theme.getColor(r122));
        r53.setTextSize(1, 14.0f);
        r53.setGravity(17);
        NotificationCenter.listenEmojiLoading(r53);
        r53.setText(Emoji.replaceEmoji(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotVerifyText, new Object[]{r7})), r53.getPaint().getFontMetricsInt(), false));
        r11.addView(r53, LayoutHelper.createLinear(-1, -2, 24.0f, 0.0f, 24.0f, 22.0f));
        int r54 = MessagesController.getInstance(r27).botVerificationDescriptionLengthLimit;
        EditTextBoldCursor r37 = new EditTextBoldCursor(r26);
        OutlineTextContainerView r142 = new OutlineTextContainerView(r26);
        r142.setForceForceUseCenter(true);
        r142.setText(LocaleController.getString(R.string.BotVerifyDescription));
        r142.setLeftPadding(AndroidUtilities.dp(2.0f));
        r37.setTextColor(Theme.getColor(r122));
        r37.setCursorSize(AndroidUtilities.dp(20.0f));
        r37.setCursorWidth(1.5f);
        r37.setBackground(null);
        r37.setTextSize(1, 18.0f);
        r37.setMaxLines(15);
        r37.setInputType(180225);
        r37.setTypeface(Typeface.DEFAULT);
        r37.setSelectAllOnFocus(true);
        r37.setHighlightColor(Theme.getColor(Theme.key_chat_inTextSelectionHighlight));
        r37.setHandlesColor(Theme.getColor(Theme.key_chat_TextSelectionCursor));
        if (LocaleController.isRTL == false) goto L33;
        r43 = 5;
    L33:
        r37.setGravity(r43);
        r37.setOnFocusChangeListener(new BotVerifySheet$$ExternalSyntheticLambda2(r142, r37));
        r142.attachEditText(r37);
        r142.addView(r37, LayoutHelper.createFrame(-1, -2.0f, 48, 12.0f, 4.0f, 12.0f, 4.0f));
        r11.addView(r142, LayoutHelper.createLinear(-1, -2));
        r37.addTextChangedListener(new EditTextSuggestionsFix());
        r37.addTextChangedListener(new AnonymousClass1(r37, r54, r142));
        if (TextUtils.isEmpty(r32.custom_description) == true) goto L39;
        r37.setText(r32.custom_description);
        if (r32.can_modify_custom_description == true) goto L42;
        r37.setEnabled(false);
        r37.setFocusable(false);
        r37.setFocusableInTouchMode(false);
    L42:
        if (r32.can_modify_custom_description == false) goto L48;
        TextView r44 = new TextView(r26);
        r44.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4));
        r44.setTextSize(1, 12.0f);
        if (r30 < 0) goto L46;
        int r1 = R.string.BotVerifyDescriptionInfo;
    L47:
        r44.setText(LocaleController.getString(r1));
        r44.setPadding(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(27.0f));
        int r62 = -1;
        r11.addView(r44, LayoutHelper.createFrame(-1, -2.0f));
    L49:
        ButtonWithCounterView r13 = new ButtonWithCounterView(r26, null);
        r13.setText(r36.getText(), false);
        r11.addView(r13, LayoutHelper.createLinear(r62, 48));
        r8.setCustomView(r11);
        BottomSheet r112 = r8.create();
        r13.setOnClickListener(new BotVerifySheet$$ExternalSyntheticLambda3(r13, r32, r37, r54, r142, r27, r28, r30, r112, r33));
        r112.smoothKeyboardAnimationEnabled = true;
        r112.smoothKeyboardByBottom = true;
        r112.show();
        return;
    L46:
        r1 = R.string.BotVerifyDescriptionInfoChat;
        goto L47
    L48:
        r62 = -1;
        r11.addView(new View(r26), LayoutHelper.createFrame(-1, 12.0f));
        goto L49
    L39:
        if (r32.can_modify_custom_description == true) goto L42;
        r142.setVisibility(8);
        goto L42
    L24:
        if (r52 == null) goto L27;
        r36.setText(LocaleController.getString(R.string.BotVerifyUserTitle));
        goto L30
    L27:
        if (ChatObject.isChannelAndNotMegaGroup(r6) == false) goto L29;
        r36.setText(LocaleController.getString(R.string.BotVerifyChannelTitle));
        goto L30
    L29:
        r36.setText(LocaleController.getString(R.string.BotVerifyGroupTitle));
        goto L30
    L12:
        TLRPC.Chat r45 = MessagesController.getInstance(r27).getChat(Long.valueOf(-r30));
        if (r45 != null) goto L15;
        String r55 = _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        if (r45.bot_verification_icon != r32.icon) goto L20;
        openRemoveVerify(r26, r27, r28, r30, r32, r33);
        return;
    L20:
        r6 = r45;
        r7 = r55;
        r52 = null;
        r42 = r45;
        goto L21
    L15:
        r55 = r45.title;
        goto L17
    }

    public static void openVerify(int r10, long r11, TL_bots.botVerifierSettings r13) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L5;
        return;
    L5:
        Bundle r1 = new Bundle();
        r1.putBoolean("onlySelect", true);
        r1.putInt("dialogsType", 16);
        r1.putBoolean("resetDelegate", false);
        DialogsActivity r5 = new DialogsActivity(r1);
        r5.setCurrentAccount(r10);
        r5.setDelegate(new BotVerifySheet$$ExternalSyntheticLambda0(r5, r10, r11, r13));
        r0.presentFragment(r5);
    }
}
