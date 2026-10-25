package androidx.camera.core;

import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.LensFacingCameraFilter;
import androidx.core.util.Preconditions;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class CameraSelector {
    public static final CameraSelector DEFAULT_BACK_CAMERA = null;
    public static final CameraSelector DEFAULT_FRONT_CAMERA = null;
    private final LinkedHashSet<CameraFilter> mCameraFilterSet;
    private final String mPhysicalCameraId;

    public static final class Builder {
        private final LinkedHashSet<CameraFilter> mCameraFilterSet;
        private String mPhysicalCameraId;

        public Builder() {
            this.mCameraFilterSet = new LinkedHashSet();
        }

        private Builder(LinkedHashSet<CameraFilter> r2) {
            this.mCameraFilterSet = new LinkedHashSet(r2);
        }

        public static Builder fromSelector(CameraSelector r1) {
            return new Builder(r1.getCameraFilterSet());
        }

        public Builder addCameraFilter(CameraFilter r2) {
            this.mCameraFilterSet.add(r2);
            return this;
        }

        public CameraSelector build() {
            return new CameraSelector(this.mCameraFilterSet, this.mPhysicalCameraId);
        }

        public Builder requireLensFacing(int r3) {
            if (r3 == (-1)) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkState(r0, "The specified lens facing is invalid.");
            this.mCameraFilterSet.add(new LensFacingCameraFilter(r3));
            return this;
        L5:
            r0 = false;
            goto L6
        }
    }

    public static /* synthetic */ List $r8$lambda$sVAI4dAQovYaCcAmS6RcCFJW3yM(CameraIdentifier[] r8, List r9) {
        ArrayList r0 = new ArrayList();
        HashSet r1 = new HashSet();
        int r2 = r8.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L13;
        CameraIdentifier r4 = r8[r3];
        Iterator r5 = r9.iterator();
    L6:
        if (r5.hasNext() == false) goto L12;
        CameraInfo r6 = (CameraInfo) r5.next();
        if (Objects.equals(r6.getCameraIdentifier(), r4) == false) goto L6;
        if (r1.contains(r4) == true) goto L6;
        r0.add(r6);
        r1.add(r4);
    L12:
        r3 = r3 + 1;
        goto L3
    L13:
        return r0;
    }

    static {
        DEFAULT_FRONT_CAMERA = new Builder().requireLensFacing(0).build();
        DEFAULT_BACK_CAMERA = new Builder().requireLensFacing(1).build();
    }

    public CameraSelector(LinkedHashSet<CameraFilter> r1, String r2) {
        this.mCameraFilterSet = r1;
        this.mPhysicalCameraId = r2;
    }

    private String logCameras(Set<CameraInternal> r3) {
        StringBuilder r2 = new StringBuilder("Cams:");
        r2.append(r3.size());
        Iterator<CameraInternal> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L7;
        CameraInfoInternal r0 = r32.next().getCameraInfoInternal();
        r2.append(String.format(" Id:%s  Lens:%s", new Object[]{r0.getCameraId(), Integer.valueOf(r0.getLensFacing())}));
        goto L4
    L7:
        return r2.toString();
    }

    private String logSelector() {
        StringBuilder r0 = new StringBuilder();
        r0.append(String.format("PhyId:%s  Filters:%s", new Object[]{this.mPhysicalCameraId, Integer.valueOf(this.mCameraFilterSet.size())}));
        Iterator<CameraFilter> r3 = this.mCameraFilterSet.iterator();
    L4:
        if (r3.hasNext() == false) goto L9;
        CameraFilter r1 = r3.next();
        r0.append(" Id:");
        r0.append(r1.getIdentifier());
        if ((r1 instanceof LensFacingCameraFilter) == false) goto L4;
        r0.append(" LensFilter:");
        r0.append(((LensFacingCameraFilter) r1).getLensFacing());
        goto L4
    L9:
        return r0.toString();
    }

    public static CameraSelector of(CameraIdentifier... r2) {
        if (r2 != null) goto L4;
    L7:
        f$$ExternalSyntheticBUOutline1.m("At least one CameraIdentifier must be provided.");
        return null;
    L4:
        if (r2.length == 0) goto L7;
        Builder r0 = new Builder();
        r0.addCameraFilter(new CameraSelector$$ExternalSyntheticLambda0(r2));
        return r0.build();
    }

    public LinkedHashSet<CameraInternal> filter(LinkedHashSet<CameraInternal> r4) {
        ArrayList r0 = new ArrayList();
        Iterator<CameraInternal> r1 = r4.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r0.add(r1.next().getCameraInfo());
        goto L4
    L6:
        List<CameraInfo> r3 = filter(r0);
        LinkedHashSet<CameraInternal> r02 = new LinkedHashSet();
        Iterator<CameraInternal> r42 = r4.iterator();
    L8:
        if (r42.hasNext() == false) goto L12;
        CameraInternal r12 = r42.next();
        if (r3.contains(r12.getCameraInfo()) == false) goto L8;
        r02.add(r12);
        goto L8
    L12:
        return r02;
    }

    public List<CameraInfo> filter(List<CameraInfo> r3) {
        List<CameraInfo> r0 = new ArrayList(r3);
        Iterator<CameraFilter> r2 = this.mCameraFilterSet.iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        r0 = r2.next().filter(Collections.unmodifiableList(r0));
        goto L4
    L6:
        r0.retainAll(r3);
        return r0;
    }

    public LinkedHashSet<CameraFilter> getCameraFilterSet() {
        return this.mCameraFilterSet;
    }

    public Integer getLensFacing() {
        Iterator<CameraFilter> r4 = this.mCameraFilterSet.iterator();
        Integer r1 = null;
    L4:
        if (r4.hasNext() == false) goto L15;
        CameraFilter r2 = r4.next();
        if ((r2 instanceof LensFacingCameraFilter) == false) goto L4;
        Integer r22 = Integer.valueOf(((LensFacingCameraFilter) r2).getLensFacing());
        if (r1 == null) goto L9;
        if (r1.equals(r22) == true) goto L4;
        Segment$$ExternalSyntheticBUOutline1.m("Multiple conflicting lens facing requirements exist.");
        return null;
    L9:
        r1 = r22;
        goto L4
    L15:
        return r1;
    }

    public String getPhysicalCameraId() {
        return this.mPhysicalCameraId;
    }

    public CameraInternal select(LinkedHashSet<CameraInternal> r3) {
        Iterator<CameraInternal> r0 = filter(r3).iterator();
        if (r0.hasNext() == true) goto L5;
        FieldSet$$ExternalSyntheticBUOutline0.m("No available camera can be found. %s %s", new Object[]{logCameras(r3), logSelector()});
        return null;
    L5:
        return r0.next();
    }
}
