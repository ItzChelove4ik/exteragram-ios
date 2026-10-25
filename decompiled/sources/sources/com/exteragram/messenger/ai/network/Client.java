package com.exteragram.messenger.ai.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.android.dx.io.Opcodes;
import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.AiConfig;
import com.exteragram.messenger.ai.AiController;
import com.exteragram.messenger.ai.data.Message;
import com.exteragram.messenger.ai.data.Role;
import com.exteragram.messenger.ai.data.Service;
import com.exteragram.messenger.ai.network.backend.Backend;
import com.exteragram.messenger.ai.network.backend.BackendRequest;
import com.exteragram.messenger.ai.network.backend.BackendSink;
import com.exteragram.messenger.ai.network.backend.OnDeviceBackend;
import com.exteragram.messenger.ai.network.backend.RestBackend;
import com.google.android.gms.cast.MediaError;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;

/* loaded from: classes4.dex */
public class Client {
    private final ConcurrentHashMap<String, Runnable> activeCancellables;
    private final ConcurrentHashMap<String, ExecutorService> activeRequests;
    private final AtomicBoolean isGenerating;
    private final OnDeviceBackend onDeviceBackend;
    private final RestBackend restBackend;
    private final Role roleOverride;
    private final Service serviceOverride;

    public static class Builder {
        private Role roleOverride;
        private Service serviceOverride;

        /* renamed from: -$$Nest$fgetroleOverride, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Role m1027$$Nest$fgetroleOverride(Builder r0) {
            return r0.roleOverride;
        }

        /* renamed from: -$$Nest$fgetserviceOverride, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Service m1028$$Nest$fgetserviceOverride(Builder r0) {
            return r0.serviceOverride;
        }

        public Builder() {
        }

        public Client build() {
            return new Client(this, null);
        }

        public Builder roleOverride(Role r1) {
            this.roleOverride = r1;
            return this;
        }

        public Builder serviceOverride(Service r1) {
            this.serviceOverride = r1;
            return this;
        }
    }

    public static final class ImagePayload extends RecordTag {
        private final byte[] data;
        private final String mimeType;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof ImagePayload) == false) goto L10;
            ImagePayload r32 = (ImagePayload) r3;
            if (Objects.equals(this.data, r32.data) == true) goto L7;
            return false;
        L7:
            if (Objects.equals(this.mimeType, r32.mimeType) == false) goto L13;
            return true;
        L13:
            return false;
        L10:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.data, this.mimeType};
        }

        private ImagePayload(byte[] r1, String r2) {
            this.data = r1;
            this.mimeType = r2;
        }

        public /* synthetic */ ImagePayload(byte[] r1, String r2, ClientIA r3) {
            this(r1, r2);
        }

        public byte[] data() {
            return this.data;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public final int hashCode() {
            return Client$ImagePayload$$ExternalSyntheticRecord0.m(this.data, this.mimeType);
        }

        public String mimeType() {
            return this.mimeType;
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), ImagePayload.class, "data;mimeType");
        }
    }

    public final class RequestSink implements BackendSink {
        private final GenerationCallback callback;
        private final Message currentMessage;
        private final String requestId;
        private final boolean saveHistory;
        final /* synthetic */ Client this$0;

        public RequestSink(Client r1, String r2, Message r3, boolean r4, GenerationCallback r5) {
            this.this$0 = r1;
            this.requestId = r2;
            this.currentMessage = r3;
            this.saveHistory = r4;
            this.callback = r5;
        }

        @Override // com.exteragram.messenger.ai.network.backend.BackendSink
        public boolean isActive() {
            return Client.m1021$$Nest$fgetactiveRequests(this.this$0).containsKey(this.requestId);
        }

        @Override // com.exteragram.messenger.ai.network.backend.BackendSink
        public void onCancellable(Runnable r2) {
            if (r2 == null) goto L5;
            Client.m1020$$Nest$fgetactiveCancellables(this.this$0).put(this.requestId, r2);
            return;
        }

        @Override // com.exteragram.messenger.ai.network.backend.BackendSink
        public void onChunk(String r3) {
            Client.m1026$$Nest$msendStreamChunk(this.this$0, this.requestId, r3, this.callback);
        }

        @Override // com.exteragram.messenger.ai.network.backend.BackendSink
        public void onComplete(String r3) {
            if (isActive() == true) goto L6;
            return;
        L6:
            if (TextUtils.isEmpty(r3) == false) goto L10;
            onError(Opcodes.SUB_DOUBLE_2ADDR, "Response body is empty");
            return;
        L10:
            if (this.saveHistory == false) goto L12;
            Client.m1025$$Nest$msaveTurn(this.this$0, this.currentMessage, r3);
        L12:
            Client.m1023$$Nest$mnotifyResponseAndFinish(this.this$0, this.requestId, this.callback, r3);
        }

        @Override // com.exteragram.messenger.ai.network.backend.BackendSink
        public void onError(int r3, String r4) {
            Client.m1022$$Nest$mnotifyErrorAndFinish(this.this$0, this.requestId, this.callback, r3, r4);
        }

        @Override // com.exteragram.messenger.ai.network.backend.BackendSink
        public void onThinking() {
            Client.m1024$$Nest$mnotifyThinking(this.this$0, this.requestId, this.callback);
        }
    }

    public static /* synthetic */ void $r8$lambda$8PcWxu2bDwiysw_AQG9wpzHuiZI(Client r0, String r1, GenerationCallback r2) {
        r0.lambda$notifyThinking$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$TZ5gYF91OPAMPjVjRhU8c3pjR_k(Client r0, String r1, String r2, String r3, boolean r4, boolean r5, GenerationCallback r6) {
        r0.lambda$getResponse$0(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$YanB1L6ZWsHpkKjYmgMbSoPA-ug, reason: not valid java name */
    public static /* synthetic */ void m1019$r8$lambda$YanB1L6ZWsHpkKjYmgMbSoPAug(Client r0, String r1, GenerationCallback r2, int r3, String r4) {
        r0.lambda$notifyErrorAndFinish$4(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$pBnI5nMHAARFoQKLTeC7fFLHz8g(Client r0, String r1, GenerationCallback r2, String r3) {
        r0.lambda$notifyResponseAndFinish$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$xP2U07ZRDJ83U_GZxFeG2frDpts(Client r0, String r1, GenerationCallback r2, String r3) {
        r0.lambda$sendStreamChunk$1(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetactiveCancellables, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ConcurrentHashMap m1020$$Nest$fgetactiveCancellables(Client r0) {
        return r0.activeCancellables;
    }

    /* renamed from: -$$Nest$fgetactiveRequests, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ConcurrentHashMap m1021$$Nest$fgetactiveRequests(Client r0) {
        return r0.activeRequests;
    }

    /* renamed from: -$$Nest$mnotifyErrorAndFinish, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1022$$Nest$mnotifyErrorAndFinish(Client r0, String r1, GenerationCallback r2, int r3, String r4) {
        r0.notifyErrorAndFinish(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$mnotifyResponseAndFinish, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1023$$Nest$mnotifyResponseAndFinish(Client r0, String r1, GenerationCallback r2, String r3) {
        r0.notifyResponseAndFinish(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mnotifyThinking, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1024$$Nest$mnotifyThinking(Client r0, String r1, GenerationCallback r2) {
        r0.notifyThinking(r1, r2);
    }

    /* renamed from: -$$Nest$msaveTurn, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1025$$Nest$msaveTurn(Client r0, Message r1, String r2) {
        r0.saveTurn(r1, r2);
    }

    /* renamed from: -$$Nest$msendStreamChunk, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1026$$Nest$msendStreamChunk(Client r0, String r1, String r2, GenerationCallback r3) {
        r0.sendStreamChunk(r1, r2, r3);
    }

    private Client(Builder r3) {
        this.isGenerating = new AtomicBoolean(false);
        this.activeRequests = new ConcurrentHashMap();
        this.activeCancellables = new ConcurrentHashMap();
        this.restBackend = new RestBackend();
        this.onDeviceBackend = new OnDeviceBackend();
        this.serviceOverride = Builder.m1028$$Nest$fgetserviceOverride(r3);
        this.roleOverride = Builder.m1027$$Nest$fgetroleOverride(r3);
    }

    public /* synthetic */ Client(Builder r1, ClientIA r2) {
        this(r1);
    }

    private static ImagePayload compressImage(String r6) {
        BitmapFactory.Options r1 = new BitmapFactory.Options();     // Catch: Exception -> L28
        r1.inJustDecodeBounds = true;     // Catch: Exception -> L28
        BitmapFactory.decodeFile(r6, r1);     // Catch: Exception -> L28
        if (r1.outWidth > 0) goto L6;
    L46:
        return null;
    L6:
        if (r1.outHeight <= 0) goto L46;
        BitmapFactory.Options r3 = new BitmapFactory.Options();     // Catch: Exception -> L28
        r3.inSampleSize = 1;     // Catch: Exception -> L28
    L9:
        int r2 = r1.outWidth;     // Catch: Exception -> L28
        int r4 = r3.inSampleSize;     // Catch: Exception -> L28
        if ((r2 / r4) > 2048) goto L44;
        if ((r1.outHeight / r4) > 2048) goto L44;
        Bitmap r12 = BitmapFactory.decodeFile(r6, r3);     // Catch: Exception -> L28
        if (r12 != null) goto L54;
        return null;
    L54:
        ByteArrayOutputStream r22 = new ByteArrayOutputStream();     // Catch: Throwable -> L30
        int r32 = 85;
    L49:
        r22.reset();     // Catch: Throwable -> L36
        r12.compress(Bitmap.CompressFormat.JPEG, r32, r22);     // Catch: Throwable -> L36
        r32 = r32 - 10;
        if (r22.size() <= 4194304) goto L24;
        if (r32 >= 55) goto L49;
    L24:
        if (r22.size() <= 4194304) goto L32;
        r22.close();     // Catch: Throwable -> L30
        r12.recycle();     // Catch: Exception -> L28
        return null;
    L32:
        ImagePayload r33 = new ImagePayload(r22.toByteArray(), "image/jpeg", null);     // Catch: Throwable -> L36
        r22.close();     // Catch: Throwable -> L30
        r12.recycle();     // Catch: Exception -> L28
        return r33;
    L36:
        th = move-exception;
        r22.close();     // Catch: Throwable -> L39
    L41:
        throw th;     // Catch: Throwable -> L30
    L39:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L30
    L30:
        th = move-exception;
        r12.recycle();     // Catch: Exception -> L28
        throw th;     // Catch: Exception -> L28
    L44:
        r3.inSampleSize = r4 * 2;     // Catch: Exception -> L28
    L28:
        e = move-exception;
        FileLog.e("Error compressing image: " + r6, e);
        return null;
    }

    private Message copyTextMessage(Message r2) {
        return new Message(r2.role(), r2.content());
    }

    private void finishRequest(String r2) {
        this.activeCancellables.remove(r2);
        ExecutorService r22 = this.activeRequests.remove(r2);
        if (r22 == null) goto L6;
        r22.shutdown();
    L6:
        if (this.activeRequests.isEmpty() == false) goto L9;
        this.isGenerating.set(false);
        return;
    }

    private Role getActiveRole() {
        Role r0 = this.roleOverride;
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (this.serviceOverride == null) goto L8;
        return null;
    L8:
        return AiController.getInstance().getSelectedRole();
    }

    public static String getMimeType(String r1) {
        if (TextUtils.isEmpty(r1) == false) goto L6;
        return null;
    L6:
        String r12 = r1.toLowerCase();
        if (r12.endsWith(".png") == false) goto L11;
        return "image/png";
    L11:
        if (r12.endsWith(".webp") == false) goto L15;
        return "image/webp";
    L15:
        if (r12.endsWith(".heic") == false) goto L17;
        return "image/heic";
    L17:
        if (r12.endsWith(".heif") == true) goto L23;
        return "image/jpeg";
    L23:
        return "image/heic";
    }

    private Service getSelectedService() {
        Service r0 = this.serviceOverride;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return AiController.getInstance().getSelected();
    }

    private ArrayList<Message> historyForRequest(ArrayList<Message> r11, Backend r12) {
        ArrayList<Message> r0 = new ArrayList();
        int r1 = r12.getHistoryCharsLimit();
        int r122 = r12.getHistoryMessagesLimit();
        int r2 = r11.size() - 1;
        int r5 = 0;
    L3:
        if (r2 <= 0) goto L27;
        if (r0.size() >= r122) goto L27;
        Message r6 = r11.get(r2);
        if (r6 == null) goto L26;
        if ("assistant".equals(r6.role()) == false) goto L26;
        if (TextUtils.isEmpty(r6.content()) == true) goto L26;
        Message r7 = r11.get(r2 - 1);
        if (r7 == null) goto L26;
        if ("assistant".equals(r7.role()) == true) goto L26;
        if (TextUtils.isEmpty(r7.content()) == true) goto L26;
        int r8 = (r7.content().length() + r6.content().length()) + r5;
        if (r8 > r1) goto L26;
        if ((r0.size() + 2) > r122) goto L26;
        r0.add(0, copyTextMessage(r7));
        r0.add(1, copyTextMessage(r6));
        r5 = r8;
    L26:
        r2 = r2 - 1;
    L27:
        return r0;
    }

    private /* synthetic */ void lambda$getResponse$0(String r11, String r12, String r13, boolean r14, boolean r15, GenerationCallback r16) {
        this.isGenerating.set(true);
        Service r3 = getSelectedService();     // Catch: Exception -> L8
        Backend r0 = resolveBackend(r3);     // Catch: Exception -> L8
        String r2 = null;
        if (r0.supportsImages() == true) goto L6;
    L10:
        ImagePayload r112 = null;
    L12:
        if (this.activeRequests.containsKey(r12) == true) goto L15;
        return;
    L15:
        if (r112 == null) goto L17;
        byte[] r4 = r112.data();     // Catch: Exception -> L8
    L18:
        if (r112 == null) goto L20;
        r2 = r112.mimeType();     // Catch: Exception -> L8
    L20:
        Message r6 = new Message("user", r13, r4, r2);     // Catch: Exception -> L8
        if (r14 == false) goto L24;
        ArrayList<Message> r113 = historyForRequest(AiConfig.getConversationHistory(), r0);     // Catch: Exception -> L8
    L23:
        ArrayList<Message> r5 = r113;
        if (this.activeRequests.containsKey(r12) == false) goto L41;
        r0.execute(new BackendRequest(r3, getActiveRole(), r5, r6, r15, (AiConfig.getTemperature() / 20.0f) * r0.getMaxTemperature(), r0.getMaxOutputTokens()), new RequestSink(this, r12, r6, r14, r16));     // Catch: Exception -> L8
        return;
    L41:
        return;
    L24:
        r113 = new ArrayList();     // Catch: Exception -> L8
        goto L23
    L17:
        r4 = null;
        goto L18
    L6:
        if (AiController.canSendImage(r11) == false) goto L10;
        r112 = loadImagePayload(r11);     // Catch: Exception -> L8
    L8:
        e = move-exception;
        if (this.activeRequests.containsKey(r12) == false) goto L42;
        FileLog.e("AI Error: ", e);
        if (e.getMessage() == null) goto L36;
        String r114 = e.getMessage();
    L37:
        notifyErrorAndFinish(r12, r16, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, r114);
        return;
    L36:
        r114 = "Unknown error";
        goto L37
    }

    private /* synthetic */ void lambda$notifyErrorAndFinish$4(String r2, GenerationCallback r3, int r4, String r5) {
    L7:
        th = move-exception;
        finishRequest(r2);
        throw th;
    L3:
        if (this.activeRequests.containsKey(r2) == false) goto L9;
        if (r3 == null) goto L9;
        r3.onError(r4, r5);     // Catch: Throwable -> L7
    L9:
        finishRequest(r2);
    }

    private /* synthetic */ void lambda$notifyResponseAndFinish$3(String r2, GenerationCallback r3, String r4) {
    L7:
        th = move-exception;
        finishRequest(r2);
        throw th;
    L3:
        if (this.activeRequests.containsKey(r2) == false) goto L9;
        if (r3 == null) goto L9;
        r3.onResponse(r4);     // Catch: Throwable -> L7
    L9:
        finishRequest(r2);
    }

    private /* synthetic */ void lambda$notifyThinking$2(String r1, GenerationCallback r2) {
        if (this.activeRequests.containsKey(r1) == false) goto L7;
        if (r2 == null) goto L8;
        r2.onThinking();
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$sendStreamChunk$1(String r1, GenerationCallback r2, String r3) {
        if (this.activeRequests.containsKey(r1) == false) goto L7;
        if (r2 == null) goto L8;
        r2.onChunk(r3);
        return;
    L8:
        return;
    }

    private static ImagePayload loadImagePayload(String r8) {
        if (TextUtils.isEmpty(r8) == false) goto L5;
        return null;
    L5:
        File r0 = new File(r8);
        if (r0.exists() == true) goto L8;
    L45:
        return null;
    L8:
        if (r0.isFile() == false) goto L45;
        if (r0.length() != 0) goto L14;
        return null;
    L14:
        if (r0.length() > 4194304) goto L16;
        FileInputStream r2 = new FileInputStream(r0);     // Catch: IOException -> L30
        ByteArrayOutputStream r3 = new ByteArrayOutputStream((int) Math.min(r0.length(), 4194304));     // Catch: Throwable -> L32
        byte[] r02 = new byte[1024];     // Catch: Throwable -> L24
    L21:
        int r4 = r2.read(r02);     // Catch: Throwable -> L24
        if (r4 == (-1)) goto L26;
        r3.write(r02, 0, r4);     // Catch: Throwable -> L24
        goto L21
    L26:
        ImagePayload r03 = new ImagePayload(r3.toByteArray(), getMimeType(r8), null);     // Catch: Throwable -> L24
        r3.close();     // Catch: Throwable -> L32
        r2.close();     // Catch: IOException -> L30
        return r03;
    L24:
        th = move-exception;
        r3.close();     // Catch: Throwable -> L36
    L38:
        throw th;     // Catch: Throwable -> L32
    L36:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L32
    L32:
        th = move-exception;
        r2.close();     // Catch: Throwable -> L41
    L43:
        throw th;     // Catch: IOException -> L30
    L41:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L30
    L30:
        e = move-exception;
        FileLog.e("Error loading image: " + r8, e);
        goto L45
    L16:
        return compressImage(r8);
    }

    private void notifyErrorAndFinish(String r7, GenerationCallback r8, int r9, String r10) {
        AndroidUtilities.runOnUIThread(new Client$$ExternalSyntheticLambda1(this, r7, r8, r9, r10));
    }

    private void notifyResponseAndFinish(String r2, GenerationCallback r3, String r4) {
        AndroidUtilities.runOnUIThread(new Client$$ExternalSyntheticLambda2(this, r2, r3, r4));
    }

    private void notifyThinking(String r2, GenerationCallback r3) {
        AndroidUtilities.runOnUIThread(new Client$$ExternalSyntheticLambda4(this, r2, r3));
    }

    private Backend resolveBackend(Service r1) {
        if (r1 == null) goto L8;
        if (r1.isOnDevice() == false) goto L8;
        return this.onDeviceBackend;
    L8:
        return this.restBackend;
    }

    private void saveTurn(Message r6, String r7) {
        ArrayList<Message> r0 = AiConfig.getConversationHistory();
        r0.add(copyTextMessage(r6));
        r0.add(new Message("assistant", r7));
        int r5 = r0.size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r5) goto L10;
        Message r3 = r0.get(r2);
        r2 = r2 + 1;
        Message r32 = r3;
        if (r32.content() != null) goto L7;
        int r33 = 0;
    L8:
        r1 = r1 + r33;
        goto L3
    L7:
        r33 = r32.content().length();
    L10:
        if (r0.size() <= 2) goto L21;
        if (r0.size() <= 64) goto L14;
    L15:
        Message r52 = r0.remove(0);
        if (r52.content() != null) goto L18;
        int r53 = 0;
    L19:
        r1 = r1 - r53;
        goto L10
    L18:
        r53 = r52.content().length();
        goto L19
    L14:
        if (r1 > 64000) goto L15;
    L21:
        if (r0.size() > 2) goto L23;
    L25:
        AiConfig.saveConversationHistory(r0);
        return;
    L23:
        if ("assistant".equals(r0.get(0).role()) == false) goto L25;
        r0.remove(0);
        goto L25
    }

    private void sendStreamChunk(String r2, String r3, GenerationCallback r4) {
        if (TextUtils.isEmpty(r3) == true) goto L6;
        AndroidUtilities.runOnUIThread(new Client$$ExternalSyntheticLambda3(this, r2, r4, r3));
        return;
    }

    public String getResponse(String r7, GenerationCallback r8) {
        return getResponse(r7, false, false, null, r8);
    }

    public String getResponse(String r10, boolean r11, boolean r12, String r13, GenerationCallback r14) {
        String r4 = UUID.randomUUID().toString();
        ExecutorService r0 = Executors.newSingleThreadExecutor();
        this.activeRequests.put(r4, r0);
        r0.execute(new Client$$ExternalSyntheticLambda0(this, r13, r4, r10, r11, r12, r14));
        return r4;
    }

    public boolean isGenerating() {
        return this.isGenerating.get();
    }

    public void stopRequest(String r2) {
        if (TextUtils.isEmpty(r2) == true) goto L21;
        Runnable r0 = this.activeCancellables.remove(r2);
        if (r0 != null) goto L18;
    L11:
        ExecutorService r22 = this.activeRequests.remove(r2);
        if (r22 == null) goto L15;
        r22.shutdownNow();
    L15:
        if (this.activeRequests.isEmpty() == false) goto L20;
        this.isGenerating.set(false);
        return;
    L20:
        return;
    L18:
        r0.run();     // Catch: Exception -> L9
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L11
    }
}
