package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.BaseCell;
import org.telegram.ui.Components.SeekBar;

/* loaded from: classes7.dex */
public class PopupAudioView extends BaseCell implements SeekBar.SeekBarDelegate, DownloadController.FileDownloadProgressListener {
    private int TAG;
    private int buttonPressed;
    private int buttonState;
    private int buttonX;
    private int buttonY;
    private int currentAccount;
    protected MessageObject currentMessageObject;
    private String lastTimeString;
    private ProgressView progressView;
    private SeekBar seekBar;
    private int seekBarX;
    private int seekBarY;
    private StaticLayout timeLayout;
    private TextPaint timePaint;
    int timeWidth;
    private int timeX;
    private boolean wasLayout;

    public PopupAudioView(Context r2) {
        super(r2);
        this.wasLayout = false;
        this.buttonState = 0;
        this.buttonPressed = 0;
        this.timeWidth = 0;
        this.lastTimeString = null;
        TextPaint r22 = new TextPaint(1);
        this.timePaint = r22;
        r22.setTextSize(AndroidUtilities.dp(16.0f));
        this.TAG = DownloadController.getInstance(this.currentAccount).generateObserverTag();
        SeekBar r23 = new SeekBar(this);
        this.seekBar = r23;
        r23.setDelegate(this);
        this.progressView = new ProgressView();
    }

    private void didPressedButton() {
        int r0 = this.buttonState;
        if (r0 != 0) goto L15;
        boolean r02 = MediaController.getInstance().playMessage(this.currentMessageObject);
        if (this.currentMessageObject.isOut() == false) goto L7;
    L11:
        if (r02 == false) goto L28;
        this.buttonState = 1;
        invalidate();
        return;
    L28:
        return;
    L7:
        if (this.currentMessageObject.isContentUnread() == false) goto L11;
        if (this.currentMessageObject.messageOwner.peer_id.channel_id != 0) goto L11;
        MessagesController.getInstance(this.currentAccount).markMessageContentAsRead(this.currentMessageObject);
        this.currentMessageObject.setContentIsRead();
        goto L11
    L15:
        if (r0 != 1) goto L21;
        if (MediaController.getInstance().pauseMessage(this.currentMessageObject) == false) goto L29;
        this.buttonState = 0;
        invalidate();
        return;
    L29:
        return;
    L21:
        if (r0 != 2) goto L25;
        FileLoader.getInstance(this.currentAccount).loadFile(this.currentMessageObject.getDocument(), this.currentMessageObject, 1, 0);
        this.buttonState = 4;
        invalidate();
        return;
    L25:
        if (r0 != 3) goto L30;
        FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.currentMessageObject.getDocument());
        this.buttonState = 2;
        invalidate();
        return;
    }

    public void downloadAudioIfNeed() {
        if (this.buttonState != 2) goto L6;
        FileLoader.getInstance(this.currentAccount).loadFile(this.currentMessageObject.getDocument(), this.currentMessageObject, 1, 0);
        this.buttonState = 3;
        invalidate();
        return;
    }

    public final MessageObject getMessageObject() {
        return this.currentMessageObject;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return this.TAG;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        if (this.currentMessageObject != null) goto L6;
        return;
    L6:
        if (this.wasLayout == true) goto L9;
        requestLayout();
        return;
    L9:
        Point r0 = AndroidUtilities.displaySize;
        int r1 = r0.y;
        int r02 = r0.x;
        if ((getParent() instanceof View) == false) goto L12;
        View r03 = (View) getParent();
        int r12 = r03.getMeasuredWidth();
        int r5 = r03.getMeasuredHeight();
        int r4 = r12;
    L13:
        Theme.chat_msgInMediaDrawable.setTop((int) getY(), r4, r5, false, false);
        BaseCell.setDrawableBounds(Theme.chat_msgInMediaDrawable, 0, 0, getMeasuredWidth(), getMeasuredHeight());
        Theme.chat_msgInMediaDrawable.draw(r9);
        if (this.currentMessageObject != null) goto L16;
        return;
    L16:
        r9.save();
        int r04 = this.buttonState;
        if (r04 != 0) goto L19;
    L22:
        r9.translate(this.seekBarX, this.seekBarY);
        this.seekBar.draw(r9);
    L23:
        r9.restore();
        int r05 = this.buttonState;
        this.timePaint.setColor(-6182221);
        Drawable r06 = Theme.chat_fileStatesDrawable[r05][this.buttonPressed];
        int r13 = AndroidUtilities.dp(36.0f);
        BaseCell.setDrawableBounds(r06, ((r13 - r06.getIntrinsicWidth()) / 2) + this.buttonX, ((r13 - r06.getIntrinsicHeight()) / 2) + this.buttonY);
        r06.draw(r9);
        r9.save();
        r9.translate(this.timeX, AndroidUtilities.dp(18.0f));
        this.timeLayout.draw(r9);
        r9.restore();
        return;
    L19:
        if (r04 == 1) goto L22;
        r9.translate(this.seekBarX + AndroidUtilities.dp(12.0f), this.seekBarY);
        this.progressView.draw(r9);
        goto L23
    L12:
        r4 = r02;
        r5 = r1;
        goto L13
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r1, boolean r2) {
        updateButtonState();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        if (this.currentMessageObject == null) goto L13;
        this.seekBarX = AndroidUtilities.dp(54.0f);
        this.buttonX = AndroidUtilities.dp(10.0f);
        this.timeX = (getMeasuredWidth() - this.timeWidth) - AndroidUtilities.dp(16.0f);
        this.seekBar.setSize((getMeasuredWidth() - AndroidUtilities.dp(70.0f)) - this.timeWidth, AndroidUtilities.dp(30.0f));
        ProgressView r42 = this.progressView;
        r42.width = (getMeasuredWidth() - AndroidUtilities.dp(94.0f)) - this.timeWidth;
        ProgressView r43 = this.progressView;
        r43.height = AndroidUtilities.dp(30.0f);
        this.seekBarY = AndroidUtilities.dp(13.0f);
        this.buttonY = AndroidUtilities.dp(10.0f);
        updateProgress();
        if (r2 == false) goto L8;
    L11:
        this.wasLayout = true;
        return;
    L8:
        if (this.wasLayout == false) goto L11;
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r1, int r2) {
        setMeasuredDimension(View.MeasureSpec.getSize(r1), AndroidUtilities.dp(56.0f));
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r1, long r2, long r4) {
        this.progressView.setProgress(Math.min(1.0f, r2 / r4));
        if (this.buttonState == 3) goto L5;
        updateButtonState();
    L5:
        invalidate();
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
    }

    @Override // org.telegram.ui.Components.SeekBar.SeekBarDelegate
    public void onSeekBarDrag(float r2) {
        MessageObject r0 = this.currentMessageObject;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.audioProgress = r2;
        MediaController.getInstance().seekToProgress(this.currentMessageObject, r2);
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onSuccessDownload(String r1) {
        updateButtonState();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        float r0 = r8.getX();
        float r1 = r8.getY();
        boolean r2 = this.seekBar.onTouch(r8.getAction(), r8.getX() - this.seekBarX, r8.getY() - this.seekBarY);
        if (r2 == true) goto L5;
        int r4 = AndroidUtilities.dp(36.0f);
        if (r8.getAction() != 0) goto L21;
        if (r0 >= this.buttonX) goto L14;
    L39:
        if (r2 == false) goto L41;
        return r2;
    L41:
        return super.onTouchEvent(r8);
    L14:
        if (r0 > (r5 + r4)) goto L39;
        if (r1 < this.buttonY) goto L39;
        if (r1 > (r0 + r4)) goto L39;
        this.buttonPressed = 1;
        invalidate();
        r2 = true;
        goto L39
    L21:
        if (this.buttonPressed != 1) goto L39;
        if (r8.getAction() != 1) goto L26;
        this.buttonPressed = 0;
        playSoundEffect(0);
        didPressedButton();
        invalidate();
        goto L39
    L26:
        if (r8.getAction() != 3) goto L29;
        this.buttonPressed = 0;
        invalidate();
        goto L39
    L29:
        if (r8.getAction() != 2) goto L39;
        if (r0 >= this.buttonX) goto L33;
    L38:
        this.buttonPressed = 0;
        invalidate();
        goto L39
    L33:
        if (r0 > (r3 + r4)) goto L38;
        if (r1 < this.buttonY) goto L38;
        if (r1 <= (r0 + r4)) goto L39;
    L5:
        if (r8.getAction() != 0) goto L7;
        getParent().requestDisallowInterceptTouchEvent(true);
    L7:
        invalidate();
        return r2;
    }

    public void setMessageObject(MessageObject r8) {
        if (this.currentMessageObject == r8) goto L5;
        this.currentAccount = r8.currentAccount;
        SeekBar r1 = this.seekBar;
        int r0 = Theme.key_chat_inAudioSeekbar;
        int r2 = Theme.getColor(r0);
        int r3 = Theme.getColor(r0);
        int r02 = Theme.key_chat_inAudioSeekbarFill;
        r1.setColors(r2, r3, Theme.getColor(r02), Theme.getColor(r02), Theme.getColor(Theme.key_chat_inAudioSeekbarSelected));
        this.progressView.setProgressColors(-2497813, -7944712);
        this.currentMessageObject = r8;
        this.wasLayout = false;
        requestLayout();
    L5:
        updateButtonState();
    }

    public void updateButtonState() {
        String r0 = this.currentMessageObject.getFileName();
        boolean r1 = FileLoader.getInstance(this.currentAccount).getPathToMessage(this.currentMessageObject.messageOwner).exists();
        int r2 = this.currentAccount;
        if (r1 == false) goto L13;
        DownloadController.getInstance(r2).removeLoadingFileObserver(this);
        boolean r02 = MediaController.getInstance().isPlayingMessage(this.currentMessageObject);
        if (r02 == false) goto L11;
        if (r02 == true) goto L8;
    L10:
        this.buttonState = 1;
    L12:
        this.progressView.setProgress(0.0f);
    L20:
        updateProgress();
        return;
    L8:
        if (MediaController.getInstance().isMessagePaused() == false) goto L10;
    L11:
        this.buttonState = 0;
        goto L12
    L13:
        DownloadController.getInstance(r2).addLoadingFileObserver(r0, this);
        if (FileLoader.getInstance(this.currentAccount).isLoadingFile(r0) == true) goto L16;
        this.buttonState = 2;
        this.progressView.setProgress(0.0f);
        goto L20
    L16:
        this.buttonState = 3;
        Float r03 = ImageLoader.getInstance().getFileProgress(r0);
        ProgressView r12 = this.progressView;
        if (r03 == null) goto L19;
        r12.setProgress(r03.floatValue());
        goto L20
    L19:
        r12.setProgress(0.0f);
        goto L20
    }

    public void updateProgress() {
        if (this.currentMessageObject != null) goto L6;
        return;
    L6:
        if (this.seekBar.isDragging() == true) goto L9;
        this.seekBar.setProgress(this.currentMessageObject.audioProgress);
    L9:
        if (MediaController.getInstance().isPlayingMessage(this.currentMessageObject) == true) goto L17;
        int r0 = 0;
        int r1 = 0;
    L12:
        if (r1 >= this.currentMessageObject.getDocument().attributes.size()) goto L18;
        TLRPC.DocumentAttribute r2 = this.currentMessageObject.getDocument().attributes.get(r1);
        if ((r2 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L15;
        r1 = r1 + 1;
        goto L12
    L15:
        r0 = (int) r2.duration;
    L18:
        String r22 = AndroidUtilities.formatLongDuration(r0);
        String r02 = this.lastTimeString;
        if (r02 == null) goto L23;
        if (r02 != null) goto L22;
    L24:
        invalidate();
        return;
    L22:
        if (r02.equals(r22) == true) goto L24;
    L23:
        this.timeWidth = (int) Math.ceil(this.timePaint.measureText(r22));
        this.timeLayout = new StaticLayout(r22, this.timePaint, this.timeWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        goto L24
    L17:
        r0 = this.currentMessageObject.audioProgressSec;
        goto L18
    }
}
