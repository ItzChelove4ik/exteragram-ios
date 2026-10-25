package androidx.camera.camera2.pipe;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087@\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u0005J\u001a\u0010\u0010\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/pipe/AudioRestrictionMode;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(ILjava/lang/Object;)Z", "equals", "I", "getValue", "()I", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class AudioRestrictionMode {
    private static final int AUDIO_RESTRICTION_NONE = 0;
    private static final int AUDIO_RESTRICTION_VIBRATION = 0;
    private static final int AUDIO_RESTRICTION_VIBRATION_SOUND = 0;
    public static final Companion Companion = null;
    private final int value;

    @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/camera/camera2/pipe/AudioRestrictionMode$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "AUDIO_RESTRICTION_NONE", "Landroidx/camera/camera2/pipe/AudioRestrictionMode;", "getAUDIO_RESTRICTION_NONE-_b5Q8KE", "()I", "I", "AUDIO_RESTRICTION_VIBRATION", "getAUDIO_RESTRICTION_VIBRATION-_b5Q8KE", "AUDIO_RESTRICTION_VIBRATION_SOUND", "getAUDIO_RESTRICTION_VIBRATION_SOUND-_b5Q8KE", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: getAUDIO_RESTRICTION_NONE-_b5Q8KE, reason: not valid java name */
        public final int m137getAUDIO_RESTRICTION_NONE_b5Q8KE() {
            return AudioRestrictionMode.access$getAUDIO_RESTRICTION_NONE$cp();
        }

        /* renamed from: getAUDIO_RESTRICTION_VIBRATION-_b5Q8KE, reason: not valid java name */
        public final int m138getAUDIO_RESTRICTION_VIBRATION_b5Q8KE() {
            return AudioRestrictionMode.access$getAUDIO_RESTRICTION_VIBRATION$cp();
        }

        /* renamed from: getAUDIO_RESTRICTION_VIBRATION_SOUND-_b5Q8KE, reason: not valid java name */
        public final int m139getAUDIO_RESTRICTION_VIBRATION_SOUND_b5Q8KE() {
            return AudioRestrictionMode.access$getAUDIO_RESTRICTION_VIBRATION_SOUND$cp();
        }
    }

    static {
        Companion = new Companion(null);
        AUDIO_RESTRICTION_NONE = m131constructorimpl(0);
        AUDIO_RESTRICTION_VIBRATION = m131constructorimpl(1);
        AUDIO_RESTRICTION_VIBRATION_SOUND = m131constructorimpl(3);
    }

    private /* synthetic */ AudioRestrictionMode(int r1) {
        this.value = r1;
    }

    public static final /* synthetic */ int access$getAUDIO_RESTRICTION_NONE$cp() {
        return AUDIO_RESTRICTION_NONE;
    }

    public static final /* synthetic */ int access$getAUDIO_RESTRICTION_VIBRATION$cp() {
        return AUDIO_RESTRICTION_VIBRATION;
    }

    public static final /* synthetic */ int access$getAUDIO_RESTRICTION_VIBRATION_SOUND$cp() {
        return AUDIO_RESTRICTION_VIBRATION_SOUND;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ AudioRestrictionMode m130boximpl(int r1) {
        return new AudioRestrictionMode(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m131constructorimpl(int r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m132equalsimpl(int r2, Object r3) {
        if ((r3 instanceof AudioRestrictionMode) == true) goto L6;
        return false;
    L6:
        if (r2 == ((AudioRestrictionMode) r3).m136unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m133equalsimpl0(int r0, int r1) {
        if (r0 != r1) goto L5;
        return true;
    L5:
        return false;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m134hashCodeimpl(int r0) {
        return Integer.hashCode(r0);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m135toStringimpl(int r2) {
        return "AudioRestrictionMode(value=" + r2 + ')';
    }

    public boolean equals(Object r1) {
        return m132equalsimpl(this.value, r1);
    }

    public int hashCode() {
        return m134hashCodeimpl(this.value);
    }

    public String toString() {
        return m135toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m136unboximpl() {
        return this.value;
    }
}
