package com.exteragram.messenger.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.TextView;
import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord0;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import com.google.android.gms.cast.MediaError;
import java.io.File;
import java.util.Locale;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.VideoPlayer;

/* loaded from: classes4.dex */
public abstract class VideoSubtitlesHelper {

    public enum LoadError extends Enum<LoadError> {
        private static final /* synthetic */ LoadError[] $VALUES = null;
        public static final LoadError LOAD_FAILED = null;
        public static final LoadError NONE = null;
        public static final LoadError UNSUPPORTED_FORMAT = null;

        private static /* synthetic */ LoadError[] $values() {
            return new LoadError[]{NONE, UNSUPPORTED_FORMAT, LOAD_FAILED};
        }

        static {
            NONE = new LoadError("NONE", 0);
            UNSUPPORTED_FORMAT = new LoadError("UNSUPPORTED_FORMAT", 1);
            LOAD_FAILED = new LoadError(MediaError.ERROR_TYPE_LOAD_FAILED, 2);
            $VALUES = $values();
        }

        LoadError(String r1, int r2) {
        }

        public static LoadError valueOf(String r1) {
            return (LoadError) Enum.valueOf(LoadError.class, r1);
        }

        public static LoadError[] values() {
            return (LoadError[]) $VALUES.clone();
        }
    }

    public static final class SubtitleLoadResult extends RecordTag {
        private final LoadError error;
        private final SubtitleState subtitleState;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof SubtitleLoadResult) == false) goto L10;
            SubtitleLoadResult r32 = (SubtitleLoadResult) r3;
            if (Objects.equals(this.subtitleState, r32.subtitleState) == true) goto L7;
            return false;
        L7:
            if (Objects.equals(this.error, r32.error) == false) goto L13;
            return true;
        L13:
            return false;
        L10:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.subtitleState, this.error};
        }

        public SubtitleLoadResult(SubtitleState r1, LoadError r2) {
            this.subtitleState = r1;
            this.error = r2;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public LoadError error() {
            return this.error;
        }

        public final int hashCode() {
            return Client$ImagePayload$$ExternalSyntheticRecord0.m(this.subtitleState, this.error);
        }

        public boolean isSuccess() {
            if (this.subtitleState == null) goto L6;
            return true;
        L6:
            return false;
        }

        public SubtitleState subtitleState() {
            return this.subtitleState;
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), SubtitleLoadResult.class, "subtitleState;error");
        }
    }

    public static final class SubtitleState extends RecordTag {
        private final String label;
        private final String mimeType;
        private final String path;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof SubtitleState) == false) goto L12;
            SubtitleState r32 = (SubtitleState) r3;
            if (Objects.equals(this.path, r32.path) == true) goto L7;
            return false;
        L7:
            if (Objects.equals(this.mimeType, r32.mimeType) == true) goto L9;
            return false;
        L9:
            if (Objects.equals(this.label, r32.label) == false) goto L16;
            return true;
        L16:
            return false;
        L12:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.path, this.mimeType, this.label};
        }

        /* renamed from: -$$Nest$fgetlabel, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m1767$$Nest$fgetlabel(SubtitleState r0) {
            return r0.label;
        }

        /* renamed from: -$$Nest$fgetmimeType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m1768$$Nest$fgetmimeType(SubtitleState r0) {
            return r0.mimeType;
        }

        /* renamed from: -$$Nest$fgetpath, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m1769$$Nest$fgetpath(SubtitleState r0) {
            return r0.path;
        }

        public SubtitleState(String r1, String r2, String r3) {
            this.path = r1;
            this.mimeType = r2;
            this.label = r3;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public String getDisplayName() {
            if (TextUtils.isEmpty(this.label) == true) goto L7;
            return this.label;
        L7:
            return new File(this.path).getName();
        }

        public final int hashCode() {
            return VideoSubtitlesHelper$SubtitleState$$ExternalSyntheticRecord0.m(this.path, this.mimeType, this.label);
        }

        public boolean isValid() {
            if (TextUtils.isEmpty(this.path) == false) goto L5;
        L13:
            return false;
        L5:
            if (TextUtils.isEmpty(this.mimeType) == true) goto L13;
            File r0 = new File(this.path);
            if (r0.exists() == false) goto L13;
            if (r0.length() <= 0) goto L13;
            return true;
        }

        public String mimeType() {
            return this.mimeType;
        }

        public String path() {
            return this.path;
        }

        public VideoPlayer.ExternalSubtitle toExternalSubtitle() {
            return new VideoPlayer.ExternalSubtitle(Uri.fromFile(new File(this.path)), this.mimeType, getDisplayName());
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), SubtitleState.class, "path;mimeType;label");
        }
    }

    public static boolean areSame(SubtitleState r4, SubtitleState r5) {
        if (r4 != r5) goto L6;
        return true;
    L6:
        if (r4 == null) goto L16;
        if (r5 == null) goto L16;
        if (TextUtils.equals(SubtitleState.m1769$$Nest$fgetpath(r4), SubtitleState.m1769$$Nest$fgetpath(r5)) == false) goto L16;
        if (TextUtils.equals(SubtitleState.m1768$$Nest$fgetmimeType(r4), SubtitleState.m1768$$Nest$fgetmimeType(r5)) == false) goto L16;
        if (TextUtils.equals(SubtitleState.m1767$$Nest$fgetlabel(r4), SubtitleState.m1767$$Nest$fgetlabel(r5)) == false) goto L16;
        return true;
    L16:
        return false;
    }

    public static String buildVideoKey(MessageObject r1, String r2, Uri r3, TL_iv.PageBlock r4) {
        if (r1 == null) goto L8;
        if (r1.getDocument() == null) goto L8;
        return "doc_" + r1.getDocument().id;
    L8:
        if (TextUtils.isEmpty(r2) == false) goto L10;
        if (r3 == null) goto L15;
        return makeUriKey(r3);
    L15:
        if ((r4 instanceof TL_iv.pageBlockVideo) == true) goto L17;
        return null;
    L17:
        return "page_video_" + ((TL_iv.pageBlockVideo) r4).video_id;
    L10:
        return makePathKey(r2);
    }

    public static void clear(String r3) {
        if (TextUtils.isEmpty(r3) == false) goto L5;
        return;
    L5:
        getPreferences().edit().remove("path_" + r3).remove("mime_" + r3).remove("label_" + r3).apply();
    }

    public static Intent createPickerIntent() {
        Intent r0 = new Intent("android.intent.action.GET_CONTENT");
        r0.addCategory("android.intent.category.OPENABLE");
        r0.setType("*/*");
        r0.putExtra("android.intent.extra.MIME_TYPES", new String[]{"application/x-subrip", "text/vtt"});
        return r0;
    }

    public static TextView createSubtitlesView(Context r5) {
        TextView r0 = new TextView(r5);
        r0.setGravity(17);
        r0.setTextColor(-1);
        if (AndroidUtilities.isTablet() == false) goto L5;
        float r52 = 20.0f;
    L6:
        r0.setTextSize(1, r52);
        r0.setMaxLines(3);
        r0.setPadding(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(8.0f));
        r0.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(8.0f), -872415232));
        r0.setShadowLayer(AndroidUtilities.dp(2.0f), 0.0f, AndroidUtilities.dp(1.0f), -1728053248);
        r0.setVisibility(8);
        return r0;
    L5:
        r52 = 16.0f;
        goto L6
    }

    private static SharedPreferences getPreferences() {
        return ApplicationLoader.applicationContext.getSharedPreferences("video_external_subtitles", 0);
    }

    private static String getSubtitleExtension(Uri r2) {
        String r0 = AndroidUtilities.getPath(r2);
        if (TextUtils.isEmpty(r0) == false) goto L6;
        r0 = r2.getPath();
    L6:
        if (TextUtils.isEmpty(r0) == false) goto L9;
        r0 = r2.toString();
    L9:
        if (TextUtils.isEmpty(r0) == false) goto L11;
        return null;
    L11:
        String r22 = r0.toLowerCase(Locale.US);
        if (r22.endsWith(".srt") == false) goto L16;
        return "srt";
    L16:
        if (r22.endsWith(".vtt") == false) goto L19;
        return "vtt";
    L19:
        return null;
    }

    public static SubtitleLoadResult loadFromPickerIntent(Intent r0) {
        if (r0 == null) goto L4;
        Uri r02 = r0.getData();
    L6:
        return loadFromUri(r02);
    L4:
        r02 = null;
        goto L6
    }

    public static SubtitleLoadResult loadFromUri(Uri r7) {
        if (r7 == null) goto L5;
        String r1 = resolveMimeType(r7);
        if (TextUtils.isEmpty(r1) == false) goto L11;
        return new SubtitleLoadResult(null, LoadError.UNSUPPORTED_FORMAT);
    L11:
        if ("text/vtt".equals(r1) == false) goto L13;
        String r2 = "vtt";
    L40:
    L17:
        String r3 = null;
    L25:
        String r72 = r3;
    L27:
        if (TextUtils.isEmpty(r72) == true) goto L29;
        File r22 = new File(r72);
        if (r22.exists() == false) goto L38;
        if (r22.length() <= 0) goto L38;
        return new SubtitleLoadResult(new SubtitleState(r72, r1, r22.getName()), LoadError.NONE);
    L38:
        return new SubtitleLoadResult(null, LoadError.LOAD_FAILED);
    L29:
        return new SubtitleLoadResult(null, LoadError.LOAD_FAILED);
    L15:
        if ("file".equalsIgnoreCase(r7.getScheme()) == false) goto L18;
        r72 = r7.getPath();     // Catch: Exception -> L17
        goto L27
    L18:
        r3 = AndroidUtilities.getPath(r7);     // Catch: Exception -> L17
        if (TextUtils.isEmpty(r3) == false) goto L22;
    L23:
        r72 = MediaController.copyFileToCache(r7, r2);     // Catch: Exception -> L39
    L22:
        if (r3.startsWith("content://") == false) goto L25;
    L13:
        r2 = "srt";
        goto L40
    L5:
        return new SubtitleLoadResult(null, LoadError.LOAD_FAILED);
    }

    public static String makePathKey(String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L7;
        return null;
    L7:
        return "uri_" + Utilities.MD5(r2);
    }

    public static String makeUriKey(Uri r0) {
        if (r0 != null) goto L6;
        return null;
    L6:
        return makePathKey(r0.toString());
    }

    private static String resolveMimeType(Uri r7) {
        String r1 = ApplicationLoader.applicationContext.getContentResolver().getType(r7);     // Catch: Exception -> L5
    L7:
        if (TextUtils.isEmpty(r1) == true) goto L21;
        String r12 = r1.toLowerCase(Locale.US);
        if ("text/vtt".equals(r12) == false) goto L11;
    L20:
        return "text/vtt";
    L11:
        if (r12.contains("vtt") == true) goto L20;
        if ("application/x-subrip".equals(r12) == false) goto L16;
    L19:
        return "application/x-subrip";
    L16:
        if (r12.contains("subrip") == true) goto L19;
        if (r12.contains("srt") == true) goto L19;
    L21:
        String r72 = getSubtitleExtension(r7);
        if ("vtt".equals(r72) == false) goto L25;
        return "text/vtt";
    L25:
        if ("srt".equals(r72) == false) goto L27;
        return "application/x-subrip";
    L27:
        return null;
    L5:
        r1 = null;
        goto L7
    }

    public static SubtitleState restore(String r6) {
        if (TextUtils.isEmpty(r6) == false) goto L5;
        return null;
    L5:
        SharedPreferences r0 = getPreferences();
        String r2 = r0.getString("path_" + r6, null);
        String r3 = r0.getString("mime_" + r6, null);
        String r02 = r0.getString("label_" + r6, null);
        if (TextUtils.isEmpty(r2) == false) goto L8;
    L15:
        return null;
    L8:
        if (TextUtils.isEmpty(r3) == true) goto L15;
        SubtitleState r4 = new SubtitleState(r2, r3, r02);
        if (r4.isValid() == true) goto L14;
        clear(r6);
        return null;
    L14:
        return r4;
    }

    public static void save(String r3, SubtitleState r4) {
        if (TextUtils.isEmpty(r3) == true) goto L10;
        if (r4 != null) goto L6;
        return;
    L6:
        if (r4.isValid() == false) goto L12;
        getPreferences().edit().putString("path_" + r3, r4.path()).putString("mime_" + r3, r4.mimeType()).putString("label_" + r3, r4.getDisplayName()).apply();
        return;
    L12:
        return;
    }
}
