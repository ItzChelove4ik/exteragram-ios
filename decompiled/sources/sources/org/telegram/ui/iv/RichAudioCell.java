package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgress2;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SeekBar;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.iv.RichCaptionController;
import org.telegram.ui.iv.RichEditor;

/* loaded from: classes7.dex */
public class RichAudioCell extends RichBlockCell implements Theme.Colorable, TextSelectionHelper.ArticleSelectableView, RichCaptionHost, NotificationCenter.NotificationCenterDelegate, DownloadController.FileDownloadProgressListener {
    private boolean attached;
    private final TextPaint audioTimePaint;
    private boolean blockRtl;
    private TLRPC.Document boundDocument;
    private boolean buttonPressed;
    private int buttonState;
    private int buttonX;
    private final int buttonY;
    private final RichCaptionController caption;
    private final int currentAccount;
    private Delegate delegate;
    private StaticLayout durationLayout;
    private String lastTimeString;
    private MessageObject messageObject;
    private final int observerTag;
    private final RadialProgress2 radialProgress;
    private final Theme.ResourcesProvider resourcesProvider;
    private final SeekBar seekBar;
    private int seekBarWidth;
    private int seekBarX;
    private int seekBarY;
    private final Paint selectionPaint;
    private final int size;
    private StaticLayout titleLayout;

    /* renamed from: org.telegram.ui.iv.RichAudioCell$1, reason: invalid class name */
    public class AnonymousClass1 implements SeekBar.SeekBarDelegate {
        final /* synthetic */ RichAudioCell this$0;

        public AnonymousClass1(RichAudioCell r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SeekBar.SeekBarDelegate
        public void onSeekBarContinuousDrag(float r2) {
            if (RichAudioCell.m21628$$Nest$fgetmessageObject(this.this$0) != null) goto L5;
            return;
        L5:
            RichAudioCell.m21628$$Nest$fgetmessageObject(this.this$0).audioProgress = r2;
        }

        @Override // org.telegram.ui.Components.SeekBar.SeekBarDelegate
        public void onSeekBarDrag(float r2) {
            if (RichAudioCell.m21628$$Nest$fgetmessageObject(this.this$0) != null) goto L5;
            return;
        L5:
            RichAudioCell.m21628$$Nest$fgetmessageObject(this.this$0).audioProgress = r2;
            MediaController.getInstance().seekToProgress(RichAudioCell.m21628$$Nest$fgetmessageObject(this.this$0), r2);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichAudioCell$2, reason: invalid class name */
    public class AnonymousClass2 implements RichCaptionController.Host {
        final /* synthetic */ RichAudioCell this$0;

        public AnonymousClass2(RichAudioCell r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public TextSelectionHelper.ArticleSelectableView cell() {
            return this.this$0;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public BlockRow currentRow() {
            return this.this$0.currentRow;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionChanged() {
            if (RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0).onCaptionChanged(this.this$0.currentRow);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionEnter() {
            if (RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0).onCaptionEnter(this.this$0.currentRow);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionLockedInsert(CharSequence r2) {
            if (RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0).onCaptionLockedInsert(r2);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public boolean onCaptionSelectAll() {
            if (RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            if (RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0).onCaptionSelectAll(this.this$0.currentRow) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionSpansChanged() {
            if (RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0).onCaptionSpansChanged(this.this$0.currentRow);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionWillChange(int r2, int r3) {
            if (RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0).onCaptionWillChange(this.this$0.currentRow, r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            if (RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0).onRequestWindowFocusable(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public TextSelectionHelper.ArticleTextSelectionHelper selectionHelper() {
            if (RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return null;
        L5:
            return RichAudioCell.m21627$$Nest$fgetdelegate(this.this$0).getSelectionHelper();
        }
    }

    public interface Delegate {
        TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper();

        void onCancelUpload(BlockRow r1);

        void onCaptionChanged(BlockRow r1);

        void onCaptionEnter(BlockRow r1);

        void onCaptionLockedInsert(CharSequence r1);

        boolean onCaptionSelectAll(BlockRow r1);

        void onCaptionSpansChanged(BlockRow r1);

        void onCaptionWillChange(BlockRow r1, int r2, int r3);

        void onRequestWindowFocusable(RichEditText r1, boolean r2);
    }

    public static final class Factory extends UItem.UItemFactory<RichAudioCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem of(BlockRow r1, Delegate r2) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            r0.object2 = r2;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            ((RichAudioCell) r1).bind((BlockRow) r2.object, (Delegate) r2.object2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public RichAudioCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            RichAudioCell r0 = new RichAudioCell(r1, r3, r5);
            r0.setBackground(new RichEditor.DraggingDrawable(Theme.getColor(Theme.key_windowBackgroundWhite, r5)));
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m21627$$Nest$fgetdelegate(RichAudioCell r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetmessageObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m21628$$Nest$fgetmessageObject(RichAudioCell r0) {
        return r0.messageObject;
    }

    public RichAudioCell(Context r5, int r6, Theme.ResourcesProvider r7) {
        super(r5);
        this.selectionPaint = new Paint(1);
        TextPaint r0 = new TextPaint(1);
        this.audioTimePaint = r0;
        this.buttonX = AndroidUtilities.dp(16.0f);
        int r1 = AndroidUtilities.dp(10.0f);
        this.buttonY = r1;
        int r2 = AndroidUtilities.dp(44.0f);
        this.size = r2;
        this.currentAccount = r6;
        this.resourcesProvider = r7;
        setWillNotDraw(false);
        this.observerTag = DownloadController.getInstance(r6).generateObserverTag();
        r0.setTypeface(AndroidUtilities.regular());
        RadialProgress2 r62 = new RadialProgress2(this, r7);
        this.radialProgress = r62;
        r62.setCircleRadius(AndroidUtilities.dp(24.0f));
        int r02 = this.buttonX;
        r62.setProgressRect(r02, r1, r02 + r2, r2 + r1);
        SeekBar r63 = new SeekBar(this);
        this.seekBar = r63;
        r63.setDelegate(new AnonymousClass1(this));
        setMinimumHeight(AndroidUtilities.dp(66.0f));
        RichCaptionController r64 = new RichCaptionController(r5, r7, new AnonymousClass2(this));
        this.caption = r64;
        addView(r64.editText, LayoutHelper.createFrame(-2, -2, 51));
        updateColors();
    }

    private TLRPC.TL_documentAttributeAudio attribute() {
        TLRPC.Document r3 = getDisplayDocument();
        if (r3 != null) goto L5;
        return null;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r3.attributes.size()) goto L13;
        if ((r3.attributes.get(r1) instanceof TLRPC.TL_documentAttributeAudio) == true) goto L11;
        r1 = r1 + 1;
        goto L7
    L11:
        return (TLRPC.TL_documentAttributeAudio) r3.attributes.get(r1);
    L13:
        return null;
    }

    private String audioAuthor() {
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L7;
        return r0.getMusicAuthor(false);
    L7:
        if (attribute() != null) goto L9;
        return null;
    L9:
        return attribute().performer;
    }

    private int audioDuration() {
        if (this.messageObject != null) goto L5;
    L8:
        TLRPC.TL_documentAttributeAudio r2 = attribute();
        if (r2 != null) goto L11;
        return 0;
    L11:
        return (int) r2.duration;
    L5:
        if (MediaController.getInstance().isPlayingMessage(this.messageObject) == false) goto L8;
        return this.messageObject.audioProgressSec;
    }

    private String audioTitle() {
        MessageObject r0 = this.messageObject;
        if (r0 == null) goto L7;
        return r0.getMusicTitle(false);
    L7:
        if (attribute() != null) goto L9;
        return null;
    L9:
        return attribute().title;
    }

    private MessageObject buildMessageObject(TLRPC.Document r7) {
        TLRPC.TL_message r0 = new TLRPC.TL_message();
        r0.out = true;
        r0.id = -Long.valueOf(r7.id).hashCode();
        r0.peer_id = new TLRPC.TL_peerUser();
        TLRPC.TL_peerUser r2 = new TLRPC.TL_peerUser();
        r0.from_id = r2;
        TLRPC.Peer r3 = r0.peer_id;
        long r4 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        r3.user_id = r4;
        r2.user_id = r4;
        r0.date = (int) (System.currentTimeMillis() / 1000);
        r0.message = _UrlKt.FRAGMENT_ENCODE_SET;
        TLRPC.TL_messageMediaDocument r22 = new TLRPC.TL_messageMediaDocument();
        r0.media = r22;
        r22.flags |= 3;
        r22.document = r7;
        r0.flags |= 768;
        BlockRow r72 = this.currentRow;
        if (r72 == null) goto L10;
        MediaUploadState r73 = r72.media;
        if (r73 == null) goto L10;
        if (TextUtils.isEmpty(r73.localPath) == true) goto L10;
        r0.attachPath = this.currentRow.media.localPath;
    L10:
        return new MessageObject(this.currentAccount, r0, false, true);
    }

    private void didPressedButton(boolean r12) {
        if (isUploading() == false) goto L9;
        Delegate r122 = this.delegate;
        if (r122 == null) goto L33;
        r122.onCancelUpload(this.currentRow);
        return;
    L33:
        return;
    L9:
        if (this.messageObject != null) goto L12;
        return;
    L12:
        if (isReady() == false) goto L14;
        TLRPC.Document r0 = this.currentRow.media.document;
    L15:
        int r1 = this.buttonState;
        if (r1 != 0) goto L21;
        ArrayList<MessageObject> r5 = new ArrayList();
        r5.add(this.messageObject);
        if (MediaController.getInstance().setPlaylist(r5, this.messageObject, 0, false, null) == false) goto L34;
        this.buttonState = 1;
        this.radialProgress.setIcon(getIconForCurrentState(), false, r12);
        invalidate();
        return;
    L34:
        return;
    L21:
        if (r1 != 1) goto L27;
        if (MediaController.getInstance().pauseMessage(this.messageObject) == false) goto L35;
        this.buttonState = 0;
        this.radialProgress.setIcon(getIconForCurrentState(), false, r12);
        invalidate();
        return;
    L35:
        return;
    L27:
        if (r1 != 2) goto L30;
        this.radialProgress.setProgress(0.0f, false);
        FileLoader.getInstance(this.currentAccount).loadFile(r0, this.messageObject, 1, 1);
        this.buttonState = 3;
        this.radialProgress.setIcon(getIconForCurrentState(), true, r12);
        invalidate();
        return;
    L30:
        if (r1 != 3) goto L36;
        FileLoader.getInstance(this.currentAccount).cancelLoadFile(r0);
        this.buttonState = 2;
        this.radialProgress.setIcon(getIconForCurrentState(), false, r12);
        invalidate();
        return;
    L36:
        return;
    L14:
        r0 = null;
        goto L15
    }

    private TLRPC.Document getDisplayDocument() {
        BlockRow r1 = this.currentRow;
        if (r1 == null) goto L12;
        MediaUploadState r12 = r1.media;
        if (r12 == null) goto L14;
        TLRPC.Document r0 = r12.document;
        if (r0 == null) goto L11;
        return r0;
    L11:
        return r12.audioDisplayDocument;
    L14:
        return null;
    L12:
        return null;
    }

    private int getIconForCurrentState() {
        if (isUploading() == false) goto L5;
        return 3;
    L5:
        int r2 = this.buttonState;
        if (r2 != 1) goto L9;
        return 1;
    L9:
        if (r2 != 2) goto L11;
        return 2;
    L11:
        if (r2 != 3) goto L13;
        return 3;
    L13:
        return 0;
    }

    private boolean isCellSelected() {
        Delegate r0 = this.delegate;
        if (r0 != null) goto L5;
        return false;
    L5:
        TextSelectionHelper.ArticleTextSelectionHelper r02 = r0.getSelectionHelper();
        if (r02 != null) goto L8;
    L22:
        return false;
    L8:
        if (r02.isInSelectionMode() == false) goto L22;
        if ((getParent() instanceof RecyclerView) == true) goto L13;
        return false;
    L13:
        int r3 = ((RecyclerView) getParent()).getChildAdapterPosition(this);
        if (r3 >= 0) goto L17;
        return false;
    L17:
        if (r3 <= r02.getStartCell()) goto L22;
        if (r3 > r02.getEndCell()) goto L22;
        return true;
    }

    private boolean isReady() {
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L10;
        MediaUploadState r02 = r0.media;
        if (r02 != null) goto L7;
        return false;
    L7:
        if (r02.isReady() == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    private boolean isUploading() {
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L10;
        MediaUploadState r02 = r0.media;
        if (r02 != null) goto L7;
        return false;
    L7:
        if (r02.isPending() == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    private void layoutInner() {
        this.seekBarX = (this.buttonX + AndroidUtilities.dp(50.0f)) + this.size;
        if (getMeasuredWidth() <= 0) goto L5;
        int r0 = getMeasuredWidth();
    L7:
        if (this.blockRtl == false) goto L9;
        int r2 = blockInset();
    L10:
        this.seekBarWidth = Math.max(0, ((r0 - this.seekBarX) - AndroidUtilities.dp(16.0f)) - r2);
        String r02 = audioAuthor();
        String r22 = audioTitle();
        if (TextUtils.isEmpty(r22) == false) goto L17;
        if (TextUtils.isEmpty(r02) == false) goto L17;
        this.titleLayout = null;
        this.seekBarY = this.buttonY + ((this.size - AndroidUtilities.dp(30.0f)) / 2);
    L29:
        this.seekBar.setSize(this.seekBarWidth, AndroidUtilities.dp(30.0f));
        return;
    L17:
        if (TextUtils.isEmpty(r22) == true) goto L22;
        if (TextUtils.isEmpty(r02) == true) goto L22;
        SpannableStringBuilder r5 = new SpannableStringBuilder(String.format("%s - %s", new Object[]{r02, r22}));
    L26:
        if (TextUtils.isEmpty(r02) == true) goto L28;
        r5.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r02.length(), 18);
    L28:
        this.audioTimePaint.setTextSize(AndroidUtilities.dp(16.0f));
        this.titleLayout = new StaticLayout(TextUtils.ellipsize(r5, this.audioTimePaint, this.seekBarWidth, TextUtils.TruncateAt.END), this.audioTimePaint, this.seekBarWidth + AndroidUtilities.dp(50.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.seekBarY = (this.buttonY + ((this.size - AndroidUtilities.dp(30.0f)) / 2)) + AndroidUtilities.dp(11.0f);
    L22:
        if (TextUtils.isEmpty(r22) == true) goto L24;
        r5 = new SpannableStringBuilder(r22);
        goto L26
    L24:
        r5 = new SpannableStringBuilder(r02);
        goto L26
    L9:
        r2 = 0;
        goto L10
    L5:
        r0 = AndroidUtilities.displaySize.x;
        goto L7
    }

    private void rebuildFromRow() {
        TLRPC.Document r0 = getDisplayDocument();
        if (r0 == this.boundDocument) goto L6;
        this.boundDocument = r0;
        this.messageObject = null;
        this.lastTimeString = null;
        this.durationLayout = null;
    L6:
        if (isReady() == true) goto L8;
    L11:
        layoutInner();
        if (this.attached == false) goto L15;
        updateButtonState(false);
        return;
    L15:
        return;
    L8:
        if (this.messageObject != null) goto L11;
        if (r0 == null) goto L11;
        this.messageObject = buildMessageObject(r0);
        goto L11
    }

    public void bind(BlockRow r1, Delegate r2) {
        this.currentRow = r1;
        this.delegate = r2;
        if (r1 != null) goto L5;
    L7:
        this.blockRtl = RichBlockChrome.rtl();
        bindBlockInset(r1);
        this.caption.bind();
        rebuildFromRow();
        requestLayout();
        invalidate();
        return;
    L5:
        if (r1.media != null) goto L7;
        r1.media = new MediaUploadState();
        goto L7
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        MessageObject r0 = this.messageObject;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r4 == this.currentAccount) goto L8;
        return;
    L8:
        if (r3 != NotificationCenter.messagePlayingDidStart) goto L10;
    L22:
        updateButtonState(true);
        return;
    L10:
        if (r3 == NotificationCenter.messagePlayingDidReset) goto L22;
        if (r3 == NotificationCenter.messagePlayingPlayStateChanged) goto L22;
        if (r3 != NotificationCenter.messagePlayingProgressDidChanged) goto L25;
        Integer r32 = (Integer) r5[0];
        if (r0.getId() != r32.intValue()) goto L26;
        MessageObject r33 = MediaController.getInstance().getPlayingMessageObject();
        if (r33 == null) goto L27;
        MessageObject r42 = this.messageObject;
        r42.audioProgress = r33.audioProgress;
        r42.audioProgressSec = r33.audioProgressSec;
        r42.audioPlayerDuration = r33.audioPlayerDuration;
        updatePlayingMessageProgress();
        return;
    L27:
        return;
    L26:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r1) {
        super.dispatchDraw(r1);
        this.caption.drawSelection(r1);
    }

    @Override // org.telegram.ui.Cells.TextSelectionHelper.ArticleSelectableView
    public void fillTextLayoutBlocks(ArrayList<TextSelectionHelper.TextLayoutBlock> r1) {
        this.caption.fillTextLayoutBlocks(r1);
    }

    @Override // org.telegram.ui.iv.RichCaptionHost
    public RichEditText getCaptionEditText() {
        return this.caption.editText;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return this.observerTag;
    }

    @Override // org.telegram.ui.iv.RichCaptionHost
    public BlockRow getRow() {
        return this.currentRow;
    }

    @Override // org.telegram.ui.iv.RichCaptionHost
    public boolean isPressOnCaption(int r1, int r2) {
        return this.caption.isPressOnCaption(r1, r2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
        this.radialProgress.setParent(this);
        this.seekBar.setParent(this);
        updateButtonState(false);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingDidStart);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
    }

    @Override // org.telegram.ui.iv.RichBlockCell
    public void onBlockInsetChanged(int r5) {
        int r0 = AndroidUtilities.dp(16.0f);
        if (this.blockRtl == false) goto L5;
        r5 = 0;
    L5:
        int r02 = r0 + r5;
        this.buttonX = r02;
        RadialProgress2 r52 = this.radialProgress;
        int r1 = this.buttonY;
        int r2 = this.size;
        r52.setProgressRect(r02, r1, r02 + r2, r2 + r1);
        requestLayout();
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingDidStart);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
    }

    @Override // android.view.View
    public void onDraw(Canvas r12) {
        if (getDisplayDocument() == null) goto L26;
        this.radialProgress.draw(r12);
        SeekBar r1 = this.seekBar;
        int r2 = Theme.getColor(Theme.key_chat_inAudioSeekbar, this.resourcesProvider);
        int r3 = Theme.getColor(Theme.key_chat_inAudioCacheSeekbar, this.resourcesProvider);
        int r0 = Theme.key_chat_inAudioSeekbarFill;
        r1.setColors(r2, r3, Theme.getColor(r0, this.resourcesProvider), Theme.getColor(r0, this.resourcesProvider), Theme.getColor(Theme.key_chat_inAudioSeekbarSelected, this.resourcesProvider));
        if (isUploading() == true) goto L8;
        r12.save();
        r12.translate(this.seekBarX, this.seekBarY);
        this.seekBar.draw(r12);
        r12.restore();
    L8:
        this.audioTimePaint.setColor(Theme.getColor(Theme.key_chat_inTimeText, this.resourcesProvider));
        if (this.durationLayout == null) goto L12;
        r12.save();
        r12.translate(this.buttonX + AndroidUtilities.dp(54.0f), this.seekBarY + AndroidUtilities.dp(6.0f));
        this.durationLayout.draw(r12);
        r12.restore();
    L12:
        if (this.titleLayout == null) goto L15;
        this.audioTimePaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        r12.save();
        r12.translate(this.buttonX + AndroidUtilities.dp(54.0f), this.seekBarY - AndroidUtilities.dp(16.0f));
        this.titleLayout.draw(r12);
        r12.restore();
    L15:
        if (isCellSelected() == false) goto L25;
        int r13 = 0;
        if (this.blockRtl == false) goto L19;
        int r02 = 0;
    L20:
        float r4 = r02 + AndroidUtilities.dp(8.0f);
        float r5 = AndroidUtilities.dp(2.0f);
        int r03 = getWidth();
        if (this.blockRtl == false) goto L23;
        r13 = blockInset();
    L23:
        r12.drawRoundRect(r4, r5, (r03 - r13) - AndroidUtilities.dp(8.0f), AndroidUtilities.dp(64.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.selectionPaint);
        return;
    L19:
        r02 = blockInset();
        goto L20
    L25:
        return;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r1, boolean r2) {
        updateButtonState(true);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        RichCaptionController r22 = this.caption;
        int r62 = 0;
        if (this.blockRtl == false) goto L5;
        int r42 = 0;
    L7:
        if (this.blockRtl == false) goto L9;
        r62 = blockInset();
    L9:
        r22.layout(r42, r62, r5 - r3, AndroidUtilities.dp(66.0f));
        layoutInner();
        return;
    L5:
        r42 = blockInset();
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        int r42 = View.MeasureSpec.getSize(r4);
        RichCaptionController r52 = this.caption;
        int r1 = 0;
        if (this.blockRtl == false) goto L5;
        int r0 = 0;
    L7:
        if (this.blockRtl == false) goto L9;
        r1 = blockInset();
    L9:
        setMeasuredDimension(r42, AndroidUtilities.dp(66.0f) + r52.measure(r0, r1, r42));
        return;
    L5:
        r0 = blockInset();
        goto L7
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r3, long r4, long r6) {
        RadialProgress2 r32 = this.radialProgress;
        if (r6 > 0) goto L5;
        float r42 = 0.0f;
    L6:
        r32.setProgress(Math.min(1.0f, r42), true);
        if (this.buttonState == 3) goto L10;
        updateButtonState(true);
        return;
    L10:
        return;
    L5:
        r42 = r4 / r6;
        goto L6
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onSuccessDownload(String r3) {
        this.radialProgress.setProgress(1.0f, true);
        updateButtonState(true);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        int r0 = r8.getActionMasked();
        float r1 = r8.getX();
        float r2 = r8.getY();
        if (isUploading() == true) goto L11;
        if (this.seekBar.onTouch(r0, r1 - this.seekBarX, r2 - this.seekBarY) == false) goto L11;
        if (r0 != 0) goto L8;
        getParent().requestDisallowInterceptTouchEvent(true);
    L8:
        invalidate();
        return true;
    L11:
        if (r0 == 0) goto L13;
        if (r0 != 1) goto L28;
        if (this.buttonPressed == false) goto L31;
        this.buttonPressed = false;
        playSoundEffect(0);
        didPressedButton(true);
        invalidate();
        return true;
    L31:
        if (this.buttonPressed == false) goto L33;
    L36:
        return true;
    L33:
        if (super.onTouchEvent(r8) == true) goto L36;
        return false;
    L28:
        if (r0 != 3) goto L31;
        this.buttonPressed = false;
        goto L31
    L13:
        if (r1 < this.buttonX) goto L31;
        int r5 = this.size;
        if (r1 > (r0 + r5)) goto L31;
        if (r2 < this.buttonY) goto L31;
        if (r2 > (r0 + r5)) goto L31;
        this.buttonPressed = true;
        invalidate();
        return true;
    }

    @Override // org.telegram.ui.iv.RichCaptionHost
    public void persistCaption() {
        this.caption.persist();
    }

    public void updateButtonState(boolean r9) {
        this.radialProgress.setColorKeys(Theme.key_chat_inLoader, Theme.key_chat_inLoaderSelected, Theme.key_chat_inMediaIcon, Theme.key_chat_inMediaIconSelected);
        this.radialProgress.setProgressColor(Theme.getColor(Theme.key_chat_inFileProgress, this.resourcesProvider));
        if (isUploading() == false) goto L7;
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        this.radialProgress.setProgress(this.currentRow.media.progress, r9);
        this.radialProgress.setIcon(3, false, r9);
        updatePlayingMessageProgress();
        return;
    L7:
        if (isReady() == false) goto L9;
        TLRPC.Document r0 = this.currentRow.media.document;
    L10:
        String r4 = FileLoader.getAttachFileName(r0);
        BlockRow r5 = this.currentRow;
        int r6 = 1;
        if (r5 == null) goto L19;
        MediaUploadState r52 = r5.media;
        if (r52 == null) goto L19;
        if (TextUtils.isEmpty(r52.localPath) == true) goto L19;
        if (new File(this.currentRow.media.localPath).exists() == false) goto L19;
        boolean r53 = true;
    L20:
        if (r0 != null) goto L22;
        File r02 = null;
    L23:
        if (r53 == true) goto L29;
        if (r02 != null) goto L26;
    L28:
        boolean r03 = false;
    L31:
        if (TextUtils.isEmpty(r4) == false) goto L34;
        this.radialProgress.setIcon(4, false, false);
        return;
    L34:
        int r54 = this.currentAccount;
        if (r03 == false) goto L42;
        DownloadController.getInstance(r54).removeLoadingFileObserver(this);
        if (MediaController.getInstance().isPlayingMessage(this.messageObject) == true) goto L39;
    L40:
        r6 = 0;
    L41:
        this.buttonState = r6;
        this.radialProgress.setIcon(getIconForCurrentState(), false, r9);
    L49:
        updatePlayingMessageProgress();
        return;
    L39:
        if (MediaController.getInstance().isMessagePaused() == false) goto L41;
    L42:
        DownloadController.getInstance(r54).addLoadingFileObserver(r4, null, this);
        float r3 = 0.0f;
        if (FileLoader.getInstance(this.currentAccount).isLoadingFile(r4) == true) goto L45;
        this.buttonState = 2;
        this.radialProgress.setProgress(0.0f, r9);
        this.radialProgress.setIcon(getIconForCurrentState(), false, r9);
        goto L49
    L45:
        this.buttonState = 3;
        Float r04 = ImageLoader.getInstance().getFileProgress(r4);
        RadialProgress2 r1 = this.radialProgress;
        if (r04 == null) goto L48;
        r3 = r04.floatValue();
    L48:
        r1.setProgress(r3, r9);
        this.radialProgress.setIcon(getIconForCurrentState(), true, r9);
        goto L49
    L26:
        if (r02.exists() == false) goto L28;
    L29:
        r03 = true;
        goto L31
    L22:
        r02 = FileLoader.getInstance(this.currentAccount).getPathToAttach(r0, true);
    L19:
        r53 = false;
        goto L20
    L9:
        r0 = null;
        goto L10
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.selectionPaint.setColor(Theme.getColor(Theme.key_chat_inTextSelectionHighlight, this.resourcesProvider));
        RichCaptionController r3 = this.caption;
        if (r3 == null) goto L6;
        r3.applyColors();
        return;
    }

    public void updatePlayingMessageProgress() {
        if (isUploading() == true) goto L10;
        if (this.messageObject == null) goto L10;
        if (this.seekBar.isDragging() == true) goto L10;
        this.seekBar.setProgress(this.messageObject.audioProgress);
    L10:
        if (isUploading() == true) goto L12;
        int r0 = audioDuration();
    L16:
        String r2 = AndroidUtilities.formatShortDuration(r0);
        String r02 = this.lastTimeString;
        if (r02 != null) goto L19;
    L20:
        this.lastTimeString = r2;
        this.audioTimePaint.setTextSize(AndroidUtilities.dp(16.0f));
        this.durationLayout = new StaticLayout(r2, this.audioTimePaint, (int) Math.ceil(this.audioTimePaint.measureText(r2)), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
    L21:
        invalidate();
        return;
    L19:
        if (r02.equals(r2) == true) goto L21;
    L12:
        if (attribute() == null) goto L14;
        r0 = (int) attribute().duration;
        goto L16
    L14:
        r0 = 0;
        goto L16
    }
}
