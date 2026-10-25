package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocationController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ShareLocationDrawable;

/* loaded from: classes6.dex */
public class SendLocationCell extends FrameLayout {
    private SimpleTextView accurateTextView;
    private int currentAccount;
    private long dialogId;
    private ImageView imageView;
    private Runnable invalidateRunnable;
    private boolean live;
    private boolean liveDisable;
    private final AnimatedFloat progress;
    private final AnimatedFloat progressAlpha;
    private final AnimatedFloat progressScale;
    private RectF rect;
    private final Theme.ResourcesProvider resourcesProvider;
    private final AnimatedTextView.AnimatedTextDrawable textDrawable;
    private SimpleTextView titleTextView;
    public boolean useDivider;

    /* renamed from: org.telegram.ui.Cells.SendLocationCell$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ SendLocationCell this$0;

        public AnonymousClass1(SendLocationCell r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            SendLocationCell.m7006$$Nest$mcheckText(this.this$0);
            this.this$0.invalidate(((int) SendLocationCell.m7005$$Nest$fgetrect(r0).left) - 5, ((int) SendLocationCell.m7005$$Nest$fgetrect(this.this$0).top) - 5, ((int) SendLocationCell.m7005$$Nest$fgetrect(this.this$0).right) + 5, ((int) SendLocationCell.m7005$$Nest$fgetrect(this.this$0).bottom) + 5);
            AndroidUtilities.runOnUIThread(SendLocationCell.m7004$$Nest$fgetinvalidateRunnable(this.this$0), 1000);
        }
    }

    /* renamed from: -$$Nest$fgetinvalidateRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m7004$$Nest$fgetinvalidateRunnable(SendLocationCell r0) {
        return r0.invalidateRunnable;
    }

    /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m7005$$Nest$fgetrect(SendLocationCell r0) {
        return r0.rect;
    }

    /* renamed from: -$$Nest$mcheckText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7006$$Nest$mcheckText(SendLocationCell r0) {
        r0.checkText();
    }

    public SendLocationCell(Context r19, boolean r20, boolean r21, Theme.ResourcesProvider r22) {
        super(r19);
        this.currentAccount = UserConfig.selectedAccount;
        this.invalidateRunnable = new AnonymousClass1(this);
        CubicBezierInterpolator r9 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.progress = new AnimatedFloat(this, 350, r9);
        this.progressAlpha = new AnimatedFloat(this, 350, r9);
        this.progressScale = new AnimatedFloat(this, 350, r9);
        AnimatedTextView.AnimatedTextDrawable r3 = new AnimatedTextView.AnimatedTextDrawable(false, true, false);
        this.textDrawable = r3;
        r3.setAnimationProperties(0.3f, 0, 320, r9);
        r3.setTextSize(AndroidUtilities.dp(12.0f));
        r3.setTypeface(Typeface.DEFAULT_BOLD);
        r3.setGravity(17);
        r3.setCallback(this);
        this.resourcesProvider = r22;
        this.live = r20;
        this.liveDisable = r21;
        ImageView r2 = new ImageView(r19);
        this.imageView = r2;
        boolean r32 = LocaleController.isRTL;
        int r4 = 3;
        if (r32 == false) goto L5;
        int r6 = 5;
    L6:
        int r13 = r6 | 16;
        if (r32 == false) goto L9;
        float r14 = 0.0f;
    L10:
        if (r32 == false) goto L12;
        float r16 = 13.0f;
    L13:
        addView(r2, LayoutHelper.createFrame(46, 46.0f, r13, r14, 0.0f, r16, 0.0f));
        SimpleTextView r23 = new SimpleTextView(r19);
        this.titleTextView = r23;
        r23.setTextSize(16);
        SimpleTextView r24 = this.titleTextView;
        if (LocaleController.isRTL == false) goto L16;
        int r33 = 5;
    L17:
        r24.setGravity(r33);
        this.titleTextView.setTypeface(AndroidUtilities.bold());
        View r25 = this.titleTextView;
        boolean r34 = LocaleController.isRTL;
        if (r34 == false) goto L20;
        int r62 = 5;
    L21:
        int r132 = r62 | 48;
        if (r34 == false) goto L24;
        float r142 = 16.0f;
    L25:
        if (r34 == false) goto L27;
        float r162 = 73.0f;
    L28:
        addView(r25, LayoutHelper.createFrame(-1, 20.0f, r132, r142, 9.33f, r162, 0.0f));
        SimpleTextView r26 = new SimpleTextView(r19);
        this.accurateTextView = r26;
        r26.setTextSize(14);
        this.accurateTextView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText3));
        SimpleTextView r1 = this.accurateTextView;
        if (LocaleController.isRTL == false) goto L31;
        int r27 = 5;
    L32:
        r1.setGravity(r27);
        View r12 = this.accurateTextView;
        boolean r28 = LocaleController.isRTL;
        if (r28 == false) goto L35;
        r4 = 5;
    L35:
        int r133 = r4 | 48;
        if (r28 == false) goto L38;
        float r143 = 16.0f;
    L39:
        if (r28 == false) goto L41;
        float r163 = 73.0f;
    L42:
        addView(r12, LayoutHelper.createFrame(-1, 20.0f, r133, r143, 33.0f, r163, 0.0f));
        updateImage();
        setWillNotDraw(false);
        return;
    L41:
        r163 = 16.0f;
        goto L42
    L38:
        r143 = 73.0f;
        goto L39
    L31:
        r27 = 3;
        goto L32
    L27:
        r162 = 16.0f;
        goto L28
    L24:
        r142 = 73.0f;
        goto L25
    L20:
        r62 = 3;
        goto L21
    L16:
        r33 = 3;
        goto L17
    L12:
        r16 = 0.0f;
        goto L13
    L9:
        r14 = 13.0f;
        goto L10
    L5:
        r6 = 3;
        goto L6
    }

    private void checkText() {
        LocationController.SharingLocationInfo r0 = LocationController.getInstance(this.currentAccount).getSharingLocationInfo(this.dialogId);
        if (r0 != null) goto L5;
        setText(LocaleController.getString(R.string.SendLiveLocation), LocaleController.getString(R.string.SendLiveLocationInfo));
        return;
    L5:
        if (this.liveDisable == false) goto L12;
        String r1 = LocaleController.getString(R.string.StopLiveLocation);
        TLRPC.Message r02 = r0.messageObject.messageOwner;
        int r2 = r02.edit_date;
        if (r2 == 0) goto L9;
        long r22 = r2;
    L10:
        setText(r1, LocaleController.formatLocationUpdateDate(r22));
        return;
    L9:
        r22 = r02.date;
        goto L10
    L12:
        setText(LocaleController.getString(R.string.SharingLiveLocation), LocaleController.getString(R.string.SharingLiveLocationAdd));
    }

    private ImageView getImageView() {
        return this.imageView;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private void updateImage() {
        SimpleTextView r0 = this.titleTextView;
        if (this.live == true) goto L5;
        int r1 = Theme.key_location_sendLocationText;
    L9:
        r0.setTag(Integer.valueOf(r1));
        SimpleTextView r02 = this.titleTextView;
        if (this.live == true) goto L12;
        int r12 = Theme.key_location_sendLocationText;
    L16:
        r02.setTextColor(getThemedColor(r12));
        ImageView r03 = this.imageView;
        if (this.live == true) goto L19;
        int r13 = Theme.key_location_sendLocationBackground;
        int r2 = Theme.key_location_sendLocationIcon;
    L22:
        int r14 = r13 + r2;
    L24:
        r03.setTag(Integer.valueOf(r14));
        int r15 = AndroidUtilities.dp(46.0f);
        if (this.live == true) goto L27;
        int r22 = Theme.key_location_sendLocationBackground;
    L31:
        int r23 = getThemedColor(r22);
        if (this.live == true) goto L34;
        int r3 = Theme.key_location_sendLocationBackground;
    L38:
        Drawable r16 = Theme.createSimpleSelectorCircleDrawable(r15, r23, getThemedColor(r3));
        if (this.live == false) goto L48;
        this.rect = new RectF();
        Context r32 = getContext();
        if (this.liveDisable == false) goto L43;
        int r4 = 5;
    L44:
        ShareLocationDrawable r24 = new ShareLocationDrawable(r32, r4);
        r24.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_location_sendLiveLocationIcon), PorterDuff.Mode.MULTIPLY));
        CombinedDrawable r33 = new CombinedDrawable(r16, r24);
        r33.setCustomSize(AndroidUtilities.dp(46.0f), AndroidUtilities.dp(46.0f));
        this.imageView.setBackgroundDrawable(r33);
        if (this.liveDisable == true) goto L50;
        AndroidUtilities.cancelRunOnUIThread(this.invalidateRunnable);
        AndroidUtilities.runOnUIThread(this.invalidateRunnable, 1000);
        return;
    L50:
        return;
    L43:
        r4 = 4;
        goto L44
    L48:
        Drawable r25 = getResources().getDrawable(R.drawable.pin).mutate();
        r25.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_location_sendLocationIcon), PorterDuff.Mode.MULTIPLY));
        CombinedDrawable r34 = new CombinedDrawable(r16, r25);
        r34.setCustomSize(AndroidUtilities.dp(46.0f), AndroidUtilities.dp(46.0f));
        r34.setIconSize(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        this.imageView.setBackgroundDrawable(r34);
        return;
    L34:
        if (this.liveDisable == false) goto L36;
        r3 = Theme.key_color_red;
        goto L38
    L36:
        r3 = Theme.key_location_sendLiveLocationBackground;
        goto L38
    L27:
        if (this.liveDisable == false) goto L29;
        r22 = Theme.key_color_red;
        goto L31
    L29:
        r22 = Theme.key_location_sendLiveLocationBackground;
        goto L31
    L19:
        if (this.liveDisable == false) goto L21;
        r14 = Theme.key_color_red;
        goto L24
    L21:
        r13 = Theme.key_location_sendLiveLocationBackground;
        r2 = Theme.key_location_sendLiveLocationIcon;
        goto L22
    L12:
        if (this.liveDisable == false) goto L14;
        r12 = Theme.key_text_RedBold;
        goto L16
    L14:
        r12 = Theme.key_location_sendLiveLocationText;
        goto L16
    L5:
        if (this.liveDisable == false) goto L7;
        r1 = Theme.key_text_RedBold;
        goto L9
    L7:
        r1 = Theme.key_location_sendLiveLocationText;
        goto L9
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.rect == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(this.invalidateRunnable);
        AndroidUtilities.runOnUIThread(this.invalidateRunnable, 1000);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AndroidUtilities.cancelRunOnUIThread(this.invalidateRunnable);
    }

    @Override // android.view.View
    public void onDraw(Canvas r17) {
        if (this.useDivider == false) goto L15;
        Paint r6 = Theme.getThemePaint("paintDivider", this.resourcesProvider);
        if (r6 == null) goto L15;
        if (LocaleController.isRTL == false) goto L9;
        float r1 = 0.0f;
    L10:
        float r3 = getMeasuredHeight() - 1;
        int r4 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L13;
        int r2 = AndroidUtilities.dp(73.0f);
    L14:
        float r22 = r1;
        Canvas r12 = r17;
        r12.drawRect(r22, r3, r4 - r2, getMeasuredHeight(), r6);
    L17:
        if (this.liveDisable == true) goto L46;
        LocationController.SharingLocationInfo r10 = LocationController.getInstance(this.currentAccount).getSharingLocationInfo(this.dialogId);
        float r23 = this.progress.get();
        int r11 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        if (r10 != null) goto L22;
    L27:
        float r32 = this.progressAlpha.set(false);
    L26:
        float r7 = r23;
        float r8 = r32;
        if (r8 > 0.0f) goto L31;
        return;
    L31:
        boolean r24 = LocaleController.isRTL;
        RectF r33 = this.rect;
        if (r24 == false) goto L34;
        r33.set(AndroidUtilities.dp(13.0f), (getMeasuredHeight() / 2.0f) - AndroidUtilities.dp(15.0f), AndroidUtilities.dp(43.0f), (getMeasuredHeight() / 2.0f) + AndroidUtilities.dp(15.0f));
    L35:
        r12.save();
        float r122 = 1.0f;
        float r25 = AndroidUtilities.lerp(0.6f, 1.0f, r8);
        r12.scale(r25, r25, this.rect.centerX(), this.rect.centerY());
        int r13 = getThemedColor(Theme.key_location_liveLocationProgress);
        Theme.chat_radialProgress2Paint.setColor(r13);
        int r14 = Theme.chat_radialProgress2Paint.getAlpha();
        float r15 = r14;
        Theme.chat_radialProgress2Paint.setAlpha((int) ((0.2f * r15) * r8));
        r12.drawArc(this.rect, -90.0f, 360.0f, false, Theme.chat_radialProgress2Paint);
        Theme.chat_radialProgress2Paint.setAlpha((int) (r15 * r8));
        r17.drawArc(this.rect, -90.0f, this.progress.set(r7) * (-360.0f), false, Theme.chat_radialProgress2Paint);
        Theme.chat_radialProgress2Paint.setAlpha(r14);
        if (r10 == null) goto L38;
        this.textDrawable.setText(LocaleController.formatLocationLeftTime(Math.abs(r10.stopTime - r11)));
    L38:
        int r26 = this.textDrawable.getText().length();
        AnimatedFloat r34 = this.progressScale;
        if (r26 <= 4) goto L42;
        r122 = 0.75f;
    L44:
        float r27 = r34.set(r122);
        r17.scale(r27, r27, this.rect.centerX(), this.rect.centerY());
        this.textDrawable.setTextColor(r13);
        this.textDrawable.setAlpha((int) (r8 * 255.0f));
        AnimatedTextView.AnimatedTextDrawable r28 = this.textDrawable;
        RectF r35 = this.rect;
        int r42 = (int) r35.left;
        int r36 = (int) (r35.centerY() - AndroidUtilities.dp(13.0f));
        RectF r5 = this.rect;
        r28.setBounds(r42, r36, (int) r5.right, (int) (r5.centerY() + AndroidUtilities.dp(12.0f)));
        this.textDrawable.draw(r17);
        r17.restore();
        return;
    L42:
        if (r26 <= 3) goto L44;
        r122 = 0.85f;
        goto L44
    L34:
        r33.set(getMeasuredWidth() - AndroidUtilities.dp(43.0f), (getMeasuredHeight() / 2.0f) - AndroidUtilities.dp(15.0f), getMeasuredWidth() - AndroidUtilities.dp(13.0f), (getMeasuredHeight() / 2.0f) + AndroidUtilities.dp(15.0f));
        goto L35
    L22:
        if (r10.stopTime < r11) goto L27;
        if (r10.period == Integer.MAX_VALUE) goto L27;
        r23 = Math.abs(r3 - r11) / r10.period;
        r32 = this.progressAlpha.set(true);
        goto L26
    L46:
        return;
    L13:
        r2 = 0;
        goto L14
    L9:
        r1 = AndroidUtilities.dp(73.0f);
    L15:
        r12 = r17;
        goto L17
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60.0f), TLObject.FLAG_30));
    }

    public void setDialogId(long r1) {
        this.dialogId = r1;
        if (this.live == false) goto L6;
        checkText();
        return;
    }

    public void setHasLocation(boolean r5) {
        if (LocationController.getInstance(this.currentAccount).getSharingLocationInfo(this.dialogId) != null) goto L17;
        SimpleTextView r0 = this.titleTextView;
        float r1 = 0.5f;
        if (r5 == false) goto L7;
        float r3 = 1.0f;
    L8:
        r0.setAlpha(r3);
        SimpleTextView r02 = this.accurateTextView;
        if (r5 == false) goto L11;
        float r32 = 1.0f;
    L12:
        r02.setAlpha(r32);
        ImageView r03 = this.imageView;
        if (r5 == false) goto L15;
        r1 = 1.0f;
    L15:
        r03.setAlpha(r1);
        goto L17
    L11:
        r32 = 0.5f;
        goto L12
    L7:
        r3 = 0.5f;
    L17:
        if (this.live == false) goto L20;
        checkText();
        return;
    }

    public void setText(String r2, String r3) {
        this.titleTextView.setText(r2);
        this.accurateTextView.setText(r3);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.textDrawable) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
