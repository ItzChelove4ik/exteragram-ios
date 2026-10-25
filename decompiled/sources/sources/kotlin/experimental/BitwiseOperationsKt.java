package kotlin.experimental;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\n\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008c\u0004\u001a\u0016\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008c\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\u008c\u0004\u001a\u000e\u0010\u0005\u001a\u00020\u0001*\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\u008c\u0004\u001a\u0016\u0010\u0003\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\u008c\u0004\u001a\u0016\u0010\u0004\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\u008c\u0004\u001a\u000e\u0010\u0005\u001a\u00020\u0006*\u00020\u0006H\u0087\u0088\u0004¨\u0006\u0007"}, d2 = {"and", _UrlKt.FRAGMENT_ENCODE_SET, "other", PluginsConstants.HookFilterTypes.OR, "xor", "inv", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class BitwiseOperationsKt {
    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte and(byte r0, byte r1) {
        return (byte) (r0 & r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short and(short r0, short r1) {
        return (short) (r0 & r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte inv(byte r0) {
        return (byte) (~r0);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short inv(short r0) {
        return (short) (~r0);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte or(byte r0, byte r1) {
        return (byte) (r0 | r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short or(short r0, short r1) {
        return (short) (r0 | r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte xor(byte r0, byte r1) {
        return (byte) (r0 ^ r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short xor(short r0, short r1) {
        return (short) (r0 ^ r1);
    }
}
