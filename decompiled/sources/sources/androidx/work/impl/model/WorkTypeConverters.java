package androidx.work.impl.model;

import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Build;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import androidx.work.impl.utils.NetworkRequest28;
import androidx.work.impl.utils.NetworkRequestCompat;
import androidx.work.impl.utils.NetworkRequestCompatKt;
import com.sun.jna.Native$$ExternalSyntheticBUOutline0;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.CharsKt__CharKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0018H\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00182\u0006\u0010\t\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010\"\u001a\u00020!2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0007¢\u0006\u0004\b\"\u0010#J\u001d\u0010%\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010$\u001a\u00020!H\u0007¢\u0006\u0004\b%\u0010&J\u0017\u0010*\u001a\u00020'2\u0006\u0010$\u001a\u00020!H\u0001¢\u0006\u0004\b(\u0010)J\u0017\u0010.\u001a\u00020!2\u0006\u0010+\u001a\u00020'H\u0001¢\u0006\u0004\b,\u0010-¨\u0006/"}, d2 = {"Landroidx/work/impl/model/WorkTypeConverters;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/work/WorkInfo$State;", "state", _UrlKt.FRAGMENT_ENCODE_SET, "stateToInt", "(Landroidx/work/WorkInfo$State;)I", "value", "intToState", "(I)Landroidx/work/WorkInfo$State;", "Landroidx/work/BackoffPolicy;", "backoffPolicy", "backoffPolicyToInt", "(Landroidx/work/BackoffPolicy;)I", "intToBackoffPolicy", "(I)Landroidx/work/BackoffPolicy;", "Landroidx/work/NetworkType;", "networkType", "networkTypeToInt", "(Landroidx/work/NetworkType;)I", "intToNetworkType", "(I)Landroidx/work/NetworkType;", "Landroidx/work/OutOfQuotaPolicy;", "policy", "outOfQuotaPolicyToInt", "(Landroidx/work/OutOfQuotaPolicy;)I", "intToOutOfQuotaPolicy", "(I)Landroidx/work/OutOfQuotaPolicy;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/Constraints$ContentUriTrigger;", "triggers", _UrlKt.FRAGMENT_ENCODE_SET, "setOfTriggersToByteArray", "(Ljava/util/Set;)[B", "bytes", "byteArrayToSetOfTriggers", "([B)Ljava/util/Set;", "Landroidx/work/impl/utils/NetworkRequestCompat;", "toNetworkRequest$work_runtime_release", "([B)Landroidx/work/impl/utils/NetworkRequestCompat;", "toNetworkRequest", "requestCompat", "fromNetworkRequest$work_runtime_release", "(Landroidx/work/impl/utils/NetworkRequestCompat;)[B", "fromNetworkRequest", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWorkTypeConverters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkTypeConverters.kt\nandroidx/work/impl/model/WorkTypeConverters\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,315:1\n1#2:316\n13493#3,2:317\n13493#3,2:319\n*S KotlinDebug\n*F\n+ 1 WorkTypeConverters.kt\nandroidx/work/impl/model/WorkTypeConverters\n*L\n307#1:317,2\n309#1:319,2\n*E\n"})
/* loaded from: classes.dex */
public final class WorkTypeConverters {
    public static final WorkTypeConverters INSTANCE = null;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = null;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2 = null;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3 = null;

        static {
            int[] r0 = new int[WorkInfo$State.values().length];
            r0[WorkInfo$State.ENQUEUED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L27
        L62:
            r0[WorkInfo$State.RUNNING.ordinal()] = 2;     // Catch: NoSuchFieldError -> L28
        L70:
            r0[WorkInfo$State.SUCCEEDED.ordinal()] = 3;     // Catch: NoSuchFieldError -> L29
        L46:
            r0[WorkInfo$State.FAILED.ordinal()] = 4;     // Catch: NoSuchFieldError -> L30
        L48:
            r0[WorkInfo$State.BLOCKED.ordinal()] = 5;     // Catch: NoSuchFieldError -> L31
        L56:
            r0[WorkInfo$State.CANCELLED.ordinal()] = 6;     // Catch: NoSuchFieldError -> L32
        L13:
            $EnumSwitchMapping$0 = r0;
            int[] r02 = new int[BackoffPolicy.values().length];
            r02[BackoffPolicy.EXPONENTIAL.ordinal()] = 1;     // Catch: NoSuchFieldError -> L33
        L50:
            r02[BackoffPolicy.LINEAR.ordinal()] = 2;     // Catch: NoSuchFieldError -> L34
        L16:
            $EnumSwitchMapping$1 = r02;
            int[] r03 = new int[NetworkType.values().length];
            r03[NetworkType.NOT_REQUIRED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L35
        L44:
            r03[NetworkType.CONNECTED.ordinal()] = 2;     // Catch: NoSuchFieldError -> L36
        L52:
            r03[NetworkType.UNMETERED.ordinal()] = 3;     // Catch: NoSuchFieldError -> L37
        L58:
            r03[NetworkType.NOT_ROAMING.ordinal()] = 4;     // Catch: NoSuchFieldError -> L38
        L64:
            r03[NetworkType.METERED.ordinal()] = 5;     // Catch: NoSuchFieldError -> L39
        L22:
            $EnumSwitchMapping$2 = r03;
            int[] r04 = new int[OutOfQuotaPolicy.values().length];
            r04[OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST.ordinal()] = 1;     // Catch: NoSuchFieldError -> L40
        L68:
            r04[OutOfQuotaPolicy.DROP_WORK_REQUEST.ordinal()] = 2;     // Catch: NoSuchFieldError -> L41
        L25:
            $EnumSwitchMapping$3 = r04;
        }
    }

    static {
        INSTANCE = new WorkTypeConverters();
    }

    private WorkTypeConverters() {
    }

    @JvmStatic
    public static final int backoffPolicyToInt(BackoffPolicy r2) {
        int r22 = WhenMappings.$EnumSwitchMapping$1[r2.ordinal()];
        if (r22 != 1) goto L5;
        return 0;
    L5:
        if (r22 != 2) goto L7;
        return 1;
    L7:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    @JvmStatic
    public static final Set<Constraints.ContentUriTrigger> byteArrayToSetOfTriggers(byte[] r8) {
        LinkedHashSet r0 = new LinkedHashSet();
        if (r8.length != 0) goto L5;
        return r0;
    L5:
        ByteArrayInputStream r1 = new ByteArrayInputStream(r8);
        ObjectInputStream r2 = new ObjectInputStream(r1);     // Catch: Throwable -> L15 IOException -> L17
        int r3 = r2.readInt();     // Catch: Throwable -> L10
        int r4 = 0;
    L8:
        if (r4 >= r3) goto L12;
        r0.add(new Constraints.ContentUriTrigger(Uri.parse(r2.readUTF()), r2.readBoolean()));     // Catch: Throwable -> L10
        r4 = r4 + 1;     // Catch: Throwable -> L10
        goto L8
    L12:
        Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L10
        CloseableKt.closeFinally(r2, null);     // Catch: Throwable -> L15 IOException -> L17
    L24:
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L15
        CloseableKt.closeFinally(r1, null);
        return r0;
    L10:
        th = move-exception;
        throw th;     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        CloseableKt.closeFinally(r2, th);     // Catch: Throwable -> L15 IOException -> L17
        throw th;     // Catch: Throwable -> L15 IOException -> L17
    L15:
        th = move-exception;
        throw th;     // Catch: Throwable -> L28
    L28:
        th = move-exception;
        CloseableKt.closeFinally(r1, th);
        throw th;
    L17:
        e = move-exception;
        e.printStackTrace();     // Catch: Throwable -> L15
        goto L24
    }

    @JvmStatic
    public static final byte[] fromNetworkRequest$work_runtime_release(NetworkRequestCompat r7) {
        int r2 = 0;
        if (Build.VERSION.SDK_INT < 28) goto L5;
        NetworkRequest r72 = r7.getNetworkRequest();
        if (r72 == null) goto L9;
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        ObjectOutputStream r1 = new ObjectOutputStream(r0);     // Catch: Throwable -> L25
        int[] r3 = NetworkRequestCompatKt.getTransportTypesCompat(r72);     // Catch: Throwable -> L15
        int[] r73 = NetworkRequestCompatKt.getCapabilitiesCompat(r72);     // Catch: Throwable -> L15
        r1.writeInt(r3.length);     // Catch: Throwable -> L15
        int r4 = r3.length;     // Catch: Throwable -> L15
        int r5 = 0;
    L13:
        if (r5 >= r4) goto L17;
        r1.writeInt(r3[r5]);     // Catch: Throwable -> L15
        r5 = r5 + 1;     // Catch: Throwable -> L15
        goto L13
    L17:
        r1.writeInt(r73.length);     // Catch: Throwable -> L15
        int r32 = r73.length;     // Catch: Throwable -> L15
    L18:
        if (r2 >= r32) goto L20;
        r1.writeInt(r73[r2]);     // Catch: Throwable -> L15
        r2 = r2 + 1;     // Catch: Throwable -> L15
        goto L18
    L20:
        Unit r74 = Unit.INSTANCE;     // Catch: Throwable -> L15
    L22:
        CloseableKt.closeFinally(r1, null);     // Catch: Throwable -> L25
        CloseableKt.closeFinally(r0, null);
        return r0.toByteArray();
    L15:
        th = move-exception;
        throw th;     // Catch: Throwable -> L28
    L28:
        th = move-exception;
        CloseableKt.closeFinally(r1, th);     // Catch: Throwable -> L25
        throw th;     // Catch: Throwable -> L25
    L25:
        th = move-exception;
        throw th;     // Catch: Throwable -> L32
    L32:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);
        throw th;
    L9:
        return new byte[0];
    L5:
        return new byte[0];
    }

    @JvmStatic
    public static final BackoffPolicy intToBackoffPolicy(int r2) {
        if (r2 == 0) goto L10;
        if (r2 == 1) goto L6;
        CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("Could not convert ", r2, " to BackoffPolicy");
        return null;
    L6:
        return BackoffPolicy.LINEAR;
    L10:
        return BackoffPolicy.EXPONENTIAL;
    }

    @JvmStatic
    public static final NetworkType intToNetworkType(int r2) {
        if (r2 == 0) goto L28;
        if (r2 == 1) goto L26;
        if (r2 == 2) goto L24;
        if (r2 == 3) goto L22;
        if (r2 == 4) goto L20;
        if (Build.VERSION.SDK_INT >= 30) goto L14;
    L17:
        CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("Could not convert ", r2, " to NetworkType");
        return null;
    L14:
        if (r2 != 5) goto L17;
        return NetworkType.TEMPORARILY_UNMETERED;
    L20:
        return NetworkType.METERED;
    L22:
        return NetworkType.NOT_ROAMING;
    L24:
        return NetworkType.UNMETERED;
    L26:
        return NetworkType.CONNECTED;
    L28:
        return NetworkType.NOT_REQUIRED;
    }

    @JvmStatic
    public static final OutOfQuotaPolicy intToOutOfQuotaPolicy(int r2) {
        if (r2 == 0) goto L10;
        if (r2 == 1) goto L6;
        CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("Could not convert ", r2, " to OutOfQuotaPolicy");
        return null;
    L6:
        return OutOfQuotaPolicy.DROP_WORK_REQUEST;
    L10:
        return OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST;
    }

    @JvmStatic
    public static final WorkInfo$State intToState(int r2) {
        if (r2 == 0) goto L26;
        if (r2 == 1) goto L24;
        if (r2 == 2) goto L22;
        if (r2 == 3) goto L20;
        if (r2 == 4) goto L18;
        if (r2 == 5) goto L14;
        CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("Could not convert ", r2, " to State");
        return null;
    L14:
        return WorkInfo$State.CANCELLED;
    L18:
        return WorkInfo$State.BLOCKED;
    L20:
        return WorkInfo$State.FAILED;
    L22:
        return WorkInfo$State.SUCCEEDED;
    L24:
        return WorkInfo$State.RUNNING;
    L26:
        return WorkInfo$State.ENQUEUED;
    }

    @JvmStatic
    public static final int networkTypeToInt(NetworkType r4) {
        int r0 = WhenMappings.$EnumSwitchMapping$2[r4.ordinal()];
        if (r0 != 1) goto L5;
        return 0;
    L5:
        if (r0 != 2) goto L7;
        return 1;
    L7:
        if (r0 != 3) goto L9;
        return 2;
    L9:
        if (r0 != 4) goto L11;
        return 3;
    L11:
        if (r0 != 5) goto L13;
        return 4;
    L13:
        if (Build.VERSION.SDK_INT >= 30) goto L15;
    L17:
        Native$$ExternalSyntheticBUOutline0.m("Could not convert ", r4, " to int");
        return 0;
    L15:
        if (r4 != NetworkType.TEMPORARILY_UNMETERED) goto L17;
        return 5;
    }

    @JvmStatic
    public static final int outOfQuotaPolicyToInt(OutOfQuotaPolicy r2) {
        int r22 = WhenMappings.$EnumSwitchMapping$3[r2.ordinal()];
        if (r22 != 1) goto L5;
        return 0;
    L5:
        if (r22 != 2) goto L7;
        return 1;
    L7:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    @JvmStatic
    public static final byte[] setOfTriggersToByteArray(Set<Constraints.ContentUriTrigger> r4) {
        if (r4.isEmpty() == true) goto L5;
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        ObjectOutputStream r1 = new ObjectOutputStream(r0);     // Catch: Throwable -> L19
        r1.writeInt(r4.size());     // Catch: Throwable -> L12
        Iterator<Constraints.ContentUriTrigger> r42 = r4.iterator();     // Catch: Throwable -> L12
    L10:
        if (r42.hasNext() == false) goto L14;
        Constraints.ContentUriTrigger r2 = r42.next();     // Catch: Throwable -> L12
        r1.writeUTF(r2.getUri().toString());     // Catch: Throwable -> L12
        r1.writeBoolean(r2.isTriggeredForDescendants());     // Catch: Throwable -> L12
        goto L10
    L14:
        Unit r43 = Unit.INSTANCE;     // Catch: Throwable -> L12
    L16:
        CloseableKt.closeFinally(r1, null);     // Catch: Throwable -> L19
        CloseableKt.closeFinally(r0, null);
        return r0.toByteArray();
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L22
    L22:
        th = move-exception;
        CloseableKt.closeFinally(r1, th);     // Catch: Throwable -> L19
        throw th;     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        throw th;     // Catch: Throwable -> L26
    L26:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);
        throw th;
    L5:
        return new byte[0];
    }

    @JvmStatic
    public static final int stateToInt(WorkInfo$State r1) {
        switch(WhenMappings.$EnumSwitchMapping$0[r1.ordinal()]) {
            case 1: goto L16;
            case 2: goto L14;
            case 3: goto L12;
            case 4: goto L10;
            case 5: goto L8;
            case 6: goto L6;
            default: goto L4;
        };
    L4:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L6:
        return 5;
    L8:
        return 4;
    L10:
        return 3;
    L12:
        return 2;
    L14:
        return 1;
    L16:
        return 0;
    }

    @JvmStatic
    public static final NetworkRequestCompat toNetworkRequest$work_runtime_release(byte[] r7) {
        if (Build.VERSION.SDK_INT < 28) goto L32;
        if (r7.length == 0) goto L32;
        ByteArrayInputStream r0 = new ByteArrayInputStream(r7);
        ObjectInputStream r72 = new ObjectInputStream(r0);     // Catch: Throwable -> L21
        int r1 = r72.readInt();     // Catch: Throwable -> L12
        int[] r3 = new int[r1];     // Catch: Throwable -> L12
        int r4 = 0;
        int r5 = 0;
    L10:
        if (r5 >= r1) goto L14;
        r3[r5] = r72.readInt();     // Catch: Throwable -> L12
        r5 = r5 + 1;     // Catch: Throwable -> L12
        goto L10
    L14:
        int r12 = r72.readInt();     // Catch: Throwable -> L12
        int[] r52 = new int[r12];     // Catch: Throwable -> L12
    L15:
        if (r4 >= r12) goto L17;
        r52[r4] = r72.readInt();     // Catch: Throwable -> L12
        r4 = r4 + 1;     // Catch: Throwable -> L12
        goto L15
    L17:
        NetworkRequestCompat r13 = NetworkRequest28.INSTANCE.createNetworkRequestCompat$work_runtime_release(r52, r3);     // Catch: Throwable -> L12
        CloseableKt.closeFinally(r72, null);     // Catch: Throwable -> L21
        CloseableKt.closeFinally(r0, null);
        return r13;
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        CloseableKt.closeFinally(r72, th);     // Catch: Throwable -> L21
        throw th;     // Catch: Throwable -> L21
    L21:
        th = move-exception;
        throw th;     // Catch: Throwable -> L28
    L28:
        th = move-exception;
        CloseableKt.closeFinally(r0, th);
        throw th;
    L32:
        return new NetworkRequestCompat(null);
    }
}
