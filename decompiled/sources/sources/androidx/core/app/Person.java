package androidx.core.app;

import android.app.Person;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.core.graphics.drawable.IconCompat;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public class Person {
    IconCompat mIcon;
    boolean mIsBot;
    boolean mIsImportant;
    String mKey;
    CharSequence mName;
    String mUri;

    /* loaded from: classes3.dex */
    public static class Api22Impl {
        public static Person fromPersistableBundle(PersistableBundle r2) {
            return new Builder().setName(r2.getString("name")).setUri(r2.getString("uri")).setKey(r2.getString(PluginsConstants.Settings.KEY)).setBot(r2.getBoolean("isBot")).setImportant(r2.getBoolean("isImportant")).build();
        }

        public static PersistableBundle toPersistableBundle(Person r3) {
            PersistableBundle r0 = new PersistableBundle();
            CharSequence r1 = r3.mName;
            if (r1 == null) goto L5;
            String r12 = r1.toString();
        L6:
            r0.putString("name", r12);
            r0.putString("uri", r3.mUri);
            r0.putString(PluginsConstants.Settings.KEY, r3.mKey);
            r0.putBoolean("isBot", r3.mIsBot);
            r0.putBoolean("isImportant", r3.mIsImportant);
            return r0;
        L5:
            r12 = null;
            goto L6
        }
    }

    public static class Api28Impl {
        public static android.app.Person toAndroidPerson(Person r2) {
            Person.Builder r0 = new Person.Builder().setName(r2.getName());
            if (r2.getIcon() == null) goto L5;
            Icon r1 = r2.getIcon().toIcon();
        L7:
            return r0.setIcon(r1).setUri(r2.getUri()).setKey(r2.getKey()).setBot(r2.isBot()).setImportant(r2.isImportant()).build();
        L5:
            r1 = null;
            goto L7
        }
    }

    public static class Builder {
        IconCompat mIcon;
        boolean mIsBot;
        boolean mIsImportant;
        String mKey;
        CharSequence mName;
        String mUri;

        public Builder() {
        }

        public Person build() {
            return new Person(this);
        }

        public Builder setBot(boolean r1) {
            this.mIsBot = r1;
            return this;
        }

        public Builder setIcon(IconCompat r1) {
            this.mIcon = r1;
            return this;
        }

        public Builder setImportant(boolean r1) {
            this.mIsImportant = r1;
            return this;
        }

        public Builder setKey(String r1) {
            this.mKey = r1;
            return this;
        }

        public Builder setName(CharSequence r1) {
            this.mName = r1;
            return this;
        }

        public Builder setUri(String r1) {
            this.mUri = r1;
            return this;
        }
    }

    public Person(Builder r2) {
        this.mName = r2.mName;
        this.mIcon = r2.mIcon;
        this.mUri = r2.mUri;
        this.mKey = r2.mKey;
        this.mIsBot = r2.mIsBot;
        this.mIsImportant = r2.mIsImportant;
    }

    public static Person fromBundle(Bundle r3) {
        Bundle r0 = r3.getBundle("icon");
        Builder r1 = new Builder().setName(r3.getCharSequence("name"));
        if (r0 == null) goto L5;
        IconCompat r02 = IconCompat.createFromBundle(r0);
    L7:
        return r1.setIcon(r02).setUri(r3.getString("uri")).setKey(r3.getString(PluginsConstants.Settings.KEY)).setBot(r3.getBoolean("isBot")).setImportant(r3.getBoolean("isImportant")).build();
    L5:
        r02 = null;
        goto L7
    }

    public static Person fromPersistableBundle(PersistableBundle r0) {
        return Api22Impl.fromPersistableBundle(r0);
    }

    public boolean equals(Object r4) {
        if (r4 != null) goto L6;
        return false;
    L6:
        if ((r4 instanceof Person) == true) goto L8;
        return false;
    L8:
        Person r42 = (Person) r4;
        String r1 = getKey();
        String r2 = r42.getKey();
        if (r1 != null) goto L24;
        if (r2 != null) goto L24;
        if (Objects.equals(Objects.toString(getName()), Objects.toString(r42.getName())) == true) goto L15;
    L22:
        return false;
    L15:
        if (Objects.equals(getUri(), r42.getUri()) == false) goto L22;
        if (Boolean.valueOf(isBot()).equals(Boolean.valueOf(r42.isBot())) == false) goto L22;
        if (Boolean.valueOf(isImportant()).equals(Boolean.valueOf(r42.isImportant())) == false) goto L22;
        return true;
    L24:
        return Objects.equals(r1, r2);
    }

    public IconCompat getIcon() {
        return this.mIcon;
    }

    public String getKey() {
        return this.mKey;
    }

    public CharSequence getName() {
        return this.mName;
    }

    public String getUri() {
        return this.mUri;
    }

    public int hashCode() {
        String r0 = getKey();
        if (r0 == null) goto L7;
        return r0.hashCode();
    L7:
        return Objects.hash(new Object[]{getName(), getUri(), Boolean.valueOf(isBot()), Boolean.valueOf(isImportant())});
    }

    public boolean isBot() {
        return this.mIsBot;
    }

    public boolean isImportant() {
        return this.mIsImportant;
    }

    public String resolveToLegacyUri() {
        String r0 = this.mUri;
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (this.mName != null) goto L8;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        return "name:" + this.mName;
    }

    public android.app.Person toAndroidPerson() {
        return Api28Impl.toAndroidPerson(this);
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putCharSequence("name", this.mName);
        IconCompat r1 = this.mIcon;
        if (r1 == null) goto L5;
        Bundle r12 = r1.toBundle();
    L6:
        r0.putBundle("icon", r12);
        r0.putString("uri", this.mUri);
        r0.putString(PluginsConstants.Settings.KEY, this.mKey);
        r0.putBoolean("isBot", this.mIsBot);
        r0.putBoolean("isImportant", this.mIsImportant);
        return r0;
    L5:
        r12 = null;
        goto L6
    }

    public PersistableBundle toPersistableBundle() {
        return Api22Impl.toPersistableBundle(this);
    }
}
