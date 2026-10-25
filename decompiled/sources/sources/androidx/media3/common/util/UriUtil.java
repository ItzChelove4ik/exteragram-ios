package androidx.media3.common.util;

import android.net.Uri;
import android.text.TextUtils;
import com.google.common.base.Ascii;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public abstract class UriUtil {
    public static String getRelativePath(Uri r5, Uri r6) {
        if (r5.isOpaque() == true) goto L38;
        if (r6.isOpaque() == true) goto L38;
        String r0 = r5.getScheme();
        String r1 = r6.getScheme();
        if (r0 != null) goto L11;
        if (r1 == null) goto L15;
    L36:
        return r6.toString();
    L15:
        if (Objects.equals(r5.getAuthority(), r6.getAuthority()) == false) goto L36;
        List<String> r52 = r5.getPathSegments();
        List<String> r62 = r6.getPathSegments();
        int r02 = Math.min(r52.size(), r62.size());
        int r12 = 0;
        int r2 = 0;
    L18:
        if (r12 >= r02) goto L23;
        if (r52.get(r12).equals(r62.get(r12)) == false) goto L23;
        r2 = r2 + 1;
        r12 = r12 + 1;
    L23:
        StringBuilder r03 = new StringBuilder();
        int r13 = r2;
    L25:
        if (r13 >= r52.size()) goto L28;
        r03.append("../");
        r13 = r13 + 1;
    L28:
        if (r2 >= r62.size()) goto L34;
        r03.append(r62.get(r2));
        if (r2 >= (r62.size() - 1)) goto L32;
        r03.append("/");
    L32:
        r2 = r2 + 1;
        goto L28
    L34:
        return r03.toString();
    L11:
        if (r1 == null) goto L36;
        if (Ascii.equalsIgnoreCase(r0, r1) == false) goto L36;
    L38:
        return r6.toString();
    }

    private static int[] getUriIndices(String r9) {
        int[] r0 = new int[4];
        if (TextUtils.isEmpty(r9) == false) goto L6;
        r0[0] = -1;
        return r0;
    L6:
        int r1 = r9.length();
        int r4 = r9.indexOf(35);
        if (r4 == (-1)) goto L10;
        r1 = r4;
    L10:
        int r42 = r9.indexOf(63);
        if (r42 == (-1)) goto L13;
        if (r42 > r1) goto L13;
    L14:
        int r6 = r9.indexOf(47);
        if (r6 == (-1)) goto L17;
        if (r6 > r42) goto L17;
    L18:
        int r7 = r9.indexOf(58);
        if (r7 <= r6) goto L21;
        r7 = -1;
    L21:
        int r62 = r7 + 2;
        if (r62 < r42) goto L24;
    L31:
        int r92 = r7 + 1;
    L32:
        r0[0] = r7;
        r0[1] = r92;
        r0[2] = r42;
        r0[3] = r1;
        return r0;
    L24:
        if (r9.charAt(r7 + 1) != '/') goto L31;
        if (r9.charAt(r62) != '/') goto L31;
        r92 = r9.indexOf(47, r7 + 3);
        if (r92 == (-1)) goto L30;
        if (r92 <= r42) goto L32;
    L30:
        r92 = r42;
    L17:
        r6 = r42;
    L13:
        r42 = r1;
        goto L14
    }

    public static boolean isAbsolute(String r2) {
        if (r2 != null) goto L5;
    L8:
        return false;
    L5:
        if (getUriIndices(r2)[0] == (-1)) goto L8;
        return true;
    }

    private static String removeDotSegments(StringBuilder r7, int r8, int r9) {
        if (r8 < r9) goto L6;
        return r7.toString();
    L6:
        if (r7.charAt(r8) != '/') goto L8;
        r8 = r8 + 1;
    L8:
        int r0 = r8;
        int r2 = r0;
    L9:
        if (r0 > r9) goto L36;
        if (r0 == r9) goto L11;
        if (r7.charAt(r0) == '/') goto L14;
        r0 = r0 + 1;
        goto L9
    L14:
        int r3 = r0 + 1;
    L15:
        int r4 = r2 + 1;
        if (r0 != r4) goto L21;
        if (r7.charAt(r2) != '.') goto L21;
        r7.delete(r2, r3);
        r9 = r9 - (r3 - r2);
    L33:
        r0 = r2;
    L21:
        if (r0 == (r2 + 2)) goto L23;
    L32:
        int r02 = r0 + 1;
    L31:
        r2 = r02;
        goto L33
    L23:
        if (r7.charAt(r2) != '.') goto L32;
        if (r7.charAt(r4) != '.') goto L32;
        r02 = r7.lastIndexOf("/", r2 - 2) + 1;
        if (r02 <= r8) goto L29;
        int r22 = r02;
    L30:
        r7.delete(r22, r3);
        r9 = r9 - (r3 - r22);
        goto L31
    L29:
        r22 = r8;
        goto L30
    L11:
        r3 = r0;
        goto L15
    L36:
        return r7.toString();
    }

    public static Uri removeQueryParameter(Uri r5, String r6) {
        Uri.Builder r0 = r5.buildUpon();
        r0.clearQuery();
        Iterator<String> r1 = r5.getQueryParameterNames().iterator();
    L4:
        if (r1.hasNext() == false) goto L12;
        String r2 = r1.next();
        if (r2.equals(r6) == true) goto L4;
        Iterator<String> r3 = r5.getQueryParameters(r2).iterator();
    L9:
        if (r3.hasNext() == false) goto L4;
        r0.appendQueryParameter(r2, r3.next());
        goto L9
    L12:
        return r0.build();
    }

    public static String resolve(String r10, String r11) {
        StringBuilder r0 = new StringBuilder();
        if (r10 != null) goto L5;
        r10 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        if (r11 != null) goto L7;
        r11 = _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        int[] r1 = getUriIndices(r11);
        if (r1[0] == (-1)) goto L11;
        r0.append(r11);
        removeDotSegments(r0, r1[1], r1[2]);
        return r0.toString();
    L11:
        int[] r3 = getUriIndices(r10);
        if (r1[3] != 0) goto L16;
        r0.append(r10, 0, r3[3]);
        r0.append(r11);
        return r0.toString();
    L16:
        if (r1[2] != 0) goto L19;
        r0.append(r10, 0, r3[2]);
        r0.append(r11);
        return r0.toString();
    L19:
        int r7 = r1[1];
        if (r7 == 0) goto L24;
        int r32 = r3[0] + 1;
        r0.append(r10, 0, r32);
        r0.append(r11);
        return removeDotSegments(r0, r1[1] + r32, r32 + r1[2]);
    L24:
        if (r11.charAt(r7) != '/') goto L27;
        r0.append(r10, 0, r3[1]);
        r0.append(r11);
        int r102 = r3[1];
        return removeDotSegments(r0, r102, r1[2] + r102);
    L27:
        int r72 = r3[0] + 2;
        int r9 = r3[1];
        if (r72 < r9) goto L30;
    L33:
        int r73 = r10.lastIndexOf(47, r3[2] - 1);
        if (r73 != (-1)) goto L36;
        int r4 = r3[1];
    L37:
        r0.append(r10, 0, r4);
        r0.append(r11);
        return removeDotSegments(r0, r3[1], r4 + r1[2]);
    L36:
        r4 = r73 + 1;
        goto L37
    L30:
        if (r9 != r3[2]) goto L33;
        r0.append(r10, 0, r9);
        r0.append('/');
        r0.append(r11);
        int r103 = r3[1];
        return removeDotSegments(r0, r103, (r1[2] + r103) + 1);
    }

    public static Uri resolveToUri(String r0, String r1) {
        return Uri.parse(resolve(r0, r1));
    }
}
