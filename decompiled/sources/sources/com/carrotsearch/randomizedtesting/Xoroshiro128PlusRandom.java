package com.carrotsearch.randomizedtesting;

import java.util.Random;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class Xoroshiro128PlusRandom extends Random {
    private long s0;
    private long s1;

    public Xoroshiro128PlusRandom(long r5) {
        super(0);
        long r52 = MurmurHash3.hash(r5);
        this.s0 = r52;
        long r53 = MurmurHash3.hash(r52);
        this.s1 = r53;
        if (this.s0 == 0) goto L5;
        return;
    L5:
        if (r53 != 0) goto L9;
        long r54 = MurmurHash3.hash(3735928559L);
        this.s0 = r54;
        this.s1 = MurmurHash3.hash(r54);
        return;
    }

    @Override // java.util.Random
    public int next(int r3) {
        return ((int) nextLong()) >>> (32 - r3);
    }

    @Override // java.util.Random
    public boolean nextBoolean() {
        if (nextLong() < 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Random
    public void nextBytes(byte[] r9) {
        int r0 = r9.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        long r2 = nextInt();
        int r4 = Math.min(r0 - r1, 8);
    L5:
        int r6 = r4 - 1;
        if (r4 <= 0) goto L3;
        r9[r1] = (byte) r2;
        r2 = r2 >>> 8;
        r1 = r1 + 1;
        r4 = r6;
        goto L5
    }

    @Override // java.util.Random
    public double nextDouble() {
        return (nextLong() >>> 11) * 1.1102230246251565E-16d;
    }

    @Override // java.util.Random
    public float nextFloat() {
        return (nextInt() >>> 8) * 5.9604645E-8f;
    }

    @Override // java.util.Random
    public double nextGaussian() {
        return super.nextGaussian();
    }

    @Override // java.util.Random
    public int nextInt() {
        return (int) nextLong();
    }

    @Override // java.util.Random
    public int nextInt(int r1) {
        return super.nextInt(r1);
    }

    @Override // java.util.Random
    public long nextLong() {
        long r0 = this.s0;
        long r2 = this.s1;
        long r4 = r0 + r2;
        long r22 = r2 ^ r0;
        this.s0 = (Long.rotateLeft(r0, 55) ^ r22) ^ (r22 << 14);
        this.s1 = Long.rotateLeft(r22, 36);
        return r4;
    }

    @Override // java.util.Random
    public void setSeed(long r3) {
        if (this.s0 == 0) goto L5;
    L7:
        GlShader$$ExternalSyntheticBUOutline1.m("No seed set");
        return;
    L5:
        if (this.s1 != 0) goto L7;
    }
}
