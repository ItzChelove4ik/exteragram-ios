package com.exteragram.messenger.speech.recognizers;

import android.text.TextUtils;
import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.speech.VoiceRecognitionController;
import com.exteragram.messenger.speech.utils.FormatConverter;
import com.exteragram.messenger.utils.network.ExteraHttpClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.vosk.LibVosk;
import org.vosk.LogLevel;
import org.vosk.Model;
import org.vosk.Recognizer;
import org.vosk.android.RecognitionListener;
import org.vosk.android.SpeechStreamService;

/* loaded from: classes4.dex */
public class VoskRecognizer implements VoiceRecognitionController.RecognitionProvider, AutoCloseable {
    private final OkHttpClient client;
    private final Map<String, Model> loadedModels;
    private final List<VoiceRecognitionController.RecognitionModel> models;
    private final File modelsDir;

    /* renamed from: com.exteragram.messenger.speech.recognizers.VoskRecognizer$1, reason: invalid class name */
    public class AnonymousClass1 extends ArrayList<VoiceRecognitionController.RecognitionModel> {
        final /* synthetic */ VoskRecognizer this$0;

        public AnonymousClass1(VoskRecognizer r5) {
            this.this$0 = r5;
            add(new VoiceRecognitionController.RecognitionModel("ca", "https://alphacephei.com/vosk/models/vosk-model-small-ca-0.4.zip", 43405881));
            add(new VoiceRecognitionController.RecognitionModel("cs", "https://alphacephei.com/vosk/models/vosk-model-small-cs-0.4-rhasspy.zip", 46088666));
            add(new VoiceRecognitionController.RecognitionModel("de", "https://alphacephei.com/vosk/models/vosk-model-small-de-0.15.zip", 46499967));
            add(new VoiceRecognitionController.RecognitionModel("en", "https://alphacephei.com/vosk/models/vosk-model-small-en-us-0.15.zip", 41205931));
            add(new VoiceRecognitionController.RecognitionModel("eo", "https://alphacephei.com/vosk/models/vosk-model-small-eo-0.42.zip", 43839401));
            add(new VoiceRecognitionController.RecognitionModel("es", "https://alphacephei.com/vosk/models/vosk-model-small-es-0.42.zip", 39817833));
            add(new VoiceRecognitionController.RecognitionModel("fa", "https://alphacephei.com/vosk/models/vosk-model-small-fa-0.42.zip", 53431220));
            add(new VoiceRecognitionController.RecognitionModel("fr", "https://alphacephei.com/vosk/models/vosk-model-small-fr-0.22.zip", 42233323));
            add(new VoiceRecognitionController.RecognitionModel("gu", "https://alphacephei.com/vosk/models/vosk-model-small-gu-0.42.zip", 108054987));
            add(new VoiceRecognitionController.RecognitionModel("hi", "https://alphacephei.com/vosk/models/vosk-model-small-hi-0.22.zip", 44458845));
            add(new VoiceRecognitionController.RecognitionModel("it", "https://alphacephei.com/vosk/models/vosk-model-small-it-0.22.zip", 49665141));
            add(new VoiceRecognitionController.RecognitionModel("ja", "https://alphacephei.com/vosk/models/vosk-model-small-ja-0.22.zip", 49704573));
            add(new VoiceRecognitionController.RecognitionModel("kk", "https://alphacephei.com/vosk/models/vosk-model-small-kz-0.42.zip", 59697294));
            add(new VoiceRecognitionController.RecognitionModel("ko", "https://alphacephei.com/vosk/models/vosk-model-small-ko-0.22.zip", 86914329));
            add(new VoiceRecognitionController.RecognitionModel("nl", "https://alphacephei.com/vosk/models/vosk-model-small-nl-0.22.zip", 40441176));
            add(new VoiceRecognitionController.RecognitionModel("pl", "https://alphacephei.com/vosk/models/vosk-model-small-pl-0.22.zip", 52979372));
            add(new VoiceRecognitionController.RecognitionModel("pt", "https://alphacephei.com/vosk/models/vosk-model-small-pt-0.3.zip", 32453112));
            add(new VoiceRecognitionController.RecognitionModel("ru", "https://alphacephei.com/vosk/models/vosk-model-small-ru-0.22.zip", 46236750));
            add(new VoiceRecognitionController.RecognitionModel("tg", "https://alphacephei.com/vosk/models/vosk-model-small-tg-0.22.zip", 51879043));
            add(new VoiceRecognitionController.RecognitionModel("tr", "https://alphacephei.com/vosk/models/vosk-model-small-tr-0.3.zip", 36855784));
            add(new VoiceRecognitionController.RecognitionModel("uk", "https://alphacephei.com/vosk/models/vosk-model-small-uk-v3-small.zip", 143914407));
            add(new VoiceRecognitionController.RecognitionModel("uz", "https://alphacephei.com/vosk/models/vosk-model-small-uz-0.22.zip", 51061189));
            add(new VoiceRecognitionController.RecognitionModel("vi", "https://alphacephei.com/vosk/models/vosk-model-small-vn-0.4.zip", 33656337));
            add(new VoiceRecognitionController.RecognitionModel("zh", "https://alphacephei.com/vosk/models/vosk-model-small-cn-0.22.zip", 43898754));
        }
    }

    /* renamed from: com.exteragram.messenger.speech.recognizers.VoskRecognizer$2, reason: invalid class name */
    public class AnonymousClass2 implements RecognitionListener {
        final /* synthetic */ VoskRecognizer this$0;
        final /* synthetic */ VoiceRecognitionController.RecognitionCallback val$callback;
        final /* synthetic */ Recognizer val$recognizer;

        public AnonymousClass2(VoskRecognizer r1, VoiceRecognitionController.RecognitionCallback r2, Recognizer r3) {
            this.this$0 = r1;
            this.val$callback = r2;
            this.val$recognizer = r3;
        }

        @Override // org.vosk.android.RecognitionListener
        public void onError(Exception r2) {
            FileLog.e("Failed to recognize", r2);
            this.val$callback.onError(r2);
            this.val$recognizer.close();
        }

        @Override // org.vosk.android.RecognitionListener
        public void onFinalResult(String r3) {
            FileLog.d("Final result: " + r3);
            this.val$callback.onCompleted((String) ((Map) ExteraConfig.getGSON().fromJson(r3, Map.class)).get("text"));
            this.val$recognizer.close();
        }

        @Override // org.vosk.android.RecognitionListener
        public void onPartialResult(String r1) {
        }

        @Override // org.vosk.android.RecognitionListener
        public void onResult(String r3) {
            FileLog.d("Result: " + r3);
            if (TextUtils.isEmpty(r3) == false) goto L5;
            return;
        L5:
            this.val$callback.onChunk((String) ((Map) ExteraConfig.getGSON().fromJson(r3, Map.class)).get("text"));
        }

        @Override // org.vosk.android.RecognitionListener
        public void onTimeout() {
        }
    }

    public static /* synthetic */ boolean $r8$lambda$24CEprXJp1zOKeZzj9XgmV9Hp6M(String r0, VoiceRecognitionController.RecognitionModel r1) {
        return r1.getLanguage().equals(r0);
    }

    public static /* synthetic */ IllegalArgumentException $r8$lambda$3Xv1azFJtwThwB0jQsW9JYtX44E(String r3) {
        return new IllegalArgumentException("Model not found: " + r3);
    }

    /* renamed from: $r8$lambda$BkjlPKLR-HILlaZGEU7X7SJ5FRI, reason: not valid java name */
    public static /* synthetic */ boolean m1731$r8$lambda$BkjlPKLRHILlaZGEU7X7SJ5FRI(String r0, VoiceRecognitionController.RecognitionModel r1) {
        return r1.getLanguage().equals(r0);
    }

    public static /* synthetic */ IllegalArgumentException $r8$lambda$MtzCrWBshN7rmxoQ9Z8XAbCYDDA(String r3) {
        return new IllegalArgumentException("Model not found: " + r3);
    }

    public static /* synthetic */ boolean $r8$lambda$OCePRm0KUqV3PSMV_6Yq_0ZSAso(VoskRecognizer r0, VoiceRecognitionController.RecognitionModel r1) {
        return r0.lambda$listDownloadedModels$0(r1);
    }

    /* renamed from: $r8$lambda$f5p2Qnm1JoBv4np-X_eqVy9QyiQ, reason: not valid java name */
    public static /* synthetic */ boolean m1732$r8$lambda$f5p2Qnm1JoBv4npX_eqVy9QyiQ(String r0, VoiceRecognitionController.RecognitionModel r1) {
        return r1.getLanguage().equals(r0);
    }

    /* renamed from: $r8$lambda$pfREJSiBahlF3yJLlAZp-lcczUQ, reason: not valid java name */
    public static /* synthetic */ boolean m1733$r8$lambda$pfREJSiBahlF3yJLlAZplcczUQ(String r0, VoiceRecognitionController.RecognitionModel r1) {
        return r1.getLanguage().equals(r0);
    }

    public VoskRecognizer() {
        this.client = ExteraHttpClient.INSTANCE.getClient();
        this.modelsDir = new File(ApplicationLoader.applicationContext.getExternalFilesDir(null), "Vosk Models");
        this.models = new AnonymousClass1(this);
        this.loadedModels = new ConcurrentHashMap();
    }

    private void deleteDirectory(File r5) {
        if (r5.isDirectory() == false) goto L10;
        File[] r0 = r5.listFiles();
        if (r0 == null) goto L10;
        int r1 = r0.length;
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L10;
        deleteDirectory(r0[r2]);
        r2 = r2 + 1;
    L10:
        if (r5.delete() == true) goto L14;
        FileLog.e("Failed to delete file or directory: " + r5.getAbsolutePath());
        return;
    }

    private boolean isDirectoryEmpty(File r1) {
        String[] r0 = r1.list();
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.length == 0) goto L11;
        return false;
    L11:
        return true;
    }

    private /* synthetic */ boolean lambda$listDownloadedModels$0(VoiceRecognitionController.RecognitionModel r3) {
        File r0 = new File(this.modelsDir, r3.getLanguage());
        File r32 = new File(r0, "model.zip");
        if (r0.exists() == true) goto L5;
        return false;
    L5:
        if (r32.exists() == false) goto L7;
        return false;
    L7:
        if (isDirectoryEmpty(r0) == true) goto L13;
        return true;
    L13:
        return false;
    }

    private static void unpackZip(String r8, String r9) {
        File r0 = new File(r9);
        if (r0.exists() == true) goto L5;
        r0.mkdirs();
    L5:
        byte[] r92 = new byte[1024];
        ZipInputStream r1 = new ZipInputStream(new FileInputStream(r8));
        ZipEntry r82 = r1.getNextEntry();
        if (r82 == null) goto L8;
        String r4 = r82.getName().split("/")[0];
    L9:
        if (r82 == null) goto L21;
        if (r82.getName().equals(r4 + "/") == true) goto L12;
        File r6 = new File(r0, r82.getName().substring(r4.length() + 1));
        new File(r6.getParent()).mkdirs();
        if (r82.isDirectory() == true) goto L20;
        FileOutputStream r83 = new FileOutputStream(r6);
    L16:
        int r5 = r1.read(r92);
        if (r5 <= 0) goto L19;
        r83.write(r92, 0, r5);
        goto L16
    L19:
        r83.close();
    L20:
        r82 = r1.getNextEntry();
        goto L9
    L12:
        r82 = r1.getNextEntry();
        goto L9
    L21:
        r1.closeEntry();
        r1.close();
        return;
    L8:
        r4 = null;
        goto L9
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        unloadModels();
    }

    @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionProvider
    public void deleteModel(String r4) {
        VoiceRecognitionController.RecognitionModel r42 = this.models.stream().filter(new VoskRecognizer$$ExternalSyntheticLambda4(r4)).findFirst().orElseThrow(new VoskRecognizer$$ExternalSyntheticLambda5(r4));
        Model r0 = this.loadedModels.remove(r42.getLanguage());
        if (r0 == null) goto L5;
        r0.close();
    L5:
        File r02 = new File(this.modelsDir, r42.getLanguage());
        if (r02.exists() == false) goto L9;
        deleteDirectory(r02);
        return;
    L9:
        CstInsn$$ExternalSyntheticBUOutline0.m("Model is not downloaded: ", r42.getLanguage());
    }

    @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionProvider
    public void downloadModel(String r8, VoiceRecognitionController.DownloadModelCallback r9) {
        VoiceRecognitionController.RecognitionModel r82 = this.models.stream().filter(new VoskRecognizer$$ExternalSyntheticLambda6(r8)).findFirst().orElseThrow(new VoskRecognizer$$ExternalSyntheticLambda7(r8));
        File r1 = new File(this.modelsDir, r82.getLanguage());
        if (new File(r1, "model.zip").exists() == false) goto L10;
        deleteDirectory(r1);     // Catch: IOException -> L6
    L6:
        e = move-exception;
        r9.onError(new IOException("Failed to delete existing model directory", e));
        return;
    L10:
        if (r1.exists() == true) goto L58;
        r1.mkdirs();
    L58:
        Response r7 = this.client.newCall(new Request.Builder().url(r82.getUrl()).build()).execute();     // Catch: Exception -> L15
        if (r7.isSuccessful() == true) goto L17;
        FileLog.e("Failed to download: " + r7);     // Catch: Exception -> L15
    L17:
        File r83 = new File(r1, "model.zip");     // Catch: Exception -> L15
        InputStream r0 = r7.body().byteStream();     // Catch: Exception -> L15
        FileOutputStream r2 = new FileOutputStream(r83);     // Catch: Throwable -> L36
        long r3 = r7.body().contentLength();     // Catch: Throwable -> L24
        byte[] r72 = new byte[4096];     // Catch: Throwable -> L24
    L20:
        int r5 = r0.read(r72);     // Catch: Throwable -> L24
        if (r5 == (-1)) goto L26;
        r2.write(r72, 0, r5);     // Catch: Throwable -> L24
        r9.onProgress(r83.length() / r3);     // Catch: Throwable -> L24
        goto L20
    L26:
        r2.close();     // Catch: Throwable -> L36
        r0.close();     // Catch: Exception -> L15
        unpackZip(r83.getAbsolutePath(), r1.getAbsolutePath());     // Catch: Exception -> L15
    L32:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L15
    L34:
        r9.onCompleted();     // Catch: Exception -> L15
        return;
    L29:
        if (r83.delete() == true) goto L34;
        r83.deleteOnExit();     // Catch: Exception -> L32
    L24:
        th = move-exception;
        r2.close();     // Catch: Throwable -> L40
    L42:
        throw th;     // Catch: Throwable -> L36
    L40:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L36
    L36:
        th = move-exception;
        if (r0 != null) goto L59;
    L48:
        throw th;     // Catch: Exception -> L15
    L59:
        r0.close();     // Catch: Throwable -> L46
    L46:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L15
    L15:
        e = move-exception;
        r9.onError(e);
    }

    @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionProvider
    public boolean hasLoadedModels() {
        return !this.loadedModels.isEmpty();
    }

    @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionProvider
    public List<VoiceRecognitionController.RecognitionModel> listAvailableModels() {
        return this.models;
    }

    @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionProvider
    public List<VoiceRecognitionController.RecognitionModel> listDownloadedModels() {
        return (List) this.models.stream().filter(new VoskRecognizer$$ExternalSyntheticLambda0(this)).collect(Collectors.toList());
    }

    @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionProvider
    public void recognize(String r7, String r8, VoiceRecognitionController.RecognitionCallback r9) {
        if (this.models.stream().filter(new VoskRecognizer$$ExternalSyntheticLambda2(r8)).findFirst().orElse(null) != null) goto L7;
        r9.onLanguageNotSupported(r8);
        return;
    L7:
        if (listDownloadedModels().stream().filter(new VoskRecognizer$$ExternalSyntheticLambda3(r8)).findFirst().orElse(null) != null) goto L19;
        r9.onLanguageNotDownloaded(r8);
        return;
    L19:
    L13:
        e = move-exception;
        FileLog.e("Failed to recognize", e);
        r9.onError(e);
        return;
    L11:
        if (this.loadedModels.containsKey(r8) == true) goto L15;
        FileLog.d("Loading model: " + r8);     // Catch: IOException -> L13
        LibVosk.setLogLevel(LogLevel.INFO);     // Catch: IOException -> L13
        this.loadedModels.put(r8, new Model(this.modelsDir + "/" + r8));     // Catch: IOException -> L13
        StringBuilder r2 = new StringBuilder("Model loaded: ");     // Catch: IOException -> L13
        r2.append(r8);     // Catch: IOException -> L13
        FileLog.d(r2.toString());     // Catch: IOException -> L13
    L15:
        Model r82 = this.loadedModels.get(r8);     // Catch: IOException -> L13
        InputStream r1 = FormatConverter.extractAndConvertToPcm(r7, false);     // Catch: IOException -> L13
        int r22 = FormatConverter.getSampleRate(r7);     // Catch: IOException -> L13
        FileLog.d("Recognizing: " + r7);     // Catch: IOException -> L13
        float r0 = (float) r22;     // Catch: IOException -> L13
        Recognizer r72 = new Recognizer(r82, r0);     // Catch: IOException -> L13
        new SpeechStreamService(r72, r1, r0).start(new AnonymousClass2(this, r9, r72));     // Catch: IOException -> L13
    }

    @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionProvider
    public void unloadModels() {
        this.loadedModels.values().forEach(new VoskRecognizer$$ExternalSyntheticLambda1());
        this.loadedModels.clear();
    }
}
