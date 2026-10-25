package okio;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.io.Opcodes;
import com.android.dx.rop.code.RegisterSpec;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaStatus;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.Closeable;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002\u00ad\u0001B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0001H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ'\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u001d\u0010\u001eJ!\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u000f¢\u0006\u0004\b!\u0010\"J\u000f\u0010$\u001a\u00020#H\u0016¢\u0006\u0004\b$\u0010%J\u0018\u0010)\u001a\u00020#2\u0006\u0010&\u001a\u00020\u000fH\u0087\u0002¢\u0006\u0004\b'\u0010(J\u000f\u0010+\u001a\u00020*H\u0016¢\u0006\u0004\b+\u0010,J\u000f\u0010.\u001a\u00020-H\u0016¢\u0006\u0004\b.\u0010/J\u000f\u00100\u001a\u00020\u000fH\u0016¢\u0006\u0004\b0\u0010\"J\u000f\u00101\u001a\u00020*H\u0016¢\u0006\u0004\b1\u0010,J\u000f\u00102\u001a\u00020-H\u0016¢\u0006\u0004\b2\u0010/J\u000f\u00103\u001a\u00020\u000fH\u0016¢\u0006\u0004\b3\u0010\"J\u000f\u00104\u001a\u00020\u000fH\u0016¢\u0006\u0004\b4\u0010\"J\u000f\u00105\u001a\u00020\u000fH\u0016¢\u0006\u0004\b5\u0010\"J\u000f\u00107\u001a\u000206H\u0016¢\u0006\u0004\b7\u00108J\u0017\u00107\u001a\u0002062\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b7\u00109J\u0017\u0010<\u001a\u00020-2\u0006\u0010;\u001a\u00020:H\u0016¢\u0006\u0004\b<\u0010=J\u001f\u0010?\u001a\u00020\u00112\u0006\u0010>\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b?\u0010@J\u0017\u0010B\u001a\u00020\u000f2\u0006\u0010>\u001a\u00020AH\u0016¢\u0006\u0004\bB\u0010CJ\u000f\u0010E\u001a\u00020DH\u0016¢\u0006\u0004\bE\u0010FJ\u0017\u0010E\u001a\u00020D2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\bE\u0010GJ\u0017\u0010J\u001a\u00020D2\u0006\u0010I\u001a\u00020HH\u0016¢\u0006\u0004\bJ\u0010KJ\u001f\u0010J\u001a\u00020D2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010I\u001a\u00020HH\u0016¢\u0006\u0004\bJ\u0010LJ\u000f\u0010M\u001a\u00020DH\u0016¢\u0006\u0004\bM\u0010FJ\u0017\u0010M\u001a\u00020D2\u0006\u0010N\u001a\u00020\u000fH\u0016¢\u0006\u0004\bM\u0010GJ\u000f\u0010O\u001a\u00020-H\u0016¢\u0006\u0004\bO\u0010/J\u000f\u0010Q\u001a\u00020PH\u0016¢\u0006\u0004\bQ\u0010RJ\u0017\u0010Q\u001a\u00020P2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\bQ\u0010SJ\u0017\u0010?\u001a\u00020\u00112\u0006\u0010>\u001a\u00020PH\u0016¢\u0006\u0004\b?\u0010TJ'\u0010U\u001a\u00020-2\u0006\u0010>\u001a\u00020P2\u0006\u0010\u001c\u001a\u00020-2\u0006\u0010\u0010\u001a\u00020-H\u0016¢\u0006\u0004\bU\u0010VJ\u0017\u0010U\u001a\u00020-2\u0006\u0010>\u001a\u00020WH\u0016¢\u0006\u0004\bU\u0010XJ\r\u0010Y\u001a\u00020\u0011¢\u0006\u0004\bY\u0010\u0006J\u0017\u0010Z\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\bZ\u0010\u0013J\u0017\u0010\\\u001a\u00020\u00002\u0006\u0010[\u001a\u000206H\u0016¢\u0006\u0004\b\\\u0010]J\u0017\u0010_\u001a\u00020\u00002\u0006\u0010^\u001a\u00020DH\u0016¢\u0006\u0004\b_\u0010`J'\u0010_\u001a\u00020\u00002\u0006\u0010^\u001a\u00020D2\u0006\u0010a\u001a\u00020-2\u0006\u0010b\u001a\u00020-H\u0016¢\u0006\u0004\b_\u0010cJ\u0017\u0010e\u001a\u00020\u00002\u0006\u0010d\u001a\u00020-H\u0016¢\u0006\u0004\be\u0010fJ\u001f\u0010g\u001a\u00020\u00002\u0006\u0010^\u001a\u00020D2\u0006\u0010I\u001a\u00020HH\u0016¢\u0006\u0004\bg\u0010hJ/\u0010g\u001a\u00020\u00002\u0006\u0010^\u001a\u00020D2\u0006\u0010a\u001a\u00020-2\u0006\u0010b\u001a\u00020-2\u0006\u0010I\u001a\u00020HH\u0016¢\u0006\u0004\bg\u0010iJ\u0017\u0010\\\u001a\u00020\u00002\u0006\u0010j\u001a\u00020PH\u0016¢\u0006\u0004\b\\\u0010kJ'\u0010\\\u001a\u00020\u00002\u0006\u0010j\u001a\u00020P2\u0006\u0010\u001c\u001a\u00020-2\u0006\u0010\u0010\u001a\u00020-H\u0016¢\u0006\u0004\b\\\u0010lJ\u0017\u0010\\\u001a\u00020-2\u0006\u0010j\u001a\u00020WH\u0016¢\u0006\u0004\b\\\u0010XJ\u0017\u0010n\u001a\u00020\u000f2\u0006\u0010j\u001a\u00020mH\u0016¢\u0006\u0004\bn\u0010oJ\u001f\u0010\\\u001a\u00020\u00002\u0006\u0010j\u001a\u00020m2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\\\u0010pJ\u0017\u0010r\u001a\u00020\u00002\u0006\u0010q\u001a\u00020-H\u0016¢\u0006\u0004\br\u0010fJ\u0017\u0010t\u001a\u00020\u00002\u0006\u0010s\u001a\u00020-H\u0016¢\u0006\u0004\bt\u0010fJ\u0017\u0010v\u001a\u00020\u00002\u0006\u0010u\u001a\u00020-H\u0016¢\u0006\u0004\bv\u0010fJ\u0017\u0010w\u001a\u00020\u00002\u0006\u0010u\u001a\u00020-H\u0016¢\u0006\u0004\bw\u0010fJ\u0017\u0010y\u001a\u00020\u00002\u0006\u0010x\u001a\u00020\u000fH\u0016¢\u0006\u0004\by\u0010zJ\u0017\u0010{\u001a\u00020\u00002\u0006\u0010x\u001a\u00020\u000fH\u0016¢\u0006\u0004\b{\u0010zJ\u0017\u0010|\u001a\u00020\u00002\u0006\u0010x\u001a\u00020\u000fH\u0016¢\u0006\u0004\b|\u0010zJ\u0019\u0010\u0081\u0001\u001a\u00020~2\u0006\u0010}\u001a\u00020-H\u0000¢\u0006\u0005\b\u007f\u0010\u0080\u0001J\u001f\u0010\\\u001a\u00020\u00112\u0006\u0010j\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\\\u0010@J \u0010U\u001a\u00020\u000f2\u0006\u0010>\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0005\bU\u0010\u0082\u0001J,\u0010\u0085\u0001\u001a\u00020\u000f2\u0006\u0010q\u001a\u00020#2\u0007\u0010\u0083\u0001\u001a\u00020\u000f2\u0007\u0010\u0084\u0001\u001a\u00020\u000fH\u0016¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J-\u0010\u0085\u0001\u001a\u00020\u000f2\u0007\u0010\u0087\u0001\u001a\u0002062\u0007\u0010\u0083\u0001\u001a\u00020\u000f2\u0007\u0010\u0084\u0001\u001a\u00020\u000fH\u0016¢\u0006\u0006\b\u0085\u0001\u0010\u0088\u0001J\u001b\u0010\u008a\u0001\u001a\u00020\u000f2\u0007\u0010\u0089\u0001\u001a\u000206H\u0016¢\u0006\u0006\b\u008a\u0001\u0010\u008b\u0001J$\u0010\u008a\u0001\u001a\u00020\u000f2\u0007\u0010\u0089\u0001\u001a\u0002062\u0007\u0010\u0083\u0001\u001a\u00020\u000fH\u0016¢\u0006\u0006\b\u008a\u0001\u0010\u008c\u0001J#\u0010\u008d\u0001\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u000f2\u0007\u0010\u0087\u0001\u001a\u000206H\u0016¢\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001J4\u0010\u008d\u0001\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u000f2\u0007\u0010\u0087\u0001\u001a\u0002062\u0007\u0010\u008f\u0001\u001a\u00020-2\u0006\u0010\u0010\u001a\u00020-H\u0016¢\u0006\u0006\b\u008d\u0001\u0010\u0090\u0001J\u0011\u0010\u0091\u0001\u001a\u00020\u0011H\u0016¢\u0006\u0005\b\u0091\u0001\u0010\u0006J\u0011\u0010\u0092\u0001\u001a\u00020\fH\u0016¢\u0006\u0005\b\u0092\u0001\u0010\u000eJ\u0011\u0010\u0093\u0001\u001a\u00020\u0011H\u0016¢\u0006\u0005\b\u0093\u0001\u0010\u0006J\u0013\u0010\u0095\u0001\u001a\u00030\u0094\u0001H\u0016¢\u0006\u0006\b\u0095\u0001\u0010\u0096\u0001J\u001f\u0010\u0099\u0001\u001a\u00020\f2\n\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0097\u0001H\u0096\u0002¢\u0006\u0006\b\u0099\u0001\u0010\u009a\u0001J\u0011\u0010\u009b\u0001\u001a\u00020-H\u0016¢\u0006\u0005\b\u009b\u0001\u0010/J\u0011\u0010\u009c\u0001\u001a\u00020DH\u0016¢\u0006\u0005\b\u009c\u0001\u0010FJ\u000f\u0010\u009d\u0001\u001a\u00020\u0000¢\u0006\u0005\b\u009d\u0001\u0010\u000bJ\u0011\u0010\u009e\u0001\u001a\u00020\u0000H\u0016¢\u0006\u0005\b\u009e\u0001\u0010\u000bJ\u000f\u0010\u009f\u0001\u001a\u000206¢\u0006\u0005\b\u009f\u0001\u00108J\u0018\u0010\u009f\u0001\u001a\u0002062\u0006\u0010\u0010\u001a\u00020-¢\u0006\u0006\b\u009f\u0001\u0010 \u0001J\u001f\u0010£\u0001\u001a\u00030¡\u00012\n\b\u0002\u0010¢\u0001\u001a\u00030¡\u0001H\u0007¢\u0006\u0006\b£\u0001\u0010¤\u0001R\u001b\u0010¥\u0001\u001a\u0004\u0018\u00010~8\u0000@\u0000X\u0081\u000e¢\u0006\b\n\u0006\b¥\u0001\u0010¦\u0001R0\u0010¨\u0001\u001a\u00020\u000f2\u0007\u0010§\u0001\u001a\u00020\u000f8\u0007@@X\u0086\u000e¢\u0006\u0016\n\u0006\b¨\u0001\u0010©\u0001\u001a\u0005\b¨\u0001\u0010\"\"\u0005\bª\u0001\u0010\u0013R\u0016\u0010¬\u0001\u001a\u00020\u00008VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b«\u0001\u0010\u000b¨\u0006®\u0001"}, d2 = {"Lokio/Buffer;", "Lokio/BufferedSource;", "Lokio/BufferedSink;", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/nio/channels/ByteChannel;", "<init>", "()V", "Ljava/io/OutputStream;", "outputStream", "()Ljava/io/OutputStream;", "emitCompleteSegments", "()Lokio/Buffer;", _UrlKt.FRAGMENT_ENCODE_SET, "exhausted", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "require", "(J)V", PluginsConstants.REQUEST, "(J)Z", "peek", "()Lokio/BufferedSource;", "Ljava/io/InputStream;", "inputStream", "()Ljava/io/InputStream;", "out", "offset", "copyTo", "(Lokio/Buffer;JJ)Lokio/Buffer;", "writeTo", "(Ljava/io/OutputStream;J)Lokio/Buffer;", "completeSegmentByteCount", "()J", _UrlKt.FRAGMENT_ENCODE_SET, "readByte", "()B", "pos", "getByte", "(J)B", "get", _UrlKt.FRAGMENT_ENCODE_SET, "readShort", "()S", _UrlKt.FRAGMENT_ENCODE_SET, "readInt", "()I", "readLong", "readShortLe", "readIntLe", "readLongLe", "readDecimalLong", "readHexadecimalUnsignedLong", "Lokio/ByteString;", "readByteString", "()Lokio/ByteString;", "(J)Lokio/ByteString;", "Lokio/Options;", "options", "select", "(Lokio/Options;)I", "sink", "readFully", "(Lokio/Buffer;J)V", "Lokio/Sink;", "readAll", "(Lokio/Sink;)J", _UrlKt.FRAGMENT_ENCODE_SET, "readUtf8", "()Ljava/lang/String;", "(J)Ljava/lang/String;", "Ljava/nio/charset/Charset;", "charset", "readString", "(Ljava/nio/charset/Charset;)Ljava/lang/String;", "(JLjava/nio/charset/Charset;)Ljava/lang/String;", "readUtf8LineStrict", "limit", "readUtf8CodePoint", _UrlKt.FRAGMENT_ENCODE_SET, "readByteArray", "()[B", "(J)[B", "([B)V", "read", "([BII)I", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;)I", "clear", "skip", "byteString", "write", "(Lokio/ByteString;)Lokio/Buffer;", "string", "writeUtf8", "(Ljava/lang/String;)Lokio/Buffer;", "beginIndex", "endIndex", "(Ljava/lang/String;II)Lokio/Buffer;", "codePoint", "writeUtf8CodePoint", "(I)Lokio/Buffer;", "writeString", "(Ljava/lang/String;Ljava/nio/charset/Charset;)Lokio/Buffer;", "(Ljava/lang/String;IILjava/nio/charset/Charset;)Lokio/Buffer;", "source", "([B)Lokio/Buffer;", "([BII)Lokio/Buffer;", "Lokio/Source;", "writeAll", "(Lokio/Source;)J", "(Lokio/Source;J)Lokio/Buffer;", "b", "writeByte", "s", "writeShort", "i", "writeInt", "writeIntLe", RegisterSpec.PREFIX, "writeLong", "(J)Lokio/Buffer;", "writeDecimalLong", "writeHexadecimalUnsignedLong", "minimumCapacity", "Lokio/Segment;", "writableSegment$okio", "(I)Lokio/Segment;", "writableSegment", "(Lokio/Buffer;J)J", "fromIndex", "toIndex", "indexOf", "(BJJ)J", "bytes", "(Lokio/ByteString;JJ)J", "targetBytes", "indexOfElement", "(Lokio/ByteString;)J", "(Lokio/ByteString;J)J", "rangeEquals", "(JLokio/ByteString;)Z", "bytesOffset", "(JLokio/ByteString;II)Z", "flush", "isOpen", "close", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", _UrlKt.FRAGMENT_ENCODE_SET, "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "copy", "clone", "snapshot", "(I)Lokio/ByteString;", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "readAndWriteUnsafe", "(Lokio/Buffer$UnsafeCursor;)Lokio/Buffer$UnsafeCursor;", "head", "Lokio/Segment;", "value", "size", "J", "setSize$okio", "getBuffer", "buffer", "UnsafeCursor", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBuffer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Buffer.kt\nokio/Buffer\n+ 2 Util.kt\nokio/-SegmentedByteString\n+ 3 Buffer.kt\nokio/internal/-Buffer\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 BufferedSource.kt\nokio/internal/-BufferedSource\n*L\n1#1,649:1\n88#2:650\n85#2:683\n85#2:685\n73#2:745\n73#2:771\n82#2:810\n76#2:821\n88#2:1014\n73#2:1029\n85#2:1133\n242#3,32:651\n277#3,10:686\n290#3,18:696\n412#3,2:714\n110#3:716\n414#3:717\n112#3,18:718\n311#3,9:736\n320#3,15:746\n338#3,10:761\n348#3,3:772\n346#3,25:775\n374#3,10:800\n384#3:811\n382#3,9:812\n391#3,7:822\n389#3,20:829\n652#3,60:849\n715#3,56:909\n773#3:965\n776#3:966\n777#3,6:968\n787#3,7:974\n797#3,6:984\n805#3,5:990\n837#3,6:995\n847#3:1001\n848#3,11:1003\n859#3,5:1015\n868#3,9:1020\n878#3,61:1030\n603#3:1091\n606#3:1092\n607#3,5:1094\n614#3:1099\n617#3,7:1100\n626#3,20:1107\n418#3:1127\n421#3,5:1128\n426#3,10:1134\n437#3,7:1144\n442#3,2:1151\n943#3:1153\n944#3,87:1155\n1034#3,48:1242\n573#3:1290\n580#3,21:1291\n1085#3,7:1312\n1095#3,7:1319\n1105#3,4:1326\n1112#3,8:1330\n1123#3,10:1338\n1136#3,14:1348\n447#3,35:1362\n513#3,40:1397\n556#3:1437\n558#3,13:1439\n1153#3:1452\n1204#3:1453\n1205#3,39:1455\n1246#3,2:1494\n1248#3,4:1497\n1255#3,3:1501\n1259#3,4:1505\n110#3:1509\n1263#3,22:1510\n112#3,18:1532\n1338#3,2:1550\n1341#3:1553\n110#3:1554\n1342#3,50:1555\n112#3,18:1605\n1401#3,12:1623\n1416#3,32:1635\n1451#3,12:1667\n1466#3,18:1679\n1488#3:1697\n1489#3:1699\n1494#3,34:1700\n1#4:684\n1#4:967\n1#4:1002\n1#4:1093\n1#4:1154\n1#4:1438\n1#4:1454\n1#4:1496\n1#4:1504\n1#4:1552\n1#4:1698\n26#5,3:981\n*S KotlinDebug\n*F\n+ 1 Buffer.kt\nokio/Buffer\n*L\n167#1:650\n197#1:683\n235#1:685\n261#1:745\n264#1:771\n267#1:810\n267#1:821\n337#1:1014\n340#1:1029\n376#1:1133\n181#1:651,32\n252#1:686,10\n255#1:696,18\n258#1:714,2\n258#1:716\n258#1:717\n258#1:718,18\n261#1:736,9\n261#1:746,15\n264#1:761,10\n264#1:772,3\n264#1:775,25\n267#1:800,10\n267#1:811\n267#1:812,9\n267#1:822,7\n267#1:829,20\n279#1:849,60\n282#1:909,56\n284#1:965\n287#1:966\n287#1:968,6\n289#1:974,7\n294#1:984,6\n297#1:990,5\n331#1:995,6\n337#1:1001\n337#1:1003,11\n337#1:1015,5\n340#1:1020,9\n340#1:1030,61\n342#1:1091\n345#1:1092\n345#1:1094,5\n347#1:1099\n350#1:1100,7\n353#1:1107,20\n373#1:1127\n376#1:1128,5\n376#1:1134,10\n378#1:1144,7\n381#1:1151,2\n386#1:1153\n386#1:1155,87\n389#1:1242,48\n412#1:1290\n418#1:1291,21\n439#1:1312,7\n443#1:1319,7\n445#1:1326,4\n447#1:1330,8\n451#1:1338,10\n455#1:1348,14\n459#1:1362,35\n462#1:1397,40\n465#1:1437\n465#1:1439,13\n467#1:1452\n467#1:1453\n467#1:1455,39\n469#1:1494,2\n469#1:1497,4\n480#1:1501,3\n480#1:1505,4\n480#1:1509\n480#1:1510,22\n480#1:1532,18\n496#1:1550,2\n496#1:1553\n496#1:1554\n496#1:1555,50\n496#1:1605,18\n506#1:1623,12\n576#1:1635,32\n578#1:1667,12\n586#1:1679,18\n594#1:1697\n594#1:1699\n596#1:1700,34\n287#1:967\n337#1:1002\n345#1:1093\n386#1:1154\n465#1:1438\n467#1:1454\n469#1:1496\n480#1:1504\n496#1:1552\n594#1:1698\n291#1:981,3\n*E\n"})
/* loaded from: classes.dex */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {

    @JvmField
    public Segment head;
    private long size;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0003R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R$\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0016\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\b\u0010\u001dR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010#\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b#\u0010\"¨\u0006$"}, d2 = {"Lokio/Buffer$UnsafeCursor;", "Ljava/io/Closeable;", "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "next", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "offset", "seek", "(J)I", "newSize", "resizeBuffer", "(J)J", _UrlKt.FRAGMENT_ENCODE_SET, "close", "Lokio/Buffer;", "buffer", "Lokio/Buffer;", _UrlKt.FRAGMENT_ENCODE_SET, "readWrite", "Z", "Lokio/Segment;", "segment", "Lokio/Segment;", "getSegment$okio", "()Lokio/Segment;", "setSegment$okio", "(Lokio/Segment;)V", "J", _UrlKt.FRAGMENT_ENCODE_SET, "data", "[B", "start", "I", "end", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nBuffer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Buffer.kt\nokio/Buffer$UnsafeCursor\n+ 2 Buffer.kt\nokio/internal/-Buffer\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,649:1\n1549#2:650\n1550#2:652\n1554#2:653\n1555#2,68:655\n1626#2:723\n1627#2,32:725\n1659#2,18:758\n1680#2:776\n1681#2,18:778\n1703#2:796\n1705#2,7:798\n1#3:651\n1#3:654\n1#3:724\n1#3:777\n1#3:797\n85#4:757\n*S KotlinDebug\n*F\n+ 1 Buffer.kt\nokio/Buffer$UnsafeCursor\n*L\n636#1:650\n636#1:652\n638#1:653\n638#1:655,68\n640#1:723\n640#1:725,32\n640#1:758,18\n642#1:776\n642#1:778,18\n645#1:796\n645#1:798,7\n636#1:651\n638#1:654\n640#1:724\n642#1:777\n645#1:797\n640#1:757\n*E\n"})
    public static final class UnsafeCursor implements Closeable {

        @JvmField
        public Buffer buffer;

        @JvmField
        public byte[] data;

        @JvmField
        public int end;

        @JvmField
        public long offset;

        @JvmField
        public boolean readWrite;
        private Segment segment;

        @JvmField
        public int start;

        public UnsafeCursor() {
            this.offset = -1;
            this.start = -1;
            this.end = -1;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.buffer == null) goto L6;
            this.buffer = null;
            setSegment$okio(null);
            this.offset = -1;
            this.data = null;
            this.start = -1;
            this.end = -1;
            return;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("not attached to a buffer");
        }

        public final Segment getSegment$okio() {
            return this.segment;
        }

        public final int next() {
            if (this.offset == this.buffer.size()) goto L10;
            long r0 = this.offset;
            if (r0 != (-1)) goto L9;
            return seek(0);
        L9:
            return seek(r0 + (this.end - this.start));
        L10:
            Segment$$ExternalSyntheticBUOutline1.m("no more bytes");
            return 0;
        }

        public final long resizeBuffer(long r14) {
            Buffer r0 = this.buffer;
            if (r0 != null) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("not attached to a buffer");
            return 0;
        L5:
            if (this.readWrite == false) goto L29;
            long r3 = r0.size();
            if (r14 <= r3) goto L9;
            if (r14 <= r3) goto L27;
            long r5 = r14 - r3;
            boolean r8 = true;
        L23:
            if (r5 <= 0) goto L27;
            Segment r9 = r0.writableSegment$okio(1);
            int r10 = (int) Math.min(r5, 8192 - r9.limit);
            r9.limit += r10;
            r5 = r5 - r10;
            if (r8 == false) goto L23;
            setSegment$okio(r9);
            this.offset = r3;
            this.data = r9.data;
            int r82 = r9.limit;
            this.start = r82 - r10;
            this.end = r82;
            r8 = false;
        L27:
            r0.setSize$okio(r14);
            return r3;
        L9:
            if (r14 < 0) goto L18;
            long r52 = r3 - r14;
        L12:
            if (r52 <= 0) goto L17;
            Segment r7 = r0.head.prev;
            int r83 = r7.limit;
            long r92 = r83 - r7.pos;
            if (r92 > r52) goto L16;
            r0.head = r7.pop();
            SegmentPool.recycle(r7);
            r52 = r52 - r92;
            goto L12
        L16:
            r7.limit = r83 - ((int) r52);
        L17:
            setSegment$okio(null);
            this.offset = r14;
            this.data = null;
            this.start = -1;
            this.end = -1;
            goto L27
        L18:
            Buffer$$ExternalSyntheticBUOutline3.m("newSize < 0: ", r14);
            return 0;
        L29:
            Segment$$ExternalSyntheticBUOutline1.m("resizeBuffer() only permitted for read/write buffers");
            return 0;
        }

        public final int seek(long r12) {
            Buffer r0 = this.buffer;
            if (r0 != null) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("not attached to a buffer");
            return 0;
        L5:
            if (r12 < (-1)) goto L41;
            if (r12 > r0.size()) goto L41;
            if (r12 != (-1)) goto L10;
        L38:
            setSegment$okio(null);
            this.offset = r12;
            this.data = null;
            this.start = -1;
            this.end = -1;
            return -1;
        L10:
            if (r12 == r0.size()) goto L38;
            long r1 = r0.size();
            Segment r3 = r0.head;
            long r5 = 0;
            if (getSegment$okio() == null) goto L18;
            long r7 = this.offset - (this.start - getSegment$okio().pos);
            if (r7 <= r12) goto L17;
            Segment r4 = r3;
            r3 = getSegment$okio();
            r1 = r7;
        L20:
            if ((r1 - r12) <= (r12 - r5)) goto L25;
        L21:
            int r13 = r4.limit;
            int r2 = r4.pos;
            if (r12 < ((r13 - r2) + r5)) goto L29;
            r5 = r5 + (r13 - r2);
            r4 = r4.next;
        L29:
            if (this.readWrite == true) goto L31;
        L36:
            setSegment$okio(r4);
            this.offset = r12;
            this.data = r4.data;
            int r02 = r4.pos + ((int) (r12 - r5));
            this.start = r02;
            int r122 = r4.limit;
            this.end = r122;
            return r122 - r02;
        L31:
            if (r4.shared == false) goto L36;
            Segment r14 = r4.unsharedCopy();
            if (r0.head != r4) goto L35;
            r0.head = r14;
        L35:
            r4 = r4.push(r14);
            r4.prev.pop();
        L25:
            if (r1 <= r12) goto L27;
            r3 = r3.prev;
            r1 = r1 - (r3.limit - r3.pos);
            goto L25
        L27:
            r5 = r1;
            r4 = r3;
            goto L29
        L17:
            r4 = getSegment$okio();
            r5 = r7;
            goto L20
        L18:
            r4 = r3;
        L41:
            throw new ArrayIndexOutOfBoundsException("offset=" + r12 + " > size=" + r0.size());
        }

        public final void setSegment$okio(Segment r1) {
            this.segment = r1;
        }
    }

    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"okio/Buffer$inputStream$1", "Ljava/io/InputStream;", "read", _UrlKt.FRAGMENT_ENCODE_SET, "sink", _UrlKt.FRAGMENT_ENCODE_SET, "offset", "byteCount", "available", "close", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nBuffer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Buffer.kt\nokio/Buffer$inputStream$1\n+ 2 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,649:1\n73#2:650\n85#2:651\n*S KotlinDebug\n*F\n+ 1 Buffer.kt\nokio/Buffer$inputStream$1\n*L\n126#1:650\n136#1:651\n*E\n"})
    /* renamed from: okio.Buffer$inputStream$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1 extends InputStream implements InputStreamRetargetInterface {
        final /* synthetic */ Buffer this$0;

        public AnonymousClass1(Buffer r1) {
            this.this$0 = r1;
        }

        @Override // java.io.InputStream
        public int available() {
            return (int) Math.min(this.this$0.size(), 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.this$0.size() > 0) goto L5;
            return -1;
        L5:
            return this.this$0.readByte() & UByte.MAX_VALUE;
        }

        @Override // java.io.InputStream
        public int read(byte[] r1, int r2, int r3) {
            return this.this$0.read(r1, r2, r3);
        }

        public String toString() {
            return this.this$0 + ".inputStream()";
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream r1) {
            return DesugarInputStream.transferTo(this, r1);
        }
    }

    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"okio/Buffer$outputStream$1", "Ljava/io/OutputStream;", "write", _UrlKt.FRAGMENT_ENCODE_SET, "b", _UrlKt.FRAGMENT_ENCODE_SET, "data", _UrlKt.FRAGMENT_ENCODE_SET, "offset", "byteCount", "flush", "close", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: okio.Buffer$outputStream$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes5.dex */
    public static final class C01921 extends OutputStream {
        final /* synthetic */ Buffer this$0;

        public C01921(Buffer r1) {
            this.this$0 = r1;
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() {
        }

        public String toString() {
            return this.this$0 + ".outputStream()";
        }

        @Override // java.io.OutputStream
        public void write(int r1) {
            this.this$0.writeByte(r1);
        }

        @Override // java.io.OutputStream
        public void write(byte[] r1, int r2, int r3) {
            this.this$0.write(r1, r2, r3);
        }
    }

    public Buffer() {
    }

    public static /* synthetic */ UnsafeCursor readAndWriteUnsafe$default(Buffer r0, UnsafeCursor r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = SegmentedByteString.getDEFAULT__new_UnsafeCursor();
    L6:
        return r0.readAndWriteUnsafe(r1);
    }

    public static /* synthetic */ Buffer writeTo$default(Buffer r0, OutputStream r1, long r2, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = r0.size;
    L6:
        return r0.writeTo(r1, r2);
    }

    public final void clear() {
        skip(size());
    }

    public /* bridge */ /* synthetic */ Object clone() {
        return m3757clone();
    }

    /* renamed from: clone, reason: collision with other method in class */
    public Buffer m3757clone() {
        return copy();
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public final long completeSegmentByteCount() {
        long r0 = size();
        if (r0 != 0) goto L5;
        return 0;
    L5:
        Segment r5 = this.head.prev;
        if (r5.limit < 8192) goto L8;
        return r0;
    L8:
        if (r5.owner == true) goto L10;
        return r0;
    L10:
        return r0 - (r2 - r5.pos);
    }

    public final Buffer copy() {
        Buffer r0 = new Buffer();
        if (size() != 0) goto L5;
        return r0;
    L5:
        Segment r1 = this.head;
        Segment r2 = r1.sharedCopy();
        r0.head = r2;
        r2.prev = r2;
        r2.next = r2;
        Segment r3 = r1.next;
    L6:
        if (r3 == r1) goto L8;
        r2.prev.push(r3.sharedCopy());
        r3 = r3.next;
        goto L6
    L8:
        r0.setSize$okio(size());
        return r0;
    }

    public final Buffer copyTo(Buffer r9, long r10, long r12) {
        long r2 = r10;
        SegmentedByteString.checkOffsetAndCount(size(), r2, r12);
        if (r12 == 0) goto L17;
        r9.setSize$okio(r9.size() + r12);
        Segment r122 = this.head;
    L6:
        int r13 = r122.limit;
        int r0 = r122.pos;
        if (r2 < (r13 - r0)) goto L9;
        r2 = r2 - (r13 - r0);
        r122 = r122.next;
        goto L6
    L9:
        Segment r02 = r122;
        long r123 = r12;
    L11:
        if (r123 <= 0) goto L17;
        Segment r1 = r02.sharedCopy();
        int r4 = r1.pos + ((int) r2);
        r1.pos = r4;
        r1.limit = Math.min(r4 + ((int) r123), r1.limit);
        Segment r22 = r9.head;
        if (r22 != null) goto L15;
        r1.prev = r1;
        r1.next = r1;
        r9.head = r1;
    L16:
        r123 = r123 - (r1.limit - r1.pos);
        r02 = r02.next;
        r2 = 0;
        goto L11
    L15:
        r22.prev.push(r1);
    L17:
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer emitCompleteSegments() {
        return this;
    }

    @Override // okio.BufferedSink
    public /* bridge */ /* synthetic */ BufferedSink emitCompleteSegments() {
        return emitCompleteSegments();
    }

    public boolean equals(Object r19) {
        if (this != r19) goto L6;
        return true;
    L6:
        if ((r19 instanceof Buffer) == true) goto L8;
        return false;
    L8:
        Buffer r1 = (Buffer) r19;
        if (size() == r1.size()) goto L12;
        return false;
    L12:
        if (size() != 0) goto L14;
        return true;
    L14:
        Segment r3 = this.head;
        Segment r12 = r1.head;
        int r5 = r3.pos;
        int r6 = r12.pos;
        long r9 = 0;
    L16:
        if (r9 >= size()) goto L31;
        long r11 = Math.min(r3.limit - r5, r12.limit - r6);
        long r13 = 0;
    L19:
        if (r13 >= r11) goto L25;
        int r16 = r5 + 1;
        int r17 = r6 + 1;
        if (r3.data[r5] != r12.data[r6]) goto L22;
        r13 = r13 + 1;
        r5 = r16;
        r6 = r17;
        goto L19
    L22:
        return false;
    L25:
        if (r5 != r3.limit) goto L28;
        r3 = r3.next;
        r5 = r3.pos;
    L28:
        if (r6 != r12.limit) goto L30;
        r12 = r12.next;
        r6 = r12.pos;
    L30:
        r9 = r9 + r11;
        goto L16
    L31:
        return true;
    }

    @Override // okio.BufferedSource
    public boolean exhausted() {
        if (this.size != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer getBuffer() {
        return this;
    }

    @JvmName(name = "getByte")
    public final byte getByte(long r7) {
        SegmentedByteString.checkOffsetAndCount(size(), r7, 1);
        Segment r72 = this.head;
        r72.getClass();
        if ((size() - r7) >= r7) goto L10;
        long r0 = size();
    L6:
        if (r0 <= r7) goto L9;
        r72 = r72.prev;
        r0 = r0 - (r72.limit - r72.pos);
        goto L6
    L9:
        return r72.data[(int) ((r72.pos + r7) - r0)];
    L10:
        long r02 = 0;
    L11:
        int r6 = r72.limit;
        int r8 = r72.pos;
        long r4 = (r6 - r8) + r02;
        if (r4 > r7) goto L15;
        r72 = r72.next;
        r02 = r4;
        goto L11
    L15:
        return r72.data[(int) ((r8 + r7) - r02)];
    }

    public int hashCode() {
        Segment r0 = this.head;
        if (r0 != null) goto L6;
        return 0;
    L6:
        int r1 = 1;
    L7:
        int r2 = r0.pos;
        int r3 = r0.limit;
    L8:
        if (r2 >= r3) goto L10;
        r1 = (r1 * 31) + r0.data[r2];
        r2 = r2 + 1;
        goto L8
    L10:
        r0 = r0.next;
        if (r0 != this.head) goto L7;
        return r1;
    }

    public long indexOf(byte r10, long r11, long r13) {
        long r0 = 0;
        if (0 > r11) goto L46;
        if (r11 > r13) goto L46;
        if (r13 <= size()) goto L10;
        r13 = size();
    L10:
        if (r11 != r13) goto L12;
        return -1;
    L12:
        Segment r2 = this.head;
        if (r2 != null) goto L16;
        return -1;
    L16:
        if ((size() - r11) >= r11) goto L32;
        r0 = size();
    L19:
        if (r0 <= r11) goto L22;
        r2 = r2.prev;
        r0 = r0 - (r2.limit - r2.pos);
    L22:
        if (r0 >= r13) goto L31;
        byte[] r9 = r2.data;
        int r5 = (int) Math.min(r2.limit, (r2.pos + r13) - r0);
        int r112 = (int) ((r2.pos + r11) - r0);
    L24:
        if (r112 >= r5) goto L30;
        if (r9[r112] == r10) goto L28;
        r112 = r112 + 1;
    L28:
        return (r112 - r2.pos) + r0;
    L30:
        r0 = r0 + (r2.limit - r2.pos);
        r2 = r2.next;
        r11 = r0;
        goto L22
    L31:
        return -1;
    L32:
        long r52 = (r2.limit - r2.pos) + r0;
        if (r52 > r11) goto L36;
        r2 = r2.next;
        r0 = r52;
    L36:
        if (r0 >= r13) goto L44;
        byte[] r92 = r2.data;
        int r53 = (int) Math.min(r2.limit, (r2.pos + r13) - r0);
        r112 = (int) ((r2.pos + r11) - r0);
    L38:
        if (r112 >= r53) goto L43;
        if (r92[r112] == r10) goto L28;
        r112 = r112 + 1;
        goto L38
    L43:
        r0 = r0 + (r2.limit - r2.pos);
        r2 = r2.next;
        r11 = r0;
        goto L36
    L44:
        return -1;
    L46:
        throw new IllegalArgumentException(("size=" + size() + " fromIndex=" + r11 + " toIndex=" + r13).toString());
    }

    @Override // okio.BufferedSource
    public long indexOf(ByteString r11, long r12, long r14) {
        return okio.internal.Buffer.commonIndexOf$default(this, r11, r12, r14, 0, 0, 24, null);
    }

    public long indexOfElement(ByteString r3) {
        return indexOfElement(r3, 0);
    }

    public long indexOfElement(ByteString r12, long r13) {
        long r0 = 0;
        if (r13 < 0) goto L73;
        Segment r2 = this.head;
        if (r2 != null) goto L8;
        return -1;
    L8:
        if ((size() - r13) >= r13) goto L43;
        r0 = size();
    L11:
        if (r0 <= r13) goto L14;
        r2 = r2.prev;
        r0 = r0 - (r2.limit - r2.pos);
        goto L11
    L14:
        if (r12.size() != 2) goto L29;
        byte r5 = r12.getByte(0);
        byte r122 = r12.getByte(1);
    L17:
        if (r0 >= size()) goto L42;
        byte[] r6 = r2.data;
        int r132 = (int) ((r2.pos + r13) - r0);
        int r14 = r2.limit;
    L19:
        if (r132 >= r14) goto L28;
        byte r7 = r6[r132];
        if (r7 == r5) goto L25;
        if (r7 == r122) goto L25;
        r132 = r132 + 1;
    L25:
        int r11 = r2.pos;
    L27:
        return (r132 - r11) + r0;
    L28:
        r0 = r0 + (r2.limit - r2.pos);
        r2 = r2.next;
        r13 = r0;
    L42:
        return -1;
    L29:
        byte[] r123 = r12.internalArray$okio();
    L31:
        if (r0 >= size()) goto L42;
        byte[] r52 = r2.data;
        r132 = (int) ((r2.pos + r13) - r0);
        int r142 = r2.limit;
    L33:
        if (r132 >= r142) goto L41;
        byte r62 = r52[r132];
        int r8 = r123.length;
        int r9 = 0;
    L35:
        if (r9 >= r8) goto L40;
        if (r62 == r123[r9]) goto L38;
        r9 = r9 + 1;
    L38:
        r11 = r2.pos;
        goto L27
    L40:
        r132 = r132 + 1;
        goto L33
    L41:
        r0 = r0 + (r2.limit - r2.pos);
        r2 = r2.next;
        r13 = r0;
    L43:
        long r92 = (r2.limit - r2.pos) + r0;
        if (r92 > r13) goto L47;
        r2 = r2.next;
        r0 = r92;
        goto L43
    L47:
        if (r12.size() != 2) goto L59;
        byte r53 = r12.getByte(0);
        byte r124 = r12.getByte(1);
    L50:
        if (r0 >= size()) goto L72;
        byte[] r63 = r2.data;
        r132 = (int) ((r2.pos + r13) - r0);
        int r143 = r2.limit;
    L52:
        if (r132 >= r143) goto L58;
        byte r72 = r63[r132];
        if (r72 == r53) goto L25;
        if (r72 == r124) goto L25;
        r132 = r132 + 1;
        goto L52
    L58:
        r0 = r0 + (r2.limit - r2.pos);
        r2 = r2.next;
        r13 = r0;
    L72:
        return -1;
    L59:
        byte[] r125 = r12.internalArray$okio();
    L61:
        if (r0 >= size()) goto L72;
        byte[] r54 = r2.data;
        r132 = (int) ((r2.pos + r13) - r0);
        int r144 = r2.limit;
    L63:
        if (r132 >= r144) goto L71;
        byte r64 = r54[r132];
        int r82 = r125.length;
        int r93 = 0;
    L65:
        if (r93 >= r82) goto L70;
        if (r64 == r125[r93]) goto L38;
        r93 = r93 + 1;
        goto L65
    L70:
        r132 = r132 + 1;
        goto L63
    L71:
        r0 = r0 + (r2.limit - r2.pos);
        r2 = r2.next;
        r13 = r0;
        goto L61
    L73:
        Buffer$$ExternalSyntheticBUOutline3.m("fromIndex < 0: ", r13);
        return 0;
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new AnonymousClass1(this);
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @Override // okio.BufferedSink
    public OutputStream outputStream() {
        return new C01921(this);
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
        if (r15 >= 0) goto L6;
        return false;
    L6:
        if (r11 >= 0) goto L8;
    L20:
        return false;
    L8:
        if ((r15 + r11) > size()) goto L20;
        if (r14 < 0) goto L20;
        if ((r14 + r15) > r13.size()) goto L20;
        if (r15 != 0) goto L18;
        return true;
    L18:
        if (okio.internal.Buffer.commonIndexOf(this, r13, r11, r11 + 1, r14, r15) == (-1)) goto L20;
        return true;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer r7) {
        Segment r0 = this.head;
        if (r0 != null) goto L6;
        return -1;
    L6:
        int r1 = Math.min(r7.remaining(), r0.limit - r0.pos);
        r7.put(r0.data, r0.pos, r1);
        int r72 = r0.pos + r1;
        r0.pos = r72;
        this.size -= r1;
        if (r72 != r0.limit) goto L9;
        this.head = r0.pop();
        SegmentPool.recycle(r0);
    L9:
        return r1;
    }

    public int read(byte[] r8, int r9, int r10) {
        SegmentedByteString.checkOffsetAndCount(r8.length, r9, r10);
        Segment r0 = this.head;
        if (r0 != null) goto L6;
        return -1;
    L6:
        int r102 = Math.min(r10, r0.limit - r0.pos);
        byte[] r1 = r0.data;
        int r2 = r0.pos;
        ArraysKt.copyInto(r1, r8, r9, r2, r2 + r102);
        r0.pos += r102;
        setSize$okio(size() - r102);
        if (r0.pos != r0.limit) goto L9;
        this.head = r0.pop();
        SegmentPool.recycle(r0);
    L9:
        return r102;
    }

    @Override // okio.Source
    public long read(Buffer r5, long r6) {
        if (r6 >= 0) goto L5;
        Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r6);
        return 0;
    L5:
        if (size() != 0) goto L9;
        return -1;
    L9:
        if (r6 <= size()) goto L11;
        r6 = size();
    L11:
        r5.write(this, r6);
        return r6;
    }

    @Override // okio.BufferedSource
    public long readAll(Sink r5) {
        long r0 = size();
        if (r0 <= 0) goto L5;
        r5.write(this, r0);
    L5:
        return r0;
    }

    @JvmOverloads
    public final UnsafeCursor readAndWriteUnsafe(UnsafeCursor r1) {
        return okio.internal.Buffer.commonReadAndWriteUnsafe(this, r1);
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        if (size() == 0) goto L10;
        Segment r0 = this.head;
        int r1 = r0.pos;
        int r2 = r0.limit;
        int r4 = r1 + 1;
        byte r12 = r0.data[r1];
        setSize$okio(size() - 1);
        if (r4 != r2) goto L8;
        this.head = r0.pop();
        SegmentPool.recycle(r0);
        return r12;
    L8:
        r0.pos = r4;
        return r12;
    L10:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray() {
        return readByteArray(size());
    }

    public byte[] readByteArray(long r3) {
        if (r3 >= 0) goto L5;
    L13:
        Buffer$$ExternalSyntheticBUOutline3.m("byteCount: ", r3);
        return null;
    L5:
        if (r3 > 2147483647L) goto L13;
        if (size() < r3) goto L10;
        byte[] r32 = new byte[(int) r3];
        readFully(r32);
        return r32;
    L10:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return null;
    }

    @Override // okio.BufferedSource
    public ByteString readByteString() {
        return readByteString(size());
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long r3) {
        if (r3 >= 0) goto L5;
    L17:
        Buffer$$ExternalSyntheticBUOutline3.m("byteCount: ", r3);
        return null;
    L5:
        if (r3 > 2147483647L) goto L17;
        if (size() >= r3) goto L9;
        Buffer$$ExternalSyntheticBUOutline1.m();
        return null;
    L9:
        if (r3 < MediaStatus.COMMAND_EDIT_TRACKS) goto L13;
        ByteString r0 = snapshot((int) r3);
        skip(r3);
        return r0;
    L13:
        return new ByteString(readByteArray(r3));
    }

    @Override // okio.BufferedSource
    public long readDecimalLong() {
        long r3 = 0;
        if (size() == 0) goto L56;
        int r1 = 0;
        boolean r2 = false;
        long r8 = 0;
        long r6 = -7;
        boolean r5 = false;
    L5:
        Segment r10 = this.head;
        byte[] r11 = r10.data;
        int r12 = r10.pos;
        int r13 = r10.limit;
    L6:
        if (r12 >= r13) goto L29;
        byte r15 = r11[r12];
        if (r15 >= 48) goto L10;
    L23:
        long r17 = r3;
        if (r15 != 45) goto L28;
        if (r1 != 0) goto L28;
        r6 = r6 - 1;
        r2 = true;
    L27:
        r12 = r12 + 1;
        r1 = r1 + 1;
        r3 = r17;
    L28:
        r5 = true;
    L30:
        if (r12 != r13) goto L32;
        this.head = r10.pop();
        SegmentPool.recycle(r10);
    L33:
        if (r5 == true) goto L38;
        if (this.head == null) goto L38;
        r3 = r17;
    L38:
        setSize$okio(size() - r1);
        if (r2 == false) goto L41;
        int r14 = 2;
    L42:
        if (r1 < r14) goto L44;
        if (r2 == false) goto L55;
        return r8;
    L55:
        return -r8;
    L44:
        if (size() == r17) goto L50;
        if (r2 == false) goto L47;
        String r16 = "Expected a digit";
    L49:
        throw new NumberFormatException(r16 + " but was 0x" + SegmentedByteString.toHexString(getByte(r17)));
    L47:
        r16 = "Expected a digit or '-'";
        goto L49
    L50:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    L41:
        r14 = 1;
        goto L42
    L32:
        r10.pos = r12;
        goto L33
    L10:
        if (r15 > 57) goto L23;
        int r142 = 48 - r15;
        r17 = r3;
        if (r8 < (-922337203685477580L)) goto L18;
        if (r8 == (-922337203685477580L)) goto L15;
    L17:
        r8 = (r8 * 10) + r142;
        goto L27
    L15:
        if (r142 >= r6) goto L17;
    L18:
        Buffer r0 = new Buffer().writeDecimalLong(r8).writeByte(r15);
        if (r2 == true) goto L21;
        r0.readByte();
    L21:
        Buffer$$ExternalSyntheticBUOutline0.m("Number too large: ", r0.readUtf8());
        return r17;
    L29:
        r17 = r3;
        goto L30
    L56:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer r3, long r4) {
        if (size() < r4) goto L6;
        r3.write(this, r4);
        return;
    L6:
        r3.write(this, size());
        Buffer$$ExternalSyntheticBUOutline1.m();
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] r4) {
        int r0 = 0;
    L4:
        if (r0 >= r4.length) goto L12;
        int r1 = read(r4, r0, r4.length - r0);
        if (r1 == (-1)) goto L8;
        r0 = r0 + r1;
        goto L4
    L8:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return;
    }

    @Override // okio.BufferedSource
    public long readHexadecimalUnsignedLong() {
        if (size() == 0) goto L39;
        int r0 = 0;
        boolean r1 = false;
        long r4 = 0;
    L5:
        Segment r6 = this.head;
        byte[] r7 = r6.data;
        int r8 = r6.pos;
        int r9 = r6.limit;
    L6:
        if (r8 >= r9) goto L31;
        byte r10 = r7[r8];
        if (r10 < 48) goto L13;
        if (r10 > 57) goto L13;
        int r11 = r10 - 48;
    L23:
        if (((-1152921504606846976L) & r4) != 0) goto L25;
        r4 = (r4 << 4) | r11;
        r8 = r8 + 1;
        r0 = r0 + 1;
        goto L6
    L25:
        Buffer$$ExternalSyntheticBUOutline0.m("Number too large: ", new Buffer().writeHexadecimalUnsignedLong(r4).writeByte(r10).readUtf8());
        return 0;
    L13:
        if (r10 < 97) goto L18;
        if (r10 > 102) goto L18;
        r11 = r10 - 87;
    L18:
        if (r10 < 65) goto L27;
        if (r10 > 70) goto L27;
        r11 = r10 - 55;
    L27:
        if (r0 == 0) goto L29;
        r1 = true;
        goto L31
    L29:
        Buffer$$ExternalSyntheticBUOutline0.m("Expected leading [0-9a-fA-F] character but was 0x", SegmentedByteString.toHexString(r10));
        return 0;
    L31:
        if (r8 != r9) goto L33;
        this.head = r6.pop();
        SegmentPool.recycle(r6);
    L34:
        if (r1 == true) goto L37;
        if (this.head != null) goto L5;
    L37:
        setSize$okio(size() - r0);
        return r4;
    L33:
        r6.pos = r8;
        goto L34
    L39:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    }

    @Override // okio.BufferedSource
    public int readInt() {
        if (size() < 4) goto L14;
        Segment r0 = this.head;
        int r1 = r0.pos;
        int r4 = r0.limit;
        if ((r4 - r1) < 4) goto L7;
        byte[] r5 = r0.data;
        int r6 = ((r5[r1 + 1] & UByte.MAX_VALUE) << 16) | ((r5[r1] & UByte.MAX_VALUE) << 24);
        int r7 = r1 + 3;
        int r62 = r6 | ((r5[r1 + 2] & UByte.MAX_VALUE) << 8);
        int r12 = r1 + 4;
        int r52 = (r5[r7] & UByte.MAX_VALUE) | r62;
        setSize$okio(size() - 4);
        if (r12 != r4) goto L12;
        this.head = r0.pop();
        SegmentPool.recycle(r0);
        return r52;
    L12:
        r0.pos = r12;
        return r52;
    L7:
        return (readByte() & UByte.MAX_VALUE) | ((((readByte() & UByte.MAX_VALUE) << 24) | ((readByte() & UByte.MAX_VALUE) << 16)) | ((readByte() & UByte.MAX_VALUE) << 8));
    L14:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    }

    @Override // okio.BufferedSource
    public int readIntLe() {
        return SegmentedByteString.reverseBytes(readInt());
    }

    @Override // okio.BufferedSource
    public long readLong() {
        if (size() < 8) goto L14;
        Segment r0 = this.head;
        int r1 = r0.pos;
        int r4 = r0.limit;
        if ((r4 - r1) < 8) goto L7;
        byte[] r5 = r0.data;
        int r8 = r1 + 7;
        long r6 = (((((((r5[r1] & 255) << 56) | ((r5[r1 + 1] & 255) << 48)) | ((r5[r1 + 2] & 255) << 40)) | ((r5[r1 + 3] & 255) << 32)) | ((r5[r1 + 4] & 255) << 24)) | ((r5[r1 + 5] & 255) << 16)) | ((r5[r1 + 6] & 255) << 8);
        int r12 = r1 + 8;
        long r52 = r6 | (r5[r8] & 255);
        setSize$okio(size() - 8);
        if (r12 != r4) goto L12;
        this.head = r0.pop();
        SegmentPool.recycle(r0);
        return r52;
    L12:
        r0.pos = r12;
        return r52;
    L7:
        return ((readInt() & 4294967295L) << 32) | (4294967295L & readInt());
    L14:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    }

    @Override // okio.BufferedSource
    public long readLongLe() {
        return SegmentedByteString.reverseBytes(readLong());
    }

    @Override // okio.BufferedSource
    public short readShort() {
        if (size() < 2) goto L14;
        Segment r0 = this.head;
        int r1 = r0.pos;
        int r4 = r0.limit;
        if ((r4 - r1) >= 2) goto L8;
        int r02 = (readByte() & UByte.MAX_VALUE) << 8;
        return (short) ((readByte() & UByte.MAX_VALUE) | r02);
    L8:
        byte[] r5 = r0.data;
        int r7 = r1 + 1;
        int r8 = (r5[r1] & UByte.MAX_VALUE) << 8;
        int r12 = r1 + 2;
        int r52 = (r5[r7] & UByte.MAX_VALUE) | r8;
        setSize$okio(size() - 2);
        if (r12 != r4) goto L11;
        this.head = r0.pop();
        SegmentPool.recycle(r0);
    L13:
        return (short) r52;
    L11:
        r0.pos = r12;
        goto L13
    L14:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    }

    @Override // okio.BufferedSource
    public short readShortLe() {
        return SegmentedByteString.reverseBytes(readShort());
    }

    public String readString(long r7, Charset r9) {
        if (r7 >= 0) goto L5;
    L21:
        Buffer$$ExternalSyntheticBUOutline3.m("byteCount: ", r7);
        return null;
    L5:
        if (r7 > 2147483647L) goto L21;
        if (this.size < r7) goto L19;
        if (r7 != 0) goto L11;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        Segment r0 = this.head;
        int r1 = r0.pos;
        if ((r1 + r7) > r0.limit) goto L14;
        int r4 = (int) r7;
        String r2 = new String(r0.data, r1, r4, r9);
        int r92 = r0.pos + r4;
        r0.pos = r92;
        this.size -= r7;
        if (r92 != r0.limit) goto L18;
        this.head = r0.pop();
        SegmentPool.recycle(r0);
    L18:
        return r2;
    L14:
        return new String(readByteArray(r7), r9);
    L19:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return null;
    }

    @Override // okio.BufferedSource
    public String readString(Charset r3) {
        return readString(this.size, r3);
    }

    public String readUtf8() {
        return readString(this.size, Charsets.UTF_8);
    }

    @Override // okio.BufferedSource
    public String readUtf8(long r2) {
        return readString(r2, Charsets.UTF_8);
    }

    @Override // okio.BufferedSource
    public int readUtf8CodePoint() {
        if (size() == 0) goto L39;
        byte r0 = getByte(0);
        int r3 = 1;
        if ((r0 & ByteCompanionObject.MIN_VALUE) != 0) goto L8;
        int r2 = r0 & ByteCompanionObject.MAX_VALUE;
        int r6 = 0;
        int r1 = 1;
    L16:
        long r9 = r1;
        if (size() < r9) goto L36;
    L18:
        if (r3 >= r1) goto L24;
        long r7 = r3;
        byte r02 = getByte(r7);
        if ((r02 & 192) != 128) goto L22;
        r2 = (r2 << 6) | (r02 & 63);
        r3 = r3 + 1;
        goto L18
    L22:
        skip(r7);
        return 65533;
    L24:
        skip(r9);
        if (r2 <= 1114111) goto L28;
        return 65533;
    L28:
        if (55296 <= r2) goto L30;
    L32:
        if (r2 >= r6) goto L34;
        return 65533;
    L34:
        return r2;
    L30:
        if (r2 >= 57344) goto L32;
        return 65533;
    L36:
        throw new EOFException("size < " + r1 + ": " + size() + " (to read code point prefixed 0x" + SegmentedByteString.toHexString(r0) + ')');
    L8:
        if ((r0 & 224) != 192) goto L11;
        r2 = r0 & 31;
        r1 = 2;
        r6 = 128;
        goto L16
    L11:
        if ((r0 & 240) != 224) goto L14;
        r2 = r0 & 15;
        r1 = 3;
        r6 = 2048;
        goto L16
    L14:
        if ((r0 & 248) != 240) goto L37;
        r2 = r0 & 7;
        r1 = 4;
        r6 = 65536;
        goto L16
    L37:
        skip(1);
        return 65533;
    L39:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() {
        return readUtf8LineStrict(LongCompanionObject.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long r11) {
        if (r11 < 0) goto L22;
        long r0 = LongCompanionObject.MAX_VALUE;
        if (r11 != LongCompanionObject.MAX_VALUE) goto L7;
    L6:
        long r4 = r0;
        long r1 = indexOf((byte) 10, 0, r4);
        if (r1 == (-1)) goto L13;
        return okio.internal.Buffer.readUtf8Line(this, r1);
    L13:
        if (r4 < size()) goto L15;
    L20:
        Buffer r12 = new Buffer();
        copyTo(r12, 0, Math.min(32, size()));
        throw new EOFException("\\n not found: limit=" + Math.min(size(), r11) + " content=" + r12.readByteString().hex() + Typography.ellipsis);
    L15:
        if (getByte(r4 - 1) != 13) goto L20;
        if (getByte(r4) != 10) goto L20;
        return okio.internal.Buffer.readUtf8Line(this, r4);
    L7:
        r0 = r11 + 1;
        goto L6
    L22:
        Buffer$$ExternalSyntheticBUOutline3.m("limit < 0: ", r11);
        return null;
    }

    @Override // okio.BufferedSource
    public boolean request(long r3) {
        if (this.size < r3) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // okio.BufferedSource
    public void require(long r3) {
        if (this.size < r3) goto L5;
        return;
    L5:
        Buffer$$ExternalSyntheticBUOutline1.m();
    }

    @Override // okio.BufferedSource
    public int select(Options r4) {
        int r0 = okio.internal.Buffer.selectPrefix$default(this, r4, false, 2, null);
        if (r0 != (-1)) goto L5;
        return -1;
    L5:
        skip(r4.getByteStrings$okio()[r0].size());
        return r0;
    }

    public final void setSize$okio(long r1) {
        this.size = r1;
    }

    @JvmName(name = "size")
    public final long size() {
        return this.size;
    }

    @Override // okio.BufferedSource
    public void skip(long r7) {
    L3:
        if (r7 <= 0) goto L17;
        Segment r0 = this.head;
        if (r0 == null) goto L9;
        int r1 = (int) Math.min(r7, r0.limit - r0.pos);
        long r4 = r1;
        setSize$okio(size() - r4);
        r7 = r7 - r4;
        int r2 = r0.pos + r1;
        r0.pos = r2;
        if (r2 != r0.limit) goto L3;
        this.head = r0.pop();
        SegmentPool.recycle(r0);
        goto L3
    L9:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return;
    }

    public final ByteString snapshot() {
        if (size() > 2147483647L) goto L7;
        return snapshot((int) size());
    L7:
        throw new IllegalStateException(("size > Int.MAX_VALUE: " + size()).toString());
    }

    public final ByteString snapshot(int r8) {
        if (r8 == 0) goto L4;
        SegmentedByteString.checkOffsetAndCount(size(), 0, r8);
        Segment r0 = this.head;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r2 >= r8) goto L12;
        int r4 = r0.limit;
        int r5 = r0.pos;
        if (r4 == r5) goto L10;
        r2 = r2 + (r4 - r5);
        r3 = r3 + 1;
        r0 = r0.next;
        goto L6
    L10:
        Buffer$$ExternalSyntheticBUOutline2.m("s.limit == s.pos");
        return null;
    L12:
        byte[][] r02 = new byte[r3][];
        int[] r22 = new int[r3 * 2];
        Segment r7 = this.head;
        int r42 = 0;
    L13:
        if (r1 >= r8) goto L16;
        r02[r42] = r7.data;
        r1 = r1 + (r7.limit - r7.pos);
        r22[r42] = Math.min(r1, r8);
        r22[r42 + r3] = r7.pos;
        r7.shared = true;
        r42 = r42 + 1;
        r7 = r7.next;
        goto L13
    L16:
        return new C0193SegmentedByteString(r02, r22);
    L4:
        return ByteString.EMPTY;
    }

    @Override // okio.Source
    public Timeout timeout() {
        return Timeout.NONE;
    }

    public String toString() {
        return snapshot().toString();
    }

    public final Segment writableSegment$okio(int r3) {
        if (r3 >= 1) goto L5;
    L18:
        f$$ExternalSyntheticBUOutline1.m("unexpected capacity");
        return null;
    L5:
        if (r3 > 8192) goto L18;
        Segment r1 = this.head;
        if (r1 != null) goto L10;
        Segment r32 = SegmentPool.take();
        this.head = r32;
        r32.prev = r32;
        r32.next = r32;
        return r32;
    L10:
        Segment r2 = r1.prev;
        if ((r2.limit + r3) > 8192) goto L17;
        if (r2.owner == false) goto L17;
        return r2;
    L17:
        return r2.push(SegmentPool.take());
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer r7) {
        int r0 = r7.remaining();
        int r1 = r0;
    L3:
        if (r1 <= 0) goto L5;
        Segment r2 = writableSegment$okio(1);
        int r3 = Math.min(r1, 8192 - r2.limit);
        r7.get(r2.data, r2.limit, r3);
        r1 = r1 - r3;
        r2.limit += r3;
        goto L3
    L5:
        this.size += r0;
        return r0;
    }

    @Override // okio.BufferedSink
    public Buffer write(ByteString r3) {
        r3.write$okio(this, 0, r3.size());
        return this;
    }

    public Buffer write(Source r5, long r6) {
    L3:
        if (r6 <= 0) goto L11;
        long r0 = r5.read(this, r6);
        if (r0 == (-1)) goto L7;
        r6 = r6 - r0;
        goto L3
    L7:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return null;
    L11:
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] r3) {
        return write(r3, 0, r3.length);
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] r8, int r9, int r10) {
        long r5 = r10;
        SegmentedByteString.checkOffsetAndCount(r8.length, r9, r5);
        int r102 = r10 + r9;
    L3:
        if (r9 >= r102) goto L5;
        Segment r0 = writableSegment$okio(1);
        int r1 = Math.min(r102 - r9, 8192 - r0.limit);
        int r4 = r9 + r1;
        ArraysKt.copyInto(r8, r0.data, r0.limit, r9, r4);
        r0.limit += r1;
        r9 = r4;
        goto L3
    L5:
        setSize$okio(size() + r5);
        return this;
    }

    @Override // okio.BufferedSink
    public /* bridge */ /* synthetic */ BufferedSink write(ByteString r1) {
        return write(r1);
    }

    @Override // okio.BufferedSink
    public /* bridge */ /* synthetic */ BufferedSink write(byte[] r1) {
        return write(r1);
    }

    @Override // okio.BufferedSink
    public /* bridge */ /* synthetic */ BufferedSink write(byte[] r1, int r2, int r3) {
        return write(r1, r2, r3);
    }

    @Override // okio.Sink
    public void write(Buffer r7, long r8) {
        if (r7 == this) goto L30;
        SegmentedByteString.checkOffsetAndCount(r7.size(), 0, r8);
    L5:
        if (r8 <= 0) goto L29;
        Segment r0 = r7.head;
        if (r8 >= (r0.limit - r0.pos)) goto L24;
        Segment r1 = this.head;
        if (r1 == null) goto L11;
        Segment r12 = r1.prev;
    L12:
        if (r12 != null) goto L14;
    L23:
        r7.head = r0.split((int) r8);
        goto L24
    L14:
        if (r12.owner == false) goto L23;
        long r2 = r12.limit + r8;
        if (r12.shared == false) goto L18;
        int r4 = 0;
    L20:
        if ((r2 - r4) > MediaStatus.COMMAND_PLAYBACK_RATE) goto L23;
        r0.writeTo(r12, (int) r8);
        r7.setSize$okio(r7.size() - r8);
        setSize$okio(size() + r8);
        return;
    L18:
        r4 = r12.pos;
        goto L20
    L11:
        r12 = null;
    L24:
        Segment r02 = r7.head;
        long r13 = r02.limit - r02.pos;
        r7.head = r02.pop();
        Segment r3 = this.head;
        if (r3 != null) goto L27;
        this.head = r02;
        r02.prev = r02;
        r02.next = r02;
    L28:
        r7.setSize$okio(r7.size() - r13);
        setSize$okio(size() + r13);
        r8 = r8 - r13;
        goto L5
    L27:
        r3.prev.push(r02).compact();
        goto L28
    L29:
        return;
    L30:
        f$$ExternalSyntheticBUOutline1.m("source == this");
    }

    @Override // okio.BufferedSink
    public long writeAll(Source r7) {
        long r0 = 0;
    L3:
        long r2 = r7.read(this, MediaStatus.COMMAND_PLAYBACK_RATE);
        if (r2 == (-1)) goto L6;
        r0 = r0 + r2;
        goto L3
    L6:
        return r0;
    }

    @Override // okio.BufferedSink
    public Buffer writeByte(int r5) {
        Segment r0 = writableSegment$okio(1);
        byte[] r1 = r0.data;
        int r2 = r0.limit;
        r0.limit = r2 + 1;
        r1[r2] = (byte) r5;
        setSize$okio(size() + 1);
        return this;
    }

    @Override // okio.BufferedSink
    public /* bridge */ /* synthetic */ BufferedSink writeByte(int r1) {
        return writeByte(r1);
    }

    @Override // okio.BufferedSink
    public Buffer writeDecimalLong(long r12) {
        if (r12 == 0) goto L5;
        if (r12 >= 0) goto L12;
        r12 = -r12;
        if (r12 < 0) goto L10;
        boolean r2 = true;
    L13:
        int r3 = okio.internal.Buffer.access$countDigitsIn(r12);
        if (r2 == false) goto L16;
        r3 = r3 + 1;
    L16:
        Segment r4 = writableSegment$okio(r3);
        byte[] r5 = r4.data;
        int r6 = r4.limit + r3;
    L18:
        if (r12 == 0) goto L20;
        r6 = r6 - 1;
        r5[r6] = okio.internal.Buffer.getHEX_DIGIT_BYTES()[(int) (r12 % 10)];
        r12 = r12 / 10;
        goto L18
    L20:
        if (r2 == false) goto L22;
        r5[r6 - 1] = 45;
    L22:
        r4.limit += r3;
        setSize$okio(size() + r3);
        return this;
    L10:
        return writeUtf8("-9223372036854775808");
    L12:
        r2 = false;
        goto L13
    L5:
        return writeByte(48);
    }

    @Override // okio.BufferedSink
    public /* bridge */ /* synthetic */ BufferedSink writeDecimalLong(long r1) {
        return writeDecimalLong(r1);
    }

    @Override // okio.BufferedSink
    public Buffer writeHexadecimalUnsignedLong(long r13) {
        if (r13 == 0) goto L5;
        long r1 = (r13 >>> 1) | r13;
        long r12 = r1 | (r1 >>> 2);
        long r14 = r12 | (r12 >>> 4);
        long r15 = r14 | (r14 >>> 8);
        long r16 = r15 | (r15 >>> 16);
        long r17 = r16 | (r16 >>> 32);
        long r18 = r17 - ((r17 >>> 1) & 6148914691236517205L);
        long r8 = ((r18 >>> 2) & 3689348814741910323L) + (r18 & 3689348814741910323L);
        long r19 = ((r8 >>> 4) + r8) & 1085102592571150095L;
        long r110 = r19 + (r19 >>> 8);
        long r111 = r110 + (r110 >>> 16);
        int r112 = (int) ((((r111 & 63) + ((r111 >>> 32) & 63)) + 3) / 4);
        Segment r2 = writableSegment$okio(r112);
        byte[] r3 = r2.data;
        int r5 = r2.limit;
        int r6 = (r5 + r112) - 1;
    L7:
        if (r6 < r5) goto L9;
        r3[r6] = okio.internal.Buffer.getHEX_DIGIT_BYTES()[(int) (15 & r13)];
        r13 = r13 >>> 4;
        r6 = r6 - 1;
        goto L7
    L9:
        r2.limit += r112;
        setSize$okio(size() + r112);
        return this;
    L5:
        return writeByte(48);
    }

    @Override // okio.BufferedSink
    public /* bridge */ /* synthetic */ BufferedSink writeHexadecimalUnsignedLong(long r1) {
        return writeHexadecimalUnsignedLong(r1);
    }

    @Override // okio.BufferedSink
    public Buffer writeInt(int r8) {
        Segment r1 = writableSegment$okio(4);
        byte[] r2 = r1.data;
        int r3 = r1.limit;
        r2[r3] = (byte) ((r8 >>> 24) & 255);
        r2[r3 + 1] = (byte) ((r8 >>> 16) & 255);
        int r4 = r3 + 3;
        r2[r3 + 2] = (byte) ((r8 >>> 8) & 255);
        r2[r4] = (byte) (r8 & 255);
        r1.limit = r3 + 4;
        setSize$okio(size() + 4);
        return this;
    }

    @Override // okio.BufferedSink
    public /* bridge */ /* synthetic */ BufferedSink writeInt(int r1) {
        return writeInt(r1);
    }

    public Buffer writeIntLe(int r1) {
        return writeInt(SegmentedByteString.reverseBytes(r1));
    }

    public Buffer writeLong(long r12) {
        Segment r1 = writableSegment$okio(8);
        byte[] r2 = r1.data;
        int r3 = r1.limit;
        r2[r3] = (byte) ((r12 >>> 56) & 255);
        r2[r3 + 1] = (byte) ((r12 >>> 48) & 255);
        r2[r3 + 2] = (byte) ((r12 >>> 40) & 255);
        r2[r3 + 3] = (byte) ((r12 >>> 32) & 255);
        r2[r3 + 4] = (byte) ((r12 >>> 24) & 255);
        r2[r3 + 5] = (byte) ((r12 >>> 16) & 255);
        r2[r3 + 6] = (byte) ((r12 >>> 8) & 255);
        r2[r3 + 7] = (byte) (r12 & 255);
        r1.limit = r3 + 8;
        setSize$okio(size() + 8);
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeShort(int r7) {
        Segment r1 = writableSegment$okio(2);
        byte[] r2 = r1.data;
        int r3 = r1.limit;
        int r4 = r3 + 1;
        r2[r3] = (byte) ((r7 >>> 8) & 255);
        r2[r4] = (byte) (r7 & 255);
        r1.limit = r3 + 2;
        setSize$okio(size() + 2);
        return this;
    }

    @Override // okio.BufferedSink
    public /* bridge */ /* synthetic */ BufferedSink writeShort(int r1) {
        return writeShort(r1);
    }

    public Buffer writeString(String r3, int r4, int r5, Charset r6) {
        if (r4 < 0) goto L17;
        if (r5 >= r4) goto L6;
        Utf8$$ExternalSyntheticBUOutline0.m("endIndex < beginIndex: ", r5, " < ", r4);
        return null;
    L6:
        if (r5 <= r3.length()) goto L8;
        Utf8$$ExternalSyntheticBUOutline0.m("endIndex > string.length: ", r5, " > ", r3.length());
        return null;
    L8:
        if (Intrinsics.areEqual(r6, Charsets.UTF_8) == true) goto L10;
        byte[] r32 = r3.substring(r4, r5).getBytes(r6);
        return write(r32, 0, r32.length);
    L10:
        return writeUtf8(r3, r4, r5);
    L17:
        Utf8$$ExternalSyntheticBUOutline1.m("beginIndex < 0: ", r4);
        return null;
    }

    public Buffer writeString(String r3, Charset r4) {
        return writeString(r3, 0, r3.length(), r4);
    }

    @JvmOverloads
    public final Buffer writeTo(OutputStream r8, long r9) {
        SegmentedByteString.checkOffsetAndCount(this.size, 0, r9);
        Segment r0 = this.head;
        long r92 = r9;
    L4:
        if (r92 <= 0) goto L8;
        int r1 = (int) Math.min(r92, r0.limit - r0.pos);
        r8.write(r0.data, r0.pos, r1);
        int r2 = r0.pos + r1;
        r0.pos = r2;
        long r5 = r1;
        this.size -= r5;
        r92 = r92 - r5;
        if (r2 != r0.limit) goto L4;
        Segment r12 = r0.pop();
        this.head = r12;
        SegmentPool.recycle(r0);
        r0 = r12;
        goto L4
    L8:
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String r3) {
        return writeUtf8(r3, 0, r3.length());
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String r10, int r11, int r12) {
        if (r11 < 0) goto L44;
        if (r12 >= r11) goto L6;
        Utf8$$ExternalSyntheticBUOutline0.m("endIndex < beginIndex: ", r12, " < ", r11);
        return null;
    L6:
        if (r12 > r10.length()) goto L40;
    L7:
        if (r11 >= r12) goto L39;
        char r0 = r10.charAt(r11);
        if (r0 < 128) goto L10;
        if (r0 < 2048) goto L19;
        if (r0 < 55296) goto L38;
        if (r0 > 57343) goto L38;
        int r2 = r11 + 1;
        if (r2 >= r12) goto L29;
        char r4 = r10.charAt(r2);
    L31:
        if (r0 > 56319) goto L37;
        if (56320 > r4) goto L37;
        if (r4 >= 57344) goto L37;
        int r02 = (((r0 & 1023) << 10) | (r4 & 1023)) + 65536;
        Segment r42 = writableSegment$okio(4);
        byte[] r5 = r42.data;
        int r6 = r42.limit;
        r5[r6] = (byte) ((r02 >> 18) | 240);
        r5[r6 + 1] = (byte) (((r02 >> 12) & 63) | 128);
        r5[r6 + 2] = (byte) (((r02 >> 6) & 63) | 128);
        r5[r6 + 3] = (byte) ((r02 & 63) | 128);
        r42.limit = r6 + 4;
        setSize$okio(size() + 4);
        r11 = r11 + 2;
    L37:
        writeByte(63);
        r11 = r2;
        goto L7
    L29:
        r4 = 0;
    L38:
        Segment r43 = writableSegment$okio(3);
        byte[] r52 = r43.data;
        int r62 = r43.limit;
        r52[r62] = (byte) ((r0 >> '\f') | Opcodes.SHL_INT_LIT8);
        r52[r62 + 1] = (byte) ((63 & (r0 >> 6)) | 128);
        r52[r62 + 2] = (byte) ((r0 & '?') | 128);
        r43.limit = r62 + 3;
        setSize$okio(size() + 3);
    L20:
        r11 = r11 + 1;
        goto L7
    L19:
        Segment r3 = writableSegment$okio(2);
        byte[] r44 = r3.data;
        int r53 = r3.limit;
        r44[r53] = (byte) ((r0 >> 6) | 192);
        r44[r53 + 1] = (byte) ((r0 & '?') | 128);
        r3.limit = r53 + 2;
        setSize$okio(size() + 2);
        goto L20
    L10:
        Segment r22 = writableSegment$okio(1);
        byte[] r32 = r22.data;
        int r45 = r22.limit - r11;
        int r54 = Math.min(r12, 8192 - r45);
        int r63 = r11 + 1;
        r32[r11 + r45] = (byte) r0;
    L11:
        r11 = r63;
        if (r11 >= r54) goto L16;
        char r03 = r10.charAt(r11);
        if (r03 >= 128) goto L16;
        r63 = r11 + 1;
        r32[r11 + r45] = (byte) r03;
    L16:
        int r04 = r22.limit;
        int r46 = (r45 + r11) - r04;
        r22.limit = r04 + r46;
        setSize$okio(size() + r46);
        goto L7
    L39:
        return this;
    L40:
        Utf8$$ExternalSyntheticBUOutline0.m("endIndex > string.length: ", r12, " > ", r10.length());
        return null;
    L44:
        Utf8$$ExternalSyntheticBUOutline1.m("beginIndex < 0: ", r11);
        return null;
    }

    @Override // okio.BufferedSink
    public /* bridge */ /* synthetic */ BufferedSink writeUtf8(String r1) {
        return writeUtf8(r1);
    }

    @Override // okio.BufferedSink
    public /* bridge */ /* synthetic */ BufferedSink writeUtf8(String r1, int r2, int r3) {
        return writeUtf8(r1, r2, r3);
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8CodePoint(int r9) {
        if (r9 >= 128) goto L7;
        writeByte(r9);
        return this;
    L7:
        if (r9 >= 2048) goto L11;
        Segment r3 = writableSegment$okio(2);
        byte[] r4 = r3.data;
        int r5 = r3.limit;
        r4[r5] = (byte) ((r9 >> 6) | 192);
        r4[r5 + 1] = (byte) ((r9 & 63) | 128);
        r3.limit = r5 + 2;
        setSize$okio(size() + 2);
        return this;
    L11:
        if (55296 > r9) goto L17;
        if (r9 >= 57344) goto L17;
        writeByte(63);
        return this;
    L17:
        if (r9 >= 65536) goto L21;
        Segment r32 = writableSegment$okio(3);
        byte[] r42 = r32.data;
        int r52 = r32.limit;
        r42[r52] = (byte) ((r9 >> 12) | Opcodes.SHL_INT_LIT8);
        r42[r52 + 1] = (byte) (((r9 >> 6) & 63) | 128);
        r42[r52 + 2] = (byte) ((r9 & 63) | 128);
        r32.limit = r52 + 3;
        setSize$okio(size() + 3);
        return this;
    L21:
        if (r9 > 1114111) goto L24;
        Segment r33 = writableSegment$okio(4);
        byte[] r43 = r33.data;
        int r53 = r33.limit;
        r43[r53] = (byte) ((r9 >> 18) | 240);
        r43[r53 + 1] = (byte) (((r9 >> 12) & 63) | 128);
        r43[r53 + 2] = (byte) (((r9 >> 6) & 63) | 128);
        r43[r53 + 3] = (byte) ((r9 & 63) | 128);
        r33.limit = r53 + 4;
        setSize$okio(size() + 4);
        return this;
    L24:
        Buffer$$ExternalSyntheticBUOutline4.m("Unexpected code point: 0x", SegmentedByteString.toHexString(r9));
        return null;
    }

    @Override // okio.BufferedSink
    public /* bridge */ /* synthetic */ BufferedSink writeUtf8CodePoint(int r1) {
        return writeUtf8CodePoint(r1);
    }
}
