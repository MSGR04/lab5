package se.ifmo.core.collection;

import se.ifmo.core.collection.objects.HumanBeing;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;

public class Resource extends LinkedHashMap<Long, HumanBeing> {
    private final Date initDate = new Date();

    public Date getInitDate() {
        return initDate;
    }

    public Resource(HumanBeing... humanBeings) {
        for (HumanBeing humanBeing : humanBeings) {
            if (humanBeing.getId() == null) put(humanBeing);
            else put(humanBeing.getId(), humanBeing);
        }
    }

    public long put(HumanBeing humanBeing) {
        long id = isEmpty() ? 1 : Collections.max(keySet()) + 1;
        humanBeing.setId(id);
        put(id, humanBeing); //закидываю в коллецию элементы
        return id;
    }
}
