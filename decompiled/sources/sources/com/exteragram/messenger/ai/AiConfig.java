package com.exteragram.messenger.ai;

import android.content.SharedPreferences;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.ai.data.Message;
import com.exteragram.messenger.ai.data.Role;
import com.exteragram.messenger.ai.data.Service;
import com.exteragram.messenger.ai.data.Suggestions;
import com.exteragram.messenger.backup.PreferencesUtils;
import com.exteragram.messenger.config.BasePref;
import com.exteragram.messenger.config.BooleanPref;
import com.exteragram.messenger.config.IntegerPref;
import com.exteragram.messenger.config.NullableStringPref;
import com.exteragram.messenger.config.StringPref;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.lang.reflect.Type;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KProperty;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.UserConfig;

@Metadata(d1 = {"\u0000R\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0010\u000e\n\u0002\b\u000f\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0001\u0010\u0002\u001a\r\u0010\u0003\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0002\u001a\u0019\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000b\u001a\u0019\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\f\u0010\b\u001a\u0015\u0010\r\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\u000b\u001a\r\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0015\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u000e¢\u0006\u0004\b\u0012\u0010\u0013\u001a\r\u0010\u0014\u001a\u00020\u0000¢\u0006\u0004\b\u0014\u0010\u0002\u001a\u001d\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0015j\b\u0012\u0004\u0012\u00020\u000e`\u0016¢\u0006\u0004\b\u0017\u0010\u0018\u001a%\u0010\u001a\u001a\u00020\u00002\u0016\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0015j\b\u0012\u0004\u0012\u00020\u000e`\u0016¢\u0006\u0004\b\u001a\u0010\u001b\u001a%\u0010\u001e\u001a\u00020\u00002\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001c0\u0015j\b\u0012\u0004\u0012\u00020\u001c`\u0016¢\u0006\u0004\b\u001e\u0010\u001b\u001a\u001d\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u001c0\u0015j\b\u0012\u0004\u0012\u00020\u001c`\u0016¢\u0006\u0004\b\u001f\u0010\u0018\u001a\u0015\u0010!\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u001c¢\u0006\u0004\b!\u0010\"\u001a\u001d\u0010$\u001a\u0012\u0012\u0004\u0012\u00020#0\u0015j\b\u0012\u0004\u0012\u00020#`\u0016¢\u0006\u0004\b$\u0010\u0018\u001a%\u0010&\u001a\u00020\u00002\u0016\u0010%\u001a\u0012\u0012\u0004\u0012\u00020#0\u0015j\b\u0012\u0004\u0012\u00020#`\u0016¢\u0006\u0004\b&\u0010\u001b\u001a\r\u0010'\u001a\u00020\u0000¢\u0006\u0004\b'\u0010\u0002\u001a\r\u0010(\u001a\u00020\u0000¢\u0006\u0004\b(\u0010\u0002\"\u0014\u0010)\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b)\u0010*\"\u0014\u0010+\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b+\u0010*\"\u0017\u0010-\u001a\u00020,8\u0006¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u001b\u00106\u001a\u0002018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b4\u00105\"\u0014\u00107\u001a\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108\"+\u0010?\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010\u000b\"+\u0010C\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b@\u0010;\u001a\u0004\bA\u0010=\"\u0004\bB\u0010\u000b\"+\u0010I\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00048F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bD\u0010;\u001a\u0004\bE\u0010F\"\u0004\bG\u0010H\"+\u0010M\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bJ\u0010;\u001a\u0004\bK\u0010=\"\u0004\bL\u0010\u000b\"+\u0010Q\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bN\u0010;\u001a\u0004\bO\u0010=\"\u0004\bP\u0010\u000b\"+\u0010U\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bR\u0010;\u001a\u0004\bS\u0010=\"\u0004\bT\u0010\u000b\"+\u0010Y\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bV\u0010;\u001a\u0004\bW\u0010=\"\u0004\bX\u0010\u000b\"+\u0010]\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bZ\u0010;\u001a\u0004\b[\u0010=\"\u0004\b\\\u0010\u000b\"/\u0010d\u001a\u0004\u0018\u00010^2\b\u00109\u001a\u0004\u0018\u00010^8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b_\u0010;\u001a\u0004\b`\u0010a\"\u0004\bb\u0010c\"+\u0010h\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00048B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\be\u0010;\u001a\u0004\bf\u0010F\"\u0004\bg\u0010H\"+\u0010l\u001a\u00020^2\u0006\u00109\u001a\u00020^8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bi\u0010;\u001a\u0004\bj\u0010a\"\u0004\bk\u0010c¨\u0006m"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "migrateLegacyConfig", "()V", "ensureConfigMigrated", _UrlKt.FRAGMENT_ENCODE_SET, "account", _UrlKt.FRAGMENT_ENCODE_SET, "getReplaceTelegramEditor", "(I)Z", "value", "setReplaceTelegramEditor", "(Z)V", "getReplaceTelegramSummaries", "setReplaceTelegramSummaries", "Lcom/exteragram/messenger/ai/data/Service;", "getSelectedService", "()Lcom/exteragram/messenger/ai/data/Service;", "service", "setSelectedServices", "(Lcom/exteragram/messenger/ai/data/Service;)V", "clearSelectedService", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getServices", "()Ljava/util/ArrayList;", "services", "saveServices", "(Ljava/util/ArrayList;)V", "Lcom/exteragram/messenger/ai/data/Role;", "roles", "saveRoles", "getRoles", "role", "setSelectedAiRole", "(Lcom/exteragram/messenger/ai/data/Role;)V", "Lcom/exteragram/messenger/ai/data/Message;", "getConversationHistory", "history", "saveConversationHistory", "clearConversationHistory", "removeLastFromHistory", "DEFAULT_SERVICE", "Lcom/exteragram/messenger/ai/data/Service;", "ON_DEVICE_SERVICE", "Landroid/content/SharedPreferences;", "preferences", "Landroid/content/SharedPreferences;", "getPreferences", "()Landroid/content/SharedPreferences;", "Landroid/content/SharedPreferences$Editor;", "editor$delegate", "Lkotlin/Lazy;", "getEditor", "()Landroid/content/SharedPreferences$Editor;", "editor", "legacyConfigMigrated", "Lkotlin/Unit;", "<set-?>", "saveHistory$delegate", "Lcom/exteragram/messenger/config/BasePref;", "getSaveHistory", "()Z", "setSaveHistory", "saveHistory", "responseStreaming$delegate", "getResponseStreaming", "setResponseStreaming", "responseStreaming", "temperature$delegate", "getTemperature", "()I", "setTemperature", "(I)V", "temperature", "showResponseOnly$delegate", "getShowResponseOnly", "setShowResponseOnly", "showResponseOnly", "insertAsQuote$delegate", "getInsertAsQuote", "setInsertAsQuote", "insertAsQuote", "onDeviceReasoning$delegate", "getOnDeviceReasoning", "setOnDeviceReasoning", "onDeviceReasoning", "onDevicePreviewModel$delegate", "getOnDevicePreviewModel", "setOnDevicePreviewModel", "onDevicePreviewModel", "onDeviceFastModel$delegate", "getOnDeviceFastModel", "setOnDeviceFastModel", "onDeviceFastModel", _UrlKt.FRAGMENT_ENCODE_SET, "selectedServiceId$delegate", "getSelectedServiceId", "()Ljava/lang/String;", "setSelectedServiceId", "(Ljava/lang/String;)V", "selectedServiceId", "selectedServiceHash$delegate", "getSelectedServiceHash", "setSelectedServiceHash", "selectedServiceHash", "selectedRole$delegate", "getSelectedRole", "setSelectedRole", "selectedRole", "TMessagesProj"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "AiConfig")
@SourceDebugExtension({"SMAP\nAiConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AiConfig.kt\ncom/exteragram/messenger/ai/AiConfig\n+ 2 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,217:1\n41#2,6:218\n47#2,6:226\n1915#3,2:224\n1915#3,2:233\n1#4:232\n*S KotlinDebug\n*F\n+ 1 AiConfig.kt\ncom/exteragram/messenger/ai/AiConfig\n*L\n65#1:218,6\n65#1:226,6\n66#1:224,2\n149#1:233,2\n*E\n"})
/* loaded from: classes4.dex */
public abstract class AiConfig {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = null;

    @JvmField
    public static final Service DEFAULT_SERVICE = null;

    @JvmField
    public static final Service ON_DEVICE_SERVICE = null;
    private static final Lazy editor$delegate = null;
    private static final BasePref insertAsQuote$delegate = null;
    private static final Unit legacyConfigMigrated = null;
    private static final BasePref onDeviceFastModel$delegate = null;
    private static final BasePref onDevicePreviewModel$delegate = null;
    private static final BasePref onDeviceReasoning$delegate = null;
    private static final SharedPreferences preferences = null;
    private static final BasePref responseStreaming$delegate = null;
    private static final BasePref saveHistory$delegate = null;
    private static final BasePref selectedRole$delegate = null;
    private static final BasePref selectedServiceHash$delegate = null;
    private static final BasePref selectedServiceId$delegate = null;
    private static final BasePref showResponseOnly$delegate = null;
    private static final BasePref temperature$delegate = null;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class EntriesMappings {
        public static final /* synthetic */ EnumEntries<Suggestions> entries$0 = null;

        static {
            entries$0 = EnumEntriesKt.enumEntries(Suggestions.values());
        }
    }

    /* renamed from: $r8$lambda$PZg0RSpjfILGqc_v0Q8lJ-F_FU8, reason: not valid java name */
    public static SharedPreferences.Editor m1013$r8$lambda$PZg0RSpjfILGqc_v0Q8lJF_FU8() {
        return preferences.edit();
    }

    static {
        KProperty<?>[] r13 = {Reflection.mutableProperty0(new MutablePropertyReference0Impl(AiConfig.class, "saveHistory", "getSaveHistory()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(AiConfig.class, "responseStreaming", "getResponseStreaming()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(AiConfig.class, "temperature", "getTemperature()I", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(AiConfig.class, "showResponseOnly", "getShowResponseOnly()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(AiConfig.class, "insertAsQuote", "getInsertAsQuote()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(AiConfig.class, "onDeviceReasoning", "getOnDeviceReasoning()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(AiConfig.class, "onDevicePreviewModel", "getOnDevicePreviewModel()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(AiConfig.class, "onDeviceFastModel", "getOnDeviceFastModel()Z", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(AiConfig.class, "selectedServiceId", "getSelectedServiceId()Ljava/lang/String;", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(AiConfig.class, "selectedServiceHash", "getSelectedServiceHash()I", 1)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(AiConfig.class, "selectedRole", "getSelectedRole()Ljava/lang/String;", 1))};
        $$delegatedProperties = r13;
        Service r1 = new Service(PluginsConstants.Settings.DEFAULT, "https://generativelanguage.googleapis.com/v1beta", "gemini-3.5-flash", null);
        DEFAULT_SERVICE = r1;
        Service r2 = new Service("on-device", null, "Gemini Nano", null);
        r2.setKind(Service.Kind.ON_DEVICE);
        ON_DEVICE_SERVICE = r2;
        preferences = PreferencesUtils.getPreferences("aiConfig");
        editor$delegate = LazyKt.lazy(new AiConfig$$ExternalSyntheticLambda0());
        migrateLegacyConfig();
        legacyConfigMigrated = Unit.INSTANCE;
        saveHistory$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r13[0]);
        responseStreaming$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r13[1]);
        temperature$delegate = new IntegerPref(10, null, 2, null).provideDelegate(null, r13[2]);
        showResponseOnly$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r13[3]);
        insertAsQuote$delegate = new BooleanPref(true, null, 2, null).provideDelegate(null, r13[4]);
        onDeviceReasoning$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r13[5]);
        onDevicePreviewModel$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r13[6]);
        onDeviceFastModel$delegate = new BooleanPref(false, null, 2, null).provideDelegate(null, r13[7]);
        selectedServiceId$delegate = new NullableStringPref(null, "selectedServiceId").provideDelegate(null, r13[8]);
        selectedServiceHash$delegate = new IntegerPref(r1.getLegacyHash(), "selectedService").provideDelegate(null, r13[9]);
        selectedRole$delegate = new StringPref(EntriesMappings.entries$0.get(0).getRole().getName(), null, 2, null).provideDelegate(null, r13[10]);
    }

    public static final void clearConversationHistory() {
        getEditor().remove("history").apply();
    }

    public static final void clearSelectedService() {
        setSelectedServiceId(null);
        ExteraConfig.getEditor().remove("selectedService").apply();
    }

    public static final void ensureConfigMigrated() {
    }

    public static final ArrayList<Message> getConversationHistory() {
        String r0 = preferences.getString("history", null);
        if (r0 == null) goto L5;
        Type r1 = new AiConfig$getConversationHistory$type$1().getType();     // Catch: Exception -> L10
        ArrayList<Message> r02 = (ArrayList) ExteraConfig.getGSON().fromJson(r0, r1);     // Catch: Exception -> L10
        if (r02 != null) goto L15;
        return new ArrayList();
    L15:
        return r02;
    L10:
        e = move-exception;
        FileLog.e(e);
        return new ArrayList();
    L5:
        return new ArrayList();
    }

    public static final SharedPreferences.Editor getEditor() {
        return (SharedPreferences.Editor) editor$delegate.getValue();
    }

    public static final boolean getInsertAsQuote() {
        return ((Boolean) insertAsQuote$delegate.getValue(null, $$delegatedProperties[4])).booleanValue();
    }

    public static final boolean getOnDeviceFastModel() {
        return ((Boolean) onDeviceFastModel$delegate.getValue(null, $$delegatedProperties[7])).booleanValue();
    }

    public static final boolean getOnDevicePreviewModel() {
        return ((Boolean) onDevicePreviewModel$delegate.getValue(null, $$delegatedProperties[6])).booleanValue();
    }

    public static final boolean getOnDeviceReasoning() {
        return ((Boolean) onDeviceReasoning$delegate.getValue(null, $$delegatedProperties[5])).booleanValue();
    }

    public static final SharedPreferences getPreferences() {
        return preferences;
    }

    @JvmOverloads
    public static final boolean getReplaceTelegramEditor(int r2) {
        return ExteraConfig.getPreferences().getBoolean("replaceTelegramEditor", !UserConfig.getInstance(r2).isPremium());
    }

    public static /* synthetic */ boolean getReplaceTelegramEditor$default(int r0, int r1, Object r2) {
        if ((r1 & 1) == 0) goto L6;
        r0 = UserConfig.selectedAccount;
    L6:
        return getReplaceTelegramEditor(r0);
    }

    @JvmOverloads
    public static final boolean getReplaceTelegramSummaries(int r2) {
        return ExteraConfig.getPreferences().getBoolean("replaceTelegramSummaries", !UserConfig.getInstance(r2).isPremium());
    }

    public static /* synthetic */ boolean getReplaceTelegramSummaries$default(int r0, int r1, Object r2) {
        if ((r1 & 1) == 0) goto L6;
        r0 = UserConfig.selectedAccount;
    L6:
        return getReplaceTelegramSummaries(r0);
    }

    public static final boolean getResponseStreaming() {
        return ((Boolean) responseStreaming$delegate.getValue(null, $$delegatedProperties[1])).booleanValue();
    }

    public static final ArrayList<Role> getRoles() {
        String r0 = preferences.getString("roles", null);
        if (r0 == null) goto L5;
        Type r1 = new AiConfig$getRoles$type$1().getType();     // Catch: Exception -> L10
        ArrayList<Role> r02 = (ArrayList) ExteraConfig.getGSON().fromJson(r0, r1);     // Catch: Exception -> L10
        if (r02 != null) goto L15;
        return new ArrayList();
    L15:
        return r02;
    L10:
        e = move-exception;
        FileLog.e(e);
        return new ArrayList();
    L5:
        return new ArrayList();
    }

    public static final boolean getSaveHistory() {
        return ((Boolean) saveHistory$delegate.getValue(null, $$delegatedProperties[0])).booleanValue();
    }

    public static final String getSelectedRole() {
        return (String) selectedRole$delegate.getValue(null, $$delegatedProperties[10]);
    }

    public static final Service getSelectedService() {
        ArrayList<Service> r0 = getServices();
        String r1 = getSelectedServiceId();
        int r2 = 0;
        Service r3 = null;
        if (r1 == null) goto L18;
        if (r1.length() == 0) goto L18;
        int r4 = r0.size();
        int r5 = 0;
    L8:
        if (r5 >= r4) goto L12;
        Service r6 = r0.get(r5);
        r5 = r5 + 1;
        if (Intrinsics.areEqual(r6.getId(), r1) == false) goto L8;
    L13:
        Service r62 = r6;
        if (r62 == null) goto L16;
        return r62;
    L16:
        setSelectedServiceId(null);
        goto L18
    L12:
        r6 = null;
    L18:
        if (ExteraConfig.getPreferences().contains("selectedService") == false) goto L29;
        int r12 = getSelectedServiceHash();
        int r52 = r0.size();
    L20:
        if (r2 >= r52) goto L24;
        Service r63 = r0.get(r2);
        r2 = r2 + 1;
        if (r63.getLegacyHash() != r12) goto L20;
        r3 = r63;
    L24:
        Service r32 = r3;
        if (r32 == null) goto L28;
        setSelectedServiceId(r32.getId());
        ExteraConfig.getEditor().remove("selectedService").apply();
        return r32;
    L28:
        ExteraConfig.getEditor().remove("selectedService").apply();
    L29:
        Service r02 = (Service) CollectionsKt.firstOrNull(r0);
        if (r02 == null) goto L32;
        return r02;
    L32:
        return DEFAULT_SERVICE;
    }

    private static final int getSelectedServiceHash() {
        return ((Number) selectedServiceHash$delegate.getValue(null, $$delegatedProperties[9])).intValue();
    }

    public static final String getSelectedServiceId() {
        return (String) selectedServiceId$delegate.getValue(null, $$delegatedProperties[8]);
    }

    public static final ArrayList<Service> getServices() {
        String r0 = preferences.getString("services", null);
        if (r0 == null) goto L5;
        ArrayList<Service> r02 = (ArrayList) ExteraConfig.getGSON().fromJson(r0, new AiConfig$getServices$type$1().getType());     // Catch: Exception -> L17
        if (r02 != null) goto L9;
        r02 = new ArrayList();     // Catch: Exception -> L17
    L9:
        int r1 = r02.size();     // Catch: Exception -> L17
        boolean r2 = false;
        int r3 = 0;
    L10:
        if (r3 >= r1) goto L14;
        Service r4 = r02.get(r3);     // Catch: Exception -> L17
        r3 = r3 + 1;     // Catch: Exception -> L17
        if (r4.ensureId() == false) goto L10;
        r2 = true;
        goto L10
    L14:
        if (r2 == false) goto L16;
        saveServices(r02);     // Catch: Exception -> L17
    L16:
        return r02;
    L17:
        e = move-exception;
        FileLog.e(e);
        return new ArrayList();
    L5:
        return new ArrayList();
    }

    public static final boolean getShowResponseOnly() {
        return ((Boolean) showResponseOnly$delegate.getValue(null, $$delegatedProperties[3])).booleanValue();
    }

    public static final int getTemperature() {
        return ((Number) temperature$delegate.getValue(null, $$delegatedProperties[2])).intValue();
    }

    private static final void migrateLegacyConfig() {
        SharedPreferences r0 = PreferencesUtils.getPreferences("pillstackconfig");
        SharedPreferences.Editor r1 = preferences.edit();
        ArrayList r2 = new ArrayList();
        String[] r3 = {"services", "roles", "history"};
        int r4 = 0;
        int r5 = 0;
    L4:
        if (r5 >= 3) goto L15;
        String r6 = r3[r5];
        if (preferences.contains(r6) == true) goto L13;
        Object r7 = r0.getAll().get(r6);
        if ((r7 instanceof String) == false) goto L10;
        String r72 = (String) r7;
    L11:
        if (r72 == null) goto L13;
        r1.putString(r6, r72);
        r2.add(r6);
        goto L13
    L10:
        r72 = null;
    L13:
        r5 = r5 + 1;
        goto L4
    L15:
        if (r2.isEmpty() == false) goto L17;
        return;
    L17:
        if (r1.commit() == false) goto L29;
        SharedPreferences.Editor r02 = r0.edit();
        int r12 = r2.size();
    L19:
        if (r4 >= r12) goto L21;
        Object r32 = r2.get(r4);
        r4 = r4 + 1;
        r02.remove((String) r32);
        goto L19
    L21:
        r02.apply();
        return;
    }

    public static final void removeLastFromHistory() {
        ArrayList<Message> r0 = getConversationHistory();
        if (r0.size() < 2) goto L6;
        r0.remove(r0.size() - 1);
        r0.remove(r0.size() - 1);
        saveConversationHistory(r0);
        return;
    }

    public static final void saveConversationHistory(ArrayList<Message> r2) {
        getEditor().putString("history", ExteraConfig.getGSON().toJson(r2)).apply();
    }

    public static final void saveRoles(ArrayList<Role> r2) {
        getEditor().putString("roles", ExteraConfig.getGSON().toJson(r2)).apply();
    }

    public static final void saveServices(ArrayList<Service> r2) {
        getEditor().putString("services", ExteraConfig.getGSON().toJson(r2)).apply();
    }

    public static final void setInsertAsQuote(boolean r3) {
        insertAsQuote$delegate.setValue(null, $$delegatedProperties[4], Boolean.valueOf(r3));
    }

    public static final void setOnDeviceFastModel(boolean r3) {
        onDeviceFastModel$delegate.setValue(null, $$delegatedProperties[7], Boolean.valueOf(r3));
    }

    public static final void setOnDevicePreviewModel(boolean r3) {
        onDevicePreviewModel$delegate.setValue(null, $$delegatedProperties[6], Boolean.valueOf(r3));
    }

    public static final void setOnDeviceReasoning(boolean r3) {
        onDeviceReasoning$delegate.setValue(null, $$delegatedProperties[5], Boolean.valueOf(r3));
    }

    public static final void setReplaceTelegramEditor(boolean r2) {
        ExteraConfig.getEditor().putBoolean("replaceTelegramEditor", r2).apply();
    }

    public static final void setReplaceTelegramSummaries(boolean r2) {
        ExteraConfig.getEditor().putBoolean("replaceTelegramSummaries", r2).apply();
    }

    public static final void setResponseStreaming(boolean r3) {
        responseStreaming$delegate.setValue(null, $$delegatedProperties[1], Boolean.valueOf(r3));
    }

    public static final void setSaveHistory(boolean r3) {
        saveHistory$delegate.setValue(null, $$delegatedProperties[0], Boolean.valueOf(r3));
    }

    public static final void setSelectedAiRole(Role r0) {
        setSelectedRole(r0.getName());
    }

    public static final void setSelectedRole(String r3) {
        selectedRole$delegate.setValue(null, $$delegatedProperties[10], r3);
    }

    public static final void setSelectedServiceId(String r3) {
        selectedServiceId$delegate.setValue(null, $$delegatedProperties[8], r3);
    }

    public static final void setSelectedServices(Service r1) {
        setSelectedServiceId(r1.getId());
        ExteraConfig.getEditor().remove("selectedService").apply();
    }

    public static final void setShowResponseOnly(boolean r3) {
        showResponseOnly$delegate.setValue(null, $$delegatedProperties[3], Boolean.valueOf(r3));
    }

    public static final void setTemperature(int r3) {
        temperature$delegate.setValue(null, $$delegatedProperties[2], Integer.valueOf(r3));
    }
}
