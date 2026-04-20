package Domain;

import java.util.Map;

public class PersonPercepion {
    private Map<WeirdnessType, Float> perseption;

    public PersonPercepion(Map<WeirdnessType, Float> perseption){
        this.perseption = perseption;
    }

    public Map<WeirdnessType, Float> getPerseption(){
        return this.perseption;
    }
}
