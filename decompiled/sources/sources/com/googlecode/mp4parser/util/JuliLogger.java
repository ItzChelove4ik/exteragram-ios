package com.googlecode.mp4parser.util;

import java.util.logging.Level;

/* loaded from: classes5.dex */
public class JuliLogger extends Logger {
    java.util.logging.Logger logger;

    public JuliLogger(String r1) {
        this.logger = java.util.logging.Logger.getLogger(r1);
    }

    @Override // com.googlecode.mp4parser.util.Logger
    public void logDebug(String r2) {
        this.logger.log(Level.FINE, r2);
    }
}
