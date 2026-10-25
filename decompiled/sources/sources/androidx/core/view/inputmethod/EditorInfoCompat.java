package androidx.core.view.inputmethod;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import androidx.core.util.Preconditions;

@SuppressLint({"PrivateConstructorForUtilityClass"})
/* loaded from: classes3.dex */
public abstract class EditorInfoCompat {
    private static final String[] EMPTY_STRING_ARRAY = null;

    public static class Api30Impl {
        public static void setInitialSurroundingSubText(EditorInfo r0, CharSequence r1, int r2) {
            r0.setInitialSurroundingSubText(r1, r2);
        }
    }

    static {
        EMPTY_STRING_ARRAY = new String[0];
    }

    public static String[] getContentMimeTypes(EditorInfo r2) {
        if (Build.VERSION.SDK_INT < 25) goto L9;
        String[] r22 = EditorInfoCompat$$ExternalSyntheticApiModelOutline1.m(r2);
        if (r22 == null) goto L8;
        return r22;
    L8:
        return EMPTY_STRING_ARRAY;
    L9:
        Bundle r0 = r2.extras;
        if (r0 == null) goto L12;
        String[] r02 = r0.getStringArray("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES");
        if (r02 != null) goto L16;
        r02 = r2.extras.getStringArray("android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES");
    L16:
        if (r02 == null) goto L19;
        return r02;
    L19:
        return EMPTY_STRING_ARRAY;
    L12:
        return EMPTY_STRING_ARRAY;
    }

    private static boolean isCutOnSurrogate(CharSequence r1, int r2, int r3) {
        if (r3 == 0) goto L10;
        if (r3 == 1) goto L8;
        return false;
    L8:
        return Character.isHighSurrogate(r1.charAt(r2));
    L10:
        return Character.isLowSurrogate(r1.charAt(r2));
    }

    private static boolean isPasswordInputType(int r1) {
        int r12 = r1 & 4095;
        if (r12 != 129) goto L5;
        return true;
    L5:
        if (r12 != 225) goto L7;
        return true;
    L7:
        if (r12 == 18) goto L14;
        return false;
    L14:
        return true;
    }

    public static void setContentMimeTypes(EditorInfo r2, String[] r3) {
        if (Build.VERSION.SDK_INT < 25) goto L7;
        EditorInfoCompat$$ExternalSyntheticApiModelOutline0.m(r2, r3);
        return;
    L7:
        if (r2.extras != null) goto L9;
        r2.extras = new Bundle();
    L9:
        r2.extras.putStringArray("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES", r3);
        r2.extras.putStringArray("android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES", r3);
    }

    public static void setInitialSurroundingSubText(EditorInfo r5, CharSequence r6, int r7) {
        Preconditions.checkNotNull(r6);
        if (Build.VERSION.SDK_INT < 30) goto L6;
        Api30Impl.setInitialSurroundingSubText(r5, r6, r7);
        return;
    L6:
        int r0 = r5.initialSelStart;
        int r1 = r5.initialSelEnd;
        if (r0 <= r1) goto L9;
        int r2 = r1 - r7;
    L10:
        if (r0 <= r1) goto L12;
        int r02 = r0 - r7;
    L13:
        int r12 = r6.length();
        if (r7 < 0) goto L28;
        if (r2 < 0) goto L28;
        if (r02 > r12) goto L28;
        if (isPasswordInputType(r5.inputType) == false) goto L23;
        setSurroundingText(r5, null, 0, 0);
        return;
    L23:
        if (r12 > 2048) goto L26;
        setSurroundingText(r5, r6, r2, r02);
        return;
    L26:
        trimLongSurroundingText(r5, r6, r2, r02);
        return;
    L28:
        setSurroundingText(r5, null, 0, 0);
        return;
    L12:
        r02 = r1 - r7;
        goto L13
    L9:
        r2 = r0 - r7;
        goto L10
    }

    public static void setInitialSurroundingText(EditorInfo r3, CharSequence r4) {
        if (Build.VERSION.SDK_INT < 30) goto L6;
        Api30Impl.setInitialSurroundingSubText(r3, r4, 0);
        return;
    L6:
        setInitialSurroundingSubText(r3, r4, 0);
    }

    private static void setSurroundingText(EditorInfo r2, CharSequence r3, int r4, int r5) {
        if (r2.extras != null) goto L5;
        r2.extras = new Bundle();
    L5:
        if (r3 == null) goto L7;
        SpannableStringBuilder r0 = new SpannableStringBuilder(r3);
    L8:
        r2.extras.putCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT", r0);
        r2.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD", r4);
        r2.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END", r5);
        return;
    L7:
        r0 = null;
        goto L8
    }

    private static void trimLongSurroundingText(EditorInfo r9, CharSequence r10, int r11, int r12) {
        int r0 = r12 - r11;
        if (r0 <= 1024) goto L5;
        int r1 = 0;
    L6:
        int r4 = 2048 - r1;
        int r3 = Math.min(r10.length() - r12, r4 - Math.min(r11, (int) (r4 * 0.8d)));
        int r42 = Math.min(r11, r4 - r3);
        int r112 = r11 - r42;
        if (isCutOnSurrogate(r10, r112, 0) == false) goto L10;
        r112 = r112 + 1;
        r42 = r42 - 1;
    L10:
        if (isCutOnSurrogate(r10, (r12 + r3) - 1, 1) == false) goto L12;
        r3 = r3 - 1;
    L12:
        int r5 = (r42 + r1) + r3;
        if (r1 == r0) goto L15;
        CharSequence r102 = TextUtils.concat(new CharSequence[]{r10.subSequence(r112, r112 + r42), r10.subSequence(r12, r3 + r12)});
    L16:
        setSurroundingText(r9, r102, r42, r1 + r42);
        return;
    L15:
        r102 = r10.subSequence(r112, r5 + r112);
        goto L16
    L5:
        r1 = r0;
        goto L6
    }
}
