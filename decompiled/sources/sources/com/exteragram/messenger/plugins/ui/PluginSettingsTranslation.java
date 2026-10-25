package com.exteragram.messenger.plugins.ui;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.translator.ChatTranslator;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.translator.core.HttpTranslator;
import com.exteragram.messenger.translator.core.TranslationError;
import com.exteragram.messenger.translator.providers.GoogleTranslator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LanguageDetector;
import org.telegram.messenger.TranslateController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 :2\u00020\u0001:\u00029:B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006J\u000e\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006J\u0006\u0010\u001e\u001a\u00020\u001bJ\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u0013J\u0012\u0010 \u001a\u0004\u0018\u00010\n2\b\u0010!\u001a\u0004\u0018\u00010\nJ\u0014\u0010\"\u001a\u00020\u001b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0$J\u0016\u0010%\u001a\u00020\u001b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0$H\u0002J\u001e\u0010&\u001a\u00020\u001b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0'2\u0006\u0010(\u001a\u00020\nH\u0002J:\u0010)\u001a\u00020\u001b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0'2\u0006\u0010(\u001a\u00020\n2\u001a\u0010*\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0'\u0012\u0004\u0012\u00020\u001b0+H\u0002Je\u0010,\u001a\u00020\u001b2\u0012\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0'0'2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0'2\u0006\u0010(\u001a\u00020\n2\u000e\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n002\u0006\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u00020.2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001b03H\u0002¢\u0006\u0002\u00104JW\u00105\u001a\u00020\u001b2\f\u00106\u001a\b\u0012\u0004\u0012\u00020.0'2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0'2\u0006\u0010(\u001a\u00020\n2\u000e\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n002\u0006\u00101\u001a\u00020\u00132\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001b03H\u0002¢\u0006\u0002\u00107J\u0010\u00108\u001a\u00020\u001b2\u0006\u0010\u000f\u001a\u00020\u0013H\u0002R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\n0\rj\b\u0012\u0004\u0012\u00020\n`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\n0\rj\b\u0012\u0004\u0012\u00020\n`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0019\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017¨\u0006;"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "listeners", "Ljava/util/ArrayList;", "Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Listener;", "Lkotlin/collections/ArrayList;", "translations", "Ljava/util/HashMap;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/collections/HashMap;", "pending", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "failed", "targetLanguage", "detectedLanguage", "detecting", _UrlKt.FRAGMENT_ENCODE_SET, "value", "enabled", "getEnabled", "()Z", "isLoading", "isTranslatable", "addListener", _UrlKt.FRAGMENT_ENCODE_SET, "listener", "removeListener", "resetFailed", "setEnabled", "text", "original", "check", "texts", _UrlKt.FRAGMENT_ENCODE_SET, "detect", PluginsConstants.REQUEST, _UrlKt.FRAGMENT_ENCODE_SET, "language", "translate", "done", "Lkotlin/Function1;", "requestEach", "chunks", _UrlKt.FRAGMENT_ENCODE_SET, "results", _UrlKt.FRAGMENT_ENCODE_SET, "retry", "position", "Lkotlin/Function0;", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;ZILkotlin/jvm/functions/Function0;)V", "requestChunk", "chunk", "(Ljava/util/List;Ljava/util/List;Ljava/lang/String;[Ljava/lang/String;ZLkotlin/jvm/functions/Function0;)V", "notifyListeners", "Listener", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginSettingsTranslation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginSettingsTranslation.kt\ncom/exteragram/messenger/plugins/ui/PluginSettingsTranslation\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,243:1\n1#2:244\n777#3:245\n873#3,2:246\n1924#3,3:248\n1924#3,3:251\n1924#3,3:254\n1586#3:257\n1661#3,3:258\n1586#3:261\n1661#3,3:262\n*S KotlinDebug\n*F\n+ 1 PluginSettingsTranslation.kt\ncom/exteragram/messenger/plugins/ui/PluginSettingsTranslation\n*L\n93#1:245\n93#1:246,2\n123#1:248,3\n213#1:251,3\n190#1:254,3\n205#1:257\n205#1:258,3\n209#1:261\n209#1:262,3\n*E\n"})
/* loaded from: classes4.dex */
public final class PluginSettingsTranslation {
    public static final Companion Companion = null;
    private static final int DETECTION_SAMPLE_LENGTH = 2000;
    private static final int MAX_TEXTS_PER_REQUEST = 20;
    private static final long TRANSLATION_TAG = -9223372036854775807L;
    private static final HashMap<String, PluginSettingsTranslation> instances = null;
    private String detectedLanguage;
    private boolean detecting;
    private boolean enabled;
    private final HashSet<String> failed;
    private final ArrayList<Listener> listeners;
    private final HashSet<String> pending;
    private String targetLanguage;
    private final HashMap<String, String> translations;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000bH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R*\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nj\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TRANSLATION_TAG", _UrlKt.FRAGMENT_ENCODE_SET, "MAX_TEXTS_PER_REQUEST", _UrlKt.FRAGMENT_ENCODE_SET, "DETECTION_SAMPLE_LENGTH", "instances", "Ljava/util/HashMap;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation;", "Lkotlin/collections/HashMap;", "of", "pluginId", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPluginSettingsTranslation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginSettingsTranslation.kt\ncom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,243:1\n410#2,7:244\n*S KotlinDebug\n*F\n+ 1 PluginSettingsTranslation.kt\ncom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Companion\n*L\n240#1:244,7\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final PluginSettingsTranslation of(String r3) {
            HashMap r2 = PluginSettingsTranslation.access$getInstances$cp();
            Object r0 = r2.get(r3);
            if (r0 != null) goto L6;
            r0 = new PluginSettingsTranslation(null);
            r2.put(r3, r0);
        L6:
            return (PluginSettingsTranslation) r0;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/PluginSettingsTranslation$Listener;", _UrlKt.FRAGMENT_ENCODE_SET, "onTranslationChanged", _UrlKt.FRAGMENT_ENCODE_SET, "failed", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Listener {
        void onTranslationChanged(boolean r1);
    }

    public static void $r8$lambda$3KJzwSQq1gHWVma1Zl0vBkVJAcM(TLObject r8, List r9, boolean r10, Function0 r11, PluginSettingsTranslation r12, List r13, String r14, String[] r15, TLRPC.TL_error r16) {
        if ((r8 instanceof TLRPC.TL_messages_translateResult) == false) goto L35;
        ArrayList r0 = new ArrayList();
        TLRPC.TL_messages_translateResult r82 = (TLRPC.TL_messages_translateResult) r8;
        int r4 = 0;
        if (r82.result.size() != r9.size()) goto L23;
        Iterator r92 = r9.iterator();
        int r1 = 0;
    L8:
        if (r92.hasNext() == false) goto L24;
        Object r3 = r92.next();
        int r5 = r1 + 1;
        if (r1 >= 0) goto L12;
        CollectionsKt.throwIndexOverflow();
    L12:
        int r32 = ((Number) r3).intValue();
        TLRPC.TL_textWithEntities r17 = r82.result.get(r1);
        if (r17 == null) goto L15;
        String r18 = r17.text;
    L16:
        if (r18 != null) goto L18;
    L21:
        r0.add(Integer.valueOf(r32));
    L22:
        r1 = r5;
        goto L8
    L18:
        if (StringsKt.isBlank(r18) == true) goto L21;
        r15[r32] = r18;
        goto L22
    L15:
        r18 = null;
    L24:
        if (r10 == true) goto L26;
    L33:
        r11.invoke();
        return;
    L26:
        if (r0.isEmpty() == true) goto L33;
        ArrayList r19 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r0, 10));
        int r83 = r0.size();
    L29:
        if (r4 >= r83) goto L31;
        Object r93 = r0.get(r4);
        r4 = r4 + 1;
        r19.add(CollectionsKt.listOf(Integer.valueOf(((Number) r93).intValue())));
        goto L29
    L31:
        r12.requestEach(r19, r13, r14, r15, false, 0, r11);
        return;
    L23:
        r0.addAll(r9);
        goto L24
    L35:
        if (r16 != null) goto L37;
    L44:
        r11.invoke();
        return;
    L37:
        if (StringsKt.equals("TRANSLATIONS_DISABLED_ALT", r16.text, true) == false) goto L44;
        List r102 = r9;
        ArrayList r122 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r102, 10));
        Iterator r103 = r102.iterator();
    L40:
        if (r103.hasNext() == false) goto L42;
        r122.add((String) r13.get(((Number) r103.next()).intValue()));
        goto L40
    L42:
        ChatTranslator.translate(TRANSLATION_TAG, r122, r14, GoogleTranslator.Companion.getInstance(), new PluginSettingsTranslation$$ExternalSyntheticLambda3(r9, r11, r15));
    }

    public static void $r8$lambda$3cblPoTowmec5WOLdm2av0zUsqw(PluginSettingsTranslation r1, String r2) {
        AndroidUtilities.runOnUIThread(new PluginSettingsTranslation$$ExternalSyntheticLambda2(r2, r1));
    }

    public static void $r8$lambda$6sDJjyrsuB15zfDuAj74Z4Tce9A(List r10, boolean r11, Function0 r12, PluginSettingsTranslation r13, List r14, String r15, String[] r16, TLObject r17, TLRPC.TL_error r18) {
        AndroidUtilities.runOnUIThread(new PluginSettingsTranslation$$ExternalSyntheticLambda8(r17, r10, r11, r12, r13, r14, r15, r16, r18));
    }

    public static void $r8$lambda$BhislwJ_w_bX3GhCIz9I8vwOc8g(List r2, Function0 r3, String[] r4, List r5, TranslationError r6) {
        Iterator r22 = r2.iterator();
        int r62 = 0;
    L4:
        if (r22.hasNext() == false) goto L9;
        Object r0 = r22.next();
        int r1 = r62 + 1;
        if (r62 >= 0) goto L8;
        CollectionsKt.throwIndexOverflow();
    L8:
        r4[((Number) r0).intValue()] = CollectionsKt.getOrNull(r5, r62);
        r62 = r1;
        goto L4
    L9:
        r3.invoke();
    }

    /* renamed from: $r8$lambda$D-96Ah5Br8D_rDrlskkOU_mZsZE, reason: not valid java name */
    public static void m1587$r8$lambda$D96Ah5Br8D_rDrlskkOU_mZsZE(Function1 r0, List r1, TranslationError r2) {
        r0.invoke(r1);
    }

    public static Unit $r8$lambda$DNIlH7lxxikJOF6XvGqwVcxihjI(PluginSettingsTranslation r0, List r1, List r2, String r3, String[] r4, boolean r5, int r6, Function0 r7) {
        r0.requestEach(r1, r2, r3, r4, r5, r6 + 1, r7);
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$GmVCDGY-KoJLxcaC_JAKQEYoydA, reason: not valid java name */
    public static Unit m1588$r8$lambda$GmVCDGYKoJLxcaC_JAKQEYoydA(PluginSettingsTranslation r6, List r7, String r8, List r9) {
        List r72 = r7;
        r6.pending.removeAll(CollectionsKt.toSet(r72));
        if (Intrinsics.areEqual(r8, r6.targetLanguage) == true) goto L6;
        r6.notifyListeners(false);
        return Unit.INSTANCE;
    L6:
        Iterator r73 = r72.iterator();
        boolean r82 = false;
        int r1 = 0;
    L7:
        boolean r3 = true;
        if (r73.hasNext() == false) goto L20;
        Object r2 = r73.next();
        int r4 = r1 + 1;
        if (r1 >= 0) goto L12;
        CollectionsKt.throwIndexOverflow();
    L12:
        String r22 = (String) r2;
        String r12 = (String) CollectionsKt.getOrNull(r9, r1);
        if (r12 != null) goto L15;
    L18:
        r6.failed.add(r22);
        r82 = true;
    L19:
        r1 = r4;
        goto L7
    L15:
        if (StringsKt.isBlank(r12) == true) goto L18;
        r6.translations.put(r22, r12);
        goto L19
    L20:
        if (r82 == true) goto L22;
    L24:
        r3 = false;
    L25:
        if (r3 == false) goto L27;
        r6.enabled = false;
    L27:
        r6.notifyListeners(r3);
        return Unit.INSTANCE;
    L22:
        if (r6.translations.isEmpty() == false) goto L24;
        goto L24
    }

    public static void $r8$lambda$PTUdpZwtDYtdYwXecXf41YtewTI(String r1, PluginSettingsTranslation r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r1.length() != 0) goto L7;
        return;
    L7:
        if (Intrinsics.areEqual(r1, TranslateController.UNKNOWN_LANGUAGE) == true) goto L11;
        r2.detectedLanguage = r1;
        r2.notifyListeners(false);
        return;
    }

    public static Unit $r8$lambda$vM607UZKvUJyPyzilOIBKU_9N4g(Function1 r0, String[] r1) {
        r0.invoke(ArraysKt.toList(r1));
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
        instances = new HashMap();
    }

    private PluginSettingsTranslation() {
        this.listeners = new ArrayList();
        this.translations = new HashMap();
        this.pending = new HashSet();
        this.failed = new HashSet();
    }

    public /* synthetic */ PluginSettingsTranslation(DefaultConstructorMarker r1) {
        this();
    }

    public static final /* synthetic */ HashMap access$getInstances$cp() {
        return instances;
    }

    private final void detect(Collection<String> r11) {
        this.detecting = true;
        LanguageDetector.detectLanguage(StringsKt.take(CollectionsKt.joinToString$default(r11, "\n", null, null, 0, null, null, 62, null), 2000), new PluginSettingsTranslation$$ExternalSyntheticLambda0(this), null);
    }

    private final void notifyListeners(boolean r2) {
        Iterator r1 = new ArrayList(this.listeners).iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((Listener) r1.next()).onTranslationChanged(r2);
        goto L4
    }

    @JvmStatic
    public static final PluginSettingsTranslation of(String r1) {
        return Companion.of(r1);
    }

    private final void request(List<String> r3, String r4) {
        this.pending.addAll(r3);
        translate(r3, r4, new PluginSettingsTranslation$$ExternalSyntheticLambda5(this, r3, r4));
    }

    private final void requestChunk(List<Integer> r14, List<String> r15, String r16, String[] r17, boolean r18, Function0<Unit> r19) {
        TLRPC.TL_messages_translateText r0 = new TLRPC.TL_messages_translateText();
        r0.flags |= 2;
        Iterator<Integer> r1 = r14.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        int r2 = r1.next().intValue();
        ArrayList<TLRPC.TL_textWithEntities> r3 = r0.text;
        TLRPC.TL_textWithEntities r4 = new TLRPC.TL_textWithEntities();
        r4.text = r15.get(r2);
        r3.add(r4);
        goto L4
    L6:
        r0.to_lang = r16;
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r0, new PluginSettingsTranslation$$ExternalSyntheticLambda1(r14, r18, r19, this, r15, r16, r17));
    }

    private final void requestEach(List<? extends List<Integer>> r11, List<String> r12, String r13, String[] r14, boolean r15, int r16, Function0<Unit> r17) {
        if (r16 != r11.size()) goto L6;
        r17.invoke();
        return;
    L6:
        requestChunk(r11.get(r16), r12, r13, r14, r15, new PluginSettingsTranslation$$ExternalSyntheticLambda4(this, r11, r12, r13, r14, r15, r16, r17));
    }

    private final void translate(List<String> r10, String r11, Function1<? super List<String>, Unit> r12) {
        if ((TranslatorUtils.getCurrentTranslator() instanceof HttpTranslator) == false) goto L6;
        ChatTranslator.translate(TRANSLATION_TAG, r10, r11, new PluginSettingsTranslation$$ExternalSyntheticLambda6(r12));
        return;
    L6:
        String[] r5 = new String[r10.size()];
        requestEach(CollectionsKt.chunked(CollectionsKt.getIndices(r10), 20), r10, r11, r5, true, 0, new PluginSettingsTranslation$$ExternalSyntheticLambda7(r12, r5));
    }

    public final void addListener(Listener r1) {
        this.listeners.add(r1);
    }

    public final void check(Collection<String> r6) {
        String r0 = TranslatorUtils.getResolvedTargetLanguageCode();
        if (Intrinsics.areEqual(r0, this.targetLanguage) == true) goto L6;
        this.targetLanguage = r0;
        this.translations.clear();
        this.failed.clear();
    L6:
        if (this.detectedLanguage != null) goto L15;
        if (this.detecting == true) goto L15;
        if (r6.isEmpty() == true) goto L15;
        if (LanguageDetector.hasSupport() == false) goto L15;
        detect(r6);
    L15:
        if (this.enabled == false) goto L45;
        ArrayList r1 = new ArrayList();
        Iterator<T> r62 = r6.iterator();
    L19:
        if (r62.hasNext() == false) goto L30;
        Object r2 = r62.next();
        String r3 = (String) r2;
        if (StringsKt.isBlank(r3) == true) goto L19;
        if (this.translations.containsKey(r3) == true) goto L19;
        if (this.pending.contains(r3) == true) goto L19;
        if (this.failed.contains(r3) == true) goto L19;
        r1.add(r2);
        goto L19
    L30:
        if (r1.isEmpty() == true) goto L44;
        request(r1, r0);
        return;
    L44:
        return;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final boolean isLoading() {
        return !this.pending.isEmpty();
    }

    public final boolean isTranslatable() {
        if (this.detectedLanguage != null) goto L5;
        return false;
    L5:
        return !TranslatorUtils.isRestrictedLanguage(r0);
    }

    public final void removeListener(Listener r1) {
        this.listeners.remove(r1);
    }

    public final void resetFailed() {
        this.failed.clear();
    }

    public final void setEnabled(boolean r2) {
        if (this.enabled != r2) goto L5;
        return;
    L5:
        this.enabled = r2;
        if (r2 == false) goto L8;
        this.failed.clear();
    L8:
        notifyListeners(false);
    }

    public final String text(String r2) {
        if (this.enabled == false) goto L10;
        if (r2 == null) goto L10;
        String r1 = this.translations.get(r2);
        if (r1 != null) goto L9;
        return r2;
    L9:
        return r1;
    L10:
        return r2;
    }
}
