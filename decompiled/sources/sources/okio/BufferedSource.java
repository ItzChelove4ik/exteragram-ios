package okio;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u00012\u00020\u0002J\u000f\u0010\u0004\u001a\u00020\u0003H&¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH&¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H&¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0010H&¢\u0006\u0004\b\u0013\u0010\u0012J\u000f\u0010\u0015\u001a\u00020\u0014H&¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0014H&¢\u0006\u0004\b\u0017\u0010\u0016J\u000f\u0010\u0018\u001a\u00020\u0006H&¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\u0006H&¢\u0006\u0004\b\u001a\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u0006H&¢\u0006\u0004\b\u001b\u0010\u0019J\u000f\u0010\u001c\u001a\u00020\u0006H&¢\u0006\u0004\b\u001c\u0010\u0019J\u0017\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\u001d\u0010\nJ\u000f\u0010\u001f\u001a\u00020\u001eH&¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\u001f\u0010!J\u0017\u0010$\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\"H&¢\u0006\u0004\b$\u0010%J\u000f\u0010'\u001a\u00020&H&¢\u0006\u0004\b'\u0010(J\u0017\u0010*\u001a\u00020\b2\u0006\u0010)\u001a\u00020&H&¢\u0006\u0004\b*\u0010+J\u001f\u0010*\u001a\u00020\b2\u0006\u0010)\u001a\u00020,2\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b*\u0010-J\u0017\u0010/\u001a\u00020\u00062\u0006\u0010)\u001a\u00020.H&¢\u0006\u0004\b/\u00100J\u0017\u00102\u001a\u0002012\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b2\u00103J\u000f\u00104\u001a\u000201H&¢\u0006\u0004\b4\u00105J\u0017\u00104\u001a\u0002012\u0006\u00106\u001a\u00020\u0006H&¢\u0006\u0004\b4\u00103J\u000f\u00107\u001a\u00020\u0014H&¢\u0006\u0004\b7\u0010\u0016J\u0017\u0010:\u001a\u0002012\u0006\u00109\u001a\u000208H&¢\u0006\u0004\b:\u0010;J'\u0010?\u001a\u00020\u00062\u0006\u0010<\u001a\u00020\u001e2\u0006\u0010=\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\u0006H&¢\u0006\u0004\b?\u0010@J\u001f\u0010B\u001a\u00020\u00032\u0006\u0010A\u001a\u00020\u00062\u0006\u0010<\u001a\u00020\u001eH&¢\u0006\u0004\bB\u0010CJ\u000f\u0010D\u001a\u00020\u0000H&¢\u0006\u0004\bD\u0010EJ\u000f\u0010G\u001a\u00020FH&¢\u0006\u0004\bG\u0010HR\u0014\u0010K\u001a\u00020,8&X¦\u0004¢\u0006\u0006\u001a\u0004\bI\u0010J\u0082\u0001\u0002,Lø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006MÀ\u0006\u0001"}, d2 = {"Lokio/BufferedSource;", "Lokio/Source;", "Ljava/nio/channels/ReadableByteChannel;", _UrlKt.FRAGMENT_ENCODE_SET, "exhausted", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "require", "(J)V", PluginsConstants.REQUEST, "(J)Z", _UrlKt.FRAGMENT_ENCODE_SET, "readByte", "()B", _UrlKt.FRAGMENT_ENCODE_SET, "readShort", "()S", "readShortLe", _UrlKt.FRAGMENT_ENCODE_SET, "readInt", "()I", "readIntLe", "readLong", "()J", "readLongLe", "readDecimalLong", "readHexadecimalUnsignedLong", "skip", "Lokio/ByteString;", "readByteString", "()Lokio/ByteString;", "(J)Lokio/ByteString;", "Lokio/Options;", "options", "select", "(Lokio/Options;)I", _UrlKt.FRAGMENT_ENCODE_SET, "readByteArray", "()[B", "sink", "readFully", "([B)V", "Lokio/Buffer;", "(Lokio/Buffer;J)V", "Lokio/Sink;", "readAll", "(Lokio/Sink;)J", _UrlKt.FRAGMENT_ENCODE_SET, "readUtf8", "(J)Ljava/lang/String;", "readUtf8LineStrict", "()Ljava/lang/String;", "limit", "readUtf8CodePoint", "Ljava/nio/charset/Charset;", "charset", "readString", "(Ljava/nio/charset/Charset;)Ljava/lang/String;", "bytes", "fromIndex", "toIndex", "indexOf", "(Lokio/ByteString;JJ)J", "offset", "rangeEquals", "(JLokio/ByteString;)Z", "peek", "()Lokio/BufferedSource;", "Ljava/io/InputStream;", "inputStream", "()Ljava/io/InputStream;", "getBuffer", "()Lokio/Buffer;", "buffer", "Lokio/RealBufferedSource;", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public interface BufferedSource extends Source, ReadableByteChannel {
    boolean exhausted();

    Buffer getBuffer();

    long indexOf(ByteString r1, long r2, long r4);

    InputStream inputStream();

    BufferedSource peek();

    boolean rangeEquals(long r1, ByteString r3);

    long readAll(Sink r1);

    byte readByte();

    byte[] readByteArray();

    ByteString readByteString();

    ByteString readByteString(long r1);

    long readDecimalLong();

    void readFully(Buffer r1, long r2);

    void readFully(byte[] r1);

    long readHexadecimalUnsignedLong();

    int readInt();

    int readIntLe();

    long readLong();

    long readLongLe();

    short readShort();

    short readShortLe();

    String readString(Charset r1);

    String readUtf8(long r1);

    int readUtf8CodePoint();

    String readUtf8LineStrict();

    String readUtf8LineStrict(long r1);

    boolean request(long r1);

    void require(long r1);

    int select(Options r1);

    void skip(long r1);
}
