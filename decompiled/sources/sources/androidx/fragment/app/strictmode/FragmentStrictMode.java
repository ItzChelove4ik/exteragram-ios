package androidx.fragment.app.strictmode;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001:\u000389:B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\f\u0010\rJ!\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0014\u0010\u0013J\u001f\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0017\u0010\u0018J'\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001bH\u0007¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001f\u0010\u0013J\u0017\u0010 \u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b \u0010\u0013J\u001f\u0010!\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b!\u0010\u0011J\u0017\u0010$\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\"H\u0002¢\u0006\u0004\b$\u0010%J7\u0010*\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u00062\u000e\u0010(\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040'2\u000e\u0010)\u001a\n\u0012\u0006\b\u0001\u0012\u00020\"0'H\u0002¢\u0006\u0004\b*\u0010+J\u001f\u0010,\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\"H\u0002¢\u0006\u0004\b,\u0010-J\u001f\u00100\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010/\u001a\u00020.H\u0002¢\u0006\u0004\b0\u00101R\"\u00102\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b2\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107¨\u0006;"}, d2 = {"Landroidx/fragment/app/strictmode/FragmentStrictMode;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/fragment/app/Fragment;", "fragment", "Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;", "getNearestPolicy", "(Landroidx/fragment/app/Fragment;)Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;", _UrlKt.FRAGMENT_ENCODE_SET, "previousFragmentId", _UrlKt.FRAGMENT_ENCODE_SET, "onFragmentReuse", "(Landroidx/fragment/app/Fragment;Ljava/lang/String;)V", "Landroid/view/ViewGroup;", "container", "onFragmentTagUsage", "(Landroidx/fragment/app/Fragment;Landroid/view/ViewGroup;)V", "onSetRetainInstanceUsage", "(Landroidx/fragment/app/Fragment;)V", "onGetRetainInstanceUsage", _UrlKt.FRAGMENT_ENCODE_SET, "isVisibleToUser", "onSetUserVisibleHint", "(Landroidx/fragment/app/Fragment;Z)V", "violatingFragment", "targetFragment", _UrlKt.FRAGMENT_ENCODE_SET, "requestCode", "onSetTargetFragmentUsage", "(Landroidx/fragment/app/Fragment;Landroidx/fragment/app/Fragment;I)V", "onGetTargetFragmentUsage", "onGetTargetFragmentRequestCodeUsage", "onWrongFragmentContainer", "Landroidx/fragment/app/strictmode/Violation;", "violation", "logIfDebuggingEnabled", "(Landroidx/fragment/app/strictmode/Violation;)V", "policy", "Ljava/lang/Class;", "fragmentClass", "violationClass", "shouldHandlePolicyViolation", "(Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;Ljava/lang/Class;Ljava/lang/Class;)Z", "handlePolicyViolation", "(Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;Landroidx/fragment/app/strictmode/Violation;)V", "Ljava/lang/Runnable;", "runnable", "runOnHostThread", "(Landroidx/fragment/app/Fragment;Ljava/lang/Runnable;)V", "defaultPolicy", "Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;", "getDefaultPolicy", "()Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;", "setDefaultPolicy", "(Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;)V", "Flag", "OnViolationListener", "Policy", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FragmentStrictMode {
    public static final FragmentStrictMode INSTANCE = null;
    private static Policy defaultPolicy;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Landroidx/fragment/app/strictmode/FragmentStrictMode$Flag;", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;I)V", "PENALTY_LOG", "PENALTY_DEATH", "DETECT_FRAGMENT_REUSE", "DETECT_FRAGMENT_TAG_USAGE", "DETECT_RETAIN_INSTANCE_USAGE", "DETECT_SET_USER_VISIBLE_HINT", "DETECT_TARGET_FRAGMENT_USAGE", "DETECT_WRONG_FRAGMENT_CONTAINER", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum Flag extends Enum<Flag> {
        private static final /* synthetic */ Flag[] $VALUES = null;
        public static final Flag DETECT_FRAGMENT_REUSE = null;
        public static final Flag DETECT_FRAGMENT_TAG_USAGE = null;
        public static final Flag DETECT_RETAIN_INSTANCE_USAGE = null;
        public static final Flag DETECT_SET_USER_VISIBLE_HINT = null;
        public static final Flag DETECT_TARGET_FRAGMENT_USAGE = null;
        public static final Flag DETECT_WRONG_FRAGMENT_CONTAINER = null;
        public static final Flag PENALTY_DEATH = null;
        public static final Flag PENALTY_LOG = null;

        private static final /* synthetic */ Flag[] $values() {
            return new Flag[]{PENALTY_LOG, PENALTY_DEATH, DETECT_FRAGMENT_REUSE, DETECT_FRAGMENT_TAG_USAGE, DETECT_RETAIN_INSTANCE_USAGE, DETECT_SET_USER_VISIBLE_HINT, DETECT_TARGET_FRAGMENT_USAGE, DETECT_WRONG_FRAGMENT_CONTAINER};
        }

        static {
            PENALTY_LOG = new Flag("PENALTY_LOG", 0);
            PENALTY_DEATH = new Flag("PENALTY_DEATH", 1);
            DETECT_FRAGMENT_REUSE = new Flag("DETECT_FRAGMENT_REUSE", 2);
            DETECT_FRAGMENT_TAG_USAGE = new Flag("DETECT_FRAGMENT_TAG_USAGE", 3);
            DETECT_RETAIN_INSTANCE_USAGE = new Flag("DETECT_RETAIN_INSTANCE_USAGE", 4);
            DETECT_SET_USER_VISIBLE_HINT = new Flag("DETECT_SET_USER_VISIBLE_HINT", 5);
            DETECT_TARGET_FRAGMENT_USAGE = new Flag("DETECT_TARGET_FRAGMENT_USAGE", 6);
            DETECT_WRONG_FRAGMENT_CONTAINER = new Flag("DETECT_WRONG_FRAGMENT_CONTAINER", 7);
            $VALUES = $values();
        }

        Flag(String r1, int r2) {
        }

        public static Flag valueOf(String r1) {
            return (Flag) Enum.valueOf(Flag.class, r1);
        }

        public static Flag[] values() {
            return (Flag[]) $VALUES.clone();
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Landroidx/fragment/app/strictmode/FragmentStrictMode$OnViolationListener;", _UrlKt.FRAGMENT_ENCODE_SET, "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnViolationListener {
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019BC\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012 \u0010\f\u001a\u001c\u0012\u0004\u0012\u00020\b\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n0\t0\u0007¢\u0006\u0004\b\r\u0010\u000eR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R4\u0010\u0012\u001a\u001c\u0012\u0004\u0012\u00020\b\u0012\u0012\u0012\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n0\u00020\u00078\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/fragment/app/strictmode/FragmentStrictMode$Flag;", "flags", "Landroidx/fragment/app/strictmode/FragmentStrictMode$OnViolationListener;", "listener", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/Class;", "Landroidx/fragment/app/strictmode/Violation;", "allowedViolations", "<init>", "(Ljava/util/Set;Landroidx/fragment/app/strictmode/FragmentStrictMode$OnViolationListener;Ljava/util/Map;)V", "Ljava/util/Set;", "getFlags$fragment_release", "()Ljava/util/Set;", "mAllowedViolations", "Ljava/util/Map;", "getMAllowedViolations$fragment_release", "()Ljava/util/Map;", "Landroidx/fragment/app/strictmode/FragmentStrictMode$OnViolationListener;", "getListener$fragment_release", "()Landroidx/fragment/app/strictmode/FragmentStrictMode$OnViolationListener;", "Companion", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Policy {
        public static final Companion Companion = null;

        @JvmField
        public static final Policy LAX = null;
        private final Set<Flag> flags;
        private final Map<String, Set<Class<? extends Violation>>> mAllowedViolations;

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "LAX", "Landroidx/fragment/app/strictmode/FragmentStrictMode$Policy;", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }
        }

        static {
            Companion = new Companion(null);
            LAX = new Policy(SetsKt.emptySet(), null, MapsKt.emptyMap());
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Policy(Set<? extends Flag> r2, OnViolationListener r3, Map<String, ? extends Set<Class<? extends Violation>>> r4) {
            this.flags = r2;
            LinkedHashMap r22 = new LinkedHashMap();
            Iterator<Map.Entry<String, ? extends Set<Class<? extends Violation>>>> r32 = r4.entrySet().iterator();
        L4:
            if (r32.hasNext() == false) goto L6;
            Map.Entry<String, ? extends Set<Class<? extends Violation>>> r42 = r32.next();
            r22.put(r42.getKey(), r42.getValue());
            goto L4
        L6:
            this.mAllowedViolations = r22;
        }

        public final Set<Flag> getFlags$fragment_release() {
            return this.flags;
        }

        public final OnViolationListener getListener$fragment_release() {
            return null;
        }

        public final Map<String, Set<Class<? extends Violation>>> getMAllowedViolations$fragment_release() {
            return this.mAllowedViolations;
        }
    }

    public static /* synthetic */ void $r8$lambda$G9JQrv8NJ8N34hODd8MWqMS1RXo(String r0, Violation r1) {
        m726handlePolicyViolation$lambda1(r0, r1);
    }

    static {
        INSTANCE = new FragmentStrictMode();
        defaultPolicy = Policy.LAX;
    }

    private FragmentStrictMode() {
    }

    private final Policy getNearestPolicy(Fragment r2) {
    L2:
        if (r2 == null) goto L11;
        if (r2.isAdded() == false) goto L9;
        FragmentManager r1 = r2.getParentFragmentManager();
        if (r1.getStrictModePolicy() == null) goto L9;
        return r1.getStrictModePolicy();
    L9:
        r2 = r2.getParentFragment();
        goto L2
    L11:
        return defaultPolicy;
    }

    private final void handlePolicyViolation(Policy r5, Violation r6) {
        Fragment r0 = r6.getFragment();
        String r1 = r0.getClass().getName();
        if (r5.getFlags$fragment_release().contains(Flag.PENALTY_LOG) == false) goto L5;
        Log.d("FragmentStrictMode", "Policy violation in ".concat(r1), r6);
    L5:
        r5.getListener$fragment_release();
        if (r5.getFlags$fragment_release().contains(Flag.PENALTY_DEATH) == false) goto L9;
        runOnHostThread(r0, new FragmentStrictMode$$ExternalSyntheticLambda0(r1, r6));
        return;
    }

    /* renamed from: handlePolicyViolation$lambda-1, reason: not valid java name */
    private static final void m726handlePolicyViolation$lambda1(String r2, Violation r3) {
        Log.e("FragmentStrictMode", "Policy violation with PENALTY_DEATH in " + r2, r3);
        throw r3;
    }

    private final void logIfDebuggingEnabled(Violation r2) {
        if (FragmentManager.isLoggingEnabled(3) == false) goto L6;
        Log.d("FragmentManager", "StrictMode violation in ".concat(r2.getFragment().getClass().getName()), r2);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final void onFragmentReuse(Fragment r4, String r5) {
        FragmentReuseViolation r0 = new FragmentReuseViolation(r4, r5);
        FragmentStrictMode r52 = INSTANCE;
        r52.logIfDebuggingEnabled(r0);
        Policy r1 = r52.getNearestPolicy(r4);
        if (r1.getFlags$fragment_release().contains(Flag.DETECT_FRAGMENT_REUSE) == true) goto L5;
        return;
    L5:
        if (r52.shouldHandlePolicyViolation(r1, r4.getClass(), r0.getClass()) == false) goto L9;
        r52.handlePolicyViolation(r1, r0);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final void onFragmentTagUsage(Fragment r4, ViewGroup r5) {
        FragmentTagUsageViolation r0 = new FragmentTagUsageViolation(r4, r5);
        FragmentStrictMode r52 = INSTANCE;
        r52.logIfDebuggingEnabled(r0);
        Policy r1 = r52.getNearestPolicy(r4);
        if (r1.getFlags$fragment_release().contains(Flag.DETECT_FRAGMENT_TAG_USAGE) == true) goto L5;
        return;
    L5:
        if (r52.shouldHandlePolicyViolation(r1, r4.getClass(), r0.getClass()) == false) goto L9;
        r52.handlePolicyViolation(r1, r0);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final void onGetRetainInstanceUsage(Fragment r5) {
        GetRetainInstanceUsageViolation r0 = new GetRetainInstanceUsageViolation(r5);
        FragmentStrictMode r1 = INSTANCE;
        r1.logIfDebuggingEnabled(r0);
        Policy r2 = r1.getNearestPolicy(r5);
        if (r2.getFlags$fragment_release().contains(Flag.DETECT_RETAIN_INSTANCE_USAGE) == true) goto L5;
        return;
    L5:
        if (r1.shouldHandlePolicyViolation(r2, r5.getClass(), r0.getClass()) == false) goto L9;
        r1.handlePolicyViolation(r2, r0);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final void onGetTargetFragmentRequestCodeUsage(Fragment r5) {
        GetTargetFragmentRequestCodeUsageViolation r0 = new GetTargetFragmentRequestCodeUsageViolation(r5);
        FragmentStrictMode r1 = INSTANCE;
        r1.logIfDebuggingEnabled(r0);
        Policy r2 = r1.getNearestPolicy(r5);
        if (r2.getFlags$fragment_release().contains(Flag.DETECT_TARGET_FRAGMENT_USAGE) == true) goto L5;
        return;
    L5:
        if (r1.shouldHandlePolicyViolation(r2, r5.getClass(), r0.getClass()) == false) goto L9;
        r1.handlePolicyViolation(r2, r0);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final void onGetTargetFragmentUsage(Fragment r5) {
        GetTargetFragmentUsageViolation r0 = new GetTargetFragmentUsageViolation(r5);
        FragmentStrictMode r1 = INSTANCE;
        r1.logIfDebuggingEnabled(r0);
        Policy r2 = r1.getNearestPolicy(r5);
        if (r2.getFlags$fragment_release().contains(Flag.DETECT_TARGET_FRAGMENT_USAGE) == true) goto L5;
        return;
    L5:
        if (r1.shouldHandlePolicyViolation(r2, r5.getClass(), r0.getClass()) == false) goto L9;
        r1.handlePolicyViolation(r2, r0);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final void onSetRetainInstanceUsage(Fragment r5) {
        SetRetainInstanceUsageViolation r0 = new SetRetainInstanceUsageViolation(r5);
        FragmentStrictMode r1 = INSTANCE;
        r1.logIfDebuggingEnabled(r0);
        Policy r2 = r1.getNearestPolicy(r5);
        if (r2.getFlags$fragment_release().contains(Flag.DETECT_RETAIN_INSTANCE_USAGE) == true) goto L5;
        return;
    L5:
        if (r1.shouldHandlePolicyViolation(r2, r5.getClass(), r0.getClass()) == false) goto L9;
        r1.handlePolicyViolation(r2, r0);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final void onSetTargetFragmentUsage(Fragment r3, Fragment r4, int r5) {
        SetTargetFragmentUsageViolation r0 = new SetTargetFragmentUsageViolation(r3, r4, r5);
        FragmentStrictMode r42 = INSTANCE;
        r42.logIfDebuggingEnabled(r0);
        Policy r52 = r42.getNearestPolicy(r3);
        if (r52.getFlags$fragment_release().contains(Flag.DETECT_TARGET_FRAGMENT_USAGE) == true) goto L5;
        return;
    L5:
        if (r42.shouldHandlePolicyViolation(r52, r3.getClass(), r0.getClass()) == false) goto L9;
        r42.handlePolicyViolation(r52, r0);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final void onSetUserVisibleHint(Fragment r4, boolean r5) {
        SetUserVisibleHintViolation r0 = new SetUserVisibleHintViolation(r4, r5);
        FragmentStrictMode r52 = INSTANCE;
        r52.logIfDebuggingEnabled(r0);
        Policy r1 = r52.getNearestPolicy(r4);
        if (r1.getFlags$fragment_release().contains(Flag.DETECT_SET_USER_VISIBLE_HINT) == true) goto L5;
        return;
    L5:
        if (r52.shouldHandlePolicyViolation(r1, r4.getClass(), r0.getClass()) == false) goto L9;
        r52.handlePolicyViolation(r1, r0);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmStatic
    public static final void onWrongFragmentContainer(Fragment r4, ViewGroup r5) {
        WrongFragmentContainerViolation r0 = new WrongFragmentContainerViolation(r4, r5);
        FragmentStrictMode r52 = INSTANCE;
        r52.logIfDebuggingEnabled(r0);
        Policy r1 = r52.getNearestPolicy(r4);
        if (r1.getFlags$fragment_release().contains(Flag.DETECT_WRONG_FRAGMENT_CONTAINER) == true) goto L5;
        return;
    L5:
        if (r52.shouldHandlePolicyViolation(r1, r4.getClass(), r0.getClass()) == false) goto L9;
        r52.handlePolicyViolation(r1, r0);
        return;
    }

    private final void runOnHostThread(Fragment r2, Runnable r3) {
        if (r2.isAdded() == false) goto L10;
        Handler r1 = r2.getParentFragmentManager().getHost().getHandler();
        if (Intrinsics.areEqual(r1.getLooper(), Looper.myLooper()) == false) goto L8;
        r3.run();
        return;
    L8:
        r1.post(r3);
        return;
    L10:
        r3.run();
    }

    private final boolean shouldHandlePolicyViolation(Policy r2, Class<? extends Fragment> r3, Class<? extends Violation> r4) {
        Set<Class<? extends Violation>> r1 = r2.getMAllowedViolations$fragment_release().get(r3.getName());
        if (r1 != null) goto L6;
        return true;
    L6:
        if (Intrinsics.areEqual(r4.getSuperclass(), Violation.class) == true) goto L12;
        if (CollectionsKt.contains(r1, r4.getSuperclass()) == false) goto L12;
        return false;
    L12:
        return !r1.contains(r4);
    }
}
