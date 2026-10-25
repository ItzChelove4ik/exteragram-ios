package kotlinx.serialization.json.internal;

import com.android.dx.rop.code.RegisterSpec;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0010\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\bJ\u000f\u0010\n\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\n\u0010\bJ\u000f\u0010\u000b\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\bJ\u000f\u0010\f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\f\u0010\bJ\u0015\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0011¢\u0006\u0004\b\u000f\u0010\u0012J\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u000f\u0010\u0014J\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u000f\u0010\u0016J\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u000f\u0010\u0018J\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u000f\u0010\u001aJ\u0017\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u001c\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001dR*\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00198\u0006@DX\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010\u001a¨\u0006#"}, d2 = {"Lkotlinx/serialization/json/internal/Composer;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/serialization/json/internal/InternalJsonWriter;", "writer", "<init>", "(Lkotlinx/serialization/json/internal/InternalJsonWriter;)V", _UrlKt.FRAGMENT_ENCODE_SET, "indent", "()V", "unIndent", "nextItem", "nextItemIfNotFirst", "space", _UrlKt.FRAGMENT_ENCODE_SET, RegisterSpec.PREFIX, "print", "(C)V", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;)V", _UrlKt.FRAGMENT_ENCODE_SET, "(D)V", _UrlKt.FRAGMENT_ENCODE_SET, "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "(J)V", _UrlKt.FRAGMENT_ENCODE_SET, "(Z)V", "value", "printQuoted", "Lkotlinx/serialization/json/internal/InternalJsonWriter;", "writingFirst", "Z", "getWritingFirst", "()Z", "setWritingFirst", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public class Composer {

    @JvmField
    public final InternalJsonWriter writer;
    private boolean writingFirst;

    public Composer(InternalJsonWriter r1) {
        this.writer = r1;
        this.writingFirst = true;
    }

    public final boolean getWritingFirst() {
        return this.writingFirst;
    }

    public void indent() {
        this.writingFirst = true;
    }

    public void nextItem() {
        this.writingFirst = false;
    }

    public void nextItemIfNotFirst() {
        this.writingFirst = false;
    }

    public final void print(char r1) {
        this.writer.writeChar(r1);
    }

    public void print(double r1) {
        this.writer.write(String.valueOf(r1));
    }

    public void print(int r3) {
        this.writer.writeLong(r3);
    }

    public void print(long r1) {
        this.writer.writeLong(r1);
    }

    public final void print(String r1) {
        this.writer.write(r1);
    }

    public void print(boolean r1) {
        this.writer.write(String.valueOf(r1));
    }

    public void printQuoted(String r1) {
        this.writer.writeQuoted(r1);
    }

    public final void setWritingFirst(boolean r1) {
        this.writingFirst = r1;
    }

    public void space() {
    }

    public void unIndent() {
    }
}
