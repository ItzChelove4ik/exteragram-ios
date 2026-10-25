package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.io.File;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.DotDividerSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgress2;
import org.telegram.ui.FilteredSearchView;

/* loaded from: classes6.dex */
public class AudioPlayerCell extends FrameLayout implements DownloadController.FileDownloadProgressListener {
    private int TAG;
    private boolean buttonPressed;
    private int buttonState;
    private int buttonX;
    private int buttonY;
    private int currentAccount;
    private MessageObject currentMessageObject;
    private StaticLayout descriptionLayout;
    private AnimatedEmojiSpan.EmojiGroupedSpans descriptionLayoutEmojis;
    private final int descriptionY;
    private SpannableStringBuilder dotSpan;
    private int hasMiniProgress;
    private boolean miniButtonPressed;
    private int miniButtonState;
    private boolean needDivider;
    private final ImageView optionsButton;
    private RadialProgress2 radialProgress;
    private final Theme.ResourcesProvider resourcesProvider;
    private StaticLayout titleLayout;
    private AnimatedEmojiSpan.EmojiGroupedSpans titleLayoutEmojis;
    private final int titleY;
    private int viewType;

    /* renamed from: $r8$lambda$rQ-9o2aGm7Anfro19pIupw3ws-o, reason: not valid java name */
    public static /* synthetic */ void m6618$r8$lambda$rQ9o2aGm7Anfro19pIupw3wso(View r0) {
    }

    public AudioPlayerCell(Context r11, int r12, Theme.ResourcesProvider r13) {
        super(r11);
        this.titleY = AndroidUtilities.dp(9.0f);
        this.descriptionY = AndroidUtilities.dp(29.0f);
        this.currentAccount = UserConfig.selectedAccount;
        setWillNotDraw(false);
        this.resourcesProvider = r13;
        this.viewType = r12;
        ImageView r1 = new ImageView(r11);
        this.optionsButton = r1;
        r1.setScaleType(ImageView.ScaleType.CENTER);
        r1.setImageResource(R.drawable.ic_ab_other);
        r1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon, r13), PorterDuff.Mode.SRC_IN));
        r1.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector, r13), 1));
        if (LocaleController.isRTL == false) goto L5;
        int r112 = 3;
    L6:
        addView(r1, LayoutHelper.createFrame(42, 42.0f, r112 | 16, 5.0f, 0.0f, 5.0f, 0.0f));
        r1.setOnClickListener(new AudioPlayerCell$$ExternalSyntheticLambda0());
        RadialProgress2 r113 = new RadialProgress2(this, r13);
        this.radialProgress = r113;
        r113.setColorKeys(Theme.key_chat_inLoader, Theme.key_chat_inLoaderSelected, Theme.key_chat_inMediaIcon, Theme.key_chat_inMediaIconSelected);
        this.TAG = DownloadController.getInstance(this.currentAccount).generateObserverTag();
        setFocusable(true);
        if (r12 != 1) goto L10;
        SpannableStringBuilder r114 = new SpannableStringBuilder(".");
        this.dotSpan = r114;
        r114.setSpan(new DotDividerSpan(), 0, 1, 0);
        return;
    L10:
        return;
    L5:
        r112 = 5;
        goto L6
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
        int r02 = this.buttonY;
        if (r1 < (r02 + r3)) goto L13;
        if (r1 > ((r02 + r3) + r2)) goto L13;
        boolean r03 = true;
    L15:
        if (r9.getAction() != 0) goto L20;
        if (r03 == false) goto L32;
        this.miniButtonPressed = true;
        this.radialProgress.setPressed(true, true);
        invalidate();
        return true;
    L32:
        return false;
    L20:
        if (this.miniButtonPressed == false) goto L32;
        if (r9.getAction() != 1) goto L25;
        this.miniButtonPressed = false;
        playSoundEffect(0);
        didPressedMiniButton(true);
        invalidate();
    L31:
        this.radialProgress.setPressed(this.miniButtonPressed, true);
        goto L32
    L25:
        if (r9.getAction() != 3) goto L28;
        this.miniButtonPressed = false;
        invalidate();
        goto L31
    L28:
        if (r9.getAction() != 2) goto L31;
        if (r03 == true) goto L31;
        this.miniButtonPressed = false;
        invalidate();
    L13:
        r03 = false;
        goto L15
    }

    private void didPressedMiniButton(boolean r6) {
        int r62 = this.miniButtonState;
        if (r62 != 0) goto L6;
        this.miniButtonState = 1;
        this.radialProgress.setProgress(0.0f, false);
        FileLoader.getInstance(this.currentAccount).loadFile(this.currentMessageObject.getDocument(), this.currentMessageObject, 3, 0);
        this.radialProgress.setMiniIcon(getMiniIconForCurrentState(), false, true);
        invalidate();
        return;
    L6:
        if (r62 == 1) goto L8;
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
        FileLoader.getInstance(this.currentAccount).loadFile(this.currentMessageObject.getDocument(), this.currentMessageObject, 1, 0);
    L8:
        if (MediaController.getInstance().findMessageInPlaylistAndPlay(this.currentMessageObject) == true) goto L10;
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

    public MessageObject getMessageObject() {
        return this.currentMessageObject;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return this.TAG;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.radialProgress.onAttachedToWindow();
        this.titleLayoutEmojis = AnimatedEmojiSpan.update(0, this, this.titleLayoutEmojis, new Layout[]{this.titleLayout});
        this.descriptionLayoutEmojis = AnimatedEmojiSpan.update(0, this, this.descriptionLayoutEmojis, new Layout[]{this.descriptionLayout});
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.radialProgress.onDetachedFromWindow();
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        AnimatedEmojiSpan.release(this, this.titleLayoutEmojis);
        AnimatedEmojiSpan.release(this, this.descriptionLayoutEmojis);
    }

    @Override // android.view.View
    public void onDraw(Canvas r14) {
        int r10 = 0;
        float r12 = 16.0f;
        if (this.titleLayout == null) goto L16;
        r14.save();
        if (LocaleController.isRTL == false) goto L7;
        float r2 = 16.0f;
    L8:
        int r22 = AndroidUtilities.dp(r2);
        if (LocaleController.isRTL == true) goto L11;
    L13:
        int r3 = 0;
    L14:
        r14.translate(r22 + r3, this.titleY);
        this.titleLayout.draw(r14);
        AnimatedEmojiSpan.drawAnimatedEmojis(r14, this.titleLayout, this.titleLayoutEmojis, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f);
        r14.restore();
        goto L16
    L11:
        if (this.optionsButton.getVisibility() != 0) goto L13;
        r3 = AndroidUtilities.dp(32.0f);
        goto L14
    L7:
        r2 = AndroidUtilities.leftBaseline;
    L16:
        if (this.descriptionLayout == null) goto L27;
        Theme.chat_contextResult_descriptionTextPaint.setColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText2));
        r14.save();
        if (LocaleController.isRTL == true) goto L21;
        r12 = AndroidUtilities.leftBaseline;
    L21:
        int r23 = AndroidUtilities.dp(r12);
        if (LocaleController.isRTL == true) goto L24;
    L26:
        r14.translate(r23 + r10, this.descriptionY);
        this.descriptionLayout.draw(r14);
        AnimatedEmojiSpan.drawAnimatedEmojis(r14, this.descriptionLayout, this.descriptionLayoutEmojis, 0.0f, null, 0.0f, 0.0f, 0.0f, 1.0f);
        r14.restore();
        goto L27
    L24:
        if (this.optionsButton.getVisibility() != 0) goto L26;
        r10 = AndroidUtilities.dp(32.0f);
    L27:
        RadialProgress2 r24 = this.radialProgress;
        if (this.buttonPressed == false) goto L30;
        int r32 = Theme.key_chat_inAudioSelectedProgress;
    L31:
        r24.setProgressColor(getThemedColor(r32));
        this.radialProgress.draw(r14);
        super.onDraw(r14);
        if (this.needDivider == false) goto L41;
        Paint r5 = Theme.getThemePaint("paintDivider", this.resourcesProvider);
        if (r5 != null) goto L36;
        return;
    L36:
        if (LocaleController.isRTL == false) goto L39;
        r14.drawRect(0.0f, getHeight() - 1, getWidth() - AndroidUtilities.dp(AndroidUtilities.leftBaseline), getHeight(), r5);
        return;
    L39:
        r14.drawRect(AndroidUtilities.dp(AndroidUtilities.leftBaseline), getHeight() - 1, getWidth(), getHeight(), r5);
        return;
    L41:
        return;
    L30:
        r32 = Theme.key_chat_inAudioProgress;
        goto L31
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r1, boolean r2) {
        updateButtonState(true, r2);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
        super.onInitializeAccessibilityNodeInfo(r3);
        if (this.currentMessageObject.isMusic() == false) goto L6;
        r3.setText(LocaleController.formatString("AccDescrMusicInfo", R.string.AccDescrMusicInfo, new Object[]{this.currentMessageObject.getMusicAuthor(), this.currentMessageObject.getMusicTitle()}));
        return;
    L6:
        r3.setText(this.titleLayout.getText() + ", " + this.descriptionLayout.getText());
    }

    @Override // android.widget.FrameLayout, android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onMeasure(int r19, int r20) {
        this.descriptionLayout = null;
        this.titleLayout = null;
        int r3 = (View.MeasureSpec.getSize(r19) - AndroidUtilities.dp(AndroidUtilities.leftBaseline)) - AndroidUtilities.dp(48.0f);
        CharSequence r0 = TextUtils.ellipsize(this.currentMessageObject.getMusicTitle().replace('\n', ' '), Theme.chat_contextResult_titleTextPaint, Math.min((int) Math.ceil(Theme.chat_contextResult_titleTextPaint.measureText(r0)), r3), TextUtils.TruncateAt.END);     // Catch: Exception -> L9
        CharSequence r9 = AndroidUtilities.highlightText(r0, this.currentMessageObject.highlightedWords, this.resourcesProvider);     // Catch: Exception -> L9
        if (r9 != null) goto L6;
        CharSequence r11 = r0;
    L7:
        StaticLayout r10 = new StaticLayout(r11, Theme.chat_contextResult_titleTextPaint, r3 + AndroidUtilities.dp(4.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L9
        this.titleLayout = r10;     // Catch: Exception -> L9
        this.titleLayoutEmojis = AnimatedEmojiSpan.update(0, this, this.titleLayoutEmojis, new Layout[]{r10});     // Catch: Exception -> L9
    L29:
        CharSequence r02 = this.currentMessageObject.getMusicAuthor().replace('\n', ' ');     // Catch: Exception -> L14
        if (this.viewType != 1) goto L16;
        r02 = new SpannableStringBuilder(r02).append(' ').append(this.dotSpan).append(' ').append(FilteredSearchView.createFromInfoString(this.currentMessageObject, 2));     // Catch: Exception -> L14
    L16:
        CharSequence r03 = TextUtils.ellipsize(r02, Theme.chat_contextResult_descriptionTextPaint, r3, TextUtils.TruncateAt.END);     // Catch: Exception -> L14
        CharSequence r6 = AndroidUtilities.highlightText(r03, this.currentMessageObject.highlightedWords, this.resourcesProvider);     // Catch: Exception -> L14
        if (r6 != null) goto L19;
        CharSequence r92 = r03;
    L20:
        StaticLayout r8 = new StaticLayout(r92, Theme.chat_contextResult_descriptionTextPaint, r3 + AndroidUtilities.dp(4.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L14
        this.descriptionLayout = r8;     // Catch: Exception -> L14
        this.descriptionLayoutEmojis = AnimatedEmojiSpan.update(0, this, this.descriptionLayoutEmojis, new Layout[]{r8});     // Catch: Exception -> L14
    L23:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r19), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
        int r04 = AndroidUtilities.dp(52.0f);
        if (LocaleController.isRTL == false) goto L26;
        int r32 = (View.MeasureSpec.getSize(r19) - AndroidUtilities.dp(8.0f)) - r04;
    L27:
        RadialProgress2 r05 = this.radialProgress;
        int r4 = AndroidUtilities.dp(4.0f) + r32;
        this.buttonX = r4;
        int r5 = AndroidUtilities.dp(6.0f);
        this.buttonY = r5;
        r05.setProgressRect(r4, r5, r32 + AndroidUtilities.dp(48.0f), AndroidUtilities.dp(50.0f));
        return;
    L26:
        r32 = AndroidUtilities.dp(8.0f);
        goto L27
    L19:
        r92 = r6;
    L14:
        e = move-exception;
        FileLog.e(e);
        goto L23
    L6:
        r11 = r9;
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L29
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
        return false;
    L10:
        return r0;
    L5:
        return super.onTouchEvent(r3);
    }

    public void setMessageObject(MessageObject r2, boolean r3, View.OnClickListener r4, boolean r5, View.OnTouchListener r6) {
        this.currentMessageObject = r2;
        if (this.needDivider == r5) goto L5;
        invalidate();
    L5:
        this.needDivider = r5;
        ImageView r52 = this.optionsButton;
        if (r3 == false) goto L8;
        int r0 = R.drawable.list_reorder;
    L9:
        r52.setImageResource(r0);
        ImageView r53 = this.optionsButton;
        if (r3 == true) goto L14;
        if (r4 != null) goto L14;
        int r32 = 8;
    L15:
        r53.setVisibility(r32);
        this.optionsButton.setOnClickListener(r4);
        this.optionsButton.setOnTouchListener(r6);
        TLRPC.Document r33 = r2.getDocument();
        if (r33 == null) goto L18;
        TLRPC.PhotoSize r54 = FileLoader.getClosestPhotoSizeWithSize(r33.thumbs, 90);
    L20:
        if ((r54 instanceof TLRPC.TL_photoSize) == false) goto L22;
    L28:
        this.radialProgress.setImageOverlay(r54, r33, r2);
    L29:
        requestLayout();
        updateButtonState(false, false);
        return;
    L22:
        if ((r54 instanceof TLRPC.TL_photoSizeProgressive) == true) goto L28;
        String r22 = r2.getArtworkUrl(true);
        boolean r34 = TextUtils.isEmpty(r22);
        RadialProgress2 r55 = this.radialProgress;
        if (r34 == true) goto L27;
        r55.setImageOverlay(r22);
        goto L29
    L27:
        r55.setImageOverlay(null, null, null);
        goto L29
    L18:
        r54 = null;
    L14:
        r32 = 0;
        goto L15
    L8:
        r0 = R.drawable.ic_ab_other;
        goto L9
    }

    public void updateButtonState(boolean r9, boolean r10) {
        String r0 = this.currentMessageObject.getFileName();
        File r2 = null;
        if (TextUtils.isEmpty(this.currentMessageObject.messageOwner.attachPath) == true) goto L8;
        File r1 = new File(this.currentMessageObject.messageOwner.attachPath);
        if (r1.exists() == false) goto L8;
        r2 = r1;
    L8:
        if (r2 != null) goto L11;
        r2 = FileLoader.getInstance(this.currentAccount).getPathToAttach(this.currentMessageObject.getDocument());
    L11:
        if (TextUtils.isEmpty(r0) == false) goto L14;
        return;
    L14:
        if (r2.exists() == true) goto L16;
    L18:
        boolean r12 = r2.exists();
        if (SharedConfig.streamMedia == true) goto L21;
    L26:
        this.miniButtonState = -1;
    L28:
        if (this.hasMiniProgress == 0) goto L55;
        RadialProgress2 r13 = this.radialProgress;
        if (this.currentMessageObject.isOutOwner() == false) goto L32;
        int r22 = Theme.key_chat_outLoader;
    L33:
        r13.setMiniProgressBackgroundColor(getThemedColor(r22));
        boolean r14 = MediaController.getInstance().isPlayingMessage(this.currentMessageObject);
        if (r14 == false) goto L40;
        if (r14 == true) goto L37;
    L39:
        this.buttonState = 1;
    L41:
        this.radialProgress.setIcon(getIconForCurrentState(), r9, r10);
        int r15 = this.hasMiniProgress;
        int r23 = this.currentAccount;
        if (r15 != 1) goto L45;
        DownloadController.getInstance(r23).removeLoadingFileObserver(this);
        this.miniButtonState = -1;
        this.radialProgress.setMiniIcon(getMiniIconForCurrentState(), r9, r10);
        return;
    L45:
        DownloadController.getInstance(r23).addLoadingFileObserver(r0, this.currentMessageObject, this);
        if (FileLoader.getInstance(this.currentAccount).isLoadingFile(r0) == true) goto L49;
        this.miniButtonState = 0;
        this.radialProgress.setMiniIcon(getMiniIconForCurrentState(), r9, r10);
        return;
    L49:
        this.miniButtonState = 1;
        this.radialProgress.setMiniIcon(getMiniIconForCurrentState(), r9, r10);
        Float r92 = ImageLoader.getInstance().getFileProgress(r0);
        RadialProgress2 r8 = this.radialProgress;
        if (r92 == null) goto L53;
        r8.setProgress(r92.floatValue(), r10);
        return;
    L53:
        r8.setProgress(0.0f, r10);
        return;
    L37:
        if (MediaController.getInstance().isMessagePaused() == false) goto L39;
    L40:
        this.buttonState = 0;
        goto L41
    L32:
        r22 = Theme.key_chat_inLoader;
        goto L33
    L55:
        int r24 = this.currentAccount;
        if (r12 == false) goto L67;
        DownloadController.getInstance(r24).removeLoadingFileObserver(this);
        boolean r02 = MediaController.getInstance().isPlayingMessage(this.currentMessageObject);
        if (r02 == false) goto L64;
        if (r02 == true) goto L61;
    L63:
        this.buttonState = 1;
    L65:
        this.radialProgress.setProgress(1.0f, r10);
        this.radialProgress.setIcon(getIconForCurrentState(), r9, r10);
        invalidate();
        return;
    L61:
        if (MediaController.getInstance().isMessagePaused() == false) goto L63;
    L64:
        this.buttonState = 0;
        goto L65
    L67:
        DownloadController.getInstance(r24).addLoadingFileObserver(r0, this);
        if (FileLoader.getInstance(this.currentAccount).isLoadingFile(r0) == true) goto L70;
        this.buttonState = 2;
        this.radialProgress.setIcon(getIconForCurrentState(), r9, r10);
    L75:
        invalidate();
        return;
    L70:
        this.buttonState = 4;
        Float r03 = ImageLoader.getInstance().getFileProgress(r0);
        RadialProgress2 r16 = this.radialProgress;
        if (r03 == null) goto L73;
        r16.setProgress(r03.floatValue(), r10);
    L74:
        this.radialProgress.setIcon(getIconForCurrentState(), r9, r10);
        goto L75
    L73:
        r16.setProgress(0.0f, r10);
        goto L74
    L21:
        if (((int) this.currentMessageObject.getDialogId()) == 0) goto L26;
        if (r12 == false) goto L24;
        int r17 = 1;
    L25:
        this.hasMiniProgress = r17;
        r12 = true;
        goto L28
    L24:
        r17 = 2;
        goto L25
    L16:
        if (r2.length() != 0) goto L18;
        r2.delete();
        goto L18
    }
}
