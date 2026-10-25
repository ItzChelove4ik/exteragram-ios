package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.DotDividerSpan;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgress2;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.FilteredSearchView;

/* loaded from: classes6.dex */
public class SharedAudioCell extends FrameLayout implements DownloadController.FileDownloadProgressListener, NotificationCenter.NotificationCenterDelegate {
    private int TAG;
    private boolean buttonPressed;
    private int buttonState;
    private int buttonX;
    private int buttonY;
    private StaticLayout captionLayout;
    AnimatedEmojiSpan.EmojiGroupedSpans captionLayoutEmojis;
    private float captionLayoutLeft;
    private float captionLayoutWidth;
    private TextPaint captionTextPaint;
    private int captionY;
    private CheckBox2 checkBox;
    private boolean checkForButtonPress;
    private int currentAccount;
    private MessageObject currentMessageObject;
    private StaticLayout dateLayout;
    private int dateLayoutX;
    private TextPaint description2TextPaint;
    private StaticLayout descriptionLayout;
    AnimatedEmojiSpan.EmojiGroupedSpans descriptionLayoutEmojis;
    private float descriptionLayoutLeft;
    private float descriptionLayoutWidth;
    private int descriptionY;
    private SpannableStringBuilder dotSpan;
    float enterAlpha;
    FlickerLoadingView globalGradientView;
    private int hasMiniProgress;
    private boolean miniButtonPressed;
    private int miniButtonState;
    private boolean needDivider;
    private Utilities.CallbackReturn<MessageObject, Boolean> needPlayMessageListener;
    private RadialProgress2 radialProgress;
    private final Theme.ResourcesProvider resourcesProvider;
    boolean showName;
    float showNameProgress;
    boolean showReorderIcon;
    float showReorderIconProgress;
    private StaticLayout titleLayout;
    AnimatedEmojiSpan.EmojiGroupedSpans titleLayoutEmojis;
    private float titleLayoutLeft;
    private float titleLayoutWidth;
    TextPaint titlePaint;
    private int titleY;
    private int viewType;

    public static final class Factory extends UItem.UItemFactory<SharedAudioCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem as(MediaController.AudioEntry r1, Utilities.CallbackReturn<MessageObject, Boolean> r2) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            r0.object2 = r2;
            return r0;
        }

        public static UItem as(MessageObject r1, Utilities.CallbackReturn<MessageObject, Boolean> r2) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            r0.object2 = r2;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            SharedAudioCell r12 = (SharedAudioCell) r1;
            Object r0 = r2.object;
            if ((r0 instanceof MessageObject) == false) goto L6;
            r12.setMessageObject((MessageObject) r0, r3);
        L8:
            Object r02 = r2.object2;
            if ((r02 instanceof Utilities.CallbackReturn) == false) goto L11;
            r12.setNeedPlayMessageListener((Utilities.CallbackReturn) r02);
        L11:
            r12.setChecked(r2.checked, false);
            return;
        L6:
            if ((r0 instanceof MediaController.AudioEntry) == false) goto L8;
            MediaController.AudioEntry r03 = (MediaController.AudioEntry) r0;
            r12.setTag(r03);
            r12.setMessageObject(r03.messageObject, r3);
            goto L8
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean contentsEquals(UItem r2, UItem r3) {
            if (r2.id == r3.id) goto L5;
            return false;
        L5:
            if (r2.object != r3.object) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public SharedAudioCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            SharedAudioCell r0 = new SharedAudioCell(r1, r5);
            r0.setCheckForButtonPress(true);
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean equals(UItem r2, UItem r3) {
            if (r2.id == r3.id) goto L5;
            return false;
        L5:
            if (r2.object != r3.object) goto L10;
            return true;
        L10:
            return false;
        }
    }

    public SharedAudioCell(Context r3) {
        this(r3, 0, null);
    }

    public SharedAudioCell(Context r13, int r14, Theme.ResourcesProvider r15) {
        super(r13);
        this.titleY = AndroidUtilities.dp(9.0f);
        this.descriptionY = AndroidUtilities.dp(29.0f);
        this.captionY = AndroidUtilities.dp(29.0f);
        this.currentAccount = UserConfig.selectedAccount;
        this.showName = true;
        float r1 = 0.0f;
        this.showNameProgress = 0.0f;
        this.enterAlpha = 1.0f;
        this.resourcesProvider = r15;
        this.viewType = r14;
        setFocusable(true);
        setImportantForAccessibility(1);
        RadialProgress2 r2 = new RadialProgress2(this, r15);
        this.radialProgress = r2;
        r2.setStyle(1);
        this.radialProgress.setNeedDrawBackground(true);
        this.radialProgress.setColorKeys(Theme.key_chat_inLoader, Theme.key_chat_inLoaderSelected, Theme.key_chat_inMediaIcon, Theme.key_chat_inMediaIconSelected);
        this.TAG = DownloadController.getInstance(this.currentAccount).generateObserverTag();
        setWillNotDraw(false);
        CheckBox2 r3 = new CheckBox2(r13, 22, r15);
        this.checkBox = r3;
        r3.setVisibility(4);
        this.checkBox.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
        this.checkBox.setDrawUnchecked(false);
        int r32 = 3;
        this.checkBox.setDrawBackgroundAsArc(3);
        CheckBox2 r132 = this.checkBox;
        boolean r4 = LocaleController.isRTL;
        if (r4 == false) goto L5;
        r32 = 5;
    L5:
        int r7 = r32 | 48;
        if (r4 == false) goto L8;
        float r8 = 0.0f;
    L9:
        if (r4 == false) goto L11;
        r1 = 6.0f;
    L11:
        addView(r132, LayoutHelper.createFrame(24, 24.0f, r7, r8, 32.1f, r1, 0.0f));
        if (r14 != 1) goto L14;
        TextPaint r142 = new TextPaint(1);
        this.description2TextPaint = r142;
        r142.setTextSize(AndroidUtilities.dp(13.0f));
        SpannableStringBuilder r143 = new SpannableStringBuilder(".");
        this.dotSpan = r143;
        r143.setSpan(new DotDividerSpan(), 0, 1, 0);
    L14:
        TextPaint r144 = new TextPaint(1);
        this.captionTextPaint = r144;
        r144.setTextSize(AndroidUtilities.dp(13.0f));
        if (r15 == null) goto L18;
        TextPaint r133 = new TextPaint(1);
        this.titlePaint = r133;
        r133.setTypeface(AndroidUtilities.bold());
        this.titlePaint.setTextSize(AndroidUtilities.dp(15.0f));
        this.titlePaint.setColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        return;
    L18:
        return;
    L8:
        r8 = 38.1f;
        goto L9
    }

    public SharedAudioCell(Context r2, Theme.ResourcesProvider r3) {
        this(r2, 0, r3);
    }

    private boolean checkAudioMotionEvent(MotionEvent r9) {
        int r0 = (int) r9.getX();
        int r1 = (int) r9.getY();
        int r2 = AndroidUtilities.dp(36.0f);
        if (this.miniButtonState < 0) goto L13;
        int r3 = AndroidUtilities.dp(27.0f);
        int r6 = this.buttonX;
        if (r0 < (r6 + r3)) goto L13;
        if (r0 > ((r6 + r3) + r2)) goto L13;
        int r62 = this.buttonY;
        if (r1 < (r62 + r3)) goto L13;
        if (r1 > ((r62 + r3) + r2)) goto L13;
        boolean r22 = true;
    L15:
        if (r9.getAction() != 0) goto L25;
        if (r22 == false) goto L20;
        this.miniButtonPressed = true;
        this.radialProgress.setPressed(true, true);
        invalidate();
    L18:
        boolean r92 = true;
    L43:
        this.radialProgress.setPressed(this.miniButtonPressed, true);
        if (r92 == false) goto L46;
    L49:
        return true;
    L46:
        if (this.buttonPressed == true) goto L49;
        return false;
    L20:
        if (this.checkForButtonPress == true) goto L22;
    L42:
        r92 = false;
        goto L43
    L22:
        if (this.radialProgress.getProgressRect().contains(r0, r1) == false) goto L42;
        requestDisallowInterceptTouchEvent(true);
        this.buttonPressed = true;
        this.radialProgress.setPressed(true, false);
        invalidate();
        goto L18
    L25:
        if (r9.getAction() != 1) goto L34;
        if (this.miniButtonPressed == false) goto L30;
        this.miniButtonPressed = false;
        playSoundEffect(0);
        didPressedMiniButton(true);
        invalidate();
    L32:
        requestDisallowInterceptTouchEvent(false);
        goto L42
    L30:
        if (this.buttonPressed == false) goto L32;
        this.buttonPressed = false;
        playSoundEffect(0);
        didPressedButton();
        invalidate();
        goto L32
    L34:
        if (r9.getAction() != 3) goto L37;
        requestDisallowInterceptTouchEvent(false);
        this.miniButtonPressed = false;
        this.buttonPressed = false;
        invalidate();
        goto L42
    L37:
        if (r9.getAction() != 2) goto L42;
        if (r22 == true) goto L42;
        if (this.miniButtonPressed == false) goto L42;
        this.miniButtonPressed = false;
        invalidate();
    L13:
        r22 = false;
        goto L15
    }

    private void didPressedMiniButton(boolean r5) {
        int r52 = this.miniButtonState;
        if (r52 != 0) goto L6;
        this.miniButtonState = 1;
        this.radialProgress.setProgress(0.0f, false);
        FileLoader.getInstance(this.currentAccount).loadFile(this.currentMessageObject.getDocument(), this.currentMessageObject, 1, 0);
        this.radialProgress.setMiniIcon(getMiniIconForCurrentState(), false, true);
        invalidate();
        return;
    L6:
        if (r52 == 1) goto L8;
        return;
    L8:
        if (MediaController.getInstance().isPlayingMessage(this.currentMessageObject) == false) goto L10;
        MediaController.getInstance().cleanupPlayer(true, true);
    L10:
        this.miniButtonState = 0;
        FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.currentMessageObject.getDocument());
        this.radialProgress.setMiniIcon(getMiniIconForCurrentState(), false, true);
        invalidate();
    }

    private void drawInternal(Canvas r15) {
        if (this.viewType != 1) goto L5;
        this.description2TextPaint.setColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText3));
    L5:
        int r2 = 0;
        float r10 = 24.0f;
        if (this.dateLayout == null) goto L16;
        r15.save();
        if (LocaleController.isRTL == false) goto L10;
        float r1 = 24.0f;
    L11:
        int r12 = AndroidUtilities.dp(r1);
        if (LocaleController.isRTL == false) goto L14;
        int r3 = 0;
    L15:
        r15.translate(r12 + r3, this.titleY);
        this.dateLayout.draw(r15);
        r15.restore();
        goto L16
    L14:
        r3 = this.dateLayoutX;
        goto L15
    L10:
        r1 = AndroidUtilities.leftBaseline;
    L16:
        float r11 = 0.0f;
        if (this.titleLayout == null) goto L42;
        int r13 = Theme.chat_contextResult_titleTextPaint.getAlpha();
        float r14 = this.showNameProgress;
        if (r14 == 1.0f) goto L21;
        Theme.chat_contextResult_titleTextPaint.setAlpha((int) (r13 * r14));
    L21:
        r15.save();
        if (LocaleController.isRTL == false) goto L24;
        float r16 = 24.0f;
    L25:
        int r17 = AndroidUtilities.dp(r16);
        if (LocaleController.isRTL == false) goto L34;
        StaticLayout r32 = this.dateLayout;
        if (r32 == null) goto L34;
        int r22 = r32.getWidth();
        if (LocaleController.isRTL == false) goto L32;
        float r33 = 12.0f;
    L33:
        r2 = r22 + AndroidUtilities.dp(r33);
        goto L34
    L32:
        r33 = 4.0f;
    L34:
        float r18 = r17 + r2;
        if (LocaleController.isRTL == false) goto L37;
        float r23 = this.titleLayout.getWidth() - this.titleLayoutWidth;
    L38:
        r15.translate((r18 + r23) - this.titleLayoutLeft, this.titleY);
        this.titleLayout.draw(r15);
        AnimatedEmojiSpan.drawAnimatedEmojis(r15, this.titleLayout, this.titleLayoutEmojis, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f);
        r15.restore();
        if (this.showNameProgress == 1.0f) goto L42;
        Theme.chat_contextResult_titleTextPaint.setAlpha(r13);
        goto L42
    L37:
        r23 = 0.0f;
        goto L38
    L24:
        r16 = AndroidUtilities.leftBaseline;
    L42:
        if (this.captionLayout == null) goto L53;
        this.captionTextPaint.setColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        r15.save();
        if (LocaleController.isRTL == false) goto L46;
        float r19 = 24.0f;
    L47:
        float r110 = AndroidUtilities.dp(r19);
        if (LocaleController.isRTL == false) goto L50;
        float r24 = this.captionLayout.getWidth() - this.captionLayoutWidth;
    L51:
        r15.translate((r110 + r24) - this.captionLayoutLeft, this.captionY);
        this.captionLayout.draw(r15);
        r15.restore();
        goto L53
    L50:
        r24 = 0.0f;
        goto L51
    L46:
        r19 = AndroidUtilities.leftBaseline;
    L53:
        if (this.descriptionLayout == null) goto L67;
        Theme.chat_contextResult_descriptionTextPaint.setColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText2));
        int r132 = Theme.chat_contextResult_descriptionTextPaint.getAlpha();
        float r111 = this.showNameProgress;
        if (r111 == 1.0f) goto L57;
        Theme.chat_contextResult_descriptionTextPaint.setAlpha((int) (r132 * r111));
    L57:
        r15.save();
        if (LocaleController.isRTL == true) goto L61;
        r10 = AndroidUtilities.leftBaseline;
    L61:
        float r112 = AndroidUtilities.dp(r10);
        if (LocaleController.isRTL == false) goto L64;
        r11 = this.descriptionLayout.getWidth() - this.descriptionLayoutWidth;
    L64:
        r15.translate((r112 + r11) - this.descriptionLayoutLeft, this.descriptionY);
        this.descriptionLayout.draw(r15);
        AnimatedEmojiSpan.drawAnimatedEmojis(r15, this.descriptionLayout, this.descriptionLayoutEmojis, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f);
        r15.restore();
        if (this.showNameProgress == 1.0f) goto L67;
        Theme.chat_contextResult_descriptionTextPaint.setAlpha(r132);
    L67:
        RadialProgress2 r113 = this.radialProgress;
        if (this.buttonPressed == false) goto L70;
        int r25 = Theme.key_chat_inAudioSelectedProgress;
    L71:
        r113.setProgressColor(getThemedColor(r25));
        this.radialProgress.setOverlayImageAlpha(this.showNameProgress);
        this.radialProgress.draw(r15);
        if (this.needDivider == true) goto L74;
        return;
    L74:
        if (LocaleController.isRTL == false) goto L77;
        r15.drawLine(0.0f, getHeight() - 1, (getWidth() - AndroidUtilities.dp(72.0f)) - getPaddingRight(), getHeight() - 1, Theme.getThemePaint("paintDivider", this.resourcesProvider));
        return;
    L77:
        r15.drawLine(AndroidUtilities.dp(72.0f), getHeight() - 1, getWidth() - getPaddingRight(), getHeight() - 1, Theme.getThemePaint("paintDivider", this.resourcesProvider));
        return;
    L70:
        r25 = Theme.key_chat_inAudioProgress;
        goto L71
    }

    private void drawReorder(Canvas r7) {
        boolean r0 = this.showReorderIcon;
        if (r0 == true) goto L9;
        if (this.showReorderIconProgress != 0.0f) goto L9;
        return;
    L9:
        if (r0 == false) goto L13;
        float r4 = this.showReorderIconProgress;
        if (r4 == 1.0f) goto L13;
        this.showReorderIconProgress = r4 + 0.10666667f;
        invalidate();
    L17:
        this.showReorderIconProgress = Utilities.clamp(this.showReorderIconProgress, 1.0f, 0.0f);
        int r02 = (getMeasuredWidth() - AndroidUtilities.dp(12.0f)) - Theme.dialogs_reorderDrawable.getIntrinsicWidth();
        int r1 = (getMeasuredHeight() - Theme.dialogs_reorderDrawable.getIntrinsicHeight()) >> 1;
        r7.save();
        float r6 = this.showReorderIconProgress;
        r7.scale(r6, r6, r02 + (Theme.dialogs_reorderDrawable.getIntrinsicWidth() / 2.0f), r1 + (Theme.dialogs_reorderDrawable.getIntrinsicHeight() / 2.0f));
        Drawable r62 = Theme.dialogs_reorderDrawable;
        r62.setBounds(r02, r1, r62.getIntrinsicWidth() + r02, Theme.dialogs_reorderDrawable.getIntrinsicHeight() + r1);
        Theme.dialogs_reorderDrawable.draw(r7);
        r7.restore();
        return;
    L13:
        if (r0 == true) goto L17;
        float r03 = this.showReorderIconProgress;
        if (r03 == 0.0f) goto L17;
        this.showReorderIconProgress = r03 - 0.10666667f;
        invalidate();
        goto L17
    }

    private int getIconForCurrentState() {
        int r1 = this.buttonState;
        if (r1 != 1) goto L6;
        return 1;
    L6:
        if (r1 != 2) goto L9;
        return 2;
    L9:
        if (r1 != 4) goto L12;
        return 3;
    L12:
        return 0;
    }

    private int getMiniIconForCurrentState() {
        int r0 = this.miniButtonState;
        if (r0 >= 0) goto L6;
        return 4;
    L6:
        if (r0 != 0) goto L9;
        return 2;
    L9:
        return 3;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    public void didPressedButton() {
        int r0 = this.buttonState;
        if (r0 == 0) goto L5;
        if (r0 != 1) goto L22;
        if (MediaController.getInstance().pauseMessage(this.currentMessageObject) == false) goto L29;
        this.buttonState = 0;
        this.radialProgress.setIcon(getIconForCurrentState(), false, true);
        invalidate();
        return;
    L29:
        return;
    L22:
        if (r0 != 2) goto L25;
        this.radialProgress.setProgress(0.0f, false);
        this.currentMessageObject.putInDownloadsStore = true;
        FileLoader.getInstance(this.currentAccount).loadFile(this.currentMessageObject.getDocument(), this.currentMessageObject, 1, 0);
        this.buttonState = 4;
        this.radialProgress.setIcon(getIconForCurrentState(), false, true);
        invalidate();
        return;
    L25:
        if (r0 != 4) goto L30;
        FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.currentMessageObject.getDocument());
        this.buttonState = 2;
        this.radialProgress.setIcon(getIconForCurrentState(), false, true);
        invalidate();
        return;
    L30:
        return;
    L5:
        if (this.miniButtonState != 0) goto L8;
        this.currentMessageObject.putInDownloadsStore = true;
        FileLoader.getInstance(this.currentAccount).loadFile(this.currentMessageObject.getDocument(), this.currentMessageObject, 1, 0);
    L8:
        if (needPlayMessage(this.currentMessageObject) == true) goto L10;
        return;
    L10:
        if (this.hasMiniProgress == 2) goto L12;
    L14:
        this.buttonState = 1;
        this.radialProgress.setIcon(getIconForCurrentState(), false, true);
        invalidate();
        return;
    L12:
        if (this.miniButtonState == 1) goto L14;
        this.miniButtonState = 1;
        this.radialProgress.setProgress(0.0f, false);
        this.radialProgress.setMiniIcon(getMiniIconForCurrentState(), false, true);
        goto L14
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        updateButtonState(false, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r20) {
        boolean r1 = this.showName;
        if (r1 == false) goto L7;
        float r5 = this.showNameProgress;
        if (r5 == 1.0f) goto L7;
        this.showNameProgress = r5 + 0.10666667f;
        invalidate();
    L11:
        this.showNameProgress = Utilities.clamp(this.showNameProgress, 1.0f, 0.0f);
        if (this.enterAlpha != 1.0f) goto L14;
    L17:
        drawInternal(r20);
        drawReorder(r20);
        super.dispatchDraw(r20);
        return;
    L14:
        if (this.globalGradientView == null) goto L17;
        r20.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), (int) ((1.0f - this.enterAlpha) * 255.0f), 31);
        this.globalGradientView.setViewType(4);
        this.globalGradientView.updateColors();
        this.globalGradientView.updateGradient();
        this.globalGradientView.draw(r20);
        r20.restore();
        r20.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), (int) (this.enterAlpha * 255.0f), 31);
        drawInternal(r20);
        super.dispatchDraw(r20);
        drawReorder(r20);
        r20.restore();
        return;
    L7:
        if (r1 == true) goto L11;
        float r12 = this.showNameProgress;
        if (r12 == 0.0f) goto L11;
        this.showNameProgress = r12 - 0.10666667f;
        invalidate();
        goto L11
    }

    public MessageObject getMessage() {
        return this.currentMessageObject;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return this.TAG;
    }

    public void initStreamingIcons() {
        this.radialProgress.initMiniIcons();
    }

    public boolean needPlayMessage(MessageObject r1) {
        Utilities.CallbackReturn<MessageObject, Boolean> r0 = this.needPlayMessageListener;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.run(r1).booleanValue() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.radialProgress.onAttachedToWindow();
        updateButtonState(false, false);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingDidStart);
        this.titleLayoutEmojis = AnimatedEmojiSpan.update(0, this, this.titleLayoutEmojis, new Layout[]{this.titleLayout});
        this.descriptionLayoutEmojis = AnimatedEmojiSpan.update(0, this, this.descriptionLayoutEmojis, new Layout[]{this.descriptionLayout});
        this.captionLayoutEmojis = AnimatedEmojiSpan.update(0, this, this.captionLayoutEmojis, new Layout[]{this.captionLayout});
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        this.radialProgress.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingDidStart);
        AnimatedEmojiSpan.release(this, this.titleLayoutEmojis);
        AnimatedEmojiSpan.release(this, this.descriptionLayoutEmojis);
        AnimatedEmojiSpan.release(this, this.captionLayoutEmojis);
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r1, boolean r2) {
        updateButtonState(true, r2);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r5) {
        super.onInitializeAccessibilityNodeInfo(r5);
        r5.setEnabled(true);
        if (this.currentMessageObject.isMusic() == false) goto L6;
        r5.setText(LocaleController.formatString("AccDescrMusicInfo", R.string.AccDescrMusicInfo, new Object[]{this.currentMessageObject.getMusicAuthor(), this.currentMessageObject.getMusicTitle()}));
    L11:
        if (this.checkBox.isChecked() == false) goto L13;
        r5.setCheckable(true);
        r5.setChecked(true);
    L13:
        int r4 = getIconForCurrentState();
        if (r4 != 1) goto L16;
        String r42 = LocaleController.getString("AccActionPause", R.string.AccActionPause);
    L23:
        r5.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, r42));
        return;
    L16:
        if (r4 != 2) goto L18;
        r42 = LocaleController.getString("AccActionDownload", R.string.AccActionDownload);
        goto L23
    L18:
        if (r4 == 3) goto L20;
        r42 = LocaleController.getString("AccActionPlay", R.string.AccActionPlay);
        goto L23
    L20:
        r42 = LocaleController.getString("AccActionCancelDownload", R.string.AccActionCancelDownload);
        goto L23
    L6:
        if (this.titleLayout == null) goto L11;
        if (this.descriptionLayout == null) goto L11;
        r5.setText(this.titleLayout.getText() + ", " + this.descriptionLayout.getText());
        goto L11
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r1) {
        return onTouchEvent(r1);
    }

    @Override // android.widget.FrameLayout, android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onMeasure(int r23, int r24) {
        this.descriptionLayout = null;
        this.titleLayout = null;
        this.captionLayout = null;
        int r2 = (View.MeasureSpec.getSize(r23) - AndroidUtilities.dp(AndroidUtilities.leftBaseline)) - AndroidUtilities.dp(28.0f);
        int r4 = 0;
        if (this.viewType != 1) goto L5;
        String r8 = LocaleController.stringForMessageListDate(this.currentMessageObject.messageOwner.date);
        int r10 = (int) Math.ceil(this.description2TextPaint.measureText(r8));
        this.dateLayout = ChatMessageCell.generateStaticLayout(r8, this.description2TextPaint, r10, r10, 0, 1);
        this.dateLayoutX = ((r2 - r10) - AndroidUtilities.dp(8.0f)) + AndroidUtilities.dp(20.0f);
        int r102 = r10 + AndroidUtilities.dp(12.0f);
    L6:
        float r9 = 0.0f;
    L14:
        e = move-exception;
        FileLog.e(e);
    L36:
        boolean r0 = this.currentMessageObject.hasHighlightedWords();
        String r103 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 == true) goto L39;
    L106:
    L66:
        e = move-exception;
        FileLog.e(e);
    L93:
        int r02 = View.MeasureSpec.getSize(r23);
        int r22 = AndroidUtilities.dp(56.0f);
        if (this.captionLayout == null) goto L97;
        r4 = AndroidUtilities.dp(18.0f);
    L97:
        setMeasuredDimension(r02, (r22 + r4) + (this.needDivider ? 1 : 0));
        int r03 = AndroidUtilities.dp(52.0f);
        if (LocaleController.isRTL == false) goto L100;
        int r25 = (View.MeasureSpec.getSize(r23) - AndroidUtilities.dp(8.0f)) - r03;
    L101:
        RadialProgress2 r04 = this.radialProgress;
        int r3 = AndroidUtilities.dp(4.0f) + r25;
        this.buttonX = r3;
        int r42 = AndroidUtilities.dp(6.0f);
        this.buttonY = r42;
        r04.setProgressRect(r3, r42, r25 + AndroidUtilities.dp(48.0f), AndroidUtilities.dp(50.0f));
        measureChildWithMargins(this.checkBox, r23, 0, r24, 0);
        if (this.captionLayout == null) goto L104;
        this.captionY = AndroidUtilities.dp(29.0f);
        this.descriptionY = AndroidUtilities.dp(29.0f) + AndroidUtilities.dp(18.0f);
        return;
    L104:
        this.descriptionY = AndroidUtilities.dp(29.0f);
        return;
    L100:
        r25 = AndroidUtilities.dp(8.0f);
        goto L101
    L56:
        if (this.currentMessageObject.isVoice() == false) goto L58;
    L77:
        String r05 = AndroidUtilities.formatDuration((int) this.currentMessageObject.getDuration(), false) + " • " + this.currentMessageObject.getMusicAuthor().replace('\n', ' ');     // Catch: Exception -> L66
        if (this.viewType != 1) goto L81;
        TextPaint r6 = this.description2TextPaint;     // Catch: Exception -> L66
    L80:
        TextPaint r12 = r6;
        this.descriptionLayout = new StaticLayout(TextUtils.ellipsize(r05, r12, r2, TextUtils.TruncateAt.END), r12, r2 + AndroidUtilities.dp(4.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L66
    L84:
        if (this.descriptionLayout.getLineCount() <= 0) goto L86;
        float r06 = this.descriptionLayout.getLineLeft(0);     // Catch: Exception -> L66
    L87:
        this.descriptionLayoutLeft = r06;     // Catch: Exception -> L66
        if (this.descriptionLayout.getLineCount() <= 0) goto L90;
        r9 = this.descriptionLayout.getLineWidth(0);     // Catch: Exception -> L66
    L90:
        this.descriptionLayoutWidth = r9;     // Catch: Exception -> L66
        this.descriptionLayoutEmojis = AnimatedEmojiSpan.update(0, this, this.descriptionLayoutEmojis, new Layout[]{this.descriptionLayout});     // Catch: Exception -> L66
        goto L93
    L86:
        r06 = 0.0f;
        goto L87
    L81:
        r6 = Theme.chat_contextResult_descriptionTextPaint;     // Catch: Exception -> L66
        goto L80
    L58:
        if (this.currentMessageObject.isRoundVideo() == true) goto L77;
        CharSequence r07 = this.currentMessageObject.getMusicAuthor().replace('\n', ' ');     // Catch: Exception -> L66
        CharSequence r62 = AndroidUtilities.highlightText(r07, this.currentMessageObject.highlightedWords, this.resourcesProvider);     // Catch: Exception -> L66
        if (r62 == null) goto L64;
        r07 = r62;
    L64:
        if (this.viewType != 1) goto L69;
        r07 = new SpannableStringBuilder(r07).append(' ').append(this.dotSpan).append(' ').append(FilteredSearchView.createFromInfoString(this.currentMessageObject, 1));     // Catch: Exception -> L66
    L69:
        if (this.viewType != 1) goto L72;
        TextPaint r63 = this.description2TextPaint;     // Catch: Exception -> L66
    L71:
        TextPaint r14 = r63;
        if (this.currentMessageObject.getDuration() <= 0.0d) goto L76;
        r103 = " • " + LocaleController.formatShortDuration((int) Math.ceil(this.currentMessageObject.getDuration()));     // Catch: Exception -> L66
    L76:
        this.descriptionLayout = new StaticLayout(SpannableStringBuilder.valueOf(TextUtils.ellipsize(r07, r14, r2 - r14.measureText(r103), TextUtils.TruncateAt.END)).append(r103), r14, r2 + AndroidUtilities.dp(4.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L66
        goto L84
    L72:
        r63 = Theme.chat_contextResult_descriptionTextPaint;     // Catch: Exception -> L66
        goto L71
    L39:
        if (TextUtils.isEmpty(this.currentMessageObject.messageOwner.message) == false) goto L41;
        CharSequence r08 = _UrlKt.FRAGMENT_ENCODE_SET;
    L42:
        CharSequence r09 = AndroidUtilities.highlightText(r08, this.currentMessageObject.highlightedWords, this.resourcesProvider);
        if (r09 == null) goto L53;
        StaticLayout r142 = new StaticLayout(TextUtils.ellipsize(AndroidUtilities.ellipsizeCenterEnd(r09, this.currentMessageObject.highlightedWords.get(0), r2, this.captionTextPaint, 130), this.captionTextPaint, r2, TextUtils.TruncateAt.END), this.captionTextPaint, r2 + AndroidUtilities.dp(4.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.captionLayout = r142;
        if (r142.getLineCount() <= 0) goto L47;
        float r010 = this.captionLayout.getLineLeft(0);
    L48:
        this.captionLayoutLeft = r010;
        if (this.captionLayout.getLineCount() <= 0) goto L51;
        float r011 = this.captionLayout.getLineWidth(0);
    L52:
        this.captionLayoutWidth = r011;
        goto L53
    L51:
        r011 = 0.0f;
        goto L52
    L47:
        r010 = 0.0f;
    L53:
        this.captionLayoutEmojis = AnimatedEmojiSpan.update(0, this, this.captionLayoutEmojis, new Layout[]{this.captionLayout});
        goto L106
    L41:
        r08 = Emoji.replaceEmoji(this.currentMessageObject.messageOwner.message.replace("\n", " ").replaceAll(" +", " ").trim(), Theme.chat_msgTextPaint.getFontMetricsInt(), false);
        goto L42
    L8:
        if (this.viewType == 1) goto L10;
    L17:
        CharSequence r012 = this.currentMessageObject.getMusicTitle().replace('\n', ' ');     // Catch: Exception -> L14
    L18:
        CharSequence r11 = AndroidUtilities.highlightText(r012, this.currentMessageObject.highlightedWords, this.resourcesProvider);     // Catch: Exception -> L14
        if (r11 == null) goto L21;
        r012 = r11;
    L21:
        TextPaint r112 = this.titlePaint;     // Catch: Exception -> L14
        if (r112 == null) goto L24;
    L23:
        TextPaint r143 = r112;
        StaticLayout r122 = new StaticLayout(TextUtils.ellipsize(r012, r143, r2 - r102, TextUtils.TruncateAt.END), r143, (AndroidUtilities.dp(4.0f) + r2) - r102, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L14
        this.titleLayout = r122;     // Catch: Exception -> L14
        if (r122.getLineCount() <= 0) goto L28;
        float r013 = this.titleLayout.getLineLeft(0);     // Catch: Exception -> L14
    L29:
        this.titleLayoutLeft = r013;     // Catch: Exception -> L14
        if (this.titleLayout.getLineCount() <= 0) goto L32;
        float r014 = this.titleLayout.getLineWidth(0);     // Catch: Exception -> L14
    L33:
        this.titleLayoutWidth = r014;     // Catch: Exception -> L14
        this.titleLayoutEmojis = AnimatedEmojiSpan.update(0, this, this.titleLayoutEmojis, new Layout[]{this.titleLayout});     // Catch: Exception -> L14
        goto L36
    L32:
        r014 = 0.0f;
        goto L33
    L28:
        r013 = 0.0f;
        goto L29
    L24:
        r112 = Theme.chat_contextResult_titleTextPaint;     // Catch: Exception -> L14
        goto L23
    L10:
        if (this.currentMessageObject.isVoice() == false) goto L12;
    L16:
        r012 = FilteredSearchView.createFromInfoString(this.currentMessageObject, 1);     // Catch: Exception -> L14
        goto L18
    L12:
        if (this.currentMessageObject.isRoundVideo() == false) goto L17;
    L5:
        r102 = 0;
        goto L6
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r1, long r2, long r4) {
        this.radialProgress.setProgress(Math.min(1.0f, r2 / r4), true);
        if (this.hasMiniProgress == 0) goto L9;
        if (this.miniButtonState == 1) goto L12;
        updateButtonState(false, true);
        return;
    L12:
        return;
    L9:
        if (this.buttonState == 4) goto L13;
        updateButtonState(false, true);
        return;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onSuccessDownload(String r3) {
        this.radialProgress.setProgress(1.0f, true);
        updateButtonState(false, true);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r3) {
        if (this.currentMessageObject == null) goto L5;
        boolean r0 = checkAudioMotionEvent(r3);
        if (r3.getAction() != 3) goto L10;
        this.miniButtonPressed = false;
        this.buttonPressed = false;
        this.radialProgress.setPressed(false, false);
        this.radialProgress.setPressed(this.miniButtonPressed, true);
        return false;
    L10:
        return r0;
    L5:
        return super.onTouchEvent(r3);
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int r2, Bundle r3) {
        if (r2 != 16) goto L7;
        didPressedButton();
        return true;
    L7:
        return super.performAccessibilityAction(r2, r3);
    }

    public void setCheckForButtonPress(boolean r1) {
        this.checkForButtonPress = r1;
    }

    public void setChecked(boolean r3, boolean r4) {
        if (this.checkBox.getVisibility() == 0) goto L5;
        this.checkBox.setVisibility(0);
    L5:
        this.checkBox.setChecked(r3, r4);
    }

    public void setEnterAnimationAlpha(float r2) {
        if (this.enterAlpha == r2) goto L6;
        this.enterAlpha = r2;
        invalidate();
        return;
    }

    public void setGlobalGradientView(FlickerLoadingView r1) {
        this.globalGradientView = r1;
    }

    public void setMessageObject(MessageObject r4, boolean r5) {
        this.needDivider = r5;
        this.currentMessageObject = r4;
        TLRPC.Document r52 = r4.getDocument();
        if (r52 == null) goto L5;
        TLRPC.PhotoSize r1 = FileLoader.getClosestPhotoSizeWithSize(r52.thumbs, 360);
    L7:
        if ((r1 instanceof TLRPC.TL_photoSize) == false) goto L9;
    L21:
        this.radialProgress.setImageOverlay(r1, r52, r4);
    L22:
        updateButtonState(false, false);
        requestLayout();
        return;
    L9:
        if ((r1 instanceof TLRPC.TL_photoSizeProgressive) == true) goto L21;
        Bitmap r53 = r4.audioCover;
        if (r53 != null) goto L15;
        r53 = null;
    L15:
        if (r53 == null) goto L17;
        this.radialProgress.setImageOverlay(r53);
        goto L22
    L17:
        String r42 = r4.getArtworkUrl(true);
        boolean r54 = TextUtils.isEmpty(r42);
        RadialProgress2 r12 = this.radialProgress;
        if (r54 == true) goto L20;
        r12.setImageOverlay(r42);
        goto L22
    L20:
        r12.setImageOverlay(null, null, null);
        goto L22
    L5:
        r1 = null;
        goto L7
    }

    public void setNeedPlayMessageListener(Utilities.CallbackReturn<MessageObject, Boolean> r1) {
        this.needPlayMessageListener = r1;
    }

    public void showName(boolean r1, boolean r2) {
        if (r2 == true) goto L8;
        if (r1 == false) goto L5;
        float r22 = 1.0f;
    L6:
        this.showNameProgress = r22;
        goto L8
    L5:
        r22 = 0.0f;
    L8:
        if (this.showName != r1) goto L10;
        return;
    L10:
        this.showName = r1;
        invalidate();
    }

    public void showReorderIcon(boolean r2, boolean r3) {
        if (this.showReorderIcon != r2) goto L5;
        return;
    L5:
        this.showReorderIcon = r2;
        if (r3 == true) goto L11;
        if (r2 == false) goto L9;
        float r22 = 1.0f;
    L10:
        this.showReorderIconProgress = r22;
        goto L11
    L9:
        r22 = 0.0f;
    L11:
        invalidate();
    }

    public void updateButtonState(boolean r11, boolean r12) {
        String r0 = this.currentMessageObject.getFileName();
        if (TextUtils.isEmpty(r0) == false) goto L5;
        return;
    L5:
        MessageObject r1 = this.currentMessageObject;
        if (r1.attachPathExists == false) goto L8;
    L11:
        boolean r2 = true;
    L13:
        if (SharedConfig.streamMedia == true) goto L15;
    L22:
        this.hasMiniProgress = 0;
        this.miniButtonState = -1;
    L24:
        if (this.hasMiniProgress == 0) goto L51;
        RadialProgress2 r13 = this.radialProgress;
        if (this.currentMessageObject.isOutOwner() == false) goto L28;
        int r22 = Theme.key_chat_outLoader;
    L29:
        r13.setMiniProgressBackgroundColor(getThemedColor(r22));
        boolean r14 = MediaController.getInstance().isPlayingMessage(this.currentMessageObject);
        if (r14 == false) goto L36;
        if (r14 == true) goto L33;
    L35:
        this.buttonState = 1;
    L37:
        this.radialProgress.setIcon(getIconForCurrentState(), r11, r12);
        int r15 = this.hasMiniProgress;
        int r23 = this.currentAccount;
        if (r15 != 1) goto L41;
        DownloadController.getInstance(r23).removeLoadingFileObserver(this);
        this.miniButtonState = -1;
        this.radialProgress.setMiniIcon(getMiniIconForCurrentState(), r11, r12);
        return;
    L41:
        DownloadController.getInstance(r23).addLoadingFileObserver(r0, this.currentMessageObject, this);
        if (FileLoader.getInstance(this.currentAccount).isLoadingFile(r0) == true) goto L45;
        this.miniButtonState = 0;
        this.radialProgress.setMiniIcon(getMiniIconForCurrentState(), r11, r12);
        return;
    L45:
        this.miniButtonState = 1;
        this.radialProgress.setMiniIcon(getMiniIconForCurrentState(), r11, r12);
        Float r112 = ImageLoader.getInstance().getFileProgress(r0);
        RadialProgress2 r10 = this.radialProgress;
        if (r112 == null) goto L49;
        r10.setProgress(r112.floatValue(), r12);
        return;
    L49:
        r10.setProgress(0.0f, r12);
        return;
    L33:
        if (MediaController.getInstance().isMessagePaused() == false) goto L35;
    L36:
        this.buttonState = 0;
        goto L37
    L28:
        r22 = Theme.key_chat_inLoader;
        goto L29
    L51:
        int r16 = this.currentAccount;
        if (r2 == false) goto L63;
        DownloadController.getInstance(r16).removeLoadingFileObserver(this);
        boolean r02 = MediaController.getInstance().isPlayingMessage(this.currentMessageObject);
        if (r02 == false) goto L60;
        if (r02 == true) goto L57;
    L59:
        this.buttonState = 1;
    L61:
        this.radialProgress.setProgress(1.0f, r12);
        this.radialProgress.setIcon(getIconForCurrentState(), r11, r12);
        invalidate();
        return;
    L57:
        if (MediaController.getInstance().isMessagePaused() == false) goto L59;
    L60:
        this.buttonState = 0;
        goto L61
    L63:
        DownloadController.getInstance(r16).addLoadingFileObserver(r0, this.currentMessageObject, this);
        if (FileLoader.getInstance(this.currentAccount).isLoadingFile(r0) == true) goto L66;
        this.buttonState = 2;
        this.radialProgress.setProgress(0.0f, r12);
    L70:
        this.radialProgress.setIcon(getIconForCurrentState(), r11, r12);
        invalidate();
        return;
    L66:
        this.buttonState = 4;
        Float r03 = ImageLoader.getInstance().getFileProgress(r0);
        RadialProgress2 r17 = this.radialProgress;
        if (r03 == null) goto L69;
        r17.setProgress(r03.floatValue(), r12);
        goto L70
    L69:
        r17.setProgress(0.0f, r12);
        goto L70
    L15:
        if (r1.isMusic() == false) goto L22;
        if (((int) this.currentMessageObject.getDialogId()) == 0) goto L22;
        if (r2 == false) goto L20;
        int r18 = 1;
    L21:
        this.hasMiniProgress = r18;
        r2 = true;
        goto L24
    L20:
        r18 = 2;
        goto L21
    L8:
        if (r1.mediaExists == true) goto L11;
        r2 = false;
        goto L13
    }
}
