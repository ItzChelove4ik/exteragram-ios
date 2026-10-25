package com.google.zxing.qrcode.detector;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.PerspectiveTransform;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.qrcode.decoder.Version;
import java.util.Map;

/* loaded from: classes5.dex */
public class Detector {
    private final BitMatrix image;

    public Detector(BitMatrix r1) {
        this.image = r1;
    }

    private float calculateModuleSizeOneWay(ResultPoint r5, ResultPoint r6) {
        float r0 = sizeOfBlackWhiteBlackRunBothWays((int) r5.getX(), (int) r5.getY(), (int) r6.getX(), (int) r6.getY());
        float r4 = sizeOfBlackWhiteBlackRunBothWays((int) r6.getX(), (int) r6.getY(), (int) r5.getX(), (int) r5.getY());
        if (Float.isNaN(r0) == false) goto L7;
        return r4 / 7.0f;
    L7:
        if (Float.isNaN(r4) == false) goto L11;
        return r0 / 7.0f;
    L11:
        return (r0 + r4) / 14.0f;
    }

    private static int computeDimension(ResultPoint r0, ResultPoint r1, ResultPoint r2, float r3) {
        int r12 = (MathUtils.round(ResultPoint.distance(r0, r1) / r3) + MathUtils.round(ResultPoint.distance(r0, r2) / r3)) / 2;
        int r22 = r12 + 7;
        int r32 = r22 & 3;
        if (r32 == 0) goto L13;
        if (r32 == 2) goto L11;
        if (r32 == 3) goto L9;
        return r22;
    L9:
        return r12 + 5;
    L11:
        return r12 + 6;
    L13:
        return r12 + 8;
    }

    private static PerspectiveTransform createTransform(ResultPoint r18, ResultPoint r19, ResultPoint r20, ResultPoint r21, int r22) {
        float r4 = r22 - 3.5f;
        if (r21 == null) goto L6;
        float r0 = r21.getX();
        float r1 = r21.getY();
        float r6 = r4 - 3.0f;
    L8:
        return PerspectiveTransform.quadrilateralToQuadrilateral(3.5f, 3.5f, r4, 3.5f, r6, r6, 3.5f, r4, r18.getX(), r18.getY(), r19.getX(), r19.getY(), r0, r1, r20.getX(), r20.getY());
    L6:
        r0 = (r19.getX() - r18.getX()) + r20.getX();
        r1 = (r19.getY() - r18.getY()) + r20.getY();
        r6 = r4;
        goto L8
    }

    private static BitMatrix sampleGrid(BitMatrix r1, PerspectiveTransform r2, int r3) {
        return GridSampler.getInstance().sampleGrid(r1, r3, r3, r2);
    }

    private float sizeOfBlackWhiteBlackRun(int r18, int r19, int r20, int r21) {
        int r3 = 1;
        if (Math.abs(r21 - r19) <= Math.abs(r20 - r18)) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == false) goto L8;
        int r4 = r18;
        int r1 = r19;
        int r6 = r20;
        int r5 = r21;
    L9:
        int r7 = Math.abs(r5 - r1);
        int r8 = Math.abs(r6 - r4);
        int r10 = 2;
        int r9 = (-r7) / 2;
        int r11 = -1;
        if (r1 >= r5) goto L12;
        int r12 = 1;
    L13:
        if (r4 >= r6) goto L15;
        r11 = 1;
    L15:
        int r52 = r5 + r12;
        int r13 = r1;
        int r14 = r4;
        int r15 = 0;
    L16:
        if (r13 == r52) goto L40;
        if (r0 == false) goto L19;
        int r2 = r14;
    L20:
        if (r0 == false) goto L22;
        int r102 = r13;
    L23:
        boolean r16 = r0;
        if (r15 != r3) goto L26;
        int r02 = r3;
        int r192 = r7;
        Detector r32 = this;
    L28:
        if (r02 == r32.image.get(r2, r102)) goto L30;
    L34:
        r9 = r9 + r8;
        if (r9 <= 0) goto L39;
        if (r14 == r6) goto L37;
        r14 = r14 + r11;
        r9 = r9 - r192;
        goto L39
    L37:
        int r03 = 2;
    L41:
        if (r15 == r03) goto L43;
        return Float.NaN;
    L43:
        return MathUtils.distance(r52, r6, r1, r4);
    L39:
        r13 = r13 + r12;
        r7 = r192;
        r0 = r16;
        r3 = 1;
        r10 = 2;
        goto L16
    L30:
        if (r15 == 2) goto L32;
        r15 = r15 + 1;
        goto L34
    L32:
        return MathUtils.distance(r13, r14, r1, r4);
    L26:
        r02 = 0;
        r32 = this;
        r192 = r7;
        goto L28
    L22:
        r102 = r14;
        goto L23
    L19:
        r2 = r13;
        goto L20
    L40:
        r03 = r10;
        goto L41
    L12:
        r12 = -1;
        goto L13
    L8:
        r1 = r18;
        r4 = r19;
        r5 = r20;
        r6 = r21;
        goto L9
    L5:
        r0 = false;
        goto L6
    }

    private float sizeOfBlackWhiteBlackRunBothWays(int r6, int r7, int r8, int r9) {
        float r0 = sizeOfBlackWhiteBlackRun(r6, r7, r8, r9);
        int r82 = r6 - (r8 - r6);
        int r1 = 0;
        if (r82 >= 0) goto L6;
        float r3 = r6 / (r6 - r82);
        r82 = 0;
    L9:
        float r4 = r7;
        int r92 = (int) (r4 - ((r9 - r7) * r3));
        if (r92 >= 0) goto L13;
        float r42 = r4 / (r7 - r92);
    L17:
        return (r0 + sizeOfBlackWhiteBlackRun(r6, r7, (int) (r6 + ((r82 - r6) * r42)), r1)) - 1.0f;
    L13:
        if (r92 < this.image.getHeight()) goto L15;
        r42 = ((this.image.getHeight() - 1) - r7) / (r92 - r7);
        r1 = this.image.getHeight() - 1;
        goto L17
    L15:
        r1 = r92;
        r42 = 1.0f;
        goto L17
    L6:
        if (r82 < this.image.getWidth()) goto L8;
        r3 = ((this.image.getWidth() - 1) - r6) / (r82 - r6);
        r82 = this.image.getWidth() - 1;
        goto L9
    L8:
        r3 = 1.0f;
        goto L9
    }

    public final float calculateModuleSize(ResultPoint r1, ResultPoint r2, ResultPoint r3) {
        return (calculateModuleSizeOneWay(r1, r2) + calculateModuleSizeOneWay(r1, r3)) / 2.0f;
    }

    public final DetectorResult detect(Map<DecodeHintType, ?> r4) {
        if (r4 == null) goto L6;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK));
    L6:
        return processFinderPatternInfo(new FinderPatternFinder(this.image, null).find(r4));
    }

    public final AlignmentPattern findAlignmentInRegion(float r11, int r12, int r13, float r14) {
        int r142 = (int) (r14 * r11);
        int r4 = Math.max(0, r12 - r142);
        int r6 = Math.min(this.image.getWidth() - 1, r12 + r142) - r4;
        float r0 = 3.0f * r11;
        if (r6 < r0) goto L11;
        int r5 = Math.max(0, r13 - r142);
        int r7 = Math.min(this.image.getHeight() - 1, r13 + r142) - r5;
        if (r7 < r0) goto L9;
        return new AlignmentPatternFinder(this.image, r4, r5, r6, r7, r11, null).find();
    L9:
        throw NotFoundException.getNotFoundInstance();
    L11:
        throw NotFoundException.getNotFoundInstance();
    }

    public final DetectorResult processFinderPatternInfo(FinderPatternInfo r11) {
        FinderPattern r0 = r11.getTopLeft();
        FinderPattern r1 = r11.getTopRight();
        FinderPattern r112 = r11.getBottomLeft();
        float r2 = calculateModuleSize(r0, r1, r112);
        if (r2 < 1.0f) goto L21;
        int r4 = computeDimension(r0, r1, r112, r2);
        Version r5 = Version.getProvisionalVersionForDimension(r4);
        int r6 = r5.getDimensionForVersion() - 7;
        if (r5.getAlignmentPatternCenters().length <= 0) goto L13;
        float r52 = (r1.getX() - r0.getX()) + r112.getX();
        float r8 = (r1.getY() - r0.getY()) + r112.getY();
        float r3 = 1.0f - (3.0f / r6);
        int r53 = (int) (r0.getX() + ((r52 - r0.getX()) * r3));
        int r32 = (int) (r0.getY() + (r3 * (r8 - r0.getY())));
        int r62 = 4;
    L8:
        if (r62 > 16) goto L13;
        AlignmentPattern r22 = findAlignmentInRegion(r2, r53, r32, r62);     // Catch: NotFoundException -> L12
    L14:
        BitMatrix r10 = sampleGrid(this.image, createTransform(r0, r1, r112, r22, r4), r4);
        if (r22 != null) goto L17;
        ResultPoint[] r23 = {r112, r0, r1};
    L19:
        return new DetectorResult(r10, r23);
    L17:
        r23 = new ResultPoint[]{r112, r0, r1, r22};
    L12:
        r62 = r62 << 1;
    L13:
        r22 = null;
        goto L14
    L21:
        throw NotFoundException.getNotFoundInstance();
    }
}
