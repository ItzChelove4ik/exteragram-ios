package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Objects;

/* loaded from: classes4.dex */
final class SessionPositionInfo {
    public static final SessionPositionInfo DEFAULT = null;
    public static final Player.PositionInfo DEFAULT_POSITION_INFO = null;
    private static final String FIELD_BUFFERED_PERCENTAGE = null;
    static final String FIELD_BUFFERED_POSITION_MS = null;
    static final String FIELD_CONTENT_BUFFERED_POSITION_MS = null;
    private static final String FIELD_CONTENT_DURATION_MS = null;
    private static final String FIELD_CURRENT_LIVE_OFFSET_MS = null;
    private static final String FIELD_DURATION_MS = null;
    private static final String FIELD_EVENT_TIME_MS = null;
    private static final String FIELD_IS_PLAYING_AD = null;
    static final String FIELD_POSITION_INFO = null;
    private static final String FIELD_TOTAL_BUFFERED_DURATION_MS = null;
    public final int bufferedPercentage;
    public final long bufferedPositionMs;
    public final long contentBufferedPositionMs;
    public final long contentDurationMs;
    public final long currentLiveOffsetMs;
    public final long durationMs;
    public final long eventTimeMs;
    public final boolean isPlayingAd;
    public final Player.PositionInfo positionInfo;
    public final long totalBufferedDurationMs;

    static {
        Player.PositionInfo r0 = new Player.PositionInfo(null, 0, null, null, 0, 0, 0, -1, -1);
        DEFAULT_POSITION_INFO = r0;
        DEFAULT = new SessionPositionInfo(r0, false, -9223372036854775807L, -9223372036854775807L, 0, 0, 0, -9223372036854775807L, -9223372036854775807L, 0);
        FIELD_POSITION_INFO = Util.intToStringMaxRadix(0);
        FIELD_IS_PLAYING_AD = Util.intToStringMaxRadix(1);
        FIELD_EVENT_TIME_MS = Util.intToStringMaxRadix(2);
        FIELD_DURATION_MS = Util.intToStringMaxRadix(3);
        FIELD_BUFFERED_POSITION_MS = Util.intToStringMaxRadix(4);
        FIELD_BUFFERED_PERCENTAGE = Util.intToStringMaxRadix(5);
        FIELD_TOTAL_BUFFERED_DURATION_MS = Util.intToStringMaxRadix(6);
        FIELD_CURRENT_LIVE_OFFSET_MS = Util.intToStringMaxRadix(7);
        FIELD_CONTENT_DURATION_MS = Util.intToStringMaxRadix(8);
        FIELD_CONTENT_BUFFERED_POSITION_MS = Util.intToStringMaxRadix(9);
    }

    public SessionPositionInfo(Player.PositionInfo r5, boolean r6, long r7, long r9, long r11, int r13, long r14, long r16, long r18, long r20) {
        boolean r2 = false;
        if (r5.adGroupIndex == (-1)) goto L5;
        boolean r0 = true;
    L6:
        if (r6 != r0) goto L8;
        r2 = true;
    L8:
        Assertions.checkArgument(r2);
        this.positionInfo = r5;
        this.isPlayingAd = r6;
        this.eventTimeMs = r7;
        this.durationMs = r9;
        this.bufferedPositionMs = r11;
        this.bufferedPercentage = r13;
        this.totalBufferedDurationMs = r14;
        this.currentLiveOffsetMs = r16;
        this.contentDurationMs = r18;
        this.contentBufferedPositionMs = r20;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if (r7 != null) goto L8;
    L31:
        return false;
    L8:
        if (SessionPositionInfo.class != r7.getClass()) goto L31;
        SessionPositionInfo r72 = (SessionPositionInfo) r7;
        if (this.eventTimeMs != r72.eventTimeMs) goto L31;
        if (this.positionInfo.equals(r72.positionInfo) == false) goto L31;
        if (this.isPlayingAd != r72.isPlayingAd) goto L31;
        if (this.durationMs != r72.durationMs) goto L31;
        if (this.bufferedPositionMs != r72.bufferedPositionMs) goto L31;
        if (this.bufferedPercentage != r72.bufferedPercentage) goto L31;
        if (this.totalBufferedDurationMs != r72.totalBufferedDurationMs) goto L31;
        if (this.currentLiveOffsetMs != r72.currentLiveOffsetMs) goto L31;
        if (this.contentDurationMs != r72.contentDurationMs) goto L31;
        if (this.contentBufferedPositionMs != r72.contentBufferedPositionMs) goto L31;
        return true;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getPhiList()" because "resultVar" is null
        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public androidx.media3.session.SessionPositionInfo filterByAvailableCommands(boolean r21, boolean r22) {
        /*  JADX ERROR: Simple mode code generation failed
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000b: NEW_INSTANCE (r3v0 ?? I:androidx.media3.session.SessionPositionInfo) =  A[DECLARE_VAR] androidx.media3.session.SessionPositionInfo in method: androidx.media3.session.SessionPositionInfo.filterByAvailableCommands(boolean, boolean):androidx.media3.session.SessionPositionInfo, file: classes4.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:355)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.ArgType.getPrimitiveType()" because "type" is null
            	at jadx.core.codegen.ClassGen.useType(ClassGen.java:554)
            	at jadx.core.codegen.InsnGen.useType(InsnGen.java:269)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:166)
            	at jadx.core.codegen.InsnGen.declareVar(InsnGen.java:159)
            	at jadx.core.codegen.InsnGen.assignVar(InsnGen.java:152)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:299)
            	... 11 more
            */
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r22
            if (r1 == 0) goto Lb
            if (r2 == 0) goto Lb
            return r0
        Lb:
            androidx.media3.session.SessionPositionInfo r3 = new androidx.media3.session.SessionPositionInfo
            androidx.media3.common.Player$PositionInfo r4 = r0.positionInfo
            androidx.media3.common.Player$PositionInfo r2 = r4.filterByAvailableCommands(r1, r2)
            r4 = 0
            if (r1 == 0) goto L1e
            boolean r5 = r0.isPlayingAd
            if (r5 == 0) goto L1e
            r5 = 1
            r7 = r4
        L1c:
            r6 = r3
            goto L21
        L1e:
            r5 = r4
            r7 = r5
            goto L1c
        L21:
            long r3 = r0.eventTimeMs
            if (r1 == 0) goto L28
            long r10 = r0.durationMs
            goto L2d
        L28:
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L2d:
            if (r1 == 0) goto L32
            long r14 = r0.bufferedPositionMs
            goto L34
        L32:
            r14 = 0
        L34:
            if (r1 == 0) goto L38
            int r7 = r0.bufferedPercentage
        L38:
            if (r1 == 0) goto L3d
            long r8 = r0.totalBufferedDurationMs
            goto L3f
        L3d:
            r8 = 0
        L3f:
            if (r1 == 0) goto L44
            long r12 = r0.currentLiveOffsetMs
            goto L49
        L44:
            r12 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L49:
            r22 = r2
            if (r1 == 0) goto L52
            long r1 = r0.contentDurationMs
            r16 = r1
            goto L57
        L52:
            r16 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L57:
            if (r21 == 0) goto L6a
            long r0 = r0.contentBufferedPositionMs
            r2 = r5
            r18 = r0
            r0 = r6
            r5 = r10
            r10 = r8
            r9 = r7
            r7 = r14
            r14 = r16
            r16 = r18
        L67:
            r1 = r22
            goto L75
        L6a:
            r2 = r5
            r0 = r6
            r5 = r10
            r10 = r8
            r9 = r7
            r7 = r14
            r14 = r16
            r16 = 0
            goto L67
        L75:
            r0.<init>(r1, r2, r3, r5, r7, r9, r10, r12, r14, r16)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.SessionPositionInfo.filterByAvailableCommands(boolean, boolean):androidx.media3.session.SessionPositionInfo");
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.positionInfo, Boolean.valueOf(this.isPlayingAd)});
    }

    public Bundle toBundle(int r10) {
        Bundle r0 = new Bundle();
        if (r10 >= 3) goto L5;
    L6:
        r0.putBundle(FIELD_POSITION_INFO, this.positionInfo.toBundle(r10));
    L7:
        boolean r2 = this.isPlayingAd;
        if (r2 == false) goto L10;
        r0.putBoolean(FIELD_IS_PLAYING_AD, r2);
    L10:
        long r22 = this.eventTimeMs;
        if (r22 == (-9223372036854775807L)) goto L13;
        r0.putLong(FIELD_EVENT_TIME_MS, r22);
    L13:
        long r23 = this.durationMs;
        if (r23 == (-9223372036854775807L)) goto L17;
        r0.putLong(FIELD_DURATION_MS, r23);
    L17:
        if (r10 >= 3) goto L19;
    L20:
        r0.putLong(FIELD_BUFFERED_POSITION_MS, this.bufferedPositionMs);
    L21:
        int r6 = this.bufferedPercentage;
        if (r6 == 0) goto L24;
        r0.putInt(FIELD_BUFFERED_PERCENTAGE, r6);
    L24:
        long r62 = this.totalBufferedDurationMs;
        if (r62 == 0) goto L27;
        r0.putLong(FIELD_TOTAL_BUFFERED_DURATION_MS, r62);
    L27:
        long r63 = this.currentLiveOffsetMs;
        if (r63 == (-9223372036854775807L)) goto L30;
        r0.putLong(FIELD_CURRENT_LIVE_OFFSET_MS, r63);
    L30:
        long r64 = this.contentDurationMs;
        if (r64 == (-9223372036854775807L)) goto L33;
        r0.putLong(FIELD_CONTENT_DURATION_MS, r64);
    L33:
        if (r10 >= 3) goto L35;
    L38:
        r0.putLong(FIELD_CONTENT_BUFFERED_POSITION_MS, this.contentBufferedPositionMs);
        return r0;
    L35:
        if (this.contentBufferedPositionMs != 0) goto L38;
        return r0;
    L19:
        if (this.bufferedPositionMs == 0) goto L21;
    L5:
        if (DEFAULT_POSITION_INFO.equalsForBundling(this.positionInfo) == true) goto L7;
        goto L6
    }

    public String toString() {
        return "SessionPositionInfo {PositionInfo {mediaItemIndex=" + this.positionInfo.mediaItemIndex + ", periodIndex=" + this.positionInfo.periodIndex + ", positionMs=" + this.positionInfo.positionMs + ", contentPositionMs=" + this.positionInfo.contentPositionMs + ", adGroupIndex=" + this.positionInfo.adGroupIndex + ", adIndexInAdGroup=" + this.positionInfo.adIndexInAdGroup + "}, isPlayingAd=" + this.isPlayingAd + ", eventTimeMs=" + this.eventTimeMs + ", durationMs=" + this.durationMs + ", bufferedPositionMs=" + this.bufferedPositionMs + ", bufferedPercentage=" + this.bufferedPercentage + ", totalBufferedDurationMs=" + this.totalBufferedDurationMs + ", currentLiveOffsetMs=" + this.currentLiveOffsetMs + ", contentDurationMs=" + this.contentDurationMs + ", contentBufferedPositionMs=" + this.contentBufferedPositionMs + "}";
    }
}
