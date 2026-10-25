package org.telegram.messenger.camera;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.media.MediaMetadataRetriever;
import android.media.MediaRecorder;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.UByte;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Bitmaps;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.SerializedData;

/* loaded from: classes5.dex */
public class CameraController implements MediaRecorder.OnInfoListener {
    private static final int CORE_POOL_SIZE = 1;
    private static volatile CameraController Instance = null;
    private static final int KEEP_ALIVE_SECONDS = 60;
    private static final int MAX_POOL_SIZE = 1;
    protected volatile ArrayList<CameraInfo> cameraInfos;
    private boolean cameraInitied;
    private ArrayList<ErrorCallback> errorCallbacks;
    private boolean loadingCameras;
    private boolean mirrorRecorderVideo;
    private ArrayList<Runnable> onFinishCameraInitRunnables;
    private VideoTakeCallback onVideoTakeCallback;
    private String recordedFile;
    private MediaRecorder recorder;
    ICameraView recordingCurrentCameraView;
    protected ThreadPoolExecutor threadPool;

    public static class CompareSizesByArea implements Comparator<Size> {
        public CompareSizesByArea() {
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Size r1, Size r2) {
            return compare2(r1, r2);
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Size r5, Size r6) {
            return Long.signum((r5.getWidth() * r5.getHeight()) - (r6.getWidth() * r6.getHeight()));
        }
    }

    public interface ErrorCallback {
        default void onError(int r1, Camera r2, CameraSessionWrapper r3) {
        }
    }

    public interface ICameraView {
        boolean startRecording(File r1, Runnable r2);

        void stopRecording();
    }

    public interface VideoTakeCallback {
        void onFinishVideoRecording(String r1, long r2);
    }

    public static /* synthetic */ void $r8$lambda$437fO9OCzo18PKy0iSoMsvoIheU(CameraController r0, Runnable r1) {
        r0.lambda$initCamera$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$7d5QkPDHPq6hSAnAldlHtcSdRqw(CameraController r0, CameraSession r1) {
        r0.lambda$stopPreview$8(r1);
    }

    /* renamed from: $r8$lambda$82Zm6W5ijX0-hTIaCG2bRV9afAU, reason: not valid java name */
    public static /* synthetic */ void m5065$r8$lambda$82Zm6W5ijX0hTIaCG2bRV9afAU(CameraController r0, Object r1, boolean r2, boolean r3) {
        r0.lambda$stopVideoRecording$17(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$9SwEbrM-DA-Q1JmesSwnxXdTkzE, reason: not valid java name */
    public static /* synthetic */ void m5066$r8$lambda$9SwEbrMDAQ1JmesSwnxXdTkzE(CameraController r0, Camera r1, CameraSession r2, boolean r3, File r4, CameraInfo r5, VideoTakeCallback r6, Runnable r7) {
        r0.lambda$recordVideo$14(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$9kHymWf0mUGY67vDhLE1WCXqQDM(File r9, CameraInfo r10, boolean r11, boolean r12, Utilities.Callback r13, byte[] r14, Camera r15) {
        int r152 = (int) (AndroidUtilities.getPhotoSize() / AndroidUtilities.density);
        String r153 = String.format(Locale.US, "%s@%d_%d", new Object[]{Utilities.MD5(r9.getAbsolutePath()), Integer.valueOf(r152), Integer.valueOf(r152)});
        int r1 = 0;
        BitmapFactory.Options r0 = new BitmapFactory.Options();     // Catch: Throwable -> L5
        r0.inJustDecodeBounds = true;     // Catch: Throwable -> L5
        BitmapFactory.decodeByteArray(r14, 0, r14.length, r0);     // Catch: Throwable -> L5
        r0.inJustDecodeBounds = false;     // Catch: Throwable -> L5
        r0.inPurgeable = true;     // Catch: Throwable -> L5
        Bitmap r02 = BitmapFactory.decodeByteArray(r14, 0, r14.length, r0);     // Catch: Throwable -> L5
    L4:
        Bitmap r2 = r02;
        int r3 = getOrientation(r14);     // Catch: Exception -> L36
    L30:
        e = move-exception;
        Exception r92 = e;
        r1 = r3;
    L38:
        FileLog.e(r92);
        r3 = r1;
    L39:
        if (r13 == null) goto L53;
        r13.run(Integer.valueOf(r3));
        return;
    L53:
        return;
    L9:
        if (r10.frontCamera == 0) goto L32;
        if (r11 == false) goto L32;
        Matrix r7 = new Matrix();     // Catch: Throwable -> L17
        if (r12 == true) goto L44;
        if (r3 == (-1)) goto L44;
        r7.setRotate(r3);     // Catch: Throwable -> L17
    L44:
        r7.postScale(-1.0f, 1.0f);     // Catch: Throwable -> L23
        Bitmap r102 = Bitmaps.createBitmap(r2, 0, 0, r2.getWidth(), r2.getHeight(), r7, true);     // Catch: Throwable -> L23
        if (r102 == r2) goto L25;
        r2.recycle();     // Catch: Throwable -> L23
    L25:
        FileOutputStream r112 = new FileOutputStream(r9);     // Catch: Throwable -> L23
        r102.compress(Bitmap.CompressFormat.JPEG, 80, r112);     // Catch: Throwable -> L23
        r112.flush();     // Catch: Throwable -> L23
        r112.getFD().sync();     // Catch: Throwable -> L23
        r112.close();     // Catch: Throwable -> L23
        ImageLoader.getInstance().putImageToCache(new BitmapDrawable(r102), r153, false);     // Catch: Throwable -> L23
        if (r13 == null) goto L52;
        r13.run(0);     // Catch: Throwable -> L23
        return;
    L52:
        return;
    L23:
        th = move-exception;
        Throwable r103 = th;
        r3 = 0;
    L29:
        FileLog.e(r103);     // Catch: Exception -> L30
    L17:
        th = move-exception;
        r103 = th;
    L32:
        FileOutputStream r104 = new FileOutputStream(r9);     // Catch: Exception -> L30
        r104.write(r14);     // Catch: Exception -> L30
        r104.flush();     // Catch: Exception -> L30
        r104.getFD().sync();     // Catch: Exception -> L30
        r104.close();     // Catch: Exception -> L30
        if (r2 == null) goto L39;
        ImageLoader.getInstance().putImageToCache(new BitmapDrawable(r2), r153, false);     // Catch: Exception -> L30
    L36:
        e = move-exception;
        r92 = e;
    L5:
        th = move-exception;
        FileLog.e(th);
        r02 = null;
        goto L4
    }

    public static /* synthetic */ void $r8$lambda$GD2MGwwGVt9YKCyLAuCAPh2FBnE(CameraController r0, Object r1, ICameraView r2, File r3, boolean r4, Runnable r5) {
        r0.lambda$recordVideo$13(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$LbbR0Pva4cugAOPQxOB2ZxZD7ds(CameraController r0, CameraSession r1, int r2, Camera r3) {
        r0.lambda$getErrorListener$18(r1, r2, r3);
    }

    public static /* synthetic */ int $r8$lambda$MOi9syO4BMDvrHgWxMTZxUyd6mU(Size r2, Size r3) {
        int r0 = r2.mWidth;
        int r1 = r3.mWidth;
        if (r0 < r1) goto L16;
        if (r0 > r1) goto L17;
        int r22 = r2.mHeight;
        int r32 = r3.mHeight;
        if (r22 >= r32) goto L11;
        return 1;
    L11:
        if (r22 <= r32) goto L14;
        return -1;
    L14:
        return 0;
    L17:
        return -1;
    L16:
        return 1;
    }

    public static /* synthetic */ void $r8$lambda$O8qpmpFVdNQ0u7vGPmox7u0dJK4(CameraController r0, File r1, Bitmap r2, long r3) {
        r0.lambda$finishRecordingVideo$15(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$QNTk6U7tvngubPRoz2y6e4VCuDE(CameraController r0, boolean r1, Runnable r2) {
        r0.lambda$initCamera$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$X7Tnq1xluvV3PgXwVBGovGNiTns(Camera r1, CameraSession r2) {
        Camera.Parameters r0 = r1.getParameters();     // Catch: Exception -> L4
        r0.setFlashMode(r2.getCurrentFlashMode());     // Catch: Exception -> L4
        r1.setParameters(r0);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$XODWAWSQ9G_8VfVA1G31_hllnYo(Runnable r1, CameraSession r2, CountDownLatch r3, Runnable r4) {
        if (r1 == null) goto L4;
        r1.run();
    L4:
        Camera r12 = r2.cameraInfo.camera;
        if (r12 != null) goto L21;
    L16:
        if (r3 == null) goto L18;
        r3.countDown();
    L18:
        if (r4 == null) goto L25;
        AndroidUtilities.runOnUIThread(r4);
        return;
    L25:
        return;
    L21:
        r12.stopPreview();     // Catch: Exception -> L9
        r2.cameraInfo.camera.setPreviewCallbackWithBuffer(null);     // Catch: Exception -> L9
    L23:
        r2.cameraInfo.camera.release();     // Catch: Exception -> L13
    L15:
        r2.cameraInfo.camera = null;
    L13:
        e = move-exception;
        FileLog.e(e);
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L23
    }

    public static /* synthetic */ void $r8$lambda$dLLxaCjfZdsrLvpEN4LZ9BSVMr4(CameraSession r7, Runnable r8, SurfaceTexture r9, Runnable r10) {
        Camera r0 = r7.cameraInfo.camera;
    L6:
        e = move-exception;
        r7.cameraInfo.camera = null;
        if (r0 == null) goto L43;
        r0.release();
    L43:
        FileLog.e(e);
        return;
    L4:
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        FileLog.d("start creating round camera session");     // Catch: Exception -> L6
    L8:
        if (r0 != null) goto L10;
        CameraInfo r1 = r7.cameraInfo;     // Catch: Exception -> L6
        Camera r2 = Camera.open(r1.cameraId);     // Catch: Exception -> L6
        r1.camera = r2;     // Catch: Exception -> L6
        r0 = r2;
    L10:
        Camera.Parameters r12 = r0.getParameters();     // Catch: Exception -> L6
        List<String> r22 = r12.getSupportedFlashModes();     // Catch: Exception -> L6
        r7.availableFlashModes.clear();     // Catch: Exception -> L6
        if (r22 == null) goto L30;
        int r4 = 0;
    L14:
        if (r4 >= r22.size()) goto L24;
        String r5 = r22.get(r4);     // Catch: Exception -> L6
        if (r5.equals("off") == false) goto L18;
    L21:
        r7.availableFlashModes.add(r5);     // Catch: Exception -> L6
    L22:
        r4 = r4 + 1;     // Catch: Exception -> L6
        goto L14
    L18:
        if (r5.equals("on") == true) goto L21;
        if (r5.equals("auto") == false) goto L22;
    L24:
        if (TextUtils.equals(r7.getCurrentFlashMode(), r12.getFlashMode()) == true) goto L26;
    L29:
        r7.checkFlashMode(r7.availableFlashModes.get(0));     // Catch: Exception -> L6
        goto L30
    L26:
        if (r7.availableFlashModes.contains(r7.getCurrentFlashMode()) == false) goto L29;
        r7.checkFlashMode(r7.getCurrentFlashMode());     // Catch: Exception -> L6
    L30:
        r7.configureRoundCamera(true);     // Catch: Exception -> L6
        if (r8 == null) goto L33;
        r8.run();     // Catch: Exception -> L6
    L33:
        r0.setPreviewTexture(r9);     // Catch: Exception -> L6
        r0.startPreview();     // Catch: Exception -> L6
        if (r10 == null) goto L37;
        AndroidUtilities.runOnUIThread(r10);     // Catch: Exception -> L6
    L37:
        if (BuildVars.LOGS_ENABLED == false) goto L50;
        FileLog.d("round camera session created");     // Catch: Exception -> L6
        return;
    }

    public static /* synthetic */ void $r8$lambda$gV2xAi88Uy8E4VI_aywdF6DTLJ0(CameraController r0) {
        r0.lambda$initCamera$1();
    }

    public static /* synthetic */ void $r8$lambda$hBeJHhKXTRupnt6dhCc7KfxDfok(CameraController r0, CameraSession r1) {
        r0.lambda$startPreview$7(r1);
    }

    /* renamed from: $r8$lambda$o0yPcd6-B5AxDK_oijYB8VYTGyg, reason: not valid java name */
    public static /* synthetic */ void m5067$r8$lambda$o0yPcd6B5AxDK_oijYB8VYTGyg(CameraController r0, boolean r1, Exception r2, Runnable r3) {
        r0.lambda$initCamera$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$omtOBrCuyNM31q26h3MmkjJRVLE(CameraController r0, ICameraView r1, File r2, boolean r3, Runnable r4) {
        r0.lambda$recordVideo$12(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$xHg0vd8uzfzzok83XK8i682WhWg(CameraController r0, CameraSession r1, Runnable r2, SurfaceTexture r3, Runnable r4) {
        r0.lambda$open$10(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$yuaOxDFy9GfARbOTU3thyPSN3Ik(CameraController r0, boolean r1) {
        r0.lambda$recordVideo$11(r1);
    }

    static {
    }

    public CameraController() {
        this.onFinishCameraInitRunnables = new ArrayList();
        LinkedBlockingQueue r7 = new LinkedBlockingQueue();
        this.threadPool = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, r7);
    }

    public static Size chooseOptimalSize(List<Size> r7, int r8, int r9, Size r10, boolean r11) {
        ArrayList r0 = new ArrayList(r7.size());
        ArrayList r1 = new ArrayList(r7.size());
        int r2 = r10.getWidth();
        int r102 = r10.getHeight();
        int r3 = 0;
    L4:
        if (r3 >= r7.size()) goto L24;
        Size r4 = r7.get(r3);
        if (r11 == false) goto L13;
        if (r4.getHeight() > r9) goto L22;
        if (r4.getWidth() <= r8) goto L13;
    L22:
        r3 = r3 + 1;
    L13:
        if (r4.getHeight() != ((r4.getWidth() * r102) / r2)) goto L20;
        if (r4.getWidth() < r8) goto L20;
        if (r4.getHeight() < r9) goto L20;
        r0.add(r4);
    L20:
        if ((r4.getHeight() * r4.getWidth()) > ((r8 * r9) * 4)) goto L22;
        r1.add(r4);
        goto L22
    L24:
        if (r0.size() <= 0) goto L28;
        return (Size) Collections.min(r0, new CompareSizesByArea());
    L28:
        if (r1.size() <= 0) goto L32;
        return (Size) Collections.min(r1, new CompareSizesByArea());
    L32:
        return (Size) Collections.max(r7, new CompareSizesByArea());
    }

    private void finishRecordingVideo(boolean r11) {
        MediaMetadataRetriever r1 = null;
        FileOutputStream r12 = null;
        long r2 = 0;
        MediaMetadataRetriever r4 = new MediaMetadataRetriever();     // Catch: Throwable -> L17 Exception -> L19
        r4.setDataSource(this.recordedFile);     // Catch: Throwable -> L8 Exception -> L10
        String r0 = r4.extractMetadata(9);     // Catch: Throwable -> L8 Exception -> L10
        if (r0 == null) goto L69;
        r2 = Long.parseLong(r0);     // Catch: Throwable -> L8 Exception -> L10
    L69:
        r4.release();     // Catch: Exception -> L14
    L16:
        long r8 = r2;
        if (r11 == false) goto L45;
        Bitmap r112 = SendMessagesHelper.createVideoThumbnail(this.recordedFile, 1);
        if (this.mirrorRecorderVideo == false) goto L28;
        Bitmap r02 = Bitmap.createBitmap(r112.getWidth(), r112.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas r22 = new Canvas(r02);
        r22.scale(-1.0f, 1.0f, r02.getWidth() / 2, r02.getHeight() / 2);
        r22.drawBitmap(r112, 0.0f, 0.0f, null);
        r112.recycle();
        r112 = r02;
    L28:
        File r23 = new File(FileLoader.getDirectory(4), "-2147483648_" + SharedConfig.getLastLocalId() + ".jpg");
        FileOutputStream r3 = new FileOutputStream(r23);     // Catch: Throwable -> L35
        r112.compress(Bitmap.CompressFormat.JPEG, 87, r3);     // Catch: Throwable -> L33
        r3.close();     // Catch: Throwable -> L54
    L56:
    L33:
        th = th;
        r12 = r3;
    L61:
        FileLog.e(th);     // Catch: Throwable -> L40
        if (r12 == null) goto L39;
        r12.close();     // Catch: Throwable -> L54
    L40:
        th = move-exception;
        if (r12 == null) goto L75;
        r12.close();     // Catch: Throwable -> L55
        throw th;
    L76:
        throw th;
    L75:
        throw th;
    L35:
        th = th;
    L39:
        Bitmap r7 = r112;
        File r6 = r23;
    L46:
        SharedConfig.saveConfig();
        AndroidUtilities.runOnUIThread(new CameraController$$ExternalSyntheticLambda8(this, r6, r7, r8));
        return;
    L45:
        r6 = null;
        r7 = null;
    L14:
        e = move-exception;
        FileLog.e(e);
    L10:
        e = e;
    L21:
        FileLog.e(e);     // Catch: Throwable -> L8
        if (r4 == null) goto L16;
    L8:
        th = move-exception;
        Throwable r10 = th;
        r1 = r4;
    L48:
        if (r1 == null) goto L77;
        r1.release();     // Catch: Exception -> L51
        throw r10;
    L51:
        e = move-exception;
        FileLog.e(e);
        throw r10;
    L77:
        throw r10;
    L19:
        e = e;
        r4 = null;
    L17:
        th = move-exception;
        r10 = th;
        goto L48
    }

    public static CameraController getInstance() {
        CameraController r0 = Instance;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(CameraController.class);
        CameraController r02 = Instance;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        r02 = new CameraController();     // Catch: Throwable -> L9
        Instance = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(CameraController.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    private static int getOrientation(byte[] r11) {
        if (r11 != null) goto L5;
        return -1;
    L5:
        int r2 = 0;
    L7:
        if ((r2 + 3) >= r11.length) goto L37;
        int r3 = r2 + 1;
        if ((r11[r2] & UByte.MAX_VALUE) != 255) goto L36;
        int r4 = r11[r3] & UByte.MAX_VALUE;
        if (r4 == 255) goto L12;
        r3 = r2 + 2;
        if (r4 == 216) goto L12;
        if (r4 == 1) goto L12;
        if (r4 == 217) goto L36;
        if (r4 == 218) goto L36;
        int r9 = pack(r11, r3, 2, false);
        if (r9 < 2) goto L35;
        r3 = r3 + r9;
        if (r3 > r11.length) goto L35;
        if (r4 != 225) goto L12;
        if (r9 < 8) goto L12;
        if (pack(r11, r2 + 4, 4, false) != 1165519206) goto L12;
        if (pack(r11, r2 + 8, 2, false) != 0) goto L12;
        r2 = r2 + 10;
        int r92 = r9 - 8;
    L38:
        if (r92 <= 8) goto L74;
        int r32 = pack(r11, r2, 4, false);
        if (r32 != 1229531648) goto L42;
    L44:
        if (r32 != 1229531648) goto L46;
        boolean r33 = true;
    L47:
        int r42 = pack(r11, r2 + 4, 4, r33) + 2;
        if (r42 < 10) goto L74;
        if (r42 > r92) goto L74;
        int r22 = r2 + r42;
        int r93 = r92 - r42;
        int r43 = pack(r11, r22 - 2, 2, r33);
    L52:
        int r6 = r43 - 1;
        if (r43 <= 0) goto L74;
        if (r93 < 12) goto L74;
        if (pack(r11, r22, 2, r33) == 274) goto L58;
        r22 = r22 + 12;
        r93 = r93 - 12;
        r43 = r6;
        goto L52
    L58:
        int r112 = pack(r11, r22 + 8, 2, r33);
        if (r112 != 1) goto L61;
        return 0;
    L61:
        if (r112 != 3) goto L63;
        return 180;
    L63:
        if (r112 == 6) goto L68;
        if (r112 == 8) goto L66;
        return -1;
    L66:
        return 270;
    L68:
        return 90;
    L46:
        r33 = false;
        goto L47
    L42:
        if (r32 == 1296891946) goto L44;
        return -1;
    L74:
        return -1;
    L35:
        return -1;
    L12:
        r2 = r3;
    L36:
        r92 = 0;
        r2 = r3;
        goto L38
    L37:
        r92 = 0;
        goto L38
    }

    private void initCamera(Runnable r3, boolean r4) {
        if (this.cameraInitied == true) goto L17;
        if (r3 == null) goto L10;
        if (this.onFinishCameraInitRunnables.contains(r3) == true) goto L10;
        this.onFinishCameraInitRunnables.add(r3);
    L10:
        if (this.loadingCameras == false) goto L12;
        return;
    L12:
        if (this.cameraInitied == true) goto L18;
        this.loadingCameras = true;
        this.threadPool.execute(new CameraController$$ExternalSyntheticLambda1(this, r4, r3));
        return;
    L18:
        return;
    }

    private /* synthetic */ void lambda$finishRecordingVideo$15(File r5, Bitmap r6, long r7) {
        if (this.onVideoTakeCallback != null) goto L5;
        return;
    L5:
        if (r5 == null) goto L9;
        String r52 = r5.getAbsolutePath();
        if (r6 == null) goto L10;
        ImageLoader.getInstance().putImageToCache(new BitmapDrawable(r6), Utilities.MD5(r52), false);
    L10:
        this.onVideoTakeCallback.onFinishVideoRecording(r52, r7);
        this.onVideoTakeCallback = null;
        return;
    L9:
        r52 = null;
        goto L10
    }

    private /* synthetic */ void lambda$getErrorListener$18(CameraSession r4, int r5, Camera r6) {
        if (this.errorCallbacks == null) goto L11;
        int r0 = 0;
    L6:
        if (r0 >= this.errorCallbacks.size()) goto L15;
        ErrorCallback r1 = this.errorCallbacks.get(r0);
        if (r1 == null) goto L10;
        r1.onError(r5, r6, CameraSessionWrapper.of(r4));
    L10:
        r0 = r0 + 1;
        goto L6
    L15:
        return;
    }

    private /* synthetic */ void lambda$initCamera$1() {
        this.loadingCameras = false;
        this.cameraInitied = true;
        if (this.onFinishCameraInitRunnables.isEmpty() == true) goto L9;
        int r1 = 0;
    L5:
        int r2 = this.onFinishCameraInitRunnables.size();
        ArrayList<Runnable> r3 = this.onFinishCameraInitRunnables;
        if (r1 >= r2) goto L8;
        r3.get(r1).run();
        r1 = r1 + 1;
        goto L5
    L8:
        r3.clear();
    L9:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.cameraInitied, new Object[0]);
    }

    private /* synthetic */ void lambda$initCamera$2(Runnable r2) {
        initCamera(r2, true);
    }

    private /* synthetic */ void lambda$initCamera$3(boolean r2, Exception r3, Runnable r4) {
        this.onFinishCameraInitRunnables.clear();
        this.loadingCameras = false;
        this.cameraInitied = false;
        if (r2 == false) goto L5;
        return;
    L5:
        if ("APP_PAUSED".equals(r3.getMessage()) == false) goto L10;
        if (r4 == null) goto L11;
        AndroidUtilities.runOnUIThread(new CameraController$$ExternalSyntheticLambda3(this, r4), 1000);
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$initCamera$4(boolean r21, Runnable r22) {
    L12:
        e = move-exception;
        FileLog.e(e);
        AndroidUtilities.runOnUIThread(new CameraController$$ExternalSyntheticLambda13(this, r21, e, r22));
        return;
    L4:
        if (this.cameraInfos != null) goto L67;
        SharedPreferences r2 = MessagesController.getGlobalMainSettings();     // Catch: Exception -> L12
        String r3 = r2.getString("cameraCache", null);     // Catch: Exception -> L12
        CameraController$$ExternalSyntheticLambda11 r4 = new CameraController$$ExternalSyntheticLambda11();     // Catch: Exception -> L12
        ArrayList<CameraInfo> r5 = new ArrayList();     // Catch: Exception -> L12
        int r6 = 0;
        if (r3 == null) goto L19;
        SerializedData r0 = new SerializedData(Base64.decode(r3, 0));     // Catch: Exception -> L12
        int r23 = r0.readInt32(false);     // Catch: Exception -> L12
        int r32 = 0;
    L8:
        if (r32 >= r23) goto L18;
        CameraInfo r7 = new CameraInfo(r0.readInt32(false), r0.readInt32(false));     // Catch: Exception -> L12
        int r8 = r0.readInt32(false);     // Catch: Exception -> L12
        int r9 = 0;
    L10:
        if (r9 >= r8) goto L14;
        r7.previewSizes.add(new Size(r0.readInt32(false), r0.readInt32(false)));     // Catch: Exception -> L12
        r9 = r9 + 1;     // Catch: Exception -> L12
        goto L10
    L14:
        int r82 = r0.readInt32(false);     // Catch: Exception -> L12
        int r92 = 0;
    L15:
        if (r92 >= r82) goto L17;
        r7.pictureSizes.add(new Size(r0.readInt32(false), r0.readInt32(false)));     // Catch: Exception -> L12
        r92 = r92 + 1;     // Catch: Exception -> L12
        goto L15
    L17:
        r5.add(r7);     // Catch: Exception -> L12
        Collections.sort(r7.previewSizes, r4);     // Catch: Exception -> L12
        Collections.sort(r7.pictureSizes, r4);     // Catch: Exception -> L12
        r32 = r32 + 1;     // Catch: Exception -> L12
        goto L8
    L18:
        r0.cleanup();     // Catch: Exception -> L12
    L66:
        this.cameraInfos = r5;     // Catch: Exception -> L12
        goto L67
    L19:
        int r33 = Camera.getNumberOfCameras();     // Catch: Exception -> L12
        Camera.CameraInfo r72 = new Camera.CameraInfo();     // Catch: Exception -> L12
        int r83 = 4;
        int r93 = 0;
    L20:
        if (r93 >= r33) goto L56;
        Camera.getCameraInfo(r93, r72);     // Catch: Exception -> L12
        CameraInfo r10 = new CameraInfo(r93, r72.facing);     // Catch: Exception -> L12
        if (ApplicationLoader.mainInterfacePaused == true) goto L24;
    L28:
        Camera r11 = Camera.open(r10.getCameraId());     // Catch: Exception -> L12
        Camera.Parameters r12 = r11.getParameters();     // Catch: Exception -> L12
        List<Camera.Size> r13 = r12.getSupportedPreviewSizes();     // Catch: Exception -> L12
        int r14 = r6;
    L31:
        if (r14 >= r13.size()) goto L42;
        Camera.Size r15 = r13.get(r14);     // Catch: Exception -> L12
        SharedPreferences r16 = r2;
        int r24 = r15.height;     // Catch: Exception -> L12
        Camera.CameraInfo r17 = r72;
        if (r24 >= 2160) goto L40;
        int r18 = r93;
        int r94 = r15.width;     // Catch: Exception -> L12
        if (r94 >= 2160) goto L39;
        Camera r19 = r11;
        r10.previewSizes.add(new Size(r94, r24));     // Catch: Exception -> L12
        if (BuildVars.LOGS_ENABLED == false) goto L41;
        FileLog.d("preview size = " + r15.width + " " + r15.height);     // Catch: Exception -> L12
    L41:
        r14 = r14 + 1;     // Catch: Exception -> L12
        r2 = r16;
        r72 = r17;
        r93 = r18;
        r11 = r19;
    L39:
        r19 = r11;
        goto L41
    L40:
        r18 = r93;
        goto L39
    L42:
        SharedPreferences r162 = r2;
        Camera.CameraInfo r172 = r72;
        int r182 = r93;
        Camera r192 = r11;
        List<Camera.Size> r25 = r12.getSupportedPictureSizes();     // Catch: Exception -> L12
        int r73 = 0;
    L44:
        if (r73 >= r25.size()) goto L55;
        Camera.Size r95 = r25.get(r73);     // Catch: Exception -> L12
        if ("samsung".equals(Build.MANUFACTURER) == true) goto L48;
    L51:
        r10.pictureSizes.add(new Size(r95.width, r95.height));     // Catch: Exception -> L12
        if (BuildVars.LOGS_ENABLED == false) goto L54;
        FileLog.d("picture size = " + r95.width + " " + r95.height);     // Catch: Exception -> L12
    L54:
        r73 = r73 + 1;     // Catch: Exception -> L12
        goto L44
    L48:
        if ("jflteuc".equals(Build.PRODUCT) == false) goto L51;
        if (r95.width >= 2048) goto L54;
    L55:
        r192.release();     // Catch: Exception -> L12
        r5.add(r10);     // Catch: Exception -> L12
        Collections.sort(r10.previewSizes, r4);     // Catch: Exception -> L12
        Collections.sort(r10.pictureSizes, r4);     // Catch: Exception -> L12
        r83 = r83 + (((r10.previewSizes.size() + r10.pictureSizes.size()) * 8) + 8);     // Catch: Exception -> L12
        r93 = r182 + 1;     // Catch: Exception -> L12
        r2 = r162;
        r72 = r172;
        r6 = 0;
        goto L20
    L24:
        if (ApplicationLoader.externalInterfacePaused == false) goto L28;
        throw new RuntimeException("APP_PAUSED");     // Catch: Exception -> L12
    L56:
        SharedPreferences r163 = r2;
        SerializedData r26 = new SerializedData(r83);     // Catch: Exception -> L12
        r26.writeInt32(r5.size());     // Catch: Exception -> L12
        int r42 = 0;
    L57:
        if (r42 >= r33) goto L65;
        CameraInfo r62 = r5.get(r42);     // Catch: Exception -> L12
        r26.writeInt32(r62.cameraId);     // Catch: Exception -> L12
        r26.writeInt32(r62.frontCamera);     // Catch: Exception -> L12
        int r74 = r62.previewSizes.size();     // Catch: Exception -> L12
        r26.writeInt32(r74);     // Catch: Exception -> L12
        int r84 = 0;
    L59:
        if (r84 >= r74) goto L61;
        Size r96 = r62.previewSizes.get(r84);     // Catch: Exception -> L12
        r26.writeInt32(r96.mWidth);     // Catch: Exception -> L12
        r26.writeInt32(r96.mHeight);     // Catch: Exception -> L12
        r84 = r84 + 1;     // Catch: Exception -> L12
        goto L59
    L61:
        int r75 = r62.pictureSizes.size();     // Catch: Exception -> L12
        r26.writeInt32(r75);     // Catch: Exception -> L12
        int r85 = 0;
    L62:
        if (r85 >= r75) goto L64;
        Size r97 = r62.pictureSizes.get(r85);     // Catch: Exception -> L12
        r26.writeInt32(r97.mWidth);     // Catch: Exception -> L12
        r26.writeInt32(r97.mHeight);     // Catch: Exception -> L12
        r85 = r85 + 1;     // Catch: Exception -> L12
        goto L62
    L64:
        r42 = r42 + 1;     // Catch: Exception -> L12
        goto L57
    L65:
        r163.edit().putString("cameraCache", Base64.encodeToString(r26.toByteArray(), 0)).apply();     // Catch: Exception -> L12
        r26.cleanup();     // Catch: Exception -> L12
    L67:
        AndroidUtilities.runOnUIThread(new CameraController$$ExternalSyntheticLambda12(this));     // Catch: Exception -> L12
    }

    private /* synthetic */ void lambda$open$10(CameraSession r7, Runnable r8, SurfaceTexture r9, Runnable r10) {
        CameraInfo r0 = r7.cameraInfo;
        Camera r1 = r0.camera;
        if (r1 == null) goto L38;
    L7:
        r1.setErrorCallback(getErrorListener(r7));     // Catch: Exception -> L5
        Camera.Parameters r6 = r1.getParameters();     // Catch: Exception -> L5
        List<String> r02 = r6.getSupportedFlashModes();     // Catch: Exception -> L5
        r7.availableFlashModes.clear();     // Catch: Exception -> L5
        if (r02 == null) goto L27;
        int r3 = 0;
    L11:
        if (r3 >= r02.size()) goto L21;
        String r4 = r02.get(r3);     // Catch: Exception -> L5
        if (r4.equals("off") == false) goto L15;
    L18:
        r7.availableFlashModes.add(r4);     // Catch: Exception -> L5
    L19:
        r3 = r3 + 1;     // Catch: Exception -> L5
        goto L11
    L15:
        if (r4.equals("on") == true) goto L18;
        if (r4.equals("auto") == false) goto L19;
    L21:
        if (TextUtils.equals(r7.getCurrentFlashMode(), r6.getFlashMode()) == true) goto L23;
    L26:
        r7.checkFlashMode(r7.availableFlashModes.get(0));     // Catch: Exception -> L5
        goto L27
    L23:
        if (r7.availableFlashModes.contains(r7.getCurrentFlashMode()) == false) goto L26;
        r7.checkFlashMode(r7.getCurrentFlashMode());     // Catch: Exception -> L5
    L27:
        if (r8 == null) goto L29;
        r8.run();     // Catch: Exception -> L5
    L29:
        r7.configurePhotoCamera();     // Catch: Exception -> L5
        r1.setPreviewTexture(r9);     // Catch: Exception -> L5
        r1.startPreview();     // Catch: Exception -> L5
        if (r10 == null) goto L43;
        AndroidUtilities.runOnUIThread(r10);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        r7.cameraInfo.camera = null;
        if (r1 == null) goto L36;
        r1.release();
    L36:
        FileLog.e(e);
        return;
    L43:
        return;
    L38:
        Camera r2 = Camera.open(r0.cameraId);     // Catch: Exception -> L5
        r0.camera = r2;     // Catch: Exception -> L5
        r1 = r2;
        goto L7
    }

    private /* synthetic */ void lambda$recordVideo$11(boolean r1) {
        finishRecordingVideo(r1);
    }

    private /* synthetic */ void lambda$recordVideo$12(ICameraView r2, File r3, boolean r4, Runnable r5) {
        r2.startRecording(r3, new CameraController$$ExternalSyntheticLambda14(this, r4));
        if (r5 == null) goto L6;
        r5.run();
        return;
    }

    private /* synthetic */ void lambda$recordVideo$13(Object r7, ICameraView r8, File r9, boolean r10, Runnable r11) {
    L20:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if ((r7 instanceof CameraSession) == false) goto L16;
        CameraSession r72 = (CameraSession) r7;     // Catch: Exception -> L20
        Camera r0 = r72.cameraInfo.camera;     // Catch: Exception -> L20
        if (r0 != null) goto L23;
    L18:
        AndroidUtilities.runOnUIThread(new CameraController$$ExternalSyntheticLambda18(this, r8, r9, r10, r11));     // Catch: Exception -> L20
        return;
    L23:
        Camera.Parameters r1 = r0.getParameters();     // Catch: Exception -> L9
        if (r72.getCurrentFlashMode().equals("on") == false) goto L11;
        String r2 = "torch";
    L12:
        r1.setFlashMode(r2);     // Catch: Exception -> L9
        r0.setParameters(r1);     // Catch: Exception -> L9
        r72.onStartRecord();     // Catch: Exception -> L9
    L11:
        r2 = "off";
    L9:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L20
        goto L18
    L16:
        if ((r7 instanceof Camera2Session) == false) goto L18;
        ((Camera2Session) r7).setRecordingVideo(true);     // Catch: Exception -> L20
        goto L18
    }

    private /* synthetic */ void lambda$recordVideo$14(Camera r4, CameraSession r5, boolean r6, File r7, CameraInfo r8, VideoTakeCallback r9, Runnable r10) {
        if (r4 == null) goto L32;
        Camera.Parameters r0 = r4.getParameters();     // Catch: Exception -> L6
        if (r5.getCurrentFlashMode().equals("on") == false) goto L8;
        String r1 = "torch";
    L9:
        r0.setFlashMode(r1);     // Catch: Exception -> L6
        r4.setParameters(r0);     // Catch: Exception -> L6
    L12:
        r4.unlock();     // Catch: Exception -> L24
        this.mirrorRecorderVideo = r6;     // Catch: Exception -> L21
        MediaRecorder r62 = new MediaRecorder();     // Catch: Exception -> L21
        this.recorder = r62;     // Catch: Exception -> L21
        r62.setCamera(r4);     // Catch: Exception -> L21
        this.recorder.setVideoSource(1);     // Catch: Exception -> L21
        this.recorder.setAudioSource(5);     // Catch: Exception -> L21
        r5.configureRecorder(1, this.recorder);     // Catch: Exception -> L21
        this.recorder.setOutputFile(r7.getAbsolutePath());     // Catch: Exception -> L21
        this.recorder.setMaxFileSize(1073741824);     // Catch: Exception -> L21
        this.recorder.setVideoFrameRate(30);     // Catch: Exception -> L21
        this.recorder.setMaxDuration(0);     // Catch: Exception -> L21
        Size r42 = new Size(16, 9);     // Catch: Exception -> L21
        Size r43 = chooseOptimalSize(r8.getPictureSizes(), 720, 480, r42, false);     // Catch: Exception -> L21
        if (Math.min(r43.mHeight, r43.mWidth) < 720) goto L16;
        int r52 = 3500000;
    L17:
        this.recorder.setVideoEncodingBitRate(r52);     // Catch: Exception -> L21
        this.recorder.setVideoSize(r43.getWidth(), r43.getHeight());     // Catch: Exception -> L21
        this.recorder.setOnInfoListener(this);     // Catch: Exception -> L21
        this.recorder.prepare();     // Catch: Exception -> L21
        this.recorder.start();     // Catch: Exception -> L21
        this.onVideoTakeCallback = r9;     // Catch: Exception -> L21
        this.recordedFile = r7.getAbsolutePath();     // Catch: Exception -> L21
        if (r10 == null) goto L33;
        AndroidUtilities.runOnUIThread(r10);     // Catch: Exception -> L21
    L34:
        return;
    L33:
        return;
    L16:
        r52 = 1800000;
    L21:
        e = move-exception;
        this.recorder.release();     // Catch: Exception -> L24
        this.recorder = null;     // Catch: Exception -> L24
        FileLog.e(e);     // Catch: Exception -> L24
        return;
    L8:
        r1 = "off";
    L6:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L24
    L24:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    private /* synthetic */ void lambda$startPreview$7(CameraSession r4) {
        CameraInfo r0 = r4.cameraInfo;
        Camera r1 = r0.camera;
        if (r1 == null) goto L18;
    L11:
        r1.startPreview();     // Catch: Exception -> L9
        return;
    L9:
        e = e;
    L13:
        r4.cameraInfo.camera = null;
        if (r1 == null) goto L16;
        r1.release();
    L16:
        FileLog.e(e);
        return;
    L18:
        Camera r2 = Camera.open(r0.cameraId);     // Catch: Exception -> L9
        r0.camera = r2;     // Catch: Exception -> L9
        r2.setErrorCallback(getErrorListener(r4));     // Catch: Exception -> L7
        r1 = r2;
    L7:
        e = e;
        r1 = r2;
        goto L13
    }

    private /* synthetic */ void lambda$stopPreview$8(CameraSession r4) {
        CameraInfo r0 = r4.cameraInfo;
        Camera r1 = r0.camera;
        if (r1 == null) goto L18;
    L11:
        r1.stopPreview();     // Catch: Exception -> L9
        return;
    L9:
        e = e;
    L13:
        r4.cameraInfo.camera = null;
        if (r1 == null) goto L16;
        r1.release();
    L16:
        FileLog.e(e);
        return;
    L18:
        Camera r2 = Camera.open(r0.cameraId);     // Catch: Exception -> L9
        r0.camera = r2;     // Catch: Exception -> L9
        r2.setErrorCallback(getErrorListener(r4));     // Catch: Exception -> L7
        r1 = r2;
    L7:
        e = e;
        r1 = r2;
        goto L13
    }

    private /* synthetic */ void lambda$stopVideoRecording$17(Object r5, boolean r6, boolean r7) {
        MediaRecorder r0 = this.recorder;     // Catch: Exception -> L39
        if (r0 == null) goto L14;
        this.recorder = null;     // Catch: Exception -> L39
        r0.stop();     // Catch: Exception -> L7
    L47:
        r0.release();     // Catch: Exception -> L11
    L11:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L39
    L7:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L39
    L14:
        if ((r5 instanceof CameraSession) == false) goto L31;
        CameraSession r52 = (CameraSession) r5;     // Catch: Exception -> L39
        Camera r02 = r52.cameraInfo.camera;     // Catch: Exception -> L39
        if (r02 == null) goto L45;
        r02.reconnect();     // Catch: Exception -> L19
        r02.startPreview();     // Catch: Exception -> L19
    L49:
        r52.stopVideoRecording();     // Catch: Exception -> L23
    L23:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L39
    L19:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L39
    L45:
        Camera.Parameters r2 = r02.getParameters();     // Catch: Exception -> L27
        r2.setFlashMode("off");     // Catch: Exception -> L27
        r02.setParameters(r2);     // Catch: Exception -> L27
    L29:
        this.threadPool.execute(new CameraController$$ExternalSyntheticLambda7(r02, r52));     // Catch: Exception -> L39
    L33:
        if (r6 == false) goto L35;
    L37:
        this.onVideoTakeCallback = null;     // Catch: Exception -> L39
        return;
    L35:
        if (this.onVideoTakeCallback == null) goto L37;
        finishRecordingVideo(r7);     // Catch: Exception -> L39
        return;
    L27:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L39
        goto L29
    L31:
        if ((r5 instanceof Camera2Session) == false) goto L33;
        ((Camera2Session) r5).setRecordingVideo(false);     // Catch: Exception -> L39
    }

    private static int pack(byte[] r2, int r3, int r4, boolean r5) {
        if (r5 == false) goto L4;
        r3 = r3 + (r4 - 1);
        int r52 = -1;
    L5:
        int r0 = 0;
    L6:
        int r1 = r4 - 1;
        if (r4 <= 0) goto L9;
        r0 = (r2[r3] & UByte.MAX_VALUE) | (r0 << 8);
        r3 = r3 + r52;
        r4 = r1;
        goto L6
    L9:
        return r0;
    L4:
        r52 = 1;
        goto L5
    }

    public void addOnErrorListener(ErrorCallback r2) {
        if (this.errorCallbacks != null) goto L5;
        this.errorCallbacks = new ArrayList();
    L5:
        this.errorCallbacks.remove(r2);
        this.errorCallbacks.add(r2);
    }

    public void cancelOnInitRunnable(Runnable r1) {
        this.onFinishCameraInitRunnables.remove(r1);
    }

    public void close(CameraSession r2, CountDownLatch r3, Runnable r4) {
        close(r2, r3, r4, null);
    }

    public void close(CameraSession r2, CountDownLatch r3, Runnable r4, Runnable r5) {
        r2.destroy();
        this.threadPool.execute(new CameraController$$ExternalSyntheticLambda2(r4, r2, r3, r5));
        if (r3 == null) goto L11;
        r3.await();     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public ArrayList<CameraInfo> getCameras() {
        return this.cameraInfos;
    }

    public Camera.ErrorCallback getErrorListener(CameraSession r2) {
        return new CameraController$$ExternalSyntheticLambda15(this, r2);
    }

    public void initCamera(Runnable r2) {
        initCamera(r2, false);
    }

    public boolean isCameraInitied() {
        if (this.cameraInitied == true) goto L5;
        return false;
    L5:
        if (this.cameraInfos != null) goto L7;
        return false;
    L7:
        if (this.cameraInfos.isEmpty() == true) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // android.media.MediaRecorder.OnInfoListener
    public void onInfo(MediaRecorder r1, int r2, int r3) {
        if (r2 != 800) goto L5;
    L7:
        MediaRecorder r12 = this.recorder;
        this.recorder = null;
        if (r12 == null) goto L11;
        r12.stop();
        r12.release();
    L11:
        if (this.onVideoTakeCallback == null) goto L15;
        finishRecordingVideo(true);
        return;
    L15:
        return;
    L5:
        if (r2 == 801) goto L7;
        if (r2 == 1) goto L7;
    }

    public void open(CameraSession r8, SurfaceTexture r9, Runnable r10, Runnable r11) {
        if (r8 == null) goto L7;
        if (r9 == null) goto L8;
        this.threadPool.execute(new CameraController$$ExternalSyntheticLambda0(this, r8, r11, r9, r10));
        return;
    L8:
        return;
    }

    public void openRound(CameraSession r2, SurfaceTexture r3, Runnable r4, Runnable r5) {
        if (r2 == null) goto L8;
        if (r3 == null) goto L8;
        this.threadPool.execute(new CameraController$$ExternalSyntheticLambda17(r2, r5, r3, r4));
        return;
    L8:
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.d("failed to open round " + r2 + " tex = " + r3);
        return;
    }

    public void recordVideo(Object r9, File r10, boolean r11, VideoTakeCallback r12, Runnable r13, ICameraView r14) {
        recordVideo(r9, r10, r11, r12, r13, r14, true);
    }

    public void recordVideo(Object r11, File r12, boolean r13, VideoTakeCallback r14, Runnable r15, ICameraView r16, boolean r17) {
        if (r11 == null) goto L13;
        if (r16 == null) goto L9;
        this.recordingCurrentCameraView = r16;
        this.onVideoTakeCallback = r14;
        this.recordedFile = r12.getAbsolutePath();
        this.threadPool.execute(new CameraController$$ExternalSyntheticLambda4(this, r11, r16, r12, r17, r15));
        return;
    L9:
        if ((r11 instanceof CameraSession) == false) goto L12;
        CameraSession r4 = (CameraSession) r11;
        CameraInfo r7 = r4.cameraInfo;
        Camera r3 = r7.camera;
        this.threadPool.execute(new CameraController$$ExternalSyntheticLambda5(this, r3, r4, r13, r12, r7, r14, r15));
        return;
    L12:
        return;
    }

    public void removeOnErrorListener(ErrorCallback r1) {
        ArrayList<ErrorCallback> r0 = this.errorCallbacks;
        if (r0 == null) goto L6;
        r0.remove(r1);
        return;
    }

    public void startPreview(Object r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if ((r3 instanceof CameraSession) == false) goto L9;
        this.threadPool.execute(new CameraController$$ExternalSyntheticLambda9(this, (CameraSession) r3));
        return;
    }

    public void stopPreview(Object r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if ((r3 instanceof CameraSession) == false) goto L9;
        this.threadPool.execute(new CameraController$$ExternalSyntheticLambda16(this, (CameraSession) r3));
        return;
    }

    public void stopVideoRecording(Object r2, boolean r3) {
        stopVideoRecording(r2, r3, true);
    }

    public void stopVideoRecording(Object r3, boolean r4, boolean r5) {
        ICameraView r0 = this.recordingCurrentCameraView;
        if (r0 == null) goto L6;
        r0.stopRecording();
        this.recordingCurrentCameraView = null;
        return;
    L6:
        this.threadPool.execute(new CameraController$$ExternalSyntheticLambda6(this, r3, r4, r5));
    }

    public boolean takePicture(File r7, boolean r8, Object r9, Utilities.Callback<Integer> r10) {
        if (r9 != null) goto L6;
        return false;
    L6:
        if ((r9 instanceof CameraSession) == false) goto L15;
        CameraSession r92 = (CameraSession) r9;
        CameraInfo r2 = r92.cameraInfo;
        boolean r3 = r92.isFlipFront();
        Camera r93 = r2.camera;
        r93.takePicture(null, null, new CameraController$$ExternalSyntheticLambda10(r7, r2, r3, r8, r10));     // Catch: Exception -> L11
        return true;
    L11:
        e = move-exception;
        FileLog.e(e);
        return false;
    L15:
        if ((r9 instanceof Camera2Session) == true) goto L17;
        return false;
    L17:
        return ((Camera2Session) r9).takePicture(r7, r10);
    }
}
