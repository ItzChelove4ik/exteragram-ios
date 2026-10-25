package org.telegram.ui.Components;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import androidx.core.content.FileProvider;
import com.exteragram.messenger.utils.system.SystemUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.PhotoAlbumPickerActivity;

/* loaded from: classes7.dex */
public class WallpaperUpdater {
    private String currentPicturePath;
    private File currentWallpaperPath;
    private WallpaperUpdaterDelegate delegate;
    private Activity parentActivity;
    private BaseFragment parentFragment;
    private File picturePath;

    /* renamed from: org.telegram.ui.Components.WallpaperUpdater$1, reason: invalid class name */
    public class AnonymousClass1 implements PhotoAlbumPickerActivity.PhotoAlbumPickerActivityDelegate {
        final /* synthetic */ WallpaperUpdater this$0;

        public AnonymousClass1(WallpaperUpdater r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PhotoAlbumPickerActivity.PhotoAlbumPickerActivityDelegate
        public void didSelectPhotos(ArrayList<SendMessagesHelper.SendingMediaInfo> r1, boolean r2, int r3) {
            WallpaperUpdater.m13584$$Nest$mdidSelectPhotos(this.this$0, r1);
        }

        @Override // org.telegram.ui.PhotoAlbumPickerActivity.PhotoAlbumPickerActivityDelegate
        public void startPhotoSelectActivity() {
            Intent r0 = new Intent("android.intent.action.PICK");     // Catch: Exception -> L4
            r0.setType("image/*");     // Catch: Exception -> L4
            WallpaperUpdater.m13583$$Nest$fgetparentActivity(this.this$0).startActivityForResult(r0, 11);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }
    }

    public interface WallpaperUpdaterDelegate {
        void didSelectWallpaper(File r1, Bitmap r2, boolean r3);

        void needOpenColorPicker();
    }

    public static /* synthetic */ void $r8$lambda$d0XIheEWZ9dKiF5m99_ALJAYUUs(WallpaperUpdater r0, boolean r1, DialogInterface r2, int r3) {
        r0.lambda$showAlert$0(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetparentActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Activity m13583$$Nest$fgetparentActivity(WallpaperUpdater r0) {
        return r0.parentActivity;
    }

    /* renamed from: -$$Nest$mdidSelectPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13584$$Nest$mdidSelectPhotos(WallpaperUpdater r0, ArrayList r1) {
        r0.didSelectPhotos(r1);
    }

    public WallpaperUpdater(Activity r2, BaseFragment r3, WallpaperUpdaterDelegate r4) {
        this.picturePath = null;
        this.parentActivity = r2;
        this.parentFragment = r3;
        this.delegate = r4;
    }

    private void didSelectPhotos(ArrayList<SendMessagesHelper.SendingMediaInfo> r5) {
    L8:
        th = move-exception;
        FileLog.e(th);
        return;
    L3:
        if (r5.isEmpty() == true) goto L13;
        SendMessagesHelper.SendingMediaInfo r52 = r5.get(0);     // Catch: Throwable -> L8
        if (r52.path == null) goto L14;
        this.currentWallpaperPath = new File(FileLoader.getDirectory(4), Utilities.random.nextInt() + ".jpg");     // Catch: Throwable -> L8
        Point r0 = AndroidUtilities.getRealScreenSize();     // Catch: Throwable -> L8
        Bitmap r53 = ImageLoader.loadBitmap(r52.path, null, (float) r0.x, (float) r0.y, true);     // Catch: Throwable -> L8
        r53.compress(Bitmap.CompressFormat.JPEG, 87, new FileOutputStream(this.currentWallpaperPath));     // Catch: Throwable -> L8
        this.delegate.didSelectWallpaper(this.currentWallpaperPath, r53, true);     // Catch: Throwable -> L8
        return;
    L14:
        return;
    }

    private /* synthetic */ void lambda$showAlert$0(boolean r6, DialogInterface r7, int r8) {
        if (r8 == 0) goto L24;
        if (r8 != 1) goto L13;
        openGallery();     // Catch: Exception -> L20
        return;
    L13:
        if (r6 == false) goto L28;
        if (r8 != 2) goto L17;
        this.delegate.needOpenColorPicker();     // Catch: Exception -> L20
        return;
    L17:
        if (r8 != 3) goto L30;
        this.delegate.didSelectWallpaper(null, null, false);     // Catch: Exception -> L20
        return;
    L30:
        return;
    L28:
        return;
    L24:
        Intent r62 = new Intent("android.media.action.IMAGE_CAPTURE");     // Catch: Exception -> L9
        File r82 = AndroidUtilities.generatePicturePath();     // Catch: Exception -> L9
        if (r82 == null) goto L7;
        r62.putExtra("output", FileProvider.getUriForFile(this.parentActivity, ApplicationLoader.getApplicationId() + ".provider", r82));     // Catch: Exception -> L9
        r62.addFlags(2);     // Catch: Exception -> L9
        r62.addFlags(1);     // Catch: Exception -> L9
        this.currentPicturePath = r82.getAbsolutePath();     // Catch: Exception -> L9
    L7:
        this.parentActivity.startActivityForResult(r62, 10);     // Catch: Exception -> L9
    L31:
        return;
    L9:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L20
        return;
    L20:
        e = move-exception;
        FileLog.e(e);
    }

    public void cleanup() {
    }

    public String getCurrentPicturePath() {
        return this.currentPicturePath;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v8 */
    public void onActivityResult(int r8, int r9, Intent r10) {
        if (r9 != (-1)) goto L52;
        ?? r92 = 10;
        FileOutputStream r5 = null;
        if (r8 != 10) goto L32;
        AndroidUtilities.addMediaToGallery(this.currentPicturePath);
        this.currentWallpaperPath = new File(FileLoader.getDirectory(4), Utilities.random.nextInt() + ".jpg");     // Catch: Throwable -> L17 Exception -> L19
        Point r82 = AndroidUtilities.getRealScreenSize();     // Catch: Throwable -> L17 Exception -> L19
        Bitmap r83 = ImageLoader.loadBitmap(this.currentPicturePath, null, (float) r82.x, (float) r82.y, true);     // Catch: Throwable -> L17 Exception -> L19
        FileOutputStream r93 = new FileOutputStream(this.currentWallpaperPath);     // Catch: Throwable -> L17 Exception -> L19
        r83.compress(Bitmap.CompressFormat.JPEG, 87, r93);     // Catch: Throwable -> L13 Exception -> L15
        this.delegate.didSelectWallpaper(this.currentWallpaperPath, r83, false);     // Catch: Throwable -> L13 Exception -> L15
        r92 = r93;
    L46:
        r92.close();     // Catch: Exception -> L11
    L24:
        this.currentPicturePath = null;
        return;
    L11:
        e = move-exception;
        FileLog.e(e);
    L15:
        e = e;
    L21:
        FileLog.e(e);     // Catch: Throwable -> L13
        r92 = r93;
        if (r93 == null) goto L24;
    L13:
        th = th;
        r5 = r92;
    L25:
        if (r5 != null) goto L47;
    L30:
        throw th;
    L47:
        r5.close();     // Catch: Exception -> L28
    L28:
        e = move-exception;
        FileLog.e(e);
    L19:
        e = e;
        r93 = null;
    L17:
        th = th;
        goto L25
    L32:
        if (r8 != 11) goto L54;
        if (r10 != null) goto L35;
        return;
    L35:
        if (r10.getData() == null) goto L56;
        this.currentWallpaperPath = new File(FileLoader.getDirectory(4), Utilities.random.nextInt() + ".jpg");     // Catch: Exception -> L39
        Point r84 = AndroidUtilities.getRealScreenSize();     // Catch: Exception -> L39
        Bitmap r85 = ImageLoader.loadBitmap(null, r10.getData(), (float) r84.x, (float) r84.y, true);     // Catch: Exception -> L39
        r85.compress(Bitmap.CompressFormat.JPEG, 87, new FileOutputStream(this.currentWallpaperPath));     // Catch: Exception -> L39
        this.delegate.didSelectWallpaper(this.currentWallpaperPath, r85, false);     // Catch: Exception -> L39
        return;
    L39:
        e = move-exception;
        FileLog.e(e);
        return;
    L56:
        return;
    L54:
        return;
    }

    public void openGallery() {
        BaseFragment r0 = this.parentFragment;
        if (r0 != null) goto L5;
        Intent r02 = new Intent("android.intent.action.PICK");
        r02.setType("image/*");
        this.parentActivity.startActivityForResult(r02, 11);
        return;
    L5:
        if (r0.getParentActivity() != null) goto L7;
    L10:
        PhotoAlbumPickerActivity r03 = new PhotoAlbumPickerActivity(PhotoAlbumPickerActivity.SELECT_TYPE_WALLPAPER, false, false, null);
        r03.setAllowSearchImages(false);
        r03.setDelegate(new AnonymousClass1(this));
        this.parentFragment.presentFragment(r03);
        return;
    L7:
        if (SystemUtils.isImagesPermissionGranted() == true) goto L10;
        SystemUtils.requestImagesPermission(this.parentFragment.getParentActivity());
    }

    public void setCurrentPicturePath(String r1) {
        this.currentPicturePath = r1;
    }

    public void showAlert(boolean r7) {
        BottomSheet.Builder r0 = new BottomSheet.Builder(this.parentActivity);
        r0.setTitle(LocaleController.getString(R.string.ChoosePhoto), true);
        if (r7 == false) goto L5;
        CharSequence[] r4 = {LocaleController.getString(R.string.ChooseTakePhoto), LocaleController.getString(R.string.SelectFromGallery), LocaleController.getString(R.string.SelectColor), LocaleController.getString(R.string.Default)};
        int[] r1 = null;
    L6:
        r0.setItems(r4, r1, new WallpaperUpdater$$ExternalSyntheticLambda0(this, r7));
        r0.show();
        return;
    L5:
        r4 = new CharSequence[]{LocaleController.getString(R.string.ChooseTakePhoto), LocaleController.getString(R.string.SelectFromGallery)};
        r1 = new int[]{R.drawable.msg_camera, R.drawable.msg_photos};
        goto L6
    }
}
