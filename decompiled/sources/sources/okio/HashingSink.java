package okio;

import java.security.MessageDigest;
import javax.crypto.Mac;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001a2\u00020\u00012\u00020\u0002:\u0001\u001aB\u0019\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\nJ\u001f\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0018\u001a\u00020\u00178G¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Lokio/HashingSink;", "Lokio/ForwardingSink;", "Lokio/Sink;", "sink", "Ljava/security/MessageDigest;", "digest", "<init>", "(Lokio/Sink;Ljava/security/MessageDigest;)V", _UrlKt.FRAGMENT_ENCODE_SET, "algorithm", "(Lokio/Sink;Ljava/lang/String;)V", "Lokio/Buffer;", "source", _UrlKt.FRAGMENT_ENCODE_SET, "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "write", "(Lokio/Buffer;J)V", "messageDigest", "Ljava/security/MessageDigest;", "Ljavax/crypto/Mac;", "mac", "Ljavax/crypto/Mac;", "Lokio/ByteString;", "hash", "()Lokio/ByteString;", "Companion", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHashingSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HashingSink.kt\nokio/HashingSink\n+ 2 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,149:1\n85#2:150\n*S KotlinDebug\n*F\n+ 1 HashingSink.kt\nokio/HashingSink\n*L\n76#1:150\n*E\n"})
/* loaded from: classes5.dex */
public final class HashingSink extends ForwardingSink implements Sink {
    public static final Companion Companion = null;
    private final Mac mac;
    private final MessageDigest messageDigest;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lokio/HashingSink$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Lokio/Sink;", "sink", "Lokio/HashingSink;", "sha256", "(Lokio/Sink;)Lokio/HashingSink;", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final HashingSink sha256(Sink r2) {
            return new HashingSink(r2, "SHA-256");
        }
    }

    static {
        Companion = new Companion(null);
    }

    public HashingSink(Sink r1, String r2) {
        this(r1, MessageDigest.getInstance(r2));
    }

    public HashingSink(Sink r1, MessageDigest r2) {
        super(r1);
        this.messageDigest = r2;
        this.mac = null;
    }

    @JvmName(name = "hash")
    public final ByteString hash() {
        MessageDigest r0 = this.messageDigest;
        if (r0 == null) goto L5;
        byte[] r1 = r0.digest();
    L7:
        return new ByteString(r1);
    L5:
        r1 = this.mac.doFinal();
        goto L7
    }

    @Override // okio.ForwardingSink, okio.Sink
    public void write(Buffer r9, long r10) {
        SegmentedByteString.checkOffsetAndCount(r9.size(), 0, r10);
        Segment r102 = r9.head;
        long r0 = 0;
    L4:
        if (r0 >= r10) goto L10;
        int r11 = (int) Math.min(r10 - r0, r102.limit - r102.pos);
        MessageDigest r2 = this.messageDigest;
        if (r2 == null) goto L8;
        r2.update(r102.data, r102.pos, r11);
    L9:
        r0 = r0 + r11;
        r102 = r102.next;
        goto L4
    L8:
        this.mac.update(r102.data, r102.pos, r11);
        goto L9
    L10:
        super.write(r9, r10);
    }
}
