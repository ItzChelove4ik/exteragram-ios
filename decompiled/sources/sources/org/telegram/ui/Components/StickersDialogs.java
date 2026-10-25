package org.telegram.ui.Components;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.FileRefController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.ContentPreviewViewer;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.Stories.recorder.EmojiBottomSheet;

/* loaded from: classes7.dex */
public abstract class StickersDialogs {

    /* renamed from: org.telegram.ui.Components.StickersDialogs$1, reason: invalid class name */
    public class AnonymousClass1 extends EditTextBoldCursor {
        public AnonymousClass1(Context r1) {
            super(r1);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Components.StickersDialogs$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcher {
        final /* synthetic */ NumberTextView val$checkTextView;
        final /* synthetic */ EditTextBoldCursor val$editText;

        public AnonymousClass2(NumberTextView r1, EditTextBoldCursor r2) {
            this.val$checkTextView = r1;
            this.val$editText = r2;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r4) {
            this.val$checkTextView.setNumber(50 - Character.codePointCount(r4, 0, r4.length()), true);
            this.val$editText.setErrorText(null);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public static /* synthetic */ CharSequence $r8$lambda$53Wx5EDwOzT1cKuG1CMoQDiyqi0(EditTextBoldCursor r0, CharSequence r1, int r2, int r3, Spanned r4, int r5, int r6) {
        if (r1.length() > 0) goto L5;
    L11:
        return r1;
    L5:
        if (Character.isWhitespace(r1.charAt(0)) == false) goto L11;
        if (TextUtils.isEmpty(r0.getText()) == true) goto L9;
        if (r5 != 0) goto L11;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static /* synthetic */ void $r8$lambda$DAW1lVNR7fxmKYYqGz1Rf67gutg(EditTextBoldCursor r2, Utilities.Callback2 r3, Context r4, boolean r5, AlertDialog r6, int r7) {
        String r72 = r2.getText().toString().trim();
        if (TextUtils.isEmpty(r72) == false) goto L5;
    L16:
        r2.setErrorText(".");
        AndroidUtilities.shakeViewSpring(r2, -6.0f);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        AndroidUtilities.showKeyboard(r2);
        return;
    L5:
        if (TextUtils.isEmpty(AndroidUtilities.translitSafe(r72.toString())) == true) goto L16;
        AndroidUtilities.hideKeyboard(r2);
        if (r3 != null) goto L11;
        return;
    L11:
        if (r5 == false) goto L13;
        DarkThemeResourceProvider r52 = null;
    L14:
        AlertDialog r0 = new AlertDialog(r4, 3, r52);
        r0.showDelayed(250);
        r3.run(r72, new StickersDialogs$$ExternalSyntheticLambda4(r0, r6, r2));
        return;
    L13:
        r52 = new DarkThemeResourceProvider();
        goto L14
    }

    /* renamed from: $r8$lambda$KwNe0gshzMmIwENIJ-T3PkIZ_Wc, reason: not valid java name */
    public static /* synthetic */ void m13133$r8$lambda$KwNe0gshzMmIwENIJT3PkIZ_Wc(ActionBarPopupWindow r0, ArrayList r1, TLRPC.TL_messages_stickerSet r2, BaseFragment r3, Theme.ResourcesProvider r4, View r5) {
        int r52 = ((Integer) r5.getTag()).intValue();
        r0.dismiss();
        if (((Integer) r1.get(r52)).intValue() != 1) goto L6;
        openStickerPickerDialog(r2, r3, r4);
        return;
    L6:
        ((ChatActivity) r3).openAttachMenuForCreatingSticker();
        ContentPreviewViewer.getInstance().setStickerSetForCustomSticker(r2);
    }

    public static /* synthetic */ void $r8$lambda$PWfBenhYVsMMCsELCmX85DIeeW0(AlertDialog r7, TLObject r8, int r9, TLRPC.Document r10, TLRPC.TL_error r11, Object r12, TLRPC.TL_stickers_addStickerToSet r13) {
        r7.dismiss();
        if ((r8 instanceof TLRPC.TL_messages_stickerSet) == false) goto L10;
        TLRPC.TL_messages_stickerSet r72 = (TLRPC.TL_messages_stickerSet) r8;
        MediaDataController.getInstance(r9).putStickerSet(r72);
        if (MediaDataController.getInstance(r9).isStickerPackInstalled(r72.set.id) == true) goto L7;
        TLObject r2 = r8;
        MediaDataController.getInstance(r9).toggleStickerSet(null, r2, 2, null, false, false);
    L8:
        AndroidUtilities.runOnUIThread(new StickersDialogs$$ExternalSyntheticLambda12(r2, r10), 250);
        return;
    L7:
        r2 = r8;
        goto L8
    L10:
        if (r11 != null) goto L12;
        return;
    L12:
        if (FileRefController.isFileRefError(r11.text) == false) goto L15;
        FileRefController.getInstance(r9).requestReference(r12, new Object[]{r13});
        return;
    L15:
        BulletinFactory.showError(r11);
    }

    public static /* synthetic */ void $r8$lambda$Q0KF3qYIa06EWHJoCmOCUPu4pZk(AlertDialog r0, AlertDialog r1, EditTextBoldCursor r2, Boolean r3) {
        r0.dismiss();
        if (r3.booleanValue() == false) goto L6;
        r1.dismiss();
        return;
    L6:
        r2.setErrorText(".");
        AndroidUtilities.shakeViewSpring(r2, -6.0f);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        AndroidUtilities.showKeyboard(r2);
    }

    /* renamed from: $r8$lambda$YO0w2MC46Ze4C-tRzzGkru9YLik, reason: not valid java name */
    public static /* synthetic */ void m13134$r8$lambda$YO0w2MC46Ze4CtRzzGkru9YLik(Runnable r0, TLRPC.StickerSet r1, AlertDialog r2, int r3) {
        r0.run();
        TLRPC.TL_stickers_deleteStickerSet r02 = new TLRPC.TL_stickers_deleteStickerSet();
        r02.stickerset = MediaDataController.getInputStickerSet(r1);
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r02, new StickersDialogs$$ExternalSyntheticLambda7());
    }

    /* renamed from: $r8$lambda$_hnR8VeKoqzA2-OPCRcOT4Rt9Us, reason: not valid java name */
    public static /* synthetic */ void m13135$r8$lambda$_hnR8VeKoqzA2OPCRcOT4Rt9Us() {
    }

    /* renamed from: $r8$lambda$eyq0pphyCX-1kZ1qcU-n4PyDQmQ, reason: not valid java name */
    public static /* synthetic */ void m13136$r8$lambda$eyq0pphyCX1kZ1qcUn4PyDQmQ(AlertDialog r8, int r9, TLRPC.Document r10, Object r11, TLRPC.TL_stickers_addStickerToSet r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new StickersDialogs$$ExternalSyntheticLambda11(r8, r13, r9, r10, r14, r11, r12));
    }

    public static /* synthetic */ boolean $r8$lambda$jLZwtbJdzymZUSGauwOzoCMqBx0(AlertDialog r0, TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L6;
        r0.getButton(-1).callOnClick();
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ void $r8$lambda$kOHx1buTxxqFdPiypijDzxz9RjA(EditTextBoldCursor r0, AlertDialog r1, int r2) {
        AndroidUtilities.hideKeyboard(r0);
        r1.dismiss();
    }

    /* renamed from: $r8$lambda$o7KvI6jticMZZAlTou-Y-SxsHIg, reason: not valid java name */
    public static /* synthetic */ Boolean m13137$r8$lambda$o7KvI6jticMZZAlTouYSxsHIg(int r3, Context r4, TLRPC.TL_messages_stickerSet r5, Object r6, TLRPC.Document r7, Boolean r8) {
        String r0 = "😀";
        String r82 = MessageObject.findAnimatedEmojiEmoticon(r7, "😀", Integer.valueOf(r3));
        if (TextUtils.isEmpty(r82) == true) goto L6;
        r0 = r82;
    L6:
        AlertDialog r42 = new AlertDialog(r4, 3);
        TLRPC.TL_stickers_addStickerToSet r83 = new TLRPC.TL_stickers_addStickerToSet();
        r83.stickerset = MediaDataController.getInputStickerSet(r5.set);
        r83.sticker = MediaDataController.getInputStickerSetItem(r7, r0);
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r83, new StickersDialogs$$ExternalSyntheticLambda9(r42, r3, r7, r6, r83));
        r42.showDelayed(350);     // Catch: Exception -> L10
    L9:
        return Boolean.TRUE;
    }

    public static /* synthetic */ void $r8$lambda$rKber1_WGsZAr6fhg8D2uGBkXZQ(TLObject r0, TLRPC.TL_error r1) {
        AndroidUtilities.runOnUIThread(new StickersDialogs$$ExternalSyntheticLambda10());
    }

    public static /* synthetic */ void $r8$lambda$y7WVL1v67IsxlypQDQjo8zkcwCk(TLObject r4, TLRPC.Document r5) {
        NotificationCenter r0 = NotificationCenter.getInstance(UserConfig.selectedAccount);
        int r1 = NotificationCenter.customStickerCreated;
        Boolean r2 = Boolean.FALSE;
        r0.postNotificationNameOnUIThread(r1, new Object[]{r2, r4, r5, null, r2});
    }

    private static int getThemedColor(int r0, Theme.ResourcesProvider r1) {
        return Theme.getColor(r0, r1);
    }

    private static void openStickerPickerDialog(TLRPC.TL_messages_stickerSet r5, BaseFragment r6, Theme.ResourcesProvider r7) {
        int r0 = UserConfig.selectedAccount;
        Context r1 = r6.getContext();
        EmojiBottomSheet r2 = new EmojiBottomSheet(r1, true, r7, false);
        r2.whenDocumentSelected(new StickersDialogs$$ExternalSyntheticLambda8(r0, r1, r5));
        if (r6.visibleDialog == null) goto L6;
        r2.show();
        return;
    L6:
        r6.showDialog(r2);
    }

    public static void showAddStickerDialog(TLRPC.TL_messages_stickerSet r11, View r12, BaseFragment r13, Theme.ResourcesProvider r14) {
        if (r13 != null) goto L4;
        return;
    L4:
        Context r0 = r13.getContext();
        if ((r13 instanceof ChatActivity) == true) goto L8;
        openStickerPickerDialog(r11, r13, r14);
        return;
    L8:
        ActionBarPopupWindow.ActionBarPopupWindowLayout r1 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r0, R.drawable.popup_fixed_alert3, r14, 0);
        ArrayList r02 = new ArrayList();
        ArrayList r6 = new ArrayList();
        ArrayList r2 = new ArrayList();
        r02.add(LocaleController.getString(R.string.StickersCreateNewSticker));
        r2.add(Integer.valueOf(R.drawable.menu_sticker_add));
        r6.add(0);
        r02.add(LocaleController.getString(R.string.StickersAddAnExistingSticker));
        r2.add(Integer.valueOf(R.drawable.menu_sticker_select));
        r6.add(1);
        ActionBarPopupWindow r5 = new ActionBarPopupWindow(r1, -2, -2);
        StickersDialogs$$ExternalSyntheticLambda5 r4 = new StickersDialogs$$ExternalSyntheticLambda5(r5, r6, r11, r13, r14);
        int r112 = 0;
    L10:
        if (r112 >= r02.size()) goto L12;
        ActionBarMenuSubItem r132 = ActionBarMenuItem.addItem(r1, ((Integer) r2.get(r112)).intValue(), (CharSequence) r02.get(r112), false, r14);
        r132.setTag(Integer.valueOf(r112));
        r132.setOnClickListener(r4);
        r112 = r112 + 1;
        goto L10
    L12:
        r5.setDismissAnimationDuration(100);
        r5.setScaleOut(true);
        r5.setOutsideTouchable(true);
        r5.setClippingEnabled(true);
        r5.setAnimationStyle(R.style.PopupContextAnimation);
        r5.setFocusable(true);
        r1.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
        r5.setInputMethodMode(2);
        r5.getContentView().setFocusableInTouchMode(true);
        int[] r133 = new int[2];
        r12.getLocationInWindow(r133);
        r5.showAtLocation(r12, 0, (r133[0] + (r12.getMeasuredWidth() / 2)) - (r1.getMeasuredWidth() / 2), (r133[1] + (r12.getMeasuredHeight() / 2)) - (r1.getMeasuredHeight() / 2));
        r5.dimBehind();
    }

    public static void showDeleteForEveryOneDialog(TLRPC.StickerSet r2, Theme.ResourcesProvider r3, Context r4, Runnable r5) {
        if (r2 == null) goto L9;
        AlertDialog r22 = new AlertDialog.Builder(r4, r3).setTitle(LocaleController.getString(R.string.StickersDeleteStickerSetTitle)).setMessage(LocaleController.getString(R.string.StickersDeleteStickerSetDescription)).setPositiveButton(LocaleController.getString(R.string.Delete), new StickersDialogs$$ExternalSyntheticLambda6(r5, r2)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
        r22.show();
        TextView r23 = (TextView) r22.getButton(-1);
        if (r23 == null) goto L8;
        r23.setTextColor(getThemedColor(Theme.key_text_RedBold, r3));
        return;
    L8:
        return;
    }

    public static void showNameEditorDialog(TLRPC.StickerSet r16, Theme.ResourcesProvider r17, Context r18, Utilities.Callback2<CharSequence, Utilities.Callback<Boolean>> r19) {
        AlertDialog.Builder r3 = new AlertDialog.Builder(r18, r17);
        if (r16 == null) goto L5;
        boolean r6 = true;
    L6:
        if (r6 == false) goto L8;
        int r7 = R.string.EditStickerPack;
    L9:
        r3.setTitle(LocaleController.getString(r7));
        r3.setMessage(LocaleController.getString(R.string.StickersChooseNameForStickerPack));
        FrameLayout r72 = new FrameLayout(r18);
        r72.setPadding(AndroidUtilities.dp(24.0f), 0, AndroidUtilities.dp(20.0f), 0);
        AnonymousClass1 r8 = new AnonymousClass1(r18);
        int r9 = Theme.key_dialogTextBlack;
        r8.setTextColor(getThemedColor(r9, r17));
        r8.setInputType(16385);
        r8.setTextSize(1, 16.0f);
        r8.setTextColor(getThemedColor(r9, r17));
        r8.setHandlesColor(getThemedColor(Theme.key_chat_TextSelectionCursor, r17));
        r8.setHeaderHintColor(getThemedColor(Theme.key_windowBackgroundWhiteBlueHeader, r17));
        r8.setSingleLine(true);
        r8.setFocusable(true);
        r8.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50), new StickersDialogs$$ExternalSyntheticLambda0(r8)});
        r8.setLineColors(getThemedColor(Theme.key_windowBackgroundWhiteInputField, r17), getThemedColor(Theme.key_windowBackgroundWhiteInputFieldActivated, r17), getThemedColor(Theme.key_text_RedRegular, r17));
        r8.setImeOptions(6);
        r8.setBackground(null);
        r8.requestFocus();
        float r92 = 0.0f;
        if (LocaleController.isRTL == false) goto L12;
        float r1 = 28.0f;
    L13:
        int r12 = AndroidUtilities.dp(r1);
        if (LocaleController.isRTL == true) goto L17;
        r92 = 28.0f;
    L17:
        r8.setPadding(r12, 0, AndroidUtilities.dp(r92), 0);
        r72.addView(r8);
        NumberTextView r13 = new NumberTextView(r18);
        r13.setCenterAlign(true);
        r13.setTextSize(15);
        r13.setNumber(50, false);
        r13.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4));
        r13.setImportantForAccessibility(2);
        if (LocaleController.isRTL == false) goto L20;
        int r4 = 3;
    L21:
        r72.addView(r13, LayoutHelper.createFrame(26, 20.0f, r4 | 16, 0.0f, 2.0f, 4.0f, 0.0f));
        r8.addTextChangedListener(new AnonymousClass2(r13, r8));
        if (r6 == false) goto L24;
        r8.setText(r16.title);
        r8.setSelection(r16.title.length());
    L24:
        r3.setView(r72);
        r3.setCustomViewOffset(4);
        if (r6 == false) goto L27;
        int r0 = R.string.Done;
    L28:
        r3.setPositiveButton(LocaleController.getString(r0), new StickersDialogs$$ExternalSyntheticLambda1(r8, r19, r18, r6));
        r3.setNegativeButton(LocaleController.getString(R.string.Cancel), new StickersDialogs$$ExternalSyntheticLambda2(r8));
        AlertDialog r02 = r3.show();
        r02.setDismissDialogByButtons(false);
        r8.setOnEditorActionListener(new StickersDialogs$$ExternalSyntheticLambda3(r02));
        return;
    L27:
        r0 = R.string.Create;
        goto L28
    L20:
        r4 = 5;
        goto L21
    L12:
        r1 = 0.0f;
        goto L13
    L8:
        r7 = R.string.NewStickerPack;
        goto L9
    L5:
        r6 = false;
        goto L6
    }
}
