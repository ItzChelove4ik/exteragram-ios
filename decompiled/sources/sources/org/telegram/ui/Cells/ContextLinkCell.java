package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.WebFile;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LetterDrawable;
import org.telegram.ui.Components.RadialProgress2;
import org.telegram.ui.PhotoViewer;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes6.dex */
public class ContextLinkCell extends FrameLayout implements DownloadController.FileDownloadProgressListener {
    private static AccelerateInterpolator interpolator;
    public final Property<ContextLinkCell, Float> IMAGE_SCALE;
    private int TAG;
    private AnimatorSet animator;
    private Paint backgroundPaint;
    private ButtonBounce buttonBounce;
    private boolean buttonPressed;
    private int buttonState;
    File cacheFile;
    private boolean canPreviewGif;
    private CheckBox2 checkBox;
    private int currentAccount;
    private int currentDate;
    private MessageObject currentMessageObject;
    private TLRPC.PhotoSize currentPhotoObject;
    private ContextLinkCellDelegate delegate;
    private StaticLayout descriptionLayout;
    private int descriptionY;
    private TLRPC.Document documentAttach;
    private int documentAttachType;
    private boolean drawLinkImageView;
    boolean fileExist;
    String fileName;
    private float imageScale;
    private TLRPC.User inlineBot;
    private TLRPC.BotInlineResult inlineResult;
    private boolean isForceGif;
    private boolean isKeyboard;
    private LetterDrawable letterDrawable;
    private ImageReceiver linkImageView;
    private StaticLayout linkLayout;
    private int linkY;
    private boolean mediaWebpage;
    private boolean needDivider;
    private boolean needShadow;
    private Object parentObject;
    private TLRPC.Photo photoAttach;
    private RadialProgress2 radialProgress;
    int resolveFileNameId;
    boolean resolvingFileName;
    private Theme.ResourcesProvider resourcesProvider;
    private boolean scaled;
    private StaticLayout titleLayout;
    private int titleY;

    /* renamed from: org.telegram.ui.Cells.ContextLinkCell$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ContextLinkCell this$0;
        final /* synthetic */ boolean val$ifSame;
        final /* synthetic */ int val$localId;

        public static /* synthetic */ void $r8$lambda$HStgoV1PLbseIWTPxnsQ73LsfB4(AnonymousClass1 r0, int r1, String r2, File r3, boolean r4, boolean r5) {
            r0.lambda$run$0(r1, r2, r3, r4, r5);
        }

        public AnonymousClass1(ContextLinkCell r1, int r2, boolean r3) {
            this.this$0 = r1;
            this.val$localId = r2;
            this.val$ifSame = r3;
        }

        private /* synthetic */ void lambda$run$0(int r2, String r3, File r4, boolean r5, boolean r6) {
            ContextLinkCell r1 = this.this$0;
            r1.resolvingFileName = false;
            if (r1.resolveFileNameId != r2) goto L8;
            r1.fileName = r3;
            if (r3 != null) goto L7;
            r1.fileName = _UrlKt.FRAGMENT_ENCODE_SET;
        L7:
            r1.cacheFile = r4;
            r1.fileExist = r5;
        L8:
            r1.updateButtonState(r6, true);
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean r1 = true;
            String r4 = null;
            if (ContextLinkCell.m6879$$Nest$fgetdocumentAttachType(this.this$0) != 5) goto L5;
        L43:
            TLRPC.Document r0 = ContextLinkCell.m6878$$Nest$fgetdocumentAttach(this.this$0);
            ContextLinkCell r6 = this.this$0;
            if (r0 == null) goto L48;
            r4 = FileLoader.getAttachFileName(ContextLinkCell.m6878$$Nest$fgetdocumentAttach(r6));
            File r02 = FileLoader.getInstance(ContextLinkCell.m6876$$Nest$fgetcurrentAccount(this.this$0)).getPathToAttach(ContextLinkCell.m6878$$Nest$fgetdocumentAttach(this.this$0));
        L46:
            File r9 = r02;
        L41:
            String r8 = r4;
        L56:
            if (TextUtils.isEmpty(r8) == false) goto L58;
        L60:
            r1 = false;
        L61:
            AndroidUtilities.runOnUIThread(new ContextLinkCell$1$$ExternalSyntheticLambda0(this, this.val$localId, r8, r9, r1, this.val$ifSame));
            return;
        L58:
            if (r9.exists() == false) goto L60;
        L48:
            if ((ContextLinkCell.m6881$$Nest$fgetinlineResult(r6).content instanceof TLRPC.TL_webDocument) == false) goto L54;
            StringBuilder r03 = new StringBuilder();
            r03.append(Utilities.MD5(ContextLinkCell.m6881$$Nest$fgetinlineResult(this.this$0).content.url));
            r03.append(".");
            String r3 = ContextLinkCell.m6881$$Nest$fgetinlineResult(this.this$0).content.url;
            if (ContextLinkCell.m6879$$Nest$fgetdocumentAttachType(this.this$0) != 5) goto L52;
            String r42 = HlsSegmentFormat.MP3;
        L53:
            r03.append(ImageLoader.getHttpUrlExtension(r3, r42));
            r4 = r03.toString();
            r02 = new File(FileLoader.getDirectory(4), r4);
            goto L46
        L52:
            r42 = "ogg";
        L54:
            r8 = null;
            r9 = null;
            goto L56
        L5:
            if (ContextLinkCell.m6879$$Nest$fgetdocumentAttachType(this.this$0) == 3) goto L43;
            if (ContextLinkCell.m6882$$Nest$fgetmediaWebpage(this.this$0) == false) goto L54;
            TLRPC.BotInlineResult r04 = ContextLinkCell.m6881$$Nest$fgetinlineResult(this.this$0);
            ContextLinkCell r5 = this.this$0;
            if (r04 == null) goto L31;
            boolean r05 = ContextLinkCell.m6881$$Nest$fgetinlineResult(r5).document instanceof TLRPC.TL_document;
            ContextLinkCell r52 = this.this$0;
            if (r05 == false) goto L14;
            String r06 = FileLoader.getAttachFileName(ContextLinkCell.m6881$$Nest$fgetinlineResult(r52).document);
            File r2 = FileLoader.getInstance(ContextLinkCell.m6876$$Nest$fgetcurrentAccount(this.this$0)).getPathToAttach(ContextLinkCell.m6881$$Nest$fgetinlineResult(this.this$0).document);
        L35:
            if (ContextLinkCell.m6878$$Nest$fgetdocumentAttach(this.this$0) != null) goto L37;
        L42:
            r8 = r06;
            r9 = r2;
            goto L56
        L37:
            if (ContextLinkCell.m6879$$Nest$fgetdocumentAttachType(this.this$0) != 2) goto L42;
            if (MessageObject.getDocumentVideoThumb(ContextLinkCell.m6878$$Nest$fgetdocumentAttach(this.this$0)) == null) goto L42;
            r9 = r2;
            goto L41
        L14:
            boolean r07 = ContextLinkCell.m6881$$Nest$fgetinlineResult(r52).photo instanceof TLRPC.TL_photo;
            ContextLinkCell r53 = this.this$0;
            if (r07 == false) goto L17;
            ContextLinkCell.m6884$$Nest$fputcurrentPhotoObject(r53, FileLoader.getClosestPhotoSizeWithSize(ContextLinkCell.m6881$$Nest$fgetinlineResult(r53).photo.sizes, AndroidUtilities.getPhotoSize(), true));
            r06 = FileLoader.getAttachFileName(ContextLinkCell.m6877$$Nest$fgetcurrentPhotoObject(this.this$0));
            r2 = FileLoader.getInstance(ContextLinkCell.m6876$$Nest$fgetcurrentAccount(this.this$0)).getPathToAttach(ContextLinkCell.m6877$$Nest$fgetcurrentPhotoObject(this.this$0));
            goto L35
        L17:
            boolean r08 = ContextLinkCell.m6881$$Nest$fgetinlineResult(r53).content instanceof TLRPC.TL_webDocument;
            ContextLinkCell r54 = this.this$0;
            if (r08 == false) goto L28;
            r06 = Utilities.MD5(ContextLinkCell.m6881$$Nest$fgetinlineResult(r54).content.url) + "." + ImageLoader.getHttpUrlExtension(ContextLinkCell.m6881$$Nest$fgetinlineResult(this.this$0).content.url, FileLoader.getMimeTypePart(ContextLinkCell.m6881$$Nest$fgetinlineResult(this.this$0).content.mime_type));
            File r32 = new File(FileLoader.getDirectory(4), r06);
            if (ContextLinkCell.m6879$$Nest$fgetdocumentAttachType(this.this$0) == 2) goto L22;
        L26:
            r2 = r32;
            goto L35
        L22:
            if ((ContextLinkCell.m6881$$Nest$fgetinlineResult(this.this$0).thumb instanceof TLRPC.TL_webDocument) == false) goto L26;
            if ("video/mp4".equals(ContextLinkCell.m6881$$Nest$fgetinlineResult(this.this$0).thumb.mime_type) == false) goto L26;
            r2 = r32;
            r06 = null;
            goto L35
        L28:
            if ((ContextLinkCell.m6881$$Nest$fgetinlineResult(r54).thumb instanceof TLRPC.TL_webDocument) == false) goto L33;
            r06 = Utilities.MD5(ContextLinkCell.m6881$$Nest$fgetinlineResult(this.this$0).thumb.url) + "." + ImageLoader.getHttpUrlExtension(ContextLinkCell.m6881$$Nest$fgetinlineResult(this.this$0).thumb.url, FileLoader.getMimeTypePart(ContextLinkCell.m6881$$Nest$fgetinlineResult(this.this$0).thumb.mime_type));
            r32 = new File(FileLoader.getDirectory(4), r06);
        L33:
            r06 = null;
            r2 = null;
            goto L35
        L31:
            if (ContextLinkCell.m6878$$Nest$fgetdocumentAttach(r5) == null) goto L33;
            r06 = FileLoader.getAttachFileName(ContextLinkCell.m6878$$Nest$fgetdocumentAttach(this.this$0));
            r2 = FileLoader.getInstance(ContextLinkCell.m6876$$Nest$fgetcurrentAccount(this.this$0)).getPathToAttach(ContextLinkCell.m6878$$Nest$fgetdocumentAttach(this.this$0));
            goto L35
        }
    }

    /* renamed from: org.telegram.ui.Cells.ContextLinkCell$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimationProperties.FloatProperty<ContextLinkCell> {
        final /* synthetic */ ContextLinkCell this$0;

        public AnonymousClass2(ContextLinkCell r1, String r2) {
            this.this$0 = r1;
            super(r2);
        }

        public Float get(ContextLinkCell r1) {
            return Float.valueOf(ContextLinkCell.m6880$$Nest$fgetimageScale(this.this$0));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((ContextLinkCell) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(ContextLinkCell r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(ContextLinkCell r1, float r2) {
            ContextLinkCell.m6885$$Nest$fputimageScale(this.this$0, r2);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Cells.ContextLinkCell$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ ContextLinkCell this$0;
        final /* synthetic */ boolean val$checked;

        public AnonymousClass3(ContextLinkCell r1, boolean r2) {
            this.this$0 = r1;
            this.val$checked = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (ContextLinkCell.m6875$$Nest$fgetanimator(this.this$0) != null) goto L5;
            return;
        L5:
            if (ContextLinkCell.m6875$$Nest$fgetanimator(this.this$0).equals(r2) == false) goto L9;
            ContextLinkCell.m6883$$Nest$fputanimator(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (ContextLinkCell.m6875$$Nest$fgetanimator(this.this$0) != null) goto L5;
            return;
        L5:
            if (ContextLinkCell.m6875$$Nest$fgetanimator(this.this$0).equals(r2) == false) goto L11;
            ContextLinkCell.m6883$$Nest$fputanimator(this.this$0, null);
            if (this.val$checked == true) goto L12;
            this.this$0.setBackgroundColor(0);
            return;
        L12:
            return;
        }
    }

    public interface ContextLinkCellDelegate {
        void didPressedImage(ContextLinkCell r1);
    }

    /* renamed from: -$$Nest$fgetanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m6875$$Nest$fgetanimator(ContextLinkCell r0) {
        return r0.animator;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6876$$Nest$fgetcurrentAccount(ContextLinkCell r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetcurrentPhotoObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.PhotoSize m6877$$Nest$fgetcurrentPhotoObject(ContextLinkCell r0) {
        return r0.currentPhotoObject;
    }

    /* renamed from: -$$Nest$fgetdocumentAttach, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Document m6878$$Nest$fgetdocumentAttach(ContextLinkCell r0) {
        return r0.documentAttach;
    }

    /* renamed from: -$$Nest$fgetdocumentAttachType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6879$$Nest$fgetdocumentAttachType(ContextLinkCell r0) {
        return r0.documentAttachType;
    }

    /* renamed from: -$$Nest$fgetimageScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m6880$$Nest$fgetimageScale(ContextLinkCell r0) {
        return r0.imageScale;
    }

    /* renamed from: -$$Nest$fgetinlineResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.BotInlineResult m6881$$Nest$fgetinlineResult(ContextLinkCell r0) {
        return r0.inlineResult;
    }

    /* renamed from: -$$Nest$fgetmediaWebpage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6882$$Nest$fgetmediaWebpage(ContextLinkCell r0) {
        return r0.mediaWebpage;
    }

    /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6883$$Nest$fputanimator(ContextLinkCell r0, AnimatorSet r1) {
        r0.animator = r1;
    }

    /* renamed from: -$$Nest$fputcurrentPhotoObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6884$$Nest$fputcurrentPhotoObject(ContextLinkCell r0, TLRPC.PhotoSize r1) {
        r0.currentPhotoObject = r1;
    }

    /* renamed from: -$$Nest$fputimageScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6885$$Nest$fputimageScale(ContextLinkCell r0, float r1) {
        r0.imageScale = r1;
    }

    static {
        interpolator = new AccelerateInterpolator(0.5f);
    }

    public ContextLinkCell(Context r3) {
        this(r3, false, null);
    }

    public ContextLinkCell(Context r11, boolean r12, Theme.ResourcesProvider r13) {
        super(r11);
        this.currentAccount = UserConfig.selectedAccount;
        this.titleY = AndroidUtilities.dp(7.0f);
        this.descriptionY = AndroidUtilities.dp(27.0f);
        this.cacheFile = null;
        this.imageScale = 1.0f;
        this.IMAGE_SCALE = new AnonymousClass2(this, "animationValue");
        this.resourcesProvider = r13;
        ImageReceiver r0 = new ImageReceiver(this);
        this.linkImageView = r0;
        r0.setAllowLoadingOnAttachedOnly(true);
        this.linkImageView.setLayerNum(1);
        this.linkImageView.setUseSharedAnimationQueue(true);
        this.letterDrawable = new LetterDrawable(r13, 0);
        this.radialProgress = new RadialProgress2(this);
        this.TAG = DownloadController.getInstance(this.currentAccount).generateObserverTag();
        setFocusable(true);
        if (r12 == false) goto L5;
        Paint r122 = new Paint();
        this.backgroundPaint = r122;
        int r02 = Theme.key_sharedMedia_photoPlaceholder;
        r122.setColor(Theme.getColor(r02, r13));
        CheckBox2 r123 = new CheckBox2(r11, 21, r13);
        this.checkBox = r123;
        r123.setVisibility(4);
        this.checkBox.setColor(-1, r02, Theme.key_checkboxCheck);
        this.checkBox.setDrawUnchecked(false);
        this.checkBox.setDrawBackgroundAsArc(1);
        addView(this.checkBox, LayoutHelper.createFrame(24, 24.0f, 53, 0.0f, 1.0f, 1.0f, 0.0f));
    L5:
        setWillNotDraw(false);
    }

    private void didPressedButton() {
        int r0 = this.documentAttachType;
        if (r0 != 3) goto L5;
    L6:
        int r02 = this.buttonState;
        if (r02 == 0) goto L9;
        if (r02 != 1) goto L18;
        if (MediaController.getInstance().pauseMessage(this.currentMessageObject) == false) goto L38;
        this.buttonState = 0;
        this.radialProgress.setIcon(getIconForCurrentState(), false, true);
        invalidate();
        return;
    L38:
        return;
    L18:
        if (r02 != 2) goto L27;
        this.radialProgress.setProgress(0.0f, false);
        if (this.documentAttach == null) goto L23;
        FileLoader.getInstance(this.currentAccount).loadFile(this.documentAttach, this.inlineResult, 1, 0);
    L25:
        this.buttonState = 4;
        this.radialProgress.setIcon(getIconForCurrentState(), false, true);
        invalidate();
        return;
    L23:
        if ((this.inlineResult.content instanceof TLRPC.TL_webDocument) == false) goto L25;
        FileLoader.getInstance(this.currentAccount).loadFile(WebFile.createWithWebDocument(this.inlineResult.content), 3, 1);
        goto L25
    L27:
        if (r02 == 4) goto L29;
        return;
    L29:
        if (this.documentAttach == null) goto L32;
        FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.documentAttach);
    L34:
        this.buttonState = 2;
        this.radialProgress.setIcon(getIconForCurrentState(), false, true);
        invalidate();
        return;
    L32:
        if ((this.inlineResult.content instanceof TLRPC.TL_webDocument) == false) goto L34;
        FileLoader.getInstance(this.currentAccount).cancelLoadFile(WebFile.createWithWebDocument(this.inlineResult.content));
        goto L34
    L9:
        if (MediaController.getInstance().playMessage(this.currentMessageObject) == false) goto L37;
        this.buttonState = 1;
        this.radialProgress.setIcon(getIconForCurrentState(), false, true);
        invalidate();
        return;
    L37:
        return;
    L5:
        if (r0 == 5) goto L6;
    }

    private int getIconForCurrentState() {
        int r0 = this.documentAttachType;
        if (r0 != 3) goto L5;
    L12:
        this.radialProgress.setColorKeys(Theme.key_chat_inLoader, Theme.key_chat_inLoaderSelected, Theme.key_chat_inMediaIcon, Theme.key_chat_inMediaIconSelected);
        int r8 = this.buttonState;
        if (r8 != 1) goto L16;
        return 1;
    L16:
        if (r8 != 2) goto L18;
        return 2;
    L18:
        if (r8 != 4) goto L20;
        return 3;
    L20:
        return 0;
    L5:
        if (r0 == 5) goto L12;
        this.radialProgress.setColorKeys(Theme.key_chat_mediaLoaderPhoto, Theme.key_chat_mediaLoaderPhotoSelected, Theme.key_chat_mediaLoaderPhotoIcon, Theme.key_chat_mediaLoaderPhotoIconSelected);
        if (this.buttonState != 1) goto L11;
        return 10;
    L11:
        return 4;
    }

    private void setAttachType() {
        this.currentMessageObject = null;
        this.documentAttachType = 0;
        TLRPC.Document r1 = this.documentAttach;
        if (r1 != null) goto L5;
        TLRPC.BotInlineResult r12 = this.inlineResult;
        if (r12 != null) goto L22;
    L30:
        int r13 = this.documentAttachType;
        if (r13 == 3) goto L35;
        if (r13 == 5) goto L35;
        return;
    L35:
        TLRPC.TL_message r14 = new TLRPC.TL_message();
        r14.out = true;
        r14.id = -Utilities.random.nextInt();
        r14.peer_id = new TLRPC.TL_peerUser();
        TLRPC.TL_peerUser r5 = new TLRPC.TL_peerUser();
        r14.from_id = r5;
        TLRPC.Peer r6 = r14.peer_id;
        long r7 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        r5.user_id = r7;
        r6.user_id = r7;
        r14.date = (int) (System.currentTimeMillis() / 1000);
        String r52 = _UrlKt.FRAGMENT_ENCODE_SET;
        r14.message = _UrlKt.FRAGMENT_ENCODE_SET;
        TLRPC.TL_messageMediaDocument r62 = new TLRPC.TL_messageMediaDocument();
        r14.media = r62;
        r62.flags |= 3;
        r62.document = new TLRPC.TL_document();
        TLRPC.MessageMedia r63 = r14.media;
        r63.document.file_reference = new byte[0];
        r14.flags |= 768;
        TLRPC.Document r72 = this.documentAttach;
        if (r72 == null) goto L38;
        r63.document = r72;
        r14.attachPath = _UrlKt.FRAGMENT_ENCODE_SET;
    L60:
        this.currentMessageObject = new MessageObject(this.currentAccount, r14, false, true);
        return;
    L38:
        String r64 = this.inlineResult.content.url;
        String r8 = "ogg";
        if (this.documentAttachType != 5) goto L41;
        String r73 = HlsSegmentFormat.MP3;
    L42:
        String r65 = ImageLoader.getHttpUrlExtension(r64, r73);
        TLRPC.Document r74 = r14.media.document;
        r74.id = 0;
        r74.access_hash = 0;
        r74.date = r14.date;
        r74.mime_type = "audio/" + r65;
        TLRPC.Document r66 = r14.media.document;
        r66.size = 0;
        r66.dc_id = 0;
        TLRPC.TL_documentAttributeAudio r67 = new TLRPC.TL_documentAttributeAudio();
        r67.duration = MessageObject.getInlineResultDuration(this.inlineResult);
        TLRPC.BotInlineResult r75 = this.inlineResult;
        String r10 = r75.title;
        if (r10 != null) goto L46;
        r10 = _UrlKt.FRAGMENT_ENCODE_SET;
    L46:
        r67.title = r10;
        String r76 = r75.description;
        if (r76 == null) goto L49;
        r52 = r76;
    L49:
        r67.performer = r52;
        r67.flags |= 3;
        if (this.documentAttachType != 3) goto L52;
        r67.voice = true;
    L52:
        r14.media.document.attributes.add(r67);
        TLRPC.TL_documentAttributeFilename r3 = new TLRPC.TL_documentAttributeFilename();
        StringBuilder r53 = new StringBuilder();
        r53.append(Utilities.MD5(this.inlineResult.content.url));
        r53.append(".");
        String r77 = this.inlineResult.content.url;
        if (this.documentAttachType != 5) goto L55;
        String r102 = HlsSegmentFormat.MP3;
    L56:
        r53.append(ImageLoader.getHttpUrlExtension(r77, r102));
        r3.file_name = r53.toString();
        r14.media.document.attributes.add(r3);
        File r54 = FileLoader.getDirectory(4);
        StringBuilder r78 = new StringBuilder();
        r78.append(Utilities.MD5(this.inlineResult.content.url));
        r78.append(".");
        String r68 = this.inlineResult.content.url;
        if (this.documentAttachType != 5) goto L59;
        r8 = HlsSegmentFormat.MP3;
    L59:
        r78.append(ImageLoader.getHttpUrlExtension(r68, r8));
        r14.attachPath = new File(r54, r78.toString()).getAbsolutePath();
        goto L60
    L55:
        r102 = "ogg";
        goto L56
    L41:
        r73 = "ogg";
        goto L42
    L22:
        if (r12.photo == null) goto L25;
        this.documentAttachType = 7;
        goto L30
    L25:
        if (r12.type.equals(MediaStreamTrack.AUDIO_TRACK_KIND) == false) goto L28;
        this.documentAttachType = 5;
        goto L30
    L28:
        if (this.inlineResult.type.equals("voice") == false) goto L30;
        this.documentAttachType = 3;
        goto L30
    L5:
        if (MessageObject.isGifDocument(r1) == false) goto L8;
        this.documentAttachType = 2;
        goto L30
    L8:
        if (MessageObject.isStickerDocument(this.documentAttach) == false) goto L10;
    L18:
        this.documentAttachType = 6;
        goto L30
    L10:
        if (MessageObject.isAnimatedStickerDocument(this.documentAttach, true) == true) goto L18;
        if (MessageObject.isMusicDocument(this.documentAttach) == false) goto L16;
        this.documentAttachType = 5;
        goto L30
    L16:
        if (MessageObject.isVoiceDocument(this.documentAttach) == false) goto L30;
        this.documentAttachType = 3;
        goto L30
    }

    public void allowButtonBounce(boolean r3) {
        if (this.buttonBounce == null) goto L5;
        boolean r0 = true;
    L6:
        if (r3 == r0) goto L12;
        if (r3 == false) goto L9;
        ButtonBounce r32 = new ButtonBounce(this, 1.0f, 3.0f).setReleaseDelay(120);
    L10:
        this.buttonBounce = r32;
        return;
    L9:
        r32 = null;
        goto L10
    L12:
        return;
    L5:
        r0 = false;
        goto L6
    }

    public TLRPC.BotInlineResult getBotInlineResult() {
        return this.inlineResult;
    }

    public int getDate() {
        return this.currentDate;
    }

    public TLRPC.Document getDocument() {
        return this.documentAttach;
    }

    public TLRPC.User getInlineBot() {
        return this.inlineBot;
    }

    public MessageObject getMessageObject() {
        return this.currentMessageObject;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return this.TAG;
    }

    public Object getParentObject() {
        return this.parentObject;
    }

    public ImageReceiver getPhotoImage() {
        return this.linkImageView;
    }

    public TLRPC.BotInlineResult getResult() {
        return this.inlineResult;
    }

    public boolean isGif() {
        if (this.documentAttachType == 2) goto L5;
        return false;
    L5:
        if (this.canPreviewGif == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isSticker() {
        if (this.documentAttachType != 6) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.linkImageView.onAttachedToWindow() == false) goto L5;
        updateButtonState(false, false);
    L5:
        this.radialProgress.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.linkImageView.onDetachedFromWindow();
        this.radialProgress.onDetachedFromWindow();
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
    }

    @Override // android.view.View
    public void onDraw(Canvas r15) {
        CheckBox2 r0 = this.checkBox;
        if (r0 != null) goto L5;
    L13:
        Canvas r8 = r15;
    L15:
        float r02 = 8.0f;
        if (this.titleLayout == null) goto L23;
        r8.save();
        if (LocaleController.isRTL == false) goto L20;
        float r152 = 8.0f;
    L21:
        r8.translate(AndroidUtilities.dp(r152), this.titleY);
        this.titleLayout.draw(r8);
        r8.restore();
        goto L23
    L20:
        r152 = AndroidUtilities.leftBaseline;
    L23:
        if (this.descriptionLayout == null) goto L30;
        Theme.chat_contextResult_descriptionTextPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.resourcesProvider));
        r8.save();
        if (LocaleController.isRTL == false) goto L27;
        float r153 = 8.0f;
    L28:
        r8.translate(AndroidUtilities.dp(r153), this.descriptionY);
        this.descriptionLayout.draw(r8);
        r8.restore();
        goto L30
    L27:
        r153 = AndroidUtilities.leftBaseline;
    L30:
        if (this.linkLayout == null) goto L37;
        Theme.chat_contextResult_descriptionTextPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText, this.resourcesProvider));
        r8.save();
        if (LocaleController.isRTL == true) goto L35;
        r02 = AndroidUtilities.leftBaseline;
    L35:
        r8.translate(AndroidUtilities.dp(r02), this.linkY);
        this.linkLayout.draw(r8);
        r8.restore();
    L37:
        if (this.mediaWebpage == false) goto L39;
        TLRPC.BotInlineResult r154 = this.inlineResult;
        if (r154 == null) goto L82;
        TLRPC.BotInlineMessage r155 = r154.send_message;
        if ((r155 instanceof TLRPC.TL_botInlineMessageMediaGeo) == false) goto L79;
    L80:
        int r156 = Theme.chat_inlineResultLocation.getIntrinsicWidth();
        int r1 = Theme.chat_inlineResultLocation.getIntrinsicHeight();
        int r2 = (int) (this.linkImageView.getImageX() + ((this.linkImageView.getImageWidth() - r156) / 2.0f));
        int r3 = (int) (this.linkImageView.getImageY() + ((this.linkImageView.getImageHeight() - r1) / 2.0f));
        r8.drawRect(this.linkImageView.getImageX(), this.linkImageView.getImageY(), this.linkImageView.getImageX() + this.linkImageView.getImageWidth(), this.linkImageView.getImageY() + this.linkImageView.getImageHeight(), LetterDrawable.paint);
        Theme.chat_inlineResultLocation.setBounds(r2, r3, r156 + r2, r1 + r3);
        Theme.chat_inlineResultLocation.draw(r8);
        goto L82
    L79:
        if ((r155 instanceof TLRPC.TL_botInlineMessageMediaVenue) == true) goto L80;
    L82:
        if (this.drawLinkImageView == false) goto L91;
        if (this.inlineResult == null) goto L86;
        this.linkImageView.setVisible(!PhotoViewer.isShowingImage(r15), false);
    L86:
        r8.save();
        float r157 = this.imageScale;
        ButtonBounce r22 = this.buttonBounce;
        if (r22 == null) goto L89;
        r157 = r157 * r22.getScale(0.1f);
    L89:
        r8.scale(r157, r157, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        this.linkImageView.draw(r8);
        r8.restore();
    L91:
        if (this.mediaWebpage == false) goto L97;
        int r158 = this.documentAttachType;
        if (r158 == 7) goto L95;
        if (r158 != 2) goto L97;
    L95:
        this.radialProgress.draw(r8);
    L97:
        if (this.needDivider == false) goto L105;
        if (this.mediaWebpage == true) goto L105;
        if (LocaleController.isRTL == false) goto L103;
        r8.drawLine(0.0f, getMeasuredHeight() - 1, getMeasuredWidth() - AndroidUtilities.dp(AndroidUtilities.leftBaseline), getMeasuredHeight() - 1, Theme.dividerPaint);
        goto L105
    L103:
        r8.drawLine(AndroidUtilities.dp(AndroidUtilities.leftBaseline), getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
    L105:
        if (this.needShadow == false) goto L108;
        Theme.chat_contextResult_shadowUnderSwitchDrawable.setBounds(0, 0, getMeasuredWidth(), AndroidUtilities.dp(3.0f));
        Theme.chat_contextResult_shadowUnderSwitchDrawable.draw(r8);
        return;
    L108:
        return;
    L39:
        if (this.drawLinkImageView == true) goto L41;
    L43:
        this.letterDrawable.setAlpha(255);
    L44:
        int r159 = this.documentAttachType;
        if (r159 != 3) goto L47;
    L69:
        RadialProgress2 r1510 = this.radialProgress;
        if (this.buttonPressed == false) goto L72;
        int r12 = Theme.key_chat_inAudioSelectedProgress;
    L73:
        r1510.setProgressColor(Theme.getColor(r12, this.resourcesProvider));
        this.radialProgress.draw(r8);
        goto L82
    L72:
        r12 = Theme.key_chat_inAudioProgress;
        goto L73
    L47:
        if (r159 == 5) goto L69;
        TLRPC.BotInlineResult r1511 = this.inlineResult;
        if (r1511 != null) goto L52;
    L54:
        TLRPC.BotInlineResult r1512 = this.inlineResult;
        if (r1512 != null) goto L57;
    L61:
        TLRPC.BotInlineResult r1513 = this.inlineResult;
        if (r1513 != null) goto L64;
    L68:
        this.letterDrawable.draw(r8);
        goto L82
    L64:
        if (r1513.type.equals("venue") == false) goto L66;
    L67:
        int r1514 = Theme.chat_inlineResultLocation.getIntrinsicWidth();
        int r23 = Theme.chat_inlineResultLocation.getIntrinsicHeight();
        int r32 = (int) (this.linkImageView.getImageX() + ((AndroidUtilities.dp(52.0f) - r1514) / 2));
        int r4 = (int) (this.linkImageView.getImageY() + ((AndroidUtilities.dp(52.0f) - r23) / 2));
        r8.drawRect(this.linkImageView.getImageX(), this.linkImageView.getImageY(), this.linkImageView.getImageX() + AndroidUtilities.dp(52.0f), this.linkImageView.getImageY() + AndroidUtilities.dp(52.0f), LetterDrawable.paint);
        Theme.chat_inlineResultLocation.setBounds(r32, r4, r1514 + r32, r23 + r4);
        Theme.chat_inlineResultLocation.draw(r8);
        goto L82
    L66:
        if (this.inlineResult.type.equals("geo") == false) goto L68;
    L57:
        if (r1512.type.equals(MediaStreamTrack.AUDIO_TRACK_KIND) == false) goto L59;
    L60:
        int r1515 = Theme.chat_inlineResultAudio.getIntrinsicWidth();
        int r24 = Theme.chat_inlineResultAudio.getIntrinsicHeight();
        int r33 = (int) (this.linkImageView.getImageX() + ((AndroidUtilities.dp(52.0f) - r1515) / 2));
        int r42 = (int) (this.linkImageView.getImageY() + ((AndroidUtilities.dp(52.0f) - r24) / 2));
        r8.drawRect(this.linkImageView.getImageX(), this.linkImageView.getImageY(), this.linkImageView.getImageX() + AndroidUtilities.dp(52.0f), this.linkImageView.getImageY() + AndroidUtilities.dp(52.0f), LetterDrawable.paint);
        Theme.chat_inlineResultAudio.setBounds(r33, r42, r1515 + r33, r24 + r42);
        Theme.chat_inlineResultAudio.draw(r8);
        goto L82
    L59:
        if (this.inlineResult.type.equals("voice") == false) goto L61;
    L52:
        if (r1511.type.equals("file") == false) goto L54;
        int r1516 = Theme.chat_inlineResultFile.getIntrinsicWidth();
        int r25 = Theme.chat_inlineResultFile.getIntrinsicHeight();
        int r34 = (int) (this.linkImageView.getImageX() + ((AndroidUtilities.dp(52.0f) - r1516) / 2));
        int r43 = (int) (this.linkImageView.getImageY() + ((AndroidUtilities.dp(52.0f) - r25) / 2));
        r8.drawRect(this.linkImageView.getImageX(), this.linkImageView.getImageY(), this.linkImageView.getImageX() + AndroidUtilities.dp(52.0f), this.linkImageView.getImageY() + AndroidUtilities.dp(52.0f), LetterDrawable.paint);
        Theme.chat_inlineResultFile.setBounds(r34, r43, r1516 + r34, r25 + r43);
        Theme.chat_inlineResultFile.draw(r8);
        goto L82
    L41:
        if (PhotoViewer.isShowingImage(this.inlineResult) == true) goto L43;
        this.letterDrawable.setAlpha((int) ((1.0f - this.linkImageView.getCurrentAlpha()) * 255.0f));
        goto L44
    L5:
        if (r0.isChecked() == false) goto L7;
    L14:
        r15.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.backgroundPaint);
        r8 = r15;
        goto L15
    L7:
        if (this.linkImageView.hasBitmapImage() == false) goto L14;
        if (this.linkImageView.getCurrentAlpha() != 1.0f) goto L14;
        if (PhotoViewer.isShowingImage((MessageObject) this.parentObject) == false) goto L13;
        goto L13
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r1, boolean r2) {
        updateButtonState(true, r2);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r8) {
        super.onInitializeAccessibilityNodeInfo(r8);
        StringBuilder r0 = new StringBuilder();
        switch(this.documentAttachType) {
            case 1: goto L12;
            case 2: goto L11;
            case 3: goto L10;
            case 4: goto L9;
            case 5: goto L8;
            case 6: goto L7;
            case 7: goto L6;
            case 8: goto L5;
            default: goto L13;
        };
    L5:
        r0.append(LocaleController.getString(R.string.AttachLocation));
        goto L13
    L6:
        r0.append(LocaleController.getString(R.string.AttachPhoto));
        goto L13
    L7:
        r0.append(LocaleController.getString(R.string.AttachSticker));
        goto L13
    L8:
        r0.append(LocaleController.getString(R.string.AttachMusic));
        goto L13
    L9:
        r0.append(LocaleController.getString(R.string.AttachVideo));
        goto L13
    L10:
        r0.append(LocaleController.getString(R.string.AttachAudio));
        goto L13
    L11:
        r0.append(LocaleController.getString(R.string.AttachGif));
        goto L13
    L12:
        r0.append(LocaleController.getString(R.string.AttachDocument));
    L13:
        StaticLayout r1 = this.titleLayout;
        boolean r2 = false;
        if (r1 != null) goto L16;
    L18:
        boolean r12 = false;
    L19:
        StaticLayout r4 = this.descriptionLayout;
        if (r4 == null) goto L25;
        if (TextUtils.isEmpty(r4.getText()) == true) goto L25;
        r2 = true;
    L25:
        if (this.documentAttachType != 5) goto L29;
        if (r12 == false) goto L29;
        if (r2 == false) goto L29;
        r0.append(", ");
        r0.append(LocaleController.formatString("AccDescrMusicInfo", R.string.AccDescrMusicInfo, new Object[]{this.descriptionLayout.getText(), this.titleLayout.getText()}));
    L39:
        r8.setText(r0);
        CheckBox2 r02 = this.checkBox;
        if (r02 != null) goto L42;
    L44:
        int r03 = this.documentAttachType;
        if (r03 == 3) goto L49;
        if (r03 == 5) goto L49;
        return;
    L49:
        int r7 = getIconForCurrentState();
        if (r7 != 1) goto L52;
        String r72 = LocaleController.getString("AccActionPause", R.string.AccActionPause);
    L58:
        r8.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, r72));
        return;
    L52:
        if (r7 == 2) goto L56;
        if (r7 == 3) goto L55;
        r72 = LocaleController.getString("AccActionPlay", R.string.AccActionPlay);
        goto L58
    L55:
        r72 = LocaleController.getString("AccActionCancelDownload", R.string.AccActionCancelDownload);
        goto L58
    L56:
        r72 = LocaleController.getString("AccActionDownload", R.string.AccActionDownload);
        goto L58
    L42:
        if (r02.isChecked() == false) goto L44;
        r8.setCheckable(true);
        r8.setChecked(true);
    L29:
        if (r12 == true) goto L31;
    L34:
        if (r2 == false) goto L39;
        if (r0.length() <= 0) goto L38;
        r0.append(", ");
    L38:
        r0.append(this.descriptionLayout.getText());
        goto L39
    L31:
        if (r0.length() <= 0) goto L33;
        r0.append(", ");
    L33:
        r0.append(this.titleLayout.getText());
        goto L34
    L16:
        if (TextUtils.isEmpty(r1.getText()) == true) goto L18;
        r12 = true;
        goto L19
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v27 */
    @Override // android.widget.FrameLayout, android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onMeasure(int r37, int r38) {
        int r4 = 0;
        this.drawLinkImageView = false;
        ImageLocation r5 = null;
        this.descriptionLayout = null;
        this.titleLayout = null;
        this.linkLayout = null;
        this.currentPhotoObject = null;
        this.linkY = AndroidUtilities.dp(27.0f);
        if (this.inlineResult == null) goto L5;
    L8:
        int r7 = View.MeasureSpec.getSize(r37);
        int r13 = (r7 - AndroidUtilities.dp(AndroidUtilities.leftBaseline)) - AndroidUtilities.dp(8.0f);
        if (this.documentAttach == null) goto L12;
        ArrayList r0 = new ArrayList(this.documentAttach.thumbs);
    L11:
        ArrayList r9 = r0;
    L19:
        if (this.mediaWebpage == true) goto L60;
        TLRPC.BotInlineResult r02 = this.inlineResult;
        if (r02 == null) goto L60;
        String r03 = r02.title;
        if (r03 != null) goto L284;
        float r19 = 100.0f;
        float r20 = 8.0f;
    L35:
        String r04 = this.inlineResult.description;
        if (r04 != null) goto L282;
        float r21 = 1.0f;
        boolean r6 = true;
        char r8 = ' ';
        int r12 = r13;
        char r2 = '\n';
    L54:
        ?? r62 = r6;
        if (this.inlineResult.url != null) goto L276;
    L61:
        TLRPC.Document r05 = this.documentAttach;
        if (r05 != null) goto L64;
        TLRPC.BotInlineResult r06 = this.inlineResult;
        if (r06 != null) goto L82;
    L85:
        TLRPC.PhotoSize r07 = null;
        String r32 = null;
    L87:
        TLRPC.BotInlineResult r10 = this.inlineResult;
        if (r10 != null) goto L90;
        int r16 = 3;
        ArrayList r15 = r9;
        WebFile r82 = null;
        String r92 = null;
    L132:
        if (this.documentAttach == null) goto L143;
        int r102 = 0;
    L135:
        if (r102 >= this.documentAttach.attributes.size()) goto L143;
        TLRPC.DocumentAttribute r122 = this.documentAttach.attributes.get(r102);
        if ((r122 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L142;
        if ((r122 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L142;
        r102 = r102 + 1;
    L142:
        int r103 = r122.w;
        int r123 = r122.h;
    L144:
        if (r103 == 0) goto L146;
        if (r123 == 0) goto L146;
    L154:
        if (r103 == 0) goto L156;
        if (r123 == 0) goto L156;
    L158:
        if (this.documentAttach == null) goto L160;
    L163:
        String r14 = "_firstframe";
        if (this.mediaWebpage == false) goto L175;
        int r104 = Math.min(r103, r123) / 3;
        if (this.documentAttachType != 2) goto L174;
        String r3 = String.format(Locale.US, "%d_%d_b", new Object[]{Integer.valueOf((int) (r104 / AndroidUtilities.density)), 80});
        if (SharedConfig.isAutoplayGifs() == false) goto L170;
    L173:
        String r24 = r3;
        String r28 = r24;
    L176:
        ImageReceiver r33 = this.linkImageView;
        if (this.documentAttachType != 6) goto L179;
        boolean r105 = r62;
    L180:
        r33.setAspectFit(r105);
        TLRPC.TL_photoStrippedSize r34 = FileLoader.getStrippedPhotoSize(r15);
        if (r34 == null) goto L185;
        TLRPC.Document r106 = this.documentAttach;
        if (r106 == null) goto L186;
        r5 = ImageLocation.getForDocument(r34, r106);
        goto L185
    L186:
        TLRPC.Photo r107 = this.photoAttach;
        if (r107 == null) goto L185;
        r5 = ImageLocation.getForPhoto(r34, r107);
    L185:
        ImageLocation r27 = r5;
        if (this.documentAttachType != 2) goto L217;
        TLRPC.Document r08 = this.documentAttach;
        if (r08 == null) goto L212;
        TLRPC.VideoSize r09 = MessageObject.getDocumentVideoThumb(r08);
        if (r09 == null) goto L202;
        ImageReceiver r83 = this.linkImageView;
        ImageLocation r23 = ImageLocation.getForDocument(r09, this.documentAttach);
        StringBuilder r010 = new StringBuilder("100_100");
        if (SharedConfig.isAutoplayGifs() == false) goto L198;
    L200:
        r14 = _UrlKt.FRAGMENT_ENCODE_SET;
    L201:
        r010.append(r14);
        r83.setImage(r23, r010.toString(), ImageLocation.getForDocument(this.currentPhotoObject, this.documentAttach), r24, r27, r28, null, -1, r32, this.parentObject, 1);
    L236:
        if (SharedConfig.isAutoplayGifs() == false) goto L238;
    L241:
        this.linkImageView.setAllowStartAnimation(r62);
        this.linkImageView.startAnimation();
    L242:
        this.drawLinkImageView = r62;
    L244:
        if (this.mediaWebpage == false) goto L249;
        int r011 = View.MeasureSpec.getSize(r38);
        if (r011 != 0) goto L248;
        r011 = AndroidUtilities.dp(r19);
    L248:
        setMeasuredDimension(r7, r011);
        int r22 = (r7 - AndroidUtilities.dp(24.0f)) / 2;
        int r42 = (r011 - AndroidUtilities.dp(24.0f)) / 2;
        this.radialProgress.setProgressRect(r22, r42, AndroidUtilities.dp(24.0f) + r22, AndroidUtilities.dp(24.0f) + r42);
        this.radialProgress.setCircleRadius(AndroidUtilities.dp(12.0f));
        this.linkImageView.setImageCoords(0.0f, 0.0f, r7, r011);
    L272:
        CheckBox2 r25 = this.checkBox;
        if (r25 == null) goto L291;
        measureChildWithMargins(r25, r37, 0, r38, 0);
        return;
    L291:
        return;
    L249:
        StaticLayout r012 = this.titleLayout;
        if (r012 != null) goto L252;
    L254:
        StaticLayout r013 = this.descriptionLayout;
        if (r013 != null) goto L257;
    L259:
        StaticLayout r014 = this.linkLayout;
        if (r014 != null) goto L262;
    L264:
        setMeasuredDimension(View.MeasureSpec.getSize(r37), Math.max(AndroidUtilities.dp(68.0f), Math.max(AndroidUtilities.dp(52.0f), r4) + AndroidUtilities.dp(16.0f)) + (this.needDivider ? 1 : 0));
        int r015 = AndroidUtilities.dp(52.0f);
        if (LocaleController.isRTL == false) goto L267;
        int r43 = (View.MeasureSpec.getSize(r37) - AndroidUtilities.dp(r20)) - r015;
    L268:
        this.letterDrawable.setBounds(r43, AndroidUtilities.dp(r20), r43 + r015, AndroidUtilities.dp(60.0f));
        float r016 = r015;
        this.linkImageView.setImageCoords(r43, AndroidUtilities.dp(r20), r016, r016);
        int r017 = this.documentAttachType;
        if (r017 == r16) goto L271;
        if (r017 != 5) goto L272;
    L271:
        this.radialProgress.setCircleRadius(AndroidUtilities.dp(24.0f));
        this.radialProgress.setProgressRect(AndroidUtilities.dp(4.0f) + r43, AndroidUtilities.dp(12.0f), r43 + AndroidUtilities.dp(48.0f), AndroidUtilities.dp(56.0f));
        goto L272
    L267:
        r43 = AndroidUtilities.dp(r20);
        goto L268
    L262:
        if (r014.getLineCount() <= 0) goto L264;
        StaticLayout r018 = this.linkLayout;
        r4 = r4 + r018.getLineBottom(r018.getLineCount() - r62);
        goto L264
    L257:
        if (r013.getLineCount() == 0) goto L259;
        StaticLayout r019 = this.descriptionLayout;
        r4 = r4 + r019.getLineBottom(r019.getLineCount() - r62);
        goto L259
    L252:
        if (r012.getLineCount() == 0) goto L254;
        StaticLayout r020 = this.titleLayout;
        r4 = r020.getLineBottom(r020.getLineCount() - r62);
        goto L254
    L238:
        if (this.isKeyboard == true) goto L241;
        this.linkImageView.setAllowStartAnimation(false);
        this.linkImageView.stopAnimation();
        goto L242
    L198:
        if (this.isKeyboard == true) goto L200;
    L202:
        ImageLocation r021 = ImageLocation.getForDocument(this.documentAttach);
        if (this.isForceGif == false) goto L205;
        r021.imageType = 2;
    L205:
        ImageReceiver r84 = this.linkImageView;
        StringBuilder r93 = new StringBuilder("100_100");
        if (SharedConfig.isAutoplayGifs() == false) goto L208;
    L210:
        r14 = _UrlKt.FRAGMENT_ENCODE_SET;
    L211:
        r93.append(r14);
        r84.setImage(r021, r93.toString(), ImageLocation.getForDocument(this.currentPhotoObject, this.documentAttach), r24, r27, r28, null, this.documentAttach.size, r32, this.parentObject, 0);
        goto L236
    L208:
        if (this.isKeyboard == true) goto L210;
    L212:
        ImageReceiver r022 = this.linkImageView;
        if (r82 == null) goto L215;
        r022.setImage(ImageLocation.getForWebFile(r82), "100_100", ImageLocation.getForPhoto(this.currentPhotoObject, this.photoAttach), r24, r27, r28, null, -1, r32, this.parentObject, 1);
        goto L236
    L215:
        r022.setImage(ImageLocation.getForPath(r92), "100_100", ImageLocation.getForPhoto(this.currentPhotoObject, this.photoAttach), r24, r27, r28, null, -1, r32, this.parentObject, 1);
        goto L236
    L217:
        if (this.currentPhotoObject == null) goto L231;
        SvgHelper.SvgDrawable r29 = DocumentObject.getSvgThumb(this.documentAttach, Theme.key_windowBackgroundGray, r21);
        if (MessageObject.canAutoplayAnimatedSticker(this.documentAttach) == false) goto L224;
        ImageReceiver r023 = this.linkImageView;
        if (r29 == null) goto L223;
        r023.setImage(ImageLocation.getForDocument(this.documentAttach), "80_80", null, null, r27, r28, r29, this.currentPhotoObject.size, r32, this.parentObject, 0);
        goto L236
    L223:
        r023.setImage(ImageLocation.getForDocument(this.documentAttach), "80_80", ImageLocation.getForDocument(this.currentPhotoObject, this.documentAttach), r28, r27, r28, null, this.currentPhotoObject.size, r32, this.parentObject, 0);
        goto L236
    L224:
        TLRPC.Document r35 = this.documentAttach;
        if (r35 == null) goto L230;
        ImageReceiver r52 = this.linkImageView;
        if (r29 == null) goto L229;
        r52.setImage(ImageLocation.getForDocument(this.currentPhotoObject, r35), r24, null, null, r27, r28, r29, this.currentPhotoObject.size, r32, this.parentObject, 0);
        goto L236
    L229:
        r52.setImage(ImageLocation.getForDocument(this.currentPhotoObject, r35), r24, ImageLocation.getForPhoto(r07, this.photoAttach), r28, r27, r28, null, this.currentPhotoObject.size, r32, this.parentObject, 0);
        goto L236
    L230:
        this.linkImageView.setImage(ImageLocation.getForPhoto(this.currentPhotoObject, this.photoAttach), r24, ImageLocation.getForPhoto(r07, this.photoAttach), r28, r27, r28, null, this.currentPhotoObject.size, r32, this.parentObject, 0);
        goto L236
    L231:
        ImageReceiver r36 = this.linkImageView;
        if (r82 == null) goto L234;
        r36.setImage(ImageLocation.getForWebFile(r82), r24, ImageLocation.getForPhoto(r07, this.photoAttach), r28, r27, r28, null, -1, r32, this.parentObject, 1);
        goto L236
    L234:
        r36.setImage(ImageLocation.getForPath(r92), r24, ImageLocation.getForPhoto(r07, this.photoAttach), r28, r27, r28, null, -1, r32, this.parentObject, 1);
        goto L236
    L179:
        r105 = false;
        goto L180
    L170:
        if (this.isKeyboard == true) goto L173;
        String r108 = r3.concat("_firstframe");
        String r39 = r3.concat("_firstframe");
    L172:
        r24 = r39;
        r28 = r108;
        goto L176
    L174:
        r39 = String.format(Locale.US, "%d_%d", new Object[]{Integer.valueOf((int) (r104 / AndroidUtilities.density)), 80});
        r108 = r39.concat("_b");
        goto L172
    L175:
        r28 = "52_52_b";
        r24 = "52_52";
        goto L176
    L160:
        if (this.currentPhotoObject != null) goto L163;
        if (r82 != null) goto L163;
        if (r92 == null) goto L244;
    L156:
        r103 = AndroidUtilities.dp(80.0f);
        r123 = r103;
    L146:
        TLRPC.PhotoSize r132 = this.currentPhotoObject;
        if (r132 == null) goto L151;
        if (r07 == null) goto L150;
        r07.size = -1;
    L150:
        r103 = r132.w;
        r123 = r132.h;
        goto L154
    L151:
        TLRPC.BotInlineResult r133 = this.inlineResult;
        if (r133 == null) goto L154;
        int[] r109 = MessageObject.getInlineResultWidthAndHeight(r133);
        int r124 = r109[0];
        r123 = r109[r62];
        r103 = r124;
    L143:
        r103 = 0;
        r123 = 0;
        goto L144
    L90:
        if ((r10.content instanceof TLRPC.TL_webDocument) == false) goto L108;
        String r1010 = r10.type;
        if (r1010 == null) goto L108;
        boolean r1011 = r1010.startsWith("gif");
        TLRPC.BotInlineResult r125 = this.inlineResult;
        if (r1011 == false) goto L103;
        TLRPC.WebDocument r1012 = r125.thumb;
        if ((r1012 instanceof TLRPC.TL_webDocument) == true) goto L98;
    L100:
        TLRPC.TL_webDocument r1013 = (TLRPC.TL_webDocument) this.inlineResult.content;
    L101:
        this.documentAttachType = 2;
    L109:
        if (r1013 != null) goto L113;
        TLRPC.WebDocument r126 = this.inlineResult.thumb;
        if ((r126 instanceof TLRPC.TL_webDocument) == false) goto L113;
        r1013 = (TLRPC.TL_webDocument) r126;
    L113:
        if (r1013 == null) goto L115;
    L122:
        r16 = 3;
        r15 = r9;
        r82 = null;
        r92 = null;
    L128:
        if (r1013 == null) goto L132;
        r82 = WebFile.createWithWebDocument(r1013);
        goto L132
    L115:
        if (this.currentPhotoObject != null) goto L122;
        if (r07 != null) goto L122;
        TLRPC.BotInlineMessage r127 = this.inlineResult.send_message;
        if ((r127 instanceof TLRPC.TL_botInlineMessageMediaVenue) == false) goto L120;
    L123:
        TLRPC.GeoPoint r128 = r127.geo;
        double r134 = r128.lat;
        r16 = 3;
        r15 = r9;
        double r85 = r128._long;
        if (MessagesController.getInstance(this.currentAccount).mapProvider != 2) goto L126;
        r82 = WebFile.createWithGeoPoint(this.inlineResult.send_message.geo, 72, 72, 15, Math.min(2, (int) Math.ceil(AndroidUtilities.density)));
        r92 = null;
        goto L128
    L126:
        r92 = AndroidUtilities.formapMapUrl(this.currentAccount, r134, r85, 72, 72, true, 15, -1);
        r82 = null;
        goto L128
    L120:
        if ((r127 instanceof TLRPC.TL_botInlineMessageMediaGeo) == false) goto L122;
    L98:
        if ("video/mp4".equals(r1012.mime_type) == false) goto L100;
        r1013 = (TLRPC.TL_webDocument) this.inlineResult.thumb;
        goto L101
    L103:
        if (r125.type.equals("photo") == false) goto L108;
        TLRPC.BotInlineResult r1014 = this.inlineResult;
        TLRPC.WebDocument r129 = r1014.thumb;
        if ((r129 instanceof TLRPC.TL_webDocument) == false) goto L107;
        r1013 = (TLRPC.TL_webDocument) r129;
        goto L109
    L107:
        r1013 = (TLRPC.TL_webDocument) r1014.content;
    L108:
        r1013 = null;
        goto L109
    L82:
        if (r06.photo == null) goto L85;
        this.currentPhotoObject = FileLoader.getClosestPhotoSizeWithSize(r9, AndroidUtilities.getPhotoSize(), r62, null, r62);
        r07 = FileLoader.getClosestPhotoSizeWithSize(r9, 80, false, null, r62);
        if (r07 == this.currentPhotoObject) goto L85;
        r32 = null;
        goto L87
    L64:
        if (this.isForceGif == false) goto L66;
    L78:
        this.currentPhotoObject = FileLoader.getClosestPhotoSizeWithSize(this.documentAttach.thumbs, 90, false, null, r62);
        goto L85
    L66:
        if (MessageObject.isGifDocument(r05) == true) goto L78;
        if (MessageObject.isStickerDocument(this.documentAttach) == false) goto L71;
    L77:
        this.currentPhotoObject = FileLoader.getClosestPhotoSizeWithSize(this.documentAttach.thumbs, 90, false, null, r62);
        r32 = "webp";
        r07 = null;
        goto L87
    L71:
        if (MessageObject.isAnimatedStickerDocument(this.documentAttach, r62) == true) goto L77;
        int r024 = this.documentAttachType;
        if (r024 == 5) goto L85;
        if (r024 == 3) goto L85;
        this.currentPhotoObject = FileLoader.getClosestPhotoSizeWithSize(this.documentAttach.thumbs, 90, false, null, r62);
        goto L85
    L276:
        int r025 = (int) Math.ceil(Theme.chat_contextResult_descriptionTextPaint.measureText(r0));     // Catch: Exception -> L58
        this.linkLayout = new StaticLayout(TextUtils.ellipsize(this.inlineResult.url.replace(r2, r8), Theme.chat_contextResult_descriptionTextPaint, Math.min(r025, r12), TextUtils.TruncateAt.MIDDLE), Theme.chat_contextResult_descriptionTextPaint, r12, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L58
        r62 = r6;
    L58:
        e = move-exception;
        FileLog.e(e);
        r62 = r6;
        goto L61
    L282:
        boolean r63 = true;
        r12 = r13;
        r2 = '\n';
        r21 = 1.0f;
        r8 = ' ';
        StaticLayout r026 = ChatMessageCell.generateStaticLayout(Emoji.replaceEmoji(r04, Theme.chat_contextResult_descriptionTextPaint.getFontMetricsInt(), false), Theme.chat_contextResult_descriptionTextPaint, r12, r13, 0, 3);     // Catch: Exception -> L45
        this.descriptionLayout = r026;     // Catch: Exception -> L45
        r6 = true;
        if (r026.getLineCount() <= 0) goto L54;
        int r027 = this.descriptionY;     // Catch: Exception -> L45
        StaticLayout r1015 = this.descriptionLayout;     // Catch: Exception -> L45
        this.linkY = (r027 + r1015.getLineBottom(r1015.getLineCount() - 1)) + AndroidUtilities.dp(1.0f);     // Catch: Exception -> L45
        r6 = true;
    L45:
        e = e;
    L52:
        FileLog.e(e);
        r6 = r63;
    L47:
        e = e;
        r21 = 1.0f;
        r63 = true;
    L49:
        r8 = ' ';
        r12 = r13;
        r2 = '\n';
    L50:
        e = e;
        r21 = 1.0f;
        r63 = true;
        goto L49
    L284:
        r19 = 100.0f;
        r20 = 8.0f;
        this.titleLayout = new StaticLayout(TextUtils.ellipsize(Emoji.replaceEmoji(r03.replace('\n', ' '), Theme.chat_contextResult_titleTextPaint.getFontMetricsInt(), false), Theme.chat_contextResult_titleTextPaint, r13, TextUtils.TruncateAt.END), Theme.chat_contextResult_titleTextPaint, r13 + AndroidUtilities.dp(4.0f), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L28
    L33:
        this.letterDrawable.setTitle(this.inlineResult.title);
    L28:
        e = e;
    L32:
        FileLog.e(e);
    L30:
        e = e;
        r19 = 100.0f;
        r20 = 8.0f;
    L60:
        r19 = 100.0f;
        r20 = 8.0f;
        r21 = 1.0f;
        r62 = 1;
        goto L61
    L12:
        TLRPC.BotInlineResult r028 = this.inlineResult;
        if (r028 != null) goto L15;
    L17:
        r9 = null;
        goto L19
    L15:
        if (r028.photo == null) goto L17;
        r0 = new ArrayList(this.inlineResult.photo.sizes);
        goto L11
    L5:
        if (this.documentAttach != null) goto L8;
        setMeasuredDimension(AndroidUtilities.dp(100.0f), AndroidUtilities.dp(100.0f));
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r1, long r2, long r4) {
        this.radialProgress.setProgress(Math.min(1.0f, r2 / r4), true);
        int r12 = this.documentAttachType;
        if (r12 == 3) goto L12;
        if (r12 == 5) goto L12;
        if (this.buttonState == 1) goto L15;
        updateButtonState(false, true);
        return;
    L15:
        return;
    L12:
        if (this.buttonState == 4) goto L16;
        updateButtonState(false, true);
        return;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onSuccessDownload(String r3) {
        this.fileExist = true;
        this.radialProgress.setProgress(1.0f, true);
        updateButtonState(false, true);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r9) {
        if (this.mediaWebpage == true) goto L61;
        if (this.delegate == null) goto L61;
        if (this.inlineResult == null) goto L61;
        int r0 = (int) r9.getX();
        int r1 = (int) r9.getY();
        AndroidUtilities.dp(48.0f);
        int r2 = this.documentAttachType;
        boolean r5 = true;
        if (r2 != 3) goto L12;
    L39:
        boolean r02 = this.letterDrawable.getBounds().contains(r0, r1);
        if (r9.getAction() != 0) goto L44;
        if (r02 == true) goto L42;
    L38:
        r5 = false;
    L56:
        if (r5 == false) goto L58;
        return r5;
    L58:
        return super.onTouchEvent(r9);
    L42:
        this.buttonPressed = true;
        this.radialProgress.setPressed(true, false);
        invalidate();
        goto L56
    L44:
        if (this.buttonPressed == false) goto L38;
        if (r9.getAction() != 1) goto L49;
        this.buttonPressed = false;
        playSoundEffect(0);
        didPressedButton();
        invalidate();
    L55:
        this.radialProgress.setPressed(this.buttonPressed, false);
        goto L38
    L49:
        if (r9.getAction() != 3) goto L52;
        this.buttonPressed = false;
        invalidate();
        goto L55
    L52:
        if (r9.getAction() != 2) goto L55;
        if (r02 == true) goto L55;
        this.buttonPressed = false;
        invalidate();
        goto L55
    L12:
        if (r2 == 5) goto L39;
        TLRPC.BotInlineResult r22 = this.inlineResult;
        if (r22 == null) goto L38;
        TLRPC.WebDocument r23 = r22.content;
        if (r23 == null) goto L38;
        if (TextUtils.isEmpty(r23.url) == true) goto L38;
        if (r9.getAction() != 0) goto L26;
        if (this.letterDrawable.getBounds().contains(r0, r1) == false) goto L38;
        this.buttonPressed = true;
        goto L56
    L26:
        if (this.buttonPressed == false) goto L38;
        if (r9.getAction() != 1) goto L31;
        this.buttonPressed = false;
        playSoundEffect(0);
        this.delegate.didPressedImage(this);
        goto L38
    L31:
        if (r9.getAction() != 3) goto L34;
        this.buttonPressed = false;
        goto L38
    L34:
        if (r9.getAction() != 2) goto L38;
        if (this.letterDrawable.getBounds().contains(r0, r1) == true) goto L38;
        this.buttonPressed = false;
    L61:
        return super.onTouchEvent(r9);
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int r3, Bundle r4) {
        if (r3 != 16) goto L11;
        int r0 = this.documentAttachType;
        if (r0 != 3) goto L7;
    L8:
        didPressedButton();
        return true;
    L7:
        if (r0 == 5) goto L8;
    L11:
        return super.performAccessibilityAction(r3, r4);
    }

    public void setCanPreviewGif(boolean r1) {
        this.canPreviewGif = r1;
    }

    public void setChecked(boolean r6, boolean r7) {
        CheckBox2 r0 = this.checkBox;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.getVisibility() == 0) goto L8;
        this.checkBox.setVisibility(0);
    L8:
        this.checkBox.setChecked(r6, r7);
        AnimatorSet r02 = this.animator;
        if (r02 == null) goto L11;
        r02.cancel();
        this.animator = null;
    L11:
        float r03 = 1.0f;
        if (r7 == false) goto L18;
        AnimatorSet r72 = new AnimatorSet();
        this.animator = r72;
        Property<ContextLinkCell, Float> r2 = this.IMAGE_SCALE;
        if (r6 == false) goto L16;
        r03 = 0.81f;
    L16:
        r72.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, r2, new float[]{r03})});
        this.animator.setDuration(200);
        this.animator.addListener(new AnonymousClass3(this, r6));
        this.animator.start();
        return;
    L18:
        if (r6 == false) goto L20;
        r03 = 0.85f;
    L20:
        this.imageScale = r03;
        invalidate();
    }

    public void setDelegate(ContextLinkCellDelegate r1) {
        this.delegate = r1;
    }

    public void setGif(TLRPC.Document r1, Object r2, int r3, boolean r4) {
        this.needDivider = r4;
        this.needShadow = false;
        this.currentDate = r3;
        this.inlineResult = null;
        this.parentObject = r2;
        this.documentAttach = r1;
        this.photoAttach = null;
        this.mediaWebpage = true;
        this.isForceGif = true;
        setAttachType();
        this.documentAttachType = 2;
        requestLayout();
        this.fileName = null;
        this.cacheFile = null;
        this.fileExist = false;
        this.resolvingFileName = false;
        updateButtonState(false, false);
    }

    public void setGif(TLRPC.Document r3, boolean r4) {
        setGif(r3, "gif" + r3, 0, r4);
    }

    public void setIsKeyboard(boolean r1) {
        this.isKeyboard = r1;
    }

    public void setLink(TLRPC.BotInlineResult r1, TLRPC.User r2, boolean r3, boolean r4, boolean r5, boolean r6) {
        this.needDivider = r4;
        this.needShadow = r5;
        this.inlineBot = r2;
        this.inlineResult = r1;
        this.parentObject = r1;
        if (r1 == null) goto L5;
        this.documentAttach = r1.document;
        this.photoAttach = r1.photo;
    L6:
        this.mediaWebpage = r3;
        this.isForceGif = r6;
        setAttachType();
        if (r6 == false) goto L9;
        this.documentAttachType = 2;
    L9:
        requestLayout();
        this.fileName = null;
        this.cacheFile = null;
        this.fileExist = false;
        this.resolvingFileName = false;
        updateButtonState(false, false);
        return;
    L5:
        this.documentAttach = null;
        this.photoAttach = null;
        goto L6
    }

    @Override // android.view.View
    public void setPressed(boolean r2) {
        super.setPressed(r2);
        ButtonBounce r0 = this.buttonBounce;
        if (r0 == null) goto L12;
        if (r2 == false) goto L6;
    L9:
        boolean r1 = true;
    L10:
        r0.setPressed(r1);
        return;
    L6:
        if (this.scaled == true) goto L9;
        r1 = false;
        goto L10
    }

    public void setScaled(boolean r2) {
        this.scaled = r2;
        ButtonBounce r22 = this.buttonBounce;
        if (r22 != null) goto L5;
        return;
    L5:
        if (isPressed() == false) goto L7;
    L10:
        boolean r1 = true;
    L11:
        r22.setPressed(r1);
        return;
    L7:
        if (this.scaled == true) goto L10;
        r1 = false;
        goto L11
    }

    public boolean showingBitmap() {
        if (this.linkImageView.getBitmap() == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void updateButtonState(boolean r9, boolean r10) {
        String r0 = this.fileName;
        if (r0 != null) goto L9;
        if (this.resolvingFileName == true) goto L9;
        this.resolvingFileName = true;
        int r102 = this.resolveFileNameId;
        this.resolveFileNameId = r102;
        Utilities.searchQueue.postRunnable(new AnonymousClass1(this, r102, r9));
        this.radialProgress.setIcon(4, r9, false);
        return;
    L9:
        if (TextUtils.isEmpty(r0) == false) goto L13;
        this.buttonState = -1;
        this.radialProgress.setIcon(4, r9, false);
        return;
    L13:
        if (this.documentAttach == null) goto L15;
        boolean r02 = FileLoader.getInstance(this.currentAccount).isLoadingFile(this.fileName);
    L17:
        if (r02 == false) goto L19;
    L35:
        DownloadController.getInstance(this.currentAccount).addLoadingFileObserver(this.fileName, this);
        int r4 = this.documentAttachType;
        float r7 = 0.0f;
        if (r4 == 5) goto L43;
        if (r4 == 3) goto L43;
        this.buttonState = 1;
        Float r03 = ImageLoader.getInstance().getFileProgress(this.fileName);
        if (r03 == null) goto L42;
        r7 = r03.floatValue();
    L42:
        this.radialProgress.setProgress(r7, false);
    L49:
        this.radialProgress.setIcon(getIconForCurrentState(), r9, r10);
        invalidate();
        return;
    L43:
        if (r02 == true) goto L45;
        this.buttonState = 2;
        goto L49
    L45:
        this.buttonState = 4;
        Float r04 = ImageLoader.getInstance().getFileProgress(this.fileName);
        RadialProgress2 r1 = this.radialProgress;
        if (r04 == null) goto L48;
        r1.setProgress(r04.floatValue(), r10);
        goto L49
    L48:
        r1.setProgress(0.0f, r10);
        goto L49
    L19:
        if (this.fileExist == false) goto L35;
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        int r05 = this.documentAttachType;
        if (r05 == 5) goto L26;
        if (r05 == 3) goto L26;
        this.buttonState = -1;
    L26:
        boolean r06 = MediaController.getInstance().isPlayingMessage(this.currentMessageObject);
        if (r06 == false) goto L33;
        if (r06 == true) goto L30;
    L32:
        this.buttonState = 1;
    L34:
        this.radialProgress.setProgress(1.0f, r10);
        goto L49
    L30:
        if (MediaController.getInstance().isMessagePaused() == false) goto L32;
    L33:
        this.buttonState = 0;
        goto L34
    L15:
        r02 = ImageLoader.getInstance().isLoadingHttpFile(this.fileName);
        goto L17
    }
}
