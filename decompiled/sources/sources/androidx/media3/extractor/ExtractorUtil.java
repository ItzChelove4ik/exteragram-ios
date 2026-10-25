package androidx.media3.extractor;

import androidx.car.app.media.CarAudioRecord;
import androidx.media3.common.ParserException;
import com.android.dx.AppDataDirGuesser;
import java.io.EOFException;
import org.checkerframework.dataflow.qual.Pure;
import org.telegram.messenger.OneUIUtilities;

/* loaded from: classes4.dex */
public abstract class ExtractorUtil {
    @Pure
    public static void checkContainerInput(boolean r0, String r1) {
        if (r0 == false) goto L5;
        return;
    L5:
        throw ParserException.createForMalformedContainer(r1, null);
    }

    public static int getMaximumEncodedRateBytesPerSecond(int r1) {
        if (r1 != 20) goto L5;
        return 63750;
    L5:
        if (r1 == 30) goto L32;
        switch(r1) {
            case 5: goto L30;
            case 6: goto L28;
            case 7: goto L26;
            case 8: goto L37;
            case 9: goto L24;
            case 10: goto L22;
            case 11: goto L20;
            case 12: goto L18;
            default: goto L7;
        };
    L7:
        switch(r1) {
            case 14: goto L16;
            case 15: goto L14;
            case 16: goto L12;
            case 17: goto L10;
            case 18: goto L36;
            default: goto L8;
        };
    L8:
        return -2147483647;
    L10:
        return 336000;
    L12:
        return 256000;
    L14:
        return 8000;
    L16:
        return 3062500;
    L36:
        return 768000;
    L18:
        return 7000;
    L20:
        return CarAudioRecord.AUDIO_CONTENT_SAMPLING_RATE;
    L22:
        return AppDataDirGuesser.PER_USER_RANGE;
    L24:
        return OneUIUtilities.ONE_UI_4_0;
    L26:
        return 192000;
    L28:
        return 768000;
    L30:
        return 80000;
    L37:
        return 2250000;
    L32:
        return 2250000;
    }

    public static boolean peekFullyQuietly(ExtractorInput r0, byte[] r1, int r2, int r3, boolean r4) {
        return r0.peekFully(r1, r2, r3, r4);
    L4:
        e = move-exception;
        if (r4 == false) goto L8;
        return false;
    L8:
        throw e;
    }

    public static int peekToLength(ExtractorInput r3, byte[] r4, int r5, int r6) {
        int r0 = 0;
    L3:
        if (r0 >= r6) goto L8;
        int r1 = r3.peek(r4, r5 + r0, r6 - r0);
        if (r1 == (-1)) goto L8;
        r0 = r0 + r1;
    L8:
        return r0;
    }

    public static boolean readFullyQuietly(ExtractorInput r0, byte[] r1, int r2, int r3) {
        r0.readFully(r1, r2, r3);     // Catch: EOFException -> L5
        return true;
    L5:
        return false;
    }

    public static boolean skipFullyQuietly(ExtractorInput r0, int r1) {
        r0.skipFully(r1);     // Catch: EOFException -> L5
        return true;
    L5:
        return false;
    }
}
