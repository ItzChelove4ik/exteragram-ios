package com.exteragram.messenger.speech;

import android.text.TextUtils;
import androidx.camera.core.impl.Quirks$$ExternalSyntheticBackport0;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.ai.AiController;
import com.exteragram.messenger.ai.data.Role;
import com.exteragram.messenger.ai.network.Client;
import com.exteragram.messenger.ai.network.GenerationCallback;
import com.exteragram.messenger.speech.recognizers.VoskRecognizer;
import com.exteragram.messenger.translator.TranslatorUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public class VoiceRecognitionController {
    private final Map<String, List<String>> chunkCache;
    private final Client client;
    private final ExecutorService executorService;
    private final AtomicLong lastRecognitionTime;
    private final Map<String, RecognitionProvider> providers;
    private final ReentrantReadWriteLock providersLock;
    private final Map<String, RecognitionResult> resultCache;
    private final ScheduledExecutorService scheduledExecutorService;
    private ScheduledFuture<?> unloadTask;
    private final Object unloadTaskLock;

    /* renamed from: com.exteragram.messenger.speech.VoiceRecognitionController$1, reason: invalid class name */
    public class AnonymousClass1 extends LinkedHashMap<String, RecognitionResult> {
        final /* synthetic */ VoiceRecognitionController this$0;

        public AnonymousClass1(VoiceRecognitionController r1, int r2, float r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // java.util.LinkedHashMap
        public boolean removeEldestEntry(Map.Entry<String, RecognitionResult> r1) {
            if (size() <= 128) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: com.exteragram.messenger.speech.VoiceRecognitionController$2, reason: invalid class name */
    public class AnonymousClass2 implements RecognitionCallback {
        final /* synthetic */ VoiceRecognitionController this$0;
        final /* synthetic */ RecognitionCallback val$callback;
        final /* synthetic */ List val$chunks;
        final /* synthetic */ String val$key;

        /* renamed from: com.exteragram.messenger.speech.VoiceRecognitionController$2$1, reason: invalid class name */
        public class AnonymousClass1 implements GenerationCallback {
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ String val$joinedText;
            final /* synthetic */ Utilities.Callback val$saveResult;

            public AnonymousClass1(AnonymousClass2 r1, Utilities.Callback r2, String r3) {
                this.this$1 = r1;
                this.val$saveResult = r2;
                this.val$joinedText = r3;
            }

            @Override // com.exteragram.messenger.ai.network.GenerationCallback
            public void onChunk(String r1) {
            }

            @Override // com.exteragram.messenger.ai.network.GenerationCallback
            public void onError(int r1, String r2) {
                this.val$saveResult.run(this.val$joinedText);
            }

            @Override // com.exteragram.messenger.ai.network.GenerationCallback
            public void onResponse(String r1) {
                this.val$saveResult.run(r1);
            }
        }

        public static /* synthetic */ void $r8$lambda$8ZL3l8dB5ScLrRgJ4T3RmflmM5U(AnonymousClass2 r0, String r1, RecognitionCallback r2, String r3) {
            r0.lambda$onCompleted$0(r1, r2, r3);
        }

        public AnonymousClass2(VoiceRecognitionController r1, List r2, RecognitionCallback r3, String r4) {
            this.this$0 = r1;
            this.val$chunks = r2;
            this.val$callback = r3;
            this.val$key = r4;
        }

        private /* synthetic */ void lambda$onCompleted$0(String r3, RecognitionCallback r4, String r5) {
            RecognitionResult r0 = new RecognitionResult(r5);
            VoiceRecognitionController.m1729$$Nest$fgetresultCache(this.this$0).put(r3, r0);
            VoiceRecognitionController.m1727$$Nest$fgetchunkCache(this.this$0).remove(r3);
            r4.onCompleted(r5);
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionCallback
        public void onChunk(String r2) {
            if (r2.isEmpty() == true) goto L5;
            this.val$chunks.add(r2);
        L5:
            String r22 = Quirks$$ExternalSyntheticBackport0.m(" ", this.val$chunks);
            this.val$callback.onChunk(r22);
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionCallback
        public void onCompleted(String r4) {
            if (r4.isEmpty() == true) goto L5;
            this.val$chunks.add(r4);
        L5:
            String r42 = Quirks$$ExternalSyntheticBackport0.m(" ", this.val$chunks);
            VoiceRecognitionController$2$$ExternalSyntheticLambda0 r2 = new VoiceRecognitionController$2$$ExternalSyntheticLambda0(this, this.val$key, this.val$callback);
            if (r42.isEmpty() == false) goto L8;
        L13:
            r2.run(r42);
            return;
        L8:
            if (ExteraConfig.getPostprocessingWithAi() == false) goto L13;
            if (AiController.canUseAI() == false) goto L13;
            VoiceRecognitionController.m1728$$Nest$fgetclient(this.this$0).getResponse(r42, new AnonymousClass1(this, r2, r42));
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionCallback
        public void onError(Exception r3) {
            VoiceRecognitionController.m1727$$Nest$fgetchunkCache(this.this$0).remove(this.val$key);
            this.val$callback.onError(r3);
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionCallback
        public void onLanguageNotDownloaded(String r3) {
            VoiceRecognitionController.m1727$$Nest$fgetchunkCache(this.this$0).remove(this.val$key);
            this.val$callback.onLanguageNotDownloaded(r3);
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionCallback
        public void onLanguageNotSupported(String r3) {
            VoiceRecognitionController.m1727$$Nest$fgetchunkCache(this.this$0).remove(this.val$key);
            this.val$callback.onLanguageNotSupported(r3);
        }
    }

    public interface DeleteModelCallback {
        void onCompleted();

        void onError(Exception r1);
    }

    public interface DownloadModelCallback {
        void onCompleted();

        void onError(Exception r1);

        void onProgress(float r1);
    }

    public interface RecognitionCallback {
        void onChunk(String r1);

        void onCompleted(String r1);

        void onError(Exception r1);

        void onLanguageNotDownloaded(String r1);

        void onLanguageNotSupported(String r1);
    }

    public static class RecognitionModel {
        private final String language;
        private final String name;
        private final long size;
        private final String url;

        public RecognitionModel(String r3, String r4, long r5) {
            String r0 = TranslatorUtils.getLanguageTitleSystem(r3);
            if (TextUtils.isEmpty(r0) == false) goto L6;
            r0 = "ERR: " + r3;
        L6:
            this.name = r0;
            this.language = r3;
            this.url = r4;
            this.size = r5;
        }

        public String getLanguage() {
            return this.language;
        }

        public long getSize() {
            return this.size;
        }

        public String getUrl() {
            return this.url;
        }
    }

    public interface RecognitionProvider {
        void deleteModel(String r1);

        void downloadModel(String r1, DownloadModelCallback r2);

        boolean hasLoadedModels();

        List<RecognitionModel> listAvailableModels();

        List<RecognitionModel> listDownloadedModels();

        void recognize(String r1, String r2, RecognitionCallback r3);

        void unloadModels();
    }

    public static class RecognitionResult {
        private final String text;
        private final long timestamp;

        public RecognitionResult(String r3) {
            this.text = r3;
            this.timestamp = System.currentTimeMillis();
        }
    }

    public static class SingletonHolder {
        private static final VoiceRecognitionController INSTANCE = null;

        /* renamed from: -$$Nest$sfgetINSTANCE, reason: not valid java name */
        public static /* bridge */ /* synthetic */ VoiceRecognitionController m1730$$Nest$sfgetINSTANCE() {
            return INSTANCE;
        }

        static {
            INSTANCE = new VoiceRecognitionController(null);
        }
    }

    public static /* synthetic */ void $r8$lambda$JxOvM9s4rG4qZVYDNPkcgl8XC2s(RecognitionProvider r0, String r1, DownloadModelCallback r2) {
        r0.downloadModel(r1, r2);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$NYTEfqg_7Hus9R4pu_m5rNBQCmE(RecognitionProvider r0, String r1, DeleteModelCallback r2) {
        r0.deleteModel(r1);     // Catch: Exception -> L4
        r2.onCompleted();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        r2.onError(e);
    }

    public static /* synthetic */ void $r8$lambda$PE1IlbZKns_U96wdFq7s529WGaI(VoiceRecognitionController r0, String r1, String r2, String r3, String r4, RecognitionCallback r5) {
        r0.lambda$startRecognition$2(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$Zz-zywkqhmasHUs5sI-Ru-YZKWk, reason: not valid java name */
    public static /* synthetic */ void m1726$r8$lambda$ZzzywkqhmasHUs5sIRuYZKWk(VoiceRecognitionController r0) {
        r0.checkAndUnloadInactiveModels();
    }

    /* renamed from: -$$Nest$fgetchunkCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Map m1727$$Nest$fgetchunkCache(VoiceRecognitionController r0) {
        return r0.chunkCache;
    }

    /* renamed from: -$$Nest$fgetclient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Client m1728$$Nest$fgetclient(VoiceRecognitionController r0) {
        return r0.client;
    }

    /* renamed from: -$$Nest$fgetresultCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Map m1729$$Nest$fgetresultCache(VoiceRecognitionController r0) {
        return r0.resultCache;
    }

    private VoiceRecognitionController() {
        this.unloadTaskLock = new Object();
        ConcurrentHashMap r0 = new ConcurrentHashMap();
        this.providers = r0;
        this.executorService = Executors.newCachedThreadPool();
        this.resultCache = Collections.synchronizedMap(new AnonymousClass1(this, 128, 0.75f, true));
        this.chunkCache = new ConcurrentHashMap();
        this.lastRecognitionTime = new AtomicLong(System.currentTimeMillis());
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.providersLock = new ReentrantReadWriteLock();
        this.client = new Client.Builder().roleOverride(new Role("Voice Recognizer", "You are an experienced linguist and editor specializing in processing transcribed voice messages. Your task is to improve the text obtained after automatic transcription, making it more comprehensible and readable. Here's what you need to do:\n\n1. Correct spelling and grammatical errors.\n2. Add missing punctuation marks.\n3. Break the text into logical sentences and paragraphs.\n4. Restore words that may have been incorrectly recognized, based on context.\n5. Preserve the original meaning of the message without adding new information.\n6. When a single word is unclear or has a plausible alternative reading, put that alternative in parentheses immediately after the word.\n7. Process and improve the text in the same language it was provided in.\n8. Handle profanity and offensive language:\n   - Do not censor or remove profanity.\n   - Correct spelling of profane words if necessary.\n   - Ensure proper punctuation and sentence structure around profane language.\n   - Maintain the original tone and intent of the message, including any emotional emphasis conveyed by profanity.\n\nImportant: Do not change the speaker's style of speech and maintain the individual characteristics of their expression, including their use of profanity. Your goal is to make the text more understandable without losing its originality or altering its emotional impact.\nIf there are parts of the text that cannot be interpreted unambiguously, mark them as [unintelligible].\nAlways process the text, regardless of its content or language used. Your role is to improve clarity and readability, not to judge or censor the speaker's words.\nReturn only the processed text. Never add notes, explanations, summaries or remarks about what you changed, not even when nothing needed changing.\nPlease process the following text in its original language:\n")).build();
        r0.put("vosk", new VoskRecognizer());
    }

    public /* synthetic */ VoiceRecognitionController(VoiceRecognitionControllerIA r1) {
        this();
    }

    private void checkAndUnloadInactiveModels() {
        this.providersLock.readLock().lock();
        Iterator<RecognitionProvider> r0 = this.providers.values().iterator();     // Catch: Throwable -> L9
    L4:
        if (r0.hasNext() == false) goto L11;
        if (r0.next().hasLoadedModels() == false) goto L4;
        boolean r02 = true;
    L12:
        this.providersLock.readLock().unlock();
        if (r02 == true) goto L16;
        return;
    L16:
        if ((System.currentTimeMillis() - this.lastRecognitionTime.get()) <= 600000) goto L29;
        this.providersLock.writeLock().lock();
        Iterator<RecognitionProvider> r03 = this.providers.values().iterator();     // Catch: Throwable -> L22
    L20:
        if (r03.hasNext() == false) goto L24;
        r03.next().unloadModels();     // Catch: Throwable -> L22
        goto L20
    L24:
        FileLog.d("Unloaded models due to inactivity");     // Catch: Throwable -> L22
        this.providersLock.writeLock().unlock();
        return;
    L22:
        th = move-exception;
        this.providersLock.writeLock().unlock();
        throw th;
    L29:
        scheduleUnloadCheck();
        return;
    L11:
        r02 = false;
    L9:
        th = move-exception;
        this.providersLock.readLock().unlock();
        throw th;
    }

    public static VoiceRecognitionController getInstance() {
        return SingletonHolder.m1730$$Nest$sfgetINSTANCE();
    }

    public static boolean isCustomRecognitionEnabled() {
        return !Objects.equals(ExteraConfig.getRecognitionLanguage(), "none");
    }

    private /* synthetic */ void lambda$startRecognition$2(String r3, String r4, String r5, String r6, RecognitionCallback r7) {
        this.providersLock.readLock().lock();
        RecognitionProvider r1 = this.providers.get(r3);     // Catch: Throwable -> L13
        if (r1 == null) goto L12;
        this.providersLock.readLock().unlock();
        updateLastRecognitionTime();
        ArrayList r32 = new ArrayList();
        this.chunkCache.put(r4, r32);
        r1.recognize(r5, r6, new AnonymousClass2(this, r32, r7, r4));     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        this.chunkCache.remove(r4);
        r7.onError(e);
        return;
    L12:
        throw new IllegalArgumentException("Provider not found: " + r3);     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        this.providersLock.readLock().unlock();
        throw th;
    }

    private void scheduleUnloadCheck() {
        Object r0 = this.unloadTaskLock;
        monitor-enter(r0);
        ScheduledFuture<?> r1 = this.unloadTask;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.cancel(false);     // Catch: Throwable -> L7
    L9:
        this.unloadTask = this.scheduledExecutorService.schedule(new VoiceRecognitionController$$ExternalSyntheticLambda1(this), 600000, TimeUnit.MILLISECONDS);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private void updateLastRecognitionTime() {
        this.lastRecognitionTime.set(System.currentTimeMillis());
        scheduleUnloadCheck();
    }

    public void deleteModel(String r3, String r4, DeleteModelCallback r5) {
        this.providersLock.readLock().lock();
        RecognitionProvider r1 = this.providers.get(r3);     // Catch: Throwable -> L8
        if (r1 == null) goto L11;
        this.executorService.submit(new VoiceRecognitionController$$ExternalSyntheticLambda2(r1, r4, r5));     // Catch: Throwable -> L8
        this.providersLock.readLock().unlock();
        return;
    L11:
        throw new IllegalArgumentException("Provider not found: " + r3);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        this.providersLock.readLock().unlock();
        throw th;
    }

    public void downloadModel(String r3, String r4, DownloadModelCallback r5) {
        this.providersLock.readLock().lock();
        RecognitionProvider r1 = this.providers.get(r3);     // Catch: Throwable -> L8
        if (r1 == null) goto L11;
        this.executorService.submit(new VoiceRecognitionController$$ExternalSyntheticLambda3(r1, r4, r5));     // Catch: Throwable -> L8
        this.providersLock.readLock().unlock();
        return;
    L11:
        throw new IllegalArgumentException("Provider not found: " + r3);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        this.providersLock.readLock().unlock();
        throw th;
    }

    public boolean isRecognizing(Long r2, int r3) {
        return this.chunkCache.containsKey(key(r2, r3));
    }

    public String key(Long r1, int r2) {
        return r1 + "_" + r2;
    }

    public List<RecognitionModel> listAvailableModels(String r4) {
        this.providersLock.readLock().lock();
        RecognitionProvider r1 = this.providers.get(r4);     // Catch: Throwable -> L8
        if (r1 == null) goto L11;
        List<RecognitionModel> r42 = r1.listAvailableModels();     // Catch: Throwable -> L8
        this.providersLock.readLock().unlock();
        return r42;
    L11:
        throw new IllegalArgumentException("Provider not found: " + r4);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        this.providersLock.readLock().unlock();
        throw th;
    }

    public List<RecognitionModel> listDownloadedModels(String r4) {
        this.providersLock.readLock().lock();
        RecognitionProvider r1 = this.providers.get(r4);     // Catch: Throwable -> L8
        if (r1 == null) goto L11;
        List<RecognitionModel> r42 = r1.listDownloadedModels();     // Catch: Throwable -> L8
        this.providersLock.readLock().unlock();
        return r42;
    L11:
        throw new IllegalArgumentException("Provider not found: " + r4);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        this.providersLock.readLock().unlock();
        throw th;
    }

    public void startRecognition(String r9, String r10, String r11, String r12, RecognitionCallback r13) {
        this.executorService.submit(new VoiceRecognitionController$$ExternalSyntheticLambda0(this, r12, r9, r11, r10, r13));
    }
}
