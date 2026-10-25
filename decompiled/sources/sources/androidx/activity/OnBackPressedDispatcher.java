package androidx.activity;

import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.core.util.Consumer;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.sun.jna.Callback;
import java.util.Iterator;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u00044567B!\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bB\u0015\b\u0017\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\tJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0005H\u0003¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0010H\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0010H\u0003¢\u0006\u0004\b\u0014\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u000bH\u0003¢\u0006\u0004\b\u0015\u0010\u000fJ\u0017\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0016H\u0007¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u001f\u0010\"\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020 2\u0006\u0010\u001b\u001a\u00020\u001aH\u0007¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\u000bH\u0007¢\u0006\u0004\b$\u0010\u000fR\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010%R\u001c\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010&R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001a0'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010*\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u0018\u0010-\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0018\u0010/\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u0016\u00101\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u0016\u00103\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00102¨\u00068"}, d2 = {"Landroidx/activity/OnBackPressedDispatcher;", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/Runnable;", "fallbackOnBackPressed", "Landroidx/core/util/Consumer;", _UrlKt.FRAGMENT_ENCODE_SET, "onHasEnabledCallbacksChanged", "<init>", "(Ljava/lang/Runnable;Landroidx/core/util/Consumer;)V", "(Ljava/lang/Runnable;)V", "shouldBeRegistered", _UrlKt.FRAGMENT_ENCODE_SET, "updateBackInvokedCallbackState", "(Z)V", "updateEnabledCallbacks", "()V", "Landroidx/activity/BackEventCompat;", "backEvent", "onBackStarted", "(Landroidx/activity/BackEventCompat;)V", "onBackProgressed", "onBackCancelled", "Landroid/window/OnBackInvokedDispatcher;", "invoker", "setOnBackInvokedDispatcher", "(Landroid/window/OnBackInvokedDispatcher;)V", "Landroidx/activity/OnBackPressedCallback;", "onBackPressedCallback", "Landroidx/activity/Cancellable;", "addCancellableCallback$activity_release", "(Landroidx/activity/OnBackPressedCallback;)Landroidx/activity/Cancellable;", "addCancellableCallback", "Landroidx/lifecycle/LifecycleOwner;", "owner", "addCallback", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/activity/OnBackPressedCallback;)V", "onBackPressed", "Ljava/lang/Runnable;", "Landroidx/core/util/Consumer;", "Lkotlin/collections/ArrayDeque;", "onBackPressedCallbacks", "Lkotlin/collections/ArrayDeque;", "inProgressCallback", "Landroidx/activity/OnBackPressedCallback;", "Landroid/window/OnBackInvokedCallback;", "onBackInvokedCallback", "Landroid/window/OnBackInvokedCallback;", "invokedDispatcher", "Landroid/window/OnBackInvokedDispatcher;", "backInvokedCallbackRegistered", "Z", "hasEnabledCallbacks", "Api33Impl", "Api34Impl", "LifecycleOnBackPressedCancellable", "OnBackPressedCancellable", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOnBackPressedDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnBackPressedDispatcher.kt\nandroidx/activity/OnBackPressedDispatcher\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,430:1\n1747#2,3:431\n533#2,6:434\n533#2,6:440\n533#2,6:446\n533#2,6:452\n*S KotlinDebug\n*F\n+ 1 OnBackPressedDispatcher.kt\nandroidx/activity/OnBackPressedDispatcher\n*L\n114#1:431,3\n233#1:434,6\n251#1:440,6\n271#1:446,6\n290#1:452,6\n*E\n"})
/* loaded from: classes.dex */
public final class OnBackPressedDispatcher {
    private boolean backInvokedCallbackRegistered;
    private final Runnable fallbackOnBackPressed;
    private boolean hasEnabledCallbacks;
    private OnBackPressedCallback inProgressCallback;
    private OnBackInvokedDispatcher invokedDispatcher;
    private OnBackInvokedCallback onBackInvokedCallback;
    private final ArrayDeque<OnBackPressedCallback> onBackPressedCallbacks;
    private final Consumer<Boolean> onHasEnabledCallbacksChanged;

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "backEvent", "Landroidx/activity/BackEventCompat;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.activity.OnBackPressedDispatcher$1, reason: invalid class name */
    public static final class AnonymousClass1 extends Lambda implements Function1<BackEventCompat, Unit> {
        final /* synthetic */ OnBackPressedDispatcher this$0;

        public AnonymousClass1(OnBackPressedDispatcher r1) {
            this.this$0 = r1;
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(BackEventCompat r1) {
            invoke2(r1);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(BackEventCompat r1) {
            OnBackPressedDispatcher.access$onBackStarted(this.this$0, r1);
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "backEvent", "Landroidx/activity/BackEventCompat;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.activity.OnBackPressedDispatcher$2, reason: invalid class name */
    public static final class AnonymousClass2 extends Lambda implements Function1<BackEventCompat, Unit> {
        final /* synthetic */ OnBackPressedDispatcher this$0;

        public AnonymousClass2(OnBackPressedDispatcher r1) {
            this.this$0 = r1;
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(BackEventCompat r1) {
            invoke2(r1);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(BackEventCompat r1) {
            OnBackPressedDispatcher.access$onBackProgressed(this.this$0, r1);
        }
    }

    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.activity.OnBackPressedDispatcher$3, reason: invalid class name */
    public static final class AnonymousClass3 extends Lambda implements Function0<Unit> {
        final /* synthetic */ OnBackPressedDispatcher this$0;

        public AnonymousClass3(OnBackPressedDispatcher r1) {
            this.this$0 = r1;
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.this$0.onBackPressed();
        }
    }

    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.activity.OnBackPressedDispatcher$4, reason: invalid class name */
    public static final class AnonymousClass4 extends Lambda implements Function0<Unit> {
        final /* synthetic */ OnBackPressedDispatcher this$0;

        public AnonymousClass4(OnBackPressedDispatcher r1) {
            this.this$0 = r1;
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            OnBackPressedDispatcher.access$onBackCancelled(this.this$0);
        }
    }

    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.activity.OnBackPressedDispatcher$5, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass5 extends Lambda implements Function0<Unit> {
        final /* synthetic */ OnBackPressedDispatcher this$0;

        public AnonymousClass5(OnBackPressedDispatcher r1) {
            this.this$0 = r1;
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.this$0.onBackPressed();
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007J \u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0001H\u0007J\u0018\u0010\r\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u0001H\u0007¨\u0006\u000e"}, d2 = {"Landroidx/activity/OnBackPressedDispatcher$Api33Impl;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "createOnBackInvokedCallback", "Landroid/window/OnBackInvokedCallback;", "onBackInvoked", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "registerOnBackInvokedCallback", "dispatcher", PluginsConstants.MenuItemProperties.PRIORITY, _UrlKt.FRAGMENT_ENCODE_SET, Callback.METHOD_NAME, "unregisterOnBackInvokedCallback", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Api33Impl {
        public static final Api33Impl INSTANCE = null;

        public static void $r8$lambda$uALQStkfKNo5swuEpG7lymZRBFs(Function0 r0) {
            r0.invoke();
        }

        static {
            INSTANCE = new Api33Impl();
        }

        private Api33Impl() {
        }

        public final OnBackInvokedCallback createOnBackInvokedCallback(Function0<Unit> r1) {
            return new OnBackPressedDispatcher$Api33Impl$$ExternalSyntheticLambda0(r1);
        }

        public final void registerOnBackInvokedCallback(Object r1, int r2, Object r3) {
            ((OnBackInvokedDispatcher) r1).registerOnBackInvokedCallback(r2, (OnBackInvokedCallback) r3);
        }

        public final void unregisterOnBackInvokedCallback(Object r1, Object r2) {
            ((OnBackInvokedDispatcher) r1).unregisterOnBackInvokedCallback((OnBackInvokedCallback) r2);
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jj\u0010\u0003\u001a\u00020\u00042!\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u00062!\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u0007¨\u0006\u0010"}, d2 = {"Landroidx/activity/OnBackPressedDispatcher$Api34Impl;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "createOnBackAnimationCallback", "Landroid/window/OnBackInvokedCallback;", "onBackStarted", "Lkotlin/Function1;", "Landroidx/activity/BackEventCompat;", "Lkotlin/ParameterName;", "name", "backEvent", _UrlKt.FRAGMENT_ENCODE_SET, "onBackProgressed", "onBackInvoked", "Lkotlin/Function0;", "onBackCancelled", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE = null;

        static {
            INSTANCE = new Api34Impl();
        }

        private Api34Impl() {
        }

        public final OnBackInvokedCallback createOnBackAnimationCallback(Function1<? super BackEventCompat, Unit> r1, Function1<? super BackEventCompat, Unit> r2, Function0<Unit> r3, Function0<Unit> r4) {
            return new OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1(r1, r2, r3, r4);
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/activity/OnBackPressedDispatcher$LifecycleOnBackPressedCancellable;", "Landroidx/lifecycle/LifecycleEventObserver;", "Landroidx/activity/Cancellable;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "onBackPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "(Landroidx/activity/OnBackPressedDispatcher;Landroidx/lifecycle/Lifecycle;Landroidx/activity/OnBackPressedCallback;)V", "currentCancellable", "cancel", _UrlKt.FRAGMENT_ENCODE_SET, "onStateChanged", "source", "Landroidx/lifecycle/LifecycleOwner;", "event", "Landroidx/lifecycle/Lifecycle$Event;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class LifecycleOnBackPressedCancellable implements LifecycleEventObserver, Cancellable {
        private Cancellable currentCancellable;
        private final Lifecycle lifecycle;
        private final OnBackPressedCallback onBackPressedCallback;
        final /* synthetic */ OnBackPressedDispatcher this$0;

        public LifecycleOnBackPressedCancellable(OnBackPressedDispatcher r1, Lifecycle r2, OnBackPressedCallback r3) {
            this.this$0 = r1;
            this.lifecycle = r2;
            this.onBackPressedCallback = r3;
            r2.addObserver(this);
        }

        @Override // androidx.activity.Cancellable
        public void cancel() {
            this.lifecycle.removeObserver(this);
            this.onBackPressedCallback.removeCancellable(this);
            Cancellable r0 = this.currentCancellable;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            this.currentCancellable = null;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner r1, Lifecycle.Event r2) {
            if (r2 != Lifecycle.Event.ON_START) goto L7;
            this.currentCancellable = this.this$0.addCancellableCallback$activity_release(this.onBackPressedCallback);
            return;
        L7:
            if (r2 != Lifecycle.Event.ON_STOP) goto L13;
            Cancellable r0 = this.currentCancellable;
            if (r0 == null) goto L16;
            r0.cancel();
            return;
        L16:
            return;
        L13:
            if (r2 != Lifecycle.Event.ON_DESTROY) goto L17;
            cancel();
            return;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/activity/OnBackPressedDispatcher$OnBackPressedCancellable;", "Landroidx/activity/Cancellable;", "onBackPressedCallback", "Landroidx/activity/OnBackPressedCallback;", "(Landroidx/activity/OnBackPressedDispatcher;Landroidx/activity/OnBackPressedCallback;)V", "cancel", _UrlKt.FRAGMENT_ENCODE_SET, "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class OnBackPressedCancellable implements Cancellable {
        private final OnBackPressedCallback onBackPressedCallback;
        final /* synthetic */ OnBackPressedDispatcher this$0;

        public OnBackPressedCancellable(OnBackPressedDispatcher r1, OnBackPressedCallback r2) {
            this.this$0 = r1;
            this.onBackPressedCallback = r2;
        }

        @Override // androidx.activity.Cancellable
        public void cancel() {
            OnBackPressedDispatcher.access$getOnBackPressedCallbacks$p(this.this$0).remove(this.onBackPressedCallback);
            if (Intrinsics.areEqual(OnBackPressedDispatcher.access$getInProgressCallback$p(this.this$0), this.onBackPressedCallback) == false) goto L5;
            this.onBackPressedCallback.handleOnBackCancelled();
            OnBackPressedDispatcher.access$setInProgressCallback$p(this.this$0, null);
        L5:
            this.onBackPressedCallback.removeCancellable(this);
            Function0<Unit> r0 = this.onBackPressedCallback.getEnabledChangedCallback$activity_release();
            if (r0 == null) goto L8;
            r0.invoke();
        L8:
            this.onBackPressedCallback.setEnabledChangedCallback$activity_release(null);
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.activity.OnBackPressedDispatcher$addCallback$1, reason: invalid class name and case insensitive filesystem */
    public /* synthetic */ class C00701 extends FunctionReferenceImpl implements Function0<Unit> {
        public C00701(Object r8) {
            super(0, r8, OnBackPressedDispatcher.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            OnBackPressedDispatcher.access$updateEnabledCallbacks((OnBackPressedDispatcher) this.receiver);
        }
    }

    @JvmOverloads
    public OnBackPressedDispatcher(Runnable r2) {
        this(r2, null);
    }

    public OnBackPressedDispatcher(Runnable r4, Consumer<Boolean> r5) {
        this.fallbackOnBackPressed = r4;
        this.onHasEnabledCallbacksChanged = r5;
        this.onBackPressedCallbacks = new ArrayDeque();
        int r42 = Build.VERSION.SDK_INT;
        if (r42 >= 33) goto L5;
        return;
    L5:
        if (r42 < 34) goto L7;
        OnBackInvokedCallback r43 = Api34Impl.INSTANCE.createOnBackAnimationCallback(new AnonymousClass1(this), new AnonymousClass2(this), new AnonymousClass3(this), new AnonymousClass4(this));
    L8:
        this.onBackInvokedCallback = r43;
        return;
    L7:
        r43 = Api33Impl.INSTANCE.createOnBackInvokedCallback(new AnonymousClass5(this));
        goto L8
    }

    public static final /* synthetic */ OnBackPressedCallback access$getInProgressCallback$p(OnBackPressedDispatcher r0) {
        return r0.inProgressCallback;
    }

    public static final /* synthetic */ ArrayDeque access$getOnBackPressedCallbacks$p(OnBackPressedDispatcher r0) {
        return r0.onBackPressedCallbacks;
    }

    public static final /* synthetic */ void access$onBackCancelled(OnBackPressedDispatcher r0) {
        r0.onBackCancelled();
    }

    public static final /* synthetic */ void access$onBackProgressed(OnBackPressedDispatcher r0, BackEventCompat r1) {
        r0.onBackProgressed(r1);
    }

    public static final /* synthetic */ void access$onBackStarted(OnBackPressedDispatcher r0, BackEventCompat r1) {
        r0.onBackStarted(r1);
    }

    public static final /* synthetic */ void access$setInProgressCallback$p(OnBackPressedDispatcher r0, OnBackPressedCallback r1) {
        r0.inProgressCallback = r1;
    }

    public static final /* synthetic */ void access$updateEnabledCallbacks(OnBackPressedDispatcher r0) {
        r0.updateEnabledCallbacks();
    }

    private final void onBackCancelled() {
        ArrayDeque<OnBackPressedCallback> r0 = this.onBackPressedCallbacks;
        ListIterator<OnBackPressedCallback> r02 = r0.listIterator(r0.size());
    L4:
        if (r02.hasPrevious() == false) goto L8;
        OnBackPressedCallback r1 = r02.previous();
        if (r1.isEnabled() == false) goto L4;
    L9:
        OnBackPressedCallback r12 = r1;
        this.inProgressCallback = null;
        if (r12 == null) goto L16;
        r12.handleOnBackCancelled();
        return;
    L16:
        return;
    L8:
        r1 = null;
        goto L9
    }

    private final void onBackProgressed(BackEventCompat r3) {
        ArrayDeque<OnBackPressedCallback> r2 = this.onBackPressedCallbacks;
        ListIterator<OnBackPressedCallback> r22 = r2.listIterator(r2.size());
    L4:
        if (r22.hasPrevious() == false) goto L8;
        OnBackPressedCallback r0 = r22.previous();
        if (r0.isEnabled() == false) goto L4;
    L9:
        OnBackPressedCallback r02 = r0;
        if (r02 == null) goto L16;
        r02.handleOnBackProgressed(r3);
        return;
    L16:
        return;
    L8:
        r0 = null;
        goto L9
    }

    private final void onBackStarted(BackEventCompat r4) {
        ArrayDeque<OnBackPressedCallback> r0 = this.onBackPressedCallbacks;
        ListIterator<OnBackPressedCallback> r02 = r0.listIterator(r0.size());
    L4:
        if (r02.hasPrevious() == false) goto L8;
        OnBackPressedCallback r1 = r02.previous();
        if (r1.isEnabled() == false) goto L4;
    L9:
        OnBackPressedCallback r12 = r1;
        this.inProgressCallback = r12;
        if (r12 == null) goto L16;
        r12.handleOnBackStarted(r4);
        return;
    L16:
        return;
    L8:
        r1 = null;
        goto L9
    }

    private final void updateBackInvokedCallbackState(boolean r5) {
        OnBackInvokedDispatcher r0 = this.invokedDispatcher;
        OnBackInvokedCallback r1 = this.onBackInvokedCallback;
        if (r0 == null) goto L16;
        if (r1 != null) goto L6;
        return;
    L6:
        if (r5 == true) goto L8;
    L11:
        if (r5 == false) goto L13;
        return;
    L13:
        if (this.backInvokedCallbackRegistered == false) goto L19;
        Api33Impl.INSTANCE.unregisterOnBackInvokedCallback(r0, r1);
        this.backInvokedCallbackRegistered = false;
        return;
    L19:
        return;
    L8:
        if (this.backInvokedCallbackRegistered == true) goto L11;
        Api33Impl.INSTANCE.registerOnBackInvokedCallback(r0, 0, r1);
        this.backInvokedCallbackRegistered = true;
        return;
    }

    private final void updateEnabledCallbacks() {
        boolean r0 = this.hasEnabledCallbacks;
        ArrayDeque<OnBackPressedCallback> r1 = this.onBackPressedCallbacks;
        boolean r2 = false;
        if (r1 != null) goto L5;
    L7:
        Iterator<OnBackPressedCallback> r12 = r1.iterator();
    L9:
        if (r12.hasNext() == false) goto L13;
        if (r12.next().isEnabled() == false) goto L9;
        r2 = true;
    L13:
        this.hasEnabledCallbacks = r2;
        if (r2 == r0) goto L25;
        Consumer<Boolean> r02 = this.onHasEnabledCallbacksChanged;
        if (r02 == null) goto L19;
        r02.accept(Boolean.valueOf(r2));
    L19:
        if (Build.VERSION.SDK_INT < 33) goto L26;
        updateBackInvokedCallbackState(r2);
        return;
    L26:
        return;
    L25:
        return;
    L5:
        if (r1.isEmpty() == false) goto L7;
        goto L7
    }

    public final void addCallback(LifecycleOwner r3, OnBackPressedCallback r4) {
        Lifecycle r32 = r3.getLifecycle();
        if (r32.getCurrentState() != Lifecycle.State.DESTROYED) goto L5;
        return;
    L5:
        r4.addCancellable(new LifecycleOnBackPressedCancellable(this, r32, r4));
        updateEnabledCallbacks();
        r4.setEnabledChangedCallback$activity_release(new C00701(this));
    }

    public final Cancellable addCancellableCallback$activity_release(OnBackPressedCallback r3) {
        this.onBackPressedCallbacks.add(r3);
        OnBackPressedCancellable r0 = new OnBackPressedCancellable(this, r3);
        r3.addCancellable(r0);
        updateEnabledCallbacks();
        r3.setEnabledChangedCallback$activity_release(new OnBackPressedDispatcher$addCancellableCallback$1(this));
        return r0;
    }

    public final void onBackPressed() {
        ArrayDeque<OnBackPressedCallback> r0 = this.onBackPressedCallbacks;
        ListIterator<OnBackPressedCallback> r02 = r0.listIterator(r0.size());
    L4:
        if (r02.hasPrevious() == false) goto L8;
        OnBackPressedCallback r1 = r02.previous();
        if (r1.isEnabled() == false) goto L4;
    L9:
        OnBackPressedCallback r12 = r1;
        this.inProgressCallback = null;
        if (r12 == null) goto L13;
        r12.handleOnBackPressed();
        return;
    L13:
        Runnable r4 = this.fallbackOnBackPressed;
        if (r4 == null) goto L20;
        r4.run();
        return;
    L20:
        return;
    L8:
        r1 = null;
        goto L9
    }

    public final void setOnBackInvokedDispatcher(OnBackInvokedDispatcher r1) {
        this.invokedDispatcher = r1;
        updateBackInvokedCallbackState(this.hasEnabledCallbacks);
    }
}
