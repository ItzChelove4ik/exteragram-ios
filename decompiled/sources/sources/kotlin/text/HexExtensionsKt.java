package kotlin.text;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;
import kotlin.ExperimentalStdlibApi;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.WasExperimental;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.HexFormat;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0019\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\n\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u0018\u0010\u000b\u001a\u00020\u0001*\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0087\u0080\u0004\u001a,\u0010\u000b\u001a\u00020\u0001*\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0087\u0080\u0004\u001a.\u0010\u0012\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0082\u0080\u0004\u001a.\u0010\u0016\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0082\u0080\u0004\u001a.\u0010\u0017\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0082\u0080\u0004\u001a.\u0010\u0018\u001a\u00020\u0001*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0004H\u0082\u0080\u0004\u001a>\u0010\u0019\u001a\u00020\u0010*\u00020\f2\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0010H\u0082\u0080\u0004\u001a.\u0010\u0019\u001a\u00020\u0010*\u00020\f2\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0010H\u0082\u0080\u0004\u001a*\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u0010H\u0082\u0080\u0004\u001aB\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u0010H\u0080\u0080\u0004\u001a\u0012\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020*H\u0082\u0080\u0004\u001a\u0018\u0010+\u001a\u00020\f*\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0087\u0080\u0004\u001a,\u0010+\u001a\u00020\f*\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0082\u0080\u0004\u001a(\u0010,\u001a\u0004\u0018\u00010\f*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0082\u0080\u0004\u001a(\u0010-\u001a\u0004\u0018\u00010\f*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0082\u0080\u0004\u001a(\u0010.\u001a\u0004\u0018\u00010\f*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0082\u0080\u0004\u001a&\u0010/\u001a\u00020\f*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0082\u0080\u0004\u001a\u0016\u00100\u001a\u000201*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0010H\u0082\u0080\u0004\u001aB\u00102\u001a\u00020\u00102\u0006\u00103\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u0010H\u0080\u0080\u0004\u001a\"\u00104\u001a\u00020*2\u0006\u00105\u001a\u00020*2\u0006\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u0010H\u0082\u0080\u0004\u001a\"\u00108\u001a\u00020*2\u0006\u00104\u001a\u00020*2\u0006\u00105\u001a\u00020*2\u0006\u00107\u001a\u00020\u0010H\u0082\u0080\u0004\u001a\u001e\u00109\u001a\u00020\u0010*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0082\u0080\u0004\u001a\u0018\u0010\u000b\u001a\u00020\u0001*\u0002012\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0087\u0080\u0004\u001a\u0018\u0010:\u001a\u000201*\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0087\u0080\u0004\u001a,\u0010:\u001a\u000201*\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0082\u0080\u0004\u001a\u0018\u0010\u000b\u001a\u00020\u0001*\u00020;2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0087\u0080\u0004\u001a\u0018\u0010<\u001a\u00020;*\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0087\u0080\u0004\u001a,\u0010<\u001a\u00020;*\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0082\u0080\u0004\u001a\u0018\u0010\u000b\u001a\u00020\u0001*\u00020\u00102\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0087\u0080\u0004\u001a\u0018\u0010=\u001a\u00020\u0010*\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0087\u0080\u0004\u001a,\u0010=\u001a\u00020\u0010*\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0080\u0080\u0004\u001a\u0018\u0010\u000b\u001a\u00020\u0001*\u00020*2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0087\u0080\u0004\u001a\u0018\u0010>\u001a\u00020**\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0087\u0080\u0004\u001a,\u0010>\u001a\u00020**\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0080\u0080\u0004\u001a&\u0010?\u001a\u00020\u0001*\u00020*2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u00012\u0006\u0010C\u001a\u00020\u0010H\u0082\u0080\u0004\u001a\u001e\u0010D\u001a\u00020\u0010*\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0010H\u0082\u0080\u0004\u001a.\u0010E\u001a\u00020\u0010*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010F\u001a\u00020\u0010H\u0082\u0080\u0004\u001a.\u0010G\u001a\u00020**\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010F\u001a\u00020\u0010H\u0082\u0080\u0004\u001a>\u0010H\u001a\u00020I*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010J\u001a\u00020\u00012\u0006\u0010K\u001a\u00020\u00012\u0006\u0010L\u001a\u00020M2\u0006\u0010F\u001a\u00020\u0010H\u0082\u0080\u0004\u001a&\u0010N\u001a\u00020I*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010F\u001a\u00020\u0010H\u0082\u0080\u0004\u001a\u001e\u0010O\u001a\u00020I*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0082\u0080\u0004\u001a\u001e\u0010P\u001a\u00020\u0010*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0082\u0080\u0004\u001a@\u0010Q\u001a\u00020\u0010*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u001d\u0010R\u001a\u0019\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020T0S¢\u0006\u0002\bUH\u0080\u0088\u0004ø\u0001\u0000\u001a\u001e\u0010V\u001a\u00020**\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0082\u0080\u0004\u001a@\u0010W\u001a\u00020**\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u001d\u0010R\u001a\u0019\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020T0S¢\u0006\u0002\bUH\u0080\u0088\u0004ø\u0001\u0000\u001a6\u0010X\u001a\u00020\u0010*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010Y\u001a\u00020\u00012\u0006\u0010L\u001a\u00020M2\u0006\u0010Z\u001a\u00020\u0001H\u0083\u0088\b\u001a5\u0010[\u001a\u00020\u0010*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00102\u001d\u0010R\u001a\u0019\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020T0S¢\u0006\u0002\bUH\u0082\u0088\u0004\u001a5\u0010\\\u001a\u00020**\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00102\u001d\u0010R\u001a\u0019\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020T0S¢\u0006\u0002\bUH\u0082\u0088\u0004\u001a.\u0010]\u001a\u00020I*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010^\u001a\u00020\u00012\u0006\u0010_\u001a\u00020\u0010H\u0082\u0080\u0004\u001a.\u0010`\u001a\u00020I*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010Y\u001a\u00020\u00012\u0006\u0010Z\u001a\u00020\u0001H\u0082\u0080\u0004\u001a.\u0010a\u001a\u00020I*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010J\u001a\u00020\u00012\u0006\u0010K\u001a\u00020\u0001H\u0082\u0080\u0004\u001a\u0016\u0010b\u001a\u00020T*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0010H\u0082\u0080\u0004\"\u000f\u0010\u0000\u001a\u00020\u0001X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0002\u001a\u00020\u0001X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u0015\u0010\u0003\u001a\u00020\u0004X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u000f\u0010\u0007\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000\"\u000f\u0010\b\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000\"\u000f\u0010\t\u001a\u00020\nX\u0082\u0084\b¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006c"}, d2 = {"LOWER_CASE_HEX_DIGITS", _UrlKt.FRAGMENT_ENCODE_SET, "UPPER_CASE_HEX_DIGITS", "BYTE_TO_LOWER_CASE_HEX_DIGITS", _UrlKt.FRAGMENT_ENCODE_SET, "getBYTE_TO_LOWER_CASE_HEX_DIGITS", "()[I", "BYTE_TO_UPPER_CASE_HEX_DIGITS", "HEX_DIGITS_TO_DECIMAL", "HEX_DIGITS_TO_LONG_DECIMAL", _UrlKt.FRAGMENT_ENCODE_SET, "toHexString", _UrlKt.FRAGMENT_ENCODE_SET, "format", "Lkotlin/text/HexFormat;", "startIndex", _UrlKt.FRAGMENT_ENCODE_SET, "endIndex", "toHexStringNoLineAndGroupSeparator", "bytesFormat", "Lkotlin/text/HexFormat$BytesHexFormat;", "byteToDigits", "toHexStringShortByteSeparatorNoPrefixAndSuffix", "toHexStringNoLineAndGroupSeparatorSlowPath", "toHexStringSlowPath", "formatByteAt", "index", "bytePrefix", "byteSuffix", "destination", _UrlKt.FRAGMENT_ENCODE_SET, "destinationOffset", "formattedStringLength", "numberOfBytes", "byteSeparatorLength", "bytePrefixLength", "byteSuffixLength", "bytesPerLine", "bytesPerGroup", "groupSeparatorLength", "checkFormatLength", "formatLength", _UrlKt.FRAGMENT_ENCODE_SET, "hexToByteArray", "hexToByteArrayNoLineAndGroupSeparator", "hexToByteArrayShortByteSeparatorNoPrefixAndSuffix", "hexToByteArrayNoLineAndGroupSeparatorSlowPath", "hexToByteArraySlowPath", "parseByteAt", _UrlKt.FRAGMENT_ENCODE_SET, "parsedByteArrayMaxSize", "stringLength", "charsPerSet", "charsPerElement", "elementsPerSet", "elementSeparatorLength", "wholeElementsPerSet", "checkNewLineAt", "hexToByte", _UrlKt.FRAGMENT_ENCODE_SET, "hexToShort", "hexToInt", "hexToLong", "toHexStringImpl", "numberFormat", "Lkotlin/text/HexFormat$NumberHexFormat;", "digits", "bits", "toCharArrayIfNotEmpty", "hexToIntImpl", "typeHexLength", "hexToLongImpl", "checkPrefixSuffixNumberOfDigits", _UrlKt.FRAGMENT_ENCODE_SET, "prefix", "suffix", "ignoreCase", _UrlKt.FRAGMENT_ENCODE_SET, "checkNumberOfDigits", "checkZeroDigits", "parseInt", "parseHexToInt", "onError", "Lkotlin/Function2;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "parseLong", "parseHexToLong", "checkContainsAt", "part", "partName", "decimalFromHexDigitAt", "longDecimalFromHexDigitAt", "throwInvalidNumberOfDigits", "specifier", "expected", "throwNotContainedAt", "throwInvalidPrefixSuffix", "throwInvalidDigitAt", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHexExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HexExtensions.kt\nkotlin/text/HexExtensionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,1260:1\n1211#1,7:1262\n1211#1,7:1269\n1211#1,7:1276\n1211#1,7:1283\n1211#1,7:1290\n1211#1,7:1297\n1211#1,7:1304\n1211#1,7:1311\n1221#1,5:1318\n1221#1,5:1323\n1211#1,7:1328\n1211#1,7:1335\n1181#1,3:1342\n1221#1,5:1345\n1185#1:1350\n1221#1,5:1351\n1201#1,3:1356\n1229#1,5:1359\n1205#1:1364\n1229#1,5:1365\n1#2:1261\n1207#3,3:1370\n1207#3,3:1373\n1207#3,3:1376\n1207#3,3:1379\n*S KotlinDebug\n*F\n+ 1 HexExtensions.kt\nkotlin/text/HexExtensionsKt\n*L\n450#1:1262,7\n482#1:1269,7\n486#1:1276,7\n489#1:1283,7\n529#1:1290,7\n532#1:1297,7\n537#1:1304,7\n542#1:1311,7\n549#1:1318,5\n550#1:1323,5\n1141#1:1328,7\n1143#1:1335,7\n1169#1:1342,3\n1169#1:1345,5\n1169#1:1350\n1183#1:1351,5\n1189#1:1356,3\n1189#1:1359,5\n1189#1:1364\n1203#1:1365,5\n42#1:1370,3\n43#1:1373,3\n54#1:1376,3\n55#1:1379,3\n*E\n"})
/* loaded from: classes5.dex */
public final class HexExtensionsKt {
    private static final int[] BYTE_TO_LOWER_CASE_HEX_DIGITS = null;
    private static final int[] BYTE_TO_UPPER_CASE_HEX_DIGITS = null;
    private static final int[] HEX_DIGITS_TO_DECIMAL = null;
    private static final long[] HEX_DIGITS_TO_LONG_DECIMAL = null;
    private static final String LOWER_CASE_HEX_DIGITS = "0123456789abcdef";
    private static final String UPPER_CASE_HEX_DIGITS = "0123456789ABCDEF";

    static {
        int[] r1 = new int[256];
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r3 >= 256) goto L6;
        r1[r3] = LOWER_CASE_HEX_DIGITS.charAt(r3 & 15) | (LOWER_CASE_HEX_DIGITS.charAt(r3 >> 4) << '\b');
        r3 = r3 + 1;
        goto L4
    L6:
        BYTE_TO_LOWER_CASE_HEX_DIGITS = r1;
        int[] r12 = new int[256];
        int r32 = 0;
    L8:
        if (r32 >= 256) goto L10;
        r12[r32] = UPPER_CASE_HEX_DIGITS.charAt(r32 & 15) | (UPPER_CASE_HEX_DIGITS.charAt(r32 >> 4) << '\b');
        r32 = r32 + 1;
        goto L8
    L10:
        BYTE_TO_UPPER_CASE_HEX_DIGITS = r12;
        int[] r13 = new int[256];
        int r33 = 0;
    L11:
        if (r33 >= 256) goto L13;
        r13[r33] = -1;
        r33 = r33 + 1;
        goto L11
    L13:
        int r34 = 0;
        int r6 = 0;
    L15:
        if (r34 >= LOWER_CASE_HEX_DIGITS.length()) goto L17;
        r13[LOWER_CASE_HEX_DIGITS.charAt(r34)] = r6;
        r34 = r34 + 1;
        r6 = r6 + 1;
        goto L15
    L17:
        int r35 = 0;
        int r62 = 0;
    L19:
        if (r35 >= UPPER_CASE_HEX_DIGITS.length()) goto L21;
        r13[UPPER_CASE_HEX_DIGITS.charAt(r35)] = r62;
        r35 = r35 + 1;
        r62 = r62 + 1;
        goto L19
    L21:
        HEX_DIGITS_TO_DECIMAL = r13;
        long[] r14 = new long[256];
        int r36 = 0;
    L22:
        if (r36 >= 256) goto L24;
        r14[r36] = -1;
        r36 = r36 + 1;
        goto L22
    L24:
        int r0 = 0;
        int r37 = 0;
    L26:
        if (r0 >= LOWER_CASE_HEX_DIGITS.length()) goto L28;
        r14[LOWER_CASE_HEX_DIGITS.charAt(r0)] = r37;
        r0 = r0 + 1;
        r37 = r37 + 1;
        goto L26
    L28:
        int r02 = 0;
    L30:
        if (r2 >= UPPER_CASE_HEX_DIGITS.length()) goto L32;
        r14[UPPER_CASE_HEX_DIGITS.charAt(r2)] = r02;
        r2 = r2 + 1;
        r02 = r02 + 1;
        goto L30
    L32:
        HEX_DIGITS_TO_LONG_DECIMAL = r14;
    }

    public static final /* synthetic */ int[] access$getHEX_DIGITS_TO_DECIMAL$p() {
        return HEX_DIGITS_TO_DECIMAL;
    }

    public static final /* synthetic */ long[] access$getHEX_DIGITS_TO_LONG_DECIMAL$p() {
        return HEX_DIGITS_TO_LONG_DECIMAL;
    }

    private static final long charsPerSet(long r4, int r6, int r7) {
        if (r6 <= 0) goto L5;
        long r0 = r6;
        return (r4 * r0) + (r7 * (r0 - 1));
    L5:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return 0;
    }

    @IgnorableReturnValue
    private static final int checkContainsAt(String r4, int r5, int r6, String r7, boolean r8, String r9) {
        if (r7.length() != 0) goto L5;
        return r5;
    L5:
        int r0 = r7.length();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L12;
        if (CharsKt__CharKt.equals(r7.charAt(r1), r4.charAt(r5 + r1), r8) == true) goto L10;
        throwNotContainedAt(r4, r5, r6, r7, r9);
    L10:
        r1 = r1 + 1;
        goto L6
    L12:
        return r5 + r7.length();
    }

    private static final int checkFormatLength(long r2) {
        if (0 <= r2) goto L5;
    L8:
        Buffer$$ExternalSyntheticBUOutline4.m("The resulting string length is too big: ", ULong.m2238toStringimpl(ULong.m2192constructorimpl(r2)));
        return 0;
    L5:
        if (r2 > 2147483647L) goto L8;
        return (int) r2;
    }

    private static final int checkNewLineAt(String r3, int r4, int r5) {
        if (r3.charAt(r4) != '\r') goto L12;
        int r0 = r4 + 1;
        if (r0 < r5) goto L7;
    L10:
        return r0;
    L7:
        if (r3.charAt(r0) != '\n') goto L10;
        return r4 + 2;
    L12:
        if (r3.charAt(r4) != '\n') goto L16;
        return r4 + 1;
    L16:
        throw new NumberFormatException("Expected a new line at index " + r4 + ", but was " + r3.charAt(r4));
    }

    private static final void checkNumberOfDigits(String r2, int r3, int r4, int r5) {
        int r0 = r4 - r3;
        if (r0 >= 1) goto L6;
        throwInvalidNumberOfDigits(r2, r3, r4, "at least", 1);
        return;
    L6:
        if (r0 <= r5) goto L9;
        checkZeroDigits(r2, r3, (r0 + r3) - r5);
        return;
    }

    private static final void checkPrefixSuffixNumberOfDigits(String r5, int r6, int r7, String r8, String r9, boolean r10, int r11) {
        if (((r7 - r6) - r8.length()) > r9.length()) goto L5;
        throwInvalidPrefixSuffix(r5, r6, r7, r8, r9);
    L5:
        int r1 = 0;
        if (r8.length() == 0) goto L15;
        int r0 = r8.length();
        int r2 = 0;
    L9:
        if (r2 >= r0) goto L14;
        if (CharsKt__CharKt.equals(r8.charAt(r2), r5.charAt(r6 + r2), r10) == true) goto L13;
        throwNotContainedAt(r5, r6, r7, r8, "prefix");
    L13:
        r2 = r2 + 1;
        goto L9
    L14:
        r6 = r6 + r8.length();
    L15:
        int r82 = r7 - r9.length();
        if (r9.length() == 0) goto L24;
        int r02 = r9.length();
    L19:
        if (r1 >= r02) goto L24;
        if (CharsKt__CharKt.equals(r9.charAt(r1), r5.charAt(r82 + r1), r10) == true) goto L23;
        throwNotContainedAt(r5, r82, r7, r9, "suffix");
    L23:
        r1 = r1 + 1;
    L24:
        checkNumberOfDigits(r5, r6, r82, r11);
    }

    private static final void checkZeroDigits(String r2, int r3, int r4) {
    L2:
        if (r3 >= r4) goto L8;
        if (r2.charAt(r3) != '0') goto L7;
        r3 = r3 + 1;
        goto L2
    L7:
        throw new NumberFormatException("Expected the hexadecimal digit '0' at index " + r3 + ", but was '" + r2.charAt(r3) + "'.\nThe result won't fit the type being parsed.");
    }

    private static final int decimalFromHexDigitAt(String r2, int r3, Function2 r4) {
        char r0 = r2.charAt(r3);
        if ((r0 >>> '\b') == 0) goto L5;
    L8:
        r4.invoke(r2, Integer.valueOf(r3));
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        if (access$getHEX_DIGITS_TO_DECIMAL$p()[r0] < 0) goto L8;
        return access$getHEX_DIGITS_TO_DECIMAL$p()[r0];
    }

    private static final int formatByteAt(byte[] r0, int r1, String r2, String r3, int[] r4, char[] r5, int r6) {
        return toCharArrayIfNotEmpty(r3, r5, formatByteAt(r0, r1, r4, r5, toCharArrayIfNotEmpty(r2, r5, r6)));
    }

    private static final int formatByteAt(byte[] r0, int r1, int[] r2, char[] r3, int r4) {
        int r02 = r2[r0[r1] & UByte.MAX_VALUE];
        r3[r4] = (char) (r02 >> 8);
        r3[r4 + 1] = (char) (r02 & 255);
        return r4 + 2;
    }

    private static final int formattedStringLength(int r4, int r5, int r6, int r7) {
        if (r4 <= 0) goto L5;
        long r52 = r5;
        return checkFormatLength((r4 * (((r6 + 2) + r7) + r52)) - r52);
    L5:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return 0;
    }

    public static final int formattedStringLength(int r5, int r6, int r7, int r8, int r9, int r10, int r11) {
        if (r5 <= 0) goto L9;
        int r0 = r5 - 1;
        int r1 = r0 / r6;
        int r2 = (r6 - 1) / r7;
        int r3 = r5 % r6;
        if (r3 == 0) goto L7;
        r6 = r3;
    L7:
        int r22 = (r2 * r1) + ((r6 - 1) / r7);
        return checkFormatLength(((r1 + (r22 * r8)) + (((r0 - r1) - r22) * r9)) + (r5 * ((r10 + 2) + r11)));
    L9:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return 0;
    }

    public static final int[] getBYTE_TO_LOWER_CASE_HEX_DIGITS() {
        return BYTE_TO_LOWER_CASE_HEX_DIGITS;
    }

    private static final byte hexToByte(String r1, int r2, int r3, HexFormat r4) {
        return (byte) hexToIntImpl(r1, r2, r3, r4, 2);
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final byte hexToByte(String r2, HexFormat r3) {
        return hexToByte(r2, 0, r2.length(), r3);
    }

    public static /* synthetic */ byte hexToByte$default(String r0, int r1, int r2, HexFormat r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = r0.length();
    L9:
        if ((r4 & 4) == 0) goto L12;
        r3 = HexFormat.Companion.getDefault();
    L12:
        return hexToByte(r0, r1, r2, r3);
    }

    public static /* synthetic */ byte hexToByte$default(String r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return hexToByte(r0, r1);
    }

    private static final byte[] hexToByteArray(String r2, int r3, int r4, HexFormat r5) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(r3, r4, r2.length());
        if (r3 == r4) goto L5;
        HexFormat.BytesHexFormat r52 = r5.getBytes();
        if (r52.getNoLineAndGroupSeparator$kotlin_stdlib() == false) goto L12;
        byte[] r0 = hexToByteArrayNoLineAndGroupSeparator(r2, r3, r4, r52);
        if (r0 == null) goto L12;
        return r0;
    L12:
        return hexToByteArraySlowPath(r2, r3, r4, r52);
    L5:
        return new byte[0];
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final byte[] hexToByteArray(String r2, HexFormat r3) {
        return hexToByteArray(r2, 0, r2.length(), r3);
    }

    public static /* synthetic */ byte[] hexToByteArray$default(String r0, int r1, int r2, HexFormat r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = r0.length();
    L9:
        if ((r4 & 4) == 0) goto L12;
        r3 = HexFormat.Companion.getDefault();
    L12:
        return hexToByteArray(r0, r1, r2, r3);
    }

    public static /* synthetic */ byte[] hexToByteArray$default(String r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return hexToByteArray(r0, r1);
    }

    private static final byte[] hexToByteArrayNoLineAndGroupSeparator(String r1, int r2, int r3, HexFormat.BytesHexFormat r4) {
        if (r4.getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib() == false) goto L7;
        return hexToByteArrayShortByteSeparatorNoPrefixAndSuffix(r1, r2, r3, r4);
    L7:
        return hexToByteArrayNoLineAndGroupSeparatorSlowPath(r1, r2, r3, r4);
    }

    private static final byte[] hexToByteArrayNoLineAndGroupSeparatorSlowPath(String r12, int r13, int r14, HexFormat.BytesHexFormat r15) {
        String r0 = r15.getBytePrefix();
        String r1 = r15.getByteSuffix();
        String r2 = r15.getByteSeparator();
        long r6 = r2.length();
        long r4 = ((r0.length() + 2) + r1.length()) + r6;
        long r8 = r14 - r13;
        int r3 = (int) ((r8 + r6) / r4);
        if (((r3 * r4) - r6) == r8) goto L6;
        return null;
    L6:
        boolean r152 = r15.getIgnoreCase$kotlin_stdlib();
        byte[] r42 = new byte[r3];
        int r62 = 0;
        if (r0.length() == 0) goto L16;
        int r5 = r0.length();
        int r7 = 0;
    L10:
        if (r7 >= r5) goto L15;
        if (CharsKt__CharKt.equals(r0.charAt(r7), r12.charAt(r13 + r7), r152) == true) goto L14;
        throwNotContainedAt(r12, r13, r14, r0, "byte prefix");
    L14:
        r7 = r7 + 1;
        goto L10
    L15:
        r13 = r13 + r0.length();
    L16:
        String r02 = r1 + r2 + r0;
        int r32 = r3 - 1;
        int r22 = 0;
    L17:
        if (r22 >= r32) goto L29;
        r42[r22] = parseByteAt(r12, r13);
        r13 = r13 + 2;
        if (r02.length() == 0) goto L28;
        int r52 = r02.length();
        int r72 = 0;
    L22:
        if (r72 >= r52) goto L27;
        if (CharsKt__CharKt.equals(r02.charAt(r72), r12.charAt(r13 + r72), r152) == true) goto L26;
        throwNotContainedAt(r12, r13, r14, r02, "byte suffix + byte separator + byte prefix");
    L26:
        r72 = r72 + 1;
        goto L22
    L27:
        r13 = r13 + r02.length();
    L28:
        r22 = r22 + 1;
        goto L17
    L29:
        r42[r32] = parseByteAt(r12, r13);
        int r132 = r13 + 2;
        if (r1.length() != 0) goto L32;
        return r42;
    L32:
        int r03 = r1.length();
    L33:
        if (r62 >= r03) goto L38;
        if (CharsKt__CharKt.equals(r1.charAt(r62), r12.charAt(r132 + r62), r152) == true) goto L37;
        throwNotContainedAt(r12, r132, r14, r1, "byte suffix");
    L37:
        r62 = r62 + 1;
        goto L33
    L38:
        return r42;
    }

    private static final byte[] hexToByteArrayShortByteSeparatorNoPrefixAndSuffix(String r11, int r12, int r13, HexFormat.BytesHexFormat r14) {
        int r0 = r14.getByteSeparator().length();
        int r2 = 1;
        if (r0 > 1) goto L32;
        int r122 = r13 - r12;
        int r3 = 2;
        int r4 = 0;
        if (r0 != 0) goto L14;
        if ((r122 & 1) == 0) goto L9;
        return null;
    L9:
        int r123 = r122 >> 1;
        byte[] r132 = new byte[r123];
        int r142 = 0;
    L10:
        if (r4 >= r123) goto L12;
        r132[r4] = parseByteAt(r11, r142);
        r142 = r142 + 2;
        r4 = r4 + 1;
        goto L10
    L12:
        return r132;
    L14:
        if ((r122 % 3) == 2) goto L16;
        return null;
    L16:
        int r124 = (r122 / 3) + 1;
        byte[] r02 = new byte[r124];
        char r1 = r14.getByteSeparator().charAt(0);
        r02[0] = parseByteAt(r11, 0);
    L17:
        if (r2 >= r124) goto L31;
        if (r11.charAt(r3) == r1) goto L30;
        String r5 = r14.getByteSeparator();
        boolean r6 = r14.getIgnoreCase$kotlin_stdlib();
        if (r5.length() == 0) goto L30;
        int r7 = r5.length();
        int r8 = 0;
    L24:
        if (r8 >= r7) goto L29;
        if (CharsKt__CharKt.equals(r5.charAt(r8), r11.charAt(r3 + r8), r6) == true) goto L28;
        throwNotContainedAt(r11, r3, r13, r5, "byte separator");
    L28:
        r8 = r8 + 1;
        goto L24
    L29:
        r5.length();
    L30:
        r02[r2] = parseByteAt(r11, r3 + 1);
        r3 = r3 + 3;
        r2 = r2 + 1;
        goto L17
    L31:
        return r02;
    L32:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return null;
    }

    private static final byte[] hexToByteArraySlowPath(String r18, int r19, int r20, HexFormat.BytesHexFormat r21) {
        int r3 = r21.getBytesPerLine();
        int r4 = r21.getBytesPerGroup();
        String r9 = r21.getBytePrefix();
        String r10 = r21.getByteSuffix();
        String r11 = r21.getByteSeparator();
        String r12 = r21.getGroupSeparator();
        boolean r13 = r21.getIgnoreCase$kotlin_stdlib();
        int r2 = parsedByteArrayMaxSize(r20 - r19, r3, r4, r12.length(), r11.length(), r9.length(), r10.length());
        byte[] r5 = new byte[r2];
        int r7 = r19;
        int r8 = 0;
        int r14 = 0;
        int r15 = 0;
    L3:
        if (r7 >= r20) goto L54;
        if (r14 != r3) goto L7;
        r7 = checkNewLineAt(r18, r7, r20);
        int r16 = r3;
        int r17 = r4;
        r14 = 0;
    L6:
        r15 = 0;
    L30:
        r14 = r14 + 1;
        r15 = r15 + 1;
        if (r9.length() == 0) goto L41;
        int r32 = r9.length();
        int r42 = 0;
    L34:
        if (r42 >= r32) goto L39;
        int r192 = r32;
        if (CharsKt__CharKt.equals(r9.charAt(r42), r18.charAt(r7 + r42), r13) == true) goto L38;
        throwNotContainedAt(r18, r7, r20, r9, "byte prefix");
    L38:
        r42 = r42 + 1;
        r32 = r192;
        goto L34
    L39:
        r7 = r7 + r9.length();
    L41:
        if ((r20 - 2) >= r7) goto L43;
        throwInvalidNumberOfDigits(r18, r7, r20, "exactly", 2);
    L43:
        int r33 = r8 + 1;
        r5[r8] = parseByteAt(r18, r7);
        r7 = r7 + 2;
        if (r10.length() != 0) goto L46;
        int r193 = r33;
    L53:
        r8 = r193;
        r3 = r16;
        r4 = r17;
        goto L3
    L46:
        int r43 = r10.length();
        int r6 = 0;
    L47:
        if (r6 >= r43) goto L52;
        int r194 = r33;
        if (CharsKt__CharKt.equals(r10.charAt(r6), r18.charAt(r7 + r6), r13) == true) goto L51;
        throwNotContainedAt(r18, r7, r20, r10, "byte suffix");
    L51:
        r6 = r6 + 1;
        r33 = r194;
        goto L47
    L52:
        r193 = r33;
        r7 = r7 + r10.length();
        goto L53
    L7:
        if (r15 == r4) goto L9;
        r16 = r3;
        r17 = r4;
        if (r15 == 0) goto L30;
        if (r11.length() == 0) goto L30;
        int r34 = r11.length();
        int r44 = 0;
    L24:
        if (r44 >= r34) goto L29;
        int r195 = r34;
        if (CharsKt__CharKt.equals(r11.charAt(r44), r18.charAt(r7 + r44), r13) == true) goto L28;
        throwNotContainedAt(r18, r7, r20, r11, "byte separator");
    L28:
        r44 = r44 + 1;
        r34 = r195;
        goto L24
    L29:
        r7 = r7 + r11.length();
        goto L30
    L9:
        if (r12.length() != 0) goto L11;
        r16 = r3;
        r17 = r4;
        goto L6
    L11:
        int r152 = r12.length();
        int r62 = 0;
    L12:
        if (r62 >= r152) goto L17;
        int r162 = r3;
        int r172 = r4;
        if (CharsKt__CharKt.equals(r12.charAt(r62), r18.charAt(r7 + r62), r13) == true) goto L16;
        throwNotContainedAt(r18, r7, r20, r12, "group separator");
    L16:
        r62 = r62 + 1;
        r3 = r162;
        r4 = r172;
        goto L12
    L17:
        r16 = r3;
        r17 = r4;
        r7 = r7 + r12.length();
        goto L6
    L54:
        if (r8 != r2) goto L57;
        return r5;
    L57:
        return Arrays.copyOf(r5, r8);
    }

    public static final int hexToInt(String r1, int r2, int r3, HexFormat r4) {
        return hexToIntImpl(r1, r2, r3, r4, 8);
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final int hexToInt(String r2, HexFormat r3) {
        return hexToInt(r2, 0, r2.length(), r3);
    }

    public static /* synthetic */ int hexToInt$default(String r0, int r1, int r2, HexFormat r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = r0.length();
    L9:
        if ((r4 & 4) == 0) goto L12;
        r3 = HexFormat.Companion.getDefault();
    L12:
        return hexToInt(r0, r1, r2, r3);
    }

    public static /* synthetic */ int hexToInt$default(String r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return hexToInt(r0, r1);
    }

    private static final int hexToIntImpl(String r7, int r8, int r9, HexFormat r10, int r11) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(r8, r9, r7.length());
        HexFormat.NumberHexFormat r102 = r10.getNumber();
        if (r102.isDigitsOnly$kotlin_stdlib() == false) goto L6;
        checkNumberOfDigits(r7, r8, r9, r11);
        return parseInt(r7, r8, r9);
    L6:
        String r3 = r102.getPrefix();
        String r4 = r102.getSuffix();
        checkPrefixSuffixNumberOfDigits(r7, r8, r9, r3, r4, r102.getIgnoreCase$kotlin_stdlib(), r11);
        return parseInt(r7, r8 + r3.length(), r9 - r4.length());
    }

    public static final long hexToLong(String r1, int r2, int r3, HexFormat r4) {
        return hexToLongImpl(r1, r2, r3, r4, 16);
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final long hexToLong(String r2, HexFormat r3) {
        return hexToLong(r2, 0, r2.length(), r3);
    }

    public static /* synthetic */ long hexToLong$default(String r0, int r1, int r2, HexFormat r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = r0.length();
    L9:
        if ((r4 & 4) == 0) goto L12;
        r3 = HexFormat.Companion.getDefault();
    L12:
        return hexToLong(r0, r1, r2, r3);
    }

    public static /* synthetic */ long hexToLong$default(String r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return hexToLong(r0, r1);
    }

    private static final long hexToLongImpl(String r7, int r8, int r9, HexFormat r10, int r11) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(r8, r9, r7.length());
        HexFormat.NumberHexFormat r102 = r10.getNumber();
        if (r102.isDigitsOnly$kotlin_stdlib() == false) goto L6;
        checkNumberOfDigits(r7, r8, r9, r11);
        return parseLong(r7, r8, r9);
    L6:
        String r3 = r102.getPrefix();
        String r4 = r102.getSuffix();
        checkPrefixSuffixNumberOfDigits(r7, r8, r9, r3, r4, r102.getIgnoreCase$kotlin_stdlib(), r11);
        return parseLong(r7, r8 + r3.length(), r9 - r4.length());
    }

    private static final short hexToShort(String r1, int r2, int r3, HexFormat r4) {
        return (short) hexToIntImpl(r1, r2, r3, r4, 4);
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final short hexToShort(String r2, HexFormat r3) {
        return hexToShort(r2, 0, r2.length(), r3);
    }

    public static /* synthetic */ short hexToShort$default(String r0, int r1, int r2, HexFormat r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = r0.length();
    L9:
        if ((r4 & 4) == 0) goto L12;
        r3 = HexFormat.Companion.getDefault();
    L12:
        return hexToShort(r0, r1, r2, r3);
    }

    public static /* synthetic */ short hexToShort$default(String r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return hexToShort(r0, r1);
    }

    private static final long longDecimalFromHexDigitAt(String r6, int r7, Function2 r8) {
        char r0 = r6.charAt(r7);
        if ((r0 >>> '\b') == 0) goto L5;
    L8:
        r8.invoke(r6, Integer.valueOf(r7));
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        if (access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r0] < 0) goto L8;
        return access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r0];
    }

    private static final byte parseByteAt(String r4, int r5) {
        char r0 = r4.charAt(r5);
        if ((r0 >>> '\b') == 0) goto L5;
    L14:
        throwInvalidDigitAt(r4, r5);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        if (access$getHEX_DIGITS_TO_DECIMAL$p()[r0] < 0) goto L14;
        int r02 = access$getHEX_DIGITS_TO_DECIMAL$p()[r0];
        int r52 = r5 + 1;
        char r1 = r4.charAt(r52);
        if ((r1 >>> '\b') == 0) goto L9;
    L12:
        throwInvalidDigitAt(r4, r52);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L9:
        if (access$getHEX_DIGITS_TO_DECIMAL$p()[r1] < 0) goto L12;
        return (byte) (access$getHEX_DIGITS_TO_DECIMAL$p()[r1] | (r02 << 4));
    }

    public static final int parseHexToInt(String r4, int r5, int r6, Function2 r7) {
        int r1 = 0;
    L3:
        if (r5 >= r6) goto L11;
        int r12 = r1 << 4;
        char r2 = r4.charAt(r5);
        if ((r2 >>> '\b') != 0) goto L9;
        if (access$getHEX_DIGITS_TO_DECIMAL$p()[r2] < 0) goto L9;
        r1 = r12 | access$getHEX_DIGITS_TO_DECIMAL$p()[r2];
        r5 = r5 + 1;
    L9:
        r7.invoke(r4, Integer.valueOf(r5));
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L11:
        return r1;
    }

    public static final long parseHexToLong(String r8, int r9, int r10, Function2 r11) {
        long r2 = 0;
    L3:
        if (r9 >= r10) goto L11;
        long r22 = r2 << 4;
        char r4 = r8.charAt(r9);
        if ((r4 >>> '\b') != 0) goto L9;
        if (access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r4] < 0) goto L9;
        r2 = r22 | access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r4];
        r9 = r9 + 1;
    L9:
        r11.invoke(r8, Integer.valueOf(r9));
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L11:
        return r2;
    }

    private static final int parseInt(String r4, int r5, int r6) {
        int r1 = 0;
    L3:
        if (r5 >= r6) goto L11;
        int r12 = r1 << 4;
        char r2 = r4.charAt(r5);
        if ((r2 >>> '\b') != 0) goto L9;
        if (access$getHEX_DIGITS_TO_DECIMAL$p()[r2] < 0) goto L9;
        r1 = r12 | access$getHEX_DIGITS_TO_DECIMAL$p()[r2];
        r5 = r5 + 1;
    L9:
        throwInvalidDigitAt(r4, r5);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L11:
        return r1;
    }

    private static final long parseLong(String r8, int r9, int r10) {
        long r2 = 0;
    L3:
        if (r9 >= r10) goto L11;
        long r22 = r2 << 4;
        char r4 = r8.charAt(r9);
        if ((r4 >>> '\b') != 0) goto L9;
        if (access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r4] < 0) goto L9;
        r2 = r22 | access$getHEX_DIGITS_TO_LONG_DECIMAL$p()[r4];
        r9 = r9 + 1;
    L9:
        throwInvalidDigitAt(r8, r9);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L11:
        return r2;
    }

    public static final int parsedByteArrayMaxSize(int r17, int r18, int r19, int r20, int r21, int r22, int r23) {
        if (r17 <= 0) goto L17;
        long r5 = (r22 + 2) + r23;
        long r7 = charsPerSet(r5, r19, r21);
        if (r18 > r19) goto L7;
        long r9 = charsPerSet(r5, r18, r21);
    L10:
        long r11 = r17;
        long r13 = wholeElementsPerSet(r11, r9, 1);
        long r112 = r11 - ((r9 + 1) * r13);
        long r92 = wholeElementsPerSet(r112, r7, r20);
        long r113 = r112 - ((r7 + r20) * r92);
        long r0 = wholeElementsPerSet(r113, r5, r21);
        if ((r113 - ((r5 + r21) * r0)) <= 0) goto L14;
        int r3 = 1;
    L16:
        return (int) ((((r13 * r18) + (r92 * r19)) + r0) + r3);
    L14:
        r3 = 0;
        goto L16
    L7:
        r9 = charsPerSet(r7, r18 / r19, r20);
        int r114 = r18 % r19;
        if (r114 == 0) goto L10;
        r9 = (r9 + r20) + charsPerSet(r5, r114, r21);
        goto L10
    L17:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return 0;
    }

    private static final Void throwInvalidDigitAt(String r3, int r4) {
        throw new NumberFormatException("Expected a hexadecimal digit at index " + r4 + ", but was " + r3.charAt(r4));
    }

    private static final void throwInvalidNumberOfDigits(String r3, int r4, int r5, String r6, int r7) {
        throw new NumberFormatException("Expected " + r6 + ' ' + r7 + " hexadecimal digits at index " + r4 + ", but was \"" + r3.substring(r4, r5) + "\" of length " + (r5 - r4));
    }

    private static final void throwInvalidPrefixSuffix(String r1, int r2, int r3, String r4, String r5) {
        throw new NumberFormatException("Expected a hexadecimal number with prefix \"" + r4 + "\" and suffix \"" + r5 + "\", but was " + r1.substring(r2, r3));
    }

    private static final void throwNotContainedAt(String r2, int r3, int r4, String r5, String r6) {
        throw new NumberFormatException("Expected " + r6 + " \"" + r5 + "\" at index " + r3 + ", but was " + r2.substring(r3, RangesKt.coerceAtMost(r5.length() + r3, r4)));
    }

    private static final int toCharArrayIfNotEmpty(String r3, char[] r4, int r5) {
        int r0 = r3.length();
        if (r0 == 0) goto L9;
        if (r0 == 1) goto L7;
        r3.getChars(0, r3.length(), r4, r5);
        goto L9
    L7:
        r4[r5] = r3.charAt(0);
    L9:
        return r5 + r3.length();
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final String toHexString(byte r5, HexFormat r6) {
        if (r6.getUpperCase() == false) goto L5;
        String r0 = UPPER_CASE_HEX_DIGITS;
    L6:
        HexFormat.NumberHexFormat r62 = r6.getNumber();
        if (r62.isDigitsOnlyAndNoPadding$kotlin_stdlib() == false) goto L15;
        char[] r3 = {r0.charAt((r5 >> 4) & 15), r0.charAt(r5 & 15)};
        if (r62.getRemoveLeadingZeros() == false) goto L13;
        return StringsKt__StringsJVMKt.concatToString$default(r3, RangesKt.coerceAtMost((Integer.numberOfLeadingZeros(r5 & UByte.MAX_VALUE) - 24) >> 2, 1), 0, 2, null);
    L13:
        return StringsKt__StringsJVMKt.concatToString(r3);
    L15:
        return toHexStringImpl(r5, r62, r0, 8);
    L5:
        r0 = LOWER_CASE_HEX_DIGITS;
        goto L6
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final String toHexString(int r10, HexFormat r11) {
        if (r11.getUpperCase() == false) goto L5;
        String r0 = UPPER_CASE_HEX_DIGITS;
    L6:
        HexFormat.NumberHexFormat r112 = r11.getNumber();
        if (r112.isDigitsOnlyAndNoPadding$kotlin_stdlib() == false) goto L15;
        char[] r8 = {r0.charAt((r10 >> 28) & 15), r0.charAt((r10 >> 24) & 15), r0.charAt((r10 >> 20) & 15), r0.charAt((r10 >> 16) & 15), r0.charAt((r10 >> 12) & 15), r0.charAt((r10 >> 8) & 15), r0.charAt((r10 >> 4) & 15), r0.charAt(r10 & 15)};
        if (r112.getRemoveLeadingZeros() == false) goto L13;
        return StringsKt__StringsJVMKt.concatToString$default(r8, RangesKt.coerceAtMost(Integer.numberOfLeadingZeros(r10) >> 2, 7), 0, 2, null);
    L13:
        return StringsKt__StringsJVMKt.concatToString(r8);
    L15:
        return toHexStringImpl(r10, r112, r0, 32);
    L5:
        r0 = LOWER_CASE_HEX_DIGITS;
        goto L6
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final String toHexString(long r26, HexFormat r28) {
        if (r28.getUpperCase() == false) goto L5;
        String r2 = UPPER_CASE_HEX_DIGITS;
    L6:
        HexFormat.NumberHexFormat r3 = r28.getNumber();
        if (r3.isDigitsOnlyAndNoPadding$kotlin_stdlib() == false) goto L15;
        char[] r4 = {r2.charAt((int) ((r26 >> 60) & 15)), r2.charAt((int) ((r26 >> 56) & 15)), r2.charAt((int) ((r26 >> 52) & 15)), r2.charAt((int) ((r26 >> 48) & 15)), r2.charAt((int) ((r26 >> 44) & 15)), r2.charAt((int) ((r26 >> 40) & 15)), r2.charAt((int) ((r26 >> 36) & 15)), r2.charAt((int) ((r26 >> 32) & 15)), r2.charAt((int) ((r26 >> 28) & 15)), r2.charAt((int) ((r26 >> 24) & 15)), r2.charAt((int) ((r26 >> 20) & 15)), r2.charAt((int) ((r26 >> 16) & 15)), r2.charAt((int) ((r26 >> 12) & 15)), r2.charAt((int) ((r26 >> 8) & 15)), r2.charAt((int) ((r26 >> 4) & 15)), r2.charAt((int) (r26 & 15))};
        if (r3.getRemoveLeadingZeros() == false) goto L13;
        return StringsKt__StringsJVMKt.concatToString$default(r4, RangesKt.coerceAtMost(Long.numberOfLeadingZeros(r26) >> 2, 15), 0, 2, null);
    L13:
        return StringsKt__StringsJVMKt.concatToString(r4);
    L15:
        return toHexStringImpl(r26, r3, r2, 64);
    L5:
        r2 = LOWER_CASE_HEX_DIGITS;
        goto L6
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final String toHexString(short r7, HexFormat r8) {
        if (r8.getUpperCase() == false) goto L5;
        String r0 = UPPER_CASE_HEX_DIGITS;
    L6:
        HexFormat.NumberHexFormat r82 = r8.getNumber();
        if (r82.isDigitsOnlyAndNoPadding$kotlin_stdlib() == false) goto L15;
        char[] r5 = {r0.charAt((r7 >> 12) & 15), r0.charAt((r7 >> 8) & 15), r0.charAt((r7 >> 4) & 15), r0.charAt(r7 & 15)};
        if (r82.getRemoveLeadingZeros() == false) goto L13;
        return StringsKt__StringsJVMKt.concatToString$default(r5, RangesKt.coerceAtMost((Integer.numberOfLeadingZeros(r7 & UShort.MAX_VALUE) - 16) >> 2, 3), 0, 2, null);
    L13:
        return StringsKt__StringsJVMKt.concatToString(r5);
    L15:
        return toHexStringImpl(r7, r82, r0, 16);
    L5:
        r0 = LOWER_CASE_HEX_DIGITS;
        goto L6
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final String toHexString(byte[] r2, int r3, int r4, HexFormat r5) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(r3, r4, r2.length);
        if (r3 != r4) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        if (r5.getUpperCase() == false) goto L9;
        int[] r0 = BYTE_TO_UPPER_CASE_HEX_DIGITS;
    L10:
        HexFormat.BytesHexFormat r52 = r5.getBytes();
        if (r52.getNoLineAndGroupSeparator$kotlin_stdlib() == false) goto L15;
        return toHexStringNoLineAndGroupSeparator(r2, r3, r4, r52, r0);
    L15:
        return toHexStringSlowPath(r2, r3, r4, r52, r0);
    L9:
        r0 = BYTE_TO_LOWER_CASE_HEX_DIGITS;
        goto L10
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    public static final String toHexString(byte[] r2, HexFormat r3) {
        return toHexString(r2, 0, r2.length, r3);
    }

    public static /* synthetic */ String toHexString$default(byte r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return toHexString(r0, r1);
    }

    public static /* synthetic */ String toHexString$default(int r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return toHexString(r0, r1);
    }

    public static /* synthetic */ String toHexString$default(long r0, HexFormat r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r2 = HexFormat.Companion.getDefault();
    L6:
        return toHexString(r0, r2);
    }

    public static /* synthetic */ String toHexString$default(short r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return toHexString(r0, r1);
    }

    public static /* synthetic */ String toHexString$default(byte[] r0, int r1, int r2, HexFormat r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = r0.length;
    L9:
        if ((r4 & 4) == 0) goto L12;
        r3 = HexFormat.Companion.getDefault();
    L12:
        return toHexString(r0, r1, r2, r3);
    }

    public static /* synthetic */ String toHexString$default(byte[] r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return toHexString(r0, r1);
    }

    private static final String toHexStringImpl(long r17, HexFormat.NumberHexFormat r19, String r20, int r21) {
        if ((r21 & 3) != 0) goto L26;
        int r1 = r21 >> 2;
        int r3 = r19.getMinLength();
        int r4 = RangesKt.coerceAtLeast(r3 - r1, 0);
        String r6 = r19.getPrefix();
        String r7 = r19.getSuffix();
        boolean r8 = r19.getRemoveLeadingZeros();
        int r9 = checkFormatLength(((r6.length() + r4) + r1) + r7.length());
        char[] r10 = new char[r9];
        int r62 = toCharArrayIfNotEmpty(r6, r10, 0);
        if (r4 <= 0) goto L7;
        int r42 = r4 + r62;
        ArraysKt.fill(r10, r20.charAt(0), r62, r42);
        r62 = r42;
    L7:
        int r43 = r21;
        int r11 = 0;
    L9:
        if (r11 >= r1) goto L20;
        r43 = r43 - 4;
        int r13 = (int) ((r17 >> r43) & 15);
        if (r8 == false) goto L16;
        if (r13 != 0) goto L16;
        if ((r43 >> 2) < r3) goto L16;
        r8 = true;
    L17:
        if (r8 == true) goto L19;
        r10[r62] = r20.charAt(r13);
        r62 = r62 + 1;
    L19:
        r11 = r11 + 1;
    L16:
        r8 = false;
        goto L17
    L20:
        int r0 = toCharArrayIfNotEmpty(r7, r10, r62);
        if (r0 != r9) goto L25;
        return StringsKt__StringsJVMKt.concatToString(r10);
    L25:
        return StringsKt__StringsJVMKt.concatToString$default(r10, 0, r0, 1, null);
    L26:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return null;
    }

    private static final String toHexStringNoLineAndGroupSeparator(byte[] r1, int r2, int r3, HexFormat.BytesHexFormat r4, int[] r5) {
        if (r4.getShortByteSeparatorNoPrefixAndSuffix$kotlin_stdlib() == false) goto L7;
        return toHexStringShortByteSeparatorNoPrefixAndSuffix(r1, r2, r3, r4, r5);
    L7:
        return toHexStringNoLineAndGroupSeparatorSlowPath(r1, r2, r3, r4, r5);
    }

    private static final String toHexStringNoLineAndGroupSeparatorSlowPath(byte[] r7, int r8, int r9, HexFormat.BytesHexFormat r10, int[] r11) {
        String r2 = r10.getBytePrefix();
        String r3 = r10.getByteSuffix();
        String r102 = r10.getByteSeparator();
        char[] r5 = new char[formattedStringLength(r9 - r8, r102.length(), r2.length(), r3.length())];
        int r72 = formatByteAt(r7, r8, r2, r3, r11, r5, 0);
        int r1 = r8 + 1;
    L3:
        if (r1 >= r9) goto L6;
        r72 = formatByteAt(r7, r1, r2, r3, r11, r5, toCharArrayIfNotEmpty(r102, r5, r72));
        r1 = r1 + 1;
        goto L3
    L6:
        return StringsKt__StringsJVMKt.concatToString(r5);
    }

    private static final String toHexStringShortByteSeparatorNoPrefixAndSuffix(byte[] r8, int r9, int r10, HexFormat.BytesHexFormat r11, int[] r12) {
        int r0 = r11.getByteSeparator().length();
        if (r0 > 1) goto L16;
        int r2 = r10 - r9;
        int r3 = 0;
        if (r0 != 0) goto L11;
        char[] r112 = new char[checkFormatLength(r2 * 2)];
    L7:
        if (r9 >= r10) goto L10;
        r3 = formatByteAt(r8, r9, r12, r112, r3);
        r9 = r9 + 1;
        goto L7
    L10:
        return StringsKt__StringsJVMKt.concatToString(r112);
    L11:
        char[] r02 = new char[checkFormatLength((r2 * 3) - 1)];
        char r113 = r11.getByteSeparator().charAt(0);
        int r22 = formatByteAt(r8, r9, r12, r02, 0);
        int r92 = r9 + 1;
    L12:
        if (r92 >= r10) goto L15;
        r02[r22] = r113;
        r22 = formatByteAt(r8, r92, r12, r02, r22 + 1);
        r92 = r92 + 1;
        goto L12
    L15:
        return StringsKt__StringsJVMKt.concatToString(r02);
    L16:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return null;
    }

    private static final String toHexStringSlowPath(byte[] r17, int r18, int r19, HexFormat.BytesHexFormat r20, int[] r21) {
        int r2 = r20.getBytesPerLine();
        int r3 = r20.getBytesPerGroup();
        String r8 = r20.getBytePrefix();
        String r9 = r20.getByteSuffix();
        String r11 = r20.getByteSeparator();
        String r12 = r20.getGroupSeparator();
        int r1 = formattedStringLength(r19 - r18, r2, r3, r12.length(), r11.length(), r8.length(), r9.length());
        char[] r92 = new char[r1];
        int r5 = r18;
        int r4 = 0;
        int r6 = 0;
        int r10 = 0;
    L3:
        if (r5 >= r19) goto L12;
        if (r6 != r2) goto L6;
        r92[r4] = '\n';
        r4 = r4 + 1;
        int r14 = 0;
        int r15 = 0;
    L9:
        if (r15 == 0) goto L11;
        r4 = toCharArrayIfNotEmpty(r11, r92, r4);
    L11:
        String r62 = r8;
        int r102 = formatByteAt(r17, r5, r62, r9, r21, r92, r4);
        r5 = r5 + 1;
        r10 = r15 + 1;
        r4 = r102;
        r8 = r62;
        r6 = r14 + 1;
        goto L3
    L6:
        if (r10 != r3) goto L8;
        r4 = toCharArrayIfNotEmpty(r12, r92, r4);
        r14 = r6;
        r15 = 0;
        goto L9
    L8:
        r14 = r6;
        r15 = r10;
        goto L9
    L12:
        if (r4 == r1) goto L14;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    L14:
        return StringsKt__StringsJVMKt.concatToString(r92);
    }

    private static final long wholeElementsPerSet(long r3, long r5, int r7) {
        if (r3 > 0) goto L5;
    L9:
        return 0;
    L5:
        if (r5 <= 0) goto L9;
        long r0 = r7;
        return (r3 + r0) / (r5 + r0);
    }
}
