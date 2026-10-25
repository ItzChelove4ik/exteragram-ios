package kotlin.jvm.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Function;
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
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.jvm.internal.markers.KMutableIterable;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.jvm.internal.markers.KMutableSet;

/* loaded from: classes.dex */
public class TypeIntrinsics {
    public TypeIntrinsics() {
    }

    public static Collection asMutableCollection(Object r1) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableCollection) == true) goto L8;
        throwCce(r1, "kotlin.collections.MutableCollection");
    L8:
        return castToCollection(r1);
    }

    public static Collection asMutableCollection(Object r1, String r2) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableCollection) == true) goto L8;
        throwCce(r2);
    L8:
        return castToCollection(r1);
    }

    public static Iterable asMutableIterable(Object r1) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableIterable) == true) goto L8;
        throwCce(r1, "kotlin.collections.MutableIterable");
    L8:
        return castToIterable(r1);
    }

    public static Iterable asMutableIterable(Object r1, String r2) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableIterable) == true) goto L8;
        throwCce(r2);
    L8:
        return castToIterable(r1);
    }

    public static Iterator asMutableIterator(Object r1) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableIterator) == true) goto L8;
        throwCce(r1, "kotlin.collections.MutableIterator");
    L8:
        return castToIterator(r1);
    }

    public static Iterator asMutableIterator(Object r1, String r2) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableIterator) == true) goto L8;
        throwCce(r2);
    L8:
        return castToIterator(r1);
    }

    public static List asMutableList(Object r1) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableList) == true) goto L8;
        throwCce(r1, "kotlin.collections.MutableList");
    L8:
        return castToList(r1);
    }

    public static List asMutableList(Object r1, String r2) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableList) == true) goto L8;
        throwCce(r2);
    L8:
        return castToList(r1);
    }

    public static ListIterator asMutableListIterator(Object r1) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableListIterator) == true) goto L8;
        throwCce(r1, "kotlin.collections.MutableListIterator");
    L8:
        return castToListIterator(r1);
    }

    public static ListIterator asMutableListIterator(Object r1, String r2) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableListIterator) == true) goto L8;
        throwCce(r2);
    L8:
        return castToListIterator(r1);
    }

    public static Map asMutableMap(Object r1) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableMap) == true) goto L8;
        throwCce(r1, "kotlin.collections.MutableMap");
    L8:
        return castToMap(r1);
    }

    public static Map asMutableMap(Object r1, String r2) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableMap) == true) goto L8;
        throwCce(r2);
    L8:
        return castToMap(r1);
    }

    public static Map.Entry asMutableMapEntry(Object r1) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableMap.Entry) == true) goto L8;
        throwCce(r1, "kotlin.collections.MutableMap.MutableEntry");
    L8:
        return castToMapEntry(r1);
    }

    public static Map.Entry asMutableMapEntry(Object r1, String r2) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableMap.Entry) == true) goto L8;
        throwCce(r2);
    L8:
        return castToMapEntry(r1);
    }

    public static Set asMutableSet(Object r1) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableSet) == true) goto L8;
        throwCce(r1, "kotlin.collections.MutableSet");
    L8:
        return castToSet(r1);
    }

    public static Set asMutableSet(Object r1, String r2) {
        if ((r1 instanceof KMappedMarker) == false) goto L8;
        if ((r1 instanceof KMutableSet) == true) goto L8;
        throwCce(r2);
    L8:
        return castToSet(r1);
    }

    public static Object beforeCheckcastToFunctionOfArity(Object r2, int r3) {
        if (r2 != null) goto L4;
    L6:
        return r2;
    L4:
        if (isFunctionOfArity(r2, r3) == true) goto L6;
        throwCce(r2, "kotlin.jvm.functions.Function" + r3);
        goto L6
    }

    public static Object beforeCheckcastToFunctionOfArity(Object r0, int r1, String r2) {
        if (r0 != null) goto L4;
    L6:
        return r0;
    L4:
        if (isFunctionOfArity(r0, r1) == true) goto L6;
        throwCce(r2);
        goto L6
    }

    public static Collection castToCollection(Object r0) {
        return (Collection) r0;
    L4:
        e = move-exception;
        throw throwCce(e);
    }

    public static Iterable castToIterable(Object r0) {
        return (Iterable) r0;
    L4:
        e = move-exception;
        throw throwCce(e);
    }

    public static Iterator castToIterator(Object r0) {
        return (Iterator) r0;
    L4:
        e = move-exception;
        throw throwCce(e);
    }

    public static List castToList(Object r0) {
        return (List) r0;
    L4:
        e = move-exception;
        throw throwCce(e);
    }

    public static ListIterator castToListIterator(Object r0) {
        return (ListIterator) r0;
    L4:
        e = move-exception;
        throw throwCce(e);
    }

    public static Map castToMap(Object r0) {
        return (Map) r0;
    L4:
        e = move-exception;
        throw throwCce(e);
    }

    public static Map.Entry castToMapEntry(Object r0) {
        return (Map.Entry) r0;
    L4:
        e = move-exception;
        throw throwCce(e);
    }

    public static Set castToSet(Object r0) {
        return (Set) r0;
    L4:
        e = move-exception;
        throw throwCce(e);
    }

    public static int getFunctionArity(Object r1) {
        if ((r1 instanceof FunctionBase) == false) goto L7;
        return ((FunctionBase) r1).getArity();
    L7:
        if ((r1 instanceof Function0) == false) goto L11;
        return 0;
    L11:
        if ((r1 instanceof Function1) == false) goto L15;
        return 1;
    L15:
        if ((r1 instanceof Function2) == false) goto L19;
        return 2;
    L19:
        if ((r1 instanceof Function3) == false) goto L23;
        return 3;
    L23:
        if ((r1 instanceof Function4) == false) goto L27;
        return 4;
    L27:
        if ((r1 instanceof Function5) == false) goto L31;
        return 5;
    L31:
        if ((r1 instanceof Function6) == false) goto L35;
        return 6;
    L35:
        if ((r1 instanceof Function7) == false) goto L39;
        return 7;
    L39:
        if ((r1 instanceof Function8) == false) goto L43;
        return 8;
    L43:
        if ((r1 instanceof Function9) == false) goto L47;
        return 9;
    L47:
        if ((r1 instanceof Function10) == false) goto L51;
        return 10;
    L51:
        if ((r1 instanceof Function11) == false) goto L55;
        return 11;
    L55:
        if ((r1 instanceof Function12) == false) goto L59;
        return 12;
    L59:
        if ((r1 instanceof Function13) == false) goto L63;
        return 13;
    L63:
        if ((r1 instanceof Function14) == false) goto L67;
        return 14;
    L67:
        if ((r1 instanceof Function15) == false) goto L71;
        return 15;
    L71:
        if ((r1 instanceof Function16) == false) goto L75;
        return 16;
    L75:
        if ((r1 instanceof Function17) == false) goto L79;
        return 17;
    L79:
        if ((r1 instanceof Function18) == false) goto L83;
        return 18;
    L83:
        if ((r1 instanceof Function19) == false) goto L87;
        return 19;
    L87:
        if ((r1 instanceof Function20) == false) goto L91;
        return 20;
    L91:
        if ((r1 instanceof Function21) == false) goto L95;
        return 21;
    L95:
        if ((r1 instanceof Function22) == false) goto L98;
        return 22;
    L98:
        return -1;
    }

    public static boolean isFunctionOfArity(Object r1, int r2) {
        if ((r1 instanceof Function) == true) goto L5;
        return false;
    L5:
        if (getFunctionArity(r1) != r2) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isMutableCollection(Object r1) {
        if ((r1 instanceof Collection) == true) goto L5;
        return false;
    L5:
        if ((r1 instanceof KMappedMarker) == true) goto L7;
        return true;
    L7:
        if ((r1 instanceof KMutableCollection) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static boolean isMutableIterable(Object r1) {
        if ((r1 instanceof Iterable) == true) goto L5;
        return false;
    L5:
        if ((r1 instanceof KMappedMarker) == true) goto L7;
        return true;
    L7:
        if ((r1 instanceof KMutableIterable) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static boolean isMutableIterator(Object r1) {
        if ((r1 instanceof Iterator) == true) goto L5;
        return false;
    L5:
        if ((r1 instanceof KMappedMarker) == true) goto L7;
        return true;
    L7:
        if ((r1 instanceof KMutableIterator) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static boolean isMutableList(Object r1) {
        if ((r1 instanceof List) == true) goto L5;
        return false;
    L5:
        if ((r1 instanceof KMappedMarker) == true) goto L7;
        return true;
    L7:
        if ((r1 instanceof KMutableList) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static boolean isMutableListIterator(Object r1) {
        if ((r1 instanceof ListIterator) == true) goto L5;
        return false;
    L5:
        if ((r1 instanceof KMappedMarker) == true) goto L7;
        return true;
    L7:
        if ((r1 instanceof KMutableListIterator) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static boolean isMutableMap(Object r1) {
        if ((r1 instanceof Map) == true) goto L5;
        return false;
    L5:
        if ((r1 instanceof KMappedMarker) == true) goto L7;
        return true;
    L7:
        if ((r1 instanceof KMutableMap) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static boolean isMutableMapEntry(Object r1) {
        if ((r1 instanceof Map.Entry) == true) goto L5;
        return false;
    L5:
        if ((r1 instanceof KMappedMarker) == true) goto L7;
        return true;
    L7:
        if ((r1 instanceof KMutableMap.Entry) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static boolean isMutableSet(Object r1) {
        if ((r1 instanceof Set) == true) goto L5;
        return false;
    L5:
        if ((r1 instanceof KMappedMarker) == true) goto L7;
        return true;
    L7:
        if ((r1 instanceof KMutableSet) == false) goto L13;
        return true;
    L13:
        return false;
    }

    private static <T extends Throwable> T sanitizeStackTrace(T r1) {
        return (T) Intrinsics.sanitizeStackTrace(r1, TypeIntrinsics.class.getName());
    }

    public static ClassCastException throwCce(ClassCastException r0) {
        throw ((ClassCastException) sanitizeStackTrace(r0));
    }

    public static void throwCce(Object r1, String r2) {
        if (r1 != null) goto L4;
        String r12 = "null";
    L5:
        throwCce(r12 + " cannot be cast to " + r2);
        return;
    L4:
        r12 = r1.getClass().getName();
        goto L5
    }

    public static void throwCce(String r1) {
        throw throwCce(new ClassCastException(r1));
    }
}
