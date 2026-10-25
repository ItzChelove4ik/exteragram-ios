package kotlin.jvm.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Function;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.scilab.forge.jlatexmath.TeXFormulaSettingsParser;

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 S2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001SB\u0015\bF\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0002H\u0097\u0080\u0004J\n\u0010J\u001a\u00020KH\u0082\u0080\u0004J\n\u0010L\u001a\u00020MH\u0096\u0080\u0004J\u0014\u0010N\u001a\u00020$2\b\u0010O\u001a\u0004\u0018\u00010\u0002H\u0096\u0082\u0004J\n\u0010P\u001a\u00020QH\u0096\u0080\u0004J\n\u0010R\u001a\u00020\fH\u0096\u0080\u0004R\u0019\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\u0004\u0018\u00010\f8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\u0004\u0018\u00010\f8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u001f\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u00128VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R!\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00170\u00128VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0015R\u001f\u0010\u0019\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u00128VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0015R\u001b\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010 \u001a\u0004\u0018\u00010\u00028VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b!\u0010\"R!\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u001c8VX\u0097\u0084\b¢\u0006\f\u0012\u0004\b(\u0010)\u001a\u0004\b*\u0010\u001fR!\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u001c8VX\u0097\u0084\b¢\u0006\f\u0012\u0004\b-\u0010)\u001a\u0004\b.\u0010\u001fR)\u0010/\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00010\u001c8VX\u0097\u0084\b¢\u0006\f\u0012\u0004\b0\u0010)\u001a\u0004\b1\u0010\u001fR\u001d\u00102\u001a\u0004\u0018\u0001038VX\u0097\u0084\b¢\u0006\f\u0012\u0004\b4\u0010)\u001a\u0004\b5\u00106R\u001b\u00107\u001a\u00020$8VX\u0097\u0084\b¢\u0006\f\u0012\u0004\b8\u0010)\u001a\u0004\b7\u00109R\u001b\u0010:\u001a\u00020$8VX\u0097\u0084\b¢\u0006\f\u0012\u0004\b;\u0010)\u001a\u0004\b:\u00109R\u001b\u0010<\u001a\u00020$8VX\u0097\u0084\b¢\u0006\f\u0012\u0004\b=\u0010)\u001a\u0004\b<\u00109R\u001b\u0010>\u001a\u00020$8VX\u0097\u0084\b¢\u0006\f\u0012\u0004\b?\u0010)\u001a\u0004\b>\u00109R\u001b\u0010@\u001a\u00020$8VX\u0097\u0084\b¢\u0006\f\u0012\u0004\bA\u0010)\u001a\u0004\b@\u00109R\u001b\u0010B\u001a\u00020$8VX\u0097\u0084\b¢\u0006\f\u0012\u0004\bC\u0010)\u001a\u0004\bB\u00109R\u001b\u0010D\u001a\u00020$8VX\u0097\u0084\b¢\u0006\f\u0012\u0004\bE\u0010)\u001a\u0004\bD\u00109R\u001b\u0010F\u001a\u00020$8VX\u0097\u0084\b¢\u0006\f\u0012\u0004\bG\u0010)\u001a\u0004\bF\u00109R\u001b\u0010H\u001a\u00020$8VX\u0097\u0084\b¢\u0006\f\u0012\u0004\bI\u0010)\u001a\u0004\bH\u00109¨\u0006T"}, d2 = {"Lkotlin/jvm/internal/ClassReference;", "Lkotlin/reflect/KClass;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "Lkotlin/jvm/internal/KotlinGenericDeclaration;", "jClass", "Ljava/lang/Class;", "<init>", "(Ljava/lang/Class;)V", "getJClass", "()Ljava/lang/Class;", "simpleName", _UrlKt.FRAGMENT_ENCODE_SET, "getSimpleName", "()Ljava/lang/String;", "qualifiedName", "getQualifiedName", "members", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KCallable;", "getMembers", "()Ljava/util/Collection;", "constructors", "Lkotlin/reflect/KFunction;", "getConstructors", "nestedClasses", "getNestedClasses", "annotations", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getAnnotations", "()Ljava/util/List;", "objectInstance", "getObjectInstance", "()Ljava/lang/Object;", "isInstance", _UrlKt.FRAGMENT_ENCODE_SET, "value", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters$annotations", "()V", "getTypeParameters", "supertypes", "Lkotlin/reflect/KType;", "getSupertypes$annotations", "getSupertypes", "sealedSubclasses", "getSealedSubclasses$annotations", "getSealedSubclasses", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "isFinal", "isFinal$annotations", "()Z", "isOpen", "isOpen$annotations", "isAbstract", "isAbstract$annotations", "isSealed", "isSealed$annotations", "isData", "isData$annotations", "isInner", "isInner$annotations", "isCompanion", "isCompanion$annotations", "isFun", "isFun$annotations", "isValue", "isValue$annotations", "error", _UrlKt.FRAGMENT_ENCODE_SET, "findJavaDeclaration", "Ljava/lang/reflect/GenericDeclaration;", "equals", "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nClassReference.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClassReference.kt\nkotlin/jvm/internal/ClassReference\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,293:1\n1596#2:294\n1629#2,4:295\n*S KotlinDebug\n*F\n+ 1 ClassReference.kt\nkotlin/jvm/internal/ClassReference\n*L\n110#1:294\n110#1:295,4\n*E\n"})
/* loaded from: classes.dex */
public final class ClassReference implements KClass<Object>, ClassBasedDeclarationContainer, KotlinGenericDeclaration {
    public static final Companion Companion = null;
    private static final Map<Class<? extends Function<?>>, Integer> FUNCTION_CLASSES = null;
    private final Class<?> jClass;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\nH\u0082\u0080\u0004J\u0014\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\nH\u0082\u0080\u0004J\u0018\u0010\r\u001a\u0004\u0018\u00010\n2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0086\u0080\u0004J\u0018\u0010\u000f\u001a\u0004\u0018\u00010\n2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0086\u0080\u0004J \u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00012\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0086\u0080\u0004R'\u0010\u0004\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlin/jvm/internal/ClassReference$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "FUNCTION_CLASSES", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/Class;", "Lkotlin/Function;", _UrlKt.FRAGMENT_ENCODE_SET, "classFqNameOf", _UrlKt.FRAGMENT_ENCODE_SET, "type", "simpleNameOf", "getClassSimpleName", "jClass", "getClassQualifiedName", "isInstance", _UrlKt.FRAGMENT_ENCODE_SET, "value", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nClassReference.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClassReference.kt\nkotlin/jvm/internal/ClassReference$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,293:1\n1#2:294\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final String classFqNameOf(String r9) {
            int r8 = r9.hashCode();
            switch(r8) {
                case -2061550653: goto L318;
                case -2056817302: goto L314;
                case -2034166429: goto L309;
                case -1979556166: goto L304;
                case -1571515090: goto L299;
                case -1383349348: goto L294;
                case -1383343454: goto L289;
                case -1325958191: goto L285;
                case -1182275604: goto L280;
                case -1062240117: goto L275;
                case -688322466: goto L270;
                case -527879800: goto L266;
                case -515992664: goto L262;
                case -246476834: goto L257;
                case -207262728: goto L252;
                case -165139126: goto L247;
                case 104431: goto L243;
                case 3039496: goto L239;
                case 3052374: goto L235;
                case 3327612: goto L231;
                case 64711720: goto L227;
                case 65821278: goto L222;
                case 77230534: goto L217;
                case 97526364: goto L213;
                case 109413500: goto L209;
                case 155276373: goto L205;
                case 226173651: goto L200;
                case 344809556: goto L196;
                case 398507100: goto L192;
                case 398585941: goto L187;
                case 398795216: goto L183;
                case 482629606: goto L178;
                case 499831342: goto L173;
                case 577341676: goto L168;
                case 599019395: goto L163;
                case 761287205: goto L159;
                case 1052881309: goto L154;
                case 1063877011: goto L149;
                case 1195259493: goto L144;
                case 1275614662: goto L139;
                case 1383693018: goto L134;
                case 1630335596: goto L129;
                case 1877171123: goto L124;
                default: goto L4;
            };
        L4:
            switch(r8) {
                case -1811142716: goto L119;
                case -1811142715: goto L114;
                case -1811142714: goto L109;
                case -1811142713: goto L104;
                case -1811142712: goto L99;
                case -1811142711: goto L94;
                case -1811142710: goto L89;
                case -1811142709: goto L84;
                case -1811142708: goto L79;
                case -1811142707: goto L74;
                default: goto L5;
            };
        L5:
            switch(r8) {
                case -1811142685: goto L69;
                case -1811142684: goto L64;
                case -1811142683: goto L59;
                default: goto L6;
            };
        L6:
            switch(r8) {
                case 80123371: goto L54;
                case 80123372: goto L49;
                case 80123373: goto L44;
                case 80123374: goto L39;
                case 80123375: goto L34;
                case 80123376: goto L29;
                case 80123377: goto L24;
                case 80123378: goto L19;
                case 80123379: goto L14;
                case 80123380: goto L9;
                default: goto L323;
            };
        L323:
            return null;
        L9:
            if (r9.equals("kotlin.jvm.functions.Function9") == false) goto L324;
            return "kotlin.Function9";
        L324:
            return null;
        L14:
            if (r9.equals("kotlin.jvm.functions.Function8") == false) goto L325;
            return "kotlin.Function8";
        L325:
            return null;
        L19:
            if (r9.equals("kotlin.jvm.functions.Function7") == false) goto L326;
            return "kotlin.Function7";
        L326:
            return null;
        L24:
            if (r9.equals("kotlin.jvm.functions.Function6") == false) goto L327;
            return "kotlin.Function6";
        L327:
            return null;
        L29:
            if (r9.equals("kotlin.jvm.functions.Function5") == false) goto L328;
            return "kotlin.Function5";
        L328:
            return null;
        L34:
            if (r9.equals("kotlin.jvm.functions.Function4") == false) goto L329;
            return "kotlin.Function4";
        L329:
            return null;
        L39:
            if (r9.equals("kotlin.jvm.functions.Function3") == false) goto L330;
            return "kotlin.Function3";
        L330:
            return null;
        L44:
            if (r9.equals("kotlin.jvm.functions.Function2") == false) goto L331;
            return "kotlin.Function2";
        L331:
            return null;
        L49:
            if (r9.equals("kotlin.jvm.functions.Function1") == false) goto L332;
            return "kotlin.Function1";
        L332:
            return null;
        L54:
            if (r9.equals("kotlin.jvm.functions.Function0") == false) goto L333;
            return "kotlin.Function0";
        L333:
            return null;
        L59:
            if (r9.equals("kotlin.jvm.functions.Function22") == false) goto L334;
            return "kotlin.Function22";
        L334:
            return null;
        L64:
            if (r9.equals("kotlin.jvm.functions.Function21") == false) goto L335;
            return "kotlin.Function21";
        L335:
            return null;
        L69:
            if (r9.equals("kotlin.jvm.functions.Function20") == false) goto L336;
            return "kotlin.Function20";
        L336:
            return null;
        L74:
            if (r9.equals("kotlin.jvm.functions.Function19") == false) goto L337;
            return "kotlin.Function19";
        L337:
            return null;
        L79:
            if (r9.equals("kotlin.jvm.functions.Function18") == false) goto L338;
            return "kotlin.Function18";
        L338:
            return null;
        L84:
            if (r9.equals("kotlin.jvm.functions.Function17") == false) goto L339;
            return "kotlin.Function17";
        L339:
            return null;
        L89:
            if (r9.equals("kotlin.jvm.functions.Function16") == false) goto L340;
            return "kotlin.Function16";
        L340:
            return null;
        L94:
            if (r9.equals("kotlin.jvm.functions.Function15") == false) goto L341;
            return "kotlin.Function15";
        L341:
            return null;
        L99:
            if (r9.equals("kotlin.jvm.functions.Function14") == false) goto L342;
            return "kotlin.Function14";
        L342:
            return null;
        L104:
            if (r9.equals("kotlin.jvm.functions.Function13") == false) goto L343;
            return "kotlin.Function13";
        L343:
            return null;
        L109:
            if (r9.equals("kotlin.jvm.functions.Function12") == false) goto L344;
            return "kotlin.Function12";
        L344:
            return null;
        L114:
            if (r9.equals("kotlin.jvm.functions.Function11") == false) goto L345;
            return "kotlin.Function11";
        L345:
            return null;
        L119:
            if (r9.equals("kotlin.jvm.functions.Function10") == false) goto L346;
            return "kotlin.Function10";
        L346:
            return null;
        L124:
            if (r9.equals("kotlin.jvm.internal.IntCompanionObject") == false) goto L347;
            return "kotlin.Int.Companion";
        L347:
            return null;
        L129:
            if (r9.equals("java.lang.Throwable") == false) goto L348;
            return "kotlin.Throwable";
        L348:
            return null;
        L134:
            if (r9.equals("kotlin.jvm.internal.BooleanCompanionObject") == false) goto L349;
            return "kotlin.Boolean.Companion";
        L349:
            return null;
        L139:
            if (r9.equals("java.lang.Iterable") == false) goto L350;
            return "kotlin.collections.Iterable";
        L350:
            return null;
        L144:
            if (r9.equals("java.lang.String") == false) goto L351;
            return "kotlin.String";
        L351:
            return null;
        L149:
            if (r9.equals("java.lang.Object") == false) goto L352;
            return "kotlin.Any";
        L352:
            return null;
        L154:
            if (r9.equals("java.lang.Number") == false) goto L353;
            return "kotlin.Number";
        L353:
            return null;
        L159:
            if (r9.equals("java.lang.Double") == false) goto L354;
            return "kotlin.Double";
        L354:
            return null;
        L163:
            if (r9.equals("kotlin.jvm.internal.StringCompanionObject") == false) goto L355;
            return "kotlin.String.Companion";
        L355:
            return null;
        L168:
            if (r9.equals("java.util.ListIterator") == false) goto L356;
            return "kotlin.collections.ListIterator";
        L356:
            return null;
        L173:
            if (r9.equals("java.util.Iterator") == false) goto L357;
            return "kotlin.collections.Iterator";
        L357:
            return null;
        L178:
            if (r9.equals("kotlin.jvm.internal.FloatCompanionObject") == false) goto L358;
            return "kotlin.Float.Companion";
        L358:
            return null;
        L183:
            if (r9.equals("java.lang.Long") == false) goto L359;
            return "kotlin.Long";
        L359:
            return null;
        L187:
            if (r9.equals("java.lang.Enum") == false) goto L360;
            return "kotlin.Enum";
        L360:
            return null;
        L192:
            if (r9.equals("java.lang.Byte") == false) goto L361;
            return "kotlin.Byte";
        L361:
            return null;
        L196:
            if (r9.equals("java.lang.Boolean") == false) goto L362;
            return "kotlin.Boolean";
        L362:
            return null;
        L200:
            if (r9.equals("kotlin.jvm.internal.EnumCompanionObject") == false) goto L363;
            return "kotlin.Enum.Companion";
        L363:
            return null;
        L205:
            if (r9.equals("java.lang.Character") == false) goto L364;
            return "kotlin.Char";
        L364:
            return null;
        L209:
            if (r9.equals("short") == false) goto L365;
            return "kotlin.Short";
        L365:
            return null;
        L213:
            if (r9.equals("float") == false) goto L366;
            return "kotlin.Float";
        L366:
            return null;
        L217:
            if (r9.equals("kotlin.jvm.internal.ShortCompanionObject") == false) goto L367;
            return "kotlin.Short.Companion";
        L367:
            return null;
        L222:
            if (r9.equals("java.util.List") == false) goto L368;
            return "kotlin.collections.List";
        L368:
            return null;
        L227:
            if (r9.equals("boolean") == false) goto L369;
            return "kotlin.Boolean";
        L369:
            return null;
        L231:
            if (r9.equals("long") == false) goto L370;
            return "kotlin.Long";
        L370:
            return null;
        L235:
            if (r9.equals("char") == false) goto L371;
            return "kotlin.Char";
        L371:
            return null;
        L239:
            if (r9.equals("byte") == false) goto L372;
            return "kotlin.Byte";
        L372:
            return null;
        L243:
            if (r9.equals("int") == false) goto L373;
            return "kotlin.Int";
        L373:
            return null;
        L247:
            if (r9.equals("java.util.Map$Entry") == false) goto L374;
            return "kotlin.collections.Map.Entry";
        L374:
            return null;
        L252:
            if (r9.equals("kotlin.jvm.internal.LongCompanionObject") == false) goto L375;
            return "kotlin.Long.Companion";
        L375:
            return null;
        L257:
            if (r9.equals("kotlin.jvm.internal.CharCompanionObject") == false) goto L376;
            return "kotlin.Char.Companion";
        L376:
            return null;
        L262:
            if (r9.equals("java.lang.Short") == false) goto L377;
            return "kotlin.Short";
        L377:
            return null;
        L266:
            if (r9.equals("java.lang.Float") == false) goto L378;
            return "kotlin.Float";
        L378:
            return null;
        L270:
            if (r9.equals("java.util.Collection") == false) goto L379;
            return "kotlin.collections.Collection";
        L379:
            return null;
        L275:
            if (r9.equals("java.lang.CharSequence") == false) goto L380;
            return "kotlin.CharSequence";
        L380:
            return null;
        L280:
            if (r9.equals("kotlin.jvm.internal.ByteCompanionObject") == false) goto L381;
            return "kotlin.Byte.Companion";
        L381:
            return null;
        L285:
            if (r9.equals("double") == false) goto L382;
            return "kotlin.Double";
        L382:
            return null;
        L289:
            if (r9.equals("java.util.Set") == false) goto L383;
            return "kotlin.collections.Set";
        L383:
            return null;
        L294:
            if (r9.equals("java.util.Map") == false) goto L384;
            return "kotlin.collections.Map";
        L384:
            return null;
        L299:
            if (r9.equals("java.lang.Comparable") == false) goto L385;
            return "kotlin.Comparable";
        L385:
            return null;
        L304:
            if (r9.equals("java.lang.annotation.Annotation") == false) goto L386;
            return "kotlin.Annotation";
        L386:
            return null;
        L309:
            if (r9.equals("java.lang.Cloneable") == false) goto L387;
            return "kotlin.Cloneable";
        L387:
            return null;
        L314:
            if (r9.equals("java.lang.Integer") == false) goto L388;
            return "kotlin.Int";
        L388:
            return null;
        L318:
            if (r9.equals("kotlin.jvm.internal.DoubleCompanionObject") == true) goto L321;
            return null;
        L321:
            return "kotlin.Double.Companion";
        }

        private final String simpleNameOf(String r10) {
            int r9 = r10.hashCode();
            switch(r9) {
                case -2061550653: goto L309;
                case -2056817302: goto L305;
                case -2034166429: goto L300;
                case -1979556166: goto L295;
                case -1571515090: goto L290;
                case -1383349348: goto L285;
                case -1383343454: goto L280;
                case -1325958191: goto L276;
                case -1182275604: goto L272;
                case -1062240117: goto L267;
                case -688322466: goto L262;
                case -527879800: goto L258;
                case -515992664: goto L254;
                case -246476834: goto L250;
                case -207262728: goto L246;
                case -165139126: goto L241;
                case 104431: goto L237;
                case 3039496: goto L233;
                case 3052374: goto L229;
                case 3327612: goto L225;
                case 64711720: goto L221;
                case 65821278: goto L216;
                case 77230534: goto L212;
                case 97526364: goto L208;
                case 109413500: goto L204;
                case 155276373: goto L200;
                case 226173651: goto L196;
                case 344809556: goto L192;
                case 398507100: goto L188;
                case 398585941: goto L183;
                case 398795216: goto L179;
                case 482629606: goto L175;
                case 499831342: goto L170;
                case 577341676: goto L165;
                case 599019395: goto L161;
                case 761287205: goto L157;
                case 1052881309: goto L152;
                case 1063877011: goto L147;
                case 1195259493: goto L142;
                case 1275614662: goto L137;
                case 1383693018: goto L133;
                case 1630335596: goto L128;
                case 1877171123: goto L124;
                default: goto L4;
            };
        L4:
            switch(r9) {
                case -1811142716: goto L119;
                case -1811142715: goto L114;
                case -1811142714: goto L109;
                case -1811142713: goto L104;
                case -1811142712: goto L99;
                case -1811142711: goto L94;
                case -1811142710: goto L89;
                case -1811142709: goto L84;
                case -1811142708: goto L79;
                case -1811142707: goto L74;
                default: goto L5;
            };
        L5:
            switch(r9) {
                case -1811142685: goto L69;
                case -1811142684: goto L64;
                case -1811142683: goto L59;
                default: goto L6;
            };
        L6:
            switch(r9) {
                case 80123371: goto L54;
                case 80123372: goto L49;
                case 80123373: goto L44;
                case 80123374: goto L39;
                case 80123375: goto L34;
                case 80123376: goto L29;
                case 80123377: goto L24;
                case 80123378: goto L19;
                case 80123379: goto L14;
                case 80123380: goto L9;
                default: goto L313;
            };
        L313:
            return null;
        L9:
            if (r10.equals("kotlin.jvm.functions.Function9") == false) goto L314;
            return "Function9";
        L314:
            return null;
        L14:
            if (r10.equals("kotlin.jvm.functions.Function8") == false) goto L315;
            return "Function8";
        L315:
            return null;
        L19:
            if (r10.equals("kotlin.jvm.functions.Function7") == false) goto L316;
            return "Function7";
        L316:
            return null;
        L24:
            if (r10.equals("kotlin.jvm.functions.Function6") == false) goto L317;
            return "Function6";
        L317:
            return null;
        L29:
            if (r10.equals("kotlin.jvm.functions.Function5") == false) goto L318;
            return "Function5";
        L318:
            return null;
        L34:
            if (r10.equals("kotlin.jvm.functions.Function4") == false) goto L319;
            return "Function4";
        L319:
            return null;
        L39:
            if (r10.equals("kotlin.jvm.functions.Function3") == false) goto L320;
            return "Function3";
        L320:
            return null;
        L44:
            if (r10.equals("kotlin.jvm.functions.Function2") == false) goto L321;
            return "Function2";
        L321:
            return null;
        L49:
            if (r10.equals("kotlin.jvm.functions.Function1") == false) goto L322;
            return "Function1";
        L322:
            return null;
        L54:
            if (r10.equals("kotlin.jvm.functions.Function0") == false) goto L323;
            return "Function0";
        L323:
            return null;
        L59:
            if (r10.equals("kotlin.jvm.functions.Function22") == false) goto L324;
            return "Function22";
        L324:
            return null;
        L64:
            if (r10.equals("kotlin.jvm.functions.Function21") == false) goto L325;
            return "Function21";
        L325:
            return null;
        L69:
            if (r10.equals("kotlin.jvm.functions.Function20") == false) goto L326;
            return "Function20";
        L326:
            return null;
        L74:
            if (r10.equals("kotlin.jvm.functions.Function19") == false) goto L327;
            return "Function19";
        L327:
            return null;
        L79:
            if (r10.equals("kotlin.jvm.functions.Function18") == false) goto L328;
            return "Function18";
        L328:
            return null;
        L84:
            if (r10.equals("kotlin.jvm.functions.Function17") == false) goto L329;
            return "Function17";
        L329:
            return null;
        L89:
            if (r10.equals("kotlin.jvm.functions.Function16") == false) goto L330;
            return "Function16";
        L330:
            return null;
        L94:
            if (r10.equals("kotlin.jvm.functions.Function15") == false) goto L331;
            return "Function15";
        L331:
            return null;
        L99:
            if (r10.equals("kotlin.jvm.functions.Function14") == false) goto L332;
            return "Function14";
        L332:
            return null;
        L104:
            if (r10.equals("kotlin.jvm.functions.Function13") == false) goto L333;
            return "Function13";
        L333:
            return null;
        L109:
            if (r10.equals("kotlin.jvm.functions.Function12") == false) goto L334;
            return "Function12";
        L334:
            return null;
        L114:
            if (r10.equals("kotlin.jvm.functions.Function11") == false) goto L335;
            return "Function11";
        L335:
            return null;
        L119:
            if (r10.equals("kotlin.jvm.functions.Function10") == false) goto L336;
            return "Function10";
        L336:
            return null;
        L124:
            if (r10.equals("kotlin.jvm.internal.IntCompanionObject") == false) goto L337;
            return "Companion";
        L337:
            return null;
        L128:
            if (r10.equals("java.lang.Throwable") == false) goto L338;
            return "Throwable";
        L338:
            return null;
        L133:
            if (r10.equals("kotlin.jvm.internal.BooleanCompanionObject") == false) goto L339;
            return "Companion";
        L339:
            return null;
        L137:
            if (r10.equals("java.lang.Iterable") == false) goto L340;
            return "Iterable";
        L340:
            return null;
        L142:
            if (r10.equals("java.lang.String") == false) goto L341;
            return "String";
        L341:
            return null;
        L147:
            if (r10.equals("java.lang.Object") == false) goto L342;
            return "Any";
        L342:
            return null;
        L152:
            if (r10.equals("java.lang.Number") == false) goto L343;
            return "Number";
        L343:
            return null;
        L157:
            if (r10.equals("java.lang.Double") == false) goto L344;
            return "Double";
        L344:
            return null;
        L161:
            if (r10.equals("kotlin.jvm.internal.StringCompanionObject") == false) goto L345;
            return "Companion";
        L345:
            return null;
        L165:
            if (r10.equals("java.util.ListIterator") == false) goto L346;
            return "ListIterator";
        L346:
            return null;
        L170:
            if (r10.equals("java.util.Iterator") == false) goto L347;
            return "Iterator";
        L347:
            return null;
        L175:
            if (r10.equals("kotlin.jvm.internal.FloatCompanionObject") == false) goto L348;
            return "Companion";
        L348:
            return null;
        L179:
            if (r10.equals("java.lang.Long") == false) goto L349;
            return "Long";
        L349:
            return null;
        L183:
            if (r10.equals("java.lang.Enum") == false) goto L350;
            return "Enum";
        L350:
            return null;
        L188:
            if (r10.equals("java.lang.Byte") == false) goto L351;
            return "Byte";
        L351:
            return null;
        L192:
            if (r10.equals("java.lang.Boolean") == false) goto L352;
            return "Boolean";
        L352:
            return null;
        L196:
            if (r10.equals("kotlin.jvm.internal.EnumCompanionObject") == false) goto L353;
            return "Companion";
        L353:
            return null;
        L200:
            if (r10.equals("java.lang.Character") == false) goto L354;
            return "Char";
        L354:
            return null;
        L204:
            if (r10.equals("short") == false) goto L355;
            return "Short";
        L355:
            return null;
        L208:
            if (r10.equals("float") == false) goto L356;
            return "Float";
        L356:
            return null;
        L212:
            if (r10.equals("kotlin.jvm.internal.ShortCompanionObject") == false) goto L357;
            return "Companion";
        L357:
            return null;
        L216:
            if (r10.equals("java.util.List") == false) goto L358;
            return "List";
        L358:
            return null;
        L221:
            if (r10.equals("boolean") == false) goto L359;
            return "Boolean";
        L359:
            return null;
        L225:
            if (r10.equals("long") == false) goto L360;
            return "Long";
        L360:
            return null;
        L229:
            if (r10.equals("char") == false) goto L361;
            return "Char";
        L361:
            return null;
        L233:
            if (r10.equals("byte") == false) goto L362;
            return "Byte";
        L362:
            return null;
        L237:
            if (r10.equals("int") == false) goto L363;
            return "Int";
        L363:
            return null;
        L241:
            if (r10.equals("java.util.Map$Entry") == false) goto L364;
            return "Entry";
        L364:
            return null;
        L246:
            if (r10.equals("kotlin.jvm.internal.LongCompanionObject") == false) goto L365;
            return "Companion";
        L365:
            return null;
        L250:
            if (r10.equals("kotlin.jvm.internal.CharCompanionObject") == false) goto L366;
            return "Companion";
        L366:
            return null;
        L254:
            if (r10.equals("java.lang.Short") == false) goto L367;
            return "Short";
        L367:
            return null;
        L258:
            if (r10.equals("java.lang.Float") == false) goto L368;
            return "Float";
        L368:
            return null;
        L262:
            if (r10.equals("java.util.Collection") == false) goto L369;
            return "Collection";
        L369:
            return null;
        L267:
            if (r10.equals("java.lang.CharSequence") == false) goto L370;
            return "CharSequence";
        L370:
            return null;
        L272:
            if (r10.equals("kotlin.jvm.internal.ByteCompanionObject") == false) goto L371;
            return "Companion";
        L371:
            return null;
        L276:
            if (r10.equals("double") == false) goto L372;
            return "Double";
        L372:
            return null;
        L280:
            if (r10.equals("java.util.Set") == false) goto L373;
            return "Set";
        L373:
            return null;
        L285:
            if (r10.equals("java.util.Map") == false) goto L374;
            return TeXFormulaSettingsParser.CHARTODEL_MAPPING_EL;
        L374:
            return null;
        L290:
            if (r10.equals("java.lang.Comparable") == false) goto L375;
            return "Comparable";
        L375:
            return null;
        L295:
            if (r10.equals("java.lang.annotation.Annotation") == false) goto L376;
            return "Annotation";
        L376:
            return null;
        L300:
            if (r10.equals("java.lang.Cloneable") == false) goto L377;
            return "Cloneable";
        L377:
            return null;
        L305:
            if (r10.equals("java.lang.Integer") == false) goto L378;
            return "Int";
        L378:
            return null;
        L309:
            if (r10.equals("kotlin.jvm.internal.DoubleCompanionObject") == true) goto L312;
            return null;
        L312:
            return "Companion";
        }

        public final String getClassQualifiedName(Class<?> r3) {
            String r1 = null;
            if (r3.isAnonymousClass() == false) goto L6;
            return null;
        L6:
            if (r3.isLocalClass() == false) goto L9;
            return null;
        L9:
            if (r3.isArray() == false) goto L19;
            Class<?> r32 = r3.getComponentType();
            if (r32.isPrimitive() == false) goto L15;
            String r2 = classFqNameOf(r32.getName());
            if (r2 == null) goto L15;
            r1 = r2.concat("Array");
        L15:
            if (r1 != null) goto L18;
            return "kotlin.Array";
        L18:
            return r1;
        L19:
            String r22 = classFqNameOf(r3.getName());
            if (r22 == null) goto L22;
            return r22;
        L22:
            return r3.getCanonicalName();
        }

        public final String getClassSimpleName(Class<?> r6) {
            String r1 = null;
            if (r6.isAnonymousClass() == false) goto L6;
            return null;
        L6:
            if (r6.isLocalClass() == false) goto L20;
            String r5 = r6.getSimpleName();
            Method r0 = r6.getEnclosingMethod();
            if (r0 == null) goto L13;
            String r02 = StringsKt.substringAfter$default(r5, r0.getName() + Typography.dollar, null, 2, null);
            if (r02 == null) goto L13;
            return r02;
        L13:
            Constructor<?> r62 = r6.getEnclosingConstructor();
            if (r62 == null) goto L18;
            return StringsKt.substringAfter$default(r5, r62.getName() + Typography.dollar, null, 2, null);
        L18:
            return StringsKt.substringAfter$default(r5, Typography.dollar, null, 2, null);
        L20:
            if (r6.isArray() == false) goto L29;
            Class<?> r63 = r6.getComponentType();
            if (r63.isPrimitive() == false) goto L26;
            String r52 = simpleNameOf(r63.getName());
            if (r52 == null) goto L26;
            r1 = r52.concat("Array");
        L26:
            if (r1 != null) goto L28;
            return "Array";
        L28:
            return r1;
        L29:
            String r53 = simpleNameOf(r6.getName());
            if (r53 == null) goto L32;
            return r53;
        L32:
            return r6.getSimpleName();
        }

        public final boolean isInstance(Object r1, Class<?> r2) {
            Integer r0 = (Integer) ClassReference.access$getFUNCTION_CLASSES$cp().get(r2);
            if (r0 == null) goto L7;
            return TypeIntrinsics.isFunctionOfArity(r1, r0.intValue());
        L7:
            if (r2.isPrimitive() == false) goto L10;
            r2 = JvmClassMappingKt.getJavaObjectType(JvmClassMappingKt.getKotlinClass(r2));
        L10:
            return r2.isInstance(r1);
        }
    }

    static {
        Companion = new Companion(null);
        List r0 = CollectionsKt.listOf(new Class[]{Function0.class, Function1.class, Function2.class, Function3.class, Function4.class, Function5.class, Function6.class, Function7.class, Function8.class, Function9.class, Function10.class, Function11.class, Function12.class, Function13.class, Function14.class, Function15.class, Function16.class, Function17.class, Function18.class, Function19.class, Function20.class, Function21.class, Function22.class});
        ArrayList r1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r0, 10));
        Iterator r02 = r0.iterator();
        int r2 = 0;
    L4:
        if (r02.hasNext() == false) goto L9;
        Object r3 = r02.next();
        int r4 = r2 + 1;
        if (r2 >= 0) goto L8;
        CollectionsKt.throwIndexOverflow();
    L8:
        r1.add(TuplesKt.to((Class) r3, Integer.valueOf(r2)));
        r2 = r4;
        goto L4
    L9:
        FUNCTION_CLASSES = MapsKt.toMap(r1);
    }

    public ClassReference(Class<?> r1) {
        this.jClass = r1;
    }

    public static final /* synthetic */ Map access$getFUNCTION_CLASSES$cp() {
        return FUNCTION_CLASSES;
    }

    private final Void error() {
        throw new KotlinReflectionNotSupportedError();
    }

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void getSealedSubclasses$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getSupertypes$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getTypeParameters$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void getVisibility$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isAbstract$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isCompanion$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isData$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isFinal$annotations() {
    }

    @SinceKotlin(version = "1.4")
    public static /* synthetic */ void isFun$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isInner$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isOpen$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void isSealed$annotations() {
    }

    @SinceKotlin(version = "1.5")
    public static /* synthetic */ void isValue$annotations() {
    }

    @Override // kotlin.reflect.KClass
    public boolean equals(Object r2) {
        if ((r2 instanceof ClassReference) == true) goto L5;
        return false;
    L5:
        if (Intrinsics.areEqual(JvmClassMappingKt.getJavaObjectType(this), JvmClassMappingKt.getJavaObjectType((KClass) r2)) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // kotlin.jvm.internal.KotlinGenericDeclaration
    public GenericDeclaration findJavaDeclaration() {
        return getJClass();
    }

    @Override // kotlin.reflect.KAnnotatedElement
    public List<Annotation> getAnnotations() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public Collection<KFunction<Object>> getConstructors() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class<?> getJClass() {
        return this.jClass;
    }

    @Override // kotlin.reflect.KClass, kotlin.reflect.KDeclarationContainer
    public Collection<KCallable<?>> getMembers() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public Collection<KClass<?>> getNestedClasses() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public Object getObjectInstance() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public String getQualifiedName() {
        return Companion.getClassQualifiedName(getJClass());
    }

    @Override // kotlin.reflect.KClass
    public List<KClass<? extends Object>> getSealedSubclasses() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public String getSimpleName() {
        return Companion.getClassSimpleName(getJClass());
    }

    @Override // kotlin.reflect.KClass
    public List<KType> getSupertypes() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public List<KTypeParameter> getTypeParameters() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public KVisibility getVisibility() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public int hashCode() {
        return JvmClassMappingKt.getJavaObjectType(this).hashCode();
    }

    @Override // kotlin.reflect.KClass
    public boolean isAbstract() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isCompanion() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isData() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isFinal() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isFun() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isInner() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    @SinceKotlin(version = "1.1")
    public boolean isInstance(Object r2) {
        return Companion.isInstance(r2, getJClass());
    }

    @Override // kotlin.reflect.KClass
    public boolean isOpen() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isSealed() {
        error();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KClass
    public boolean isValue() {
        error();
        throw new KotlinNothingValueException();
    }

    public String toString() {
        return getJClass().toString() + " (Kotlin reflection is not available)";
    }
}
