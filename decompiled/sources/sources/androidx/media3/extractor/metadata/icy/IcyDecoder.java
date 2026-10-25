package androidx.media3.extractor.metadata.icy;

import androidx.media3.common.Metadata;
import androidx.media3.extractor.metadata.MetadataInputBuffer;
import androidx.media3.extractor.metadata.SimpleMetadataDecoder;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public final class IcyDecoder extends SimpleMetadataDecoder {
    private static final Pattern METADATA_ELEMENT = null;
    private final CharsetDecoder iso88591Decoder;
    private final CharsetDecoder utf8Decoder;

    static {
        METADATA_ELEMENT = Pattern.compile("(.+?)='(.*?)';", 32);
    }

    public IcyDecoder() {
        this.utf8Decoder = StandardCharsets.UTF_8.newDecoder();
        this.iso88591Decoder = StandardCharsets.ISO_8859_1.newDecoder();
    }

    private String decodeToString(ByteBuffer r2) {
        String r0 = this.utf8Decoder.decode(r2).toString();     // Catch: Throwable -> L5 CharacterCodingException -> L8
        this.utf8Decoder.reset();
        r2.rewind();
        return r0;
    L8:
        this.utf8Decoder.reset();
        r2.rewind();
        String r02 = this.iso88591Decoder.decode(r2).toString();     // Catch: Throwable -> L12 CharacterCodingException -> L15
        this.iso88591Decoder.reset();
        r2.rewind();
        return r02;
    L15:
        this.iso88591Decoder.reset();
        r2.rewind();
        return null;
    L12:
        th = move-exception;
        this.iso88591Decoder.reset();
        r2.rewind();
        throw th;
    L5:
        th = move-exception;
        this.utf8Decoder.reset();
        r2.rewind();
        throw th;
    }

    @Override // androidx.media3.extractor.metadata.SimpleMetadataDecoder
    public Metadata decode(MetadataInputBuffer r7, ByteBuffer r8) {
        String r6 = decodeToString(r8);
        byte[] r72 = new byte[r8.limit()];
        r8.get(r72);
        String r1 = null;
        if (r6 == null) goto L5;
        Matcher r62 = METADATA_ELEMENT.matcher(r6);
        int r3 = 0;
        String r2 = null;
    L8:
        if (r62.find(r3) == false) goto L20;
        String r32 = r62.group(1);
        String r4 = r62.group(2);
        if (r32 == null) goto L18;
        String r33 = Ascii.toLowerCase(r32);
        r33.getClass();
        if (r33.equals("streamurl") == false) goto L14;
        r2 = r4;
        goto L18
    L14:
        if (r33.equals("streamtitle") == false) goto L18;
        r1 = r4;
    L18:
        r3 = r62.end();
        goto L8
    L20:
        return new Metadata(new Metadata.Entry[]{new IcyInfo(r72, r1, r2)});
    L5:
        return new Metadata(new Metadata.Entry[]{new IcyInfo(r72, null, null)});
    }
}
