package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class SwipeGestureSettingsView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    int[] backgroundKeys;
    float colorProgress;
    private final int currentAccount;
    int currentColorKey;
    int currentIconIndex;
    int currentIconValue;
    Paint filledPaint;
    int fromColor;
    RLottieImageView[] iconViews;
    RLottieDrawable[] icons;
    Paint linePaint;
    Paint outlinePaint;
    private NumberPicker picker;
    Paint pickerDividersPaint;
    float progressToSwipeFolders;
    RectF rect;
    String[] strings;
    Runnable swapIconRunnable;

    /* renamed from: org.telegram.ui.Components.SwipeGestureSettingsView$1, reason: invalid class name */
    public class AnonymousClass1 extends NumberPicker {
        final /* synthetic */ SwipeGestureSettingsView this$0;

        public AnonymousClass1(SwipeGestureSettingsView r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.NumberPicker, android.widget.LinearLayout, android.view.View
        public void onDraw(Canvas r15) {
            super.onDraw(r15);
            float r4 = AndroidUtilities.dp(31.0f);
            this.this$0.pickerDividersPaint.setColor(Theme.getColor(Theme.key_radioBackgroundChecked));
            r15.drawLine(AndroidUtilities.dp(2.0f), r4, getMeasuredWidth() - AndroidUtilities.dp(2.0f), r4, this.this$0.pickerDividersPaint);
            float r10 = getMeasuredHeight() - AndroidUtilities.dp(31.0f);
            r15.drawLine(AndroidUtilities.dp(2.0f), r10, getMeasuredWidth() - AndroidUtilities.dp(2.0f), r10, this.this$0.pickerDividersPaint);
        }
    }

    public static /* synthetic */ void $r8$lambda$0hUX7nY40mVAwwp2tf4tNwb3uSQ(SwipeGestureSettingsView r0, NumberPicker r1, int r2, int r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ String $r8$lambda$2gXlfLhO1ET6asucbmiPmkPSnWw(SwipeGestureSettingsView r0, int r1) {
        return r0.lambda$new$0(r1);
    }

    public static /* synthetic */ String $r8$lambda$37Yp2U_ah1qg87r2VAwuA6RKOH4(SwipeGestureSettingsView r0, int r1) {
        return r0.lambda$updateFoldersState$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$D6VH_oItHSTFBuoRw9AcM7jgZW4(SwipeGestureSettingsView r0) {
        r0.lambda$swapIcons$3();
    }

    public SwipeGestureSettingsView(Context r18, int r19) {
        super(r18);
        this.outlinePaint = new Paint(1);
        this.filledPaint = new Paint(1);
        this.linePaint = new Paint(1);
        this.pickerDividersPaint = new Paint(1);
        this.rect = new RectF();
        String[] r4 = new String[6];
        this.strings = r4;
        this.backgroundKeys = new int[6];
        this.icons = new RLottieDrawable[6];
        this.iconViews = new RLottieImageView[2];
        float r5 = 1.0f;
        this.colorProgress = 1.0f;
        this.currentAccount = r19;
        r4[0] = LocaleController.getString(R.string.SwipeSettingsPin);
        this.strings[1] = LocaleController.getString(R.string.SwipeSettingsRead);
        this.strings[2] = LocaleController.getString(R.string.SwipeSettingsArchive);
        this.strings[3] = LocaleController.getString(R.string.SwipeSettingsMute);
        this.strings[4] = LocaleController.getString(R.string.SwipeSettingsDelete);
        updateFoldersState();
        int[] r42 = this.backgroundKeys;
        int r7 = Theme.key_chats_archiveBackground;
        r42[0] = r7;
        r42[1] = r7;
        r42[2] = r7;
        r42[3] = r7;
        r42[4] = Theme.key_dialogSwipeRemove;
        r42[5] = Theme.key_chats_archivePinBackground;
        Paint r43 = this.outlinePaint;
        Paint.Style r72 = Paint.Style.STROKE;
        r43.setStyle(r72);
        this.outlinePaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        this.linePaint.setStyle(r72);
        Paint r44 = this.linePaint;
        Paint.Cap r10 = Paint.Cap.ROUND;
        r44.setStrokeCap(r10);
        this.linePaint.setStrokeWidth(AndroidUtilities.dp(5.0f));
        this.pickerDividersPaint.setStyle(r72);
        this.pickerDividersPaint.setStrokeCap(r10);
        this.pickerDividersPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        AnonymousClass1 r45 = new AnonymousClass1(this, r18, 13);
        this.picker = r45;
        r45.setMinValue(0);
        this.picker.setDrawDividers(false);
        this.picker.setMaxValue(this.strings.length - 1);
        this.picker.setAllItemsCount(this.strings.length);
        this.picker.setWrapSelectorWheel(true);
        this.picker.setFormatter(new SwipeGestureSettingsView$$ExternalSyntheticLambda0(this));
        this.picker.setOnValueChangedListener(new SwipeGestureSettingsView$$ExternalSyntheticLambda1(this));
        this.picker.setImportantForAccessibility(2);
        this.picker.setValue(SharedConfig.getChatSwipeAction(r19));
        addView(this.picker, LayoutHelper.createFrame(132, -1.0f, 5, 21.0f, 0.0f, 21.0f, 0.0f));
        setWillNotDraw(false);
        this.currentIconIndex = 0;
        int r46 = 0;
    L3:
        if (r46 >= 2) goto L5;
        this.iconViews[r46] = new RLottieImageView(r18);
        addView(this.iconViews[r46], LayoutHelper.createFrame(28, 28.0f, 21, 0.0f, 0.0f, 184.0f, 0.0f));
        r46 = r46 + 1;
        goto L3
    L5:
        RLottieDrawable r1 = getIcon(this.picker.getValue());
        if (r1 == null) goto L8;
        this.iconViews[0].setImageDrawable(r1);
        r1.setCurrentFrame(r1.getFramesCount() - 1);
    L8:
        AndroidUtilities.updateViewVisibilityAnimated(this.iconViews[0], true, 0.5f, false);
        AndroidUtilities.updateViewVisibilityAnimated(this.iconViews[1], false, 0.5f, false);
        if (this.picker.getValue() == 5) goto L12;
        r5 = 0.0f;
    L12:
        this.progressToSwipeFolders = r5;
        this.currentIconValue = this.picker.getValue();
    }

    private /* synthetic */ String lambda$new$0(int r1) {
        return this.strings[r1];
    }

    private /* synthetic */ void lambda$new$1(NumberPicker r1, int r2, int r3) {
        swapIcons();
        SharedConfig.updateChatListSwipeSetting(r3);
        invalidate();
        r1.performHapticFeedback(3, 2);     // Catch: Exception -> L5
        return;
    }

    private /* synthetic */ void lambda$swapIcons$3() {
        this.swapIconRunnable = null;
        swapIcons();
    }

    private /* synthetic */ String lambda$updateFoldersState$2(int r1) {
        return this.strings[r1];
    }

    private void swapIcons() {
        if (this.swapIconRunnable != null) goto L17;
        int r0 = this.picker.getValue();
        if (this.currentIconValue == r0) goto L16;
        this.currentIconValue = r0;
        int r1 = (this.currentIconIndex + 1) % 2;
        RLottieDrawable r02 = getIcon(r0);
        RLottieImageView[] r3 = this.iconViews;
        if (r02 != null) goto L10;
        r3[r1].clearAnimationDrawable();
    L14:
        AndroidUtilities.updateViewVisibilityAnimated(this.iconViews[this.currentIconIndex], false, 0.5f, true);
        AndroidUtilities.updateViewVisibilityAnimated(this.iconViews[r1], true, 0.5f, true);
        this.currentIconIndex = r1;
        SwipeGestureSettingsView$$ExternalSyntheticLambda3 r03 = new SwipeGestureSettingsView$$ExternalSyntheticLambda3(this);
        this.swapIconRunnable = r03;
        AndroidUtilities.runOnUIThread(r03, 150);
        return;
    L10:
        if (r3[r1].getVisibility() == 0) goto L12;
        r02.setCurrentFrame(0, false);
    L12:
        this.iconViews[r1].setAnimation(r02);
        this.iconViews[r1].playAnimation();
        goto L14
    L16:
        return;
    }

    private void updateFoldersState() {
        boolean r1 = true;
        if (MessagesController.getInstance(this.currentAccount).getDialogFilters().size() > 1) goto L6;
        r1 = false;
    L6:
        String[] r0 = this.strings;
        if (r1 == false) goto L9;
        int r12 = R.string.SwipeSettingsFolders;
    L10:
        r0[5] = LocaleController.getString(r12);
        NumberPicker r02 = this.picker;
        if (r02 == null) goto L17;
        int r03 = r02.getValue();
        this.picker.setFormatter(null);
        this.picker.setFormatter(new SwipeGestureSettingsView$$ExternalSyntheticLambda2(this));
        this.picker.setValue(SharedConfig.getChatSwipeAction(this.currentAccount));
        if (r03 == this.picker.getValue()) goto L15;
        swapIcons();
    L15:
        this.picker.invalidate();
        return;
    L17:
        return;
    L9:
        r12 = R.string.Disable;
        goto L10
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.dialogFiltersUpdated) goto L5;
        return;
    L5:
        if (r2 != this.currentAccount) goto L9;
        updateFoldersState();
        return;
    }

    public RLottieDrawable getIcon(int r9) {
        RLottieDrawable[] r0 = this.icons;
        if (r0[r9] != null) goto L23;
        if (r9 != 1) goto L7;
        int r1 = R.raw.swipe_read;
    L15:
        int r3 = r1;
        r0[r9] = new RLottieDrawable(r3, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), true, null);
        updateIconColor(r9);
        goto L23
    L7:
        if (r9 != 2) goto L9;
        r1 = R.raw.chats_archive;
        goto L15
    L9:
        if (r9 != 3) goto L11;
        r1 = R.raw.swipe_mute;
        goto L15
    L11:
        if (r9 != 4) goto L13;
        r1 = R.raw.swipe_delete;
        goto L15
    L13:
        if (r9 == 5) goto L16;
        r1 = R.raw.swipe_pin;
        goto L15
    L16:
        r1 = R.raw.swipe_disabled;
    L23:
        return this.icons[r9];
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.dialogFiltersUpdated);
        updateFoldersState();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.dialogFiltersUpdated);
    }

    @Override // android.view.View
    public void onDraw(Canvas r15) {
        super.onDraw(r15);
        if (this.picker.getValue() != 5) goto L5;
        boolean r1 = true;
    L7:
        if (r1 == false) goto L14;
        float r7 = this.progressToSwipeFolders;
        if (r7 == 1.0f) goto L14;
        float r72 = r7 + 0.053333335f;
        this.progressToSwipeFolders = r72;
        if (r72 <= 1.0f) goto L13;
        this.progressToSwipeFolders = 1.0f;
    L21:
        Paint r12 = this.outlinePaint;
        int r2 = Theme.key_switchTrack;
        r12.setColor(Theme.getColor(r2));
        this.linePaint.setColor(Theme.getColor(r2));
        int r13 = getMeasuredWidth() - ((AndroidUtilities.dp(132.0f) + AndroidUtilities.dp(21.0f)) + AndroidUtilities.dp(16.0f));
        int r3 = AndroidUtilities.dp(21.0f);
        float r32 = r3;
        float r8 = (getMeasuredHeight() - AndroidUtilities.dp(48.0f)) / 2;
        this.rect.set(r32, r8, r13, getMeasuredHeight() - r4);
        int r73 = this.currentColorKey;
        int[] r9 = this.backgroundKeys;
        if (r73 >= 0) goto L25;
        this.currentColorKey = r9[this.picker.getValue()];
        this.colorProgress = 1.0f;
        this.fromColor = ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundWhite), Theme.getColor(this.currentColorKey), 0.9f);
    L27:
        float r74 = this.colorProgress;
        if (r74 == 1.0f) goto L33;
        float r75 = r74 + 0.16f;
        this.colorProgress = r75;
        if (r75 <= 1.0f) goto L32;
        this.colorProgress = 1.0f;
        goto L33
    L32:
        invalidate();
    L33:
        int r76 = this.fromColor;
        int r92 = Theme.key_windowBackgroundWhite;
        this.filledPaint.setColor(ColorUtils.blendARGB(r76, ColorUtils.blendARGB(Theme.getColor(r92), Theme.getColor(this.currentColorKey), 0.9f), this.colorProgress));
        r15.drawRoundRect(this.rect, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.filledPaint);
        this.filledPaint.setColor(Theme.getColor(r92));
        this.filledPaint.setAlpha(255);
        this.rect.set(r32, r8, r13 - AndroidUtilities.dp(58.0f), getMeasuredHeight() - r4);
        this.rect.inset(-AndroidUtilities.dp(1.0f), -AndroidUtilities.dp(1.0f));
        r15.drawRoundRect(this.rect, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.filledPaint);
        this.outlinePaint.setAlpha(31);
        r15.drawRoundRect(this.rect, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.outlinePaint);
        r15.save();
        r15.clipRect(this.rect);
        this.filledPaint.setColor(Theme.getColor(r2));
        this.filledPaint.setAlpha(60);
        RectF r14 = this.rect;
        r15.drawCircle(r14.left + 0.0f, r14.centerY(), AndroidUtilities.dp(15.0f), this.filledPaint);
        float r22 = this.rect.centerY() - AndroidUtilities.dp(6.0f);
        this.linePaint.setAlpha(57);
        r15.drawLine((this.rect.left + AndroidUtilities.dp(23.0f)) + 0.0f, r22, this.rect.right - AndroidUtilities.dp(68.0f), r22, this.linePaint);
        float r23 = this.rect.centerY() + AndroidUtilities.dp(6.0f);
        r15.drawLine((this.rect.left + AndroidUtilities.dp(23.0f)) + 0.0f, r23, this.rect.right - AndroidUtilities.dp(23.0f), r23, this.linePaint);
        r15.restore();
        return;
    L25:
        if (r9[this.picker.getValue()] == this.currentColorKey) goto L27;
        this.fromColor = ColorUtils.blendARGB(this.fromColor, ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundWhite), Theme.getColor(this.currentColorKey), 0.9f), this.colorProgress);
        this.colorProgress = 0.0f;
        this.currentColorKey = this.backgroundKeys[this.picker.getValue()];
        goto L27
    L13:
        this.iconViews[0].invalidate();
        this.iconViews[1].invalidate();
        invalidate();
    L14:
        if (r1 == true) goto L21;
        float r16 = this.progressToSwipeFolders;
        if (r16 == 0.0f) goto L21;
        float r17 = r16 - 0.053333335f;
        this.progressToSwipeFolders = r17;
        if (r17 >= 0.0f) goto L20;
        this.progressToSwipeFolders = 0.0f;
        goto L21
    L20:
        this.iconViews[0].invalidate();
        this.iconViews[1].invalidate();
        invalidate();
        goto L21
    L5:
        r1 = false;
        goto L7
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent r3) {
        super.onInitializeAccessibilityEvent(r3);
        if (r3.getEventType() != 1) goto L10;
        int r32 = this.picker.getValue() + 1;
        if (r32 > this.picker.getMaxValue()) goto L7;
        if (r32 < 0) goto L7;
    L8:
        setContentDescription(this.strings[r32]);
        this.picker.changeValueByOne(true);
        return;
    L7:
        r32 = 0;
        goto L8
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        r3.setEnabled(true);
        r3.setContentDescription(this.strings[this.picker.getValue()]);
        r3.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, null));
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(102.0f), TLObject.FLAG_30));
    }

    @Override // android.view.View
    public void setBackgroundColor(int r2) {
        super.setBackgroundColor(r2);
        updateColors();
        this.picker.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        this.picker.invalidate();
    }

    public void updateColors() {
        int r0 = 0;
    L4:
        if (r0 >= this.icons.length) goto L6;
        updateIconColor(r0);
        r0 = r0 + 1;
        goto L4
    }

    public void updateIconColor(int r5) {
        if (this.icons[r5] == null) goto L10;
        int r0 = ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundWhite), Theme.getColor(Theme.key_chats_archiveBackground), 0.9f);
        int r1 = Theme.getColor(Theme.key_chats_archiveIcon);
        RLottieDrawable[] r2 = this.icons;
        if (r5 != 2) goto L8;
        r2[r5].setLayerColor("Arrow", r0);
        this.icons[r5].setLayerColor("Box2", r1);
        this.icons[r5].setLayerColor("Box1", r1);
        return;
    L8:
        r2[r5].setColorFilter(new PorterDuffColorFilter(r1, PorterDuff.Mode.SRC_IN));
        return;
    }
}
