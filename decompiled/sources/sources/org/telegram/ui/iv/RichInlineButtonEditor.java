package org.telegram.ui.iv;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.AlertDialogDecor;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.TopicsFragment;
import org.telegram.ui.iv.RichEditorListView;

/* loaded from: classes7.dex */
public abstract class RichInlineButtonEditor {

    public interface BlockApply {
        void run(String r1, String r2);
    }

    public interface UserPicked {
        void run(long r1);
    }

    public static /* synthetic */ void $r8$lambda$6_hV9xJkRph8O2RL7FmP6qEKnQg(Context r0, Theme.ResourcesProvider r1, RichEditorListView.BlockButtonEdit r2, boolean r3) {
        showBlockLinkDialog(r0, r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Gi4Gfh1y_O3TV1ASyCBSCpCELx0(RichEditorListView.InlineButtonEdit r1, String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L5;
        return;
    L5:
        TL_keyboard.TL_inlineButtonTypeUrl r0 = new TL_keyboard.TL_inlineButtonTypeUrl();
        r0.url = r2;
        r1.apply(r0);
    }

    public static /* synthetic */ void $r8$lambda$IfhlbcYMJ4HNaqXpUlBv4yxjpYw(RichEditorListView.InlineButtonEdit r0, boolean r1) {
        showInlineLinkDialog(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$JlIy6My55sprHvl_5eMxqrbVgy4(RichEditorListView.InlineButtonEdit r1, long r2) {
        TL_keyboard.TL_inlineButtonTypeUserProfile r0 = new TL_keyboard.TL_inlineButtonTypeUserProfile();
        r0.user_id = r2;
        r1.apply(r0);
    }

    public static /* synthetic */ void $r8$lambda$P7dQtan30XNyU9h1Ye5r7PHcUVQ(Runnable r0, AlertDialog r1, int r2) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$RO9v448iYbuF0MxMF5pgWUDRNNs(Context r0, Theme.ResourcesProvider r1, RichEditorListView.BlockButtonEdit r2, boolean r3) {
        showBlockCopyDialog(r0, r1, r2, r3);
    }

    /* renamed from: $r8$lambda$W-xOSkdjUS_c3XezDSneFYNktOY, reason: not valid java name */
    public static /* synthetic */ void m21870$r8$lambda$WxOSkdjUS_c3XezDSneFYNktOY(EditTextBoldCursor r1, DialogInterface r2) {
        r1.requestFocus();
        AndroidUtilities.showKeyboard(r1);
        r1.setSelection(0, r1.length());
    }

    /* renamed from: $r8$lambda$aQAC9urWRNFWGEd1_EG2stusK-g, reason: not valid java name */
    public static /* synthetic */ void m21871$r8$lambda$aQAC9urWRNFWGEd1_EG2stusKg(EditTextBoldCursor r1, BaseFragment r2, boolean r3, RichEditorListView.BlockButtonEdit r4) {
        String r12 = r1.getText().toString().trim();
        if (TextUtils.isEmpty(r12) == false) goto L5;
        return;
    L5:
        showUserPicker(r2, r3, new RichInlineButtonEditor$$ExternalSyntheticLambda18(r4, r12));
    }

    public static /* synthetic */ void $r8$lambda$agvEds9tSky5DHnVmGKbHvOwxzo(boolean r0, Runnable r1, EditTextBoldCursor r2, RichEditorListView.BlockButtonEdit r3, AlertDialog r4, int r5) {
        if (r0 == true) goto L5;
        r1.run();
        return;
    L5:
        String r02 = r2.getText().toString().trim();
        if (TextUtils.isEmpty(r02) == false) goto L8;
        return;
    L8:
        if (r3.getUserId() <= 0) goto L13;
        TL_keyboard.TL_inlineButtonTypeUserProfile r12 = new TL_keyboard.TL_inlineButtonTypeUserProfile();
        r12.user_id = r3.getUserId();
        r3.apply(r02, r12);
        return;
    }

    /* renamed from: $r8$lambda$k4Uyi-bSmgnkrb0D_htrUlGmT84, reason: not valid java name */
    public static /* synthetic */ void m21872$r8$lambda$k4UyibSmgnkrb0D_htrUlGmT84(RichEditorListView.InlineButtonEdit r1, String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L5;
        return;
    L5:
        TL_keyboard.TL_inlineButtonTypeCopy r0 = new TL_keyboard.TL_inlineButtonTypeCopy();
        r0.copy_text = r2;
        r1.apply(r0);
    }

    /* renamed from: $r8$lambda$kiEtvQxyO3M-Gj9F2wc7QVpOmzw, reason: not valid java name */
    public static /* synthetic */ void m21873$r8$lambda$kiEtvQxyO3MGj9F2wc7QVpOmzw(EditTextBoldCursor r0, EditTextBoldCursor r1, BlockApply r2, AlertDialog r3, int r4) {
        String r02 = r0.getText().toString().trim();
        String r12 = r1.getText().toString().trim();
        if (TextUtils.isEmpty(r02) == false) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r12) == true) goto L9;
        r2.run(r02, r12);
        return;
    }

    /* renamed from: $r8$lambda$mspDOiWdcbdOdd1-wNlivRibXBo, reason: not valid java name */
    public static /* synthetic */ void m21874$r8$lambda$mspDOiWdcbdOdd1wNlivRibXBo(BaseFragment r0, RichEditorListView.InlineButtonEdit r1, boolean r2) {
        showInlineUserPicker(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$p-K8tTR3ncT9dsKrIrnXn_0uaRg, reason: not valid java name */
    public static /* synthetic */ void m21875$r8$lambda$pK8tTR3ncT9dsKrIrnXn_0uaRg(RichEditorListView.InlineButtonEdit r0, boolean r1) {
        showInlineCopyDialog(r0, r1);
    }

    /* renamed from: $r8$lambda$plAy2aj-P4WFvyMss9W5bArwOH0, reason: not valid java name */
    public static /* synthetic */ void m21876$r8$lambda$plAy2ajP4WFvyMss9W5bArwOH0(RichEditorListView.BlockButtonEdit r1, String r2, String r3) {
        TL_keyboard.TL_inlineButtonTypeCopy r0 = new TL_keyboard.TL_inlineButtonTypeCopy();
        r0.copy_text = r3;
        r1.apply(r2, r0);
    }

    /* renamed from: $r8$lambda$qMBpry1bfbAa-E_RzYXh0pBN8JA, reason: not valid java name */
    public static /* synthetic */ void m21877$r8$lambda$qMBpry1bfbAaE_RzYXh0pBN8JA(RichEditorListView.BlockButtonEdit r0, AlertDialog r1, int r2) {
        r0.delete();
    }

    public static /* synthetic */ void $r8$lambda$swtvOWFWJJOtofFsTfBDP_iuzXE(RichEditorListView.BlockButtonEdit r0, AlertDialog r1, int r2) {
        r0.delete();
    }

    /* renamed from: $r8$lambda$um-XAv4tsqZtiYmQE9KLOcy84t8, reason: not valid java name */
    public static /* synthetic */ void m21878$r8$lambda$umXAv4tsqZtiYmQE9KLOcy84t8(RichEditorListView.BlockButtonEdit r1, String r2, long r3) {
        TL_keyboard.TL_inlineButtonTypeUserProfile r0 = new TL_keyboard.TL_inlineButtonTypeUserProfile();
        r0.user_id = r3;
        r1.apply(r2, r0);
    }

    public static /* synthetic */ void $r8$lambda$wZL_qzcmJBk47z4zB5GbKXEawDM(RichEditorListView.BlockButtonEdit r1, String r2, String r3) {
        TL_keyboard.TL_inlineButtonTypeUrl r0 = new TL_keyboard.TL_inlineButtonTypeUrl();
        r0.url = r3;
        r1.apply(r2, r0);
    }

    /* renamed from: $r8$lambda$xD89-Y_QWSGhRCV4ruUcWuJky28, reason: not valid java name */
    public static /* synthetic */ boolean m21879$r8$lambda$xD89Y_QWSGhRCV4ruUcWuJky28(UserPicked r0, DialogsActivity r1, ArrayList r2, CharSequence r3, boolean r4, boolean r5, int r6, int r7, TopicsFragment r8) {
        if (r2 != null) goto L5;
    L11:
        return false;
    L5:
        if (r2.isEmpty() == true) goto L11;
        if (((MessagesStorage.TopicKey) r2.get(0)).dialogId <= 0) goto L11;
        r0.run(((MessagesStorage.TopicKey) r2.get(0)).dialogId);
        r1.finishFragment();
        return true;
    }

    /* renamed from: $r8$lambda$yBW9ItDuAO-pZHZsSd2izABCyuA, reason: not valid java name */
    public static /* synthetic */ void m21880$r8$lambda$yBW9ItDuAOpZHZsSd2izABCyuA(BaseFragment r0, Context r1, Theme.ResourcesProvider r2, RichEditorListView.BlockButtonEdit r3, boolean r4) {
        showBlockProfileDialog(r0, r1, r2, r3, r4);
    }

    private static void addCancelAndDelete(AlertDialog.Builder r3, RichEditorListView.BlockButtonEdit r4) {
        if (r4.exists() == false) goto L6;
        r3.setNeutralButton(LocaleController.getString(R.string.Delete), new RichInlineButtonEditor$$ExternalSyntheticLambda0(r4)).setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        return;
    L6:
        r3.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
    }

    private static EditTextBoldCursor createField(Context r2, Theme.ResourcesProvider r3, String r4, String r5) {
        EditTextBoldCursor r0 = new EditTextBoldCursor(r2);
        r0.setTextSize(1, 18.0f);
        r0.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r3));
        r0.setHintText(r4);
        r0.setHintColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText, r3));
        r0.setHeaderHintColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader, r3));
        r0.setSingleLine(true);
        r0.setFocusable(true);
        r0.setTransformHintToHeaderOnFocus(false);
        r0.setTransformHintToHeader(true);
        if (r5 != null) goto L5;
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        r0.setText(r5);
        r0.setLineColors(Theme.getColor(Theme.key_windowBackgroundWhiteInputField, r3), Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated, r3), Theme.getColor(Theme.key_text_RedRegular, r3));
        r0.setImeOptions(5);
        r0.setBackgroundDrawable(null);
        r0.setPadding(0, 0, 0, 0);
        r0.setHighlightColor(Theme.getColor(Theme.key_chat_inTextSelectionHighlight, r3));
        r0.setHandlesColor(Theme.getColor(Theme.key_chat_TextSelectionCursor, r3));
        return r0;
    }

    private static AlertDialog.Builder createInputDialogBuilder(Context r0, Theme.ResourcesProvider r1, boolean r2) {
        if (r2 == false) goto L6;
        return new AlertDialog.Builder(r0, r1);
    L6:
        return new AlertDialogDecor.Builder(r0, r1);
    }

    private static void editExisting(BaseFragment r1, RichEditorListView.InlineButtonEdit r2, TL_keyboard.InlineButtonType r3, boolean r4) {
        if ((r3 instanceof TL_keyboard.TL_inlineButtonTypeUrl) == false) goto L7;
        showInlineLinkDialog(r2, r4);
        return;
    L7:
        if ((r3 instanceof TL_keyboard.TL_inlineButtonTypeCopy) == false) goto L11;
        showInlineCopyDialog(r2, r4);
        return;
    L11:
        if ((r3 instanceof TL_keyboard.TL_inlineButtonTypeUserProfile) == false) goto L14;
        showInlineUserPicker(r1, r2, r4);
        return;
    }

    private static void editExistingBlock(BaseFragment r1, Context r2, Theme.ResourcesProvider r3, RichEditorListView.BlockButtonEdit r4, TL_keyboard.InlineButtonType r5, boolean r6) {
        if ((r5 instanceof TL_keyboard.TL_inlineButtonTypeUrl) == false) goto L7;
        showBlockLinkDialog(r2, r3, r4, r6);
        return;
    L7:
        if ((r5 instanceof TL_keyboard.TL_inlineButtonTypeCopy) == false) goto L11;
        showBlockCopyDialog(r2, r3, r4, r6);
        return;
    L11:
        if ((r5 instanceof TL_keyboard.TL_inlineButtonTypeUserProfile) == false) goto L14;
        showBlockProfileDialog(r1, r2, r3, r4, r6);
        return;
    }

    public static ItemOptions show(ItemOptions r6, BaseFragment r7, Context r8, Theme.ResourcesProvider r9, RichEditorListView.InlineButtonEdit r10) {
        return show(r6, r7, r8, r9, r10, false);
    }

    public static ItemOptions show(ItemOptions r2, BaseFragment r3, Context r4, Theme.ResourcesProvider r5, RichEditorListView.InlineButtonEdit r6, boolean r7) {
        TL_keyboard.InlineButtonType r42 = r6.getType();
        if (r42 == null) goto L6;
        editExisting(r3, r6, r42, r7);
        return null;
    L6:
        r2.add(R.drawable.media_link_24, LocaleController.getString(R.string.ChatLink), new RichInlineButtonEditor$$ExternalSyntheticLambda1(r6, r7)).add(R.drawable.msg_copy, LocaleController.getString(R.string.Copy), new RichInlineButtonEditor$$ExternalSyntheticLambda2(r6, r7)).add(R.drawable.left_status_profile, LocaleController.getString(R.string.RichEditorUserProfile), new RichInlineButtonEditor$$ExternalSyntheticLambda3(r3, r6, r7));
        return r2.show();
    }

    public static ItemOptions showBlock(ItemOptions r6, BaseFragment r7, Context r8, Theme.ResourcesProvider r9, RichEditorListView.BlockButtonEdit r10) {
        return showBlock(r6, r7, r8, r9, r10, false);
    }

    public static ItemOptions showBlock(ItemOptions r6, BaseFragment r7, Context r8, Theme.ResourcesProvider r9, RichEditorListView.BlockButtonEdit r10, boolean r11) {
        TL_keyboard.InlineButtonType r4 = r10.getType();
        if (r4 == null) goto L6;
        editExistingBlock(r7, r8, r9, r10, r4, r11);
        return null;
    L6:
        r6.add(R.drawable.media_link_24, LocaleController.getString(R.string.ChatLink), new RichInlineButtonEditor$$ExternalSyntheticLambda7(r8, r9, r10, r11)).add(R.drawable.msg_copy, LocaleController.getString(R.string.Copy), new RichInlineButtonEditor$$ExternalSyntheticLambda8(r8, r9, r10, r11)).add(R.drawable.left_status_profile, LocaleController.getString(R.string.RichEditorUserProfile), new RichInlineButtonEditor$$ExternalSyntheticLambda9(r7, r8, r9, r10, r11));
        return r6.show();
    }

    private static void showBlockCopyDialog(Context r10, Theme.ResourcesProvider r11, RichEditorListView.BlockButtonEdit r12, boolean r13) {
        boolean r0 = r12.exists();
        TL_keyboard.InlineButtonType r1 = r12.getType();
        if ((r1 instanceof TL_keyboard.TL_inlineButtonTypeCopy) == false) goto L6;
        String r14 = ((TL_keyboard.TL_inlineButtonTypeCopy) r1).copy_text;
    L5:
        String r8 = r14;
        if (r0 == false) goto L9;
        int r02 = R.string.RichEditorEditCopyButton;
    L10:
        showBlockTextAndValueDialog(r10, r11, r12, r13, LocaleController.getString(r02), LocaleController.getString(R.string.RichEditorButtonCopyText), r8, new RichInlineButtonEditor$$ExternalSyntheticLambda16(r12));
        return;
    L9:
        r02 = R.string.RichEditorCreateCopyButton;
        goto L10
    L6:
        r14 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L5
    }

    private static void showBlockLinkDialog(Context r10, Theme.ResourcesProvider r11, RichEditorListView.BlockButtonEdit r12, boolean r13) {
        boolean r0 = r12.exists();
        TL_keyboard.InlineButtonType r1 = r12.getType();
        if ((r1 instanceof TL_keyboard.TL_inlineButtonTypeUrl) == false) goto L6;
        String r14 = ((TL_keyboard.TL_inlineButtonTypeUrl) r1).url;
    L5:
        String r8 = r14;
        if (r0 == false) goto L9;
        int r02 = R.string.RichEditorEditLinkButton;
    L10:
        showBlockTextAndValueDialog(r10, r11, r12, r13, LocaleController.getString(r02), LocaleController.getString(R.string.RichEditorButtonURL), r8, new RichInlineButtonEditor$$ExternalSyntheticLambda11(r12));
        return;
    L9:
        r02 = R.string.RichEditorCreateLinkButton;
        goto L10
    L6:
        r14 = "https://";
        goto L5
    }

    private static void showBlockProfileDialog(BaseFragment r6, Context r7, Theme.ResourcesProvider r8, RichEditorListView.BlockButtonEdit r9, boolean r10) {
        boolean r0 = r9.exists();
        LinearLayout r1 = new LinearLayout(r7);
        r1.setOrientation(1);
        int r4 = 0;
        r1.setPadding(AndroidUtilities.dp(24.0f), 0, AndroidUtilities.dp(24.0f), 0);
        EditTextBoldCursor r2 = createField(r7, r8, LocaleController.getString(R.string.RichEditorButtonText), r9.getLabel());
        r1.addView(r2, LayoutHelper.createLinear(-1, 64));
        RichInlineButtonEditor$$ExternalSyntheticLambda12 r3 = new RichInlineButtonEditor$$ExternalSyntheticLambda12(r2, r6, r10, r9);
        AlertDialog.Builder r62 = createInputDialogBuilder(r7, r8, r10);
        if (r0 == false) goto L5;
        int r72 = R.string.RichEditorEditProfileButton;
    L6:
        r62.setTitle(LocaleController.getString(r72)).setView(r1).setPositiveButton(LocaleController.getString(R.string.OK), new RichInlineButtonEditor$$ExternalSyntheticLambda13(r0, r3, r2, r9));
        if (r0 == false) goto L9;
        r4 = -4;
        r62.setNeutralButton(LocaleController.getString(R.string.RichEditorChangeUser), new RichInlineButtonEditor$$ExternalSyntheticLambda14(r3)).setButton(-4, LocaleController.getString(R.string.Delete), new RichInlineButtonEditor$$ExternalSyntheticLambda15(r9)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).twoRowsButtonsWhenNeeded();
    L10:
        showInputDialog(r62, r2, r4, r8);
        return;
    L9:
        r62.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        goto L10
    L5:
        r72 = R.string.RichEditorCreateProfileButton;
        goto L6
    }

    private static void showBlockTextAndValueDialog(Context r5, Theme.ResourcesProvider r6, RichEditorListView.BlockButtonEdit r7, boolean r8, String r9, String r10, String r11, BlockApply r12) {
        LinearLayout r0 = new LinearLayout(r5);
        r0.setOrientation(1);
        int r3 = 0;
        r0.setPadding(AndroidUtilities.dp(24.0f), 0, AndroidUtilities.dp(24.0f), 0);
        EditTextBoldCursor r1 = createField(r5, r6, LocaleController.getString(R.string.RichEditorButtonText), r7.getLabel());
        EditTextBoldCursor r102 = createField(r5, r6, r10, r11);
        r0.addView(r1, LayoutHelper.createLinear(-1, 64));
        r0.addView(r102, LayoutHelper.createLinear(-1, 64));
        AlertDialog.Builder r52 = createInputDialogBuilder(r5, r6, r8);
        r52.setTitle(r9).setView(r0).setPositiveButton(LocaleController.getString(R.string.OK), new RichInlineButtonEditor$$ExternalSyntheticLambda19(r1, r102, r12));
        addCancelAndDelete(r52, r7);
        if (TextUtils.isEmpty(r1.getText()) == true) goto L7;
        r1 = r102;
    L7:
        if (r7.exists() == false) goto L9;
        r3 = -3;
    L9:
        showInputDialog(r52, r1, r3, r6);
    }

    private static void showInlineCopyDialog(RichEditorListView.InlineButtonEdit r9, boolean r10) {
        TL_keyboard.InlineButtonType r0 = r9.getType();
        boolean r1 = r0 instanceof TL_keyboard.TL_inlineButtonTypeCopy;
        if (r1 == false) goto L6;
        String r02 = ((TL_keyboard.TL_inlineButtonTypeCopy) r0).copy_text;
    L5:
        String r5 = r02;
        r9.hideSelectionUi();
        if (r1 == false) goto L10;
        int r03 = R.string.RichEditorEditCopyButton;
    L11:
        r9.showInputDialog(LocaleController.getString(r03), LocaleController.getString(R.string.RichEditorButtonCopyText), r5, false, !r10, new RichInlineButtonEditor$$ExternalSyntheticLambda5(r9));
        return;
    L10:
        r03 = R.string.RichEditorCreateCopyButton;
        goto L11
    L6:
        r02 = r9.getLabel();
        goto L5
    }

    private static void showInlineLinkDialog(RichEditorListView.InlineButtonEdit r9, boolean r10) {
        TL_keyboard.InlineButtonType r0 = r9.getType();
        boolean r1 = r0 instanceof TL_keyboard.TL_inlineButtonTypeUrl;
        if (r1 == false) goto L6;
        String r02 = ((TL_keyboard.TL_inlineButtonTypeUrl) r0).url;
    L5:
        String r5 = r02;
        r9.hideSelectionUi();
        if (r1 == false) goto L10;
        int r03 = R.string.RichEditorEditLinkButton;
    L11:
        r9.showInputDialog(LocaleController.getString(r03), LocaleController.getString(R.string.RichEditorButtonURL), r5, true, !r10, new RichInlineButtonEditor$$ExternalSyntheticLambda4(r9));
        return;
    L10:
        r03 = R.string.RichEditorCreateLinkButton;
        goto L11
    L6:
        r02 = "https://";
        goto L5
    }

    private static void showInlineUserPicker(BaseFragment r1, RichEditorListView.InlineButtonEdit r2, boolean r3) {
        r2.dismissSelectionUi();
        showUserPicker(r1, r3, new RichInlineButtonEditor$$ExternalSyntheticLambda6(r2));
    }

    private static AlertDialog showInputDialog(AlertDialog.Builder r2, EditTextBoldCursor r3, int r4, Theme.ResourcesProvider r5) {
        AlertDialog r22 = r2.create();
        r22.setOnShowListener(new RichInlineButtonEditor$$ExternalSyntheticLambda17(r3));
        r22.showDelayed(250);
        if (r4 != 0) goto L5;
    L7:
        return r22;
    L5:
        if ((r22.getButton(r4) instanceof TextView) == false) goto L7;
        ((TextView) r22.getButton(r4)).setTextColor(Theme.getColor(Theme.key_text_RedBold, r5));
        goto L7
    }

    private static void showUserPicker(BaseFragment r5, boolean r6, UserPicked r7) {
        if (r5 != null) goto L4;
        return;
    L4:
        Bundle r0 = new Bundle();
        r0.putBoolean("onlySelect", true);
        r0.putBoolean("checkCanWrite", false);
        r0.putInt("dialogsType", 4);
        DialogsActivity r1 = new DialogsActivity(r0);
        r1.setDelegate(new RichInlineButtonEditor$$ExternalSyntheticLambda10(r7));
        if (r6 == false) goto L8;
        BaseFragment.BottomSheetParams r62 = new BaseFragment.BottomSheetParams();
        r62.transitionFromLeft = true;
        r62.allowNestedScroll = false;
        r5.showAsSheet(r1, r62);
        return;
    L8:
        r5.presentFragment(r1);
    }
}
