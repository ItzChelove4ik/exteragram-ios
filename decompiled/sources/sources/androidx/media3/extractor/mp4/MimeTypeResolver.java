package androidx.media3.extractor.mp4;

import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
abstract class MimeTypeResolver {
    public static String getContainerMimeType(Format r1) {
        String r12 = r1.sampleMimeType;
        if (MimeTypes.isVideo(r12) == false) goto L7;
        return "video/mp4";
    L7:
        if (MimeTypes.isAudio(r12) == false) goto L11;
        return "audio/mp4";
    L11:
        if (MimeTypes.isImage(r12) == true) goto L13;
        return "application/mp4";
    L13:
        if (Objects.equals(r12, "image/heic") == false) goto L17;
        return "image/heif";
    L17:
        if (Objects.equals(r12, "image/avif") == false) goto L21;
        return "image/avif";
    L21:
        return "application/mp4";
    }

    public static String getContainerMimeType(List<TrackSampleTable> r4) {
        Iterator<TrackSampleTable> r42 = r4.iterator();
        boolean r0 = false;
        String r1 = null;
    L4:
        if (r42.hasNext() == false) goto L20;
        String r2 = r42.next().track.format.sampleMimeType;
        if (MimeTypes.isVideo(r2) == true) goto L7;
        if (MimeTypes.isAudio(r2) == true) goto L11;
        if (MimeTypes.isImage(r2) == false) goto L4;
        if (Objects.equals(r2, "image/heic") == true) goto L16;
        if (Objects.equals(r2, "image/avif") == false) goto L4;
        r1 = "image/avif";
        goto L4
    L16:
        r1 = "image/heif";
        goto L4
    L11:
        r0 = true;
        goto L4
    L7:
        return "video/mp4";
    L20:
        if (r0 == false) goto L23;
        return "audio/mp4";
    L23:
        if (r1 == null) goto L25;
        return r1;
    L25:
        return "application/mp4";
    }
}
