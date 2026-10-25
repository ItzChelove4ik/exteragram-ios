package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public abstract class GigagroupConvertAlert extends BottomSheet {

    public static class BottomSheetCell extends FrameLayout {
        private View background;
        private TextView textView;

        /* renamed from: -$$Nest$fgetbackground, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m10830$$Nest$fgetbackground(BottomSheetCell r0) {
            return r0.background;
        }

        public BottomSheetCell(Context r11) {
            super(r11);
            View r0 = new View(r11);
            this.background = r0;
            r0.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{4.0f}));
            addView(this.background, LayoutHelper.createFrame(-1, -1.0f, 0, 16.0f, 16.0f, 16.0f, 16.0f));
            TextView r02 = new TextView(r11);
            this.textView = r02;
            r02.setLines(1);
            this.textView.setSingleLine(true);
            this.textView.setGravity(1);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.textView.setGravity(17);
            this.textView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
            this.textView.setTextSize(1, 14.0f);
            this.textView.setTypeface(AndroidUtilities.bold());
            addView(this.textView, LayoutHelper.createFrame(-2, -2, 17));
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(80.0f), TLObject.FLAG_30));
        }

        public void setText(CharSequence r1) {
            this.textView.setText(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$FTuWYgmbzOwjn4a7vilSPTP5cLA(GigagroupConvertAlert r0, AlertDialog r1, int r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$GhNpFiXkLGUQ3ICK7bl5eECsEqg(GigagroupConvertAlert r0, Context r1, BaseFragment r2, View r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$hWWRzPjgdyh5pt4ccgzsQ74-NZ4, reason: not valid java name */
    public static /* synthetic */ void m10829$r8$lambda$hWWRzPjgdyh5pt4ccgzsQ74NZ4(GigagroupConvertAlert r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public GigagroupConvertAlert(Context r19, BaseFragment r20) {
        super(r19, true);
        setApplyBottomPadding(false);
        setApplyTopPadding(false);
        LinearLayout r4 = new LinearLayout(r19);
        r4.setOrientation(1);
        setCustomView(r4);
        RLottieImageView r5 = new RLottieImageView(r19);
        r5.setAutoRepeat(true);
        r5.setAnimation(R.raw.utyan_gigagroup, 120, 120);
        r5.playAnimation();
        r4.addView(r5, LayoutHelper.createLinear(160, 160, 49, 17, 30, 17, 0));
        TextView r52 = new TextView(r19);
        r52.setTypeface(AndroidUtilities.bold());
        r52.setTextSize(1, 24.0f);
        r52.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r52.setText(LocaleController.getString(R.string.GigagroupConvertTitle));
        r4.addView(r52, LayoutHelper.createLinear(-2, -2, 49, 17, 18, 17, 0));
        LinearLayout r53 = new LinearLayout(r19);
        r53.setOrientation(1);
        r4.addView(r53, LayoutHelper.createLinear(-2, -2, 1, 0, 12, 0, 0));
        int r6 = 0;
    L3:
        int r7 = 3;
        if (r6 >= 3) goto L26;
        LinearLayout r8 = new LinearLayout(r19);
        r8.setOrientation(0);
        if (LocaleController.isRTL == false) goto L8;
        int r13 = 5;
    L9:
        r53.addView(r8, LayoutHelper.createLinear(-2, -2, r13, 0, 8, 0, 0));
        ImageView r9 = new ImageView(r19);
        int r12 = Theme.key_dialogTextGray3;
        r9.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r12), PorterDuff.Mode.MULTIPLY));
        r9.setImageResource(R.drawable.list_circle);
        TextView r11 = new TextView(r19);
        r11.setTextSize(1, 15.0f);
        r11.setTextColor(Theme.getColor(r12));
        if (LocaleController.isRTL == false) goto L12;
        r7 = 5;
    L12:
        r11.setGravity(r7 | 16);
        r11.setMaxWidth(AndroidUtilities.dp(260.0f));
        if (r6 == 0) goto L20;
        if (r6 != 1) goto L16;
        r11.setText(LocaleController.getString(R.string.GigagroupConvertInfo2));
    L22:
        if (LocaleController.isRTL == false) goto L24;
        r8.addView(r11, LayoutHelper.createLinear(-2, -2));
        r8.addView(r9, LayoutHelper.createLinear(-2, -2, 8.0f, 7.0f, 0.0f, 0.0f));
    L25:
        r6 = r6 + 1;
        goto L3
    L24:
        r8.addView(r9, LayoutHelper.createLinear(-2, -2, 0.0f, 8.0f, 8.0f, 0.0f));
        r8.addView(r11, LayoutHelper.createLinear(-2, -2));
        goto L25
    L16:
        if (r6 != 2) goto L22;
        r11.setText(LocaleController.getString(R.string.GigagroupConvertInfo3));
        goto L22
    L20:
        r11.setText(LocaleController.getString(R.string.GigagroupConvertInfo1));
        goto L22
    L8:
        r13 = 3;
        goto L9
    L26:
        BottomSheetCell r3 = new BottomSheetCell(r19);
        r3.setBackground(null);
        r3.setText(LocaleController.getString(R.string.GigagroupConvertProcessButton));
        BottomSheetCell.m10830$$Nest$fgetbackground(r3).setOnClickListener(new GigagroupConvertAlert$$ExternalSyntheticLambda0(this, r19, r20));
        r4.addView(r3, LayoutHelper.createLinear(-1, 50, 51, 0, 29, 0, 0));
        TextView r32 = new TextView(r19);
        r32.setTextSize(1, 14.0f);
        r32.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2));
        r32.setText(LocaleController.getString(R.string.GigagroupConvertCancelButton));
        r32.setGravity(17);
        r4.addView(r32, LayoutHelper.createLinear(-2, 48, 49, 17, 0, 17, 16));
        r32.setOnClickListener(new GigagroupConvertAlert$$ExternalSyntheticLambda1(this));
    }

    private /* synthetic */ void lambda$new$0(AlertDialog r1, int r2) {
        onCovert();
    }

    private /* synthetic */ void lambda$new$1(Context r2, BaseFragment r3, View r4) {
        dismiss();
        AlertDialog.Builder r42 = new AlertDialog.Builder(r2);
        r42.setTitle(LocaleController.getString(R.string.GigagroupConvertAlertTitle));
        r42.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.GigagroupConvertAlertText)));
        r42.setPositiveButton(LocaleController.getString(R.string.GigagroupConvertAlertConver), new GigagroupConvertAlert$$ExternalSyntheticLambda2(this));
        r42.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r3.showDialog(r42.create());
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        onCancel();
        dismiss();
    }

    public abstract void onCancel();

    public abstract void onCovert();
}
