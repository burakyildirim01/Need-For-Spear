package domain.abilities;

import java.util.*;

public enum AbilityTypes {
    INCREASECHANCE(0), EXPANSION(1), HEX(2), UNSTOPPABLE(3);

    private int i;

    private static final Map<Integer, AbilityTypes> map = new HashMap<>(values().length, 1);

    static {
        for (AbilityTypes atype : values())
            map.put(atype.i, atype);
    }

    private AbilityTypes(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public String getString() {
        switch (i) {
            case 0:
                return "IC";
            case 1:
                return "EXP";
            case 2:
                return "HEX";
            case 3:
                return "UNS";
            default:
                return "?";

        }
    }

    public static AbilityTypes getAbilityType(int i) {
        return map.get(i);
    }

}