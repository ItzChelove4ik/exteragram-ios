package androidx.camera.core.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public interface EncoderProfilesProvider {
    public static final EncoderProfilesProvider EMPTY = null;
    public static final List<Integer> QUALITY_HIGH_TO_LOW = null;

    /* renamed from: androidx.camera.core.impl.EncoderProfilesProvider$1, reason: invalid class name */
    public class AnonymousClass1 implements EncoderProfilesProvider {
        public AnonymousClass1() {
        }

        @Override // androidx.camera.core.impl.EncoderProfilesProvider
        public EncoderProfilesProxy getAll(int r1) {
            return null;
        }

        @Override // androidx.camera.core.impl.EncoderProfilesProvider
        public boolean hasProfile(int r1) {
            return false;
        }
    }

    static {
        EMPTY = new AnonymousClass1();
        QUALITY_HIGH_TO_LOW = Collections.unmodifiableList(Arrays.asList(new Integer[]{13, 10, 8, 11, 6, 5, 4, 9, 3, 7, 2}));
    }

    EncoderProfilesProxy getAll(int r1);

    boolean hasProfile(int r1);
}
