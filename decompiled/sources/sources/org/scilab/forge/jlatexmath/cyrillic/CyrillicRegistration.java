package org.scilab.forge.jlatexmath.cyrillic;

import java.lang.Character;
import org.scilab.forge.jlatexmath.AlphabetRegistration;

/* loaded from: classes5.dex */
public class CyrillicRegistration implements AlphabetRegistration {
    public CyrillicRegistration() {
    }

    @Override // org.scilab.forge.jlatexmath.AlphabetRegistration
    public Object getPackage() {
        return this;
    }

    @Override // org.scilab.forge.jlatexmath.AlphabetRegistration
    public String getTeXFontFileName() {
        return "fonts/language_cyrillic.xml";
    }

    @Override // org.scilab.forge.jlatexmath.AlphabetRegistration
    public Character.UnicodeBlock[] getUnicodeBlock() {
        return new Character.UnicodeBlock[]{Character.UnicodeBlock.CYRILLIC};
    }
}
