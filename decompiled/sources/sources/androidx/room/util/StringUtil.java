package androidx.room.util;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.NotificationBadge;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a!\u0010\u0006\u001a\u00020\u00052\n\u0010\u0002\u001a\u00060\u0000j\u0002`\u00012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007\"\"\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u0012\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "builder", _UrlKt.FRAGMENT_ENCODE_SET, NotificationBadge.NewHtcHomeBadger.COUNT, _UrlKt.FRAGMENT_ENCODE_SET, "appendPlaceholders", "(Ljava/lang/StringBuilder;I)V", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "EMPTY_STRING_ARRAY", "[Ljava/lang/String;", "getEMPTY_STRING_ARRAY$annotations", "()V", "room-runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
@JvmName(name = "StringUtil")
@SourceDebugExtension({"SMAP\nStringUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringUtil.kt\nandroidx/room/util/StringUtil\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,83:1\n1617#2,9:84\n1869#2:93\n1870#2:95\n1626#2:96\n1#3:94\n*S KotlinDebug\n*F\n+ 1 StringUtil.kt\nandroidx/room/util/StringUtil\n*L\n64#1:84,9\n64#1:93\n64#1:95\n64#1:96\n64#1:94\n*E\n"})
/* loaded from: classes4.dex */
public abstract class StringUtil {

    @JvmField
    public static final String[] EMPTY_STRING_ARRAY = null;

    static {
        EMPTY_STRING_ARRAY = new String[0];
    }

    public static final void appendPlaceholders(StringBuilder r2, int r3) {
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L8;
        r2.append("?");
        if (r0 >= (r3 - 1)) goto L7;
        r2.append(",");
    L7:
        r0 = r0 + 1;
        goto L3
    }
}
