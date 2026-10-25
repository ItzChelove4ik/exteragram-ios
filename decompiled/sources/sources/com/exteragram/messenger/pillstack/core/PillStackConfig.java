package com.exteragram.messenger.pillstack.core;

import android.content.SharedPreferences;
import com.exteragram.messenger.backup.PreferencesUtils;
import com.exteragram.messenger.config.BasePref;
import com.exteragram.messenger.config.BooleanPref;
import com.exteragram.messenger.config.IntegerPref;
import com.exteragram.messenger.config.NullableStringPref;
import com.exteragram.messenger.config.StringPref;
import com.exteragram.messenger.pillstack.core.PillRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KProperty;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.NotificationCenter;

@Metadata(d1 = {"\u0000b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b1\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a)\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u00042\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001d\u0010\t\u001a\u00020\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u0002¢\u0006\u0004\b\t\u0010\n\u001a\u001d\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0002j\b\u0012\u0004\u0012\u00020\u0003`\u0004¢\u0006\u0004\b\u000b\u0010\f\u001a\r\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000f\u001a\r\u0010\u0010\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u000f\u001a\r\u0010\u0011\u001a\u00020\r¢\u0006\u0004\b\u0011\u0010\u000f\u001a\r\u0010\u0012\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u000f\u001a\u0015\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0003¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0019\u0010\u0018\u001a\u00020\r2\n\u0010\u0017\u001a\u00020\u0016\"\u00020\u0003¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0015\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u0003¢\u0006\u0004\b\u001c\u0010\u001d\u001a+\u0010!\u001a\u00020\u001b2\u0010\u0010 \u001a\f\u0012\u0006\b\u0001\u0012\u00020\u001f\u0018\u00010\u001e2\n\u0010\u0017\u001a\u00020\u0016\"\u00020\u0003¢\u0006\u0004\b!\u0010\"\"\u0017\u0010$\u001a\u00020#8\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0017\u0010)\u001a\u00020(8\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0014\u0010-\u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.\"\"\u0010/\u001a\u00020\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104\"+\u0010:\u001a\u00020\u001b2\u0006\u00105\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00102\"\u0004\b9\u00104\"/\u0010@\u001a\u0004\u0018\u00010\u00002\b\u00105\u001a\u0004\u0018\u00010\u00008F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b;\u00107\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?\"/\u0010D\u001a\u0004\u0018\u00010\u00002\b\u00105\u001a\u0004\u0018\u00010\u00008F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bA\u00107\u001a\u0004\bB\u0010=\"\u0004\bC\u0010?\"+\u0010H\u001a\u00020\u001b2\u0006\u00105\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bE\u00107\u001a\u0004\bF\u00102\"\u0004\bG\u00104\"+\u0010L\u001a\u00020\u00002\u0006\u00105\u001a\u00020\u00008F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bI\u00107\u001a\u0004\bJ\u0010=\"\u0004\bK\u0010?\"+\u0010P\u001a\u00020\u00002\u0006\u00105\u001a\u00020\u00008F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bM\u00107\u001a\u0004\bN\u0010=\"\u0004\bO\u0010?\"+\u0010T\u001a\u00020\u00002\u0006\u00105\u001a\u00020\u00008F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bQ\u00107\u001a\u0004\bR\u0010=\"\u0004\bS\u0010?\"+\u0010Y\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u00038F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bU\u00107\u001a\u0004\bV\u0010W\"\u0004\bX\u0010\u0015\"(\u0010[\u001a\b\u0012\u0004\u0012\u00020\u00030Z8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b[\u0010\\\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`\"(\u0010a\u001a\b\u0012\u0004\u0012\u00020\u00030Z8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\ba\u0010\\\u001a\u0004\bb\u0010^\"\u0004\bc\u0010`\"\u001a\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00030d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010f\"\u001a\u0010g\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\f\n\u0004\bg\u0010h\u0012\u0004\bi\u0010\u000f¨\u0006j"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "data", "Ljava/util/ArrayList;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/collections/ArrayList;", "parsePillsList", "(Ljava/lang/String;)Ljava/util/ArrayList;", _UrlKt.FRAGMENT_ENCODE_SET, "list", "serializePillsList", "(Ljava/util/List;)Ljava/lang/String;", "getDefaultActivePills", "()Ljava/util/ArrayList;", _UrlKt.FRAGMENT_ENCODE_SET, "loadConfig", "()V", "reloadConfig", "sanitizePills", "savePillsLayout", "id", "saveLastActivePillId", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "pillIds", "notifySettingsChanged", "([I)V", "pillId", _UrlKt.FRAGMENT_ENCODE_SET, "checkAndClearPendingUpdate", "(I)Z", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "args", "shouldUpdatePill", "([Ljava/lang/Object;[I)Z", "Landroid/content/SharedPreferences;", "preferences", "Landroid/content/SharedPreferences;", "getPreferences", "()Landroid/content/SharedPreferences;", "Landroid/content/SharedPreferences$Editor;", "editor", "Landroid/content/SharedPreferences$Editor;", "getEditor", "()Landroid/content/SharedPreferences$Editor;", "sync", "Ljava/lang/Object;", "configLoaded", "Z", "getConfigLoaded", "()Z", "setConfigLoaded", "(Z)V", "<set-?>", "useCurrentLocation$delegate", "Lcom/exteragram/messenger/config/BasePref;", "getUseCurrentLocation", "setUseCurrentLocation", "useCurrentLocation", "customWeatherLocation$delegate", "getCustomWeatherLocation", "()Ljava/lang/String;", "setCustomWeatherLocation", "(Ljava/lang/String;)V", "customWeatherLocation", "customWeatherAddress$delegate", "getCustomWeatherAddress", "setCustomWeatherAddress", "customWeatherAddress", "infiniteScrolling$delegate", "getInfiniteScrolling", "setInfiniteScrolling", "infiniteScrolling", "gramTargetCurrency$delegate", "getGramTargetCurrency", "setGramTargetCurrency", "gramTargetCurrency", "btcTargetCurrency$delegate", "getBtcTargetCurrency", "setBtcTargetCurrency", "btcTargetCurrency", "usdTargetCurrency$delegate", "getUsdTargetCurrency", "setUsdTargetCurrency", "usdTargetCurrency", "lastActivePillId$delegate", "getLastActivePillId", "()I", "setLastActivePillId", "lastActivePillId", _UrlKt.FRAGMENT_ENCODE_SET, "activePills", "Ljava/util/List;", "getActivePills", "()Ljava/util/List;", "setActivePills", "(Ljava/util/List;)V", "hiddenPills", "getHiddenPills", "setHiddenPills", "Ljava/util/HashSet;", "pendingUpdates", "Ljava/util/HashSet;", "init", "Lkotlin/Unit;", "getInit$annotations", "TMessagesProj"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "PillStackConfig")
@SourceDebugExtension({"SMAP\nPillStackConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PillStackConfig.kt\ncom/exteragram/messenger/pillstack/core/PillStackConfig\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,177:1\n1915#2:178\n1916#2:180\n1#3:179\n14069#4,2:181\n*S KotlinDebug\n*F\n+ 1 PillStackConfig.kt\ncom/exteragram/messenger/pillstack/core/PillStackConfig\n*L\n66#1:178\n66#1:180\n150#1:181,2\n*E\n"})
/* loaded from: classes.dex */
public abstract class PillStackConfig {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = null;
    private static List<Integer> activePills;
    private static final BasePref btcTargetCurrency$delegate = null;
    private static boolean configLoaded;
    private static final BasePref customWeatherAddress$delegate = null;
    private static final BasePref customWeatherLocation$delegate = null;
    private static final SharedPreferences.Editor editor = null;
    private static final BasePref gramTargetCurrency$delegate = null;
    private static List<Integer> hiddenPills;
    private static final BasePref infiniteScrolling$delegate = null;
    private static final Unit init = null;
    private static final BasePref lastActivePillId$delegate = null;
    private static final HashSet<Integer> pendingUpdates = null;
    private static final SharedPreferences preferences = null;
    private static final Object sync = null;
    private static final BasePref usdTargetCurrency$delegate = null;
    private static final BasePref useCurrentLocation$delegate = null;

    public static boolean $r8$lambda$IRwijP9L3m8dKh_HFzlyXzfIHA8(int r0) {
        return !PillRegistry.isRegistered(r0);
    }

    public static boolean $r8$lambda$guHUWx_1IVaOaS4FforiTpgVDaQ(int r0) {
        return !PillRegistry.isRegistered(r0);
    }

    static {
        KProperty<?>[] r9 = {Reflection.mutableProperty0(new MutablePropertyReference0Impl(PillStackConfig.class, "useCurrentLocation", "getUseCurrentLocation()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(PillStackConfig.class, "customWeatherLocation", "getCustomWeatherLocation()Ljava/lang/String;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(PillStackConfig.class, "customWeatherAddress", "getCustomWeatherAddress()Ljava/lang/String;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(PillStackConfig.class, "infiniteScrolling", "getInfiniteScrolling()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(PillStackConfig.class, "gramTargetCurrency", "getGramTargetCurrency()Ljava/lang/String;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(PillStackConfig.class, "btcTargetCurrency", "getBtcTargetCurrency()Ljava/lang/String;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(PillStackConfig.class, "usdTargetCurrency", "getUsdTargetCurrency()Ljava/lang/String;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(PillStackConfig.class, "lastActivePillId", "getLastActivePillId()I", 1))};
        $$delegatedProperties = r9;
        SharedPreferences r1 = PreferencesUtils.getPreferences("pillstackconfig");
        preferences = r1;
        editor = r1.edit();
        sync = new Object();
        useCurrentLocation$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r9[0]);
        customWeatherLocation$delegate = new NullableStringPref(null, null, 2, null).provideDelegate(null, r9[1]);
        customWeatherAddress$delegate = new NullableStringPref(null, null, 2, null).provideDelegate(null, r9[2]);
        infiniteScrolling$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r9[3]);
        gramTargetCurrency$delegate = new StringPref("AUTO", null, 2, null).provideDelegate(null, r9[4]);
        btcTargetCurrency$delegate = new StringPref("AUTO", null, 2, null).provideDelegate(null, r9[5]);
        usdTargetCurrency$delegate = new StringPref("AUTO", null, 2, null).provideDelegate(null, r9[6]);
        lastActivePillId$delegate = new IntegerPref(-1, "lastActivePillId").provideDelegate(null, r9[7]);
        activePills = new ArrayList();
        hiddenPills = new ArrayList();
        pendingUpdates = new HashSet();
        loadConfig();
        init = Unit.INSTANCE;
    }

    public static final boolean checkAndClearPendingUpdate(int r1) {
        return pendingUpdates.remove(Integer.valueOf(r1));
    }

    public static final List<Integer> getActivePills() {
        return activePills;
    }

    public static final String getBtcTargetCurrency() {
        return (String) btcTargetCurrency$delegate.getValue(null, $$delegatedProperties[5]);
    }

    public static final boolean getConfigLoaded() {
        return configLoaded;
    }

    public static final String getCustomWeatherAddress() {
        return (String) customWeatherAddress$delegate.getValue(null, $$delegatedProperties[2]);
    }

    public static final String getCustomWeatherLocation() {
        return (String) customWeatherLocation$delegate.getValue(null, $$delegatedProperties[1]);
    }

    public static final ArrayList<Integer> getDefaultActivePills() {
        return new ArrayList();
    }

    public static final SharedPreferences.Editor getEditor() {
        return editor;
    }

    public static final String getGramTargetCurrency() {
        return (String) gramTargetCurrency$delegate.getValue(null, $$delegatedProperties[4]);
    }

    public static final List<Integer> getHiddenPills() {
        return hiddenPills;
    }

    public static final boolean getInfiniteScrolling() {
        return ((Boolean) infiniteScrolling$delegate.getValue(null, $$delegatedProperties[3])).booleanValue();
    }

    public static final int getLastActivePillId() {
        return ((Number) lastActivePillId$delegate.getValue(null, $$delegatedProperties[7])).intValue();
    }

    public static final SharedPreferences getPreferences() {
        return preferences;
    }

    public static final String getUsdTargetCurrency() {
        return (String) usdTargetCurrency$delegate.getValue(null, $$delegatedProperties[6]);
    }

    public static final boolean getUseCurrentLocation() {
        return ((Boolean) useCurrentLocation$delegate.getValue(null, $$delegatedProperties[0])).booleanValue();
    }

    public static final void loadConfig() {
        Object r0 = sync;
        monitor-enter(r0);
    L13:
        th = move-exception;
        throw th;
    L5:
        if (configLoaded == false) goto L8;
        monitor-exit(r0);
        return;
    L8:
        SharedPreferences r1 = preferences;     // Catch: Throwable -> L13
        String r2 = r1.getString("activePills", null);     // Catch: Throwable -> L13
        String r12 = r1.getString("hiddenPills", null);     // Catch: Throwable -> L13
        if (r2 == null) goto L17;
        activePills = CollectionsKt.toMutableList(parsePillsList(r2));     // Catch: Throwable -> L13
        if (r12 == null) goto L15;
        List<Integer> r13 = CollectionsKt.toMutableList(parsePillsList(r12));     // Catch: Throwable -> L13
    L16:
        hiddenPills = r13;     // Catch: Throwable -> L13
    L24:
        sanitizePills();     // Catch: Throwable -> L13
        configLoaded = true;     // Catch: Throwable -> L13
        Unit r14 = Unit.INSTANCE;     // Catch: Throwable -> L13
        monitor-exit(r0);
        return;
    L15:
        r13 = new ArrayList();     // Catch: Throwable -> L13
        goto L16
    L17:
        activePills = CollectionsKt.toMutableList(getDefaultActivePills());     // Catch: Throwable -> L13
        hiddenPills = new ArrayList();     // Catch: Throwable -> L13
        Iterator<PillRegistry.PillInfo> r15 = PillRegistry.getRegisteredPills().iterator();     // Catch: Throwable -> L13
    L19:
        if (r15.hasNext() == false) goto L23;
        PillRegistry.PillInfo r22 = r15.next();     // Catch: Throwable -> L13
        if (activePills.contains(Integer.valueOf(r22.id())) == true) goto L19;
        hiddenPills.add(Integer.valueOf(r22.id()));     // Catch: Throwable -> L13
        goto L19
    L23:
        savePillsLayout();     // Catch: Throwable -> L13
        goto L24
    }

    public static final void notifySettingsChanged(int... r4) {
        int r1 = 0;
        if (r4.length != 0) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == true) goto L12;
        int r02 = r4.length;
    L8:
        if (r1 >= r02) goto L10;
        pendingUpdates.add(Integer.valueOf(r4[r1]));
        r1 = r1 + 1;
        goto L8
    L10:
        Integer[] r42 = ArraysKt.toTypedArray(r4);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pillStackSettingsChanged, Arrays.copyOf(r42, r42.length));
        return;
    L12:
        Iterator<PillRegistry.PillInfo> r43 = PillRegistry.getRegisteredPills().iterator();
    L14:
        if (r43.hasNext() == false) goto L16;
        pendingUpdates.add(Integer.valueOf(r43.next().id()));
        goto L14
    L16:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.pillStackSettingsChanged, new Object[0]);
        return;
    L5:
        r0 = false;
        goto L6
    }

    private static final ArrayList<Integer> parsePillsList(String r7) {
        ArrayList<Integer> r0 = new ArrayList();
        if (r7 != null) goto L5;
    L16:
        return r0;
    L5:
        if (r7.length() == 0) goto L16;
        if (StringsKt.startsWith$default(r7, "[", false, 2, null) == false) goto L10;
        r7 = new Regex("[\\[\\]\"]").replace(r7, _UrlKt.FRAGMENT_ENCODE_SET);
    L10:
        Iterator r72 = StringsKt.split$default(r7, new String[]{","}, false, 0, 6, null).iterator();
    L12:
        if (r72.hasNext() == false) goto L16;
        Integer r1 = StringsKt.toIntOrNull(StringsKt.trim((String) r72.next()).toString());
        if (r1 == null) goto L12;
        r0.add(Integer.valueOf(r1.intValue()));
        goto L12
    }

    public static final void reloadConfig() {
        Object r0 = sync;
        monitor-enter(r0);
        configLoaded = false;     // Catch: Throwable -> L8
        loadConfig();     // Catch: Throwable -> L8
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L8
        monitor-exit(r0);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public static final void sanitizePills() {
        if (CollectionsKt.removeAll(activePills, new PillStackConfig$$ExternalSyntheticLambda0()) == false) goto L5;
    L8:
        boolean r0 = true;
    L9:
        Iterator<PillRegistry.PillInfo> r2 = PillRegistry.getRegisteredPills().iterator();
    L11:
        if (r2.hasNext() == false) goto L17;
        PillRegistry.PillInfo r3 = r2.next();
        if (activePills.contains(Integer.valueOf(r3.id())) == true) goto L11;
        if (hiddenPills.contains(Integer.valueOf(r3.id())) == true) goto L11;
        hiddenPills.add(Integer.valueOf(r3.id()));
        r0 = true;
        goto L11
    L17:
        if (r0 == false) goto L27;
        savePillsLayout();
        return;
    L27:
        return;
    L5:
        if (CollectionsKt.removeAll(hiddenPills, new PillStackConfig$$ExternalSyntheticLambda1()) == true) goto L8;
        r0 = false;
        goto L9
    }

    public static final void saveLastActivePillId(int r2) {
        setLastActivePillId(r2);
        editor.putInt("lastActivePillId", r2).apply();
    }

    public static final void savePillsLayout() {
        editor.putString("activePills", serializePillsList(activePills)).putString("hiddenPills", serializePillsList(hiddenPills)).apply();
    }

    private static final String serializePillsList(List<Integer> r9) {
        if (r9.isEmpty() == false) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return CollectionsKt.joinToString$default(r9, ",", null, null, 0, null, null, 62, null);
    }

    public static final void setBtcTargetCurrency(String r3) {
        btcTargetCurrency$delegate.setValue(null, $$delegatedProperties[5], r3);
    }

    public static final void setCustomWeatherAddress(String r3) {
        customWeatherAddress$delegate.setValue(null, $$delegatedProperties[2], r3);
    }

    public static final void setCustomWeatherLocation(String r3) {
        customWeatherLocation$delegate.setValue(null, $$delegatedProperties[1], r3);
    }

    public static final void setGramTargetCurrency(String r3) {
        gramTargetCurrency$delegate.setValue(null, $$delegatedProperties[4], r3);
    }

    public static final void setInfiniteScrolling(boolean r3) {
        infiniteScrolling$delegate.setValue(null, $$delegatedProperties[3], Boolean.valueOf(r3));
    }

    public static final void setLastActivePillId(int r3) {
        lastActivePillId$delegate.setValue(null, $$delegatedProperties[7], Integer.valueOf(r3));
    }

    public static final void setUsdTargetCurrency(String r3) {
        usdTargetCurrency$delegate.setValue(null, $$delegatedProperties[6], r3);
    }

    public static final void setUseCurrentLocation(boolean r3) {
        useCurrentLocation$delegate.setValue(null, $$delegatedProperties[0], Boolean.valueOf(r3));
    }

    public static final boolean shouldUpdatePill(Object[] r3, int... r4) {
        if (r3 != null) goto L5;
    L20:
        return true;
    L5:
        if (r3.length == 0) goto L20;
        if (r4.length == 0) goto L20;
        Iterator r32 = ArrayIteratorKt.iterator(r3);
    L12:
        if (r32.hasNext() == false) goto L18;
        Object r1 = r32.next();
        if ((r1 instanceof Integer) == false) goto L12;
        if (ArraysKt.contains(r4, ((Number) r1).intValue()) == false) goto L12;
        return true;
    L18:
        return false;
    }
}
