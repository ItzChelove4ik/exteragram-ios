package com.exteragram.messenger.translator;

import android.os.SystemClock;
import com.exteragram.messenger.translator.core.TranslationError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.TranslateAlert2;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jq\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2$\u0010\u000f\u001a \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000e0\r0\b2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u0014\u0010\u0015JC\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0006\u0010\u0010\u001a\u00020\u000e2\u0014\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b0\u0016H\u0007¢\u0006\u0004\b\u0018\u0010\u0019JQ\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\u0006\u0010\u0010\u001a\u00020\u000e2\"\u0010\u0017\u001a\u001e\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\b\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001aH\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001dH\u0002¢\u0006\u0004\b\u001f\u0010 R0\u0010#\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040!j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006%"}, d2 = {"Lcom/exteragram/messenger/translator/ChatTranslationBridge;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "dialogId", _UrlKt.FRAGMENT_ENCODE_SET, "isTranscription", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "ids", "Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;", "sources", "Lorg/telegram/messenger/Utilities$Callback4;", _UrlKt.FRAGMENT_ENCODE_SET, "callbacks", "toLang", "Ljava/lang/Runnable;", "onFinished", _UrlKt.FRAGMENT_ENCODE_SET, "translateMessages", "(JZLjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/Runnable;)V", "Lorg/telegram/messenger/Utilities$Callback;", "onDone", "translateTexts", "(JLjava/util/List;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)V", "Lkotlin/Function2;", "translate", "(JLjava/util/List;Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "Lcom/exteragram/messenger/translator/core/TranslationError;", "error", "notifyFailure", "(JLcom/exteragram/messenger/translator/core/TranslationError;)V", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "lastFailureNotice", "Ljava/util/HashMap;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nChatTranslationBridge.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChatTranslationBridge.kt\ncom/exteragram/messenger/translator/ChatTranslationBridge\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,102:1\n1586#2:103\n1661#2,3:104\n1#3:107\n*S KotlinDebug\n*F\n+ 1 ChatTranslationBridge.kt\ncom/exteragram/messenger/translator/ChatTranslationBridge\n*L\n70#1:103\n70#1:104,3\n*E\n"})
/* loaded from: classes4.dex */
public final class ChatTranslationBridge {
    public static final ChatTranslationBridge INSTANCE = null;
    private static final HashMap<Long, Long> lastFailureNotice = null;

    public static Unit $r8$lambda$29bWt5csaXfFme9ETH_dYptwKc4(Utilities.Callback r1, List r2, List r3, boolean r4) {
        List r42 = null;
        if (r3 == null) goto L9;
        List r32 = CollectionsKt.filterNotNull(r3);
        if (r32 == null) goto L9;
        if (r32.size() != r2.size()) goto L9;
        r42 = r32;
    L9:
        r1.run(r42);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$n4IWVuXo_sYKN8VFSAsNKZlNhQ4(List r5, boolean r6, List r7, String r8, Runnable r9, List r10, boolean r11) {
        if (r11 == true) goto L10;
        int r112 = r5.size();
        int r0 = 0;
    L4:
        if (r0 >= r112) goto L10;
        Utilities.Callback4 r1 = (Utilities.Callback4) r5.get(r0);
        Boolean r2 = Boolean.valueOf(r6);
        Object r3 = r7.get(r0);
        if (r10 == null) goto L8;
        TLRPC.TL_textWithEntities r4 = (TLRPC.TL_textWithEntities) CollectionsKt.getOrNull(r10, r0);
    L9:
        r1.run(r2, r3, r4, r8);
        r0 = r0 + 1;
        goto L4
    L8:
        r4 = null;
    L10:
        r9.run();
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$qFx9JU0PIK7NSKHEqlnh55oJOqM(List r7, Function2 r8, long r9, List r11, TranslationError r12) {
        boolean r0 = r12 instanceof TranslationError.Cancelled;
        ArrayList r1 = null;
        if (r0 == true) goto L13;
        int r2 = r7.size();
        ArrayList r3 = new ArrayList(r2);
        int r4 = 0;
    L6:
        if (r4 >= r2) goto L12;
        String r5 = (String) CollectionsKt.getOrNull(r11, r4);
        if (r5 == null) goto L10;
        TLRPC.TL_textWithEntities r6 = new TLRPC.TL_textWithEntities();
        r6.text = r5;
        TLRPC.TL_textWithEntities r52 = TranslateAlert2.preprocess((TLRPC.TL_textWithEntities) r7.get(r4), r6);
    L11:
        r3.add(r52);
        r4 = r4 + 1;
        goto L6
    L10:
        r52 = null;
        goto L11
    L12:
        r1 = r3;
    L13:
        r8.invoke(r1, Boolean.valueOf(r0));
        if (r12 == null) goto L21;
        if (r0 == true) goto L22;
        INSTANCE.notifyFailure(r9, r12);
        return;
    L22:
        return;
    }

    static {
        INSTANCE = new ChatTranslationBridge();
        lastFailureNotice = new HashMap();
    }

    private ChatTranslationBridge() {
    }

    private final void notifyFailure(long r7, TranslationError r9) {
        long r0 = SystemClock.elapsedRealtime();
        HashMap<Long, Long> r6 = lastFailureNotice;
        Long r2 = r6.get(Long.valueOf(r7));
        if (r2 != null) goto L5;
    L7:
        r6.put(Long.valueOf(r7), Long.valueOf(r0));
        NotificationCenter r62 = NotificationCenter.getGlobalInstance();
        int r72 = NotificationCenter.showBulletin;
        if ((r9 instanceof TranslationError.RateLimited) == false) goto L10;
        int r92 = R.string.TranslationFailedAlert1;
    L11:
        r62.postNotificationName(r72, new Object[]{1, LocaleController.getString(r92)});
        return;
    L10:
        r92 = R.string.TranslationFailedAlert2;
        goto L11
    L5:
        if ((r0 - r2.longValue()) >= 60000) goto L7;
    }

    private final void translate(long r3, List<? extends TLRPC.TL_textWithEntities> r5, String r6, Function2<? super List<? extends TLRPC.TL_textWithEntities>, ? super Boolean, Unit> r7) {
        List<? extends TLRPC.TL_textWithEntities> r2 = r5;
        ArrayList r0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r2, 10));
        Iterator<T> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L9;
        String r1 = ((TLRPC.TL_textWithEntities) r22.next()).text;
        if (r1 != null) goto L8;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        r0.add(r1);
        goto L4
    L9:
        ChatTranslator.translate(r3, r0, r6, new ChatTranslationBridge$$ExternalSyntheticLambda2(r5, r7, r3));
    }

    @JvmStatic
    public static final void translateMessages(long r6, boolean r8, List<Integer> r9, List<? extends TLRPC.TL_textWithEntities> r10, List<? extends Utilities.Callback4<Boolean, Integer, TLRPC.TL_textWithEntities, String>> r11, String r12, Runnable r13) {
        INSTANCE.translate(r6, r10, r12, new ChatTranslationBridge$$ExternalSyntheticLambda1(r11, r8, r9, r12, r13));
    }

    @JvmStatic
    public static final void translateTexts(long r6, List<? extends TLRPC.TL_textWithEntities> r8, String r9, Utilities.Callback<List<TLRPC.TL_textWithEntities>> r10) {
        INSTANCE.translate(r6, r8, r9, new ChatTranslationBridge$$ExternalSyntheticLambda0(r10, r8));
    }
}
