package androidx.car.app.utils;

import android.content.Context;
import java.util.Objects;

/* loaded from: classes3.dex */
public abstract class CommonUtils {
    public static boolean isAutomotiveOS(Context r1) {
        Objects.requireNonNull(r1);
        return r1.getPackageManager().hasSystemFeature("android.hardware.type.automotive");
    }
}
