package androidx.car.app.messaging.model;

import androidx.core.app.Person;
import java.util.Objects;

/* loaded from: classes3.dex */
abstract class PersonsEqualityHelper {
    public static boolean arePersonsEqual(Person r4, Person r5) {
        if (r4 != null) goto L7;
        if (r5 != null) goto L7;
        return true;
    L7:
        if (r4 == null) goto L26;
        if (r5 == null) goto L26;
        String r2 = r4.getKey();
        String r3 = r5.getKey();
        if (r2 != null) goto L25;
        if (r3 != null) goto L25;
        if (Objects.equals(Objects.toString(r4.getName()), Objects.toString(r5.getName())) == true) goto L17;
    L23:
        return false;
    L17:
        if (Objects.equals(r4.getUri(), r5.getUri()) == false) goto L23;
        if (Boolean.valueOf(r4.isBot()).equals(Boolean.valueOf(r5.isBot())) == false) goto L23;
        if (Boolean.valueOf(r4.isImportant()).equals(Boolean.valueOf(r5.isImportant())) == false) goto L23;
        return true;
    L25:
        return Objects.equals(r2, r3);
    L26:
        return false;
    }

    public static int getPersonHashCode(Person r3) {
        if (r3 != null) goto L5;
        return 0;
    L5:
        String r0 = r3.getKey();
        if (r0 == null) goto L10;
        return r0.hashCode();
    L10:
        return Objects.hash(new Object[]{r3.getName(), r3.getUri(), Boolean.valueOf(r3.isBot()), Boolean.valueOf(r3.isImportant())});
    }
}
