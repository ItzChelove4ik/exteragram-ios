package com.exteragram.messenger.translator;

import com.exteragram.messenger.translator.core.BaseTranslator;
import com.exteragram.messenger.translator.core.HttpTranslator;
import com.exteragram.messenger.translator.core.ProviderLimits;
import com.exteragram.messenger.translator.core.TranslationDispatcher;
import com.exteragram.messenger.translator.core.TranslationError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;

@Metadata(d1 = {"\u0000_\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0006*\u0001)\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002,-B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\r\u0010\u000eJ=\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\r\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\fH\u0007¢\u0006\u0004\b\u0014\u0010\u0003J\u000f\u0010\u0015\u001a\u00020\fH\u0007¢\u0006\u0004\b\u0015\u0010\u0003JK\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00062\u000e\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u001aH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ%\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\b!\u0010\"J1\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00060\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00062\u0006\u0010$\u001a\u00020#H\u0002¢\u0006\u0004\b%\u0010&J'\u0010'\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0007H\u0002¢\u0006\u0004\b'\u0010(R\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+¨\u0006."}, d2 = {"Lcom/exteragram/messenger/translator/ChatTranslator;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "dialogId", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "texts", "toLang", "Lcom/exteragram/messenger/translator/ChatTranslator$Callback;", com.sun.jna.Callback.METHOD_NAME, _UrlKt.FRAGMENT_ENCODE_SET, "translate", "(JLjava/util/List;Ljava/lang/String;Lcom/exteragram/messenger/translator/ChatTranslator$Callback;)V", "Lcom/exteragram/messenger/translator/core/BaseTranslator;", "current", "(JLjava/util/List;Ljava/lang/String;Lcom/exteragram/messenger/translator/core/BaseTranslator;Lcom/exteragram/messenger/translator/ChatTranslator$Callback;)V", "cancel", "(J)V", "cancelAll", "clearCache", "Lcom/exteragram/messenger/translator/core/HttpTranslator;", "translator", "Lcom/exteragram/messenger/translator/ChatTranslator$Piece;", "pieces", _UrlKt.FRAGMENT_ENCODE_SET, "results", "assemble", "(Lcom/exteragram/messenger/translator/core/HttpTranslator;Ljava/lang/String;Ljava/util/List;Ljava/util/List;[Ljava/lang/String;)V", "text", _UrlKt.FRAGMENT_ENCODE_SET, "limit", "split", "(Ljava/lang/String;I)Ljava/util/List;", "Lcom/exteragram/messenger/translator/core/ProviderLimits;", "limits", "chunk", "(Ljava/util/List;Lcom/exteragram/messenger/translator/core/ProviderLimits;)Ljava/util/List;", "cacheKey", "(Lcom/exteragram/messenger/translator/core/BaseTranslator;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "com/exteragram/messenger/translator/ChatTranslator$cache$1", "cache", "Lcom/exteragram/messenger/translator/ChatTranslator$cache$1;", "Callback", "Piece", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nChatTranslator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChatTranslator.kt\ncom/exteragram/messenger/translator/ChatTranslator\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,198:1\n1#2:199\n1586#3:200\n1661#3,3:201\n*S KotlinDebug\n*F\n+ 1 ChatTranslator.kt\ncom/exteragram/messenger/translator/ChatTranslator\n*L\n85#1:200\n85#1:201,3\n*E\n"})
/* loaded from: classes4.dex */
public final class ChatTranslator {
    public static final ChatTranslator INSTANCE = null;
    private static final ChatTranslator$cache$1 cache = null;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/translator/ChatTranslator$Callback;", _UrlKt.FRAGMENT_ENCODE_SET, "onResult", _UrlKt.FRAGMENT_ENCODE_SET, "results", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "error", "Lcom/exteragram/messenger/translator/core/TranslationError;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Callback {
        void onResult(List<String> r1, TranslationError r2);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000b\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/exteragram/messenger/translator/ChatTranslator$Piece;", _UrlKt.FRAGMENT_ENCODE_SET, "messageIndex", _UrlKt.FRAGMENT_ENCODE_SET, "text", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(ILjava/lang/String;)V", "getMessageIndex", "()I", "getText", "()Ljava/lang/String;", "translated", "getTranslated", "setTranslated", "(Ljava/lang/String;)V", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Piece {
        private final int messageIndex;
        private final String text;
        private String translated;

        public Piece(int r1, String r2) {
            this.messageIndex = r1;
            this.text = r2;
        }

        public final int getMessageIndex() {
            return this.messageIndex;
        }

        public final String getText() {
            return this.text;
        }

        public final String getTranslated() {
            return this.translated;
        }

        public final void setTranslated(String r1) {
            this.translated = r1;
        }
    }

    public static void $r8$lambda$7pS8Hb_AAjHICieQwAVhdpw17LI(Callback r1, List r2) {
        r1.onResult(ArraysKt.toList(new String[r2.size()]), TranslationError.Fatal.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v2, types: [com.exteragram.messenger.translator.core.TranslationError$Transient] */
    /* renamed from: $r8$lambda$mytC8HTQw-0Wntx8e7mXkUSUWpY, reason: not valid java name */
    public static void m1736$r8$lambda$mytC8HTQw0Wntx8e7mXkUSUWpY(List r3, Ref.ObjectRef r4, Ref.IntRef r5, HttpTranslator r6, String r7, List r8, ArrayList r9, String[] r10, Callback r11, List r12, T r13) {
        if (r12 == null) goto L9;
        if (r12.size() != r3.size()) goto L9;
        int r132 = r3.size();
        int r0 = 0;
    L6:
        if (r0 >= r132) goto L15;
        ((Piece) r3.get(r0)).setTranslated((String) r12.get(r0));
        r0 = r0 + 1;
    L15:
        int r32 = r5.element - 1;
        r5.element = r32;
        if (r32 != 0) goto L20;
        INSTANCE.assemble(r6, r7, r8, r9, r10);
        r11.onResult(ArraysKt.toList(r10), (TranslationError) r4.element);
        return;
    L20:
        return;
    L9:
        if ((r13 instanceof TranslationError.Cancelled) == false) goto L11;
    L12:
        if (r13 != 0) goto L14;
        r13 = TranslationError.Transient.INSTANCE;
    L14:
        r4.element = r13;
        goto L15
    L11:
        if (r4.element != 0) goto L15;
        goto L12
    }

    public static void $r8$lambda$zo9fEk4kosspHK8upFDhBXI08VM(Callback r1, String[] r2) {
        r1.onResult(ArraysKt.toList(r2), null);
    }

    static {
        INSTANCE = new ChatTranslator();
        cache = new ChatTranslator$cache$1();
    }

    private ChatTranslator() {
    }

    private final void assemble(HttpTranslator r7, String r8, List<String> r9, List<Piece> r10, String[] r11) {
        StringBuilder r6 = new StringBuilder();
        int r1 = 0;
    L4:
        if (r1 >= r10.size()) goto L27;
        int r2 = r10.get(r1).getMessageIndex();
        boolean r3 = true;
        int r4 = r1;
    L7:
        if (r4 >= r10.size()) goto L14;
        if (r10.get(r4).getMessageIndex() != r2) goto L14;
        if (r10.get(r4).getTranslated() != null) goto L13;
        r3 = false;
    L13:
        r4 = r4 + 1;
    L14:
        if (r3 == false) goto L26;
        r6.setLength(0);
    L16:
        if (r1 >= r4) goto L18;
        r6.append(r10.get(r1).getTranslated());
        r1 = r1 + 1;
        goto L16
    L18:
        String r12 = r6.toString();
        r11[r2] = r12;
        ChatTranslator$cache$1 r32 = cache;
        monitor-enter(r32);
        r32.put(INSTANCE.cacheKey(r7, r8, r9.get(r2)), r12);     // Catch: Throwable -> L23
        Unit r13 = Unit.INSTANCE;     // Catch: Throwable -> L23
        monitor-exit(r32);
    L23:
        th = move-exception;
        throw th;
    L26:
        r1 = r4;
        goto L4
    }

    private final String cacheKey(BaseTranslator r1, String r2, String r3) {
        return r1.getClass().getSimpleName() + " " + r2 + " " + r3;
    }

    @JvmStatic
    public static final void cancel(long r0) {
        TranslationDispatcher.cancel(r0);
    }

    @JvmStatic
    public static final void cancelAll() {
        TranslationDispatcher.cancelAll();
    }

    private final List<List<Piece>> chunk(List<Piece> r7, ProviderLimits r8) {
        ArrayList r6 = new ArrayList();
        ArrayList r0 = new ArrayList();
        Iterator<Piece> r72 = r7.iterator();
        int r2 = 0;
    L4:
        if (r72.hasNext() == false) goto L14;
        Piece r3 = r72.next();
        if (r0.isEmpty() == true) goto L12;
        if (r0.size() < r8.getMaxTextsPerRequest()) goto L10;
    L11:
        r6.add(r0);
        r0 = new ArrayList();
        r2 = 0;
        goto L12
    L10:
        if ((r3.getText().length() + r2) > r8.getMaxCharsPerRequest()) goto L11;
    L12:
        r0.add(r3);
        r2 = r2 + r3.getText().length();
        goto L4
    L14:
        if (r0.isEmpty() == true) goto L16;
        r6.add(r0);
    L16:
        return r6;
    }

    @JvmStatic
    public static final void clearCache() {
        ChatTranslator$cache$1 r0 = cache;
        monitor-enter(r0);
        r0.clear();     // Catch: Throwable -> L7
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private final List<String> split(String r9, int r10) {
        if (r9.length() <= r10) goto L5;
        ArrayList r8 = new ArrayList();
        int r0 = 0;
    L8:
        if (r0 >= r9.length()) goto L20;
        if ((r9.length() - r0) <= r10) goto L11;
        int r1 = r0 + r10;
        int r4 = r1 - 1;
        String r2 = r9;
        int r92 = StringsKt.lastIndexOf$default(r2, '\n', r4, false, 4, null);
        if (r92 > r0) goto L16;
        r92 = StringsKt.lastIndexOf$default(r2, ' ', r4, false, 4, null);
    L16:
        if (r92 > r0) goto L18;
        int r93 = r1;
    L19:
        r8.add(r2.substring(r0, r93));
        r0 = r93;
        r9 = r2;
        goto L8
    L18:
        r93 = r92 + 1;
        goto L19
    L11:
        r8.add(r9.substring(r0));
        return r8;
    L20:
        return r8;
    L5:
        return CollectionsKt.listOf(r9);
    }

    @JvmStatic
    public static final void translate(long r6, List<String> r8, String r9, Callback r10) {
        translate(r6, r8, r9, TranslatorUtils.getCurrentTranslator(), r10);
    }

    @JvmStatic
    public static final void translate(long r16, List<String> r18, String r19, BaseTranslator r20, Callback r21) {
        List<String> r6 = r18;
        Callback r9 = r21;
        if ((r20 instanceof HttpTranslator) == true) goto L6;
        AndroidUtilities.runOnUIThread(new ChatTranslator$$ExternalSyntheticLambda0(r9, r6));
        return;
    L6:
        HttpTranslator r1 = (HttpTranslator) r20;
        ProviderLimits r0 = r1.getLimits();
        String[] r8 = new String[r6.size()];
        ArrayList r7 = new ArrayList();
        int r2 = r6.size();
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L25;
        String r4 = r6.get(r3);
        if (StringsKt.isBlank(r4) == false) goto L11;
        r8[r3] = r4;
    L21:
        r3 = r3 + 1;
        goto L7
    L11:
        ChatTranslator$cache$1 r5 = cache;
        monitor-enter(r5);
        ChatTranslator r10 = INSTANCE;     // Catch: Throwable -> L22
        String r12 = (String) r5.get(r10.cacheKey(r1, r19, r4));     // Catch: Throwable -> L22
        monitor-exit(r5);
        if (r12 == null) goto L17;
        r8[r3] = r12;
        goto L21
    L17:
        Iterator<String> r42 = r10.split(r4, r0.getMaxCharsPerText()).iterator();
    L19:
        if (r42.hasNext() == false) goto L21;
        r7.add(new Piece(r3, r42.next()));
    L22:
        th = move-exception;
        throw th;
    L25:
        String r11 = r19;
        if (r7.isEmpty() == false) goto L29;
        AndroidUtilities.runOnUIThread(new ChatTranslator$$ExternalSyntheticLambda1(r9, r8));
        return;
    L29:
        List<List<Piece>> r02 = INSTANCE.chunk(r7, r0);
        Ref.IntRef r32 = new Ref.IntRef();
        r32.element = r02.size();
        Ref.ObjectRef r22 = new Ref.ObjectRef();
        Iterator<List<Piece>> r122 = r02.iterator();
    L31:
        if (r122.hasNext() == false) goto L37;
        List<Piece> r03 = r122.next();
        List<Piece> r43 = r03;
        ArrayList r102 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r43, 10));
        Iterator<T> r44 = r43.iterator();
    L34:
        if (r44.hasNext() == false) goto L36;
        r102.add(((Piece) r44.next()).getText());
        goto L34
    L36:
        HttpTranslator r45 = r1;
        ChatTranslator$$ExternalSyntheticLambda2 r04 = new ChatTranslator$$ExternalSyntheticLambda2(r03, r22, r32, r45, r11, r6, r7, r8, r9);
        r1 = r45;
        TranslationDispatcher.enqueue$default(r1, r16, r102, "auto", r19, false, r04, 32, null);
        r6 = r18;
        r9 = r21;
        r8 = r8;
        r7 = r7;
        r32 = r32;
        r22 = r22;
        r11 = r19;
        goto L31
    }
}
