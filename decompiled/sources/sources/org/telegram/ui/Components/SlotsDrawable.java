package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DispatchQueuePoolBackground;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes7.dex */
public final class SlotsDrawable extends RLottieDiceDrawable {
    private Bitmap backgroundBitmapTmp;
    private ReelValue center;
    private final int[] frameCounts;
    private final int[] frameNums;
    private ReelValue left;
    private final RLottieNative[] lottieNatives;
    private boolean playWinAnimation;
    private ReelValue right;
    private final int[] secondFrameCounts;
    private final int[] secondFrameNums;
    private final RLottieNative[] secondLottieNatives;

    public enum ReelValue extends Enum<ReelValue> {
        private static final /* synthetic */ ReelValue[] $VALUES = null;
        public static final ReelValue bar = null;
        public static final ReelValue berries = null;
        public static final ReelValue lemon = null;
        public static final ReelValue seven = null;
        public static final ReelValue sevenWin = null;

        private static /* synthetic */ ReelValue[] $values() {
            return new ReelValue[]{bar, berries, lemon, seven, sevenWin};
        }

        static {
            bar = new ReelValue("bar", 0);
            berries = new ReelValue("berries", 1);
            lemon = new ReelValue("lemon", 2);
            seven = new ReelValue("seven", 3);
            sevenWin = new ReelValue("sevenWin", 4);
            $VALUES = $values();
        }

        ReelValue(String r1, int r2) {
        }

        public static ReelValue valueOf(String r1) {
            return (ReelValue) Enum.valueOf(ReelValue.class, r1);
        }

        public static ReelValue[] values() {
            return (ReelValue[]) $VALUES.clone();
        }
    }

    /* renamed from: $r8$lambda$9KLqIfaDM0m_YCfSseYHuHp-5_Y, reason: not valid java name */
    public static /* synthetic */ void m13035$r8$lambda$9KLqIfaDM0m_YCfSseYHuHp5_Y(TLRPC.Document r2, int r3, MessageObject r4, ChatMessageCell r5, TLRPC.TL_messages_stickerSet r6) {
        String r0 = FileLoader.getAttachFileName(r2);
        DownloadController.getInstance(r3).addLoadingFileObserver(r0, r4, r5);
        FileLoader.getInstance(r3).loadFile(r2, r6, 1, 1);
    }

    /* renamed from: $r8$lambda$G5dlPAGjXU2yBBWolrEEzsB3d-s, reason: not valid java name */
    public static /* synthetic */ void m13036$r8$lambda$G5dlPAGjXU2yBBWolrEEzsB3ds(SlotsDrawable r0) {
        r0.lambda$setBaseDice$2();
    }

    public static /* synthetic */ void $r8$lambda$Jcpv5H3iASqARzXVbbE1hQV6ctI(SlotsDrawable r0, int r1, ChatMessageCell r2) {
        r0.lambda$setBaseDice$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$RH7MJ862DxFYMJhjhGQWhB9Yu5Y(SlotsDrawable r0) {
        r0.lambda$setBaseDice$0();
    }

    public static /* synthetic */ void $r8$lambda$RoHuee07gQabM45_NttKZi4RKCQ(SlotsDrawable r0, boolean r1, int r2, ChatMessageCell r3) {
        r0.lambda$setDiceNumber$8(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$SwaipdUDkyQOAr0vEYM6lOjS19Q(SlotsDrawable r0, TLRPC.TL_messages_stickerSet r1, int r2, MessageObject r3, ChatMessageCell r4) {
        r0.lambda$setBaseDice$4(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$Yx_Hm1dYBCI-XIS7S3Kd43B3xMo, reason: not valid java name */
    public static /* synthetic */ void m13037$r8$lambda$Yx_Hm1dYBCIXIS7S3Kd43B3xMo(SlotsDrawable r0) {
        r0.lambda$setDiceNumber$7();
    }

    public static /* synthetic */ void $r8$lambda$bXcpEeOGI8EctpXQn0QcA9HGTic(SlotsDrawable r0, TLRPC.TL_messages_stickerSet r1, int r2, MessageObject r3, ChatMessageCell r4, boolean r5) {
        r0.lambda$setDiceNumber$9(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$egTlNg9fUo53Gc7N3NSFK35fWTg(ArrayList r2) {
        int r0 = 0;
    L4:
        if (r0 >= r2.size()) goto L6;
        ((RLottieNative) r2.get(r0)).recycle();
        r0 = r0 + 1;
        goto L4
    }

    public static /* synthetic */ void $r8$lambda$ioI6n65Ds3Po212F8eKCB1Qemms(TLRPC.Document r2, int r3, MessageObject r4, ChatMessageCell r5, TLRPC.TL_messages_stickerSet r6) {
        String r0 = FileLoader.getAttachFileName(r2);
        DownloadController.getInstance(r3).addLoadingFileObserver(r0, r4, r5);
        FileLoader.getInstance(r3).loadFile(r2, r6, 1, 1);
    }

    public static /* synthetic */ void $r8$lambda$lRJDDl8N6Jb7xm9T694Sjai9720(SlotsDrawable r0) {
        r0.lambda$setDiceNumber$5();
    }

    public SlotsDrawable(String r1, int r2, int r3) {
        super(r1, r2, r3);
        this.lottieNatives = new RLottieNative[5];
        this.frameCounts = new int[5];
        this.frameNums = new int[5];
        this.secondLottieNatives = new RLottieNative[3];
        this.secondFrameCounts = new int[3];
        this.secondFrameNums = new int[3];
    }

    private ArrayList<RLottieNative> detachNatives(boolean r7) {
        ArrayList<RLottieNative> r0 = new ArrayList();
        int r1 = 0;
        int r2 = 0;
    L3:
        RLottieNative[] r3 = this.lottieNatives;
        if (r2 >= r3.length) goto L13;
        RLottieNative r32 = r3[r2];
        if (r32 == null) goto L12;
        if (r7 == true) goto L9;
    L11:
        r0.add(this.lottieNatives[r2]);
        this.lottieNatives[r2] = null;
        goto L12
    L9:
        if (r32 != this.nativePtr) goto L11;
        this.nativePtr = null;
    L12:
        r2 = r2 + 1;
    L13:
        RLottieNative[] r22 = this.secondLottieNatives;
        if (r1 >= r22.length) goto L23;
        RLottieNative r23 = r22[r1];
        if (r23 == null) goto L22;
        if (r7 == true) goto L19;
    L21:
        r0.add(this.secondLottieNatives[r1]);
        this.secondLottieNatives[r1] = null;
        goto L22
    L19:
        if (r23 != this.secondNativePtr) goto L21;
        this.secondNativePtr = null;
    L22:
        r1 = r1 + 1;
        goto L13
    L23:
        return r0;
    }

    private void init(int r4) {
        int r42 = r4 - 1;
        ReelValue r0 = reelValue(r42 & 3);
        ReelValue r1 = reelValue((r42 >> 2) & 3);
        ReelValue r43 = reelValue(r42 >> 4);
        ReelValue r2 = ReelValue.seven;
        if (r0 != r2) goto L7;
        if (r1 != r2) goto L7;
        if (r43 != r2) goto L7;
        r0 = ReelValue.sevenWin;
        r43 = r0;
        r1 = r43;
    L7:
        this.left = r0;
        this.center = r1;
        this.right = r43;
    }

    private /* synthetic */ void lambda$setBaseDice$0() {
        this.loadingInBackground = false;
        if (this.secondLoadingInBackground == false) goto L5;
        return;
    L5:
        if (this.destroyAfterLoading == false) goto L9;
        recycle(true);
        return;
    }

    private /* synthetic */ void lambda$setBaseDice$2() {
        this.loadingInBackground = false;
    }

    private /* synthetic */ void lambda$setBaseDice$3(int r3, ChatMessageCell r4) {
        this.loadingInBackground = false;
        if (this.secondLoadingInBackground == false) goto L5;
    L8:
        this.nativePtr = this.lottieNatives[0];
        checkChoreographer();
        DownloadController.getInstance(r3).removeLoadingFileObserver(r4);
        scheduleNextGetFrame();
        invalidateInternal();
        return;
    L5:
        if (this.destroyAfterLoading == false) goto L8;
        recycle(true);
    }

    private /* synthetic */ void lambda$setBaseDice$4(TLRPC.TL_messages_stickerSet r12, int r13, MessageObject r14, ChatMessageCell r15) {
        if (this.destroyAfterLoading == false) goto L6;
        AndroidUtilities.runOnUIThread(new SlotsDrawable$$ExternalSyntheticLambda7(this));
        return;
    L6:
        int r1 = 0;
        boolean r2 = false;
    L7:
        RLottieNative[] r3 = this.lottieNatives;
        if (r1 >= r3.length) goto L31;
        if (r3[r1] == null) goto L13;
    L11:
        TLRPC.TL_messages_stickerSet r10 = r12;
        int r7 = r13;
        MessageObject r8 = r14;
        ChatMessageCell r9 = r15;
    L30:
        r1 = r1 + 1;
        r13 = r7;
        r14 = r8;
        r15 = r9;
        r12 = r10;
        goto L7
    L13:
        if (r1 != 0) goto L15;
        int r4 = 1;
    L24:
        if (r4 >= r12.documents.size()) goto L11;
        TLRPC.Document r6 = r12.documents.get(r4);
        String r42 = AndroidUtilities.readRes(FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r6, true), 0);
        if (TextUtils.isEmpty(r42) == false) goto L29;
        r10 = r12;
        r7 = r13;
        r8 = r14;
        r9 = r15;
        AndroidUtilities.runOnUIThread(new SlotsDrawable$$ExternalSyntheticLambda8(r6, r7, r8, r9, r10));
        r2 = true;
        goto L30
    L29:
        r10 = r12;
        r7 = r13;
        r8 = r14;
        r9 = r15;
        this.lottieNatives[r1] = RLottieNative.createFromRawJson(r42, this.metaData);
        this.frameCounts[r1] = this.metaData[0];
        goto L30
    L15:
        if (r1 != 1) goto L17;
        r4 = 8;
        goto L24
    L17:
        r4 = 2;
        if (r1 != 2) goto L21;
        r4 = 14;
        goto L24
    L21:
        if (r1 != 3) goto L24;
        r4 = 20;
        goto L24
    L31:
        int r72 = r13;
        ChatMessageCell r92 = r15;
        if (r2 == false) goto L35;
        AndroidUtilities.runOnUIThread(new SlotsDrawable$$ExternalSyntheticLambda9(this));
        return;
    L35:
        AndroidUtilities.runOnUIThread(new SlotsDrawable$$ExternalSyntheticLambda10(this, r72, r92));
    }

    private /* synthetic */ void lambda$setDiceNumber$5() {
        this.secondLoadingInBackground = false;
        if (this.loadingInBackground == false) goto L5;
        return;
    L5:
        if (this.destroyAfterLoading == false) goto L9;
        recycle(true);
        return;
    }

    private /* synthetic */ void lambda$setDiceNumber$7() {
        this.secondLoadingInBackground = false;
    }

    private /* synthetic */ void lambda$setDiceNumber$8(boolean r3, int r4, ChatMessageCell r5) {
        if (r3 == true) goto L5;
    L9:
        this.secondLoadingInBackground = false;
        if (this.loadingInBackground == false) goto L12;
    L15:
        this.secondNativePtr = this.secondLottieNatives[0];
        DownloadController.getInstance(r4).removeLoadingFileObserver(r5);
        scheduleNextGetFrame();
        invalidateInternal();
        return;
    L12:
        if (this.destroyAfterLoading == false) goto L15;
        recycle(true);
        return;
    L5:
        if (bothRenderingBitmapsAreNull() == false) goto L9;
        if (this.loadFrameTask != null) goto L9;
        this.isDice = 2;
        this.setLastFrame = true;
        goto L9
    }

    private /* synthetic */ void lambda$setDiceNumber$9(TLRPC.TL_messages_stickerSet r15, int r16, MessageObject r17, ChatMessageCell r18, boolean r19) {
        if (this.destroyAfterLoading == false) goto L6;
        AndroidUtilities.runOnUIThread(new SlotsDrawable$$ExternalSyntheticLambda3(this));
        return;
    L6:
        int r1 = 0;
        boolean r2 = false;
    L7:
        RLottieNative[] r3 = this.secondLottieNatives;
        if (r1 >= (r3.length + 2)) goto L76;
        char r4 = 4;
        if (r1 > 2) goto L56;
        if (r3[r1] != null) goto L75;
        if (r1 != 0) goto L28;
        ReelValue r32 = this.left;
        if (r32 != ReelValue.bar) goto L19;
        int r33 = 5;
    L61:
        TLRPC.Document r9 = r15.documents.get(r33);
        String r34 = AndroidUtilities.readRes(FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r9, true), 0);
        if (TextUtils.isEmpty(r34) == false) goto L64;
        AndroidUtilities.runOnUIThread(new SlotsDrawable$$ExternalSyntheticLambda4(r9, r16, r17, r18, r15));
        r2 = true;
        goto L75
    L64:
        RLottieNative r35 = RLottieNative.createFromRawJson(r34, this.metaData);
        if (r1 > 2) goto L67;
        this.secondLottieNatives[r1] = r35;
        this.secondFrameCounts[r1] = this.metaData[0];
        goto L75
    L67:
        RLottieNative[] r5 = this.lottieNatives;
        if (r1 != 3) goto L70;
        char r7 = 0;
    L71:
        r5[r7] = r35;
        int[] r36 = this.frameCounts;
        if (r1 != 3) goto L74;
        r4 = 0;
    L74:
        r36[r4] = this.metaData[0];
        goto L75
    L70:
        r7 = 4;
        goto L71
    L19:
        if (r32 != ReelValue.berries) goto L22;
        r33 = 6;
        goto L61
    L22:
        if (r32 != ReelValue.lemon) goto L25;
        r33 = 7;
        goto L61
    L25:
        if (r32 != ReelValue.seven) goto L27;
        r33 = 4;
        goto L61
    L27:
        r33 = 3;
        goto L61
    L28:
        if (r1 != 1) goto L42;
        ReelValue r37 = this.center;
        if (r37 != ReelValue.bar) goto L33;
        r33 = 11;
        goto L61
    L33:
        if (r37 != ReelValue.berries) goto L36;
        r33 = 12;
        goto L61
    L36:
        if (r37 != ReelValue.lemon) goto L39;
        r33 = 13;
        goto L61
    L39:
        if (r37 != ReelValue.seven) goto L41;
        r33 = 10;
        goto L61
    L41:
        r33 = 9;
        goto L61
    L42:
        ReelValue r38 = this.right;
        if (r38 != ReelValue.bar) goto L46;
        r33 = 17;
        goto L61
    L46:
        if (r38 != ReelValue.berries) goto L49;
        r33 = 18;
        goto L61
    L49:
        if (r38 != ReelValue.lemon) goto L52;
        r33 = 19;
        goto L61
    L52:
        if (r38 != ReelValue.seven) goto L54;
        r33 = 16;
        goto L61
    L54:
        r33 = 15;
    L75:
        r1 = r1 + 1;
        goto L7
    L56:
        if (this.lottieNatives[r1] != null) goto L75;
        if (r1 != 3) goto L60;
        r33 = 1;
        goto L61
    L60:
        r33 = 2;
        goto L61
    L76:
        if (r2 == false) goto L79;
        AndroidUtilities.runOnUIThread(new SlotsDrawable$$ExternalSyntheticLambda5(this));
        return;
    L79:
        AndroidUtilities.runOnUIThread(new SlotsDrawable$$ExternalSyntheticLambda6(this, r19, r16, r18));
    }

    private void recycleInternal(boolean r2) {
        ArrayList<RLottieNative> r1 = detachNatives(r2);
        int r22 = 0;
    L4:
        if (r22 >= r1.size()) goto L6;
        r1.get(r22).recycle();
        r22 = r22 + 1;
        goto L4
    }

    private void recycleInternal(boolean r1, boolean r2) {
        ArrayList<RLottieNative> r0 = detachNatives(r1);
        if (r0.isEmpty() == false) goto L5;
        return;
    L5:
        SlotsDrawable$$ExternalSyntheticLambda2 r12 = new SlotsDrawable$$ExternalSyntheticLambda2(r0);
        if (r2 == false) goto L9;
        DispatchQueuePoolBackground.execute(r12);
        return;
    L9:
        Utilities.globalQueue.postRunnable(r12);
    }

    private ReelValue reelValue(int r1) {
        if (r1 == 0) goto L14;
        if (r1 == 1) goto L12;
        if (r1 == 2) goto L10;
        return ReelValue.seven;
    L10:
        return ReelValue.lemon;
    L12:
        return ReelValue.berries;
    L14:
        return ReelValue.bar;
    }

    @Override // org.telegram.ui.Components.RLottieDiceDrawable, org.telegram.ui.Components.RLottieDrawable
    public void afterLoadFrameImpl() {
    }

    @Override // org.telegram.ui.Components.RLottieDiceDrawable, org.telegram.ui.Components.RLottieDrawable
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

    @Override // org.telegram.ui.Components.RLottieDiceDrawable, org.telegram.ui.Components.RLottieDrawable
    public void decodeFrameFinishedInternal() {
        if (this.destroyWhenDone == false) goto L8;
        checkRunningTasks();
        if (this.loadFrameTask != null) goto L8;
        recycleInternal(false);
    L8:
        if (this.nativePtr == null) goto L10;
    L13:
        this.waitingForNextTask = true;
        if (hasParentView() == true) goto L16;
        stop();
    L16:
        scheduleNextGetFrame();
        return;
    L10:
        if (this.secondNativePtr != null) goto L13;
        recycleResources();
    }

    @Override // org.telegram.ui.Components.RLottieDiceDrawable, org.telegram.ui.Components.RLottieDrawable
    public int loadFrameRunnableImpl(Bitmap r10, boolean r11) {
        if (this.backgroundBitmapTmp == null) goto L85;
    L9:
        int r2 = -1;
        if (this.isDice != 1) goto L30;
        int r112 = 0;
    L12:
        RLottieNative[] r5 = this.lottieNatives;
        if (r112 >= r5.length) goto L81;
        RLottieNative r22 = r5[r112];
        int r52 = this.frameNums[r112];
        Bitmap r6 = this.backgroundBitmapTmp;
        if (r112 != 0) goto L17;
        boolean r7 = true;
    L18:
        r2 = r22.getFrame(r52, r6, r7);
        if (r112 == 0) goto L28;
        int[] r53 = this.frameNums;
        int r62 = r53[r112];
        if ((r62 + 1) >= this.frameCounts[r112]) goto L24;
        r53[r112] = r62 + 1;
        goto L28
    L24:
        if (r112 == 4) goto L28;
        r53[r112] = 0;
        this.nextFrameIsLast = false;
        if (this.secondNativePtr == null) goto L28;
        this.isDice = 2;
    L28:
        r112 = r112 + 1;
        goto L12
    L17:
        r7 = false;
    L81:
        if (r2 >= 0) goto L83;
        return 2;
    L83:
        Utilities.copyBitmaps(this.backgroundBitmapTmp, r10);
        return 1;
    L30:
        if (this.setLastFrame == false) goto L36;
        int r113 = 0;
    L32:
        int[] r54 = this.secondFrameNums;
        if (r113 >= r54.length) goto L36;
        r54[r113] = this.secondFrameCounts[r113] - 1;
        r113 = r113 + 1;
    L36:
        if (this.playWinAnimation == false) goto L41;
        int[] r114 = this.frameNums;
        int r55 = r114[0];
        if ((r55 + 1) >= this.frameCounts[0]) goto L40;
        r114[0] = r55 + 1;
        goto L41
    L40:
        r114[0] = -1;
    L41:
        this.lottieNatives[0].getFrame(Math.max(this.frameNums[0], 0), this.backgroundBitmapTmp, true);
        int r115 = 0;
    L42:
        RLottieNative[] r56 = this.secondLottieNatives;
        if (r115 >= r56.length) goto L55;
        RLottieNative r57 = r56[r115];
        int r63 = this.secondFrameNums[r115];
        if (r63 >= 0) goto L48;
        r63 = this.secondFrameCounts[r115] - 1;
    L48:
        r57.getFrame(r63, this.backgroundBitmapTmp, false);
        if (this.nextFrameIsLast == true) goto L54;
        int[] r58 = this.secondFrameNums;
        int r64 = r58[r115];
        if ((r64 + 1) >= this.secondFrameCounts[r115]) goto L53;
        r58[r115] = r64 + 1;
        goto L54
    L53:
        r58[r115] = -1;
    L54:
        r115 = r115 + 1;
        goto L42
    L55:
        int r116 = this.lottieNatives[4].getFrame(this.frameNums[4], this.backgroundBitmapTmp, false);
        int[] r59 = this.frameNums;
        int r65 = r59[4];
        if ((r65 + 1) >= this.frameCounts[4]) goto L58;
        r59[4] = r65 + 1;
    L58:
        int[] r1 = this.secondFrameNums;
        if (r1[0] == (-1)) goto L61;
    L65:
        ReelValue r12 = this.left;
        ReelValue r510 = this.right;
        if (r12 == r510) goto L68;
    L79:
        this.frameNums[0] = -1;
    L80:
        r2 = r116;
        goto L81
    L68:
        if (r510 != this.center) goto L79;
        if (this.secondFrameNums[0] != (this.secondFrameCounts[0] - 100)) goto L80;
        this.playWinAnimation = true;
        if (r12 != ReelValue.sevenWin) goto L80;
        WeakReference<Runnable> r13 = this.onFinishCallback;
        if (r13 != null) goto L76;
        Runnable r14 = null;
    L77:
        if (r14 == null) goto L80;
        AndroidUtilities.runOnUIThread(r14);
        goto L80
    L76:
        r14 = r13.get();
        goto L77
    L61:
        if (r1[1] != (-1)) goto L65;
        if (r1[2] != (-1)) goto L65;
        this.nextFrameIsLast = true;
        this.autoRepeatPlayCount++;
        goto L65
    L85:
        this.backgroundBitmapTmp = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        FileLog.e(th);
        return 2;
    }

    @Override // org.telegram.ui.Components.RLottieDiceDrawable, org.telegram.ui.Components.RLottieDrawable
    public void recycle(boolean r3) {
        this.isRunning = false;
        this.isRecycled = true;
        checkRunningTasks();
        checkChoreographer();
        if (this.loadingInBackground == false) goto L5;
    L13:
        this.destroyAfterLoading = true;
        return;
    L5:
        if (this.secondLoadingInBackground == true) goto L13;
        if (this.loadFrameTask != null) goto L11;
        recycleInternal(true, r3);
        recycleResources();
        return;
    L11:
        this.destroyWhenDone = true;
    }

    public boolean setBaseDice(ChatMessageCell r9, TLRPC.TL_messages_stickerSet r10) {
        if (this.nativePtr == null) goto L5;
    L8:
        return true;
    L5:
        if (this.loadingInBackground == true) goto L8;
        this.loadingInBackground = true;
        MessageObject r6 = r9.getMessageObject();
        int r5 = r9.getMessageObject().currentAccount;
        Utilities.globalQueue.postRunnable(new SlotsDrawable$$ExternalSyntheticLambda0(this, r10, r5, r6, r9));
        goto L8
    }

    public boolean setDiceNumber(ChatMessageCell r10, int r11, TLRPC.TL_messages_stickerSet r12, boolean r13) {
        if (this.secondNativePtr == null) goto L5;
    L8:
        return true;
    L5:
        if (this.secondLoadingInBackground == true) goto L8;
        init(r11);
        MessageObject r6 = r10.getMessageObject();
        int r5 = r10.getMessageObject().currentAccount;
        this.secondLoadingInBackground = true;
        Utilities.globalQueue.postRunnable(new SlotsDrawable$$ExternalSyntheticLambda1(this, r12, r5, r6, r10, r13));
        goto L8
    }
}
