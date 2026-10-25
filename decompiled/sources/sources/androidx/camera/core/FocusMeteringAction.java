package androidx.camera.core;

import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class FocusMeteringAction {
    private final long mAutoCancelDurationInMillis;
    private final List<MeteringPoint> mMeteringPointsAe;
    private final List<MeteringPoint> mMeteringPointsAf;
    private final List<MeteringPoint> mMeteringPointsAwb;

    public static class Builder {
        long mAutoCancelDurationInMillis;
        final List<MeteringPoint> mMeteringPointsAe;
        final List<MeteringPoint> mMeteringPointsAf;
        final List<MeteringPoint> mMeteringPointsAwb;

        public Builder(FocusMeteringAction r6) {
            ArrayList r0 = new ArrayList();
            this.mMeteringPointsAf = r0;
            ArrayList r1 = new ArrayList();
            this.mMeteringPointsAe = r1;
            ArrayList r2 = new ArrayList();
            this.mMeteringPointsAwb = r2;
            this.mAutoCancelDurationInMillis = 5000;
            r0.addAll(r6.getMeteringPointsAf());
            r1.addAll(r6.getMeteringPointsAe());
            r2.addAll(r6.getMeteringPointsAwb());
            this.mAutoCancelDurationInMillis = r6.getAutoCancelDurationInMillis();
        }

        public Builder(MeteringPoint r3, int r4) {
            this.mMeteringPointsAf = new ArrayList();
            this.mMeteringPointsAe = new ArrayList();
            this.mMeteringPointsAwb = new ArrayList();
            this.mAutoCancelDurationInMillis = 5000;
            addPoint(r3, r4);
        }

        public Builder addPoint(MeteringPoint r5, int r6) {
            boolean r0 = false;
            if (r5 == null) goto L5;
            boolean r2 = true;
        L6:
            Preconditions.checkArgument(r2, "Point cannot be null.");
            if (r6 >= 1) goto L9;
        L11:
            Preconditions.checkArgument(r0, "Invalid metering mode " + r6);
            if ((r6 & 1) == 0) goto L15;
            this.mMeteringPointsAf.add(r5);
        L15:
            if ((r6 & 2) == 0) goto L18;
            this.mMeteringPointsAe.add(r5);
        L18:
            if ((r6 & 4) == 0) goto L20;
            this.mMeteringPointsAwb.add(r5);
        L20:
            return this;
        L9:
            if (r6 > 7) goto L11;
            r0 = true;
            goto L11
        L5:
            r2 = false;
            goto L6
        }

        public FocusMeteringAction build() {
            return new FocusMeteringAction(this);
        }

        public Builder removePoints(int r2) {
            if ((r2 & 1) == 0) goto L6;
            this.mMeteringPointsAf.clear();
        L6:
            if ((r2 & 2) == 0) goto L9;
            this.mMeteringPointsAe.clear();
        L9:
            if ((r2 & 4) == 0) goto L11;
            this.mMeteringPointsAwb.clear();
        L11:
            return this;
        }
    }

    public FocusMeteringAction(Builder r3) {
        this.mMeteringPointsAf = Collections.unmodifiableList(r3.mMeteringPointsAf);
        this.mMeteringPointsAe = Collections.unmodifiableList(r3.mMeteringPointsAe);
        this.mMeteringPointsAwb = Collections.unmodifiableList(r3.mMeteringPointsAwb);
        this.mAutoCancelDurationInMillis = r3.mAutoCancelDurationInMillis;
    }

    public long getAutoCancelDurationInMillis() {
        return this.mAutoCancelDurationInMillis;
    }

    public List<MeteringPoint> getMeteringPointsAe() {
        return this.mMeteringPointsAe;
    }

    public List<MeteringPoint> getMeteringPointsAf() {
        return this.mMeteringPointsAf;
    }

    public List<MeteringPoint> getMeteringPointsAwb() {
        return this.mMeteringPointsAwb;
    }

    public boolean isAutoCancelEnabled() {
        if (this.mAutoCancelDurationInMillis <= 0) goto L6;
        return true;
    L6:
        return false;
    }
}
