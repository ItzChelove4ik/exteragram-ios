package retrofit2;

import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.regex.Pattern;
import kotlin.UByte;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;
import okio.Buffer;
import okio.BufferedSink;

/* loaded from: classes3.dex */
final class RequestBuilder {
    private static final char[] HEX_DIGITS = null;
    private static final Pattern PATH_TRAVERSAL = null;
    private final HttpUrl baseUrl;
    private RequestBody body;
    private MediaType contentType;
    private FormBody.Builder formBuilder;
    private final boolean hasBody;
    private final Headers.Builder headersBuilder;
    private final String method;
    private MultipartBody.Builder multipartBuilder;
    private String relativeUrl;
    private final Request.Builder requestBuilder;
    private HttpUrl.Builder urlBuilder;

    /* loaded from: classes7.dex */
    public static class ContentTypeOverridingRequestBody extends RequestBody {
        private final MediaType contentType;
        private final RequestBody delegate;

        public ContentTypeOverridingRequestBody(RequestBody r1, MediaType r2) {
            this.delegate = r1;
            this.contentType = r2;
        }

        @Override // okhttp3.RequestBody
        public long contentLength() {
            return this.delegate.contentLength();
        }

        @Override // okhttp3.RequestBody
        public MediaType contentType() {
            return this.contentType;
        }

        @Override // okhttp3.RequestBody
        public void writeTo(BufferedSink r1) {
            this.delegate.writeTo(r1);
        }
    }

    static {
        HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        PATH_TRAVERSAL = Pattern.compile("(.*/)?(\\.|%2e|%2E){1,2}(/.*)?");
    }

    public RequestBuilder(String r1, HttpUrl r2, String r3, Headers r4, MediaType r5, boolean r6, boolean r7, boolean r8) {
        this.method = r1;
        this.baseUrl = r2;
        this.relativeUrl = r3;
        this.requestBuilder = new Request.Builder();
        this.contentType = r5;
        this.hasBody = r6;
        if (r4 == null) goto L5;
        this.headersBuilder = r4.newBuilder();
    L6:
        if (r7 == false) goto L9;
        this.formBuilder = new FormBody.Builder();
        return;
    L9:
        if (r8 == false) goto L12;
        MultipartBody.Builder r12 = new MultipartBody.Builder();
        this.multipartBuilder = r12;
        r12.setType(MultipartBody.FORM);
        return;
    L12:
        return;
    L5:
        this.headersBuilder = new Headers.Builder();
        goto L6
    }

    private static String canonicalizeForPath(String r6, boolean r7) {
        int r0 = r6.length();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L27;
        int r3 = r6.codePointAt(r2);
        if (r3 < 32) goto L17;
        if (r3 >= 127) goto L17;
        if (" \"<>^`{}|\\?#".indexOf(r3) != (-1)) goto L17;
        if (r7 == true) goto L16;
        if (r3 == 47) goto L17;
        if (r3 == 37) goto L17;
    L16:
        r2 = r2 + Character.charCount(r3);
    L17:
        Buffer r32 = new Buffer();
        r32.writeUtf8(r6, 0, r2);
        canonicalizeForPath(r32, r6, r2, r0, r7);
        return r32.readUtf8();
    L27:
        return r6;
    }

    private static void canonicalizeForPath(Buffer r10, String r11, int r12, int r13, boolean r14) {
        Buffer r0 = null;
    L3:
        if (r12 >= r13) goto L35;
        int r1 = r11.codePointAt(r12);
        if (r14 == false) goto L16;
        if (r1 == 9) goto L34;
        if (r1 == 10) goto L34;
        if (r1 == 12) goto L34;
        if (r1 != 13) goto L16;
    L34:
        r12 = r12 + Character.charCount(r1);
    L16:
        if (r1 >= 32) goto L18;
    L27:
        if (r0 != null) goto L29;
        r0 = new Buffer();
    L29:
        r0.writeUtf8CodePoint(r1);
        long r4 = r0.size();
        long r6 = 0;
    L31:
        if (r6 >= r4) goto L33;
        byte r2 = r0.getByte(r6);
        int r8 = r2 & UByte.MAX_VALUE;
        r10.writeByte(37);
        char[] r9 = HEX_DIGITS;
        r10.writeByte(r9[(r8 >> 4) & 15]);
        r10.writeByte(r9[r2 & 15]);
        r6 = r6 + 1;
        goto L31
    L33:
        r0.clear();
        goto L34
    L18:
        if (r1 >= 127) goto L27;
        if (" \"<>^`{}|\\?#".indexOf(r1) != (-1)) goto L27;
        if (r14 == false) goto L23;
    L26:
        r10.writeUtf8CodePoint(r1);
        goto L34
    L23:
        if (r1 == 47) goto L27;
        if (r1 != 37) goto L26;
    }

    public void addFormField(String r1, String r2, boolean r3) {
        FormBody.Builder r0 = this.formBuilder;
        if (r3 == false) goto L6;
        r0.addEncoded(r1, r2);
        return;
    L6:
        r0.add(r1, r2);
    }

    public void addHeader(String r2, String r3, boolean r4) {
        if ("Content-Type".equalsIgnoreCase(r2) == true) goto L15;
        Headers.Builder r1 = this.headersBuilder;
        if (r4 == false) goto L13;
        r1.addUnsafeNonAscii(r2, r3);
        return;
    L13:
        r1.add(r2, r3);
        return;
    L15:
        this.contentType = MediaType.get(r3);     // Catch: IllegalArgumentException -> L6
        return;
    L6:
        e = move-exception;
        throw new IllegalArgumentException("Malformed content type: " + r3, e);
    }

    public void addHeaders(Headers r1) {
        this.headersBuilder.addAll(r1);
    }

    public void addPart(Headers r1, RequestBody r2) {
        this.multipartBuilder.addPart(r1, r2);
    }

    public void addPart(MultipartBody.Part r1) {
        this.multipartBuilder.addPart(r1);
    }

    public void addPathParam(String r4, String r5, boolean r6) {
        if (this.relativeUrl == null) goto L10;
        String r62 = canonicalizeForPath(r5, r6);
        String r42 = this.relativeUrl.replace("{" + r4 + "}", r62);
        if (PATH_TRAVERSAL.matcher(r42).matches() == true) goto L8;
        this.relativeUrl = r42;
        return;
    L8:
        Native$$ExternalSyntheticBUOutline5.m("@Path parameters shouldn't perform path traversal ('.' or '..'): ", r5);
        return;
    L10:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
    }

    public void addQueryParam(String r3, String r4, boolean r5) {
        String r0 = this.relativeUrl;
        if (r0 == null) goto L9;
        HttpUrl.Builder r02 = this.baseUrl.newBuilder(r0);
        this.urlBuilder = r02;
        if (r02 == null) goto L7;
        this.relativeUrl = null;
        goto L9
    L7:
        StringBuilder r32 = new StringBuilder("Malformed URL. Base: ");
        r32.append(this.baseUrl);
        Utils$$ExternalSyntheticBUOutline1.m(r32, ", Relative: ", this.relativeUrl);
        return;
    L9:
        HttpUrl.Builder r2 = this.urlBuilder;
        if (r5 == false) goto L13;
        r2.addEncodedQueryParameter(r3, r4);
        return;
    L13:
        r2.addQueryParameter(r3, r4);
    }

    public <T> void addTag(Class<T> r1, T r2) {
        this.requestBuilder.tag(r1, r2);
    }

    public Request.Builder get() {
        HttpUrl.Builder r0 = this.urlBuilder;
        if (r0 == null) goto L5;
        HttpUrl r02 = r0.build();
    L7:
        RequestBody r2 = this.body;
        if (r2 != null) goto L18;
        FormBody.Builder r3 = this.formBuilder;
        if (r3 == null) goto L12;
        r2 = r3.build();
        goto L18
    L12:
        MultipartBody.Builder r32 = this.multipartBuilder;
        if (r32 == null) goto L16;
        r2 = r32.build();
        goto L18
    L16:
        if (this.hasBody == false) goto L18;
        r2 = RequestBody.create(null, new byte[0]);
    L18:
        MediaType r1 = this.contentType;
        if (r1 == null) goto L24;
        if (r2 == null) goto L22;
        r2 = new ContentTypeOverridingRequestBody(r2, r1);
        goto L24
    L22:
        this.headersBuilder.add("Content-Type", r1.toString());
    L24:
        return this.requestBuilder.url(r02).headers(this.headersBuilder.build()).method(this.method, r2);
    L5:
        r02 = this.baseUrl.resolve(this.relativeUrl);
        if (r02 != null) goto L7;
        StringBuilder r03 = new StringBuilder("Malformed URL. Base: ");
        r03.append(this.baseUrl);
        Utils$$ExternalSyntheticBUOutline1.m(r03, ", Relative: ", this.relativeUrl);
        return null;
    }

    public void setBody(RequestBody r1) {
        this.body = r1;
    }

    public void setRelativeUrl(Object r1) {
        this.relativeUrl = r1.toString();
    }
}
