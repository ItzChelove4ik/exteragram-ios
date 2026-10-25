package org.scilab.forge.jlatexmath;

import java.lang.Character;
import org.scilab.forge.jlatexmath.cyrillic.CyrillicRegistration;
import org.scilab.forge.jlatexmath.greek.GreekRegistration;

/* loaded from: classes5.dex */
public class WebStartAlphabetRegistration implements AlphabetRegistration {
    private Character.UnicodeBlock[] blocks;
    private AlphabetRegistration reg;

    private WebStartAlphabetRegistration(Character.UnicodeBlock[] r1) {
        this.blocks = r1;
    }

    public static void register(Character.UnicodeBlock[] r1) {
        DefaultTeXFont.registerAlphabet(new WebStartAlphabetRegistration(r1));
    }

    @Override // org.scilab.forge.jlatexmath.AlphabetRegistration
    public Object getPackage() {
        Character.UnicodeBlock[] r0 = this.blocks;
        if (r0 != AlphabetRegistration.JLM_GREEK) goto L6;
        this.reg = new GreekRegistration();
    L9:
        return this.reg;
    L6:
        if (r0 != AlphabetRegistration.JLM_CYRILLIC) goto L11;
        this.reg = new CyrillicRegistration();
        goto L9
    L11:
        throw new AlphabetRegistrationException("Invalid Unicode Block");
    }

    @Override // org.scilab.forge.jlatexmath.AlphabetRegistration
    public String getTeXFontFileName() {
        return this.reg.getTeXFontFileName();
    }

    @Override // org.scilab.forge.jlatexmath.AlphabetRegistration
    public Character.UnicodeBlock[] getUnicodeBlock() {
        return this.blocks;
    }
}
