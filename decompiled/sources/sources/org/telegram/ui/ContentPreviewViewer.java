package org.telegram.ui;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.exteragram.messenger.utils.system.VibratorUtils;
import java.util.ArrayList;
import java.util.List;
import me.vkryl.core.reference.ReferenceList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.WebFile;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ContextLinkCell;
import org.telegram.ui.Cells.StickerCell;
import org.telegram.ui.Cells.StickerEmojiCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EmojiPacksAlert;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PaintingOverlay;
import org.telegram.ui.Components.Reactions.CustomEmojiReactionsWindow;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.ReactionsContainerLayout;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScrimOptions;
import org.telegram.ui.Components.StickersDialogs;
import org.telegram.ui.Components.SuggestEmojiView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceBitmap;
import org.telegram.ui.Components.blur3.utils.Blur3Utils;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Components.poll.RecentVotersCell;
import org.telegram.ui.Stories.DarkThemeResourceProvider;

/* loaded from: classes6.dex */
public class ContentPreviewViewer {

    @SuppressLint({"StaticFieldLeak"})
    private static volatile ContentPreviewViewer Instance;
    private static TextPaint textPaint;
    private ColorDrawable backgroundDrawable;
    private float blurProgress;
    private Bitmap blurrBitmap;
    public ImageReceiver centerImage;
    private boolean clearsInputField;
    private boolean closeOnDismiss;
    private FrameLayoutDrawer containerView;
    private int currentAccount;
    private int currentContentType;
    private TLRPC.Document currentDocument;
    private float currentMoveY;
    private float currentMoveYProgress;
    private View currentPreviewCell;
    private String currentQuery;
    private TLRPC.InputStickerSet currentStickerSet;
    private ContentPreviewViewerDelegate delegate;
    private boolean drawEffect;
    private ImageReceiver effectImage;
    private float finalMoveY;
    private SendMessagesHelper.ImportingSticker importingSticker;
    private TLRPC.BotInlineResult inlineResult;
    private boolean isPhotoEditor;
    private boolean isRecentSticker;
    private boolean isStickerEditor;
    private boolean isVisible;
    private int keyboardHeight;
    private Insets lastInsets;
    private float lastTouchY;
    private long lastUpdateTime;
    private boolean menuVisible;
    private float moveY;
    private Runnable openPreviewRunnable;
    private final Paint paint;
    public PaintingOverlay paintingOverlay;
    private Path paintingOverlayClipPath;
    private Activity parentActivity;
    private Object parentObject;
    private View popupLayout;
    ActionBarPopupWindow popupWindow;
    private boolean preparingBitmap;
    private ReactionsContainerLayout reactionsLayout;
    private FrameLayout reactionsLayoutContainer;
    private Theme.ResourcesProvider resourcesProvider;
    private final BlurredBackgroundDrawableViewFactory scrimBlur3Factory;
    private final BlurredBackgroundSourceBitmap scrimBlur3SourceBitmap;
    private ArrayList<String> selectedEmojis;
    private float showProgress;
    private final Runnable showSheetRunnable;
    private Drawable slideUpDrawable;
    private float startMoveY;
    private int startX;
    private int startY;
    private StaticLayout stickerEmojiLayout;
    private TLRPC.TL_messages_stickerSet stickerSetForCustomSticker;
    private UnlockPremiumView unlockPremiumView;
    private VibrationEffect vibrationEffect;
    private WindowManager.LayoutParams windowLayoutParams;
    private FrameLayout windowView;

    /* renamed from: org.telegram.ui.ContentPreviewViewer$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ContentPreviewViewer this$0;

        /* renamed from: org.telegram.ui.ContentPreviewViewer$1$1, reason: invalid class name and collision with other inner class name */
        public class C00631 extends ActionBarPopupWindow {
            final /* synthetic */ AnonymousClass1 this$1;

            public C00631(AnonymousClass1 r1, View r2, int r3, int r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow, android.widget.PopupWindow
            public void dismiss() {
                super.dismiss();
                ContentPreviewViewer r0 = this.this$1.this$0;
                r0.popupWindow = null;
                ContentPreviewViewer.m14140$$Nest$fputmenuVisible(r0, false);
                if (ContentPreviewViewer.m14102$$Nest$fgetcloseOnDismiss(this.this$1.this$0) == false) goto L6;
                this.this$1.this$0.close();
                return;
            }
        }

        /* renamed from: org.telegram.ui.ContentPreviewViewer$1$2, reason: invalid class name */
        public class AnonymousClass2 implements View.OnClickListener {
            final /* synthetic */ AnonymousClass1 this$1;
            final /* synthetic */ ArrayList val$actions;
            final /* synthetic */ boolean val$inFavs;

            public static /* synthetic */ void $r8$lambda$QmPIlbmjY_8wQ1mc2ysPrpigN6Q(Uri r2) {
                NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{10});
            }

            public static /* synthetic */ void $r8$lambda$WAv1zb1nofkSl4f4cXds_KrE1ZY(ContentPreviewViewerDelegate r0, TLRPC.Document r1, String r2, Object r3, boolean r4, int r5, int r6) {
                r0.sendSticker(r1, r2, r3, r4, r5, r6);
            }

            public AnonymousClass2(AnonymousClass1 r1, ArrayList r2, boolean r3) {
                this.this$1 = r1;
                this.val$actions = r2;
                this.val$inFavs = r3;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View r11) {
                if (ContentPreviewViewer.m14124$$Nest$fgetparentActivity(this.this$1.this$0) == null) goto L48;
                int r112 = ((Integer) r11.getTag()).intValue();
                boolean r1 = true;
                if (((Integer) this.val$actions.get(r112)).intValue() == 0) goto L40;
                if (((Integer) this.val$actions.get(r112)).intValue() == 6) goto L40;
                if (((Integer) this.val$actions.get(r112)).intValue() != 1) goto L16;
                if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$1.this$0) == null) goto L46;
                ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$1.this$0).openSet(ContentPreviewViewer.m14110$$Nest$fgetcurrentStickerSet(this.this$1.this$0), ContentPreviewViewer.m14101$$Nest$fgetclearsInputField(this.this$1.this$0));
            L46:
                ContentPreviewViewer.m14146$$Nest$mdismissPopupWindow(this.this$1.this$0);
                return;
            L16:
                if (((Integer) this.val$actions.get(r112)).intValue() != 2) goto L19;
                MediaDataController.getInstance(ContentPreviewViewer.m14104$$Nest$fgetcurrentAccount(this.this$1.this$0)).addRecentSticker(2, ContentPreviewViewer.m14125$$Nest$fgetparentObject(this.this$1.this$0), ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$1.this$0), (int) (System.currentTimeMillis() / 1000), this.val$inFavs);
                goto L46
            L19:
                if (((Integer) this.val$actions.get(r112)).intValue() != 3) goto L25;
                TLRPC.Document r113 = ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$1.this$0);
                Object r0 = ContentPreviewViewer.m14125$$Nest$fgetparentObject(this.this$1.this$0);
                String r12 = ContentPreviewViewer.m14109$$Nest$fgetcurrentQuery(this.this$1.this$0);
                ContentPreviewViewerDelegate r2 = ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$1.this$0);
                if (r2 != null) goto L23;
                return;
            L23:
                AlertsCreator.createScheduleDatePickerDialog(ContentPreviewViewer.m14124$$Nest$fgetparentActivity(this.this$1.this$0), r2.getDialogId(), new ContentPreviewViewer$1$2$$ExternalSyntheticLambda0(r2, r113, r12, r0));
                goto L46
            L25:
                if (((Integer) this.val$actions.get(r112)).intValue() != 4) goto L28;
                MediaDataController.getInstance(ContentPreviewViewer.m14104$$Nest$fgetcurrentAccount(this.this$1.this$0)).addRecentSticker(0, ContentPreviewViewer.m14125$$Nest$fgetparentObject(this.this$1.this$0), ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$1.this$0), (int) (System.currentTimeMillis() / 1000), true);
                goto L46
            L28:
                if (((Integer) this.val$actions.get(r112)).intValue() != 5) goto L31;
                ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$1.this$0).remove(ContentPreviewViewer.m14115$$Nest$fgetimportingSticker(this.this$1.this$0));
                goto L46
            L31:
                if (((Integer) this.val$actions.get(r112)).intValue() != 10) goto L34;
                ChatUtils.getInstance().saveStickerToGallery(ContentPreviewViewer.m14124$$Nest$fgetparentActivity(this.this$1.this$0), ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$1.this$0), new ContentPreviewViewer$1$2$$ExternalSyntheticLambda1());
                goto L46
            L34:
                if (((Integer) this.val$actions.get(r112)).intValue() != 7) goto L37;
                ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$1.this$0).editSticker(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$1.this$0));
                goto L46
            L37:
                if (((Integer) this.val$actions.get(r112)).intValue() != 8) goto L46;
                ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$1.this$0).deleteSticker(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$1.this$0));
            L40:
                if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$1.this$0) == null) goto L46;
                ContentPreviewViewerDelegate r22 = ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$1.this$0);
                TLRPC.Document r3 = ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$1.this$0);
                String r4 = ContentPreviewViewer.m14109$$Nest$fgetcurrentQuery(this.this$1.this$0);
                Object r5 = ContentPreviewViewer.m14125$$Nest$fgetparentObject(this.this$1.this$0);
                if (((Integer) this.val$actions.get(r112)).intValue() == 0) goto L45;
                r1 = false;
            L45:
                r22.sendSticker(r3, r4, r5, r1, 0, 0);
                goto L46
            }
        }

        /* renamed from: org.telegram.ui.ContentPreviewViewer$1$3, reason: invalid class name */
        public class AnonymousClass3 extends ActionBarPopupWindow {
            final /* synthetic */ AnonymousClass1 this$1;

            public AnonymousClass3(AnonymousClass1 r1, View r2, int r3, int r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow, android.widget.PopupWindow
            public void dismiss() {
                super.dismiss();
                ContentPreviewViewer r0 = this.this$1.this$0;
                r0.popupWindow = null;
                ContentPreviewViewer.m14140$$Nest$fputmenuVisible(r0, false);
                if (ContentPreviewViewer.m14102$$Nest$fgetcloseOnDismiss(this.this$1.this$0) == false) goto L6;
                this.this$1.this$0.close();
            L6:
                if (ContentPreviewViewer.m14108$$Nest$fgetcurrentPreviewCell(this.this$1.this$0) == null) goto L18;
                boolean r02 = ContentPreviewViewer.m14108$$Nest$fgetcurrentPreviewCell(this.this$1.this$0) instanceof StickerEmojiCell;
                AnonymousClass1 r3 = this.this$1;
                if (r02 == false) goto L10;
                ((StickerEmojiCell) ContentPreviewViewer.m14108$$Nest$fgetcurrentPreviewCell(r3.this$0)).setScaled(false);
            L16:
                ContentPreviewViewer.m14138$$Nest$fputcurrentPreviewCell(this.this$1.this$0, null);
                return;
            L10:
                boolean r03 = ContentPreviewViewer.m14108$$Nest$fgetcurrentPreviewCell(r3.this$0) instanceof StickerCell;
                AnonymousClass1 r32 = this.this$1;
                if (r03 == false) goto L14;
                ((StickerCell) ContentPreviewViewer.m14108$$Nest$fgetcurrentPreviewCell(r32.this$0)).setScaled(false);
                goto L16
            L14:
                if ((ContentPreviewViewer.m14108$$Nest$fgetcurrentPreviewCell(r32.this$0) instanceof ContextLinkCell) == false) goto L16;
                ((ContextLinkCell) ContentPreviewViewer.m14108$$Nest$fgetcurrentPreviewCell(this.this$1.this$0)).setScaled(false);
                goto L16
            }
        }

        /* renamed from: org.telegram.ui.ContentPreviewViewer$1$4, reason: invalid class name */
        public class AnonymousClass4 extends ActionBarPopupWindow {
            final /* synthetic */ AnonymousClass1 this$1;

            public AnonymousClass4(AnonymousClass1 r1, View r2, int r3, int r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow, android.widget.PopupWindow
            public void dismiss() {
                super.dismiss();
                ContentPreviewViewer r0 = this.this$1.this$0;
                r0.popupWindow = null;
                ContentPreviewViewer.m14140$$Nest$fputmenuVisible(r0, false);
                if (ContentPreviewViewer.m14102$$Nest$fgetcloseOnDismiss(this.this$1.this$0) == false) goto L6;
                this.this$1.this$0.close();
                return;
            }
        }

        /* renamed from: org.telegram.ui.ContentPreviewViewer$1$5, reason: invalid class name */
        public class AnonymousClass5 extends ActionBarPopupWindow {
            final /* synthetic */ AnonymousClass1 this$1;

            public AnonymousClass5(AnonymousClass1 r1, View r2, int r3, int r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow, android.widget.PopupWindow
            public void dismiss() {
                super.dismiss();
                ContentPreviewViewer r0 = this.this$1.this$0;
                r0.popupWindow = null;
                ContentPreviewViewer.m14140$$Nest$fputmenuVisible(r0, false);
                if (ContentPreviewViewer.m14102$$Nest$fgetcloseOnDismiss(this.this$1.this$0) == false) goto L6;
                this.this$1.this$0.close();
                return;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: $r8$lambda$-xZ6OpnvmIxb3UCjxjQ2ZX130uQ, reason: not valid java name */
        public static /* synthetic */ void m14151$r8$lambda$xZ6OpnvmIxb3UCjxjQ2ZX130uQ(ContentPreviewViewerDelegate r0, TLRPC.Document r1, TLRPC.BotInlineResult r2, Object r3, boolean r4, int r5, int r6) {
            if (r1 != null) goto L5;
            r1 = r2;
        L5:
            r0.sendGif(r1, r3, r4, r5, r6);
        }

        public static /* synthetic */ void $r8$lambda$1vDXubHeziR91yHzbi8gXxSEzhI(AnonymousClass1 r0, ArrayList r1, View r2) {
            r0.lambda$run$12(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$3tVZoAvMxS4uMwguhJmRYtE0OmQ(Uri r2) {
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{11});
        }

        /* renamed from: $r8$lambda$7GcV3heHV-DciB0lbyFx80UtpM4, reason: not valid java name */
        public static /* synthetic */ void m14152$r8$lambda$7GcV3heHVDciB0lbyFx80UtpM4(AnonymousClass1 r0, ArrayList r1, RecyclerListView r2, LinearLayout r3, ActionBarPopupWindow.ActionBarPopupWindowLayout r4, View r5) {
            r0.lambda$run$5(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$HKEhKkLkNP2q11GEM3TElz5yvwg(AnonymousClass1 r0, Utilities.Callback r1, Boolean r2) {
            r0.lambda$run$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$Tak9QAnXNbORUcoMkLBprqeE_Mk(AnonymousClass1 r0, ValueAnimator r1) {
            r0.lambda$run$13(r1);
        }

        public static /* synthetic */ void $r8$lambda$XyL97JVdUWflIJvPstsWsjyhZNA(Uri r2) {
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{12});
        }

        public static /* synthetic */ void $r8$lambda$aT3LJ854n2DLjhah9OVXU3Ws7a8(AnonymousClass1 r0, ValueAnimator r1) {
            r0.lambda$run$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$d6nIAlxSivrNr_x1h6bDaspi8Lg(AnonymousClass1 r0, ArrayList r1, boolean r2, View r3) {
            r0.lambda$run$8(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$pszl_nPapTVtxTJPWWSKQPI-29k, reason: not valid java name */
        public static /* synthetic */ void m14153$r8$lambda$pszl_nPapTVtxTJPWWSKQPI29k(ActionBarPopupWindow.ActionBarPopupWindowLayout r0, View r1) {
            r0.getSwipeBack().closeForeground();
        }

        public static /* synthetic */ void $r8$lambda$q58rmAiJquwHenAqCDIhVsAeD5I(AnonymousClass1 r0) {
            r0.lambda$run$0();
        }

        public static /* synthetic */ void $r8$lambda$tgdKCLpKo8XA3WzaCQrqCzFCFp4(AnonymousClass1 r0, View r1, int r2) {
            r0.lambda$run$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$uyNb8M6TGOrx9QFcsBIXJYD_0lU(AnonymousClass1 r0, CharSequence r1, Utilities.Callback r2) {
            r0.lambda$run$3(r1, r2);
        }

        /* renamed from: $r8$lambda$vdu4dmudROgcGcWmG-wu5FgoN5c, reason: not valid java name */
        public static /* synthetic */ void m14154$r8$lambda$vdu4dmudROgcGcWmGwu5FgoN5c(AnonymousClass1 r0, ValueAnimator r1) {
            r0.lambda$run$9(r1);
        }

        public AnonymousClass1(ContentPreviewViewer r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$run$0() {
            ContentPreviewViewer.m14146$$Nest$mdismissPopupWindow(this.this$0);
        }

        private /* synthetic */ void lambda$run$1(ValueAnimator r4) {
            ContentPreviewViewer.m14137$$Nest$fputcurrentMoveYProgress(this.this$0, ((Float) r4.getAnimatedValue()).floatValue());
            ContentPreviewViewer r42 = this.this$0;
            ContentPreviewViewer.m14141$$Nest$fputmoveY(r42, ContentPreviewViewer.m14132$$Nest$fgetstartMoveY(r42) + ((ContentPreviewViewer.m14114$$Nest$fgetfinalMoveY(this.this$0) - ContentPreviewViewer.m14132$$Nest$fgetstartMoveY(this.this$0)) * ContentPreviewViewer.m14107$$Nest$fgetcurrentMoveYProgress(this.this$0)));
            ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).invalidate();
        }

        private /* synthetic */ void lambda$run$12(ArrayList r7, View r8) {
            if (ContentPreviewViewer.m14124$$Nest$fgetparentActivity(this.this$0) != null) goto L5;
            return;
        L5:
            int r82 = ((Integer) r8.getTag()).intValue();
            if (((Integer) r7.get(r82)).intValue() != 0) goto L14;
            ContentPreviewViewerDelegate r0 = ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0);
            TLRPC.Document r72 = ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0);
            ContentPreviewViewer r83 = this.this$0;
            if (r72 == null) goto L11;
            Object r73 = ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(r83);
        L10:
            Object r1 = r73;
            r0.sendGif(r1, ContentPreviewViewer.m14125$$Nest$fgetparentObject(this.this$0), true, 0, 0);
        L41:
            ContentPreviewViewer.m14146$$Nest$mdismissPopupWindow(this.this$0);
            return;
        L11:
            r73 = ContentPreviewViewer.m14116$$Nest$fgetinlineResult(r83);
            goto L10
        L14:
            if (((Integer) r7.get(r82)).intValue() != 4) goto L22;
            ContentPreviewViewerDelegate r02 = ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0);
            TLRPC.Document r74 = ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0);
            ContentPreviewViewer r84 = this.this$0;
            if (r74 == null) goto L19;
            Object r75 = ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(r84);
        L18:
            Object r12 = r75;
            r02.sendGif(r12, ContentPreviewViewer.m14125$$Nest$fgetparentObject(this.this$0), false, 0, 0);
            goto L41
        L19:
            r75 = ContentPreviewViewer.m14116$$Nest$fgetinlineResult(r84);
            goto L18
        L22:
            if (((Integer) r7.get(r82)).intValue() != 1) goto L25;
            MediaDataController.getInstance(ContentPreviewViewer.m14104$$Nest$fgetcurrentAccount(this.this$0)).removeRecentGif(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0));
            ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).gifAddedOrDeleted();
            goto L41
        L25:
            if (((Integer) r7.get(r82)).intValue() != 2) goto L28;
            MediaDataController.getInstance(ContentPreviewViewer.m14104$$Nest$fgetcurrentAccount(this.this$0)).addRecentGif(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0), (int) (System.currentTimeMillis() / 1000), true);
            MessagesController.getInstance(ContentPreviewViewer.m14104$$Nest$fgetcurrentAccount(this.this$0)).saveGif("gif", ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0));
            ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).gifAddedOrDeleted();
            goto L41
        L28:
            if (((Integer) r7.get(r82)).intValue() != 3) goto L31;
            TLRPC.Document r76 = ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0);
            TLRPC.BotInlineResult r85 = ContentPreviewViewer.m14116$$Nest$fgetinlineResult(this.this$0);
            Object r03 = ContentPreviewViewer.m14125$$Nest$fgetparentObject(this.this$0);
            ContentPreviewViewerDelegate r13 = ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0);
            AlertsCreator.createScheduleDatePickerDialog(ContentPreviewViewer.m14124$$Nest$fgetparentActivity(this.this$0), r13.getDialogId(), new ContentPreviewViewer$1$$ExternalSyntheticLambda11(r13, r76, r85, r03), ContentPreviewViewer.m14128$$Nest$fgetresourcesProvider(this.this$0));
            goto L41
        L31:
            if (((Integer) r7.get(r82)).intValue() != 10) goto L34;
            ChatUtils.getInstance().saveGifToGallery(ContentPreviewViewer.m14124$$Nest$fgetparentActivity(this.this$0), ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0), ContentPreviewViewer.m14116$$Nest$fgetinlineResult(this.this$0), new ContentPreviewViewer$1$$ExternalSyntheticLambda12());
            goto L41
        L34:
            if (((Integer) r7.get(r82)).intValue() != 11) goto L41;
            ContentPreviewViewerDelegate r04 = ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0);
            TLRPC.Document r77 = ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0);
            ContentPreviewViewer r86 = this.this$0;
            if (r77 == null) goto L39;
            Object r78 = ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(r86);
        L38:
            Object r14 = r78;
            r04.addCaptionToGif(r14, ContentPreviewViewer.m14125$$Nest$fgetparentObject(this.this$0), true, 0, 0);
            goto L41
        L39:
            r78 = ContentPreviewViewer.m14116$$Nest$fgetinlineResult(r86);
            goto L38
        }

        private /* synthetic */ void lambda$run$13(ValueAnimator r4) {
            ContentPreviewViewer.m14137$$Nest$fputcurrentMoveYProgress(this.this$0, ((Float) r4.getAnimatedValue()).floatValue());
            ContentPreviewViewer r42 = this.this$0;
            ContentPreviewViewer.m14141$$Nest$fputmoveY(r42, ContentPreviewViewer.m14132$$Nest$fgetstartMoveY(r42) + ((ContentPreviewViewer.m14114$$Nest$fgetfinalMoveY(this.this$0) - ContentPreviewViewer.m14132$$Nest$fgetstartMoveY(this.this$0)) * ContentPreviewViewer.m14107$$Nest$fgetcurrentMoveYProgress(this.this$0)));
            ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).invalidate();
        }

        private /* synthetic */ void lambda$run$2(Utilities.Callback r1, Boolean r2) {
            r1.run(r2);
            if (r2.booleanValue() == false) goto L6;
            ContentPreviewViewer.m14146$$Nest$mdismissPopupWindow(this.this$0);
            return;
        }

        private /* synthetic */ void lambda$run$3(CharSequence r4, Utilities.Callback r5) {
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0) == null) goto L12;
            ContentPreviewViewerDelegate r0 = ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0);
            String r1 = TextUtils.join(_UrlKt.FRAGMENT_ENCODE_SET, ContentPreviewViewer.m14131$$Nest$fgetselectedEmojis(this.this$0));
            if (r5 == null) goto L7;
            ContentPreviewViewer$1$$ExternalSyntheticLambda13 r2 = new ContentPreviewViewer$1$$ExternalSyntheticLambda13(this, r5);
        L8:
            r0.newStickerPackSelected(r4, r1, r2);
            if (r5 != null) goto L13;
            ContentPreviewViewer.m14146$$Nest$mdismissPopupWindow(this.this$0);
            return;
        L13:
            return;
        L7:
            r2 = null;
            goto L8
        }

        private /* synthetic */ void lambda$run$4(View r3, int r4) {
            TLRPC.StickerSetCovered r32 = ((StickerPackNameView) r3).getCover();
            CustomEmojiReactionsWindow r42 = ContentPreviewViewer.m14127$$Nest$fgetreactionsLayout(this.this$0).getReactionsWindow();
            if (r42 != null) goto L5;
        L7:
            boolean r43 = r32 instanceof TLRPC.TL_stickerSetNoCovered;
            ContentPreviewViewer r0 = this.this$0;
            if (r43 == false) goto L12;
            StickersDialogs.showNameEditorDialog(null, ContentPreviewViewer.m14128$$Nest$fgetresourcesProvider(r0), ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getContext(), new ContentPreviewViewer$1$$ExternalSyntheticLambda9(this));
            return;
        L12:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(r0) == null) goto L14;
            ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).stickerSetSelected(r32.set, TextUtils.join(_UrlKt.FRAGMENT_ENCODE_SET, ContentPreviewViewer.m14131$$Nest$fgetselectedEmojis(this.this$0)));
        L14:
            ContentPreviewViewer.m14146$$Nest$mdismissPopupWindow(this.this$0);
            return;
        L5:
            if (r42.isShowing() == false) goto L7;
            r42.dismiss();
            goto L7
        }

        private /* synthetic */ void lambda$run$5(ArrayList r6, RecyclerListView r7, LinearLayout r8, ActionBarPopupWindow.ActionBarPopupWindowLayout r9, View r10) {
            if (ContentPreviewViewer.m14124$$Nest$fgetparentActivity(this.this$0) != null) goto L5;
            return;
        L5:
            int r102 = ((Integer) r10.getTag()).intValue();
            int r0 = ((Integer) r6.get(r102)).intValue();
            ContentPreviewViewer r1 = this.this$0;
            if (r0 != 2) goto L17;
            if (ContentPreviewViewer.m14134$$Nest$fgetstickerSetForCustomSticker(r1) != null) goto L12;
            r7.requestLayout();
            r8.requestLayout();
            r7.getAdapter().notifyDataSetChanged();
            r9.getSwipeBack().openForeground(1);
            return;
        L12:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0) == null) goto L14;
            ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).stickerSetSelected(ContentPreviewViewer.m14134$$Nest$fgetstickerSetForCustomSticker(this.this$0).set, TextUtils.join(_UrlKt.FRAGMENT_ENCODE_SET, ContentPreviewViewer.m14131$$Nest$fgetselectedEmojis(this.this$0)));
        L14:
            ContentPreviewViewer.m14146$$Nest$mdismissPopupWindow(this.this$0);
            return;
        L17:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(r1) != null) goto L19;
        L27:
            ContentPreviewViewer.m14146$$Nest$mdismissPopupWindow(this.this$0);
            return;
        L19:
            if (((Integer) r6.get(r102)).intValue() != 1) goto L22;
            ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).addToFavoriteSelected(TextUtils.join(_UrlKt.FRAGMENT_ENCODE_SET, ContentPreviewViewer.m14131$$Nest$fgetselectedEmojis(this.this$0)));
            goto L27
        L22:
            if (((Integer) r6.get(r102)).intValue() != 0) goto L27;
            boolean r62 = ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).isSettingIntroSticker();
            ContentPreviewViewer r72 = this.this$0;
            if (r62 == false) goto L26;
            ContentPreviewViewer.m14111$$Nest$fgetdelegate(r72).setIntroSticker(TextUtils.join(_UrlKt.FRAGMENT_ENCODE_SET, ContentPreviewViewer.m14131$$Nest$fgetselectedEmojis(this.this$0)));
            goto L27
        L26:
            ContentPreviewViewer.m14111$$Nest$fgetdelegate(r72).sendSticker(TextUtils.join(_UrlKt.FRAGMENT_ENCODE_SET, ContentPreviewViewer.m14131$$Nest$fgetselectedEmojis(this.this$0)));
            goto L27
        }

        private /* synthetic */ void lambda$run$8(ArrayList r9, boolean r10, View r11) {
            if (ContentPreviewViewer.m14124$$Nest$fgetparentActivity(this.this$0) != null) goto L5;
            return;
        L5:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0) == null) goto L37;
            int r112 = ((Integer) r11.getTag()).intValue();
            int r0 = ((Integer) r9.get(r112)).intValue();
            if (r0 != 0) goto L11;
            ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).sendEmoji(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0));
        L34:
            ContentPreviewViewer.m14146$$Nest$mdismissPopupWindow(this.this$0);
            return;
        L11:
            if (r0 != 1) goto L14;
            ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).setAsEmojiStatus(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0), null);
            goto L34
        L14:
            if (r0 != 2) goto L17;
            ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).setAsEmojiStatus(null, null);
            goto L34
        L17:
            if (r0 != 77) goto L20;
            ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).setAsBadge(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0));
            goto L34
        L20:
            if (r0 != 3) goto L23;
            ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).copyEmoji(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0));
            goto L34
        L23:
            if (r0 != 11) goto L26;
            ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).showEmojiSet(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0));
            goto L34
        L26:
            if (r0 != 4) goto L29;
            ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).removeFromRecent(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0));
            goto L34
        L29:
            if (r0 != 5) goto L32;
            MediaDataController.getInstance(ContentPreviewViewer.m14104$$Nest$fgetcurrentAccount(this.this$0)).addRecentSticker(2, ContentPreviewViewer.m14125$$Nest$fgetparentObject(this.this$0), ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0), (int) (System.currentTimeMillis() / 1000), r10);
            goto L34
        L32:
            if (((Integer) r9.get(r112)).intValue() != 10) goto L34;
            ChatUtils.getInstance().saveStickerToGallery(ContentPreviewViewer.m14124$$Nest$fgetparentActivity(this.this$0), ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0), new ContentPreviewViewer$1$$ExternalSyntheticLambda10());
            goto L34
        }

        private /* synthetic */ void lambda$run$9(ValueAnimator r4) {
            ContentPreviewViewer.m14137$$Nest$fputcurrentMoveYProgress(this.this$0, ((Float) r4.getAnimatedValue()).floatValue());
            ContentPreviewViewer r42 = this.this$0;
            ContentPreviewViewer.m14141$$Nest$fputmoveY(r42, ContentPreviewViewer.m14132$$Nest$fgetstartMoveY(r42) + ((ContentPreviewViewer.m14114$$Nest$fgetfinalMoveY(this.this$0) - ContentPreviewViewer.m14132$$Nest$fgetstartMoveY(this.this$0)) * ContentPreviewViewer.m14107$$Nest$fgetcurrentMoveYProgress(this.this$0)));
            ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).invalidate();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            int r9 = 0;
            if (ContentPreviewViewer.m14124$$Nest$fgetparentActivity(this.this$0) != null) goto L5;
            return;
        L5:
            if (ContentPreviewViewer.m14117$$Nest$fgetisPhotoEditor(this.this$0) == true) goto L360;
            ContentPreviewViewer.m14136$$Nest$fputcloseOnDismiss(this.this$0, true);
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0) == null) goto L24;
            ItemOptions r7 = ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).getCustomItemOptions(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0), ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0));
            if (r7 == null) goto L24;
            r7.setBlurBackground(ContentPreviewViewer.m14129$$Nest$fgetscrimBlur3Factory(this.this$0), BlurredBackgroundProviderImpl.scrimMenuBackground(ContentPreviewViewer.m14128$$Nest$fgetresourcesProvider(this.this$0)), true);
            r7.setDrawScrim(false);
            r7.setupSelectors();
            r7.setOnDismiss(new ContentPreviewViewer$1$$ExternalSyntheticLambda0(this));
            ViewGroup r0 = r7.getLayout();
            this.this$0.popupWindow = new C00631(this, r0, -2, -2);
            this.this$0.popupWindow.setPauseNotifications(true);
            this.this$0.popupWindow.setDismissAnimationDuration(150);
            this.this$0.popupWindow.setScaleOut(true);
            this.this$0.popupWindow.setOutsideTouchable(true);
            this.this$0.popupWindow.setClippingEnabled(true);
            this.this$0.popupWindow.setAnimationStyle(R.style.PopupContextAnimation);
            this.this$0.popupWindow.setFocusable(true);
            r0.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
            this.this$0.popupWindow.setInputMethodMode(2);
            this.this$0.popupWindow.getContentView().setFocusableInTouchMode(true);
            int r2 = ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).bottom + ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).top;
            int r3 = ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).top;
            int r4 = Math.min(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getWidth(), ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getHeight() - r2) - AndroidUtilities.dp(40.0f);
            float r5 = ContentPreviewViewer.m14123$$Nest$fgetmoveY(this.this$0);
            int r32 = r3 + (r4 / 2);
            if (ContentPreviewViewer.m14133$$Nest$fgetstickerEmojiLayout(this.this$0) == null) goto L14;
            int r6 = AndroidUtilities.dp(40.0f);
        L15:
            int r22 = (int) (((int) ((r5 + Math.max(r32 + r6, ((ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getHeight() - r2) - ContentPreviewViewer.m14120$$Nest$fgetkeyboardHeight(this.this$0)) / 2)) + r4)) + (AndroidUtilities.dp(24.0f) - ContentPreviewViewer.m14123$$Nest$fgetmoveY(this.this$0)));
            ContentPreviewViewer r33 = this.this$0;
            r33.popupWindow.showAtLocation(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(r33), 0, (int) ((ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getMeasuredWidth() - r0.getMeasuredWidth()) / 2.0f), r22);
            ContentPreviewViewer r02 = this.this$0;
            r02.runLongPressHaptic(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(r02));
            if (ContentPreviewViewer.m14123$$Nest$fgetmoveY(this.this$0) != 0.0f) goto L18;
        L21:
            ContentPreviewViewer.m14140$$Nest$fputmenuVisible(this.this$0, true);
            return;
        L18:
            if (ContentPreviewViewer.m14114$$Nest$fgetfinalMoveY(this.this$0) != 0.0f) goto L20;
            ContentPreviewViewer.m14139$$Nest$fputfinalMoveY(this.this$0, 0.0f);
            ContentPreviewViewer r03 = this.this$0;
            ContentPreviewViewer.m14143$$Nest$fputstartMoveY(r03, ContentPreviewViewer.m14123$$Nest$fgetmoveY(r03));
        L20:
            ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r04.addUpdateListener(new ContentPreviewViewer$1$$ExternalSyntheticLambda1(this));
            r04.setDuration(350);
            r04.setInterpolator(CubicBezierInterpolator.DEFAULT);
            r04.start();
            goto L21
        L14:
            r6 = 0;
        L24:
            if (ContentPreviewViewer.m14105$$Nest$fgetcurrentContentType(this.this$0) != 3) goto L26;
        L30:
            int r72 = 1;
        L31:
            ActionBarPopupWindow.ActionBarPopupWindowLayout r52 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getContext(), R.drawable.popup_fixed_alert4, ContentPreviewViewer.m14128$$Nest$fgetresourcesProvider(this.this$0), r72);
            r52.setBackground(ContentPreviewViewer.m14129$$Nest$fgetscrimBlur3Factory(this.this$0).create(r52, true).setColorProvider(BlurredBackgroundProviderImpl.scrimMenuBackground(ContentPreviewViewer.m14128$$Nest$fgetresourcesProvider(this.this$0))).setRadius(AndroidUtilities.dp(12.0f)).setPadding(AndroidUtilities.dp(8.0f)).setHasPadding(true));
            if (ContentPreviewViewer.m14105$$Nest$fgetcurrentContentType(this.this$0) != 3) goto L74;
            ArrayList r62 = new ArrayList();
            ArrayList r23 = new ArrayList();
            ArrayList r73 = new ArrayList();
            if (ContentPreviewViewer.m14134$$Nest$fgetstickerSetForCustomSticker(this.this$0) != null) goto L45;
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0) == null) goto L41;
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).isSettingIntroSticker() == false) goto L41;
            r62.add(LocaleController.getString(R.string.SetIntroSticker));
            r73.add(Integer.valueOf(R.drawable.menu_sticker_add));
            r23.add(0);
        L41:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).canSendSticker() == false) goto L43;
            r62.add(LocaleController.getString(R.string.SendStickerPreview));
            r73.add(Integer.valueOf(R.drawable.msg_send));
            r23.add(0);
        L43:
            r62.add(LocaleController.getString(R.string.AddToFavorites));
            r73.add(Integer.valueOf(R.drawable.msg_fave));
            r23.add(1);
        L45:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0) == null) goto L49;
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).isSettingIntroSticker() == false) goto L49;
        L61:
            ActionBarMenuSubItem r05 = new ActionBarMenuSubItem(ContentPreviewViewer.m14124$$Nest$fgetparentActivity(this.this$0), true, false, ContentPreviewViewer.m14128$$Nest$fgetresourcesProvider(this.this$0));
            r05.setItemHeight(44);
            r05.setTextAndIcon(LocaleController.getString(R.string.Back), R.drawable.msg_arrow_back);
            AnimatedEmojiSpan.TextViewEmojis r34 = r05.getTextView();
            if (LocaleController.isRTL == false) goto L64;
            int r42 = 0;
        L66:
            if (LocaleController.isRTL == false) goto L68;
            int r8 = AndroidUtilities.dp(40.0f);
        L69:
            r34.setPadding(r42, 0, r8, 0);
            FrameLayout r82 = new FrameLayout(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getContext());
            LinearLayout r43 = new LinearLayout(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getContext());
            r43.setBackgroundColor(ContentPreviewViewer.m14147$$Nest$mgetThemedColor(this.this$0, Theme.key_actionBarDefaultSubmenuBackground));
            r43.setOrientation(1);
            RecyclerListView r35 = this.this$0.createMyStickerPacksListView();
            r35.setOnItemClickListener(new ContentPreviewViewer$1$$ExternalSyntheticLambda2(this));
            r82.addView(r05);
            r43.addView(r82);
            r43.addView(new ActionBarPopupWindow.GapView(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getContext(), ContentPreviewViewer.m14128$$Nest$fgetresourcesProvider(this.this$0)), LayoutHelper.createLinear(-1, 8));
            ContentPreviewViewer$1$$ExternalSyntheticLambda3 r06 = new ContentPreviewViewer$1$$ExternalSyntheticLambda3(this, r23, r35, r43, r52);
            int r24 = 0;
        L71:
            if (r24 >= r62.size()) goto L73;
            ActionBarMenuSubItem r10 = ActionBarMenuItem.addItem(r52, ((Integer) r73.get(r24)).intValue(), (CharSequence) r62.get(r24), false, ContentPreviewViewer.m14128$$Nest$fgetresourcesProvider(this.this$0));
            r10.setTag(Integer.valueOf(r24));
            r10.setOnClickListener(r06);
            r24 = r24 + 1;
            goto L71
        L73:
            r52.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
            r43.addView(r35, new LinearLayout.LayoutParams(r52.getMeasuredWidth() - AndroidUtilities.dp(16.0f), (int) (r52.getMeasuredHeight() * 1.5f)));
            r52.addViewToSwipeBack(r43);
            r82.setOnClickListener(new ContentPreviewViewer$1$$ExternalSyntheticLambda4(r52));
            int r07 = ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).bottom + ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).top;
            int r25 = ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).top;
            ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).addView(r52, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, (((int) ((ContentPreviewViewer.m14123$$Nest$fgetmoveY(this.this$0) + Math.max(r25 + r3, ((ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getHeight() - r07) - ContentPreviewViewer.m14120$$Nest$fgetkeyboardHeight(this.this$0)) / 2)) + (((int) (Math.min(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getWidth(), ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getHeight() - r07) / 1.8f)) / 2))) + AndroidUtilities.dp(84.0f)) / AndroidUtilities.density, 0.0f, 0.0f));
            ContentPreviewViewer.m14142$$Nest$fputpopupLayout(this.this$0, r52);
            ContentPreviewViewer.m14126$$Nest$fgetpopupLayout(this.this$0).setTranslationY(-AndroidUtilities.dp(12.0f));
            ContentPreviewViewer.m14126$$Nest$fgetpopupLayout(this.this$0).setAlpha(0.0f);
            ContentPreviewViewer.m14126$$Nest$fgetpopupLayout(this.this$0).setScaleX(0.8f);
            ContentPreviewViewer.m14126$$Nest$fgetpopupLayout(this.this$0).setScaleY(0.8f);
            ContentPreviewViewer.m14126$$Nest$fgetpopupLayout(this.this$0).setPivotY(0.0f);
            ContentPreviewViewer.m14126$$Nest$fgetpopupLayout(this.this$0).setPivotX(ContentPreviewViewer.m14126$$Nest$fgetpopupLayout(this.this$0).getMeasuredWidth() / 2.0f);
            ContentPreviewViewer.m14126$$Nest$fgetpopupLayout(this.this$0).animate().translationY(0.0f).alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            ContentPreviewViewer.m14149$$Nest$mshowEmojiSelectorForStickers(this.this$0);
            ContentPreviewViewer.m14140$$Nest$fputmenuVisible(this.this$0, true);
            ContentPreviewViewer r08 = this.this$0;
            r08.runLongPressHaptic(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(r08));
            ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).invalidate();
            ActionBarPopupWindow.ActionBarPopupWindowLayout r14 = r52;
        L327:
            int r09 = r9;
        L329:
            if (r09 >= r14.getItemsCount()) goto L364;
            View r1 = r14.getItemAt(r09);
            if ((r1 instanceof ActionBarMenuSubItem) == false) goto L342;
            ActionBarMenuSubItem r12 = (ActionBarMenuSubItem) r1;
            if (r09 != 0) goto L335;
            boolean r26 = 1;
        L337:
            if (r09 != (r14.getItemsCount() - 1)) goto L339;
            boolean r36 = 1;
        L340:
            r12.updateSelectorBackground(r26, r36, 12);
            goto L342
        L339:
            r36 = r9;
            goto L340
        L335:
            r26 = r9;
        L342:
            r09 = r09 + 1;
            goto L329
        L364:
            return;
        L68:
            r8 = 0;
            goto L69
        L64:
            r42 = AndroidUtilities.dp(40.0f);
        L49:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0) != null) goto L51;
        L53:
            int r010 = R.string.AddToStickerPack;
        L54:
            r62.add(LocaleController.getString(r010));
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0) != null) goto L57;
        L59:
            int r011 = R.drawable.menu_sticker_add;
        L60:
            r73.add(Integer.valueOf(r011));
            r23.add(2);
            goto L61
        L57:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).isReplacedSticker() == false) goto L59;
            r011 = R.drawable.msg_replace;
            goto L60
        L51:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).isReplacedSticker() == false) goto L53;
            r010 = R.string.StickersReplaceSticker;
            goto L54
        L74:
            int r37 = ContentPreviewViewer.m14105$$Nest$fgetcurrentContentType(this.this$0);
            ContentPreviewViewer r74 = this.this$0;
            if (r37 != 0) goto L182;
            if (MessageObject.isPremiumSticker(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(r74)) == true) goto L79;
        L82:
            boolean r38 = MediaDataController.getInstance(ContentPreviewViewer.m14104$$Nest$fgetcurrentAccount(this.this$0)).isStickerInFavorites(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0));
            ArrayList r75 = new ArrayList();
            ArrayList r142 = new ArrayList();
            int r232 = 8;
            ArrayList r15 = new ArrayList();
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0) == null) goto L104;
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).needSend(ContentPreviewViewer.m14105$$Nest$fgetcurrentContentType(this.this$0)) == false) goto L90;
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).isInScheduleMode() == true) goto L90;
            r75.add(LocaleController.getString(R.string.SendStickerPreview));
            r15.add(Integer.valueOf(R.drawable.msg_send));
            r142.add(0);
        L90:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).needSend(ContentPreviewViewer.m14105$$Nest$fgetcurrentContentType(this.this$0)) == false) goto L95;
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).isInScheduleMode() == true) goto L95;
            r75.add(LocaleController.getString(R.string.SendWithoutSound));
            r15.add(Integer.valueOf(R.drawable.input_notify_off));
            r142.add(6);
        L95:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).canSchedule() == false) goto L98;
            r75.add(LocaleController.getString(R.string.Schedule));
            r15.add(Integer.valueOf(R.drawable.msg_autodelete));
            r142.add(3);
        L98:
            if (MessageObject.isStickerDocument(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0)) == false) goto L101;
            r75.add(LocaleController.formatString("SaveToGallery", R.string.SaveToGallery, new Object[0]));
            r15.add(Integer.valueOf(R.drawable.msg_gallery));
            r142.add(10);
        L101:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).needRemove() == false) goto L104;
            r75.add(LocaleController.getString(R.string.ImportStickersRemoveMenu));
            r15.add(Integer.valueOf(R.drawable.msg_delete));
            r142.add(5);
        L104:
            if (MessageObject.isMaskDocument(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0)) == true) goto L120;
            if (r38 == false) goto L107;
        L110:
            if (r38 == false) goto L113;
            int r012 = R.string.DeleteFromFavorites;
        L114:
            r75.add(LocaleController.getString(r012));
            if (r38 == false) goto L117;
            int r013 = R.drawable.msg_unfave;
        L118:
            r15.add(Integer.valueOf(r013));
            r142.add(2);
            goto L120
        L117:
            r013 = R.drawable.msg_fave;
            goto L118
        L113:
            r012 = R.string.AddToFavorites;
            goto L114
        L107:
            if (MediaDataController.getInstance(ContentPreviewViewer.m14104$$Nest$fgetcurrentAccount(this.this$0)).canAddStickerToFavorites() == false) goto L120;
            if (MessageObject.isStickerHasSet(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0)) == true) goto L110;
        L120:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0) == null) goto L129;
            if (ContentPreviewViewer.m14110$$Nest$fgetcurrentStickerSet(this.this$0) == null) goto L129;
            if ((ContentPreviewViewer.m14110$$Nest$fgetcurrentStickerSet(this.this$0) instanceof TLRPC.TL_inputStickerSetEmpty) == true) goto L129;
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).needOpen() == false) goto L129;
            r75.add(LocaleController.formatString(R.string.ViewPackPreview, new Object[0]));
            r15.add(Integer.valueOf(R.drawable.msg_media));
            r142.add(1);
        L129:
            if (ContentPreviewViewer.m14118$$Nest$fgetisRecentSticker(this.this$0) == false) goto L132;
            r75.add(LocaleController.getString(R.string.DeleteFromRecent));
            r15.add(Integer.valueOf(R.drawable.msg_delete));
            r142.add(4);
        L132:
            if (ContentPreviewViewer.m14110$$Nest$fgetcurrentStickerSet(this.this$0) == null) goto L155;
            if (ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0) == null) goto L155;
            TLRPC.TL_messages_stickerSet r014 = MediaDataController.getInstance(ContentPreviewViewer.m14104$$Nest$fgetcurrentAccount(this.this$0)).getStickerSet(ContentPreviewViewer.m14110$$Nest$fgetcurrentStickerSet(this.this$0), true);
            if (r014 != null) goto L138;
        L146:
            if (r014 == null) goto L155;
            if (r014.set.creator == false) goto L155;
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0) == null) goto L155;
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).canDeleteSticker(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0)) == false) goto L155;
            r75.add(LocaleController.getString(R.string.DeleteSticker));
            r15.add(Integer.valueOf(R.drawable.msg_delete));
            r142.add(8);
            goto L155
        L138:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0) == null) goto L146;
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).canEditSticker() == false) goto L146;
            TLRPC.StickerSet r27 = r014.set;
            if (r27.emojis == true) goto L146;
            if (r27.masks == true) goto L146;
            r75.add(LocaleController.getString(R.string.EditSticker));
            r15.add(Integer.valueOf(R.drawable.msg_edit));
            r142.add(7);
        L155:
            if (r75.isEmpty() == true) goto L361;
            ContentPreviewViewer.m14140$$Nest$fputmenuVisible(this.this$0, true);
            ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).invalidate();
            AnonymousClass2 r015 = new AnonymousClass2(this, r142, r38);
            ContentPreviewViewer.m14144$$Nest$maddVoteOptions(this.this$0, r52);
            int r28 = 0;
        L159:
            if (r28 >= r75.size()) goto L164;
            ActionBarMenuSubItem r39 = ActionBarMenuItem.addItem(r52, ((Integer) r15.get(r28)).intValue(), (CharSequence) r75.get(r28), false, ContentPreviewViewer.m14128$$Nest$fgetresourcesProvider(this.this$0));
            r39.setTag(Integer.valueOf(r28));
            r39.setOnClickListener(r015);
            int r63 = r232;
            if (((Integer) r142.get(r28)).intValue() != r63) goto L163;
            int r44 = ContentPreviewViewer.m14147$$Nest$mgetThemedColor(this.this$0, Theme.key_text_RedBold);
            r39.setColors(r44, r44);
            r39.setSelectorColor(Theme.multAlpha(r44, 0.1f));
        L163:
            r28 = r28 + 1;
            r232 = r63;
            goto L159
        L164:
            this.this$0.popupWindow = new AnonymousClass3(this, r52, -2, -2);
            this.this$0.popupWindow.setPauseNotifications(true);
            this.this$0.popupWindow.setDismissAnimationDuration(100);
            this.this$0.popupWindow.setScaleOut(true);
            this.this$0.popupWindow.setOutsideTouchable(true);
            this.this$0.popupWindow.setClippingEnabled(true);
            this.this$0.popupWindow.setAnimationStyle(R.style.PopupContextAnimation);
            this.this$0.popupWindow.setFocusable(true);
            r52.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
            this.this$0.popupWindow.setInputMethodMode(2);
            this.this$0.popupWindow.getContentView().setFocusableInTouchMode(true);
            int r016 = ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).bottom + ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).top;
            int r29 = ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).top;
            int r310 = ContentPreviewViewer.m14105$$Nest$fgetcurrentContentType(this.this$0);
            ContentPreviewViewer r45 = this.this$0;
            if (r310 != 1) goto L167;
            int r311 = Math.min(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(r45).getWidth(), ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getHeight() - r016) - AndroidUtilities.dp(40.0f);
        L172:
            float r46 = ContentPreviewViewer.m14123$$Nest$fgetmoveY(this.this$0);
            int r312 = r311 / 2;
            int r210 = r29 + r312;
            if (ContentPreviewViewer.m14133$$Nest$fgetstickerEmojiLayout(this.this$0) == null) goto L175;
            int r64 = AndroidUtilities.dp(40.0f);
        L176:
            int r017 = ((int) ((r46 + Math.max(r210 + r64, ((ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getHeight() - r016) - ContentPreviewViewer.m14120$$Nest$fgetkeyboardHeight(this.this$0)) / 2)) + r312)) + AndroidUtilities.dp(24.0f);
            if (ContentPreviewViewer.m14112$$Nest$fgetdrawEffect(this.this$0) == false) goto L179;
            r017 = r017 + AndroidUtilities.dp(24.0f);
        L179:
            ContentPreviewViewer r211 = this.this$0;
            r211.popupWindow.showAtLocation(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(r211), 0, (int) ((ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getMeasuredWidth() - r52.getMeasuredWidth()) / 2.0f), r017);
            ContentPreviewViewer r018 = this.this$0;
            r018.runLongPressHaptic(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(r018));
            r14 = r52;
        L180:
            r9 = 0;
            goto L327
        L175:
            r64 = 0;
            goto L176
        L167:
            boolean r313 = ContentPreviewViewer.m14112$$Nest$fgetdrawEffect(r45);
            ContentPreviewViewer r47 = this.this$0;
            if (r313 == false) goto L171;
            float r314 = Math.min(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(r47).getWidth(), ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getHeight() - r016) - AndroidUtilities.dpf2(40.0f);
        L170:
            r311 = (int) r314;
            goto L172
        L171:
            r314 = Math.min(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(r47).getWidth(), ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getHeight() - r016) / 1.8f;
            goto L170
        L361:
            return;
        L79:
            if (AccountInstance.getInstance(ContentPreviewViewer.m14104$$Nest$fgetcurrentAccount(this.this$0)).getUserConfig().isPremium() == true) goto L82;
            ContentPreviewViewer.m14150$$Nest$mshowUnlockPremiumView(this.this$0);
            ContentPreviewViewer.m14140$$Nest$fputmenuVisible(this.this$0, true);
            ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).invalidate();
            ContentPreviewViewer r019 = this.this$0;
            r019.runLongPressHaptic(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(r019));
            return;
        L182:
            if (ContentPreviewViewer.m14105$$Nest$fgetcurrentContentType(r74) == 2) goto L184;
        L259:
            r14 = r52;
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0) == null) goto L180;
            ArrayList r020 = new ArrayList();
            ArrayList r315 = new ArrayList();
            ArrayList r53 = new ArrayList();
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).needSend(ContentPreviewViewer.m14105$$Nest$fgetcurrentContentType(this.this$0)) == false) goto L267;
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).isInScheduleMode() == true) goto L267;
            r020.add(LocaleController.getString(R.string.SendGifPreview));
            r53.add(Integer.valueOf(R.drawable.msg_send));
            r315.add(0);
        L267:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).needSend(ContentPreviewViewer.m14105$$Nest$fgetcurrentContentType(this.this$0)) == false) goto L272;
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).isInScheduleMode() == true) goto L272;
            r020.add(LocaleController.getString(R.string.SendWithoutSound));
            r53.add(Integer.valueOf(R.drawable.input_notify_off));
            r315.add(4);
        L272:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).canSchedule() == false) goto L275;
            r020.add(LocaleController.getString(R.string.Schedule));
            r53.add(Integer.valueOf(R.drawable.msg_autodelete));
            r315.add(3);
        L275:
            if (ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0) == null) goto L280;
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).canAddCaption(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0)) == false) goto L280;
            r020.add(LocaleController.getString(R.string.AddACaption));
            r53.add(Integer.valueOf(R.drawable.outline_caption_24));
            r315.add(11);
        L280:
            if (ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0) != null) goto L282;
        L284:
            boolean r48 = false;
        L285:
            if (r48 == false) goto L287;
        L291:
            if (r48 == false) goto L294;
            r020.add(LocaleController.formatString("SaveToGallery", R.string.SaveToGallery, new Object[0]));
            r53.add(Integer.valueOf(R.drawable.msg_gallery));
            r315.add(10);
        L294:
            if (ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0) == null) goto L300;
            boolean r212 = MediaDataController.getInstance(ContentPreviewViewer.m14104$$Nest$fgetcurrentAccount(this.this$0)).hasRecentGif(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0));
            if (r212 == false) goto L298;
            r020.add(LocaleController.formatString("Delete", R.string.Delete, new Object[0]));
            r53.add(Integer.valueOf(R.drawable.msg_delete));
            r315.add(1);
        L299:
            boolean r92 = r212;
        L302:
            if (r020.isEmpty() == true) goto L363;
            ContentPreviewViewer.m14140$$Nest$fputmenuVisible(this.this$0, true);
            ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).invalidate();
            int[] r213 = new int[r53.size()];
            int r49 = 0;
        L306:
            if (r49 >= r53.size()) goto L308;
            r213[r49] = ((Integer) r53.get(r49)).intValue();
            r49 = r49 + 1;
            goto L306
        L308:
            ContentPreviewViewer$1$$ExternalSyntheticLambda7 r214 = new ContentPreviewViewer$1$$ExternalSyntheticLambda7(this, r315);
            int r316 = 0;
        L310:
            if (r316 >= r020.size()) goto L317;
            ActionBarMenuSubItem r410 = ActionBarMenuItem.addItem(r14, ((Integer) r53.get(r316)).intValue(), (CharSequence) r020.get(r316), false, ContentPreviewViewer.m14128$$Nest$fgetresourcesProvider(this.this$0));
            r410.setTag(Integer.valueOf(r316));
            r410.setOnClickListener(r214);
            if (r92 == false) goto L316;
            if (r316 != (r020.size() - 1)) goto L316;
            r410.setColors(ContentPreviewViewer.m14147$$Nest$mgetThemedColor(this.this$0, Theme.key_text_RedBold), ContentPreviewViewer.m14147$$Nest$mgetThemedColor(this.this$0, Theme.key_text_RedRegular));
        L316:
            r316 = r316 + 1;
            goto L310
        L317:
            this.this$0.popupWindow = new AnonymousClass5(this, r14, -2, -2);
            this.this$0.popupWindow.setPauseNotifications(true);
            this.this$0.popupWindow.setDismissAnimationDuration(150);
            this.this$0.popupWindow.setScaleOut(true);
            this.this$0.popupWindow.setOutsideTouchable(true);
            this.this$0.popupWindow.setClippingEnabled(true);
            this.this$0.popupWindow.setAnimationStyle(R.style.PopupContextAnimation);
            this.this$0.popupWindow.setFocusable(true);
            r14.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
            this.this$0.popupWindow.setInputMethodMode(2);
            this.this$0.popupWindow.getContentView().setFocusableInTouchMode(true);
            int r021 = ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).bottom + ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).top;
            int r215 = ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).top;
            int r317 = Math.min(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getWidth(), ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getHeight() - r021) - AndroidUtilities.dp(40.0f);
            float r411 = ContentPreviewViewer.m14123$$Nest$fgetmoveY(this.this$0);
            int r216 = r215 + (r317 / 2);
            if (ContentPreviewViewer.m14133$$Nest$fgetstickerEmojiLayout(this.this$0) == null) goto L320;
            int r93 = AndroidUtilities.dp(40.0f);
        L321:
            int r022 = (int) (((int) ((r411 + Math.max(r216 + r93, ((ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getHeight() - r021) - ContentPreviewViewer.m14120$$Nest$fgetkeyboardHeight(this.this$0)) / 2)) + r3)) + (AndroidUtilities.dp(24.0f) - ContentPreviewViewer.m14123$$Nest$fgetmoveY(this.this$0)));
            ContentPreviewViewer r217 = this.this$0;
            r9 = 0;
            r217.popupWindow.showAtLocation(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(r217), 0, (int) ((ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getMeasuredWidth() - r14.getMeasuredWidth()) / 2.0f), r022);
            ContentPreviewViewer r023 = this.this$0;
            r023.runLongPressHaptic(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(r023));
            if (ContentPreviewViewer.m14123$$Nest$fgetmoveY(this.this$0) == 0.0f) goto L327;
            if (ContentPreviewViewer.m14114$$Nest$fgetfinalMoveY(this.this$0) != 0.0f) goto L326;
            ContentPreviewViewer.m14139$$Nest$fputfinalMoveY(this.this$0, 0.0f);
            ContentPreviewViewer r024 = this.this$0;
            ContentPreviewViewer.m14143$$Nest$fputstartMoveY(r024, ContentPreviewViewer.m14123$$Nest$fgetmoveY(r024));
        L326:
            ValueAnimator r025 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r025.addUpdateListener(new ContentPreviewViewer$1$$ExternalSyntheticLambda8(this));
            r025.setDuration(350);
            r025.setInterpolator(CubicBezierInterpolator.DEFAULT);
            r025.start();
            goto L327
        L320:
            r93 = 0;
            goto L321
        L363:
            return;
        L298:
            r020.add(LocaleController.formatString("SaveToGIFs", R.string.SaveToGIFs, new Object[0]));
            r53.add(Integer.valueOf(R.drawable.msg_gif_add));
            r315.add(2);
            goto L299
        L300:
            r92 = false;
            goto L302
        L287:
            if (ContentPreviewViewer.m14116$$Nest$fgetinlineResult(this.this$0) == null) goto L291;
            if (ContentPreviewViewer.m14116$$Nest$fgetinlineResult(this.this$0).content == null) goto L291;
            r48 = MessageObject.isNewGifDocument(WebFile.createWithWebDocument(ContentPreviewViewer.m14116$$Nest$fgetinlineResult(this.this$0).content));
            goto L291
        L282:
            if (MessageObject.isNewGifDocument(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0)) == false) goto L284;
            r48 = true;
            goto L285
        L184:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0) == null) goto L259;
            ArrayList r318 = new ArrayList();
            ArrayList r94 = new ArrayList();
            ArrayList r11 = new ArrayList();
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).needSend(ContentPreviewViewer.m14105$$Nest$fgetcurrentContentType(this.this$0)) == false) goto L188;
            r318.add(LocaleController.getString(R.string.SendEmojiPreview));
            r11.add(Integer.valueOf(R.drawable.msg_send));
            r94.add(0);
        L188:
            Boolean r102 = ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).canSetAsStatus(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0));
            if (r102 == null) goto L195;
            if (r102.booleanValue() == false) goto L193;
            r318.add(LocaleController.getString(R.string.SetAsEmojiStatus));
            r11.add(Integer.valueOf(R.drawable.msg_smile_status));
            r94.add(1);
            goto L195
        L193:
            r318.add(LocaleController.getString(R.string.RemoveStatus));
            r11.add(Integer.valueOf(R.drawable.msg_smile_status));
            r94.add(2);
        L195:
            if (BadgesController.INSTANCE.canChangeBadge() == false) goto L198;
            r318.add(LocaleController.getString(R.string.SetAsBadge));
            r11.add(Integer.valueOf(R.drawable.extera_outline));
            r94.add(77);
        L198:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).needCopy(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0)) == false) goto L201;
            r318.add(LocaleController.getString(R.string.CopyEmojiPreview));
            r11.add(Integer.valueOf(R.drawable.msg_copy));
            r94.add(3);
        L201:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).needShowEmojiSet(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0)) == false) goto L204;
            r318.add(LocaleController.getString(R.string.ViewEmojiSet));
            r11.add(Integer.valueOf(R.drawable.msg_media));
            r94.add(11);
        L204:
            if (ChatUtils.getInstance().isEmoji(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0)) == false) goto L207;
            r318.add(LocaleController.formatString("SaveToGallery", R.string.SaveToGallery, new Object[0]));
            r11.add(Integer.valueOf(R.drawable.msg_gallery));
            r94.add(10);
        L207:
            if (ContentPreviewViewer.m14111$$Nest$fgetdelegate(this.this$0).needRemoveFromRecent(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0)) == false) goto L209;
            r318.add(LocaleController.getString(R.string.RemoveFromRecent));
            r11.add(Integer.valueOf(R.drawable.msg_delete));
            r94.add(4);
        L209:
            boolean r218 = MediaDataController.getInstance(ContentPreviewViewer.m14104$$Nest$fgetcurrentAccount(this.this$0)).isStickerInFavorites(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0));
            if (MessageObject.isAnimatedEmoji(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0)) == true) goto L228;
            if (MessageObject.isMaskDocument(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0)) == true) goto L228;
            if (r218 == false) goto L215;
        L218:
            if (r218 == false) goto L221;
            int r412 = R.string.DeleteFromFavorites;
        L222:
            r318.add(LocaleController.getString(r412));
            if (r218 == false) goto L225;
            int r413 = R.drawable.msg_unfave;
        L226:
            r11.add(Integer.valueOf(r413));
            r94.add(5);
            goto L228
        L225:
            r413 = R.drawable.msg_fave;
            goto L226
        L221:
            r412 = R.string.AddToFavorites;
            goto L222
        L215:
            if (MediaDataController.getInstance(ContentPreviewViewer.m14104$$Nest$fgetcurrentAccount(this.this$0)).canAddStickerToFavorites() == false) goto L228;
            if (MessageObject.isStickerHasSet(ContentPreviewViewer.m14106$$Nest$fgetcurrentDocument(this.this$0)) == true) goto L218;
        L228:
            if (r318.isEmpty() == true) goto L362;
            ContentPreviewViewer.m14140$$Nest$fputmenuVisible(this.this$0, true);
            ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).invalidate();
            int[] r026 = new int[r11.size()];
            int r414 = 0;
        L232:
            if (r414 >= r11.size()) goto L234;
            r026[r414] = ((Integer) r11.get(r414)).intValue();
            r414 = r414 + 1;
            goto L232
        L234:
            ContentPreviewViewer$1$$ExternalSyntheticLambda5 r027 = new ContentPreviewViewer$1$$ExternalSyntheticLambda5(this, r94, r218);
            boolean r219 = ContentPreviewViewer.m14144$$Nest$maddVoteOptions(this.this$0, r52);
            int r415 = 0;
            ActionBarPopupWindow.ActionBarPopupWindowLayout r54 = r52;
        L236:
            if (r415 >= r318.size()) goto L249;
            if (r219 == true) goto L240;
            if (r415 != 0) goto L240;
            boolean r233 = true;
        L242:
            if (r415 != (r318.size() - 1)) goto L244;
            boolean r242 = true;
        L245:
            ActionBarPopupWindow.ActionBarPopupWindowLayout r252 = r54;
            ActionBarMenuSubItem r55 = ActionBarMenuItem.addItem(r233, r242, r252, ((Integer) r11.get(r415)).intValue(), (CharSequence) r318.get(r415), false, ContentPreviewViewer.m14128$$Nest$fgetresourcesProvider(this.this$0));
            if (((Integer) r94.get(r415)).intValue() != 4) goto L248;
            r55.setIconColor(ContentPreviewViewer.m14147$$Nest$mgetThemedColor(this.this$0, Theme.key_text_RedRegular));
            r55.setTextColor(ContentPreviewViewer.m14147$$Nest$mgetThemedColor(this.this$0, Theme.key_text_RedBold));
        L248:
            r55.setTag(Integer.valueOf(r415));
            r55.setOnClickListener(r027);
            r415 = r415 + 1;
            r54 = r252;
            goto L236
        L244:
            r242 = false;
        L240:
            r233 = false;
            goto L242
        L249:
            r14 = r54;
            this.this$0.popupWindow = new AnonymousClass4(this, r14, -2, -2);
            this.this$0.popupWindow.setPauseNotifications(true);
            this.this$0.popupWindow.setDismissAnimationDuration(150);
            this.this$0.popupWindow.setScaleOut(true);
            this.this$0.popupWindow.setOutsideTouchable(true);
            this.this$0.popupWindow.setClippingEnabled(true);
            this.this$0.popupWindow.setAnimationStyle(R.style.PopupContextAnimation);
            this.this$0.popupWindow.setFocusable(true);
            r14.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
            this.this$0.popupWindow.setInputMethodMode(2);
            this.this$0.popupWindow.getContentView().setFocusableInTouchMode(true);
            int r028 = ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).bottom + ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).top;
            int r220 = ContentPreviewViewer.m14121$$Nest$fgetlastInsets(this.this$0).top;
            int r319 = Math.min(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getWidth(), ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getHeight() - r028) - AndroidUtilities.dp(40.0f);
            float r416 = ContentPreviewViewer.m14123$$Nest$fgetmoveY(this.this$0);
            int r221 = r220 + (r319 / 2);
            if (ContentPreviewViewer.m14133$$Nest$fgetstickerEmojiLayout(this.this$0) == null) goto L252;
            int r56 = AndroidUtilities.dp(40.0f);
        L253:
            int r029 = (int) (((int) ((r416 + Math.max(r221 + r56, ((ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getHeight() - r028) - ContentPreviewViewer.m14120$$Nest$fgetkeyboardHeight(this.this$0)) / 2)) + r3)) + (AndroidUtilities.dp(24.0f) - ContentPreviewViewer.m14123$$Nest$fgetmoveY(this.this$0)));
            ContentPreviewViewer r222 = this.this$0;
            r222.popupWindow.showAtLocation(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(r222), 0, (int) ((ContentPreviewViewer.m14103$$Nest$fgetcontainerView(this.this$0).getMeasuredWidth() - r14.getMeasuredWidth()) / 2.0f), r029);
            ActionBarPopupWindow.startAnimation(r14);
            ContentPreviewViewer r030 = this.this$0;
            r030.runLongPressHaptic(ContentPreviewViewer.m14103$$Nest$fgetcontainerView(r030));
            if (ContentPreviewViewer.m14123$$Nest$fgetmoveY(this.this$0) == 0.0f) goto L180;
            if (ContentPreviewViewer.m14114$$Nest$fgetfinalMoveY(this.this$0) != 0.0f) goto L258;
            ContentPreviewViewer.m14139$$Nest$fputfinalMoveY(this.this$0, 0.0f);
            ContentPreviewViewer r031 = this.this$0;
            ContentPreviewViewer.m14143$$Nest$fputstartMoveY(r031, ContentPreviewViewer.m14123$$Nest$fgetmoveY(r031));
        L258:
            ValueAnimator r032 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r032.addUpdateListener(new ContentPreviewViewer$1$$ExternalSyntheticLambda6(this));
            r032.setDuration(350);
            r032.setInterpolator(CubicBezierInterpolator.DEFAULT);
            r032.start();
            goto L180
        L252:
            r56 = 0;
            goto L253
        L362:
            return;
        L26:
            if (ContentPreviewViewer.m14145$$Nest$mcanShowFullVotersList(this.this$0) == true) goto L30;
            r72 = 0;
            goto L31
        }
    }

    /* renamed from: org.telegram.ui.ContentPreviewViewer$2, reason: invalid class name */
    public class AnonymousClass2 extends ReactionsContainerLayout {
        final /* synthetic */ ContentPreviewViewer this$0;

        public AnonymousClass2(ContentPreviewViewer r1, int r2, BaseFragment r3, Context r4, int r5, Theme.ResourcesProvider r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout
        public void invalidateLoopViews() {
            super.invalidateLoopViews();
            ContentPreviewViewer r0 = this.this$0;
            if (getReactionsWindow() == null) goto L5;
            boolean r1 = true;
        L6:
            r0.setFocusable(r1);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.ContentPreviewViewer$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        final /* synthetic */ ContentPreviewViewer this$0;

        public AnonymousClass3(ContentPreviewViewer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent r3) {
            if (r3.getKeyCode() != 4) goto L15;
            if (r3.getAction() != 1) goto L15;
            if (ContentPreviewViewer.m14119$$Nest$fgetisStickerEditor(this.this$0) == false) goto L9;
        L12:
            this.this$0.closeWithMenu();
        L13:
            return true;
        L9:
            if (ContentPreviewViewer.m14122$$Nest$fgetmenuVisible(this.this$0) == true) goto L12;
            this.this$0.close();
        L15:
            return super.dispatchKeyEvent(r3);
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            Blur3Utils.checkBitmapSourceMatrixScale(ContentPreviewViewer.m14130$$Nest$fgetscrimBlur3SourceBitmap(this.this$0), ContentPreviewViewer.m14135$$Nest$fgetwindowView(this.this$0));
            ContentPreviewViewer.m14129$$Nest$fgetscrimBlur3Factory(this.this$0).invalidateAllLinkedViews();
        }
    }

    /* renamed from: org.telegram.ui.ContentPreviewViewer$4, reason: invalid class name */
    public class AnonymousClass4 extends FrameLayoutDrawer {
        final /* synthetic */ ContentPreviewViewer this$0;

        public AnonymousClass4(ContentPreviewViewer r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.this$0.centerImage.onAttachedToWindow();
            ContentPreviewViewer.m14113$$Nest$fgeteffectImage(this.this$0).onAttachedToWindow();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.this$0.centerImage.onDetachedFromWindow();
            ContentPreviewViewer.m14113$$Nest$fgeteffectImage(this.this$0).onDetachedFromWindow();
        }
    }

    /* renamed from: org.telegram.ui.ContentPreviewViewer$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerListView {
        final /* synthetic */ ContentPreviewViewer this$0;

        public AnonymousClass5(ContentPreviewViewer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r4, int r5) {
            int r52 = View.MeasureSpec.getSize(r5);
            int r0 = AndroidUtilities.dp(4.0f) + (AndroidUtilities.dp(50.0f) * getAdapter().getItemCount());
            if (r0 > r52) goto L6;
            r52 = r0;
        L6:
            super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.ContentPreviewViewer$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.ItemDecoration {
        final /* synthetic */ ContentPreviewViewer this$0;
        final /* synthetic */ List val$stickerSetCoveredList;

        public AnonymousClass6(ContentPreviewViewer r1, List r2) {
            this.this$0 = r1;
            this.val$stickerSetCoveredList = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            if (r3.getChildAdapterPosition(r2) != (this.val$stickerSetCoveredList.size() - 1)) goto L6;
            r1.bottom = AndroidUtilities.dp(4.0f);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ContentPreviewViewer$7, reason: invalid class name */
    public class AnonymousClass7 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ ContentPreviewViewer this$0;
        final /* synthetic */ List val$stickerSetCoveredList;

        public AnonymousClass7(ContentPreviewViewer r1, List r2) {
            this.this$0 = r1;
            this.val$stickerSetCoveredList = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.val$stickerSetCoveredList.size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
            ((StickerPackNameView) r1.itemView).bind((TLRPC.StickerSetCovered) this.val$stickerSetCoveredList.get(r2));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            StickerPackNameView r32 = new StickerPackNameView(r2.getContext(), ContentPreviewViewer.m14128$$Nest$fgetresourcesProvider(this.this$0));
            r32.setLayoutParams(new RecyclerView.LayoutParams(-2, AndroidUtilities.dp(48.0f)));
            return new RecyclerListView.Holder(r32);
        }
    }

    public interface ContentPreviewViewerDelegate {
        default void addCaptionToGif(Object r1, Object r2, boolean r3, int r4, int r5) {
        }

        default void addToFavoriteSelected(String r1) {
        }

        default boolean can() {
            return true;
        }

        default boolean canAddCaption(TLRPC.Document r1) {
            return false;
        }

        default boolean canDeleteSticker(TLRPC.Document r1) {
            return false;
        }

        default boolean canEditSticker() {
            return false;
        }

        default boolean canSchedule() {
            return false;
        }

        default boolean canSendSticker() {
            return true;
        }

        default Boolean canSetAsStatus(TLRPC.Document r1) {
            return null;
        }

        default void copyEmoji(TLRPC.Document r1) {
        }

        default void deleteSticker(TLRPC.Document r1) {
        }

        default void editSticker(TLRPC.Document r1) {
        }

        default ItemOptions getCustomItemOptions(ViewGroup r1, View r2) {
            return null;
        }

        long getDialogId();

        default TLRPC.TL_messageMediaPoll getPoll() {
            return null;
        }

        default TLRPC.PollAnswer getPollAnswer() {
            return null;
        }

        default MessageObject getPollMessageObject() {
            return null;
        }

        default String getQuery(boolean r1) {
            return null;
        }

        default void gifAddedOrDeleted() {
        }

        default boolean isInScheduleMode() {
            return false;
        }

        default boolean isPhotoEditor() {
            return false;
        }

        default boolean isReplacedSticker() {
            return false;
        }

        default boolean isSettingIntroSticker() {
            return false;
        }

        default boolean isStickerEditor() {
            return false;
        }

        default boolean needCopy(TLRPC.Document r1) {
            return false;
        }

        default boolean needMenu() {
            return true;
        }

        default boolean needOpen() {
            return true;
        }

        default boolean needRemove() {
            return false;
        }

        default boolean needRemoveFromRecent(TLRPC.Document r1) {
            return false;
        }

        default boolean needSend(int r1) {
            return false;
        }

        default boolean needShowEmojiSet(TLRPC.Document r1) {
            return false;
        }

        default void newStickerPackSelected(CharSequence r1, String r2, Utilities.Callback<Boolean> r3) {
        }

        default void openSet(TLRPC.InputStickerSet r1, boolean r2) {
        }

        default void remove(SendMessagesHelper.ImportingSticker r1) {
        }

        default void removeFromRecent(TLRPC.Document r1) {
        }

        default void resetTouch() {
        }

        default void retractVote() {
        }

        default void sendEmoji(TLRPC.Document r1) {
        }

        default void sendGif(Object r1, Object r2, boolean r3, int r4, int r5) {
        }

        default void sendSticker(String r1) {
        }

        default void sendSticker(TLRPC.Document r1, String r2, Object r3, boolean r4, int r5, int r6) {
        }

        default void sendVote() {
        }

        default void setAsBadge(TLRPC.Document r1) {
        }

        default void setAsEmojiStatus(TLRPC.Document r1, Integer r2) {
        }

        default void setIntroSticker(String r1) {
        }

        default void showEmojiSet(TLRPC.Document r1) {
        }

        default void stickerSetSelected(TLRPC.StickerSet r1, String r2) {
        }
    }

    public class FrameLayoutDrawer extends FrameLayout {
        final /* synthetic */ ContentPreviewViewer this$0;

        public FrameLayoutDrawer(ContentPreviewViewer r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            setWillNotDraw(false);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if ((r3 instanceof PaintingOverlay) == false) goto L7;
            return false;
        L7:
            return super.drawChild(r2, r3, r4);
        }

        @Override // android.view.View
        public void onDraw(Canvas r1) {
            ContentPreviewViewer.m14148$$Nest$monDraw(this.this$0, r1);
        }
    }

    public static class StickerPackNameView extends LinearLayout {
        private TLRPC.StickerSetCovered cover;
        private final BackupImageView imageView;
        private final Theme.ResourcesProvider resourcesProvider;
        private final SimpleTextView textView;

        public StickerPackNameView(Context r9, Theme.ResourcesProvider r10) {
            super(r9);
            this.resourcesProvider = r10;
            BackupImageView r102 = new BackupImageView(r9);
            this.imageView = r102;
            SimpleTextView r0 = new SimpleTextView(r9);
            this.textView = r0;
            r0.setTextSize(16);
            r0.setTextColor(-1);
            setOrientation(0);
            addView(r102, LayoutHelper.createLinear(24, 24, 17, 17, 0, 17, 0));
            addView(r0, LayoutHelper.createLinear(-2, -2, 17, 0, 0, 12, 0));
        }

        public void bind(TLRPC.StickerSetCovered r13) {
            this.cover = r13;
            boolean r1 = r13 instanceof TLRPC.TL_stickerSetNoCovered;
            SimpleTextView r2 = this.textView;
            if (r1 == false) goto L6;
            r2.setText(LocaleController.getString(R.string.NewStickerPack));
            this.imageView.setImageResource(R.drawable.msg_addbot);
            return;
        L6:
            r2.setText(r13.set.title);
            TLRPC.Document r12 = r13.cover;
            if (r12 == null) goto L18;
            TLRPC.PhotoSize r14 = FileLoader.getClosestPhotoSizeWithSize(r12.thumbs, 90);
            SvgHelper.SvgDrawable r4 = DocumentObject.getSvgThumb(r13.cover, Theme.key_windowBackgroundGray, 1.0f, 1.0f, this.resourcesProvider);
            if (r4 == null) goto L16;
            BackupImageView r0 = this.imageView;
            if (r14 == null) goto L14;
            r0.setImage(ImageLocation.getForDocument(r14, r13.cover), null, "webp", r4, r13);
            return;
        L14:
            r0.setImage(ImageLocation.getForDocument(r13.cover), null, "webp", r4, r13);
            return;
        L16:
            this.imageView.setImage(ImageLocation.getForDocument(r14, r13.cover), null, "webp", null, r13);
            return;
        L18:
            this.imageView.setImage(null, null, null, null, null, 0);
        }

        public TLRPC.StickerSetCovered getCover() {
            return this.cover;
        }
    }

    /* renamed from: $r8$lambda$1tSQX8j3FMZreSpkLJgC-0dehHI, reason: not valid java name */
    public static /* synthetic */ void m14094$r8$lambda$1tSQX8j3FMZreSpkLJgC0dehHI(ContentPreviewViewer r0, View r1) {
        r0.lambda$showUnlockPremiumView$7(r1);
    }

    /* renamed from: $r8$lambda$5I2frc1F0z-vGzeuv_VhRZEEo8k, reason: not valid java name */
    public static /* synthetic */ void m14095$r8$lambda$5I2frc1F0zvGzeuv_VhRZEEo8k(ContentPreviewViewer r0, View r1, ReactionsLayoutInBubble.VisibleReaction r2, boolean r3, boolean r4) {
        r0.lambda$showEmojiSelectorForStickers$5(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$7Vnt9PMCrIYSUtyL4Z_JtpBVheM(ActionBarPopupWindow.ActionBarPopupWindowLayout r0) {
        r0.getSwipeBack().closeForeground();
    }

    public static /* synthetic */ void $r8$lambda$FmTosfgd_vLKZGk4ogAPD85QkJ4(RecyclerListView r0, Object r1) {
        if (r0 == null) goto L5;
        r0.setOnItemClickListener((RecyclerListView.OnItemClickListener) r1);
        return;
    }

    public static /* synthetic */ void $r8$lambda$I2ypfppbNwLBeQ03JV9akNMDmVg(ContentPreviewViewer r0, RecyclerListView r1, int r2, Theme.ResourcesProvider r3) {
        r0.lambda$onInterceptTouchEvent$10(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$Jdz5Ez8Vj0r68F5f-3dbz2pODAQ, reason: not valid java name */
    public static /* synthetic */ void m14096$r8$lambda$Jdz5Ez8Vj0r68F5f3dbz2pODAQ(ContentPreviewViewer r0) {
        r0.lambda$showEmojiSelectorForStickers$6();
    }

    public static /* synthetic */ void $r8$lambda$OgxbI1adNIGIKCIOW7e3A3yGn0c(ContentPreviewViewer r0, View r1) {
        r0.lambda$addVoteOptions$4(r1);
    }

    /* renamed from: $r8$lambda$R3rlDOEDAEg6xgm4A-kp3KTdNaI, reason: not valid java name */
    public static /* synthetic */ void m14097$r8$lambda$R3rlDOEDAEg6xgm4Akp3KTdNaI(ContentPreviewViewer r0, TLRPC.TL_error r1, TLObject r2, List r3, TLRPC.TL_messages_getMyStickers r4) {
        r0.lambda$getMyStickersRemote$16(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$VwaxCwMdcxcupem9qDkcWLiFg0Q(ContentPreviewViewer r0) {
        r0.lambda$onDraw$14();
    }

    public static /* synthetic */ boolean $r8$lambda$XI2WBvKcJPtmMKCbricKU8qvFgg(ContentPreviewViewer r0, View r1, MotionEvent r2) {
        return r0.lambda$setParentActivity$12(r1, r2);
    }

    /* renamed from: $r8$lambda$bPEf-m3y3djm_vJxSFQgc8yss6M, reason: not valid java name */
    public static /* synthetic */ void m14098$r8$lambda$bPEfm3y3djm_vJxSFQgc8yss6M(ContentPreviewViewer r0, Bitmap r1, Bitmap r2) {
        r0.lambda$prepareBlurBitmap$15(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$l5USu0UUxXB5kSkdyAPvp5hn1OM(ContentPreviewViewer r0, List r1, TLRPC.TL_messages_getMyStickers r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$getMyStickersRemote$17(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$nPS2u3sEB5Wv7LEL66J9paod708(ContentPreviewViewer r0) {
        r0.lambda$close$13();
    }

    public static /* synthetic */ void $r8$lambda$qXe3xEfm3fqnCc_kQ2czkFlcjkU(ContentPreviewViewer r0, View r1) {
        r0.lambda$showUnlockPremiumView$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$qtsyO6wSKxr70PbZzNZM1Xggksk(ContentPreviewViewer r0, BaseFragment r1, Long r2) {
        r0.lambda$addVoteOptions$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$wZ9BMD5JUSPetNUtnq0g54tvOcU(ActionBarPopupWindow.ActionBarPopupWindowLayout r0, int r1, View r2) {
        r0.getSwipeBack().openForeground(r1);
    }

    /* renamed from: $r8$lambda$xHMJCV1xK-GrU9QYzI8luDEY2pU, reason: not valid java name */
    public static /* synthetic */ void m14099$r8$lambda$xHMJCV1xKGrU9QYzI8luDEY2pU(ContentPreviewViewer r0, View r1) {
        r0.lambda$addVoteOptions$3(r1);
    }

    /* renamed from: $r8$lambda$y0g-iljGJDkZeq4dMvGg78hQ4sc, reason: not valid java name */
    public static /* synthetic */ WindowInsetsCompat m14100$r8$lambda$y0giljGJDkZeq4dMvGg78hQ4sc(ContentPreviewViewer r0, View r1, WindowInsetsCompat r2) {
        return r0.lambda$setParentActivity$11(r1, r2);
    }

    /* renamed from: -$$Nest$fgetclearsInputField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14101$$Nest$fgetclearsInputField(ContentPreviewViewer r0) {
        return r0.clearsInputField;
    }

    /* renamed from: -$$Nest$fgetcloseOnDismiss, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14102$$Nest$fgetcloseOnDismiss(ContentPreviewViewer r0) {
        return r0.closeOnDismiss;
    }

    /* renamed from: -$$Nest$fgetcontainerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayoutDrawer m14103$$Nest$fgetcontainerView(ContentPreviewViewer r0) {
        return r0.containerView;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14104$$Nest$fgetcurrentAccount(ContentPreviewViewer r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetcurrentContentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14105$$Nest$fgetcurrentContentType(ContentPreviewViewer r0) {
        return r0.currentContentType;
    }

    /* renamed from: -$$Nest$fgetcurrentDocument, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Document m14106$$Nest$fgetcurrentDocument(ContentPreviewViewer r0) {
        return r0.currentDocument;
    }

    /* renamed from: -$$Nest$fgetcurrentMoveYProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m14107$$Nest$fgetcurrentMoveYProgress(ContentPreviewViewer r0) {
        return r0.currentMoveYProgress;
    }

    /* renamed from: -$$Nest$fgetcurrentPreviewCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m14108$$Nest$fgetcurrentPreviewCell(ContentPreviewViewer r0) {
        return r0.currentPreviewCell;
    }

    /* renamed from: -$$Nest$fgetcurrentQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m14109$$Nest$fgetcurrentQuery(ContentPreviewViewer r0) {
        return r0.currentQuery;
    }

    /* renamed from: -$$Nest$fgetcurrentStickerSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.InputStickerSet m14110$$Nest$fgetcurrentStickerSet(ContentPreviewViewer r0) {
        return r0.currentStickerSet;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContentPreviewViewerDelegate m14111$$Nest$fgetdelegate(ContentPreviewViewer r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdrawEffect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14112$$Nest$fgetdrawEffect(ContentPreviewViewer r0) {
        return r0.drawEffect;
    }

    /* renamed from: -$$Nest$fgeteffectImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageReceiver m14113$$Nest$fgeteffectImage(ContentPreviewViewer r0) {
        return r0.effectImage;
    }

    /* renamed from: -$$Nest$fgetfinalMoveY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m14114$$Nest$fgetfinalMoveY(ContentPreviewViewer r0) {
        return r0.finalMoveY;
    }

    /* renamed from: -$$Nest$fgetimportingSticker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SendMessagesHelper.ImportingSticker m14115$$Nest$fgetimportingSticker(ContentPreviewViewer r0) {
        return r0.importingSticker;
    }

    /* renamed from: -$$Nest$fgetinlineResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.BotInlineResult m14116$$Nest$fgetinlineResult(ContentPreviewViewer r0) {
        return r0.inlineResult;
    }

    /* renamed from: -$$Nest$fgetisPhotoEditor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14117$$Nest$fgetisPhotoEditor(ContentPreviewViewer r0) {
        return r0.isPhotoEditor;
    }

    /* renamed from: -$$Nest$fgetisRecentSticker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14118$$Nest$fgetisRecentSticker(ContentPreviewViewer r0) {
        return r0.isRecentSticker;
    }

    /* renamed from: -$$Nest$fgetisStickerEditor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14119$$Nest$fgetisStickerEditor(ContentPreviewViewer r0) {
        return r0.isStickerEditor;
    }

    /* renamed from: -$$Nest$fgetkeyboardHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14120$$Nest$fgetkeyboardHeight(ContentPreviewViewer r0) {
        return r0.keyboardHeight;
    }

    /* renamed from: -$$Nest$fgetlastInsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Insets m14121$$Nest$fgetlastInsets(ContentPreviewViewer r0) {
        return r0.lastInsets;
    }

    /* renamed from: -$$Nest$fgetmenuVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14122$$Nest$fgetmenuVisible(ContentPreviewViewer r0) {
        return r0.menuVisible;
    }

    /* renamed from: -$$Nest$fgetmoveY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m14123$$Nest$fgetmoveY(ContentPreviewViewer r0) {
        return r0.moveY;
    }

    /* renamed from: -$$Nest$fgetparentActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Activity m14124$$Nest$fgetparentActivity(ContentPreviewViewer r0) {
        return r0.parentActivity;
    }

    /* renamed from: -$$Nest$fgetparentObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Object m14125$$Nest$fgetparentObject(ContentPreviewViewer r0) {
        return r0.parentObject;
    }

    /* renamed from: -$$Nest$fgetpopupLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m14126$$Nest$fgetpopupLayout(ContentPreviewViewer r0) {
        return r0.popupLayout;
    }

    /* renamed from: -$$Nest$fgetreactionsLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReactionsContainerLayout m14127$$Nest$fgetreactionsLayout(ContentPreviewViewer r0) {
        return r0.reactionsLayout;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m14128$$Nest$fgetresourcesProvider(ContentPreviewViewer r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetscrimBlur3Factory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundDrawableViewFactory m14129$$Nest$fgetscrimBlur3Factory(ContentPreviewViewer r0) {
        return r0.scrimBlur3Factory;
    }

    /* renamed from: -$$Nest$fgetscrimBlur3SourceBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceBitmap m14130$$Nest$fgetscrimBlur3SourceBitmap(ContentPreviewViewer r0) {
        return r0.scrimBlur3SourceBitmap;
    }

    /* renamed from: -$$Nest$fgetselectedEmojis, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m14131$$Nest$fgetselectedEmojis(ContentPreviewViewer r0) {
        return r0.selectedEmojis;
    }

    /* renamed from: -$$Nest$fgetstartMoveY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m14132$$Nest$fgetstartMoveY(ContentPreviewViewer r0) {
        return r0.startMoveY;
    }

    /* renamed from: -$$Nest$fgetstickerEmojiLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StaticLayout m14133$$Nest$fgetstickerEmojiLayout(ContentPreviewViewer r0) {
        return r0.stickerEmojiLayout;
    }

    /* renamed from: -$$Nest$fgetstickerSetForCustomSticker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_messages_stickerSet m14134$$Nest$fgetstickerSetForCustomSticker(ContentPreviewViewer r0) {
        return r0.stickerSetForCustomSticker;
    }

    /* renamed from: -$$Nest$fgetwindowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m14135$$Nest$fgetwindowView(ContentPreviewViewer r0) {
        return r0.windowView;
    }

    /* renamed from: -$$Nest$fputcloseOnDismiss, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14136$$Nest$fputcloseOnDismiss(ContentPreviewViewer r0, boolean r1) {
        r0.closeOnDismiss = r1;
    }

    /* renamed from: -$$Nest$fputcurrentMoveYProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14137$$Nest$fputcurrentMoveYProgress(ContentPreviewViewer r0, float r1) {
        r0.currentMoveYProgress = r1;
    }

    /* renamed from: -$$Nest$fputcurrentPreviewCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14138$$Nest$fputcurrentPreviewCell(ContentPreviewViewer r0, View r1) {
        r0.currentPreviewCell = r1;
    }

    /* renamed from: -$$Nest$fputfinalMoveY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14139$$Nest$fputfinalMoveY(ContentPreviewViewer r0, float r1) {
        r0.finalMoveY = r1;
    }

    /* renamed from: -$$Nest$fputmenuVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14140$$Nest$fputmenuVisible(ContentPreviewViewer r0, boolean r1) {
        r0.menuVisible = r1;
    }

    /* renamed from: -$$Nest$fputmoveY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14141$$Nest$fputmoveY(ContentPreviewViewer r0, float r1) {
        r0.moveY = r1;
    }

    /* renamed from: -$$Nest$fputpopupLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14142$$Nest$fputpopupLayout(ContentPreviewViewer r0, View r1) {
        r0.popupLayout = r1;
    }

    /* renamed from: -$$Nest$fputstartMoveY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14143$$Nest$fputstartMoveY(ContentPreviewViewer r0, float r1) {
        r0.startMoveY = r1;
    }

    /* renamed from: -$$Nest$maddVoteOptions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14144$$Nest$maddVoteOptions(ContentPreviewViewer r0, ActionBarPopupWindow.ActionBarPopupWindowLayout r1) {
        return r0.addVoteOptions(r1);
    }

    /* renamed from: -$$Nest$mcanShowFullVotersList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14145$$Nest$mcanShowFullVotersList(ContentPreviewViewer r0) {
        return r0.canShowFullVotersList();
    }

    /* renamed from: -$$Nest$mdismissPopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14146$$Nest$mdismissPopupWindow(ContentPreviewViewer r0) {
        r0.dismissPopupWindow();
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14147$$Nest$mgetThemedColor(ContentPreviewViewer r0, int r1) {
        return r0.getThemedColor(r1);
    }

    /* renamed from: -$$Nest$monDraw, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14148$$Nest$monDraw(ContentPreviewViewer r0, Canvas r1) {
        r0.onDraw(r1);
    }

    /* renamed from: -$$Nest$mshowEmojiSelectorForStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14149$$Nest$mshowEmojiSelectorForStickers(ContentPreviewViewer r0) {
        r0.showEmojiSelectorForStickers();
    }

    /* renamed from: -$$Nest$mshowUnlockPremiumView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14150$$Nest$mshowUnlockPremiumView(ContentPreviewViewer r0) {
        r0.showUnlockPremiumView();
    }

    static {
    }

    public ContentPreviewViewer() {
        this.moveY = 0.0f;
        this.lastInsets = Insets.NONE;
        BlurredBackgroundSourceBitmap r0 = new BlurredBackgroundSourceBitmap();
        this.scrimBlur3SourceBitmap = r0;
        this.scrimBlur3Factory = new BlurredBackgroundDrawableViewFactory(r0);
        this.backgroundDrawable = new ColorDrawable(1895825408);
        this.centerImage = new ImageReceiver();
        this.effectImage = new ImageReceiver();
        this.isVisible = false;
        this.keyboardHeight = AndroidUtilities.dp(200.0f);
        this.paint = new Paint(1);
        this.showSheetRunnable = new AnonymousClass1(this);
    }

    private boolean addVoteOptions(ActionBarPopupWindow.ActionBarPopupWindowLayout r24) {
        ContentPreviewViewerDelegate r4 = this.delegate;
        if (r4 != null) goto L5;
        return false;
    L5:
        TLRPC.TL_messageMediaPoll r42 = r4.getPoll();
        TLRPC.PollAnswer r6 = this.delegate.getPollAnswer();
        if (r42 == null) goto L64;
        if (r42.poll == null) goto L64;
        if (r6 == null) goto L64;
        TLRPC.PollAnswerVoters r7 = MessageObject.getPollResult(r42, r6.option);
        if (r7 != null) goto L14;
    L18:
        boolean r8 = false;
    L20:
        if (MessageObject.isVoted(r42) == false) goto L22;
    L26:
        boolean r9 = false;
    L27:
        if (r9 == false) goto L29;
    L31:
        boolean r43 = false;
    L33:
        if (r8 == false) goto L47;
        RecentVotersCell r13 = new RecentVotersCell(r24.getContext(), this.currentAccount, this.resourcesProvider);
        ItemOptions r10 = ItemOptions.swipeback(r24, this.resourcesProvider);
        int r14 = r24.addViewToSwipeBack(r10.getLinearLayout());
        int r15 = Theme.key_actionBarDefaultSubmenuItem;
        r10.setGapBackgroundColor(Theme.multAlpha(Theme.getColor(r15, this.resourcesProvider), 0.06f));
        r10.setBlurBackgroundForSwipeback(this.scrimBlur3Factory, BlurredBackgroundProviderImpl.scrimMenuBackground(this.resourcesProvider), true);
        boolean r21 = true;
        r10.add(R.drawable.ic_ab_back, LocaleController.getString(R.string.Back), new ContentPreviewViewer$$ExternalSyntheticLambda5(r24));
        r10.addGap();
        MessageObject r2 = this.delegate.getPollMessageObject();
        Activity r11 = this.parentActivity;
        if ((r11 instanceof LaunchActivity) == false) goto L45;
        if (r2 == null) goto L45;
        LaunchActivity r112 = (LaunchActivity) r11;
        if (r112.getActionBarLayout() != null) goto L40;
    L42:
        BaseFragment r113 = null;
    L43:
        if (r113 == null) goto L45;
        int r12 = r15;
        int r22 = r14;
        r10.addView(r13.createListView(r113, r2.getDialogId(), r2.getId(), r6.option, r7.voters, new ContentPreviewViewer$$ExternalSyntheticLambda6(this, r113)));
    L46:
        r13.setText(LocaleController.formatPluralString("PollVotesCount", r7.voters, new Object[0]));
        r13.setRecentVoters(r7.recent_voters, false);
        r13.setLayoutParams(LayoutHelper.createLinear(-1, 48));
        r13.setBackground(Theme.createRadSelectorDrawable(getThemedColor(Theme.key_dialogButtonSelector), 12, 0));
        r13.setOnClickListener(new ContentPreviewViewer$$ExternalSyntheticLambda7(r24, r22));
        r24.addView(r13);
        ActionBarPopupWindow.GapView r23 = new ActionBarPopupWindow.GapView(r24.getContext(), this.resourcesProvider);
        r23.setTag(R.id.fit_width_tag, 1);
        r23.setColor(Theme.multAlpha(Theme.getColor(r12, this.resourcesProvider), 0.06f));
        r23.setLayoutParams(LayoutHelper.createLinear(-1, 8));
        r24.addView(r23);
    L48:
        if (r9 == false) goto L50;
        boolean r72 = false;
        ActionBarMenuItem.addItem(r24, R.drawable.msg_select, LocaleController.getString(R.string.PollSubmitVotesNoCaps), false, this.resourcesProvider).setOnClickListener(new ContentPreviewViewer$$ExternalSyntheticLambda8(this));
    L51:
        if (r43 == false) goto L53;
        ActionBarMenuItem.addItem(r24, R.drawable.msg_unvote, LocaleController.getString(R.string.Unvote), r72, this.resourcesProvider).setOnClickListener(new ContentPreviewViewer$$ExternalSyntheticLambda9(this));
    L53:
        if (r8 == true) goto L57;
        if (r9 == true) goto L56;
        if (r43 == false) goto L57;
    L56:
        ActionBarPopupWindow.GapView r25 = new ActionBarPopupWindow.GapView(r24.getContext(), this.resourcesProvider);
        r25.setTag(R.id.fit_width_tag, 1);
        r25.setColor(Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourcesProvider), 0.06f));
        r25.setLayoutParams(LayoutHelper.createLinear(-1, 8));
        r24.addView(r25);
    L57:
        if (r8 == true) goto L63;
        if (r9 == true) goto L63;
        if (r43 == true) goto L63;
        return false;
    L63:
        return r21;
    L50:
        r72 = false;
        goto L51
    L40:
        if (r112.getActionBarLayout().getLastFragment() == null) goto L42;
        r113 = r112.getActionBarLayout().getLastFragment();
    L45:
        r22 = r14;
        r12 = r15;
        goto L46
    L47:
        r21 = true;
        goto L48
    L29:
        if (MessageObject.canUnvote(r42) == false) goto L31;
        r43 = true;
        goto L33
    L22:
        if (r42.poll.closed == true) goto L26;
        if (this.delegate.isInScheduleMode() == true) goto L26;
        r9 = true;
        goto L27
    L14:
        if (r7.voters <= 0) goto L18;
        if (MessageObject.canShowVotersList(r42) == false) goto L18;
        r8 = true;
    L64:
        return false;
    }

    private boolean canShowFullVotersList() {
        ContentPreviewViewerDelegate r0 = this.delegate;
        if (r0 != null) goto L5;
        return false;
    L5:
        TLRPC.TL_messageMediaPoll r02 = r0.getPoll();
        TLRPC.PollAnswer r3 = this.delegate.getPollAnswer();
        if (r02 != null) goto L8;
    L18:
        return false;
    L8:
        if (r02.poll == null) goto L18;
        if (r3 == null) goto L18;
        TLRPC.PollAnswerVoters r32 = MessageObject.getPollResult(r02, r3.option);
        if (r32 != null) goto L14;
        return true;
    L14:
        if (r32.voters <= 0) goto L20;
        MessageObject.canShowVotersList(r02);
        return true;
    L20:
        return true;
    }

    private void dismissPopupWindow() {
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 == null) goto L6;
        r0.dismiss();
        this.popupWindow = null;
        return;
    L6:
        View r02 = this.popupLayout;
        if (r02 == null) goto L12;
        r02.animate().alpha(0.0f).scaleX(0.8f).scaleY(0.8f).translationY(AndroidUtilities.dp(-12.0f)).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(320).start();
        this.popupLayout = null;
        this.menuVisible = false;
        if (this.closeOnDismiss == false) goto L13;
        close();
        return;
    L13:
        return;
    }

    public static ContentPreviewViewer getInstance() {
        ContentPreviewViewer r0 = Instance;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(PhotoViewer.class);
        ContentPreviewViewer r02 = Instance;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        r02 = new ContentPreviewViewer();     // Catch: Throwable -> L9
        Instance = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(PhotoViewer.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    private void getMyStickersRemote(TLRPC.TL_messages_getMyStickers r3, List<TLRPC.StickerSetCovered> r4) {
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r3, new ContentPreviewViewer$$ExternalSyntheticLambda10(this, r4, r3));
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    public static boolean hasInstance() {
        if (Instance == null) goto L6;
        return true;
    L6:
        return false;
    }

    private /* synthetic */ void lambda$addVoteOptions$1(BaseFragment r6, Long r7) {
        Bundle r0 = new Bundle();
        if (r7.longValue() < 0) goto L5;
        r0.putLong("user_id", r7.longValue());
    L6:
        r6.presentFragment(new ProfileActivity(r0));
        dismissPopupWindow();
        return;
    L5:
        r0.putLong("chat_id", -r7.longValue());
        goto L6
    }

    private /* synthetic */ void lambda$addVoteOptions$3(View r1) {
        ContentPreviewViewerDelegate r12 = this.delegate;
        if (r12 == null) goto L5;
        r12.sendVote();
    L5:
        dismissPopupWindow();
    }

    private /* synthetic */ void lambda$addVoteOptions$4(View r1) {
        ContentPreviewViewerDelegate r12 = this.delegate;
        if (r12 == null) goto L5;
        r12.retractVote();
    L5:
        dismissPopupWindow();
    }

    private /* synthetic */ void lambda$close$13() {
        this.resourcesProvider = null;
    }

    private /* synthetic */ void lambda$getMyStickersRemote$16(TLRPC.TL_error r8, TLObject r9, List r10, TLRPC.TL_messages_getMyStickers r11) {
        if (r8 == null) goto L5;
        return;
    L5:
        if ((r9 instanceof TLRPC.TL_messages_myStickers) == false) goto L32;
        TLRPC.TL_messages_myStickers r92 = (TLRPC.TL_messages_myStickers) r9;
        ArrayList<TLRPC.StickerSetCovered> r82 = r92.sets;
        int r0 = r82.size();
        int r1 = 0;
    L8:
        if (r1 >= r0) goto L19;
        TLRPC.StickerSetCovered r3 = r82.get(r1);
        r1 = r1 + 1;
        TLRPC.StickerSetCovered r32 = r3;
        TLRPC.StickerSet r4 = r32.set;
        if (r4.emojis == true) goto L8;
        if (r4.masks == true) goto L8;
        TLRPC.TL_inputStickerSetID r42 = new TLRPC.TL_inputStickerSetID();
        r42.id = r32.set.id;
        TLRPC.TL_messages_stickerSet r2 = MediaDataController.getInstance(this.currentAccount).getStickerSet(r42, true);
        if (r2 == null) goto L17;
        if (r2.documents.size() >= 120) goto L8;
    L17:
        r10.add(r32);
        goto L8
    L19:
        if (r92.sets.size() != r11.limit) goto L33;
        ArrayList<TLRPC.StickerSetCovered> r83 = r92.sets;
        r11.offset_id = r83.get(r83.size() - 1).set.id;
        getMyStickersRemote(r11, r10);
        return;
    L33:
        return;
    }

    private /* synthetic */ void lambda$getMyStickersRemote$17(List r7, TLRPC.TL_messages_getMyStickers r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new ContentPreviewViewer$$ExternalSyntheticLambda12(this, r10, r9, r7, r8));
    }

    private /* synthetic */ void lambda$onDraw$14() {
        this.centerImage.setImageBitmap(null);
        PaintingOverlay r0 = this.paintingOverlay;
        if (r0 == null) goto L6;
        r0.reset();
        this.containerView.removeView(this.paintingOverlay);
        this.paintingOverlay = null;
        return;
    }

    private /* synthetic */ void lambda$onInterceptTouchEvent$10(RecyclerListView r13, int r14, Theme.ResourcesProvider r15) {
        if (this.openPreviewRunnable == null) goto L63;
        String r2 = null;
        r13.setOnItemClickListener(null);
        r13.requestDisallowInterceptTouchEvent(true);
        this.openPreviewRunnable = null;
        setParentActivity(AndroidUtilities.findActivity(r13.getContext()));
        this.clearsInputField = false;
        View r3 = this.currentPreviewCell;
        if ((r3 instanceof StickerEmojiCell) == false) goto L12;
        StickerEmojiCell r11 = (StickerEmojiCell) r3;
        TLRPC.Document r32 = r11.getSticker();
        SendMessagesHelper.ImportingSticker r4 = r11.getStickerPath();
        String r5 = MessageObject.findAnimatedEmojiEmoticon(r11.getSticker(), null, Integer.valueOf(this.currentAccount));
        ContentPreviewViewerDelegate r6 = this.delegate;
        if (r6 == null) goto L10;
        r2 = r6.getQuery(false);
    L10:
        open(r32, r4, r5, r2, null, r14, r11.isRecent(), r11.getParentObject(), this.resourcesProvider);
        r11.setScaled(true);
    L56:
        runLongPressHaptic(this.currentPreviewCell);     // Catch: Exception -> L55
    L51:
        ContentPreviewViewerDelegate r0 = this.delegate;
        if (r0 == null) goto L62;
        r0.resetTouch();
        return;
    L62:
        return;
    L12:
        if ((r3 instanceof StickerCell) == false) goto L18;
        StickerCell r112 = (StickerCell) r3;
        TLRPC.Document r33 = r112.getSticker();
        ContentPreviewViewerDelegate r42 = this.delegate;
        if (r42 == null) goto L16;
        r2 = r42.getQuery(false);
    L16:
        open(r33, null, null, r2, null, r14, false, r112.getParentObject(), r15);
        r112.setScaled(true);
        this.clearsInputField = r112.isClearsInputField();
        goto L56
    L18:
        if ((r3 instanceof ContextLinkCell) == false) goto L33;
        ContextLinkCell r113 = (ContextLinkCell) r3;
        TLRPC.Document r1 = r113.getDocument();
        ContentPreviewViewerDelegate r34 = this.delegate;
        if (r34 == null) goto L22;
        r2 = r34.getQuery(true);
    L22:
        String r43 = r2;
        TLRPC.BotInlineResult r52 = r113.getBotInlineResult();
        if (r113.getBotInlineResult() == null) goto L26;
        Object r22 = r113.getInlineBot();
    L27:
        open(r1, null, null, r43, r52, r14, false, r22, r15);
        if (r14 == 1) goto L30;
    L31:
        r113.setScaled(true);
        goto L56
    L30:
        if (this.isPhotoEditor == false) goto L56;
    L26:
        r22 = r113.getParentObject();
        goto L27
    L33:
        if ((r3 instanceof EmojiPacksAlert.EmojiImageView) == false) goto L38;
        TLRPC.Document r12 = ((EmojiPacksAlert.EmojiImageView) r3).getDocument();
        if (r12 == null) goto L58;
        open(r12, null, MessageObject.findAnimatedEmojiEmoticon(r12, null, Integer.valueOf(this.currentAccount)), null, null, r14, false, null, r15);
        goto L56
    L58:
        return;
    L38:
        if ((r3 instanceof EmojiView.ImageViewEmoji) == false) goto L43;
        TLRPC.Document r16 = ((EmojiView.ImageViewEmoji) r3).getDocument();
        if (r16 == null) goto L59;
        open(r16, null, MessageObject.findAnimatedEmojiEmoticon(r16, null, Integer.valueOf(this.currentAccount)), null, null, r14, false, null, r15);
        goto L56
    L59:
        return;
    L43:
        if ((r3 instanceof SuggestEmojiView.EmojiImageView) == false) goto L60;
        Drawable r17 = ((SuggestEmojiView.EmojiImageView) r3).drawable;
        if ((r17 instanceof AnimatedEmojiDrawable) == false) goto L47;
        TLRPC.Document r18 = ((AnimatedEmojiDrawable) r17).getDocument();
    L48:
        if (r18 == null) goto L61;
        open(r18, null, MessageObject.findAnimatedEmojiEmoticon(r18, null, Integer.valueOf(this.currentAccount)), null, null, r14, false, null, r15);
        goto L56
    L61:
        return;
    L47:
        r18 = null;
        goto L48
    L60:
        return;
    }

    private /* synthetic */ void lambda$prepareBlurBitmap$15(Bitmap r5, Bitmap r6) {
        this.centerImage.setVisible(true, false);
        this.blurrBitmap = r5;
        Shader.TileMode r3 = Shader.TileMode.CLAMP;
        BitmapShader r0 = new BitmapShader(r5, r3, r3);
        Matrix r52 = new Matrix();
        r52.setScale(15.0f, 15.0f);
        r0.setLocalMatrix(r52);
        if (Build.VERSION.SDK_INT < 33) goto L5;
        r0.setFilterMode(2);
    L5:
        this.paint.setFilterBitmap(true);
        this.paint.setShader(r0);
        this.scrimBlur3SourceBitmap.setBitmap(r6);
        Blur3Utils.checkBitmapSourceMatrixScale(this.scrimBlur3SourceBitmap, this.windowView);
        this.scrimBlur3Factory.invalidateAllLinkedViews();
        this.preparingBitmap = false;
        FrameLayoutDrawer r4 = this.containerView;
        if (r4 == null) goto L9;
        r4.invalidate();
        return;
    }

    private /* synthetic */ WindowInsetsCompat lambda$setParentActivity$11(View r1, WindowInsetsCompat r2) {
        this.lastInsets = AndroidUtilities.getDefaultWindowInsets(r2, false);
        return r2;
    }

    private /* synthetic */ boolean lambda$setParentActivity$12(View r3, MotionEvent r4) {
        if (r4.getAction() == 1) goto L9;
        if (r4.getAction() == 6) goto L9;
        if (r4.getAction() == 3) goto L9;
    L12:
        return true;
    L9:
        if (this.isStickerEditor == false) goto L11;
        closeWithMenu();
        goto L12
    L11:
        close();
        goto L12
    }

    private /* synthetic */ void lambda$showEmojiSelectorForStickers$5(View r2, ReactionsLayoutInBubble.VisibleReaction r3, boolean r4, boolean r5) {
        if (r3 == null) goto L21;
        CustomEmojiReactionsWindow r22 = this.reactionsLayout.getReactionsWindow();
        boolean r42 = this.selectedEmojis.contains(r3.emojicon);
        ArrayList<String> r52 = this.selectedEmojis;
        if (r42 == true) goto L7;
        r52.add(r3.emojicon);
        if (this.selectedEmojis.size() <= 7) goto L13;
        this.selectedEmojis.remove(0);
    L13:
        this.reactionsLayout.setSelectedEmojis(this.selectedEmojis);
        if (r22 == null) goto L20;
        this.reactionsLayout.setMessage(null, null, false);
        if (r22.getSelectAnimatedEmojiDialog() == null) goto L18;
        r22.getSelectAnimatedEmojiDialog().setSelectedReactions(this.selectedEmojis);
        r22.getSelectAnimatedEmojiDialog().setRecentReactions(this.reactionsLayout.allReactionsList);
    L18:
        r22.dismiss();
        return;
    L20:
        return;
    L7:
        if (r52.size() <= 1) goto L22;
        this.selectedEmojis.remove(r3.emojicon);
        goto L13
    L22:
        return;
    }

    private /* synthetic */ void lambda$showEmojiSelectorForStickers$6() {
        this.reactionsLayoutContainer.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(420).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
    }

    private /* synthetic */ void lambda$showUnlockPremiumView$7(View r1) {
        this.menuVisible = false;
        this.containerView.invalidate();
        close();
    }

    private /* synthetic */ void lambda$showUnlockPremiumView$8(View r3) {
        Activity r32 = this.parentActivity;
        if ((r32 instanceof LaunchActivity) == false) goto L10;
        LaunchActivity r33 = (LaunchActivity) r32;
        if (r33.getActionBarLayout() != null) goto L7;
    L9:
        r33.presentFragment(new PremiumPreviewFragment(PremiumPreviewFragment.featureTypeToServerString(5)));
        goto L10
    L7:
        if (r33.getActionBarLayout().getLastFragment() == null) goto L9;
        r33.getActionBarLayout().getLastFragment().dismissCurrentDialog();
    L10:
        this.menuVisible = false;
        this.containerView.invalidate();
        close();
    }

    @SuppressLint({"DrawAllocation"})
    private void onDraw(Canvas r15) {
        if (this.containerView != null) goto L5;
        return;
    L5:
        if (this.backgroundDrawable != null) goto L8;
        return;
    L8:
        if (this.menuVisible == false) goto L13;
        if (this.blurrBitmap != null) goto L13;
        prepareBlurBitmap();
    L13:
        if (this.blurrBitmap == null) goto L37;
        boolean r0 = this.menuVisible;
        if (r0 == false) goto L22;
        float r5 = this.blurProgress;
        if (r5 == 1.0f) goto L22;
        float r52 = r5 + 0.13333334f;
        this.blurProgress = r52;
        if (r52 <= 1.0f) goto L21;
        this.blurProgress = 1.0f;
    L21:
        this.containerView.invalidate();
    L29:
        float r02 = this.blurProgress;
        if (r02 == 0.0f) goto L37;
        if (this.blurrBitmap == null) goto L37;
        this.paint.setAlpha((int) (r02 * 255.0f));
        if (this.paint.getAlpha() == 255) goto L36;
        r15.drawColor(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider), this.blurProgress));
    L36:
        r15.drawPaint(this.paint);
    L22:
        if (r0 == true) goto L29;
        float r03 = this.blurProgress;
        if (r03 == 0.0f) goto L29;
        float r04 = r03 - 0.13333334f;
        this.blurProgress = r04;
        if (r04 >= 0.0f) goto L28;
        this.blurProgress = 0.0f;
    L28:
        this.containerView.invalidate();
    L37:
        this.backgroundDrawable.setAlpha((int) (this.showProgress * 180.0f));
        this.backgroundDrawable.setBounds(0, 0, this.containerView.getWidth(), this.containerView.getHeight());
        this.backgroundDrawable.draw(r15);
        r15.save();
        Insets r05 = this.lastInsets;
        int r4 = r05.bottom;
        int r06 = r05.top;
        int r42 = r4 + r06;
        if (this.currentContentType != 1) goto L40;
        int r53 = Math.min(this.containerView.getWidth(), this.containerView.getHeight() - r42) - AndroidUtilities.dp(40.0f);
    L45:
        int r9 = (r53 / 2) + r06;
        if (this.stickerEmojiLayout == null) goto L48;
        int r07 = AndroidUtilities.dp(40.0f);
    L49:
        float r08 = Math.max(r9 + r07, ((this.containerView.getHeight() - r42) - this.keyboardHeight) / 2);
        if (this.drawEffect == false) goto L52;
        r08 = r08 + AndroidUtilities.dp(40.0f);
    L52:
        r15.translate(this.containerView.getWidth() / 2, this.moveY + r08);
        int r09 = (int) (r53 * ((this.showProgress * 0.8f) / 0.8f));
        if (this.currentContentType != 3) goto L55;
        r15.translate(0.0f, AndroidUtilities.dp(70.0f));
    L55:
        boolean r43 = this.drawEffect;
        ImageReceiver r54 = this.centerImage;
        if (r43 == false) goto L58;
        float r44 = r09;
        float r92 = 0.6669f * r44;
        r54.setAlpha(this.showProgress);
        float r11 = r44 - r92;
        float r12 = r44 / 2.0f;
        this.centerImage.setImageCoords((r11 - r12) - (0.0546875f * r44), (r11 / 2.0f) - r12, r92, r92);
        this.centerImage.draw(r15);
        this.effectImage.setAlpha(this.showProgress);
        float r93 = (-r09) / 2.0f;
        this.effectImage.setImageCoords(r93, r93, r44, r44);
        this.effectImage.draw(r15);
    L60:
        if (this.paintingOverlay == null) goto L66;
        r15.save();
        float r45 = (-r09) / 2.0f;
        r15.translate(r45, r45);
        float r010 = r09;
        r15.scale(r010 / this.paintingOverlay.getWidth(), r010 / this.paintingOverlay.getHeight());
        this.paintingOverlay.setAlpha(this.showProgress);
        if (this.paintingOverlayClipPath != null) goto L64;
        this.paintingOverlayClipPath = new Path();
    L64:
        this.paintingOverlayClipPath.rewind();
        RectF r46 = AndroidUtilities.rectTmp;
        r46.set(0.0f, 0.0f, this.paintingOverlay.getWidth(), this.paintingOverlay.getHeight());
        float r011 = r010 / 8.0f;
        this.paintingOverlayClipPath.addRoundRect(r46, r011, r011, Path.Direction.CW);
        r15.clipPath(this.paintingOverlayClipPath);
        this.paintingOverlay.draw(r15);
        r15.restore();
    L66:
        if (this.currentContentType != 1) goto L73;
        if (this.isPhotoEditor == true) goto L73;
        Drawable r012 = this.slideUpDrawable;
        if (r012 == null) goto L73;
        int r013 = r012.getIntrinsicWidth();
        int r47 = this.slideUpDrawable.getIntrinsicHeight();
        int r55 = (int) (this.centerImage.getDrawRegion().top - AndroidUtilities.dp(((this.currentMoveY / AndroidUtilities.dp(60.0f)) * 6.0f) + 17.0f));
        this.slideUpDrawable.setAlpha((int) ((1.0f - this.currentMoveYProgress) * 255.0f));
        this.slideUpDrawable.setBounds((-r013) / 2, (-r47) + r55, r013 / 2, r55);
        this.slideUpDrawable.draw(r15);
    L73:
        if (this.stickerEmojiLayout != null) goto L75;
    L79:
        r15.restore();
        boolean r152 = this.isVisible;
        float r014 = this.showProgress;
        if (r152 == false) goto L88;
        if (r014 == 1.0f) goto L108;
        long r3 = System.currentTimeMillis();
        long r56 = r3 - this.lastUpdateTime;
        this.lastUpdateTime = r3;
        this.showProgress += r56 / 120.0f;
        this.containerView.invalidate();
        if (this.showProgress <= 1.0f) goto L109;
        this.showProgress = 1.0f;
        return;
    L109:
        return;
    L108:
        return;
    L88:
        if (r014 == 0.0f) goto L110;
        long r48 = System.currentTimeMillis();
        long r7 = r48 - this.lastUpdateTime;
        this.lastUpdateTime = r48;
        this.showProgress -= r7 / 120.0f;
        this.containerView.invalidate();
        if (this.showProgress >= 0.0f) goto L93;
        this.showProgress = 0.0f;
    L93:
        if (this.showProgress != 0.0f) goto L111;
        this.centerImage.setImageBitmap(null);
        AndroidUtilities.unlockOrientation(this.parentActivity);
        AndroidUtilities.runOnUIThread(new ContentPreviewViewer$$ExternalSyntheticLambda16(this));
        Bitmap r153 = this.blurrBitmap;
        if (r153 == null) goto L97;
        r153.recycle();
        this.blurrBitmap = null;
    L97:
        AndroidUtilities.updateViewVisibilityAnimated(this.unlockPremiumView, false, 1.0f, false);
        this.blurProgress = 0.0f;
    L102:
        e = move-exception;
        FileLog.e(e);
        return;
    L99:
        if (this.windowView.getParent() == null) goto L112;
        ((WindowManager) this.parentActivity.getSystemService("window")).removeView(this.windowView);     // Catch: Exception -> L102
        return;
    L112:
        return;
    L111:
        return;
    L110:
        return;
    L75:
        if (this.drawEffect == false) goto L77;
        r15.translate((-r0.getWidth()) / 2.0f, ((-this.effectImage.getImageHeight()) / 2.0f) - AndroidUtilities.dp(30.0f));
    L78:
        textPaint.setAlpha((int) (this.showProgress * 255.0f));
        this.stickerEmojiLayout.draw(r15);
        goto L79
    L77:
        r15.translate((-r0.getWidth()) / 2.0f, ((-this.centerImage.getImageHeight()) / 2.0f) - AndroidUtilities.dp(30.0f));
        goto L78
    L58:
        r54.setAlpha(this.showProgress);
        float r57 = (-r09) / 2.0f;
        float r94 = r09;
        this.centerImage.setImageCoords(r57, r57, r94, r94);
        this.centerImage.draw(r15);
        goto L60
    L48:
        r07 = 0;
        goto L49
    L40:
        boolean r58 = this.drawEffect;
        FrameLayoutDrawer r95 = this.containerView;
        if (r58 == false) goto L44;
        float r59 = Math.min(r95.getWidth(), this.containerView.getHeight() - r42) - AndroidUtilities.dpf2(40.0f);
    L43:
        r53 = (int) r59;
        goto L45
    L44:
        r59 = Math.min(r95.getWidth(), this.containerView.getHeight() - r42) / 1.8f;
        goto L43
    }

    private void prepareBlurBitmap() {
        if (this.parentActivity != null) goto L5;
        return;
    L5:
        if (this.preparingBitmap == true) goto L10;
        this.preparingBitmap = true;
        this.centerImage.setVisible(false, false);
        ScrimOptions.makeGlobalBlurBitmaps(new ContentPreviewViewer$$ExternalSyntheticLambda17(this));
        return;
    }

    private float rubberYPoisition(float r2, float r3) {
        float r0 = 1.0f;
        float r1 = -((1.0f - (1.0f / (((Math.abs(r2) * 0.55f) / r3) + 1.0f))) * r3);
        if (r2 < 0.0f) goto L7;
        r0 = -1.0f;
    L7:
        return r1 * r0;
    }

    private void showEmojiSelectorForStickers() {
        if (this.reactionsLayout != null) goto L5;
        ContentPreviewViewer r3 = this;
        AnonymousClass2 r2 = new AnonymousClass2(r3, 4, null, this.containerView.getContext(), UserConfig.selectedAccount, this.resourcesProvider);
        r3.reactionsLayout = r2;
        r2.skipEnterAnimation = true;
        r2.setPadding(0, AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f));
        r3.reactionsLayout.setClipChildren(false);
        r3.reactionsLayout.setClipToPadding(false);
        r3.reactionsLayout.setVisibility(0);
        r3.reactionsLayout.setHint(LocaleController.getString(R.string.StickersSetEmojiForSticker));
        r3.reactionsLayout.setBubbleOffset(-AndroidUtilities.dp(105.0f));
        r3.reactionsLayout.setMiniBubblesOffset(-AndroidUtilities.dp(14.0f));
        FrameLayout r11 = new FrameLayout(r3.containerView.getContext());
        r3.reactionsLayoutContainer = r11;
        r11.addView(r3.reactionsLayout, LayoutHelper.createFrame(-2, 116.0f, 1, 0.0f, 0.0f, 0.0f, 0.0f));
        r3.containerView.addView(r3.reactionsLayoutContainer, LayoutHelper.createFrame(-2, -2.0f, 1, 0.0f, 100.0f, 0.0f, 0.0f));
    L6:
        r3.reactionsLayout.setSelectedEmojis(r3.selectedEmojis);
        r3.reactionsLayout.setDelegate(new ContentPreviewViewer$$ExternalSyntheticLambda1(r3));
        r3.reactionsLayout.setMessage(null, null, false);
        r3.reactionsLayoutContainer.setScaleY(0.6f);
        r3.reactionsLayoutContainer.setScaleX(0.6f);
        r3.reactionsLayoutContainer.setAlpha(0.0f);
        AndroidUtilities.runOnUIThread(new ContentPreviewViewer$$ExternalSyntheticLambda2(r3), 10);
        return;
    L5:
        r3 = this;
        goto L6
    }

    private void showUnlockPremiumView() {
        if (this.unlockPremiumView != null) goto L5;
        UnlockPremiumView r0 = new UnlockPremiumView(this.containerView.getContext(), 0, this.resourcesProvider);
        this.unlockPremiumView = r0;
        this.containerView.addView(r0, LayoutHelper.createFrame(-1, -1.0f));
        this.unlockPremiumView.setOnClickListener(new ContentPreviewViewer$$ExternalSyntheticLambda3(this));
        this.unlockPremiumView.premiumButtonView.buttonLayout.setOnClickListener(new ContentPreviewViewer$$ExternalSyntheticLambda4(this));
    L5:
        AndroidUtilities.updateViewVisibilityAnimated(this.unlockPremiumView, false, 1.0f, false);
        AndroidUtilities.updateViewVisibilityAnimated(this.unlockPremiumView, true);
        this.unlockPremiumView.setTranslationY(0.0f);
    }

    public void clearDelegate(ContentPreviewViewerDelegate r2) {
        if (this.delegate != r2) goto L6;
        this.currentDocument = null;
        this.currentStickerSet = null;
        this.currentQuery = null;
        this.delegate = null;
        this.resourcesProvider = null;
        reset();
        return;
    }

    public void close() {
        if (this.parentActivity != null) goto L5;
        return;
    L5:
        if (this.menuVisible == true) goto L16;
        AndroidUtilities.cancelRunOnUIThread(this.showSheetRunnable);
        this.showProgress = 1.0f;
        this.lastUpdateTime = System.currentTimeMillis();
        this.containerView.invalidate();
        this.currentDocument = null;
        this.currentStickerSet = null;
        this.currentQuery = null;
        this.delegate = null;
        this.isVisible = false;
        AndroidUtilities.runOnUIThread(new ContentPreviewViewer$$ExternalSyntheticLambda0(this), 200);
        UnlockPremiumView r0 = this.unlockPremiumView;
        if (r0 == null) goto L10;
        r0.animate().alpha(0.0f).translationY(AndroidUtilities.dp(56.0f)).setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
    L10:
        FrameLayout r5 = this.reactionsLayoutContainer;
        if (r5 == null) goto L13;
        r5.animate().alpha(0.0f).setDuration(150).scaleX(0.6f).scaleY(0.6f).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
    L13:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{8});
        return;
    }

    public void closeWithMenu() {
        ReactionsContainerLayout r0 = this.reactionsLayout;
        if (r0 == null) goto L10;
        CustomEmojiReactionsWindow r02 = r0.getReactionsWindow();
        if (r02 == null) goto L10;
        if (r02.isShowing() == false) goto L10;
        r02.dismiss();
        return;
    L10:
        this.menuVisible = false;
        dismissPopupWindow();
        close();
    }

    public RecyclerListView createMyStickerPacksListView() {
        if (this.parentActivity != null) goto L6;
        return null;
    L6:
        ArrayList r0 = new ArrayList();
        r0.add(new TLRPC.TL_stickerSetNoCovered());
        TLRPC.TL_messages_getMyStickers r1 = new TLRPC.TL_messages_getMyStickers();
        r1.limit = 100;
        getMyStickersRemote(r1, r0);
        AnonymousClass5 r12 = new AnonymousClass5(this, this.parentActivity);
        r12.setLayoutManager(new LinearLayoutManager(this.parentActivity));
        r12.addItemDecoration(new AnonymousClass6(this, r0));
        r12.setAdapter(new AnonymousClass7(this, r0));
        return r12;
    }

    public void destroy() {
        this.isVisible = false;
        this.delegate = null;
        this.currentDocument = null;
        this.currentQuery = null;
        this.currentStickerSet = null;
        if (this.parentActivity != null) goto L5;
        return;
    L5:
        if (this.windowView == null) goto L24;
        Bitmap r2 = this.blurrBitmap;
        if (r2 == null) goto L10;
        r2.recycle();
        this.blurrBitmap = null;
    L10:
        this.blurProgress = 0.0f;
        this.menuVisible = false;
    L14:
        e = move-exception;
        FileLog.e(e);
    L19:
        Instance = null;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{8});
        return;
    L12:
        if (this.windowView.getParent() == null) goto L16;
        ((WindowManager) this.parentActivity.getSystemService("window")).removeViewImmediate(this.windowView);     // Catch: Exception -> L14
    L16:
        this.windowView = null;     // Catch: Exception -> L14
        goto L19
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public boolean onInterceptTouchEvent(MotionEvent r8, RecyclerListView r9, int r10, ContentPreviewViewerDelegate r11, Theme.ResourcesProvider r12) {
        this.delegate = r11;
        if (r11 == null) goto L5;
        this.isPhotoEditor = r11.isPhotoEditor();
        this.isStickerEditor = this.delegate.isStickerEditor();
    L5:
        ContentPreviewViewerDelegate r102 = this.delegate;
        if (r102 == null) goto L11;
        if (r102.can() == true) goto L11;
        return false;
    L11:
        if (r8.getAction() != 0) goto L64;
        int r103 = (int) r8.getX();
        int r82 = (int) r8.getY();
        int r0 = r9.getChildCount();
        int r1 = 0;
    L13:
        if (r1 >= r0) goto L64;
        View r2 = r9.getChildAt(r1);
        if (r2 == null) goto L16;
        int r3 = r2.getTop();
        int r4 = r2.getBottom();
        int r5 = r2.getLeft();
        int r6 = r2.getRight();
        if (r3 > r82) goto L63;
        if (r4 < r82) goto L63;
        if (r5 > r103) goto L63;
        if (r6 < r103) goto L63;
        if ((r2 instanceof StickerEmojiCell) == false) goto L30;
        if (((StickerEmojiCell) r2).showingBitmap() == false) goto L58;
        this.centerImage.setRoundRadius(0);
    L28:
        int r02 = 0;
    L59:
        if (r02 != (-1)) goto L61;
        return false;
    L61:
        this.startX = r103;
        this.startY = r82;
        this.currentPreviewCell = r2;
        ContentPreviewViewer$$ExternalSyntheticLambda15 r83 = new ContentPreviewViewer$$ExternalSyntheticLambda15(this, r9, r02, r12);
        this.openPreviewRunnable = r83;
        AndroidUtilities.runOnUIThread(r83, 200);
        return true;
    L58:
        r02 = -1;
        goto L59
    L30:
        if ((r2 instanceof StickerCell) == false) goto L35;
        if (((StickerCell) r2).showingBitmap() == false) goto L58;
        this.centerImage.setRoundRadius(0);
        goto L28
    L35:
        if ((r2 instanceof ContextLinkCell) == false) goto L45;
        ContextLinkCell r03 = (ContextLinkCell) r2;
        if (r03.showingBitmap() == false) goto L58;
        if (r03.isSticker() == false) goto L42;
        this.centerImage.setRoundRadius(0);
        goto L28
    L42:
        if (r03.isGif() == false) goto L58;
        this.centerImage.setRoundRadius(AndroidUtilities.dp(6.0f));
        r02 = 1;
        goto L59
    L45:
        if ((r2 instanceof EmojiPacksAlert.EmojiImageView) == false) goto L49;
        this.centerImage.setRoundRadius(0);
    L47:
        r02 = 2;
        goto L59
    L49:
        if ((r2 instanceof EmojiView.ImageViewEmoji) == false) goto L54;
        if (((EmojiView.ImageViewEmoji) r2).getSpan() == null) goto L54;
        this.centerImage.setRoundRadius(0);
    L54:
        if ((r2 instanceof SuggestEmojiView.EmojiImageView) == false) goto L58;
        if ((((SuggestEmojiView.EmojiImageView) r2).drawable instanceof AnimatedEmojiDrawable) == false) goto L58;
        this.centerImage.setRoundRadius(0);
    L63:
        r1 = r1 + 1;
        goto L13
    L16:
        return false;
    L64:
        return false;
    }

    public boolean onTouch(MotionEvent r16, RecyclerListView r17, int r18, Object r19, ContentPreviewViewerDelegate r20, Theme.ResourcesProvider r21) {
        this.delegate = r20;
        if (r20 == null) goto L5;
        this.isPhotoEditor = r20.isPhotoEditor();
        this.isStickerEditor = this.delegate.isStickerEditor();
    L5:
        ContentPreviewViewerDelegate r2 = this.delegate;
        if (r2 == null) goto L11;
        if (r2.can() == true) goto L11;
        return false;
    L11:
        if (this.openPreviewRunnable == null) goto L13;
    L14:
        String r4 = null;
        if (r16.getAction() != 1) goto L17;
    L160:
        AndroidUtilities.runOnUIThread(new ContentPreviewViewer$$ExternalSyntheticLambda11(r17, r19), 150);
        Runnable r1 = this.openPreviewRunnable;
        if (r1 == null) goto L164;
        AndroidUtilities.cancelRunOnUIThread(r1);
        this.openPreviewRunnable = null;
    L177:
        return false;
    L164:
        if (isVisible() == false) goto L177;
        close();
        View r12 = this.currentPreviewCell;
        if (r12 == null) goto L177;
        if ((r12 instanceof StickerEmojiCell) == false) goto L171;
        ((StickerEmojiCell) r12).setScaled(false);
    L176:
        this.currentPreviewCell = null;
        goto L177
    L171:
        if ((r12 instanceof StickerCell) == false) goto L174;
        ((StickerCell) r12).setScaled(false);
        goto L176
    L174:
        if ((r12 instanceof ContextLinkCell) == false) goto L176;
        ((ContextLinkCell) r12).setScaled(false);
        goto L176
    L17:
        if (r16.getAction() == 3) goto L160;
        if (r16.getAction() == 6) goto L160;
        if (r16.getAction() == 0) goto L177;
        int r5 = 2;
        if (this.isVisible == false) goto L153;
        if (r16.getAction() == 2) goto L28;
    L151:
        return true;
    L28:
        if (this.currentContentType == 1) goto L30;
    L48:
        int r22 = (int) r16.getX();
        int r6 = (int) r16.getY();
        int r7 = r17.getChildCount();
        int r8 = 0;
    L49:
        if (r8 >= r7) goto L151;
        View r9 = r17.getChildAt(r8);
        if (r9 == null) goto L52;
        int r11 = r9.getTop();
        int r122 = r9.getBottom();
        int r13 = r9.getLeft();
        int r14 = r9.getRight();
        if (r11 > r6) goto L150;
        if (r122 < r6) goto L150;
        if (r13 > r22) goto L150;
        if (r14 < r22) goto L150;
        if ((r9 instanceof StickerEmojiCell) == false) goto L64;
        this.centerImage.setRoundRadius(0);
    L62:
        int r62 = 0;
    L85:
        if (r62 == (-1)) goto L151;
        if (r9 == this.currentPreviewCell) goto L151;
        ContentPreviewViewerDelegate r15 = this.delegate;
        if (r15 == null) goto L92;
        r15.resetTouch();
    L92:
        View r110 = this.currentPreviewCell;
        if ((r110 instanceof StickerEmojiCell) == false) goto L96;
        ((StickerEmojiCell) r110).setScaled(false);
    L101:
        this.clearsInputField = false;
        this.menuVisible = false;
        this.closeOnDismiss = false;
        dismissPopupWindow();
        AndroidUtilities.updateViewVisibilityAnimated(this.unlockPremiumView, false);
        this.currentPreviewCell = r9;
        if ((r9 instanceof StickerEmojiCell) == false) goto L108;
        StickerEmojiCell r112 = (StickerEmojiCell) r9;
        TLRPC.Document r111 = r112.getSticker();
        SendMessagesHelper.ImportingSticker r23 = r112.getStickerPath();
        String r52 = MessageObject.findAnimatedEmojiEmoticon(r112.getSticker(), null, Integer.valueOf(this.currentAccount));
        ContentPreviewViewerDelegate r72 = this.delegate;
        if (r72 == null) goto L106;
        r4 = r72.getQuery(false);
    L106:
        open(r111, r23, r52, r4, null, r62, r112.isRecent(), r112.getParentObject(), r21);
        r112.setScaled(true);
    L148:
        runSmoothHaptic();
        return true;
    L108:
        if ((r9 instanceof StickerCell) == false) goto L114;
        StickerCell r113 = (StickerCell) r9;
        TLRPC.Document r114 = r113.getSticker();
        String r24 = MessageObject.findAnimatedEmojiEmoticon(r113.getSticker(), null, Integer.valueOf(this.currentAccount));
        ContentPreviewViewerDelegate r53 = this.delegate;
        if (r53 == null) goto L112;
        r4 = r53.getQuery(false);
    L112:
        open(r114, null, r24, r4, null, r62, false, r113.getParentObject(), r21);
        r113.setScaled(true);
        this.clearsInputField = r113.isClearsInputField();
        goto L148
    L114:
        if ((r9 instanceof ContextLinkCell) == false) goto L129;
        ContextLinkCell r115 = (ContextLinkCell) r9;
        TLRPC.Document r116 = r115.getDocument();
        ContentPreviewViewerDelegate r25 = this.delegate;
        if (r25 == null) goto L118;
        r4 = r25.getQuery(true);
    L118:
        TLRPC.BotInlineResult r54 = r115.getBotInlineResult();
        if (r115.getBotInlineResult() == null) goto L122;
        Object r26 = r115.getInlineBot();
    L123:
        open(r116, null, null, r4, r54, r62, false, r26, r21);
        if (r62 == 1) goto L126;
    L127:
        r115.setScaled(true);
        goto L148
    L126:
        if (this.isPhotoEditor == false) goto L148;
    L122:
        r26 = r115.getParentObject();
        goto L123
    L129:
        if ((r9 instanceof EmojiPacksAlert.EmojiImageView) == false) goto L134;
        TLRPC.Document r117 = ((EmojiPacksAlert.EmojiImageView) r9).getDocument();
        if (r117 == null) goto L148;
        open(r117, null, MessageObject.findAnimatedEmojiEmoticon(r117, null, Integer.valueOf(this.currentAccount)), null, null, r62, false, null, r21);
        goto L148
    L134:
        if ((r9 instanceof EmojiView.ImageViewEmoji) == false) goto L140;
        TLRPC.Document r118 = ((EmojiView.ImageViewEmoji) r9).getDocument();
        if (r118 == null) goto L138;
        open(r118, null, MessageObject.findAnimatedEmojiEmoticon(r118, null, Integer.valueOf(this.currentAccount)), null, null, r62, false, null, r21);
        goto L148
    L138:
        return false;
    L140:
        if ((r9 instanceof SuggestEmojiView.EmojiImageView) == false) goto L148;
        Drawable r119 = ((SuggestEmojiView.EmojiImageView) r9).drawable;
        if ((r119 instanceof AnimatedEmojiDrawable) == false) goto L144;
        TLRPC.Document r120 = ((AnimatedEmojiDrawable) r119).getDocument();
    L145:
        if (r120 != null) goto L147;
        return false;
    L147:
        open(r120, null, MessageObject.findAnimatedEmojiEmoticon(r120, null, Integer.valueOf(this.currentAccount)), null, null, r62, false, null, r21);
        goto L148
    L144:
        r120 = null;
        goto L145
    L96:
        if ((r110 instanceof StickerCell) == false) goto L99;
        ((StickerCell) r110).setScaled(false);
        goto L101
    L99:
        if ((r110 instanceof ContextLinkCell) == false) goto L101;
        ((ContextLinkCell) r110).setScaled(false);
        goto L101
    L64:
        if ((r9 instanceof StickerCell) == false) goto L67;
        this.centerImage.setRoundRadius(0);
        goto L62
    L67:
        if ((r9 instanceof ContextLinkCell) == false) goto L77;
        ContextLinkCell r121 = (ContextLinkCell) r9;
        if (r121.isSticker() == false) goto L72;
        this.centerImage.setRoundRadius(0);
        r5 = 0;
    L75:
        r62 = r5;
        goto L85
    L72:
        if (r121.isGif() == false) goto L74;
        this.centerImage.setRoundRadius(AndroidUtilities.dp(6.0f));
        r5 = 1;
        goto L75
    L74:
        r5 = -1;
        goto L75
    L77:
        if ((r9 instanceof EmojiPacksAlert.EmojiImageView) == false) goto L80;
        this.centerImage.setRoundRadius(0);
        goto L75
    L80:
        if ((r9 instanceof EmojiView.ImageViewEmoji) == true) goto L82;
    L84:
        r62 = -1;
        goto L85
    L82:
        if (((EmojiView.ImageViewEmoji) r9).getSpan() == null) goto L84;
        this.centerImage.setRoundRadius(0);
    L150:
        r8 = r8 + 1;
        goto L49
    L52:
        return false;
    L30:
        if (this.isPhotoEditor == true) goto L48;
        if (this.menuVisible == false) goto L34;
    L47:
        return true;
    L34:
        if (this.showProgress != 1.0f) goto L47;
        if (this.lastTouchY != (-10000.0f)) goto L38;
        this.lastTouchY = r16.getY();
        this.currentMoveY = 0.0f;
        this.moveY = 0.0f;
        goto L47
    L38:
        float r123 = r16.getY();
        float r3 = this.currentMoveY + (r123 - this.lastTouchY);
        this.currentMoveY = r3;
        this.lastTouchY = r123;
        if (r3 <= 0.0f) goto L42;
        this.currentMoveY = 0.0f;
    L44:
        this.moveY = rubberYPoisition(this.currentMoveY, AndroidUtilities.dp(200.0f));
        this.containerView.invalidate();
        if (this.currentMoveY > (-AndroidUtilities.dp(55.0f))) goto L47;
        AndroidUtilities.cancelRunOnUIThread(this.showSheetRunnable);
        this.showSheetRunnable.run();
        goto L47
    L42:
        if (r3 >= (-AndroidUtilities.dp(60.0f))) goto L44;
        this.currentMoveY = -AndroidUtilities.dp(60.0f);
        goto L44
    L153:
        if (this.openPreviewRunnable == null) goto L177;
        if (r16.getAction() == 2) goto L157;
        AndroidUtilities.cancelRunOnUIThread(this.openPreviewRunnable);
        this.openPreviewRunnable = null;
        goto L177
    L157:
        if (Math.hypot(this.startX - r16.getX(), this.startY - r16.getY()) <= AndroidUtilities.dp(10.0f)) goto L177;
        AndroidUtilities.cancelRunOnUIThread(this.openPreviewRunnable);
        this.openPreviewRunnable = null;
        goto L177
    L13:
        if (isVisible() == false) goto L177;
        goto L14
    }

    public void open(TLRPC.Document r12, SendMessagesHelper.ImportingSticker r13, String r14, String r15, TLRPC.BotInlineResult r16, int r17, boolean r18, Object r19, Theme.ResourcesProvider r20) {
        open(r12, r13, r14, r15, r16, r17, r18, r19, r20, 0);
    }

    public void open(TLRPC.Document r40, SendMessagesHelper.ImportingSticker r41, String r42, String r43, TLRPC.BotInlineResult r44, int r45, boolean r46, Object r47, Theme.ResourcesProvider r48, int r49) {
        if (this.parentActivity != null) goto L5;
        return;
    L5:
        if (this.windowView == null) goto L144;
        this.resourcesProvider = r48;
        this.isRecentSticker = r46;
        this.stickerEmojiLayout = null;
        boolean r10 = AndroidUtilities.isDarkColor(Theme.getColor(Theme.key_windowBackgroundWhite, r48));
        ColorDrawable r11 = this.backgroundDrawable;
        if (r10 == false) goto L10;
        int r102 = 1895825408;
    L11:
        r11.setColor(r102);
        this.drawEffect = false;
        this.centerImage.setColorFilter(null);
        if (r45 == 0) goto L34;
        if (r45 == 2) goto L34;
        if (r45 == 3) goto L34;
        if (r40 == null) goto L23;
        TLRPC.PhotoSize r7 = FileLoader.getClosestPhotoSizeWithSize(r40.thumbs, 90);
        TLRPC.VideoSize r9 = MessageObject.getDocumentVideoThumb(r40);
        ImageLocation r112 = ImageLocation.getForDocument(r40);
        r112.imageType = 2;
        ImageReceiver r14 = this.centerImage;
        if (r9 == null) goto L22;
        r14.setImage(r112, null, ImageLocation.getForDocument(r9, r40), null, ImageLocation.getForDocument(r7, r40), "90_90_b", null, r40.size, null, "gif" + r40, 0);
    L33:
        AndroidUtilities.cancelRunOnUIThread(this.showSheetRunnable);
        AndroidUtilities.runOnUIThread(this.showSheetRunnable, 800);
    L112:
        if (this.centerImage.getLottieAnimation() == null) goto L114;
        int r92 = 0;
        this.centerImage.getLottieAnimation().setCurrentFrame(0);
    L116:
        if (this.drawEffect == true) goto L118;
    L120:
        this.currentContentType = r45;
        this.currentDocument = r40;
        this.importingSticker = r41;
        this.currentQuery = r43;
        this.inlineResult = r44;
        this.parentObject = r47;
        this.resourcesProvider = r48;
        this.containerView.invalidate();
        if (this.isVisible == true) goto L143;
        AndroidUtilities.lockOrientation(this.parentActivity);
    L127:
        e = move-exception;
        FileLog.e(e);
    L129:
        ((WindowManager) this.parentActivity.getSystemService("window")).addView(this.windowView, this.windowLayoutParams);
        this.isVisible = true;
        this.showProgress = 0.0f;
        this.lastTouchY = -10000.0f;
        this.currentMoveYProgress = 0.0f;
        this.finalMoveY = 0.0f;
        this.currentMoveY = 0.0f;
        this.moveY = 0.0f;
        this.lastUpdateTime = System.currentTimeMillis();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{8});
        return;
    L124:
        if (this.windowView.getParent() == null) goto L129;
        ((WindowManager) this.parentActivity.getSystemService("window")).removeView(this.windowView);     // Catch: Exception -> L127
        goto L129
    L143:
        return;
    L118:
        if (this.effectImage.getLottieAnimation() == null) goto L120;
        this.effectImage.getLottieAnimation().setCurrentFrame(r92);
        goto L120
    L114:
        r92 = 0;
        goto L116
    L22:
        r14.setImage(r112, null, ImageLocation.getForDocument(r7, r40), "90_90_b", r40.size, null, "gif" + r40, 0);
        goto L33
    L23:
        if (r44 != null) goto L25;
        return;
    L25:
        if (r44.content == null) goto L145;
        TLRPC.WebDocument r72 = r44.thumb;
        if ((r72 instanceof TLRPC.TL_webDocument) == true) goto L30;
    L32:
        this.centerImage.setImage(ImageLocation.getForWebFile(WebFile.createWithWebDocument(r44.content)), null, ImageLocation.getForWebFile(WebFile.createWithWebDocument(r44.thumb)), "90_90_b", r44.content.size, null, "gif" + r44, 1);
        goto L33
    L30:
        if ("video/mp4".equals(r72.mime_type) == false) goto L32;
        this.centerImage.setImage(ImageLocation.getForWebFile(WebFile.createWithWebDocument(r44.content)), null, ImageLocation.getForWebFile(WebFile.createWithWebDocument(r44.thumb)), null, ImageLocation.getForWebFile(WebFile.createWithWebDocument(r44.thumb)), "90_90_b", null, r44.content.size, null, "gif" + r44, 1);
        goto L33
    L145:
        return;
    L34:
        if (r40 != null) goto L38;
        if (r41 != null) goto L38;
        return;
    L38:
        if (textPaint != null) goto L40;
        TextPaint r103 = new TextPaint(1);
        textPaint = r103;
        r103.setTextSize(AndroidUtilities.dp(24.0f));
    L40:
        this.effectImage.clearImage();
        this.drawEffect = false;
        if (r40 == null) goto L90;
        int r12 = 0;
    L44:
        if (r12 >= r40.attributes.size()) goto L51;
        TLRPC.DocumentAttribute r13 = r40.attributes.get(r12);
        if ((r13 instanceof TLRPC.TL_documentAttributeSticker) == false) goto L50;
        TLRPC.InputStickerSet r113 = r13.stickerset;
        if (r113 == null) goto L50;
    L52:
        if (r42 == null) goto L54;
        this.stickerEmojiLayout = new StaticLayout(AndroidUtilities.replaceCharSequence("…", TextUtils.ellipsize(Emoji.replaceEmoji(r42, textPaint.getFontMetricsInt(), false), textPaint, AndroidUtilities.dp(200.0f), TextUtils.TruncateAt.END), _UrlKt.FRAGMENT_ENCODE_SET), textPaint, AndroidUtilities.dp(200.0f), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    L54:
        if (r113 != null) goto L56;
        if (r45 == 2) goto L56;
    L65:
        TLRPC.TL_messages_stickerSet r3 = MediaDataController.getInstance(this.currentAccount).getStickerSet(r113, true);
        if (r3 != null) goto L68;
    L70:
        this.currentStickerSet = r113;
        TLRPC.PhotoSize r32 = FileLoader.getClosestPhotoSizeWithSize(r40.thumbs, 90);
        boolean r73 = MessageObject.isVideoStickerDocument(r40);
        ImageReceiver r114 = this.centerImage;
        if (r73 == false) goto L73;
        r114.setImage(ImageLocation.getForDocument(r40), null, ImageLocation.getForDocument(r32, r40), null, null, 0, "webp", this.currentStickerSet, 1);
    L77:
        if (MessageObject.isTextColorEmoji(r40) == false) goto L80;
        this.centerImage.setColorFilter(Theme.getAnimatedEmojiColorFilter(r48));
    L80:
        if (this.stickerEmojiLayout != null) goto L112;
        int r33 = 0;
    L83:
        if (r33 >= r40.attributes.size()) goto L112;
        TLRPC.DocumentAttribute r74 = r40.attributes.get(r33);
        if ((r74 instanceof TLRPC.TL_documentAttributeSticker) == false) goto L89;
        if (TextUtils.isEmpty(r74.alt) == true) goto L89;
        this.stickerEmojiLayout = new StaticLayout(AndroidUtilities.replaceCharSequence("…", TextUtils.ellipsize(Emoji.replaceEmoji(r74.alt, textPaint.getFontMetricsInt(), false), textPaint, AndroidUtilities.dp(200.0f), TextUtils.TruncateAt.END), _UrlKt.FRAGMENT_ENCODE_SET), textPaint, AndroidUtilities.dp(200.0f), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    L89:
        r33 = r33 + 1;
        goto L83
    L73:
        r114.setImage(ImageLocation.getForDocument(r40), null, ImageLocation.getForDocument(r32, r40), null, "webp", this.currentStickerSet, 1);
        if (MessageObject.isPremiumSticker(r40) == false) goto L77;
        this.drawEffect = true;
        this.effectImage.setImage(ImageLocation.getForDocument(MessageObject.getPremiumStickerAnimation(r40), r40), null, null, null, "tgs", this.currentStickerSet, 1);
        goto L77
    L68:
        if (r3.documents.isEmpty() == false) goto L70;
        r113 = null;
    L56:
        ContentPreviewViewerDelegate r34 = this.delegate;
        if (r34 != null) goto L59;
    L60:
        AndroidUtilities.cancelRunOnUIThread(this.showSheetRunnable);
        Runnable r35 = this.showSheetRunnable;
        if (r49 <= 0) goto L63;
        long r122 = r49;
    L64:
        AndroidUtilities.runOnUIThread(r35, r122);
        goto L65
    L63:
        r122 = 800;
        goto L64
    L59:
        if (r34.needMenu() == false) goto L65;
    L50:
        r12 = r12 + 1;
        goto L44
    L51:
        r113 = null;
        goto L52
    L90:
        if (r41 == null) goto L112;
        ImageReceiver r115 = this.centerImage;
        String r123 = r41.path;
        if (r41.animated == false) goto L94;
        String r26 = "tgs";
    L95:
        r115.setImage(r123, null, null, r26, 0);
        if (r41.videoEditedInfo != null) goto L98;
        boolean r142 = false;
    L102:
        if (r42 == null) goto L105;
        this.stickerEmojiLayout = new StaticLayout(AndroidUtilities.replaceCharSequence("…", TextUtils.ellipsize(Emoji.replaceEmoji(r42, textPaint.getFontMetricsInt(), r142), textPaint, AndroidUtilities.dp(200.0f), TextUtils.TruncateAt.END), _UrlKt.FRAGMENT_ENCODE_SET), textPaint, AndroidUtilities.dp(200.0f), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    L105:
        if (this.delegate.needMenu() == false) goto L112;
        AndroidUtilities.cancelRunOnUIThread(this.showSheetRunnable);
        Runnable r36 = this.showSheetRunnable;
        if (r49 <= 0) goto L109;
        long r124 = r49;
    L110:
        AndroidUtilities.runOnUIThread(r36, r124);
        goto L112
    L109:
        r124 = 800;
        goto L110
    L98:
        if (this.paintingOverlay != null) goto L100;
        PaintingOverlay r116 = new PaintingOverlay(this.containerView.getContext());
        this.paintingOverlay = r116;
        this.containerView.addView(r116, new FrameLayout.LayoutParams(512, 512));
    L100:
        r142 = false;
        this.paintingOverlay.setEntities(r41.videoEditedInfo.mediaEntities, true, true, false);
        goto L102
    L94:
        r26 = null;
        goto L95
    L10:
        r102 = 1692853990;
        goto L11
    }

    public void reset() {
        Runnable r0 = this.openPreviewRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.openPreviewRunnable = null;
    L5:
        View r02 = this.currentPreviewCell;
        if (r02 != null) goto L8;
        return;
    L8:
        if ((r02 instanceof StickerEmojiCell) == false) goto L11;
        ((StickerEmojiCell) r02).setScaled(false);
    L16:
        this.currentPreviewCell = null;
        return;
    L11:
        if ((r02 instanceof StickerCell) == false) goto L14;
        ((StickerCell) r02).setScaled(false);
        goto L16
    L14:
        if ((r02 instanceof ContextLinkCell) == false) goto L16;
        ((ContextLinkCell) r02).setScaled(false);
        goto L16
    }

    public void runLongPressHaptic(View r2) {
        if (ExteraConfig.getInAppVibration() == false) goto L12;
        if (r2 != null) goto L10;
        return;
    L10:
        r2.performHapticFeedback(VibratorUtils.getType(0), 3);     // Catch: Exception -> L9
        return;
    L14:
        return;
    }

    public void runSmoothHaptic() {
        if (ExteraConfig.getInAppVibration() == false) goto L16;
        if (Build.VERSION.SDK_INT >= 26) goto L8;
        return;
    L8:
        if (this.vibrationEffect != null) goto L10;
        this.vibrationEffect = VibrationEffect.createWaveform(new long[]{0, 2}, -1);     // Catch: Exception -> L12
    L10:
        VibratorUtils.vibrateEffect(this.vibrationEffect);     // Catch: Exception -> L12
        return;
    L17:
        return;
    }

    public void setDelegate(ContentPreviewViewerDelegate r1) {
        this.delegate = r1;
        if (r1 == null) goto L6;
        this.isPhotoEditor = r1.isPhotoEditor();
        this.isStickerEditor = this.delegate.isStickerEditor();
        return;
    }

    public void setFocusable(boolean r3) {
        WindowManager.LayoutParams r0 = this.windowLayoutParams;
        if (r3 == false) goto L5;
        r0.flags &= -131073;
        r0.softInputMode = 16;
    L12:
        ((WindowManager) this.parentActivity.getSystemService("window")).updateViewLayout(this.windowView, this.windowLayoutParams);     // Catch: Exception -> L9
        return;
    L9:
        e = move-exception;
        FileLog.e(e);
        return;
    L5:
        r0.flags |= 131072;
        goto L12
    }

    public void setParentActivity(Activity r5) {
        int r0 = UserConfig.selectedAccount;
        this.currentAccount = r0;
        this.centerImage.setCurrentAccount(r0);
        this.centerImage.setLayerNum(Integer.MAX_VALUE);
        this.effectImage.setCurrentAccount(this.currentAccount);
        this.effectImage.setLayerNum(Integer.MAX_VALUE);
        if (this.parentActivity != r5) goto L5;
        return;
    L5:
        this.parentActivity = r5;
        this.slideUpDrawable = r5.getResources().getDrawable(R.drawable.preview_arrow);
        this.windowView = new AnonymousClass3(this, r5);
        this.scrimBlur3Factory.setSourceRootView(new ViewPositionWatcher(this.windowView), this.windowView);
        this.scrimBlur3Factory.setLinkedViewsRef(new ReferenceList());
        this.windowView.setFocusable(true);
        this.windowView.setFocusableInTouchMode(true);
        this.windowView.setSystemUiVisibility(1792);
        ViewCompat.setOnApplyWindowInsetsListener(this.windowView, new ContentPreviewViewer$$ExternalSyntheticLambda13(this));
        AnonymousClass4 r02 = new AnonymousClass4(this, r5);
        this.containerView = r02;
        r02.setFocusable(false);
        this.containerView.setHapticFeedbackEnabled(ExteraConfig.getInAppVibration());
        this.windowView.addView(this.containerView, LayoutHelper.createFrame(-1, -1, 51));
        this.containerView.setOnTouchListener(new ContentPreviewViewer$$ExternalSyntheticLambda14(this));
        MessagesController.getInstance(this.currentAccount);
        this.keyboardHeight = MessagesController.getGlobalEmojiSettings().getInt("kbd_height", AndroidUtilities.dp(200.0f));
        WindowManager.LayoutParams r52 = new WindowManager.LayoutParams();
        this.windowLayoutParams = r52;
        r52.height = -1;
        r52.format = -3;
        r52.width = -1;
        r52.gravity = 48;
        r52.type = 99;
        r52.flags = -2147286784;
        AndroidUtilities.applyEdgeToEdgeLayoutParams(r52);
        this.centerImage.setAspectFit(true);
        this.centerImage.setInvalidateAll(true);
        this.centerImage.setParentView(this.containerView);
        this.effectImage.setAspectFit(true);
        this.effectImage.setInvalidateAll(true);
        this.effectImage.setParentView(this.containerView);
    }

    public void setStickerSetForCustomSticker(TLRPC.TL_messages_stickerSet r1) {
        this.stickerSetForCustomSticker = r1;
    }

    public void showCustomStickerActions(String r11, VideoEditedInfo r12, View r13, ArrayList<String> r14, ContentPreviewViewerDelegate r15) {
        Activity r132 = AndroidUtilities.findActivity(r13.getContext());
        if (r132 != null) goto L5;
        return;
    L5:
        setParentActivity(r132);
        setDelegate(r15);
        SendMessagesHelper.ImportingSticker r2 = new SendMessagesHelper.ImportingSticker();
        r2.path = r11;
        r2.videoEditedInfo = r12;
        this.selectedEmojis = r14;
        open(null, r2, null, null, null, 3, false, null, new DarkThemeResourceProvider());
        AndroidUtilities.cancelRunOnUIThread(this.showSheetRunnable);
        AndroidUtilities.runOnUIThread(this.showSheetRunnable, 16);
    }

    public boolean showMenuFor(View r14) {
        if ((r14 instanceof StickerEmojiCell) == false) goto L21;
        Activity r0 = AndroidUtilities.findActivity(r14.getContext());
        if (r0 != null) goto L7;
        return true;
    L7:
        setParentActivity(r0);
        StickerEmojiCell r142 = (StickerEmojiCell) r14;
        View r02 = this.currentPreviewCell;
        if ((r02 instanceof StickerEmojiCell) == false) goto L11;
        ((StickerEmojiCell) r02).setScaled(false);
    L16:
        this.currentPreviewCell = r142;
        TLRPC.Document r4 = r142.getSticker();
        SendMessagesHelper.ImportingSticker r5 = r142.getStickerPath();
        String r6 = null;
        String r03 = MessageObject.findAnimatedEmojiEmoticon(r142.getSticker(), null, Integer.valueOf(this.currentAccount));
        ContentPreviewViewerDelegate r3 = this.delegate;
        if (r3 == null) goto L19;
        r6 = r3.getQuery(false);
    L19:
        open(r4, r5, r03, r6, null, 0, r142.isRecent(), r142.getParentObject(), this.resourcesProvider);
        AndroidUtilities.cancelRunOnUIThread(this.showSheetRunnable);
        AndroidUtilities.runOnUIThread(this.showSheetRunnable, 16);
        r142.setScaled(true);
        return true;
    L11:
        if ((r02 instanceof StickerCell) == false) goto L14;
        ((StickerCell) r02).setScaled(false);
        goto L16
    L14:
        if ((r02 instanceof ContextLinkCell) == false) goto L16;
        ((ContextLinkCell) r02).setScaled(false);
        goto L16
    L21:
        return false;
    }
}
