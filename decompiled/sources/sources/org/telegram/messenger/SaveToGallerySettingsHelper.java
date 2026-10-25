package org.telegram.messenger;

import android.content.SharedPreferences;
import android.util.LongSparseArray;
import org.telegram.messenger.FilePathDatabase;
import org.telegram.messenger.NotificationBadge;

/* loaded from: classes.dex */
public class SaveToGallerySettingsHelper {
    public static String CHANNELS_PREF_NAME = "channels_save_gallery_exceptions";
    public static final long DEFAULT_VIDEO_LIMIT = 104857600;
    public static String GROUPS_PREF_NAME = "groups_save_gallery_exceptions";
    public static final long MAX_VIDEO_LIMIT = 4194304000L;
    public static String USERS_PREF_NAME = "users_save_gallery_exceptions";
    public static SharedSettings channels;
    public static SharedSettings groups;
    public static SharedSettings user;

    public static class DialogException extends Settings {
        public long dialogId;

        public DialogException() {
        }

        @Override // org.telegram.messenger.SaveToGallerySettingsHelper.Settings
        public CharSequence createDescription(int r6) {
            StringBuilder r62 = new StringBuilder();
            if (enabled() == true) goto L5;
            r62.append(LocaleController.getString(R.string.SaveToGalleryOff));
            return r62;
        L5:
            if (this.savePhoto == false) goto L8;
            r62.append(LocaleController.getString(R.string.SaveToGalleryPhotos));
        L8:
            if (this.saveVideo == true) goto L10;
        L19:
            return r62;
        L10:
            if (r62.length() == 0) goto L12;
            r62.append(", ");
        L12:
            long r0 = this.limitVideo;
            if (r0 > 0) goto L15;
        L18:
            r62.append(LocaleController.formatString("SaveToGalleryVideos", R.string.SaveToGalleryVideos, new Object[0]));
            goto L19
        L15:
            if (r0 >= SaveToGallerySettingsHelper.MAX_VIDEO_LIMIT) goto L18;
            r62.append(LocaleController.formatString("SaveToGalleryVideosUpTo", R.string.SaveToGalleryVideosUpTo, new Object[]{AndroidUtilities.formatFileSize(r0, true, false)}));
            return r62;
        }
    }

    public static abstract class Settings {
        public long limitVideo;
        public boolean savePhoto;
        public boolean saveVideo;

        public Settings() {
            this.limitVideo = SaveToGallerySettingsHelper.DEFAULT_VIDEO_LIMIT;
        }

        public abstract CharSequence createDescription(int r1);

        public boolean enabled() {
            if (this.savePhoto == false) goto L5;
            return true;
        L5:
            if (this.saveVideo == true) goto L11;
            return false;
        L11:
            return true;
        }

        public void toggle() {
            if (enabled() == false) goto L6;
            this.saveVideo = false;
            this.savePhoto = false;
            return;
        L6:
            this.savePhoto = true;
            this.saveVideo = true;
        }
    }

    public static class SharedSettings extends Settings {
        private int type;

        /* renamed from: -$$Nest$fputtype, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4876$$Nest$fputtype(SharedSettings r0, int r1) {
            r0.type = r1;
        }

        /* renamed from: -$$Nest$mneedSave, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4877$$Nest$mneedSave(SharedSettings r0, FilePathDatabase.FileMeta r1, MessageObject r2, int r3) {
            return r0.needSave(r1, r2, r3);
        }

        /* renamed from: -$$Nest$msave, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4878$$Nest$msave(SharedSettings r0, String r1, SharedPreferences r2) {
            r0.save(r1, r2);
        }

        /* renamed from: -$$Nest$smread, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SharedSettings m4879$$Nest$smread(String r0, SharedPreferences r1) {
            return read(r0, r1);
        }

        public SharedSettings() {
        }

        private boolean needSave(FilePathDatabase.FileMeta r8, MessageObject r9, int r10) {
            DialogException r102 = UserConfig.getInstance(r10).getSaveGalleryExceptions(this.type).get(r8.dialogId);
            if (r9 == null) goto L10;
            if (r9.isOutOwner() == false) goto L7;
        L8:
            return false;
        L7:
            if (r9.isSecretMedia() == true) goto L8;
        L10:
            if (r9 == null) goto L14;
            if (r9.isVideo() == false) goto L14;
        L15:
            boolean r2 = true;
        L17:
            if (r9 == null) goto L19;
            long r82 = r9.getSize();
        L20:
            boolean r3 = this.saveVideo;
            boolean r4 = this.savePhoto;
            long r5 = this.limitVideo;
            if (r102 == null) goto L23;
            r3 = r102.saveVideo;
            r4 = r102.savePhoto;
            r5 = r102.limitVideo;
        L23:
            if (r2 == false) goto L30;
            if (r3 == true) goto L26;
        L32:
            return false;
        L26:
            if (r5 != (-1)) goto L28;
        L29:
            return true;
        L28:
            if (r82 >= r5) goto L32;
        L30:
            if (r4 == false) goto L32;
            return true;
        L19:
            r82 = r8.messageSize;
        L14:
            if (r8.messageType == 3) goto L15;
            r2 = false;
            goto L17
        }

        private static SharedSettings read(String r4, SharedPreferences r5) {
            SharedSettings r0 = new SharedSettings();
            r0.savePhoto = r5.getBoolean(r4 + "_save_gallery_photo", false);
            r0.saveVideo = r5.getBoolean(r4 + "_save_gallery_video", false);
            r0.limitVideo = r5.getLong(r4 + "_save_gallery_limitVideo", SaveToGallerySettingsHelper.DEFAULT_VIDEO_LIMIT);
            return r0;
        }

        private void save(String r3, SharedPreferences r4) {
            r4.edit().putBoolean(r3 + "_save_gallery_photo", this.savePhoto).putBoolean(r3 + "_save_gallery_video", this.saveVideo).putLong(r3 + "_save_gallery_limitVideo", this.limitVideo).apply();
        }

        @Override // org.telegram.messenger.SaveToGallerySettingsHelper.Settings
        public CharSequence createDescription(int r8) {
            StringBuilder r0 = new StringBuilder();
            if (enabled() == true) goto L5;
            r0.append(LocaleController.getString(R.string.SaveToGalleryOff));
        L18:
            LongSparseArray<DialogException> r7 = UserConfig.getInstance(r8).getSaveGalleryExceptions(this.type);
            if (r7.size() != 0) goto L21;
        L24:
            return r0;
        L21:
            if (r0.length() == 0) goto L23;
            r0.append(", ");
        L23:
            r0.append(LocaleController.formatPluralString("Exception", r7.size(), new Object[]{Integer.valueOf(r7.size())}));
            goto L24
        L5:
            if (this.savePhoto == false) goto L8;
            r0.append(LocaleController.getString(R.string.SaveToGalleryPhotos));
        L8:
            if (this.saveVideo == false) goto L18;
            if (r0.length() == 0) goto L12;
            r0.append(", ");
        L12:
            r0.append(LocaleController.getString(R.string.SaveToGalleryVideos));
            long r3 = this.limitVideo;
            if (r3 <= 0) goto L18;
            if (r3 >= SaveToGallerySettingsHelper.MAX_VIDEO_LIMIT) goto L18;
            r0.append(" (");
            r0.append(AndroidUtilities.formatFileSize(this.limitVideo, true, false));
            r0.append(")");
            goto L18
        }

        @Override // org.telegram.messenger.SaveToGallerySettingsHelper.Settings
        public void toggle() {
            super.toggle();
            SaveToGallerySettingsHelper.saveSettings(this.type);
        }
    }

    static {
    }

    public SaveToGallerySettingsHelper() {
    }

    public static Settings getSettings(int r1) {
        if (r1 != 1) goto L7;
        return user;
    L7:
        if (r1 != 2) goto L11;
        return groups;
    L11:
        if (r1 == 4) goto L13;
        return null;
    L13:
        return channels;
    }

    public static void load(SharedPreferences r11) {
        boolean r1 = false;
        if (r11.getBoolean("save_gallery", false) == true) goto L5;
    L7:
        int r2 = r11.getInt("save_gallery_flags", -1);
    L9:
        if (r2 == (-1)) goto L22;
        r11.edit().remove("save_gallery").remove("save_gallery_flags").apply();
        SharedSettings r0 = new SharedSettings();
        user = r0;
        if ((r2 & 1) == 0) goto L13;
        boolean r3 = true;
    L14:
        r0.saveVideo = r3;
        r0.savePhoto = r3;
        r0.limitVideo = DEFAULT_VIDEO_LIMIT;
        SharedSettings.m4878$$Nest$msave(r0, "user", r11);
        SharedSettings r02 = new SharedSettings();
        groups = r02;
        SharedSettings r8 = user;
        if ((r2 & 2) == 0) goto L17;
        boolean r10 = true;
    L18:
        r8.saveVideo = r10;
        r02.savePhoto = r10;
        r02.limitVideo = DEFAULT_VIDEO_LIMIT;
        SharedSettings.m4878$$Nest$msave(r02, "groups", r11);
        SharedSettings r03 = new SharedSettings();
        channels = r03;
        if ((r2 & 4) == 0) goto L21;
        r1 = true;
    L21:
        r03.saveVideo = r1;
        r03.savePhoto = r1;
        r03.limitVideo = DEFAULT_VIDEO_LIMIT;
        SharedSettings.m4878$$Nest$msave(r03, "channels", r11);
    L23:
        SharedSettings.m4876$$Nest$fputtype(user, 1);
        SharedSettings.m4876$$Nest$fputtype(groups, 2);
        SharedSettings.m4876$$Nest$fputtype(channels, 4);
        return;
    L17:
        r10 = false;
        goto L18
    L13:
        r3 = false;
        goto L14
    L22:
        user = SharedSettings.m4879$$Nest$smread("user", r11);
        groups = SharedSettings.m4879$$Nest$smread("groups", r11);
        channels = SharedSettings.m4879$$Nest$smread("channels", r11);
        goto L23
    L5:
        if (BuildVars.NO_SCOPED_STORAGE == false) goto L7;
        r2 = 7;
        goto L9
    }

    public static LongSparseArray<DialogException> loadExceptions(SharedPreferences r10) {
        LongSparseArray<DialogException> r0 = new LongSparseArray();
        int r1 = r10.getInt(NotificationBadge.NewHtcHomeBadger.COUNT, 0);
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L8;
        DialogException r4 = new DialogException();
        r4.dialogId = r10.getLong(r3 + "_dialog_id", 0);
        r4.savePhoto = r10.getBoolean(r3 + "_photo", false);
        r4.saveVideo = r10.getBoolean(r3 + "_video", false);
        r4.limitVideo = r10.getLong(r3 + "_limitVideo", DEFAULT_VIDEO_LIMIT);
        long r8 = r4.dialogId;
        if (r8 == 0) goto L7;
        r0.put(r8, r4);
    L7:
        r3 = r3 + 1;
        goto L3
    L8:
        return r0;
    }

    public static boolean needSave(int r1, FilePathDatabase.FileMeta r2, MessageObject r3, int r4) {
        if (r1 != 1) goto L6;
        SharedSettings r12 = user;
    L12:
        return SharedSettings.m4877$$Nest$mneedSave(r12, r2, r3, r4);
    L6:
        if (r1 != 4) goto L9;
        r12 = channels;
        goto L12
    L9:
        if (r1 != 2) goto L13;
        r12 = groups;
        goto L12
    L13:
        return false;
    }

    public static void saveExceptions(SharedPreferences r5, LongSparseArray<DialogException> r6) {
        r5.edit().clear().apply();
        SharedPreferences.Editor r52 = r5.edit();
        r52.putInt(NotificationBadge.NewHtcHomeBadger.COUNT, r6.size());
        int r0 = 0;
    L4:
        if (r0 >= r6.size()) goto L6;
        DialogException r1 = r6.valueAt(r0);
        r52.putLong(r0 + "_dialog_id", r1.dialogId);
        r52.putBoolean(r0 + "_photo", r1.savePhoto);
        r52.putBoolean(r0 + "_video", r1.saveVideo);
        r52.putLong(r0 + "_limitVideo", r1.limitVideo);
        r0 = r0 + 1;
        goto L4
    L6:
        r52.apply();
    }

    public static void saveSettings(int r3) {
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
        if (r3 != 1) goto L7;
        SharedSettings.m4878$$Nest$msave(user, "user", r0);
        return;
    L7:
        if (r3 != 2) goto L11;
        SharedSettings.m4878$$Nest$msave(groups, "groups", r0);
        return;
    L11:
        if (r3 != 4) goto L14;
        SharedSettings.m4878$$Nest$msave(channels, "channels", r0);
        return;
    }
}
