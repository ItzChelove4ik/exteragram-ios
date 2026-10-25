package androidx.car.app.versioning;

import com.android.dex.EncodedValueReader$$ExternalSyntheticBUOutline0;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public abstract class CarAppApiLevels {
    public static int getLatest() {
        ClassLoader r1 = CarAppApiLevels.class.getClassLoader();
        Objects.requireNonNull(r1);
        InputStream r12 = r1.getResourceAsStream("car-app-api.level");
        if (r12 != null) goto L15;
        EncodedValueReader$$ExternalSyntheticBUOutline0.m("Car API level file %s not found", new Object[]{"car-app-api.level"});
        return 0;
    L15:
        String r13 = new BufferedReader(new InputStreamReader(r12)).readLine();     // Catch: IOException -> L11
        int r2 = Integer.parseInt(r13);     // Catch: IOException -> L11
        if (r2 < 1) goto L10;
        if (r2 > 7) goto L10;
        return r2;
    L10:
        throw new IllegalStateException("Unrecognized Car API level: " + r13);     // Catch: IOException -> L11
    L11:
        Segment$$ExternalSyntheticBUOutline1.m("Unable to read Car API level file");
        return 0;
    }

    public static int getOldest() {
        return 1;
    }

    public static boolean isValid(int r1) {
        if (r1 >= getOldest()) goto L5;
        return false;
    L5:
        if (r1 > getLatest()) goto L10;
        return true;
    L10:
        return false;
    }
}
