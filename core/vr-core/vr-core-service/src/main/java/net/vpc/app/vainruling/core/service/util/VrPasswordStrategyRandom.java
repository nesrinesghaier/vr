package net.vpc.app.vainruling.core.service.util;

import net.vpc.app.vainruling.core.service.model.AppContact;

public class VrPasswordStrategyRandom implements VrPasswordStrategy {
    public static final VrPasswordStrategy INSTANCE = new VrPasswordStrategyRandom();

    @Override
    public String generatePassword(AppContact contact) {
        String fn = contact.getFirstName();
        String ln = contact.getLastName();
        if (fn == null) {
            fn = "";
        }
        if (ln == null) {
            ln = "";
        }
        String fnlower = fn.toLowerCase();
        String[] fns = fnlower.split(" ");
        if (fns.length > 0 && fns[0].length() >= 3) {
            String p = fns[0];
            for (int i = 0; i < 4; i++) {
                int x = (int) (Math.random() * 10);
                p += x;
            }
            return p;
        }
//        if()
        String p = fnlower.replace(" ", "");
        for (int i = 0; i < 4; i++) {
            int x = (int) (Math.random() * 10);
            p += x;
        }
        return p;
    }
}
