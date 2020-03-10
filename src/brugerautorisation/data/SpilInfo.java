package brugerautorisation.data;

import java.util.ArrayList;

public class SpilInfo {

    private final Integer ID;
    private String ordet;
    private String synligtOrd;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;

    public SpilInfo(Integer ID, String ordet, String synligtOrd, int antalForkerteBogstaver, boolean sidsteBogstavVarKorrekt, boolean spilletErVundet, boolean spilletErTabt) {
        this.ID = ID;
        this.ordet = ordet;
        this.synligtOrd = synligtOrd;
        this.antalForkerteBogstaver = antalForkerteBogstaver;
        this.sidsteBogstavVarKorrekt = sidsteBogstavVarKorrekt;
        this.spilletErVundet = spilletErVundet;
        this.spilletErTabt = spilletErTabt;
    }

    public Integer getID() {
        return ID;
    }

    public String getOrdet() {
        return ordet;
    }

    public String getSynligtOrd() {
        return synligtOrd;
    }

    public int getAntalForkerteBogstaver() {
        return antalForkerteBogstaver;
    }

    public boolean isSidsteBogstavVarKorrekt() {
        return sidsteBogstavVarKorrekt;
    }

    public boolean isSpilletErVundet() {
        return spilletErVundet;
    }

    public boolean isSpilletErTabt() {
        return spilletErTabt;
    }
}
