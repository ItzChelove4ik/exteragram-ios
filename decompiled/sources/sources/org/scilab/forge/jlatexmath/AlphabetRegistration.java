package org.scilab.forge.jlatexmath;

import java.lang.Character;

/* loaded from: classes5.dex */
public interface AlphabetRegistration {
    public static final Character.UnicodeBlock[] JLM_CYRILLIC = null;
    public static final Character.UnicodeBlock[] JLM_GREEK = null;

    static {
        JLM_GREEK = new Character.UnicodeBlock[]{Character.UnicodeBlock.GREEK, Character.UnicodeBlock.GREEK_EXTENDED};
        JLM_CYRILLIC = new Character.UnicodeBlock[]{Character.UnicodeBlock.CYRILLIC};
    }

    Object getPackage();

    String getTeXFontFileName();

    Character.UnicodeBlock[] getUnicodeBlock();
}
