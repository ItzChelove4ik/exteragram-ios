package okio;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaStatus;
import j$.io.InputStreamRetargetInterface;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.CharsKt;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\n\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\n\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u0018\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0018\u0010\u001aJ\u0017\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010!\u001a\u00020 H\u0016¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020 H\u0016¢\u0006\u0004\b#\u0010$J\u0017\u0010\n\u001a\u00020\u001d2\u0006\u0010\u0007\u001a\u00020%H\u0016¢\u0006\u0004\b\n\u0010&J\u001f\u0010#\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b#\u0010'J\u0017\u0010)\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020(H\u0016¢\u0006\u0004\b)\u0010*J\u0017\u0010,\u001a\u00020+2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b,\u0010-J\u0017\u00100\u001a\u00020+2\u0006\u0010/\u001a\u00020.H\u0016¢\u0006\u0004\b0\u00101J\u000f\u00102\u001a\u00020+H\u0016¢\u0006\u0004\b2\u00103J\u0017\u00102\u001a\u00020+2\u0006\u00104\u001a\u00020\bH\u0016¢\u0006\u0004\b2\u0010-J\u000f\u00105\u001a\u00020\u001dH\u0016¢\u0006\u0004\b5\u00106J\u000f\u00108\u001a\u000207H\u0016¢\u0006\u0004\b8\u00109J\u000f\u0010:\u001a\u000207H\u0016¢\u0006\u0004\b:\u00109J\u000f\u0010;\u001a\u00020\u001dH\u0016¢\u0006\u0004\b;\u00106J\u000f\u0010<\u001a\u00020\u001dH\u0016¢\u0006\u0004\b<\u00106J\u000f\u0010=\u001a\u00020\bH\u0016¢\u0006\u0004\b=\u0010>J\u000f\u0010?\u001a\u00020\bH\u0016¢\u0006\u0004\b?\u0010>J\u000f\u0010@\u001a\u00020\bH\u0016¢\u0006\u0004\b@\u0010>J\u000f\u0010A\u001a\u00020\bH\u0016¢\u0006\u0004\bA\u0010>J\u0017\u0010B\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\bB\u0010\u0011J\u0017\u0010D\u001a\u00020\b2\u0006\u0010C\u001a\u00020\u0014H\u0016¢\u0006\u0004\bD\u0010EJ'\u0010D\u001a\u00020\b2\u0006\u0010C\u001a\u00020\u00142\u0006\u0010F\u001a\u00020\b2\u0006\u0010G\u001a\u00020\bH\u0016¢\u0006\u0004\bD\u0010HJ'\u0010D\u001a\u00020\b2\u0006\u0010I\u001a\u00020\u00172\u0006\u0010F\u001a\u00020\b2\u0006\u0010G\u001a\u00020\bH\u0016¢\u0006\u0004\bD\u0010JJ\u001f\u0010L\u001a\u00020\f2\u0006\u0010K\u001a\u00020\b2\u0006\u0010I\u001a\u00020\u0017H\u0016¢\u0006\u0004\bL\u0010MJ/\u0010L\u001a\u00020\f2\u0006\u0010K\u001a\u00020\b2\u0006\u0010I\u001a\u00020\u00172\u0006\u0010N\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\u001dH\u0016¢\u0006\u0004\bL\u0010OJ\u000f\u0010P\u001a\u00020\u0001H\u0016¢\u0006\u0004\bP\u0010QJ\u000f\u0010S\u001a\u00020RH\u0016¢\u0006\u0004\bS\u0010TJ\u000f\u0010U\u001a\u00020\fH\u0016¢\u0006\u0004\bU\u0010\u000eJ\u000f\u0010V\u001a\u00020\u000fH\u0016¢\u0006\u0004\bV\u0010WJ\u000f\u0010Y\u001a\u00020XH\u0016¢\u0006\u0004\bY\u0010ZJ\u000f\u0010[\u001a\u00020+H\u0016¢\u0006\u0004\b[\u00103R\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\\R\u0014\u0010]\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b]\u0010^R\u0016\u0010_\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b_\u0010`R\u001b\u0010d\u001a\u00020\u00068Ö\u0002X\u0096\u0004¢\u0006\f\u0012\u0004\bc\u0010W\u001a\u0004\ba\u0010b¨\u0006e"}, d2 = {"Lokio/RealBufferedSource;", "Lokio/BufferedSource;", "Lokio/Source;", "source", "<init>", "(Lokio/Source;)V", "Lokio/Buffer;", "sink", _UrlKt.FRAGMENT_ENCODE_SET, "byteCount", "read", "(Lokio/Buffer;J)J", _UrlKt.FRAGMENT_ENCODE_SET, "exhausted", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "require", "(J)V", PluginsConstants.REQUEST, "(J)Z", _UrlKt.FRAGMENT_ENCODE_SET, "readByte", "()B", "Lokio/ByteString;", "readByteString", "()Lokio/ByteString;", "(J)Lokio/ByteString;", "Lokio/Options;", "options", _UrlKt.FRAGMENT_ENCODE_SET, "select", "(Lokio/Options;)I", _UrlKt.FRAGMENT_ENCODE_SET, "readByteArray", "()[B", "readFully", "([B)V", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;)I", "(Lokio/Buffer;J)V", "Lokio/Sink;", "readAll", "(Lokio/Sink;)J", _UrlKt.FRAGMENT_ENCODE_SET, "readUtf8", "(J)Ljava/lang/String;", "Ljava/nio/charset/Charset;", "charset", "readString", "(Ljava/nio/charset/Charset;)Ljava/lang/String;", "readUtf8LineStrict", "()Ljava/lang/String;", "limit", "readUtf8CodePoint", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "readShort", "()S", "readShortLe", "readInt", "readIntLe", "readLong", "()J", "readLongLe", "readDecimalLong", "readHexadecimalUnsignedLong", "skip", "b", "indexOf", "(B)J", "fromIndex", "toIndex", "(BJJ)J", "bytes", "(Lokio/ByteString;JJ)J", "offset", "rangeEquals", "(JLokio/ByteString;)Z", "bytesOffset", "(JLokio/ByteString;II)Z", "peek", "()Lokio/BufferedSource;", "Ljava/io/InputStream;", "inputStream", "()Ljava/io/InputStream;", "isOpen", "close", "()V", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "toString", "Lokio/Source;", "bufferField", "Lokio/Buffer;", "closed", "Z", "getBuffer", "()Lokio/Buffer;", "getBuffer$annotations", "buffer", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRealBufferedSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealBufferedSource.kt\nokio/RealBufferedSource\n+ 2 RealBufferedSource.kt\nokio/internal/-RealBufferedSource\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 BufferedSource.kt\nokio/internal/-BufferedSource\n+ 5 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,207:1\n63#1:213\n63#1:224\n63#1:231\n63#1:237\n63#1:239\n63#1:243\n63#1:248\n63#1:266\n63#1:270\n63#1:277\n63#1:290\n63#1:299\n63#1:300\n63#1:301\n63#1:307\n63#1:315\n63#1:328\n63#1:332\n63#1:333\n63#1:334\n63#1:335\n63#1:340\n63#1:352\n63#1:368\n63#1:378\n63#1:381\n63#1:384\n63#1:387\n63#1:390\n63#1:393\n63#1:399\n63#1:416\n63#1:436\n63#1:451\n63#1:468\n63#1:495\n39#2:208\n40#2,3:210\n43#2,7:214\n53#2:221\n54#2:223\n58#2,2:225\n62#2:227\n63#2,2:229\n65#2,3:232\n71#2,2:235\n76#2:238\n77#2:240\n81#2,2:241\n86#2:244\n88#2,2:246\n90#2,13:249\n109#2:265\n110#2:267\n114#2,2:268\n119#2,6:271\n125#2,9:278\n136#2,3:287\n139#2,6:291\n145#2:298\n149#2,5:302\n154#2,5:308\n161#2,2:313\n163#2,11:316\n177#2:327\n178#2:329\n182#2,2:330\n187#2,4:336\n191#2,6:341\n201#2:347\n202#2,3:349\n205#2,8:353\n213#2,3:362\n220#2,3:365\n223#2,7:369\n233#2,2:376\n238#2,2:379\n243#2,2:382\n248#2,2:385\n253#2,2:388\n258#2,2:391\n263#2,5:394\n268#2,11:400\n282#2,5:411\n287#2,14:417\n304#2,2:431\n306#2,2:434\n308#2,7:437\n317#2,2:444\n319#2,4:447\n323#2,11:452\n421#2,2:463\n424#2,2:466\n426#2,7:469\n442#2:476\n444#2,12:478\n459#2:490\n463#2,4:491\n467#2:496\n469#2:497\n471#2:498\n1#3:209\n1#3:222\n1#3:228\n1#3:245\n1#3:348\n1#3:433\n1#3:446\n1#3:465\n1#3:477\n26#4,3:262\n88#5:297\n88#5:361\n*S KotlinDebug\n*F\n+ 1 RealBufferedSource.kt\nokio/RealBufferedSource\n*L\n67#1:213\n68#1:224\n70#1:231\n71#1:237\n72#1:239\n73#1:243\n74#1:248\n76#1:266\n77#1:270\n79#1:277\n81#1:290\n84#1:299\n85#1:300\n89#1:301\n93#1:307\n94#1:315\n95#1:328\n96#1:332\n99#1:333\n100#1:334\n105#1:335\n108#1:340\n110#1:352\n111#1:368\n112#1:378\n113#1:381\n114#1:384\n115#1:387\n116#1:390\n117#1:393\n118#1:399\n119#1:416\n120#1:436\n125#1:451\n135#1:468\n203#1:495\n67#1:208\n67#1:210,3\n67#1:214,7\n68#1:221\n68#1:223\n69#1:225,2\n70#1:227\n70#1:229,2\n70#1:232,3\n71#1:235,2\n72#1:238\n72#1:240\n73#1:241,2\n74#1:244\n74#1:246,2\n74#1:249,13\n76#1:265\n76#1:267\n77#1:268,2\n79#1:271,6\n79#1:278,9\n81#1:287,3\n81#1:291,6\n81#1:298\n93#1:302,5\n93#1:308,5\n94#1:313,2\n94#1:316,11\n95#1:327\n95#1:329\n96#1:330,2\n108#1:336,4\n108#1:341,6\n110#1:347\n110#1:349,3\n110#1:353,8\n110#1:362,3\n111#1:365,3\n111#1:369,7\n112#1:376,2\n113#1:379,2\n114#1:382,2\n115#1:385,2\n116#1:388,2\n117#1:391,2\n118#1:394,5\n118#1:400,11\n119#1:411,5\n119#1:417,14\n120#1:431,2\n120#1:434,2\n120#1:437,7\n125#1:444,2\n125#1:447,4\n125#1:452,11\n135#1:463,2\n135#1:466,2\n135#1:469,7\n149#1:476\n149#1:478,12\n151#1:490\n203#1:491,4\n203#1:496\n204#1:497\n205#1:498\n67#1:209\n68#1:222\n70#1:228\n74#1:245\n110#1:348\n120#1:433\n125#1:446\n135#1:465\n149#1:477\n75#1:262,3\n81#1:297\n110#1:361\n*E\n"})
/* loaded from: classes.dex */
public final class RealBufferedSource implements BufferedSource {

    @JvmField
    public final Buffer bufferField;

    @JvmField
    public boolean closed;

    @JvmField
    public final Source source;

    @Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, d2 = {"okio/RealBufferedSource$inputStream$1", "Ljava/io/InputStream;", "read", _UrlKt.FRAGMENT_ENCODE_SET, "data", _UrlKt.FRAGMENT_ENCODE_SET, "offset", "byteCount", "available", "close", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "transferTo", _UrlKt.FRAGMENT_ENCODE_SET, "out", "Ljava/io/OutputStream;", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nRealBufferedSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealBufferedSource.kt\nokio/RealBufferedSource$inputStream$1\n+ 2 RealBufferedSource.kt\nokio/RealBufferedSource\n+ 3 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,207:1\n63#2:208\n63#2:209\n63#2:210\n63#2:212\n63#2:213\n63#2:214\n63#2:215\n63#2:217\n63#2:218\n63#2:219\n63#2:220\n73#3:211\n85#3:216\n*S KotlinDebug\n*F\n+ 1 RealBufferedSource.kt\nokio/RealBufferedSource$inputStream$1\n*L\n157#1:208\n158#1:209\n161#1:210\n168#1:212\n169#1:213\n173#1:214\n178#1:215\n189#1:217\n190#1:218\n193#1:219\n194#1:220\n161#1:211\n178#1:216\n*E\n"})
    /* renamed from: okio.RealBufferedSource$inputStream$1, reason: invalid class name */
    public static final class AnonymousClass1 extends InputStream implements InputStreamRetargetInterface {
        final /* synthetic */ RealBufferedSource this$0;

        public AnonymousClass1(RealBufferedSource r1) {
            this.this$0 = r1;
        }

        @Override // java.io.InputStream
        public int available() {
            RealBufferedSource r4 = this.this$0;
            if (r4.closed == false) goto L5;
            Model$$ExternalSyntheticBUOutline0.m("closed");
            return 0;
        L5:
            return (int) Math.min(r4.bufferField.size(), 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.this$0.close();
        }

        @Override // java.io.InputStream
        public int read() {
            RealBufferedSource r0 = this.this$0;
            if (r0.closed == false) goto L5;
            Model$$ExternalSyntheticBUOutline0.m("closed");
            return 0;
        L5:
            if (r0.bufferField.size() != 0) goto L11;
            RealBufferedSource r02 = this.this$0;
            if (r02.source.read(r02.bufferField, MediaStatus.COMMAND_PLAYBACK_RATE) != (-1)) goto L11;
            return -1;
        L11:
            return this.this$0.bufferField.readByte() & UByte.MAX_VALUE;
        }

        @Override // java.io.InputStream
        public int read(byte[] r8, int r9, int r10) {
            if (this.this$0.closed == true) goto L12;
            SegmentedByteString.checkOffsetAndCount(r8.length, r9, r10);
            if (this.this$0.bufferField.size() != 0) goto L11;
            RealBufferedSource r0 = this.this$0;
            if (r0.source.read(r0.bufferField, MediaStatus.COMMAND_PLAYBACK_RATE) != (-1)) goto L11;
            return -1;
        L11:
            return this.this$0.bufferField.read(r8, r9, r10);
        L12:
            Model$$ExternalSyntheticBUOutline0.m("closed");
            return 0;
        }

        public String toString() {
            return this.this$0 + ".inputStream()";
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public long transferTo(OutputStream r12) {
            if (this.this$0.closed == true) goto L12;
            long r2 = 0;
        L6:
            if (this.this$0.bufferField.size() != 0) goto L11;
            RealBufferedSource r4 = this.this$0;
            if (r4.source.read(r4.bufferField, MediaStatus.COMMAND_PLAYBACK_RATE) != (-1)) goto L11;
            return r2;
        L11:
            r2 = r2 + this.this$0.bufferField.size();
            Buffer.writeTo$default(this.this$0.bufferField, r12, 0, 2, null);
            goto L6
        L12:
            Model$$ExternalSyntheticBUOutline0.m("closed");
            return 0;
        }
    }

    public RealBufferedSource(Source r1) {
        this.source = r1;
        this.bufferField = new Buffer();
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed == true) goto L6;
        this.closed = true;
        this.source.close();
        this.bufferField.clear();
        return;
    }

    @Override // okio.BufferedSource
    public boolean exhausted() {
        if (this.closed == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return false;
    L5:
        if (this.bufferField.exhausted() == true) goto L7;
        return false;
    L7:
        if (this.source.read(this.bufferField, MediaStatus.COMMAND_PLAYBACK_RATE) != (-1)) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer getBuffer() {
        return this.bufferField;
    }

    public long indexOf(byte r7) {
        return indexOf(r7, 0, LongCompanionObject.MAX_VALUE);
    }

    public long indexOf(byte r10, long r11, long r13) {
        if (this.closed == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return 0;
    L5:
        if (0 > r11) goto L22;
        if (r11 > r13) goto L22;
        long r3 = r11;
    L10:
        if (r3 >= r13) goto L20;
        byte r2 = r10;
        long r5 = r13;
        long r102 = this.bufferField.indexOf(r2, r3, r5);
        if (r102 != (-1)) goto L13;
        long r103 = this.bufferField.size();
        if (r103 >= r5) goto L20;
        if (this.source.read(this.bufferField, MediaStatus.COMMAND_PLAYBACK_RATE) == (-1)) goto L20;
        r3 = Math.max(r3, r103);
        r10 = r2;
        r13 = r5;
        goto L10
    L13:
        return r102;
    L20:
        return -1;
    L22:
        throw new IllegalArgumentException(("fromIndex=" + r11 + " toIndex=" + r13).toString());
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString r11, long r12, long r14) {
        return okio.internal.RealBufferedSource.commonIndexOf$default(this, r11, 0, 0, r12, r14, 6, null);
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new AnonymousClass1(this);
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // okio.BufferedSource
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long r7, ByteString r9) {
        return rangeEquals(r7, r9, 0, r9.size());
    }

    public boolean rangeEquals(long r11, ByteString r13, int r14, int r15) {
        if (this.closed == true) goto L20;
        if (r15 >= 0) goto L7;
        return false;
    L7:
        if (r11 >= 0) goto L9;
        return false;
    L9:
        if (r14 >= 0) goto L11;
    L19:
        return false;
    L11:
        if ((r14 + r15) > r13.size()) goto L19;
        if (r15 != 0) goto L17;
        return true;
    L17:
        if (okio.internal.RealBufferedSource.commonIndexOf(this, r13, r14, r15, r11, r11 + 1) == (-1)) goto L19;
        return true;
    L20:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return false;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer r5) {
        if (this.bufferField.size() != 0) goto L9;
        if (this.source.read(this.bufferField, MediaStatus.COMMAND_PLAYBACK_RATE) != (-1)) goto L9;
        return -1;
    L9:
        return this.bufferField.read(r5);
    }

    @Override // okio.Source
    public long read(Buffer r6, long r7) {
        if (r7 >= 0) goto L5;
        Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r7);
        return 0;
    L5:
        if (this.closed == false) goto L7;
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return 0;
    L7:
        if (this.bufferField.size() != 0) goto L13;
        if (r7 != 0) goto L11;
        return 0;
    L11:
        if (this.source.read(this.bufferField, MediaStatus.COMMAND_PLAYBACK_RATE) != (-1)) goto L13;
        return -1;
    L13:
        long r72 = Math.min(r7, this.bufferField.size());
        return this.bufferField.read(r6, r72);
    }

    @Override // okio.BufferedSource
    public long readAll(Sink r9) {
        long r2 = 0;
    L3:
        long r4 = this.source.read(this.bufferField, MediaStatus.COMMAND_PLAYBACK_RATE);
        Buffer r5 = this.bufferField;
        if (r4 == (-1)) goto L9;
        long r42 = r5.completeSegmentByteCount();
        if (r42 <= 0) goto L3;
        r2 = r2 + r42;
        r9.write(this.bufferField, r42);
        goto L3
    L9:
        if (r5.size() <= 0) goto L17;
        long r22 = r2 + this.bufferField.size();
        Buffer r8 = this.bufferField;
        r9.write(r8, r8.size());
        return r22;
    L17:
        return r2;
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        require(1);
        return this.bufferField.readByte();
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray() {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readByteArray();
    }

    @Override // okio.BufferedSource
    public ByteString readByteString() {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readByteString();
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long r1) {
        require(r1);
        return this.bufferField.readByteString(r1);
    }

    @Override // okio.BufferedSource
    public long readDecimalLong() {
        require(1);
        long r4 = 0;
    L3:
        long r6 = r4 + 1;
        if (request(r6) == false) goto L20;
        byte r8 = this.bufferField.getByte(r4);
        if (r8 < 48) goto L10;
        if (r8 > 57) goto L10;
    L14:
        r4 = r6;
    L10:
        if (r4 != 0) goto L15;
        if (r8 == 45) goto L14;
    L15:
        if (r4 != 0) goto L20;
        Buffer$$ExternalSyntheticBUOutline0.m("Expected a digit or '-' but was 0x", Integer.toString(r8, CharsKt.checkRadix(16)));
        return 0;
    L20:
        return this.bufferField.readDecimalLong();
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer r1, long r2) {
        require(r2);     // Catch: EOFException -> L5
        this.bufferField.readFully(r1, r2);
        return;
    L5:
        e = move-exception;
        r1.writeAll(this.bufferField);
        throw e;
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] r7) {
        require(r7.length);     // Catch: EOFException -> L5
        this.bufferField.readFully(r7);
        return;
    L5:
        e = move-exception;
        int r1 = 0;
    L8:
        if (this.bufferField.size() <= 0) goto L14;
        Buffer r2 = this.bufferField;
        int r22 = r2.read(r7, r1, (int) r2.size());
        if (r22 == (-1)) goto L12;
        r1 = r1 + r22;
        goto L8
    L12:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return;
    L14:
        throw e;
    }

    @Override // okio.BufferedSource
    public long readHexadecimalUnsignedLong() {
        require(1);
        int r0 = 0;
    L3:
        int r1 = r0 + 1;
        if (request(r1) == false) goto L24;
        byte r2 = this.bufferField.getByte(r0);
        if (r2 < 48) goto L10;
        if (r2 > 57) goto L10;
    L18:
        r0 = r1;
    L10:
        if (r2 < 97) goto L14;
        if (r2 <= 102) goto L18;
    L14:
        if (r2 < 65) goto L19;
        if (r2 <= 70) goto L18;
    L19:
        if (r0 != 0) goto L24;
        Buffer$$ExternalSyntheticBUOutline0.m("Expected leading [0-9a-fA-F] character but was 0x", Integer.toString(r2, CharsKt.checkRadix(16)));
        return 0;
    L24:
        return this.bufferField.readHexadecimalUnsignedLong();
    }

    @Override // okio.BufferedSource
    public int readInt() {
        require(4);
        return this.bufferField.readInt();
    }

    @Override // okio.BufferedSource
    public int readIntLe() {
        require(4);
        return this.bufferField.readIntLe();
    }

    @Override // okio.BufferedSource
    public long readLong() {
        require(8);
        return this.bufferField.readLong();
    }

    @Override // okio.BufferedSource
    public long readLongLe() {
        require(8);
        return this.bufferField.readLongLe();
    }

    @Override // okio.BufferedSource
    public short readShort() {
        require(2);
        return this.bufferField.readShort();
    }

    @Override // okio.BufferedSource
    public short readShortLe() {
        require(2);
        return this.bufferField.readShortLe();
    }

    @Override // okio.BufferedSource
    public String readString(Charset r3) {
        this.bufferField.writeAll(this.source);
        return this.bufferField.readString(r3);
    }

    @Override // okio.BufferedSource
    public String readUtf8(long r1) {
        require(r1);
        return this.bufferField.readUtf8(r1);
    }

    @Override // okio.BufferedSource
    public int readUtf8CodePoint() {
        require(1);
        byte r0 = this.bufferField.getByte(0);
        if ((r0 & 224) != 192) goto L6;
        require(2);
    L12:
        return this.bufferField.readUtf8CodePoint();
    L6:
        if ((r0 & 240) != 224) goto L9;
        require(3);
        goto L12
    L9:
        if ((r0 & 248) != 240) goto L12;
        require(4);
        goto L12
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() {
        return readUtf8LineStrict(LongCompanionObject.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long r14) {
        if (r14 >= 0) goto L5;
        Buffer$$ExternalSyntheticBUOutline3.m("limit < 0: ", r14);
        return null;
    L5:
        if (r14 != LongCompanionObject.MAX_VALUE) goto L7;
        long r11 = Long.MAX_VALUE;
    L8:
        long r5 = indexOf((byte) 10, 0, r11);
        if (r5 == (-1)) goto L13;
        return okio.internal.Buffer.readUtf8Line(this.bufferField, r5);
    L13:
        if (r11 < LongCompanionObject.MAX_VALUE) goto L15;
    L24:
        Buffer r1 = new Buffer();
        Buffer r0 = this.bufferField;
        r0.copyTo(r1, 0, Math.min(32, r0.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.bufferField.size(), r14) + " content=" + r1.readByteString().hex() + Typography.ellipsis);
    L15:
        if (request(r11) == false) goto L24;
        if (this.bufferField.getByte(r11 - 1) != 13) goto L24;
        if (request(r11 + 1) == false) goto L24;
        if (this.bufferField.getByte(r11) != 10) goto L24;
        return okio.internal.Buffer.readUtf8Line(this.bufferField, r11);
    L7:
        r11 = r14 + 1;
        goto L8
    }

    @Override // okio.BufferedSource
    public boolean request(long r5) {
        if (r5 >= 0) goto L5;
        Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r5);
        return false;
    L5:
        if (this.closed == false) goto L7;
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return false;
    L7:
        if (this.bufferField.size() >= r5) goto L12;
        if (this.source.read(this.bufferField, MediaStatus.COMMAND_PLAYBACK_RATE) != (-1)) goto L7;
        return false;
    L12:
        return true;
    }

    @Override // okio.BufferedSource
    public void require(long r1) {
        if (request(r1) == false) goto L5;
        return;
    L5:
        Buffer$$ExternalSyntheticBUOutline1.m();
    }

    @Override // okio.BufferedSource
    public int select(Options r6) {
        if (this.closed == true) goto L13;
    L4:
        int r0 = okio.internal.Buffer.selectPrefix(this.bufferField, r6, true);
        if (r0 != (-2)) goto L6;
        if (this.source.read(this.bufferField, MediaStatus.COMMAND_PLAYBACK_RATE) != (-1)) goto L4;
        return -1;
    L6:
        if (r0 == (-1)) goto L9;
        this.bufferField.skip(r6.getByteStrings$okio()[r0].size());
        return r0;
    L9:
        return -1;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return 0;
    }

    @Override // okio.BufferedSource
    public void skip(long r5) {
        if (this.closed == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return;
    L5:
        if (r5 <= 0) goto L14;
        if (this.bufferField.size() != 0) goto L13;
        if (this.source.read(this.bufferField, MediaStatus.COMMAND_PLAYBACK_RATE) != (-1)) goto L13;
        Buffer$$ExternalSyntheticBUOutline1.m();
        return;
    L13:
        long r0 = Math.min(r5, this.bufferField.size());
        this.bufferField.skip(r0);
        r5 = r5 - r0;
        goto L5
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    public String toString() {
        return "buffer(" + this.source + ')';
    }
}
