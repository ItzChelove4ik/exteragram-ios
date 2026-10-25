package org.telegram.messenger.utils;

import android.os.Bundle;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ImageUpdater;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public abstract class PhotoUtilities {
    /* renamed from: $r8$lambda$-rf7IQp9Jz1wkQHGw2xBg1dde9Y, reason: not valid java name */
    public static /* synthetic */ void m5197$r8$lambda$rf7IQp9Jz1wkQHGw2xBg1dde9Y(int r1, TLRPC.PhotoSize r2, TLRPC.PhotoSize r3, Runnable r4, INavigationLayout r5, TLObject r6, TLRPC.TL_error r7) {
        AndroidUtilities.runOnUIThread(new PhotoUtilities$$ExternalSyntheticLambda7(r6, r1, r2, r3, r4, r5));
    }

    /* renamed from: $r8$lambda$4Ua-qjCAnbRsMNEcpG9vGjmmDbE, reason: not valid java name */
    public static /* synthetic */ void m5198$r8$lambda$4UaqjCAnbRsMNEcpG9vGjmmDbE(ChatActivity r11, TLRPC.FileLocation[] r12, TLRPC.FileLocation[] r13, long r14, TLRPC.InputFile r16, TLRPC.InputFile r17, double r18, String r20, TLRPC.PhotoSize r21, TLRPC.PhotoSize r22, boolean r23, TLRPC.VideoSize r24) {
        if (r16 != null) goto L9;
        if (r17 != null) goto L9;
        if (r24 != null) goto L9;
        r12[0] = r22.location;
        r13[0] = r21.location;
        return;
    L9:
        TLRPC.TL_photos_uploadProfilePhoto r3 = new TLRPC.TL_photos_uploadProfilePhoto();
        if (r16 == null) goto L12;
        r3.file = r16;
        r3.flags |= 1;
    L12:
        if (r17 == null) goto L14;
        r3.video = r17;
        int r0 = r3.flags;
        r3.video_start_ts = r18;
        r3.flags = r0 | 6;
    L14:
        if (r24 == null) goto L16;
        r3.video_emoji_markup = r24;
        r3.flags |= 16;
    L16:
        r11.getConnectionsManager().sendRequest(r3, new PhotoUtilities$$ExternalSyntheticLambda2(r11, r12, r20, r13, r14));
    }

    /* renamed from: $r8$lambda$7M-ejB92G72HNHArTnHvAEjZ0Ys, reason: not valid java name */
    public static /* synthetic */ void m5199$r8$lambda$7MejB92G72HNHArTnHvAEjZ0Ys(TLRPC.InputFile r1, TLRPC.InputFile r2, double r3, TLRPC.VideoSize r5, int r6, TLRPC.PhotoSize r7, TLRPC.PhotoSize r8, Runnable r9, INavigationLayout r10, ImageUpdater r11) {
        TLRPC.TL_photos_uploadProfilePhoto r0 = new TLRPC.TL_photos_uploadProfilePhoto();
        if (r1 == null) goto L5;
        r0.file = r1;
        r0.flags |= 1;
    L5:
        if (r2 == null) goto L7;
        r0.video = r2;
        int r12 = r0.flags;
        r0.video_start_ts = r3;
        r0.flags = r12 | 6;
    L7:
        if (r5 == null) goto L9;
        r0.video_emoji_markup = r5;
        r0.flags |= 16;
    L9:
        ConnectionsManager.getInstance(r6).sendRequest(r0, new PhotoUtilities$$ExternalSyntheticLambda6(r6, r7, r8, r9, r10));
        r11.onPause();
    }

    public static /* synthetic */ void $r8$lambda$Mx2YzVeYn5DUhfZ5dqRwyzRUmJI(ChatActivity r9, TLRPC.FileLocation[] r10, String r11, TLRPC.FileLocation[] r12, long r13, TLObject r15, TLRPC.TL_error r16) {
        AndroidUtilities.runOnUIThread(new PhotoUtilities$$ExternalSyntheticLambda3(r16, r9, r15, r10, r11, r12, r13));
    }

    public static /* synthetic */ void $r8$lambda$VGyOKEJ4EPt_YZymp46qHxmuRrU(TLRPC.TL_error r16, ChatActivity r17, TLObject r18, TLRPC.FileLocation[] r19, String r20, TLRPC.FileLocation[] r21, long r22) {
        if (r16 != null) goto L27;
        TLRPC.User r3 = r17.getMessagesController().getUser(Long.valueOf(r17.getUserConfig().getClientUserId()));
        TLRPC.TL_photos_photo r4 = (TLRPC.TL_photos_photo) r18;
        ArrayList<TLRPC.PhotoSize> r5 = r4.photo.sizes;
        TLRPC.PhotoSize r6 = FileLoader.getClosestPhotoSizeWithSize(r5, 150);
        TLRPC.PhotoSize r52 = FileLoader.getClosestPhotoSizeWithSize(r5, 800);
        if (r4.photo.video_sizes.isEmpty() == false) goto L7;
        TLRPC.VideoSize r7 = null;
    L8:
        TLRPC.TL_userProfilePhoto r9 = new TLRPC.TL_userProfilePhoto();
        r3.photo = r9;
        r9.photo_id = r4.photo.id;
        if (r6 == null) goto L11;
        r9.photo_small = r6.location;
    L11:
        if (r52 == null) goto L14;
        r9.photo_big = r52.location;
    L14:
        if (r6 != null) goto L16;
    L18:
        if (r7 == null) goto L21;
        if (r20 == null) goto L21;
        new File(r20).renameTo(FileLoader.getInstance(r17.getCurrentAccount()).getPathToAttach(r7, "mp4", true));
    L25:
        r17.getMessagesController().getDialogPhotos(r3.id).addPhotoAtStart(r4.photo);
        ArrayList r0 = new ArrayList();
        r0.add(r3);
        r17.getMessagesStorage().putUsersAndChats(r0, null, false, true);
        TLRPC.UserFull r02 = r17.getMessagesController().getUserFull(r22);
        r02.profile_photo = r4.photo;
        r17.getMessagesStorage().updateUserInfo(r02, false);
        BulletinFactory.of(r17).createUsersBulletin(Collections.singletonList(r3), AndroidUtilities.replaceTags(LocaleController.getString(R.string.ApplyAvatarHintTitle)), AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.ApplyAvatarHint), new PhotoUtilities$$ExternalSyntheticLambda5(r22, r17)), null).show();
        return;
    L21:
        if (r52 == null) goto L25;
        if (r21[0] == null) goto L25;
        FileLoader.getInstance(r17.getCurrentAccount()).getPathToAttach(r21[0], true).renameTo(FileLoader.getInstance(r17.getCurrentAccount()).getPathToAttach(r52, true));
        goto L25
    L16:
        if (r19[0] == null) goto L18;
        FileLoader.getInstance(r17.getCurrentAccount()).getPathToAttach(r19[0], true).renameTo(FileLoader.getInstance(r17.getCurrentAccount()).getPathToAttach(r6, true));
        ImageLoader.getInstance().replaceImageInCache(r19[0].volume_id + "_" + r19[0].local_id + "@50_50", r6.location.volume_id + "_" + r6.location.local_id + "@50_50", ImageLocation.getForUserOrChat(r17.getCurrentAccount(), r3, 1), false);
        goto L18
    L7:
        r7 = FileLoader.getClosestVideoSizeWithSize(r4.photo.video_sizes, 1000);
        goto L8
    }

    public static /* synthetic */ void $r8$lambda$otNObLZ6D1u9ArXEpcP1ZYbBJfs(int r0, Runnable r1, INavigationLayout r2, ImageUpdater r3, TLRPC.InputFile r4, TLRPC.InputFile r5, double r6, String r8, TLRPC.PhotoSize r9, TLRPC.PhotoSize r10, boolean r11, TLRPC.VideoSize r12) {
        AndroidUtilities.runOnUIThread(new PhotoUtilities$$ExternalSyntheticLambda4(r4, r5, r6, r12, r0, r10, r9, r1, r2, r3));
    }

    /* renamed from: $r8$lambda$ozcDEvBk9PRJN_Uvr7r6Ef-rkSw, reason: not valid java name */
    public static /* synthetic */ void m5200$r8$lambda$ozcDEvBk9PRJN_Uvr7r6EfrkSw(int r3, INavigationLayout r4) {
        Bundle r0 = new Bundle();
        r0.putLong("user_id", UserConfig.getInstance(r3).clientUserId);
        r4.getLastFragment().presentFragment(new ProfileActivity(r0));
    }

    /* renamed from: $r8$lambda$qLMsmLE_mPCuS_MPL8DHMzZJa-s, reason: not valid java name */
    public static /* synthetic */ void m5201$r8$lambda$qLMsmLE_mPCuS_MPL8DHMzZJas(long r2, ChatActivity r4) {
        Bundle r0 = new Bundle();
        r0.putLong("user_id", r2);
        r4.presentFragment(new ProfileActivity(r0));
    }

    /* renamed from: $r8$lambda$qx2kormntPEvjsmL_m-b7R_n0Dg, reason: not valid java name */
    public static /* synthetic */ void m5202$r8$lambda$qx2kormntPEvjsmL_mb7R_n0Dg(TLObject r10, int r11, TLRPC.PhotoSize r12, TLRPC.PhotoSize r13, Runnable r14, INavigationLayout r15) {
        if ((r10 instanceof TLRPC.TL_photos_photo) == false) goto L23;
        TLRPC.TL_photos_photo r102 = (TLRPC.TL_photos_photo) r10;
        MessagesController.getInstance(r11).putUsers(r102.users, false);
        TLRPC.User r0 = MessagesController.getInstance(r11).getUser(Long.valueOf(UserConfig.getInstance(r11).clientUserId));
        TLRPC.Photo r1 = r102.photo;
        if ((r1 instanceof TLRPC.TL_photo) == false) goto L24;
        if (r0 == null) goto L25;
        TLRPC.PhotoSize r16 = FileLoader.getClosestPhotoSizeWithSize(r1.sizes, 100);
        TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r102.photo.sizes, 1000);
        if (r16 == null) goto L13;
        if (r12 == null) goto L13;
        if (r12.location == null) goto L13;
        FileLoader.getInstance(r11).getPathToAttach(r12.location, true).renameTo(FileLoader.getInstance(r11).getPathToAttach(r16, true));
        ImageLoader.getInstance().replaceImageInCache(r12.location.volume_id + "_" + r12.location.local_id + "@50_50", r16.location.volume_id + "_" + r16.location.local_id + "@50_50", ImageLocation.getForUser(r11, r0, 1), false);
    L13:
        if (r3 == null) goto L18;
        if (r13 == null) goto L18;
        if (r13.location == null) goto L18;
        FileLoader.getInstance(r11).getPathToAttach(r13.location, true).renameTo(FileLoader.getInstance(r11).getPathToAttach(r3, true));
    L18:
        applyPhotoToUser(r102.photo, r0, false);
        UserConfig.getInstance(r11).setCurrentUser(r0);
        UserConfig.getInstance(r11).saveConfig(true);
        if (r14 == null) goto L21;
        r14.run();
    L21:
        BulletinFactory.of(r15.getLastFragment()).createUsersBulletin(Collections.singletonList(r0), AndroidUtilities.replaceTags(LocaleController.getString(R.string.ApplyAvatarHintTitle)), AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.ApplyAvatarHint), new PhotoUtilities$$ExternalSyntheticLambda8(r11, r15)), null).show();
        return;
    L25:
        return;
    L24:
        return;
    }

    public static void applyPhotoToUser(TLRPC.Photo r5, TLRPC.User r6, boolean r7) {
        ArrayList<TLRPC.PhotoSize> r0 = r5.sizes;
        TLRPC.PhotoSize r1 = FileLoader.getClosestPhotoSizeWithSize(r0, 100);
        TLRPC.PhotoSize r02 = FileLoader.getClosestPhotoSizeWithSize(r0, 1000);
        r6.flags |= 32;
        TLRPC.TL_userProfilePhoto r2 = new TLRPC.TL_userProfilePhoto();
        r6.photo = r2;
        r2.personal = r7;
        r2.photo_id = r5.id;
        ArrayList<TLRPC.VideoSize> r52 = r5.video_sizes;
        if (r52 != null) goto L5;
    L7:
        boolean r53 = false;
    L8:
        r2.has_video = r53;
        if (r1 == null) goto L11;
        r6.photo.photo_small = r1.location;
    L11:
        if (r02 == null) goto L14;
        r6.photo.photo_big = r02.location;
        return;
    L14:
        return;
    L5:
        if (r52.size() <= 0) goto L7;
        r53 = true;
        goto L8
    }

    public static void applyPhotoToUser(TLRPC.PhotoSize r1, TLRPC.PhotoSize r2, boolean r3, TLRPC.User r4, boolean r5) {
        r4.flags |= 32;
        TLRPC.TL_userProfilePhoto r0 = new TLRPC.TL_userProfilePhoto();
        r4.photo = r0;
        r0.personal = r5;
        r0.photo_id = 0;
        r0.has_video = r3;
        if (r1 == null) goto L5;
        r0.photo_small = r1.location;
    L5:
        if (r2 == null) goto L8;
        r0.photo_big = r2.location;
        return;
    }

    public static void replacePhotoImagesInCache(int r11, TLRPC.Photo r12, TLRPC.Photo r13) {
        TLRPC.PhotoSize r0 = FileLoader.getClosestPhotoSizeWithSize(r12.sizes, 100);
        TLRPC.PhotoSize r2 = FileLoader.getClosestPhotoSizeWithSize(r12.sizes, 1000);
        TLRPC.PhotoSize r1 = FileLoader.getClosestPhotoSizeWithSize(r13.sizes, 100);
        TLRPC.PhotoSize r132 = FileLoader.getClosestPhotoSizeWithSize(r13.sizes, 1000);
        if (r1 == null) goto L6;
        if (r0 == null) goto L6;
        FileLoader.getInstance(r11).getPathToAttach(r0, true).renameTo(FileLoader.getInstance(r11).getPathToAttach(r1, true));
        ImageLoader.getInstance().replaceImageInCache(r0.location.volume_id + "_" + r0.location.local_id + "@50_50", r1.location.volume_id + "_" + r1.location.local_id + "@50_50", ImageLocation.getForPhoto(r0, r12), false);
    L6:
        if (r132 == null) goto L10;
        if (r2 == null) goto L11;
        FileLoader.getInstance(r11).getPathToAttach(r2, true).renameTo(FileLoader.getInstance(r11).getPathToAttach(r132, true));
        ImageLoader.getInstance().replaceImageInCache(r2.location.volume_id + "_" + r2.location.local_id + "@150_150", r132.location.volume_id + "_" + r132.location.local_id + "@150_150", ImageLocation.getForPhoto(r2, r12), false);
        return;
    L11:
        return;
    }

    public static void setImageAsAvatar(MediaController.PhotoEntry r5, BaseFragment r6, Runnable r7) {
        INavigationLayout r0 = r6.getParentLayout();
        int r1 = r6.getCurrentAccount();
        ImageUpdater r2 = new ImageUpdater(true, 0, true);
        r2.parentFragment = r6;
        r2.processEntry(r5);
        r2.setDelegate(new PhotoUtilities$$ExternalSyntheticLambda1(r1, r7, r0, r2));
    }

    public static void showAvatartConstructorForUpdateUserPhoto(ChatActivity r9, TLRPC.VideoSize r10) {
        ImageUpdater r0 = new ImageUpdater(true, 0, true);
        r0.parentFragment = r9;
        r0.showAvatarConstructor(r10);
        r0.setDelegate(new PhotoUtilities$$ExternalSyntheticLambda0(r9, new TLRPC.FileLocation[1], new TLRPC.FileLocation[1], r9.getUserConfig().getClientUserId()));
    }
}
