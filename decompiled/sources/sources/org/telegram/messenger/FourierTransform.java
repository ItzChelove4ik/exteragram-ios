package org.telegram.messenger;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public abstract class FourierTransform {
    protected static final int LINAVG = 1;
    protected static final int LOGAVG = 2;
    protected static final int NOAVG = 3;
    protected static final float TWO_PI = 6.2831855f;
    protected float[] averages;
    protected int avgPerOctave;
    protected float bandWidth;
    protected float[] imag;
    protected int octaves;
    protected float[] real;
    protected int sampleRate;
    protected float[] spectrum;
    protected int timeSize;
    protected int whichAverage;

    public static class FFT extends FourierTransform {
        private float[] coslookup;
        private int[] reverse;
        private float[] sinlookup;

        public FFT(int r1, float r2) {
            super(r1, r2);
            if ((r1 & (r1 - 1)) != 0) goto L6;
            buildReverseTable();
            buildTrigTables();
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("FFT: timeSize must be a power of two.");
            throw null;
        }

        private void bitReverseComplex() {
            float[] r0 = new float[this.real.length];
            float[] r1 = new float[this.imag.length];
            int r2 = 0;
        L3:
            float[] r3 = this.real;
            if (r2 >= r3.length) goto L6;
            int r4 = this.reverse[r2];
            r0[r2] = r3[r4];
            r1[r2] = this.imag[r4];
            r2 = r2 + 1;
            goto L3
        L6:
            this.real = r0;
            this.imag = r1;
        }

        private void bitReverseSamples(float[] r4, int r5) {
            int r0 = 0;
        L4:
            if (r0 >= this.timeSize) goto L6;
            this.real[r0] = r4[this.reverse[r0] + r5];
            this.imag[r0] = 0.0f;
            r0 = r0 + 1;
            goto L4
        }

        private void buildReverseTable() {
            int r0 = this.timeSize;
            int[] r1 = new int[r0];
            this.reverse = r1;
            r1[0] = 0;
            int r12 = r0 / 2;
            int r3 = 1;
        L3:
            if (r3 >= r0) goto L8;
            int r4 = 0;
        L5:
            if (r4 >= r3) goto L7;
            int[] r5 = this.reverse;
            r5[r4 + r3] = r5[r4] + r12;
            r4 = r4 + 1;
            goto L5
        L7:
            r3 = r3 << 1;
            r12 = r12 >> 1;
            goto L3
        }

        private void buildTrigTables() {
            int r0 = this.timeSize;
            this.sinlookup = new float[r0];
            this.coslookup = new float[r0];
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            double r3 = (-3.1415927f) / r1;
            this.sinlookup[r1] = (float) Math.sin(r3);
            this.coslookup[r1] = (float) Math.cos(r3);
            r1 = r1 + 1;
            goto L3
        }

        private float cos(int r1) {
            return this.coslookup[r1];
        }

        private void fft() {
            int r0 = 1;
        L4:
            if (r0 >= this.real.length) goto L13;
            float r1 = cos(r0);
            float r2 = sin(r0);
            float r3 = 1.0f;
            float r4 = 0.0f;
            int r5 = 0;
        L6:
            if (r5 >= r0) goto L12;
            int r6 = r5;
        L8:
            float[] r7 = this.real;
            if (r6 >= r7.length) goto L11;
            int r8 = r6 + r0;
            float r9 = r7[r8];
            float[] r11 = this.imag;
            float r12 = r11[r8];
            float r10 = (r3 * r9) - (r4 * r12);
            float r122 = (r12 * r3) + (r9 * r4);
            r7[r8] = r7[r6] - r10;
            r11[r8] = r11[r6] - r122;
            r7[r6] = r7[r6] + r10;
            r11[r6] = r11[r6] + r122;
            r6 = r6 + (r0 * 2);
            goto L8
        L11:
            float r62 = (r3 * r1) - (r4 * r2);
            float r42 = r4 * r1;
            r4 = r42 + (r3 * r2);
            r5 = r5 + 1;
            r3 = r62;
            goto L6
        L12:
            r0 = r0 * 2;
            goto L4
        }

        private float sin(int r1) {
            return this.sinlookup[r1];
        }

        @Override // org.telegram.messenger.FourierTransform
        public void allocateArrays() {
            int r0 = this.timeSize;
            this.spectrum = new float[(r0 / 2) + 1];
            this.real = new float[r0];
            this.imag = new float[r0];
        }

        @Override // org.telegram.messenger.FourierTransform
        public void forward(float[] r3) {
            if (r3.length == this.timeSize) goto L5;
            return;
        L5:
            bitReverseSamples(r3, 0);
            fft();
            fillSpectrum();
        }

        @Override // org.telegram.messenger.FourierTransform
        public void forward(float[] r3, int r4) {
            if ((r3.length - r4) >= this.timeSize) goto L5;
            return;
        L5:
            bitReverseSamples(r3, r4);
            fft();
            fillSpectrum();
        }

        public void forward(float[] r3, float[] r4) {
            int r0 = r3.length;
            int r1 = this.timeSize;
            if (r0 == r1) goto L5;
            return;
        L5:
            if (r4.length != r1) goto L10;
            setComplex(r3, r4);
            bitReverseComplex();
            fft();
            fillSpectrum();
            return;
        }

        @Override // org.telegram.messenger.FourierTransform
        public void inverse(float[] r6) {
            if (r6.length > this.real.length) goto L13;
            int r0 = 0;
            int r1 = 0;
        L7:
            if (r1 >= this.timeSize) goto L9;
            float[] r2 = this.imag;
            r2[r1] = r2[r1] * (-1.0f);
            r1 = r1 + 1;
            goto L7
        L9:
            bitReverseComplex();
            fft();
        L11:
            if (r0 >= r6.length) goto L16;
            r6[r0] = this.real[r0] / r1.length;
            r0 = r0 + 1;
            goto L11
        L16:
            return;
        }

        @Override // org.telegram.messenger.FourierTransform
        public void scaleBand(int r5, float r6) {
            if (r6 < 0.0f) goto L13;
            float[] r0 = this.real;
            r0[r5] = r0[r5] * r6;
            float[] r1 = this.imag;
            r1[r5] = r1[r5] * r6;
            float[] r2 = this.spectrum;
            r2[r5] = r2[r5] * r6;
            if (r5 == 0) goto L11;
            int r4 = this.timeSize;
            if (r5 == (r4 / 2)) goto L12;
            r0[r4 - r5] = r0[r5];
            r1[r4 - r5] = -r1[r5];
            return;
        L12:
            return;
        L11:
            return;
        }

        @Override // org.telegram.messenger.FourierTransform
        public void setBand(int r6, float r7) {
            if (r7 < 0.0f) goto L18;
            float[] r1 = this.real;
            float r2 = r1[r6];
            if (r2 == 0.0f) goto L8;
        L10:
            float[] r0 = this.spectrum;
            r1[r6] = r2 / r0[r6];
            float[] r22 = this.imag;
            r22[r6] = r22[r6] / r0[r6];
            r0[r6] = r7;
            r1[r6] = r1[r6] * r7;
            r22[r6] = r22[r6] * r0[r6];
        L11:
            if (r6 == 0) goto L16;
            int r72 = this.timeSize;
            if (r6 == (r72 / 2)) goto L17;
            r1[r72 - r6] = r1[r6];
            float[] r5 = this.imag;
            r5[r72 - r6] = -r5[r6];
            return;
        L17:
            return;
        L16:
            return;
        L8:
            if (this.imag[r6] != 0.0f) goto L10;
            r1[r6] = r7;
            this.spectrum[r6] = r7;
            goto L11
        }
    }

    public FourierTransform(int r2, float r3) {
        this.timeSize = r2;
        int r32 = (int) r3;
        this.sampleRate = r32;
        this.bandWidth = (2.0f / r2) * (r32 / 2.0f);
        noAverages();
        allocateArrays();
    }

    public abstract void allocateArrays();

    public float calcAvg(float r4, float r5) {
        int r42 = freqToIndex(r4);
        int r52 = freqToIndex(r5);
        float r0 = 0.0f;
        int r1 = r42;
    L3:
        if (r1 > r52) goto L6;
        r0 = r0 + this.spectrum[r1];
        r1 = r1 + 1;
        goto L3
    L6:
        return r0 / ((r52 - r42) + 1);
    }

    public void fillSpectrum() {
        int r1 = 0;
    L3:
        float[] r2 = this.spectrum;
        if (r1 >= r2.length) goto L6;
        float r3 = this.real[r1];
        float r4 = this.imag[r1];
        r2[r1] = (float) Math.sqrt((r3 * r3) + (r4 * r4));
        r1 = r1 + 1;
        goto L3
    L6:
        int r12 = this.whichAverage;
        if (r12 != 1) goto L18;
        int r13 = r2.length / this.averages.length;
        int r22 = 0;
    L10:
        if (r22 >= this.averages.length) goto L38;
        int r42 = 0;
        float r5 = 0.0f;
    L12:
        if (r42 >= r13) goto L16;
        int r6 = (r22 * r13) + r42;
        float[] r7 = this.spectrum;
        if (r6 >= r7.length) goto L16;
        r5 = r5 + r7[r6];
        r42 = r42 + 1;
    L16:
        this.averages[r22] = r5 / (r42 + 1);
        r22 = r22 + 1;
        goto L10
    L38:
        return;
    L18:
        if (r12 != 2) goto L31;
        int r14 = 0;
    L21:
        if (r14 >= this.octaves) goto L39;
        if (r14 != 0) goto L25;
        float r8 = 0.0f;
    L26:
        float r52 = (((this.sampleRate / 2) / ((float) Math.pow(2.0d, (this.octaves - r14) - 1))) - r8) / this.avgPerOctave;
        int r62 = 0;
    L27:
        int r72 = this.avgPerOctave;
        if (r62 >= r72) goto L30;
        float r10 = r8 + r52;
        this.averages[(r72 * r14) + r62] = calcAvg(r8, r10);
        r62 = r62 + 1;
        r8 = r10;
        goto L27
    L30:
        r14 = r14 + 1;
        goto L21
    L25:
        r8 = (this.sampleRate / 2) / ((float) Math.pow(2.0d, r5 - r14));
        goto L26
    L39:
        return;
    }

    public abstract void forward(float[] r1);

    public void forward(float[] r4, int r5) {
        int r0 = r4.length - r5;
        int r1 = this.timeSize;
        if (r0 >= r1) goto L5;
        return;
    L5:
        float[] r02 = new float[r1];
        System.arraycopy(r4, r5, r02, 0, r1);
        forward(r02);
    }

    public int freqToIndex(float r4) {
        if (r4 >= (getBandWidth() / 2.0f)) goto L7;
        return 0;
    L7:
        if (r4 <= ((this.sampleRate / 2) - (getBandWidth() / 2.0f))) goto L11;
        return this.spectrum.length - 1;
    L11:
        return Math.round(this.timeSize * (r4 / this.sampleRate));
    }

    public float getBand(int r2) {
        if (r2 >= 0) goto L4;
        r2 = 0;
    L4:
        float[] r1 = this.spectrum;
        if (r2 <= (r1.length - 1)) goto L8;
        r2 = r1.length - 1;
    L8:
        return r1[r2];
    }

    public float getBandWidth() {
        return this.bandWidth;
    }

    public float[] getSpectrumImaginary() {
        return this.imag;
    }

    public float[] getSpectrumReal() {
        return this.real;
    }

    public float indexToFreq(int r4) {
        float r0 = getBandWidth();
        if (r4 != 0) goto L7;
        return r0 * 0.25f;
    L7:
        if (r4 != (this.spectrum.length - 1)) goto L11;
        return ((this.sampleRate / 2) - (r0 / 2.0f)) + (r0 * 0.25f);
    L11:
        return r4 * r0;
    }

    public abstract void inverse(float[] r1);

    public void inverse(float[] r1, float[] r2, float[] r3) {
        setComplex(r1, r2);
        inverse(r3);
    }

    public void linAverages(int r2) {
        if (r2 <= (this.spectrum.length / 2)) goto L5;
        return;
    L5:
        this.averages = new float[r2];
        this.whichAverage = 1;
    }

    public void logAverages(int r5, int r6) {
        float r0 = this.sampleRate / 2.0f;
        this.octaves = 1;
    L3:
        r0 = r0 / 2.0f;
        if (r0 <= r5) goto L6;
        this.octaves++;
        goto L3
    L6:
        this.avgPerOctave = r6;
        this.averages = new float[this.octaves * r6];
        this.whichAverage = 2;
    }

    public void noAverages() {
        this.averages = new float[0];
        this.whichAverage = 3;
    }

    public abstract void scaleBand(int r1, float r2);

    public abstract void setBand(int r1, float r2);

    public void setComplex(float[] r4, float[] r5) {
        float[] r0 = this.real;
        if (r0.length != r4.length) goto L5;
    L7:
        System.arraycopy(r4, 0, r0, 0, r4.length);
        System.arraycopy(r5, 0, this.imag, 0, r5.length);
        return;
    L5:
        if (this.imag.length == r5.length) goto L7;
    }

    public int specSize() {
        return this.spectrum.length;
    }

    public int timeSize() {
        return this.timeSize;
    }
}
