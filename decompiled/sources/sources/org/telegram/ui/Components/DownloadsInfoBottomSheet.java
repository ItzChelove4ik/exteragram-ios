package org.telegram.ui.Components;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.widget.NestedScrollView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.CacheControlActivity;

/* loaded from: classes7.dex */
public class DownloadsInfoBottomSheet extends BottomSheet {
    public static /* synthetic */ void $r8$lambda$VOwYj_6M82azc6opdqHqm1UCo2Y(DownloadsInfoBottomSheet r0, BaseFragment r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    /* renamed from: $r8$lambda$dWG-e6sDjI1oHpB-Vcjt2WcN2bQ, reason: not valid java name */
    public static /* synthetic */ void m10128$r8$lambda$dWGe6sDjI1oHpBVcjt2WcN2bQ(DownloadsInfoBottomSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$i2i8OTLyz8l5ynliADrqr-5kwXs, reason: not valid java name */
    public static /* synthetic */ void m10129$r8$lambda$i2i8OTLyz8l5ynliADrqr5kwXs(DownloadsInfoBottomSheet r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public DownloadsInfoBottomSheet(Context r24, BaseFragment r25, boolean r26) {
        super(r24, r26);
        setApplyBottomPadding(false);
        setApplyTopPadding(false);
        fixNavigationBar(getThemedColor(Theme.key_dialogBackground));
        LinearLayout r3 = new LinearLayout(r24);
        r3.setOrientation(1);
        FrameLayout r5 = new FrameLayout(r24);
        r5.addView(r3);
        ImageView r6 = new ImageView(r24);
        r6.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_listSelector)));
        r6.setColorFilter(getThemedColor(Theme.key_sheet_other));
        r6.setImageResource(R.drawable.ic_layer_close);
        r6.setOnClickListener(new DownloadsInfoBottomSheet$$ExternalSyntheticLambda0(this));
        int r8 = AndroidUtilities.dp(8.0f);
        r6.setPadding(r8, r8, r8, r8);
        r5.addView(r6, LayoutHelper.createFrame(36, 36.0f, 8388661, 6.0f, 8.0f, 8.0f, 0.0f));
        StickerImageView r62 = new StickerImageView(r24, this.currentAccount);
        r62.setStickerNum(9);
        r62.getImageReceiver().setAutoRepeat(1);
        r3.addView(r62, LayoutHelper.createLinear(110, 110, 1, 0, 26, 0, 0));
        TextView r63 = new TextView(r24);
        r63.setGravity(1);
        int r82 = Theme.key_dialogTextBlack;
        r63.setTextColor(Theme.getColor(r82));
        r63.setTextSize(1, 20.0f);
        r63.setText(LocaleController.getString(R.string.DownloadedFiles));
        r3.addView(r63, LayoutHelper.createFrame(-1, -2.0f, 0, 21.0f, 20.0f, 21.0f, 0.0f));
        TextView r64 = new TextView(r24);
        r64.setGravity(1);
        r64.setTextSize(1, 14.0f);
        r64.setTextColor(Theme.getColor(r82));
        r64.setLineSpacing(r64.getLineSpacingExtra(), r64.getLineSpacingMultiplier() * 1.1f);
        r64.setText(LocaleController.formatString("DownloadedFilesMessage", R.string.DownloadedFilesMessage, new Object[0]));
        r3.addView(r64, LayoutHelper.createFrame(-1, -2.0f, 0, 28.0f, 7.0f, 28.0f, 0.0f));
        TextView r65 = new TextView(r24);
        r65.setGravity(17);
        TextUtils.TruncateAt r10 = TextUtils.TruncateAt.END;
        r65.setEllipsize(r10);
        r65.setSingleLine(true);
        r65.setTextSize(1, 14.0f);
        r65.setTypeface(AndroidUtilities.bold());
        r65.setText(LocaleController.getString(R.string.ManageDeviceStorage));
        r65.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        int r11 = AndroidUtilities.dp(8.0f);
        int r12 = Theme.key_featuredStickers_addButton;
        r65.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r11, Theme.getColor(r12), ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_windowBackgroundWhite), 120)));
        r3.addView(r65, LayoutHelper.createFrame(-1, 48.0f, 0, 14.0f, 28.0f, 14.0f, 6.0f));
        TextView r112 = new TextView(r24);
        r112.setGravity(17);
        r112.setEllipsize(r10);
        r112.setSingleLine(true);
        r112.setTextSize(1, 14.0f);
        r112.setTypeface(AndroidUtilities.bold());
        r112.setText(LocaleController.getString(R.string.ClearDownloadsList));
        r112.setTextColor(Theme.getColor(r12));
        r112.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), 0, ColorUtils.setAlphaComponent(Theme.getColor(r12), 120)));
        r112.setLetterSpacing(0.025f);
        r3.addView(r112, LayoutHelper.createFrame(-1, 48.0f, 0, 14.0f, 0.0f, 14.0f, 6.0f));
        NestedScrollView r2 = new NestedScrollView(r24);
        r2.addView(r5);
        setCustomView(r2);
        r65.setOnClickListener(new DownloadsInfoBottomSheet$$ExternalSyntheticLambda1(this, r25));
        r112.setOnClickListener(new DownloadsInfoBottomSheet$$ExternalSyntheticLambda2(this));
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(BaseFragment r1, View r2) {
        dismiss();
        r1.presentFragment(new CacheControlActivity());
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        dismiss();
        DownloadController.getInstance(this.currentAccount).clearRecentDownloadedFiles();
    }

    public static void show(Activity r2, BaseFragment r3) {
        if (r3 == null) goto L7;
        if (r2 == null) goto L8;
        new DownloadsInfoBottomSheet(r2, r3, false).show();
        return;
    L8:
        return;
    }
}
