package kotlin.text;

import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Utf8$$ExternalSyntheticBUOutline1;

@SinceKotlin(version = "2.2")
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0004\u0012\u0013\u0014\u0015B!\b@\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0006\u001a\u00020\u0007X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lkotlin/text/HexFormat;", _UrlKt.FRAGMENT_ENCODE_SET, "upperCase", _UrlKt.FRAGMENT_ENCODE_SET, "bytes", "Lkotlin/text/HexFormat$BytesHexFormat;", "number", "Lkotlin/text/HexFormat$NumberHexFormat;", "<init>", "(ZLkotlin/text/HexFormat$BytesHexFormat;Lkotlin/text/HexFormat$NumberHexFormat;)V", "getUpperCase", "()Z", "getBytes", "()Lkotlin/text/HexFormat$BytesHexFormat;", "getNumber", "()Lkotlin/text/HexFormat$NumberHexFormat;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "BytesHexFormat", "NumberHexFormat", "Builder", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@WasExperimental(markerClass = {ExperimentalStdlibApi.class})
/* loaded from: classes5.dex */
public final class HexFormat {
    public static final Companion Companion = null;
    private static final HexFormat Default = null;
    private static final HexFormat UpperCase = null;
    private final BytesHexFormat bytes;
    private final NumberHexFormat number;
    private final boolean upperCase;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\t\bA¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\n\u001a\u00020\u00142\u0017\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00140\u0016¢\u0006\u0002\b\u0017H\u0087\u0088\u0004ø\u0001\u0000J&\u0010\u000f\u001a\u00020\u00142\u0017\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00140\u0016¢\u0006\u0002\b\u0017H\u0087\u0088\u0004ø\u0001\u0000J\n\u0010\u0018\u001a\u00020\u0019H\u0081\u0080\u0004R\u001b\u0010\u0004\u001a\u00020\u0005X\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0015\u0010\n\u001a\u00020\u000b8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u0004\u0018\u00010\u000bX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\u000f\u001a\u00020\u00108FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0010X\u0082\u008e\b¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001a"}, d2 = {"Lkotlin/text/HexFormat$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "upperCase", _UrlKt.FRAGMENT_ENCODE_SET, "getUpperCase", "()Z", "setUpperCase", "(Z)V", "bytes", "Lkotlin/text/HexFormat$BytesHexFormat$Builder;", "getBytes", "()Lkotlin/text/HexFormat$BytesHexFormat$Builder;", "_bytes", "number", "Lkotlin/text/HexFormat$NumberHexFormat$Builder;", "getNumber", "()Lkotlin/text/HexFormat$NumberHexFormat$Builder;", "_number", _UrlKt.FRAGMENT_ENCODE_SET, "builderAction", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "build", "Lkotlin/text/HexFormat;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Builder {
        private BytesHexFormat.Builder _bytes;
        private NumberHexFormat.Builder _number;
        private boolean upperCase;

        @PublishedApi
        public Builder() {
            this.upperCase = HexFormat.Companion.getDefault().getUpperCase();
        }

        @InlineOnly
        private final void bytes(Function1<? super BytesHexFormat.Builder, Unit> r1) {
            r1.invoke(getBytes());
        }

        @InlineOnly
        private final void number(Function1<? super NumberHexFormat.Builder, Unit> r1) {
            r1.invoke(getNumber());
        }

        @PublishedApi
        public final HexFormat build() {
            boolean r1 = this.upperCase;
            BytesHexFormat.Builder r2 = this._bytes;
            if (r2 == null) goto L6;
            BytesHexFormat r22 = r2.build$kotlin_stdlib();
            if (r22 == null) goto L6;
        L7:
            NumberHexFormat.Builder r3 = this._number;
            if (r3 == null) goto L11;
            NumberHexFormat r32 = r3.build$kotlin_stdlib();
            if (r32 == null) goto L11;
        L13:
            return new HexFormat(r1, r22, r32);
        L11:
            r32 = NumberHexFormat.Companion.getDefault$kotlin_stdlib();
        L6:
            r22 = BytesHexFormat.Companion.getDefault$kotlin_stdlib();
            goto L7
        }

        public final BytesHexFormat.Builder getBytes() {
            if (this._bytes != null) goto L6;
            this._bytes = new BytesHexFormat.Builder();
        L6:
            return this._bytes;
        }

        public final NumberHexFormat.Builder getNumber() {
            if (this._number != null) goto L6;
            this._number = new NumberHexFormat.Builder();
        L6:
            return this._number;
        }

        public final boolean getUpperCase() {
            return this.upperCase;
        }

        public final void setUpperCase(boolean r1) {
            this.upperCase = r1;
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 %2\u00020\u0001:\u0002$%B9\b@\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bJ\n\u0010\u001c\u001a\u00020\u0006H\u0096\u0080\u0004J)\u0010\u001d\u001a\u00060\u001ej\u0002`\u001f2\n\u0010 \u001a\u00060\u001ej\u0002`\u001f2\u0006\u0010!\u001a\u00020\u0006H\u0080\u0080\u0004¢\u0006\u0004\b\"\u0010#R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0004\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0015\u0010\u0005\u001a\u00020\u0006X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0007\u001a\u00020\u0006X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0015\u0010\b\u001a\u00020\u0006X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0015\u0010\t\u001a\u00020\u0006X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0015\u0010\u0014\u001a\u00020\u0015X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\u0018\u001a\u00020\u0015X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0015\u0010\u001a\u001a\u00020\u0015X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017¨\u0006&"}, d2 = {"Lkotlin/text/HexFormat$BytesHexFormat;", _UrlKt.FRAGMENT_ENCODE_SET, "bytesPerLine", _UrlKt.FRAGMENT_ENCODE_SET, "bytesPerGroup", "groupSeparator", _UrlKt.FRAGMENT_ENCODE_SET, "byteSeparator", "bytePrefix", "byteSuffix", "<init>", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBytesPerLine", "()I", "getBytesPerGroup", "getGroupSeparator", "()Ljava/lang/String;", "getByteSeparator", "getBytePrefix", "getByteSuffix", "noLineAndGroupSeparator", _UrlKt.FRAGMENT_ENCODE_SET, "getNoLineAndGroupSeparator$kotlin_stdlib", "()Z", "shortByteSeparatorNoPrefixAndSuffix", "getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib", "ignoreCase", "getIgnoreCase$kotlin_stdlib", "toString", "appendOptionsTo", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "sb", "indent", "appendOptionsTo$kotlin_stdlib", "(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;", "Builder", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class BytesHexFormat {
        public static final Companion Companion = null;
        private static final BytesHexFormat Default = null;
        private final String bytePrefix;
        private final String byteSeparator;
        private final String byteSuffix;
        private final int bytesPerGroup;
        private final int bytesPerLine;
        private final String groupSeparator;
        private final boolean ignoreCase;
        private final boolean noLineAndGroupSeparator;
        private final boolean shortByteSeparatorNoPrefixAndSuffix;

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\t\b@¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u001d\u001a\u00020\u001eH\u0080\u0080\u0004¢\u0006\u0002\b\u001fR%\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@FX\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR%\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@FX\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001b\u0010\u000e\u001a\u00020\u000fX\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R%\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000f@FX\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R%\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000f@FX\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R%\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u000f@FX\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013¨\u0006 "}, d2 = {"Lkotlin/text/HexFormat$BytesHexFormat$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "value", _UrlKt.FRAGMENT_ENCODE_SET, "bytesPerLine", "getBytesPerLine", "()I", "setBytesPerLine", "(I)V", "bytesPerGroup", "getBytesPerGroup", "setBytesPerGroup", "groupSeparator", _UrlKt.FRAGMENT_ENCODE_SET, "getGroupSeparator", "()Ljava/lang/String;", "setGroupSeparator", "(Ljava/lang/String;)V", "byteSeparator", "getByteSeparator", "setByteSeparator", "bytePrefix", "getBytePrefix", "setBytePrefix", "byteSuffix", "getByteSuffix", "setByteSuffix", "build", "Lkotlin/text/HexFormat$BytesHexFormat;", "build$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Builder {
            private String bytePrefix;
            private String byteSeparator;
            private String byteSuffix;
            private int bytesPerGroup;
            private int bytesPerLine;
            private String groupSeparator;

            public Builder() {
                Companion r0 = BytesHexFormat.Companion;
                this.bytesPerLine = r0.getDefault$kotlin_stdlib().getBytesPerLine();
                this.bytesPerGroup = r0.getDefault$kotlin_stdlib().getBytesPerGroup();
                this.groupSeparator = r0.getDefault$kotlin_stdlib().getGroupSeparator();
                this.byteSeparator = r0.getDefault$kotlin_stdlib().getByteSeparator();
                this.bytePrefix = r0.getDefault$kotlin_stdlib().getBytePrefix();
                this.byteSuffix = r0.getDefault$kotlin_stdlib().getByteSuffix();
            }

            public final BytesHexFormat build$kotlin_stdlib() {
                return new BytesHexFormat(this.bytesPerLine, this.bytesPerGroup, this.groupSeparator, this.byteSeparator, this.bytePrefix, this.byteSuffix);
            }

            public final String getBytePrefix() {
                return this.bytePrefix;
            }

            public final String getByteSeparator() {
                return this.byteSeparator;
            }

            public final String getByteSuffix() {
                return this.byteSuffix;
            }

            public final int getBytesPerGroup() {
                return this.bytesPerGroup;
            }

            public final int getBytesPerLine() {
                return this.bytesPerLine;
            }

            public final String getGroupSeparator() {
                return this.groupSeparator;
            }

            public final void setBytePrefix(String r5) {
                if (StringsKt__StringsKt.contains$default(r5, '\n', false, 2, null) == false) goto L5;
            L8:
                Native$$ExternalSyntheticBUOutline5.m("LF and CR characters are prohibited in bytePrefix, but was ", r5);
                return;
            L5:
                if (StringsKt__StringsKt.contains$default(r5, '\r', false, 2, null) == true) goto L8;
                this.bytePrefix = r5;
            }

            public final void setByteSeparator(String r5) {
                if (StringsKt__StringsKt.contains$default(r5, '\n', false, 2, null) == false) goto L5;
            L8:
                Native$$ExternalSyntheticBUOutline5.m("LF and CR characters are prohibited in byteSeparator, but was ", r5);
                return;
            L5:
                if (StringsKt__StringsKt.contains$default(r5, '\r', false, 2, null) == true) goto L8;
                this.byteSeparator = r5;
            }

            public final void setByteSuffix(String r5) {
                if (StringsKt__StringsKt.contains$default(r5, '\n', false, 2, null) == false) goto L5;
            L8:
                Native$$ExternalSyntheticBUOutline5.m("LF and CR characters are prohibited in byteSuffix, but was ", r5);
                return;
            L5:
                if (StringsKt__StringsKt.contains$default(r5, '\r', false, 2, null) == true) goto L8;
                this.byteSuffix = r5;
            }

            public final void setBytesPerGroup(int r1) {
                if (r1 <= 0) goto L5;
                this.bytesPerGroup = r1;
                return;
            L5:
                CharCodeKt$$ExternalSyntheticBUOutline0.m("Non-positive values are prohibited for bytesPerGroup, but was ", r1);
            }

            public final void setBytesPerLine(int r1) {
                if (r1 <= 0) goto L5;
                this.bytesPerLine = r1;
                return;
            L5:
                CharCodeKt$$ExternalSyntheticBUOutline0.m("Non-positive values are prohibited for bytesPerLine, but was ", r1);
            }

            public final void setGroupSeparator(String r1) {
                this.groupSeparator = r1;
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u0005X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlin/text/HexFormat$BytesHexFormat$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Default", "Lkotlin/text/HexFormat$BytesHexFormat;", "getDefault$kotlin_stdlib", "()Lkotlin/text/HexFormat$BytesHexFormat;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public final BytesHexFormat getDefault$kotlin_stdlib() {
                return BytesHexFormat.access$getDefault$cp();
            }
        }

        static {
            Companion = new Companion(null);
            Default = new BytesHexFormat(Integer.MAX_VALUE, Integer.MAX_VALUE, "  ", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET);
        }

        public BytesHexFormat(int r4, int r5, String r6, String r7, String r8, String r9) {
            this.bytesPerLine = r4;
            this.bytesPerGroup = r5;
            this.groupSeparator = r6;
            this.byteSeparator = r7;
            this.bytePrefix = r8;
            this.byteSuffix = r9;
            boolean r0 = false;
            if (r4 != Integer.MAX_VALUE) goto L6;
            if (r5 != Integer.MAX_VALUE) goto L6;
            boolean r42 = true;
        L7:
            this.noLineAndGroupSeparator = r42;
            if (r8.length() == 0) goto L10;
        L14:
            boolean r43 = false;
        L15:
            this.shortByteSeparatorNoPrefixAndSuffix = r43;
            if (HexFormatKt.access$isCaseSensitive(r6) == false) goto L18;
        L23:
            r0 = true;
        L24:
            this.ignoreCase = r0;
            return;
        L18:
            if (HexFormatKt.access$isCaseSensitive(r7) == true) goto L23;
            if (HexFormatKt.access$isCaseSensitive(r8) == true) goto L23;
            if (HexFormatKt.access$isCaseSensitive(r9) == false) goto L24;
        L10:
            if (r9.length() != 0) goto L14;
            if (r7.length() > 1) goto L14;
            r43 = true;
        L6:
            r42 = false;
            goto L7
        }

        public static final /* synthetic */ BytesHexFormat access$getDefault$cp() {
            return Default;
        }

        public final StringBuilder appendOptionsTo$kotlin_stdlib(StringBuilder r4, String r5) {
            r4.append(r5);
            r4.append("bytesPerLine = ");
            r4.append(this.bytesPerLine);
            r4.append(",");
            r4.append('\n');
            r4.append(r5);
            r4.append("bytesPerGroup = ");
            r4.append(this.bytesPerGroup);
            r4.append(",");
            r4.append('\n');
            r4.append(r5);
            r4.append("groupSeparator = \"");
            r4.append(this.groupSeparator);
            r4.append("\",");
            r4.append('\n');
            r4.append(r5);
            r4.append("byteSeparator = \"");
            r4.append(this.byteSeparator);
            r4.append("\",");
            r4.append('\n');
            r4.append(r5);
            r4.append("bytePrefix = \"");
            r4.append(this.bytePrefix);
            r4.append("\",");
            r4.append('\n');
            r4.append(r5);
            r4.append("byteSuffix = \"");
            r4.append(this.byteSuffix);
            r4.append("\"");
            return r4;
        }

        public final String getBytePrefix() {
            return this.bytePrefix;
        }

        public final String getByteSeparator() {
            return this.byteSeparator;
        }

        public final String getByteSuffix() {
            return this.byteSuffix;
        }

        public final int getBytesPerGroup() {
            return this.bytesPerGroup;
        }

        public final int getBytesPerLine() {
            return this.bytesPerLine;
        }

        public final String getGroupSeparator() {
            return this.groupSeparator;
        }

        public final boolean getIgnoreCase$kotlin_stdlib() {
            return this.ignoreCase;
        }

        public final boolean getNoLineAndGroupSeparator$kotlin_stdlib() {
            return this.noLineAndGroupSeparator;
        }

        public final boolean getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib() {
            return this.shortByteSeparatorNoPrefixAndSuffix;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder();
            r0.append("BytesHexFormat(\n");
            appendOptionsTo$kotlin_stdlib(r0, "    ").append('\n');
            r0.append(")");
            return r0.toString();
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\b\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lkotlin/text/HexFormat$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Default", "Lkotlin/text/HexFormat;", "getDefault", "()Lkotlin/text/HexFormat;", "UpperCase", "getUpperCase", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final HexFormat getDefault() {
            return HexFormat.access$getDefault$cp();
        }

        public final HexFormat getUpperCase() {
            return HexFormat.access$getUpperCase$cp();
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 #2\u00020\u0001:\u0002\"#B)\b@\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\n\u0010\u001a\u001a\u00020\u0003H\u0096\u0080\u0004J)\u0010\u001b\u001a\u00060\u001cj\u0002`\u001d2\n\u0010\u001e\u001a\u00060\u001cj\u0002`\u001d2\u0006\u0010\u001f\u001a\u00020\u0003H\u0080\u0080\u0004¢\u0006\u0004\b \u0010!R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0015\u0010\u0005\u001a\u00020\u0006X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0084\b¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0014\u001a\u00020\u0006X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0015\u0010\u0016\u001a\u00020\u0006X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0015\u0010\u0018\u001a\u00020\u0006X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000f¨\u0006$"}, d2 = {"Lkotlin/text/HexFormat$NumberHexFormat;", _UrlKt.FRAGMENT_ENCODE_SET, "prefix", _UrlKt.FRAGMENT_ENCODE_SET, "suffix", "removeLeadingZeros", _UrlKt.FRAGMENT_ENCODE_SET, "minLength", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;Ljava/lang/String;ZI)V", "getPrefix", "()Ljava/lang/String;", "getSuffix", "getRemoveLeadingZeros", "()Z", "getMinLength$annotations", "()V", "getMinLength", "()I", "isDigitsOnly", "isDigitsOnly$kotlin_stdlib", "isDigitsOnlyAndNoPadding", "isDigitsOnlyAndNoPadding$kotlin_stdlib", "ignoreCase", "getIgnoreCase$kotlin_stdlib", "toString", "appendOptionsTo", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "sb", "indent", "appendOptionsTo$kotlin_stdlib", "(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;", "Builder", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class NumberHexFormat {
        public static final Companion Companion = null;
        private static final NumberHexFormat Default = null;
        private final boolean ignoreCase;
        private final boolean isDigitsOnly;
        private final boolean isDigitsOnlyAndNoPadding;
        private final int minLength;
        private final String prefix;
        private final boolean removeLeadingZeros;
        private final String suffix;

        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\t\b@¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u001b\u001a\u00020\u001cH\u0080\u0080\u0004¢\u0006\u0002\b\u001dR%\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@FX\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR%\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@FX\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001b\u0010\u000e\u001a\u00020\u000fX\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R-\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u00148\u0006@FX\u0087\u008e\b¢\u0006\u0014\n\u0000\u0012\u0004\b\u0016\u0010\u0003\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001e"}, d2 = {"Lkotlin/text/HexFormat$NumberHexFormat$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "value", _UrlKt.FRAGMENT_ENCODE_SET, "prefix", "getPrefix", "()Ljava/lang/String;", "setPrefix", "(Ljava/lang/String;)V", "suffix", "getSuffix", "setSuffix", "removeLeadingZeros", _UrlKt.FRAGMENT_ENCODE_SET, "getRemoveLeadingZeros", "()Z", "setRemoveLeadingZeros", "(Z)V", _UrlKt.FRAGMENT_ENCODE_SET, "minLength", "getMinLength$annotations", "getMinLength", "()I", "setMinLength", "(I)V", "build", "Lkotlin/text/HexFormat$NumberHexFormat;", "build$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nHexFormat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HexFormat.kt\nkotlin/text/HexFormat$NumberHexFormat$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,845:1\n1#2:846\n*E\n"})
        public static final class Builder {
            private int minLength;
            private String prefix;
            private boolean removeLeadingZeros;
            private String suffix;

            public Builder() {
                Companion r0 = NumberHexFormat.Companion;
                this.prefix = r0.getDefault$kotlin_stdlib().getPrefix();
                this.suffix = r0.getDefault$kotlin_stdlib().getSuffix();
                this.removeLeadingZeros = r0.getDefault$kotlin_stdlib().getRemoveLeadingZeros();
                this.minLength = r0.getDefault$kotlin_stdlib().getMinLength();
            }

            @SinceKotlin(version = "2.0")
            public static /* synthetic */ void getMinLength$annotations() {
            }

            public final NumberHexFormat build$kotlin_stdlib() {
                return new NumberHexFormat(this.prefix, this.suffix, this.removeLeadingZeros, this.minLength);
            }

            public final int getMinLength() {
                return this.minLength;
            }

            public final String getPrefix() {
                return this.prefix;
            }

            public final boolean getRemoveLeadingZeros() {
                return this.removeLeadingZeros;
            }

            public final String getSuffix() {
                return this.suffix;
            }

            public final void setMinLength(int r1) {
                if (r1 <= 0) goto L5;
                this.minLength = r1;
                return;
            L5:
                Utf8$$ExternalSyntheticBUOutline1.m("Non-positive values are prohibited for minLength, but was ", r1);
            }

            public final void setPrefix(String r5) {
                if (StringsKt__StringsKt.contains$default(r5, '\n', false, 2, null) == false) goto L5;
            L8:
                Native$$ExternalSyntheticBUOutline5.m("LF and CR characters are prohibited in prefix, but was ", r5);
                return;
            L5:
                if (StringsKt__StringsKt.contains$default(r5, '\r', false, 2, null) == true) goto L8;
                this.prefix = r5;
            }

            public final void setRemoveLeadingZeros(boolean r1) {
                this.removeLeadingZeros = r1;
            }

            public final void setSuffix(String r5) {
                if (StringsKt__StringsKt.contains$default(r5, '\n', false, 2, null) == false) goto L5;
            L8:
                Native$$ExternalSyntheticBUOutline5.m("LF and CR characters are prohibited in suffix, but was ", r5);
                return;
            L5:
                if (StringsKt__StringsKt.contains$default(r5, '\r', false, 2, null) == true) goto L8;
                this.suffix = r5;
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u0005X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlin/text/HexFormat$NumberHexFormat$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Default", "Lkotlin/text/HexFormat$NumberHexFormat;", "getDefault$kotlin_stdlib", "()Lkotlin/text/HexFormat$NumberHexFormat;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public final NumberHexFormat getDefault$kotlin_stdlib() {
                return NumberHexFormat.access$getDefault$cp();
            }
        }

        static {
            Companion = new Companion(null);
            Default = new NumberHexFormat(_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, false, 1);
        }

        public NumberHexFormat(String r3, String r4, boolean r5, int r6) {
            this.prefix = r3;
            this.suffix = r4;
            this.removeLeadingZeros = r5;
            this.minLength = r6;
            boolean r0 = false;
            if (r3.length() == 0) goto L5;
        L7:
            boolean r52 = false;
        L8:
            this.isDigitsOnly = r52;
            if (r52 == false) goto L12;
            if (r6 != 1) goto L12;
            boolean r53 = true;
        L13:
            this.isDigitsOnlyAndNoPadding = r53;
            if (HexFormatKt.access$isCaseSensitive(r3) == false) goto L16;
        L17:
            r0 = true;
        L18:
            this.ignoreCase = r0;
            return;
        L16:
            if (HexFormatKt.access$isCaseSensitive(r4) == false) goto L18;
        L12:
            r53 = false;
            goto L13
        L5:
            if (r4.length() != 0) goto L7;
            r52 = true;
            goto L8
        }

        public static final /* synthetic */ NumberHexFormat access$getDefault$cp() {
            return Default;
        }

        @SinceKotlin(version = "2.0")
        public static /* synthetic */ void getMinLength$annotations() {
        }

        public final StringBuilder appendOptionsTo$kotlin_stdlib(StringBuilder r4, String r5) {
            r4.append(r5);
            r4.append("prefix = \"");
            r4.append(this.prefix);
            r4.append("\",");
            r4.append('\n');
            r4.append(r5);
            r4.append("suffix = \"");
            r4.append(this.suffix);
            r4.append("\",");
            r4.append('\n');
            r4.append(r5);
            r4.append("removeLeadingZeros = ");
            r4.append(this.removeLeadingZeros);
            r4.append(',');
            r4.append('\n');
            r4.append(r5);
            r4.append("minLength = ");
            r4.append(this.minLength);
            return r4;
        }

        public final boolean getIgnoreCase$kotlin_stdlib() {
            return this.ignoreCase;
        }

        public final int getMinLength() {
            return this.minLength;
        }

        public final String getPrefix() {
            return this.prefix;
        }

        public final boolean getRemoveLeadingZeros() {
            return this.removeLeadingZeros;
        }

        public final String getSuffix() {
            return this.suffix;
        }

        public final boolean isDigitsOnly$kotlin_stdlib() {
            return this.isDigitsOnly;
        }

        public final boolean isDigitsOnlyAndNoPadding$kotlin_stdlib() {
            return this.isDigitsOnlyAndNoPadding;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder();
            r0.append("NumberHexFormat(\n");
            appendOptionsTo$kotlin_stdlib(r0, "    ").append('\n');
            r0.append(")");
            return r0.toString();
        }
    }

    static {
        Companion = new Companion(null);
        BytesHexFormat.Companion r1 = BytesHexFormat.Companion;
        BytesHexFormat r2 = r1.getDefault$kotlin_stdlib();
        NumberHexFormat.Companion r3 = NumberHexFormat.Companion;
        Default = new HexFormat(false, r2, r3.getDefault$kotlin_stdlib());
        UpperCase = new HexFormat(true, r1.getDefault$kotlin_stdlib(), r3.getDefault$kotlin_stdlib());
    }

    public HexFormat(boolean r1, BytesHexFormat r2, NumberHexFormat r3) {
        this.upperCase = r1;
        this.bytes = r2;
        this.number = r3;
    }

    public static final /* synthetic */ HexFormat access$getDefault$cp() {
        return Default;
    }

    public static final /* synthetic */ HexFormat access$getUpperCase$cp() {
        return UpperCase;
    }

    public final BytesHexFormat getBytes() {
        return this.bytes;
    }

    public final NumberHexFormat getNumber() {
        return this.number;
    }

    public final boolean getUpperCase() {
        return this.upperCase;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append("HexFormat(\n    upperCase = ");
        r0.append(this.upperCase);
        r0.append(",\n    bytes = BytesHexFormat(\n");
        this.bytes.appendOptionsTo$kotlin_stdlib(r0, "        ").append('\n');
        r0.append("    ),");
        r0.append('\n');
        r0.append("    number = NumberHexFormat(");
        r0.append('\n');
        this.number.appendOptionsTo$kotlin_stdlib(r0, "        ").append('\n');
        r0.append("    )");
        r0.append('\n');
        r0.append(")");
        return r0.toString();
    }
}
