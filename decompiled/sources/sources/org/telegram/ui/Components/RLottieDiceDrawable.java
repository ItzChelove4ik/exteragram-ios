package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.text.TextUtils;
import java.io.File;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DispatchQueuePoolBackground;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.BitmapsCache;

/* loaded from: classes7.dex */
public class RLottieDiceDrawable extends RLottieDrawable {
    protected boolean destroyAfterLoading;
    private int diceSwitchFramesCount;
    protected boolean loadingInBackground;
    private int secondFramesCount;
    protected boolean secondLoadingInBackground;
    protected volatile RLottieNative secondNativePtr;
    protected volatile boolean setLastFrame;

    /* renamed from: $r8$lambda$BgEnD-QhMLTkJ_vAxqtg762UBPE, reason: not valid java name */
    public static /* synthetic */ void m12284$r8$lambda$BgEnDQhMLTkJ_vAxqtg762UBPE(RLottieDiceDrawable r0, String r1) {
        r0.lambda$setBaseDice$4(r1);
    }

    /* renamed from: $r8$lambda$QSWgcJBp2uKr-yqUHak9nXgNYA4, reason: not valid java name */
    public static /* synthetic */ void m12285$r8$lambda$QSWgcJBp2uKryqUHak9nXgNYA4(RLottieDiceDrawable r0, int r1) {
        r0.lambda$setDiceNumber$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$TxYrY2NMfU5GJAEGHfSR6XkyoA4(RLottieDiceDrawable r0, String r1) {
        r0.lambda$setDiceNumber$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$WKg1LNg5D9gF7ICIvxT06PSrCiw(RLottieDiceDrawable r0) {
        r0.lambda$setDiceNumber$0();
    }

    public static /* synthetic */ void $r8$lambda$dzkidqaevrZJQv_c80YElxZyjkM(RLottieNative r0, RLottieNative r1) {
        if (r0 == null) goto L4;
        r0.recycle();
    L4:
        if (r1 == null) goto L7;
        r1.recycle();
        return;
    }

    /* renamed from: $r8$lambda$v8Gx_P06-_a3zPbHHiuSxLcTsgY, reason: not valid java name */
    public static /* synthetic */ void m12286$r8$lambda$v8Gx_P06_a3zPbHHiuSxLcTsgY(RLottieDiceDrawable r0) {
        r0.lambda$setBaseDice$3();
    }

    public RLottieDiceDrawable(String r1, int r2, int r3) {
        super(r2, r3);
        this.diceSwitchFramesCount = -1;
        this.isDice = 1;
        if ("🎲".equals(r1) == false) goto L6;
        String r12 = AndroidUtilities.readRes(R.raw.diceloop);
        this.diceSwitchFramesCount = 60;
    L9:
        getPaint().setFlags(2);
        if (TextUtils.isEmpty(r12) == false) goto L12;
        return;
    L12:
        this.nativePtr = RLottieNative.createFromRawJson(r12, this.metaData);
        return;
    L6:
        if ("🎯".equals(r1) == false) goto L8;
        r12 = AndroidUtilities.readRes(R.raw.dartloop);
        goto L9
    L8:
        r12 = null;
        goto L9
    }

    private /* synthetic */ void lambda$setBaseDice$3() {
        this.loadingInBackground = false;
        if (this.secondLoadingInBackground == false) goto L5;
    L8:
        checkChoreographer();
        scheduleNextGetFrame();
        invalidateInternal();
        return;
    L5:
        if (this.destroyAfterLoading == false) goto L8;
        recycle(true);
    }

    private /* synthetic */ void lambda$setBaseDice$4(String r2) {
        this.nativePtr = RLottieNative.createFromRawJson(r2, this.metaData);
        AndroidUtilities.runOnUIThread(new RLottieDiceDrawable$$ExternalSyntheticLambda3(this));
    }

    private /* synthetic */ void lambda$setDiceNumber$0() {
        this.secondLoadingInBackground = false;
        if (this.loadingInBackground == false) goto L5;
        return;
    L5:
        if (this.destroyAfterLoading == false) goto L9;
        recycle(true);
        return;
    }

    private /* synthetic */ void lambda$setDiceNumber$1(int r2) {
        this.secondLoadingInBackground = false;
        if (this.destroyAfterLoading == false) goto L6;
        recycle(true);
        return;
    L6:
        this.secondFramesCount = r2;
        scheduleNextGetFrame();
        invalidateInternal();
    }

    private /* synthetic */ void lambda$setDiceNumber$2(String r2) {
        if (this.destroyAfterLoading == false) goto L6;
        AndroidUtilities.runOnUIThread(new RLottieDiceDrawable$$ExternalSyntheticLambda4(this));
        return;
    L6:
        this.secondNativePtr = RLottieNative.createFromRawJson(r2);
        if (this.secondNativePtr == null) goto L9;
        int r22 = this.secondNativePtr.getFrameCount();
    L11:
        if (this.secondNativePtr == null) goto L13;
        this.secondNativePtr.getFps();
    L13:
        AndroidUtilities.runOnUIThread(new RLottieDiceDrawable$$ExternalSyntheticLambda5(this, r22));
        return;
    L9:
        r22 = 0;
        goto L11
    }

    @Override // org.telegram.ui.Components.RLottieDrawable
    public void afterLoadFrameImpl() {
        int r0 = this.isDice;
        if (r0 != 1) goto L17;
        int r02 = this.currentFrame;
        int r3 = r02 + 1;
        int r4 = this.diceSwitchFramesCount;
        if (r4 != (-1)) goto L7;
        r4 = this.metaData[0];
    L7:
        if (r3 >= r4) goto L10;
        this.currentFrame = r02 + 1;
        return;
    L10:
        this.currentFrame = 0;
        this.nextFrameIsLast = false;
        if (this.secondNativePtr == null) goto L14;
        this.isDice = 2;
    L14:
        if (this.resetVibrationAfterRestart == false) goto L24;
        this.vibrationPattern = null;
        this.resetVibrationAfterRestart = false;
        return;
    L24:
        return;
    L17:
        if (r0 != 2) goto L25;
        int r03 = this.currentFrame;
        if ((r03 + 1) >= this.secondFramesCount) goto L22;
        this.currentFrame = r03 + 1;
        return;
    L22:
        this.nextFrameIsLast = true;
        this.autoRepeatPlayCount++;
        return;
    }

    @Override // org.telegram.ui.Components.RLottieDrawable
    public int beforeLoadFrameImpl() {
        if (this.isRecycled == false) goto L7;
        return 3;
    L7:
        if (this.nativePtr != null) goto L9;
    L15:
        return 2;
    L9:
        if (this.isDice == 2) goto L11;
        return 1;
    L11:
        if (this.secondNativePtr == null) goto L15;
        return 1;
    }

    @Override // org.telegram.ui.Components.RLottieDrawable
    public void decodeFrameFinishedInternal() {
        if (this.destroyWhenDone == false) goto L10;
        checkRunningTasks();
        if (this.loadFrameTask != null) goto L10;
        if (this.nativePtr == null) goto L10;
        recycleNativePtr(true);
    L10:
        if (this.nativePtr == null) goto L12;
    L17:
        this.waitingForNextTask = true;
        if (hasParentView() == true) goto L21;
        stop();
    L21:
        if (this.isRunning == false) goto L24;
        scheduleNextGetFrame();
        return;
    L24:
        return;
    L12:
        if (this.secondNativePtr != null) goto L17;
        if (this.bitmapsCache != null) goto L17;
        recycleResources();
    }

    public boolean hasBaseDice() {
        if (this.nativePtr == null) goto L5;
        return true;
    L5:
        if (this.loadingInBackground == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // org.telegram.ui.Components.RLottieDrawable
    public boolean ignoreScheduleNextGetFrame() {
        return this.loadingInBackground;
    }

    public boolean isDiceRevealed() {
        int r0 = this.isDice;
        if (r0 != 1) goto L6;
        return false;
    L6:
        if (r0 == 2) goto L8;
    L16:
        return false;
    L8:
        if (this.setLastFrame == false) goto L10;
        return true;
    L10:
        float r02 = getProgress();
        if (this.secondNativePtr == null) goto L14;
        r02 = this.currentFrame / this.secondFramesCount;
    L14:
        if (r02 <= 0.95f) goto L16;
        return true;
    }

    @Override // org.telegram.ui.Components.RLottieDrawable
    public boolean isHeavyDrawable() {
        return false;
    }

    @Override // org.telegram.ui.Components.RLottieDrawable
    public int loadFrameRunnableImpl(Bitmap r5, boolean r6) {
        int r0 = this.isDice;
        if (r0 != 1) goto L5;
        RLottieNative r02 = this.nativePtr;
    L11:
        if (r02.getFrame(this.currentFrame, r5, r6) >= 0) goto L13;
        return 2;
    L13:
        return 1;
    L5:
        if (r0 != 2) goto L9;
        r02 = this.secondNativePtr;
        if (this.setLastFrame == false) goto L11;
        this.currentFrame = this.secondFramesCount - 1;
        goto L11
    L9:
        r02 = this.nativePtr;
        goto L11
    }

    @Override // org.telegram.ui.Components.RLottieDrawable
    public void recycle(boolean r3) {
        this.isRunning = false;
        this.isRecycled = true;
        checkRunningTasks();
        checkChoreographer();
        if (this.loadingInBackground == false) goto L5;
    L18:
        this.destroyAfterLoading = true;
        return;
    L5:
        if (this.secondLoadingInBackground == true) goto L18;
        if (this.loadFrameTask == null) goto L10;
    L16:
        this.destroyWhenDone = true;
        return;
    L10:
        if (this.generatingCache == true) goto L16;
        recycleNativePtr(r3);
        BitmapsCache r32 = this.bitmapsCache;
        if (r32 == null) goto L14;
        r32.recycle();
        this.bitmapsCache = null;
    L14:
        recycleResources();
    }

    @Override // org.telegram.ui.Components.RLottieDrawable
    public void recycleNativePtr(boolean r4) {
        RLottieNative r0 = this.nativePtr;
        RLottieNative r1 = this.secondNativePtr;
        this.nativePtr = null;
        this.secondNativePtr = null;
        if (r0 != null) goto L7;
        if (r1 != null) goto L7;
        return;
    L7:
        RLottieDiceDrawable$$ExternalSyntheticLambda0 r3 = new RLottieDiceDrawable$$ExternalSyntheticLambda0(r0, r1);
        if (r4 == false) goto L11;
        DispatchQueuePoolBackground.execute(r3);
        return;
    L11:
        Utilities.globalQueue.postRunnable(r3);
    }

    public boolean setBaseDice(File r4) {
        if (this.nativePtr == null) goto L5;
    L12:
        return true;
    L5:
        if (this.loadingInBackground == true) goto L12;
        String r42 = AndroidUtilities.readRes(r4);
        if (TextUtils.isEmpty(r42) == false) goto L11;
        return false;
    L11:
        this.loadingInBackground = true;
        Utilities.globalQueue.postRunnable(new RLottieDiceDrawable$$ExternalSyntheticLambda2(this, r42));
        goto L12
    }

    public boolean setDiceNumber(File r3, boolean r4) {
        if (this.secondNativePtr == null) goto L5;
    L18:
        return true;
    L5:
        if (this.secondLoadingInBackground == true) goto L18;
        String r32 = AndroidUtilities.readRes(r3);
        if (TextUtils.isEmpty(r32) == false) goto L11;
        return false;
    L11:
        if (r4 == true) goto L13;
    L17:
        this.secondLoadingInBackground = true;
        Utilities.globalQueue.postRunnable(new RLottieDiceDrawable$$ExternalSyntheticLambda1(this, r32));
        goto L18
    L13:
        if (bothRenderingBitmapsAreNull() == false) goto L17;
        if (this.loadFrameTask != null) goto L17;
        this.isDice = 2;
        this.setLastFrame = true;
        goto L17
    }
}
