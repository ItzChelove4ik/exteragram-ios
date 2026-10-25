package com.exteragram.messenger.translator.core;

import android.os.SystemClock;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.translator.core.ProviderResponse;
import com.exteragram.messenger.translator.core.TranslationError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003/01B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002JH\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u00122\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\b\u0010\u001a\u001a\u00020\u000eH\u0007J\u001c\u0010\u001b\u001a\u00020\u000e2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00160\u001dH\u0002J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\bH\u0002J\u0012\u0010!\u001a\u0004\u0018\u00010\u001e2\u0006\u0010 \u001a\u00020\bH\u0002J\u0018\u0010\"\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\b2\u0006\u0010#\u001a\u00020\u0010H\u0002J\u0018\u0010$\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\b2\u0006\u0010%\u001a\u00020\u001eH\u0002J \u0010&\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\b2\u0006\u0010%\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020(H\u0002J\u0018\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J2\u0010.\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\b2\u0006\u0010%\u001a\u00020\u001e2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00122\b\u0010'\u001a\u0004\u0018\u00010(H\u0002R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/exteragram/messenger/translator/core/TranslationDispatcher;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "lock", "gates", "Ljava/util/HashMap;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/translator/core/TranslationDispatcher$Gate;", "Lkotlin/collections/HashMap;", "gateOf", "translator", "Lcom/exteragram/messenger/translator/core/HttpTranslator;", "enqueue", _UrlKt.FRAGMENT_ENCODE_SET, "tag", _UrlKt.FRAGMENT_ENCODE_SET, "texts", _UrlKt.FRAGMENT_ENCODE_SET, "fromLang", "toLang", PluginsConstants.MenuItemProperties.PRIORITY, _UrlKt.FRAGMENT_ENCODE_SET, "completion", "Lcom/exteragram/messenger/translator/core/TranslationDispatcher$Completion;", "cancel", "cancelAll", "cancelMatching", "matches", "Lkotlin/Function1;", "Lcom/exteragram/messenger/translator/core/TranslationDispatcher$Job;", "pump", "gate", "nextJob", "schedulePump", "delay", "start", "job", "retryOrFail", "error", "Lcom/exteragram/messenger/translator/core/TranslationError;", "backoff", "limits", "Lcom/exteragram/messenger/translator/core/ProviderLimits;", "attempt", _UrlKt.FRAGMENT_ENCODE_SET, "finish", "Completion", "Job", "Gate", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTranslationDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TranslationDispatcher.kt\ncom/exteragram/messenger/translator/core/TranslationDispatcher\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,251:1\n410#2,3:252\n413#2,4:256\n1#3:255\n777#4:260\n873#4,2:261\n1915#4,2:263\n1915#4,2:265\n*S KotlinDebug\n*F\n+ 1 TranslationDispatcher.kt\ncom/exteragram/messenger/translator/core/TranslationDispatcher\n*L\n59#1:252,3\n59#1:256,4\n97#1:260\n97#1:261,2\n108#1:263,2\n111#1:265,2\n*E\n"})
/* loaded from: classes4.dex */
public final class TranslationDispatcher {
    public static final TranslationDispatcher INSTANCE = null;
    private static final HashMap<String, Gate> gates = null;
    private static final Object lock = null;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/translator/core/TranslationDispatcher$Completion;", _UrlKt.FRAGMENT_ENCODE_SET, "onDone", _UrlKt.FRAGMENT_ENCODE_SET, "texts", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "error", "Lcom/exteragram/messenger/translator/core/TranslationError;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Completion {
        void onDone(List<String> r1, TranslationError r2);
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR!\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\n0\u000ej\b\u0012\u0004\u0012\u00020\n`\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u001a\u0010\u001b\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001a\u0010\u001e\u001a\u00020\u001fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lcom/exteragram/messenger/translator/core/TranslationDispatcher$Gate;", _UrlKt.FRAGMENT_ENCODE_SET, "limits", "Lcom/exteragram/messenger/translator/core/ProviderLimits;", "<init>", "(Lcom/exteragram/messenger/translator/core/ProviderLimits;)V", "getLimits", "()Lcom/exteragram/messenger/translator/core/ProviderLimits;", "queue", "Lkotlin/collections/ArrayDeque;", "Lcom/exteragram/messenger/translator/core/TranslationDispatcher$Job;", "getQueue", "()Lkotlin/collections/ArrayDeque;", "running", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getRunning", "()Ljava/util/ArrayList;", "lastStartedAt", _UrlKt.FRAGMENT_ENCODE_SET, "getLastStartedAt", "()J", "setLastStartedAt", "(J)V", "cooldownUntil", "getCooldownUntil", "setCooldownUntil", "scheduledPumpAt", "getScheduledPumpAt", "setScheduledPumpAt", "pumpRunnable", "Ljava/lang/Runnable;", "getPumpRunnable", "()Ljava/lang/Runnable;", "setPumpRunnable", "(Ljava/lang/Runnable;)V", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Gate {
        private long cooldownUntil;
        private long lastStartedAt;
        private final ProviderLimits limits;
        public Runnable pumpRunnable;
        private final ArrayDeque<Job> queue;
        private final ArrayList<Job> running;
        private long scheduledPumpAt;

        public Gate(ProviderLimits r1) {
            this.limits = r1;
            this.queue = new ArrayDeque();
            this.running = new ArrayList();
        }

        public final long getCooldownUntil() {
            return this.cooldownUntil;
        }

        public final long getLastStartedAt() {
            return this.lastStartedAt;
        }

        public final ProviderLimits getLimits() {
            return this.limits;
        }

        public final Runnable getPumpRunnable() {
            Runnable r0 = this.pumpRunnable;
            if (r0 == null) goto L5;
            return r0;
        L5:
            return null;
        }

        public final ArrayDeque<Job> getQueue() {
            return this.queue;
        }

        public final ArrayList<Job> getRunning() {
            return this.running;
        }

        public final long getScheduledPumpAt() {
            return this.scheduledPumpAt;
        }

        public final void setCooldownUntil(long r1) {
            this.cooldownUntil = r1;
        }

        public final void setLastStartedAt(long r1) {
            this.lastStartedAt = r1;
        }

        public final void setPumpRunnable(Runnable r1) {
            this.pumpRunnable = r1;
        }

        public final void setScheduledPumpAt(long r1) {
            this.scheduledPumpAt = r1;
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010&\u001a\u0004\u0018\u00010'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u0006,"}, d2 = {"Lcom/exteragram/messenger/translator/core/TranslationDispatcher$Job;", _UrlKt.FRAGMENT_ENCODE_SET, "translator", "Lcom/exteragram/messenger/translator/core/HttpTranslator;", "tag", _UrlKt.FRAGMENT_ENCODE_SET, "texts", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "fromLang", "toLang", "completion", "Lcom/exteragram/messenger/translator/core/TranslationDispatcher$Completion;", "<init>", "(Lcom/exteragram/messenger/translator/core/HttpTranslator;JLjava/util/List;Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/translator/core/TranslationDispatcher$Completion;)V", "getTranslator", "()Lcom/exteragram/messenger/translator/core/HttpTranslator;", "getTag", "()J", "getTexts", "()Ljava/util/List;", "getFromLang", "()Ljava/lang/String;", "getToLang", "getCompletion", "()Lcom/exteragram/messenger/translator/core/TranslationDispatcher$Completion;", "attempt", _UrlKt.FRAGMENT_ENCODE_SET, "getAttempt", "()I", "setAttempt", "(I)V", "cancelled", _UrlKt.FRAGMENT_ENCODE_SET, "getCancelled", "()Z", "setCancelled", "(Z)V", "call", "Lokhttp3/Call;", "getCall", "()Lokhttp3/Call;", "setCall", "(Lokhttp3/Call;)V", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Job {
        private int attempt;
        private Call call;
        private boolean cancelled;
        private final Completion completion;
        private final String fromLang;
        private final long tag;
        private final List<String> texts;
        private final String toLang;
        private final HttpTranslator translator;

        public Job(HttpTranslator r1, long r2, List<String> r4, String r5, String r6, Completion r7) {
            this.translator = r1;
            this.tag = r2;
            this.texts = r4;
            this.fromLang = r5;
            this.toLang = r6;
            this.completion = r7;
        }

        public final int getAttempt() {
            return this.attempt;
        }

        public final Call getCall() {
            return this.call;
        }

        public final boolean getCancelled() {
            return this.cancelled;
        }

        public final Completion getCompletion() {
            return this.completion;
        }

        public final String getFromLang() {
            return this.fromLang;
        }

        public final long getTag() {
            return this.tag;
        }

        public final List<String> getTexts() {
            return this.texts;
        }

        public final String getToLang() {
            return this.toLang;
        }

        public final HttpTranslator getTranslator() {
            return this.translator;
        }

        public final void setAttempt(int r1) {
            this.attempt = r1;
        }

        public final void setCall(Call r1) {
            this.call = r1;
        }

        public final void setCancelled(boolean r1) {
            this.cancelled = r1;
        }
    }

    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/exteragram/messenger/translator/core/TranslationDispatcher$start$1", "Lokhttp3/Callback;", "onFailure", _UrlKt.FRAGMENT_ENCODE_SET, "call", "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", PluginsConstants.RESPONSE, "Lokhttp3/Response;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nTranslationDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TranslationDispatcher.kt\ncom/exteragram/messenger/translator/core/TranslationDispatcher$start$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,251:1\n1#2:252\n*E\n"})
    /* renamed from: com.exteragram.messenger.translator.core.TranslationDispatcher$start$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Callback {
        final /* synthetic */ Gate $gate;
        final /* synthetic */ Job $job;

        public AnonymousClass1(Gate r1, Job r2) {
            this.$gate = r1;
            this.$job = r2;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call r2, IOException r3) {
            if (r2.isCanceled() == false) goto L6;
            TranslationDispatcher.access$finish(TranslationDispatcher.INSTANCE, this.$gate, this.$job, null, null);
            return;
        L6:
            FileLog.e(r3);
            TranslationDispatcher.access$retryOrFail(TranslationDispatcher.INSTANCE, this.$gate, this.$job, TranslationError.Transient.INSTANCE);
        }

        @Override // okhttp3.Callback
        public void onResponse(Call r3, Response r4) {
            Job r0 = this.$job;     // Catch: Exception -> L7
            ProviderResponse r02 = r0.getTranslator().parseResponse(r4, r0.getTexts().size());     // Catch: Throwable -> L9
            CloseableKt.closeFinally(r4, null);     // Catch: Exception -> L7
        L16:
            if ((r02 instanceof ProviderResponse.Success) == false) goto L19;
            TranslationDispatcher.access$finish(TranslationDispatcher.INSTANCE, this.$gate, this.$job, ((ProviderResponse.Success) r02).getTexts(), null);
            return;
        L19:
            if ((r02 instanceof ProviderResponse.Failure) == false) goto L22;
            TranslationDispatcher.access$retryOrFail(TranslationDispatcher.INSTANCE, this.$gate, this.$job, ((ProviderResponse.Failure) r02).getError());
            return;
        L22:
            LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
            return;
        L9:
            th = move-exception;
            throw th;     // Catch: Throwable -> L11
        L11:
            th = move-exception;
            CloseableKt.closeFinally(r4, th);     // Catch: Exception -> L7
            throw th;     // Catch: Exception -> L7
        L7:
            e = move-exception;
            FileLog.e(e);
            r02 = new ProviderResponse.Failure(TranslationError.Transient.INSTANCE);
            goto L16
        }
    }

    /* renamed from: $r8$lambda$1NNHS5Ggi1pRMlx-GIDWuX5Nf2g, reason: not valid java name */
    public static void m1743$r8$lambda$1NNHS5Ggi1pRMlxGIDWuX5Nf2g(Gate r1) {
        INSTANCE.pump(r1);
    }

    public static void $r8$lambda$8VAxblk5cSrgLEb2IT851gv_Cp4(Gate r1) {
        INSTANCE.pump(r1);
    }

    public static void $r8$lambda$9htQioRTsfcUi6eodxGqrWMlsrI(Gate r1) {
        INSTANCE.pump(r1);
    }

    /* renamed from: $r8$lambda$SS1nJLW7UIdxy0QzW3Dg2Z8tS-4, reason: not valid java name */
    public static void m1744$r8$lambda$SS1nJLW7UIdxy0QzW3Dg2Z8tS4(Completion r2) {
        r2.onDone(CollectionsKt.emptyList(), null);
    }

    /* renamed from: $r8$lambda$TgTB89gtHaOSQaRG-xWzrekQBXU, reason: not valid java name */
    public static void m1745$r8$lambda$TgTB89gtHaOSQaRGxWzrekQBXU(Job r2) {
        r2.getCompletion().onDone(null, TranslationError.Cancelled.INSTANCE);
    }

    /* renamed from: $r8$lambda$VGX54Q4i1Cskg-WYy7zprfcllGM, reason: not valid java name */
    public static void m1746$r8$lambda$VGX54Q4i1CskgWYy7zprfcllGM(Gate r1) {
        INSTANCE.pump(r1);
    }

    /* renamed from: $r8$lambda$YgkMm1tTSBJeHRcSiH6yxkP-WcU, reason: not valid java name */
    public static boolean m1747$r8$lambda$YgkMm1tTSBJeHRcSiH6yxkPWcU(long r2, Job r4) {
        if (r4.getTag() != r2) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean $r8$lambda$dlGT4IYH9GaeUwn58jwdjFq8gMo(Job r0) {
        return true;
    }

    public static void $r8$lambda$fKvaJXZz4Z9eg26HlE3NWmdUvgY(boolean r0, Job r1, List r2, TranslationError r3) {
        if (r0 == false) goto L5;
        r1.getCompletion().onDone(null, TranslationError.Cancelled.INSTANCE);
        return;
    L5:
        r1.getCompletion().onDone(r2, r3);
    }

    public static void $r8$lambda$zfieef9Kbhl4WA3IigC4KtjN0KQ(ArrayList r5) {
        int r0 = r5.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Object r2 = r5.get(r1);
        r1 = r1 + 1;
        ((Job) r2).getCompletion().onDone(null, TranslationError.Cancelled.INSTANCE);
        goto L3
    }

    static {
        INSTANCE = new TranslationDispatcher();
        lock = new Object();
        gates = new HashMap();
    }

    private TranslationDispatcher() {
    }

    public static final /* synthetic */ void access$finish(TranslationDispatcher r0, Gate r1, Job r2, List r3, TranslationError r4) {
        r0.finish(r1, r2, r3, r4);
    }

    public static final /* synthetic */ void access$retryOrFail(TranslationDispatcher r0, Gate r1, Job r2, TranslationError r3) {
        r0.retryOrFail(r1, r2, r3);
    }

    private final long backoff(ProviderLimits r5, int r6) {
        return Math.min(r5.getBaseBackoffMs() << RangesKt.coerceIn(r6 - 1, 0, 16), r5.getMaxBackoffMs()) + Utilities.random.nextInt(RangesKt.coerceAtLeast((int) (r5 / 4), 1));
    }

    @JvmStatic
    public static final void cancel(long r2) {
        INSTANCE.cancelMatching(new TranslationDispatcher$$ExternalSyntheticLambda0(r2));
    }

    @JvmStatic
    public static final void cancelAll() {
        INSTANCE.cancelMatching(new TranslationDispatcher$$ExternalSyntheticLambda1());
    }

    private final void cancelMatching(Function1<? super Job, Boolean> r9) {
        ArrayList r8 = new ArrayList();
        ArrayList r0 = new ArrayList();
        Object r1 = lock;
        monitor-enter(r1);
        Iterator<Gate> r2 = gates.values().iterator();     // Catch: Throwable -> L13
    L6:
        if (r2.hasNext() == false) goto L23;
        Gate r3 = r2.next();     // Catch: Throwable -> L13
        ArrayDeque<Job> r4 = r3.getQueue();     // Catch: Throwable -> L13
        ArrayList r5 = new ArrayList();     // Catch: Throwable -> L13
        Iterator<Job> r42 = r4.iterator();     // Catch: Throwable -> L13
    L9:
        if (r42.hasNext() == false) goto L15;
        Job r6 = r42.next();     // Catch: Throwable -> L13
        if (r9.invoke(r6).booleanValue() == false) goto L9;
        r5.add(r6);     // Catch: Throwable -> L13
        goto L9
    L15:
        r3.getQueue().removeAll(r5);     // Catch: Throwable -> L13
        r0.addAll(r5);     // Catch: Throwable -> L13
        Iterator<Job> r32 = r3.getRunning().iterator();     // Catch: Throwable -> L13
    L17:
        if (r32.hasNext() == false) goto L6;
        Job r43 = r32.next();     // Catch: Throwable -> L13
        if (r9.invoke(r43).booleanValue() == false) goto L17;
        r43.setCancelled(true);     // Catch: Throwable -> L13
        Call r44 = r43.getCall();     // Catch: Throwable -> L13
        if (r44 == null) goto L17;
        r8.add(r44);     // Catch: Throwable -> L13
        goto L17
    L23:
        Unit r92 = Unit.INSTANCE;     // Catch: Throwable -> L13
        monitor-exit(r1);
        int r93 = r8.size();
        int r12 = 0;
    L26:
        if (r12 >= r93) goto L29;
        Object r22 = r8.get(r12);
        r12 = r12 + 1;
        ((Call) r22).cancel();
        goto L26
    L29:
        if (r0.isEmpty() == true) goto L50;
        AndroidUtilities.runOnUIThread(new TranslationDispatcher$$ExternalSyntheticLambda2(r0));
        return;
    L50:
        return;
    L13:
        th = move-exception;
        throw th;
    }

    @JvmStatic
    public static final void enqueue(HttpTranslator r10, long r11, List<String> r13, String r14, String r15, boolean r16, Completion r17) {
        if (r13.isEmpty() == false) goto L6;
        AndroidUtilities.runOnUIThread(new TranslationDispatcher$$ExternalSyntheticLambda3(r17));
        return;
    L6:
        Object r8 = lock;
        monitor-enter(r8);
        Gate r9 = INSTANCE.gateOf(r10);     // Catch: Throwable -> L11
        Job r0 = new Job(r10, r11, r13, r14, r15, r17);     // Catch: Throwable -> L11
        if (r16 == false) goto L13;
        r9.getQueue().addFirst(r0);     // Catch: Throwable -> L11
    L14:
        monitor-exit(r8);
        Utilities.globalQueue.postRunnable(new TranslationDispatcher$$ExternalSyntheticLambda4(r9));
        return;
    L13:
        r9.getQueue().addLast(r0);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ void enqueue$default(HttpTranslator r8, long r9, List r11, String r12, String r13, boolean r14, Completion r15, int r16, Object r17) {
        if ((r16 & 32) == 0) goto L5;
        r14 = false;
    L5:
        enqueue(r8, r9, r11, r12, r13, r14, r15);
    }

    private final void finish(Gate r2, Job r3, List<String> r4, TranslationError r5) {
        Object r1 = lock;
        monitor-enter(r1);
        r2.getRunning().remove(r3);     // Catch: Throwable -> L8
        r3.setCall(null);     // Catch: Throwable -> L8
        boolean r0 = r3.getCancelled();     // Catch: Throwable -> L8
        monitor-exit(r1);
        AndroidUtilities.runOnUIThread(new TranslationDispatcher$$ExternalSyntheticLambda6(r0, r3, r4, r5));
        Utilities.globalQueue.postRunnable(new TranslationDispatcher$$ExternalSyntheticLambda7(r2));
        return;
    L8:
        th = move-exception;
        throw th;
    }

    private final Gate gateOf(HttpTranslator r3) {
        HashMap<String, Gate> r2 = gates;
        String r0 = r3.getClass().getName();
        Gate r1 = r2.get(r0);
        if (r1 != null) goto L6;
        r1 = new Gate(r3.getLimits());
        r1.setPumpRunnable(new TranslationDispatcher$$ExternalSyntheticLambda5(r1));
        r2.put(r0, r1);
    L6:
        return r1;
    }

    private final Job nextJob(Gate r10) {
        Object r9 = lock;
        monitor-enter(r9);
        ProviderLimits r0 = r10.getLimits();     // Catch: Throwable -> L12
        Job r2 = null;
        if (r10.getQueue().isEmpty() == false) goto L7;
    L15:
        monitor-exit(r9);
        return r2;
    L7:
        if (r10.getRunning().size() >= r0.getMaxConcurrent()) goto L15;
        long r3 = SystemClock.elapsedRealtime();     // Catch: Throwable -> L12
        long r02 = Math.max(r10.getCooldownUntil(), r10.getLastStartedAt() + r0.getMinIntervalMs());     // Catch: Throwable -> L12
        if (r3 >= r02) goto L14;
        INSTANCE.schedulePump(r10, r02 - r3);     // Catch: Throwable -> L12
        goto L15
    L14:
        r2 = r10.getQueue().removeFirst();     // Catch: Throwable -> L12
        r10.setLastStartedAt(r3);     // Catch: Throwable -> L12
        r10.getRunning().add(r2);     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        throw th;
    }

    private final void pump(Gate r2) {
    L2:
        Job r0 = nextJob(r2);
        if (r0 == null) goto L4;
        start(r2, r0);
        goto L2
    }

    private final void retryOrFail(Gate r8, Job r9, TranslationError r10) {
        r9.setAttempt(r9.getAttempt() + 1);
        ProviderLimits r0 = r8.getLimits();
        if (r10.isRetryable() == true) goto L5;
    L32:
        finish(r8, r9, null, r10);
        return;
    L5:
        if (r9.getAttempt() >= r0.getMaxAttempts()) goto L32;
        if ((r10 instanceof TranslationError.RateLimited) == false) goto L12;
        TranslationError.RateLimited r1 = (TranslationError.RateLimited) r10;
        if (r1.getRetryAfterMs() <= 0) goto L12;
        long r02 = r1.getRetryAfterMs();
    L13:
        Object r7 = lock;
        monitor-enter(r7);
        r8.getRunning().remove(r9);     // Catch: Throwable -> L20
        r9.setCall(null);     // Catch: Throwable -> L20
        if (r9.getCancelled() == false) goto L18;
    L23:
        boolean r2 = r9.getCancelled();     // Catch: Throwable -> L20
        monitor-exit(r7);
        if (r2 == false) goto L28;
        AndroidUtilities.runOnUIThread(new TranslationDispatcher$$ExternalSyntheticLambda8(r9));
        Utilities.globalQueue.postRunnable(new TranslationDispatcher$$ExternalSyntheticLambda9(r8));
        return;
    L28:
        FileLog.d("translator: retrying in " + r02 + "ms after " + r10 + " (attempt " + r9.getAttempt() + ")");
        return;
    L18:
        if ((r10 instanceof TranslationError.RateLimited) == false) goto L22;
        r8.setCooldownUntil(Math.max(r8.getCooldownUntil(), SystemClock.elapsedRealtime() + r02));     // Catch: Throwable -> L20
    L22:
        r8.getQueue().addFirst(r9);     // Catch: Throwable -> L20
        INSTANCE.schedulePump(r8, r02);     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        throw th;
    L12:
        r02 = backoff(r0, r9.getAttempt());
        goto L13
    }

    private final void schedulePump(Gate r7, long r8) {
        long r0 = SystemClock.elapsedRealtime();
        long r2 = r0 + r8;
        long r02 = r0 + 1;
        long r4 = r7.getScheduledPumpAt();
        if (r02 <= r4) goto L5;
    L7:
        r7.setScheduledPumpAt(r2);
        Utilities.globalQueue.cancelRunnable(r7.getPumpRunnable());
        Utilities.globalQueue.postRunnable(r7.getPumpRunnable(), r8);
        return;
    L5:
        if (r4 > r2) goto L7;
    }

    private final void start(Gate r6, Job r7) {
        Request r1 = r7.getTranslator().buildRequest(r7.getTexts(), r7.getFromLang(), r7.getToLang());     // Catch: Exception -> L20
        Call r12 = r7.getTranslator().getClient().newCall(r1);
        Object r2 = lock;
        monitor-enter(r2);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (r7.getCancelled() == true) goto L11;
        r7.setCall(r12);     // Catch: Throwable -> L9
    L11:
        boolean r3 = r7.getCancelled();     // Catch: Throwable -> L9
        monitor-exit(r2);
        if (r3 == false) goto L16;
        finish(r6, r7, null, null);
        return;
    L16:
        r12.enqueue(new AnonymousClass1(r6, r7));
        return;
    L20:
        e = move-exception;
        FileLog.e(e);
        finish(r6, r7, null, TranslationError.Fatal.INSTANCE);
    }
}
