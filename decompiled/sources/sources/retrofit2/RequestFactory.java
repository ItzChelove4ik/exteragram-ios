package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.coroutines.Continuation;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import retrofit2.ParameterHandler;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;
import retrofit2.http.Tag;
import retrofit2.http.Url;

/* loaded from: classes3.dex */
final class RequestFactory {
    private final HttpUrl baseUrl;
    private final MediaType contentType;
    private final boolean hasBody;
    private final Headers headers;
    final String httpMethod;
    private final boolean isFormEncoded;
    final boolean isKotlinSuspendFunction;
    private final boolean isMultipart;
    private final Method method;
    private final ParameterHandler<?>[] parameterHandlers;
    private final String relativeUrl;
    private final Class<?> service;

    public static final class Builder {
        private static final Pattern PARAM_NAME_REGEX = null;
        private static final Pattern PARAM_URL_REGEX = null;
        MediaType contentType;
        boolean gotBody;
        boolean gotField;
        boolean gotPart;
        boolean gotPath;
        boolean gotQuery;
        boolean gotQueryMap;
        boolean gotQueryName;
        boolean gotUrl;
        boolean hasBody;
        Headers headers;
        String httpMethod;
        boolean isFormEncoded;
        boolean isKotlinSuspendFunction;
        boolean isMultipart;
        final Method method;
        final Annotation[] methodAnnotations;
        final Annotation[][] parameterAnnotationsArray;
        ParameterHandler<?>[] parameterHandlers;
        final Type[] parameterTypes;
        String relativeUrl;
        Set<String> relativeUrlParamNames;
        final Retrofit retrofit;
        final Class<?> service;

        static {
            PARAM_URL_REGEX = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
            PARAM_NAME_REGEX = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]*");
        }

        public Builder(Retrofit r1, Class<?> r2, Method r3) {
            this.retrofit = r1;
            this.service = r2;
            this.method = r3;
            this.methodAnnotations = r3.getAnnotations();
            this.parameterTypes = r3.getGenericParameterTypes();
            this.parameterAnnotationsArray = r3.getParameterAnnotations();
        }

        private static Class<?> boxIfPrimitive(Class<?> r1) {
            if (Boolean.TYPE != r1) goto L7;
            return Boolean.class;
        L7:
            if (Byte.TYPE != r1) goto L11;
            return Byte.class;
        L11:
            if (Character.TYPE != r1) goto L15;
            return Character.class;
        L15:
            if (Double.TYPE != r1) goto L19;
            return Double.class;
        L19:
            if (Float.TYPE != r1) goto L23;
            return Float.class;
        L23:
            if (Integer.TYPE != r1) goto L27;
            return Integer.class;
        L27:
            if (Long.TYPE != r1) goto L31;
            return Long.class;
        L31:
            if (Short.TYPE != r1) goto L34;
            return Short.class;
        L34:
            return r1;
        }

        private Headers parseHeaders(String[] r8, boolean r9) {
            Headers.Builder r0 = new Headers.Builder();
            int r1 = r8.length;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L23;
            String r4 = r8[r3];
            int r5 = r4.indexOf(58);
            if (r5 == (-1)) goto L21;
            if (r5 == 0) goto L21;
            if (r5 == (r4.length() - 1)) goto L21;
            String r6 = r4.substring(0, r5);
            String r42 = r4.substring(r5 + 1).trim();
            if ("Content-Type".equalsIgnoreCase(r6) == true) goto L24;
            if (r9 == false) goto L18;
            r0.addUnsafeNonAscii(r6, r42);
        L19:
            r3 = r3 + 1;
            goto L3
        L18:
            r0.add(r6, r42);
            goto L19
        L24:
            this.contentType = MediaType.get(r42);     // Catch: IllegalArgumentException -> L13
        L13:
            e = move-exception;
            throw Utils.methodError(this.method, e, "Malformed content type: %s", new Object[]{r42});
        L21:
            throw Utils.methodError(this.method, "@Headers value must be in the form \"Name: Value\". Found: \"%s\"", new Object[]{r4});
        L23:
            return r0.build();
        }

        private void parseHttpMethodAndPath(String r2, String r3, boolean r4) {
            String r0 = this.httpMethod;
            if (r0 != null) goto L19;
            this.httpMethod = r2;
            this.hasBody = r4;
            if (r3.isEmpty() == false) goto L7;
            return;
        L7:
            int r22 = r3.indexOf(63);
            if (r22 != (-1)) goto L10;
        L16:
            this.relativeUrl = r3;
            this.relativeUrlParamNames = parsePathParameters(r3);
            return;
        L10:
            if (r22 >= (r3.length() - 1)) goto L16;
            String r23 = r3.substring(r22 + 1);
            if (PARAM_URL_REGEX.matcher(r23).find() == false) goto L16;
            throw Utils.methodError(this.method, "URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", new Object[]{r23});
        L19:
            throw Utils.methodError(this.method, "Only one HTTP method is allowed. Found: %s and %s.", new Object[]{r0, r2});
        }

        private void parseMethodAnnotation(Annotation r5) {
            if ((r5 instanceof DELETE) == false) goto L7;
            parseHttpMethodAndPath("DELETE", ((DELETE) r5).value(), false);
            return;
        L7:
            if ((r5 instanceof GET) == false) goto L11;
            parseHttpMethodAndPath("GET", ((GET) r5).value(), false);
            return;
        L11:
            if ((r5 instanceof HEAD) == false) goto L15;
            parseHttpMethodAndPath("HEAD", ((HEAD) r5).value(), false);
            return;
        L15:
            if ((r5 instanceof PATCH) == false) goto L19;
            parseHttpMethodAndPath("PATCH", ((PATCH) r5).value(), true);
            return;
        L19:
            if ((r5 instanceof POST) == false) goto L23;
            parseHttpMethodAndPath("POST", ((POST) r5).value(), true);
            return;
        L23:
            if ((r5 instanceof PUT) == false) goto L27;
            parseHttpMethodAndPath("PUT", ((PUT) r5).value(), true);
            return;
        L27:
            if ((r5 instanceof OPTIONS) == false) goto L31;
            parseHttpMethodAndPath("OPTIONS", ((OPTIONS) r5).value(), false);
            return;
        L31:
            if ((r5 instanceof HTTP) == false) goto L35;
            HTTP r52 = (HTTP) r5;
            parseHttpMethodAndPath(r52.method(), r52.path(), r52.hasBody());
            return;
        L35:
            if ((r5 instanceof retrofit2.http.Headers) == false) goto L43;
            retrofit2.http.Headers r53 = (retrofit2.http.Headers) r5;
            String[] r0 = r53.value();
            if (r0.length == 0) goto L41;
            this.headers = parseHeaders(r0, r53.allowUnsafeNonAsciiValues());
            return;
        L41:
            throw Utils.methodError(this.method, "@Headers annotation is empty.", new Object[0]);
        L43:
            if ((r5 instanceof Multipart) == false) goto L51;
            if (this.isFormEncoded == true) goto L49;
            this.isMultipart = true;
            return;
        L49:
            throw Utils.methodError(this.method, "Only one encoding annotation is allowed.", new Object[0]);
        L51:
            if ((r5 instanceof FormUrlEncoded) == true) goto L53;
            return;
        L53:
            if (this.isMultipart == true) goto L57;
            this.isFormEncoded = true;
            return;
        L57:
            throw Utils.methodError(this.method, "Only one encoding annotation is allowed.", new Object[0]);
        }

        private ParameterHandler<?> parseParameter(int r7, Type r8, Annotation[] r9, boolean r10) {
            if (r9 == null) goto L14;
            int r2 = r9.length;
            int r3 = 0;
            ParameterHandler<?> r4 = null;
        L5:
            if (r3 >= r2) goto L15;
            ParameterHandler<?> r5 = parseParameterAnnotation(r7, r8, r9, r9[r3]);
            if (r5 == null) goto L11;
            if (r4 != null) goto L13;
            r4 = r5;
            goto L11
        L13:
            throw Utils.parameterError(this.method, r7, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
        L11:
            r3 = r3 + 1;
        L15:
            if (r4 != null) goto L23;
            if (r10 == false) goto L22;
            if (Utils.getRawType(r8) != Continuation.class) goto L22;
            this.isKotlinSuspendFunction = true;     // Catch: NoClassDefFoundError -> L24
            return null;
        L22:
            throw Utils.parameterError(this.method, r7, "No Retrofit annotation found.", new Object[0]);
        L23:
            return r4;
        L14:
            r4 = null;
            goto L15
        }

        private ParameterHandler<?> parseParameterAnnotation(int r11, Type r12, Annotation[] r13, Annotation r14) {
            if ((r14 instanceof Url) == false) goto L43;
            validateResolvableType(r11, r12);
            if (this.gotUrl == true) goto L41;
            if (this.gotPath == true) goto L39;
            if (this.gotQuery == true) goto L37;
            if (this.gotQueryName == true) goto L35;
            if (this.gotQueryMap == true) goto L33;
            if (this.relativeUrl != null) goto L31;
            this.gotUrl = true;
            if (r12 == HttpUrl.class) goto L29;
            if (r12 == String.class) goto L29;
            if (r12 == URI.class) goto L29;
            if ((r12 instanceof Class) == false) goto L27;
            if ("android.net.Uri".equals(((Class) r12).getName()) == true) goto L29;
        L27:
            throw Utils.parameterError(this.method, r11, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
        L29:
            return new ParameterHandler.RelativeUrl(this.method, r11);
        L31:
            throw Utils.parameterError(this.method, r11, "@Url cannot be used with @%s URL", new Object[]{this.httpMethod});
        L33:
            throw Utils.parameterError(this.method, r11, "A @Url parameter must not come after a @QueryMap.", new Object[0]);
        L35:
            throw Utils.parameterError(this.method, r11, "A @Url parameter must not come after a @QueryName.", new Object[0]);
        L37:
            throw Utils.parameterError(this.method, r11, "A @Url parameter must not come after a @Query.", new Object[0]);
        L39:
            throw Utils.parameterError(this.method, r11, "@Path parameters may not be used with @Url.", new Object[0]);
        L41:
            throw Utils.parameterError(this.method, r11, "Multiple @Url method annotations found.", new Object[0]);
        L43:
            if ((r14 instanceof Path) == false) goto L67;
            validateResolvableType(r11, r12);
            if (this.gotQuery == true) goto L65;
            if (this.gotQueryName == true) goto L63;
            if (this.gotQueryMap == true) goto L61;
            if (this.gotUrl == true) goto L59;
            if (this.relativeUrl == null) goto L57;
            this.gotPath = true;
            Path r142 = (Path) r14;
            String r3 = r142.value();
            validatePathName(r11, r3);
            return new ParameterHandler.Path(this.method, r11, r3, this.retrofit.stringConverter(r12, r13), r142.encoded());
        L57:
            throw Utils.parameterError(this.method, r11, "@Path can only be used with relative url on @%s", new Object[]{this.httpMethod});
        L59:
            throw Utils.parameterError(this.method, r11, "@Path parameters may not be used with @Url.", new Object[0]);
        L61:
            throw Utils.parameterError(this.method, r11, "A @Path parameter must not come after a @QueryMap.", new Object[0]);
        L63:
            throw Utils.parameterError(this.method, r11, "A @Path parameter must not come after a @QueryName.", new Object[0]);
        L65:
            throw Utils.parameterError(this.method, r11, "A @Path parameter must not come after a @Query.", new Object[0]);
        L67:
            if ((r14 instanceof Query) == false) goto L83;
            validateResolvableType(r11, r12);
            Query r143 = (Query) r14;
            String r0 = r143.value();
            boolean r144 = r143.encoded();
            Class<?> r2 = Utils.getRawType(r12);
            this.gotQuery = true;
            if (Iterable.class.isAssignableFrom(r2) == false) goto L77;
            if ((r12 instanceof ParameterizedType) == false) goto L75;
            return new ParameterHandler.Query(r0, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) r12), r13), r144).iterable();
        L75:
            throw Utils.parameterError(this.method, r11, r2.getSimpleName() + " must include generic type (e.g., " + r2.getSimpleName() + "<String>)", new Object[0]);
        L77:
            if (r2.isArray() == false) goto L81;
            return new ParameterHandler.Query(r0, this.retrofit.stringConverter(boxIfPrimitive(r2.getComponentType()), r13), r144).array();
        L81:
            return new ParameterHandler.Query(r0, this.retrofit.stringConverter(r12, r13), r144);
        L83:
            if ((r14 instanceof QueryName) == false) goto L99;
            validateResolvableType(r11, r12);
            boolean r145 = ((QueryName) r14).encoded();
            Class<?> r02 = Utils.getRawType(r12);
            this.gotQueryName = true;
            if (Iterable.class.isAssignableFrom(r02) == false) goto L93;
            if ((r12 instanceof ParameterizedType) == false) goto L91;
            return new ParameterHandler.QueryName(this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) r12), r13), r145).iterable();
        L91:
            throw Utils.parameterError(this.method, r11, r02.getSimpleName() + " must include generic type (e.g., " + r02.getSimpleName() + "<String>)", new Object[0]);
        L93:
            if (r02.isArray() == false) goto L97;
            return new ParameterHandler.QueryName(this.retrofit.stringConverter(boxIfPrimitive(r02.getComponentType()), r13), r145).array();
        L97:
            return new ParameterHandler.QueryName(this.retrofit.stringConverter(r12, r13), r145);
        L99:
            if ((r14 instanceof QueryMap) == false) goto L115;
            validateResolvableType(r11, r12);
            Class<?> r03 = Utils.getRawType(r12);
            this.gotQueryMap = true;
            if (Map.class.isAssignableFrom(r03) == false) goto L113;
            Type r122 = Utils.getSupertype(r12, r03, Map.class);
            if ((r122 instanceof ParameterizedType) == false) goto L111;
            ParameterizedType r123 = (ParameterizedType) r122;
            Type r04 = Utils.getParameterUpperBound(0, r123);
            if (String.class != r04) goto L109;
            return new ParameterHandler.QueryMap(this.method, r11, this.retrofit.stringConverter(Utils.getParameterUpperBound(1, r123), r13), ((QueryMap) r14).encoded());
        L109:
            throw Utils.parameterError(this.method, r11, "@QueryMap keys must be of type String: " + r04, new Object[0]);
        L111:
            throw Utils.parameterError(this.method, r11, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
        L113:
            throw Utils.parameterError(this.method, r11, "@QueryMap parameter type must be Map.", new Object[0]);
        L115:
            if ((r14 instanceof Header) == false) goto L131;
            validateResolvableType(r11, r12);
            Header r146 = (Header) r14;
            String r05 = r146.value();
            Class<?> r22 = Utils.getRawType(r12);
            if (Iterable.class.isAssignableFrom(r22) == false) goto L125;
            if ((r12 instanceof ParameterizedType) == false) goto L123;
            return new ParameterHandler.Header(r05, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) r12), r13), r146.allowUnsafeNonAsciiValues()).iterable();
        L123:
            throw Utils.parameterError(this.method, r11, r22.getSimpleName() + " must include generic type (e.g., " + r22.getSimpleName() + "<String>)", new Object[0]);
        L125:
            if (r22.isArray() == false) goto L129;
            return new ParameterHandler.Header(r05, this.retrofit.stringConverter(boxIfPrimitive(r22.getComponentType()), r13), r146.allowUnsafeNonAsciiValues()).array();
        L129:
            return new ParameterHandler.Header(r05, this.retrofit.stringConverter(r12, r13), r146.allowUnsafeNonAsciiValues());
        L131:
            if ((r14 instanceof HeaderMap) == false) goto L151;
            if (r12 == Headers.class) goto L135;
            validateResolvableType(r11, r12);
            Class<?> r06 = Utils.getRawType(r12);
            if (Map.class.isAssignableFrom(r06) == false) goto L149;
            Type r124 = Utils.getSupertype(r12, r06, Map.class);
            if ((r124 instanceof ParameterizedType) == false) goto L147;
            ParameterizedType r125 = (ParameterizedType) r124;
            Type r07 = Utils.getParameterUpperBound(0, r125);
            if (String.class != r07) goto L145;
            return new ParameterHandler.HeaderMap(this.method, r11, this.retrofit.stringConverter(Utils.getParameterUpperBound(1, r125), r13), ((HeaderMap) r14).allowUnsafeNonAsciiValues());
        L145:
            throw Utils.parameterError(this.method, r11, "@HeaderMap keys must be of type String: " + r07, new Object[0]);
        L147:
            throw Utils.parameterError(this.method, r11, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
        L149:
            throw Utils.parameterError(this.method, r11, "@HeaderMap parameter type must be Map or Headers.", new Object[0]);
        L135:
            return new ParameterHandler.Headers(this.method, r11);
        L151:
            if ((r14 instanceof Field) == false) goto L171;
            validateResolvableType(r11, r12);
            if (this.isFormEncoded == false) goto L169;
            Field r147 = (Field) r14;
            String r08 = r147.value();
            boolean r148 = r147.encoded();
            this.gotField = true;
            Class<?> r23 = Utils.getRawType(r12);
            if (Iterable.class.isAssignableFrom(r23) == false) goto L163;
            if ((r12 instanceof ParameterizedType) == false) goto L161;
            return new ParameterHandler.Field(r08, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) r12), r13), r148).iterable();
        L161:
            throw Utils.parameterError(this.method, r11, r23.getSimpleName() + " must include generic type (e.g., " + r23.getSimpleName() + "<String>)", new Object[0]);
        L163:
            if (r23.isArray() == false) goto L167;
            return new ParameterHandler.Field(r08, this.retrofit.stringConverter(boxIfPrimitive(r23.getComponentType()), r13), r148).array();
        L167:
            return new ParameterHandler.Field(r08, this.retrofit.stringConverter(r12, r13), r148);
        L169:
            throw Utils.parameterError(this.method, r11, "@Field parameters can only be used with form encoding.", new Object[0]);
        L171:
            if ((r14 instanceof FieldMap) == false) goto L191;
            validateResolvableType(r11, r12);
            if (this.isFormEncoded == false) goto L189;
            Class<?> r09 = Utils.getRawType(r12);
            if (Map.class.isAssignableFrom(r09) == false) goto L187;
            Type r126 = Utils.getSupertype(r12, r09, Map.class);
            if ((r126 instanceof ParameterizedType) == false) goto L185;
            ParameterizedType r127 = (ParameterizedType) r126;
            Type r010 = Utils.getParameterUpperBound(0, r127);
            if (String.class != r010) goto L183;
            Converter r128 = this.retrofit.stringConverter(Utils.getParameterUpperBound(1, r127), r13);
            this.gotField = true;
            return new ParameterHandler.FieldMap(this.method, r11, r128, ((FieldMap) r14).encoded());
        L183:
            throw Utils.parameterError(this.method, r11, "@FieldMap keys must be of type String: " + r010, new Object[0]);
        L185:
            throw Utils.parameterError(this.method, r11, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
        L187:
            throw Utils.parameterError(this.method, r11, "@FieldMap parameter type must be Map.", new Object[0]);
        L189:
            throw Utils.parameterError(this.method, r11, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
        L191:
            if ((r14 instanceof Part) == false) goto L251;
            validateResolvableType(r11, r12);
            if (this.isMultipart == false) goto L249;
            Part r149 = (Part) r14;
            this.gotPart = true;
            String r011 = r149.value();
            Class<?> r24 = Utils.getRawType(r12);
            if (r011.isEmpty() == true) goto L197;
            Headers r1410 = Headers.of(new String[]{"Content-Disposition", "form-data; name=\"" + r011 + "\"", "Content-Transfer-Encoding", r149.encoding()});
            if (Iterable.class.isAssignableFrom(r24) == false) goto L235;
            if ((r12 instanceof ParameterizedType) == false) goto L233;
            Type r129 = Utils.getParameterUpperBound(0, (ParameterizedType) r12);
            if (MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(r129)) == true) goto L231;
            return new ParameterHandler.Part(this.method, r11, r1410, this.retrofit.requestBodyConverter(r129, r13, this.methodAnnotations)).iterable();
        L231:
            throw Utils.parameterError(this.method, r11, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
        L233:
            throw Utils.parameterError(this.method, r11, r24.getSimpleName() + " must include generic type (e.g., " + r24.getSimpleName() + "<String>)", new Object[0]);
        L235:
            if (r24.isArray() == false) goto L243;
            Class<?> r1210 = boxIfPrimitive(r24.getComponentType());
            if (MultipartBody.Part.class.isAssignableFrom(r1210) == true) goto L241;
            return new ParameterHandler.Part(this.method, r11, r1410, this.retrofit.requestBodyConverter(r1210, r13, this.methodAnnotations)).array();
        L241:
            throw Utils.parameterError(this.method, r11, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
        L243:
            if (MultipartBody.Part.class.isAssignableFrom(r24) == true) goto L247;
            return new ParameterHandler.Part(this.method, r11, r1410, this.retrofit.requestBodyConverter(r12, r13, this.methodAnnotations));
        L247:
            throw Utils.parameterError(this.method, r11, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
        L197:
            if (Iterable.class.isAssignableFrom(r24) == false) goto L209;
            if ((r12 instanceof ParameterizedType) == false) goto L207;
            if (MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(Utils.getParameterUpperBound(0, (ParameterizedType) r12))) == false) goto L205;
            return ParameterHandler.RawPart.INSTANCE.iterable();
        L205:
            throw Utils.parameterError(this.method, r11, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
        L207:
            throw Utils.parameterError(this.method, r11, r24.getSimpleName() + " must include generic type (e.g., " + r24.getSimpleName() + "<String>)", new Object[0]);
        L209:
            if (r24.isArray() == false) goto L217;
            if (MultipartBody.Part.class.isAssignableFrom(r24.getComponentType()) == false) goto L215;
            return ParameterHandler.RawPart.INSTANCE.array();
        L215:
            throw Utils.parameterError(this.method, r11, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
        L217:
            if (MultipartBody.Part.class.isAssignableFrom(r24) == false) goto L221;
            return ParameterHandler.RawPart.INSTANCE;
        L221:
            throw Utils.parameterError(this.method, r11, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
        L249:
            throw Utils.parameterError(this.method, r11, "@Part parameters can only be used with multipart encoding.", new Object[0]);
        L251:
            if ((r14 instanceof PartMap) == false) goto L275;
            validateResolvableType(r11, r12);
            if (this.isMultipart == false) goto L273;
            this.gotPart = true;
            Class<?> r012 = Utils.getRawType(r12);
            if (Map.class.isAssignableFrom(r012) == false) goto L271;
            Type r1211 = Utils.getSupertype(r12, r012, Map.class);
            if ((r1211 instanceof ParameterizedType) == false) goto L269;
            ParameterizedType r1212 = (ParameterizedType) r1211;
            Type r013 = Utils.getParameterUpperBound(0, r1212);
            if (String.class != r013) goto L267;
            Type r1213 = Utils.getParameterUpperBound(1, r1212);
            if (MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(r1213)) == true) goto L265;
            return new ParameterHandler.PartMap(this.method, r11, this.retrofit.requestBodyConverter(r1213, r13, this.methodAnnotations), ((PartMap) r14).encoding());
        L265:
            throw Utils.parameterError(this.method, r11, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
        L267:
            throw Utils.parameterError(this.method, r11, "@PartMap keys must be of type String: " + r013, new Object[0]);
        L269:
            throw Utils.parameterError(this.method, r11, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
        L271:
            throw Utils.parameterError(this.method, r11, "@PartMap parameter type must be Map.", new Object[0]);
        L273:
            throw Utils.parameterError(this.method, r11, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
        L275:
            if ((r14 instanceof Body) == false) goto L293;
            validateResolvableType(r11, r12);
            if (this.isFormEncoded == true) goto L291;
            if (this.isMultipart == true) goto L291;
            if (this.gotBody == true) goto L289;
            Converter r1214 = this.retrofit.requestBodyConverter(r12, r13, this.methodAnnotations);     // Catch: RuntimeException -> L285
            this.gotBody = true;
            return new ParameterHandler.Body(this.method, r11, r1214);
        L285:
            e = move-exception;
            throw Utils.parameterError(this.method, e, r11, "Unable to create @Body converter for %s", new Object[]{r12});
        L289:
            throw Utils.parameterError(this.method, r11, "Multiple @Body method annotations found.", new Object[0]);
        L291:
            throw Utils.parameterError(this.method, r11, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
        L293:
            if ((r14 instanceof Tag) == false) goto L306;
            validateResolvableType(r11, r12);
            Class<?> r1215 = boxIfPrimitive(Utils.getRawType(r12));
            int r132 = r11 - 1;
        L295:
            if (r132 < 0) goto L305;
            ParameterHandler<?> r1411 = this.parameterHandlers[r132];
            if ((r1411 instanceof ParameterHandler.Tag) == false) goto L303;
            if (((ParameterHandler.Tag) r1411).cls.equals(r1215) == false) goto L303;
            throw Utils.parameterError(this.method, r11, "@Tag type " + r1215.getName() + " is duplicate of " + Platform.reflection.describeMethodParameter(this.method, r132) + " and would always overwrite its value.", new Object[0]);
        L303:
            r132 = r132 - 1;
            goto L295
        L305:
            return new ParameterHandler.Tag(r1215);
        L306:
            return null;
        }

        public static Set<String> parsePathParameters(String r2) {
            Matcher r22 = PARAM_URL_REGEX.matcher(r2);
            LinkedHashSet r0 = new LinkedHashSet();
        L4:
            if (r22.find() == false) goto L6;
            r0.add(r22.group(1));
            goto L4
        L6:
            return r0;
        }

        private void validatePathName(int r2, String r3) {
            if (PARAM_NAME_REGEX.matcher(r3).matches() == false) goto L10;
            if (this.relativeUrlParamNames.contains(r3) == false) goto L8;
            return;
        L8:
            throw Utils.parameterError(this.method, r2, "URL \"%s\" does not contain \"{%s}\".", new Object[]{this.relativeUrl, r3});
        L10:
            throw Utils.parameterError(this.method, r2, "@Path parameter name must match %s. Found: %s", new Object[]{PARAM_URL_REGEX.pattern(), r3});
        }

        private void validateResolvableType(int r2, Type r3) {
            if (Utils.hasUnresolvableType(r3) == true) goto L6;
            return;
        L6:
            throw Utils.parameterError(this.method, r2, "Parameter type must not include a type variable or wildcard: %s", new Object[]{r3});
        }

        public RequestFactory build() {
            Annotation[] r0 = this.methodAnnotations;
            int r1 = r0.length;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L6;
            parseMethodAnnotation(r0[r3]);
            r3 = r3 + 1;
            goto L3
        L6:
            if (this.httpMethod == null) goto L59;
            if (this.hasBody == false) goto L10;
        L18:
            int r02 = this.parameterAnnotationsArray.length;
            this.parameterHandlers = new ParameterHandler[r02];
            int r12 = r02 - 1;
            int r32 = 0;
        L19:
            if (r32 >= r02) goto L26;
            ParameterHandler<?>[] r4 = this.parameterHandlers;
            Type r5 = this.parameterTypes[r32];
            Annotation[] r6 = this.parameterAnnotationsArray[r32];
            if (r32 != r12) goto L23;
            boolean r7 = true;
        L24:
            r4[r32] = parseParameter(r32, r5, r6, r7);
            r32 = r32 + 1;
            goto L19
        L23:
            r7 = false;
            goto L24
        L26:
            if (this.relativeUrl == null) goto L28;
        L32:
            boolean r03 = this.isFormEncoded;
            if (r03 == false) goto L35;
        L43:
            if (r03 == false) goto L50;
            if (this.gotField == true) goto L50;
            throw Utils.methodError(this.method, "Form-encoded method must contain at least one @Field.", new Object[0]);
        L50:
            if (this.isMultipart == false) goto L57;
            if (this.gotPart == true) goto L57;
            throw Utils.methodError(this.method, "Multipart method must contain at least one @Part.", new Object[0]);
        L57:
            return new RequestFactory(this);
        L35:
            if (this.isMultipart == true) goto L43;
            if (this.hasBody == true) goto L43;
            if (this.gotBody == false) goto L43;
            throw Utils.methodError(this.method, "Non-body HTTP method cannot contain @Body.", new Object[0]);
        L28:
            if (this.gotUrl == true) goto L32;
            throw Utils.methodError(this.method, "Missing either @%s URL or @Url parameter.", new Object[]{this.httpMethod});
        L10:
            if (this.isMultipart == true) goto L17;
            if (this.isFormEncoded == false) goto L18;
            throw Utils.methodError(this.method, "FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
        L17:
            throw Utils.methodError(this.method, "Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
        L59:
            throw Utils.methodError(this.method, "HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
        }
    }

    public RequestFactory(Builder r2) {
        this.service = r2.service;
        this.method = r2.method;
        this.baseUrl = r2.retrofit.baseUrl;
        this.httpMethod = r2.httpMethod;
        this.relativeUrl = r2.relativeUrl;
        this.headers = r2.headers;
        this.contentType = r2.contentType;
        this.hasBody = r2.hasBody;
        this.isFormEncoded = r2.isFormEncoded;
        this.isMultipart = r2.isMultipart;
        this.parameterHandlers = r2.parameterHandlers;
        this.isKotlinSuspendFunction = r2.isKotlinSuspendFunction;
    }

    public static RequestFactory parseAnnotations(Retrofit r1, Class<?> r2, Method r3) {
        return new Builder(r1, r2, r3).build();
    }

    public Request create(Object r13, Object[] r14) {
        ParameterHandler<?>[] r0 = this.parameterHandlers;
        int r1 = r14.length;
        if (r1 != r0.length) goto L13;
        RequestBuilder r3 = new RequestBuilder(this.httpMethod, this.baseUrl, this.relativeUrl, this.headers, this.contentType, this.hasBody, this.isFormEncoded, this.isMultipart);
        if (this.isKotlinSuspendFunction == false) goto L7;
        r1 = r1 - 1;
    L7:
        ArrayList r2 = new ArrayList(r1);
        int r4 = 0;
    L8:
        if (r4 >= r1) goto L11;
        r2.add(r14[r4]);
        r0[r4].apply(r3, r14[r4]);
        r4 = r4 + 1;
        goto L8
    L11:
        return r3.get().tag(Invocation.class, new Invocation(this.service, r13, this.method, r2)).build();
    L13:
        throw new IllegalArgumentException("Argument count (" + r1 + ") doesn't match expected count (" + r0.length + ")");
    }
}
