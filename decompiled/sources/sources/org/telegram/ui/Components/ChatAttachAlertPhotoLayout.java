package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.system.SystemUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.camera.CameraController;
import org.telegram.messenger.camera.CameraSessionWrapper;
import org.telegram.messenger.camera.CameraView;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.PhotoAttachCameraCell;
import org.telegram.ui.Cells.PhotoAttachPermissionCell;
import org.telegram.ui.Cells.PhotoAttachPhotoCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AvatarConstructorFragment;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.MessagePreviewView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerViewItemRangeSelector;
import org.telegram.ui.Components.ShutterButton;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.capture.IBlur3Hash;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.AlbumButton;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public class ChatAttachAlertPhotoLayout extends ChatAttachAlert.AttachAlertLayout implements NotificationCenter.NotificationCenterDelegate {
    private static ArrayList<Object> cameraPhotos;
    public static int lastImageId;
    private static boolean mediaFromExternalCamera;
    public static HashMap<Object, Object> selectedPhotos;
    public static ArrayList<Object> selectedPhotosOrder;
    private PhotoAttachAdapter adapter;
    float additionCloseCameraY;
    private Runnable afterCameraInitRunnable;
    private int alertOnlyOnce;
    private int[] animateCameraValues;
    float animationClipBottom;
    float animationClipLeft;
    float animationClipRight;
    float animationClipTop;
    private boolean cameraAnimationInProgress;
    private PhotoAttachAdapter cameraAttachAdapter;
    boolean cameraExpanded;
    private AnimatorSet cameraInitAnimation;
    private float cameraOpenProgress;
    public boolean cameraOpened;
    private FrameLayout cameraPanel;
    private LinearLayoutManager cameraPhotoLayoutManager;
    private RecyclerListView cameraPhotoRecyclerView;
    private boolean cameraPhotoRecyclerViewIgnoreLayout;
    protected CameraViewInternal cameraView;
    private final CameraViewItemDecoration cameraViewItemDecoration;
    private float[] cameraViewLocation;
    private float cameraViewOffsetBottomY;
    private float cameraViewOffsetX;
    private float cameraViewOffsetY;
    private float cameraZoom;
    private boolean canSaveCameraPreview;
    private boolean cancelTakingPhotos;
    public MessagePreviewView.ToggleButton captionItem;
    private boolean checkCameraWhenShown;
    private ActionBarMenuSubItem compressItem;
    private TextView counterTextView;
    public int currentItemTop;
    private float currentPanTranslationY;
    private int currentSelectedCount;
    boolean deviceHasGoodCamera;
    private boolean documentsEnabled;
    private boolean dragging;
    public TextView dropDown;
    private ArrayList<MediaController.AlbumEntry> dropDownAlbums;
    private ActionBarMenuItem dropDownContainer;
    private Drawable dropDownDrawable;
    private boolean flashAnimationInProgress;
    private ImageView[] flashModeButton;
    boolean forceDarkTheme;
    private MediaController.AlbumEntry galleryAlbumEntry;
    private int gridExtraSpace;
    public RecyclerListView gridView;
    private ViewPropertyAnimator headerAnimator;
    private Rect hitRect;
    private boolean ignoreLayout;
    private boolean includeVideosInGallery;
    private DecelerateInterpolator interpolator;
    private Boolean isCameraFrontfaceBeforeEnteringEditMode;
    private boolean isHidden;
    private RecyclerViewItemRangeSelector itemRangeSelector;
    private int itemSize;
    private int itemsPerRow;
    private int lastItemSize;
    private int lastNotifyWidth;
    private float lastY;
    private GridLayoutManager layoutManager;
    public int listAdditionalH;
    private boolean loading;
    private boolean maybeStartDraging;
    private boolean mediaEnabled;
    private final boolean needCamera;
    private boolean noCameraPermissions;
    private boolean noGalleryPermissions;
    private AnimationNotificationsLocker notificationsLocker;
    private boolean photoEnabled;
    public PhotoViewer.PhotoViewerProvider photoViewerProvider;
    private float pinchStartDistance;
    private boolean pressed;
    protected ActionBarMenuSubItem previewItem;
    private EmptyTextProgressView progressView;
    private ActionBarMenuSubItem qualityItem;
    private TextView recordTime;
    private boolean requestingPermissions;
    private MediaController.AlbumEntry selectedAlbumEntry;
    private boolean showAvatarConstructor;
    private ShutterButton shutterButton;
    private ActionBarMenuSubItem spoilerItem;
    private ActionBarMenuSubItem starsItem;
    private ImageView switchCameraButton;
    private boolean takingPhoto;
    private TextView tooltipTextView;
    private boolean videoEnabled;
    private Runnable videoRecordRunnable;
    private int videoRecordTime;
    private int[] viewPosition;
    private AnimatorSet zoomControlAnimation;
    private Runnable zoomControlHideRunnable;
    private ZoomControlView zoomControlView;
    private boolean zoomWas;
    private boolean zooming;

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends BasePhotoProvider {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        /* renamed from: $r8$lambda$IegxmkTPAJk45V3qQFv8ZAnm-Gw, reason: not valid java name */
        public static /* synthetic */ void m9766$r8$lambda$IegxmkTPAJk45V3qQFv8ZAnmGw(AnonymousClass1 r0, boolean r1, int r2, boolean r3, Long r4) {
            r0.lambda$sendButtonPressed$1(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$y5RdRl6FssTZIzqrzzjS2b0Mavo(AnonymousClass1 r0) {
            r0.lambda$onClose$0();
        }

        public AnonymousClass1(ChatAttachAlertPhotoLayout r2) {
            this.this$0 = r2;
            super(r2, null);
        }

        private /* synthetic */ void lambda$onClose$0() {
            ChatAttachAlertPhotoLayout.m9760$$Nest$msetCurrentSpoilerVisible(this.this$0, -1, true);
        }

        private /* synthetic */ void lambda$sendButtonPressed$1(boolean r15, int r16, boolean r17, Long r18) {
            ChatAttachAlert r0 = this.this$0.parentAlert;
            if (r0 == null) goto L5;
            r0.setButtonPressed(true);
        L5:
            ChatAttachAlert r14 = this.this$0.parentAlert;
            r14.delegate.didPressedButton(7, true, r15, r16, 0, 0, r14.isCaptionAbove(), r17, r18.longValue());
            ChatAttachAlertPhotoLayout.selectedPhotos.clear();
            ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().clear();
            ChatAttachAlertPhotoLayout.selectedPhotosOrder.clear();
            ChatAttachAlertPhotoLayout.selectedPhotos.clear();
            if (PhotoViewer.getInstance() == null) goto L9;
            PhotoViewer.getInstance().closePhoto(PhotoViewer.getInstance().closePhotoAfterSelectWithAnimation, false);
            PhotoViewer.getInstance().doneButtonPressed = true;
            return;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean allowCaption() {
            ChatAttachAlert r1 = this.this$0.parentAlert;
            if (r1.isPhotoPicker == false) goto L5;
            return false;
        L5:
            if (r1.isPollAttach == true) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean canMoveCaptionAbove() {
            ChatAttachAlert r0 = this.this$0.parentAlert;
            if (r0 != null) goto L5;
            return false;
        L5:
            if ((r0.baseFragment instanceof ChatActivity) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean cancelButtonPressed() {
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public long getDialogId() {
            BaseFragment r0 = this.this$0.parentAlert.baseFragment;
            if ((r0 instanceof ChatActivity) == false) goto L7;
            return ((ChatActivity) r0).getDialogId();
        L7:
            return super.getDialogId();
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r1, TLRPC.FileLocation r2, int r3, boolean r4, boolean r5) {
            if (r5 == false) goto L8;
            Utilities.Callback0Return<PhotoViewer.PlaceProviderObject> r12 = this.this$0.parentAlert.avatarWithBulletin;
            if (r12 == null) goto L8;
            PhotoViewer.PlaceProviderObject r13 = r12.run();
            if (r13 == null) goto L8;
            return r13;
        L8:
            PhotoAttachPhotoCell r14 = ChatAttachAlertPhotoLayout.m9750$$Nest$mgetCellForIndex(this.this$0, r3);
            if (r14 == null) goto L15;
            int[] r22 = new int[2];
            r14.getImageView().getLocationInWindow(r22);
            if (Build.VERSION.SDK_INT >= 26) goto L13;
            r22[0] = r22[0] - this.this$0.parentAlert.getLeftInset();
        L13:
            PhotoViewer.PlaceProviderObject r32 = new PhotoViewer.PlaceProviderObject();
            r32.viewX = r22[0];
            r32.viewY = r22[1];
            r32.parentView = this.this$0.gridView;
            ImageReceiver r23 = r14.getImageView().getImageReceiver();
            r32.imageReceiver = r23;
            r32.thumb = r23.getBitmapSafe();
            r32.scale = r14.getScale();
            r32.clipBottomAddition = (int) this.this$0.parentAlert.getClipLayoutBottom();
            r14.showCheck(false);
            return r32;
        L15:
            return null;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public ImageReceiver.BitmapHolder getThumbForPhoto(MessageObject r1, TLRPC.FileLocation r2, int r3) {
            PhotoAttachPhotoCell r0 = ChatAttachAlertPhotoLayout.m9750$$Nest$mgetCellForIndex(this.this$0, r3);
            if (r0 != null) goto L5;
            return null;
        L5:
            return r0.getImageView().getImageReceiver().getBitmapSafe();
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean isCaptionAbove() {
            ChatAttachAlert r0 = this.this$0.parentAlert;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.captionAbove == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean isEditingMessage() {
            ChatAttachAlert r0 = this.this$0.parentAlert;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.editingMessageObject == null) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean isEditingMessageResend() {
            ChatAttachAlert r0 = this.this$0.parentAlert;
            if (r0 == null) goto L10;
            MessageObject r02 = r0.editingMessageObject;
            if (r02 != null) goto L7;
            return false;
        L7:
            if (r02.needResendWhenEdit() == false) goto L13;
            return true;
        L13:
            return false;
        L10:
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void moveCaptionAbove(boolean r3) {
            ChatAttachAlert r0 = this.this$0.parentAlert;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.captionAbove == r3) goto L10;
            r0.setCaptionAbove(r3);
            this.this$0.captionItem.setState(!r2.parentAlert.captionAbove, true);
            return;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void onApplyCaption(CharSequence r9) {
            if (ChatAttachAlertPhotoLayout.selectedPhotos.size() > 0) goto L5;
            return;
        L5:
            if (ChatAttachAlertPhotoLayout.selectedPhotosOrder.size() <= 0) goto L24;
            Object r92 = ChatAttachAlertPhotoLayout.selectedPhotos.get(ChatAttachAlertPhotoLayout.selectedPhotosOrder.get(0));
            if ((r92 instanceof MediaController.PhotoEntry) == false) goto L9;
            MediaController.PhotoEntry r0 = (MediaController.PhotoEntry) r92;
            CharSequence r1 = r0.caption;
            ArrayList<TLRPC.MessageEntity> r02 = r0.entities;
        L11:
            if ((r92 instanceof MediaController.SearchImage) == false) goto L13;
            MediaController.SearchImage r93 = (MediaController.SearchImage) r92;
            r1 = r93.caption;
            r02 = r93.entities;
        L13:
            ArrayList<TLRPC.MessageEntity> r3 = r02;
            if (r1 == null) goto L21;
            if (r3 == null) goto L21;
            if ((r1 instanceof Spannable) == true) goto L19;
            CharSequence r2 = new SpannableStringBuilder(r1);
        L20:
            MessageObject.addEntitiesToText(r2, r3, false, false, false, false);
            r1 = r2;
            goto L21
        L19:
            r2 = r1;
        L21:
            this.this$0.parentAlert.getCommentView().setText(AnimatedEmojiSpan.cloneSpans(r1, 3));
            return;
        L9:
            r1 = null;
            r02 = null;
            goto L11
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void onClose() {
            ChatAttachAlertPhotoLayout.m9759$$Nest$mresumeCameraPreview(this.this$0);
            AndroidUtilities.runOnUIThread(new ChatAttachAlertPhotoLayout$1$$ExternalSyntheticLambda1(this), 150);
            this.this$0.onSelectedItemsCountChanged(getSelectedCount());
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void onEditModeChanged(boolean r1) {
            ChatAttachAlertPhotoLayout.m9754$$Nest$monPhotoEditModeChanged(this.this$0, r1);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void onOpen() {
            ChatAttachAlertPhotoLayout.m9756$$Nest$mpauseCameraPreview(this.this$0);
            ChatAttachAlertPhotoLayout.m9760$$Nest$msetCurrentSpoilerVisible(this.this$0, -1, true);
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void onPreClose() {
            ChatAttachAlertPhotoLayout.m9760$$Nest$msetCurrentSpoilerVisible(this.this$0, -1, false);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void sendButtonPressed(int r6, VideoEditedInfo r7, boolean r8, int r9, int r10, boolean r11) {
            ChatAttachAlertPhotoLayout r102 = this.this$0;
            r102.parentAlert.sent = true;
            MediaController.PhotoEntry r62 = ChatAttachAlertPhotoLayout.m9751$$Nest$mgetPhotoEntryAtPosition(r102, r6);
            if (r62 == null) goto L6;
            r62.editedInfo = r7;
        L6:
            if (ChatAttachAlertPhotoLayout.selectedPhotos.isEmpty() == false) goto L9;
            if (r62 == null) goto L9;
            ChatAttachAlertPhotoLayout.m9748$$Nest$maddToSelectedPhotos(this.this$0, r62, -1);
        L9:
            ChatAttachAlert r63 = this.this$0.parentAlert;
            if (r63.checkCaption(r63.getCommentView().getText()) == true) goto L25;
            this.this$0.parentAlert.applyCaption();
            if (PhotoViewer.getInstance().hasCaptionForAllMedia == false) goto L28;
            HashMap<Object, Object> r64 = getSelectedPhotos();
            ArrayList<Object> r103 = getSelectedPhotosOrder();
            if (r64.isEmpty() == true) goto L28;
            int r0 = 0;
        L18:
            if (r0 >= r103.size()) goto L28;
            Object r2 = r64.get(r103.get(r0));
            if ((r2 instanceof MediaController.PhotoEntry) == false) goto L27;
            MediaController.PhotoEntry r22 = (MediaController.PhotoEntry) r2;
            if (r0 != 0) goto L26;
            CharSequence[] r4 = {PhotoViewer.getInstance().captionForAllMedia};
            r22.entities = MediaDataController.getInstance(UserConfig.selectedAccount).getEntities(r4, false);
            CharSequence r3 = r4[0];
            r22.caption = r3;
            if (this.this$0.parentAlert.checkCaption(r3) == false) goto L27;
            return;
        L26:
            r22.caption = null;
        L27:
            r0 = r0 + 1;
        L28:
            ChatAttachAlert r65 = this.this$0.parentAlert;
            if (r65 == null) goto L32;
            r65.setButtonPressed(false);
        L32:
            if (PhotoViewer.getInstance() == null) goto L34;
            PhotoViewer.getInstance().closePhotoAfterSelect = false;
            PhotoViewer.getInstance().doneButtonPressed = false;
        L34:
            ChatAttachAlert r66 = this.this$0.parentAlert;
            AlertsCreator.ensurePaidMessageConfirmation(r66.currentAccount, r66.getDialogId(), getSelectedPhotos().size() + this.this$0.parentAlert.getAdditionalMessagesCount(), new ChatAttachAlertPhotoLayout$1$$ExternalSyntheticLambda0(this, r8, r9, r11));
            return;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void updatePhotoAtIndex(int r6) {
            PhotoAttachPhotoCell r0 = ChatAttachAlertPhotoLayout.m9750$$Nest$mgetCellForIndex(this.this$0, r6);
            if (r0 == null) goto L25;
            r0.getImageView().setOrientation(0, true);
            MediaController.PhotoEntry r5 = ChatAttachAlertPhotoLayout.m9751$$Nest$mgetPhotoEntryAtPosition(this.this$0, r6);
            if (r5 != null) goto L8;
            return;
        L8:
            if (r5.coverPath == null) goto L12;
            r0.getImageView().setImage(r5.coverPath, null, Theme.chat_attachEmptyDrawable);
            return;
        L12:
            if (r5.thumbPath == null) goto L16;
            r0.getImageView().setImage(r5.thumbPath, null, Theme.chat_attachEmptyDrawable);
            return;
        L16:
            if (r5.path == null) goto L23;
            r0.getImageView().setOrientation(r5.orientation, r5.invert, true);
            if (r5.isVideo == false) goto L21;
            r0.getImageView().setImage("vthumb://" + r5.imageId + ":" + r5.path, null, Theme.chat_attachEmptyDrawable);
            return;
        L21:
            r0.getImageView().setImage("thumb://" + r5.imageId + ":" + r5.path, null, Theme.chat_attachEmptyDrawable);
            return;
        L23:
            r0.getImageView().setImageDrawable(Theme.chat_attachEmptyDrawable);
            return;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void willHidePhotoViewer() {
            int r0 = this.this$0.gridView.getChildCount();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            View r2 = this.this$0.gridView.getChildAt(r1);
            if ((r2 instanceof PhotoAttachPhotoCell) == false) goto L7;
            ((PhotoAttachPhotoCell) r2).showCheck(true);
        L7:
            r1 = r1 + 1;
            goto L3
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void willSwitchFromPhoto(MessageObject r1, TLRPC.FileLocation r2, int r3) {
            PhotoAttachPhotoCell r0 = ChatAttachAlertPhotoLayout.m9750$$Nest$mgetCellForIndex(this.this$0, r3);
            if (r0 == null) goto L6;
            r0.showCheck(true);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$10, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass10 implements ShutterButton.ShutterButtonDelegate {
        private File outputFile;
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;
        final /* synthetic */ FrameLayout val$container;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
        private boolean zoomingWas;

        public static /* synthetic */ void $r8$lambda$MLCHdfYeVtijEgUqP9kwLfoBCOY(AnonymousClass10 r0) {
            r0.lambda$shutterLongPressed$2();
        }

        public static /* synthetic */ void $r8$lambda$dQc_CjaDzPhRoR0bbD16lXSe3_k(AnonymousClass10 r0) {
            r0.lambda$shutterLongPressed$0();
        }

        public static /* synthetic */ void $r8$lambda$yIZBWKlwcLEp9knEErpKbEU6dsg(AnonymousClass10 r0, File r1, boolean r2, Integer r3) {
            r0.lambda$shutterReleased$3(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$zHJ2G1OK6Q9aFSG0VnvNHvzaZWQ(AnonymousClass10 r0, String r1, long r2) {
            r0.lambda$shutterLongPressed$1(r1, r2);
        }

        public AnonymousClass10(ChatAttachAlertPhotoLayout r1, Theme.ResourcesProvider r2, FrameLayout r3) {
            this.this$0 = r1;
            this.val$resourcesProvider = r2;
            this.val$container = r3;
        }

        private /* synthetic */ void lambda$shutterLongPressed$0() {
            if (ChatAttachAlertPhotoLayout.m9733$$Nest$fgetvideoRecordRunnable(this.this$0) != null) goto L5;
            return;
        L5:
            ChatAttachAlertPhotoLayout r0 = this.this$0;
            ChatAttachAlertPhotoLayout.m9746$$Nest$fputvideoRecordTime(r0, ChatAttachAlertPhotoLayout.m9734$$Nest$fgetvideoRecordTime(r0) + 1);
            ChatAttachAlertPhotoLayout.m9725$$Nest$fgetrecordTime(this.this$0).setText(AndroidUtilities.formatLongDuration(ChatAttachAlertPhotoLayout.m9734$$Nest$fgetvideoRecordTime(this.this$0)));
            AndroidUtilities.runOnUIThread(ChatAttachAlertPhotoLayout.m9733$$Nest$fgetvideoRecordRunnable(this.this$0), 1000);
        }

        private /* synthetic */ void lambda$shutterLongPressed$1(String r19, long r20) {
            if (this.outputFile == null) goto L28;
            ChatAttachAlertPhotoLayout r2 = this.this$0;
            if (r2.parentAlert.destroyed == false) goto L7;
            return;
        L7:
            if (r2.cameraView == null) goto L30;
            ChatAttachAlertPhotoLayout.m9765$$Nest$sfputmediaFromExternalCamera(false);
            BitmapFactory.Options r4 = new BitmapFactory.Options();     // Catch: Exception -> L14
            r4.inJustDecodeBounds = true;     // Catch: Exception -> L14
            BitmapFactory.decodeFile(new File(r19).getAbsolutePath(), r4);     // Catch: Exception -> L14
            int r5 = r4.outWidth;     // Catch: Exception -> L14
            int r15 = r4.outHeight;     // Catch: Exception -> L23
        L13:
            int r14 = r5;
            int r8 = ChatAttachAlertPhotoLayout.lastImageId;
            ChatAttachAlertPhotoLayout.lastImageId = r8 - 1;
            MediaController.PhotoEntry r6 = new MediaController.PhotoEntry(0, r8, 0, this.outputFile.getAbsolutePath(), 0, true, r14, r15, 0);
            r6.duration = (int) (r20 / 1000.0f);
            r6.thumbPath = r19;
            ChatAttachAlertPhotoLayout r1 = this.this$0;
            if (r1.parentAlert.avatarPicker != 0) goto L19;
        L21:
            this.this$0.openPhotoViewer(r6, false, false);
            return;
        L19:
            if (r1.cameraView.isFrontface() == false) goto L21;
            MediaController.CropState r12 = new MediaController.CropState();
            r6.cropState = r12;
            r12.mirrored = true;
            r12.freeform = false;
            r12.lockedAspectRatio = 1.0f;
        L15:
            r15 = 0;
        L14:
            r5 = 0;
            goto L15
        L30:
            return;
        }

        private /* synthetic */ void lambda$shutterLongPressed$2() {
            AndroidUtilities.runOnUIThread(ChatAttachAlertPhotoLayout.m9733$$Nest$fgetvideoRecordRunnable(this.this$0), 1000);
        }

        private /* synthetic */ void lambda$shutterReleased$3(File r18, boolean r19, Integer r20) {
            ChatAttachAlertPhotoLayout.m9744$$Nest$fputtakingPhoto(this.this$0, false);
            if (r18 != null) goto L5;
            return;
        L5:
            if (this.this$0.parentAlert.destroyed == true) goto L26;
            ChatAttachAlertPhotoLayout.m9765$$Nest$sfputmediaFromExternalCamera(false);
            BitmapFactory.Options r3 = new BitmapFactory.Options();     // Catch: Exception -> L12
            r3.inJustDecodeBounds = true;     // Catch: Exception -> L12
            BitmapFactory.decodeFile(new File(r18.getAbsolutePath()).getAbsolutePath(), r3);     // Catch: Exception -> L12
            int r4 = r3.outWidth;     // Catch: Exception -> L12
            int r14 = r3.outHeight;     // Catch: Exception -> L20
        L11:
            int r13 = r4;
            int r7 = ChatAttachAlertPhotoLayout.lastImageId;
            ChatAttachAlertPhotoLayout.lastImageId = r7 - 1;
            String r10 = r18.getAbsolutePath();
            if (r20.intValue() != (-1)) goto L17;
            int r11 = 0;
        L18:
            MediaController.PhotoEntry r5 = new MediaController.PhotoEntry(0, r7, 0, r10, r11, false, r13, r14, 0);
            r5.canDeleteAfter = true;
            this.this$0.openPhotoViewer(r5, r19, false);
            return;
        L17:
            r11 = r20.intValue();
        L13:
            r14 = 0;
        L12:
            r4 = 0;
            goto L13
        }

        @Override // org.telegram.ui.Components.ShutterButton.ShutterButtonDelegate
        public boolean onTranslationChanged(float r6, float r7) {
            if (this.val$container.getWidth() >= this.val$container.getHeight()) goto L5;
            boolean r0 = true;
        L6:
            if (r0 == false) goto L8;
            float r1 = r6;
        L9:
            if (r0 == false) goto L11;
            float r02 = r7;
        L13:
            if (this.zoomingWas == true) goto L21;
            if (Math.abs(r1) <= Math.abs(r02)) goto L21;
            if (ChatAttachAlertPhotoLayout.m9735$$Nest$fgetzoomControlView(this.this$0).getTag() != null) goto L19;
            return true;
        L19:
            return false;
        L21:
            if (r02 >= 0.0f) goto L25;
            ChatAttachAlertPhotoLayout.m9761$$Nest$mshowZoomControls(this.this$0, true, true);
            ChatAttachAlertPhotoLayout.m9735$$Nest$fgetzoomControlView(this.this$0).setZoom((-r02) / AndroidUtilities.dp(200.0f), true);
            this.zoomingWas = true;
            return false;
        L25:
            if (this.zoomingWas == false) goto L28;
            ChatAttachAlertPhotoLayout.m9735$$Nest$fgetzoomControlView(this.this$0).setZoom(0.0f, true);
        L28:
            if (r6 != 0.0f) goto L33;
            if (r7 != 0.0f) goto L33;
            this.zoomingWas = false;
        L33:
            if (this.zoomingWas == true) goto L38;
            if (r6 == 0.0f) goto L36;
        L37:
            return true;
        L36:
            if (r7 != 0.0f) goto L37;
        L38:
            return false;
        L11:
            r02 = r6;
            goto L13
        L8:
            r1 = r7;
            goto L9
        L5:
            r0 = false;
            goto L6
        }

        @Override // org.telegram.ui.Components.ShutterButton.ShutterButtonDelegate
        public void shutterCancel() {
            File r0 = this.outputFile;
            if (r0 == null) goto L5;
            r0.delete();
            this.outputFile = null;
        L5:
            ChatAttachAlertPhotoLayout.m9758$$Nest$mresetRecordState(this.this$0);
            CameraController.getInstance().stopVideoRecording(this.this$0.cameraView.getCameraSession(), true);
        }

        @Override // org.telegram.ui.Components.ShutterButton.ShutterButtonDelegate
        public boolean shutterLongPressed() {
            ChatAttachAlertPhotoLayout r0 = this.this$0;
            ChatAttachAlert r1 = r0.parentAlert;
            if (r1.avatarPicker == 2) goto L7;
            if ((r1.baseFragment instanceof ChatActivity) == true) goto L7;
        L47:
            return false;
        L7:
            if (ChatAttachAlertPhotoLayout.m9730$$Nest$fgettakingPhoto(r0) == true) goto L47;
            ChatAttachAlertPhotoLayout r02 = this.this$0;
            ChatAttachAlert r12 = r02.parentAlert;
            if (r12.destroyed == true) goto L47;
            if (r02.cameraView == null) goto L47;
            if (r12.isStickerMode == false) goto L16;
            return false;
        L16:
            BaseFragment r03 = r12.baseFragment;
            if (r03 != null) goto L19;
            r03 = LaunchActivity.getLastFragment();
        L19:
            if (r03 == null) goto L47;
            if (r03.getParentActivity() == null) goto L47;
            boolean r13 = ChatAttachAlertPhotoLayout.m9732$$Nest$fgetvideoEnabled(this.this$0);
            ChatAttachAlertPhotoLayout r2 = this.this$0;
            if (r13 == true) goto L28;
            BulletinFactory.of(r2.cameraView, this.val$resourcesProvider).createErrorBulletin(LocaleController.getString(R.string.GlobalAttachVideoRestricted)).show();
            return false;
        L28:
            if (r2.getContext().checkSelfPermission("android.permission.RECORD_AUDIO") == 0) goto L31;
            ChatAttachAlertPhotoLayout.m9743$$Nest$fputrequestingPermissions(this.this$0, true);
            r03.getParentActivity().requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 21);
            return false;
        L31:
            int r14 = 0;
        L32:
            ChatAttachAlertPhotoLayout r22 = this.this$0;
            if (r14 >= 2) goto L35;
            ChatAttachAlertPhotoLayout.m9712$$Nest$fgetflashModeButton(r22)[r14].animate().alpha(0.0f).translationX(AndroidUtilities.dp(30.0f)).setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
            r14 = r14 + 1;
            goto L32
        L35:
            ViewPropertyAnimator r15 = ChatAttachAlertPhotoLayout.m9729$$Nest$fgetswitchCameraButton(r22).animate().alpha(0.0f).translationX(-AndroidUtilities.dp(30.0f)).setDuration(150);
            CubicBezierInterpolator r23 = CubicBezierInterpolator.DEFAULT;
            r15.setInterpolator(r23).start();
            ChatAttachAlertPhotoLayout.m9731$$Nest$fgettooltipTextView(this.this$0).animate().alpha(0.0f).setDuration(150).setInterpolator(r23).start();
            BaseFragment r16 = this.this$0.parentAlert.baseFragment;
            if ((r16 instanceof ChatActivity) == true) goto L38;
        L40:
            boolean r17 = false;
        L41:
            this.outputFile = AndroidUtilities.generateVideoPath(r17);
            AndroidUtilities.updateViewVisibilityAnimated(ChatAttachAlertPhotoLayout.m9725$$Nest$fgetrecordTime(this.this$0), true);
            ChatAttachAlertPhotoLayout.m9725$$Nest$fgetrecordTime(this.this$0).setText(AndroidUtilities.formatLongDuration(0));
            ChatAttachAlertPhotoLayout.m9746$$Nest$fputvideoRecordTime(this.this$0, 0);
            ChatAttachAlertPhotoLayout.m9745$$Nest$fputvideoRecordRunnable(this.this$0, new ChatAttachAlertPhotoLayout$10$$ExternalSyntheticLambda1(this));
            AndroidUtilities.lockOrientation(r03.getParentActivity());
            CameraController r6 = CameraController.getInstance();
            Object r7 = this.this$0.cameraView.getCameraSessionObject();
            File r8 = this.outputFile;
            if (this.this$0.parentAlert.avatarPicker == 0) goto L44;
            boolean r9 = true;
        L45:
            r6.recordVideo(r7, r8, r9, new ChatAttachAlertPhotoLayout$10$$ExternalSyntheticLambda2(this), new ChatAttachAlertPhotoLayout$10$$ExternalSyntheticLambda3(this), this.this$0.cameraView);
            ChatAttachAlertPhotoLayout.m9728$$Nest$fgetshutterButton(this.this$0).setState(ShutterButton.State.RECORDING, true);
            this.this$0.cameraView.runHaptic();
            return true;
        L44:
            r9 = false;
            goto L45
        L38:
            if (((ChatActivity) r16).isSecretChat() == false) goto L40;
            r17 = true;
            goto L41
        }

        @Override // org.telegram.ui.Components.ShutterButton.ShutterButtonDelegate
        public void shutterReleased() {
            if (ChatAttachAlertPhotoLayout.m9730$$Nest$fgettakingPhoto(this.this$0) == true) goto L32;
            CameraViewInternal r0 = this.this$0.cameraView;
            if (r0 != null) goto L7;
            return;
        L7:
            if (r0.getCameraSession() == null) goto L34;
            ShutterButton.State r02 = ChatAttachAlertPhotoLayout.m9728$$Nest$fgetshutterButton(this.this$0).getState();
            ShutterButton.State r1 = ShutterButton.State.RECORDING;
            ChatAttachAlertPhotoLayout r2 = this.this$0;
            if (r02 != r1) goto L13;
            ChatAttachAlertPhotoLayout.m9758$$Nest$mresetRecordState(r2);
            CameraController.getInstance().stopVideoRecording(this.this$0.cameraView.getCameraSession(), false);
            ChatAttachAlertPhotoLayout.m9728$$Nest$fgetshutterButton(this.this$0).setState(ShutterButton.State.DEFAULT, true);
            return;
        L13:
            boolean r03 = ChatAttachAlertPhotoLayout.m9723$$Nest$fgetphotoEnabled(r2);
            ChatAttachAlertPhotoLayout r12 = this.this$0;
            if (r03 == true) goto L17;
            BulletinFactory.of(r12.cameraView, this.val$resourcesProvider).createErrorBulletin(LocaleController.getString(R.string.GlobalAttachPhotoRestricted)).show();
            return;
        L17:
            BaseFragment r04 = r12.parentAlert.baseFragment;
            if ((r04 instanceof ChatActivity) == true) goto L20;
        L22:
            boolean r05 = false;
        L23:
            File r06 = AndroidUtilities.generatePicturePath(r05, null);
            boolean r13 = this.this$0.cameraView.getCameraSession().isSameTakePictureOrientation();
            CameraSessionWrapper r22 = this.this$0.cameraView.getCameraSession();
            ChatAttachAlert r5 = this.this$0.parentAlert;
            if ((r5.baseFragment instanceof ChatActivity) == false) goto L26;
        L29:
            boolean r52 = true;
        L30:
            r22.setFlipFront(r52);
            ChatAttachAlertPhotoLayout.m9744$$Nest$fputtakingPhoto(this.this$0, CameraController.getInstance().takePicture(r06, false, this.this$0.cameraView.getCameraSessionObject(), new ChatAttachAlertPhotoLayout$10$$ExternalSyntheticLambda0(this, r06, r13)));
            this.this$0.cameraView.startTakePictureAnimation(true);
            return;
        L26:
            if (r5.avatarPicker == 2) goto L29;
            r52 = false;
            goto L30
        L20:
            if (((ChatActivity) r04).isSecretChat() == false) goto L22;
            r05 = true;
            goto L23
        L34:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$11, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass11 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public AnonymousClass11(ChatAttachAlertPhotoLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            ImageView r42 = ChatAttachAlertPhotoLayout.m9729$$Nest$fgetswitchCameraButton(this.this$0);
            CameraViewInternal r0 = this.this$0.cameraView;
            if (r0 != null) goto L5;
        L7:
            int r02 = R.drawable.camera_revert2;
        L8:
            r42.setImageResource(r02);
            ObjectAnimator.ofFloat(ChatAttachAlertPhotoLayout.m9729$$Nest$fgetswitchCameraButton(this.this$0), View.SCALE_X, new float[]{1.0f}).setDuration(100).start();
            return;
        L5:
            if (r0.isFrontface() == false) goto L7;
            r02 = R.drawable.camera_revert1;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$12, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass12 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;
        final /* synthetic */ View val$currentImage;
        final /* synthetic */ ImageView val$nextImage;

        public AnonymousClass12(ChatAttachAlertPhotoLayout r1, View r2, ImageView r3) {
            this.this$0 = r1;
            this.val$currentImage = r2;
            this.val$nextImage = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ChatAttachAlertPhotoLayout.m9742$$Nest$fputflashAnimationInProgress(this.this$0, false);
            this.val$currentImage.setVisibility(4);
            this.val$nextImage.sendAccessibilityEvent(8);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$13, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass13 extends RecyclerListView {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public AnonymousClass13(ChatAttachAlertPhotoLayout r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (ChatAttachAlertPhotoLayout.m9705$$Nest$fgetcameraPhotoRecyclerViewIgnoreLayout(this.this$0) == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$14, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass14 extends LinearLayoutManager {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public AnonymousClass14(ChatAttachAlertPhotoLayout r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$15, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass15 extends BasePhotoProvider {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;
        final /* synthetic */ boolean val$sameTakePictureOrientation;

        public static /* synthetic */ void $r8$lambda$FD997arGNNXkpu5XFTsJbEcQ2uA(AnonymousClass15 r0, boolean r1, boolean r2, int r3, Long r4) {
            r0.lambda$sendButtonPressed$1(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$fsUizSclPyQmYihex3D-c4ffnjQ, reason: not valid java name */
        public static /* synthetic */ void m9767$r8$lambda$fsUizSclPyQmYihex3Dc4ffnjQ(AnonymousClass15 r0) {
            r0.lambda$cancelButtonPressed$0();
        }

        public AnonymousClass15(ChatAttachAlertPhotoLayout r1, boolean r2) {
            this.this$0 = r1;
            this.val$sameTakePictureOrientation = r2;
            super(r1, null);
        }

        private /* synthetic */ void lambda$cancelButtonPressed$0() {
            ChatAttachAlertPhotoLayout r0 = this.this$0;
            if (r0.cameraView != null) goto L5;
            return;
        L5:
            if (r0.parentAlert.isDismissed() == true) goto L9;
            this.this$0.cameraView.setSystemUiVisibility(1028);
            return;
        }

        private /* synthetic */ void lambda$sendButtonPressed$1(boolean r17, boolean r18, int r19, Long r20) {
            if (PhotoViewer.getInstance() == null) goto L5;
            PhotoViewer.getInstance().closePhotoAfterSelect = false;
            PhotoViewer.getInstance().doneButtonPressed = false;
        L5:
            ChatAttachAlert r1 = this.this$0.parentAlert;
            r1.sent = true;
            if (r1 == null) goto L8;
            r1.setButtonPressed(true);
        L8:
            this.this$0.closeCamera(false);
            ChatAttachAlert r12 = this.this$0.parentAlert;
            ChatAttachAlert.ChatAttachViewDelegate r4 = r12.delegate;
            if (r17 == false) goto L11;
            int r5 = 4;
        L12:
            r4.didPressedButton(r5, true, r18, r19, 0, 0, r12.isCaptionAbove(), r17, r20.longValue());
            ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().clear();
            ChatAttachAlertPhotoLayout.selectedPhotosOrder.clear();
            ChatAttachAlertPhotoLayout.selectedPhotos.clear();
            ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0).notifyDataSetChanged();
            ChatAttachAlertPhotoLayout.m9699$$Nest$fgetcameraAttachAdapter(this.this$0).notifyDataSetChanged();
            this.this$0.parentAlert.dismiss(true);
            if (PhotoViewer.getInstance() == null) goto L16;
            PhotoViewer.getInstance().closePhoto(PhotoViewer.getInstance().closePhotoAfterSelectWithAnimation, false);
            PhotoViewer.getInstance().doneButtonPressed = true;
            return;
        L16:
            return;
        L11:
            r5 = 8;
            goto L12
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean allowCaption() {
            ChatAttachAlert r1 = this.this$0.parentAlert;
            if (r1.isPhotoPicker == false) goto L5;
            return false;
        L5:
            if (r1.isPollAttach == true) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean canCaptureMorePhotos() {
            if (this.this$0.parentAlert.maxSelectedPhotos == 1) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean canScrollAway() {
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean cancelButtonPressed() {
            ChatAttachAlertPhotoLayout r0 = this.this$0;
            if (r0.cameraOpened == false) goto L8;
            if (r0.cameraView == null) goto L8;
            AndroidUtilities.runOnUIThread(new ChatAttachAlertPhotoLayout$15$$ExternalSyntheticLambda0(this), 1000);
            ChatAttachAlertPhotoLayout.m9735$$Nest$fgetzoomControlView(this.this$0).setZoom(0.0f, false);
            ChatAttachAlertPhotoLayout.m9739$$Nest$fputcameraZoom(this.this$0, 0.0f);
            this.this$0.cameraView.setZoom(0.0f);
            CameraController.getInstance().startPreview(this.this$0.cameraView.getCameraSession());
        L8:
            if (ChatAttachAlertPhotoLayout.m9709$$Nest$fgetcancelTakingPhotos(this.this$0) == true) goto L10;
        L21:
            return true;
        L10:
            if (ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().size() != 1) goto L21;
            int r02 = ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().size();
            int r3 = 0;
        L12:
            if (r3 >= r02) goto L20;
            MediaController.PhotoEntry r4 = (MediaController.PhotoEntry) ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().get(r3);
            new File(r4.path).delete();
            if (r4.imagePath == null) goto L17;
            new File(r4.imagePath).delete();
        L17:
            if (r4.thumbPath == null) goto L19;
            new File(r4.thumbPath).delete();
        L19:
            r3 = r3 + 1;
            goto L12
        L20:
            ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().clear();
            ChatAttachAlertPhotoLayout.selectedPhotosOrder.clear();
            ChatAttachAlertPhotoLayout.selectedPhotos.clear();
            ChatAttachAlertPhotoLayout.m9710$$Nest$fgetcounterTextView(this.this$0).setVisibility(4);
            ChatAttachAlertPhotoLayout.m9704$$Nest$fgetcameraPhotoRecyclerView(this.this$0).setVisibility(8);
            ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0).notifyDataSetChanged();
            ChatAttachAlertPhotoLayout.m9699$$Nest$fgetcameraAttachAdapter(this.this$0).notifyDataSetChanged();
            this.this$0.parentAlert.updateCountButton(0);
            goto L21
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public ImageReceiver.BitmapHolder getThumbForPhoto(MessageObject r1, TLRPC.FileLocation r2, int r3) {
            return null;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void needAddMorePhotos() {
            ChatAttachAlertPhotoLayout.m9741$$Nest$fputcancelTakingPhotos(this.this$0, false);
            boolean r0 = ChatAttachAlertPhotoLayout.m9764$$Nest$sfgetmediaFromExternalCamera();
            ChatAttachAlertPhotoLayout r2 = this.this$0;
            if (r0 == false) goto L7;
            ChatAttachAlert r12 = r2.parentAlert;
            r12.delegate.didPressedButton(0, true, true, 0, 0, 0, r12.isCaptionAbove(), false, 0);
            return;
        L7:
            if (r2.cameraOpened == true) goto L9;
            r2.openCamera(false);
        L9:
            ChatAttachAlertPhotoLayout.m9710$$Nest$fgetcounterTextView(this.this$0).setVisibility(0);
            ChatAttachAlertPhotoLayout.m9704$$Nest$fgetcameraPhotoRecyclerView(this.this$0).setVisibility(0);
            ChatAttachAlertPhotoLayout.m9710$$Nest$fgetcounterTextView(this.this$0).setAlpha(1.0f);
            this.this$0.updatePhotosCounter(false);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void onClose() {
            ChatAttachAlertPhotoLayout.m9759$$Nest$mresumeCameraPreview(this.this$0);
            this.this$0.onSelectedItemsCountChanged(getSelectedCount());
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void onEditModeChanged(boolean r1) {
            ChatAttachAlertPhotoLayout.m9754$$Nest$monPhotoEditModeChanged(this.this$0, r1);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void onOpen() {
            ChatAttachAlertPhotoLayout.m9756$$Nest$mpauseCameraPreview(this.this$0);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean scaleToFill() {
            ChatAttachAlertPhotoLayout r0 = this.this$0;
            if (r0.parentAlert.destroyed == false) goto L5;
            return false;
        L5:
            int r02 = Settings.System.getInt(r0.getContext().getContentResolver(), "accelerometer_rotation", 0);
            if (this.val$sameTakePictureOrientation == true) goto L10;
            if (r02 == 1) goto L10;
            return false;
        L10:
            return true;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void sendButtonPressed(int r3, VideoEditedInfo r4, boolean r5, int r6, int r7, boolean r8) {
            if (ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().isEmpty() == false) goto L5;
            return;
        L5:
            if (this.this$0.parentAlert.destroyed == true) goto L32;
            if (r4 == null) goto L12;
            if (r3 < 0) goto L12;
            if (r3 >= ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().size()) goto L12;
            ((MediaController.PhotoEntry) ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().get(r3)).editedInfo = r4;
        L12:
            BaseFragment r32 = this.this$0.parentAlert.baseFragment;
            if ((r32 instanceof ChatActivity) == true) goto L15;
        L16:
            int r33 = ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().size();
            int r42 = 0;
        L17:
            if (r42 >= r33) goto L23;
            MediaController.PhotoEntry r0 = (MediaController.PhotoEntry) ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().get(r42);
            if (r0.ttl > 0) goto L22;
            AndroidUtilities.addMediaToGallery(r0.path);
        L22:
            r42 = r42 + 1;
        L23:
            this.this$0.parentAlert.applyCaption();
            if (PhotoViewer.getInstance() == null) goto L26;
            PhotoViewer.getInstance().closePhotoAfterSelect = false;
            PhotoViewer.getInstance().doneButtonPressed = false;
        L26:
            ChatAttachAlert r34 = this.this$0.parentAlert;
            AlertsCreator.ensurePaidMessageConfirmation(r34.currentAccount, r34.getDialogId(), getSelectedCount() + this.this$0.parentAlert.getAdditionalMessagesCount(), new ChatAttachAlertPhotoLayout$15$$ExternalSyntheticLambda1(this, r8, r5, r6));
            return;
        L15:
            if (((ChatActivity) r32).isSecretChat() == true) goto L23;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void willHidePhotoViewer() {
            int r0 = this.this$0.gridView.getChildCount();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            View r2 = this.this$0.gridView.getChildAt(r1);
            if ((r2 instanceof PhotoAttachPhotoCell) == false) goto L7;
            PhotoAttachPhotoCell r22 = (PhotoAttachPhotoCell) r2;
            r22.showImage();
            r22.showCheck(true);
        L7:
            r1 = r1 + 1;
            goto L3
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$16, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass16 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public AnonymousClass16(ChatAttachAlertPhotoLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ChatAttachAlertPhotoLayout.m9747$$Nest$fputzoomControlAnimation(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$17, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass17 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public AnonymousClass17(ChatAttachAlertPhotoLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ChatAttachAlertPhotoLayout.m9722$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            ChatAttachAlertPhotoLayout.m9737$$Nest$fputcameraAnimationInProgress(this.this$0, false);
            CameraViewInternal r22 = this.this$0.cameraView;
            if (r22 == null) goto L5;
            r22.invalidateOutline();
            this.this$0.cameraView.invalidate();
        L5:
            ChatAttachAlertPhotoLayout r23 = this.this$0;
            if (r23.cameraOpened == false) goto L8;
            r23.parentAlert.delegate.onCameraOpened();
        L8:
            CameraViewInternal r24 = this.this$0.cameraView;
            if (r24 == null) goto L11;
            r24.setSystemUiVisibility(1028);
        L11:
            RecyclerListView r1 = this.this$0.gridView;
            if (r1 == null) goto L15;
            r1.invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$18, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass18 extends ViewOutlineProvider {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public AnonymousClass18(ChatAttachAlertPhotoLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r10, Outline r11) {
            float r0 = this.this$0.parentAlert.getCommentTextViewTop();
            MentionsContainerView r1 = this.this$0.parentAlert.mentionContainer;
            if (r1 == null) goto L5;
            float r12 = r1.clipBottom() + AndroidUtilities.dp(16.0f);
        L6:
            int r02 = (int) Math.min((((r0 - r12) + ChatAttachAlertPhotoLayout.m9711$$Nest$fgetcurrentPanTranslationY(this.this$0)) + this.this$0.parentAlert.getContainerView().getTranslationY()) - this.this$0.cameraView.getTranslationY(), r10.getMeasuredHeight());
            ChatAttachAlertPhotoLayout r13 = this.this$0;
            if (r13.cameraOpened == false) goto L10;
            r02 = r10.getMeasuredHeight();
        L13:
            if (ChatAttachAlertPhotoLayout.m9698$$Nest$fgetcameraAnimationInProgress(this.this$0) == false) goto L17;
            RectF r102 = AndroidUtilities.rectTmp;
            ChatAttachAlertPhotoLayout r14 = this.this$0;
            float r2 = r14.animationClipLeft + (ChatAttachAlertPhotoLayout.m9707$$Nest$fgetcameraViewOffsetX(r14) * (1.0f - ChatAttachAlertPhotoLayout.m9701$$Nest$fgetcameraOpenProgress(this.this$0)));
            ChatAttachAlertPhotoLayout r15 = this.this$0;
            float r3 = r15.animationClipTop + (ChatAttachAlertPhotoLayout.m9708$$Nest$fgetcameraViewOffsetY(r15) * (1.0f - ChatAttachAlertPhotoLayout.m9701$$Nest$fgetcameraOpenProgress(this.this$0)));
            ChatAttachAlertPhotoLayout r9 = this.this$0;
            r102.set(r2, r3, r9.animationClipRight, r9.animationClipBottom);
            r11.setRect((int) r102.left, (int) r102.top, (int) r102.right, Math.min(r02, (int) r102.bottom));
            return;
        L17:
            if (ChatAttachAlertPhotoLayout.m9698$$Nest$fgetcameraAnimationInProgress(this.this$0) == false) goto L19;
        L22:
            r11.setRect(0, 0, r10.getMeasuredWidth(), Math.min(r02, r10.getMeasuredHeight()));
            return;
        L19:
            if (this.this$0.cameraOpened == true) goto L22;
            int r16 = AndroidUtilities.dp(16.0f);
            r11.setRoundRect((int) ChatAttachAlertPhotoLayout.m9707$$Nest$fgetcameraViewOffsetX(this.this$0), (int) ChatAttachAlertPhotoLayout.m9708$$Nest$fgetcameraViewOffsetY(this.this$0), r10.getMeasuredWidth() + r16, Math.min(r02, r10.getMeasuredHeight()) + r16, r16);
            return;
        L10:
            if (ChatAttachAlertPhotoLayout.m9698$$Nest$fgetcameraAnimationInProgress(r13) == false) goto L13;
            r02 = AndroidUtilities.lerp(r02, r10.getMeasuredHeight(), ChatAttachAlertPhotoLayout.m9701$$Nest$fgetcameraOpenProgress(this.this$0));
            goto L13
        L5:
            r12 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$19, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass19 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public AnonymousClass19(ChatAttachAlertPhotoLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            ChatAttachAlertPhotoLayout.m9738$$Nest$fputcameraInitAnimation(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2.equals(ChatAttachAlertPhotoLayout.m9700$$Nest$fgetcameraInitAnimation(this.this$0)) == false) goto L6;
            ChatAttachAlertPhotoLayout.m9740$$Nest$fputcanSaveCameraPreview(this.this$0, true);
            ChatAttachAlertPhotoLayout.m9738$$Nest$fputcameraInitAnimation(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends ActionBarMenuItem {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public AnonymousClass2(ChatAttachAlertPhotoLayout r1, Context r2, ActionBarMenu r3, int r4, int r5, Theme.ResourcesProvider r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem, android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            r1.setText(this.this$0.dropDown.getText());
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$20, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass20 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public AnonymousClass20(ChatAttachAlertPhotoLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            ChatAttachAlertPhotoLayout.m9722$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            ChatAttachAlertPhotoLayout r32 = this.this$0;
            r32.cameraExpanded = false;
            r32.parentAlert.getWindow().clearFlags(128);
            this.this$0.setCameraOpenProgress(0.0f);
            ChatAttachAlertPhotoLayout.m9737$$Nest$fputcameraAnimationInProgress(this.this$0, false);
            RecyclerListView r33 = this.this$0.gridView;
            if (r33 == null) goto L5;
            r33.invalidate();
        L5:
            CameraViewInternal r34 = this.this$0.cameraView;
            if (r34 == null) goto L8;
            r34.invalidateOutline();
            this.this$0.cameraView.invalidate();
        L8:
            ChatAttachAlertPhotoLayout r35 = this.this$0;
            r35.cameraOpened = false;
            if (ChatAttachAlertPhotoLayout.m9702$$Nest$fgetcameraPanel(r35) == null) goto L12;
            ChatAttachAlertPhotoLayout.m9702$$Nest$fgetcameraPanel(this.this$0).setVisibility(8);
        L12:
            if (ChatAttachAlertPhotoLayout.m9735$$Nest$fgetzoomControlView(this.this$0) == null) goto L15;
            ChatAttachAlertPhotoLayout.m9735$$Nest$fgetzoomControlView(this.this$0).setVisibility(8);
            ChatAttachAlertPhotoLayout.m9735$$Nest$fgetzoomControlView(this.this$0).setTag(null);
        L15:
            if (ChatAttachAlertPhotoLayout.m9704$$Nest$fgetcameraPhotoRecyclerView(this.this$0) == null) goto L17;
            ChatAttachAlertPhotoLayout.m9704$$Nest$fgetcameraPhotoRecyclerView(this.this$0).setVisibility(8);
        L17:
            this.this$0.parentAlert.onCameraStateChanged();
            CameraViewInternal r36 = this.this$0.cameraView;
            if (r36 == null) goto L21;
            r36.setFpsLimit(30);
            this.this$0.cameraView.setSystemUiVisibility(1024);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends RecyclerListView {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public AnonymousClass3(ChatAttachAlertPhotoLayout r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r5) {
            if (r5.getAction() != 0) goto L8;
            if (r5.getY() >= (this.this$0.parentAlert.scrollOffsetY[0] - AndroidUtilities.dp(80.0f))) goto L8;
            return false;
        L8:
            return super.onInterceptTouchEvent(r5);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            PhotoViewer.getInstance().checkCurrentImageVisibility();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public boolean onTouchEvent(MotionEvent r5) {
            if (r5.getAction() != 0) goto L8;
            if (r5.getY() >= (this.this$0.parentAlert.scrollOffsetY[0] - AndroidUtilities.dp(80.0f))) goto L8;
            return false;
        L8:
            return super.onTouchEvent(r5);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        boolean parentPinnedToTop;
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public AnonymousClass4(ChatAttachAlertPhotoLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r4, int r5) {
            if (r5 != 0) goto L15;
            int r42 = AndroidUtilities.dp(13.0f);
            ActionBarMenuItem r52 = this.this$0.parentAlert.selectedMenuItem;
            if (r52 == null) goto L6;
            int r53 = AndroidUtilities.dp(r52.getAlpha() * 26.0f);
        L7:
            int r43 = r42 + r53;
            int r54 = this.this$0.parentAlert.getBackgroundPaddingTop();
            if ((((this.this$0.parentAlert.scrollOffsetY[0] - r54) - r43) + r54) >= (ActionBar.getCurrentActionBarHeight() + (this.this$0.parentAlert.topCommentContainer.getMeasuredHeight() * this.this$0.parentAlert.topCommentContainer.getAlpha()))) goto L16;
            RecyclerListView.Holder r44 = (RecyclerListView.Holder) this.this$0.gridView.findViewHolderForAdapterPosition(0);
            if (r44 != null) goto L12;
            return;
        L12:
            if (r44.itemView.getTop() <= ChatAttachAlertPhotoLayout.m9752$$Nest$mgetTopScrollOffset(this.this$0)) goto L18;
            this.this$0.gridView.smoothScrollBy(0, r44.itemView.getTop() - ChatAttachAlertPhotoLayout.m9752$$Nest$mgetTopScrollOffset(this.this$0));
            return;
        L18:
            return;
        L16:
            return;
        L6:
            r53 = 0;
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r3, int r4, int r5) {
            if (this.this$0.gridView.getChildCount() <= 0) goto L18;
            ChatAttachAlertPhotoLayout r32 = this.this$0;
            r32.parentAlert.updateLayout(r32, true, r5);
            float r0 = 0.0f;
            if (ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0).getTotalItemsCount() <= 30) goto L13;
            boolean r33 = this.parentPinnedToTop;
            ChatAttachAlertPhotoLayout r42 = this.this$0;
            boolean r1 = r42.parentAlert.pinnedToTop;
            if (r33 == r1) goto L14;
            this.parentPinnedToTop = r1;
            ViewPropertyAnimator r34 = r42.gridView.getFastScroll().animate();
            if (this.parentPinnedToTop == false) goto L12;
            r0 = 1.0f;
        L12:
            r34.alpha(r0).setDuration(100).start();
        L14:
            if (r5 == 0) goto L17;
            this.this$0.checkCameraViewPosition();
            return;
        L17:
            return;
        L13:
            this.this$0.gridView.getFastScroll().setAlpha(0.0f);
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$5, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass5 extends GridLayoutManager {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$5$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearSmoothScroller {
            final /* synthetic */ AnonymousClass5 this$1;

            public AnonymousClass1(AnonymousClass5 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateDyToMakeVisible(View r1, int r2) {
                return super.calculateDyToMakeVisible(r1, r2) - (this.this$1.this$0.gridView.getPaddingTop() - ChatAttachAlertPhotoLayout.m9752$$Nest$mgetTopScrollOffset(this.this$1.this$0));
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateTimeForDeceleration(int r1) {
                return super.calculateTimeForDeceleration(r1) * 2;
            }
        }

        public AnonymousClass5(ChatAttachAlertPhotoLayout r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView r1, RecyclerView.State r2, int r3) {
            AnonymousClass1 r22 = new AnonymousClass1(this, r1.getContext());
            r22.setTargetPosition(r3);
            startSmoothScroll(r22);
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$6, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass6 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public AnonymousClass6(ChatAttachAlertPhotoLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r3) {
            if (r3 == (PhotoAttachAdapter.m9773$$Nest$fgetitemsCount(ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0)) - 1)) goto L20;
            if (ChatAttachAlertPhotoLayout.m9721$$Nest$fgetnoGalleryPermissions(this.this$0) == false) goto L7;
        L8:
            if (r3 == 0) goto L20;
        L11:
            if (ChatAttachAlertPhotoLayout.m9720$$Nest$fgetnoCameraPermissions(this.this$0) == false) goto L13;
            r3 = r3 - 1;
        L13:
            int r0 = ChatAttachAlertPhotoLayout.m9716$$Nest$fgetitemSize(this.this$0);
            if ((r3 % ChatAttachAlertPhotoLayout.m9717$$Nest$fgetitemsPerRow(this.this$0)) == (ChatAttachAlertPhotoLayout.m9717$$Nest$fgetitemsPerRow(this.this$0) - 1)) goto L16;
            int r2 = AndroidUtilities.dp(2.0f);
        L18:
            return r0 + r2;
        L16:
            r2 = 0;
            goto L18
        L7:
            if (ChatAttachAlertPhotoLayout.m9720$$Nest$fgetnoCameraPermissions(this.this$0) == false) goto L11;
        L20:
            return ChatAttachAlertPhotoLayout.m9718$$Nest$fgetlayoutManager(this.this$0).getSpanCount();
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$7, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass7 implements RecyclerViewItemRangeSelector.RecyclerViewItemRangeSelectorDelegate {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public AnonymousClass7(ChatAttachAlertPhotoLayout r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.RecyclerViewItemRangeSelector.RecyclerViewItemRangeSelectorDelegate
        public int getItemCount() {
            return ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0).getItemCount();
        }

        @Override // org.telegram.ui.Components.RecyclerViewItemRangeSelector.RecyclerViewItemRangeSelectorDelegate
        public boolean isIndexSelectable(int r1) {
            if (ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0).getItemViewType(r1) != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerViewItemRangeSelector.RecyclerViewItemRangeSelectorDelegate
        public boolean isSelected(int r1) {
            MediaController.PhotoEntry r0 = PhotoAttachAdapter.m9775$$Nest$mgetPhoto(ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0), r1);
            if (r0 != null) goto L5;
            return false;
        L5:
            if (ChatAttachAlertPhotoLayout.selectedPhotos.containsKey(Integer.valueOf(r0.imageId)) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerViewItemRangeSelector.RecyclerViewItemRangeSelectorDelegate
        public void onStartStopSelection(boolean r2) {
            ChatAttachAlertPhotoLayout.m9736$$Nest$fputalertOnlyOnce(this.this$0, r2 ? 1 : 0);
            this.this$0.gridView.hideSelector(true);
        }

        @Override // org.telegram.ui.Components.RecyclerViewItemRangeSelector.RecyclerViewItemRangeSelectorDelegate
        public void setSelected(View r2, int r3, boolean r4) {
            if (isSelected(r3) != r4) goto L6;
            return;
        L6:
            if ((r2 instanceof PhotoAttachPhotoCell) == false) goto L9;
            ((PhotoAttachPhotoCell) r2).callDelegate();
            return;
        L9:
            ChatAttachAlertPhotoLayout r1 = this.this$0;
            r1.photoViewerProvider.setPhotoChecked(PhotoAttachAdapter.m9776$$Nest$mgetPhotoPosition(ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(r1), r3), null);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$8, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass8 extends TextView {
        float alpha;
        boolean isIncr;
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;
        final /* synthetic */ Paint val$recordPaint;

        public AnonymousClass8(ChatAttachAlertPhotoLayout r1, Context r2, Paint r3) {
            this.this$0 = r1;
            this.val$recordPaint = r3;
            super(r2);
            this.alpha = 0.0f;
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r5) {
            this.val$recordPaint.setAlpha((int) ((this.alpha * 130.0f) + 125.0f));
            boolean r0 = this.isIncr;
            float r1 = this.alpha;
            if (r0 == true) goto L7;
            float r12 = r1 - 0.026666667f;
            this.alpha = r12;
            if (r12 > 0.0f) goto L10;
            this.alpha = 0.0f;
            this.isIncr = true;
        L10:
            super.onDraw(r5);
            r5.drawCircle(AndroidUtilities.dp(14.0f), getMeasuredHeight() / 2, AndroidUtilities.dp(4.0f), this.val$recordPaint);
            invalidate();
            return;
        L7:
            float r13 = r1 + 0.026666667f;
            this.alpha = r13;
            if (r13 < 1.0f) goto L10;
            this.alpha = 1.0f;
            this.isIncr = false;
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$9, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass9 extends FrameLayout {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public AnonymousClass9(ChatAttachAlertPhotoLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r9, int r10, int r11, int r12, int r13) {
            if (getMeasuredWidth() != AndroidUtilities.dp(126.0f)) goto L5;
            int r92 = getMeasuredWidth() / 2;
            int r112 = getMeasuredHeight() / 2;
            int r0 = getMeasuredWidth() / 2;
            int r1 = r112 / 2;
            int r2 = (r112 + r1) + AndroidUtilities.dp(17.0f);
            int r14 = r1 - AndroidUtilities.dp(17.0f);
            int r122 = r0;
        L6:
            int r3 = (getMeasuredHeight() - ChatAttachAlertPhotoLayout.m9731$$Nest$fgettooltipTextView(this.this$0).getMeasuredHeight()) - AndroidUtilities.dp(12.0f);
            int r4 = getMeasuredWidth();
            int r102 = AndroidUtilities.dp(126.0f);
            ChatAttachAlertPhotoLayout r5 = this.this$0;
            if (r4 != r102) goto L9;
            ChatAttachAlertPhotoLayout.m9731$$Nest$fgettooltipTextView(r5).layout(r92 - (ChatAttachAlertPhotoLayout.m9731$$Nest$fgettooltipTextView(this.this$0).getMeasuredWidth() / 2), getMeasuredHeight(), (ChatAttachAlertPhotoLayout.m9731$$Nest$fgettooltipTextView(this.this$0).getMeasuredWidth() / 2) + r92, getMeasuredHeight() + ChatAttachAlertPhotoLayout.m9731$$Nest$fgettooltipTextView(this.this$0).getMeasuredHeight());
        L10:
            ChatAttachAlertPhotoLayout.m9728$$Nest$fgetshutterButton(this.this$0).layout(r92 - (ChatAttachAlertPhotoLayout.m9728$$Nest$fgetshutterButton(this.this$0).getMeasuredWidth() / 2), r112 - (ChatAttachAlertPhotoLayout.m9728$$Nest$fgetshutterButton(this.this$0).getMeasuredHeight() / 2), r92 + (ChatAttachAlertPhotoLayout.m9728$$Nest$fgetshutterButton(this.this$0).getMeasuredWidth() / 2), r112 + (ChatAttachAlertPhotoLayout.m9728$$Nest$fgetshutterButton(this.this$0).getMeasuredHeight() / 2));
            ChatAttachAlertPhotoLayout.m9729$$Nest$fgetswitchCameraButton(this.this$0).layout(r0 - (ChatAttachAlertPhotoLayout.m9729$$Nest$fgetswitchCameraButton(this.this$0).getMeasuredWidth() / 2), r2 - (ChatAttachAlertPhotoLayout.m9729$$Nest$fgetswitchCameraButton(this.this$0).getMeasuredHeight() / 2), r0 + (ChatAttachAlertPhotoLayout.m9729$$Nest$fgetswitchCameraButton(this.this$0).getMeasuredWidth() / 2), r2 + (ChatAttachAlertPhotoLayout.m9729$$Nest$fgetswitchCameraButton(this.this$0).getMeasuredHeight() / 2));
            int r93 = 0;
        L11:
            if (r93 >= 2) goto L13;
            ChatAttachAlertPhotoLayout.m9712$$Nest$fgetflashModeButton(this.this$0)[r93].layout(r122 - (ChatAttachAlertPhotoLayout.m9712$$Nest$fgetflashModeButton(this.this$0)[r93].getMeasuredWidth() / 2), r14 - (ChatAttachAlertPhotoLayout.m9712$$Nest$fgetflashModeButton(this.this$0)[r93].getMeasuredHeight() / 2), (ChatAttachAlertPhotoLayout.m9712$$Nest$fgetflashModeButton(this.this$0)[r93].getMeasuredWidth() / 2) + r122, (ChatAttachAlertPhotoLayout.m9712$$Nest$fgetflashModeButton(this.this$0)[r93].getMeasuredHeight() / 2) + r14);
            r93 = r93 + 1;
            goto L11
        L13:
            return;
        L9:
            ChatAttachAlertPhotoLayout.m9731$$Nest$fgettooltipTextView(r5).layout(r92 - (ChatAttachAlertPhotoLayout.m9731$$Nest$fgettooltipTextView(this.this$0).getMeasuredWidth() / 2), r3, (ChatAttachAlertPhotoLayout.m9731$$Nest$fgettooltipTextView(this.this$0).getMeasuredWidth() / 2) + r92, ChatAttachAlertPhotoLayout.m9731$$Nest$fgettooltipTextView(this.this$0).getMeasuredHeight() + r3);
            goto L10
        L5:
            r92 = getMeasuredWidth() / 2;
            r112 = (getMeasuredHeight() / 2) - AndroidUtilities.dp(13.0f);
            int r15 = r92 / 2;
            int r22 = (r92 + r15) + AndroidUtilities.dp(17.0f);
            r122 = r15 - AndroidUtilities.dp(17.0f);
            r14 = (getMeasuredHeight() / 2) - AndroidUtilities.dp(13.0f);
            r0 = r22;
            r2 = r14;
            goto L6
        }
    }

    /* loaded from: classes7.dex */
    public class BasePhotoProvider extends PhotoViewer.EmptyPhotoViewerProvider {
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        private BasePhotoProvider(ChatAttachAlertPhotoLayout r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ BasePhotoProvider(ChatAttachAlertPhotoLayout r1, ChatAttachAlertPhotoLayoutIA r2) {
            this(r1);
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean allowLivePhotos() {
            ChatAttachAlert r0 = this.this$0.parentAlert;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.allowLivePhotos == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public int getPhotoIndex(int r1) {
            MediaController.PhotoEntry r0 = ChatAttachAlertPhotoLayout.m9751$$Nest$mgetPhotoEntryAtPosition(this.this$0, r1);
            if (r0 != null) goto L7;
            return -1;
        L7:
            return ChatAttachAlertPhotoLayout.selectedPhotosOrder.indexOf(Integer.valueOf(r0.imageId));
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public int getSelectedCount() {
            return ChatAttachAlertPhotoLayout.selectedPhotos.size();
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public HashMap<Object, Object> getSelectedPhotos() {
            return ChatAttachAlertPhotoLayout.selectedPhotos;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public ArrayList<Object> getSelectedPhotosOrder() {
            return ChatAttachAlertPhotoLayout.selectedPhotosOrder;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean isPhotoChecked(int r1) {
            MediaController.PhotoEntry r0 = ChatAttachAlertPhotoLayout.m9751$$Nest$mgetPhotoEntryAtPosition(this.this$0, r1);
            if (r0 != null) goto L5;
            return false;
        L5:
            if (ChatAttachAlertPhotoLayout.selectedPhotos.containsKey(Integer.valueOf(r0.imageId)) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public int setPhotoChecked(int r9, VideoEditedInfo r10) {
            boolean r0 = isPhotoChecked(r9);
            if (this.this$0.parentAlert.maxSelectedPhotos >= 0) goto L5;
        L8:
            MediaController.PhotoEntry r1 = ChatAttachAlertPhotoLayout.m9751$$Nest$mgetPhotoEntryAtPosition(this.this$0, r9);
            if (r1 != null) goto L12;
            return -1;
        L12:
            if (ChatAttachAlertPhotoLayout.m9749$$Nest$mcheckSendMediaEnabled(this.this$0, r1) == false) goto L14;
            return -1;
        L14:
            int r3 = 1;
            if (r0 == false) goto L17;
        L19:
            int r02 = ChatAttachAlertPhotoLayout.m9748$$Nest$maddToSelectedPhotos(this.this$0, r1, -1);
            if (r02 != (-1)) goto L22;
            r02 = ChatAttachAlertPhotoLayout.selectedPhotosOrder.indexOf(Integer.valueOf(r1.imageId));
            boolean r5 = true;
        L23:
            r1.editedInfo = r10;
            int r102 = this.this$0.gridView.getChildCount();
            int r12 = 0;
        L24:
            if (r12 >= r102) goto L36;
            View r6 = this.this$0.gridView.getChildAt(r12);
            if ((r6 instanceof PhotoAttachPhotoCell) == false) goto L35;
            if (((Integer) r6.getTag()).intValue() != r9) goto L35;
            ChatAttachAlert r103 = this.this$0.parentAlert;
            if ((r103.baseFragment instanceof ChatActivity) == true) goto L32;
        L34:
            ((PhotoAttachPhotoCell) r6).setChecked(-1, r5, false);
            goto L36
        L32:
            if (r103.allowOrder == false) goto L34;
            ((PhotoAttachPhotoCell) r6).setChecked(r02, r5, false);
        L35:
            r12 = r12 + 1;
        L36:
            int r104 = ChatAttachAlertPhotoLayout.m9704$$Nest$fgetcameraPhotoRecyclerView(this.this$0).getChildCount();
            int r13 = 0;
        L37:
            if (r13 >= r104) goto L49;
            View r62 = ChatAttachAlertPhotoLayout.m9704$$Nest$fgetcameraPhotoRecyclerView(this.this$0).getChildAt(r13);
            if ((r62 instanceof PhotoAttachPhotoCell) == false) goto L48;
            if (((Integer) r62.getTag()).intValue() != r9) goto L48;
            ChatAttachAlert r92 = this.this$0.parentAlert;
            if ((r92.baseFragment instanceof ChatActivity) == true) goto L45;
        L47:
            ((PhotoAttachPhotoCell) r62).setChecked(-1, r5, false);
            goto L49
        L45:
            if (r92.allowOrder == false) goto L47;
            ((PhotoAttachPhotoCell) r62).setChecked(r02, r5, false);
        L48:
            r13 = r13 + 1;
        L49:
            ChatAttachAlert r8 = this.this$0.parentAlert;
            if (r5 == true) goto L53;
            r3 = 2;
        L53:
            r8.updateCountButton(r3);
            return r02;
        L22:
            r1.editedInfo = null;
            r5 = false;
            goto L23
        L17:
            if ((ChatAttachAlertPhotoLayout.selectedPhotos.size() + 1) <= ChatAttachAlertPhotoLayout.m9753$$Nest$mmaxCount(this.this$0)) goto L19;
            return -1;
        L5:
            if (ChatAttachAlertPhotoLayout.selectedPhotos.size() < this.this$0.parentAlert.maxSelectedPhotos) goto L8;
            if (r0 == true) goto L8;
            return -1;
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void spoilerPressed() {
            this.this$0.onMenuItemClick(10);
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void updatedLivePhotos() {
            ChatAttachAlertPhotoLayout.m9762$$Nest$mupdateCells(this.this$0);
        }
    }

    /* loaded from: classes7.dex */
    public class CameraViewInternal extends CameraView {
        Bulletin.Delegate bulletinDelegate;
        public boolean drawInDecoration;
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$CameraViewInternal$1, reason: invalid class name */
        public class AnonymousClass1 implements Bulletin.Delegate {
            final /* synthetic */ CameraViewInternal this$1;

            public AnonymousClass1(CameraViewInternal r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getBottomOffset(int r1) {
                return AndroidUtilities.dp(126.0f) + this.this$1.this$0.parentAlert.getBottomInset();
            }
        }

        public CameraViewInternal(ChatAttachAlertPhotoLayout r1, Context r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
            this.bulletinDelegate = new AnonymousClass1(this);
        }

        @Override // org.telegram.messenger.camera.CameraView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            if (AndroidUtilities.makingGlobalBlurBitmap == false) goto L6;
            return;
        L6:
            if (this.drawInDecoration == false) goto L8;
        L28:
            super.dispatchDraw(r8);
            return;
        L8:
            if (ChatAttachAlertPhotoLayout.m9698$$Nest$fgetcameraAnimationInProgress(this.this$0) == false) goto L10;
        L12:
            float r0 = ((this.this$0.parentAlert.getCommentTextViewTop() + ChatAttachAlertPhotoLayout.m9711$$Nest$fgetcurrentPanTranslationY(this.this$0)) + this.this$0.parentAlert.getContainerView().getTranslationY()) - this.this$0.cameraView.getTranslationY();
            MentionsContainerView r1 = this.this$0.parentAlert.mentionContainer;
            if (r1 == null) goto L15;
            float r12 = r1.clipBottom() + AndroidUtilities.dp(8.0f);
        L16:
            int r02 = (int) Math.min(r0 - r12, getMeasuredHeight());
            if (ChatAttachAlertPhotoLayout.m9698$$Nest$fgetcameraAnimationInProgress(this.this$0) == false) goto L20;
            RectF r13 = AndroidUtilities.rectTmp;
            ChatAttachAlertPhotoLayout r2 = this.this$0;
            float r3 = r2.animationClipLeft + (ChatAttachAlertPhotoLayout.m9707$$Nest$fgetcameraViewOffsetX(r2) * (1.0f - ChatAttachAlertPhotoLayout.m9701$$Nest$fgetcameraOpenProgress(this.this$0)));
            ChatAttachAlertPhotoLayout r22 = this.this$0;
            float r4 = r22.animationClipTop + (ChatAttachAlertPhotoLayout.m9708$$Nest$fgetcameraViewOffsetY(r22) * (1.0f - ChatAttachAlertPhotoLayout.m9701$$Nest$fgetcameraOpenProgress(this.this$0)));
            ChatAttachAlertPhotoLayout r23 = this.this$0;
            r13.set(r3, r4, r23.animationClipRight, AndroidUtilities.lerp(Math.min(r02, r23.animationClipBottom), getMeasuredHeight(), ChatAttachAlertPhotoLayout.m9701$$Nest$fgetcameraOpenProgress(this.this$0)));
        L26:
            r8.save();
            r8.clipRect(AndroidUtilities.rectTmp);
            super.dispatchDraw(r8);
            r8.restore();
            return;
        L20:
            if (ChatAttachAlertPhotoLayout.m9698$$Nest$fgetcameraAnimationInProgress(this.this$0) == true) goto L25;
            ChatAttachAlertPhotoLayout r14 = this.this$0;
            if (r14.cameraOpened == true) goto L25;
            AndroidUtilities.rectTmp.set(ChatAttachAlertPhotoLayout.m9707$$Nest$fgetcameraViewOffsetX(r14), ChatAttachAlertPhotoLayout.m9708$$Nest$fgetcameraViewOffsetY(this.this$0), getMeasuredWidth(), Math.min(r02, getMeasuredHeight()));
            return;
        L25:
            AndroidUtilities.rectTmp.set(0.0f, 0.0f, getMeasuredWidth(), Math.min(r02, getMeasuredHeight()));
            goto L26
        L15:
            r12 = 0.0f;
            goto L16
        L10:
            if (this.this$0.cameraOpened == false) goto L12;
            goto L12
        }

        @Override // org.telegram.messenger.camera.CameraView, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            Bulletin.addDelegate(this.this$0.cameraView, this.bulletinDelegate);
            this.this$0.gridView.invalidate();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            Bulletin.removeDelegate(this.this$0.cameraView);
        }

        @Override // android.view.View
        public void setVisibility(int r1) {
            super.setVisibility(r1);
            this.this$0.gridView.invalidate();
        }

        @Override // org.telegram.messenger.camera.CameraView
        public void showTexture(boolean r1, boolean r2) {
            super.showTexture(r1, r2);
            this.this$0.gridView.invalidate();
        }
    }

    /* loaded from: classes7.dex */
    public class CameraViewItemDecoration extends RecyclerView.ItemDecoration implements IBlur3Capture {
        private final Drawable cameraDrawable;
        private final Path clipPath;
        private final RecyclerView parent;
        private Drawable placeholderDrawable;
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;

        public CameraViewItemDecoration(ChatAttachAlertPhotoLayout r2, RecyclerView r3) {
            this.this$0 = r2;
            this.clipPath = new Path();
            this.parent = r3;
            this.cameraDrawable = r2.getContext().getResources().getDrawable(R.drawable.camera).mutate();
        }

        private void draw(Canvas r19, RecyclerView r20, IBlur3Hash r21, RectF r22) {
            if (ChatAttachAlertPhotoLayout.m9698$$Nest$fgetcameraAnimationInProgress(this.this$0) == true) goto L71;
            ChatAttachAlertPhotoLayout r5 = this.this$0;
            if (r5.cameraOpened == true) goto L71;
            if (PhotoAttachAdapter.m9771$$Nest$fgethasCamera(ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(r5)) == false) goto L71;
            if (ChatAttachAlertPhotoLayout.m9720$$Nest$fgetnoCameraPermissions(this.this$0) == true) goto L71;
            if (ChatAttachAlertPhotoLayout.m9721$$Nest$fgetnoGalleryPermissions(this.this$0) == true) goto L71;
            if (ExteraConfig.getHideCameraTile() == true) goto L71;
            RecyclerView.ViewHolder r6 = r20.findViewHolderForAdapterPosition(0);
            if (r6 == null) goto L18;
            int r2 = r6.itemView.getTop();
        L21:
            int r62 = r6.itemView.getLeft();
            int r8 = ChatAttachAlertPhotoLayout.m9716$$Nest$fgetitemSize(this.this$0) + r62;
            int r9 = ((ChatAttachAlertPhotoLayout.m9716$$Nest$fgetitemSize(this.this$0) * 2) + r2) + AndroidUtilities.dp(2.0f);
            if (r21 == null) goto L24;
            r21.add(r62);
            r21.add(r2);
            r21.add(r8);
            r21.add(r9);
        L24:
            if (r22 == null) goto L29;
            if (r22.intersects(r62, r2, r8, r9) == true) goto L29;
            return;
        L29:
            if (r21 != null) goto L31;
        L49:
            if (r19 == null) goto L77;
            float r15 = AndroidUtilities.dp(16.0f);
            this.clipPath.rewind();
            float r11 = r62;
            float r12 = r2;
            this.clipPath.addRoundRect(r11, r12, r8 + r15, r9 + r15, r15, r15, Path.Direction.CW);
            r19.save();
            r19.clipPath(this.clipPath);
            if (this.placeholderDrawable == null) goto L60;
            CameraViewInternal r3 = this.this$0.cameraView;
            if (r3 != null) goto L56;
        L59:
            this.placeholderDrawable.setBounds(r62, r2, r8, r9);
            this.placeholderDrawable.draw(r19);
            goto L60
        L56:
            if (r3.isInited() == false) goto L59;
            if (ChatAttachAlertPhotoLayout.m9715$$Nest$fgetisHidden(this.this$0) == true) goto L59;
        L60:
            CameraViewInternal r32 = this.this$0.cameraView;
            if (r32 == null) goto L64;
            r32.drawInDecoration = true;
            r19.save();
            r19.clipRect(r62, r2, r8, r9);
            r19.translate(r11, r12);
            this.this$0.cameraView.draw(r19);
            r19.restore();
            this.this$0.cameraView.drawInDecoration = false;
        L64:
            if (this.cameraDrawable == null) goto L66;
            int r33 = AndroidUtilities.dp(24.0f);
            int r82 = (r8 - AndroidUtilities.dp(7.0f)) - r33;
            int r23 = r2 + AndroidUtilities.dp(7.0f);
            this.cameraDrawable.setBounds(r82, r23, r82 + r33, r33 + r23);
            this.cameraDrawable.draw(r19);
        L66:
            r19.restore();
            this.this$0.gridView.invalidate();
            return;
        L77:
            return;
        L31:
            if (this.placeholderDrawable == null) goto L39;
            CameraViewInternal r7 = this.this$0.cameraView;
            if (r7 != null) goto L35;
        L38:
            boolean r72 = true;
        L40:
            r21.add(r72);
            if (this.this$0.cameraView == null) goto L43;
            boolean r73 = true;
        L44:
            r21.add(r73);
            if (this.cameraDrawable == null) goto L47;
            boolean r74 = true;
        L48:
            r21.add(r74);
            goto L49
        L47:
            r74 = false;
            goto L48
        L43:
            r73 = false;
            goto L44
        L35:
            if (r7.isInited() == false) goto L38;
            if (ChatAttachAlertPhotoLayout.m9715$$Nest$fgetisHidden(this.this$0) == true) goto L38;
        L39:
            r72 = false;
            goto L40
        L18:
            r6 = r20.findViewHolderForAdapterPosition(ChatAttachAlertPhotoLayout.m9717$$Nest$fgetitemsPerRow(this.this$0));
            if (r6 == null) goto L68;
            r2 = (r6.itemView.getTop() - AndroidUtilities.dp(2.0f)) - ChatAttachAlertPhotoLayout.m9716$$Nest$fgetitemSize(this.this$0);
            goto L21
        L68:
            if (r21 == null) goto L74;
            r21.unsupported();
            return;
        L74:
            return;
        L71:
            if (r21 == null) goto L75;
            r21.unsupported();
            return;
        }

        @Override // org.telegram.ui.Components.blur3.capture.IBlur3Capture
        public void capture(Canvas r3, RectF r4) {
            draw(r3, this.parent, null, r4);
        }

        @Override // org.telegram.ui.Components.blur3.capture.IBlur3Capture
        public void captureCalculateHash(IBlur3Hash r3, RectF r4) {
            draw(null, this.parent, r3, r4);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDraw(Canvas r1, RecyclerView r2, RecyclerView.State r3) {
            draw(r1, r2, null, null);
        }

        public void updateBitmap() {
            Bitmap r0 = BitmapFactory.decodeFile(new File(ApplicationLoader.getFilesDirFixed(), "cthumb.jpg").getAbsolutePath());     // Catch: Throwable -> L4
        L5:
            if (r0 == null) goto L7;
            this.placeholderDrawable = new BitmapDrawable(this.this$0.getContext().getResources(), r0);
        L8:
            RecyclerListView r3 = this.this$0.gridView;
            if (r3 == null) goto L14;
            r3.invalidate();
            return;
        L14:
            return;
        L7:
            this.placeholderDrawable = this.this$0.getContext().getResources().getDrawable(R.drawable.icplaceholder).mutate();
        L4:
            r0 = null;
            goto L5
        }
    }

    /* loaded from: classes7.dex */
    public class PhotoAttachAdapter extends RecyclerListView.FastScrollAdapter {
        private boolean hasCamera;
        private boolean hasCameraSpaceRow;
        private boolean isInFastScroll;
        private int itemsCount;
        private String lastLetter;
        private long lastLetterDate;
        private final Context mContext;
        private final boolean needCamera;
        private int photosEndRow;
        private int photosStartRow;
        final /* synthetic */ ChatAttachAlertPhotoLayout this$0;
        private final ArrayList<RecyclerListView.Holder> viewsCache;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$PhotoAttachAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends ViewOutlineProvider {
            final /* synthetic */ PhotoAttachAdapter this$1;

            public AnonymousClass1(PhotoAttachAdapter r1) {
                this.this$1 = r1;
            }

            @Override // android.view.ViewOutlineProvider
            public void getOutline(View r14, Outline r15) {
                PhotoAttachPhotoCell r0 = (PhotoAttachPhotoCell) r14;
                if (r0.getTag() != null) goto L5;
                return;
            L5:
                int r02 = ((Integer) r0.getTag()).intValue();
                if (PhotoAttachAdapter.m9774$$Nest$fgetneedCamera(this.this$1) == false) goto L15;
                if (ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$1.this$0) != ChatAttachAlertPhotoLayout.m9713$$Nest$fgetgalleryAlbumEntry(this.this$1.this$0)) goto L15;
                if (ExteraConfig.getHideCameraTile() == true) goto L12;
            L13:
                r02 = r02 + 1;
                goto L15
            L12:
                if (ChatAttachAlertPhotoLayout.m9720$$Nest$fgetnoCameraPermissions(this.this$1.this$0) == true) goto L13;
            L15:
                if (ChatAttachAlertPhotoLayout.m9727$$Nest$fgetshowAvatarConstructor(this.this$1.this$0) == false) goto L18;
                r02 = r02 + 1;
            L18:
                if (r02 != 0) goto L22;
                int r13 = AndroidUtilities.dp(16.0f);
                r15.setRoundRect(0, 0, r14.getMeasuredWidth() + r13, r14.getMeasuredHeight() + r13, r13);
                return;
            L22:
                if (r02 != (ChatAttachAlertPhotoLayout.m9717$$Nest$fgetitemsPerRow(this.this$1.this$0) - 1)) goto L25;
                int r132 = AndroidUtilities.dp(16.0f);
                r15.setRoundRect(-r132, 0, r14.getMeasuredWidth(), r14.getMeasuredHeight() + r132, r132);
                return;
            L25:
                r15.setRect(0, 0, r14.getMeasuredWidth(), r14.getMeasuredHeight());
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$PhotoAttachAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends View {
            final /* synthetic */ PhotoAttachAdapter this$1;

            public AnonymousClass2(PhotoAttachAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(ChatAttachAlertPhotoLayout.m9714$$Nest$fgetgridExtraSpace(this.this$1.this$0), TLObject.FLAG_30));
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertPhotoLayout$PhotoAttachAdapter$3, reason: invalid class name */
        public class AnonymousClass3 extends AvatarConstructorPreviewCell {
            final /* synthetic */ PhotoAttachAdapter this$1;

            public AnonymousClass3(PhotoAttachAdapter r1, Context r2, boolean r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Components.AvatarConstructorPreviewCell, android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(ChatAttachAlertPhotoLayout.m9716$$Nest$fgetitemSize(this.this$1.this$0), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(ChatAttachAlertPhotoLayout.m9716$$Nest$fgetitemSize(this.this$1.this$0), TLObject.FLAG_30));
            }
        }

        /* renamed from: $r8$lambda$5cjFdY0jEZSE-AkLp301I4dlGCk, reason: not valid java name */
        public static /* synthetic */ void m9768$r8$lambda$5cjFdY0jEZSEAkLp301I4dlGCk(PhotoAttachAdapter r0, Long r1) {
            r0.lambda$onCreateViewHolder$1(r1);
        }

        /* renamed from: $r8$lambda$Pwlp2L-HtYjiEZocjcWDVwpUBfY, reason: not valid java name */
        public static /* synthetic */ void m9769$r8$lambda$Pwlp2LHtYjiEZocjcWDVwpUBfY(ChatAttachAlertPhotoLayout r0) {
            ChatAttachAlertPhotoLayout.m9757$$Nest$mrequestGalleryPermission(r0);
        }

        public static /* synthetic */ void $r8$lambda$drHm9p02R3gQIwxLeGIlsXvGu1E(PhotoAttachAdapter r0, PhotoAttachPhotoCell r1, PhotoAttachPhotoCell r2) {
            r0.lambda$createHolder$0(r1, r2);
        }

        /* renamed from: $r8$lambda$ijbzYrMCQu073KHdAbK-tPR0fCE, reason: not valid java name */
        public static /* synthetic */ void m9770$r8$lambda$ijbzYrMCQu073KHdAbKtPR0fCE(ChatAttachAlertPhotoLayout r0) {
            ChatAttachAlertPhotoLayout.m9755$$Nest$mopenCameraWithPermissionCheck(r0);
        }

        /* renamed from: -$$Nest$fgethasCamera, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m9771$$Nest$fgethasCamera(PhotoAttachAdapter r0) {
            return r0.hasCamera;
        }

        /* renamed from: -$$Nest$fgethasCameraSpaceRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m9772$$Nest$fgethasCameraSpaceRow(PhotoAttachAdapter r0) {
            return r0.hasCameraSpaceRow;
        }

        /* renamed from: -$$Nest$fgetitemsCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m9773$$Nest$fgetitemsCount(PhotoAttachAdapter r0) {
            return r0.itemsCount;
        }

        /* renamed from: -$$Nest$fgetneedCamera, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m9774$$Nest$fgetneedCamera(PhotoAttachAdapter r0) {
            return r0.needCamera;
        }

        /* renamed from: -$$Nest$mgetPhoto, reason: not valid java name */
        public static /* bridge */ /* synthetic */ MediaController.PhotoEntry m9775$$Nest$mgetPhoto(PhotoAttachAdapter r0, int r1) {
            return r0.getPhoto(r1);
        }

        /* renamed from: -$$Nest$mgetPhotoPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m9776$$Nest$mgetPhotoPosition(PhotoAttachAdapter r0, int r1) {
            return r0.getPhotoPosition(r1);
        }

        public PhotoAttachAdapter(ChatAttachAlertPhotoLayout r3, Context r4, boolean r5) {
            this.this$0 = r3;
            this.viewsCache = new ArrayList(8);
            this.lastLetterDate = -1;
            this.mContext = r4;
            this.needCamera = r5;
        }

        private MediaController.PhotoEntry getPhoto(int r2) {
            return ChatAttachAlertPhotoLayout.m9751$$Nest$mgetPhotoEntryAtPosition(this.this$0, getPhotoPosition(r2));
        }

        private int getPhotoPosition(int r3) {
            if (this.hasCameraSpaceRow == false) goto L8;
            if (r3 <= ChatAttachAlertPhotoLayout.m9717$$Nest$fgetitemsPerRow(this.this$0)) goto L8;
            r3 = r3 - 1;
        L8:
            if (this.needCamera == true) goto L10;
            return r3;
        L10:
            if (ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0) == ChatAttachAlertPhotoLayout.m9713$$Nest$fgetgalleryAlbumEntry(this.this$0)) goto L12;
            return r3;
        L12:
            if (ExteraConfig.getHideCameraTile() == false) goto L16;
            if (ChatAttachAlertPhotoLayout.m9720$$Nest$fgetnoCameraPermissions(this.this$0) == true) goto L16;
            return r3;
        L16:
            return r3 - 1;
        }

        private /* synthetic */ void lambda$createHolder$0(PhotoAttachPhotoCell r12, PhotoAttachPhotoCell r13) {
            if (ChatAttachAlertPhotoLayout.m9719$$Nest$fgetmediaEnabled(this.this$0) == false) goto L72;
            ChatAttachAlert r0 = this.this$0.parentAlert;
            if (r0.avatarPicker == 0) goto L7;
            return;
        L7:
            if (r0.isPollAttach == true) goto L81;
            int r02 = ((Integer) r13.getTag()).intValue();
            MediaController.PhotoEntry r1 = r13.getPhotoEntry();
            if (ChatAttachAlertPhotoLayout.m9749$$Nest$mcheckSendMediaEnabled(this.this$0, r1) == true) goto L82;
            boolean r2 = ChatAttachAlertPhotoLayout.selectedPhotos.containsKey(Integer.valueOf(r1.imageId));
            boolean r3 = !r2;
            boolean r4 = false;
            if (r2 == false) goto L15;
        L18:
            int r6 = 2;
            if (r2 == true) goto L41;
            if (this.this$0.parentAlert.maxSelectedPhotos < 0) goto L41;
            int r7 = ChatAttachAlertPhotoLayout.selectedPhotos.size();
            ChatAttachAlert r8 = this.this$0.parentAlert;
            if (r7 < r8.maxSelectedPhotos) goto L41;
            if (r8.allowOrder == false) goto L74;
            BaseFragment r122 = r8.baseFragment;
            if ((r122 instanceof ChatActivity) == false) goto L75;
            TLRPC.Chat r123 = ((ChatActivity) r122).getCurrentChat();
            if (r123 != null) goto L31;
            return;
        L31:
            if (ChatObject.hasAdminRights(r123) == false) goto L33;
            return;
        L33:
            if (r123.slowmode_enabled == true) goto L35;
            return;
        L35:
            if (ChatAttachAlertPhotoLayout.m9697$$Nest$fgetalertOnlyOnce(this.this$0) == 2) goto L79;
            AlertsCreator.createSimpleAlert(this.this$0.getContext(), LocaleController.getString(R.string.Slowmode), LocaleController.getString(R.string.SlowmodeSelectSendError), this.this$0.resourcesProvider).show();
            if (ChatAttachAlertPhotoLayout.m9697$$Nest$fgetalertOnlyOnce(this.this$0) != 1) goto L80;
            ChatAttachAlertPhotoLayout.m9736$$Nest$fputalertOnlyOnce(this.this$0, 2);
            return;
        L80:
            return;
        L79:
            return;
        L75:
            return;
        L74:
            return;
        L41:
            if (r2 == true) goto L43;
            int r82 = ChatAttachAlertPhotoLayout.selectedPhotosOrder.size();
        L44:
            ChatAttachAlert r9 = this.this$0.parentAlert;
            if ((r9.baseFragment instanceof ChatActivity) == true) goto L47;
        L49:
            r13.setChecked(-1, r3, true);
        L50:
            ChatAttachAlertPhotoLayout.m9748$$Nest$maddToSelectedPhotos(this.this$0, r1, r02);
            PhotoAttachAdapter r132 = ChatAttachAlertPhotoLayout.m9699$$Nest$fgetcameraAttachAdapter(this.this$0);
            ChatAttachAlertPhotoLayout r32 = this.this$0;
            if (this == r132) goto L53;
            ChatAttachAlertPhotoLayout.m9699$$Nest$fgetcameraAttachAdapter(r32).notifyItemChanged(r02);
        L64:
            ChatAttachAlert r11 = this.this$0.parentAlert;
            if (r2 == true) goto L67;
            r6 = 1;
        L67:
            r11.updateCountButton(r6);
            r12.setHasSpoiler(r1.hasSpoiler);
            r12.setHighQuality(r1.isHighQuality());
            long r03 = r1.starsAmount;
            if (ChatAttachAlertPhotoLayout.selectedPhotos.size() <= 1) goto L70;
            r4 = true;
        L70:
            r12.setStarsPrice(r03, r4);
            return;
        L53:
            if (ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(r32).needCamera == false) goto L58;
            if (ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0) != ChatAttachAlertPhotoLayout.m9713$$Nest$fgetgalleryAlbumEntry(this.this$0)) goto L58;
            r02 = r02 + 1;
        L58:
            if (ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0).hasCameraSpaceRow == true) goto L60;
        L62:
            ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0).notifyItemChanged(r02);
            goto L64
        L60:
            if (r02 < ChatAttachAlertPhotoLayout.m9717$$Nest$fgetitemsPerRow(this.this$0)) goto L62;
            r02 = r02 + 1;
            goto L62
        L47:
            if (r9.allowOrder == false) goto L49;
            r13.setChecked(r82, r3, true);
            goto L50
        L43:
            r82 = -1;
            goto L44
        L15:
            if ((ChatAttachAlertPhotoLayout.selectedPhotos.size() + 1) <= ChatAttachAlertPhotoLayout.m9753$$Nest$mmaxCount(this.this$0)) goto L18;
            ChatAttachAlertPhotoLayout r124 = this.this$0;
            BulletinFactory.of(r124.parentAlert.sizeNotifierFrameLayout, r124.resourcesProvider).createErrorBulletin(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BusinessRepliesToastLimit", this.this$0.parentAlert.baseFragment.getMessagesController().config.quickReplyMessagesLimit.get(), new Object[0]))).show();
            return;
        L82:
            return;
        L81:
            return;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$1(Long r5) {
            this.this$0.showAvatarConstructorFragment(null, null, r5.longValue());
            this.this$0.parentAlert.dismiss();
        }

        public void createCache() {
            int r0 = 0;
        L4:
            if (r0 >= 8) goto L6;
            this.viewsCache.add(createHolder());
            r0 = r0 + 1;
            goto L4
        }

        public RecyclerListView.Holder createHolder() {
            PhotoAttachPhotoCell r0 = new PhotoAttachPhotoCell(this.mContext, this.this$0.resourcesProvider);
            if (this != ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0)) goto L5;
            r0.setOutlineProvider(new AnonymousClass1(this));
            r0.setClipToOutline(true);
        L5:
            r0.setFastScrollDelegate(new ChatAttachAlertPhotoLayout$PhotoAttachAdapter$$ExternalSyntheticLambda3(this));
            r0.setDelegate(new ChatAttachAlertPhotoLayout$PhotoAttachAdapter$$ExternalSyntheticLambda4(this, r0));
            return new RecyclerListView.Holder(r0);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public boolean fastScrollIsVisible(RecyclerListView r1) {
            if (ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().isEmpty() == false) goto L9;
            if (ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0) != null) goto L7;
            return false;
        L7:
            if (ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0).photos.isEmpty() == false) goto L9;
            return false;
        L9:
            if (this.this$0.parentAlert.pinnedToTop == true) goto L11;
            return false;
        L11:
            if (getTotalItemsCount() <= 30) goto L18;
            return true;
        L18:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (ChatAttachAlertPhotoLayout.m9719$$Nest$fgetmediaEnabled(this.this$0) == true) goto L5;
            return 1;
        L5:
            int r0 = 0;
            this.hasCamera = false;
            this.hasCameraSpaceRow = false;
            if (ChatAttachAlertPhotoLayout.m9721$$Nest$fgetnoGalleryPermissions(this.this$0) == false) goto L12;
            if (this != ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0)) goto L12;
            return 2;
        L12:
            if (this.needCamera == false) goto L21;
            if (ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0) != ChatAttachAlertPhotoLayout.m9713$$Nest$fgetgalleryAlbumEntry(this.this$0)) goto L21;
            if (ExteraConfig.getHideCameraTile() == true) goto L18;
        L19:
            this.hasCamera = true;
            r0 = 1;
            goto L21
        L18:
            if (ChatAttachAlertPhotoLayout.m9720$$Nest$fgetnoCameraPermissions(this.this$0) == true) goto L19;
        L21:
            if (ChatAttachAlertPhotoLayout.m9727$$Nest$fgetshowAvatarConstructor(this.this$0) == false) goto L24;
            r0 = r0 + 1;
        L24:
            if (ChatAttachAlertPhotoLayout.m9721$$Nest$fgetnoGalleryPermissions(this.this$0) == true) goto L26;
        L28:
            this.photosStartRow = r0;
            if (ChatAttachAlertPhotoLayout.m9721$$Nest$fgetnoGalleryPermissions(this.this$0) == true) goto L33;
            r0 = r0 + ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().size();
            if (ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0) == null) goto L33;
            r0 = r0 + ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0).photos.size();
        L33:
            this.photosEndRow = r0;
            if (this.hasCamera == false) goto L41;
            if (r0 <= ChatAttachAlertPhotoLayout.m9717$$Nest$fgetitemsPerRow(this.this$0)) goto L41;
            if (ChatAttachAlertPhotoLayout.m9720$$Nest$fgetnoCameraPermissions(this.this$0) == true) goto L41;
            this.hasCameraSpaceRow = true;
            r0 = r0 + 1;
        L41:
            if (this != ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0)) goto L43;
            r0 = r0 + 1;
        L43:
            this.itemsCount = r0;
            return r0;
        L26:
            if (this != ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0)) goto L28;
            r0 = r0 + 1;
            goto L28
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r5) {
            if (ChatAttachAlertPhotoLayout.m9719$$Nest$fgetmediaEnabled(this.this$0) == true) goto L6;
            return 2;
        L6:
            if (ChatAttachAlertPhotoLayout.m9721$$Nest$fgetnoGalleryPermissions(this.this$0) == false) goto L14;
            if (this != ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0)) goto L14;
            if (r5 != 0) goto L12;
            return 7;
        L12:
            return 2;
        L14:
            if (this.needCamera == false) goto L28;
            if (r5 != 0) goto L28;
            if (ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0) != ChatAttachAlertPhotoLayout.m9713$$Nest$fgetgalleryAlbumEntry(this.this$0)) goto L28;
            if (ExteraConfig.getHideCameraTile() == false) goto L23;
            if (ChatAttachAlertPhotoLayout.m9720$$Nest$fgetnoCameraPermissions(this.this$0) == false) goto L28;
        L23:
            if (ChatAttachAlertPhotoLayout.m9720$$Nest$fgetnoCameraPermissions(this.this$0) == false) goto L26;
            return 8;
        L26:
            return 1;
        L28:
            if (this.hasCameraSpaceRow == false) goto L34;
            if (r5 != ChatAttachAlertPhotoLayout.m9717$$Nest$fgetitemsPerRow(this.this$0)) goto L34;
            return 5;
        L34:
            if (this.hasCameraSpaceRow == true) goto L36;
        L38:
            int r0 = r5;
        L40:
            if (this.needCamera == false) goto L47;
            if (ExteraConfig.getHideCameraTile() == true) goto L44;
        L45:
            r0 = r0 - 1;
            goto L47
        L44:
            if (ChatAttachAlertPhotoLayout.m9720$$Nest$fgetnoCameraPermissions(this.this$0) == true) goto L45;
        L47:
            if (ChatAttachAlertPhotoLayout.m9727$$Nest$fgetshowAvatarConstructor(this.this$0) == false) goto L52;
            if (r0 != 0) goto L52;
            return 4;
        L52:
            if (this != ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0)) goto L57;
            if (r5 != (this.itemsCount - 1)) goto L57;
            return 2;
        L57:
            if (ChatAttachAlertPhotoLayout.m9721$$Nest$fgetnoGalleryPermissions(this.this$0) == false) goto L60;
            return 3;
        L60:
            return 0;
        L36:
            if (r5 <= ChatAttachAlertPhotoLayout.m9717$$Nest$fgetitemsPerRow(this.this$0)) goto L38;
            r0 = r5 - 1;
            goto L40
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public String getLetter(int r7) {
            MediaController.PhotoEntry r0 = getPhoto(r7);
            if (r0 == null) goto L5;
        L17:
            if (r0 == null) goto L28;
            long r2 = r0.dateTaken;
            if (Build.VERSION.SDK_INT > 28) goto L22;
            r2 = r2 / 1000;
        L22:
            if (this.lastLetter != null) goto L24;
        L25:
            this.lastLetterDate = r2;
            this.lastLetter = LocaleController.formatYearMont(r2, true);
        L27:
            return this.lastLetter;
        L24:
            if (this.lastLetterDate == r2) goto L27;
        L28:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            if (r7 > this.photosStartRow) goto L15;
            if (ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().isEmpty() == true) goto L10;
            r0 = (MediaController.PhotoEntry) ChatAttachAlertPhotoLayout.m9763$$Nest$sfgetcameraPhotos().get(0);
            goto L17
        L10:
            if (ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0) == null) goto L17;
            if (ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0).photos == null) goto L17;
            r0 = ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0).photos.get(0);
            goto L17
        L15:
            if (ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0).photos.isEmpty() == true) goto L17;
            r0 = ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0).photos.get(ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0).photos.size() - 1);
            goto L17
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void getPositionForScrollProgress(RecyclerListView r8, float r9, int[] r10) {
            int r0 = ActionBar.getCurrentActionBarHeight();
            float r3 = (((int) (Math.ceil(getTotalItemsCount() / ChatAttachAlertPhotoLayout.m9717$$Nest$fgetitemsPerRow(this.this$0)) * r2)) - (r8.getMeasuredHeight() - r0)) * r9;
            float r2 = r8.getChildAt(0).getMeasuredHeight();
            r10[0] = ((int) (r3 / r2)) * ChatAttachAlertPhotoLayout.m9717$$Nest$fgetitemsPerRow(this.this$0);
            int r22 = (((int) (r3 % r2)) + r8.getPaddingTop()) + ((int) (r0 * (1.0f - r9)));
            r10[1] = r22;
            if (r10[0] == 0) goto L5;
            return;
        L5:
            if (r22 >= this.this$0.getListTopPadding()) goto L9;
            r10[1] = this.this$0.getListTopPadding() + r0;
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public float getScrollProgress(RecyclerListView r7) {
            int r0 = ChatAttachAlertPhotoLayout.m9717$$Nest$fgetitemsPerRow(this.this$0);
            int r6 = (int) Math.ceil(this.itemsCount / r0);
            if (r7.getChildCount() != 0) goto L5;
            return 0.0f;
        L5:
            int r3 = r7.getChildAt(0).getMeasuredHeight();
            View r1 = r7.getChildAt(0);
            if (r7.getChildAdapterPosition(r1) >= 0) goto L9;
            return 0.0f;
        L9:
            return Utilities.clamp((((r4 / r0) * r3) - r1.getTop()) / ((r6 * r3) - (r7.getMeasuredHeight() - ActionBar.getCurrentActionBarHeight())), 1.0f, 0.0f);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        public boolean isInFastScroll() {
            return this.isInFastScroll;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            if (this != ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0)) goto L16;
            EmptyTextProgressView r0 = ChatAttachAlertPhotoLayout.m9724$$Nest$fgetprogressView(this.this$0);
            if (getItemCount() != 1) goto L11;
            if (ChatAttachAlertPhotoLayout.m9721$$Nest$fgetnoGalleryPermissions(this.this$0) == true) goto L11;
            if (ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0) != null) goto L11;
        L12:
            int r3 = 0;
        L14:
            r0.setVisibility(r3);
            return;
        L11:
            if (ChatAttachAlertPhotoLayout.m9719$$Nest$fgetmediaEnabled(this.this$0) == false) goto L12;
            r3 = 4;
            goto L14
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
            int r0 = r10.getItemViewType();
            int r1 = 0;
            if (r0 == 0) goto L24;
            if (r0 != 1) goto L6;
            ((PhotoAttachCameraCell) r10.itemView).setItemSize(ChatAttachAlertPhotoLayout.m9716$$Nest$fgetitemSize(this.this$0));
            return;
        L6:
            if (r0 != 3) goto L8;
            PhotoAttachPermissionCell r102 = (PhotoAttachPermissionCell) r10.itemView;
            r102.setItemSize(ChatAttachAlertPhotoLayout.m9716$$Nest$fgetitemSize(this.this$0));
            if (this.needCamera == true) goto L15;
        L18:
            r1 = 1;
        L19:
            r102.setType(r1);
            return;
        L15:
            if (ChatAttachAlertPhotoLayout.m9720$$Nest$fgetnoCameraPermissions(this.this$0) == false) goto L18;
            if (r11 != 0) goto L18;
        L8:
            if (r0 != 7) goto L103;
            ((GalleryEmptyView) r10.itemView).setUseAnEmojiVisible(ChatAttachAlertPhotoLayout.m9727$$Nest$fgetshowAvatarConstructor(this.this$0));
            return;
        L103:
            return;
        L24:
            if (this.hasCameraSpaceRow == false) goto L29;
            if (r11 <= ChatAttachAlertPhotoLayout.m9717$$Nest$fgetitemsPerRow(this.this$0)) goto L29;
            r11 = r11 - 1;
        L29:
            if (this.needCamera == false) goto L38;
            if (ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0) != ChatAttachAlertPhotoLayout.m9713$$Nest$fgetgalleryAlbumEntry(this.this$0)) goto L38;
            if (ExteraConfig.getHideCameraTile() == true) goto L35;
        L36:
            r11 = r11 - 1;
            goto L38
        L35:
            if (ChatAttachAlertPhotoLayout.m9720$$Nest$fgetnoCameraPermissions(this.this$0) == true) goto L36;
        L38:
            if (ChatAttachAlertPhotoLayout.m9727$$Nest$fgetshowAvatarConstructor(this.this$0) == false) goto L40;
            r11 = r11 - 1;
        L40:
            PhotoAttachPhotoCell r3 = (PhotoAttachPhotoCell) r10.itemView;
            PhotoAttachAdapter r103 = ChatAttachAlertPhotoLayout.m9696$$Nest$fgetadapter(this.this$0);
            ChatAttachAlertPhotoLayout r02 = this.this$0;
            if (this != r103) goto L44;
            r3.setItemSize(ChatAttachAlertPhotoLayout.m9716$$Nest$fgetitemSize(r02));
        L48:
            ChatAttachAlert r104 = this.this$0.parentAlert;
            if (r104.avatarPicker == 0) goto L51;
        L56:
            r3.getCheckBox().setVisibility(8);
        L57:
            MediaController.PhotoEntry r4 = ChatAttachAlertPhotoLayout.m9751$$Nest$mgetPhotoEntryAtPosition(this.this$0, r11);
            if (r4 != null) goto L61;
            return;
        L61:
            if (ChatAttachAlertPhotoLayout.selectedPhotos.size() <= 1) goto L63;
            boolean r5 = true;
        L65:
            if (this.needCamera == true) goto L67;
        L73:
            boolean r6 = false;
        L75:
            if (r11 != (getItemCount() - 1)) goto L77;
            boolean r7 = true;
        L78:
            ChatAttachAlert r105 = this.this$0.parentAlert;
            if (r105 != null) goto L81;
        L83:
            boolean r8 = false;
        L84:
            r3.setPhotoEntry(r4, r5, r6, r7, r8);
            ChatAttachAlert r106 = this.this$0.parentAlert;
            if ((r106.baseFragment instanceof ChatActivity) == true) goto L87;
        L89:
            r3.setChecked(-1, ChatAttachAlertPhotoLayout.selectedPhotos.containsKey(Integer.valueOf(r4.imageId)), false);
        L91:
            if (ChatAttachAlertPhotoLayout.m9732$$Nest$fgetvideoEnabled(this.this$0) == true) goto L96;
            if (r4.isVideo == false) goto L96;
            r3.setAlpha(0.3f);
        L101:
            r3.getImageView().setTag(Integer.valueOf(r11));
            r3.setTag(Integer.valueOf(r11));
            return;
        L96:
            if (ChatAttachAlertPhotoLayout.m9723$$Nest$fgetphotoEnabled(this.this$0) == false) goto L98;
        L100:
            r3.setAlpha(1.0f);
            goto L101
        L98:
            if (r4.isVideo == true) goto L100;
            r3.setAlpha(0.3f);
            goto L101
        L87:
            if (r106.allowOrder == false) goto L89;
            r3.setChecked(ChatAttachAlertPhotoLayout.selectedPhotosOrder.indexOf(Integer.valueOf(r4.imageId)), ChatAttachAlertPhotoLayout.selectedPhotos.containsKey(Integer.valueOf(r4.imageId)), false);
            goto L91
        L81:
            if (r105.allowLivePhotos == false) goto L83;
            r8 = true;
            goto L84
        L77:
            r7 = false;
            goto L78
        L67:
            if (ChatAttachAlertPhotoLayout.m9726$$Nest$fgetselectedAlbumEntry(this.this$0) != ChatAttachAlertPhotoLayout.m9713$$Nest$fgetgalleryAlbumEntry(this.this$0)) goto L73;
            if (ExteraConfig.getHideCameraTile() == true) goto L71;
        L72:
            r6 = true;
            goto L75
        L71:
            if (ChatAttachAlertPhotoLayout.m9720$$Nest$fgetnoCameraPermissions(this.this$0) == false) goto L73;
        L63:
            r5 = false;
            goto L65
        L51:
            if (r104.storyMediaPicker == true) goto L56;
            if (r104.isPollAttach == true) goto L56;
            r3.getCheckBox().setVisibility(0);
            goto L57
        L44:
            if (ChatAttachAlertPhotoLayout.m9703$$Nest$fgetcameraPhotoLayoutManager(r02).getOrientation() != 1) goto L46;
            boolean r107 = true;
        L47:
            r3.setIsVertical(r107);
            goto L48
        L46:
            r107 = false;
            goto L47
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r11, int r12) {
            if (r12 == 0) goto L31;
            if (r12 == 1) goto L29;
            if (r12 == 2) goto L27;
            if (r12 == 4) goto L25;
            if (r12 == 5) goto L23;
            if (r12 != 7) goto L15;
            Context r122 = this.mContext;
            ChatAttachAlert r0 = this.this$0.parentAlert;
            GalleryEmptyView r112 = new GalleryEmptyView(r122, r0.currentAccount, r0.forUser);
            r112.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(400.0f)));
            r112.setGravity(17);
            r112.isClickable();
            r112.doOnCameraAccess(new ChatAttachAlertPhotoLayout$PhotoAttachAdapter$$ExternalSyntheticLambda0(this.this$0));
            r112.doOnGalleryAccessClick(new ChatAttachAlertPhotoLayout$PhotoAttachAdapter$$ExternalSyntheticLambda1(this.this$0));
            r112.doOnEmojiButton(new ChatAttachAlertPhotoLayout$PhotoAttachAdapter$$ExternalSyntheticLambda2(this));
            return new RecyclerListView.Holder(r112);
        L15:
            if (r12 != 8) goto L17;
            FrameLayout r123 = new FrameLayout(this.mContext);
            r123.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(56.0f)));
            ButtonWithCounterView r1 = new ButtonWithCounterView(this.mContext, this.this$0.resourcesProvider);
            r1.setUseWrapContent(true);
            r1.setRound();
            r1.setPadding(AndroidUtilities.dp(28.0f), 0, AndroidUtilities.dp(28.0f), 0);
            SpannableStringBuilder r10 = new SpannableStringBuilder("c");
            r10.setSpan(new ColoredImageSpan(R.drawable.camera), 0, 1, 33);
            r10.append("  ").append(LocaleController.getString(R.string.GalleryAccessAllowAccessCamera));
            r1.setText(r10, false);
            r123.addView(r1, LayoutHelper.createFrame(-2, 44.0f, 81, 10.0f, 0.0f, 10.0f, 12.0f));
            return new RecyclerListView.Holder(r123);
        L17:
            return new RecyclerListView.Holder(new PhotoAttachPermissionCell(this.mContext, this.this$0.resourcesProvider));
        L23:
            return new RecyclerListView.Holder(new View(this.mContext));
        L25:
            return new RecyclerListView.Holder(new AnonymousClass3(this, this.mContext, this.this$0.parentAlert.forUser));
        L27:
            return new RecyclerListView.Holder(new AnonymousClass2(this, this.mContext));
        L29:
            return new RecyclerListView.Holder(new PhotoAttachCameraCell(this.mContext));
        L31:
            if (this.viewsCache.isEmpty() == true) goto L35;
            RecyclerListView.Holder r124 = this.viewsCache.get(0);
            this.viewsCache.remove(0);
            return r124;
        L35:
            return createHolder();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void onFinishFastScroll(RecyclerListView r4) {
            super.onFinishFastScroll(r4);
            int r0 = 0;
            this.isInFastScroll = false;
            if (r4 == null) goto L10;
            int r3 = r4.getChildCount();
        L5:
            if (r0 >= r3) goto L14;
            View r1 = r4.getChildAt(r0);
            r1.invalidate();
            if ((r1 instanceof PhotoAttachPhotoCell) == false) goto L9;
            ((PhotoAttachPhotoCell) r1).imageView.invalidate();
        L9:
            r0 = r0 + 1;
            goto L5
        L14:
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void onStartFastScroll() {
            super.onStartFastScroll();
            this.isInFastScroll = true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r1) {
            if ((r1.itemView instanceof PhotoAttachCameraCell) == false) goto L6;
            ChatAttachAlertPhotoLayout.m9706$$Nest$fgetcameraViewItemDecoration(this.this$0).updateBitmap();
            return;
        }
    }

    /* renamed from: $r8$lambda$3euXTjXQNfrQ-s-eS_2bNZduyQQ, reason: not valid java name */
    public static /* synthetic */ void m9682$r8$lambda$3euXTjXQNfrQseS_2bNZduyQQ(ChatAttachAlertPhotoLayout r0, Long r1) {
        r0.lambda$onMenuItemClick$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$72nP2Ex6HwTllcSymNJCEI6jG14(ChatAttachAlertPhotoLayout r0) {
        r0.lambda$onPreMeasure$30();
    }

    public static /* synthetic */ void $r8$lambda$8Ye_0IT4e3vimiHICjWzl3VsWrg(ChatAttachAlertPhotoLayout r0, boolean r1) {
        r0.lambda$onMenuItemClick$25(r1);
    }

    /* renamed from: $r8$lambda$9DDEwr9Sm2MvafG-zRPwaouRrms, reason: not valid java name */
    public static /* synthetic */ void m9683$r8$lambda$9DDEwr9Sm2MvafGzRPwaouRrms(ChatAttachAlertPhotoLayout r0, boolean r1, Theme.ResourcesProvider r2, View r3, int r4, float r5, float r6) {
        r0.lambda$new$3(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$AU-IMk3LpjOl63bBUfavYacv60U, reason: not valid java name */
    public static /* synthetic */ void m9684$r8$lambda$AUIMk3LpjOl63bBUfavYacv60U(ChatAttachAlertPhotoLayout r0, AvatarConstructorFragment r1, AvatarConstructorFragment.BackgroundGradient r2, long r3, TLRPC.Document r5, AvatarConstructorFragment.PreviewView r6) {
        r0.lambda$showAvatarConstructorFragment$10(r1, r2, r3, r5, r6);
    }

    /* renamed from: $r8$lambda$BH5VEaOqTNXlufm9ws-DERQmtbE, reason: not valid java name */
    public static /* synthetic */ void m9685$r8$lambda$BH5VEaOqTNXlufm9wsDERQmtbE(ChatAttachAlertPhotoLayout r0, int r1, BaseFragment r2, ArrayList r3, int r4, ChatActivity r5) {
        r0.lambda$new$2(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$CKQumWRRWo6CKf_6R3B2Z-ksHxo, reason: not valid java name */
    public static /* synthetic */ void m9686$r8$lambda$CKQumWRRWo6CKf_6R3B2ZksHxo(ChatAttachAlertPhotoLayout r0, boolean r1) {
        r0.lambda$onMenuItemClick$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$CgXEdtFwzBIVyapTg9P1ATwwR1E(ChatAttachAlertPhotoLayout r0, MediaController.PhotoEntry r1, boolean r2, View r3) {
        r0.lambda$setCurrentSpoilerVisible$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$D1JvruG4gM6ILqznZl2OZg_A8jQ(ChatAttachAlertPhotoLayout r0, Long r1) {
        r0.lambda$onMenuItemClick$20(r1);
    }

    /* renamed from: $r8$lambda$GQg-7L31KvldWhCi96qPcDzTIG8, reason: not valid java name */
    public static /* synthetic */ int m9687$r8$lambda$GQg7L31KvldWhCi96qPcDzTIG8(ArrayList r2, MediaController.AlbumEntry r3, MediaController.AlbumEntry r4) {
        int r0 = r3.bucketId;
        if (r0 == 0) goto L5;
    L7:
        if (r0 != 0) goto L9;
    L11:
        int r32 = r2.indexOf(r3);
        int r22 = r2.indexOf(r4);
        if (r32 <= r22) goto L15;
        return 1;
    L15:
        if (r32 >= r22) goto L18;
        return -1;
    L18:
        return 0;
    L9:
        if (r4.bucketId != 0) goto L11;
        return 1;
    L5:
        if (r4.bucketId == 0) goto L7;
        return -1;
    }

    public static /* synthetic */ void $r8$lambda$GquF2NvxHXoNTnSlEzQQPPsrWcY(ChatAttachAlertPhotoLayout r0) {
        r0.lambda$showCamera$16();
    }

    /* renamed from: $r8$lambda$Hyf4Uls-OlM2PTV0ElHfmvtmDto, reason: not valid java name */
    public static /* synthetic */ void m9688$r8$lambda$Hyf4UlsOlM2PTV0ElHfmvtmDto(ChatAttachAlertPhotoLayout r0, float r1) {
        r0.lambda$new$6(r1);
    }

    /* renamed from: $r8$lambda$LkpA_2BOvbk17-wsOcc-aC_zKHQ, reason: not valid java name */
    public static /* synthetic */ void m9689$r8$lambda$LkpA_2BOvbk17wsOccaC_zKHQ(List r1, boolean r2, View r3) {
        if ((r3 instanceof PhotoAttachPhotoCell) == false) goto L13;
        PhotoAttachPhotoCell r32 = (PhotoAttachPhotoCell) r3;
        MediaController.PhotoEntry r0 = r32.getPhotoEntry();
        if (r0 != null) goto L7;
    L10:
        boolean r12 = false;
    L11:
        r32.setHighQuality(r12);
        return;
    L7:
        if (r1.contains(Integer.valueOf(r0.imageId)) == false) goto L10;
        if (r2 == false) goto L10;
        r12 = true;
        goto L11
    }

    /* renamed from: $r8$lambda$NTVFM-XHrJ3qObApNXs6JjxjOPg, reason: not valid java name */
    public static /* synthetic */ void m9690$r8$lambda$NTVFMXHrJ3qObApNXs6JjxjOPg(ChatAttachAlertPhotoLayout r0, FrameLayout.LayoutParams r1) {
        r0.lambda$applyCameraViewPosition$18(r1);
    }

    /* renamed from: $r8$lambda$NeszZNQa3cX0B-osKNJ4VS-uang, reason: not valid java name */
    public static /* synthetic */ void m9691$r8$lambda$NeszZNQa3cX0BosKNJ4VSuang(ChatAttachAlertPhotoLayout r0) {
        r0.lambda$showZoomControls$15();
    }

    public static /* synthetic */ boolean $r8$lambda$OVRP_zFYXE2yLAjfDrFtybrje1Y(ChatAttachAlertPhotoLayout r0, View r1, int r2) {
        return r0.lambda$new$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$UhQaUVwNl6KHsOTMExryYiZoGbE(ChatAttachAlertPhotoLayout r0) {
        r0.lambda$hideCamera$17();
    }

    /* renamed from: $r8$lambda$UnF-J1cPq7uJ6LQi-z2jc5aFYC8, reason: not valid java name */
    public static /* synthetic */ void m9692$r8$lambda$UnFJ1cPq7uJ6LQiz2jc5aFYC8(ChatAttachAlertPhotoLayout r0, int r1, View r2) {
        r0.lambda$updateAlbumsDropDown$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$VkbZQzNZGQHKFTS2QZwtbVxpK3w(ChatAttachAlertPhotoLayout r0, boolean r1, int r2, int r3) {
        r0.lambda$onMenuItemClick$21(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$VklzUSeZPvZpHOoLP0Rq5PldOfI(View r0, int r1) {
        if ((r0 instanceof PhotoAttachPhotoCell) == false) goto L6;
        ((PhotoAttachPhotoCell) r0).callDelegate();
        return;
    }

    /* renamed from: $r8$lambda$YYch0eRH_sS6vchNFcP-oceILpg, reason: not valid java name */
    public static /* synthetic */ void m9693$r8$lambda$YYch0eRH_sS6vchNFcPoceILpg(ChatAttachAlertPhotoLayout r0, View r1) {
        r0.lambda$new$7(r1);
    }

    /* renamed from: $r8$lambda$Zybd87d3UpSKL-rzBOzsGXt-66A, reason: not valid java name */
    public static /* synthetic */ void m9694$r8$lambda$Zybd87d3UpSKLrzBOzsGXt66A(ChatAttachAlertPhotoLayout r0, ChatAttachAlert.AttachAlertLayout r1) {
        r0.lambda$onShow$28(r1);
    }

    public static /* synthetic */ void $r8$lambda$aebbyfoc8e0MjhvL8heaLOuE_I0(ChatAttachAlertPhotoLayout r0, View r1) {
        r0.lambda$new$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$fWe3IniolEUY1TgwOud6G8oZxxA(ChatAttachAlertPhotoLayout r0) {
        r0.lambda$showZoomControls$14();
    }

    public static /* synthetic */ void $r8$lambda$j7zm8n531tbC7FnGcqehqgZvv6o(View r5) {
        if ((r5 instanceof PhotoAttachPhotoCell) == false) goto L25;
        PhotoAttachPhotoCell r52 = (PhotoAttachPhotoCell) r5;
        boolean r1 = false;
        if (r52.getPhotoEntry() != null) goto L7;
    L9:
        boolean r0 = false;
    L10:
        r52.setHasSpoiler(r0, Float.valueOf(250.0f));
        if (r52.getPhotoEntry() != null) goto L13;
    L15:
        boolean r02 = false;
    L16:
        r52.setHighQuality(r02);
        if (r52.getPhotoEntry() == null) goto L19;
        long r3 = r52.getPhotoEntry().starsAmount;
    L21:
        if (selectedPhotos.size() <= 1) goto L23;
        r1 = true;
    L23:
        r52.setStarsPrice(r3, r1);
        return;
    L19:
        r3 = 0;
        goto L21
    L13:
        if (r52.getPhotoEntry().isHighQuality() == false) goto L15;
        r02 = true;
        goto L16
    L7:
        if (r52.getPhotoEntry().hasSpoiler == false) goto L9;
        r0 = true;
        goto L10
    }

    public static /* synthetic */ void $r8$lambda$kyh7rEFRW8ZIB_Uu9CopvTNjPqI(List r1, boolean r2, View r3) {
        if ((r3 instanceof PhotoAttachPhotoCell) == false) goto L13;
        PhotoAttachPhotoCell r32 = (PhotoAttachPhotoCell) r3;
        MediaController.PhotoEntry r0 = r32.getPhotoEntry();
        if (r0 != null) goto L7;
    L10:
        boolean r12 = false;
    L11:
        r32.setHasSpoiler(r12);
        return;
    L7:
        if (r1.contains(Integer.valueOf(r0.imageId)) == false) goto L10;
        if (r2 == false) goto L10;
        r12 = true;
        goto L11
    }

    public static /* synthetic */ void $r8$lambda$l4W3dk0VHKBXzPM1ZgK2Xdx0sms(ChatAttachAlertPhotoLayout r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$qXM7PFZroQ_lklJIbp-7HUzdsoI, reason: not valid java name */
    public static /* synthetic */ void m9695$r8$lambda$qXM7PFZroQ_lklJIbp7HUzdsoI(ChatAttachAlertPhotoLayout r0, Long r1, Runnable r2) {
        r0.lambda$onMenuItemClick$27(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sD_QU3ik2Vl4yi144jym6wdREj8(ChatAttachAlertPhotoLayout r0) {
        r0.lambda$onHide$29();
    }

    public static /* synthetic */ void $r8$lambda$siyRT8aH5cLUKA5jtBoDDo4uuPY(ChatAttachAlertPhotoLayout r0, boolean r1, int r2, int r3) {
        r0.lambda$onMenuItemClick$19(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$yXWuVoXjwtHqO2sCUwgBHf8yioM(ChatAttachAlertPhotoLayout r0, View r1) {
        r0.lambda$new$8(r1);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoAttachAdapter m9696$$Nest$fgetadapter(ChatAttachAlertPhotoLayout r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetalertOnlyOnce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9697$$Nest$fgetalertOnlyOnce(ChatAttachAlertPhotoLayout r0) {
        return r0.alertOnlyOnce;
    }

    /* renamed from: -$$Nest$fgetcameraAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9698$$Nest$fgetcameraAnimationInProgress(ChatAttachAlertPhotoLayout r0) {
        return r0.cameraAnimationInProgress;
    }

    /* renamed from: -$$Nest$fgetcameraAttachAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoAttachAdapter m9699$$Nest$fgetcameraAttachAdapter(ChatAttachAlertPhotoLayout r0) {
        return r0.cameraAttachAdapter;
    }

    /* renamed from: -$$Nest$fgetcameraInitAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m9700$$Nest$fgetcameraInitAnimation(ChatAttachAlertPhotoLayout r0) {
        return r0.cameraInitAnimation;
    }

    /* renamed from: -$$Nest$fgetcameraOpenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9701$$Nest$fgetcameraOpenProgress(ChatAttachAlertPhotoLayout r0) {
        return r0.cameraOpenProgress;
    }

    /* renamed from: -$$Nest$fgetcameraPanel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m9702$$Nest$fgetcameraPanel(ChatAttachAlertPhotoLayout r0) {
        return r0.cameraPanel;
    }

    /* renamed from: -$$Nest$fgetcameraPhotoLayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m9703$$Nest$fgetcameraPhotoLayoutManager(ChatAttachAlertPhotoLayout r0) {
        return r0.cameraPhotoLayoutManager;
    }

    /* renamed from: -$$Nest$fgetcameraPhotoRecyclerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m9704$$Nest$fgetcameraPhotoRecyclerView(ChatAttachAlertPhotoLayout r0) {
        return r0.cameraPhotoRecyclerView;
    }

    /* renamed from: -$$Nest$fgetcameraPhotoRecyclerViewIgnoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9705$$Nest$fgetcameraPhotoRecyclerViewIgnoreLayout(ChatAttachAlertPhotoLayout r0) {
        return r0.cameraPhotoRecyclerViewIgnoreLayout;
    }

    /* renamed from: -$$Nest$fgetcameraViewItemDecoration, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraViewItemDecoration m9706$$Nest$fgetcameraViewItemDecoration(ChatAttachAlertPhotoLayout r0) {
        return r0.cameraViewItemDecoration;
    }

    /* renamed from: -$$Nest$fgetcameraViewOffsetX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9707$$Nest$fgetcameraViewOffsetX(ChatAttachAlertPhotoLayout r0) {
        return r0.cameraViewOffsetX;
    }

    /* renamed from: -$$Nest$fgetcameraViewOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9708$$Nest$fgetcameraViewOffsetY(ChatAttachAlertPhotoLayout r0) {
        return r0.cameraViewOffsetY;
    }

    /* renamed from: -$$Nest$fgetcancelTakingPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9709$$Nest$fgetcancelTakingPhotos(ChatAttachAlertPhotoLayout r0) {
        return r0.cancelTakingPhotos;
    }

    /* renamed from: -$$Nest$fgetcounterTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m9710$$Nest$fgetcounterTextView(ChatAttachAlertPhotoLayout r0) {
        return r0.counterTextView;
    }

    /* renamed from: -$$Nest$fgetcurrentPanTranslationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9711$$Nest$fgetcurrentPanTranslationY(ChatAttachAlertPhotoLayout r0) {
        return r0.currentPanTranslationY;
    }

    /* renamed from: -$$Nest$fgetflashModeButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView[] m9712$$Nest$fgetflashModeButton(ChatAttachAlertPhotoLayout r0) {
        return r0.flashModeButton;
    }

    /* renamed from: -$$Nest$fgetgalleryAlbumEntry, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MediaController.AlbumEntry m9713$$Nest$fgetgalleryAlbumEntry(ChatAttachAlertPhotoLayout r0) {
        return r0.galleryAlbumEntry;
    }

    /* renamed from: -$$Nest$fgetgridExtraSpace, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9714$$Nest$fgetgridExtraSpace(ChatAttachAlertPhotoLayout r0) {
        return r0.gridExtraSpace;
    }

    /* renamed from: -$$Nest$fgetisHidden, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9715$$Nest$fgetisHidden(ChatAttachAlertPhotoLayout r0) {
        return r0.isHidden;
    }

    /* renamed from: -$$Nest$fgetitemSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9716$$Nest$fgetitemSize(ChatAttachAlertPhotoLayout r0) {
        return r0.itemSize;
    }

    /* renamed from: -$$Nest$fgetitemsPerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9717$$Nest$fgetitemsPerRow(ChatAttachAlertPhotoLayout r0) {
        return r0.itemsPerRow;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GridLayoutManager m9718$$Nest$fgetlayoutManager(ChatAttachAlertPhotoLayout r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetmediaEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9719$$Nest$fgetmediaEnabled(ChatAttachAlertPhotoLayout r0) {
        return r0.mediaEnabled;
    }

    /* renamed from: -$$Nest$fgetnoCameraPermissions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9720$$Nest$fgetnoCameraPermissions(ChatAttachAlertPhotoLayout r0) {
        return r0.noCameraPermissions;
    }

    /* renamed from: -$$Nest$fgetnoGalleryPermissions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9721$$Nest$fgetnoGalleryPermissions(ChatAttachAlertPhotoLayout r0) {
        return r0.noGalleryPermissions;
    }

    /* renamed from: -$$Nest$fgetnotificationsLocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m9722$$Nest$fgetnotificationsLocker(ChatAttachAlertPhotoLayout r0) {
        return r0.notificationsLocker;
    }

    /* renamed from: -$$Nest$fgetphotoEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9723$$Nest$fgetphotoEnabled(ChatAttachAlertPhotoLayout r0) {
        return r0.photoEnabled;
    }

    /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmptyTextProgressView m9724$$Nest$fgetprogressView(ChatAttachAlertPhotoLayout r0) {
        return r0.progressView;
    }

    /* renamed from: -$$Nest$fgetrecordTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m9725$$Nest$fgetrecordTime(ChatAttachAlertPhotoLayout r0) {
        return r0.recordTime;
    }

    /* renamed from: -$$Nest$fgetselectedAlbumEntry, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MediaController.AlbumEntry m9726$$Nest$fgetselectedAlbumEntry(ChatAttachAlertPhotoLayout r0) {
        return r0.selectedAlbumEntry;
    }

    /* renamed from: -$$Nest$fgetshowAvatarConstructor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9727$$Nest$fgetshowAvatarConstructor(ChatAttachAlertPhotoLayout r0) {
        return r0.showAvatarConstructor;
    }

    /* renamed from: -$$Nest$fgetshutterButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ShutterButton m9728$$Nest$fgetshutterButton(ChatAttachAlertPhotoLayout r0) {
        return r0.shutterButton;
    }

    /* renamed from: -$$Nest$fgetswitchCameraButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m9729$$Nest$fgetswitchCameraButton(ChatAttachAlertPhotoLayout r0) {
        return r0.switchCameraButton;
    }

    /* renamed from: -$$Nest$fgettakingPhoto, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9730$$Nest$fgettakingPhoto(ChatAttachAlertPhotoLayout r0) {
        return r0.takingPhoto;
    }

    /* renamed from: -$$Nest$fgettooltipTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m9731$$Nest$fgettooltipTextView(ChatAttachAlertPhotoLayout r0) {
        return r0.tooltipTextView;
    }

    /* renamed from: -$$Nest$fgetvideoEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9732$$Nest$fgetvideoEnabled(ChatAttachAlertPhotoLayout r0) {
        return r0.videoEnabled;
    }

    /* renamed from: -$$Nest$fgetvideoRecordRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m9733$$Nest$fgetvideoRecordRunnable(ChatAttachAlertPhotoLayout r0) {
        return r0.videoRecordRunnable;
    }

    /* renamed from: -$$Nest$fgetvideoRecordTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9734$$Nest$fgetvideoRecordTime(ChatAttachAlertPhotoLayout r0) {
        return r0.videoRecordTime;
    }

    /* renamed from: -$$Nest$fgetzoomControlView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ZoomControlView m9735$$Nest$fgetzoomControlView(ChatAttachAlertPhotoLayout r0) {
        return r0.zoomControlView;
    }

    /* renamed from: -$$Nest$fputalertOnlyOnce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9736$$Nest$fputalertOnlyOnce(ChatAttachAlertPhotoLayout r0, int r1) {
        r0.alertOnlyOnce = r1;
    }

    /* renamed from: -$$Nest$fputcameraAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9737$$Nest$fputcameraAnimationInProgress(ChatAttachAlertPhotoLayout r0, boolean r1) {
        r0.cameraAnimationInProgress = r1;
    }

    /* renamed from: -$$Nest$fputcameraInitAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9738$$Nest$fputcameraInitAnimation(ChatAttachAlertPhotoLayout r0, AnimatorSet r1) {
        r0.cameraInitAnimation = r1;
    }

    /* renamed from: -$$Nest$fputcameraZoom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9739$$Nest$fputcameraZoom(ChatAttachAlertPhotoLayout r0, float r1) {
        r0.cameraZoom = r1;
    }

    /* renamed from: -$$Nest$fputcanSaveCameraPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9740$$Nest$fputcanSaveCameraPreview(ChatAttachAlertPhotoLayout r0, boolean r1) {
        r0.canSaveCameraPreview = r1;
    }

    /* renamed from: -$$Nest$fputcancelTakingPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9741$$Nest$fputcancelTakingPhotos(ChatAttachAlertPhotoLayout r0, boolean r1) {
        r0.cancelTakingPhotos = r1;
    }

    /* renamed from: -$$Nest$fputflashAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9742$$Nest$fputflashAnimationInProgress(ChatAttachAlertPhotoLayout r0, boolean r1) {
        r0.flashAnimationInProgress = r1;
    }

    /* renamed from: -$$Nest$fputrequestingPermissions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9743$$Nest$fputrequestingPermissions(ChatAttachAlertPhotoLayout r0, boolean r1) {
        r0.requestingPermissions = r1;
    }

    /* renamed from: -$$Nest$fputtakingPhoto, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9744$$Nest$fputtakingPhoto(ChatAttachAlertPhotoLayout r0, boolean r1) {
        r0.takingPhoto = r1;
    }

    /* renamed from: -$$Nest$fputvideoRecordRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9745$$Nest$fputvideoRecordRunnable(ChatAttachAlertPhotoLayout r0, Runnable r1) {
        r0.videoRecordRunnable = r1;
    }

    /* renamed from: -$$Nest$fputvideoRecordTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9746$$Nest$fputvideoRecordTime(ChatAttachAlertPhotoLayout r0, int r1) {
        r0.videoRecordTime = r1;
    }

    /* renamed from: -$$Nest$fputzoomControlAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9747$$Nest$fputzoomControlAnimation(ChatAttachAlertPhotoLayout r0, AnimatorSet r1) {
        r0.zoomControlAnimation = r1;
    }

    /* renamed from: -$$Nest$maddToSelectedPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9748$$Nest$maddToSelectedPhotos(ChatAttachAlertPhotoLayout r0, MediaController.PhotoEntry r1, int r2) {
        return r0.addToSelectedPhotos(r1, r2);
    }

    /* renamed from: -$$Nest$mcheckSendMediaEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9749$$Nest$mcheckSendMediaEnabled(ChatAttachAlertPhotoLayout r0, MediaController.PhotoEntry r1) {
        return r0.checkSendMediaEnabled(r1);
    }

    /* renamed from: -$$Nest$mgetCellForIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoAttachPhotoCell m9750$$Nest$mgetCellForIndex(ChatAttachAlertPhotoLayout r0, int r1) {
        return r0.getCellForIndex(r1);
    }

    /* renamed from: -$$Nest$mgetPhotoEntryAtPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MediaController.PhotoEntry m9751$$Nest$mgetPhotoEntryAtPosition(ChatAttachAlertPhotoLayout r0, int r1) {
        return r0.getPhotoEntryAtPosition(r1);
    }

    /* renamed from: -$$Nest$mgetTopScrollOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9752$$Nest$mgetTopScrollOffset(ChatAttachAlertPhotoLayout r0) {
        return r0.getTopScrollOffset();
    }

    /* renamed from: -$$Nest$mmaxCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9753$$Nest$mmaxCount(ChatAttachAlertPhotoLayout r0) {
        return r0.maxCount();
    }

    /* renamed from: -$$Nest$monPhotoEditModeChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9754$$Nest$monPhotoEditModeChanged(ChatAttachAlertPhotoLayout r0, boolean r1) {
        r0.onPhotoEditModeChanged(r1);
    }

    /* renamed from: -$$Nest$mopenCameraWithPermissionCheck, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9755$$Nest$mopenCameraWithPermissionCheck(ChatAttachAlertPhotoLayout r0) {
        r0.openCameraWithPermissionCheck();
    }

    /* renamed from: -$$Nest$mpauseCameraPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9756$$Nest$mpauseCameraPreview(ChatAttachAlertPhotoLayout r0) {
        r0.pauseCameraPreview();
    }

    /* renamed from: -$$Nest$mrequestGalleryPermission, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9757$$Nest$mrequestGalleryPermission(ChatAttachAlertPhotoLayout r0) {
        r0.requestGalleryPermission();
    }

    /* renamed from: -$$Nest$mresetRecordState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9758$$Nest$mresetRecordState(ChatAttachAlertPhotoLayout r0) {
        r0.resetRecordState();
    }

    /* renamed from: -$$Nest$mresumeCameraPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9759$$Nest$mresumeCameraPreview(ChatAttachAlertPhotoLayout r0) {
        r0.resumeCameraPreview();
    }

    /* renamed from: -$$Nest$msetCurrentSpoilerVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9760$$Nest$msetCurrentSpoilerVisible(ChatAttachAlertPhotoLayout r0, int r1, boolean r2) {
        r0.setCurrentSpoilerVisible(r1, r2);
    }

    /* renamed from: -$$Nest$mshowZoomControls, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9761$$Nest$mshowZoomControls(ChatAttachAlertPhotoLayout r0, boolean r1, boolean r2) {
        r0.showZoomControls(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateCells, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9762$$Nest$mupdateCells(ChatAttachAlertPhotoLayout r0) {
        r0.updateCells();
    }

    /* renamed from: -$$Nest$sfgetcameraPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m9763$$Nest$sfgetcameraPhotos() {
        return cameraPhotos;
    }

    /* renamed from: -$$Nest$sfgetmediaFromExternalCamera, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9764$$Nest$sfgetmediaFromExternalCamera() {
        return mediaFromExternalCamera;
    }

    /* renamed from: -$$Nest$sfputmediaFromExternalCamera, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9765$$Nest$sfputmediaFromExternalCamera(boolean r0) {
        mediaFromExternalCamera = r0;
    }

    static {
        cameraPhotos = new ArrayList();
        selectedPhotos = new HashMap();
        selectedPhotosOrder = new ArrayList();
        lastImageId = -1;
    }

    public ChatAttachAlertPhotoLayout(ChatAttachAlert r34, Context r35, boolean r36, boolean r37, Theme.ResourcesProvider r38) {
        super(r34, r35, r38);
        this.flashModeButton = new ImageView[2];
        this.cameraViewLocation = new float[2];
        this.viewPosition = new int[2];
        this.animateCameraValues = new int[5];
        this.interpolator = new DecelerateInterpolator(1.5f);
        this.isCameraFrontfaceBeforeEnteringEditMode = null;
        this.hitRect = new Rect();
        int r3 = AndroidUtilities.dp(80.0f);
        this.itemSize = r3;
        this.lastItemSize = r3;
        this.itemsPerRow = 3;
        this.loading = true;
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.photoViewerProvider = new AnonymousClass1(this);
        this.currentItemTop = 0;
        this.forceDarkTheme = r36;
        this.needCamera = r37;
        BottomSheet.ContainerView r15 = r34.getContainer();
        ChatAttachAlert r0 = this.parentAlert;
        if (r0.avatarPicker == 0) goto L5;
        boolean r32 = true;
    L6:
        this.showAvatarConstructor = r32;
        AnonymousClass2 r02 = new AnonymousClass2(this, r35, r0.actionBar.createMenu(), 0, 0, r38);
        this.dropDownContainer = r02;
        r02.setSubMenuOpenSide(1);
        FrameLayout.LayoutParams r1 = LayoutHelper.createFrame(-2, -1.0f, 51, 60.0f, 0.0f, 40.0f, 0.0f);
        r1.topMargin = AndroidUtilities.statusBarHeight;
        this.parentAlert.actionBar.addView(this.dropDownContainer, 0, r1);
        this.dropDownContainer.setOnClickListener(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda8(this));
        TextView r12 = new TextView(r35);
        this.dropDown = r12;
        r12.setImportantForAccessibility(2);
        this.dropDown.setGravity(3);
        this.dropDown.setSingleLine(true);
        this.dropDown.setLines(1);
        this.dropDown.setMaxLines(1);
        this.dropDown.setEllipsize(TextUtils.TruncateAt.END);
        TextView r13 = this.dropDown;
        int r33 = Theme.key_dialogTextBlack;
        r13.setTextColor(getThemedColor(r33));
        this.dropDown.setText(LocaleController.getString(R.string.ChatGallery));
        this.dropDown.setTypeface(AndroidUtilities.bold());
        Drawable r14 = r35.getResources().getDrawable(R.drawable.ic_arrow_drop_down).mutate();
        this.dropDownDrawable = r14;
        int r39 = getThemedColor(r33);
        PorterDuff.Mode r5 = PorterDuff.Mode.MULTIPLY;
        r14.setColorFilter(new PorterDuffColorFilter(r39, r5));
        this.dropDown.setPadding(0, 0, AndroidUtilities.dp(10.0f), 0);
        this.dropDownContainer.addView(this.dropDown, LayoutHelper.createFrame(-2, -2.0f, 16, 16.0f, 0.0f, 0.0f, 0.0f));
        checkCamera(false);
        MessagePreviewView.ToggleButton r03 = new MessagePreviewView.ToggleButton(r35, R.raw.position_below, LocaleController.getString(R.string.CaptionAbove), R.raw.position_above, LocaleController.getString(R.string.CaptionBelow), r38);
        this.captionItem = r03;
        r03.setState(!this.parentAlert.captionAbove, false);
        this.previewItem = this.parentAlert.selectedMenuItem.addSubItem(7, R.drawable.msg_view_file, LocaleController.getString(R.string.AttachMediaPreviewButton));
        this.parentAlert.selectedMenuItem.addColoredGap(5);
        this.parentAlert.selectedMenuItem.addSubItem(4, R.drawable.msg_openin, LocaleController.getString(R.string.OpenInExternalApp));
        this.compressItem = this.parentAlert.selectedMenuItem.addSubItem(1, R.drawable.msg_filehq, LocaleController.getString(R.string.SendWithoutCompression));
        this.parentAlert.selectedMenuItem.addSubItem(0, R.drawable.msg_ungroup, LocaleController.getString(R.string.SendWithoutGrouping));
        this.parentAlert.selectedMenuItem.addColoredGap(6);
        this.spoilerItem = this.parentAlert.selectedMenuItem.addSubItem(3, R.drawable.msg_spoiler, LocaleController.getString(R.string.EnablePhotoSpoiler));
        this.qualityItem = this.parentAlert.selectedMenuItem.addSubItem(2, R.drawable.menu_quality_hd, LocaleController.getString(R.string.SendInHighQuality));
        this.parentAlert.selectedMenuItem.addSubItem(8, this.captionItem);
        this.starsItem = this.parentAlert.selectedMenuItem.addSubItem(9, R.drawable.menu_feature_paid, LocaleController.getString(R.string.PaidMediaButton));
        this.parentAlert.selectedMenuItem.setFitSubItems(true);
        AnonymousClass3 r04 = new AnonymousClass3(this, r35, r38);
        this.gridView = r04;
        r04.setFastScrollEnabled(1);
        this.gridView.setFastScrollVisible(true);
        this.gridView.getFastScroll().setAlpha(0.0f);
        this.gridView.getFastScroll().usePadding = false;
        this.gridView.getFastScroll().topOffset = ActionBar.getCurrentActionBarHeight();
        RecyclerListView r05 = this.gridView;
        PhotoAttachAdapter r52 = new PhotoAttachAdapter(this, r35, r37);
        this.adapter = r52;
        r05.setAdapter(r52);
        RecyclerListView r06 = this.gridView;
        CameraViewItemDecoration r53 = new CameraViewItemDecoration(this, r06);
        this.cameraViewItemDecoration = r53;
        r06.addItemDecoration(r53);
        this.adapter.createCache();
        this.gridView.setClipToPadding(false);
        this.gridView.setItemAnimator(null);
        this.gridView.setLayoutAnimation(null);
        this.gridView.setVerticalScrollBarEnabled(false);
        this.gridView.setGlowColor(getThemedColor(Theme.key_dialogScrollGlow));
        addView(this.gridView, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 0.0f, -48.0f));
        this.gridView.setOnScrollListener(new AnonymousClass4(this));
        AnonymousClass5 r07 = new AnonymousClass5(this, r35, this.itemSize);
        this.layoutManager = r07;
        r07.setSpanSizeLookup(new AnonymousClass6(this));
        this.gridView.setLayoutManager(this.layoutManager);
        this.gridView.setInstantClick(true);
        this.gridView.setOnItemClickListener(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda9(this, r37, r38));
        this.gridView.setOnItemLongClickListener(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda10(this));
        RecyclerViewItemRangeSelector r08 = new RecyclerViewItemRangeSelector(new AnonymousClass7(this));
        this.itemRangeSelector = r08;
        this.gridView.addOnItemTouchListener(r08);
        RecyclerListView r09 = this.gridView;
        this.iBlur3Capture = r09;
        this.iBlur3CaptureView = r09;
        this.occupyNavigationBar = true;
        EmptyTextProgressView r010 = new EmptyTextProgressView(r35, null, r38);
        this.progressView = r010;
        r010.setText(LocaleController.getString(R.string.NoPhotos));
        this.progressView.setOnTouchListener(null);
        this.progressView.setTextSize(16);
        addView(this.progressView, LayoutHelper.createFrame(-1, -2.0f));
        boolean r011 = this.loading;
        EmptyTextProgressView r54 = this.progressView;
        if (r011 == false) goto L9;
        r54.showProgress();
    L10:
        Paint r012 = new Paint(1);
        r012.setColor(-2468275);
        AnonymousClass8 r55 = new AnonymousClass8(this, r35, r012);
        this.recordTime = r55;
        AndroidUtilities.updateViewVisibilityAnimated(r55, false, 1.0f, false);
        this.recordTime.setBackgroundResource(R.drawable.system);
        this.recordTime.getBackground().setColorFilter(new PorterDuffColorFilter(1711276032, r5));
        this.recordTime.setTextSize(1, 15.0f);
        this.recordTime.setTypeface(AndroidUtilities.bold());
        this.recordTime.setAlpha(0.0f);
        this.recordTime.setTextColor(-1);
        this.recordTime.setPadding(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(5.0f));
        r15.addView(this.recordTime, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 16.0f, 0.0f, 0.0f));
        AnonymousClass9 r013 = new AnonymousClass9(this, r35);
        this.cameraPanel = r013;
        r013.setVisibility(8);
        this.cameraPanel.setAlpha(0.0f);
        r15.addView(this.cameraPanel, LayoutHelper.createFrame(-1, 126, 83));
        TextView r014 = new TextView(r35);
        this.counterTextView = r014;
        r014.setBackgroundResource(R.drawable.photos_rounded);
        this.counterTextView.setVisibility(8);
        this.counterTextView.setTextColor(-1);
        this.counterTextView.setGravity(17);
        this.counterTextView.setPivotX(0.0f);
        this.counterTextView.setPivotY(0.0f);
        this.counterTextView.setTypeface(AndroidUtilities.bold());
        this.counterTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.photos_arrow, 0);
        this.counterTextView.setCompoundDrawablePadding(AndroidUtilities.dp(4.0f));
        this.counterTextView.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        r15.addView(this.counterTextView, LayoutHelper.createFrame(-2, 38.0f, 51, 0.0f, 0.0f, 0.0f, 116.0f));
        this.counterTextView.setOnClickListener(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda11(this));
        ZoomControlView r015 = new ZoomControlView(r35);
        this.zoomControlView = r015;
        r015.setVisibility(8);
        this.zoomControlView.setAlpha(0.0f);
        r15.addView(this.zoomControlView, LayoutHelper.createFrame(-2, 50.0f, 51, 0.0f, 0.0f, 0.0f, 116.0f));
        this.zoomControlView.setDelegate(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda12(this));
        ShutterButton r016 = new ShutterButton(r35);
        this.shutterButton = r016;
        this.cameraPanel.addView(r016, LayoutHelper.createFrame(84, 84, 17));
        this.shutterButton.setDelegate(new AnonymousClass10(this, r38, r15));
        this.shutterButton.setFocusable(true);
        this.shutterButton.setContentDescription(LocaleController.getString(R.string.AccDescrShutter));
        ImageView r017 = new ImageView(r35);
        this.switchCameraButton = r017;
        r017.setScaleType(ImageView.ScaleType.CENTER);
        this.cameraPanel.addView(this.switchCameraButton, LayoutHelper.createFrame(48, 48, 21));
        this.switchCameraButton.setOnClickListener(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda13(this));
        this.switchCameraButton.setContentDescription(LocaleController.getString(R.string.AccDescrSwitchCamera));
        int r018 = 0;
    L11:
        if (r018 >= 2) goto L13;
        this.flashModeButton[r018] = new ImageView(r35);
        this.flashModeButton[r018].setScaleType(ImageView.ScaleType.CENTER);
        this.flashModeButton[r018].setVisibility(4);
        this.cameraPanel.addView(this.flashModeButton[r018], LayoutHelper.createFrame(48, 48, 51));
        this.flashModeButton[r018].setOnClickListener(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda14(this));
        this.flashModeButton[r018].setContentDescription("flash mode " + r018);
        r018 = r018 + 1;
        goto L11
    L13:
        TextView r019 = new TextView(r35);
        this.tooltipTextView = r019;
        r019.setTextSize(1, 15.0f);
        this.tooltipTextView.setTextColor(-1);
        this.tooltipTextView.setText(LocaleController.getString(R.string.TapForVideo));
        this.tooltipTextView.setShadowLayer(AndroidUtilities.dp(3.33333f), 0.0f, AndroidUtilities.dp(0.666f), 1275068416);
        this.tooltipTextView.setPadding(AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f), 0);
        this.cameraPanel.addView(this.tooltipTextView, LayoutHelper.createFrame(-2, -2.0f, 81, 0.0f, 0.0f, 0.0f, 16.0f));
        AnonymousClass13 r020 = new AnonymousClass13(this, r35, r38);
        this.cameraPhotoRecyclerView = r020;
        r020.setVerticalScrollBarEnabled(true);
        RecyclerListView r021 = this.cameraPhotoRecyclerView;
        PhotoAttachAdapter r310 = new PhotoAttachAdapter(this, r35, false);
        this.cameraAttachAdapter = r310;
        r021.setAdapter(r310);
        this.cameraAttachAdapter.createCache();
        this.cameraPhotoRecyclerView.setClipToPadding(false);
        this.cameraPhotoRecyclerView.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.cameraPhotoRecyclerView.setItemAnimator(null);
        this.cameraPhotoRecyclerView.setLayoutAnimation(null);
        this.cameraPhotoRecyclerView.setOverScrollMode(2);
        this.cameraPhotoRecyclerView.setVisibility(4);
        this.cameraPhotoRecyclerView.setAlpha(0.0f);
        r15.addView(this.cameraPhotoRecyclerView, LayoutHelper.createFrame(-1, 80.0f));
        AnonymousClass14 r022 = new AnonymousClass14(this, r35, 0, false);
        this.cameraPhotoLayoutManager = r022;
        this.cameraPhotoRecyclerView.setLayoutManager(r022);
        this.cameraPhotoRecyclerView.setOnItemClickListener(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda15());
        return;
    L9:
        r54.showTextView();
        goto L10
    L5:
        r32 = false;
        goto L6
    }

    private int addToSelectedPhotos(MediaController.PhotoEntry r8, int r9) {
        Integer r0 = Integer.valueOf(r8.imageId);
        if (selectedPhotos.containsKey(r0) == false) goto L11;
        r8.starsAmount = 0;
        r8.hasSpoiler = false;
        r8.discardLivePhoto = null;
        r8.highQuality = null;
        selectedPhotos.remove(r0);
        int r02 = selectedPhotosOrder.indexOf(r0);
        if (r02 < 0) goto L7;
        selectedPhotosOrder.remove(r02);
    L7:
        updatePhotosCounter(false);
        updateCheckedPhotoIndices();
        if (r9 < 0) goto L10;
        r8.reset();
        this.photoViewerProvider.updatePhotoAtIndex(r9);
    L10:
        return r02;
    L11:
        r8.starsAmount = getStarsPrice();
        if (getStarsPrice() <= 0) goto L14;
        boolean r92 = true;
    L15:
        r8.hasSpoiler = r92;
        r8.isChatPreviewSpoilerRevealed = false;
        r8.isAttachSpoilerRevealed = false;
        if (hasLivePhotos() == false) goto L18;
        r8.discardLivePhoto = Boolean.valueOf(!areLivePhotosEnabled());
    L18:
        r8.highQuality = Boolean.valueOf(r8.isHighQuality());
        boolean r93 = checkSelectedCount(true);
        selectedPhotos.put(r0, r8);
        selectedPhotosOrder.add(r0);
        if (r93 == false) goto L21;
        updateCheckedPhotos();
        return -1;
    L21:
        updatePhotosCounter(true);
        return -1;
    L14:
        r92 = false;
        goto L15
    }

    private void applyCameraViewPosition() {
        CameraViewInternal r0 = this.cameraView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.cameraOpened == true) goto L7;
        r0.setTranslationX(this.cameraViewLocation[0]);
    L7:
        int r02 = this.itemSize;
        int r1 = (r02 * 2) + AndroidUtilities.dp(2.0f);
        if (this.cameraOpened == true) goto L16;
        this.cameraView.setClipTop((int) this.cameraViewOffsetY);
        this.cameraView.setClipBottom((int) this.cameraViewOffsetBottomY);
        FrameLayout.LayoutParams r2 = (FrameLayout.LayoutParams) this.cameraView.getLayoutParams();
        if (r2.height == r1) goto L12;
    L13:
        r2.width = r02;
        r2.height = r1;
        this.cameraView.setLayoutParams(r2);
        AndroidUtilities.runOnUIThread(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda5(this, r2));
        return;
    L12:
        if (r2.width != r02) goto L13;
        return;
    }

    private boolean checkSelectedCount(boolean r5) {
        if (getStarsPrice() <= 0) goto L14;
        boolean r0 = false;
    L6:
        if (selectedPhotos.size() <= (10 - (r5 ? 1 : 0))) goto L13;
        if (selectedPhotosOrder.isEmpty() == true) goto L13;
        Object r2 = selectedPhotos.get(selectedPhotosOrder.get(0));
        if ((r2 instanceof MediaController.PhotoEntry) == false) goto L13;
        addToSelectedPhotos((MediaController.PhotoEntry) r2, -1);
        r0 = true;
    L13:
        return r0;
    L14:
        return false;
    }

    private boolean checkSendMediaEnabled(MediaController.PhotoEntry r3) {
        if (this.videoEnabled == true) goto L12;
        if (r3.isVideo == false) goto L12;
        if (this.parentAlert.checkCanRemoveRestrictionsByBoosts() == false) goto L9;
        return true;
    L9:
        BulletinFactory.of(this.parentAlert.sizeNotifierFrameLayout, this.resourcesProvider).createErrorBulletin(LocaleController.getString(R.string.GlobalAttachVideoRestricted)).show();
        return true;
    L12:
        if (this.photoEnabled == false) goto L14;
        return false;
    L14:
        if (r3.isVideo == false) goto L16;
        return false;
    L16:
        if (this.parentAlert.checkCanRemoveRestrictionsByBoosts() == false) goto L18;
        return true;
    L18:
        BulletinFactory.of(this.parentAlert.sizeNotifierFrameLayout, this.resourcesProvider).createErrorBulletin(LocaleController.getString(R.string.GlobalAttachPhotoRestricted)).show();
        return true;
    }

    private PhotoAttachPhotoCell getCellForIndex(int r7) {
        int r0 = this.gridView.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L15;
        View r2 = this.gridView.getChildAt(r1);
        if (r2.getTop() >= (this.gridView.getMeasuredHeight() - this.parentAlert.getClipLayoutBottom())) goto L14;
        if ((r2 instanceof PhotoAttachPhotoCell) == false) goto L14;
        PhotoAttachPhotoCell r22 = (PhotoAttachPhotoCell) r2;
        if (r22.getImageView().getTag() == null) goto L14;
        if (((Integer) r22.getImageView().getTag()).intValue() != r7) goto L14;
        return r22;
    L14:
        r1 = r1 + 1;
        goto L3
    L15:
        return null;
    }

    private MediaController.PhotoEntry getPhotoEntryAtPosition(int r3) {
        if (r3 >= 0) goto L5;
        return null;
    L5:
        int r1 = cameraPhotos.size();
        if (r3 < r1) goto L8;
        int r32 = r3 - r1;
        MediaController.AlbumEntry r12 = this.selectedAlbumEntry;
        if (r12 != null) goto L12;
    L15:
        return null;
    L12:
        if (r32 >= r12.photos.size()) goto L15;
        return this.selectedAlbumEntry.photos.get(r32);
    L8:
        return (MediaController.PhotoEntry) cameraPhotos.get(r3);
    }

    private int getTopScrollOffset() {
        return (AndroidUtilities.dp(7.0f) + ActionBar.getCurrentActionBarHeight()) + this.listAdditionalH;
    }

    private boolean isNoGalleryPermissions() {
        Activity r0 = AndroidUtilities.findActivity(getContext());
        if (r0 != null) goto L5;
        r0 = this.parentAlert.baseFragment.getParentActivity();
    L5:
        if (r0 == null) goto L18;
        int r3 = Build.VERSION.SDK_INT;
        if (r3 >= 33) goto L9;
    L12:
        if (r3 < 33) goto L14;
        return false;
    L14:
        if (r0.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) goto L23;
        return false;
    L23:
        return true;
    L9:
        if (r0.checkSelfPermission("android.permission.READ_MEDIA_IMAGES") == 0) goto L11;
        return true;
    L11:
        if (r0.checkSelfPermission("android.permission.READ_MEDIA_VIDEO") == 0) goto L12;
        return true;
    L18:
        return true;
    }

    private /* synthetic */ void lambda$applyCameraViewPosition$18(FrameLayout.LayoutParams r1) {
        CameraViewInternal r0 = this.cameraView;
        if (r0 == null) goto L6;
        r0.setLayoutParams(r1);
        return;
    }

    private /* synthetic */ void lambda$hideCamera$17() {
        this.parentAlert.getContainer().removeView(this.cameraView);
        this.cameraView = null;
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        this.dropDownContainer.toggleSubMenu();
    }

    private /* synthetic */ void lambda$new$2(int r11, BaseFragment r12, ArrayList r13, int r14, ChatActivity r15) {
        ChatAttachAlert r0 = this.parentAlert;
        if (r0.isPhotoPicker == false) goto L8;
        if (r0.isStickerMode == true) goto L8;
        PhotoViewer.getInstance().setParentActivity(r12);
        PhotoViewer.getInstance().setMaxSelectedPhotos(0, false);
        r11 = 3;
    L8:
        if (this.parentAlert.isPollAttach == false) goto L10;
        r11 = 13;
    L10:
        int r6 = r11;
        PhotoViewer r3 = PhotoViewer.getInstance();
        PhotoViewer.PhotoViewerProvider r8 = this.photoViewerProvider;
        if (this.parentAlert.isPollAttach == false) goto L13;
        ChatActivity r9 = null;
    L14:
        r3.openPhotoForSelect(r13, r14, r6, false, r8, r9);
        PhotoViewer.getInstance().setAvatarFor(this.parentAlert.getAvatarFor());
        ChatAttachAlert r112 = this.parentAlert;
        if (r112.isPhotoPicker == false) goto L20;
        if (r112.isStickerMode == true) goto L20;
        PhotoViewer.getInstance().closePhotoAfterSelect = false;
    L27:
        if (this.parentAlert.isStickerMode == false) goto L30;
        PhotoViewer.getInstance().enableStickerMode(null, null, false, this.parentAlert.customStickerHandler);
    L30:
        if (captionForAllMedia() == false) goto L33;
        PhotoViewer.getInstance().setCaption(this.parentAlert.getCommentView().getText());
        return;
    L33:
        return;
    L20:
        if (r112.avatarPicker == 0) goto L27;
        boolean r132 = true;
        PhotoViewer.getInstance().closePhotoAfterSelect = true;
        PhotoViewer r113 = PhotoViewer.getInstance();
        if (this.parentAlert.avatarWithBulletin != null) goto L25;
        r132 = false;
    L25:
        r113.closePhotoAfterSelectWithAnimation = r132;
        goto L27
    L13:
        r9 = r15;
        goto L14
    }

    private /* synthetic */ void lambda$new$3(boolean r13, Theme.ResourcesProvider r14, View r15, int r16, float r17, float r18) {
        int r1 = r16;
        if (this.mediaEnabled == false) goto L144;
        ChatAttachAlert r2 = this.parentAlert;
        if (r2.destroyed == true) goto L146;
        BaseFragment r22 = r2.baseFragment;
        if (r22 != null) goto L10;
        r22 = LaunchActivity.getLastFragment();
    L10:
        BaseFragment r6 = r22;
        if (r6 != null) goto L14;
        return;
    L14:
        if ((r15 instanceof GalleryEmptyView) == false) goto L17;
        return;
    L17:
        if (PhotoAttachAdapter.m9774$$Nest$fgetneedCamera(this.adapter) == false) goto L26;
        if (this.selectedAlbumEntry != this.galleryAlbumEntry) goto L26;
        if (r1 != 0) goto L26;
        if (this.noCameraPermissions == false) goto L26;
        r6.getParentActivity().requestPermissions(new String[]{"android.permission.CAMERA"}, 18);     // Catch: Exception -> L141
        return;
    L151:
        return;
    L26:
        if (this.noGalleryPermissions == false) goto L30;
        SystemUtils.requestImagesAndVideoPermission(r6.getParentActivity());
        return;
    L30:
        if (PhotoAttachAdapter.m9772$$Nest$fgethasCameraSpaceRow(this.adapter) == true) goto L32;
    L35:
        if (r1 != 0) goto L47;
        if (r13 == false) goto L47;
        if (this.selectedAlbumEntry != this.galleryAlbumEntry) goto L47;
        if (ExteraConfig.getHideCameraTile() == true) goto L47;
        if (this.noCameraPermissions == true) goto L47;
        openCameraByClick();
        return;
    L47:
        if (PhotoAttachAdapter.m9772$$Nest$fgethasCameraSpaceRow(this.adapter) == false) goto L52;
        if (r1 <= this.itemsPerRow) goto L52;
        r1 = r1 - 1;
    L52:
        if (this.selectedAlbumEntry != this.galleryAlbumEntry) goto L59;
        if (r13 == false) goto L59;
        if (ExteraConfig.getHideCameraTile() == true) goto L57;
    L58:
        r1 = r1 - 1;
        goto L59
    L57:
        if (this.noCameraPermissions == true) goto L58;
    L59:
        ChatActivity r23 = null;
        if (this.showAvatarConstructor == false) goto L67;
        if (r1 == 0) goto L63;
    L66:
        r1 = r1 - 1;
        goto L67
    L63:
        if ((r15 instanceof AvatarConstructorPreviewCell) == false) goto L148;
        showAvatarConstructorFragment((AvatarConstructorPreviewCell) r15, null);
        this.parentAlert.dismiss();
        goto L66
    L148:
        return;
    L67:
        int r8 = r1;
        ArrayList<Object> r7 = getAllPhotosArray();
        if (r8 >= 0) goto L70;
        return;
    L70:
        if (r8 >= r7.size()) goto L149;
        ChatAttachAlert.ChatAttachViewDelegate r132 = this.parentAlert.delegate;
        if (r132 != null) goto L75;
    L83:
        PhotoViewer.getInstance().setParentActivity(r6, r14);
        PhotoViewer.getInstance().setParentAlert(this.parentAlert);
        PhotoViewer r133 = PhotoViewer.getInstance();
        ChatAttachAlert r142 = this.parentAlert;
        r133.setMaxSelectedPhotos(r142.maxSelectedPhotos, r142.allowOrder);
        ChatAttachAlert r134 = this.parentAlert;
        if (r134.isPhotoPicker == false) goto L92;
        if (r134.isStickerMode == false) goto L92;
        BaseFragment r143 = r134.baseFragment;
        int r12 = 11;
        if ((r143 instanceof ChatActivity) == false) goto L90;
        r23 = (ChatActivity) r143;
    L90:
        int r5 = r12;
        ChatActivity r9 = r23;
    L103:
        if (r134.delegate.needEnterComment() == true) goto L106;
        AndroidUtilities.hideKeyboard(r6.getFragmentView().findFocus());
        AndroidUtilities.hideKeyboard(this.parentAlert.getContainer().findFocus());
    L106:
        if (selectedPhotos.size() <= 0) goto L116;
        if (selectedPhotosOrder.size() <= 0) goto L116;
        Object r135 = selectedPhotos.get(selectedPhotosOrder.get(0));
        if ((r135 instanceof MediaController.PhotoEntry) == false) goto L113;
        ((MediaController.PhotoEntry) r135).caption = this.parentAlert.getCommentView().getText();
    L113:
        if ((r135 instanceof MediaController.SearchImage) == false) goto L116;
        ((MediaController.SearchImage) r135).caption = this.parentAlert.getCommentView().getText();
    L116:
        if (this.parentAlert.getAvatarFor() == null) goto L123;
        if ((r7.get(r8) instanceof MediaController.PhotoEntry) == false) goto L120;
        boolean r136 = ((MediaController.PhotoEntry) r7.get(r8)).isVideo;
    L121:
        this.parentAlert.getAvatarFor().isVideo = r136;
        goto L123
    L120:
        r136 = false;
    L123:
        if ((r7.get(r8) instanceof MediaController.PhotoEntry) == true) goto L125;
    L127:
        boolean r137 = false;
    L128:
        Object r144 = r7.get(r8);
        if ((r144 instanceof MediaController.PhotoEntry) == true) goto L131;
    L133:
        if (r137 == false) goto L135;
        setCurrentSpoilerVisible(r8, false);
    L135:
        ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda27 r3 = new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda27(this, r5, r6, r7, r8, r9);
        if (r137 == false) goto L138;
        long r122 = 250;
    L139:
        AndroidUtilities.runOnUIThread(r3, r122);
        return;
    L138:
        r122 = 0;
        goto L139
    L131:
        if (checkSendMediaEnabled((MediaController.PhotoEntry) r144) == false) goto L133;
        return;
    L125:
        if (((MediaController.PhotoEntry) r7.get(r8)).hasSpoiler == false) goto L127;
        r137 = true;
    L92:
        if (r134.avatarPicker == 0) goto L94;
        r9 = null;
        r5 = 1;
        goto L103
    L94:
        BaseFragment r145 = r134.baseFragment;
        if ((r145 instanceof ChatActivity) == false) goto L99;
        r23 = (ChatActivity) r145;
    L97:
        r9 = r23;
        r5 = 0;
        goto L103
    L99:
        if (r134.allowEnterCaption == true) goto L97;
        r12 = 4;
        goto L90
    L75:
        if (r132.selectItemOnClicking() == false) goto L83;
        if ((r7.get(r8) instanceof MediaController.PhotoEntry) == false) goto L83;
        MediaController.PhotoEntry r138 = (MediaController.PhotoEntry) r7.get(r8);
        selectedPhotos.clear();
        if (r138 == null) goto L81;
        addToSelectedPhotos(r138, -1);
    L81:
        this.parentAlert.applyCaption();
        ChatAttachAlert r123 = this.parentAlert;
        r123.delegate.didPressedButton(7, true, true, 0, 0, 0, r123.isCaptionAbove(), false, 0);
        selectedPhotos.clear();
        cameraPhotos.clear();
        selectedPhotosOrder.clear();
        selectedPhotos.clear();
        return;
    L149:
        return;
    L32:
        if (r1 != this.itemsPerRow) goto L35;
        openCameraByClick();
        return;
    L146:
        return;
    }

    private /* synthetic */ boolean lambda$new$4(View r13, int r14) {
        if (this.parentAlert.storyMediaPicker == false) goto L5;
        return false;
    L5:
        if (r14 != 0) goto L18;
        if (this.selectedAlbumEntry != this.galleryAlbumEntry) goto L18;
        if (ExteraConfig.getHideCameraTile() == true) goto L18;
        if (this.noCameraPermissions == true) goto L18;
        ChatAttachAlert r12 = this.parentAlert;
        ChatAttachAlert.ChatAttachViewDelegate r0 = r12.delegate;
        if (r0 == null) goto L21;
        r0.didPressedButton(0, false, true, 0, 0, 0, r12.isCaptionAbove(), false, 0);
        return true;
    L21:
        return true;
    L18:
        if ((r13 instanceof PhotoAttachPhotoCell) == false) goto L20;
        this.itemRangeSelector.startSelection(r13, r14);
    L20:
        return false;
    }

    private /* synthetic */ void lambda$new$5(View r2) {
        if (this.cameraView != null) goto L5;
        return;
    L5:
        openPhotoViewer(null, false, false);
        CameraController.getInstance().stopPreview(this.cameraView.getCameraSessionObject());
    }

    private /* synthetic */ void lambda$new$6(float r2) {
        CameraViewInternal r0 = this.cameraView;
        if (r0 == null) goto L5;
        this.cameraZoom = r2;
        r0.setZoom(r2);
    L5:
        showZoomControls(true, true);
    }

    private /* synthetic */ void lambda$new$7(View r4) {
        if (this.takingPhoto == true) goto L11;
        CameraViewInternal r42 = this.cameraView;
        if (r42 != null) goto L7;
        return;
    L7:
        if (r42.isInited() == false) goto L13;
        this.canSaveCameraPreview = false;
        this.cameraView.switchCamera();
        ObjectAnimator r43 = ObjectAnimator.ofFloat(this.switchCameraButton, View.SCALE_X, new float[]{0.0f}).setDuration(100);
        r43.addListener(new AnonymousClass11(this));
        r43.start();
        return;
    L13:
        return;
    }

    private /* synthetic */ void lambda$new$8(View r11) {
        if (this.flashAnimationInProgress == true) goto L19;
        CameraViewInternal r0 = this.cameraView;
        if (r0 != null) goto L7;
        return;
    L7:
        if (r0.isInited() == true) goto L9;
        return;
    L9:
        if (this.cameraOpened == false) goto L22;
        String r02 = this.cameraView.getCameraSession().getCurrentFlashMode();
        String r1 = this.cameraView.getCameraSession().getNextFlashMode();
        if (r02.equals(r1) == true) goto L23;
        this.cameraView.getCameraSession().setCurrentFlashMode(r1);
        this.flashAnimationInProgress = true;
        ImageView[] r2 = this.flashModeButton;
        ImageView r4 = r2[0];
        if (r4 != r11) goto L17;
        r4 = r2[1];
    L17:
        r4.setVisibility(0);
        setCameraFlashModeIcon(r4, r1);
        AnimatorSet r12 = new AnimatorSet();
        Property r5 = View.TRANSLATION_Y;
        ObjectAnimator r7 = ObjectAnimator.ofFloat(r11, r5, new float[]{0.0f, AndroidUtilities.dp(48.0f)});
        ObjectAnimator r22 = ObjectAnimator.ofFloat(r4, r5, new float[]{-AndroidUtilities.dp(48.0f), 0.0f});
        Property r8 = View.ALPHA;
        r12.playTogether(new Animator[]{r7, r22, ObjectAnimator.ofFloat(r11, r8, new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(r4, r8, new float[]{0.0f, 1.0f})});
        r12.setDuration(220);
        r12.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r12.addListener(new AnonymousClass12(this, r11, r4));
        r12.start();
        return;
    L23:
        return;
    L22:
        return;
    }

    private /* synthetic */ void lambda$onHide$29() {
        this.dropDownContainer.setVisibility(8);
    }

    private /* synthetic */ void lambda$onMenuItemClick$19(boolean r13, int r14, int r15) {
        this.parentAlert.applyCaption();
        ChatAttachAlert r12 = this.parentAlert;
        r12.delegate.didPressedButton(7, false, r13, r14, 0, 0, r12.isCaptionAbove(), false, 0);
    }

    private /* synthetic */ void lambda$onMenuItemClick$20(Long r13) {
        this.parentAlert.applyCaption();
        ChatAttachAlert r12 = this.parentAlert;
        r12.delegate.didPressedButton(7, false, true, 0, 0, 0, r12.isCaptionAbove(), false, r13.longValue());
    }

    private /* synthetic */ void lambda$onMenuItemClick$21(boolean r13, int r14, int r15) {
        this.parentAlert.applyCaption();
        ChatAttachAlert r12 = this.parentAlert;
        r12.delegate.didPressedButton(4, true, r13, r14, 0, 0, r12.isCaptionAbove(), false, 0);
    }

    private /* synthetic */ void lambda$onMenuItemClick$22(Long r13) {
        this.parentAlert.applyCaption();
        ChatAttachAlert r12 = this.parentAlert;
        r12.delegate.didPressedButton(4, true, true, 0, 0, 0, r12.isCaptionAbove(), false, r13.longValue());
    }

    private /* synthetic */ void lambda$onMenuItemClick$23(boolean r4) {
        ActionBarMenuSubItem r0 = this.spoilerItem;
        if (r4 == false) goto L5;
        int r1 = R.string.DisablePhotoSpoiler;
    L6:
        r0.setText(LocaleController.getString(r1));
        ActionBarMenuSubItem r02 = this.spoilerItem;
        if (r4 == false) goto L9;
        r02.setIcon(R.drawable.msg_spoiler_off);
    L10:
        ChatAttachAlert r03 = this.parentAlert;
        if (r4 == false) goto L16;
        r03.selectedMenuItem.hideSubItem(1);
        if (getSelectedItemsCount() > 1) goto L20;
        this.parentAlert.selectedMenuItem.hideSubItem(6);
        return;
    L20:
        return;
    L16:
        r03.selectedMenuItem.showSubItem(1);
        if (getSelectedItemsCount() > 1) goto L21;
        this.parentAlert.selectedMenuItem.showSubItem(6);
        return;
    L21:
        return;
    L9:
        r02.setAnimatedIcon(R.raw.photo_spoiler);
        goto L10
    L5:
        r1 = R.string.EnablePhotoSpoiler;
        goto L6
    }

    private /* synthetic */ void lambda$onMenuItemClick$25(boolean r3) {
        ActionBarMenuSubItem r0 = this.qualityItem;
        if (r3 == false) goto L5;
        int r1 = R.string.SendInStandardQuality;
    L6:
        r0.setText(LocaleController.getString(r1));
        ActionBarMenuSubItem r2 = this.qualityItem;
        if (r3 == false) goto L10;
        r2.setIcon(R.drawable.menu_quality_sd);
        return;
    L10:
        r2.setIcon(R.drawable.menu_quality_hd);
        return;
    L5:
        r1 = R.string.SendInHighQuality;
        goto L6
    }

    private /* synthetic */ void lambda$onMenuItemClick$27(Long r1, Runnable r2) {
        r2.run();
        setStarsPrice(r1.longValue());
    }

    private /* synthetic */ void lambda$onPreMeasure$30() {
        this.adapter.notifyDataSetChanged();
    }

    private /* synthetic */ void lambda$onShow$28(ChatAttachAlert.AttachAlertLayout r3) {
        int r0 = r3.getCurrentItemTop();
        int r32 = r3.getListTopPadding();
        RecyclerListView r2 = this.gridView;
        if (r0 <= AndroidUtilities.dp(8.0f)) goto L5;
        r32 = r32 - r0;
    L5:
        r2.scrollBy(0, r32);
    }

    private /* synthetic */ void lambda$setCurrentSpoilerVisible$0(MediaController.PhotoEntry r2, boolean r3, View r4) {
        if ((r4 instanceof PhotoAttachPhotoCell) == false) goto L12;
        PhotoAttachPhotoCell r42 = (PhotoAttachPhotoCell) r4;
        if (r42.getPhotoEntry() != r2) goto L13;
        r42.setHasSpoiler(r3, Float.valueOf(250.0f));
        long r1 = getStarsPrice();
        boolean r0 = true;
        if (selectedPhotos.size() > 1) goto L10;
        r0 = false;
    L10:
        r42.setStarsPrice(r1, r0);
        return;
    L13:
        return;
    }

    private /* synthetic */ void lambda$showAvatarConstructorFragment$10(AvatarConstructorFragment r30, AvatarConstructorFragment.BackgroundGradient r31, long r32, TLRPC.Document r34, AvatarConstructorFragment.PreviewView r35) {
        selectedPhotos.clear();
        Bitmap r5 = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
        Canvas r7 = new Canvas(r5);
        GradientTools r8 = new GradientTools();
        byte r14 = 1;
        if (r31 == null) goto L5;
        r8.setColors(r31.color1, r31.color2, r31.color3, r31.color4);
    L6:
        r8.setBounds(0.0f, 0.0f, 800.0f, 800.0f);
        r7.drawRect(0.0f, 0.0f, 800.0f, 800.0f, r8.paint);
        File r82 = new File(FileLoader.getDirectory(4), SharedConfig.getLastLocalId() + "avatar_background.png");
        r82.createNewFile();     // Catch: IOException -> L9
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();     // Catch: IOException -> L9
        r5.compress(Bitmap.CompressFormat.PNG, 0, r0);     // Catch: IOException -> L9
        byte[] r02 = r0.toByteArray();     // Catch: IOException -> L9
        FileOutputStream r10 = new FileOutputStream(r82);     // Catch: IOException -> L9
        r10.write(r02);     // Catch: IOException -> L9
        r10.flush();     // Catch: IOException -> L9
        r10.close();     // Catch: IOException -> L9
    L11:
        ImageReceiver r03 = r35.getImageReceiver();
        if (r03.getAnimation() == null) goto L15;
        Bitmap r04 = r03.getAnimation().getFirstFrame(null);
        ImageReceiver r102 = new ImageReceiver();
        r102.setImageBitmap(r04);
        r102.setImageCoords(120.0f, 120.0f, 560.0f, 560.0f);
        r102.setRoundRadius(72);
        r102.draw(r7);
        r102.clearImage();
        r04.recycle();
    L18:
        File r72 = new File(FileLoader.getDirectory(4), SharedConfig.getLastLocalId() + "avatar_background.png");
        r72.createNewFile();     // Catch: IOException -> L21
        ByteArrayOutputStream r05 = new ByteArrayOutputStream();     // Catch: IOException -> L21
        r5.compress(Bitmap.CompressFormat.PNG, 0, r05);     // Catch: IOException -> L21
        byte[] r06 = r05.toByteArray();     // Catch: IOException -> L21
        FileOutputStream r52 = new FileOutputStream(r72);     // Catch: IOException -> L21
        r52.write(r06);     // Catch: IOException -> L21
        r52.flush();     // Catch: IOException -> L21
        r52.close();     // Catch: IOException -> L21
    L24:
        if (r35.hasAnimation() == false) goto L68;
        MediaController.PhotoEntry r07 = new MediaController.PhotoEntry(0, 0, 0, r82.getPath(), 0, false, 0, 0, 0);
        r07.thumbPath = r72.getPath();
        if (r35.documentId == 0) goto L38;
        TLRPC.TL_videoSizeEmojiMarkup r53 = new TLRPC.TL_videoSizeEmojiMarkup();
        r53.emoji_id = r35.documentId;
        r53.background_colors.add(Integer.valueOf(r35.backgroundGradient.color1));
        int r73 = r35.backgroundGradient.color2;
        if (r73 == 0) goto L30;
        r53.background_colors.add(Integer.valueOf(r73));
    L30:
        int r74 = r35.backgroundGradient.color3;
        if (r74 == 0) goto L33;
        r53.background_colors.add(Integer.valueOf(r74));
    L33:
        int r75 = r35.backgroundGradient.color4;
        if (r75 == 0) goto L36;
        r53.background_colors.add(Integer.valueOf(r75));
    L36:
        r07.emojiMarkup = r53;
    L49:
        VideoEditedInfo r54 = new VideoEditedInfo();
        r07.editedInfo = r54;
        r54.originalPath = r82.getPath();
        VideoEditedInfo r55 = r07.editedInfo;
        r55.resultWidth = 800;
        r55.resultHeight = 800;
        r55.originalWidth = 800;
        r55.originalHeight = 800;
        r55.isPhoto = true;
        r55.bitrate = -1;
        r55.muted = true;
        r55.startTime = 0;
        r55.start = 0.0f;
        r55.endTime = r35.getDuration();
        VideoEditedInfo r2 = r07.editedInfo;
        r2.framerate = 30;
        r2.avatarStartTime = 0;
        long r56 = r2.endTime;
        r2.estimatedSize = (int) ((r56 / 1000.0f) * 115200.0f);
        r2.estimatedDuration = r56;
        VideoEditedInfo.MediaEntity r22 = new VideoEditedInfo.MediaEntity();
        r22.type = 0;
        if (r34 != null) goto L52;
        TLRPC.Document r57 = AnimatedEmojiDrawable.findDocument(UserConfig.selectedAccount, r32);
    L53:
        if (r57 == null) goto L81;
        r22.viewWidth = 560;
        r22.viewHeight = 560;
        r22.width = 0.7f;
        r22.height = 0.7f;
        r22.x = 0.15f;
        r22.y = 0.15f;
        r22.document = r57;
        r22.parentObject = null;
        r22.text = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r57, true).getAbsolutePath();
        r22.roundRadius = 0.13f;
        if (MessageObject.isAnimatedStickerDocument(r57, true) == false) goto L58;
    L59:
        boolean r3 = MessageObject.isAnimatedStickerDocument(r57, true);
        byte r6 = r22.subType;
        if (r3 == true) goto L63;
        r14 = 4;
    L63:
        r22.subType = (byte) (r6 | r14);
    L65:
        if (MessageObject.isTextColorEmoji(r57) == false) goto L67;
        r22.color = -1;
        r22.subType = (byte) (r22.subType | 8);
    L67:
        r07.editedInfo.mediaEntities = new ArrayList();
        r07.editedInfo.mediaEntities.add(r22);
    L69:
        selectedPhotos.put(-1, r07);
        selectedPhotosOrder.add(-1);
        ChatAttachAlert r08 = this.parentAlert;
        r08.delegate.didPressedButton(7, true, false, 0, 0, 0, r08.isCaptionAbove(), false, 0);
        if (r30.finishOnDone == true) goto L80;
        BaseFragment r09 = this.parentAlert.baseFragment;
        if (r09 == null) goto L74;
        r09.removeSelfFromStack();
    L74:
        r30.finishFragment();
        return;
    L80:
        return;
    L58:
        if (MessageObject.isVideoStickerDocument(r57) == false) goto L65;
    L81:
        return;
    L52:
        r57 = r34;
        goto L53
    L38:
        if (r35.document == null) goto L49;
        TLRPC.TL_videoSizeStickerMarkup r58 = new TLRPC.TL_videoSizeStickerMarkup();
        TLRPC.Document r76 = r35.document;
        r58.sticker_id = r76.id;
        r58.stickerset = MessageObject.getInputStickerSet(r76);
        r58.background_colors.add(Integer.valueOf(r35.backgroundGradient.color1));
        int r77 = r35.backgroundGradient.color2;
        if (r77 == 0) goto L42;
        r58.background_colors.add(Integer.valueOf(r77));
    L42:
        int r78 = r35.backgroundGradient.color3;
        if (r78 == 0) goto L45;
        r58.background_colors.add(Integer.valueOf(r78));
    L45:
        int r79 = r35.backgroundGradient.color4;
        if (r79 == 0) goto L48;
        r58.background_colors.add(Integer.valueOf(r79));
    L48:
        r07.emojiMarkup = r58;
        goto L49
    L68:
        r07 = new MediaController.PhotoEntry(0, 0, 0, r72.getPath(), 0, false, 0, 0, 0);
    L21:
        e = move-exception;
        e.printStackTrace();
        goto L24
    L15:
        if (r03.getLottieAnimation() == null) goto L17;
        r03.getLottieAnimation().setCurrentFrame(0, false, true);
    L17:
        r03.setImageCoords(120.0f, 120.0f, 560.0f, 560.0f);
        r03.setRoundRadius(72);
        r03.draw(r7);
    L9:
        e = move-exception;
        e.printStackTrace();
        goto L11
    L5:
        int[] r010 = AvatarConstructorFragment.defaultColors[0];
        r8.setColors(r010[0], r010[1], r010[2], r010[3]);
        goto L6
    }

    private /* synthetic */ void lambda$showCamera$16() {
        String r0 = this.cameraView.getCameraSession().getCurrentFlashMode();
        String r1 = this.cameraView.getCameraSession().getNextFlashMode();
        if (r0 == null) goto L42;
        if (r1 == null) goto L44;
        int r2 = 4;
        if (r0.equals(r1) == false) goto L11;
        int r02 = 0;
    L9:
        if (r02 >= 2) goto L24;
        this.flashModeButton[r02].setVisibility(4);
        this.flashModeButton[r02].setAlpha(0.0f);
        this.flashModeButton[r02].setTranslationY(0.0f);
        r02 = r02 + 1;
    L24:
        ImageView r03 = this.switchCameraButton;
        if (this.cameraView.isFrontface() == false) goto L27;
        int r3 = R.drawable.camera_revert1;
    L28:
        r03.setImageResource(r3);
        ImageView r04 = this.switchCameraButton;
        if (this.cameraView.hasFrontFaceCamera() == false) goto L31;
        r2 = 0;
    L31:
        r04.setVisibility(r2);
        if (this.cameraOpened == true) goto L34;
        AnimatorSet r05 = new AnimatorSet();
        this.cameraInitAnimation = r05;
        r05.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.cameraView, View.ALPHA, new float[]{0.0f, 1.0f})});
        this.cameraInitAnimation.setDuration(180);
        this.cameraInitAnimation.addListener(new AnonymousClass19(this));
        this.cameraInitAnimation.start();
    L34:
        Runnable r7 = this.afterCameraInitRunnable;
        if (r7 == null) goto L43;
        r7.run();
        return;
    L43:
        return;
    L27:
        r3 = R.drawable.camera_revert2;
        goto L28
    L11:
        setCameraFlashModeIcon(this.flashModeButton[0], this.cameraView.getCameraSession().getCurrentFlashMode());
        int r06 = 0;
    L12:
        if (r06 >= 2) goto L24;
        ImageView r5 = this.flashModeButton[r06];
        if (r06 != 0) goto L16;
        int r6 = 0;
    L17:
        r5.setVisibility(r6);
        ImageView r52 = this.flashModeButton[r06];
        if (r06 == 0) goto L20;
    L22:
        float r62 = 0.0f;
    L23:
        r52.setAlpha(r62);
        this.flashModeButton[r06].setTranslationY(0.0f);
        r06 = r06 + 1;
        goto L12
    L20:
        if (this.cameraOpened == false) goto L22;
        r62 = 1.0f;
        goto L23
    L16:
        r6 = 4;
        goto L17
    L44:
        return;
    }

    private /* synthetic */ void lambda$showZoomControls$14() {
        showZoomControls(false, true);
        this.zoomControlHideRunnable = null;
    }

    private /* synthetic */ void lambda$showZoomControls$15() {
        showZoomControls(false, true);
        this.zoomControlHideRunnable = null;
    }

    private /* synthetic */ void lambda$updateAlbumsDropDown$13(int r1, View r2) {
        this.parentAlert.actionBar.getActionBarMenuOnItemClick().onItemClick(r1);
        this.dropDownContainer.toggleSubMenu();
    }

    private int maxCount() {
        BaseFragment r0 = this.parentAlert.baseFragment;
        if ((r0 instanceof ChatActivity) == true) goto L5;
        return Integer.MAX_VALUE;
    L5:
        if (((ChatActivity) r0).getChatMode() == 5) goto L7;
        return Integer.MAX_VALUE;
    L7:
        return this.parentAlert.baseFragment.getMessagesController().config.quickReplyMessagesLimit.get() - ((ChatActivity) this.parentAlert.baseFragment).messages.size();
    }

    private void onPhotoEditModeChanged(boolean r1) {
    }

    private void openCameraByClick() {
        if (SharedConfig.inAppCamera == false) goto L6;
        openCamera(true);
        return;
    L6:
        ChatAttachAlert r12 = this.parentAlert;
        ChatAttachAlert.ChatAttachViewDelegate r0 = r12.delegate;
        if (r0 == null) goto L10;
        r0.didPressedButton(0, false, true, 0, 0, 0, r12.isCaptionAbove(), false, 0);
        return;
    }

    private void openCameraWithPermissionCheck() {
        if (ContextCompat.checkSelfPermission(this.parentAlert.baseFragment.getParentActivity(), "android.permission.CAMERA") != 0) goto L9;
        openCameraByClick();
        return;
    L9:
        this.parentAlert.baseFragment.getParentActivity().requestPermissions(new String[]{"android.permission.CAMERA"}, 18);     // Catch: Exception -> L8
        return;
    }

    private void pauseCameraPreview() {
    L6:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if (this.cameraView == null) goto L11;
        CameraController.getInstance().stopPreview(this.cameraView.getCameraSessionObject());     // Catch: Exception -> L6
        return;
    }

    private boolean processTouchEvent(MotionEvent r20) {
        if (r20 != null) goto L6;
        return false;
    L6:
        if (this.pressed == true) goto L10;
        if (r20.getActionMasked() != 0) goto L10;
    L11:
        this.zoomControlView.getHitRect(this.hitRect);
        if (this.zoomControlView.getTag() == null) goto L17;
        if (this.hitRect.contains((int) r20.getX(), (int) r20.getY()) == false) goto L17;
        return false;
    L17:
        if (this.takingPhoto == false) goto L19;
    L85:
        return true;
    L19:
        if (this.dragging == true) goto L85;
        if (r20.getPointerCount() != 2) goto L23;
        this.pinchStartDistance = (float) Math.hypot(r20.getX(1) - r20.getX(0), r20.getY(1) - r20.getY(0));
        this.zooming = true;
    L24:
        this.zoomWas = false;
        this.pressed = true;
        goto L85
    L23:
        this.maybeStartDraging = true;
        this.lastY = r20.getY();
        this.zooming = false;
    L10:
        if (r20.getActionMasked() == 5) goto L11;
        if (this.pressed == false) goto L85;
        int r3 = r20.getActionMasked();
        Property r10 = View.ALPHA;
        if (r3 != 2) goto L67;
        if (this.zooming == true) goto L32;
    L49:
        float r1 = r20.getY();
        float r32 = r1 - this.lastY;
        if (this.maybeStartDraging == false) goto L55;
        if (Math.abs(r32) <= AndroidUtilities.getPixelsInCM(0.4f, false)) goto L85;
        this.maybeStartDraging = false;
        this.dragging = true;
        goto L85
    L55:
        if (this.dragging == false) goto L85;
        if (this.cameraView == null) goto L85;
        if (ExteraConfig.getHideCameraTile() == true) goto L85;
        CameraViewInternal r12 = this.cameraView;
        r12.setTranslationY(r12.getTranslationY() + r32);
        this.lastY = r1;
        this.zoomControlView.setTag(null);
        Runnable r13 = this.zoomControlHideRunnable;
        if (r13 == null) goto L64;
        AndroidUtilities.cancelRunOnUIThread(r13);
        this.zoomControlHideRunnable = null;
    L64:
        if (this.cameraPanel.getTag() != null) goto L85;
        this.cameraPanel.setTag(1);
        AnimatorSet r14 = new AnimatorSet();
        r14.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.cameraPanel, r10, new float[]{0.0f}), ObjectAnimator.ofFloat(this.zoomControlView, r10, new float[]{0.0f}), ObjectAnimator.ofFloat(this.counterTextView, r10, new float[]{0.0f}), ObjectAnimator.ofFloat(this.flashModeButton[0], r10, new float[]{0.0f}), ObjectAnimator.ofFloat(this.flashModeButton[1], r10, new float[]{0.0f}), ObjectAnimator.ofFloat(this.cameraPhotoRecyclerView, r10, new float[]{0.0f})});
        r14.setDuration(220);
        r14.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r14.start();
        goto L85
    L32:
        if (r20.getPointerCount() != 2) goto L49;
        if (this.dragging == true) goto L49;
        float r15 = (float) Math.hypot(r20.getX(1) - r20.getX(0), r20.getY(1) - r20.getY(0));
        if (this.zoomWas == true) goto L41;
        if (Math.abs(r15 - this.pinchStartDistance) < AndroidUtilities.getPixelsInCM(0.4f, false)) goto L85;
        this.pinchStartDistance = r15;
        this.zoomWas = true;
        goto L85
    L41:
        if (this.cameraView == null) goto L85;
        float r33 = (r15 - this.pinchStartDistance) / AndroidUtilities.dp(100.0f);
        this.pinchStartDistance = r15;
        float r16 = this.cameraZoom + r33;
        this.cameraZoom = r16;
        if (r16 >= 0.0f) goto L46;
        this.cameraZoom = 0.0f;
    L48:
        this.zoomControlView.setZoom(this.cameraZoom, false);
        this.parentAlert.getSheetContainer().invalidate();
        this.cameraView.setZoom(this.cameraZoom);
        showZoomControls(true, true);
        goto L85
    L46:
        if (r16 <= 1.0f) goto L48;
        this.cameraZoom = 1.0f;
        goto L48
    L67:
        if (r20.getActionMasked() != 3) goto L69;
    L72:
        this.pressed = false;
        this.zooming = false;
        if (this.dragging == false) goto L80;
        this.dragging = false;
        CameraViewInternal r17 = this.cameraView;
        if (r17 == null) goto L85;
        if (Math.abs(r17.getTranslationY()) <= (this.cameraView.getMeasuredHeight() / 6.0f)) goto L79;
        closeCamera(true);
        goto L85
    L79:
        AnimatorSet r18 = new AnimatorSet();
        r18.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.cameraView, View.TRANSLATION_Y, new float[]{0.0f}), ObjectAnimator.ofFloat(this.cameraPanel, r10, new float[]{1.0f}), ObjectAnimator.ofFloat(this.counterTextView, r10, new float[]{1.0f}), ObjectAnimator.ofFloat(this.flashModeButton[0], r10, new float[]{1.0f}), ObjectAnimator.ofFloat(this.flashModeButton[1], r10, new float[]{1.0f}), ObjectAnimator.ofFloat(this.cameraPhotoRecyclerView, r10, new float[]{1.0f})});
        r18.setDuration(250);
        r18.setInterpolator(this.interpolator);
        r18.start();
        this.cameraPanel.setTag(null);
        goto L85
    L80:
        CameraViewInternal r2 = this.cameraView;
        if (r2 == null) goto L85;
        if (this.zoomWas == true) goto L85;
        r2.getLocationOnScreen(this.viewPosition);
        this.cameraView.focusToPoint((int) (r20.getRawX() - this.viewPosition[0]), (int) (r20.getRawY() - this.viewPosition[1]));
        goto L85
    L69:
        if (r20.getActionMasked() == 1) goto L72;
        if (r20.getActionMasked() != 6) goto L85;
        goto L72
    }

    private void requestGalleryPermission() {
        int r0 = Build.VERSION.SDK_INT;     // Catch: Exception -> L9
        ChatAttachAlert r5 = this.parentAlert;
        if (r0 < 33) goto L7;
        r5.baseFragment.getParentActivity().requestPermissions(new String[]{"android.permission.READ_MEDIA_VIDEO", "android.permission.READ_MEDIA_IMAGES"}, 4);     // Catch: Exception -> L9
        return;
    L7:
        r5.baseFragment.getParentActivity().requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 4);     // Catch: Exception -> L9
        return;
    }

    private void resetRecordState() {
        if (this.parentAlert.destroyed == false) goto L5;
        return;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= 2) goto L9;
        this.flashModeButton[r1].animate().alpha(1.0f).translationX(0.0f).setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        r1 = r1 + 1;
        goto L7
    L9:
        ViewPropertyAnimator r12 = this.switchCameraButton.animate().alpha(1.0f).translationX(0.0f).setDuration(150);
        CubicBezierInterpolator r2 = CubicBezierInterpolator.DEFAULT;
        r12.setInterpolator(r2).start();
        this.tooltipTextView.animate().alpha(1.0f).setDuration(150).setInterpolator(r2).start();
        AndroidUtilities.updateViewVisibilityAnimated(this.recordTime, false);
        AndroidUtilities.cancelRunOnUIThread(this.videoRecordRunnable);
        this.videoRecordRunnable = null;
        AndroidUtilities.unlockOrientation(AndroidUtilities.findActivity(getContext()));
    }

    private void resumeCameraPreview() {
        checkCamera(false);     // Catch: Exception -> L7
        if (this.cameraView == null) goto L12;
        CameraController.getInstance().startPreview(this.cameraView.getCameraSessionObject());     // Catch: Exception -> L7
        return;
    L12:
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
    }

    private void saveLastCameraBitmap() {
        if (this.canSaveCameraPreview == false) goto L18;
        Bitmap r1 = this.cameraView.getTextureView().getBitmap();     // Catch: Throwable -> L13
        if (r1 == null) goto L16;
        Bitmap r8 = Bitmap.createBitmap(r1, 0, 0, r1.getWidth(), r1.getHeight(), this.cameraView.getMatrix(), true);     // Catch: Throwable -> L13
        r1.recycle();     // Catch: Throwable -> L13
        Bitmap r0 = Bitmap.createScaledBitmap(r8, 80, (int) (r8.getHeight() / (r8.getWidth() / 80.0f)), true);     // Catch: Throwable -> L13
        if (r0 == null) goto L17;
        if (r0 == r8) goto L11;
        r8.recycle();     // Catch: Throwable -> L13
    L11:
        Utilities.blurBitmap(r0, 7);     // Catch: Throwable -> L13
        FileOutputStream r12 = new FileOutputStream(new File(ApplicationLoader.getFilesDirFixed(), "cthumb.jpg"));     // Catch: Throwable -> L13
        r0.compress(Bitmap.CompressFormat.JPEG, 87, r12);     // Catch: Throwable -> L13
        r0.recycle();     // Catch: Throwable -> L13
        r12.close();     // Catch: Throwable -> L13
        return;
    L17:
        return;
    L16:
        return;
    L19:
        return;
    }

    private void setCameraFlashModeIcon(ImageView r2, String r3) {
        r3.getClass();
        char r0 = 65535;
        switch(r3.hashCode()) {
            case 3551: goto L14;
            case 109935: goto L10;
            case 3005871: goto L6;
            default: goto L17;
        };
    L17:
        switch(r0) {
            case 0: goto L23;
            case 1: goto L21;
            case 2: goto L19;
            default: goto L18;
        };
    L18:
        return;
    L19:
        r2.setImageResource(R.drawable.flash_auto);
        r2.setContentDescription(LocaleController.getString(R.string.AccDescrCameraFlashAuto));
        return;
    L21:
        r2.setImageResource(R.drawable.flash_off);
        r2.setContentDescription(LocaleController.getString(R.string.AccDescrCameraFlashOff));
        return;
    L23:
        r2.setImageResource(R.drawable.flash_on);
        r2.setContentDescription(LocaleController.getString(R.string.AccDescrCameraFlashOn));
        return;
    L6:
        if (r3.equals("auto") == false) goto L17;
        r0 = 2;
        goto L17
    L10:
        if (r3.equals("off") == false) goto L17;
        r0 = 1;
        goto L17
    L14:
        if (r3.equals("on") == false) goto L17;
        r0 = 0;
        goto L17
    }

    private void setCurrentSpoilerVisible(int r3, boolean r4) {
        PhotoViewer r0 = PhotoViewer.getInstance();
        if (r3 != (-1)) goto L5;
        r3 = r0.getCurrentIndex();
    L5:
        List<Object> r02 = r0.getImagesArrLocals();
        if (r02 != null) goto L8;
        return;
    L8:
        if (r02.isEmpty() == false) goto L10;
        return;
    L10:
        if (r3 < r02.size()) goto L12;
        return;
    L12:
        if ((r02.get(r3) instanceof MediaController.PhotoEntry) == true) goto L14;
        return;
    L14:
        if (((MediaController.PhotoEntry) r02.get(r3)).hasSpoiler == false) goto L21;
        MediaController.PhotoEntry r32 = (MediaController.PhotoEntry) r02.get(r3);
        this.gridView.forAllChild(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda29(this, r32, r4));
        return;
    }

    private boolean shouldLoadAllMedia() {
        if (this.includeVideosInGallery == true) goto L15;
        ChatAttachAlert r1 = this.parentAlert;
        if (r1.isPhotoPicker == false) goto L7;
        return false;
    L7:
        if ((r1.baseFragment instanceof ChatActivity) == false) goto L9;
        return true;
    L9:
        if (r1.storyMediaPicker == false) goto L11;
        return true;
    L11:
        if (r1.avatarPicker == 2) goto L20;
        return false;
    L20:
        return true;
    L15:
        return true;
    }

    private void showZoomControls(boolean r8, boolean r9) {
        if (this.zoomControlView.getTag() == null) goto L6;
        if (r8 == false) goto L6;
    L8:
        if (r8 == false) goto L29;
        Runnable r82 = this.zoomControlHideRunnable;
        if (r82 == null) goto L12;
        AndroidUtilities.cancelRunOnUIThread(r82);
    L12:
        ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda6 r83 = new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda6(this);
        this.zoomControlHideRunnable = r83;
        AndroidUtilities.runOnUIThread(r83, 2000);
        return;
    L29:
        return;
    L6:
        if (this.zoomControlView.getTag() != null) goto L14;
        if (r8 == false) goto L8;
    L14:
        AnimatorSet r92 = this.zoomControlAnimation;
        if (r92 == null) goto L17;
        r92.cancel();
    L17:
        ZoomControlView r93 = this.zoomControlView;
        if (r8 == false) goto L20;
        Integer r3 = 1;
    L21:
        r93.setTag(r3);
        AnimatorSet r94 = new AnimatorSet();
        this.zoomControlAnimation = r94;
        r94.setDuration(180);
        AnimatorSet r95 = this.zoomControlAnimation;
        ZoomControlView r32 = this.zoomControlView;
        if (r8 == false) goto L24;
        float r4 = 1.0f;
    L25:
        r95.playTogether(new Animator[]{ObjectAnimator.ofFloat(r32, View.ALPHA, new float[]{r4})});
        this.zoomControlAnimation.addListener(new AnonymousClass16(this));
        this.zoomControlAnimation.start();
        if (r8 == false) goto L30;
        ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda7 r84 = new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda7(this);
        this.zoomControlHideRunnable = r84;
        AndroidUtilities.runOnUIThread(r84, 2000);
        return;
    L30:
        return;
    L24:
        r4 = 0.0f;
        goto L25
    L20:
        r3 = null;
        goto L21
    }

    private void updateAlbumsDropDown() {
        this.dropDownContainer.removeAllSubItems();
        if (this.mediaEnabled == true) goto L5;
        this.dropDownAlbums = new ArrayList();
    L10:
        boolean r0 = this.dropDownAlbums.isEmpty();
        TextView r1 = this.dropDown;
        if (r0 == false) goto L14;
        r1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        return;
    L14:
        r1.setCompoundDrawablesWithIntrinsicBounds(null, null, this.dropDownDrawable, null);
        int r02 = this.dropDownAlbums.size();
        int r12 = 0;
    L15:
        if (r12 >= r02) goto L17;
        MediaController.AlbumEntry r2 = this.dropDownAlbums.get(r12);
        AlbumButton r3 = new AlbumButton(getContext(), r2.coverPhoto, r2.bucketName, r2.photos.size(), this.resourcesProvider);
        this.dropDownContainer.getPopupLayout().addView(r3);
        r3.setOnClickListener(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda4(this, r12 + 10));
        r12 = r12 + 1;
        goto L15
    L17:
        return;
    L5:
        if (shouldLoadAllMedia() == false) goto L7;
        ArrayList<MediaController.AlbumEntry> r03 = MediaController.allMediaAlbums;
    L8:
        ArrayList<MediaController.AlbumEntry> r13 = new ArrayList(r03);
        this.dropDownAlbums = r13;
        Collections.sort(r13, new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda3(r03));
        goto L10
    L7:
        r03 = MediaController.allPhotoAlbums;
        goto L8
    }

    private void updateCells() {
        if (this.gridView == null) goto L11;
        int r0 = 0;
    L6:
        if (r0 >= this.gridView.getChildCount()) goto L15;
        View r1 = this.gridView.getChildAt(r0);
        if ((r1 instanceof PhotoAttachPhotoCell) == false) goto L10;
        ((PhotoAttachPhotoCell) r1).imageView.invalidate();
    L10:
        r0 = r0 + 1;
        goto L6
    L15:
        return;
    }

    private void updatePhotoStarsPrice() {
        this.gridView.forAllChild(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda0());
    }

    private void updateStarsItem() {
        if (this.starsItem != null) goto L5;
        return;
    L5:
        long r0 = getStarsPrice();
        ActionBarMenuSubItem r3 = this.starsItem;
        if (r0 <= 0) goto L9;
        r3.setText(LocaleController.getString(R.string.PaidMediaPriceButton));
        this.starsItem.setSubtext(LocaleController.formatPluralString("Stars", (int) r0, new Object[0]));
        return;
    L9:
        r3.setText(LocaleController.getString(R.string.PaidMediaButton));
        this.starsItem.setSubtext(null);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void applyCaption(CharSequence r6) {
        int r0 = 0;
    L4:
        if (r0 >= selectedPhotosOrder.size()) goto L14;
        if (r0 != 0) goto L13;
        Object r1 = selectedPhotosOrder.get(r0);
        Object r2 = selectedPhotos.get(r1);
        if ((r2 instanceof MediaController.PhotoEntry) == false) goto L9;
        MediaController.PhotoEntry r22 = ((MediaController.PhotoEntry) r2).m4368clone();
        CharSequence[] r3 = {r6};
        r22.entities = MediaDataController.getInstance(UserConfig.selectedAccount).getEntities(r3, false);
        r22.caption = r3[0];
        Object r23 = r22;
    L12:
        selectedPhotos.put(r1, r23);
        goto L13
    L9:
        boolean r32 = r2 instanceof MediaController.SearchImage;
        r23 = r2;
        if (r32 == false) goto L12;
        MediaController.SearchImage r24 = ((MediaController.SearchImage) r2).m4369clone();
        CharSequence[] r33 = {r6};
        r24.entities = MediaDataController.getInstance(UserConfig.selectedAccount).getEntities(r33, false);
        r24.caption = r33[0];
        r23 = r24;
    L13:
        r0 = r0 + 1;
        goto L4
    }

    public boolean areLivePhotosEnabled() {
        if (selectedPhotos.isEmpty() == false) goto L5;
        return false;
    L5:
        Iterator<Map.Entry<Object, Object>> r3 = selectedPhotos.entrySet().iterator();
    L7:
        if (r3.hasNext() == false) goto L15;
        Map.Entry<Object, Object> r1 = r3.next();
        if ((r1.getValue() instanceof MediaController.PhotoEntry) == false) goto L7;
        MediaController.PhotoEntry r12 = (MediaController.PhotoEntry) r1.getValue();
        if (r12.isLivePhoto() == false) goto L7;
        if (r12.isUnalivePhoto() == false) goto L7;
        return false;
    L15:
        return true;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean canDismissWithTouchOutside() {
        return !this.cameraOpened;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean canScheduleMessages() {
        Iterator<Map.Entry<Object, Object>> r2 = selectedPhotos.entrySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L16;
        Object r0 = r2.next().getValue();
        if ((r0 instanceof MediaController.PhotoEntry) == true) goto L8;
        if ((r0 instanceof MediaController.SearchImage) == false) goto L4;
        if (((MediaController.SearchImage) r0).ttl == 0) goto L4;
        return false;
    L8:
        if (((MediaController.PhotoEntry) r0).ttl == 0) goto L4;
        return false;
    L16:
        return true;
    }

    public boolean captionForAllMedia() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= selectedPhotosOrder.size()) goto L17;
        Object r2 = selectedPhotos.get(selectedPhotosOrder.get(r0));
        if ((r2 instanceof MediaController.PhotoEntry) == false) goto L9;
        CharSequence r22 = ((MediaController.PhotoEntry) r2).caption;
    L13:
        if (TextUtils.isEmpty(r22) == true) goto L15;
        r1 = r1 + 1;
    L15:
        r0 = r0 + 1;
        goto L4
    L9:
        if ((r2 instanceof MediaController.SearchImage) == false) goto L11;
        r22 = ((MediaController.SearchImage) r2).caption;
        goto L13
    L11:
        r22 = null;
        goto L13
    L17:
        if (r1 > 1) goto L19;
        return true;
    L19:
        return false;
    }

    public void checkCamera(boolean r10) {
        ChatAttachAlert r0 = this.parentAlert;
        if (r0.destroyed == false) goto L5;
        return;
    L5:
        if (this.needCamera == false) goto L64;
        boolean r1 = this.deviceHasGoodCamera;
        boolean r2 = this.noCameraPermissions;
        BaseFragment r02 = r0.baseFragment;
        if (r02 != null) goto L10;
        r02 = LaunchActivity.getLastFragment();
    L10:
        if (r02 != null) goto L12;
        return;
    L12:
        if (r02.getParentActivity() != null) goto L15;
        return;
    L15:
        if (SharedConfig.inAppCamera == true) goto L18;
        this.deviceHasGoodCamera = false;
    L36:
        if (r1 == this.deviceHasGoodCamera) goto L38;
    L39:
        PhotoAttachAdapter r102 = this.adapter;
        if (r102 == null) goto L42;
        r102.notifyDataSetChanged();
    L42:
        ChatAttachAlert r103 = this.parentAlert;
        if (r103.destroyed == true) goto L54;
        if (r103.isShowing() == false) goto L54;
        if (this.deviceHasGoodCamera == false) goto L54;
        if (this.parentAlert.getBackDrawable().getAlpha() == 0) goto L54;
        if (this.cameraOpened == true) goto L54;
        showCamera();
    L54:
        if (r1 == this.deviceHasGoodCamera) goto L63;
        this.parentAlert.onCameraStateChanged();
        return;
    L63:
        return;
    L38:
        if (r2 == this.noCameraPermissions) goto L42;
    L18:
        if (r02.getParentActivity().checkSelfPermission("android.permission.CAMERA") == 0) goto L20;
        boolean r03 = true;
    L21:
        this.noCameraPermissions = r03;
        if (r03 == false) goto L30;
        if (r10 == true) goto L59;
    L29:
        this.deviceHasGoodCamera = false;
        goto L36
    L59:
        int r104 = Build.VERSION.SDK_INT;     // Catch: Exception -> L57
        ChatAttachAlert r04 = this.parentAlert;
        if (r104 < 33) goto L28;
        r04.baseFragment.getParentActivity().requestPermissions(new String[]{"android.permission.CAMERA", "android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO"}, 17);     // Catch: Exception -> L57
    L58:
        goto L29
    L28:
        r04.baseFragment.getParentActivity().requestPermissions(new String[]{"android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE"}, 17);     // Catch: Exception -> L57
        goto L58
    L30:
        if (r10 == false) goto L32;
    L33:
        CameraController.getInstance().initCamera(null);
    L34:
        this.deviceHasGoodCamera = CameraController.getInstance().isCameraInitied();
        goto L36
    L32:
        if (SharedConfig.hasCameraCache == false) goto L34;
    L20:
        r03 = false;
        goto L21
    }

    public void checkCameraViewPosition() {
        if (PhotoViewer.hasInstance() == true) goto L5;
    L9:
        CameraViewInternal r0 = this.cameraView;
        if (r0 == null) goto L12;
        r0.invalidateOutline();
    L12:
        RecyclerView.ViewHolder r02 = this.gridView.findViewHolderForAdapterPosition(this.itemsPerRow - 1);
        if (r02 == null) goto L16;
        r02.itemView.invalidateOutline();
    L16:
        if (PhotoAttachAdapter.m9774$$Nest$fgetneedCamera(this.adapter) == true) goto L18;
    L25:
        RecyclerView.ViewHolder r03 = this.gridView.findViewHolderForAdapterPosition(0);
        if (r03 == null) goto L28;
        r03.itemView.invalidateOutline();
    L28:
        CameraViewInternal r04 = this.cameraView;
        if (r04 == null) goto L31;
        r04.invalidate();
    L31:
        TextView r05 = this.recordTime;
        if (r05 == null) goto L39;
        ViewGroup.MarginLayoutParams r06 = (ViewGroup.MarginLayoutParams) r05.getLayoutParams();
        if (getRootWindowInsets() != null) goto L36;
        int r3 = AndroidUtilities.dp(16.0f);
    L37:
        r06.topMargin = r3;
        goto L39
    L36:
        r3 = getRootWindowInsets().getSystemWindowInsetTop() + AndroidUtilities.dp(2.0f);
    L39:
        if (this.deviceHasGoodCamera == true) goto L41;
        return;
    L41:
        if (ExteraConfig.getHideCameraTile() == false) goto L43;
        return;
    L43:
        if (this.noCameraPermissions == true) goto L95;
        int r07 = this.gridView.getChildCount();
        int r32 = 0;
    L47:
        if (r32 >= r07) goto L82;
        View r5 = this.gridView.getChildAt(r32);
        if ((r5 instanceof PhotoAttachCameraCell) == true) goto L51;
        r32 = r32 + 1;
        goto L47
    L51:
        if (r5.isAttachedToWindow() == false) goto L82;
        float r08 = (r5.getY() + this.gridView.getY()) + getY();
        float r33 = this.parentAlert.getSheetContainer().getY() + r08;
        float r6 = (((r5.getX() + this.gridView.getX()) + getX()) + this.parentAlert.getSheetContainer().getX()) - getRootWindowInsets().getSystemWindowInsetLeft();
        if (this.parentAlert.inBubbleMode == true) goto L56;
        int r7 = AndroidUtilities.statusBarHeight;
    L57:
        float r72 = (r7 + ActionBar.getCurrentActionBarHeight()) + (this.parentAlert.topCommentContainer.getMeasuredHeight() * this.parentAlert.topCommentContainer.getAlpha());
        MentionsContainerView r8 = this.parentAlert.mentionContainer;
        if (r8 == null) goto L63;
        if (r8.isReversed() == false) goto L63;
        r72 = Math.max(r72, (this.parentAlert.mentionContainer.getY() + this.parentAlert.mentionContainer.clipTop()) - this.parentAlert.currentPanTranslationY);
    L63:
        if (r08 >= r72) goto L65;
        float r73 = r72 - r08;
    L67:
        if (r73 == this.cameraViewOffsetY) goto L71;
        this.cameraViewOffsetY = r73;
        CameraViewInternal r74 = this.cameraView;
        if (r74 == null) goto L71;
        r74.invalidateOutline();
        this.cameraView.invalidate();
    L71:
        float r75 = (int) ((this.parentAlert.getSheetContainer().getMeasuredHeight() - this.parentAlert.buttonsRecyclerView.getMeasuredHeight()) + this.parentAlert.buttonsRecyclerView.getTranslationY());
        MentionsContainerView r82 = this.parentAlert.mentionContainer;
        if (r82 == null) goto L75;
        r75 = r75 - (r82.clipBottom() - AndroidUtilities.dp(6.0f));
    L75:
        if ((r5.getMeasuredHeight() + r08) <= r75) goto L77;
        this.cameraViewOffsetBottomY = Math.min(-AndroidUtilities.dp(5.0f), r08 - r75) + r5.getMeasuredHeight();
    L78:
        float[] r09 = this.cameraViewLocation;
        r09[0] = r6;
        r09[1] = r33;
        applyCameraViewPosition();
        return;
    L77:
        this.cameraViewOffsetBottomY = 0.0f;
        goto L78
    L65:
        r73 = 0.0f;
        goto L67
    L56:
        r7 = 0;
    L82:
        if (this.cameraViewOffsetY == 0.0f) goto L84;
    L85:
        this.cameraViewOffsetX = 0.0f;
        this.cameraViewOffsetY = 0.0f;
        CameraViewInternal r010 = this.cameraView;
        if (r010 == null) goto L88;
        r010.invalidateOutline();
        this.cameraView.invalidate();
    L88:
        this.cameraViewLocation[0] = AndroidUtilities.dp(-400.0f);
        this.cameraViewLocation[1] = 0.0f;
        applyCameraViewPosition();
        return;
    L84:
        if (this.cameraViewOffsetX == 0.0f) goto L88;
    L95:
        return;
    L18:
        if (this.deviceHasGoodCamera == false) goto L25;
        if (this.selectedAlbumEntry != this.galleryAlbumEntry) goto L25;
        if (ExteraConfig.getHideCameraTile() == true) goto L25;
        if (this.noCameraPermissions == false) goto L28;
    L5:
        if (PhotoViewer.getInstance().stickerMakerView == null) goto L9;
        if (PhotoViewer.getInstance().stickerMakerView.isThanosInProgress == false) goto L9;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void checkColors() {
        if (this.forceDarkTheme == false) goto L5;
        int r0 = Theme.key_voipgroup_actionBarItems;
    L6:
        this.progressView.setTextColor(getThemedColor(Theme.key_emptyListPlaceholder));
        this.gridView.setGlowColor(getThemedColor(Theme.key_dialogScrollGlow));
        this.gridView.findViewHolderForAdapterPosition(0);
        this.dropDown.setTextColor(getThemedColor(r0));
        ActionBarMenuItem r1 = this.dropDownContainer;
        if (this.forceDarkTheme == false) goto L9;
        int r3 = Theme.key_voipgroup_actionBarItems;
    L10:
        r1.setPopupItemsColor(getThemedColor(r3), false);
        ActionBarMenuItem r12 = this.dropDownContainer;
        if (this.forceDarkTheme == false) goto L13;
        int r2 = Theme.key_voipgroup_actionBarItems;
    L14:
        r12.setPopupItemsColor(getThemedColor(r2), true);
        ActionBarMenuItem r13 = this.dropDownContainer;
        if (this.forceDarkTheme == false) goto L17;
        int r22 = Theme.key_voipgroup_actionBarUnscrolled;
    L18:
        r13.redrawPopup(getThemedColor(r22));
        Theme.setDrawableColor(this.dropDownDrawable, getThemedColor(r0));
        return;
    L17:
        r22 = Theme.key_actionBarDefaultSubmenuBackground;
        goto L18
    L13:
        r2 = Theme.key_actionBarDefaultSubmenuItem;
        goto L14
    L9:
        r3 = Theme.key_actionBarDefaultSubmenuItem;
        goto L10
    L5:
        r0 = Theme.key_dialogTextBlack;
        goto L6
    }

    public void checkStorage() {
        if (this.noGalleryPermissions == false) goto L9;
        boolean r0 = isNoGalleryPermissions();
        this.noGalleryPermissions = r0;
        if (r0 == true) goto L7;
        loadGalleryPhotos();
    L7:
        this.adapter.notifyDataSetChanged();
        this.cameraAttachAdapter.notifyDataSetChanged();
        return;
    }

    public void clearSelectedPhotos() {
        this.spoilerItem.setText(LocaleController.getString(R.string.EnablePhotoSpoiler));
        this.spoilerItem.setAnimatedIcon(R.raw.photo_spoiler);
        this.parentAlert.selectedMenuItem.showSubItem(1);
        if (selectedPhotos.isEmpty() == true) goto L10;
        Iterator<Map.Entry<Object, Object>> r0 = selectedPhotos.entrySet().iterator();
    L6:
        if (r0.hasNext() == false) goto L8;
        ((MediaController.PhotoEntry) r0.next().getValue()).reset();
        goto L6
    L8:
        selectedPhotos.clear();
        selectedPhotosOrder.clear();
    L10:
        if (cameraPhotos.isEmpty() == true) goto L21;
        int r02 = cameraPhotos.size();
        int r1 = 0;
    L12:
        if (r1 >= r02) goto L20;
        MediaController.PhotoEntry r2 = (MediaController.PhotoEntry) cameraPhotos.get(r1);
        new File(r2.path).delete();
        if (r2.imagePath == null) goto L17;
        new File(r2.imagePath).delete();
    L17:
        if (r2.thumbPath == null) goto L19;
        new File(r2.thumbPath).delete();
    L19:
        r1 = r1 + 1;
        goto L12
    L20:
        cameraPhotos.clear();
    L21:
        this.adapter.notifyDataSetChanged();
        this.cameraAttachAdapter.notifyDataSetChanged();
    }

    public void closeCamera(boolean r9) {
        if (this.takingPhoto == false) goto L5;
        return;
    L5:
        if (this.cameraView == null) goto L61;
        int[] r0 = this.animateCameraValues;
        int r1 = this.itemSize;
        r0[1] = r1;
        r0[2] = (r1 * 2) + AndroidUtilities.dp(2.0f);
        Runnable r02 = this.zoomControlHideRunnable;
        if (r02 == null) goto L10;
        AndroidUtilities.cancelRunOnUIThread(r02);
        this.zoomControlHideRunnable = null;
    L10:
        ChatAttachAlert r03 = this.parentAlert;
        if (AndroidUtilities.computePerceivedBrightness(getThemedColor(Theme.key_windowBackgroundGray)) <= 0.721d) goto L13;
        boolean r4 = true;
    L14:
        AndroidUtilities.setLightNavigationBar(r03, r4);
        if (r9 == false) goto L30;
        this.additionCloseCameraY = this.cameraView.getTranslationY();
        this.cameraAnimationInProgress = true;
        RecyclerListView r12 = this.gridView;
        if (r12 == null) goto L19;
        r12.invalidate();
    L19:
        ArrayList r13 = new ArrayList();
        if (ExteraConfig.getHideCameraTile() == false) goto L22;
        setCameraOpenProgress(0.0f);
    L23:
        Property r7 = View.ALPHA;
        r13.add(ObjectAnimator.ofFloat(this.cameraPanel, r7, new float[]{0.0f}));
        r13.add(ObjectAnimator.ofFloat(this.zoomControlView, r7, new float[]{0.0f}));
        r13.add(ObjectAnimator.ofFloat(this.counterTextView, r7, new float[]{0.0f}));
        r13.add(ObjectAnimator.ofFloat(this.cameraPhotoRecyclerView, r7, new float[]{0.0f}));
        int r42 = 0;
    L24:
        if (r42 >= 2) goto L29;
        if (this.flashModeButton[r42].getVisibility() == 0) goto L27;
        r42 = r42 + 1;
        goto L24
    L27:
        r13.add(ObjectAnimator.ofFloat(this.flashModeButton[r42], r7, new float[]{0.0f}));
    L29:
        this.notificationsLocker.lock();
        AnimatorSet r04 = new AnimatorSet();
        r04.playTogether(r13);
        r04.setDuration(220);
        r04.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r04.addListener(new AnonymousClass20(this));
        r04.start();
    L43:
        CameraViewInternal r05 = this.cameraView;
        if (r05 == null) goto L46;
        r05.setImportantForAccessibility(0);
    L46:
        this.gridView.setImportantForAccessibility(0);
        if (LiteMode.isEnabled(LiteMode.FLAGS_CHAT) == true) goto L49;
    L50:
        CameraViewInternal r8 = this.cameraView;
        if (r8 == null) goto L60;
        r8.showTexture(false, r9);
        return;
    L60:
        return;
    L49:
        if (ExteraConfig.getHideCameraTile() == true) goto L50;
        return;
    L22:
        r13.add(ObjectAnimator.ofFloat(this, "cameraOpenProgress", new float[]{0.0f}));
        goto L23
    L30:
        this.cameraExpanded = false;
        this.parentAlert.getWindow().clearFlags(128);
        setCameraOpenProgress(0.0f);
        this.animateCameraValues[0] = 0;
        setCameraOpenProgress(0.0f);
        this.cameraPanel.setAlpha(0.0f);
        this.cameraPanel.setVisibility(8);
        this.zoomControlView.setAlpha(0.0f);
        this.zoomControlView.setTag(null);
        this.zoomControlView.setVisibility(8);
        this.cameraPhotoRecyclerView.setAlpha(0.0f);
        this.counterTextView.setAlpha(0.0f);
        this.cameraPhotoRecyclerView.setVisibility(8);
        int r14 = 0;
    L31:
        if (r14 >= 2) goto L36;
        if (this.flashModeButton[r14].getVisibility() == 0) goto L34;
        r14 = r14 + 1;
        goto L31
    L34:
        this.flashModeButton[r14].setAlpha(0.0f);
    L36:
        this.cameraOpened = false;
        CameraViewInternal r06 = this.cameraView;
        if (r06 == null) goto L39;
        r06.setFpsLimit(30);
        this.cameraView.setSystemUiVisibility(1024);
    L39:
        RecyclerListView r07 = this.gridView;
        if (r07 == null) goto L42;
        r07.invalidate();
    L42:
        this.parentAlert.onCameraStateChanged();
        goto L43
    L13:
        r4 = false;
        goto L14
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        int r62 = 0;
        if (r4 != NotificationCenter.albumsDidLoad) goto L45;
        if (this.adapter != null) goto L7;
        return;
    L7:
        if (shouldLoadAllMedia() == false) goto L9;
        this.galleryAlbumEntry = MediaController.allMediaAlbumEntry;
    L11:
        if (this.selectedAlbumEntry == null) goto L28;
        ChatAttachAlert r42 = this.parentAlert;
        if (r42 == null) goto L18;
        if (r42.isStickerMode == true) goto L28;
    L18:
        if (shouldLoadAllMedia() == false) goto L29;
        int r43 = 0;
    L21:
        if (r43 >= MediaController.allMediaAlbums.size()) goto L29;
        MediaController.AlbumEntry r52 = MediaController.allMediaAlbums.get(r43);
        int r0 = r52.bucketId;
        MediaController.AlbumEntry r1 = this.selectedAlbumEntry;
        if (r0 != r1.bucketId) goto L27;
        if (r52.videoOnly != r1.videoOnly) goto L27;
        this.selectedAlbumEntry = r52;
    L27:
        r43 = r43 + 1;
    L29:
        this.loading = false;
        this.progressView.showTextView();
        this.adapter.notifyDataSetChanged();
        this.cameraAttachAdapter.notifyDataSetChanged();
        if (selectedPhotosOrder.isEmpty() == false) goto L32;
    L42:
        updateAlbumsDropDown();
        return;
    L32:
        if (this.galleryAlbumEntry == null) goto L42;
        int r44 = selectedPhotosOrder.size();
    L34:
        if (r62 >= r44) goto L42;
        Integer r53 = (Integer) selectedPhotosOrder.get(r62);
        Object r02 = selectedPhotos.get(r53);
        MediaController.PhotoEntry r12 = this.galleryAlbumEntry.photosByIds.get(r53.intValue());
        if (r12 == null) goto L41;
        if ((r02 instanceof MediaController.PhotoEntry) == false) goto L40;
        r12.copyFrom((MediaController.PhotoEntry) r02);
    L40:
        selectedPhotos.put(r53, r12);
    L41:
        r62 = r62 + 1;
    L28:
        this.selectedAlbumEntry = this.galleryAlbumEntry;
        goto L29
    L9:
        this.galleryAlbumEntry = MediaController.allPhotosAlbumEntry;
        goto L11
    L45:
        if (r4 != NotificationCenter.cameraInitied) goto L56;
        checkCamera(false);
        return;
    }

    public ArrayList<Object> getAllPhotosArray() {
        if (this.selectedAlbumEntry == null) goto L11;
        if (cameraPhotos.isEmpty() == true) goto L9;
        ArrayList<Object> r0 = new ArrayList(this.selectedAlbumEntry.photos.size() + cameraPhotos.size());
        r0.addAll(cameraPhotos);
        r0.addAll(this.selectedAlbumEntry.photos);
        return r0;
    L9:
        return this.selectedAlbumEntry.photos;
    L11:
        if (cameraPhotos.isEmpty() == true) goto L15;
        return cameraPhotos;
    L15:
        return new ArrayList(0);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getButtonsHideOffset() {
        return super.getButtonsHideOffset() + ActionBar.getCurrentActionBarHeight();
    }

    @Keep
    public float getCameraOpenProgress() {
        return this.cameraOpenProgress;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCurrentItemTop() {
        int r0 = this.gridView.getChildCount();
        RecyclerListView r1 = this.gridView;
        if (r0 > 0) goto L6;
        int r02 = r1.getPaddingTop();
        this.currentItemTop = r02;
        r1.setTopGlowOffset(r02);
        this.progressView.setTranslationY(0.0f);
        return Integer.MAX_VALUE;
    L6:
        View r03 = r1.getChildAt(0);
        RecyclerListView.Holder r12 = (RecyclerListView.Holder) this.gridView.findContainingViewHolder(r03);
        int r04 = r03.getTop() - this.listAdditionalH;
        int r3 = AndroidUtilities.dp(7.0f);
        if (r04 < AndroidUtilities.dp(7.0f)) goto L12;
        if (r12 == null) goto L12;
        if (r12.getAdapterPosition() != 0) goto L12;
    L13:
        this.progressView.setTranslationY(r04 + ((((getMeasuredHeight() - r04) - AndroidUtilities.dp(50.0f)) - this.progressView.getMeasuredHeight()) / 2.0f));
        this.gridView.setTopGlowOffset(r04);
        this.currentItemTop = r04;
        return r04;
    L12:
        r04 = r3;
        goto L13
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getFirstOffset() {
        return getListTopPadding() + AndroidUtilities.dp(56.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getListTopPadding() {
        return this.gridView.getPaddingTop();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getSelectedItemsCount() {
        return selectedPhotosOrder.size();
    }

    public HashMap<Object, Object> getSelectedPhotos() {
        return selectedPhotos;
    }

    public int getSelectedPhotosCount() {
        Iterator<Object> r3 = selectedPhotos.values().iterator();
        int r0 = 0;
    L4:
        if (r3.hasNext() == false) goto L12;
        Object r1 = r3.next();
        if ((r1 instanceof MediaController.PhotoEntry) == false) goto L4;
        MediaController.PhotoEntry r12 = (MediaController.PhotoEntry) r1;
        if (r12.isVideo == true) goto L4;
        if (r12.editedInfo != null) goto L4;
        r0 = r0 + 1;
        goto L4
    L12:
        return r0;
    }

    public int getSelectedPhotosHighQualityCount() {
        Iterator<Object> r3 = selectedPhotos.values().iterator();
        int r0 = 0;
    L4:
        if (r3.hasNext() == false) goto L14;
        Object r1 = r3.next();
        if ((r1 instanceof MediaController.PhotoEntry) == false) goto L4;
        MediaController.PhotoEntry r12 = (MediaController.PhotoEntry) r1;
        if (r12.isHighQuality() == false) goto L4;
        if (r12.isVideo == true) goto L4;
        if (r12.editedInfo != null) goto L4;
        r0 = r0 + 1;
        goto L4
    L14:
        return r0;
    }

    public ArrayList<Object> getSelectedPhotosOrder() {
        return selectedPhotosOrder;
    }

    public long getStarsPrice() {
        Iterator<Map.Entry<Object, Object>> r2 = selectedPhotos.entrySet().iterator();
        if (r2.hasNext() == true) goto L5;
        return 0;
    L5:
        return ((MediaController.PhotoEntry) r2.next().getValue()).starsAmount;
    }

    public boolean hasLivePhotos() {
        if (selectedPhotos.isEmpty() == false) goto L5;
        return false;
    L5:
        Iterator<Map.Entry<Object, Object>> r3 = selectedPhotos.entrySet().iterator();
    L7:
        if (r3.hasNext() == false) goto L14;
        Map.Entry<Object, Object> r1 = r3.next();
        if ((r1.getValue() instanceof MediaController.PhotoEntry) == false) goto L7;
        if (((MediaController.PhotoEntry) r1.getValue()).isLivePhoto() == false) goto L7;
        return true;
    L14:
        return false;
    }

    public void hideCamera(boolean r3) {
        if (this.deviceHasGoodCamera == true) goto L5;
        return;
    L5:
        if (this.cameraView == null) goto L13;
        saveLastCameraBitmap();
        this.cameraViewItemDecoration.updateBitmap();
        this.cameraView.destroy(r3, null);
        AnimatorSet r32 = this.cameraInitAnimation;
        if (r32 == null) goto L10;
        r32.cancel();
        this.cameraInitAnimation = null;
    L10:
        AndroidUtilities.runOnUIThread(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda1(this), 300);
        this.canSaveCameraPreview = false;
        return;
    }

    public void loadGalleryPhotos() {
        if (shouldLoadAllMedia() == false) goto L5;
        MediaController.AlbumEntry r0 = MediaController.allMediaAlbumEntry;
    L6:
        if (r0 != null) goto L9;
        MediaController.loadGalleryPhotosAlbums(0);
        return;
    L9:
        return;
    L5:
        r0 = MediaController.allPhotosAlbumEntry;
        goto L6
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int needsActionBar() {
        return 1;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x0144 -> B:94:0x0154). Please report as a decompilation issue!!! */
    public void onActivityResultFragment(int r29, Intent r30, String r31) {
        String r7 = r31;
        if (this.parentAlert.destroyed == true) goto L107;
        mediaFromExternalCamera = true;
        if (r29 != 0) goto L16;
        PhotoViewer.getInstance().setParentActivity(this.parentAlert.baseFragment.getParentActivity(), this.resourcesProvider);
        PhotoViewer r0 = PhotoViewer.getInstance();
        ChatAttachAlert r2 = this.parentAlert;
        r0.setMaxSelectedPhotos(r2.maxSelectedPhotos, r2.allowOrder);
        Pair<Integer, Integer> r02 = AndroidUtilities.getImageOrientation(r7);
        BitmapFactory.Options r22 = new BitmapFactory.Options();     // Catch: Exception -> L12
        r22.inJustDecodeBounds = true;     // Catch: Exception -> L12
        BitmapFactory.decodeFile(new File(r7).getAbsolutePath(), r22);     // Catch: Exception -> L12
        int r3 = r22.outWidth;     // Catch: Exception -> L12
        int r11 = r22.outHeight;     // Catch: Exception -> L86
    L11:
        int r10 = r3;
        int r4 = lastImageId;
        lastImageId = r4 - 1;
        MediaController.PhotoEntry r03 = new MediaController.PhotoEntry(0, r4, 0, r7, ((Integer) r02.first).intValue(), false, r10, r11, 0).setOrientation(r02);
        r03.canDeleteAfter = true;
        openPhotoViewer(r03, false, true);
        return;
    L13:
        r11 = 0;
    L12:
        r3 = 0;
        goto L13
    L16:
        if (r29 == 2) goto L18;
        return;
    L18:
        if (BuildVars.LOGS_ENABLED == false) goto L20;
        FileLog.d("pic path " + r7);
    L20:
        MediaMetadataRetriever r23 = null;
        if (r30 == null) goto L26;
        if (r7 == null) goto L26;
        if (new File(r7).exists() == false) goto L26;
        Intent r04 = null;
    L27:
        if (r04 == null) goto L46;
        Uri r05 = r04.getData();
        if (r05 != null) goto L31;
    L39:
        String r06 = r7;
    L40:
        BaseFragment r32 = this.parentAlert.baseFragment;
        if ((r32 instanceof ChatActivity) == true) goto L43;
    L44:
        AndroidUtilities.addMediaToGallery(r7);
    L45:
        r7 = null;
    L47:
        if (r06 != null) goto L52;
        if (r7 == null) goto L52;
        if (new File(r7).exists() == false) goto L52;
    L53:
        long r33 = 0;
        MediaMetadataRetriever r5 = new MediaMetadataRetriever();     // Catch: Throwable -> L67 Exception -> L69
        r5.setDataSource(r7);     // Catch: Throwable -> L59 Exception -> L61
        if (r5.extractMetadata(9) == null) goto L63;
        r33 = (int) Math.ceil(Long.parseLong(r0) / 1000.0f);
        goto L63
    L63:
        r5.release();     // Catch: Exception -> L65
    L94:
    L73:
        Bitmap r24 = SendMessagesHelper.createVideoThumbnail(r7, 1);
        File r52 = new File(FileLoader.getDirectory(4), "-2147483648_" + SharedConfig.getLastLocalId() + ".jpg");
        r24.compress(Bitmap.CompressFormat.JPEG, 55, new FileOutputStream(r52));     // Catch: Throwable -> L76
    L78:
        SharedConfig.saveConfig();
        int r18 = lastImageId;
        lastImageId = r18 - 1;
        MediaController.PhotoEntry r16 = new MediaController.PhotoEntry(0, r18, 0, r7, 0, true, r24.getWidth(), r24.getHeight(), 0);
        r16.duration = (int) r33;
        r16.thumbPath = r52.getAbsolutePath();
        openPhotoViewer(r16, false, true);
        return;
    L76:
        th = move-exception;
        FileLog.e(th);
    L61:
        e = e;
        r23 = r5;
    L70:
        FileLog.e(e);     // Catch: Throwable -> L67
        if (r23 == null) goto L73;
        r23.release();     // Catch: Exception -> L65
    L59:
        th = move-exception;
        Throwable r1 = th;
        r23 = r5;
    L79:
        if (r23 == null) goto L103;
        r23.release();     // Catch: Exception -> L82
        throw r1;
    L82:
        e = move-exception;
        FileLog.e(e);
        throw r1;
    L103:
        throw r1;
    L69:
        e = e;
    L67:
        th = move-exception;
        r1 = th;
    L65:
        e = move-exception;
        FileLog.e(e);
    L52:
        r7 = r06;
        goto L53
    L43:
        if (((ChatActivity) r32).isSecretChat() == true) goto L45;
    L31:
        if (BuildVars.LOGS_ENABLED == false) goto L33;
        FileLog.d("video record uri " + r05.toString());
    L33:
        r06 = AndroidUtilities.getPath(r05);
        if (BuildVars.LOGS_ENABLED == false) goto L36;
        FileLog.d("resolved path = " + r06);
    L36:
        if (r06 == null) goto L39;
        if (new File(r06).exists() == true) goto L40;
    L46:
        r06 = null;
    L26:
        r04 = r30;
        goto L27
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.albumsDidLoad);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.cameraInitied);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onButtonsTranslationYUpdated() {
        checkCameraViewPosition();
        invalidate();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onContainerTranslationUpdated(float r1) {
        this.currentPanTranslationY = r1;
        checkCameraViewPosition();
        CameraViewInternal r12 = this.cameraView;
        if (r12 == null) goto L5;
        r12.invalidateOutline();
        this.cameraView.invalidate();
    L5:
        invalidate();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean onContainerViewTouchEvent(MotionEvent r2) {
        if (this.cameraAnimationInProgress == false) goto L7;
        return true;
    L7:
        if (this.cameraOpened == true) goto L9;
        return false;
    L9:
        return processTouchEvent(r2);
    }

    public boolean onCustomLayout(View r9, int r10, int r11, int r12, int r13) {
        int r0 = r12 - r10;
        int r112 = r13 - r11;
        if (r0 >= r112) goto L5;
        boolean r3 = true;
    L6:
        int r4 = AndroidUtilities.navigationBarHeight;
        if (r9 != this.cameraPanel) goto L20;
        RecyclerListView r92 = this.cameraPhotoRecyclerView;
        if (r3 == false) goto L14;
        int r93 = r92.getVisibility();
        FrameLayout r8 = this.cameraPanel;
        if (r93 != 0) goto L13;
        r8.layout(0, (r13 - AndroidUtilities.dp(222.0f)) - r4, r0, (r13 - AndroidUtilities.dp(96.0f)) - r4);
    L18:
        return true;
    L13:
        r8.layout(0, (r13 - AndroidUtilities.dp(126.0f)) - r4, r0, r13 - r4);
        goto L18
    L14:
        int r94 = r92.getVisibility();
        FrameLayout r82 = this.cameraPanel;
        if (r94 != 0) goto L17;
        r82.layout((r12 - AndroidUtilities.dp(222.0f)) - r4, 0, r12 - AndroidUtilities.dp(96.0f), r112 - r4);
        goto L18
    L17:
        r82.layout((r12 - AndroidUtilities.dp(126.0f)) - r4, 0, r12, r112 - r4);
        goto L18
    L20:
        if (r9 != this.zoomControlView) goto L32;
        RecyclerListView r95 = this.cameraPhotoRecyclerView;
        if (r3 == false) goto L27;
        int r96 = r95.getVisibility();
        ZoomControlView r83 = this.zoomControlView;
        if (r96 != 0) goto L26;
        r83.layout(0, (r13 - AndroidUtilities.dp(310.0f)) - r4, r0, (r13 - AndroidUtilities.dp(260.0f)) - r4);
    L31:
        return true;
    L26:
        r83.layout(0, (r13 - AndroidUtilities.dp(176.0f)) - r4, r0, (r13 - AndroidUtilities.dp(126.0f)) - r4);
        goto L31
    L27:
        int r97 = r95.getVisibility();
        ZoomControlView r84 = this.zoomControlView;
        if (r97 != 0) goto L30;
        r84.layout((r12 - AndroidUtilities.dp(310.0f)) - r4, 0, r12 - AndroidUtilities.dp(260.0f), r112 - r4);
        goto L31
    L30:
        r84.layout((r12 - AndroidUtilities.dp(176.0f)) - r4, 0, r12 - AndroidUtilities.dp(126.0f), r112 - r4);
        goto L31
    L32:
        TextView r5 = this.counterTextView;
        if (r9 != r5) goto L46;
        if (r3 == false) goto L40;
        int r02 = (r0 - r5.getMeasuredWidth()) / 2;
        int r132 = r13 - AndroidUtilities.dp(167.0f);
        this.counterTextView.setRotation(0.0f);
        if (this.cameraPhotoRecyclerView.getVisibility() != 0) goto L39;
        r132 = r132 - AndroidUtilities.dp(96.0f);
    L39:
        int r133 = r132 - r4;
    L43:
        TextView r98 = this.counterTextView;
        r98.layout(r02, r133, r98.getMeasuredWidth() + r02, this.counterTextView.getMeasuredHeight() + r133);
        return true;
    L40:
        r02 = r12 - AndroidUtilities.dp(167.0f);
        r133 = (r112 / 2) + (this.counterTextView.getMeasuredWidth() / 2);
        this.counterTextView.setRotation(-90.0f);
        if (this.cameraPhotoRecyclerView.getVisibility() != 0) goto L43;
        r02 = r02 - AndroidUtilities.dp(96.0f);
        goto L43
    L46:
        if (r9 == this.cameraPhotoRecyclerView) goto L48;
        return false;
    L48:
        if (r3 == false) goto L50;
        int r113 = (r112 - AndroidUtilities.dp(88.0f)) - r4;
        r9.layout(0, r113, r9.getMeasuredWidth(), r9.getMeasuredHeight() + r113);
    L51:
        return true;
    L50:
        int r102 = (r10 + r0) - AndroidUtilities.dp(88.0f);
        r9.layout(r102, 0, r9.getMeasuredWidth() + r102, r9.getMeasuredHeight());
        goto L51
    L5:
        r3 = false;
        goto L6
    }

    public boolean onCustomMeasure(View r7, int r8, int r9) {
        if (r8 >= r9) goto L5;
        boolean r2 = true;
    L6:
        CameraViewInternal r3 = this.cameraView;
        if (r7 == r3) goto L9;
        FrameLayout r32 = this.cameraPanel;
        if (r7 == r32) goto L17;
        ZoomControlView r33 = this.zoomControlView;
        if (r7 == r33) goto L24;
        RecyclerListView r34 = this.cameraPhotoRecyclerView;
        if (r7 != r34) goto L40;
        this.cameraPhotoRecyclerViewIgnoreLayout = true;
        if (r2 == false) goto L35;
        r34.measure(View.MeasureSpec.makeMeasureSpec(r8, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(80.0f), TLObject.FLAG_30));
        if (this.cameraPhotoLayoutManager.getOrientation() == 0) goto L38;
        this.cameraPhotoRecyclerView.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.cameraPhotoLayoutManager.setOrientation(0);
        this.cameraAttachAdapter.notifyDataSetChanged();
    L38:
        this.cameraPhotoRecyclerViewIgnoreLayout = false;
        return true;
    L35:
        r34.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(80.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r9, TLObject.FLAG_30));
        if (this.cameraPhotoLayoutManager.getOrientation() == 1) goto L38;
        this.cameraPhotoRecyclerView.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
        this.cameraPhotoLayoutManager.setOrientation(1);
        this.cameraAttachAdapter.notifyDataSetChanged();
    L40:
        return false;
    L24:
        if (r2 == false) goto L26;
        r33.measure(View.MeasureSpec.makeMeasureSpec(r8, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30));
    L27:
        return true;
    L26:
        r33.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r9, TLObject.FLAG_30));
        goto L27
    L17:
        if (r2 == false) goto L19;
        r32.measure(View.MeasureSpec.makeMeasureSpec(r8, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(126.0f), TLObject.FLAG_30));
    L20:
        return true;
    L19:
        r32.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(126.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r9, TLObject.FLAG_30));
        goto L20
    L9:
        if (this.cameraOpened == false) goto L40;
        if (this.cameraAnimationInProgress == true) goto L40;
        r3.measure(View.MeasureSpec.makeMeasureSpec(r8, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r9, TLObject.FLAG_30));
        return true;
    L5:
        r2 = false;
        goto L6
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onDestroy() {
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.cameraInitied);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.albumsDidLoad);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.albumsDidLoad);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.cameraInitied);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean onDismiss() {
        if (this.cameraAnimationInProgress == false) goto L6;
        return true;
    L6:
        if (this.cameraOpened == false) goto L9;
        closeCamera(true);
        return true;
    L9:
        hideCamera(true);
        return false;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onDismissWithButtonClick(int r2) {
        if (r2 != 0) goto L4;
    L6:
        boolean r22 = false;
    L7:
        hideCamera(r22);
        return;
    L4:
        if (r2 == 2) goto L6;
        r22 = true;
        goto L7
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHidden() {
        CameraViewInternal r0 = this.cameraView;
        if (r0 == null) goto L5;
        r0.setVisibility(8);
    L5:
        Iterator<Map.Entry<Object, Object>> r02 = selectedPhotos.entrySet().iterator();
    L7:
        if (r02.hasNext() == false) goto L11;
        Map.Entry<Object, Object> r1 = r02.next();
        if ((r1.getValue() instanceof MediaController.PhotoEntry) == false) goto L7;
        ((MediaController.PhotoEntry) r1.getValue()).isAttachSpoilerRevealed = false;
        goto L7
    L11:
        this.adapter.notifyDataSetChanged();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHide() {
        this.isHidden = true;
        int r0 = this.gridView.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if ((this.gridView.getChildAt(r1) instanceof PhotoAttachCameraCell) == true) goto L6;
        this.cameraViewItemDecoration.updateBitmap();
        r1 = r1 + 1;
        goto L3
    L6:
        saveLastCameraBitmap();
    L8:
        ViewPropertyAnimator r02 = this.headerAnimator;
        if (r02 == null) goto L11;
        r02.cancel();
    L11:
        ViewPropertyAnimator r03 = this.dropDown.animate().alpha(0.0f).setDuration(150).setInterpolator(CubicBezierInterpolator.EASE_BOTH).withEndAction(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda26(this));
        this.headerAnimator = r03;
        r03.start();
        pauseCameraPreview();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHideShowProgress(float r2) {
        CameraViewInternal r0 = this.cameraView;
        if (r0 == null) goto L15;
        r0.setAlpha(r2);
        if (r2 != 0.0f) goto L7;
    L10:
        if (r2 == 0.0f) goto L12;
        return;
    L12:
        if (this.cameraView.getVisibility() == 4) goto L17;
        this.cameraView.setVisibility(4);
        return;
    L17:
        return;
    L7:
        if (this.cameraView.getVisibility() == 0) goto L10;
        this.cameraView.setVisibility(0);
        return;
    }

    public void onInit(boolean r4, boolean r5, boolean r6) {
        if (r4 == true) goto L7;
        if (r5 == true) goto L7;
        boolean r1 = false;
    L8:
        this.mediaEnabled = r1;
        this.videoEnabled = r4;
        this.photoEnabled = r5;
        this.documentsEnabled = r6;
        CameraViewInternal r42 = this.cameraView;
        if (r42 == null) goto L14;
        if (r1 == false) goto L12;
        float r52 = 1.0f;
    L13:
        r42.setAlpha(r52);
        this.cameraView.setEnabled(this.mediaEnabled);
        goto L14
    L12:
        r52 = 0.2f;
    L14:
        ChatAttachAlert r43 = this.parentAlert;
        if ((r43.baseFragment instanceof ChatActivity) == false) goto L17;
    L18:
        ChatAttachAlert r44 = this.parentAlert;
        if (r44.avatarPicker != 0) goto L31;
        this.galleryAlbumEntry = MediaController.allMediaAlbumEntry;
        if (this.mediaEnabled == false) goto L23;
        this.progressView.setText(LocaleController.getString(R.string.NoPhotos));
        this.progressView.setLottie(0, 0, 0);
    L34:
        this.noGalleryPermissions = isNoGalleryPermissions();
        if (this.galleryAlbumEntry == null) goto L40;
        int r45 = 0;
    L38:
        if (r45 >= Math.min(100, this.galleryAlbumEntry.photos.size())) goto L40;
        this.galleryAlbumEntry.photos.get(r45).reset();
        r45 = r45 + 1;
    L40:
        clearSelectedPhotos();
        updatePhotosCounter(false);
        this.cameraPhotoLayoutManager.scrollToPositionWithOffset(0, DurationKt.NANOS_IN_MILLIS);
        this.layoutManager.scrollToPositionWithOffset(0, DurationKt.NANOS_IN_MILLIS);
        this.dropDown.setText(LocaleController.getString(R.string.ChatGallery));
        MediaController.AlbumEntry r46 = this.galleryAlbumEntry;
        this.selectedAlbumEntry = r46;
        if (r46 == null) goto L45;
        this.loading = false;
        EmptyTextProgressView r47 = this.progressView;
        if (r47 == null) goto L45;
        r47.showTextView();
    L45:
        updateAlbumsDropDown();
        return;
    L23:
        TLRPC.Chat r48 = r44.getChat();
        this.progressView.setLottie(R.raw.media_forbidden, 150, 150);
        if (ChatObject.isActionBannedByDefault(r48, 7) == false) goto L26;
        this.progressView.setText(LocaleController.getString(R.string.GlobalAttachMediaRestricted));
        goto L34
    L26:
        boolean r53 = AndroidUtilities.isBannedForever(r48.banned_rights);
        EmptyTextProgressView r62 = this.progressView;
        if (r53 == false) goto L29;
        r62.setText(LocaleController.formatString("AttachMediaRestrictedForever", R.string.AttachMediaRestrictedForever, new Object[0]));
        goto L34
    L29:
        r62.setText(LocaleController.formatString("AttachMediaRestricted", R.string.AttachMediaRestricted, new Object[]{LocaleController.formatDateForBan(r48.banned_rights.until_date)}));
    L31:
        if (shouldLoadAllMedia() == false) goto L33;
        this.galleryAlbumEntry = MediaController.allMediaAlbumEntry;
        goto L34
    L33:
        this.galleryAlbumEntry = MediaController.allPhotosAlbumEntry;
        goto L34
    L17:
        if (r43.getChat() == null) goto L31;
    L7:
        r1 = true;
        goto L8
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        int r1 = r6 - r4;
        if (this.lastNotifyWidth == r1) goto L7;
        this.lastNotifyWidth = r1;
        PhotoAttachAdapter r0 = this.adapter;
        if (r0 == null) goto L7;
        r0.notifyDataSetChanged();
    L7:
        super.onLayout(r3, r4, r5, r6, r7);
        checkCameraViewPosition();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onMenuItemClick(int r13) {
        boolean r1 = true;
        if (r13 != 8) goto L6;
        this.parentAlert.setCaptionAbove(!r13.captionAbove);
        this.captionItem.setState(!this.parentAlert.captionAbove, true);
        return;
    L6:
        if (r13 == 0) goto L9;
        if (r13 == 1) goto L9;
    L20:
        if (r13 != 0) goto L31;
        ChatAttachAlert r132 = this.parentAlert;
        if (r132.editingMessageObject != null) goto L29;
        BaseFragment r133 = r132.baseFragment;
        if ((r133 instanceof ChatActivity) == false) goto L29;
        if (((ChatActivity) r133).isInScheduleMode() == false) goto L29;
        AlertsCreator.createScheduleDatePickerDialog(getContext(), ((ChatActivity) this.parentAlert.baseFragment).getDialogId(), new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda16(this), this.resourcesProvider);
        return;
    L29:
        ChatAttachAlert r134 = this.parentAlert;
        AlertsCreator.ensurePaidMessageConfirmation(r134.currentAccount, r134.getDialogId(), selectedPhotos.size() + this.parentAlert.getAdditionalMessagesCount(), new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda17(this));
        return;
    L31:
        if (r13 != 1) goto L43;
        ChatAttachAlert r135 = this.parentAlert;
        if (r135.editingMessageObject != null) goto L40;
        BaseFragment r136 = r135.baseFragment;
        if ((r136 instanceof ChatActivity) == false) goto L40;
        if (((ChatActivity) r136).isInScheduleMode() == false) goto L40;
        AlertsCreator.createScheduleDatePickerDialog(getContext(), ((ChatActivity) this.parentAlert.baseFragment).getDialogId(), new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda18(this), this.resourcesProvider);
        return;
    L40:
        ChatAttachAlert r137 = this.parentAlert;
        AlertsCreator.ensurePaidMessageConfirmation(r137.currentAccount, r137.getDialogId(), selectedPhotos.size() + this.parentAlert.getAdditionalMessagesCount(), new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda19(this));
        return;
    L43:
        if (r13 == 3) goto L110;
        if (r13 == 10) goto L110;
        if (r13 != 2) goto L73;
        if (this.parentAlert.getPhotoPreviewLayout() == null) goto L52;
        this.parentAlert.getPhotoPreviewLayout().startMediaCrossfade();
    L52:
        Iterator<Map.Entry<Object, Object>> r138 = selectedPhotos.entrySet().iterator();
    L54:
        if (r138.hasNext() == false) goto L58;
        if (((MediaController.PhotoEntry) r138.next().getValue()).isHighQuality() == false) goto L54;
        boolean r139 = true;
    L59:
        boolean r1310 = !r139;
        AndroidUtilities.runOnUIThread(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda22(this, r1310), 200);
        ArrayList r0 = new ArrayList();
        Iterator<Map.Entry<Object, Object>> r12 = selectedPhotos.entrySet().iterator();
    L61:
        if (r12.hasNext() == false) goto L65;
        Map.Entry<Object, Object> r2 = r12.next();
        if ((r2.getValue() instanceof MediaController.PhotoEntry) == false) goto L61;
        MediaController.PhotoEntry r22 = (MediaController.PhotoEntry) r2.getValue();
        r22.highQuality = Boolean.valueOf(r1310);
        r22.isChatPreviewSpoilerRevealed = false;
        r22.isAttachSpoilerRevealed = false;
        r0.add(Integer.valueOf(r22.imageId));
        goto L61
    L65:
        this.gridView.forAllChild(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda23(r0, r1310));
        if (this.parentAlert.getCurrentAttachLayout() == this) goto L69;
        this.adapter.notifyDataSetChanged();
    L69:
        if (this.parentAlert.getPhotoPreviewLayout() == null) goto L155;
        this.parentAlert.getPhotoPreviewLayout().invalidateGroupsView();
        return;
    L155:
        return;
    L58:
        r139 = false;
        goto L59
    L73:
        if (r13 != 4) goto L91;
    L87:
        e = move-exception;
        FileLog.e(e);
        return;
    L76:
        if (shouldLoadAllMedia() == false) goto L81;
        Intent r1311 = new Intent();     // Catch: Exception -> L87
        r1311.setType("video/*");     // Catch: Exception -> L87
        r1311.setAction("android.intent.action.GET_CONTENT");     // Catch: Exception -> L87
        r1311.putExtra("android.intent.extra.sizeLimit", FileLoader.DEFAULT_MAX_FILE_SIZE);     // Catch: Exception -> L87
        Intent r4 = new Intent("android.intent.action.PICK");     // Catch: Exception -> L87
        r4.setType("image/*");     // Catch: Exception -> L87
        Intent r23 = Intent.createChooser(r4, null);     // Catch: Exception -> L87
        r23.putExtra("android.intent.extra.INITIAL_INTENTS", new Intent[]{r1311});     // Catch: Exception -> L87
        ChatAttachAlert r1312 = this.parentAlert;     // Catch: Exception -> L87
        if (r1312.avatarPicker == 0) goto L80;
        r1312.baseFragment.startActivityForResult(r23, 14);     // Catch: Exception -> L87
    L85:
        this.parentAlert.dismiss(true);     // Catch: Exception -> L87
        return;
    L80:
        r1312.baseFragment.startActivityForResult(r23, 1);     // Catch: Exception -> L87
        goto L85
    L81:
        Intent r1313 = new Intent("android.intent.action.PICK");     // Catch: Exception -> L87
        r1313.setType("image/*");     // Catch: Exception -> L87
        ChatAttachAlert r24 = this.parentAlert;     // Catch: Exception -> L87
        if (r24.avatarPicker == 0) goto L84;
        r24.baseFragment.startActivityForResult(r1313, 14);     // Catch: Exception -> L87
        goto L85
    L84:
        r24.baseFragment.startActivityForResult(r1313, 1);     // Catch: Exception -> L87
        goto L85
    L91:
        if (r13 != 7) goto L99;
        ChatAttachAlert r1314 = this.parentAlert;
        if (r1314.getCurrentAttachLayout() != this.parentAlert.getPhotoPreviewLayout()) goto L96;
        r1 = false;
    L96:
        r1314.updatePhotoPreview(r1);
        return;
    L99:
        if (r13 != 9) goto L102;
        StarsIntroActivity.showMediaPriceSheet(getContext(), getStarsPrice(), true, new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda24(this), this.resourcesProvider);
        return;
    L102:
        if (r13 < 10) goto L156;
        MediaController.AlbumEntry r1315 = this.dropDownAlbums.get(r13 - 10);
        this.selectedAlbumEntry = r1315;
        MediaController.AlbumEntry r02 = this.galleryAlbumEntry;
        TextView r14 = this.dropDown;
        if (r1315 != r02) goto L106;
        r14.setText(LocaleController.getString(R.string.ChatGallery));
    L107:
        this.adapter.notifyDataSetChanged();
        this.cameraAttachAdapter.notifyDataSetChanged();
        this.layoutManager.scrollToPositionWithOffset(0, -(this.gridView.getPaddingTop() - getTopScrollOffset()));
        return;
    L106:
        r14.setText(r1315.bucketName);
        goto L107
    L156:
        return;
    L110:
        if (this.parentAlert.getPhotoPreviewLayout() == null) goto L112;
        this.parentAlert.getPhotoPreviewLayout().startMediaCrossfade();
    L112:
        Iterator<Map.Entry<Object, Object>> r5 = selectedPhotos.entrySet().iterator();
    L114:
        if (r5.hasNext() == false) goto L118;
        if (((MediaController.PhotoEntry) r5.next().getValue()).hasSpoiler == false) goto L114;
    L119:
        if (r13 != 3) goto L121;
        r1 = !r1;
    L121:
        AndroidUtilities.runOnUIThread(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda20(this, r1), 200);
        ArrayList r25 = new ArrayList();
        Iterator<Map.Entry<Object, Object>> r3 = selectedPhotos.entrySet().iterator();
    L123:
        if (r3.hasNext() == false) goto L130;
        Map.Entry<Object, Object> r52 = r3.next();
        if ((r52.getValue() instanceof MediaController.PhotoEntry) == false) goto L123;
        MediaController.PhotoEntry r53 = (MediaController.PhotoEntry) r52.getValue();
        if (r13 != 3) goto L129;
        r53.hasSpoiler = r1;
    L129:
        r53.isChatPreviewSpoilerRevealed = false;
        r53.isAttachSpoilerRevealed = false;
        r25.add(Integer.valueOf(r53.imageId));
        goto L123
    L130:
        this.gridView.forAllChild(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda21(r25, r1));
        if (this.parentAlert.getCurrentAttachLayout() == this) goto L134;
        this.adapter.notifyDataSetChanged();
    L134:
        if (this.parentAlert.getPhotoPreviewLayout() == null) goto L157;
        this.parentAlert.getPhotoPreviewLayout().invalidateGroupsView();
        return;
    L157:
        return;
    L118:
        r1 = false;
    L9:
        if (this.parentAlert.maxSelectedPhotos <= 0) goto L20;
        if (selectedPhotosOrder.size() <= 1) goto L20;
        TLRPC.Chat r03 = this.parentAlert.getChat();
        if (r03 == null) goto L20;
        if (ChatObject.hasAdminRights(r03) == true) goto L20;
        if (r03.slowmode_enabled == false) goto L20;
        AlertsCreator.createSimpleAlert(getContext(), LocaleController.getString(R.string.Slowmode), LocaleController.getString(R.string.SlowmodeSendError), this.resourcesProvider).show();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onOpenAnimationEnd() {
        ChatAttachAlert r0 = this.parentAlert;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L8:
        checkCamera(r02);
        return;
    L5:
        if ((r0.baseFragment instanceof ChatActivity) == false) goto L7;
        r02 = true;
        goto L8
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPanTransitionStart(boolean r1, int r2) {
        super.onPanTransitionStart(r1, r2);
        checkCameraViewPosition();
        CameraViewInternal r12 = this.cameraView;
        if (r12 == null) goto L6;
        r12.invalidateOutline();
        this.cameraView.invalidate();
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPause() {
        ShutterButton r0 = this.shutterButton;
        if (r0 != null) goto L5;
        return;
    L5:
        boolean r1 = this.requestingPermissions;
        CameraViewInternal r2 = this.cameraView;
        if (r1 == true) goto L16;
        if (r2 == null) goto L12;
        if (r0.getState() != ShutterButton.State.RECORDING) goto L12;
        resetRecordState();
        CameraController.getInstance().stopVideoRecording(this.cameraView.getCameraSession(), false);
        this.shutterButton.setState(ShutterButton.State.DEFAULT, true);
    L12:
        if (this.cameraOpened == false) goto L14;
        closeCamera(false);
    L14:
        hideCamera(true);
        return;
    L16:
        if (r2 != null) goto L18;
    L20:
        this.requestingPermissions = false;
        return;
    L18:
        if (r0.getState() != ShutterButton.State.RECORDING) goto L20;
        this.shutterButton.setState(ShutterButton.State.DEFAULT, true);
        goto L20
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPreMeasure(int r7, int r8) {
        this.ignoreLayout = true;
        int r1 = this.itemsPerRow;
        if (AndroidUtilities.isTablet() == false) goto L5;
        this.itemsPerRow = 4;
    L9:
        this.listAdditionalH = AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(48.0f);
        ((FrameLayout.LayoutParams) this.gridView.getLayoutParams()).topMargin = -this.listAdditionalH;
        int r72 = (r7 - AndroidUtilities.dp(4.0f)) - AndroidUtilities.dp(4.0f);
        int r2 = this.itemsPerRow;
        int r73 = r72 / r2;
        this.itemSize = r73;
        if (this.lastItemSize != r73) goto L12;
        if (r1 != r2) goto L12;
    L13:
        this.layoutManager.setSpanCount(Math.max(1, (this.itemSize * this.itemsPerRow) + (AndroidUtilities.dp(2.0f) * (this.itemsPerRow - 1))));
        int r74 = (int) Math.ceil((this.adapter.getItemCount() - 1) / this.itemsPerRow);
        if (this.noGalleryPermissions == false) goto L16;
        int r75 = AndroidUtilities.dp(400.0f);
    L17:
        int r0 = ActionBar.getCurrentActionBarHeight();
        int r76 = Math.max(0, ((((r8 - r75) - r0) - AndroidUtilities.navigationBarHeight) - AndroidUtilities.dp(12.0f)) + AndroidUtilities.dp(6.0f));
        if (this.gridExtraSpace == r76) goto L21;
        this.gridExtraSpace = r76;
        this.adapter.notifyDataSetChanged();
    L21:
        if (AndroidUtilities.isTablet() == true) goto L25;
        Point r77 = AndroidUtilities.displaySize;
        if (r77.x <= r77.y) goto L25;
        int r78 = (int) (r8 / 3.5f);
    L26:
        int r79 = ((r78 + r0) - AndroidUtilities.dp(52.0f)) + this.listAdditionalH;
        if (r79 >= 0) goto L30;
        r79 = 0;
    L30:
        if (this.gridView.getPaddingTop() == r79) goto L32;
    L33:
        this.gridView.setPadding(AndroidUtilities.dp(2.0f), r79, AndroidUtilities.dp(2.0f), this.listPaddingBottom + AndroidUtilities.dp(48.0f));
    L34:
        this.dropDown.setTextSize(17.0f);
        this.ignoreLayout = false;
        return;
    L32:
        if (this.gridView.getPaddingBottom() == this.listPaddingBottom) goto L34;
    L25:
        r78 = (r8 / 5) * 2;
        goto L26
    L16:
        r75 = ((r74 - 1) * AndroidUtilities.dp(2.0f)) + (this.itemSize * r74);
    L12:
        this.lastItemSize = r73;
        AndroidUtilities.runOnUIThread(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda28(this));
        goto L13
    L5:
        Point r22 = AndroidUtilities.displaySize;
        if (r22.x <= r22.y) goto L8;
        this.itemsPerRow = 4;
        goto L9
    L8:
        this.itemsPerRow = 3;
        goto L9
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onResume() {
        if (this.parentAlert.isShowing() == true) goto L5;
        return;
    L5:
        if (this.parentAlert.isDismissed() == false) goto L7;
        return;
    L7:
        if (PhotoViewer.getInstance().isVisible() == true) goto L12;
        checkCamera(false);
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onSelectedItemsCountChanged(int r10) {
        boolean r4 = true;
        if (r10 > 1) goto L5;
    L18:
        this.parentAlert.selectedMenuItem.hideSubItem(0);
        if (r10 != 0) goto L23;
        this.parentAlert.selectedMenuItem.showSubItem(4);
        this.parentAlert.selectedMenuItem.hideSubItem(1);
    L21:
        boolean r5 = false;
        boolean r6 = false;
    L30:
        if (r10 == 0) goto L32;
        this.parentAlert.selectedMenuItem.hideSubItem(4);
    L32:
        ChatAttachAlert r0 = this.parentAlert;
        if (r10 <= 1) goto L35;
        r0.selectedMenuItem.showSubItem(5);
        this.parentAlert.selectedMenuItem.showSubItem(7);
        this.compressItem.setText(LocaleController.getString(R.string.SendAsFiles));
    L38:
        if (r10 > 0) goto L40;
    L48:
        boolean r02 = false;
    L49:
        if (r10 <= 0) goto L57;
        ChatAttachAlert r1 = this.parentAlert;
        if (r1 == null) goto L57;
        if (r1.hasCaption() == false) goto L57;
        if ((this.parentAlert.baseFragment instanceof ChatActivity) == false) goto L57;
        boolean r12 = true;
    L58:
        if (r10 <= 0) goto L70;
        ChatAttachAlert r2 = this.parentAlert;
        if (r2 == null) goto L70;
        BaseFragment r22 = r2.baseFragment;
        if ((r22 instanceof ChatActivity) == false) goto L70;
        if (ChatObject.isChannelAndNotMegaGroup(((ChatActivity) r22).getCurrentChat()) == false) goto L70;
        if (((ChatActivity) this.parentAlert.baseFragment).getCurrentChatInfo() == null) goto L70;
        if (((ChatActivity) this.parentAlert.baseFragment).getCurrentChatInfo().paid_media_allowed == false) goto L70;
    L72:
        if (r02 == true) goto L74;
        this.spoilerItem.setText(LocaleController.getString(R.string.EnablePhotoSpoiler));
        this.spoilerItem.setAnimatedIcon(R.raw.photo_spoiler);
        this.parentAlert.selectedMenuItem.hideSubItem(3);
    L78:
        if (this.parentAlert != null) goto L80;
    L87:
        MessagePreviewView.ToggleButton r102 = this.captionItem;
        if (r12 == false) goto L90;
        r102.setVisibility(0);
    L92:
        if (r02 == true) goto L94;
        if (r12 == true) goto L94;
    L97:
        this.parentAlert.selectedMenuItem.hideSubItem(6);
    L100:
        if (r4 == false) goto L103;
        updateStarsItem();
        updatePhotoStarsPrice();
        this.parentAlert.selectedMenuItem.showSubItem(9);
        return;
    L103:
        this.parentAlert.selectedMenuItem.hideSubItem(9);
        return;
    L94:
        if (r6 == true) goto L98;
        if (r5 == false) goto L97;
    L98:
        this.parentAlert.selectedMenuItem.showSubItem(6);
        goto L100
    L90:
        r102.setVisibility(8);
        goto L92
    L80:
        if (getSelectedPhotosCount() != r10) goto L86;
        int r103 = getSelectedPhotosHighQualityCount();
        ActionBarMenuSubItem r23 = this.qualityItem;
        if (r103 <= 0) goto L84;
        r23.setText(LocaleController.getString(R.string.SendInStandardQuality));
        this.qualityItem.setIcon(R.drawable.menu_quality_sd);
    L85:
        this.parentAlert.selectedMenuItem.showSubItem(2);
        goto L87
    L84:
        r23.setText(LocaleController.getString(R.string.SendInHighQuality));
        this.qualityItem.setIcon(R.drawable.menu_quality_hd);
        goto L85
    L86:
        this.parentAlert.selectedMenuItem.hideSubItem(2);
        goto L87
    L74:
        ChatAttachAlert r7 = this.parentAlert;
        if (r7 == null) goto L78;
        r7.selectedMenuItem.showSubItem(3);
    L70:
        r4 = false;
    L57:
        r12 = false;
        goto L58
    L40:
        if (getStarsPrice() > 0) goto L48;
        ChatAttachAlert r03 = this.parentAlert;
        if (r03 == null) goto L47;
        BaseFragment r04 = r03.baseFragment;
        if ((r04 instanceof ChatActivity) == false) goto L48;
        if (((ChatActivity) r04).isSecretChat() == true) goto L48;
    L47:
        r02 = true;
        goto L49
    L35:
        r0.selectedMenuItem.hideSubItem(5);
        this.parentAlert.selectedMenuItem.hideSubItem(7);
        if (r10 == 0) goto L38;
        this.compressItem.setText(LocaleController.getString(R.string.SendAsFile));
        goto L38
    L23:
        if (this.documentsEnabled == true) goto L25;
    L29:
        this.parentAlert.selectedMenuItem.hideSubItem(1);
        goto L21
    L25:
        if (getStarsPrice() > 0) goto L29;
        ChatAttachAlert r52 = this.parentAlert;
        if (r52.editingMessageObject != null) goto L29;
        r52.selectedMenuItem.showSubItem(1);
        r5 = false;
    L16:
        r6 = true;
        goto L30
    L5:
        if (this.parentAlert.editingMessageObject != null) goto L18;
        long r53 = getStarsPrice();
        ChatAttachAlert r62 = this.parentAlert;
        if (r53 > 0) goto L10;
        r62.selectedMenuItem.showSubItem(0);
        r5 = true;
    L12:
        if (this.documentsEnabled == true) goto L14;
    L17:
        this.parentAlert.selectedMenuItem.hideSubItem(1);
        r6 = false;
        goto L30
    L14:
        if (getStarsPrice() > 0) goto L17;
        this.parentAlert.selectedMenuItem.showSubItem(1);
        goto L16
    L10:
        r62.selectedMenuItem.hideSubItem(0);
        r5 = false;
        goto L12
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean onSheetKeyDown(int r1, KeyEvent r2) {
        if (this.cameraOpened == true) goto L5;
        return false;
    L5:
        if (r1 != 24) goto L7;
    L12:
        this.shutterButton.getDelegate().shutterReleased();
        return true;
    L7:
        if (r1 == 25) goto L12;
        if (r1 == 79) goto L12;
        if (r1 == 85) goto L12;
        return false;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onShow(ChatAttachAlert.AttachAlertLayout r6) {
        ViewPropertyAnimator r0 = this.headerAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.dropDownContainer.setVisibility(0);
        boolean r02 = r6 instanceof ChatAttachAlertPhotoLayoutPreview;
        if (r02 == true) goto L8;
        clearSelectedPhotos();
        this.dropDown.setAlpha(1.0f);
    L9:
        this.parentAlert.actionBar.setTitle(_UrlKt.FRAGMENT_ENCODE_SET);
        this.layoutManager.scrollToPositionWithOffset(0, 0);
        if (r02 == false) goto L12;
        ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda25 r03 = new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda25(this, r6);
        this.gridView.post(r03);
    L12:
        checkCameraViewPosition();
        resumeCameraPreview();
        return;
    L8:
        ViewPropertyAnimator r2 = this.dropDown.animate().alpha(1.0f).setDuration(150).setInterpolator(CubicBezierInterpolator.EASE_BOTH);
        this.headerAnimator = r2;
        r2.start();
        goto L9
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onShown() {
        this.isHidden = false;
        CameraViewInternal r1 = this.cameraView;
        if (r1 == null) goto L6;
        r1.setVisibility(0);
    L6:
        if (this.checkCameraWhenShown == false) goto L9;
        this.checkCameraWhenShown = false;
        checkCamera(true);
        return;
    }

    public void openCamera(boolean r11) {
        if (this.cameraView != null) goto L5;
        return;
    L5:
        if (this.cameraInitAnimation == null) goto L7;
        return;
    L7:
        if (this.parentAlert.isDismissed() == false) goto L10;
        return;
    L10:
        if (this.cameraView.isInited() == false) goto L12;
    L16:
        BaseFragment r0 = this.parentAlert.baseFragment;
        if (r0 != null) goto L19;
        r0 = LaunchActivity.getLastFragment();
    L19:
        if (r0 != null) goto L21;
        return;
    L21:
        if (r0.getParentActivity() == null) goto L104;
        int r3 = 0;
        if (PhotoAttachAdapter.m9774$$Nest$fgetneedCamera(this.adapter) == false) goto L32;
        if (this.selectedAlbumEntry != this.galleryAlbumEntry) goto L32;
        if (this.noCameraPermissions == false) goto L32;
        this.parentAlert.baseFragment.getParentActivity().requestPermissions(new String[]{"android.permission.CAMERA"}, 18);     // Catch: Exception -> L89
        return;
    L105:
        return;
    L32:
        if (SystemUtils.isImagesAndVideoPermissionGranted() == true) goto L35;
        SystemUtils.requestImagesAndVideoPermission(this.parentAlert.baseFragment.getParentActivity());
        return;
    L35:
        this.cameraView.initTexture();
        boolean r02 = shouldLoadAllMedia();
        TextView r4 = this.tooltipTextView;
        if (r02 == false) goto L38;
        r4.setVisibility(0);
    L39:
        boolean r03 = cameraPhotos.isEmpty();
        TextView r42 = this.counterTextView;
        if (r03 == false) goto L42;
        r42.setVisibility(4);
        this.cameraPhotoRecyclerView.setVisibility(8);
    L44:
        if (this.parentAlert.getCommentView().isKeyboardVisible() == true) goto L46;
    L48:
        this.zoomControlView.setVisibility(0);
        this.zoomControlView.setAlpha(0.0f);
        this.cameraPanel.setVisibility(0);
        this.cameraPanel.setTag(null);
        int[] r04 = this.animateCameraValues;
        r04[0] = 0;
        int r5 = this.itemSize;
        r04[1] = r5;
        r04[2] = (r5 * 2) + AndroidUtilities.dp(2.0f);
        this.additionCloseCameraY = 0.0f;
        this.cameraExpanded = true;
        CameraViewInternal r05 = this.cameraView;
        if (r05 == null) goto L51;
        r05.setFpsLimit(-1);
    L51:
        AndroidUtilities.hideKeyboard(this);
        AndroidUtilities.setLightNavigationBar(this.parentAlert, false);
        this.parentAlert.getWindow().addFlags(128);
        if (r11 == false) goto L67;
        setCameraOpenProgress(0.0f);
        this.cameraAnimationInProgress = true;
        RecyclerListView r43 = this.gridView;
        if (r43 == null) goto L56;
        r43.invalidate();
    L56:
        this.notificationsLocker.lock();
        ArrayList r44 = new ArrayList();
        if (ExteraConfig.getHideCameraTile() == false) goto L59;
        setCameraOpenProgress(1.0f);
    L60:
        Property r9 = View.ALPHA;
        r44.add(ObjectAnimator.ofFloat(this.cameraPanel, r9, new float[]{1.0f}));
        r44.add(ObjectAnimator.ofFloat(this.counterTextView, r9, new float[]{1.0f}));
        r44.add(ObjectAnimator.ofFloat(this.cameraPhotoRecyclerView, r9, new float[]{1.0f}));
        int r52 = 0;
    L61:
        if (r52 >= 2) goto L66;
        if (this.flashModeButton[r52].getVisibility() == 0) goto L64;
        r52 = r52 + 1;
        goto L61
    L64:
        r44.add(ObjectAnimator.ofFloat(this.flashModeButton[r52], r9, new float[]{1.0f}));
    L66:
        AnimatorSet r06 = new AnimatorSet();
        r06.playTogether(r44);
        r06.setDuration(350);
        r06.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r06.addListener(new AnonymousClass17(this));
        r06.start();
    L76:
        this.cameraOpened = true;
        CameraViewInternal r07 = this.cameraView;
        if (r07 == null) goto L79;
        r07.setImportantForAccessibility(2);
    L79:
        this.gridView.setImportantForAccessibility(4);
        this.gridView.invalidate();
        this.parentAlert.onCameraStateChanged();
        if (LiteMode.isEnabled(LiteMode.FLAGS_CHAT) == true) goto L82;
    L83:
        CameraViewInternal r08 = this.cameraView;
        if (r08 != null) goto L86;
        return;
    L86:
        if (r08.isInited() == false) goto L101;
        this.cameraView.showTexture(true, r11);
        return;
    L101:
        return;
    L82:
        if (ExteraConfig.getHideCameraTile() == true) goto L83;
        return;
    L59:
        r44.add(ObjectAnimator.ofFloat(this, "cameraOpenProgress", new float[]{0.0f, 1.0f}));
        goto L60
    L67:
        setCameraOpenProgress(1.0f);
        this.cameraPanel.setAlpha(1.0f);
        this.counterTextView.setAlpha(1.0f);
        this.cameraPhotoRecyclerView.setAlpha(1.0f);
    L68:
        if (r3 >= 2) goto L73;
        if (this.flashModeButton[r3].getVisibility() == 0) goto L71;
        r3 = r3 + 1;
        goto L68
    L71:
        this.flashModeButton[r3].setAlpha(1.0f);
    L73:
        this.parentAlert.delegate.onCameraOpened();
        CameraViewInternal r09 = this.cameraView;
        if (r09 == null) goto L76;
        r09.setSystemUiVisibility(1028);
        goto L76
    L46:
        if (isFocusable() == false) goto L48;
        this.parentAlert.getCommentView().closeKeyboard();
        goto L48
    L42:
        r42.setVisibility(0);
        this.cameraPhotoRecyclerView.setVisibility(0);
        goto L44
    L38:
        r4.setVisibility(8);
        goto L39
    L104:
        return;
    L12:
        if (LiteMode.isEnabled(LiteMode.FLAGS_CHAT) == false) goto L16;
        if (ExteraConfig.getHideCameraTile() == true) goto L16;
    }

    public void openPhotoViewer(MediaController.PhotoEntry r17, boolean r18, boolean r19) {
        if (r17 == null) goto L6;
        cameraPhotos.add(r17);
        selectedPhotos.put(Integer.valueOf(r17.imageId), r17);
        selectedPhotosOrder.add(Integer.valueOf(r17.imageId));
        this.parentAlert.updateCountButton(0);
        this.adapter.notifyDataSetChanged();
        this.cameraAttachAdapter.notifyDataSetChanged();
    L6:
        if (r17 == null) goto L15;
        if (r19 == true) goto L15;
        if (cameraPhotos.size() <= 1) goto L15;
        updatePhotosCounter(false);
        if (this.cameraView == null) goto L55;
        this.zoomControlView.setZoom(0.0f, false);
        this.cameraZoom = 0.0f;
        this.cameraView.setZoom(0.0f);
        CameraController.getInstance().startPreview(this.cameraView.getCameraSessionObject());
        return;
    L55:
        return;
    L15:
        if (cameraPhotos.isEmpty() == true) goto L57;
        this.cancelTakingPhotos = true;
        BaseFragment r4 = this.parentAlert.baseFragment;
        if (r4 != null) goto L20;
        r4 = LaunchActivity.getLastFragment();
    L20:
        if (r4 == null) goto L58;
        PhotoViewer.getInstance().setParentActivity(r4.getParentActivity(), this.resourcesProvider);
        PhotoViewer.getInstance().setParentAlert(this.parentAlert);
        PhotoViewer r42 = PhotoViewer.getInstance();
        ChatAttachAlert r5 = this.parentAlert;
        r42.setMaxSelectedPhotos(r5.maxSelectedPhotos, r5.allowOrder);
        ChatAttachAlert r43 = this.parentAlert;
        if (r43.isPhotoPicker == false) goto L28;
        if (r43.isStickerMode == false) goto L28;
        ChatActivity r52 = (ChatActivity) r43.baseFragment;
        int r7 = 11;
    L35:
        boolean r8 = r43.isPollAttach;
        if (r8 == false) goto L39;
        r7 = 13;
        ChatActivity r15 = null;
    L38:
        int r12 = r7;
        if (r43.avatarPicker != 0) goto L46;
        if (r8 == true) goto L46;
        ArrayList<Object> r44 = getAllPhotosArray();
        int r11 = cameraPhotos.size() - 1;
    L45:
        ArrayList<Object> r10 = r44;
        if (this.parentAlert.getAvatarFor() == null) goto L51;
        if (r17 == null) goto L51;
        this.parentAlert.getAvatarFor().isVideo = r17.isVideo;
    L51:
        PhotoViewer.getInstance().openPhotoForSelect(r10, r11, r12, false, new AnonymousClass15(this, r18), r15);
        PhotoViewer.getInstance().setAvatarFor(this.parentAlert.getAvatarFor());
        if (this.parentAlert.isStickerMode == false) goto L56;
        PhotoViewer.getInstance().enableStickerMode(null, null, false, this.parentAlert.customStickerHandler);
        PhotoViewer.getInstance().prepareSegmentImage();
        return;
    L56:
        return;
    L46:
        r44 = new ArrayList();
        r44.add(r17);
        r11 = 0;
        goto L45
    L39:
        r15 = r52;
    L28:
        if (r43.avatarPicker == 0) goto L31;
        r7 = 1;
    L30:
        r52 = null;
        goto L35
    L31:
        BaseFragment r53 = r43.baseFragment;
        if ((r53 instanceof ChatActivity) == false) goto L34;
        r52 = (ChatActivity) r53;
        r7 = 2;
        goto L35
    L34:
        r7 = 5;
        goto L30
    L58:
        return;
    }

    public void pauseCamera(boolean r2) {
        if (this.needCamera == true) goto L5;
        return;
    L5:
        if (this.noCameraPermissions == true) goto L14;
        if (r2 == false) goto L11;
        CameraViewInternal r22 = this.cameraView;
        if (r22 == null) goto L15;
        this.isCameraFrontfaceBeforeEnteringEditMode = Boolean.valueOf(r22.isFrontface());
        hideCamera(true);
        return;
    L15:
        return;
    L11:
        showCamera();
        return;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void scrollToTop() {
        this.gridView.smoothScrollToPosition(0);
    }

    @Keep
    public void setCameraOpenProgress(float r20) {
        if (this.cameraView != null) goto L5;
        return;
    L5:
        this.cameraOpenProgress = r20;
        int[] r2 = this.animateCameraValues;
        float r4 = r2[1];
        float r22 = r2[2];
        Point r5 = AndroidUtilities.displaySize;
        int r6 = r5.x;
        int r52 = r5.y;
        float r53 = (this.parentAlert.getContainer().getWidth() - this.parentAlert.getLeftInset()) - this.parentAlert.getRightInset();
        float r62 = this.parentAlert.getContainer().getHeight();
        float[] r7 = this.cameraViewLocation;
        float r8 = r7[0];
        float r3 = r7[1];
        float r72 = this.additionCloseCameraY;
        FrameLayout.LayoutParams r9 = (FrameLayout.LayoutParams) this.cameraView.getLayoutParams();
        float r10 = this.cameraView.getTextureHeight(r4, r22) / this.cameraView.getTextureHeight(r53, r62);
        float r11 = r22 / r62;
        float r12 = r4 / r53;
        boolean r13 = this.cameraExpanded;
        CameraViewInternal r14 = this.cameraView;
        if (r13 == false) goto L8;
        int r132 = (int) r53;
        int r15 = (int) r62;
        float r18 = 1.0f - r20;
        float r102 = (r10 * r18) + r20;
        r14.getTextureView().setScaleX(r102);
        this.cameraView.getTextureView().setScaleY(r102);
        float r142 = r8 * r18;
        this.cameraView.setTranslationX((r142 + (0.0f * r20)) - (((1.0f - ((r12 * r18) + r20)) * r53) / 2.0f));
        float r122 = r3 * r18;
        this.cameraView.setTranslationY(((r72 * r20) + r122) - (((1.0f - ((r11 * r18) + r20)) * r62) / 2.0f));
        this.animationClipTop = r122 - this.cameraView.getTranslationY();
        this.animationClipBottom = (((r3 + r22) * r18) - this.cameraView.getTranslationY()) + (r62 * r20);
        this.animationClipLeft = r142 - this.cameraView.getTranslationX();
        this.animationClipRight = (((r8 + r4) * r18) - this.cameraView.getTranslationX()) + (r53 * r20);
    L10:
        if (r9.width == r132) goto L12;
    L13:
        r9.width = r132;
        r9.height = r15;
        this.cameraView.requestLayout();
    L14:
        this.cameraView.invalidateOutline();
        this.cameraView.invalidate();
        return;
    L12:
        if (r9.height == r15) goto L14;
    L8:
        r132 = (int) r4;
        r15 = (int) r22;
        r14.getTextureView().setScaleX(1.0f);
        this.cameraView.getTextureView().setScaleY(1.0f);
        this.animationClipTop = 0.0f;
        this.animationClipBottom = r62;
        this.animationClipLeft = 0.0f;
        this.animationClipRight = r53;
        this.cameraView.setTranslationX(r8);
        this.cameraView.setTranslationY(r3);
        goto L10
    }

    public void setCheckCameraWhenShown(boolean r1) {
        this.checkCameraWhenShown = r1;
    }

    public void setIncludeVideosInGallery(boolean r1) {
        this.includeVideosInGallery = r1;
    }

    public void setStarsPrice(long r6) {
        if (selectedPhotos.isEmpty() == true) goto L12;
        Iterator<Map.Entry<Object, Object>> r0 = selectedPhotos.entrySet().iterator();
    L6:
        if (r0.hasNext() == false) goto L12;
        MediaController.PhotoEntry r2 = (MediaController.PhotoEntry) r0.next().getValue();
        r2.starsAmount = r6;
        if (r6 <= 0) goto L10;
        boolean r3 = true;
    L11:
        r2.hasSpoiler = r3;
        r2.isChatPreviewSpoilerRevealed = false;
        r2.isAttachSpoilerRevealed = false;
        goto L6
    L10:
        r3 = false;
    L12:
        onSelectedItemsCountChanged(getSelectedItemsCount());
        if (checkSelectedCount(false) == false) goto L19;
        updateCheckedPhotos();
        return;
    }

    @Override // android.view.View
    public void setTranslationY(float r7) {
        if (this.parentAlert.getSheetAnimationType() != 1) goto L10;
        float r0 = (r7 / 40.0f) * (-0.1f);
        int r1 = this.gridView.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L10;
        View r3 = this.gridView.getChildAt(r2);
        if ((r3 instanceof PhotoAttachPhotoCell) == false) goto L9;
        PhotoAttachPhotoCell r32 = (PhotoAttachPhotoCell) r3;
        float r5 = 1.0f + r0;
        r32.getCheckBox().setScaleX(r5);
        r32.getCheckBox().setScaleY(r5);
    L9:
        r2 = r2 + 1;
    L10:
        super.setTranslationY(r7);
        this.parentAlert.getSheetContainer().invalidate();
        invalidate();
    }

    public void showAvatarConstructorFragment(AvatarConstructorPreviewCell r3, TLRPC.VideoSize r4) {
        showAvatarConstructorFragment(r3, r4, 0);
    }

    public void showAvatarConstructorFragment(AvatarConstructorPreviewCell r4, TLRPC.VideoSize r5, long r6) {
        ChatAttachAlert r1 = this.parentAlert;
        AvatarConstructorFragment r0 = new AvatarConstructorFragment(r1.parentImageUpdater, r1.getAvatarFor());
        if (this.parentAlert.getAvatarFor() != null) goto L5;
    L8:
        boolean r12 = true;
    L9:
        r0.finishOnDone = r12;
        this.parentAlert.baseFragment.presentFragment(r0);
        if (r4 == null) goto L12;
        r0.startFrom(r4);
    L12:
        if (r5 == null) goto L15;
        r0.startFrom(r5);
    L15:
        if (r6 == 0) goto L17;
        r0.startFrom(r6, this.parentAlert.forUser);
    L17:
        r0.setDelegate(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda30(this, r0));
        return;
    L5:
        if (this.parentAlert.getAvatarFor().type != 2) goto L8;
        r12 = false;
        goto L9
    }

    public void showCamera() {
        if (this.parentAlert.paused == false) goto L5;
        return;
    L5:
        if (this.mediaEnabled == true) goto L7;
        return;
    L7:
        if (CameraView.isCameraAllowed() == true) goto L10;
        return;
    L10:
        if (this.cameraView == null) goto L12;
    L43:
        ZoomControlView r0 = this.zoomControlView;
        if (r0 == null) goto L47;
        r0.setZoom(0.0f, false);
        this.cameraZoom = 0.0f;
    L47:
        if (this.cameraOpened == true) goto L52;
        this.cameraView.setTranslationX(this.cameraViewLocation[0]);
        return;
    L52:
        return;
    L12:
        if (LiteMode.isEnabled(LiteMode.FLAGS_CHAT) == true) goto L14;
    L17:
        boolean r02 = true;
    L18:
        Context r4 = getContext();
        Boolean r5 = this.isCameraFrontfaceBeforeEnteringEditMode;
        if (r5 == null) goto L21;
        boolean r52 = r5.booleanValue();
    L22:
        CameraViewInternal r3 = new CameraViewInternal(this, r4, r52, r02);
        this.cameraView = r3;
        BaseFragment r03 = this.parentAlert.baseFragment;
        if ((r03 instanceof ChatActivity) == true) goto L25;
    L27:
        boolean r04 = false;
    L28:
        r3.setRecordFile(AndroidUtilities.generateVideoPath(r04));
        this.cameraView.setFocusable(true);
        this.cameraView.setFpsLimit(30);
        this.cameraView.setOutlineProvider(new AnonymousClass18(this));
        this.cameraView.setClipToOutline(true);
        this.cameraView.setContentDescription(LocaleController.getString(R.string.AccDescrInstantCamera));
        BottomSheet.ContainerView r05 = this.parentAlert.getContainer();
        CameraViewInternal r32 = this.cameraView;
        int r53 = this.itemSize;
        r05.addView(r32, 1, new FrameLayout.LayoutParams(r53, r53));
        this.cameraView.setDelegate(new ChatAttachAlertPhotoLayout$$ExternalSyntheticLambda2(this));
        CameraViewInternal r06 = this.cameraView;
        if (this.mediaEnabled == false) goto L31;
        float r2 = 1.0f;
    L32:
        r06.setAlpha(r2);
        this.cameraView.setEnabled(this.mediaEnabled);
        if (this.isHidden == false) goto L36;
        this.cameraView.setVisibility(8);
    L36:
        if (this.cameraOpened == true) goto L39;
        checkCameraViewPosition();
    L39:
        RecyclerListView r07 = this.gridView;
        if (r07 == null) goto L42;
        r07.invalidate();
    L42:
        invalidate();
        goto L43
    L31:
        r2 = 0.2f;
        goto L32
    L25:
        if (((ChatActivity) r03).isSecretChat() == false) goto L27;
        r04 = true;
        goto L28
    L21:
        r52 = this.parentAlert.openWithFrontFaceCamera;
        goto L22
    L14:
        if (ExteraConfig.getHideCameraTile() == true) goto L17;
        r02 = false;
        goto L18
    }

    public void toggleLivePhotos(boolean r6) {
        if (selectedPhotos.isEmpty() == false) goto L5;
        return;
    L5:
        Iterator<Map.Entry<Object, Object>> r0 = selectedPhotos.entrySet().iterator();
    L6:
        int r2 = 0;
        if (r0.hasNext() == false) goto L21;
        Map.Entry<Object, Object> r1 = r0.next();
        if ((r1.getValue() instanceof MediaController.PhotoEntry) == false) goto L6;
        MediaController.PhotoEntry r12 = (MediaController.PhotoEntry) r1.getValue();
        if (r12.isLivePhoto() == false) goto L6;
        r12.discardLivePhoto = Boolean.valueOf(!r6);
    L14:
        if (r2 >= this.gridView.getChildCount()) goto L6;
        View r3 = this.gridView.getChildAt(r2);
        if ((r3 instanceof PhotoAttachPhotoCell) == false) goto L20;
        PhotoAttachPhotoCell r32 = (PhotoAttachPhotoCell) r3;
        if (r32.getPhotoEntry() != r12) goto L20;
        r32.getImageView().invalidate();
    L20:
        r2 = r2 + 1;
        goto L14
    L21:
        SharedPreferences.Editor r02 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit();
        SharedConfig.photoLiveDefault = r6;
        r02.putBoolean("photoLiveDefault", r6).apply();
        updateCells();
    }

    public void updateAvatarPicker() {
        ChatAttachAlert r0 = this.parentAlert;
        if (r0.avatarPicker != 0) goto L5;
    L7:
        boolean r02 = false;
    L8:
        this.showAvatarConstructor = r02;
        return;
    L5:
        if (r0.isPhotoPicker == true) goto L7;
        r02 = true;
        goto L8
    }

    public void updateCheckedPhotoIndices() {
        if ((this.parentAlert.baseFragment instanceof ChatActivity) == false) goto L21;
        int r0 = this.gridView.getChildCount();
        int r1 = 0;
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L13;
        View r3 = this.gridView.getChildAt(r2);
        if ((r3 instanceof PhotoAttachPhotoCell) == false) goto L12;
        PhotoAttachPhotoCell r32 = (PhotoAttachPhotoCell) r3;
        MediaController.PhotoEntry r4 = getPhotoEntryAtPosition(((Integer) r32.getTag()).intValue());
        if (r4 == null) goto L12;
        r32.setNum(selectedPhotosOrder.indexOf(Integer.valueOf(r4.imageId)));
    L12:
        r2 = r2 + 1;
        goto L6
    L13:
        int r02 = this.cameraPhotoRecyclerView.getChildCount();
    L14:
        if (r1 >= r02) goto L30;
        View r22 = this.cameraPhotoRecyclerView.getChildAt(r1);
        if ((r22 instanceof PhotoAttachPhotoCell) == false) goto L20;
        PhotoAttachPhotoCell r23 = (PhotoAttachPhotoCell) r22;
        MediaController.PhotoEntry r33 = getPhotoEntryAtPosition(((Integer) r23.getTag()).intValue());
        if (r33 == null) goto L20;
        r23.setNum(selectedPhotosOrder.indexOf(Integer.valueOf(r33.imageId)));
    L20:
        r1 = r1 + 1;
        goto L14
    L30:
        return;
    }

    public void updateCheckedPhotos() {
        if ((this.parentAlert.baseFragment instanceof ChatActivity) == false) goto L105;
        int r0 = this.gridView.getChildCount();
        int r2 = 0;
    L6:
        int r3 = -1;
        if (r2 >= r0) goto L55;
        View r5 = this.gridView.getChildAt(r2);
        if ((r5 instanceof PhotoAttachPhotoCell) == false) goto L54;
        PhotoAttachPhotoCell r6 = (PhotoAttachPhotoCell) r5;
        int r52 = this.gridView.getChildAdapterPosition(r5);
        if (PhotoAttachAdapter.m9772$$Nest$fgethasCameraSpaceRow(this.adapter) == false) goto L16;
        if (r52 <= this.itemsPerRow) goto L16;
        r52 = r52 - 1;
    L16:
        if (PhotoAttachAdapter.m9774$$Nest$fgetneedCamera(this.adapter) == true) goto L18;
    L24:
        MediaController.PhotoEntry r53 = getPhotoEntryAtPosition(r52);
        if (r53 != null) goto L27;
    L29:
        boolean r7 = false;
    L30:
        r6.setHasSpoiler(r7);
        if (r53 != null) goto L33;
    L35:
        boolean r72 = false;
    L36:
        r6.setHighQuality(r72);
        ChatAttachAlert r73 = this.parentAlert;
        if ((r73.baseFragment instanceof ChatActivity) == true) goto L39;
    L48:
        if (r53 != null) goto L50;
    L52:
        boolean r54 = false;
    L53:
        r6.setChecked(-1, r54, true);
        goto L54
    L50:
        if (selectedPhotos.containsKey(Integer.valueOf(r53.imageId)) == false) goto L52;
        r54 = true;
        goto L53
    L39:
        if (r73.allowOrder == false) goto L48;
        if (r53 == null) goto L42;
        r3 = selectedPhotosOrder.indexOf(Integer.valueOf(r53.imageId));
    L42:
        if (r53 != null) goto L44;
    L46:
        boolean r55 = false;
    L47:
        r6.setChecked(r3, r55, true);
        goto L54
    L44:
        if (selectedPhotos.containsKey(Integer.valueOf(r53.imageId)) == false) goto L46;
        r55 = true;
        goto L47
    L33:
        if (r53.isHighQuality() == false) goto L35;
        r72 = true;
        goto L36
    L27:
        if (r53.hasSpoiler == false) goto L29;
        r7 = true;
        goto L30
    L18:
        if (this.selectedAlbumEntry != this.galleryAlbumEntry) goto L24;
        if (ExteraConfig.getHideCameraTile() == true) goto L22;
    L23:
        r52 = r52 - 1;
        goto L24
    L22:
        if (this.noCameraPermissions == false) goto L24;
    L54:
        r2 = r2 + 1;
        goto L6
    L55:
        int r02 = this.cameraPhotoRecyclerView.getChildCount();
        int r22 = 0;
    L56:
        if (r22 >= r02) goto L114;
        View r56 = this.cameraPhotoRecyclerView.getChildAt(r22);
        if ((r56 instanceof PhotoAttachPhotoCell) == false) goto L104;
        PhotoAttachPhotoCell r62 = (PhotoAttachPhotoCell) r56;
        int r57 = this.cameraPhotoRecyclerView.getChildAdapterPosition(r56);
        if (PhotoAttachAdapter.m9772$$Nest$fgethasCameraSpaceRow(this.adapter) == false) goto L65;
        if (r57 <= this.itemsPerRow) goto L65;
        r57 = r57 - 1;
    L65:
        if (PhotoAttachAdapter.m9774$$Nest$fgetneedCamera(this.adapter) == true) goto L67;
    L73:
        MediaController.PhotoEntry r58 = getPhotoEntryAtPosition(r57);
        if (r58 != null) goto L76;
    L78:
        boolean r74 = false;
    L79:
        r62.setHasSpoiler(r74);
        if (r58 != null) goto L82;
    L84:
        boolean r75 = false;
    L85:
        r62.setHighQuality(r75);
        ChatAttachAlert r76 = this.parentAlert;
        if ((r76.baseFragment instanceof ChatActivity) == true) goto L88;
    L98:
        if (r58 != null) goto L100;
    L102:
        boolean r59 = false;
    L103:
        r62.setChecked(-1, r59, true);
        goto L104
    L100:
        if (selectedPhotos.containsKey(Integer.valueOf(r58.imageId)) == false) goto L102;
        r59 = true;
        goto L103
    L88:
        if (r76.allowOrder == false) goto L98;
        if (r58 == null) goto L91;
        int r77 = selectedPhotosOrder.indexOf(Integer.valueOf(r58.imageId));
    L92:
        if (r58 != null) goto L94;
    L96:
        boolean r510 = false;
    L97:
        r62.setChecked(r77, r510, true);
        goto L104
    L94:
        if (selectedPhotos.containsKey(Integer.valueOf(r58.imageId)) == false) goto L96;
        r510 = true;
        goto L97
    L91:
        r77 = -1;
        goto L92
    L82:
        if (r58.isHighQuality() == false) goto L84;
        r75 = true;
        goto L85
    L76:
        if (r58.hasSpoiler == false) goto L78;
        r74 = true;
        goto L79
    L67:
        if (this.selectedAlbumEntry != this.galleryAlbumEntry) goto L73;
        if (ExteraConfig.getHideCameraTile() == true) goto L71;
    L72:
        r57 = r57 - 1;
        goto L73
    L71:
        if (this.noCameraPermissions == false) goto L73;
    L104:
        r22 = r22 + 1;
        goto L56
    L114:
        return;
    }

    public void updatePhotosCounter(boolean r8) {
        if (this.counterTextView == null) goto L48;
        ChatAttachAlert r0 = this.parentAlert;
        if (r0.avatarPicker == 0) goto L7;
        return;
    L7:
        if (r0.storyMediaPicker == false) goto L9;
        return;
    L9:
        if (r0.isPollAttach == true) goto L51;
        Iterator<Map.Entry<Object, Object>> r02 = selectedPhotos.entrySet().iterator();
        boolean r1 = false;
        boolean r2 = false;
        boolean r3 = false;
    L13:
        if (r02.hasNext() == false) goto L20;
        if (((MediaController.PhotoEntry) r02.next().getValue()).isVideo == false) goto L17;
        r2 = true;
    L18:
        if (r2 == false) goto L13;
        if (r3 == false) goto L13;
    L17:
        r3 = true;
    L20:
        int r03 = Math.max(1, selectedPhotos.size());
        if (r2 == false) goto L27;
        if (r3 == false) goto L27;
        this.counterTextView.setText(LocaleController.formatPluralString("Media", selectedPhotos.size(), new Object[0]).toUpperCase());
        if (r03 != this.currentSelectedCount) goto L26;
        if (r8 == true) goto L26;
    L37:
        ChatAttachAlert r82 = this.parentAlert;
        if (r03 <= 1) goto L40;
        r1 = true;
    L40:
        r82.setCanOpenPreview(r1);
        this.currentSelectedCount = r03;
        return;
    L26:
        this.parentAlert.selectedTextView.setText(LocaleController.formatPluralString("MediaSelected", r03, new Object[0]));
    L27:
        TextView r32 = this.counterTextView;
        if (r2 == false) goto L33;
        r32.setText(LocaleController.formatPluralString("Videos", selectedPhotos.size(), new Object[0]).toUpperCase());
        if (r03 != this.currentSelectedCount) goto L32;
        if (r8 == false) goto L37;
    L32:
        this.parentAlert.selectedTextView.setText(LocaleController.formatPluralString("VideosSelected", r03, new Object[0]));
        goto L37
    L33:
        r32.setText(LocaleController.formatPluralString("Photos", selectedPhotos.size(), new Object[0]).toUpperCase());
        if (r03 != this.currentSelectedCount) goto L36;
        if (r8 == false) goto L37;
    L36:
        this.parentAlert.selectedTextView.setText(LocaleController.formatPluralString("PhotosSelected", r03, new Object[0]));
        goto L37
    L51:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void updateSelected(HashMap<Object, Object> r9, ArrayList<Object> r10, boolean r11) {
        selectedPhotos.clear();
        selectedPhotos.putAll(r9);
        selectedPhotosOrder.clear();
        selectedPhotosOrder.addAll(r10);
        if (r11 == false) goto L60;
        updatePhotosCounter(false);
        updateCheckedPhotoIndices();
        int r102 = this.gridView.getChildCount();
        int r112 = 0;
    L5:
        if (r112 >= r102) goto L66;
        View r0 = this.gridView.getChildAt(r112);
        if ((r0 instanceof PhotoAttachPhotoCell) == false) goto L59;
        int r1 = this.gridView.getChildAdapterPosition(r0);
        if (PhotoAttachAdapter.m9772$$Nest$fgethasCameraSpaceRow(this.adapter) == false) goto L14;
        if (r1 <= this.itemsPerRow) goto L14;
        r1 = r1 - 1;
    L14:
        if (PhotoAttachAdapter.m9774$$Nest$fgetneedCamera(this.adapter) == true) goto L16;
    L22:
        PhotoAttachPhotoCell r2 = (PhotoAttachPhotoCell) r0;
        ChatAttachAlert r02 = this.parentAlert;
        if (r02.avatarPicker == 0) goto L25;
    L26:
        r2.getCheckBox().setVisibility(8);
    L27:
        MediaController.PhotoEntry r3 = getPhotoEntryAtPosition(r1);
        if (r3 == null) goto L59;
        boolean r4 = true;
        if (selectedPhotos.size() <= 1) goto L32;
        int r03 = 1;
    L34:
        if (PhotoAttachAdapter.m9774$$Nest$fgetneedCamera(this.adapter) == true) goto L36;
    L42:
        boolean r5 = 0;
    L44:
        if (r1 != (this.adapter.getItemCount() - r03)) goto L46;
        boolean r6 = r03;
    L47:
        ChatAttachAlert r12 = this.parentAlert;
        if (r12 != null) goto L50;
    L52:
        boolean r7 = 0;
    L53:
        r2.setPhotoEntry(r3, r4, r5, r6, r7);
        ChatAttachAlert r04 = this.parentAlert;
        if ((r04.baseFragment instanceof ChatActivity) == true) goto L56;
    L58:
        r2.setChecked(-1, selectedPhotos.containsKey(Integer.valueOf(r3.imageId)), false);
        goto L59
    L56:
        if (r04.allowOrder == false) goto L58;
        r2.setChecked(selectedPhotosOrder.indexOf(Integer.valueOf(r3.imageId)), selectedPhotos.containsKey(Integer.valueOf(r3.imageId)), false);
        goto L59
    L50:
        if (r12.allowLivePhotos == false) goto L52;
        r7 = r03;
        goto L53
    L46:
        r6 = 0;
        goto L47
    L36:
        if (this.selectedAlbumEntry != this.galleryAlbumEntry) goto L42;
        if (ExteraConfig.getHideCameraTile() == true) goto L40;
    L41:
        r5 = r03;
        goto L44
    L40:
        if (this.noCameraPermissions == false) goto L42;
    L32:
        r03 = 1;
        r4 = false;
        goto L34
    L25:
        if (r02.isPollAttach == false) goto L27;
    L16:
        if (this.selectedAlbumEntry != this.galleryAlbumEntry) goto L22;
        if (ExteraConfig.getHideCameraTile() == true) goto L20;
    L21:
        r1 = r1 - 1;
        goto L22
    L20:
        if (this.noCameraPermissions == false) goto L22;
    L59:
        r112 = r112 + 1;
        goto L5
    L66:
        return;
    }
}
