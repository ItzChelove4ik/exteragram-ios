package com.exteragram.messenger.preferences;

import android.view.View;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.preferences.utils.SettingsRegistry;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BooleanSupplier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KMutableProperty0;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationBadge;
import org.telegram.ui.Cells.TextCheckCell2;
import org.telegram.ui.Components.UItem;

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0002GHBE\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eBI\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u000f\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u001b\u0010!\u001a\u00020\u00002\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00180\u001f¢\u0006\u0004\b!\u0010\"J\u0015\u0010$\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\t¢\u0006\u0004\b$\u0010%J=\u0010\u0014\u001a\u00020\u00002\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u000f2\b\b\u0002\u0010&\u001a\u00020\u00042\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000b0'2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0014\u0010*J?\u0010\u0014\u001a\u00020\u00002\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u000f2\b\b\u0002\u0010&\u001a\u00020\u00042\u0006\u0010,\u001a\u00020+2\u0006\u0010.\u001a\u00020-2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0014\u0010/JE\u00101\u001a\u00020\u00002\u0006\u00100\u001a\u00020+2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u000f2\b\b\u0002\u0010&\u001a\u00020\u00042\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000b0'2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b1\u00102JG\u00101\u001a\u00020\u00002\u0006\u00100\u001a\u00020+2\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u000f2\b\b\u0002\u0010&\u001a\u00020\u00042\u0006\u0010,\u001a\u00020+2\u0006\u0010.\u001a\u00020-2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b1\u00103J%\u00108\u001a\u00020\u00182\u0016\u00107\u001a\u0012\u0012\u0004\u0012\u00020504j\b\u0012\u0004\u0012\u000205`6¢\u0006\u0004\b8\u00109J\u0015\u0010;\u001a\u00020\u00182\u0006\u0010:\u001a\u000205¢\u0006\u0004\b;\u0010<R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010=R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010>R\u0014\u0010\u0006\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010>R\u0016\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010?R\u0016\u0010\n\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010@R\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010AR\u001a\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00120B8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u0010DR\u001e\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010ER\u0016\u0010F\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bF\u0010A¨\u0006I"}, d2 = {"Lcom/exteragram/messenger/preferences/SwitchGroup;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "fragment", _UrlKt.FRAGMENT_ENCODE_SET, "id", "titleRes", _UrlKt.FRAGMENT_ENCODE_SET, "title", _UrlKt.FRAGMENT_ENCODE_SET, "linkAlias", _UrlKt.FRAGMENT_ENCODE_SET, "searchable", "<init>", "(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;IILjava/lang/CharSequence;Ljava/lang/String;Z)V", _UrlKt.FRAGMENT_ENCODE_SET, "enum", "(Lcom/exteragram/messenger/preferences/BasePreferencesActivity;Ljava/lang/Enum;ILjava/lang/CharSequence;Ljava/lang/String;Z)V", "Lcom/exteragram/messenger/preferences/SwitchGroup$Child;", "child", "add", "(Lcom/exteragram/messenger/preferences/SwitchGroup$Child;)Lcom/exteragram/messenger/preferences/SwitchGroup;", "Landroid/view/View;", PluginsConstants.Settings.VIEW, _UrlKt.FRAGMENT_ENCODE_SET, "onSwitchClick", "(Landroid/view/View;)V", "()Ljava/lang/CharSequence;", "enabledOnly", NotificationBadge.NewHtcHomeBadger.COUNT, "(Z)I", "Lkotlin/Function0;", "runnable", "onChanged", "(Lkotlin/jvm/functions/Function0;)Lcom/exteragram/messenger/preferences/SwitchGroup;", "alias", "markNew", "(Ljava/lang/String;)Lcom/exteragram/messenger/preferences/SwitchGroup;", "textRes", "Lkotlin/reflect/KMutableProperty0;", "property", "text", "(Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;", "Ljava/util/function/BooleanSupplier;", "getter", "Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;", "setter", "(Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;", "visible", "addIf", "(Ljava/util/function/BooleanSupplier;Ljava/lang/Enum;ILkotlin/reflect/KMutableProperty0;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;", "(Ljava/util/function/BooleanSupplier;Ljava/lang/Enum;ILjava/util/function/BooleanSupplier;Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;Ljava/lang/CharSequence;)Lcom/exteragram/messenger/preferences/SwitchGroup;", "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "Lkotlin/collections/ArrayList;", PluginsConstants.Settings.ITEMS, "fill", "(Ljava/util/ArrayList;)V", PluginsConstants.Settings.ITEM, "onClick", "(Lorg/telegram/ui/Components/UItem;)V", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "I", "Ljava/lang/CharSequence;", "Ljava/lang/String;", "Z", _UrlKt.FRAGMENT_ENCODE_SET, "children", "Ljava/util/List;", "Lkotlin/jvm/functions/Function0;", "expanded", "Setter", "Child", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSwitchGroup.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SwitchGroup.kt\ncom/exteragram/messenger/preferences/SwitchGroup\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,178:1\n1#2:179\n296#3,2:180\n1834#3,4:182\n*S KotlinDebug\n*F\n+ 1 SwitchGroup.kt\ncom/exteragram/messenger/preferences/SwitchGroup\n*L\n127#1:180,2\n150#1:182,4\n*E\n"})
/* loaded from: classes4.dex */
public final class SwitchGroup {
    private final List<Child> children;
    private boolean expanded;
    private final BasePreferencesActivity fragment;
    private final int id;
    private final String linkAlias;
    private Function0<Unit> onChanged;
    private final boolean searchable;
    private final CharSequence title;
    private final int titleRes;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0013\u001a\u0004\b\u0016\u0010\u0015R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0017\u001a\u0004\b\u0018\u0010\u0012R\u001f\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006¢\u0006\f\n\u0004\b\n\u0010\u0019\u001a\u0004\b\u001c\u0010\u001bR#\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u000b8\u0006¢\u0006\f\n\u0004\b\r\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR$\u0010!\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u0006'"}, d2 = {"Lcom/exteragram/messenger/preferences/SwitchGroup$Child;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "id", "textRes", _UrlKt.FRAGMENT_ENCODE_SET, "text", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "visible", "getter", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "setter", "<init>", "(IILjava/lang/CharSequence;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "isVisible", "()Z", "()Ljava/lang/CharSequence;", "I", "getId", "()I", "getTextRes", "Ljava/lang/CharSequence;", "getText", "Lkotlin/jvm/functions/Function0;", "getVisible", "()Lkotlin/jvm/functions/Function0;", "getGetter", "Lkotlin/jvm/functions/Function1;", "getSetter", "()Lkotlin/jvm/functions/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "newFeatureAlias", "Ljava/lang/String;", "getNewFeatureAlias", "()Ljava/lang/String;", "setNewFeatureAlias", "(Ljava/lang/String;)V", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Child {
        private final Function0<Boolean> getter;
        private final int id;
        private String newFeatureAlias;
        private final Function1<Boolean, Unit> setter;
        private final CharSequence text;
        private final int textRes;
        private final Function0<Boolean> visible;

        /* JADX WARN: Multi-variable type inference failed */
        public Child(int r1, int r2, CharSequence r3, Function0<Boolean> r4, Function0<Boolean> r5, Function1<? super Boolean, Unit> r6) {
            this.id = r1;
            this.textRes = r2;
            this.text = r3;
            this.visible = r4;
            this.getter = r5;
            this.setter = r6;
        }

        public final Function0<Boolean> getGetter() {
            return this.getter;
        }

        public final int getId() {
            return this.id;
        }

        public final Function1<Boolean, Unit> getSetter() {
            return this.setter;
        }

        public final boolean isVisible() {
            Function0<Boolean> r0 = this.visible;
            if (r0 != null) goto L5;
            return true;
        L5:
            return r0.invoke().booleanValue();
        }

        public final void setNewFeatureAlias(String r1) {
            this.newFeatureAlias = r1;
        }

        public final CharSequence text() {
            CharSequence r0 = this.text;
            if (r0 != null) goto L5;
            r0 = LocaleController.getString(this.textRes);
        L5:
            String r1 = this.newFeatureAlias;
            if (r1 != null) goto L8;
        L11:
            return r0;
        L8:
            if (SettingsRegistry.markAsNewFeature(r1) == false) goto L11;
            return LocaleUtils.applyNewSpan(r0);
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/preferences/SwitchGroup$Setter;", _UrlKt.FRAGMENT_ENCODE_SET, "set", _UrlKt.FRAGMENT_ENCODE_SET, "checked", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Setter {
        void set(boolean r1);
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.preferences.SwitchGroup$add$5, reason: invalid class name */
    public static final /* synthetic */ class AnonymousClass5 extends FunctionReferenceImpl implements Function0<Boolean> {
        public AnonymousClass5(Object r8) {
            super(0, r8, BooleanSupplier.class, "getAsBoolean", "getAsBoolean()Z", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(((BooleanSupplier) this.receiver).getAsBoolean());
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return invoke();
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.preferences.SwitchGroup$add$6, reason: invalid class name */
    public static final /* synthetic */ class AnonymousClass6 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        public AnonymousClass6(Object r8) {
            super(1, r8, Setter.class, "set", "set(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean r1) {
            invoke(r1.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean r1) {
            ((Setter) this.receiver).set(r1);
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.preferences.SwitchGroup$addIf$4, reason: invalid class name */
    public static final /* synthetic */ class AnonymousClass4 extends FunctionReferenceImpl implements Function0<Boolean> {
        public AnonymousClass4(Object r8) {
            super(0, r8, BooleanSupplier.class, "getAsBoolean", "getAsBoolean()Z", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(((BooleanSupplier) this.receiver).getAsBoolean());
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return invoke();
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.preferences.SwitchGroup$addIf$7, reason: invalid class name */
    public static final /* synthetic */ class AnonymousClass7 extends FunctionReferenceImpl implements Function0<Boolean> {
        public AnonymousClass7(Object r8) {
            super(0, r8, BooleanSupplier.class, "getAsBoolean", "getAsBoolean()Z", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(((BooleanSupplier) this.receiver).getAsBoolean());
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return invoke();
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.preferences.SwitchGroup$addIf$8, reason: invalid class name */
    public static final /* synthetic */ class AnonymousClass8 extends FunctionReferenceImpl implements Function0<Boolean> {
        public AnonymousClass8(Object r8) {
            super(0, r8, BooleanSupplier.class, "getAsBoolean", "getAsBoolean()Z", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(((BooleanSupplier) this.receiver).getAsBoolean());
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return invoke();
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.preferences.SwitchGroup$addIf$9, reason: invalid class name */
    public static final /* synthetic */ class AnonymousClass9 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        public AnonymousClass9(Object r8) {
            super(1, r8, Setter.class, "set", "set(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean r1) {
            invoke(r1.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean r1) {
            ((Setter) this.receiver).set(r1);
        }
    }

    public static Unit $r8$lambda$H1vtAg21u4HYev2gzJx5EFysn30(KMutableProperty0 r0, boolean r1) {
        r0.set(Boolean.valueOf(r1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean $r8$lambda$NrwV9E00icjSfutjNzsualdsb7A(KMutableProperty0 r0) {
        return ((Boolean) r0.get()).booleanValue();
    }

    public static Unit $r8$lambda$Wm1qktTcUdWT7W6MQ8u_rRLcscg(KMutableProperty0 r0, boolean r1) {
        r0.set(Boolean.valueOf(r1));
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$aeufRtXptfL3O3SkKV8uI86o1Bk(SwitchGroup r0, View r1) {
        r0.onSwitchClick(r1);
    }

    /* renamed from: $r8$lambda$e8_Wb2zIsFTiLBfPR7uY6g-swZ0, reason: not valid java name */
    public static void m1651$r8$lambda$e8_Wb2zIsFTiLBfPR7uY6gswZ0(Child r0, boolean r1) {
        r0.getSetter().invoke(Boolean.valueOf(r1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: $r8$lambda$oN8i_dARB3CxVsO-sZu-V-RlNAI, reason: not valid java name */
    public static boolean m1652$r8$lambda$oN8i_dARB3CxVsOsZuVRlNAI(KMutableProperty0 r0) {
        return ((Boolean) r0.get()).booleanValue();
    }

    @JvmOverloads
    public SwitchGroup(BasePreferencesActivity r1, int r2, int r3, CharSequence r4, String r5, boolean r6) {
        this.fragment = r1;
        this.id = r2;
        this.titleRes = r3;
        this.title = r4;
        this.linkAlias = r5;
        this.searchable = r6;
        this.children = new ArrayList();
    }

    @JvmOverloads
    public SwitchGroup(BasePreferencesActivity r8, Enum<?> r9, int r10, CharSequence r11, String r12, boolean r13) {
        this(r8, r9.ordinal() + 1, r10, r11, r12, r13);
    }

    public /* synthetic */ SwitchGroup(BasePreferencesActivity r8, Enum r9, int r10, CharSequence r11, String r12, boolean r13, int r14, DefaultConstructorMarker r15) {
        if ((r14 & 4) == 0) goto L5;
        r10 = 0;
    L5:
        int r3 = r10;
        if ((r14 & 8) == 0) goto L8;
        CharSequence r4 = null;
    L10:
        if ((r14 & 16) == 0) goto L12;
        String r5 = null;
    L14:
        if ((r14 & 32) == 0) goto L16;
        r13 = true;
    L16:
        this(r8, r9, r3, r4, r5, r13);
        return;
    L12:
        r5 = r12;
        goto L14
    L8:
        r4 = r11;
        goto L10
    }

    private final SwitchGroup add(Child r2) {
        this.children.add(r2);
        return this;
    }

    public static /* synthetic */ SwitchGroup add$default(SwitchGroup r0, Enum r1, int r2, KMutableProperty0 r3, CharSequence r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r5 & 8) == 0) goto L9;
        r4 = null;
    L9:
        return r0.add(r1, r2, r3, r4);
    }

    public static /* synthetic */ SwitchGroup addIf$default(SwitchGroup r7, BooleanSupplier r8, Enum r9, int r10, BooleanSupplier r11, Setter r12, CharSequence r13, int r14, Object r15) {
        if ((r14 & 4) == 0) goto L5;
        r10 = 0;
    L5:
        int r3 = r10;
        if ((r14 & 32) == 0) goto L9;
        r13 = null;
    L9:
        return r7.addIf(r8, r9, r3, r11, r12, r13);
    }

    public static /* synthetic */ SwitchGroup addIf$default(SwitchGroup r6, BooleanSupplier r7, Enum r8, int r9, KMutableProperty0 r10, CharSequence r11, int r12, Object r13) {
        if ((r12 & 4) == 0) goto L5;
        r9 = 0;
    L5:
        int r3 = r9;
        if ((r12 & 16) == 0) goto L9;
        r11 = null;
    L9:
        return r6.addIf(r7, r8, r3, r10, r11);
    }

    private final int count(boolean r4) {
        List<Child> r3 = this.children;
        int r1 = 0;
        if ((r3 instanceof Collection) == true) goto L5;
    L7:
        Iterator<T> r32 = r3.iterator();
    L9:
        if (r32.hasNext() == false) goto L18;
        Child r0 = (Child) r32.next();
        if (r0.isVisible() == false) goto L9;
        if (r4 == false) goto L15;
        if (r0.getGetter().invoke().booleanValue() == false) goto L9;
    L15:
        r1 = r1 + 1;
        if (r1 >= 0) goto L9;
        CollectionsKt.throwCountOverflow();
        goto L9
    L18:
        return r1;
    L5:
        if (r3.isEmpty() == false) goto L7;
        return 0;
    }

    private final void onSwitchClick(View r6) {
        UItem r62 = this.fragment.getListView$TMessagesProj().findItemByItemId(((TextCheckCell2) r6).id);
        boolean r0 = !r62.checked;
        Iterator<Child> r2 = this.children.iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        Child r3 = r2.next();
        if (r3.isVisible() == false) goto L4;
        r3.getSetter().invoke(Boolean.valueOf(r0));
        goto L4
    L8:
        r62.setChecked(r0);
        this.fragment.getListView$TMessagesProj().adapter.update(true);
        Function0<Unit> r5 = this.onChanged;
        if (r5 == null) goto L17;
        r5.invoke();
        return;
    }

    private final CharSequence title() {
        CharSequence r0 = this.title;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return LocaleController.getString(this.titleRes);
    }

    public final SwitchGroup add(Enum<?> r8, int r9, BooleanSupplier r10, Setter r11, CharSequence r12) {
        return add(new Child(r8.ordinal() + 1, r9, r12, null, new AnonymousClass5(r10), new AnonymousClass6(r11)));
    }

    public final SwitchGroup add(Enum<?> r8, int r9, KMutableProperty0<Boolean> r10, CharSequence r11) {
        return add(new Child(r8.ordinal() + 1, r9, r11, null, new SwitchGroup$$ExternalSyntheticLambda0(r10), new SwitchGroup$$ExternalSyntheticLambda1(r10)));
    }

    public final SwitchGroup addIf(BooleanSupplier r8, Enum<?> r9, int r10, BooleanSupplier r11, Setter r12, CharSequence r13) {
        return add(new Child(r9.ordinal() + 1, r10, r13, new AnonymousClass7(r8), new AnonymousClass8(r11), new AnonymousClass9(r12)));
    }

    public final SwitchGroup addIf(BooleanSupplier r8, Enum<?> r9, int r10, KMutableProperty0<Boolean> r11, CharSequence r12) {
        return add(new Child(r9.ordinal() + 1, r10, r12, new AnonymousClass4(r8), new SwitchGroup$$ExternalSyntheticLambda2(r11), new SwitchGroup$$ExternalSyntheticLambda3(r11)));
    }

    public final void fill(ArrayList<UItem> r8) {
        int r1 = count(true);
        boolean r2 = false;
        UItem r3 = UItem.asExteraExpandableSwitch(this.id, title(), String.format("%d/%d", Arrays.copyOf(new Object[]{Integer.valueOf(r1), Integer.valueOf(count(false))}, 2)), new SwitchGroup$$ExternalSyntheticLambda4(this));
        if (r1 <= 0) goto L5;
        r2 = true;
    L5:
        r3.setChecked(r2);
        r3.setCollapsed(true ^ this.expanded);
        if (this.searchable == false) goto L8;
        r3.setSearchable(this.fragment);
    L8:
        String r0 = this.linkAlias;
        if (r0 == null) goto L11;
        r3.setLinkAlias(r0, this.fragment);
    L11:
        r8.add(r3);
        if (this.expanded == false) goto L21;
        Iterator<Child> r7 = this.children.iterator();
    L16:
        if (r7.hasNext() == false) goto L27;
        Child r02 = r7.next();
        if (r02.isVisible() == false) goto L16;
        UItem r12 = UItem.asRoundCheckbox(r02.getId(), r02.text());
        r12.setChecked(r02.getGetter().invoke().booleanValue()).pad();
        r8.add(r12);
        goto L16
    L27:
        return;
    }

    public final SwitchGroup markNew(String r2) {
        ((Child) CollectionsKt.last(this.children)).setNewFeatureAlias(r2);
        return this;
    }

    public final SwitchGroup onChanged(Function0<Unit> r1) {
        this.onChanged = r1;
        return this;
    }

    public final void onClick(UItem r5) {
        if (r5.id != this.id) goto L6;
        boolean r0 = this.expanded;
        this.expanded = !r0;
        r5.setCollapsed(r0);
        this.fragment.getListView$TMessagesProj().adapter.update(true);
        return;
    L6:
        Iterator<T> r02 = this.children.iterator();
    L8:
        if (r02.hasNext() == false) goto L12;
        Object r1 = r02.next();
        if (((Child) r1).getId() != r5.id) goto L8;
    L13:
        Child r12 = (Child) r1;
        if (r12 == null) goto L24;
        this.fragment.toggleBooleanSettingAndRefresh$TMessagesProj(r5, new SwitchGroup$$ExternalSyntheticLambda5(r12));
        Function0<Unit> r4 = this.onChanged;
        if (r4 == null) goto L23;
        r4.invoke();
        return;
    L23:
        return;
    L24:
        return;
    L12:
        r1 = null;
        goto L13
    }
}
