package org.telegram.ui.iv;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Layout;
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
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgress2;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.iv.RichCaptionController;
import org.telegram.ui.iv.RichEditor;

/* loaded from: classes7.dex */
public class RichDocumentCell extends RichBlockCell implements Theme.Colorable, TextSelectionHelper.ArticleSelectableView, RichCaptionHost, DownloadController.FileDownloadProgressListener {
    private boolean attached;
    private boolean blockRtl;
    private TLRPC.Document boundDocument;
    private final int buttonSize;
    private int buttonState;
    private int buttonX;
    private final int buttonY;
    private final RichCaptionController caption;
    private final int currentAccount;
    private Delegate delegate;
    private boolean hasPreview;
    private int mediaX;
    private MessageObject messageObject;
    private final int observerTag;
    private boolean pressed;
    private final Paint previewBackgroundPaint;
    private final ImageReceiver previewImage;
    private final RadialProgress2 radialProgress;
    private final Theme.ResourcesProvider resourcesProvider;
    private final Paint selectionPaint;
    private StaticLayout sizeLayout;
    private final TextPaint sizePaint;
    private final TextPaint textPaint;
    private StaticLayout titleLayout;

    /* renamed from: org.telegram.ui.iv.RichDocumentCell$1, reason: invalid class name */
    public class AnonymousClass1 implements RichCaptionController.Host {
        final /* synthetic */ RichDocumentCell this$0;

        public AnonymousClass1(RichDocumentCell r1) {
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
            if (RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0).onCaptionChanged(this.this$0.currentRow);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionEnter() {
            if (RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0).onCaptionEnter(this.this$0.currentRow);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionLockedInsert(CharSequence r2) {
            if (RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0).onCaptionLockedInsert(r2);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public boolean onCaptionSelectAll() {
            if (RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            if (RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0).onCaptionSelectAll(this.this$0.currentRow) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionSpansChanged() {
            if (RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0).onCaptionSpansChanged(this.this$0.currentRow);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionWillChange(int r2, int r3) {
            if (RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0).onCaptionWillChange(this.this$0.currentRow, r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            if (RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0).onRequestWindowFocusable(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public TextSelectionHelper.ArticleTextSelectionHelper selectionHelper() {
            if (RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0) != null) goto L7;
            return null;
        L7:
            return RichDocumentCell.m21644$$Nest$fgetdelegate(this.this$0).getSelectionHelper();
        }
    }

    public interface Delegate {
        MessageObject getFileRefParentObject();

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

    public static final class Factory extends UItem.UItemFactory<RichDocumentCell> {
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
            ((RichDocumentCell) r1).bind((BlockRow) r2.object, (Delegate) r2.object2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public RichDocumentCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            RichDocumentCell r0 = new RichDocumentCell(r1, r3, r5);
            r0.setBackground(new RichEditor.DraggingDrawable(Theme.getColor(Theme.key_windowBackgroundWhite, r5)));
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m21644$$Nest$fgetdelegate(RichDocumentCell r0) {
        return r0.delegate;
    }

    public RichDocumentCell(Context r6, int r7, Theme.ResourcesProvider r8) {
        super(r6);
        this.selectionPaint = new Paint(1);
        this.previewBackgroundPaint = new Paint(1);
        this.textPaint = new TextPaint(1);
        this.sizePaint = new TextPaint(1);
        int r0 = AndroidUtilities.dp(10.0f);
        this.buttonY = r0;
        int r2 = AndroidUtilities.dp(44.0f);
        this.buttonSize = r2;
        this.buttonX = AndroidUtilities.dp(16.0f);
        this.mediaX = AndroidUtilities.dp(16.0f);
        this.currentAccount = r7;
        this.resourcesProvider = r8;
        setWillNotDraw(false);
        setMinimumHeight(AndroidUtilities.dp(66.0f));
        this.observerTag = DownloadController.getInstance(r7).generateObserverTag();
        RadialProgress2 r72 = new RadialProgress2(this, r8);
        this.radialProgress = r72;
        r72.setCircleRadius(AndroidUtilities.dp(24.0f));
        int r3 = this.buttonX;
        r72.setProgressRect(r3, r0, r3 + r2, r2 + r0);
        ImageReceiver r73 = new ImageReceiver(this);
        this.previewImage = r73;
        r73.setAllowLoadingOnAttachedOnly(true);
        r73.setRoundRadius(AndroidUtilities.dp(6.0f));
        RichCaptionController r74 = new RichCaptionController(r6, r8, new AnonymousClass1(this));
        this.caption = r74;
        addView(r74.editText, LayoutHelper.createFrame(-2, -2, 51));
        updateColors();
    }

    private void bindPreview(TLRPC.Document r15) {
        BlockRow r0 = this.currentRow;
        ImageLocation r1 = null;
        if (r0 == null) goto L7;
        MediaUploadState r02 = r0.media;
        if (r02 == null) goto L7;
        String r03 = r02.localPath;
    L9:
        if (TextUtils.isEmpty(r03) == false) goto L11;
        File r2 = null;
    L12:
        if (r15 == null) goto L17;
        String r3 = r15.mime_type;
        if (r3 == null) goto L17;
        String r32 = r3.toLowerCase();
    L19:
        if (isUploading() == false) goto L28;
        if (r2 == null) goto L28;
        if (r2.exists() == false) goto L28;
        if (r32.startsWith("image/") == false) goto L26;
    L27:
        boolean r22 = true;
    L29:
        boolean r33 = MessageObject.isDocumentHasThumb(r15);
        if (r22 == true) goto L34;
        if (r33 == true) goto L34;
        boolean r4 = false;
    L35:
        this.hasPreview = r4;
        int r42 = AndroidUtilities.dp(16.0f);
        if (this.blockRtl == false) goto L38;
        int r7 = 0;
    L39:
        int r43 = r42 + r7;
        this.mediaX = r43;
        if (this.hasPreview == false) goto L42;
        r43 = r43 + AndroidUtilities.dp(21.0f);
    L42:
        this.buttonX = r43;
        if (this.hasPreview == false) goto L45;
        int r44 = AndroidUtilities.dp(31.0f);
    L46:
        RadialProgress2 r72 = this.radialProgress;
        int r8 = this.buttonX;
        int r9 = this.buttonSize;
        r72.setProgressRect(r8, r44, r8 + r9, r9 + r44);
        if (r22 == false) goto L51;
        this.previewImage.setImageCoords(this.mediaX, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(86.0f), AndroidUtilities.dp(86.0f));
        this.previewImage.setImage(ImageLocation.getForPath(r03), "86_86", null, null, r15, 1);
        return;
    L51:
        if (r33 == false) goto L66;
        TLRPC.PhotoSize r152 = FileLoader.getClosestPhotoSizeWithSize(r15.thumbs, 320, false, null, true);
        Delegate r04 = this.delegate;
        if (r04 != null) goto L55;
        MessageObject r05 = null;
    L56:
        this.previewImage.setImageCoords(this.mediaX, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(86.0f), AndroidUtilities.dp(86.0f));
        ImageReceiver r73 = this.previewImage;
        if (r152 != null) goto L59;
    L58:
        ImageLocation r82 = r1;
        Drawable r10 = ImageLoader.createStripedBitmap(r15.thumbs);
        if (r05 != null) goto L64;
        r05 = this.messageObject;
    L64:
        r73.setImage(r82, "86_86", r10, null, r05, 1);
        return;
    L59:
        r1 = ImageLocation.getForDocument(r152, r15);
        goto L58
    L55:
        r05 = r04.getFileRefParentObject();
        goto L56
    L66:
        this.previewImage.clearImage();
        return;
    L45:
        r44 = this.buttonY;
        goto L46
    L38:
        r7 = blockInset();
    L34:
        r4 = true;
        goto L35
    L26:
        if (r32.equals("video/mp4") == true) goto L27;
    L28:
        r22 = false;
    L17:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L19
    L11:
        r2 = new File(r03);
    L7:
        r03 = null;
        goto L9
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

    private TLRPC.Document document() {
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L9;
        MediaUploadState r02 = r0.media;
        if (r02 != null) goto L8;
        return null;
    L8:
        return r02.document;
    L9:
        return null;
    }

    private static Activity findActivity(Context r1) {
    L3:
        if ((r1 instanceof ContextWrapper) == false) goto L10;
        if ((r1 instanceof Activity) == true) goto L7;
        r1 = ((ContextWrapper) r1).getBaseContext();
        goto L3
    L7:
        return (Activity) r1;
    L10:
        if ((r1 instanceof Activity) == true) goto L12;
        return null;
    L12:
        return (Activity) r1;
    }

    private boolean isCellSelected() {
        if (this.delegate != null) goto L5;
    L18:
        return false;
    L5:
        if ((getParent() instanceof RecyclerView) == false) goto L18;
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.delegate.getSelectionHelper();
        if (r0 == null) goto L18;
        if (r0.isInSelectionMode() == false) goto L18;
        int r3 = ((RecyclerView) getParent()).getChildAdapterPosition(this);
        if (r3 <= r0.getStartCell()) goto L18;
        if (r3 > r0.getEndCell()) goto L18;
        return true;
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

    private File localFile() {
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L12;
        MediaUploadState r02 = r0.media;
        if (r02 == null) goto L12;
        if (TextUtils.isEmpty(r02.localPath) == true) goto L12;
        File r03 = new File(this.currentRow.media.localPath);
        if (r03.exists() == false) goto L12;
        return r03;
    L12:
        if (document() != null) goto L15;
        return null;
    L15:
        File r04 = FileLoader.getInstance(this.currentAccount).getPathToAttach(document(), false);
        if (r04 == null) goto L21;
        if (r04.exists() == false) goto L21;
        return r04;
    L21:
        return FileLoader.getInstance(this.currentAccount).getPathToAttach(document(), true);
    }

    private void pressButton() {
        if (isUploading() == false) goto L7;
        Delegate r0 = this.delegate;
        if (r0 == null) goto L32;
        r0.onCancelUpload(this.currentRow);
    L32:
        invalidate();
        return;
    L7:
        int r02 = this.buttonState;
        if (r02 != 0) goto L15;
        Activity r03 = findActivity(getContext());
        if (r03 == null) goto L32;
        MessageObject r2 = this.messageObject;
        if (r2 == null) goto L32;
        AndroidUtilities.openForView(r2, r03, this.resourcesProvider, false);
        goto L32
    L15:
        if (r02 != 1) goto L28;
        if (document() == null) goto L28;
        Delegate r04 = this.delegate;
        if (r04 != null) goto L21;
        MessageObject r05 = null;
    L22:
        FileLoader r1 = FileLoader.getInstance(this.currentAccount);
        TLRPC.Document r4 = document();
        if (r05 != null) goto L26;
        r05 = this.messageObject;
    L26:
        r1.loadFile(r4, r05, 1, 1);
        this.buttonState = 2;
        this.radialProgress.setIcon(3, true, true);
        goto L32
    L21:
        r05 = r04.getFileRefParentObject();
    L28:
        if (this.buttonState != 2) goto L32;
        if (document() == null) goto L32;
        FileLoader.getInstance(this.currentAccount).cancelLoadFile(document());
        this.buttonState = 1;
        this.radialProgress.setIcon(2, false, true);
        goto L32
    }

    private void rebuildLayouts() {
        TLRPC.Document r0 = document();
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.hasPreview == false) goto L9;
        int r1 = this.mediaX;
        float r2 = 97.0f;
    L8:
        int r12 = r1 + AndroidUtilities.dp(r2);
        int r22 = AndroidUtilities.dp(40.0f);
        if (getMeasuredWidth() <= 0) goto L13;
        int r3 = getMeasuredWidth();
    L14:
        int r32 = (r3 - r12) - AndroidUtilities.dp(16.0f);
        if (this.blockRtl == false) goto L17;
        int r13 = blockInset();
    L18:
        int r7 = Math.max(r22, r32 - r13);
        this.textPaint.setTextSize(AndroidUtilities.dp(15.0f));
        this.textPaint.setTypeface(AndroidUtilities.bold());
        this.sizePaint.setTextSize(AndroidUtilities.dp(13.0f));
        String r14 = FileLoader.getDocumentFileName(r0);
        if (TextUtils.isEmpty(r14) == false) goto L25;
        MediaUploadState r23 = this.currentRow.media;
        if (r23 == null) goto L25;
        if (TextUtils.isEmpty(r23.localPath) == true) goto L25;
        r14 = new File(this.currentRow.media.localPath).getName();
    L25:
        if (r14 != null) goto L27;
        r14 = _UrlKt.FRAGMENT_ENCODE_SET;
    L27:
        CharSequence r5 = TextUtils.ellipsize(r14, this.textPaint, r7, TextUtils.TruncateAt.END);
        TextPaint r6 = this.textPaint;
        Layout.Alignment r8 = Layout.Alignment.ALIGN_NORMAL;
        this.titleLayout = new StaticLayout(r5, r6, r7, r8, 1.0f, 0.0f, false);
        long r02 = r0.size;
        if (r02 > 0) goto L36;
        MediaUploadState r03 = this.currentRow.media;
        if (r03 != null) goto L33;
    L35:
        r02 = 0;
        goto L36
    L33:
        if (TextUtils.isEmpty(r03.localPath) == true) goto L35;
        r02 = new File(this.currentRow.media.localPath).length();
    L36:
        this.sizeLayout = new StaticLayout(AndroidUtilities.formatFileSize(r02), this.sizePaint, r7, r8, 1.0f, 0.0f, false);
        return;
    L17:
        r13 = 0;
        goto L18
    L13:
        r3 = AndroidUtilities.displaySize.x;
        goto L14
    L9:
        r1 = this.buttonX;
        r2 = 54.0f;
        goto L8
    }

    public void bind(BlockRow r1, Delegate r2) {
        this.currentRow = r1;
        this.delegate = r2;
        this.blockRtl = RichBlockChrome.rtl();
        bindBlockInset(r1);
        this.caption.bind();
        TLRPC.Document r12 = document();
        if (this.boundDocument == r12) goto L9;
        this.boundDocument = r12;
        if (r12 != null) goto L7;
        MessageObject r22 = null;
    L8:
        this.messageObject = r22;
        goto L9
    L7:
        r22 = buildMessageObject(r12);
    L9:
        bindPreview(r12);
        rebuildLayouts();
        if (this.attached == false) goto L12;
        updateButtonState(false);
    L12:
        requestLayout();
        invalidate();
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
        this.previewImage.onAttachedToWindow();
        updateButtonState(false);
    }

    @Override // org.telegram.ui.iv.RichBlockCell
    public void onBlockInsetChanged(int r5) {
        int r0 = AndroidUtilities.dp(16.0f);
        if (this.blockRtl == false) goto L5;
        r5 = 0;
    L5:
        int r02 = r0 + r5;
        this.mediaX = r02;
        if (this.hasPreview == false) goto L8;
        r02 = r02 + AndroidUtilities.dp(21.0f);
    L8:
        this.buttonX = r02;
        if (this.hasPreview == false) goto L11;
        int r52 = AndroidUtilities.dp(31.0f);
    L12:
        RadialProgress2 r03 = this.radialProgress;
        int r1 = this.buttonX;
        int r2 = this.buttonSize;
        r03.setProgressRect(r1, r52, r1 + r2, r2 + r52);
        requestLayout();
        return;
    L11:
        r52 = this.buttonY;
        goto L12
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
        this.previewImage.onDetachedFromWindow();
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
    }

    @Override // android.view.View
    public void onDraw(Canvas r10) {
        if (document() != null) goto L6;
        return;
    L6:
        if (this.hasPreview == true) goto L8;
    L10:
        this.radialProgress.draw(r10);
        if (this.hasPreview == false) goto L14;
        int r2 = this.mediaX;
        float r3 = 97.0f;
    L13:
        int r22 = r2 + AndroidUtilities.dp(r3);
        this.textPaint.setColor(Theme.getColor(Theme.key_chat_inFileNameText, this.resourcesProvider));
        int r32 = AndroidUtilities.dp(12.0f);
        if (this.titleLayout == null) goto L18;
        r10.save();
        r10.translate(r22, r32);
        this.titleLayout.draw(r10);
        r10.restore();
    L18:
        this.sizePaint.setColor(Theme.getColor(Theme.key_chat_inTimeText, this.resourcesProvider));
        StaticLayout r4 = this.titleLayout;
        int r5 = 0;
        if (r4 != null) goto L21;
        int r42 = 0;
    L22:
        int r33 = (r32 + r42) + AndroidUtilities.dp(2.0f);
        if (this.sizeLayout == null) goto L26;
        r10.save();
        r10.translate(r22, r33);
        this.sizeLayout.draw(r10);
        r10.restore();
    L26:
        if (isCellSelected() == true) goto L28;
        return;
    L28:
        if (this.blockRtl == false) goto L30;
        int r23 = 0;
    L31:
        float r24 = r23 + AndroidUtilities.dp(8.0f);
        float r43 = AndroidUtilities.dp(2.0f);
        int r6 = getWidth();
        if (this.blockRtl == false) goto L34;
        r5 = blockInset();
    L34:
        float r52 = (r6 - r5) - AndroidUtilities.dp(8.0f);
        if (this.hasPreview == false) goto L37;
        float r62 = 104.0f;
    L38:
        r10.drawRoundRect(r24, r43, r52, AndroidUtilities.dp(r62), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.selectionPaint);
        return;
    L37:
        r62 = 64.0f;
        goto L38
    L30:
        r23 = blockInset();
        goto L31
    L21:
        r42 = r4.getHeight();
        goto L22
    L14:
        r2 = this.buttonX;
        r3 = 54.0f;
        goto L13
    L8:
        if (this.previewImage.draw(r10) == true) goto L10;
        this.previewBackgroundPaint.setColor(Theme.getColor(Theme.key_chat_inFileBackground, this.resourcesProvider));
        r10.drawRoundRect(this.mediaX, AndroidUtilities.dp(10.0f), this.mediaX + AndroidUtilities.dp(86.0f), AndroidUtilities.dp(96.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.previewBackgroundPaint);
        goto L10
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
        int r52 = r5 - r3;
        if (this.hasPreview == false) goto L12;
        float r32 = 106.0f;
    L13:
        r22.layout(r42, r62, r52, AndroidUtilities.dp(r32));
        rebuildLayouts();
        return;
    L12:
        r32 = 66.0f;
        goto L13
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
        int r53 = r52.measure(r0, r1, r42);
        if (this.hasPreview == false) goto L12;
        float r02 = 106.0f;
    L13:
        setMeasuredDimension(r42, AndroidUtilities.dp(r02) + r53);
        return;
    L12:
        r02 = 66.0f;
        goto L13
    L5:
        r0 = blockInset();
        goto L7
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r3, long r4, long r6) {
        RadialProgress2 r2 = this.radialProgress;
        if (r6 > 0) goto L5;
        float r32 = 0.0f;
    L6:
        r2.setProgress(r32, true);
        return;
    L5:
        r32 = Math.min(1.0f, r4 / r6);
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
    public boolean onTouchEvent(MotionEvent r6) {
        if (r6.getX() >= this.mediaX) goto L5;
    L15:
        boolean r0 = false;
    L17:
        if (r6.getActionMasked() != 0) goto L22;
        if (r0 == false) goto L22;
        this.pressed = true;
        return true;
    L22:
        if (r6.getActionMasked() != 1) goto L30;
        if (this.pressed == false) goto L30;
        this.pressed = false;
        if (r0 == false) goto L28;
        playSoundEffect(0);
        pressButton();
    L28:
        return true;
    L30:
        if (r6.getActionMasked() != 3) goto L33;
        this.pressed = false;
    L33:
        if (this.pressed == false) goto L35;
    L38:
        return true;
    L35:
        if (super.onTouchEvent(r6) == true) goto L38;
        return false;
    L5:
        if (r6.getX() > (getWidth() - AndroidUtilities.dp(12.0f))) goto L15;
        if (r6.getY() < AndroidUtilities.dp(10.0f)) goto L15;
        float r02 = r6.getY();
        if (this.hasPreview == false) goto L11;
        float r3 = 96.0f;
    L13:
        if (r02 > AndroidUtilities.dp(r3)) goto L15;
        r0 = true;
        goto L17
    L11:
        r3 = 54.0f;
        goto L13
    }

    @Override // org.telegram.ui.iv.RichCaptionHost
    public void persistCaption() {
        this.caption.persist();
    }

    public void refreshUploadState() {
        bindPreview(document());
        rebuildLayouts();
        updateButtonState(false);
        requestLayout();
        invalidate();
    }

    public void updateButtonState(boolean r8) {
        boolean r0 = this.hasPreview;
        RadialProgress2 r1 = this.radialProgress;
        if (r0 == false) goto L5;
        r1.setColorKeys(Theme.key_chat_mediaLoaderPhoto, Theme.key_chat_mediaLoaderPhotoSelected, Theme.key_chat_mediaLoaderPhotoIcon, Theme.key_chat_mediaLoaderPhotoIconSelected);
        this.radialProgress.setProgressColor(Theme.getColor(Theme.key_chat_mediaProgress, this.resourcesProvider));
    L7:
        if (isUploading() == false) goto L10;
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        this.radialProgress.setProgress(this.currentRow.media.progress, r8);
        this.radialProgress.setIcon(3, false, r8);
        return;
    L10:
        String r02 = FileLoader.getAttachFileName(document());
        File r3 = localFile();
        if (r3 == null) goto L21;
        if (r3.exists() == false) goto L21;
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        this.buttonState = 0;
        RadialProgress2 r03 = this.radialProgress;
        if (this.hasPreview == false) goto L17;
        int r7 = 4;
    L18:
        r03.setIcon(r7, false, r8);
        return;
    L17:
        r7 = 5;
    L21:
        if (TextUtils.isEmpty(r02) == true) goto L32;
        DownloadController.getInstance(this.currentAccount).addLoadingFileObserver(r02, null, this);
        float r5 = 0.0f;
        if (FileLoader.getInstance(this.currentAccount).isLoadingFile(r02) == false) goto L30;
        this.buttonState = 2;
        Float r04 = ImageLoader.getInstance().getFileProgress(r02);
        RadialProgress2 r2 = this.radialProgress;
        if (r04 == null) goto L28;
        r5 = r04.floatValue();
    L28:
        r2.setProgress(r5, r8);
        this.radialProgress.setIcon(3, true, r8);
        return;
    L30:
        this.buttonState = 1;
        this.radialProgress.setProgress(0.0f, r8);
        this.radialProgress.setIcon(2, false, r8);
        return;
    L32:
        return;
    L5:
        r1.setColorKeys(Theme.key_chat_inLoader, Theme.key_chat_inLoaderSelected, Theme.key_chat_inMediaIcon, Theme.key_chat_inMediaIconSelected);
        this.radialProgress.setProgressColor(Theme.getColor(Theme.key_chat_inFileProgress, this.resourcesProvider));
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.selectionPaint.setColor(Theme.getColor(Theme.key_chat_inTextSelectionHighlight, this.resourcesProvider));
        RichCaptionController r3 = this.caption;
        if (r3 == null) goto L6;
        r3.applyColors();
        return;
    }
}
