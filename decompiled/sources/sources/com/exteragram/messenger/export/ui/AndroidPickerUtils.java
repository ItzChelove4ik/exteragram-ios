package com.exteragram.messenger.export.ui;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import java.io.File;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes4.dex */
public abstract class AndroidPickerUtils {
    public static String getPath(Context r11, Uri r12) {
        Uri r3 = null;
        if (DocumentsContract.isDocumentUri(r11, r12) == false) goto L83;
        boolean r2 = AndroidUtilities.isExternalStorageDocument(r12);
        int r4 = 0;
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r2 == false) goto L84;
        String r122 = DocumentsContract.getDocumentId(r12);
        String[] r0 = r122.split(":");
        String r1 = r0[0];
        if ("primary".equalsIgnoreCase(r1) == false) goto L15;
        if (r0.length <= 1) goto L13;
        return Environment.getExternalStorageDirectory() + "/" + r0[1];
    L13:
        return Environment.getExternalStorageDirectory() + "/";
    L15:
        if ("home".equalsIgnoreCase(r1) == false) goto L22;
        StringBuilder r112 = new StringBuilder();
        r112.append(Environment.getExternalStorageDirectory());
        r112.append("/Documents/");
        if (r0.length <= 1) goto L19;
        r5 = r0[1];
    L19:
        r112.append(r5);
        return r112.toString();
    L22:
        if (new File("storage/" + r122.replace(":", "/")).exists() == true) goto L24;
        String[] r113 = AndroidSDUtils.getStorageDirectories(r11);
        int r22 = r113.length;
    L26:
        if (r4 >= r22) goto L33;
        String r52 = r113[r4];
        if (r0[1].startsWith("/") == false) goto L30;
        r5 = r52 + r0[1];
    L31:
        r4 = r4 + 1;
        goto L26
    L30:
        r5 = r52 + "/" + r0[1];
        goto L31
    L33:
        if (r5.contains(r1) == false) goto L37;
        return "storage/" + r122.replace(":", "/");
    L37:
        if (r5.startsWith("/storage/") == false) goto L39;
    L47:
        return r5;
    L39:
        if (r5.startsWith("storage/") == true) goto L47;
        if (r5.startsWith("/") == false) goto L46;
        return "/storage".concat(r5);
    L46:
        return "/storage/".concat(r5);
    L24:
        return "/storage/" + r122.replace(":", "/");
    L84:
        if (AndroidUtilities.isDownloadsDocument(r12) == false) goto L67;
        String r23 = DocumentsContract.getDocumentId(r12);     // Catch: NumberFormatException -> L62
        if (r23.startsWith("raw:") == false) goto L56;
        r23 = r23.replaceFirst("raw:", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: NumberFormatException -> L62
        if (new File(r23).exists() == false) goto L56;
        return r23;
    L56:
        if (r23.startsWith("raw%3A%2F") == false) goto L60;
        r23 = r23.replaceFirst("raw%3A%2F", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: NumberFormatException -> L62
        if (new File(r23).exists() == false) goto L60;
        return r23;
    L60:
        Long r123 = Long.valueOf(r23);     // Catch: NumberFormatException -> L62
    L63:
        return AndroidUtilities.getDataColumn(r11, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), r123.longValue()), null, null);
    L62:
        r123 = Long.valueOf(ContentUris.parseId(r12));     // Catch: Exception -> L65
        goto L63
    L67:
        if (AndroidUtilities.isMediaDocument(r12) == false) goto L80;
        String[] r124 = DocumentsContract.getDocumentId(r12).split(":");
        String r02 = r124[0];
        if ("image".equals(r02) == false) goto L72;
        r3 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    L78:
        return AndroidUtilities.getDataColumn(r11, r3, "_id=?", new String[]{r124[1]});
    L72:
        if (MediaStreamTrack.VIDEO_TRACK_KIND.equals(r02) == false) goto L75;
        r3 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        goto L78
    L75:
        if (MediaStreamTrack.AUDIO_TRACK_KIND.equals(r02) == false) goto L78;
        r3 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        goto L78
    L80:
        if ("file".equalsIgnoreCase(r12.getScheme()) == false) goto L83;
        return r12.getPath();
    L65:
        return null;
    L83:
        return null;
    }
}
