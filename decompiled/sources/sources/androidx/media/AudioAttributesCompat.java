package androidx.media;

import android.media.AudioAttributes;
import android.os.Build;
import android.util.SparseIntArray;
import androidx.versionedparcelable.VersionedParcelable;
import kotlin.text.CharsKt__CharKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class AudioAttributesCompat implements VersionedParcelable {
    private static final int[] SDK_USAGES = null;
    private static final SparseIntArray SUPPRESSIBLE_USAGES = null;
    static boolean sForceLegacyBehavior;
    public AudioAttributesImpl mImpl;

    static {
        SparseIntArray r0 = new SparseIntArray();
        SUPPRESSIBLE_USAGES = r0;
        r0.put(5, 1);
        r0.put(6, 2);
        r0.put(7, 2);
        r0.put(8, 1);
        r0.put(9, 1);
        r0.put(10, 1);
        SDK_USAGES = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16};
    }

    public AudioAttributesCompat() {
    }

    public AudioAttributesCompat(AudioAttributesImpl r1) {
        this.mImpl = r1;
    }

    public static int toVolumeStreamType(boolean r3, int r4, int r5) {
        if ((r4 & 1) != 1) goto L9;
        if (r3 == false) goto L6;
        return 1;
    L6:
        return 7;
    L9:
        if ((r4 & 4) != 4) goto L15;
        if (r3 == false) goto L12;
        return 0;
    L12:
        return 6;
    L15:
        switch(r5) {
            case 0: goto L33;
            case 1: goto L33;
            case 2: goto L32;
            case 3: goto L28;
            case 4: goto L27;
            case 5: goto L25;
            case 6: goto L23;
            case 7: goto L25;
            case 8: goto L25;
            case 9: goto L25;
            case 10: goto L25;
            case 11: goto L21;
            case 12: goto L33;
            case 13: goto L20;
            case 14: goto L33;
            case 15: goto L16;
            case 16: goto L33;
            default: goto L16;
        };
    L16:
        if (r3 == true) goto L18;
        return 3;
    L18:
        CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("Unknown usage value ", r5, " in audio attributes");
        return 0;
    L20:
        return 1;
    L21:
        return 10;
    L23:
        return 2;
    L25:
        return 5;
    L27:
        return 4;
    L28:
        if (r3 == false) goto L30;
        return 0;
    L30:
        return 8;
    L32:
        return 0;
    L33:
        return 3;
    }

    public static String usageToString(int r2) {
        switch(r2) {
            case 0: goto L35;
            case 1: goto L33;
            case 2: goto L31;
            case 3: goto L29;
            case 4: goto L27;
            case 5: goto L25;
            case 6: goto L23;
            case 7: goto L21;
            case 8: goto L19;
            case 9: goto L17;
            case 10: goto L15;
            case 11: goto L13;
            case 12: goto L11;
            case 13: goto L9;
            case 14: goto L7;
            case 15: goto L4;
            case 16: goto L5;
            default: goto L4;
        };
    L5:
        return "USAGE_ASSISTANT";
    L7:
        return "USAGE_GAME";
    L9:
        return "USAGE_ASSISTANCE_SONIFICATION";
    L11:
        return "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
    L13:
        return "USAGE_ASSISTANCE_ACCESSIBILITY";
    L15:
        return "USAGE_NOTIFICATION_EVENT";
    L17:
        return "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
    L19:
        return "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
    L21:
        return "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
    L23:
        return "USAGE_NOTIFICATION_RINGTONE";
    L25:
        return "USAGE_NOTIFICATION";
    L27:
        return "USAGE_ALARM";
    L29:
        return "USAGE_VOICE_COMMUNICATION_SIGNALLING";
    L31:
        return "USAGE_VOICE_COMMUNICATION";
    L33:
        return "USAGE_MEDIA";
    L35:
        return "USAGE_UNKNOWN";
    L4:
        return "unknown usage " + r2;
    }

    public static AudioAttributesCompat wrap(Object r2) {
        if (sForceLegacyBehavior == false) goto L7;
        return null;
    L7:
        if (Build.VERSION.SDK_INT < 26) goto L11;
        return new AudioAttributesCompat(new AudioAttributesImplApi26((AudioAttributes) r2));
    L11:
        return new AudioAttributesCompat(new AudioAttributesImplApi21((AudioAttributes) r2));
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof AudioAttributesCompat) == true) goto L5;
        return false;
    L5:
        AudioAttributesImpl r2 = this.mImpl;
        AudioAttributesImpl r32 = ((AudioAttributesCompat) r3).mImpl;
        if (r2 != null) goto L12;
        if (r32 != null) goto L10;
        return true;
    L10:
        return false;
    L12:
        return r2.equals(r32);
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public String toString() {
        return this.mImpl.toString();
    }
}
