package org.telegram.ui.Components;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import androidx.core.content.FileProvider;
import com.exteragram.messenger.utils.system.SystemUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PhotoAlbumPickerActivity;
import org.telegram.ui.PhotoCropActivity;
import org.telegram.ui.PhotoPickerActivity;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes3.dex */
public class ImageUpdater implements NotificationCenter.NotificationCenterDelegate, PhotoCropActivity.PhotoEditActivityDelegate {
    private TLRPC.PhotoSize bigPhoto;
    private boolean canSelectVideo;
    private boolean canceled;
    private ChatAttachAlert chatAttachAlert;
    private boolean clearAfterUpdate;
    private MessageObject convertingVideo;
    private int currentAccount;
    private float currentImageProgress;
    public String currentPicturePath;
    private ImageUpdaterDelegate delegate;
    private String finalPath;
    private boolean forUser;
    private boolean forceDarkTheme;
    private ImageReceiver imageReceiver;
    private boolean isVideo;
    private boolean openWithFrontfaceCamera;
    public BaseFragment parentFragment;
    private File picturePath;
    private boolean searchAvailable;
    public final int setForType;
    private boolean showingFromDialog;
    private TLRPC.PhotoSize smallPhoto;
    private Bitmap smallPhotoBitmap;
    private boolean supportEmojiMarkup;
    private int type;
    private boolean uploadAfterSelect;
    private TLRPC.InputFile uploadedPhoto;
    private TLRPC.InputFile uploadedVideo;
    private String uploadingImage;
    private String uploadingVideo;
    private boolean useAttachMenu;
    private TLRPC.User user;
    private TLRPC.VideoSize vectorMarkup;
    private String videoPath;
    private double videoTimestamp;

    /* renamed from: org.telegram.ui.Components.ImageUpdater$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 implements PhotoPickerActivity.PhotoPickerActivityDelegate {
        private boolean sendPressed;
        final /* synthetic */ ImageUpdater this$0;
        final /* synthetic */ ArrayList val$order;
        final /* synthetic */ HashMap val$photos;

        public AnonymousClass1(ImageUpdater r1, HashMap r2, ArrayList r3) {
            this.this$0 = r1;
            this.val$photos = r2;
            this.val$order = r3;
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void actionButtonPressed(boolean r3, boolean r4, int r5, int r6) {
            if (this.val$photos.isEmpty() == false) goto L5;
            return;
        L5:
            if (ImageUpdater.m10916$$Nest$fgetdelegate(this.this$0) != null) goto L7;
            return;
        L7:
            if (this.sendPressed == true) goto L32;
            if (r3 == true) goto L33;
            this.sendPressed = true;
            ArrayList r32 = new ArrayList();
            int r52 = 0;
        L12:
            if (r52 >= this.val$order.size()) goto L25;
            Object r62 = this.val$photos.get(this.val$order.get(r52));
            SendMessagesHelper.SendingMediaInfo r0 = new SendMessagesHelper.SendingMediaInfo();
            r32.add(r0);
            if ((r62 instanceof MediaController.SearchImage) == false) goto L24;
            MediaController.SearchImage r63 = (MediaController.SearchImage) r62;
            String r1 = r63.imagePath;
            if (r1 == null) goto L18;
            r0.path = r1;
        L19:
            r0.videoEditedInfo = r63.editedInfo;
            r0.thumbPath = r63.thumbPath;
            CharSequence r12 = r63.caption;
            if (r12 == null) goto L22;
            String r13 = r12.toString();
        L23:
            r0.caption = r13;
            r0.entities = r63.entities;
            r0.masks = r63.stickers;
            r0.ttl = r63.ttl;
            goto L24
        L22:
            r13 = null;
            goto L23
        L18:
            r0.searchImage = r63;
        L24:
            r52 = r52 + 1;
            goto L12
        L25:
            ImageUpdater.m10917$$Nest$mdidSelectPhotos(this.this$0, false, r32);
            return;
        L33:
            return;
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public boolean canFinishFragment() {
            return ImageUpdater.m10916$$Nest$fgetdelegate(this.this$0).canFinishFragment();
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void onCaptionChanged(CharSequence r1) {
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void selectedPhotosChanged() {
        }
    }

    /* renamed from: org.telegram.ui.Components.ImageUpdater$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 implements ChatAttachAlert.ChatAttachViewDelegate {
        final /* synthetic */ ImageUpdater this$0;

        public AnonymousClass2(ImageUpdater r1) {
            this.this$0 = r1;
        }

        private void processSelectedAttach(int r1) {
            if (r1 != 0) goto L5;
            this.this$0.openCamera();
            return;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r4, boolean r5, boolean r6, int r7, int r8, long r9, boolean r11, boolean r12, long r13) {
            BaseFragment r52 = this.this$0.parentFragment;
            if (r52 != null) goto L5;
            return;
        L5:
            if (r52.getParentActivity() != null) goto L7;
            return;
        L7:
            if (ImageUpdater.m10915$$Nest$fgetchatAttachAlert(this.this$0) != null) goto L10;
            return;
        L10:
            if (r4 != 8) goto L12;
        L16:
            HashMap<Object, Object> r62 = ImageUpdater.m10915$$Nest$fgetchatAttachAlert(this.this$0).getPhotoLayout().getSelectedPhotos();
            ArrayList<Object> r72 = ImageUpdater.m10915$$Nest$fgetchatAttachAlert(this.this$0).getPhotoLayout().getSelectedPhotosOrder();
            ArrayList r82 = new ArrayList();
            int r92 = 0;
            boolean r10 = false;
        L18:
            if (r92 >= r72.size()) goto L45;
            Object r112 = r62.get(r72.get(r92));
            SendMessagesHelper.SendingMediaInfo r132 = new SendMessagesHelper.SendingMediaInfo();
            r82.add(r132);
            String r0 = null;
            if ((r112 instanceof MediaController.PhotoEntry) == false) goto L30;
            MediaController.PhotoEntry r113 = (MediaController.PhotoEntry) r112;
            String r102 = r113.imagePath;
            if (r102 == null) goto L24;
            r132.path = r102;
        L25:
            r132.thumbPath = r113.thumbPath;
            r132.coverPath = r113.coverPath;
            r132.videoEditedInfo = r113.editedInfo;
            r132.isLivePhoto = r113.isLivePhoto();
            r132.isVideo = r113.isVideo;
            r132.livePhotoVideoOffset = r113.livePhotoVideoOffset;
            r132.discardLivePhoto = true;
            CharSequence r103 = r113.caption;
            if (r103 == null) goto L28;
            r0 = r103.toString();
        L28:
            r132.caption = r0;
            r132.entities = r113.entities;
            r132.masks = r113.stickers;
            r132.ttl = r113.ttl;
            TLRPC.VideoSize r104 = r113.emojiMarkup;
            r132.emojiMarkup = r104;
            r10 = r104 instanceof TLRPC.TL_videoSizeEmojiMarkup;
        L44:
            r92 = r92 + 1;
            goto L18
        L24:
            r132.path = r113.path;
            goto L25
        L30:
            if ((r112 instanceof MediaController.SearchImage) == false) goto L44;
            MediaController.SearchImage r114 = (MediaController.SearchImage) r112;
            String r14 = r114.imagePath;
            if (r14 == null) goto L34;
            r132.path = r14;
        L35:
            r132.thumbPath = r114.thumbPath;
            r132.coverPath = r114.coverPath;
            r132.videoEditedInfo = r114.editedInfo;
            CharSequence r142 = r114.caption;
            if (r142 == null) goto L38;
            r0 = r142.toString();
        L38:
            r132.caption = r0;
            r132.entities = r114.entities;
            r132.masks = r114.stickers;
            r132.ttl = r114.ttl;
            TLRPC.BotInlineResult r143 = r114.inlineResult;
            if (r143 != null) goto L41;
        L43:
            r114.date = (int) (System.currentTimeMillis() / 1000);
            goto L44
        L41:
            if (r114.type != 1) goto L43;
            r132.inlineResult = r143;
            r132.params = r114.params;
            goto L43
        L34:
            r132.searchImage = r114;
            goto L35
        L45:
            ImageUpdater.m10917$$Nest$mdidSelectPhotos(this.this$0, r10, r82);
            if (r4 == 8) goto L55;
            ImageUpdater.m10915$$Nest$fgetchatAttachAlert(this.this$0).dismiss(true);
            return;
        L55:
            return;
        L12:
            if (r4 == 7) goto L16;
            ImageUpdater.m10915$$Nest$fgetchatAttachAlert(this.this$0).dismissWithButtonClick(r4);
            processSelectedAttach(r4);
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didSelectBot(TLRPC.User r1) {
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void doOnIdle(Runnable r1) {
            r1.run();
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public boolean needEnterComment() {
            return false;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void onCameraOpened() {
            AndroidUtilities.hideKeyboard(this.this$0.parentFragment.getFragmentView().findFocus());
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void openAvatarsSearch() {
            this.this$0.openSearch();
        }
    }

    /* renamed from: org.telegram.ui.Components.ImageUpdater$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 implements PhotoAlbumPickerActivity.PhotoAlbumPickerActivityDelegate {
        final /* synthetic */ ImageUpdater this$0;

        public AnonymousClass3(ImageUpdater r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PhotoAlbumPickerActivity.PhotoAlbumPickerActivityDelegate
        public void didSelectPhotos(ArrayList<SendMessagesHelper.SendingMediaInfo> r1, boolean r2, int r3) {
            ImageUpdater.m10917$$Nest$mdidSelectPhotos(this.this$0, false, r1);
        }

        @Override // org.telegram.ui.PhotoAlbumPickerActivity.PhotoAlbumPickerActivityDelegate
        public void startPhotoSelectActivity() {
            Intent r0 = new Intent("android.intent.action.GET_CONTENT");     // Catch: Exception -> L4
            r0.setType("image/*");     // Catch: Exception -> L4
            this.this$0.parentFragment.startActivityForResult(r0, 14);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }
    }

    /* renamed from: org.telegram.ui.Components.ImageUpdater$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends PhotoViewer.EmptyPhotoViewerProvider {
        final /* synthetic */ ImageUpdater this$0;
        final /* synthetic */ ArrayList val$arrayList;

        public AnonymousClass4(ImageUpdater r1, ArrayList r2) {
            this.this$0 = r1;
            this.val$arrayList = r2;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean allowCaption() {
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean canScrollAway() {
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r1, TLRPC.FileLocation r2, int r3, boolean r4, boolean r5) {
            if (ImageUpdater.m10916$$Nest$fgetdelegate(this.this$0) != null) goto L7;
            return null;
        L7:
            return ImageUpdater.m10916$$Nest$fgetdelegate(this.this$0).getCloseIntoObject();
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void sendButtonPressed(int r1, VideoEditedInfo r2, boolean r3, int r4, int r5, boolean r6) {
            MediaController.PhotoEntry r12 = (MediaController.PhotoEntry) this.val$arrayList.get(0);
            this.this$0.processEntry(r12);
        }
    }

    /* loaded from: classes7.dex */
    public static class AvatarFor {
        public TLRPC.User fromObject;
        public boolean isVideo;
        public final TLObject object;
        public boolean self;
        public final int type;

        public AvatarFor(TLObject r1, int r2) {
            this.object = r1;
            this.type = r2;
            if ((r1 instanceof TLRPC.User) == true) goto L5;
        L7:
            boolean r12 = false;
        L8:
            this.self = r12;
            return;
        L5:
            if (((TLRPC.User) r1).self == false) goto L7;
            r12 = true;
            goto L8
        }
    }

    public interface ImageUpdaterDelegate {
        default boolean canFinishFragment() {
            return true;
        }

        default void didStartUpload(boolean r1, boolean r2) {
        }

        default void didUploadFailed() {
        }

        void didUploadPhoto(TLRPC.InputFile r1, TLRPC.InputFile r2, double r3, String r5, TLRPC.PhotoSize r6, TLRPC.PhotoSize r7, boolean r8, TLRPC.VideoSize r9);

        default PhotoViewer.PlaceProviderObject getCloseIntoObject() {
            return null;
        }

        default String getInitialSearchString() {
            return null;
        }

        default void onUploadProgressChanged(float r1) {
        }

        default boolean supportsBulletin() {
            return false;
        }
    }

    /* renamed from: $r8$lambda$QrysCIg-NRpKsI_bdOkEadxHZLc, reason: not valid java name */
    public static /* synthetic */ void m10914$r8$lambda$QrysCIgNRpKsI_bdOkEadxHZLc(ImageUpdater r0, String r1, Uri r2) {
        r0.lambda$startCrop$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$c9wvfC_B2k_MMTddpCxuQJAHeIE(ImageUpdater r0, ArrayList r1, Runnable r2, DialogInterface r3, int r4) {
        r0.lambda$openMenu$0(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetchatAttachAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAttachAlert m10915$$Nest$fgetchatAttachAlert(ImageUpdater r0) {
        return r0.chatAttachAlert;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageUpdaterDelegate m10916$$Nest$fgetdelegate(ImageUpdater r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$mdidSelectPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10917$$Nest$mdidSelectPhotos(ImageUpdater r0, boolean r1, ArrayList r2) {
        r0.didSelectPhotos(r1, r2);
    }

    public ImageUpdater(boolean r3, int r4, boolean r5) {
        this.currentAccount = UserConfig.selectedAccount;
        this.picturePath = null;
        this.useAttachMenu = true;
        this.searchAvailable = true;
        this.uploadAfterSelect = true;
        this.imageReceiver = new ImageReceiver(null);
        this.canSelectVideo = r3;
        this.supportEmojiMarkup = r5;
        this.setForType = r4;
    }

    private void cleanup() {
        this.uploadingImage = null;
        this.uploadingVideo = null;
        this.videoPath = null;
        this.convertingVideo = null;
        if (this.clearAfterUpdate == false) goto L6;
        this.imageReceiver.setImageBitmap(null);
        this.parentFragment = null;
        this.delegate = null;
        return;
    }

    private void createChatAttachView() {
        BaseFragment r0 = this.parentFragment;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getParentActivity() != null) goto L8;
        return;
    L8:
        if (this.chatAttachAlert != null) goto L20;
        ChatAttachAlert r02 = new ChatAttachAlert(this.parentFragment.getParentActivity(), this.parentFragment, this.forceDarkTheme, this.showingFromDialog);
        this.chatAttachAlert = r02;
        if (this.canSelectVideo == false) goto L12;
        int r3 = 2;
    L13:
        boolean r4 = this.searchAvailable;
        ImageUpdaterDelegate r5 = this.delegate;
        if (r5 != null) goto L16;
    L18:
        ImageUpdater$$ExternalSyntheticLambda1 r6 = null;
    L19:
        r02.setAvatarPicker(r3, r4, r6);
        this.chatAttachAlert.setDelegate(new AnonymousClass2(this));
        this.chatAttachAlert.setImageUpdater(this);
        goto L20
    L16:
        if (r5.supportsBulletin() == false) goto L18;
        ImageUpdaterDelegate r52 = this.delegate;
        Objects.requireNonNull(r52);
        r6 = new ImageUpdater$$ExternalSyntheticLambda1(r52);
        goto L19
    L12:
        r3 = 1;
    L20:
        int r03 = this.type;
        if (r03 != 1) goto L24;
        this.chatAttachAlert.getSelectedTextView().setText(LocaleController.formatString("SetPhotoFor", R.string.SetPhotoFor, new Object[]{this.user.first_name}));
        return;
    L24:
        if (r03 != 2) goto L28;
        this.chatAttachAlert.getSelectedTextView().setText(LocaleController.formatString("SuggestPhotoFor", R.string.SuggestPhotoFor, new Object[]{this.user.first_name}));
        return;
    }

    private void didSelectPhotos(boolean r17, ArrayList<SendMessagesHelper.SendingMediaInfo> r18) {
        if (r18.isEmpty() == true) goto L42;
        SendMessagesHelper.SendingMediaInfo r2 = r18.get(0);
        Bitmap r8 = null;
        if (r2.isVideo == true) goto L9;
        if (r2.videoEditedInfo != null) goto L9;
    L12:
        String r3 = r2.path;
        if (r3 == null) goto L16;
        Bitmap r1 = ImageLoader.loadBitmap(r3, null, 800.0f, 800.0f, true);
    L15:
        MessageObject r5 = null;
        r8 = r1;
    L11:
        boolean r12 = r17;
    L40:
        processBitmap(r12, r8, r5);
        return;
    L16:
        MediaController.SearchImage r32 = r2.searchImage;
        if (r32 == null) goto L39;
        TLRPC.Photo r10 = r32.photo;
        if (r10 == null) goto L32;
        TLRPC.PhotoSize r33 = FileLoader.getClosestPhotoSizeWithSize(r10.sizes, AndroidUtilities.getPhotoSize());
        if (r33 == null) goto L30;
        File r4 = FileLoader.getInstance(this.currentAccount).getPathToAttach(r33, true);
        this.finalPath = r4.getAbsolutePath();
        if (r4.exists() == true) goto L27;
        r4 = FileLoader.getInstance(this.currentAccount).getPathToAttach(r33, false);
        if (r4.exists() == true) goto L27;
        r4 = null;
    L27:
        if (r4 == null) goto L29;
        r1 = ImageLoader.loadBitmap(r4.getAbsolutePath(), null, 800.0f, 800.0f, true);
        goto L15
    L29:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileLoadFailed);
        this.uploadingImage = FileLoader.getAttachFileName(r33.location);
        this.imageReceiver.setImage(ImageLocation.getForPhoto(r33, r2.searchImage.photo), null, null, "jpg", null, 1);
    L30:
        r1 = null;
        goto L15
    L32:
        if (r32.imageUrl == null) goto L39;
        File r34 = new File(FileLoader.getDirectory(4), Utilities.MD5(r2.searchImage.imageUrl) + "." + ImageLoader.getHttpUrlExtension(r2.searchImage.imageUrl, "jpg"));
        this.finalPath = r34.getAbsolutePath();
        if (r34.exists() == true) goto L36;
    L38:
        this.uploadingImage = r2.searchImage.imageUrl;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.httpFileDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.httpFileDidFailedLoad);
        this.imageReceiver.setImage(r2.searchImage.imageUrl, null, null, "jpg", 1);
        goto L39
    L36:
        if (r34.length() == 0) goto L38;
        r1 = ImageLoader.loadBitmap(r34.getAbsolutePath(), null, 800.0f, 800.0f, true);
    L39:
        r12 = r17;
        r5 = null;
    L9:
        if (r2.isLivePhoto == true) goto L12;
        TLRPC.TL_message r35 = new TLRPC.TL_message();
        r35.id = 0;
        r35.message = _UrlKt.FRAGMENT_ENCODE_SET;
        r35.media = new TLRPC.TL_messageMediaEmpty();
        r35.action = new TLRPC.TL_messageActionEmpty();
        r35.dialog_id = 0;
        r5 = new MessageObject(UserConfig.selectedAccount, r35, false, false);
        r5.messageOwner.attachPath = new File(FileLoader.getDirectory(4), SharedConfig.getLastLocalId() + "_avatar.mp4").getAbsolutePath();
        r5.videoEditedInfo = r2.videoEditedInfo;
        r5.emojiMarkup = r2.emojiMarkup;
        r8 = ImageLoader.loadBitmap(r2.thumbPath, null, 800.0f, 800.0f, true);
        goto L11
    }

    private /* synthetic */ void lambda$openMenu$0(ArrayList r1, Runnable r2, DialogInterface r3, int r4) {
        int r12 = ((Integer) r1.get(r4)).intValue();
        if (r12 != 0) goto L5;
        openCamera();
        return;
    L5:
        if (r12 != 1) goto L7;
        openGallery();
        return;
    L7:
        if (r12 != 2) goto L9;
        openSearch();
        return;
    L9:
        if (r12 != 3) goto L11;
        r2.run();
        return;
    L11:
        if (r12 == 4) goto L13;
        return;
    L13:
        openVideoCamera();
    }

    private /* synthetic */ void lambda$startCrop$1(String r4, Uri r5) {
        LaunchActivity r0 = (LaunchActivity) this.parentFragment.getParentActivity();     // Catch: Exception -> L8
        if (r0 != null) goto L5;
        return;
    L5:
        Bundle r1 = new Bundle();     // Catch: Exception -> L8
        if (r4 == null) goto L10;
        r1.putString("photoPath", r4);     // Catch: Exception -> L8
    L12:
        PhotoCropActivity r2 = new PhotoCropActivity(r1);     // Catch: Exception -> L8
        r2.setDelegate(this);     // Catch: Exception -> L8
        r0.presentFragment(r2);     // Catch: Exception -> L8
        return;
    L10:
        if (r5 == null) goto L12;
        r1.putParcelable("photoUri", r5);     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
        processBitmap(false, ImageLoader.loadBitmap(r4, r5, 800.0f, 800.0f, true), null);
    }

    private void openAttachMenu(DialogInterface.OnDismissListener r4) {
        BaseFragment r0 = this.parentFragment;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getParentActivity() == null) goto L13;
        createChatAttachView();
        this.chatAttachAlert.setOpenWithFrontFaceCamera(this.openWithFrontfaceCamera);
        this.chatAttachAlert.setMaxSelectedPhotos(1, false);
        this.chatAttachAlert.getPhotoLayout().loadGalleryPhotos();
        this.chatAttachAlert.init();
        this.chatAttachAlert.setOnHideListener(r4);
        int r42 = this.type;
        if (r42 == 0) goto L10;
        this.chatAttachAlert.avatarFor(new AvatarFor(this.user, r42));
    L10:
        ChatAttachAlert r43 = this.chatAttachAlert;
        r43.forUser = this.forUser;
        this.parentFragment.showDialog(r43);
        return;
    }

    private void processBitmap(boolean r28, Bitmap r29, MessageObject r30) {
        if (r29 == null) goto L54;
        this.uploadedVideo = null;
        this.uploadedPhoto = null;
        this.convertingVideo = null;
        this.videoPath = null;
        if (r30 != null) goto L8;
        TLRPC.VideoSize r4 = null;
    L9:
        this.vectorMarkup = r4;
        this.bigPhoto = ImageLoader.scaleAndSaveImage(r29, 800.0f, 800.0f, 80, false, 320, 320);
        TLRPC.PhotoSize r42 = ImageLoader.scaleAndSaveImage(r29, 150.0f, 150.0f, 80, false, 150, 150);
        this.smallPhoto = r42;
        if (r42 != null) goto L48;
    L12:
        r29.recycle();
        if (this.bigPhoto == null) goto L50;
        UserConfig.getInstance(this.currentAccount).saveConfig(false);
        this.uploadingImage = FileLoader.getDirectory(4) + "/" + this.bigPhoto.location.volume_id + "_" + this.bigPhoto.location.local_id + ".jpg";
        if (this.uploadAfterSelect == false) goto L43;
        if (r30 != null) goto L18;
    L36:
        ImageUpdaterDelegate r2 = this.delegate;
        if (r2 == null) goto L39;
        r2.didStartUpload(r28, false);
    L39:
        this.isVideo = false;
    L40:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploadProgressChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploadFailed);
        if (this.uploadingImage == null) goto L43;
        FileLoader.getInstance(this.currentAccount).uploadFile(this.uploadingImage, false, true, 16777216);
        goto L43
    L18:
        if (r30.videoEditedInfo == null) goto L36;
        if (this.supportEmojiMarkup == true) goto L22;
    L29:
        this.convertingVideo = r30;
        VideoEditedInfo r43 = r30.videoEditedInfo;
        long r8 = r43.startTime;
        if (r8 >= 0) goto L32;
        r8 = 0;
    L32:
        this.videoTimestamp = (r43.avatarStartTime - r8) / 1000000.0d;
        r43.shouldLimitFps = false;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.filePreparingStarted);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.filePreparingFailed);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileNewChunkAvailable);
        MediaController.getInstance().scheduleVideoConvert(r30, true, true, false);
        this.uploadingImage = null;
        ImageUpdaterDelegate r22 = this.delegate;
        if (r22 == null) goto L35;
        r22.didStartUpload(r28, true);
    L35:
        this.isVideo = true;
        goto L40
    L22:
        if (MessagesController.getInstance(this.currentAccount).uploadMarkupVideo == true) goto L29;
        ImageUpdaterDelegate r23 = this.delegate;
        if (r23 == null) goto L26;
        r23.didStartUpload(r28, true);
    L26:
        ImageUpdaterDelegate r7 = this.delegate;
        if (r7 == null) goto L51;
        r7.didUploadPhoto(null, null, 0.0d, null, this.bigPhoto, this.smallPhoto, this.isVideo, null);
        this.delegate.didUploadPhoto(null, null, this.videoTimestamp, this.videoPath, this.bigPhoto, this.smallPhoto, this.isVideo, this.vectorMarkup);
        cleanup();
        return;
    L51:
        return;
    L43:
        ImageUpdaterDelegate r82 = this.delegate;
        if (r82 == null) goto L53;
        r82.didUploadPhoto(null, null, 0.0d, null, this.bigPhoto, this.smallPhoto, this.isVideo, null);
        return;
    L53:
        return;
    L50:
        return;
    L48:
        Bitmap r44 = BitmapFactory.decodeFile(FileLoader.getInstance(this.currentAccount).getPathToAttach(this.smallPhoto, true).getAbsolutePath());     // Catch: Throwable -> L47
        this.smallPhotoBitmap = r44;     // Catch: Throwable -> L47
        ImageLoader.getInstance().putImageToCache(new BitmapDrawable(r44), this.smallPhoto.location.volume_id + "_" + this.smallPhoto.location.local_id + "@50_50", true);     // Catch: Throwable -> L47
        goto L12
    L8:
        r4 = r30.emojiMarkup;
        goto L9
    }

    private void startCrop(String r2, Uri r3) {
        AndroidUtilities.runOnUIThread(new ImageUpdater$$ExternalSyntheticLambda0(this, r2, r3));
    }

    public void cancel() {
        this.canceled = true;
        if (this.uploadingImage == null) goto L6;
        FileLoader.getInstance(this.currentAccount).cancelFileUpload(this.uploadingImage, false);
    L6:
        if (this.uploadingVideo == null) goto L8;
        FileLoader.getInstance(this.currentAccount).cancelFileUpload(this.uploadingVideo, false);
    L8:
        ImageUpdaterDelegate r3 = this.delegate;
        if (r3 == null) goto L12;
        r3.didUploadFailed();
        return;
    }

    public void clear() {
        this.canceled = false;
        if (this.uploadingImage == null) goto L5;
    L10:
        this.clearAfterUpdate = true;
    L11:
        ChatAttachAlert r0 = this.chatAttachAlert;
        if (r0 == null) goto L15;
        r0.dismissInternal();
        this.chatAttachAlert.onDestroy();
        return;
    L15:
        return;
    L5:
        if (this.uploadingVideo != null) goto L10;
        if (this.convertingVideo != null) goto L10;
        this.parentFragment = null;
        this.delegate = null;
        goto L11
    }

    @Override // org.telegram.ui.PhotoCropActivity.PhotoEditActivityDelegate
    public void didFinishEdit(Bitmap r3) {
        processBitmap(false, r3, null);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r18, int r19, Object... r20) {
        int r2 = NotificationCenter.fileUploaded;
        if (r18 != r2) goto L5;
    L88:
        String r3 = (String) r20[0];
        if (r3.equals(this.uploadingImage) == false) goto L94;
        this.uploadingImage = null;
        if (r18 != r2) goto L99;
        this.uploadedPhoto = (TLRPC.InputFile) r20[1];
    L99:
        if (this.uploadingImage == null) goto L101;
        return;
    L101:
        if (this.uploadingVideo == null) goto L103;
        return;
    L103:
        if (this.convertingVideo != null) goto L127;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, r2);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploadProgressChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploadFailed);
        if (r18 != r2) goto L109;
        ImageUpdaterDelegate r5 = this.delegate;
        if (r5 == null) goto L109;
        r5.didUploadPhoto(this.uploadedPhoto, this.uploadedVideo, this.videoTimestamp, this.videoPath, this.bigPhoto, this.smallPhoto, this.isVideo, this.vectorMarkup);
    L109:
        cleanup();
        return;
    L127:
        return;
    L94:
        if (r3.equals(this.uploadingVideo) == false) goto L124;
        this.uploadingVideo = null;
        if (r18 != r2) goto L99;
        this.uploadedVideo = (TLRPC.InputFile) r20[1];
        goto L99
    L124:
        return;
    L5:
        if (r18 == NotificationCenter.fileUploadFailed) goto L88;
        if (r18 != NotificationCenter.fileUploadProgressChanged) goto L19;
        String r1 = (String) r20[0];
        if (this.convertingVideo == null) goto L12;
        String r22 = this.uploadingVideo;
    L14:
        if (this.delegate != null) goto L16;
        return;
    L16:
        if (r1.equals(r22) == false) goto L115;
        Long r12 = (Long) r20[1];
        Long r23 = (Long) r20[2];
        float r13 = Math.min(1.0f, r12.longValue() / r23.longValue());
        ImageUpdaterDelegate r24 = this.delegate;
        this.currentImageProgress = r13;
        r24.onUploadProgressChanged(r13);
        return;
    L115:
        return;
    L12:
        r22 = this.uploadingImage;
        goto L14
    L19:
        int r25 = NotificationCenter.fileLoaded;
        if (r18 != r25) goto L22;
    L76:
        String r7 = (String) r20[0];
        this.currentImageProgress = 1.0f;
        if (r7.equals(this.uploadingImage) == false) goto L122;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, r25);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileLoadFailed);
        NotificationCenter r6 = NotificationCenter.getInstance(this.currentAccount);
        int r72 = NotificationCenter.httpFileDidLoad;
        r6.removeObserver(this, r72);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.httpFileDidFailedLoad);
        this.uploadingImage = null;
        if (r18 == r25) goto L86;
        if (r18 == r72) goto L86;
        this.imageReceiver.setImageBitmap(null);
        ImageUpdaterDelegate r0 = this.delegate;
        if (r0 == null) goto L123;
        r0.didUploadFailed();
        return;
    L123:
        return;
    L86:
        processBitmap(false, ImageLoader.loadBitmap(this.finalPath, null, 800.0f, 800.0f, true), null);
        return;
    L122:
        return;
    L22:
        if (r18 == NotificationCenter.fileLoadFailed) goto L76;
        if (r18 == NotificationCenter.httpFileDidLoad) goto L76;
        if (r18 == NotificationCenter.httpFileDidFailedLoad) goto L76;
        int r26 = NotificationCenter.filePreparingFailed;
        if (r18 != r26) goto L38;
        if (((MessageObject) r20[0]) == this.convertingVideo) goto L33;
        return;
    L33:
        if (this.parentFragment == null) goto L128;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.filePreparingStarted);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, r26);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileNewChunkAvailable);
        cleanup();
        return;
    L128:
        return;
    L38:
        if (r18 != NotificationCenter.fileNewChunkAvailable) goto L68;
        if (((MessageObject) r20[0]) == this.convertingVideo) goto L42;
        return;
    L42:
        if (this.parentFragment == null) goto L129;
        String r9 = (String) r20[1];
        long r11 = ((Long) r20[2]).longValue();
        long r132 = ((Long) r20[3]).longValue();
        this.parentFragment.getFileLoader().checkUploadNewDataAvailable(r9, false, r11, r132);
        if (r132 == 0) goto L118;
        double r14 = ((Long) r20[5]).longValue() / 1000000.0d;
        if (this.videoTimestamp <= r14) goto L49;
        this.videoTimestamp = r14;
    L49:
        Bitmap r10 = SendMessagesHelper.createVideoThumbnailAtTime(r9, (long) (this.videoTimestamp * 1000.0d), null, true);
        if (r10 == null) goto L66;
        File r15 = FileLoader.getInstance(this.currentAccount).getPathToAttach(this.smallPhoto, true);
        if (r15 != null) goto L54;
    L57:
        File r16 = FileLoader.getInstance(this.currentAccount).getPathToAttach(this.bigPhoto, true);
        if (r16 != null) goto L60;
    L63:
        this.bigPhoto = ImageLoader.scaleAndSaveImage(r10, 800.0f, 800.0f, 80, false, 320, 320);
        TLRPC.PhotoSize r17 = ImageLoader.scaleAndSaveImage(r10, 150.0f, 150.0f, 80, false, 150, 150);
        this.smallPhoto = r17;
        if (r17 == null) goto L66;
        Bitmap r110 = BitmapFactory.decodeFile(FileLoader.getInstance(this.currentAccount).getPathToAttach(this.smallPhoto, true).getAbsolutePath());     // Catch: Throwable -> L111
        ImageLoader.getInstance().putImageToCache(new BitmapDrawable(r110), this.smallPhoto.location.volume_id + "_" + this.smallPhoto.location.local_id + "@50_50", true);     // Catch: Throwable -> L111
        goto L66
    L60:
        if (BuildVars.LOGS_ENABLED == false) goto L62;
        FileLog.e("delete file " + r16);
    L62:
        r16.delete();
        goto L63
    L54:
        if (BuildVars.LOGS_ENABLED == false) goto L56;
        FileLog.e("delete file " + r15);
    L56:
        r15.delete();
    L66:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.filePreparingStarted);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.filePreparingFailed);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileNewChunkAvailable);
        this.videoPath = r9;
        this.uploadingVideo = r9;
        this.convertingVideo = null;
        return;
    L118:
        return;
    L129:
        return;
    L68:
        if (r18 == NotificationCenter.filePreparingStarted) goto L70;
        return;
    L70:
        if (((MessageObject) r20[0]) != this.convertingVideo) goto L121;
        BaseFragment r111 = this.parentFragment;
        if (r111 == null) goto L130;
        this.uploadingVideo = (String) r20[1];
        r111.getFileLoader().uploadFile(this.uploadingVideo, false, false, (int) this.convertingVideo.videoEditedInfo.estimatedSize, 33554432, false);
        return;
    L130:
        return;
    }

    public boolean dismissCurrentDialog(Dialog r3) {
        ChatAttachAlert r0 = this.chatAttachAlert;
        if (r0 == null) goto L7;
        if (r3 != r0) goto L7;
        r0.getPhotoLayout().closeCamera(false);
        this.chatAttachAlert.dismissInternal();
        this.chatAttachAlert.getPhotoLayout().hideCamera(true);
        return true;
    L7:
        return false;
    }

    public boolean dismissDialogOnPause(Dialog r1) {
        if (r1 == this.chatAttachAlert) goto L6;
        return true;
    L6:
        return false;
    }

    public float getCurrentImageProgress() {
        return this.currentImageProgress;
    }

    public Bitmap getPreviewBitmap() {
        return this.smallPhotoBitmap;
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public boolean isUploadingImage() {
        if (this.uploadingImage == null) goto L5;
        return true;
    L5:
        if (this.uploadingVideo == null) goto L7;
        return true;
    L7:
        if (this.convertingVideo != null) goto L14;
        return false;
    L14:
        return true;
    }

    public void onActivityResult(int r3, int r4, Intent r5) {
        if (r4 == (-1)) goto L5;
        return;
    L5:
        if (r3 != 0) goto L7;
    L25:
        createChatAttachView();
        ChatAttachAlert r0 = this.chatAttachAlert;
        if (r0 == null) goto L28;
        r0.onActivityResultFragment(r3, r5, this.currentPicturePath);
    L28:
        this.currentPicturePath = null;
        return;
    L7:
        if (r3 == 2) goto L25;
        if (r3 != 13) goto L14;
        this.parentFragment.getParentActivity().overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
        PhotoViewer.getInstance().setParentActivity(this.parentFragment);
        Pair<Integer, Integer> r32 = AndroidUtilities.getImageOrientation(this.currentPicturePath);
        openPhotoForEdit(this.currentPicturePath, null, r32, false);
        AndroidUtilities.addMediaToGallery(this.currentPicturePath);
        this.currentPicturePath = null;
        return;
    L14:
        if (r3 != 14) goto L22;
        if (r5 != null) goto L17;
        return;
    L17:
        if (r5.getData() == null) goto L33;
        startCrop(null, r5.getData());
        return;
    L33:
        return;
    L22:
        if (r3 != 15) goto L32;
        openPhotoForEdit(this.currentPicturePath, null, 0, true);
        AndroidUtilities.addMediaToGallery(this.currentPicturePath);
        this.currentPicturePath = null;
        return;
    }

    public void onPause() {
        ChatAttachAlert r0 = this.chatAttachAlert;
        if (r0 == null) goto L6;
        r0.onPause();
        return;
    }

    public void onRequestPermissionsResultFragment(int r1, String[] r2, int[] r3) {
        ChatAttachAlert r22 = this.chatAttachAlert;
        if (r22 != null) goto L5;
        return;
    L5:
        if (r1 != 17) goto L9;
        r22.getPhotoLayout().checkCamera(false);
        this.chatAttachAlert.getPhotoLayout().checkStorage();
        return;
    L9:
        if (r1 != 4) goto L13;
        r22.getPhotoLayout().checkStorage();
        return;
    }

    public void onResume() {
        ChatAttachAlert r0 = this.chatAttachAlert;
        if (r0 == null) goto L6;
        r0.onResume();
        return;
    }

    public void openCamera() {
        BaseFragment r1 = this.parentFragment;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.getParentActivity() == null) goto L21;
    L16:
        e = move-exception;
        FileLog.e(e);
        return;
    L8:
        if (this.parentFragment.getParentActivity().checkSelfPermission("android.permission.CAMERA") == 0) goto L11;
        this.parentFragment.getParentActivity().requestPermissions(new String[]{"android.permission.CAMERA"}, 20);     // Catch: Exception -> L16
        return;
    L11:
        Intent r0 = new Intent("android.media.action.IMAGE_CAPTURE");     // Catch: Exception -> L16
        File r12 = AndroidUtilities.generatePicturePath();     // Catch: Exception -> L16
        if (r12 == null) goto L14;
        r0.putExtra("output", FileProvider.getUriForFile(this.parentFragment.getParentActivity(), ApplicationLoader.getApplicationId() + ".provider", r12));     // Catch: Exception -> L16
        r0.addFlags(2);     // Catch: Exception -> L16
        r0.addFlags(1);     // Catch: Exception -> L16
        this.currentPicturePath = r12.getAbsolutePath();     // Catch: Exception -> L16
    L14:
        this.parentFragment.startActivityForResult(r0, 13);     // Catch: Exception -> L16
        return;
    }

    public void openGallery() {
        BaseFragment r0 = this.parentFragment;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.getParentActivity() == null) goto L21;
        if (this.canSelectVideo == false) goto L13;
        if (SystemUtils.isImagesAndVideoPermissionGranted() == true) goto L21;
    L14:
        boolean r02 = this.canSelectVideo;
        BaseFragment r4 = this.parentFragment;
        if (r02 == false) goto L18;
        SystemUtils.requestImagesAndVideoPermission(r4.getParentActivity(), 151);
        return;
    L18:
        SystemUtils.requestImagesPermission(r4.getParentActivity(), 151);
        return;
    L13:
        if (SystemUtils.isImagesPermissionGranted() == false) goto L14;
    L21:
        if (this.canSelectVideo == false) goto L23;
        int r1 = PhotoAlbumPickerActivity.SELECT_TYPE_AVATAR_VIDEO;
    L24:
        PhotoAlbumPickerActivity r03 = new PhotoAlbumPickerActivity(r1, false, false, null);
        r03.setAllowSearchImages(this.searchAvailable);
        r03.setDelegate(new AnonymousClass3(this));
        this.parentFragment.presentFragment(r03);
        return;
    L23:
        r1 = PhotoAlbumPickerActivity.SELECT_TYPE_AVATAR;
        goto L24
    }

    public void openMenu(boolean r10, Runnable r11, DialogInterface.OnDismissListener r12, int r13) {
        BaseFragment r0 = this.parentFragment;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getParentActivity() == null) goto L35;
        this.canceled = false;
        this.type = r13;
        if (this.useAttachMenu == false) goto L11;
        openAttachMenu(r12);
        return;
    L11:
        BottomSheet.Builder r1 = new BottomSheet.Builder(this.parentFragment.getParentActivity());
        if (r13 != 1) goto L14;
        r1.setTitle(LocaleController.formatString("SetPhotoFor", R.string.SetPhotoFor, new Object[]{this.user.first_name}), true);
    L17:
        ArrayList r132 = new ArrayList();
        ArrayList r4 = new ArrayList();
        ArrayList r5 = new ArrayList();
        r132.add(LocaleController.getString(R.string.ChooseTakePhoto));
        r4.add(Integer.valueOf(R.drawable.msg_camera));
        r5.add(0);
        if (this.canSelectVideo == false) goto L20;
        r132.add(LocaleController.getString(R.string.ChooseRecordVideo));
        r4.add(Integer.valueOf(R.drawable.msg_video));
        r5.add(4);
    L20:
        r132.add(LocaleController.getString(R.string.ChooseFromGallery));
        r4.add(Integer.valueOf(R.drawable.msg_photos));
        r5.add(1);
        if (this.searchAvailable == false) goto L23;
        r132.add(LocaleController.getString(R.string.ChooseFromSearch));
        r4.add(Integer.valueOf(R.drawable.msg_search));
        r5.add(2);
    L23:
        if (r10 == false) goto L25;
        r132.add(LocaleController.getString(R.string.DeletePhoto));
        r4.add(Integer.valueOf(R.drawable.msg_delete));
        r5.add(3);
    L25:
        int[] r2 = new int[r4.size()];
        int r6 = r4.size();
        int r7 = 0;
    L26:
        if (r7 >= r6) goto L28;
        r2[r7] = ((Integer) r4.get(r7)).intValue();
        r7 = r7 + 1;
        goto L26
    L28:
        r1.setItems((CharSequence[]) r132.toArray(new CharSequence[0]), r2, new ImageUpdater$$ExternalSyntheticLambda2(this, r5, r11));
        BottomSheet r112 = r1.create();
        r112.setOnHideListener(r12);
        this.parentFragment.showDialog(r112);
        if (r10 == false) goto L34;
        r112.setItemColor(r132.size() - 1, Theme.getColor(Theme.key_text_RedBold), Theme.getColor(Theme.key_text_RedRegular));
        return;
    L34:
        return;
    L14:
        if (r13 != 2) goto L16;
        r1.setTitle(LocaleController.formatString("SuggestPhotoFor", R.string.SuggestPhotoFor, new Object[]{this.user.first_name}), true);
        goto L17
    L16:
        r1.setTitle(LocaleController.getString(R.string.ChoosePhoto), true);
        goto L17
    }

    public void openPhotoForEdit(String r3, String r4, int r5, boolean r6) {
        openPhotoForEdit(r3, r4, new Pair(Integer.valueOf(r5), 0), r6);
    }

    public void openPhotoForEdit(String r15, String r16, Pair<Integer, Integer> r17, boolean r18) {
        ArrayList<Object> r1 = new ArrayList();
        MediaController.PhotoEntry r0 = new MediaController.PhotoEntry(0, 0, 0, r15, ((Integer) r17.first).intValue(), false, 0, 0, 0).setOrientation(r17);
        r0.isVideo = r18;
        r0.thumbPath = r16;
        r1.add(r0);
        PhotoViewer.getInstance().setParentActivity(this.parentFragment);
        PhotoViewer.getInstance().openPhotoForSelect(r1, 0, 1, false, new AnonymousClass4(this, r1), null);
        PhotoViewer.getInstance().closePhotoAfterSelectWithAnimation = true;
    }

    public void openSearch() {
        if (this.parentFragment != null) goto L5;
        return;
    L5:
        HashMap r4 = new HashMap();
        ArrayList r5 = new ArrayList();
        PhotoPickerActivity r1 = new PhotoPickerActivity(0, null, r4, r5, 1, false, null, this.forceDarkTheme);
        r1.setDelegate(new AnonymousClass1(this, r4, r5));
        r1.setMaxSelectedPhotos(1, false);
        r1.setInitialSearchString(this.delegate.getInitialSearchString());
        boolean r0 = this.showingFromDialog;
        BaseFragment r10 = this.parentFragment;
        if (r0 == false) goto L9;
        r10.showAsSheet(r1);
        return;
    L9:
        r10.presentFragment(r1);
    }

    public void openVideoCamera() {
        BaseFragment r1 = this.parentFragment;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.getParentActivity() == null) goto L21;
    L16:
        e = move-exception;
        FileLog.e(e);
        return;
    L8:
        if (this.parentFragment.getParentActivity().checkSelfPermission("android.permission.CAMERA") == 0) goto L11;
        this.parentFragment.getParentActivity().requestPermissions(new String[]{"android.permission.CAMERA"}, 19);     // Catch: Exception -> L16
        return;
    L11:
        Intent r0 = new Intent("android.media.action.VIDEO_CAPTURE");     // Catch: Exception -> L16
        File r12 = AndroidUtilities.generateVideoPath();     // Catch: Exception -> L16
        if (r12 == null) goto L14;
        r0.putExtra("output", FileProvider.getUriForFile(this.parentFragment.getParentActivity(), ApplicationLoader.getApplicationId() + ".provider", r12));     // Catch: Exception -> L16
        r0.addFlags(2);     // Catch: Exception -> L16
        r0.addFlags(1);     // Catch: Exception -> L16
        r0.putExtra("android.intent.extras.CAMERA_FACING", 1);     // Catch: Exception -> L16
        r0.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);     // Catch: Exception -> L16
        r0.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);     // Catch: Exception -> L16
        r0.putExtra("android.intent.extra.durationLimit", 10);     // Catch: Exception -> L16
        this.currentPicturePath = r12.getAbsolutePath();     // Catch: Exception -> L16
    L14:
        this.parentFragment.startActivityForResult(r0, 15);     // Catch: Exception -> L16
        return;
    }

    public void processEntry(MediaController.PhotoEntry r11) {
        String r0 = r11.imagePath;
        if (r0 != null) goto L6;
        r0 = r11.path;
    L6:
        MessageObject r3 = null;
        if (r11.isVideo == true) goto L11;
        if (r11.editedInfo != null) goto L11;
    L13:
        Bitmap r112 = ImageLoader.loadBitmap(r0, null, 800.0f, 800.0f, true);
    L14:
        processBitmap(false, r112, r3);
        return;
    L11:
        if (r11.isLivePhoto() == true) goto L13;
        TLRPC.TL_message r02 = new TLRPC.TL_message();
        r02.id = 0;
        r02.message = _UrlKt.FRAGMENT_ENCODE_SET;
        r02.media = new TLRPC.TL_messageMediaEmpty();
        r02.action = new TLRPC.TL_messageActionEmpty();
        r02.dialog_id = 0;
        MessageObject r1 = new MessageObject(UserConfig.selectedAccount, r02, false, false);
        r1.messageOwner.attachPath = new File(FileLoader.getDirectory(4), SharedConfig.getLastLocalId() + "_avatar.mp4").getAbsolutePath();
        r1.videoEditedInfo = r11.editedInfo;
        r1.emojiMarkup = r11.emojiMarkup;
        r112 = ImageLoader.loadBitmap(r11.thumbPath, null, 800.0f, 800.0f, true);
        r3 = r1;
        goto L14
    }

    public void setDelegate(ImageUpdaterDelegate r1) {
        this.delegate = r1;
    }

    public void setForceDarkTheme(boolean r1) {
        this.forceDarkTheme = r1;
    }

    public void setOpenWithFrontfaceCamera(boolean r1) {
        this.openWithFrontfaceCamera = r1;
    }

    public void setSearchAvailable(boolean r1) {
        this.searchAvailable = r1;
        this.useAttachMenu = r1;
    }

    public void setSearchAvailable(boolean r1, boolean r2) {
        this.useAttachMenu = r2;
        this.searchAvailable = r1;
    }

    public void setShowingFromDialog(boolean r1) {
        this.showingFromDialog = r1;
    }

    public void setUploadAfterSelect(boolean r1) {
        this.uploadAfterSelect = r1;
    }

    public void setUser(TLRPC.User r1) {
        this.user = r1;
    }

    public void showAvatarConstructor(TLRPC.VideoSize r2) {
        createChatAttachView();
        this.chatAttachAlert.getPhotoLayout().showAvatarConstructorFragment(null, r2);
    }

    public void updateColors() {
        ChatAttachAlert r0 = this.chatAttachAlert;
        if (r0 == null) goto L6;
        r0.checkColors();
        return;
    }
}
