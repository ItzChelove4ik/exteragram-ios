package org.scilab.forge.jlatexmath;

import java.lang.Character;
import java.net.URL;
import java.net.URLClassLoader;

/* loaded from: classes5.dex */
public class URLAlphabetRegistration implements AlphabetRegistration {
    private Character.UnicodeBlock[] blocks;
    private String language;
    private AlphabetRegistration pack;
    private URL url;

    private URLAlphabetRegistration(URL r2, String r3, Character.UnicodeBlock[] r4) {
        this.pack = null;
        this.url = r2;
        this.language = r3;
        this.blocks = r4;
    }

    public static void register(URL r1, String r2, Character.UnicodeBlock[] r3) {
        DefaultTeXFont.registerAlphabet(new URLAlphabetRegistration(r1, r2, r3));
    }

    @Override // org.scilab.forge.jlatexmath.AlphabetRegistration
    public Object getPackage() {
        URL[] r0 = {this.url};
        this.language = this.language.toLowerCase();
        StringBuilder r1 = new StringBuilder("org.scilab.forge.jlatexmath.");
        r1.append(this.language);
        r1.append(".");
        r1.append(Character.toString(Character.toUpperCase(this.language.charAt(0))));
        String r2 = this.language;
        r1.append(r2.substring(1, r2.length()));
        r1.append("Registration");
        AlphabetRegistration r02 = (AlphabetRegistration) Class.forName(r1.toString(), true, new URLClassLoader(r0)).newInstance();     // Catch: Exception -> L5 ClassNotFoundException -> L8
        this.pack = r02;     // Catch: Exception -> L5 ClassNotFoundException -> L8
        return r02;
    L5:
        e = move-exception;
        StringBuilder r22 = new StringBuilder("Problem in loading the class at ");
        r22.append(this.url);
        String r5 = e.getMessage();
        r22.append(" :\n");
        r22.append(r5);
        throw new AlphabetRegistrationException(r22.toString());
    L9:
        throw new AlphabetRegistrationException("Class at " + this.url + " cannot be got.");
    }

    @Override // org.scilab.forge.jlatexmath.AlphabetRegistration
    public String getTeXFontFileName() {
        return this.pack.getTeXFontFileName();
    }

    @Override // org.scilab.forge.jlatexmath.AlphabetRegistration
    public Character.UnicodeBlock[] getUnicodeBlock() {
        return this.blocks;
    }
}
