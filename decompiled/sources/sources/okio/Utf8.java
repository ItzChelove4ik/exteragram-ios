package okio;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\u001a'\u0010\u0007\u001a\u00020\u0004*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "beginIndex", "endIndex", _UrlKt.FRAGMENT_ENCODE_SET, "size", "(Ljava/lang/String;II)J", "utf8Size", "okio"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "Utf8")
@SourceDebugExtension({"SMAP\nUtf8.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Utf8.kt\nokio/Utf8\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,559:1\n397#1,9:563\n127#1:572\n406#1,20:574\n440#1,4:595\n127#1:599\n446#1,10:601\n127#1:611\n456#1,5:612\n127#1:617\n461#1,24:618\n500#1,4:643\n127#1:647\n506#1,2:649\n127#1:651\n510#1,10:652\n127#1:662\n520#1,5:663\n127#1:668\n525#1,5:669\n127#1:674\n530#1,28:675\n397#1,9:704\n127#1:713\n406#1,20:715\n440#1,4:736\n127#1:740\n446#1,10:742\n127#1:752\n456#1,5:753\n127#1:758\n461#1,24:759\n500#1,4:784\n127#1:788\n506#1,2:790\n127#1:792\n510#1,10:793\n127#1:803\n520#1,5:804\n127#1:809\n525#1,5:810\n127#1:815\n530#1,28:816\n127#1:844\n127#1:846\n127#1:848\n127#1:850\n127#1:852\n127#1:854\n127#1:856\n127#1:858\n127#1:860\n1#2:560\n73#3:561\n67#3:562\n73#3:573\n67#3:594\n73#3:600\n67#3:642\n73#3:648\n67#3:703\n73#3:714\n67#3:735\n73#3:741\n67#3:783\n73#3:789\n73#3:845\n73#3:847\n73#3:849\n73#3:851\n73#3:853\n73#3:855\n73#3:857\n73#3:859\n73#3:861\n*S KotlinDebug\n*F\n+ 1 Utf8.kt\nokio/Utf8\n*L\n228#1:563,9\n228#1:572\n228#1:574,20\n232#1:595,4\n232#1:599\n232#1:601,10\n232#1:611\n232#1:612,5\n232#1:617\n232#1:618,24\n236#1:643,4\n236#1:647\n236#1:649,2\n236#1:651\n236#1:652,10\n236#1:662\n236#1:663,5\n236#1:668\n236#1:669,5\n236#1:674\n236#1:675,28\n277#1:704,9\n277#1:713\n277#1:715,20\n281#1:736,4\n281#1:740\n281#1:742,10\n281#1:752\n281#1:753,5\n281#1:758\n281#1:759,24\n285#1:784,4\n285#1:788\n285#1:790,2\n285#1:792\n285#1:793,10\n285#1:803\n285#1:804,5\n285#1:809\n285#1:810,5\n285#1:815\n285#1:816,28\n405#1:844\n443#1:846\n455#1:848\n460#1:850\n503#1:852\n507#1:854\n519#1:856\n524#1:858\n529#1:860\n127#1:561\n226#1:562\n228#1:573\n230#1:594\n232#1:600\n234#1:642\n236#1:648\n275#1:703\n277#1:714\n279#1:735\n281#1:741\n283#1:783\n285#1:789\n405#1:845\n443#1:847\n455#1:849\n460#1:851\n503#1:853\n507#1:855\n519#1:857\n524#1:859\n529#1:861\n*E\n"})
/* loaded from: classes.dex */
public abstract class Utf8 {
    @JvmOverloads
    @JvmName(name = "size")
    public static final long size(String r9, int r10, int r11) {
        long r0 = 0;
        if (r10 < 0) goto L39;
        if (r11 >= r10) goto L6;
        Utf8$$ExternalSyntheticBUOutline0.m("endIndex < beginIndex: ", r11, " < ", r10);
        return 0;
    L6:
        if (r11 > r9.length()) goto L35;
    L7:
        if (r10 >= r11) goto L34;
        char r2 = r9.charAt(r10);
        if (r2 < 128) goto L10;
        if (r2 < 2048) goto L14;
        if (r2 < 55296) goto L33;
        if (r2 > 57343) goto L33;
        int r6 = r10 + 1;
        if (r6 >= r11) goto L24;
        char r7 = r9.charAt(r6);
    L26:
        if (r2 > 56319) goto L32;
        if (r7 < 56320) goto L32;
        if (r7 > 57343) goto L32;
        r0 = r0 + 4;
        r10 = r10 + 2;
    L32:
        r0 = r0 + 1;
        r10 = r6;
        goto L7
    L24:
        r7 = 0;
    L33:
        long r22 = 3;
    L15:
        r0 = r0 + r22;
    L11:
        r10 = r10 + 1;
        goto L7
    L14:
        r22 = 2;
        goto L15
    L10:
        r0 = r0 + 1;
        goto L11
    L34:
        return r0;
    L35:
        Utf8$$ExternalSyntheticBUOutline0.m("endIndex > string.length: ", r11, " > ", r9.length());
        return 0;
    L39:
        Utf8$$ExternalSyntheticBUOutline1.m("beginIndex < 0: ", r10);
        return 0;
    }

    public static /* synthetic */ long size$default(String r0, int r1, int r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = r0.length();
    L9:
        return size(r0, r1, r2);
    }
}
