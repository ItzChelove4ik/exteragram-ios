package org.telegram.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.StickerImageView;

/* loaded from: classes6.dex */
public class SuggestClearDatabaseBottomSheet extends BottomSheet {

    @SuppressLint({"StaticFieldLeak"})
    private static SuggestClearDatabaseBottomSheet dialog;
    BaseFragment fragment;

    public static /* synthetic */ void $r8$lambda$BwepNoyBI8JV0cKnsndgx73IOFI(SuggestClearDatabaseBottomSheet r0, BaseFragment r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$jb28j_lyGFuCb3X0ucFTzdw652A(SuggestClearDatabaseBottomSheet r0, BaseFragment r1, AlertDialog r2, int r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    private SuggestClearDatabaseBottomSheet(BaseFragment r18) {
        super(r18.getParentActivity(), false);
        this.fragment = r18;
        Activity r2 = r18.getParentActivity();
        LinearLayout r4 = new LinearLayout(r2);
        r4.setOrientation(1);
        StickerImageView r6 = new StickerImageView(r2, this.currentAccount);
        r6.setStickerNum(7);
        r6.getImageReceiver().setAutoRepeat(1);
        r4.addView(r6, LayoutHelper.createLinear(144, 144, 1, 0, 16, 0, 0));
        TextView r62 = new TextView(r2);
        r62.setGravity(PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES);
        int r8 = Theme.key_dialogTextBlack;
        r62.setTextColor(Theme.getColor(r8));
        r62.setTextSize(1, 20.0f);
        r62.setTypeface(AndroidUtilities.bold());
        r62.setText(LocaleController.getString(R.string.SuggestClearDatabaseTitle));
        r4.addView(r62, LayoutHelper.createFrame(-1, -2.0f, 0, 21.0f, 30.0f, 21.0f, 0.0f));
        TextView r63 = new TextView(r2);
        r63.setGravity(PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES);
        r63.setTextSize(1, 15.0f);
        r63.setTextColor(Theme.getColor(r8));
        r63.setText(AndroidUtilities.replaceTags(LocaleController.formatString("SuggestClearDatabaseMessage", R.string.SuggestClearDatabaseMessage, new Object[]{AndroidUtilities.formatFileSize(r18.getMessagesStorage().getDatabaseSize())})));
        r4.addView(r63, LayoutHelper.createFrame(-1, -2.0f, 0, 21.0f, 15.0f, 21.0f, 16.0f));
        TextView r64 = new TextView(r2);
        r64.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        r64.setGravity(17);
        r64.setTextSize(1, 14.0f);
        r64.setTypeface(AndroidUtilities.bold());
        r64.setText(LocaleController.getString(R.string.ClearLocalDatabase));
        r64.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        r64.setBackgroundDrawable(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(6.0f), Theme.getColor(Theme.key_featuredStickers_addButton), ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_windowBackgroundWhite), 120)));
        r4.addView(r64, LayoutHelper.createFrame(-1, 48.0f, 0, 16.0f, 15.0f, 16.0f, 16.0f));
        r64.setOnClickListener(new SuggestClearDatabaseBottomSheet$$ExternalSyntheticLambda0(this, r18));
        ScrollView r1 = new ScrollView(r2);
        r1.addView(r4);
        setCustomView(r1);
    }

    public static void dismissDialog() {
        SuggestClearDatabaseBottomSheet r0 = dialog;
        if (r0 == null) goto L6;
        r0.dismiss();
        dialog = null;
        return;
    }

    private /* synthetic */ void lambda$new$0(BaseFragment r1, AlertDialog r2, int r3) {
        if (r1.getParentActivity() != null) goto L5;
        return;
    L5:
        MessagesController.getInstance(this.currentAccount).clearQueryTime();
        r1.getMessagesStorage().clearLocalDatabase();
    }

    private /* synthetic */ void lambda$new$1(BaseFragment r3, View r4) {
        AlertDialog.Builder r42 = new AlertDialog.Builder(r3.getParentActivity());
        r42.setTitle(LocaleController.getString(R.string.LocalDatabaseClearTextTitle));
        r42.setMessage(LocaleController.getString(R.string.LocalDatabaseClearText));
        r42.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r42.setPositiveButton(LocaleController.getString(R.string.CacheClear), new SuggestClearDatabaseBottomSheet$$ExternalSyntheticLambda1(this, r3));
        AlertDialog r2 = r42.create();
        r3.showDialog(r2);
        TextView r22 = (TextView) r2.getButton(-1);
        if (r22 == null) goto L6;
        r22.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    }

    public static void show(BaseFragment r1) {
        if (dialog != null) goto L6;
        SuggestClearDatabaseBottomSheet r0 = new SuggestClearDatabaseBottomSheet(r1);
        dialog = r0;
        r0.show();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        dialog = null;
    }
}
