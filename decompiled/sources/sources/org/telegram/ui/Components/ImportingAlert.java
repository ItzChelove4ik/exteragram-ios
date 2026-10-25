package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;

/* loaded from: classes7.dex */
public class ImportingAlert extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
    private BottomSheetCell cell;
    private boolean completed;
    private RLottieDrawable completedDrawable;
    private RLottieImageView imageView;
    private TextView[] importCountTextView;
    private TextView[] infoTextView;
    private LineProgressView lineProgressView;
    private final Runnable onFinishCallback;
    private ChatActivity parentFragment;
    private TextView percentTextView;
    private String stickersShortName;

    public static class BottomSheetCell extends FrameLayout {
        private View background;
        private RLottieImageView imageView;
        private LinearLayout linearLayout;
        private Theme.ResourcesProvider resourcesProvider;
        private TextView textView;

        /* renamed from: -$$Nest$fgetbackground, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m10919$$Nest$fgetbackground(BottomSheetCell r0) {
            return r0.background;
        }

        /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RLottieImageView m10920$$Nest$fgetimageView(BottomSheetCell r0) {
            return r0.imageView;
        }

        /* renamed from: -$$Nest$fgetlinearLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LinearLayout m10921$$Nest$fgetlinearLayout(BottomSheetCell r0) {
            return r0.linearLayout;
        }

        public BottomSheetCell(Context r10, Theme.ResourcesProvider r11) {
            super(r10);
            this.resourcesProvider = r11;
            View r112 = new View(r10);
            this.background = r112;
            int r0 = AndroidUtilities.dp(4.0f);
            int r1 = Theme.key_featuredStickers_addButton;
            r112.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r0, getThemedColor(r1), getThemedColor(Theme.key_featuredStickers_addButtonPressed)));
            addView(this.background, LayoutHelper.createFrame(-1, -1.0f, 0, 16.0f, 16.0f, 16.0f, 16.0f));
            LinearLayout r113 = new LinearLayout(r10);
            this.linearLayout = r113;
            r113.setOrientation(0);
            addView(this.linearLayout, LayoutHelper.createFrame(-2, -2, 17));
            RLottieImageView r114 = new RLottieImageView(r10);
            this.imageView = r114;
            int r02 = AndroidUtilities.dp(20.0f);
            int r3 = Theme.key_featuredStickers_buttonText;
            r114.setBackground(Theme.createCircleDrawable(r02, getThemedColor(r3)));
            this.imageView.setScaleType(ImageView.ScaleType.CENTER);
            this.imageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(r1), PorterDuff.Mode.MULTIPLY));
            this.imageView.setAnimation(R.raw.import_check, 26, 26);
            this.imageView.setScaleX(0.8f);
            this.imageView.setScaleY(0.8f);
            this.linearLayout.addView(this.imageView, LayoutHelper.createLinear(20, 20, 16));
            TextView r115 = new TextView(r10);
            this.textView = r115;
            r115.setLines(1);
            this.textView.setSingleLine(true);
            this.textView.setGravity(1);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.textView.setGravity(17);
            this.textView.setTextColor(getThemedColor(r3));
            this.textView.setTextSize(1, 14.0f);
            this.textView.setTypeface(AndroidUtilities.bold());
            this.linearLayout.addView(this.textView, LayoutHelper.createLinear(-2, -2, 16, 10, 0, 0, 0));
        }

        public int getThemedColor(int r1) {
            return Theme.getColor(r1, this.resourcesProvider);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(80.0f), TLObject.FLAG_30));
        }

        public void setGravity(int r1) {
            this.textView.setGravity(r1);
        }

        public void setText(CharSequence r1) {
            this.textView.setText(r1);
        }

        public void setTextColor(int r1) {
            this.textView.setTextColor(r1);
        }
    }

    /* renamed from: $r8$lambda$P7XkjWVMSCK7fIx9gBFgdwpW0-M, reason: not valid java name */
    public static /* synthetic */ void m10918$r8$lambda$P7XkjWVMSCK7fIx9gBFgdwpW0M(ImportingAlert r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$bySYz1aEODEBQL7TSbSSSORZOzc(ImportingAlert r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public ImportingAlert(Context r20, String r21, ChatActivity r22, Theme.ResourcesProvider r23) {
        super(r20, false, r23);
        this.importCountTextView = new TextView[2];
        this.infoTextView = new TextView[2];
        ImportingAlert$$ExternalSyntheticLambda0 r6 = new ImportingAlert$$ExternalSyntheticLambda0(this);
        this.onFinishCallback = r6;
        setApplyBottomPadding(false);
        setApplyTopPadding(false);
        this.parentFragment = r22;
        this.stickersShortName = r21;
        FrameLayout r7 = new FrameLayout(r20);
        setCustomView(r7);
        TextView r8 = new TextView(r20);
        r8.setTypeface(AndroidUtilities.bold());
        r8.setTextSize(1, 20.0f);
        int r9 = Theme.key_dialogTextBlack;
        r8.setTextColor(getThemedColor(r9));
        r8.setSingleLine(true);
        r8.setEllipsize(TextUtils.TruncateAt.END);
        r7.addView(r8, LayoutHelper.createFrame(-2, -2.0f, 51, 17.0f, 20.0f, 17.0f, 0.0f));
        RLottieDrawable r12 = new RLottieDrawable(R.raw.import_finish, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(120.0f), false, null);
        this.completedDrawable = r12;
        r12.setAllowDecodeSingleFrame(true);
        RLottieImageView r11 = new RLottieImageView(r20);
        this.imageView = r11;
        r11.setAutoRepeat(true);
        this.imageView.setAnimation(R.raw.import_loop, 120, 120);
        this.imageView.playAnimation();
        r7.addView(this.imageView, LayoutHelper.createFrame(160, 160.0f, 49, 17.0f, 79.0f, 17.0f, 0.0f));
        this.imageView.getAnimatedDrawable().setOnFinishCallback(r6, 178);
        TextView r62 = new TextView(r20);
        this.percentTextView = r62;
        r62.setTypeface(AndroidUtilities.bold());
        this.percentTextView.setTextSize(1, 24.0f);
        this.percentTextView.setTextColor(getThemedColor(r9));
        r7.addView(this.percentTextView, LayoutHelper.createFrame(-2, -2.0f, 49, 17.0f, 262.0f, 17.0f, 0.0f));
        LineProgressView r63 = new LineProgressView(getContext());
        this.lineProgressView = r63;
        r63.setProgressColor(getThemedColor(Theme.key_featuredStickers_addButton));
        this.lineProgressView.setBackColor(getThemedColor(Theme.key_dialogLineProgressBackground));
        r7.addView(this.lineProgressView, LayoutHelper.createFrame(-1, 4.0f, 51, 50.0f, 307.0f, 50.0f, 0.0f));
        BottomSheetCell r64 = new BottomSheetCell(r20, r23);
        this.cell = r64;
        r64.setBackground(null);
        this.cell.setText(LocaleController.getString(R.string.ImportDone));
        this.cell.setVisibility(4);
        BottomSheetCell.m10919$$Nest$fgetbackground(this.cell).setOnClickListener(new ImportingAlert$$ExternalSyntheticLambda1(this));
        BottomSheetCell.m10919$$Nest$fgetbackground(this.cell).setPivotY(AndroidUtilities.dp(48.0f));
        BottomSheetCell.m10919$$Nest$fgetbackground(this.cell).setScaleY(0.04f);
        r7.addView(this.cell, LayoutHelper.createFrame(-1, 50.0f, 51, 34.0f, 247.0f, 34.0f, 0.0f));
        int r3 = 0;
    L3:
        if (r3 >= 2) goto L10;
        this.importCountTextView[r3] = new TextView(r20);
        this.importCountTextView[r3].setTextSize(1, 16.0f);
        this.importCountTextView[r3].setTypeface(AndroidUtilities.bold());
        this.importCountTextView[r3].setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        r7.addView(this.importCountTextView[r3], LayoutHelper.createFrame(-2, -2.0f, 49, 17.0f, 340.0f, 17.0f, 0.0f));
        this.infoTextView[r3] = new TextView(r20);
        this.infoTextView[r3].setTextSize(1, 14.0f);
        this.infoTextView[r3].setTextColor(getThemedColor(Theme.key_dialogTextGray3));
        this.infoTextView[r3].setGravity(1);
        r7.addView(this.infoTextView[r3], LayoutHelper.createFrame(-2, -2.0f, 49, 30.0f, 368.0f, 30.0f, 44.0f));
        TextView[] r65 = this.infoTextView;
        if (r3 != 0) goto L7;
        r65[r3].setText(LocaleController.getString(R.string.ImportImportingInfo));
    L8:
        r3 = r3 + 1;
        goto L3
    L7:
        r65[r3].setAlpha(0.0f);
        this.infoTextView[r3].setTranslationY(AndroidUtilities.dp(10.0f));
        this.importCountTextView[r3].setAlpha(0.0f);
        this.importCountTextView[r3].setTranslationY(AndroidUtilities.dp(10.0f));
        goto L8
    L10:
        if (this.parentFragment == null) goto L13;
        r8.setText(LocaleController.getString(R.string.ImportImportingTitle));
        SendMessagesHelper.ImportingHistory r1 = this.parentFragment.getSendMessagesHelper().getImportingHistory(this.parentFragment.getDialogId());
        this.percentTextView.setText(String.format("%d%%", new Object[]{Integer.valueOf(r1.uploadProgress)}));
        this.lineProgressView.setProgress(r1.uploadProgress / 100.0f, false);
        this.importCountTextView[0].setText(LocaleController.formatString("ImportCount", R.string.ImportCount, new Object[]{AndroidUtilities.formatFileSize(r1.getUploadedCount()), AndroidUtilities.formatFileSize(r1.getTotalCount())}));
        this.infoTextView[1].setText(LocaleController.getString(R.string.ImportDoneInfo));
        this.importCountTextView[1].setText(LocaleController.getString(R.string.ImportDoneTitle));
        this.parentFragment.getNotificationCenter().addObserver(this, NotificationCenter.historyImportProgressChanged);
        return;
    L13:
        r8.setText(LocaleController.getString(R.string.ImportStickersImportingTitle));
        SendMessagesHelper.ImportingStickers r13 = SendMessagesHelper.getInstance(this.currentAccount).getImportingStickers(r21);
        this.percentTextView.setText(String.format("%d%%", new Object[]{Integer.valueOf(r13.uploadProgress)}));
        this.lineProgressView.setProgress(r13.uploadProgress / 100.0f, false);
        this.importCountTextView[0].setText(LocaleController.formatString("ImportCount", R.string.ImportCount, new Object[]{AndroidUtilities.formatFileSize(r13.getUploadedCount()), AndroidUtilities.formatFileSize(r13.getTotalCount())}));
        this.infoTextView[1].setText(LocaleController.getString(R.string.ImportStickersDoneInfo));
        this.importCountTextView[1].setText(LocaleController.getString(R.string.ImportStickersDoneTitle));
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.stickersImportProgressChanged);
    }

    private /* synthetic */ void lambda$new$0() {
        if (this.completed == false) goto L6;
        this.imageView.getAnimatedDrawable().setAutoRepeat(0);
        this.imageView.setAnimation(this.completedDrawable);
        this.imageView.playAnimation();
        return;
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        dismiss();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r10, int r11, Object... r12) {
        if (r10 != NotificationCenter.historyImportProgressChanged) goto L20;
        if (r12.length <= 1) goto L8;
        dismiss();
        return;
    L8:
        SendMessagesHelper.ImportingHistory r102 = this.parentFragment.getSendMessagesHelper().getImportingHistory(this.parentFragment.getDialogId());
        if (r102 != null) goto L13;
        setCompleted();
        return;
    L13:
        if (this.completed == false) goto L15;
    L17:
        this.percentTextView.setText(String.format("%d%%", new Object[]{Integer.valueOf(r102.uploadProgress)}));
        this.importCountTextView[0].setText(LocaleController.formatString("ImportCount", R.string.ImportCount, new Object[]{AndroidUtilities.formatFileSize(r102.getUploadedCount()), AndroidUtilities.formatFileSize(r102.getTotalCount())}));
        this.lineProgressView.setProgress(r102.uploadProgress / 100.0f, true);
        return;
    L15:
        if ((((180 - this.imageView.getAnimatedDrawable().getCurrentFrame()) * 16.6d) + 3000.0d) < r102.timeUntilFinish) goto L17;
        this.imageView.setAutoRepeat(false);
        this.completed = true;
        goto L17
    L20:
        if (r10 == NotificationCenter.stickersImportProgressChanged) goto L22;
        return;
    L22:
        if (r12.length <= 1) goto L25;
        dismiss();
        return;
    L25:
        SendMessagesHelper.ImportingStickers r103 = SendMessagesHelper.getInstance(this.currentAccount).getImportingStickers(this.stickersShortName);
        if (r103 != null) goto L30;
        setCompleted();
        return;
    L30:
        if (this.completed == false) goto L32;
    L34:
        this.percentTextView.setText(String.format("%d%%", new Object[]{Integer.valueOf(r103.uploadProgress)}));
        this.importCountTextView[0].setText(LocaleController.formatString("ImportCount", R.string.ImportCount, new Object[]{AndroidUtilities.formatFileSize(r103.getUploadedCount()), AndroidUtilities.formatFileSize(r103.getTotalCount())}));
        this.lineProgressView.setProgress(r103.uploadProgress / 100.0f, true);
        return;
    L32:
        if ((((180 - this.imageView.getAnimatedDrawable().getCurrentFrame()) * 16.6d) + 3000.0d) < r103.timeUntilFinish) goto L34;
        this.imageView.setAutoRepeat(false);
        this.completed = true;
        goto L34
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        ChatActivity r0 = this.parentFragment;
        if (r0 == null) goto L6;
        r0.getNotificationCenter().removeObserver(this, NotificationCenter.historyImportProgressChanged);
        return;
    L6:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.stickersImportProgressChanged);
    }

    public void setCompleted() {
        this.completed = true;
        this.imageView.setAutoRepeat(false);
        this.cell.setVisibility(0);
        AnimatorSet r2 = new AnimatorSet();
        r2.setDuration(250);
        r2.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        TextView r6 = this.percentTextView;
        Property r7 = View.ALPHA;
        ObjectAnimator r62 = ObjectAnimator.ofFloat(r6, r7, new float[]{0.0f});
        TextView r8 = this.percentTextView;
        Property r10 = View.TRANSLATION_Y;
        r2.playTogether(new Animator[]{r62, ObjectAnimator.ofFloat(r8, r10, new float[]{-AndroidUtilities.dp(10.0f)}), ObjectAnimator.ofFloat(this.infoTextView[0], r7, new float[]{0.0f}), ObjectAnimator.ofFloat(this.infoTextView[0], r10, new float[]{-AndroidUtilities.dp(10.0f)}), ObjectAnimator.ofFloat(this.importCountTextView[0], r7, new float[]{0.0f}), ObjectAnimator.ofFloat(this.importCountTextView[0], r10, new float[]{-AndroidUtilities.dp(10.0f)}), ObjectAnimator.ofFloat(this.infoTextView[1], r7, new float[]{1.0f}), ObjectAnimator.ofFloat(this.infoTextView[1], r10, new float[]{0.0f}), ObjectAnimator.ofFloat(this.importCountTextView[1], r7, new float[]{1.0f}), ObjectAnimator.ofFloat(this.importCountTextView[1], r10, new float[]{0.0f}), ObjectAnimator.ofFloat(this.lineProgressView, r7, new float[]{0.0f}), ObjectAnimator.ofFloat(BottomSheetCell.m10921$$Nest$fgetlinearLayout(this.cell), r10, new float[]{AndroidUtilities.dp(8.0f), 0.0f})});
        BottomSheetCell.m10919$$Nest$fgetbackground(this.cell).animate().scaleY(1.0f).setInterpolator(new OvershootInterpolator(1.02f)).setDuration(250).start();
        BottomSheetCell.m10920$$Nest$fgetimageView(this.cell).animate().scaleY(1.0f).scaleX(1.0f).setInterpolator(new OvershootInterpolator(1.02f)).setDuration(250).start();
        BottomSheetCell.m10920$$Nest$fgetimageView(this.cell).playAnimation();
        r2.start();
    }
}
