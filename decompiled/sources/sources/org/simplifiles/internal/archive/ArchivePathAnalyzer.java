package org.simplifiles.internal.archive;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0002J \u0010\r\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0002J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\u000f"}, d2 = {"Lorg/simplifiles/internal/archive/ArchivePathAnalyzer;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "analyze", "Lorg/simplifiles/internal/archive/ArchivePathAnalysis;", "path", _UrlKt.FRAGMENT_ENCODE_SET, "isCurrentDirectorySegment", _UrlKt.FRAGMENT_ENCODE_SET, "start", _UrlKt.FRAGMENT_ENCODE_SET, "length", "isParentDirectorySegment", "isWindowsDriveAbsolute", "simplifiles"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ArchivePathAnalyzer {
    public static final ArchivePathAnalyzer INSTANCE = null;

    static {
        INSTANCE = new ArchivePathAnalyzer();
    }

    private ArchivePathAnalyzer() {
    }

    private final boolean isCurrentDirectorySegment(String r1, int r2, int r3) {
        if (r3 == 1) goto L5;
        return false;
    L5:
        if (r1.charAt(r2) != '.') goto L9;
        return true;
    L9:
        return false;
    }

    private final boolean isParentDirectorySegment(String r1, int r2, int r3) {
        if (r3 == 2) goto L5;
        return false;
    L5:
        if (r1.charAt(r2) == '.') goto L7;
        return false;
    L7:
        if (r1.charAt(r2 + 1) != '.') goto L12;
        return true;
    L12:
        return false;
    }

    private final boolean isWindowsDriveAbsolute(String r5) {
        if (r5.length() >= 2) goto L5;
    L15:
        return false;
    L5:
        if (r5.charAt(1) != ':') goto L15;
        if (Character.isLetter(r5.charAt(0)) == false) goto L15;
        if (r5.length() != 2) goto L11;
    L14:
        return true;
    L11:
        if (r5.charAt(2) == '/') goto L14;
        if (r5.charAt(2) != '\\') goto L15;
        goto L14
    }

    public final ArchivePathAnalysis analyze(String r14) {
        boolean r1 = false;
        String r3 = null;
        if (StringsKt.startsWith$default(r14, "/", false, 2, null) == false) goto L5;
    L10:
        boolean r0 = true;
    L12:
        if (r14.length() == 0) goto L14;
        StringBuilder r2 = new StringBuilder(r14.length());
        int r5 = r14.length();
        if (r5 < 0) goto L41;
        int r6 = 0;
        int r7 = 0;
        boolean r8 = false;
        boolean r9 = false;
    L19:
        if (r6 < r14.length()) goto L21;
    L25:
        int r10 = r6 - r7;
        if (r10 > 0) goto L28;
    L38:
        r7 = r6 + 1;
    L39:
        if (r6 == r5) goto L42;
        r6 = r6 + 1;
    L42:
        if (r8 == true) goto L48;
        if (r2.length() == 0) goto L48;
        r3 = r2.toString();
    L48:
        if (r8 == true) goto L52;
        if (r9 == true) goto L52;
        r1 = true;
    L52:
        return new ArchivePathAnalysis(r3, r1, r0, r8);
    L28:
        if (isCurrentDirectorySegment(r14, r7, r10) == true) goto L38;
        if (isParentDirectorySegment(r14, r7, r10) == false) goto L32;
        r8 = true;
        r9 = true;
        goto L38
    L32:
        if (r8 == false) goto L34;
    L37:
        r9 = true;
        goto L38
    L34:
        if (r2.length() <= 0) goto L36;
        r2.append('/');
    L36:
        r2.append(r14, r7, r6);
        goto L37
    L21:
        if (r14.charAt(r6) == '/') goto L25;
        if (r14.charAt(r6) == '\\') goto L25;
    L41:
        r8 = false;
        r9 = false;
        goto L42
    L14:
        return new ArchivePathAnalysis(null, true, r0, false);
    L5:
        if (StringsKt.startsWith$default(r14, "\\", false, 2, null) == true) goto L10;
        if (isWindowsDriveAbsolute(r14) == true) goto L10;
        r0 = false;
        goto L12
    }
}
