package androidx.media3.extractor;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.metadata.id3.CommentFrame;
import androidx.media3.extractor.metadata.id3.InternalFrame;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public final class GaplessInfoHolder {
    private static final Pattern GAPLESS_COMMENT_PATTERN = null;
    public int encoderDelay;
    public int encoderPadding;

    static {
        GAPLESS_COMMENT_PATTERN = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");
    }

    public GaplessInfoHolder() {
        this.encoderDelay = -1;
        this.encoderPadding = -1;
    }

    private boolean setFromComment(String r5) {
        Matcher r52 = GAPLESS_COMMENT_PATTERN.matcher(r5);
        if (r52.find() == true) goto L13;
        return false;
    L13:
        int r1 = Integer.parseInt((String) Util.castNonNull(r52.group(1)), 16);     // Catch: NumberFormatException -> L12
        int r53 = Integer.parseInt((String) Util.castNonNull(r52.group(2)), 16);     // Catch: NumberFormatException -> L12
        if (r1 > 0) goto L8;
        if (r53 > 0) goto L8;
        return false;
    L8:
        this.encoderDelay = r1;     // Catch: NumberFormatException -> L12
        this.encoderPadding = r53;     // Catch: NumberFormatException -> L12
        return true;
    L16:
        return false;
    }

    public boolean hasGaplessInfo() {
        if (this.encoderDelay != (-1)) goto L5;
        return false;
    L5:
        if (this.encoderPadding == (-1)) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean setFromMetadata(Metadata r8) {
        int r1 = 0;
    L4:
        if (r1 >= r8.length()) goto L22;
        Metadata.Entry r2 = r8.get(r1);
        if ((r2 instanceof CommentFrame) == false) goto L13;
        CommentFrame r22 = (CommentFrame) r2;
        if ("iTunSMPB".equals(r22.description) == false) goto L21;
        if (setFromComment(r22.text) == false) goto L21;
        return true;
    L21:
        r1 = r1 + 1;
        goto L4
    L13:
        if ((r2 instanceof InternalFrame) == false) goto L21;
        InternalFrame r23 = (InternalFrame) r2;
        if ("com.apple.iTunes".equals(r23.domain) == false) goto L21;
        if ("iTunSMPB".equals(r23.description) == false) goto L21;
        if (setFromComment(r23.text) == false) goto L21;
        return true;
    L22:
        return false;
    }
}
